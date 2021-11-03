CREATE OR REPLACE PACKAGE SCH_MST.PKG_REIM_UPLD AS
-- ****************************************************************************
-- Package name         : PKG_REIM_UPLD
-- Language/Shell       : PL/SQL
-- Description          : This Package is used to Upload ReIM Data through an 
--                        Excel File from UI and Insert the data into ReIM 
--                        Database Tables.
-- Returns              : None
-- Return Values        : None
--
-- Called from scripts/ : None
-- programs(if any)     :
--
-- Execute Mode
-- (batch/interactive)  : Batch
--
-- Author               : Bikash , Infosys Technologies Ltd.
-- Date written         : 01-Feb-2016
--
-- Modification History :
--
-- Description of change                 Date           Modified by
-- ---------------------                 ----           -----------
-- ****************************************************************************
-------------------------------------------------------------------------------
--Function To Load NRD_UPLD_INPUT STAGE table, Validate Data
FUNCTION  FN_REIM_UPLD(I_TAB_TYPE_REIM_ATTR       IN     TAB_TYPE_REIM_ATTR,
                       I_USER_ID                  IN     NRD_UPLD_INPUT_STG.USER_ID%TYPE,
                       I_FILE_NAME                IN     NRD_UPLD_INPUT_STG.FILE_NAME%TYPE,
                       I_UPLOAD_DATE              IN     DATE,
                       O_SUCCESS_COUNT            OUT    VARCHAR2,
                       O_FAILURE_COUNT            OUT    VARCHAR2
)
RETURN VARCHAR2;

--Function to Validate data through RSE Service Call
FUNCTION FN_VALIDATE_DATA(I_upld_id            IN     NUMBER)
RETURN BOOLEAN;

--Function to update the cost components with the Prorated values
FUNCTION FN_UPD_CALC_COMP(I_comp       IN             VARCHAR2,
                          I_amount     IN             VARCHAR2,
                          I_seq_no     IN             NUMBER)
RETURN BOOLEAN;

-- Function to Calculate Cost Components and Allowance    
FUNCTION FN_CALC_DATA(O_error_msg            OUT    VARCHAR2)
RETURN BOOLEAN;
--------------------------------------------------------------------------------------------------------------------------
END PKG_REIM_UPLD ;
/

CREATE OR REPLACE PACKAGE BODY SCH_MST.PKG_REIM_UPLD
AS
---------------------------------------------------------------------------------------------------------------------------
--Function to load Staging table and perform validations
FUNCTION FN_REIM_UPLD ( I_TAB_TYPE_REIM_ATTR       IN     TAB_TYPE_REIM_ATTR,
                       I_user_id                  IN     NRD_UPLD_INPUT_STG.USER_ID%TYPE,
                       I_file_name                IN     NRD_UPLD_INPUT_STG.FILE_NAME%TYPE,
                       I_upload_date              IN     DATE,
                       O_success_count            OUT    VARCHAR2,
                       O_failure_count            OUT    VARCHAR2
                     )
   RETURN VARCHAR2
IS

   L_file_exist     VARCHAR2(1);
   L_upld_id        NRD_UPLD_FILE_NAME_LKP.FILE_NAME_SEQ%TYPE;
   L_doc_code       nrd_upld_input_stg.doc_code%TYPE;
   L_check_flag     VARCHAR2(1):='N';
   L_ins_flag       VARCHAR2(1):='N';
   L_dup_flag       NUMBER:=0;
   L_vdate          DATE;
   L_doc_type       VARCHAR2(90);
   L_vendor_type    VARCHAR2(6);
   L_vendor_type_2  VARCHAR2(6);
   L_count          NUMBER;
   L_discr_hdr_dtl  NUMBER;
   L_errm           VARCHAR2(1000);
   L_count_item     NUMBER;
   

   CURSOR c_check_file IS
   SELECT 'Y' 
     FROM nrd_upld_file_name_lkp
    WHERE file_name=I_file_name;
    
   
   CURSOR c_check_dup(i NUMBER) IS
   SELECT count(1)
     FROM nrd_upld_input_stg
    WHERE I_TAB_TYPE_REIM_ATTR(i).vendor=vendor
      AND TO_DATE(TRIM(I_TAB_TYPE_REIM_ATTR(i).doc_date),'MM/DD/YYYY')=doc_date
      AND I_TAB_TYPE_REIM_ATTR(i).ext_doc_id=ext_doc_id
      AND NVL(I_TAB_TYPE_REIM_ATTR(i).item,0)=NVL(item,0)
      AND status_code='L';
   
   
   CURSOR C_check_dup2(i NUMBER) IS
   SELECT 'Y'
     FROM nrd_upld_input_stg
    WHERE vendor=I_TAB_TYPE_REIM_ATTR(i).vendor
      AND doc_date=TO_DATE(I_TAB_TYPE_REIM_ATTR(i).doc_date,'MM/DD/YYYY')
      AND ext_doc_id=I_TAB_TYPE_REIM_ATTR(i).ext_doc_id
      AND status_code='V';
   
   --Merchandise Header cost must be equal to sum of detail cost
   CURSOR C_val_hdr_dtl_mrch_cost(i NUMBER) IS
   SELECT MAX(NVL(total_cost_excl_tax,0))-MAX(NVL(total_nonmerch_cost,0))-SUM(NVL(unit_cost,0)*NVL(invoice_qty,0)),
          count(item)
     FROM nrd_upld_input_stg
    WHERE vendor=I_TAB_TYPE_REIM_ATTR(i).vendor
      AND doc_date=TO_DATE(I_TAB_TYPE_REIM_ATTR(i).doc_date,'MM/DD/YYYY')
      AND ext_doc_id=I_TAB_TYPE_REIM_ATTR(i).ext_doc_id
      AND status_code='L';
   
   --Total Non Merchandise Header cost must be equal to sum of cost components
   CURSOR C_val_hdr_dtl_nonmrch_cost(i NUMBER) IS
   SELECT MAX(NVL(total_nonmerch_cost,0))-(SUM
          (NVL(cost_amount1,0)+
           NVL(cost_amount2,0)+
           NVL(cost_amount3,0)+
           NVL(cost_amount4,0)+
           NVL(cost_amount5,0)+
           NVL(cost_amount6,0)+
           NVL(cost_amount7,0)+
           NVL(cost_amount8,0)+
           NVL(cost_amount9,0)+
           NVL(cost_amount10,0)))
     FROM nrd_upld_input_stg
    WHERE vendor=I_TAB_TYPE_REIM_ATTR(i).vendor
      AND doc_date=TO_DATE(I_TAB_TYPE_REIM_ATTR(i).doc_date,'MM/DD/YYYY')
      AND ext_doc_id=I_TAB_TYPE_REIM_ATTR(i).ext_doc_id
      AND status_code='L';
   
   --Validate Doc_code
   CURSOR C_val_doc_code IS
   SELECT 'Y'
     FROM INVC_DOC_TYPE_CATEGORY_MAP
    WHERE invoice_type=L_doc_type
      AND category_type_id=L_doc_code;
   
   --Get data to Update total_qty in case of Credit/Debit Memo
   --If item details are present update total_qty with same as the sum of detail qty
   CURSOR C_get_hdr_qty_crd_deb IS
   SELECT seq_no,upload_id,
           COUNT(item) OVER (PARTITION BY ext_doc_id,doc_date,vendor) count_item,
           SUM(NVL(invoice_qty,0)) OVER (PARTITION BY ext_doc_id,doc_date,vendor) sum_qty
      FROM nrd_upld_input_stg 
     WHERE type in ('CRDMEC','DEBMEC')
       AND status_code in ('L','V');
   
   TYPE Tab_type_crd_deb_qty IS TABLE OF C_get_hdr_qty_crd_deb%ROWTYPE;
   L_tab_crd_deb_qty Tab_type_crd_deb_qty;
      
   TYPE Tab_type_seq IS TABLE OF nrd_upld_input_stg.seq_no%TYPE
   INDEX BY BINARY_INTEGER;
   TYPE Tab_type_status IS TABLE OF nrd_upld_input_stg.status_code%TYPE
   INDEX BY BINARY_INTEGER;
   TYPE Tab_type_status_desc IS TABLE OF nrd_upld_input_stg.status_description%TYPE
   INDEX BY BINARY_INTEGER;
   TYPE Tab_type_cust_ref1 IS TABLE OF nrd_upld_input_stg.custom_doc_ref1%TYPE
   INDEX BY BINARY_INTEGER;
   TYPE Tab_type_cust_ref2 IS TABLE OF nrd_upld_input_stg.custom_doc_ref2%TYPE
   INDEX BY BINARY_INTEGER;
   TYPE Tab_type_cust_ref3 IS TABLE OF nrd_upld_input_stg.custom_doc_ref3%TYPE
   INDEX BY BINARY_INTEGER;
   TYPE Tab_type_cust_ref4 IS TABLE OF nrd_upld_input_stg.custom_doc_ref4%TYPE
   INDEX BY BINARY_INTEGER;
   
   
   L_tab_seq_no Tab_type_seq;
   L_tab_status Tab_type_status;
   L_tab_status_desc Tab_type_status_desc;
   L_tab_cust_ref1 Tab_type_cust_ref1;
   L_tab_cust_ref2 Tab_type_cust_ref2;
   L_tab_cust_ref3 Tab_type_cust_ref3;
   L_tab_cust_ref4 Tab_type_cust_ref4;

BEGIN
   
   SELECT vdate
     INTO L_vdate
     FROM mv_period;
     
   OPEN c_check_file;
   FETCH c_check_file INTO L_file_exist;
   CLOSE c_check_file;
   
   IF L_file_exist='Y' THEN
      O_success_count:=0;
      O_FAILURE_COUNT:=I_TAB_TYPE_REIM_ATTR.COUNT;
      RETURN '2'; -- Duplicate File
   END IF;
   
   SELECT UPLOAD_ID_SEQ.NEXTVAL
     INTO L_upld_id
     FROM DUAL;
   
   IF (I_TAB_TYPE_REIM_ATTR.COUNT = 0) THEN
      RETURN '3';
   ELSE   
      FORALL i in 1..I_TAB_TYPE_REIM_ATTR.COUNT
      INSERT INTO nrd_upld_input_stg
                  (seq_no
                  ,user_id
                  ,file_name
                  ,upload_id
                  ,ext_doc_id
                  ,doc_date
                  ,type
                  ,vendor_type
                  ,vendor
                  ,ext_doc_id_2
                  ,doc_date_2
                  ,vendor_type_2
                  ,vendor_2
                  ,vendor_name_2
                  ,supplier
                  ,supplier_site_id
                  ,order_no
                  ,currency_code
                  ,terms
                  ,location
                  ,custom_doc_ref1
                  ,custom_doc_ref2
                  ,custom_doc_ref3
                  ,custom_doc_ref4
                  ,scan_no
                  ,scan_date
                  ,oracle_reason_code
                  ,gl_desc
                  ,shipment_ref_no
                  ,wire_transfer_no
                  ,bank_exchange_rate
                  ,cost_allocation
                  ,doc_code
                  ,dept
                  ,date_from
                  ,date_to
                  ,auth_no
                  ,noap
                  ,rtv_number
                  ,total_nonmerch_cost
                  ,total_cost_excl_tax
                  ,tax_amount
                  ,total_qty
                  ,allowance_amt_hdr
                  ,allowance_perc_hdr
                  ,prepaid_ind
                  ,text_i
                  ,text_e
                  ,item
                  ,item_description
                  ,vpn_number
                  ,vpn_description
                  ,invoice_qty
                  ,unit_cost
                  ,reason_code
                  ,allowance_amt_det
                  ,allowance_perc_det
                  ,invoice_qty_det
                  ,duty
                  ,unit_of_measure
                  ,cost_component1
                  ,cost_amount1
                  ,cost_component2
                  ,cost_amount2
                  ,cost_component3
                  ,cost_amount3
                  ,cost_component4
                  ,cost_amount4
                  ,cost_component5
                  ,cost_amount5
                  ,cost_component6
                  ,cost_amount6
                  ,cost_component7
                  ,cost_amount7
                  ,cost_component8
                  ,cost_amount8
                  ,cost_component9
                  ,cost_amount9
                  ,cost_component10
                  ,cost_amount10
                  ,entry_ip
                  ,ext_doc_id_ip
                  ,broker_file_ip
                  ,invoice_date_ip
                  ,manuf_name_ip
                  ,manuf_comm_inv_ip
                  ,manuf_comm_inv_val_ip
                  ,manuf_comm_inv_po_ip
                  ,extended_cost
                  ,upload_date
                  ,status_code)
           VALUES (NRD_UPLD_INPUT_STG_SEQ.NEXTVAL
                  ,UPPER(I_user_id)
                  ,I_file_name
                  ,L_upld_id
                  ,I_TAB_TYPE_REIM_ATTR(i).ext_doc_id
                  ,TO_DATE(TRIM(I_TAB_TYPE_REIM_ATTR(i).doc_date),'MM/DD/YYYY')
                  ,DECODE(TRIM(I_TAB_TYPE_REIM_ATTR(i).type),'Merchandise','MRCHI'
                                                            ,'Non Merchandise','NMRCHI'
                                                            ,'Credit Memo Cost','CRDMEC'
                                                            ,'Debit Memo Cost','DEBMEC'
                                                            ,I_TAB_TYPE_REIM_ATTR(i).type)
                  ,REGEXP_SUBSTR(I_TAB_TYPE_REIM_ATTR(i).vendor_type, '[^-]+', 1, 2)
                  ,I_TAB_TYPE_REIM_ATTR(i).vendor
                  ,I_TAB_TYPE_REIM_ATTR(i).ext_doc_id_2
                  ,TO_DATE(TRIM(I_TAB_TYPE_REIM_ATTR(i).doc_date_2),'MM/DD/YYYY')
                  ,REGEXP_SUBSTR(I_TAB_TYPE_REIM_ATTR(i).vendor_type_2, '[^-]+', 1, 2)
                  ,I_TAB_TYPE_REIM_ATTR(i).vendor_2
                  ,I_TAB_TYPE_REIM_ATTR(i).vendor_name_2
                  ,I_TAB_TYPE_REIM_ATTR(i).supplier
                  ,I_TAB_TYPE_REIM_ATTR(i).supplier_site_id
                  ,TO_NUMBER(TRIM(I_TAB_TYPE_REIM_ATTR(i).order_no))
                  ,I_TAB_TYPE_REIM_ATTR(i).currency_code
                  ,I_TAB_TYPE_REIM_ATTR(i).terms
                  ,TO_NUMBER(TRIM(I_TAB_TYPE_REIM_ATTR(i).location))
                  ,NULL--custom_doc_ref1
                  ,NULL--custom_doc_ref2
                  ,NULL--custom_doc_ref3
                  ,NULL--custom_doc_ref4
                  ,I_TAB_TYPE_REIM_ATTR(i).scan_no
                  ,I_TAB_TYPE_REIM_ATTR(i).scan_date
                  ,I_TAB_TYPE_REIM_ATTR(i).oracle_reason_code
                  ,I_TAB_TYPE_REIM_ATTR(i).gl_desc
                  ,I_TAB_TYPE_REIM_ATTR(i).shipment_ref_no
                  ,I_TAB_TYPE_REIM_ATTR(i).wire_transfer_no
                  ,TO_NUMBER(TRIM(I_TAB_TYPE_REIM_ATTR(i).bank_exchange_rate))
                  ,TRIM(I_TAB_TYPE_REIM_ATTR(i).cost_allocation)
                  ,I_TAB_TYPE_REIM_ATTR(i).doc_code
                  ,TO_NUMBER(TRIM(I_TAB_TYPE_REIM_ATTR(i).dept))
                  ,TO_DATE(TRIM(I_TAB_TYPE_REIM_ATTR(i).date_from),'MM/DD/YYYY')
                  ,TO_DATE(TRIM(I_TAB_TYPE_REIM_ATTR(i).date_to),'MM/DD/YYYY')
                  ,I_TAB_TYPE_REIM_ATTR(i).auth_no
                  ,I_TAB_TYPE_REIM_ATTR(i).noap
                  ,TO_NUMBER(TRIM(I_TAB_TYPE_REIM_ATTR(i).rtv_number))
                  ,NVL(TO_NUMBER(TRIM(I_TAB_TYPE_REIM_ATTR(i).total_nonmerch_cost)),0)
                  ,NVL(TO_NUMBER(TRIM(I_TAB_TYPE_REIM_ATTR(i).total_cost_excl_tax)),0)
                  ,NVL(TO_NUMBER(TRIM(I_TAB_TYPE_REIM_ATTR(i).tax_amount)),0)
                  ,NVL(TO_NUMBER(TRIM(I_TAB_TYPE_REIM_ATTR(i).total_qty)),0)
                  ,TO_NUMBER(TRIM(I_TAB_TYPE_REIM_ATTR(i).allowance_amt_hdr))
                  ,TO_NUMBER(TRIM(I_TAB_TYPE_REIM_ATTR(i).allowance_perc_hdr))
                  ,I_TAB_TYPE_REIM_ATTR(i).prepaid_flag
                  ,I_TAB_TYPE_REIM_ATTR(i).text_i
                  ,I_TAB_TYPE_REIM_ATTR(i).text_e
                  ,I_TAB_TYPE_REIM_ATTR(i).item
                  ,I_TAB_TYPE_REIM_ATTR(i).item_description
                  ,I_TAB_TYPE_REIM_ATTR(i).vpn_number
                  ,I_TAB_TYPE_REIM_ATTR(i).vpn_description
                  ,TO_NUMBER(TRIM(I_TAB_TYPE_REIM_ATTR(i).invoice_qty))
                  ,TO_NUMBER(TRIM(I_TAB_TYPE_REIM_ATTR(i).unit_cost))
                  ,I_TAB_TYPE_REIM_ATTR(i).reason_code
                  ,TO_NUMBER(TRIM(I_TAB_TYPE_REIM_ATTR(i).allowance_amt_det))
                  ,TO_NUMBER(TRIM(I_TAB_TYPE_REIM_ATTR(i).allowance_perc_det))
                  ,TO_NUMBER(TRIM(I_TAB_TYPE_REIM_ATTR(i).invoice_qty_det))
                  ,TO_NUMBER(TRIM(I_TAB_TYPE_REIM_ATTR(i).duty))
                  ,TO_NUMBER(TRIM(I_TAB_TYPE_REIM_ATTR(i).unit_of_measure))
                  ,I_TAB_TYPE_REIM_ATTR(i).cost_component1
                  ,TO_NUMBER(TRIM(I_TAB_TYPE_REIM_ATTR(i).cost_amount1))
                  ,I_TAB_TYPE_REIM_ATTR(i).cost_component2
                  ,TO_NUMBER(TRIM(I_TAB_TYPE_REIM_ATTR(i).cost_amount2))
                  ,I_TAB_TYPE_REIM_ATTR(i).cost_component3
                  ,TO_NUMBER(TRIM(I_TAB_TYPE_REIM_ATTR(i).cost_amount3))
                  ,I_TAB_TYPE_REIM_ATTR(i).cost_component4
                  ,TO_NUMBER(TRIM(I_TAB_TYPE_REIM_ATTR(i).cost_amount4))
                  ,I_TAB_TYPE_REIM_ATTR(i).cost_component5
                  ,TO_NUMBER(TRIM(I_TAB_TYPE_REIM_ATTR(i).cost_amount5))
                  ,I_TAB_TYPE_REIM_ATTR(i).cost_component6
                  ,TO_NUMBER(TRIM(I_TAB_TYPE_REIM_ATTR(i).cost_amount6))
                  ,I_TAB_TYPE_REIM_ATTR(i).cost_component7
                  ,TO_NUMBER(TRIM(I_TAB_TYPE_REIM_ATTR(i).cost_amount7))
                  ,I_TAB_TYPE_REIM_ATTR(i).cost_component8
                  ,TO_NUMBER(TRIM(I_TAB_TYPE_REIM_ATTR(i).cost_amount8))
                  ,I_TAB_TYPE_REIM_ATTR(i).cost_component9
                  ,TO_NUMBER(TRIM(I_TAB_TYPE_REIM_ATTR(i).cost_amount9))
                  ,I_TAB_TYPE_REIM_ATTR(i).cost_component10
                  ,TO_NUMBER(TRIM(I_TAB_TYPE_REIM_ATTR(i).cost_amount10))
                  ,TO_NUMBER(TRIM(I_TAB_TYPE_REIM_ATTR(i).entry_ip))
                  ,I_TAB_TYPE_REIM_ATTR(i).ext_doc_id_ip
                  ,I_TAB_TYPE_REIM_ATTR(i).broker_file_ip
                  ,TO_DATE(TRIM(I_TAB_TYPE_REIM_ATTR(i).invoice_date_ip),'MM/DD/YYYY')
                  ,I_TAB_TYPE_REIM_ATTR(i).manuf_name_ip
                  ,TO_NUMBER(TRIM(I_TAB_TYPE_REIM_ATTR(i).manuf_comm_inv_ip))
                  ,TO_NUMBER(TRIM(I_TAB_TYPE_REIM_ATTR(i).manuf_comm_inv_val_ip))
                  ,TO_NUMBER(TRIM(I_TAB_TYPE_REIM_ATTR(i).manuf_comm_inv_po_ip))
                  ,TO_NUMBER(TRIM(I_TAB_TYPE_REIM_ATTR(i).extended_cost))
                  ,I_upload_date
                  ,'L') RETURNING seq_no BULK COLLECT INTO L_tab_seq_no;
      L_ins_flag:='Y';
      INSERT INTO NRD_UPLD_FILE_NAME_LKP
                  (file_name_seq
                  ,file_name)
           VALUES (L_upld_id
                  ,I_file_name);
      
      OPEN C_get_hdr_qty_crd_deb;
      FETCH C_get_hdr_qty_crd_deb BULK COLLECT INTO L_tab_crd_deb_qty;
      CLOSE C_get_hdr_qty_crd_deb;
      
      FORALL i IN 1..L_tab_crd_deb_qty.COUNT
      UPDATE nrd_upld_input_stg
         SET total_qty=DECODE(L_tab_crd_deb_qty(i).count_item,0,0,L_tab_crd_deb_qty(i).sum_qty)
       WHERE seq_no=L_tab_crd_deb_qty(i).seq_no;
      
      FOR i IN 1 .. I_TAB_TYPE_REIM_ATTR.COUNT
      LOOP
         L_dup_flag:=0;
         L_doc_code:=I_TAB_TYPE_REIM_ATTR(i).doc_code;
         L_tab_status(i):='L';
         L_tab_status_desc(i):=NULL;
         
         SELECT DECODE(I_TAB_TYPE_REIM_ATTR(i).type,'Merchandise','MRCHI'
                                                   ,'Non Merchandise','NMRCHI'
                                                   ,'Credit Memo Cost','CRDMEC'
                                                   ,'Debit Memo Cost','DEBMEC'
                                                   ,I_TAB_TYPE_REIM_ATTR(i).type),
                REGEXP_SUBSTR(I_TAB_TYPE_REIM_ATTR(i).vendor_type, '[^-]+', 1, 2),
                REGEXP_SUBSTR(I_TAB_TYPE_REIM_ATTR(i).vendor_type_2, '[^-]+', 1, 2)
           INTO L_doc_type,
                L_vendor_type,
                L_vendor_type_2
           FROM dual; 
         
         L_check_flag:='N';
         OPEN C_check_dup2(i);
         FETCH C_check_dup2 INTO L_check_flag;
         CLOSE C_check_dup2;
         IF L_check_flag ='Y' THEN  
            L_tab_status(i):='E';
            L_tab_status_desc(i):=L_tab_status_desc(i)||' | Duplicate Invoice';
         END IF;
         
         OPEN C_check_dup(i);
         FETCH C_check_dup INTO L_dup_flag;
         CLOSE C_check_dup;
         IF L_dup_flag >1 THEN  
            L_tab_status(i):='E';
            L_tab_status_desc(i):=L_tab_status_desc(i)||' | Duplicate Invoice';
         END IF;
         
         IF TO_DATE(TRIM(I_TAB_TYPE_REIM_ATTR(i).doc_date),'MM/DD/YYYY')>L_vdate THEN
            L_tab_status(i):='E';
            L_tab_status_desc(i):=L_tab_status_desc(i)||' | Document Date Cannot be in future';
         END IF;
         
         IF REGEXP_LIKE(L_doc_type,'[^[:alnum:]]') THEN
            L_tab_status(i):='E';
            L_tab_status_desc(i):=L_tab_status_desc(i)||' | Document Type has special characters';
         END IF;
         
         IF REGEXP_LIKE(L_vendor_type,'[^[:alnum:]]') THEN
            L_tab_status(i):='E';
            L_tab_status_desc(i):=L_tab_status_desc(i)||' | Vendor Type has special characters';
         ELSIF (L_doc_type='MRCHI' AND L_vendor_type<>'SUPP')
         OR (L_doc_type='NMRCHI' AND L_vendor_type NOT IN ('BR','AG','CO','FF','SUPP'))  THEN
            L_tab_status(i):='E';
            L_tab_status_desc(i):=L_tab_status_desc(i)||' | Vendor type not compatible with Document type';
         END IF;
         
         IF TO_DATE(TRIM(I_TAB_TYPE_REIM_ATTR(i).doc_date_2),'MM/DD/YYYY')>L_vdate THEN
            L_tab_status(i):='E';
            L_tab_status_desc(i):=L_tab_status_desc(i)||' | 2nd Document Date Cannot be in future';
         END IF;
         
         IF REGEXP_LIKE(L_vendor_type_2,'[^[:alnum:]]') THEN
            L_tab_status(i):='E';
            L_tab_status_desc(i):=L_tab_status_desc(i)||' | Vendor Type has special characters';
         ELSIF (L_doc_type='MRCHI' AND L_vendor_type<>'SUPP')
         OR (L_doc_type='NMRCHI' AND L_vendor_type NOT IN ('BR','AG','CO','FF','SUPP'))  THEN
            L_tab_status(i):='E';
            L_tab_status_desc(i):=L_tab_status_desc(i)||' | Vendor type not compatible with Document type';
         END IF;
         
         IF L_doc_type<>'NMRCHI'  THEN
            OPEN C_val_hdr_dtl_mrch_cost(i);
            FETCH C_val_hdr_dtl_mrch_cost INTO L_discr_hdr_dtl,L_count_item;
            CLOSE C_val_hdr_dtl_mrch_cost;
            IF L_count_item>0 AND L_discr_hdr_dtl<>0 THEN
               L_tab_status(i):='E';
               L_tab_status_desc(i):=L_tab_status_desc(i)||' | Merchandise Header Cost Doesnt match with Total Detail Cost';
            END IF;
         END IF;
         
         OPEN C_val_hdr_dtl_nonmrch_cost(i);
         FETCH C_val_hdr_dtl_nonmrch_cost INTO L_discr_hdr_dtl;
         CLOSE C_val_hdr_dtl_nonmrch_cost;
         IF L_count_item>0 AND L_discr_hdr_dtl<>0 THEN
            L_tab_status(i):='E';
            L_tab_status_desc(i):=L_tab_status_desc(i)||' | Non Merchandise Header Cost Doesnt match with Total Cost Components';
         END IF;
         
         IF I_TAB_TYPE_REIM_ATTR(i).currency_code NOT IN ('USD','CAD') 
            AND NOT REGEXP_LIKE (I_TAB_TYPE_REIM_ATTR(i).bank_exchange_rate,'^-?\d+(\.\d+)?$') THEN
            L_tab_status(i):='E';
            L_tab_status_desc(i):=L_tab_status_desc(i)||' | Invalid Exchange Rate';
         END IF;
         
         IF L_doc_type='DEBMEC' AND TO_NUMBER(TRIM(I_TAB_TYPE_REIM_ATTR(i).total_cost_excl_tax))>=0 THEN
            L_tab_status(i):='E';
            L_tab_status_desc(i):=L_tab_status_desc(i)||' | Total Cost Must be Negative for Debit Memo Cost Invoice';
         END IF;
         
         IF L_doc_type='MRCHI' AND TO_NUMBER(TRIM(I_TAB_TYPE_REIM_ATTR(i).total_qty))=0 THEN
            L_tab_status(i):='E';
            L_tab_status_desc(i):=L_tab_status_desc(i)||' | Quantity cannot be 0 if document is MRCHI';
         END IF;
         
         IF NVL(TO_NUMBER(TRIM(I_TAB_TYPE_REIM_ATTR(i).total_qty)),0)<0 OR 
            NVL(TO_NUMBER(TRIM(I_TAB_TYPE_REIM_ATTR(i).invoice_qty_det)),0)<0 OR 
            NVL(TO_NUMBER(TRIM(I_TAB_TYPE_REIM_ATTR(i).invoice_qty)),0)<0  THEN
            L_tab_status(i):='E';
            L_tab_status_desc(i):=L_tab_status_desc(i)||'| Quantity cannot be negative';
         END IF;
         /*
         IF REGEXP_LIKE(I_TAB_TYPE_REIM_ATTR(i).cost_component1,'[^[:alnum:]]')
            OR REGEXP_LIKE(I_TAB_TYPE_REIM_ATTR(i).cost_component2,'[^[:alnum:]]')
            OR REGEXP_LIKE(I_TAB_TYPE_REIM_ATTR(i).cost_component3,'[^[:alnum:]]')
            OR REGEXP_LIKE(I_TAB_TYPE_REIM_ATTR(i).cost_component4,'[^[:alnum:]]')
            OR REGEXP_LIKE(I_TAB_TYPE_REIM_ATTR(i).cost_component5,'[^[:alnum:]]')
            OR REGEXP_LIKE(I_TAB_TYPE_REIM_ATTR(i).cost_component6,'[^[:alnum:]]')
            OR REGEXP_LIKE(I_TAB_TYPE_REIM_ATTR(i).cost_component7,'[^[:alnum:]]')
            OR REGEXP_LIKE(I_TAB_TYPE_REIM_ATTR(i).cost_component8,'[^[:alnum:]]')
            OR REGEXP_LIKE(I_TAB_TYPE_REIM_ATTR(i).cost_component9,'[^[:alnum:]]')
            OR REGEXP_LIKE(I_TAB_TYPE_REIM_ATTR(i).cost_component10,'[^[:alnum:]]')THEN
            UPDATE nrd_upld_input_stg
               SET status_code='E'
                   ,status_description=status_description||' | Cost_component has special characters'
             WHERE seq_no=L_tab_seq_no(i);
         END IF; */
         
         IF L_doc_type='DEBMEC'
            AND (TO_NUMBER(TRIM(I_TAB_TYPE_REIM_ATTR(i).total_cost_excl_tax))>=0 
                 OR TO_NUMBER(TRIM(I_TAB_TYPE_REIM_ATTR(i).total_nonmerch_cost))>=0 )THEN
            L_tab_status(i):='E';
            L_tab_status_desc(i):=L_tab_status_desc(i)||' | Cost Component should be negative for debit memo';
         END IF;
         
         IF L_doc_type in ('CRDMEC','DEBMEC') AND I_TAB_TYPE_REIM_ATTR(i).cost_allocation='Y' THEN
            L_check_flag:='N';
            OPEN C_val_doc_code;
            FETCH C_val_doc_code INTO L_check_flag;
            CLOSE C_val_doc_code;
            IF L_check_flag='N' THEN
               L_tab_status(i):='E';
               L_tab_status_desc(i):=L_tab_status_desc(i)||' | Invalid Document Code';
            END IF;
         END IF;
         
         --Custom_doc_ref Calculations
         L_tab_cust_ref1(i):=I_TAB_TYPE_REIM_ATTR(i).scan_no||'.'||I_TAB_TYPE_REIM_ATTR(i).scan_date
                         ||'.'||I_TAB_TYPE_REIM_ATTR(i).oracle_reason_code||'.'||I_TAB_TYPE_REIM_ATTR(i).gl_desc;
         IF L_doc_type='MRCHI' THEN
            L_tab_cust_ref2(i):=NULL;
            SELECT DECODE(I_TAB_TYPE_REIM_ATTR(i).bank_exchange_rate,NULL,NULL
                                                                     ,I_TAB_TYPE_REIM_ATTR(i).wire_transfer_no)
              INTO L_tab_cust_ref3(i)
              FROM DUAL;
            L_tab_cust_ref4(i):=I_TAB_TYPE_REIM_ATTR(i).bank_exchange_rate;
         ELSIF L_doc_type='NMRCHI' THEN
            L_tab_cust_ref2(i):=I_TAB_TYPE_REIM_ATTR(i).shipment_ref_no;
            L_tab_cust_ref3(i):=I_TAB_TYPE_REIM_ATTR(i).wire_transfer_no;
            L_tab_cust_ref4(i):=NULL;
         ELSIF L_doc_type IN ('CRDMEC','DEBMEC') THEN
            SELECT DECODE(I_TAB_TYPE_REIM_ATTR(i).cost_allocation
                           ,'N',NULL
                           ,I_TAB_TYPE_REIM_ATTR(i).doc_code||'.'||
                            I_TAB_TYPE_REIM_ATTR(i).dept||'.'||
                            I_TAB_TYPE_REIM_ATTR(i).supplier_site_id)
              INTO L_tab_cust_ref2(i)
              FROM DUAL;
            SELECT DECODE(I_TAB_TYPE_REIM_ATTR(i).cost_allocation
                           ,'N',NULL
                           ,DECODE(I_TAB_TYPE_REIM_ATTR(i).order_no,
                                   NULL,TO_DATE(TRIM(I_TAB_TYPE_REIM_ATTR(i).date_from),'MM/DD/YYYY')||'-'||
                                        TO_DATE(TRIM(I_TAB_TYPE_REIM_ATTR(i).date_to),'MM/DD/YYYY')
                                   ,NULL))
              INTO L_tab_cust_ref3(i)
              FROM DUAL;
            L_tab_cust_ref4(i):=I_TAB_TYPE_REIM_ATTR(i).auth_no||'-'||I_TAB_TYPE_REIM_ATTR(i).bank_exchange_rate||'-'||
                                I_TAB_TYPE_REIM_ATTR(i).noap||'-'||I_TAB_TYPE_REIM_ATTR(i).rtv_number;
         END IF;
         
      END LOOP;
   
   FORALL i IN 1..L_tab_seq_no.COUNT
   UPDATE nrd_upld_input_stg
      SET status_code=L_tab_status(i)
          ,status_description=L_tab_status_desc(i)
          ,custom_doc_ref1=L_tab_cust_ref1(i)
          ,custom_doc_ref2=L_tab_cust_ref2(i)
          ,custom_doc_ref3=L_tab_cust_ref3(i)
          ,custom_doc_ref4=L_tab_cust_ref4(i)
    WHERE seq_no=L_tab_seq_no(i); 
   
   END IF;
   O_success_count:=I_TAB_TYPE_REIM_ATTR.COUNT;
   O_FAILURE_COUNT:=0;
   IF NOT FN_VALIDATE_DATA(L_upld_id) THEN
      SELECT COUNT(DISTINCT ext_doc_id) 
        INTO O_success_count
        FROM nrd_upld_input_stg n1
       WHERE file_name=I_file_name
         AND status_code='V'
         AND NOT EXISTS(SELECT 1
                          FROM nrd_upld_input_stg 
                         WHERE file_name=I_file_name
                           AND ext_doc_id=n1.ext_doc_id
                           AND status_code<>'V');
      SELECT COUNT(DISTINCT ext_doc_id) 
        INTO O_FAILURE_COUNT
        FROM nrd_upld_input_stg
       WHERE file_name=I_file_name
         AND status_code<>'V';
      RETURN '1';
   END IF; 
   COMMIT;
   SELECT COUNT(DISTINCT ext_doc_id) 
     INTO O_success_count
     FROM nrd_upld_input_stg n1
    WHERE file_name=I_file_name
      AND status_code='V'
      AND NOT EXISTS(SELECT 1
                       FROM nrd_upld_input_stg 
                      WHERE file_name=I_file_name
                        AND ext_doc_id=n1.ext_doc_id
                        AND status_code<>'V');
   SELECT COUNT(DISTINCT ext_doc_id) 
     INTO O_FAILURE_COUNT
     FROM nrd_upld_input_stg
    WHERE file_name=I_file_name
      AND status_code<>'V';

   RETURN '0';
EXCEPTION
   WHEN OTHERS THEN
      ROLLBACK;
      IF L_ins_flag='N' THEN
         NRD_WRITE_CUSTOM_LOG( SQLCODE,
                               SQLERRM,
                               'Upload ID : '||L_upld_id,
                               'Upload File : '||I_FILE_NAME,
                               'Error while loading Staging table');
         O_success_count:=0;
         O_FAILURE_COUNT:=I_TAB_TYPE_REIM_ATTR.COUNT;
         RETURN '3'; -- File upload failed due to datatype mismatch
      END IF;
      DBMS_OUTPUT.put_line ('Failed');
      NRD_WRITE_CUSTOM_LOG( SQLCODE,
                            SQLERRM,
                            'Upload ID : '||L_upld_id,
                            'Upload File : '||I_FILE_NAME,
                            'Error while Validating Staging table Data');
      SELECT COUNT(DISTINCT ext_doc_id) 
        INTO O_success_count
        FROM nrd_upld_input_stg n1
       WHERE file_name=I_file_name
         AND status_code='V'
         AND NOT EXISTS(SELECT 1
                          FROM nrd_upld_input_stg 
                         WHERE file_name=I_file_name
                           AND ext_doc_id=n1.ext_doc_id
                           AND status_code<>'V');
      SELECT COUNT(DISTINCT ext_doc_id) 
        INTO O_FAILURE_COUNT
        FROM nrd_upld_input_stg
       WHERE file_name=I_file_name
         AND status_code<>'V';
      RETURN '1';
END FN_REIM_UPLD;
---------------------------------------------------------------------------------------------------------------------
FUNCTION FN_VALIDATE_DATA(I_upld_id            IN     NUMBER)
   RETURN BOOLEAN
IS

L_svc_link nrd_upld_ctrl.webservice_link%TYPE;

CURSOR C_get_targets IS
SELECT distinct webservice_link
  FROM nrd_upld_ctrl nuc,
       nrd_upld_input_stg nuis
 WHERE nuc.location=nuis.location
   AND nuis.upload_id=I_upld_id;
   
CURSOR C_create_req_msg IS
SELECT "OBJ_REIMUPLDVALIDATIONREQ" (tbl) FROM
    (SELECT  CAST(MULTISET(
       SELECT "OBJ_REIMUPLDVALIDATIONDTL" (ns.seq_no
                                          ,ns.user_id
                                          ,ns.file_name
                                          ,ns.ext_doc_id
                                          ,ns.doc_date
                                          ,ns.vendor_type
                                          ,ns.vendor
                                          ,ns.ext_doc_id_2
                                          ,ns.doc_date_2
                                          ,ns.vendor_type_2
                                          ,ns.vendor_2
                                          ,ns.vendor_name_2
                                          ,ns.supplier_site_id
                                          ,ns.order_no
                                          ,ns.currency_code
                                          ,ns.terms
                                          ,ns.reason_code
                                          ,ns.item
                                          ,ns.location
                                          ,ns.type)
                                     FROM nrd_upld_input_stg ns
                                          ,nrd_upld_ctrl nc
                                    WHERE ns.location=nc.location
                                      AND nc.webservice_link=L_svc_link
                                      AND ns.upload_id=I_upld_id
                                      AND NOT EXISTS(SELECT 1
                                                       FROM nrd_ip_po
                                                      WHERE order_no=ns.order_no)
                                    ) AS "TBL_OBJ_REIMUPLDVALIDATIONDTL") tbl
                FROM dual);

TYPE Tab_type_svc_link IS TABLE OF nrd_upld_ctrl.webservice_link%TYPE
INDEX BY BINARY_INTEGER;
L_tab_svc_link Tab_type_svc_link;
L_ser_endpt             VARCHAR2(100);

L_reimuplddtl OBJ_REIMUPLDVALIDATIONREQ;
L_servicereturn_msg     OBJ_REIMVALIDATIONRETMSG :=NULL;
L_err varchar2(1000);

 
BEGIN
   OPEN C_get_targets;
   FETCH C_get_targets BULK COLLECT INTO L_tab_svc_link;
   CLOSE C_get_targets;
   --Calling Different web services for different instances
   FOR i IN 1..L_tab_svc_link.COUNT
   LOOP
      L_svc_link:=L_tab_svc_link(i);
      OPEN C_create_req_msg;
      FETCH C_create_req_msg INTO L_reimuplddtl;
      CLOSE C_create_req_msg;
            
      L_ser_endpt:=L_svc_link||'/DatavalidationBean/DatavalidationService?WSDL';
      DatavalidationServiceConsumer.setEndpoint(L_ser_endpt);
      
      L_servicereturn_msg:=new OBJ_REIMVALIDATIONRETMSG(NULL);
      L_servicereturn_msg:=DatavalidationServiceConsumer.getValidationReIMUpldValid_0(L_reimuplddtl); 
      
      FORALL j IN 1..L_servicereturn_msg.reIMBatchReturn_.COUNT
      UPDATE nrd_upld_input_stg
         SET status_code=DECODE(status_code,'E',status_code
                                               ,L_servicereturn_msg.reIMBatchReturn_(j).status_code_),
             status_description=NVL(DECODE(L_servicereturn_msg.reIMBatchReturn_(j).status_code_
                                       ,'E',status_description||L_servicereturn_msg.reIMBatchReturn_(j).status_description_
                                           ,status_description)
                                    ,L_servicereturn_msg.reIMBatchReturn_(j).status_description_),
             system_flag=L_servicereturn_msg.reIMBatchReturn_(j).system_flag_,
             currency_code=NVL(L_servicereturn_msg.reIMBatchReturn_(j).currency_code_,currency_code)
       WHERE seq_no=L_servicereturn_msg.reIMBatchReturn_(j).seq_no_;
       
      FORALL j IN 1..L_servicereturn_msg.reIMBatchReturn_.COUNT
      UPDATE nrd_upld_input_stg
         SET terms=L_servicereturn_msg.reIMBatchReturn_(j).terms_
       WHERE seq_no=L_servicereturn_msg.reIMBatchReturn_(j).seq_no_;
   END LOOP;
   
   UPDATE nrd_upld_input_stg nuis
      SET status_code='E',
          status_description=status_description||'| Invalid Location'
    WHERE status_code in ('L','E')
      AND NOT EXISTS(SELECT 1 
                       FROM nrd_upld_ctrl
                      WHERE location=nuis.location);
   
   COMMIT;
   RETURN TRUE;

EXCEPTION
WHEN OTHERS THEN  
   ROLLBACK;
   L_err:=SQLERRM;
   NRD_WRITE_CUSTOM_LOG(SQLCODE,
                         SQLERRM,
                         'Failed while Calling RSE Service');
   RETURN FALSE;
END FN_VALIDATE_DATA;
---------------------------------------------------------------------------------------------------------------------
FUNCTION FN_UPD_CALC_COMP(I_comp       IN             VARCHAR2,
                          I_amount     IN             VARCHAR2,
                          I_seq_no     IN             NUMBER)
RETURN BOOLEAN IS 
   L_stmt    VARCHAR2(2000);
   L_comp_no NUMBER(2);
   CURSOR C_get_comp IS
   SELECT DECODE(I_comp,cost_component1,1
                       ,cost_component2,2
                       ,cost_component3,3
                       ,cost_component4,4
                       ,cost_component5,5
                       ,cost_component6,6
                       ,cost_component7,7
                       ,cost_component8,8
                       ,cost_component9,9
                       ,cost_component10,10)
     FROM nrd_upld_input_stg
    WHERE seq_no=I_seq_no;
BEGIN
   OPEN C_get_comp;
   FETCH C_get_comp INTO L_comp_no;
   CLOSE C_get_comp;
   IF L_comp_no IS NOT NULL THEN
      L_stmt:='UPDATE NRD_UPLD_INPUT_STG SET COST_AMOUNT'||L_comp_no;
      L_stmt:=L_stmt||'='||I_amount||' WHERE seq_no='||I_seq_no;
      -- dbms_output.put_line(L_stmt);
      EXECUTE IMMEDIATE L_stmt;
   END IF;
   RETURN TRUE;
EXCEPTION
WHEN OTHERS THEN
   NRD_WRITE_CUSTOM_LOG(SQLCODE,
                         SQLERRM,
                         'Failed while Updating Prorated Cost Components');
   RETURN FALSE;
END FN_UPD_CALC_COMP;
---------------------------------------------------------------------------------------------------------------------
FUNCTION FN_CALC_DATA(O_error_msg            OUT    VARCHAR2)
RETURN BOOLEAN IS
        
   L_vdate  DATE;
   L_alwnc_perc_hdr nrd_upld_input_stg.allowance_perc_hdr%TYPE;
   L_alwnc_amt_hdr nrd_upld_input_stg.allowance_amt_hdr%TYPE;
   L_alwnc_perc_det nrd_upld_input_stg.allowance_perc_det%TYPE;
   L_alwnc_amt_det nrd_upld_input_stg.allowance_amt_det%TYPE;
   L_ext_doc_id nrd_upld_input_stg.ext_doc_id%TYPE;
   L_unit_cost nrd_upld_input_stg.unit_cost%TYPE;
   L_invc_qty_det NUMBER;
   L_weighted_avg_cost NUMBER;
   L_cost_det NUMBER;
      
   CURSOR C_get_data IS 
   SELECT nuis.seq_no seq_no,
          user_id,
          file_name,
          upload_id,
          ext_doc_id,
          doc_date,
          type,
          vendor_type,
          vendor,
          ext_doc_id_2,
          doc_date_2,
          vendor_type_2,
          vendor_2,
          vendor_name_2,
          supplier_site_id,
          order_no,
          currency_code,
          terms,
          location,
          custom_doc_ref1,
          custom_doc_ref2,
          custom_doc_ref3,
          custom_doc_ref4,
          total_nonmerch_cost,
          total_cost_excl_tax,
          tax_amount,
          total_qty,
          temp.allowance_amt_hdr allowance_amt_hdr,
          temp.allowance_perc_hdr allowance_perc_hdr,
          prepaid_ind,
          text_i,
          text_e,
          item,
          item_description,
          vpn_number,
          vpn_description,
          total_qty invoice_qty_hdr,
          unit_cost,
          reason_code,
          temp.allowance_amt_det allowance_amt_det,
          temp.allowance_perc_det allowance_perc_det,
          0 total_cost,
          0 cost_adj,
          NULL due_date,
          NULL due_date2,
          DECODE(type,'NMRCHI',invoice_qty_det,invoice_qty) invoice_qty_det,
          (unit_cost*DECODE(type,'NMRCHI',invoice_qty_det,invoice_qty))/
            DECODE(SUM(unit_cost*DECODE(type,'NMRCHI',invoice_qty_det
                                                     ,invoice_qty))OVER (PARTITION BY ext_doc_id,
                                                                                      vendor,
                                                                                      doc_date,
                                                                                      NVL(order_no,0)),0,1) weighted_avg_cost,
         SUM(temp.allowance_amt_det) OVER (PARTITION BY ext_doc_id,
                                                   vendor,
                                                   doc_date,
                                                   NVL(order_no,0)) sum_allowance_amt_dtl,
         SUM(unit_cost*DECODE(type,'NMRCHI',invoice_qty_det
                                           ,invoice_qty)*temp.allowance_perc_det/100) OVER (PARTITION BY ext_doc_id,
                                                   vendor,
                                                   doc_date,
                                                   NVL(order_no,0)) sum_allowance_perc_dtl,
          duty,
          unit_of_measure,
          extended_cost,
          entry_ip,
          ext_doc_id_ip,
          broker_file_ip,
          invoice_date_ip,
          manuf_name_ip,
          manuf_comm_inv_ip,
          manuf_comm_inv_val_ip,
          manuf_comm_inv_po_ip,
          upload_date,
          agent_commission,
          count(distinct system_flag) OVER (PARTITION BY ext_doc_id) sys_count,
          system_flag,
          ROW_NUMBER() OVER (PARTITION BY ext_doc_id,doc_date,NVL(order_no,0),vendor
                         ORDER BY item) rn_head,
          ROUND(sum(avg_ag_co) OVER (PARTITION BY ext_doc_id,system_flag),2) calc_ag_co,
          COUNT(distinct system_flag) OVER (PARTITION BY ext_doc_id) count_sys,
          ROUND(DECODE(NVL(unit_of_measure,0),0,1,unit_of_measure)/
                DECODE(NVL(sum(avg_cbm) over (partition by ext_doc_id),0),0,1
                       ,sum(avg_cbm) over (partition by ext_doc_id)),2) weighted_avg_cbm,
          CASE WHEN cost_component1='Duty' THEN cost_amount1
               WHEN cost_component2='Duty' THEN cost_amount2
               WHEN cost_component3='Duty' THEN cost_amount3
               WHEN cost_component4='Duty' THEN cost_amount4
               WHEN cost_component5='Duty' THEN cost_amount5
               WHEN cost_component6='Duty' THEN cost_amount6
               WHEN cost_component7='Duty' THEN cost_amount7
               WHEN cost_component8='Duty' THEN cost_amount8
               WHEN cost_component9='Duty' THEN cost_amount9
               WHEN cost_component10='Duty' THEN cost_amount10 
               ELSE NULL END duty_comp,
          CASE WHEN cost_component1='Freight - Air' THEN cost_amount1
               WHEN cost_component2='Freight - Air' THEN cost_amount2
               WHEN cost_component3='Freight - Air' THEN cost_amount3
               WHEN cost_component4='Freight - Air' THEN cost_amount4
               WHEN cost_component5='Freight - Air' THEN cost_amount5
               WHEN cost_component6='Freight - Air' THEN cost_amount6
               WHEN cost_component7='Freight - Air' THEN cost_amount7
               WHEN cost_component8='Freight - Air' THEN cost_amount8
               WHEN cost_component9='Freight - Air' THEN cost_amount9
               WHEN cost_component10='Freight - Air' THEN cost_amount10 
               ELSE NULL END freight_air,
          CASE WHEN cost_component1='Freight - Surface' THEN cost_amount1
               WHEN cost_component2='Freight - Surface' THEN cost_amount2
               WHEN cost_component3='Freight - Surface' THEN cost_amount3
               WHEN cost_component4='Freight - Surface' THEN cost_amount4
               WHEN cost_component5='Freight - Surface' THEN cost_amount5
               WHEN cost_component6='Freight - Surface' THEN cost_amount6
               WHEN cost_component7='Freight - Surface' THEN cost_amount7
               WHEN cost_component8='Freight - Surface' THEN cost_amount8
               WHEN cost_component9='Freight - Surface' THEN cost_amount9
               WHEN cost_component10='Freight - Surface' THEN cost_amount10  
               ELSE NULL END freight_surface,
          CASE WHEN cost_component1='Freight - Ocean' THEN cost_amount1
               WHEN cost_component2='Freight - Ocean' THEN cost_amount2
               WHEN cost_component3='Freight - Ocean' THEN cost_amount3
               WHEN cost_component4='Freight - Ocean' THEN cost_amount4
               WHEN cost_component5='Freight - Ocean' THEN cost_amount5
               WHEN cost_component6='Freight - Ocean' THEN cost_amount6
               WHEN cost_component7='Freight - Ocean' THEN cost_amount7
               WHEN cost_component8='Freight - Ocean' THEN cost_amount8
               WHEN cost_component9='Freight - Ocean' THEN cost_amount9
               WHEN cost_component10='Freight - Ocean' THEN cost_amount10  
               ELSE NULL END freight_ocean,
          CASE WHEN cost_component1='Consolidation Fees' THEN cost_amount1
               WHEN cost_component2='Consolidation Fees' THEN cost_amount2
               WHEN cost_component3='Consolidation Fees' THEN cost_amount3
               WHEN cost_component4='Consolidation Fees' THEN cost_amount4
               WHEN cost_component5='Consolidation Fees' THEN cost_amount5
               WHEN cost_component6='Consolidation Fees' THEN cost_amount6
               WHEN cost_component7='Consolidation Fees' THEN cost_amount7
               WHEN cost_component8='Consolidation Fees' THEN cost_amount8
               WHEN cost_component9='Consolidation Fees' THEN cost_amount9
               WHEN cost_component10='Consolidation Fees' THEN cost_amount10  
               ELSE NULL END cons_fees,
          CASE WHEN cost_component1='Customs Broker Fee' THEN cost_amount1
               WHEN cost_component2='Customs Broker Fee' THEN cost_amount2
               WHEN cost_component3='Customs Broker Fee' THEN cost_amount3
               WHEN cost_component4='Customs Broker Fee' THEN cost_amount4
               WHEN cost_component5='Customs Broker Fee' THEN cost_amount5
               WHEN cost_component6='Customs Broker Fee' THEN cost_amount6
               WHEN cost_component7='Customs Broker Fee' THEN cost_amount7
               WHEN cost_component8='Customs Broker Fee' THEN cost_amount8
               WHEN cost_component9='Customs Broker Fee' THEN cost_amount9
               WHEN cost_component10='Customs Broker Fee' THEN cost_amount10   
               ELSE NULL END broker_fees,
          CASE WHEN cost_component1='Customs Brokerage-Entry Misc' THEN cost_amount1
               WHEN cost_component2='Customs Brokerage-Entry Misc' THEN cost_amount2
               WHEN cost_component3='Customs Brokerage-Entry Misc' THEN cost_amount3
               WHEN cost_component4='Customs Brokerage-Entry Misc' THEN cost_amount4
               WHEN cost_component5='Customs Brokerage-Entry Misc' THEN cost_amount5
               WHEN cost_component6='Customs Brokerage-Entry Misc' THEN cost_amount6
               WHEN cost_component7='Customs Brokerage-Entry Misc' THEN cost_amount7
               WHEN cost_component8='Customs Brokerage-Entry Misc' THEN cost_amount8
               WHEN cost_component9='Customs Brokerage-Entry Misc' THEN cost_amount9
               WHEN cost_component10='Customs Brokerage-Entry Misc' THEN cost_amount10  
               ELSE NULL END broker_misc,
          CASE WHEN cost_component1='Special Charge F050' THEN cost_amount1
               WHEN cost_component2='Special Charge F050' THEN cost_amount2
               WHEN cost_component3='Special Charge F050' THEN cost_amount3
               WHEN cost_component4='Special Charge F050' THEN cost_amount4
               WHEN cost_component5='Special Charge F050' THEN cost_amount5
               WHEN cost_component6='Special Charge F050' THEN cost_amount6
               WHEN cost_component7='Special Charge F050' THEN cost_amount7
               WHEN cost_component8='Special Charge F050' THEN cost_amount8
               WHEN cost_component9='Special Charge F050' THEN cost_amount9
               WHEN cost_component10='Special Charge F050' THEN cost_amount10 
               ELSE NULL END spl_chrg_F050,
          CASE WHEN cost_component1='Special Charge H060' THEN cost_amount1
               WHEN cost_component2='Special Charge H060' THEN cost_amount2
               WHEN cost_component3='Special Charge H060' THEN cost_amount3
               WHEN cost_component4='Special Charge H060' THEN cost_amount4
               WHEN cost_component5='Special Charge H060' THEN cost_amount5
               WHEN cost_component6='Special Charge H060' THEN cost_amount6
               WHEN cost_component7='Special Charge H060' THEN cost_amount7
               WHEN cost_component8='Special Charge H060' THEN cost_amount8
               WHEN cost_component9='Special Charge H060' THEN cost_amount9
               WHEN cost_component10='Special Charge H060' THEN cost_amount10 
               ELSE NULL END spl_chrg_H060,
          CASE WHEN cost_component1='Tax for Import' THEN cost_amount1
               WHEN cost_component2='Tax for Import' THEN cost_amount2
               WHEN cost_component3='Tax for Import' THEN cost_amount3
               WHEN cost_component4='Tax for Import' THEN cost_amount4
               WHEN cost_component5='Tax for Import' THEN cost_amount5
               WHEN cost_component6='Tax for Import' THEN cost_amount6
               WHEN cost_component7='Tax for Import' THEN cost_amount7
               WHEN cost_component8='Tax for Import' THEN cost_amount8
               WHEN cost_component9='Tax for Import' THEN cost_amount9
               WHEN cost_component10='Tax for Import' THEN cost_amount10 
               ELSE NULL END tax_for_import,
          0 fa_calc,  --Calculated Freight-Air
          0 fo_calc,  --Calculated Freight-Ocean
          0 fs_calc,  --Calculated freight_surface
          0 cf_calc,  --calculated cons_fees
          0 bf_calc,  --calculated broker_fees
          0 bm_calc  --calculated broker_misc
   FROM (SELECT seq_no,
                avg_cbm,
                agent_commission,
                agent_commission/count(1) over (partition by ext_doc_id,order_no) avg_ag_co,
                allowance_amt_hdr,
                allowance_perc_hdr,
                allowance_amt_det,
                allowance_perc_det
           FROM (SELECT  seq_no,
                         ext_doc_id,
                         order_no,
                         MAX(NVL(unit_of_measure,0)) over (partition by ext_doc_id,NVL(order_no,0))
                           /count(1) over (partition by ext_doc_id,nvl(order_no,0)) avg_cbm,
                         CASE WHEN cost_component1='Agent Commission' THEN cost_amount1
                              WHEN cost_component2='Agent Commission' THEN cost_amount2
                              WHEN cost_component3='Agent Commission' THEN cost_amount3
                              WHEN cost_component4='Agent Commission' THEN cost_amount4
                              WHEN cost_component5='Agent Commission' THEN cost_amount5
                              WHEN cost_component6='Agent Commission' THEN cost_amount6
                              WHEN cost_component7='Agent Commission' THEN cost_amount7
                              WHEN cost_component8='Agent Commission' THEN cost_amount8
                              WHEN cost_component9='Agent Commission' THEN cost_amount9
                              WHEN cost_component10='Agent Commission' THEN cost_amount10 
                              ELSE NULL END agent_commission,
                         MAX(NVL(allowance_amt_hdr,0)) 
                           OVER (PARTITION BY ext_doc_id,vendor,doc_date,NVL(order_no,0)) allowance_amt_hdr,
                         MAX(NVL(allowance_perc_hdr,0)) 
                           OVER (PARTITION BY ext_doc_id,vendor,doc_date,NVL(order_no,0)) allowance_perc_hdr,
                         MAX(NVL(allowance_amt_det,0)) 
                           OVER (PARTITION BY ext_doc_id,vendor,doc_date,NVL(order_no,0),NVL(item,0)) allowance_amt_det,
                         MAX(NVL(allowance_perc_det,0)) 
                           OVER (PARTITION BY ext_doc_id,vendor,doc_date,NVL(order_no,0),NVL(item,0)) allowance_perc_det
                    FROM nrd_upld_input_stg
                   WHERE status_code='V')) temp,
          nrd_upld_input_stg nuis
    WHERE nuis.seq_no=temp.seq_no
    ORDER BY ext_doc_id,doc_date,order_no,vendor,item;

   TYPE Tab_type_data IS TABLE OF C_get_data%ROWTYPE 
   INDEX BY BINARY_INTEGER;

   L_tab_data Tab_type_data; 

BEGIN
   OPEN C_get_data;
   FETCH C_get_data BULK COLLECT INTO L_tab_data;
   CLOSE C_get_data;
   
   SELECT vdate
     INTO L_vdate
     FROM mv_period;
   
   FOR i IN 1..L_tab_data.COUNT
   LOOP
      -- L_ext_doc_id:=L_tab_data(i).ext_doc_id;
      IF L_tab_data(i).sys_count>1 AND L_tab_data(i).system_flag='R' THEN
         L_tab_data(i).ext_doc_id:=L_tab_data(i).ext_doc_id||'_'||L_tab_data(i).location;
      END IF;
      
      -- dbms_output.put_line('Proration starts'); 
      --Proration Logic
      L_tab_data(i).fa_calc:=L_tab_data(i).freight_air*L_tab_data(i).weighted_avg_cbm;
      L_tab_data(i).fo_calc:=L_tab_data(i).freight_ocean*L_tab_data(i).weighted_avg_cbm;
      L_tab_data(i).fs_calc:=L_tab_data(i).freight_surface*L_tab_data(i).weighted_avg_cbm;
      L_tab_data(i).cf_calc:=L_tab_data(i).cons_fees*L_tab_data(i).weighted_avg_cbm;
      L_tab_data(i).bf_calc:=L_tab_data(i).broker_fees*L_tab_data(i).weighted_avg_cbm;
      L_tab_data(i).bm_calc:=L_tab_data(i).broker_misc*L_tab_data(i).weighted_avg_cbm;
      
      --Allowance calculation
      L_alwnc_perc_hdr:=L_tab_data(i).allowance_perc_hdr;
      L_alwnc_amt_hdr:=L_tab_data(i).allowance_amt_hdr;
      L_alwnc_perc_det:=L_tab_data(i).allowance_perc_det;
      L_alwnc_amt_det:=L_tab_data(i).allowance_amt_det;
      L_unit_cost:=L_tab_data(i).unit_cost;
      L_weighted_avg_cost:=L_tab_data(i).weighted_avg_cost;
      IF NVL(L_tab_data(i).invoice_qty_det,0)=0 THEN
         L_invc_qty_det:=to_binary_float('INF');
      ELSE
         L_invc_qty_det:=L_tab_data(i).invoice_qty_det;
      END IF;
      
      IF L_alwnc_amt_det<>0 THEN
         L_tab_data(i).total_cost_excl_tax:=L_tab_data(i).total_cost_excl_tax-L_tab_data(i).sum_allowance_amt_dtl;
         L_tab_data(i).unit_cost:=L_unit_cost-(L_alwnc_amt_det/L_invc_qty_det);
      ELSIF L_alwnc_perc_det<>0 THEN
         L_tab_data(i).total_cost_excl_tax:=L_tab_data(i).total_cost_excl_tax-L_tab_data(i).sum_allowance_perc_dtl;
         L_tab_data(i).unit_cost:=L_unit_cost-(L_tab_data(i).unit_cost*L_alwnc_perc_det/100);
      ELSIF L_alwnc_amt_hdr<>0 THEN
         L_tab_data(i).total_cost_excl_tax:=L_tab_data(i).total_cost_excl_tax-L_alwnc_amt_hdr;
         L_tab_data(i).unit_cost:=L_unit_cost-(L_alwnc_amt_hdr*L_weighted_avg_cost/L_invc_qty_det);
      ELSIF L_alwnc_perc_hdr<>0 THEN
         L_alwnc_amt_hdr:=L_tab_data(i).total_cost_excl_tax*L_alwnc_perc_hdr/100;
         L_tab_data(i).total_cost_excl_tax:=L_tab_data(i).total_cost_excl_tax-L_alwnc_amt_hdr;
         L_tab_data(i).unit_cost:=L_unit_cost-(L_alwnc_amt_det*L_weighted_avg_cost/L_invc_qty_det);
      END IF;
      
      
   END LOOP;
   -- dbms_output.put_line('Insert'); 
   --For loading tables IM_DOC_HEAD, IM_DOC_TAX
   FORALL i IN 1..L_tab_data.COUNT
   INSERT INTO nrd_upld_hdr ( record_type,
                              user_id,
                              file_name,
                              type,
                              status,
                              order_no,
                              location,
                              doc_date,
                              vendor_type,
                              vendor,
                              ext_doc_id,
                              terms,
                              due_date,
                              currency_code,
                              total_cost,
                              total_qty,
                              manually_paid_ind,
                              custom_doc_ref_1,
                              custom_doc_ref_2,
                              custom_doc_ref_3,
                              custom_doc_ref_4,
                              last_update_id,
                              last_datetime,
                              total_cost_inc_tax,
                              supplier_site_id,
                              tax_basis,
                              comment_type,
                              text,
                              system_flag)
                       SELECT L_tab_data(i).type,
                              L_tab_data(i).user_id,
                              L_tab_data(i).file_name,
                              L_tab_data(i).type,
                              DECODE(L_tab_data(i).type,'MRCHI','RMTCH'
                                                       ,'NMRCHI','RMTCH'
                                                       ,'CRDMEC','APPRVE'
                                                       ,'DEBMEC','APPRVE'), --status,
                              L_tab_data(i).order_no,
                              L_tab_data(i).location,
                              L_tab_data(i).doc_date,
                              L_tab_data(i).vendor_type,
                              L_tab_data(i).vendor,
                              L_tab_data(i).ext_doc_id,
                              L_tab_data(i).terms,
                              L_tab_data(i).due_date,--due_date,
                              L_tab_data(i).currency_code,--currency_code,
                              L_tab_data(i).total_cost_excl_tax,--total_cost,
                              L_tab_data(i).total_qty,
                              'N' manually_paid_ind,
                              L_tab_data(i).custom_doc_ref1,
                              L_tab_data(i).custom_doc_ref2,
                              L_tab_data(i).custom_doc_ref3,
                              L_tab_data(i).custom_doc_ref4,
                              user,--last_update_id,
                              sysdate,--last_datetime,
                              L_tab_data(i).total_cost_excl_tax
                                 +L_tab_data(i).tax_amount,--total_cost_inc_tax/tax_basis
                              L_tab_data(i).supplier_site_id,
                              L_tab_data(i).total_cost_excl_tax
                                 +L_tab_data(i).tax_amount,--tax_basis,
                              NULL,--comment_type,
                              NULL,--text
                              L_tab_data(i).system_flag
                         FROM dual
                        WHERE L_tab_data(i).rn_head=1
                        UNION ALL
                       SELECT L_tab_data(i).type,
                              L_tab_data(i).user_id,
                              L_tab_data(i).file_name,
                              L_tab_data(i).type,
                              DECODE(L_tab_data(i).type,'MRCHI','RMTCH'
                                                       ,'NMRCHI','RMTCH'
                                                       ,'CRDMEC','APPRVE'
                                                       ,'DEBMEC','APPRVE'), --status,
                              L_tab_data(i).order_no,
                              L_tab_data(i).location,
                              L_tab_data(i).doc_date_2,
                              L_tab_data(i).vendor_type_2,
                              L_tab_data(i).vendor_2,
                              L_tab_data(i).ext_doc_id_2,
                              L_tab_data(i).terms,
                              L_tab_data(i).due_date2,--due_date,
                              L_tab_data(i).currency_code,--currency_code,
                              L_tab_data(i).total_cost_excl_tax,--total_cost,
                              L_tab_data(i).total_qty,
                              'N' manually_paid_ind,
                              L_tab_data(i).custom_doc_ref1,
                              L_tab_data(i).custom_doc_ref2,
                              L_tab_data(i).custom_doc_ref3,
                              L_tab_data(i).custom_doc_ref4,
                              user,--last_update_id,
                              sysdate,--last_datetime,
                              L_tab_data(i).total_cost_excl_tax
                                 +L_tab_data(i).tax_amount,--total_cost_inc_tax/tax_basis
                              L_tab_data(i).supplier_site_id,
                              L_tab_data(i).total_cost_excl_tax
                                 +L_tab_data(i).tax_amount,--tax_basis,
                              NULL,--comment_type,
                              NULL,--text
                              L_tab_data(i).system_flag
                         FROM dual
                        WHERE L_tab_data(i).rn_head=1
                          AND L_tab_data(i).vendor_2 IS NOT NULL
                          AND L_tab_data(i).duty IS NOT NULL
                          AND L_tab_data(i).type='NMRCHI';
                        
   --For loading tables IM_INVOICE_DETAIL, IM_DOC_DETAIL_TAX
   FORALL i IN 1..L_tab_data.COUNT
   INSERT INTO NRD_UPLD_DTL ( record_type,
                              user_id,
                              ext_doc_id,
                              vendor,
                              doc_date,
                              order_no,
                              item,
                              unit_cost,
                              invoice_qty,
                              status,
                              reason_code,
                              tax_basis, 
                              comment_type,
                              text,
                              non_merch_code,
                              non_merch_amt)
                       SELECT L_tab_data(i).type,
                              L_tab_data(i).user_id,
                              L_tab_data(i).ext_doc_id,
                              L_tab_data(i).vendor,
                              L_tab_data(i).doc_date,
                              L_tab_data(i).order_no,
                              L_tab_data(i).item,
                              L_tab_data(i).unit_cost,
                              L_tab_data(i).invoice_qty_det,
                              'X',--status,
                              L_tab_data(i).reason_code,--reason_code
                              L_tab_data(i).unit_cost*L_tab_data(i).invoice_qty_det,-- tax_basis,
                              NULL,--comment_type,
                              NULL,--text,
                              NULL,--non_merch_code,
                              NULL--non_merch_amt
                         FROM DUAL
                        WHERE L_tab_data(i).item IS NOT NULL
                        UNION ALL
                       SELECT 'NMRCHT',
                              L_tab_data(i).user_id,
                              L_tab_data(i).ext_doc_id_2,
                              L_tab_data(i).vendor_2,
                              L_tab_data(i).doc_date_2,
                              NULL,--order_no,
                              NULL, --item
                              NULL,--unit_cost,
                              NULL,--invoice_qty_det,
                              'X',--status,
                              NULL,--reason_code,--reason_code
                              0 tax_basis,
                              NULL,--comment_type,
                              NULL,--text,
                              'Duty Canada',--non_merch_code,
                              L_tab_data(i).duty--non_merch_amt
                         FROM DUAL
                        WHERE L_tab_data(i).vendor_2 IS NOT NULL
                          AND L_tab_data(i).rn_head=1
                          AND L_tab_data(i).type='NMRCHI'
                          AND L_tab_data(i).duty IS NOT NULL;
                           
   FOR i IN 1..L_tab_data.COUNT
   LOOP
      IF NVL(L_tab_data(i).duty_comp,0)<>0 THEN
         INSERT INTO NRD_UPLD_DTL ( record_type,
                                    user_id,
                                    order_no,
                                    ext_doc_id,
                                    vendor,
                                    doc_date,
                                    tax_basis,
                                    non_merch_code,
                                    non_merch_amt)
                             SELECT 'NMRCHT',
                                    L_tab_data(i).user_id,
                                    L_tab_data(i).order_no,
                                    L_tab_data(i).ext_doc_id,
                                    L_tab_data(i).vendor,
                                    L_tab_data(i).doc_date,
                                    L_tab_data(i).tax_amount,
                                    'Duty',--non_merch_code,
                                    L_tab_data(i).duty_comp--non_merch_amt
                               FROM DUAL
                              WHERE L_tab_data(i).rn_head=1;
         IF NOT PKG_REIM_UPLD.FN_UPD_CALC_COMP('Duty',
                                               L_tab_data(i).duty_comp,
                                               L_tab_data(i).seq_no) THEN
            DBMS_OUTPUT.PUT_LINE('Unable to update Calculated values');
            RETURN FALSE;
         END IF;
      END IF;
      IF NVL(L_tab_data(i).fa_calc,0)<>0 THEN
         INSERT INTO NRD_UPLD_DTL ( record_type,
                                    user_id,
                                    order_no,
                                    ext_doc_id,
                                    vendor,
                                    doc_date,
                                    tax_basis,
                                    non_merch_code,
                                    non_merch_amt)
                             SELECT 'NMRCHT',
                                    L_tab_data(i).user_id,
                                    L_tab_data(i).order_no,
                                    L_tab_data(i).ext_doc_id,
                                    L_tab_data(i).vendor,
                                    L_tab_data(i).doc_date,
                                    L_tab_data(i).tax_amount,
                                    'Freight - Air',--non_merch_code,
                                    L_tab_data(i).fa_calc--non_merch_amt
                               FROM DUAL
                              WHERE L_tab_data(i).rn_head=1;
         IF NOT PKG_REIM_UPLD.FN_UPD_CALC_COMP('Freight - Air',
                                               L_tab_data(i).fa_calc,
                                               L_tab_data(i).seq_no) THEN
            DBMS_OUTPUT.PUT_LINE('Unable to update Calculated values');
            RETURN FALSE;
         END IF;
      END IF;
      IF NVL(L_tab_data(i).fs_calc,0)<>0 THEN
         INSERT INTO NRD_UPLD_DTL ( record_type,
                                    user_id,
                                    order_no,
                                    ext_doc_id,
                                    vendor,
                                    doc_date,
                                    tax_basis,
                                    non_merch_code,
                                    non_merch_amt)
                             SELECT 'NMRCHT',
                                    L_tab_data(i).user_id,
                                    L_tab_data(i).order_no,
                                    L_tab_data(i).ext_doc_id,
                                    L_tab_data(i).vendor,
                                    L_tab_data(i).doc_date,
                                    L_tab_data(i).tax_amount,
                                    'Freight - Surface',--non_merch_code,
                                    L_tab_data(i).fs_calc--non_merch_amt
                               FROM DUAL
                              WHERE L_tab_data(i).rn_head=1;
         IF NOT PKG_REIM_UPLD.FN_UPD_CALC_COMP('Freight - Surface',
                                               L_tab_data(i).fs_calc,
                                               L_tab_data(i).seq_no) THEN
            DBMS_OUTPUT.PUT_LINE('Unable to update Calculated values');
            RETURN FALSE;
         END IF;
      END IF;
      IF NVL(L_tab_data(i).fo_calc,0)<>0 THEN
         INSERT INTO NRD_UPLD_DTL ( record_type,
                                    user_id,
                                    order_no,
                                    ext_doc_id,
                                    vendor,
                                    doc_date,
                                    tax_basis,
                                    non_merch_code,
                                    non_merch_amt)
                             SELECT 'NMRCHT',
                                    L_tab_data(i).user_id,
                                    L_tab_data(i).order_no,
                                    L_tab_data(i).ext_doc_id,
                                    L_tab_data(i).vendor,
                                    L_tab_data(i).doc_date,
                                    L_tab_data(i).tax_amount,
                                    'Freight - Ocean',--non_merch_code,
                                    L_tab_data(i).fo_calc--non_merch_amt
                               FROM DUAL
                              WHERE L_tab_data(i).rn_head=1;
         IF NOT PKG_REIM_UPLD.FN_UPD_CALC_COMP('Freight - Ocean',
                                               L_tab_data(i).fo_calc,
                                               L_tab_data(i).seq_no) THEN
            DBMS_OUTPUT.PUT_LINE('Unable to update Calculated values');
            RETURN FALSE;
         END IF;
      END IF;
      IF NVL(L_tab_data(i).cf_calc,0)<>0 THEN
         INSERT INTO NRD_UPLD_DTL ( record_type,
                                    user_id,
                                    order_no,
                                    ext_doc_id,
                                    vendor,
                                    doc_date,
                                    tax_basis,
                                    non_merch_code,
                                    non_merch_amt)
                             SELECT 'NMRCHT',
                                    L_tab_data(i).user_id,
                                    L_tab_data(i).order_no,
                                    L_tab_data(i).ext_doc_id,
                                    L_tab_data(i).vendor,
                                    L_tab_data(i).doc_date,
                                    L_tab_data(i).tax_amount,
                                    'Consolidation Fees',--non_merch_code,
                                    L_tab_data(i).cf_calc--non_merch_amt
                               FROM DUAL
                              WHERE L_tab_data(i).rn_head=1;
         IF NOT PKG_REIM_UPLD.FN_UPD_CALC_COMP('Consolidation Fees',
                                               L_tab_data(i).cf_calc,
                                               L_tab_data(i).seq_no) THEN
            DBMS_OUTPUT.PUT_LINE('Unable to update Calculated values');
            RETURN FALSE;
         END IF;
      END IF;
      IF NVL(L_tab_data(i).bf_calc,0)<>0 THEN
         INSERT INTO NRD_UPLD_DTL ( record_type,
                                    user_id,
                                    order_no,
                                    ext_doc_id,
                                    vendor,
                                    doc_date,
                                    tax_basis,
                                    non_merch_code,
                                    non_merch_amt)
                             SELECT 'NMRCHT',
                                    L_tab_data(i).user_id,
                                    L_tab_data(i).order_no,
                                    L_tab_data(i).ext_doc_id,
                                    L_tab_data(i).vendor,
                                    L_tab_data(i).doc_date,
                                    L_tab_data(i).tax_amount,
                                    'Customs Broker Fee',--non_merch_code,
                                    L_tab_data(i).bf_calc--non_merch_amt
                               FROM DUAL
                              WHERE L_tab_data(i).rn_head=1;
         IF NOT PKG_REIM_UPLD.FN_UPD_CALC_COMP('Customs Broker Fee',
                                               L_tab_data(i).bf_calc,
                                               L_tab_data(i).seq_no) THEN
            DBMS_OUTPUT.PUT_LINE('Unable to update Calculated values');
            RETURN FALSE;
         END IF;
      END IF;
      IF NVL(L_tab_data(i).bm_calc,0)<>0 THEN
         INSERT INTO NRD_UPLD_DTL ( record_type,
                                    user_id,
                                    order_no,
                                    ext_doc_id,
                                    vendor,
                                    doc_date,
                                    tax_basis,
                                    non_merch_code,
                                    non_merch_amt)
                             SELECT 'NMRCHT',
                                    L_tab_data(i).user_id,
                                    L_tab_data(i).order_no,
                                    L_tab_data(i).ext_doc_id,
                                    L_tab_data(i).vendor,
                                    L_tab_data(i).doc_date,
                                    L_tab_data(i).tax_amount,
                                    'Customs Brokerage-Entry Misc',--non_merch_code,
                                    L_tab_data(i).bm_calc--non_merch_amt
                               FROM DUAL
                              WHERE L_tab_data(i).rn_head=1;
         IF NOT PKG_REIM_UPLD.FN_UPD_CALC_COMP('Customs Brokerage-Entry Misc',
                                               L_tab_data(i).bm_calc,
                                               L_tab_data(i).seq_no) THEN
            DBMS_OUTPUT.PUT_LINE('Unable to update Calculated values');
            RETURN FALSE;
         END IF;
      END IF;
      IF NVL(L_tab_data(i).agent_commission,0)<>0 THEN
         INSERT INTO NRD_UPLD_DTL ( record_type,
                                    user_id,
                                    order_no,
                                    ext_doc_id,
                                    vendor,
                                    doc_date,
                                    tax_basis,
                                    non_merch_code,
                                    non_merch_amt)
                             SELECT 'NMRCHT',
                                    L_tab_data(i).user_id,
                                    L_tab_data(i).order_no,
                                    L_tab_data(i).ext_doc_id,
                                    L_tab_data(i).vendor,
                                    L_tab_data(i).doc_date,
                                    L_tab_data(i).tax_amount,
                                    'Agent Commission',--non_merch_code,
                                    L_tab_data(i).agent_commission--non_merch_amt
                               FROM DUAL
                              WHERE L_tab_data(i).rn_head=1;
      END IF;
      IF NVL(L_tab_data(i).spl_chrg_F050,0)<>0 THEN
         INSERT INTO NRD_UPLD_DTL ( record_type,
                                    user_id,
                                    order_no,
                                    ext_doc_id,
                                    vendor,
                                    doc_date,
                                    tax_basis,
                                    non_merch_code,
                                    non_merch_amt)
                             SELECT 'NMRCHT',
                                    L_tab_data(i).user_id,
                                    L_tab_data(i).order_no,
                                    L_tab_data(i).ext_doc_id,
                                    L_tab_data(i).vendor,
                                    L_tab_data(i).doc_date,
                                    L_tab_data(i).tax_amount,
                                    'Special Charge F050',--non_merch_code,
                                    L_tab_data(i).spl_chrg_F050--non_merch_amt
                               FROM DUAL
                              WHERE L_tab_data(i).rn_head=1;
      END IF;
      IF NVL(L_tab_data(i).spl_chrg_H060,0)<>0 THEN
         INSERT INTO NRD_UPLD_DTL ( record_type,
                                    user_id,
                                    order_no,
                                    ext_doc_id,
                                    vendor,
                                    doc_date,
                                    tax_basis,
                                    non_merch_code,
                                    non_merch_amt)
                             SELECT 'NMRCHT',
                                    L_tab_data(i).user_id,
                                    L_tab_data(i).order_no,
                                    L_tab_data(i).ext_doc_id,
                                    L_tab_data(i).vendor,
                                    L_tab_data(i).doc_date,
                                    L_tab_data(i).tax_amount,
                                    'Special Charge H060',--non_merch_code,
                                    L_tab_data(i).spl_chrg_H060--non_merch_amt
                               FROM DUAL
                              WHERE L_tab_data(i).rn_head=1;
      END IF;
      IF NVL(L_tab_data(i).tax_for_import,0)<>0 THEN
         INSERT INTO NRD_UPLD_DTL ( record_type,
                                    user_id,
                                    order_no,
                                    ext_doc_id,
                                    vendor,
                                    doc_date,
                                    tax_basis,
                                    non_merch_code,
                                    non_merch_amt)
                             SELECT 'NMRCHT',
                                    L_tab_data(i).user_id,
                                    L_tab_data(i).order_no,
                                    L_tab_data(i).ext_doc_id,
                                    L_tab_data(i).vendor,
                                    L_tab_data(i).doc_date,
                                    L_tab_data(i).tax_amount,
                                    'Tax for Import',--non_merch_code,
                                    L_tab_data(i).tax_for_import--non_merch_amt
                               FROM DUAL
                              WHERE L_tab_data(i).rn_head=1;
      END IF;
      
      IF NVL(L_tab_data(i).tax_amount,0)<>0 THEN
         INSERT INTO NRD_UPLD_DTL ( record_type,
                                    user_id,
                                    order_no,
                                    ext_doc_id,
                                    vendor,
                                    doc_date,
                                    tax_basis,
                                    non_merch_code,
                                    non_merch_amt)
                             SELECT 'NMRCHT',
                                    L_tab_data(i).user_id,
                                    L_tab_data(i).order_no,
                                    L_tab_data(i).ext_doc_id,
                                    L_tab_data(i).vendor,
                                    L_tab_data(i).doc_date,
                                    L_tab_data(i).tax_amount,
                                    'Tax Difference',--non_merch_code,
                                    L_tab_data(i).tax_amount--non_merch_amt
                               FROM DUAL
                              WHERE L_tab_data(i).rn_head=1;
      END IF;
      
      INSERT INTO nrd_upld_hdr ( record_type,
                                 user_id,
                                 order_no,
                                 file_name,
                                 type,
                                 doc_date,
                                 vendor,
                                 ext_doc_id,
                                 location,
                                 last_update_id,
                                 last_datetime,
                                 comment_type,
                                 text)
                          SELECT 'COMM',
                                 L_tab_data(i).user_id,
                                 L_tab_data(i).order_no,
                                 L_tab_data(i).file_name,
                                 L_tab_data(i).type,
                                 L_tab_data(i).doc_date,
                                 L_tab_data(i).vendor,
                                 L_tab_data(i).ext_doc_id,
                                 L_tab_data(i).location,
                                 user,--last_update_id,
                                 sysdate,--last_datetime,
                                 'E',--comment_type
                                 L_tab_data(i).text_e--text
                            FROM dual
                           WHERE L_tab_data(i).rn_head=1
                             AND L_tab_data(i).text_e IS NOT NULL
                           UNION ALL
                          SELECT 'COMM',
                                 L_tab_data(i).user_id,
                                 L_tab_data(i).order_no,
                                 L_tab_data(i).file_name,
                                 L_tab_data(i).type,
                                 L_tab_data(i).doc_date,
                                 L_tab_data(i).vendor,
                                 L_tab_data(i).ext_doc_id,
                                 L_tab_data(i).location,
                                 user,--last_update_id,
                                 sysdate,--last_datetime,
                                 'I',--comment_type
                                 L_tab_data(i).text_i--text
                            FROM dual
                           WHERE L_tab_data(i).rn_head=1
                             AND L_tab_data(i).text_i IS NOT NULL;
                             
      INSERT INTO nrd_upld_dtl ( record_type,
                                 user_id,
                                 order_no,
                                 doc_date,
                                 vendor,
                                 ext_doc_id,
                                 item,
                                 comment_type,
                                 text)
                          SELECT 'COMM',
                                 L_tab_data(i).user_id,
                                 L_tab_data(i).order_no,
                                 L_tab_data(i).doc_date,
                                 L_tab_data(i).vendor,
                                 L_tab_data(i).ext_doc_id,
                                 L_tab_data(i).item,
                                 'E',--comment_type
                                 L_tab_data(i).text_e--text
                            FROM dual
                           WHERE L_tab_data(i).item IS NOT NULL
                             AND L_tab_data(i).text_e IS NOT NULL
                           UNION ALL
                          SELECT 'COMM',
                                 L_tab_data(i).user_id,
                                 L_tab_data(i).order_no,
                                 L_tab_data(i).doc_date,
                                 L_tab_data(i).vendor,
                                 L_tab_data(i).ext_doc_id,
                                 L_tab_data(i).item,
                                 'I',--comment_type
                                 L_tab_data(i).text_i--text
                            FROM dual
                           WHERE L_tab_data(i).item IS NOT NULL
                             AND L_tab_data(i).text_i IS NOT NULL;
      
   END LOOP;
   FORALL i IN 1..L_tab_data.COUNT
   UPDATE nrd_upld_input_stg
      SET status_code='I',
          status_description='Caclulation Done.'
    WHERE seq_no=L_tab_data(i).seq_no;
   
   COMMIT;
   RETURN TRUE;

EXCEPTION
   WHEN OTHERS THEN
      ROLLBACK;
      O_error_msg:=SQLERRM;
      RETURN FALSE;
   
END FN_CALC_DATA;
---------------------------------------------------------------------------------------------------------------------
END PKG_REIM_UPLD;
/

CREATE OR REPLACE PUBLIC SYNONYM PKG_REIM_UPLD
FOR SCH_MST.PKG_REIM_UPLD;
/
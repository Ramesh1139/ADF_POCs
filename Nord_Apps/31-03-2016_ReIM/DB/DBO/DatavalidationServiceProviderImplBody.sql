/******************************************************************************
* Service Name     : DatavalidationService
* Namespace        : http://www.oracle.com/retail/reim/integration/services/DatavalidationService/v1
* Description      : $service.documentation
*
*******************************************************************************/
CREATE OR REPLACE PACKAGE DatavalidationServiceProviderI AS


/******************************************************************************
 *
 * Operation       : getValidationReIMUpldValidatio
 * Description     : ${operation.documentation} 
 * 
 * Input           : "RIB_ReIMUpldValidationReq_REC"
 * Namespace       : http://www.oracle.com/retail/integration/custom/bo/ExtOfReIMUpld/v1
 * Description     : ${operation.input.documentation}
 * 
 * Output          : "RIB_ReIMValidationRetMsg_REC"
 * Namespace       : http://www.oracle.com/retail/integration/custom/bo/ExtOfReIMUpld/v1
 * Description     : ${operation.output.documentation}
 * 
 * Valid errorType : com.oracle.retail.integration.services.exception.v1.IllegalArgumentWSFaultException
 * Description     : Throw this exception when a "soap:Client" side message problem occurs.
 *
 * Valid errorType : com.oracle.retail.integration.services.exception.v1.EntityDoesExistsWSFaultException
 * Description     : Throw this exception when the Transfer does not exists.
 *
 * Valid errorType : com.oracle.retail.integration.services.exception.v1.IllegalStateWSFaultException
 * Description     : Throw this exception when an unknown "soap:Server" side problem occurs.
 *
 * Valid errorType : com.oracle.retail.integration.services.exception.v1.ValidationWSFaultException
 * Description     : Validation fault to be thrown by all operations
 *
 * Valid errorType : com.oracle.retail.integration.services.exception.v1.ValidationWSFaultException
 * Description     : use this errorType to throw validation errors.
 
 * Valid errorType : java.lang.UnsupportedOperationException
 * Description     : If the service operation is not implemented in this release use this errorType.
 * 
 *     
 ******************************************************************************/
PROCEDURE getValidationReIMUpldValidatio(
                          I_serviceOperationContext IN OUT "RIB_ServiceOpContext_REC",
                                                    I_businessObject          IN  "RIB_ReIMUpldValidationReq_REC",
                                                    O_serviceOperationStatus  OUT "RIB_ServiceOpStatus_REC",
                          O_businessObject          OUT "RIB_ReIMValidationRetMsg_REC"
                          );
/******************************************************************************/



END DatavalidationServiceProviderI;
/
 

/******************************************************************************
* Service Name     : DatavalidationService
* Namespace        : http://www.oracle.com/retail/reim/integration/services/DatavalidationService/v1
* Description      : $service.documentation
*
*******************************************************************************/
CREATE OR REPLACE PACKAGE BODY DatavalidationServiceProviderI AS


/******************************************************************************
 *
 * Operation       : getValidationReIMUpldValidatio
 * Description     : ${operation.documentation}           
 * 
 * Input           : "RIB_ReIMUpldValidationReq_REC"
 * Namespace       : http://www.oracle.com/retail/integration/custom/bo/ExtOfReIMUpld/v1
 * Description     : ${operation.input.documentation}
 * 
 * Output          : "RIB_ReIMValidationRetMsg_REC"
 * Namespace       : http://www.oracle.com/retail/integration/custom/bo/ExtOfReIMUpld/v1
 * Description     : ${operation.output.documentation}
 * 
 * Valid errorType : com.oracle.retail.integration.services.exception.v1.IllegalArgumentWSFaultException
 * Description     : Throw this exception when a "soap:Client" side message problem occurs.
 *
 * Valid errorType : com.oracle.retail.integration.services.exception.v1.EntityDoesExistsWSFaultException
 * Description     : Throw this exception when the Transfer does not exists.
 *
 * Valid errorType : com.oracle.retail.integration.services.exception.v1.IllegalStateWSFaultException
 * Description     : Throw this exception when an unknown "soap:Server" side problem occurs.
 *
 * Valid errorType : com.oracle.retail.integration.services.exception.v1.ValidationWSFaultException
 * Description     : Validation fault to be thrown by all operations
 *
 * Valid errorType : com.oracle.retail.integration.services.exception.v1.ValidationWSFaultException
 * Description     : use this errorType to throw validation errors.
 
 * Valid errorType : java.lang.UnsupportedOperationException
 * Description     : If the service operation is not implemented in this release use this errorType.
 * 
 *     
 ******************************************************************************/
PROCEDURE getValidationReIMUpldValidatio(
                          I_serviceOperationContext IN OUT "RIB_ServiceOpContext_REC",
                                                    I_businessObject          IN  "RIB_ReIMUpldValidationReq_REC",
                                                    O_serviceOperationStatus  OUT "RIB_ServiceOpStatus_REC",
                          O_businessObject          OUT "RIB_ReIMValidationRetMsg_REC"
                          )
                          
    IS
    
    status "RIB_SuccessStatus_REC" := NULL;
    successStatus_TBL "RIB_SuccessStatus_TBL" :="RIB_SuccessStatus_TBL"();
    L_response "RIB_ReIMBatchReturn_TBL":="RIB_ReIMBatchReturn_TBL"();
    
    L_ext_doc_id  v_nrd_im_doc_head.ext_doc_id%TYPE;
    L_ext_doc_id2 v_nrd_im_doc_head.ext_doc_id%TYPE;
    L_check_flag  VARCHAR2(1);
    L_status_flag VARCHAR2(1):= 'V';
    L_order_status_flag VARCHAR2(1);
    L_currency_val_flag VARCHAR2(1);
    L_status_description VARCHAR2(2000):=NULL;
    L_vendor_type varchar2(6);
    L_vendor varchar2(10);
    L_order_no v_ordhead.order_no%TYPE;
    L_item v_nrd_item_supplier.item%TYPE;
    L_location v_ordloc.location%TYPE;
    L_qty_ordrerd v_ordloc.qty_ordered%TYPE;
    L_currency_code v_nrd_currency_rates.currency_code%TYPE;
    L_order_currency v_nrd_currency_rates.currency_code%TYPE;
    L_sup_currency v_nrd_currency_rates.currency_code%TYPE;
    L_terms v_nrd_terms.terms%TYPE;
    L_terms_in v_nrd_terms.terms%TYPE;
    L_system_flag VARCHAR2(1):='I';
    L_type VARCHAR2(6);
    L_nmrch_wo_po VARCHAR2(1):='N';
    L_doc_date DATE;
    L_reason_code v_nrd_im_reason_codes.reason_code_id%TYPE;
   
   --Check For the existence Of PO in RMS
   CURSOR C_flag_system IS
   SELECT 'R',status,currency_code
     FROM v_ordhead
    WHERE order_no=L_order_no;
   
   --Ext_doc_id should not be already present in ReIM Tables
   CURSOR C_check_doc_id IS 
   SELECT 'Y' 
     FROM v_nrd_im_doc_head
    WHERE status<>'DELETE'
      AND ((ext_doc_id=L_ext_doc_id) 
           OR (L_ext_doc_id2 IS NOT NULL AND ext_doc_id=L_ext_doc_id2));
   
   --Check for Duplicate Invoice
   CURSOR C_dup_invc IS
   SELECT 'Y'
     FROM v_nrd_im_doc_head
    WHERE ext_doc_id=L_ext_doc_id
      AND doc_date=L_doc_date
      AND vendor=L_vendor
      AND status<>'DELETE';
   
   --Validate Vendor Exists in RMS or Not
   CURSOR C_exists_vendor IS 
   SELECT 'Y',terms
     FROM v_sups
    WHERE L_vendor_type ='SUPP' AND L_vendor=TO_CHAR(supplier)
   UNION ALL
   SELECT 'Y',terms 
     FROM v_nrd_partner 
    WHERE L_vendor_type in ('BR','AG','CO','FF')  AND L_vendor=partner_id;
   
   --Check if the vendor in invoice matches the Supplier vendor or 
   --belongs to same supplier group
   CURSOR C_exists_vendor_po IS 
   SELECT 'Y'
     FROM v_ordhead oh,
          v_sups sup
    WHERE oh.order_no=L_order_no
      AND oh.supplier=sup.supplier
      AND sup.supplier_parent=L_vendor
   UNION ALL
   SELECT 'Y'
     FROM v_ordhead oh,
          v_sups sup,
          im_supplier_group_members  isgm,
          im_supplier_group_members  isgm1
    WHERE oh.order_no=L_order_no
      AND oh.supplier=sup.supplier
      AND sup.supplier_parent=isgm.supplier_id
      AND isgm.group_id=isgm1.group_id
      AND isgm1.supplier_id=L_vendor;
   
   --Check if the Item is on the PO
   CURSOR C_exist_po_item IS
   SELECT 'Y'
     FROM v_ordloc ol
    WHERE ol.order_no=L_order_no
      AND ol.item=L_item;
   
   --Check the PO-Item is for the location or not
   CURSOR C_exist_po_loc IS
   SELECT 'Y'
     FROM v_ordloc ol,
          v_nrd_location loc 
    WHERE ol.order_no=L_order_no
      AND ol.item=L_item
      AND ol.location=loc.location_no
      AND DECODE(ol.loc_type,'W',loc.physical_wh_no,ol.location)=L_location;
   
   --Get ordered quantity. It must not be 0
   CURSOR C_get_qty_ord IS
   SELECT qty_ordered
     FROM v_ordloc 
    WHERE order_no=L_order_no
      AND item=L_item
      AND location=L_location;     
   
   --Validate Currency
   CURSOR C_check_currency IS
   SELECT 'Y'
     FROM v_nrd_currency_rates
    WHERE currency_code=L_currency_code;
   
   --Validate terms
   CURSOR C_check_terms IS
   SELECT 'Y'
     FROM v_nrd_terms
    WHERE terms=to_number(L_terms_in)
      AND enabled_flag='Y';
   
   --Validate The Item
   CURSOR C_check_item_supplier IS 
   SELECT 'Y' 
     FROM v_nrd_item_supplier its,
          v_sups sup
    WHERE its.item=L_item
      AND its.supplier=sup.supplier
      AND sup.supplier_parent=L_vendor;
   
   --Validate Credit an debit Memo Reason Code
   CURSOR C_check_rc IS
   SELECT 'Y'
     FROM v_nrd_im_reason_codes
    WHERE reason_code_id=L_reason_code
      AND action= DECODE(L_type,'CRDMEC','CMC'
                               ,'DEBMEC','CBC');
   
   --Get Supplier Currency
   CURSOR C_get_sup_curr IS
   SELECT currency_code
     FROM v_sups
    WHERE supplier=L_vendor;
   
   --Get the Default Terms
   CURSOR C_get_terms IS
   SELECT default_pay_now_terms 
     FROM im_system_options;
   
    BEGIN
    
        FOR i IN 1..I_businessObject.ReIMUpldValidationDtl_TBL.COUNT
        LOOP
           L_response.EXTEND;
           L_status_description:=NULL;
           L_status_flag:='V';
           L_ext_doc_id := I_businessObject.ReIMUpldValidationDtl_TBL(i).ext_doc_id;
           L_doc_date:=I_businessObject.ReIMUpldValidationDtl_TBL(i).doc_date;
           L_ext_doc_id2 := I_businessObject.ReIMUpldValidationDtl_TBL(i).ext_doc_id_2;
           L_type :=I_businessObject.ReIMUpldValidationDtl_TBL(i).type;
           L_vendor_type := I_businessObject.ReIMUpldValidationDtl_TBL(i).vendor_type;
           L_vendor :=I_businessObject.ReIMUpldValidationDtl_TBL(i).vendor;
           L_order_no := I_businessObject.ReIMUpldValidationDtl_TBL(i).order_no;
           L_location:=I_businessObject.ReIMUpldValidationDtl_TBL(i).location;
           L_item:=I_businessObject.ReIMUpldValidationDtl_TBL(i).item;
           L_currency_code:=I_businessObject.ReIMUpldValidationDtl_TBL(i).currency_code;
           L_terms_in:=I_businessObject.ReIMUpldValidationDtl_TBL(i).terms;
           L_reason_code:=I_businessObject.ReIMUpldValidationDtl_TBL(i).reason_code;
           
           L_system_flag:='I';
           OPEN C_flag_system;
           FETCH C_flag_system INTO L_system_flag,L_order_status_flag,L_order_currency;
           CLOSE C_flag_system;
           
           IF L_type IN('NMRCHI','CRDMEC','DEBMEC') THEN
              IF L_order_no IS NULL THEN 
                 L_system_flag:='R';
                 L_nmrch_wo_po:='Y';
              ELSE 
                 L_nmrch_wo_po:='N';
                 IF L_system_flag='I' THEN 
                    L_status_flag:='E';
                    L_status_description:=L_status_description||'| Invalid Order_no';
                 END IF;
              END IF;
           ELSE   
              -- IF doc_type is not Non-Merch Invoice and order no is not there in ordhead
              -- Flag the system as ReIM
              IF L_system_flag='I' THEN 
                  L_system_flag:='R';
                  L_status_flag:='E';
                  L_status_description:=L_status_description||'| Invalid Order_no';
              END IF;
           END IF;              
           
           IF L_system_flag='R' THEN
              L_check_flag:='N';
              OPEN C_check_doc_id;
              FETCH C_check_doc_id INTO L_check_flag;
              CLOSE C_check_doc_id;
              IF L_check_flag='Y' THEN
                  L_status_flag:='E';
                  L_status_description:=L_status_description||'| Document Number Already Exists';
              END IF;
              
              L_check_flag:='N';
              OPEN C_dup_invc;
              FETCH C_dup_invc INTO L_check_flag;
              CLOSE C_dup_invc;
              IF L_check_flag='Y' THEN
                  L_status_flag:='E';
                  L_status_description:=L_status_description||'| Duplicate Invoice';
              END IF;
              
              --If terms is null for Credit or Debit Memo then update with default terms
              IF L_terms_in IS NULL THEN
                  IF L_type IN ('CRDMEC','DEBMEC') THEN
                     OPEN C_get_terms;
                     FETCH C_get_terms into L_terms_in;
                     CLOSE C_get_terms;
                  ELSE 
                     L_terms_in:=L_terms;
                  END IF;
              END IF;
              
              IF L_vendor_type='SUPP' AND L_item IS NOT NULL THEN
                 L_check_flag:='N';
                 OPEN C_check_item_supplier;
                 FETCH C_check_item_supplier INTO L_check_flag;
                 CLOSE C_check_item_supplier;
                 IF L_check_flag='N' THEN
                     L_status_flag:='E';
                     L_status_description:=L_status_description||'| Invalid item';
                 END IF;
              END IF;
              
              L_check_flag:='N';
              OPEN C_exists_vendor;
              FETCH C_exists_vendor INTO L_check_flag,L_terms;
              CLOSE C_exists_vendor;
              IF L_check_flag='N' THEN
                  L_status_flag:='E';
                  L_status_description:=L_status_description||'| Vendor Number Invalid';
              END IF;
                 
              IF L_nmrch_wo_po='N' THEN -- Dont do these validations if The Invoice is Non_Merch/CRDMEC/debmec without a PO 
              
                 L_check_flag:='N';
                 IF L_vendor_type='SUPP'  THEN
                    OPEN C_exists_vendor_po;
                    FETCH C_exists_vendor_po INTO L_check_flag;
                    CLOSE C_exists_vendor_po;
                 END IF;
                 IF L_check_flag='N' AND L_vendor_type NOT IN ('BR','AG','CO','FF') THEN
                     L_status_flag:='E';
                     L_status_description:=L_status_description||'| Vendor not on PO';
                 END IF;
                 
                 IF L_order_status_flag NOT IN ('A','C') THEN
                     L_status_flag:='E';
                     L_status_description:=L_status_description||'| Order is not in Approved/Closed Status';
                 END IF;
                 IF L_order_currency<>L_currency_code THEN
                     L_status_flag:='E';
                     L_status_description:=L_status_description||'| Currency not same as PO Currency';
                 END IF;
                 
                 IF L_item IS NOT NULL THEN
                    L_check_flag:='N';
                    OPEN C_exist_po_item;
                    FETCH C_exist_po_item INTO L_check_flag;
                    CLOSE C_exist_po_item;
                    IF L_check_flag='N' THEN
                        L_status_flag:='E';
                        L_status_description:=L_status_description||'| Invalid Order-Item';
                    END IF;
                    
                    L_check_flag:='N';
                    OPEN C_exist_po_loc;
                    FETCH C_exist_po_loc INTO L_check_flag;
                    CLOSE C_exist_po_loc;
                    IF L_check_flag='N' THEN
                        L_status_flag:='E';
                        L_status_description:=L_status_description||'| Invalid Order Location';
                    END IF;
                 END IF;
                 
                 OPEN C_get_qty_ord;
                 FETCH C_get_qty_ord INTO L_qty_ordrerd;
                 CLOSE C_get_qty_ord;
                 IF NVL(L_qty_ordrerd,1)=0 THEN
                     L_status_flag:='E';
                     L_status_description:=L_status_description||'| OrderQty cannot be 0';
                 END IF;
                 
              END IF;

              --Validate for Ext_doc_id_2, vendor_2
              L_vendor_type := I_businessObject.ReIMUpldValidationDtl_TBL(i).vendor_type_2;
              L_vendor :=I_businessObject.ReIMUpldValidationDtl_TBL(i).vendor_2;
              IF (L_vendor_type IS NOT NULL AND L_Vendor IS NOT NULL) THEN
                 L_check_flag:='N';
                 OPEN C_exists_vendor;
                 FETCH C_exists_vendor INTO L_check_flag,L_terms;
                 CLOSE C_exists_vendor;
                 IF L_check_flag='N' THEN
                     L_status_flag:='E';
                     L_status_description:=L_status_description||'| 2nd Vendor Number Invalid';
                 END IF;
                 
                 IF L_nmrch_wo_po='N' THEN -- Don't do these validations if The Invoice is Non_Merch without a PO  
                    L_check_flag:='N';
                    IF L_vendor_type='SUPP' THEN
                       OPEN C_exists_vendor_po;
                       FETCH C_exists_vendor_po INTO L_check_flag;
                       CLOSE C_exists_vendor_po;
                    END IF;
                    IF L_check_flag='N' AND L_vendor_type NOT IN ('BR','AG','CO','FF') THEN
                        L_status_flag:='E';
                        L_status_description:=L_status_description||'| Vendor not on PO';
                    END IF;
                 END IF;
              END IF;
               
              L_check_flag:='N';
              OPEN C_check_currency;
              FETCH C_check_currency INTO L_check_flag;
              CLOSE C_check_currency;
              IF L_check_flag='N' THEN
                  L_status_flag:='E';
                  L_status_description:=L_status_description||'| Invalid Currency Code';
              END IF;
              
              L_check_flag:='N';
              OPEN C_check_terms;
              FETCH C_check_terms INTO L_check_flag;
              CLOSE C_check_terms;
              IF L_check_flag='N' THEN
                  L_status_flag:='E';
                  L_status_description:=L_status_description||'| Invalid terms';
              END IF;
              
              IF L_type IN ('CRDMEC','DEBMEC') AND L_item IS NOT NULL THEN
                 L_check_flag:='N';
                 OPEN C_check_rc;
                 FETCH C_check_rc INTO L_check_flag;
                 CLOSE C_check_rc;
                 IF L_check_flag='N' THEN
                     L_status_flag:='E';
                     L_status_description:=L_status_description||'| Invalid Reason Code';
                 END IF;
              END IF;
              
              IF L_type IN ('NMRCHI','CRDMEC','DEBMEC') THEN
                 OPEN C_get_sup_curr;
                 FETCH C_get_sup_curr INTO L_sup_currency;
                 CLOSE C_get_sup_curr;
              ELSE 
                 L_sup_currency:=L_currency_code;
              END IF;
              
           ELSE
               L_status_description:='Data Validation Success';
               L_status_flag:='V';
           END IF;
           L_response(i):= "RIB_ReIMBatchReturn_REC"(0,
                                                     I_businessObject.ReIMUpldValidationDtl_TBL(i).seq_no,
                                                     I_businessObject.ReIMUpldValidationDtl_TBL(i).user_id,
                                                     I_businessObject.ReIMUpldValidationDtl_TBL(i).file_name,
                                                     L_ext_doc_id,
                                                     I_businessObject.ReIMUpldValidationDtl_TBL(i).doc_date,
                                                     I_businessObject.ReIMUpldValidationDtl_TBL(i).type,
                                                     I_businessObject.ReIMUpldValidationDtl_TBL(i).vendor,
                                                     I_businessObject.ReIMUpldValidationDtl_TBL(i).order_no,
                                                     L_sup_currency, --currency_code
                                                     L_terms_in,
                                                     I_businessObject.ReIMUpldValidationDtl_TBL(i).item,
                                                     L_status_flag,
                                                     NVL(L_status_description,'Data Validation Success'),
                                                     L_system_flag);
        END LOOP;

       
        
        O_businessObject := "RIB_ReIMValidationRetMsg_REC"(0,L_response);
        
        status := "RIB_SuccessStatus_REC"(0, 'getValidationReIMUpldValidatio service call was successful.');
        successStatus_TBL.EXTEND;
        successStatus_TBL(1) := status;
        O_serviceOperationStatus := "RIB_ServiceOpStatus_REC"(0, successStatus_TBL);
        
        
    END getValidationReIMUpldValidatio;
/******************************************************************************/



END DatavalidationServiceProviderI;
/

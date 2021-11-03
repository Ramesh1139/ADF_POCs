-- ****************************************************************************
-- Script Name          : n5rim10.sql
-- Language/Shell       : PL/SQL
-- Description          : The sql block calls webservice
--                        ReIMInsertServiceConsumer.getReIMInsReIMInsertReq
--
-- Returns              : None
-- Return Values        : None
--
-- Called from scripts/ :
-- programs(if any)     :
--
-- Execute Mode
-- (batch/interactive)  : Interactive
--
-- Author               : Bikash, Infosy Limited.
-- Date written         : 15-Feb-2016
--
-- Modification History :
--
-- Description of change                 Date           Modified by
-- ---------------------                 ----           -----------
-- ****************************************************************************

DECLARE

L_svc_link           nrd_upld_ctrl.webservice_link%TYPE;

CURSOR C_get_targets IS
SELECT distinct webservice_link
  FROM nrd_upld_ctrl nuc,
       nrd_upld_hdr nuh
 WHERE nuc.location=nuh.location
   AND nuh.system_flag='R'
   AND nuh.status_code='I';

CURSOR C_create_req_msg IS
SELECT "OBJ_REIMINSERTREQ" (tbl)
  FROM (SELECT CAST (
                  MULTISET (
                     SELECT "OBJ_REIMINSERTREQREC" (
                               nuh.record_type,
                               nuh.user_id,
                               nuh.file_name,
                               nuh.ext_doc_id,
                               nuh.vendor,
                               nuh.doc_date,
                               nuh.order_no,
                               nuh.status,
                               nuh.vendor_type,
                               nuh.terms,
                               NULL, --due_date
                               nuh.currency_code,
                               nuh.total_cost,
                               nuh.total_qty,
                               nuh.custom_doc_ref_1,
                               nuh.custom_doc_ref_2,
                               nuh.custom_doc_ref_3,
                               nuh.custom_doc_ref_4,
                               nuh.total_cost_inc_tax,
                               nuh.supplier_site_id,
                               nuh.tax_basis,
                               nuh.comment_type,
                               CAST (
                                  MULTISET (
                                     SELECT "OBJ_REIMINSDTL" (
                                               nud.unit_cost
                                               ,nud.invoice_qty
                                               ,nud.reason_code
                                               ,nud.non_merch_code
                                               ,nud.non_merch_amt
                                               ,nud.record_type
                                               ,nuh.user_id
                                               ,nuh.ext_doc_id
                                               ,nuh.vendor
                                               ,nuh.doc_date
                                               ,nud.order_no
                                               ,nud.tax_basis
                                               ,nud.comment_type
                                               ,nud.text
                                               ,nud.item
                                             )
                                      FROM nrd_upld_hdr nuh1,
                                           nrd_upld_dtl nud
                                     WHERE nuh1.rowid=nuh.rowid
                                       AND nuh1.ext_doc_id = nud.ext_doc_id(+)
                                       AND NVL (nuh1.order_no, 0) = NVL (nud.order_no(+), 0)
                                       AND nuh1.vendor = nud.vendor(+)
                                       AND nuh1.doc_date = nud.doc_date(+)
                                       AND nuh1.system_flag='R'
                                       AND nuh1.status_code='I'
                                       AND DECODE(nuh1.record_type,'COMM',nuh1.comment_type,'X')
                                             =DECODE(nuh1.record_type,'COMM',nud.comment_type(+),'X')) AS "TBL_OBJ_REIMINSDTL"),
                               nuh.text,
                               nuh.location,
                               nuh.type)
                        FROM NRD_UPLD_HDR nuh, 
                             nrd_upld_ctrl nc
                       WHERE nc.location=nuh.location
                         AND nuh.system_flag='R'
                         AND nuh.status_code='I'
                         AND nc.webservice_link=L_svc_link) AS "TBL_OBJ_REIMINSERTREQREC") tbl
          FROM DUAL);
          
TYPE Tab_type_svc_link IS TABLE OF nrd_upld_ctrl.webservice_link%TYPE
INDEX BY BINARY_INTEGER;
L_tab_svc_link       Tab_type_svc_link;
L_ser_endpt          VARCHAR2(1000);

L_reiminsreq "OBJ_REIMINSERTREQ" :=NULL;
L_servicereturn_msg     "OBJ_REIMBATCHIRETURN" :=NULL;
 
begin
   :ERROR_CODE:=0;
   OPEN C_get_targets;
   FETCH C_get_targets BULK COLLECT INTO L_tab_svc_link;
   CLOSE C_get_targets;
   
   FOR i IN 1..L_tab_svc_link.COUNT
   LOOP
      L_svc_link:=L_tab_svc_link(i);
      OPEN C_create_req_msg;
      FETCH C_create_req_msg INTO L_reiminsreq;
      CLOSE C_create_req_msg;
      
      L_ser_endpt:=L_svc_link||'/ReIMInsertBean/ReIMInsertService';
      ReIMInsertServiceConsumer.setEndpoint(L_ser_endpt);

      L_servicereturn_msg:=new OBJ_REIMBATCHIRETURN(NULL);
      L_servicereturn_msg:=ReIMInsertServiceConsumer.getReIMInsReIMInsertReq(L_reiminsreq);

      FOR i IN 1..L_servicereturn_msg.reIMBatchIReturnRec_.COUNT
      LOOP
      UPDATE nrd_upld_input_stg
         SET status_code=L_servicereturn_msg.reIMBatchIReturnRec_(i).status_code_,
             status_description=L_servicereturn_msg.reIMBatchIReturnRec_(i).status_description_
       WHERE ext_doc_id=L_servicereturn_msg.reIMBatchIReturnRec_(i).ext_doc_id_
         AND doc_date=L_servicereturn_msg.reIMBatchIReturnRec_(i).doc_date_
         AND vendor=L_servicereturn_msg.reIMBatchIReturnRec_(i).vendor_
         AND NVL(order_no,0)=NVL(L_servicereturn_msg.reIMBatchIReturnRec_(i).order_no_,0)
         AND status_code='I';
      
       UPDATE nrd_upld_input_stg
         SET batch_date=SYSDATE,
             batch_status='COMPLETE'
       WHERE file_name=L_servicereturn_msg.reIMBatchIReturnRec_(i).file_name_;
      
      UPDATE nrd_upld_hdr
         SET status_code=L_servicereturn_msg.reIMBatchIReturnRec_(i).status_code_,
             status_description=L_servicereturn_msg.reIMBatchIReturnRec_(i).status_description_
       WHERE ext_doc_id=L_servicereturn_msg.reIMBatchIReturnRec_(i).ext_doc_id_
         AND doc_date=L_servicereturn_msg.reIMBatchIReturnRec_(i).doc_date_
         AND vendor=L_servicereturn_msg.reIMBatchIReturnRec_(i).vendor_
         AND NVL(order_no,0)=NVL(L_servicereturn_msg.reIMBatchIReturnRec_(i).order_no_,0)
         AND status_code='I';
      END LOOP;
   END LOOP;

   COMMIT;
exception
WHEN OTHERS THEN 
    NRD_WRITE_CUSTOM_LOG(SQLCODE,
                         SQLERRM,
                         'Failed In Insert Service',
                         'Job Name : n5rim10');
    :ERROR_CODE:=255;
end;
/
/******************************************************************************
* Service Name     : ReIMInsertService
* Namespace        : http://www.oracle.com/retail/reim/integration/services/ReIMInsertService/v1
* Description      : $service.documentation
*
*******************************************************************************/
CREATE OR REPLACE PACKAGE ReIMInsertServiceProviderImpl AS


/******************************************************************************
 *
 * Operation       : getReIMInsReIMInsertReq
 * Description     : ${operation.documentation} 
 * 
 * Input           : "RIB_ReIMInsertReq_REC"
 * Namespace       : http://www.oracle.com/retail/integration/custom/bo/ExtOfReIMIns/v1
 * Description     : ${operation.input.documentation}
 * 
 * Output          : "RIB_ReIMBatchIReturn_REC"
 * Namespace       : http://www.oracle.com/retail/integration/custom/bo/ExtOfReIMIns/v1
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
PROCEDURE getReIMInsReIMInsertReq(
                          I_serviceOperationContext IN OUT "RIB_ServiceOpContext_REC",
                                                    I_businessObject          IN  "RIB_ReIMInsertReq_REC",
                                                    O_serviceOperationStatus  OUT "RIB_ServiceOpStatus_REC",
                          O_businessObject          OUT "RIB_ReIMBatchIReturn_REC"
                          );
/******************************************************************************/

END ReIMInsertServiceProviderImpl;
/
 
/******************************************************************************
* Service Name     : ReIMInsertService
* Namespace        : http://www.oracle.com/retail/reim/integration/services/ReIMInsertService/v1
* Description      : $service.documentation
*
*******************************************************************************/
CREATE OR REPLACE PACKAGE BODY ReIMInsertServiceProviderImpl AS


/******************************************************************************
 *
 * Operation       : getReIMInsReIMInsertReq
 * Description     : ${operation.documentation}           
 * 
 * Input           : "RIB_ReIMInsertReq_REC"
 * Namespace       : http://www.oracle.com/retail/integration/custom/bo/ExtOfReIMIns/v1
 * Description     : ${operation.input.documentation}
 * 
 * Output          : "RIB_ReIMBatchIReturn_REC"
 * Namespace       : http://www.oracle.com/retail/integration/custom/bo/ExtOfReIMIns/v1
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
PROCEDURE getReIMInsReIMInsertReq(
                          I_serviceOperationContext IN OUT "RIB_ServiceOpContext_REC",
                                                    I_businessObject          IN  "RIB_ReIMInsertReq_REC",
                                                    O_serviceOperationStatus  OUT "RIB_ServiceOpStatus_REC",
                          O_businessObject          OUT "RIB_ReIMBatchIReturn_REC"
                          )
                          
   IS

   status "RIB_SuccessStatus_REC" := NULL;
   successStatus_TBL "RIB_SuccessStatus_TBL" := "RIB_SuccessStatus_TBL"();
   L_response "RIB_ReIMBatchIReturnRec_TBL":="RIB_ReIMBatchIReturnRec_TBL"();


   L_im_doc_seq im_doc_head.doc_id%TYPE;
   L_ext_doc_id VARCHAR2(30);
   L_check_flag VARCHAR2(1);
   L_terms NUMBER(6);
   L_terms_discount NUMBER(12,4);
   L_group_id im_doc_head.group_id%TYPE;
   L_currency_code im_doc_head.currency_code%TYPE;
   L_vdate DATE;
   L_exchange_rate im_doc_head.exchange_rate%TYPE;
   L_order_no im_doc_head.order_no%TYPE;
   L_freight_type im_doc_head.freight_type%TYPE;
   L_type im_doc_head.type%TYPE;
   L_comm_seq im_doc_head_comments.comment_id%TYPE;
   L_supp_site_id ordhead.supplier%TYPE;
   L_due_date DATE;
   L_doc_date DATE;
   
   --CURSOR to Prefix Ext-doc-id in case of Credit/Debit Memo
   CURSOR C_prefix_ext_doc_id IS
   SELECT DECODE(L_type,'CRDMEC',credit_memo_prefix_cost||L_ext_doc_id
                       ,'DEBMEC',debit_memo_prefix_cost||L_ext_doc_id
                       ,L_ext_doc_id)
     FROM im_system_options;
   
   CURSOR C_check_doc_exists IS
   SELECT 'Y'
     FROM im_doc_head
    WHERE ext_doc_id=L_ext_doc_id
      AND status<>'DELETE';

   CURSOR C_get_terms_disc IS
   SELECT percent
     FROM terms
    WHERE terms=L_terms;
   
   --Get Bank Exchange Rate
   CURSOR C_get_exchange_rate IS
   SELECT /*+ INDEX(c,PK_CURRENCIES) INDEX(cr, PK_CURRENCY_RATES) */
          FIRST_VALUE (cr.exchange_rate) OVER (PARTITION BY c.currency_code, cr.exchange_type
                                             ORDER BY cr.effective_date DESC)
     FROM currencies c, currency_rates cr
    WHERE cr.effective_date(+) <= L_vdate
      AND cr.currency_code(+) = c.currency_code
      AND c.currency_code = L_currency_code
      AND exchange_type = 'C';
   
   CURSOR C_get_freight_type IS
   SELECT ship_pay_method,supplier
     FROM ordhead 
    WHERE order_no=L_order_no;
    
   --Calculate Due_date
   CURSOR C_get_due_date IS 
   SELECT CASE 
          WHEN duedays IS NOT NULL THEN
             L_doc_date+duedays
          WHEN cutoff_day IS NOT NULL AND TO_CHAR(L_doc_date,'DD')<cutoff_day THEN
             TO_DATE(due_dom||'-'||TO_CHAR(ADD_MONTHS(L_doc_date,1),'MM-YYYY'),'DD-MM-YYYY')
          WHEN cutoff_day IS NOT NULL AND TO_CHAR(L_doc_date,'DD')>=cutoff_day THEN
             TO_DATE(due_dom||'-'||TO_CHAR(ADD_MONTHS(L_doc_date,2),'MM-YYYY'),'DD-MM-YYYY')
          END DUE_DATE
     FROM v_nrd_terms
    WHERE terms=L_terms
      AND enabled_flag='Y';

    
BEGIN
   
   SELECT vdate 
     INTO L_vdate 
     FROM period;
  
   FOR i IN 1..I_businessObject.ReIMInsertReqRec_TBL.COUNT
   LOOP
   
      L_response.EXTEND;
      L_type:=I_businessObject.ReIMInsertReqRec_TBL(i).type;
      L_ext_doc_id:=I_businessObject.ReIMInsertReqRec_TBL(i).ext_doc_id;
      L_terms:=I_businessObject.ReIMInsertReqRec_TBL(i).terms;
      L_currency_code:=I_businessObject.ReIMInsertReqRec_TBL(i).currency_code;
      L_order_no:=I_businessObject.ReIMInsertReqRec_TBL(i).order_no;
      L_doc_date:=I_businessObject.ReIMInsertReqRec_TBL(i).doc_date;
      
      OPEN C_prefix_ext_doc_id;
      FETCH C_prefix_ext_doc_id INTO L_ext_doc_id;
      CLOSE C_prefix_ext_doc_id;
      
      L_check_flag:='N';
      OPEN C_check_doc_exists;
      FETCH C_check_doc_exists INTO L_check_flag;
      CLOSE C_check_doc_exists;
      IF L_check_flag='N' THEN
         SELECT sch_rms13.im_doc_head_seq.NEXTVAL 
           INTO L_im_doc_seq
           FROM dual;
           
         SELECT sch_rms13.im_doc_group_list_seq.NEXTVAL 
           INTO L_group_id
           FROM dual;
         
         OPEN C_get_terms_disc;
         FETCH C_get_terms_disc INTO L_terms_discount;
         CLOSE C_get_terms_disc;
         
         OPEN C_get_exchange_rate;
         FETCH C_get_exchange_rate INTO L_exchange_rate;
         CLOSE C_get_exchange_rate;
         
         OPEN C_get_freight_type;
         FETCH C_get_freight_type INTO L_freight_type,L_supp_site_id;
         CLOSE C_get_freight_type;
         
         OPEN C_get_due_date;
         FETCH C_get_due_date INTO L_due_date;
         CLOSE C_get_due_date;
         
         IF L_type IN ('MRCHI','NMRCHI','DEBMEC','CRDMEC') THEN
            INSERT INTO v_nrd_im_doc_head
                        (doc_id,
                        type,
                        status,
                        order_no,
                        location,
                        loc_type,
                        total_discount,
                        group_id,
                        parent_id,
                        doc_date,
                        create_date,
                        create_id,
                        vendor_type,
                        vendor,
                        ext_doc_id,
                        edi_upload_ind,
                        edi_download_ind,
                        terms,
                        terms_dscnt_pct,
                        due_date,
                        payment_method,
                        match_id,
                        match_date,
                        approval_id,
                        approval_date,
                        pre_paid_ind,
                        pre_paid_id,
                        post_date,
                        currency_code,
                        exchange_rate,
                        total_cost,
                        total_qty,
                        manually_paid_ind,
                        custom_doc_ref_1,
                        custom_doc_ref_2,
                        custom_doc_ref_3,
                        custom_doc_ref_4,
                        last_update_id,
                        last_datetime,
                        freight_type,
                        ref_doc,
                        ref_auth_no,
                        cost_pre_match,
                        detail_matched,
                        best_terms,
                        best_terms_source,
                        best_terms_date,
                        best_terms_date_source,
                        variance_within_tolerance,
                        resolution_adjusted_total_cost,
                        resolution_adjusted_total_qty,
                        consignment_ind,
                        deal_id,
                        rtv_ind,
                        discount_date,
                        deal_type,
                        hold_status,
                        total_cost_inc_tax,
                        tax_disc_create_date,
                        dsd_ind,
                        ers_ind,
                        supplier_site_id,
                        manually_created_ind)
                 SELECT L_im_doc_seq,
                        L_type,
                        I_businessObject.ReIMInsertReqRec_TBL(i).status,
                        I_businessObject.ReIMInsertReqRec_TBL(i).order_no,
                        I_businessObject.ReIMInsertReqRec_TBL(i).location,
                        CASE WHEN EXISTS(SELECT 1 FROM store where store=I_businessObject.ReIMInsertReqRec_TBL(i).location)
                             THEN 'S'
                             ELSE 'W' END loc_type,
                        L_terms_discount*I_businessObject.ReIMInsertReqRec_TBL(i).total_cost,
                        L_group_id,
                        NULL parent_id,
                        I_businessObject.ReIMInsertReqRec_TBL(i).doc_date,
                        sysdate create_date,
                        USER create_id,
                        I_businessObject.ReIMInsertReqRec_TBL(i).vendor_type,
                        I_businessObject.ReIMInsertReqRec_TBL(i).vendor,
                        L_ext_doc_id,
                        'N' edi_upload_ind,
                        'N' edi_download_ind,
                        I_businessObject.ReIMInsertReqRec_TBL(i).terms,
                        L_terms_discount,
                        L_due_date,
                        NULL payment_method,
                        NULL match_id,
                        NULL match_date,
                        user approval_id,
                        sysdate approval_date,
                        'N' pre_paid_ind,
                        NULL pre_paid_id,
                        NULL post_date,
                        I_businessObject.ReIMInsertReqRec_TBL(i).currency_code,
                        L_exchange_rate exchange_rate,
                        I_businessObject.ReIMInsertReqRec_TBL(i).total_cost,
                        I_businessObject.ReIMInsertReqRec_TBL(i).total_qty,
                        'N' manually_paid_ind,
                        I_businessObject.ReIMInsertReqRec_TBL(i).custom_doc_ref_1,
                        I_businessObject.ReIMInsertReqRec_TBL(i).custom_doc_ref_2,
                        I_businessObject.ReIMInsertReqRec_TBL(i).custom_doc_ref_3,
                        I_businessObject.ReIMInsertReqRec_TBL(i).custom_doc_ref_4,
                        USER last_update_id,
                        sysdate last_datetime,
                        L_freight_type freight_type,
                        NULL ref_doc,
                        NULL ref_auth_no,
                        'N' cost_pre_match,
                        'N' detail_matched,
                        DECODE(L_type,'CRDMEC',L_terms,'DEBMEC',L_terms) best_terms,
                        DECODE(L_type,'CRDMEC','DOC','DEBMEC','DOC') best_terms_source, 
                        DECODE(L_type,'CRDMEC',I_businessObject.ReIMInsertReqRec_TBL(i).doc_date
                                     ,'DEBMEC',I_businessObject.ReIMInsertReqRec_TBL(i).doc_date) best_terms_date,
                        DECODE(L_type,'CRDMEC','DOC','DEBMEC','DOC') best_terms_date_source,
                        NULL variance_within_tolerance,
                        I_businessObject.ReIMInsertReqRec_TBL(i).total_cost resolution_adjusted_total_cost,
                        DECODE(L_type
                               ,'NMRCHI',0
                               ,I_businessObject.ReIMInsertReqRec_TBL(i).total_qty)resolution_adjusted_total_qty,
                        'N' consignment_ind,
                        NULL deal_id,
                        'N' rtv_ind,
                        NULL discount_date,
                        NULL deal_type,
                        'N' hold_status,
                        I_businessObject.ReIMInsertReqRec_TBL(i).total_cost_inc_tax,
                        NULL tax_disc_create_date,
                        'N' dsd_ind,
                        'N' ers_ind,
                        NVL(I_businessObject.ReIMInsertReqRec_TBL(i).supplier_site_id,L_supp_site_id),
                        'Y' manually_created_ind
                   FROM dual;
                   
            INSERT INTO v_nrd_im_invoice_detail
                        (doc_id,
                        item,
                        unit_cost,
                        invoice_qty,
                        resolution_adjusted_unit_cost,
                        resolution_adjusted_qty,
                        status,
                        cost_matched,
                        qty_matched,
                        cost_variance_within_tolerance,
                        qty_variance_within_tolerance,
                        adjustment_pending,
                        last_update_id,
                        last_update_datetime,
                        tax_discrepancy_ind,
                        unit_freight,
                        unit_mrp,
                        unit_retail)
                 SELECT L_im_doc_seq,
                        d.item,
                        d.unit_cost,
                        d.invoice_qty,
                        d.unit_cost resolution_adjusted_unit_cost,
                        d.invoice_qty resolution_adjusted_qty,
                        DECODE(L_type,'MRCHI','UNMTCH'
                                     ,'MTCH') status,
                        'N' cost_matched,
                        'N' qty_matched,
                        NULL cost_variance_within_tolerance,
                        NULL qty_variance_within_tolerance,
                        'N' adjustment_pending,
                        user last_update_id,
                        sysdate last_update_datetime,
                        'N' tax_discrepancy_ind,
                        NULL unit_freight,
                        NULL unit_mrp,
                        NULL unit_retail
                   FROM TABLE(CAST(I_businessObject.ReIMInsertReqRec_TBL(i).ReIMInsDtl_TBL 
                              AS "RIB_ReIMInsDtl_TBL")) d
                  WHERE d.record_type<>'NMRCHT'
                    AND d.item IS NOT NULL;
                              
            INSERT INTO im_doc_tax -- No views
                        (doc_id,
                        tax_code,
                        tax_rate,
                        tax_basis,
                        tax_amount)
                 VALUES(L_im_doc_seq,
                        'Z',
                        0,
                        I_businessObject.ReIMInsertReqRec_TBL(i).tax_basis,
                        0);
                        
            INSERT INTO im_invoice_detail_tax  -- No views
                        (doc_id,
                        item,
                        tax_code,
                        tax_rate,
                        tax_basis,
                        tax_amount,
                        tax_formula,
                        tax_order,
                        tax_formula_type,
                        reverse_vat_ind)
                 SELECT L_im_doc_seq,
                        d.item,
                        'Z' tax_code,
                        0 tax_rate,
                        d.tax_basis,
                        0 tax_amount,
                        'CO' tax_formula,
                        1 tax_order,
                        'CO' tax_formula_type,
                        'N' reverse_vat_ind
                   FROM TABLE(CAST(I_businessObject.ReIMInsertReqRec_TBL(i).ReIMInsDtl_TBL 
                              AS "RIB_ReIMInsDtl_TBL")) d
                  WHERE d.record_type<>'NMRCHT'
                    AND d.item IS NOT NULL;
            
            INSERT INTO v_nrd_im_doc_non_merch
                        (doc_id, 
                        non_merch_code, 
                        non_merch_amt)
                 SELECT L_im_doc_seq,
                        nmc.non_merch_code,
                        d.non_merch_amount
                   FROM sch_rms13.non_merch_code_head_tl nmc,
                        TABLE(CAST(I_businessObject.ReIMInsertReqRec_TBL(i).ReIMInsDtl_TBL 
                              AS "RIB_ReIMInsDtl_TBL")) d
                  WHERE UPPER(d.non_merch_code)=UPPER(DECODE(SUBSTR(d.non_merch_code,1,7)
                                                      ,'Special',nmc.non_merch_code_desc||' '||nmc.non_merch_code
                                                      ,nmc.non_merch_code_desc))
                    AND d.record_type='NMRCHT';
            
            INSERT INTO im_doc_non_merch_tax
                        (doc_id,
                        non_merch_code,
                        tax_code,
                        tax_rate,
                        tax_basis,
                        tax_amount)
                 SELECT L_im_doc_seq,
                        nmc.non_merch_code,
                        'Z' tax_code,
                        0 tax_rate,
                        d.non_merch_amount tax_basis,
                        0 tax_amount
                   FROM sch_rms13.non_merch_code_head_tl nmc,
                        TABLE(CAST(I_businessObject.ReIMInsertReqRec_TBL(i).ReIMInsDtl_TBL 
                              AS "RIB_ReIMInsDtl_TBL")) d
                  WHERE UPPER(d.non_merch_code)=UPPER(DECODE(SUBSTR(d.non_merch_code,1,7)
                                                      ,'Special',nmc.non_merch_code_desc||' '||nmc.non_merch_code
                                                      ,nmc.non_merch_code_desc))
                    AND d.record_type='NMRCHT';
            
            INSERT INTO v_im_doc_detail_reason_codes
                        (im_doc_detail_reason_codes_id,
                        doc_id,
                        item,
                        reason_code_id,
                        status,
                        cost_matched,
                        qty_matched,
                        adjusted_unit_cost,
                        adjusted_qty,
                        last_update_id,
                        last_update_datetime,
                        orig_unit_cost,
                        orig_qty,
                        tax_matched,
                        unit_freight,
                        unit_mrp,
                        unit_retail)
                 SELECT im_doc_detail_reason_codes_seq.NEXTVAL,
                        L_im_doc_seq,
                        d.item,
                        d.debit_reason_code reason_code_id,
                        'APPRVE' status,
                        'N' cost_matched,
                        'N' qty_matched,
                        d.unit_cost adjusted_unit_cost,
                        d.invoice_qty adjusted_qty,
                        user last_update_id,
                        sysdate last_update_datetime,
                        d.unit_cost orig_unit_cost,
                        d.invoice_qty orig_qty,
                        NULL tax_matched,
                        0 unit_freight,
                        0 unit_mrp,
                        0 unit_retail
                   FROM TABLE(CAST(I_businessObject.ReIMInsertReqRec_TBL(i).ReIMInsDtl_TBL 
                              AS "RIB_ReIMInsDtl_TBL")) d
                  WHERE d.record_type IN ('CRDMEC','DEBMEC')
                    AND d.item IS NOT NULL;
                              
            INSERT INTO im_doc_detail_rc_tax
                        (im_doc_detail_reason_codes_id,
                        tax_code,
                        tax_rate,
                        tax_basis,
                        tax_amount,
                        tax_formula,
                        tax_order,
                        tax_formula_type,
                        reverse_vat_ind)
                 SELECT irc.im_doc_detail_reason_codes_id,
                        'Z' tax_code,
                        0 tax_rate,
                        d.tax_basis tax_basis,
                        0 tax_amount,
                        NULL tax_formula,
                        NVL(I_businessObject.ReIMInsertReqRec_TBL(i).order_no,0) tax_order,
                        NULL tax_formula_type,
                        'N' reverse_vat_ind
                   FROM TABLE(CAST(I_businessObject.ReIMInsertReqRec_TBL(i).ReIMInsDtl_TBL 
                              AS "RIB_ReIMInsDtl_TBL")) d,
                        im_doc_detail_reason_codes irc
                  WHERE irc.item=d.item
                    AND irc.doc_id=L_im_doc_seq
                    AND d.record_type IN ('CRDMEC','DEBMEC');
            
         ELSIF I_businessObject.ReIMInsertReqRec_TBL(i).record_type ='COMM' THEN
            SELECT im_doc_head_comments_seq.NEXTVAL 
              INTO L_comm_seq
              FROM dual;
             
            INSERT INTO v_nrd_im_doc_head_comments
                        (comment_id,
                        comment_type,
                        comment_text,
                        create_userid,
                        create_date,
                        document_id)  
                 VALUES(L_comm_seq,
                        I_businessObject.ReIMInsertReqRec_TBL(i).comment_type,
                        I_businessObject.ReIMInsertReqRec_TBL(i).text,
                        user,
                        sysdate,
                        L_im_doc_seq);
                       
            INSERT INTO v_nrd_im_doc_detail_comments
                        (comment_id,
                        comment_type,
                        comment_text,
                        create_userid,
                        create_datetime,
                        document_id,
                        item_no,
                        discrepancy_type,
                        reason_code_id,
                        debit_reason_code)
                 SELECT L_comm_seq,
                        d.comment_type,
                        d.text,
                        user create_id,
                        sysdate create_datetime,
                        L_im_doc_seq doc_id,
                        d.item,
                        NULL discrepancy_type,
                        d.debit_reason_code reason_code,
                        d.debit_reason_code
                   FROM TABLE(CAST(I_businessObject.ReIMInsertReqRec_TBL(i).ReIMInsDtl_TBL 
                              AS "RIB_ReIMInsDtl_TBL")) d
                  WHERE d.item IS NOT NULL;
                    
         END IF;
      END IF;
      
      L_response(i):="RIB_ReIMBatchIReturnRec_REC"(0
                                                  ,I_businessObject.ReIMInsertReqRec_TBL(i).record_type --record_type
                                                  ,I_businessObject.ReIMInsertReqRec_TBL(i).user_id --user_id
                                                  ,I_businessObject.ReIMInsertReqRec_TBL(i).file_name--file_name
                                                  ,I_businessObject.ReIMInsertReqRec_TBL(i).ext_doc_id --ext_doc_id
                                                  ,I_businessObject.ReIMInsertReqRec_TBL(i).vendor --vendor
                                                  ,I_businessObject.ReIMInsertReqRec_TBL(i).doc_date--doc_date
                                                  ,I_businessObject.ReIMInsertReqRec_TBL(i).order_no -- order_no
                                                  ,L_type--type
                                                  ,'P'--status_code
                                                  ,'ReIM Insert Complete'--status_desc
                                                  );
   END LOOP;

         O_businessObject := "RIB_ReIMBatchIReturn_REC"(0,L_response);
          

         status := "RIB_SuccessStatus_REC"(0, 'getReIMInsReIMInsertReq service call was successful.');
         successStatus_TBL.EXTEND;
         successStatus_TBL(1) := status;
         O_serviceOperationStatus := "RIB_ServiceOpStatus_REC"(0, successStatus_TBL);
     
    END getReIMInsReIMInsertReq;
/******************************************************************************/



END ReIMInsertServiceProviderImpl;
/




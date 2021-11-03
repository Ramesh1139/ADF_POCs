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
 
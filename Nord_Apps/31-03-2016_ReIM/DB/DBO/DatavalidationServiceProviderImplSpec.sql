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
 
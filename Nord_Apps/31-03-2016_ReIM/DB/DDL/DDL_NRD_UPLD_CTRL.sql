CREATE TABLE SCH_MST.NRD_UPLD_CTRL 
(LOCATION	               NUMBER (10)    NOT NULL,
 DATABASE_INSTANCE	      VARCHAR2(90)   NOT NULL,
 WEBSERVICE_LINK	         VARCHAR2(120)  NOT NULL);
 
CREATE OR REPLACE PUBLIC SYNONYM NRD_UPLD_CTRL 
FOR SCH_MST.NRD_UPLD_CTRL;
/
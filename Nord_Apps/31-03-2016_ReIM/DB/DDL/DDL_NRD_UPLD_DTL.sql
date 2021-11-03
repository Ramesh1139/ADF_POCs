/*
                  DATA DEFINITION LANGUAGE (DDL) FOR:

                               TABLE : NRD_UPLD_DTL 
                              SCHEMA : SCH_MST
                          CREATED BY : INFOSYS
                        CREATED DATE : 29-JAN-2016
*/

/*
  THE FOLLOWING SECTION CONTAINS THE SQL REQUIRED TO CREATE TABLE NRD_UPLD_DTL
*/

DROP TABLE SCH_MST.NRD_UPLD_DTL CASCADE CONSTRAINTS;

CREATE TABLE SCH_MST.NRD_UPLD_DTL
(
  RECORD_TYPE                     VARCHAR2(6 BYTE) NOT NULL,
  USER_ID                         VARCHAR2(30 BYTE),
  EXT_DOC_ID                      VARCHAR2(150 BYTE) NOT NULL,
  VENDOR                          VARCHAR2(10 BYTE) NOT NULL,
  DOC_DATE                        DATE          NOT NULL,
  ORDER_NO                        NUMBER(12),
  ITEM                            VARCHAR2(25 BYTE),
  UNIT_COST                       NUMBER(20,4),
  INVOICE_QTY                     NUMBER(12,4),
  STATUS                          VARCHAR2(6 BYTE),
  TAX_BASIS                       NUMBER(20,4),
  REASON_CODE                     VARCHAR2(20 BYTE),
  COMMENT_TYPE                    VARCHAR2(6 BYTE),
  TEXT                            VARCHAR2(2000 BYTE),
  NON_MERCH_CODE                  VARCHAR2(120 BYTE),
  NON_MERCH_AMT                   NUMBER(20,4)
)
TABLESPACE STAGING_DATA;

CREATE OR REPLACE PUBLIC SYNONYM NRD_UPLD_DTL FOR SCH_MST.NRD_UPLD_DTL;

/
/*
   THIS SECTION INCORPORATES CHANGES THROUGH REVISION: ______________
*/

/* REVISION NUMBER: _____________
   REVISED BY:
   REVISION DATE:
   CHANGE DOCUMENT:
   REASON FOR CHANGE:
*/


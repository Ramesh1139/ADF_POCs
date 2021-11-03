/*
                  DATA DEFINITION LANGUAGE (DDL) FOR:

                               TABLE : NRD_UPLD_HDR_ARCH 
                              SCHEMA : SCH_MST
                          CREATED BY : INFOSYS
                        CREATED DATE : 08-FEB-2016
*/

/*
  THE FOLLOWING SECTION CONTAINS THE SQL REQUIRED TO CREATE TABLE NRD_UPLD_HDR_ARCH
*/

DROP TABLE SCH_MST.NRD_UPLD_HDR_ARCH CASCADE CONSTRAINTS;

CREATE TABLE SCH_MST.NRD_UPLD_HDR_ARCH
(
  RECORD_TYPE                     VARCHAR2(6 BYTE) NOT NULL,
  USER_ID                         VARCHAR2(10 BYTE),
  FILE_NAME                       VARCHAR2(90 BYTE),
  TYPE                            VARCHAR2(6 BYTE),
  STATUS                          VARCHAR2(10 BYTE),
  ORDER_NO                        NUMBER(12),
  LOCATION                        NUMBER(10),
  DOC_DATE                        DATE          NOT NULL,
  VENDOR_TYPE                     VARCHAR2(6 BYTE),
  VENDOR                          VARCHAR2(10 BYTE) NOT NULL,
  EXT_DOC_ID                      VARCHAR2(150 BYTE) NOT NULL,
  TERMS                           VARCHAR2(15 BYTE),
  DUE_DATE                        DATE,
  CURRENCY_CODE                   VARCHAR2(3 BYTE),
  TOTAL_COST                      NUMBER(20,4),
  TOTAL_QTY                       NUMBER(12,4),
  MANUALLY_PAID_IND               VARCHAR2(1 BYTE),
  CUSTOM_DOC_REF_1                VARCHAR2(90 BYTE),
  CUSTOM_DOC_REF_2                VARCHAR2(90 BYTE),
  CUSTOM_DOC_REF_3                VARCHAR2(90 BYTE),
  CUSTOM_DOC_REF_4                VARCHAR2(90 BYTE),
  LAST_UPDATE_ID                  VARCHAR2(30 BYTE) ,
  LAST_DATETIME                   DATE ,
  RESOLUTION_ADJUSTED_TOTAL_COST  NUMBER(20,4),
  RESOLUTION_ADJUSTED_TOTAL_QTY   NUMBER(12,4),
  TOTAL_COST_INC_TAX              NUMBER(20,4),
  SUPPLIER_SITE_ID                VARCHAR2(10 BYTE),
  TAX_BASIS                       NUMBER(20,4),
  COMMENT_TYPE                    VARCHAR2(6 BYTE),
  TEXT                            VARCHAR2(2000 BYTE),
  STATUS_CODE                     VARCHAR2(1 BYTE),
  STATUS_DESCRIPTION              VARCHAR2(2000 BYTE),
  SYSTEM_FLAG                     VARCHAR2(1 BYTE),
  ARCHIVAL_DATE                   DATE          DEFAULT SYSDATE
)
TABLESPACE STAGING_DATA;

CREATE OR REPLACE PUBLIC SYNONYM NRD_UPLD_HDR_ARCH FOR SCH_MST.NRD_UPLD_HDR_ARCH;


/*
   THIS SECTION INCORPORATES CHANGES THROUGH REVISION: ______________
*/

/* REVISION NUMBER: _____________
   REVISED BY:
   REVISION DATE:
   CHANGE DOCUMENT:
   REASON FOR CHANGE:
*/

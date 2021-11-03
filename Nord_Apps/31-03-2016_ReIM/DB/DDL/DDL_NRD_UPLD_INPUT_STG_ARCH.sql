/*
                  DATA DEFINITION LANGUAGE (DDL) FOR:

                               TABLE : NRD_UPLD_INPUT_STG_ARCH 
                              SCHEMA : SCH_MST
                          CREATED BY : INFOSYS
                        CREATED DATE : 28-JAN-2016
*/

/*
  THE FOLLOWING SECTION CONTAINS THE SQL REQUIRED TO CREATE TABLE NRD_UPLD_INPUT_STG_ARCH	
*/
DROP TABLE SCH_MST.NRD_UPLD_INPUT_STG_ARCH CASCADE CONSTRAINTS;

CREATE TABLE SCH_MST.NRD_UPLD_INPUT_STG_ARCH
(
  SEQ_NO                 NUMBER(10) PRIMARY KEY,
  USER_ID                VARCHAR2(30 BYTE)      NOT NULL,
  FILE_NAME              VARCHAR2(90 BYTE)      NOT NULL,
  UPLOAD_ID              NUMBER(10),
  EXT_DOC_ID             VARCHAR2(150 BYTE)      NOT NULL,
  DOC_DATE               DATE                   NOT NULL,
  TYPE                   VARCHAR2(6 BYTE)       NOT NULL,
  VENDOR_TYPE            VARCHAR2(6 BYTE)       NOT NULL,
  VENDOR                 VARCHAR2(10 BYTE)      NOT NULL,
  EXT_DOC_ID_2           VARCHAR2(150 BYTE),
  DOC_DATE_2             DATE,
  VENDOR_TYPE_2          VARCHAR2(6 BYTE),
  VENDOR_2               VARCHAR2(10 BYTE),
  VENDOR_NAME_2          VARCHAR2(240 BYTE),
  SUPPLIER               VARCHAR2(10 BYTE),     --NEW
  SUPPLIER_SITE_ID       VARCHAR2(10 BYTE),
  ORDER_NO               NUMBER(12),
  CURRENCY_CODE          VARCHAR2(3 BYTE)       NOT NULL,
  TERMS                  NUMBER(15),
  LOCATION               NUMBER(10)             NOT NULL,
  CUSTOM_DOC_REF1        VARCHAR2(90 BYTE),     --RETAINED FOR CALCULATION
  CUSTOM_DOC_REF2        VARCHAR2(90 BYTE),     --RETAINED FOR CALCULATION
  CUSTOM_DOC_REF3        VARCHAR2(90 BYTE),     --RETAINED FOR CALCULATION
  CUSTOM_DOC_REF4        VARCHAR2(90 BYTE),     --RETAINED FOR CALCULATION
  SCAN_NO                VARCHAR2(30),          --NEW
  SCAN_DATE              VARCHAR2(30),          --NEW
  ORACLE_REASON_CODE     VARCHAR2(30),          --NEW
  GL_DESC                VARCHAR2(360),         --NEW
  SHIPMENT_REF_NO        VARCHAR2(30),          --NEW
  WIRE_TRANSFER_NO       VARCHAR2(30),          --NEW
  BANK_EXCHANGE_RATE     NUMBER(20,4),          --NEW
  COST_ALLOCATION        VARCHAR2(1),           --NEW (Y/N)
  DOC_CODE               VARCHAR2(30),          --NEW
  DEPT                   NUMBER(20,4),          --NEW
  DATE_FROM              DATE,                  --NEW
  DATE_TO                DATE,                  --NEW
  AUTH_NO                VARCHAR2(30),          --NEW
  NOAP                   VARCHAR2(30),          --NEW
  RTV_NUMBER             NUMBER(20,4),          --NEW
  TOTAL_NONMERCH_COST    NUMBER(20,4)           NOT NULL,   --Renamed From TOTAL_MERCH_COST
  TOTAL_COST_EXCL_TAX    NUMBER(20,4)           NOT NULL,
  TAX_AMOUNT             NUMBER(20,4),
  TOTAL_QTY              NUMBER(12,4)           NOT NULL,
  ALLOWANCE_AMT_HDR      NUMBER(20,4),
  ALLOWANCE_PERC_HDR     NUMBER(12),
  PREPAID_IND            VARCHAR2(1 BYTE)       NOT NULL,   --Renamed From MANUALLY_PAID_IND
  TEXT_I                 VARCHAR2(2000 BYTE),
  TEXT_E                 VARCHAR2(2000 BYTE),
  ITEM                   VARCHAR2(25 BYTE),
  ITEM_DESCRIPTION       VARCHAR2(30 BYTE),
  VPN_NUMBER             VARCHAR2(30 BYTE),
  VPN_DESCRIPTION        VARCHAR2(120 BYTE),
  INVOICE_QTY            NUMBER(12,4),
  UNIT_COST              NUMBER(20,4)           NOT NULL,
  REASON_CODE            VARCHAR2(20 BYTE),
  ALLOWANCE_AMT_DET      NUMBER(12,4),
  ALLOWANCE_PERC_DET     NUMBER(12),
  INVOICE_QTY_DET        NUMBER(12,4),
  DUTY                   NUMBER(20,4),
  UNIT_OF_MEASURE        NUMBER(20,4),
  COST_COMPONENT1        VARCHAR2(30 BYTE),
  COST_AMOUNT1           NUMBER(20,4),
  COST_COMPONENT2        VARCHAR2(30 BYTE),
  COST_AMOUNT2           NUMBER(20,4),
  COST_COMPONENT3        VARCHAR2(30 BYTE),
  COST_AMOUNT3           NUMBER(20,4),
  COST_COMPONENT4        VARCHAR2(30 BYTE),
  COST_AMOUNT4           NUMBER(20,4),
  COST_COMPONENT5        VARCHAR2(30 BYTE),
  COST_AMOUNT5           NUMBER(20,4),
  COST_COMPONENT6        VARCHAR2(30 BYTE),
  COST_AMOUNT6           NUMBER(20,4),
  COST_COMPONENT7        VARCHAR2(30 BYTE),
  COST_AMOUNT7           NUMBER(20,4),
  COST_COMPONENT8        VARCHAR2(30 BYTE),
  COST_AMOUNT8           NUMBER(20,4),
  COST_COMPONENT9        VARCHAR2(30 BYTE),
  COST_AMOUNT9           NUMBER(20,4),
  COST_COMPONENT10       VARCHAR2(30 BYTE),
  COST_AMOUNT10          NUMBER(20,4),
  EXTENDED_COST          NUMBER(20,4),
  ENTRY_IP               NUMBER(20),
  EXT_DOC_ID_IP          VARCHAR2(150 BYTE),
  BROKER_FILE_IP         VARCHAR2(40 BYTE),
  INVOICE_DATE_IP        DATE,
  MANUF_NAME_IP          VARCHAR2(30 BYTE),
  MANUF_COMM_INV_IP      NUMBER(20),
  MANUF_COMM_INV_VAL_IP  NUMBER(20),
  MANUF_COMM_INV_PO_IP   NUMBER(20),
  UPLOAD_DATE            DATE                   DEFAULT SYSDATE,
  BATCH_DATE             DATE,
  STATUS_CODE            VARCHAR2(1 BYTE)       NOT NULL,
  STATUS_DESCRIPTION     VARCHAR2(2000 BYTE),
  BATCH_STATUS           VARCHAR2(10 BYTE)      DEFAULT 'PENDING',
  SPLIT_STATUS           VARCHAR2(10 BYTE)      DEFAULT 'NONE',
  SYSTEM_FLAG            VARCHAR2(1 BYTE),
  ARCHIVAL_DATE          DATE                   DEFAULT SYSDATE
);

CREATE OR REPLACE PUBLIC SYNONYM NRD_UPLD_INPUT_STG_ARCH
FOR SCH_MST.NRD_UPLD_INPUT_STG_ARCH;
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

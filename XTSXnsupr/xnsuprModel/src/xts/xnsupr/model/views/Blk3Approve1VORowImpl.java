package xts.xnsupr.model.views;

import java.math.BigDecimal;

import java.sql.Date;

import java.util.HashMap;

import oracle.jbo.server.ViewAttributeDefImpl;

import xts.formadf.model.framework.GenericProgrammaticViewRowImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Wed Apr 19 15:41:54 IST 2017
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class Blk3Approve1VORowImpl extends GenericProgrammaticViewRowImpl {
    /**
     * createRowData - for custom java data source support.
     * Overridden to initialize the dataProvier for newly created row.
     * Used for updateable View Objects.
     */
    public Object createRowData(HashMap attrNameValueMap) {
        Object value = super.createRowData(attrNameValueMap);
        return value;
    }

    /**
     * convertToSourceType - for custom java data source support.
     * Overridden to provide custom implementation for conversions of a value
     * from attribute java type to datasource column/field type.
     * Not required in most cases.
     */
    public Object convertToSourceType(ViewAttributeDefImpl attrDef, String sourceType, Object val) {
        Object value = super.convertToSourceType(attrDef, sourceType, val);
        return value;
    }

    /**
     * convertToAttributeType - for custom java data source support.
     * Overridden to provide custom implementation for conversions of a value
     *  from datasource/column field type to attribute java type.
     * Not required in most cases.
     */
    public Object convertToAttributeType(ViewAttributeDefImpl attrDef, Class javaTypeClass, Object val) {
        Object value = super.convertToAttributeType(attrDef, javaTypeClass, val);
        return value;
    }

    /**
     * AttributesEnum: generated enum for identifying attributes and accessors. DO NOT MODIFY.
     */
    protected enum AttributesEnum {
        Key1,
        MppfcSup,
        MppfcShp,
        MppfcMan,
        MppfcMat,
        MppfcCon,
        MppfcSbmid,
        MppfcFulnama1,
        MppfcFulnamLocala1,
        MppfcConsubrol,
        MppfcConsubrolDesc,
        MppfcAdr1,
        MppfcAdr2,
        MppfcAdr3,
        MppfcAdr4,
        MppfcAdr1Local,
        MppfcAdr2Local,
        MppfcAdr3Local,
        MppfcAdr4Local,
        MppfcCtycod,
        CtycodDes,
        MppfcLoccod,
        LocDes,
        MppfcRmk,
        MppfcSbmby,
        MppfcSbmbyDivision,
        MppfcSbmbyFulnam,
        MppfcSbmdata1,
        Remark,
        ButReject,
        ButNext,
        ButExit;
        private static AttributesEnum[] vals = null;
        private static final int firstIndex = 0;

        protected int index() {
            return AttributesEnum.firstIndex() + ordinal();
        }

        protected static final int firstIndex() {
            return firstIndex;
        }

        protected static int count() {
            return AttributesEnum.firstIndex() + AttributesEnum.staticValues().length;
        }

        protected static final AttributesEnum[] staticValues() {
            if (vals == null) {
                vals = AttributesEnum.values();
            }
            return vals;
        }
    }
    public static final int KEY1 = AttributesEnum.Key1.index();
    public static final int MPPFCSUP = AttributesEnum.MppfcSup.index();
    public static final int MPPFCSHP = AttributesEnum.MppfcShp.index();
    public static final int MPPFCMAN = AttributesEnum.MppfcMan.index();
    public static final int MPPFCMAT = AttributesEnum.MppfcMat.index();
    public static final int MPPFCCON = AttributesEnum.MppfcCon.index();
    public static final int MPPFCSBMID = AttributesEnum.MppfcSbmid.index();
    public static final int MPPFCFULNAMA1 = AttributesEnum.MppfcFulnama1.index();
    public static final int MPPFCFULNAMLOCALA1 = AttributesEnum.MppfcFulnamLocala1.index();
    public static final int MPPFCCONSUBROL = AttributesEnum.MppfcConsubrol.index();
    public static final int MPPFCCONSUBROLDESC = AttributesEnum.MppfcConsubrolDesc.index();
    public static final int MPPFCADR1 = AttributesEnum.MppfcAdr1.index();
    public static final int MPPFCADR2 = AttributesEnum.MppfcAdr2.index();
    public static final int MPPFCADR3 = AttributesEnum.MppfcAdr3.index();
    public static final int MPPFCADR4 = AttributesEnum.MppfcAdr4.index();
    public static final int MPPFCADR1LOCAL = AttributesEnum.MppfcAdr1Local.index();
    public static final int MPPFCADR2LOCAL = AttributesEnum.MppfcAdr2Local.index();
    public static final int MPPFCADR3LOCAL = AttributesEnum.MppfcAdr3Local.index();
    public static final int MPPFCADR4LOCAL = AttributesEnum.MppfcAdr4Local.index();
    public static final int MPPFCCTYCOD = AttributesEnum.MppfcCtycod.index();
    public static final int CTYCODDES = AttributesEnum.CtycodDes.index();
    public static final int MPPFCLOCCOD = AttributesEnum.MppfcLoccod.index();
    public static final int LOCDES = AttributesEnum.LocDes.index();
    public static final int MPPFCRMK = AttributesEnum.MppfcRmk.index();
    public static final int MPPFCSBMBY = AttributesEnum.MppfcSbmby.index();
    public static final int MPPFCSBMBYDIVISION = AttributesEnum.MppfcSbmbyDivision.index();
    public static final int MPPFCSBMBYFULNAM = AttributesEnum.MppfcSbmbyFulnam.index();
    public static final int MPPFCSBMDATA1 = AttributesEnum.MppfcSbmdata1.index();
    public static final int REMARK = AttributesEnum.Remark.index();
    public static final int BUTREJECT = AttributesEnum.ButReject.index();
    public static final int BUTNEXT = AttributesEnum.ButNext.index();
    public static final int BUTEXIT = AttributesEnum.ButExit.index();

    /**
     * This is the default constructor (do not remove).
     */
    public Blk3Approve1VORowImpl() {
    }
    
    // Start : Generated Code
    public String getMppfcFulnama1(){
       return (String)getAttributeInternal(MPPFCFULNAMA1);
    }
    public void setMppfcFulnama1(String value){
       setAttributeInternal(MPPFCFULNAMA1, value);
    }
    public void setMppfcFulnam_noVal(String value){
       setAttributeInternal(MPPFCFULNAMA1, value);
    }
    public String getMppfcSbmid(){
       return (String)getAttributeInternal(MPPFCSBMID);
    }
    public void setMppfcSbmid(String value){
       setAttributeInternal(MPPFCSBMID, value);
    }
    public void setMppfcSbmid_noVal(String value){
       setAttributeInternal(MPPFCSBMID, value);
    }
    public String getMppfcCon(){
       return (String)getAttributeInternal(MPPFCCON);
    }
    public void setMppfcCon(String value){
       setAttributeInternal(MPPFCCON, value);
    }
    public void setMppfcCon_noVal(String value){
       setAttributeInternal(MPPFCCON, value);
    }
    public String getMppfcMat(){
       return (String)getAttributeInternal(MPPFCMAT);
    }
    public void setMppfcMat(String value){
       setAttributeInternal(MPPFCMAT, value);
    }
    public void setMppfcMat_noVal(String value){
       setAttributeInternal(MPPFCMAT, value);
    }
    public String getMppfcConsubrolDesc(){
       return (String)getAttributeInternal(MPPFCCONSUBROLDESC);
    }
    public void setMppfcConsubrolDesc(String value){
       setAttributeInternal(MPPFCCONSUBROLDESC, value);
    }
    public void setMppfcConsubrolDesc_noVal(String value){
       setAttributeInternal(MPPFCCONSUBROLDESC, value);
    }
    public String getMppfcConsubrol(){
       return (String)getAttributeInternal(MPPFCCONSUBROL);
    }
    public void setMppfcConsubrol(String value){
       setAttributeInternal(MPPFCCONSUBROL, value);
    }
    public void setMppfcConsubrol_noVal(String value){
       setAttributeInternal(MPPFCCONSUBROL, value);
    }
    public String getMppfcSup(){
       return (String)getAttributeInternal(MPPFCSUP);
    }
    public void setMppfcSup(String value){
       setAttributeInternal(MPPFCSUP, value);
    }
    public void setMppfcSup_noVal(String value){
       setAttributeInternal(MPPFCSUP, value);
    }
    public String getMppfcFulnamLocala1(){
       return (String)getAttributeInternal(MPPFCFULNAMLOCALA1);
    }
    public void setMppfcFulnamLocala1(String value){
       setAttributeInternal(MPPFCFULNAMLOCALA1, value);
    }
    public void setMppfcFulnamLocal_noVal(String value){
       setAttributeInternal(MPPFCFULNAMLOCALA1, value);
    }
    public String getMppfcMan(){
       return (String)getAttributeInternal(MPPFCMAN);
    }
    public void setMppfcMan(String value){
       setAttributeInternal(MPPFCMAN, value);
    }
    public void setMppfcMan_noVal(String value){
       setAttributeInternal(MPPFCMAN, value);
    }
    public String getMppfcShp(){
       return (String)getAttributeInternal(MPPFCSHP);
    }
    public void setMppfcShp(String value){
       setAttributeInternal(MPPFCSHP, value);
    }
    public void setMppfcShp_noVal(String value){
       setAttributeInternal(MPPFCSHP, value);
    }
    public String getMppfcAdr3(){
       return (String)getAttributeInternal(MPPFCADR3);
    }
    public void setMppfcAdr3(String value){
       setAttributeInternal(MPPFCADR3, value);
    }
    public void setMppfcAdr3_noVal(String value){
       setAttributeInternal(MPPFCADR3, value);
    }
    public String getMppfcAdr4(){
       return (String)getAttributeInternal(MPPFCADR4);
    }
    public void setMppfcAdr4(String value){
       setAttributeInternal(MPPFCADR4, value);
    }
    public void setMppfcAdr4_noVal(String value){
       setAttributeInternal(MPPFCADR4, value);
    }
    public String getMppfcAdr1(){
       return (String)getAttributeInternal(MPPFCADR1);
    }
    public void setMppfcAdr1(String value){
       setAttributeInternal(MPPFCADR1, value);
    }
    public void setMppfcAdr1_noVal(String value){
       setAttributeInternal(MPPFCADR1, value);
    }
    public String getMppfcAdr2(){
       return (String)getAttributeInternal(MPPFCADR2);
    }
    public void setMppfcAdr2(String value){
       setAttributeInternal(MPPFCADR2, value);
    }
    public void setMppfcAdr2_noVal(String value){
       setAttributeInternal(MPPFCADR2, value);
    }
    public String getMppfcAdr3Local(){
       return (String)getAttributeInternal(MPPFCADR3LOCAL);
    }
    public void setMppfcAdr3Local(String value){
       setAttributeInternal(MPPFCADR3LOCAL, value);
    }
    public void setMppfcAdr3Local_noVal(String value){
       setAttributeInternal(MPPFCADR3LOCAL, value);
    }
    public String getMppfcAdr4Local(){
       return (String)getAttributeInternal(MPPFCADR4LOCAL);
    }
    public void setMppfcAdr4Local(String value){
       setAttributeInternal(MPPFCADR4LOCAL, value);
    }
    public void setMppfcAdr4Local_noVal(String value){
       setAttributeInternal(MPPFCADR4LOCAL, value);
    }
    public String getMppfcAdr1Local(){
       return (String)getAttributeInternal(MPPFCADR1LOCAL);
    }
    public void setMppfcAdr1Local(String value){
       setAttributeInternal(MPPFCADR1LOCAL, value);
    }
    public void setMppfcAdr1Local_noVal(String value){
       setAttributeInternal(MPPFCADR1LOCAL, value);
    }
    public String getMppfcAdr2Local(){
       return (String)getAttributeInternal(MPPFCADR2LOCAL);
    }
    public void setMppfcAdr2Local(String value){
       setAttributeInternal(MPPFCADR2LOCAL, value);
    }
    public void setMppfcAdr2Local_noVal(String value){
       setAttributeInternal(MPPFCADR2LOCAL, value);
    }
    public String getMppfcLoccod(){
       return (String)getAttributeInternal(MPPFCLOCCOD);
    }
    public void setMppfcLoccod(String value){
       setAttributeInternal(MPPFCLOCCOD, value);
    }
    public void setMppfcLoccod_noVal(String value){
       setAttributeInternal(MPPFCLOCCOD, value);
    }
    public String getLocDes(){
       return (String)getAttributeInternal(LOCDES);
    }
    public void setLocDes(String value){
       setAttributeInternal(LOCDES, value);
    }
    public void setLocDes_noVal(String value){
       setAttributeInternal(LOCDES, value);
    }
    public String getMppfcCtycod(){
       return (String)getAttributeInternal(MPPFCCTYCOD);
    }
    public void setMppfcCtycod(String value){
       setAttributeInternal(MPPFCCTYCOD, value);
    }
    public void setMppfcCtycod_noVal(String value){
       setAttributeInternal(MPPFCCTYCOD, value);
    }
    public String getCtycodDes(){
       return (String)getAttributeInternal(CTYCODDES);
    }
    public void setCtycodDes(String value){
       setAttributeInternal(CTYCODDES, value);
    }
    public void setCtycodDes_noVal(String value){
       setAttributeInternal(CTYCODDES, value);
    }
    public String getMppfcSbmbyDivision(){
       return (String)getAttributeInternal(MPPFCSBMBYDIVISION);
    }
    public void setMppfcSbmbyDivision(String value){
       setAttributeInternal(MPPFCSBMBYDIVISION, value);
    }
    public void setMppfcSbmbyDivision_noVal(String value){
       setAttributeInternal(MPPFCSBMBYDIVISION, value);
    }
    public String getMppfcSbmbyFulnam(){
       return (String)getAttributeInternal(MPPFCSBMBYFULNAM);
    }
    public void setMppfcSbmbyFulnam(String value){
       setAttributeInternal(MPPFCSBMBYFULNAM, value);
    }
    public void setMppfcSbmbyFulnam_noVal(String value){
       setAttributeInternal(MPPFCSBMBYFULNAM, value);
    }
    public String getMppfcRmk(){
       return (String)getAttributeInternal(MPPFCRMK);
    }
    public void setMppfcRmk(String value){
       setAttributeInternal(MPPFCRMK, value);
    }
    public void setMppfcRmk_noVal(String value){
       setAttributeInternal(MPPFCRMK, value);
    }
    public String getMppfcSbmby(){
       return (String)getAttributeInternal(MPPFCSBMBY);
    }
    public void setMppfcSbmby(String value){
       setAttributeInternal(MPPFCSBMBY, value);
    }
    public void setMppfcSbmby_noVal(String value){
       setAttributeInternal(MPPFCSBMBY, value);
    }
    public String getButNext(){
       return (String)getAttributeInternal(BUTNEXT);
    }
    public void setButNext(String value){
       setAttributeInternal(BUTNEXT, value);
    }
    public void setButNext_noVal(String value){
       setAttributeInternal(BUTNEXT, value);
    }
    public String getButReject(){
       return (String)getAttributeInternal(BUTREJECT);
    }
    public void setButReject(String value){
       setAttributeInternal(BUTREJECT, value);
    }
    public void setButReject_noVal(String value){
       setAttributeInternal(BUTREJECT, value);
    }
    public String getRemark(){
       return (String)getAttributeInternal(REMARK);
    }
    public void setRemark(String value){
       setAttributeInternal(REMARK, value);
    }
    public void setRemark_noVal(String value){
       setAttributeInternal(REMARK, value);
    }
    public Date getMppfcSbmdata1(){
       return (Date)getAttributeInternal(MPPFCSBMDATA1);
    }
    public void setMppfcSbmdata1(Date value){
       setAttributeInternal(MPPFCSBMDATA1, value);
    }
    public void setMppfcSbmdat_noVal(Date value){
       setAttributeInternal(MPPFCSBMDATA1, value);
    }
    public void setMppfcSbmdat_noVal(String value){
       Date value1 = String2Date(value);
       setAttributeInternal(MPPFCSBMDATA1, value1);
    }
    public String getButExit(){
       return (String)getAttributeInternal(BUTEXIT);
    }
    public void setButExit(String value){
       setAttributeInternal(BUTEXIT, value);
    }
    public void setButExit_noVal(String value){
       setAttributeInternal(BUTEXIT, value);
    }

    
    // End : Generated Code
}

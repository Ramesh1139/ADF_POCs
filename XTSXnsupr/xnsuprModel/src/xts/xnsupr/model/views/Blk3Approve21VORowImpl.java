package xts.xnsupr.model.views;

import java.math.BigDecimal;

import java.util.HashMap;

import oracle.jbo.server.ViewAttributeDefImpl;

import xts.formadf.model.framework.GenericProgrammaticViewRowImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Tue Apr 18 16:52:53 IST 2017
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class Blk3Approve21VORowImpl extends GenericProgrammaticViewRowImpl {
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
        NewFtycod,
        NewFulnama21,
        NewAdr1,
        NewAdr2,
        NewAdr3,
        NewAdr4;
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
    public static final int NEWFTYCOD = AttributesEnum.NewFtycod.index();
    public static final int NEWFULNAMA21 = AttributesEnum.NewFulnama21.index();
    public static final int NEWADR1 = AttributesEnum.NewAdr1.index();
    public static final int NEWADR2 = AttributesEnum.NewAdr2.index();
    public static final int NEWADR3 = AttributesEnum.NewAdr3.index();
    public static final int NEWADR4 = AttributesEnum.NewAdr4.index();

    /**
     * This is the default constructor (do not remove).
     */
    public Blk3Approve21VORowImpl() {
    }
    
    // Start : Generated Code
    
    public String getNewFtycod(){
       return (String)getAttributeInternal(NEWFTYCOD);
    }
    public void setNewFtycod(String value){
       setAttributeInternal(NEWFTYCOD, value);
    }
    public void setNewFtycod_noVal(String value){
       setAttributeInternal(NEWFTYCOD, value);
    }
    public String getNewAdr1(){
       return (String)getAttributeInternal(NEWADR1);
    }
    public void setNewAdr1(String value){
       setAttributeInternal(NEWADR1, value);
    }
    public void setNewAdr1_noVal(String value){
       setAttributeInternal(NEWADR1, value);
    }
    public String getNewFulnama21(){
       return (String)getAttributeInternal(NEWFULNAMA21);
    }
    public void setNewFulnama21(String value){
       setAttributeInternal(NEWFULNAMA21, value);
    }
    public void setNewFulnam_noVal(String value){
       setAttributeInternal(NEWFULNAMA21, value);
    }
    public String getNewAdr3(){
       return (String)getAttributeInternal(NEWADR3);
    }
    public void setNewAdr3(String value){
       setAttributeInternal(NEWADR3, value);
    }
    public void setNewAdr3_noVal(String value){
       setAttributeInternal(NEWADR3, value);
    }
    public String getNewAdr2(){
       return (String)getAttributeInternal(NEWADR2);
    }
    public void setNewAdr2(String value){
       setAttributeInternal(NEWADR2, value);
    }
    public void setNewAdr2_noVal(String value){
       setAttributeInternal(NEWADR2, value);
    }
    public String getNewAdr4(){
       return (String)getAttributeInternal(NEWADR4);
    }
    public void setNewAdr4(String value){
       setAttributeInternal(NEWADR4, value);
    }
    public void setNewAdr4_noVal(String value){
       setAttributeInternal(NEWADR4, value);
    }
    

    
    // End : Generated Code
}

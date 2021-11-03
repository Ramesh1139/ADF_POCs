package xts.xnsupr.model.views;

import java.math.BigDecimal;

import java.util.HashMap;

import oracle.jbo.server.ViewAttributeDefImpl;

import xts.formadf.model.framework.GenericProgrammaticViewRowImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Thu Apr 20 16:26:33 IST 2017
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class Blk3Insert21VORowImpl extends GenericProgrammaticViewRowImpl {
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
        NEWADR1,
        NEWADR2,
        NEWADR3,
        NEWADR4,
        NEWFULNAM,
        key1;
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


    public static final int NEWADR1 = AttributesEnum.NEWADR1.index();
    public static final int NEWADR2 = AttributesEnum.NEWADR2.index();
    public static final int NEWADR3 = AttributesEnum.NEWADR3.index();
    public static final int NEWADR4 = AttributesEnum.NEWADR4.index();
    public static final int NEWFULNAM = AttributesEnum.NEWFULNAM.index();
    public static final int KEY1 = AttributesEnum.key1.index();

    /**
     * This is the default constructor (do not remove).
     */
    public Blk3Insert21VORowImpl() {
    }
    
    // Start : Generated Code
    
    public String getNewAdr2(){
       return (String)getAttributeInternal(NEWADR2);
    }
    public void setNewAdr2(String value){
       setAttributeInternal(NEWADR2, value);
    }
    public void setNewAdr2_noVal(String value){
       setAttributeInternal(NEWADR2, value);
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
    public String getNewAdr4(){
       return (String)getAttributeInternal(NEWADR4);
    }
    public void setNewAdr4(String value){
       setAttributeInternal(NEWADR4, value);
    }
    public void setNewAdr4_noVal(String value){
       setAttributeInternal(NEWADR4, value);
    }
    public String getNewFulnam(){
       return (String)getAttributeInternal(NEWFULNAM);
    }
    public void setNewFulnam(String value){
       setAttributeInternal(NEWFULNAM, value);
    }
    public void setNewFulnam_noVal(String value){
       setAttributeInternal(NEWFULNAM, value);
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

    // End : Generated Code
    

}


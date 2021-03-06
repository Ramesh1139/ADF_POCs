package xts.xnsupr.model.views;

import oracle.jbo.JboException;

import xts.formadf.model.framework.GenericVORowImpl;

import xts.xnsupr.model.entities.BlkConsubrolImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Tue Apr 18 13:52:35 IST 2017
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class BlkConsubrolVORowImpl extends GenericVORowImpl {


    public static final int ENTITY_BLKCONSUBROL = 0;

    /**
     * AttributesEnum: generated enum for identifying attributes and accessors. DO NOT MODIFY.
     */
    protected enum AttributesEnum {
        MptmColumn,
        MptmTypcod,
        MptmDes,
        MptmVal1,
        MptmChkbox,
        isConSuballowed,
        ConsubCheckBox;
        private static AttributesEnum[] vals = null;
        ;
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


    public static final int MPTMCOLUMN = AttributesEnum.MptmColumn.index();
    public static final int MPTMTYPCOD = AttributesEnum.MptmTypcod.index();
    public static final int MPTMDES = AttributesEnum.MptmDes.index();
    public static final int MPTMVAL1 = AttributesEnum.MptmVal1.index();
    public static final int MPTMCHKBOX = AttributesEnum.MptmChkbox.index();
    public static final int ISCONSUBALLOWED = AttributesEnum.isConSuballowed.index();
    public static final int CONSUBCHECKBOX = AttributesEnum.ConsubCheckBox.index();

    /**
     * This is the default constructor (do not remove).
     */
    public BlkConsubrolVORowImpl()
    {
        
    }

    /**
     * Gets BlkConsubrol entity object.
     * @return the BlkConsubrol
     */
    public BlkConsubrolImpl getBlkConsubrol() {
        return (BlkConsubrolImpl) getEntity(ENTITY_BLKCONSUBROL);
    }

    /**
     * Gets the attribute value for MPTM_COLUMN using the alias name MptmColumn.
     * @return the MPTM_COLUMN
     */
    public String getMptmColumn() {
        return (String) getAttributeInternal(MPTMCOLUMN);
    }

    /**
     * Sets <code>value</code> as attribute value for MPTM_COLUMN using the alias name MptmColumn.
     * @param value value to set the MPTM_COLUMN
     */
    public void setMptmColumn(String value) {
        setAttributeInternal(MPTMCOLUMN, value);
    }

    /**
     * Gets the attribute value for MPTM_TYPCOD using the alias name MptmTypcod.
     * @return the MPTM_TYPCOD
     */
    public String getMptmTypcod() {
        return (String) getAttributeInternal(MPTMTYPCOD);
    }

    /**
     * Sets <code>value</code> as attribute value for MPTM_TYPCOD using the alias name MptmTypcod.
     * @param value value to set the MPTM_TYPCOD
     */
    public void setMptmTypcod(String value) {
        setAttributeInternal(MPTMTYPCOD, value);
    }

    /**
     * Gets the attribute value for MPTM_DES using the alias name MptmDes.
     * @return the MPTM_DES
     */
    public String getMptmDes() {
        return (String) getAttributeInternal(MPTMDES);
    }

    /**
     * Sets <code>value</code> as attribute value for MPTM_DES using the alias name MptmDes.
     * @param value value to set the MPTM_DES
     */
    public void setMptmDes(String value) {
        setAttributeInternal(MPTMDES, value);
    }

    /**
     * Gets the attribute value for MPTM_VAL1 using the alias name MptmVal1.
     * @return the MPTM_VAL1
     */
    public String getMptmVal1() {
        return (String) getAttributeInternal(MPTMVAL1);
    }

    /**
     * Sets <code>value</code> as attribute value for MPTM_VAL1 using the alias name MptmVal1.
     * @param value value to set the MPTM_VAL1
     */
    public void setMptmVal1(String value) {
        setAttributeInternal(MPTMVAL1, value);
    }

    /**
     * Gets the attribute value for MPTM_CHKBOX using the alias name MptmChkbox.
     * @return the MPTM_CHKBOX
     */
    public String getMptmChkbox() {
        return (String) getAttributeInternal(MPTMCHKBOX);
    }

    /**
     * Sets <code>value</code> as attribute value for MPTM_CHKBOX using the alias name MptmChkbox.
     * @param value value to set the MPTM_CHKBOX
     */
  /*  public void setMptmChkbox(String value) {
        setAttributeInternal(MPTMCHKBOX, value);
    } */

    // Start : Generated Code

   
    public void setMptmChkbox(String value){
       String oldValue = getMptmChkbox();
       try{
           setAttributeInternal(MPTMCHKBOX, value);
         //  BlkConsubrolVOFieldEvents.MptmChkbox_cc();
       } catch(JboException e){
           setAttributeInternal(MPTMCHKBOX,oldValue);
           throw(e);
       }
    }

    /**
     * Gets the attribute value for the calculated attribute isConSuballowed.
     * @return the isConSuballowed
     */
    public Boolean getisConSuballowed() {
        return (Boolean) getAttributeInternal(ISCONSUBALLOWED);
    }


    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute isConSuballowed.
     * @param value value to set the  isConSuballowed
     */
    public void setisConSuballowed(Boolean value) {
        populateAttribute(ISCONSUBALLOWED, value);
    }

    /**
     * Gets the attribute value for the calculated attribute ConsubCheckBox.
     * @return the ConsubCheckBox
     */
    public Boolean getConsubCheckBox() {
        return (Boolean) getAttributeInternal(CONSUBCHECKBOX);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute ConsubCheckBox.
     * @param value value to set the  ConsubCheckBox
     */
    public void setConsubCheckBox(Boolean value) {
        populateAttribute(CONSUBCHECKBOX, value);
    }

    public void setMptmChkbox_noVal(String value){
       populateAttribute(MPTMCHKBOX, value);
    }
   
    public void setMptmDes_noVal(String value){
       setAttributeInternal(MPTMDES, value);
    }
   
    public void setMptmVal1_noVal(String value){
       setAttributeInternal(MPTMVAL1, value);
    }

    
    // End : Generated Code
   
}


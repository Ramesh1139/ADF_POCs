package xts.salinv.model.views;

import oracle.jbo.RowSet;

import xts.common.model.framework.GenericViewRowImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Fri Sep 23 16:51:24 CST 2016
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class LoadTplInputVORowImpl extends GenericViewRowImpl {
    /**
     * AttributesEnum: generated enum for identifying attributes and accessors. DO NOT MODIFY.
     */
    protected enum AttributesEnum {
        Tplcod,
        Tpldes,
        Loc,
        Divcod,
        TmpAddamtTplLovVO1;
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


    public static final int TPLCOD = AttributesEnum.Tplcod.index();
    public static final int TPLDES = AttributesEnum.Tpldes.index();
    public static final int LOC = AttributesEnum.Loc.index();
    public static final int DIVCOD = AttributesEnum.Divcod.index();
    public static final int TMPADDAMTTPLLOVVO1 = AttributesEnum.TmpAddamtTplLovVO1.index();

    /**
     * This is the default constructor (do not remove).
     */
    public LoadTplInputVORowImpl() {
    }

    /**
     * Gets the attribute value for the calculated attribute Tplcod.
     * @return the Tplcod
     */
    public String getTplcod() {
        return (String) getAttributeInternal(TPLCOD);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute Tplcod.
     * @param value value to set the  Tplcod
     */
    public void setTplcod(String value) {
        setAttributeInternal(TPLCOD, value);
    }

    /**
     * Gets the attribute value for the calculated attribute Tpldes.
     * @return the Tpldes
     */
    public String getTpldes() {
        return (String) getAttributeInternal(TPLDES);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute Tpldes.
     * @param value value to set the  Tpldes
     */
    public void setTpldes(String value) {
        setAttributeInternal(TPLDES, value);
    }

    /**
     * Gets the attribute value for the calculated attribute Loc.
     * @return the Loc
     */
    public String getLoc() {
        return (String) getAttributeInternal(LOC);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute Loc.
     * @param value value to set the  Loc
     */
    public void setLoc(String value) {
        setAttributeInternal(LOC, value);
    }

    /**
     * Gets the attribute value for the calculated attribute Divcod.
     * @return the Divcod
     */
    public String getDivcod() {
        return (String) getAttributeInternal(DIVCOD);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute Divcod.
     * @param value value to set the  Divcod
     */
    public void setDivcod(String value) {
        setAttributeInternal(DIVCOD, value);
    }

    /**
     * Gets the view accessor <code>RowSet</code> TmpAddamtTplLovVO1.
     */
    public RowSet getTmpAddamtTplLovVO1() {
        return (RowSet) getAttributeInternal(TMPADDAMTTPLLOVVO1);
    }
}


package xts.xnsupr.model.views.lovs;

import xts.formadf.model.framework.GenericVORowImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Fri Apr 21 12:48:08 IST 2017
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class OpdvDivcodLovVORowImpl extends GenericVORowImpl {
    /**
     * AttributesEnum: generated enum for identifying attributes and accessors. DO NOT MODIFY.
     */
    protected enum AttributesEnum {
        OpdvDivcod,
        OpdvNam;
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
    public static final int OPDVDIVCOD = AttributesEnum.OpdvDivcod.index();
    public static final int OPDVNAM = AttributesEnum.OpdvNam.index();

    /**
     * This is the default constructor (do not remove).
     */
    public OpdvDivcodLovVORowImpl() {
    }

    /**
     * Gets the attribute value for the calculated attribute OpdvDivcod.
     * @return the OpdvDivcod
     */
    public String getOpdvDivcod() {
        return (String) getAttributeInternal(OPDVDIVCOD);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute OpdvDivcod.
     * @param value value to set the  OpdvDivcod
     */
    public void setOpdvDivcod(String value) {
        setAttributeInternal(OPDVDIVCOD, value);
    }

    /**
     * Gets the attribute value for the calculated attribute OpdvNam.
     * @return the OpdvNam
     */
    public String getOpdvNam() {
        return (String) getAttributeInternal(OPDVNAM);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute OpdvNam.
     * @param value value to set the  OpdvNam
     */
    public void setOpdvNam(String value) {
        setAttributeInternal(OPDVNAM, value);
    }
}


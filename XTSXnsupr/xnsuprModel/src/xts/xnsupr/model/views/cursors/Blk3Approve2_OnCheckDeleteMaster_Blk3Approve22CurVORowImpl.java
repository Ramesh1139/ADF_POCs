package xts.xnsupr.model.views.cursors;

import java.math.BigDecimal;

import xts.formadf.model.framework.GenericVORowImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Fri Apr 21 16:44:28 IST 2017
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class Blk3Approve2_OnCheckDeleteMaster_Blk3Approve22CurVORowImpl extends GenericVORowImpl {
    /**
     * AttributesEnum: generated enum for identifying attributes and accessors. DO NOT MODIFY.
     */
    protected enum AttributesEnum {
        J1;
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


    public static final int J1 = AttributesEnum.J1.index();

    /**
     * This is the default constructor (do not remove).
     */
    public Blk3Approve2_OnCheckDeleteMaster_Blk3Approve22CurVORowImpl() {
    }

    /**
     * Gets the attribute value for the calculated attribute J1.
     * @return the J1
     */
    public BigDecimal getJ1() {
        return (BigDecimal) getAttributeInternal(J1);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute J1.
     * @param value value to set the  J1
     */
    public void setJ1(BigDecimal value) {
        setAttributeInternal(J1, value);
    }
}


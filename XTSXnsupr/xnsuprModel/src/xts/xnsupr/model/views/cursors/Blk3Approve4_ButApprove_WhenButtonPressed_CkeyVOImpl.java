package xts.xnsupr.model.views.cursors;

import xts.common.model.framework.GenericViewObjectImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Fri Apr 21 17:06:17 IST 2017
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class Blk3Approve4_ButApprove_WhenButtonPressed_CkeyVOImpl extends GenericViewObjectImpl {
    /**
     * This is the default constructor (do not remove).
     */
    public Blk3Approve4_ButApprove_WhenButtonPressed_CkeyVOImpl() {
    }

    /**
     * Returns the bind variable value for I_MPPFC_RUNNUM.
     * @return bind variable value for I_MPPFC_RUNNUM
     */
    public String getI_MPPFC_RUNNUM() {
        return (String) getNamedWhereClauseParam("I_MPPFC_RUNNUM");
    }

    /**
     * Sets <code>value</code> for bind variable I_MPPFC_RUNNUM.
     * @param value value to bind as I_MPPFC_RUNNUM
     */
    public void setI_MPPFC_RUNNUM(String value) {
        setNamedWhereClauseParam("I_MPPFC_RUNNUM", value);
    }
}

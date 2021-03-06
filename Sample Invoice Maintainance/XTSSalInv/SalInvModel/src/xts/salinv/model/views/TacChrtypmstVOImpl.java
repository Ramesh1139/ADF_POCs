package xts.salinv.model.views;

import xts.common.model.framework.GenericViewObjectImpl;

import xts.salinv.model.views.common.TacChrtypmstVO;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Wed Oct 05 14:17:16 CST 2016
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class TacChrtypmstVOImpl extends GenericViewObjectImpl implements TacChrtypmstVO {
    /**
     * This is the default constructor (do not remove).
     */
    public TacChrtypmstVOImpl() {
    }

    public void setVarExe(String chrTyp) {
        this.setchrtypVar(chrTyp);
        this.executeQuery();
    }

    /**
     * Returns the bind variable value for chrtypVar.
     * @return bind variable value for chrtypVar
     */
    public String getchrtypVar() {
        return (String) getNamedWhereClauseParam("chrtypVar");
    }

    /**
     * Sets <code>value</code> for bind variable chrtypVar.
     * @param value value to bind as chrtypVar
     */
    public void setchrtypVar(String value) {
        setNamedWhereClauseParam("chrtypVar", value);
    }
}


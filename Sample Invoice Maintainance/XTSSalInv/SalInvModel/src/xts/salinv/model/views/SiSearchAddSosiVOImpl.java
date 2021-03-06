package xts.salinv.model.views;

import java.sql.ResultSet;

import oracle.adf.share.logging.ADFLogger;

import oracle.jbo.Row;
import oracle.jbo.RowSetIterator;
import oracle.jbo.ViewCriteriaItem;
import oracle.jbo.domain.Date;
import oracle.jbo.server.QueryCollection;
import oracle.jbo.server.ViewRowImpl;

import xts.common.model.framework.GenericViewObjectImpl;

import xts.salinv.model.services.XTSSalInvAMImpl;
import xts.salinv.model.views.common.SiSearchAddSosiVO;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Fri Oct 07 09:04:48 CST 2016
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class SiSearchAddSosiVOImpl extends GenericViewObjectImpl implements SiSearchAddSosiVO {

    private static final ADFLogger LOGGER = ADFLogger.createADFLogger(SiSearchAddSosiVOImpl.class);

    /**
     * This is the default constructor (do not remove).
     */
    public SiSearchAddSosiVOImpl() {
    }

    @Override
    public String getCriteriaItemClause(ViewCriteriaItem viewCriteriaItem) {
        Object bindVariableValue = viewCriteriaItem.getValueFromVariableManager(this.getVariableManager());

        String currentViewCriteria = viewCriteriaItem.getViewCriteria().getName();
        LOGGER.fine("*********CurrentViewCriteria:" + currentViewCriteria);
        LOGGER.fine("*********ConjunctionString: " + viewCriteriaItem.getConjunctionString());
        LOGGER.fine("*********Name: " + viewCriteriaItem.getName());
        LOGGER.fine("*********GetColumnNameForQuery: " + viewCriteriaItem.getColumnNameForQuery());
        LOGGER.fine("*********Operator: " + viewCriteriaItem.getOperator());
        LOGGER.fine("*********Bind Variable Value: " + bindVariableValue);

        if (bindVariableValue == null && viewCriteriaItem.getOperator().equals("DP_SHP_PLN.F_CHECK_SHP_PLN")) {
            LOGGER.fine("Replacement .. DP_SHP_PLN.F_CHECK_SHP_PLN(SOSI_DIVCOD,null, SOSI_RUNNUM, SOSI_SHISEQ,SOSI_ITMSEQ) = 'Y'");
            return " DP_SHP_PLN.F_CHECK_SHP_PLN(SOSI_DIVCOD,null, SOSI_RUNNUM, SOSI_SHISEQ,SOSI_ITMSEQ) = 'Y' ";
        }
        return super.getCriteriaItemClause(viewCriteriaItem);
    }

    @Override
    public void executeQuery() {
        LOGGER.info("*** SiSearchAddSosiVOImpl running executeQuery()");
        if (this.getshpnmNoVar() != null) {
            LOGGER.info("Search with Shipment Plan No. : " + this.getshpnmNoVar() + "\nRemoved the following criteria: " + this.getsopoPoNumFromVar() + ";" +
                        this.getsomsDocnbrVar() + ";" + this.getsosiSchdatFromVar() + ";" + this.getsosiSchdatToVar());
            this.setsopoPoNumFromVar(null);
            this.setsomsDocnbrVar(null);
            this.setsosiSchdatFromVar(null);
            this.setsosiSchdatToVar(null);
        }

        super.executeQuery();
    }

    @Override
    public ViewRowImpl createInstanceFromResultSet(QueryCollection queryCollection, ResultSet resultSet) {
        ViewRowImpl vr = super.createInstanceFromResultSet(queryCollection, resultSet);

        if (vr instanceof SiSearchAddSosiVORowImpl) {
            SiSearchAddSosiVORowImpl row = (SiSearchAddSosiVORowImpl) vr;
            row.setisSelected(true);
        }
        return vr;
    }

    public String defaultCuscod() {
        XTSSalInvAMImpl am = (XTSSalInvAMImpl) this.getApplicationModule();
        TciMstVORowImpl tciMst = (TciMstVORowImpl) am.getTciMstVO().getCurrentRow();
        if (tciMst != null)
            return tciMst.getCimsCuscod();
        return null;
    }

    public String defaultDptcod() {
        XTSSalInvAMImpl am = (XTSSalInvAMImpl) this.getApplicationModule();
        TciMstVORowImpl tciMst = (TciMstVORowImpl) am.getTciMstVO().getCurrentRow();
        if (tciMst != null)
            return tciMst.getCimsDptcod();
        return null;
    }

    public String defaultCurcod() {
        XTSSalInvAMImpl am = (XTSSalInvAMImpl) this.getApplicationModule();
        TciMstVORowImpl tciMst = (TciMstVORowImpl) am.getTciMstVO().getCurrentRow();
        if (tciMst != null)
            return tciMst.getCimsCurcod();
        return null;
    }

    public Long defaultCimsRunnum() {
        XTSSalInvAMImpl am = (XTSSalInvAMImpl) this.getApplicationModule();
        TciMstVORowImpl tciMst = (TciMstVORowImpl) am.getTciMstVO().getCurrentRow();
        if (tciMst != null)
            return tciMst.getCimsRunnum();
        return null;
    }

    public Date defaultSchDatFrom() {
        Date tmp = new Date(Date.getCurrentDate());
        Date date = new oracle.jbo.domain.Date(tmp.addMonths(-3));
        Object obj = date.dateValue();
        return Date.toDate(obj.toString());
    }

    public Date defaultSchDatTo() {
        Date tmp = new Date(Date.getCurrentDate());
        Date date = new oracle.jbo.domain.Date(tmp.addMonths(6));
        Object obj = date.dateValue();
        return Date.toDate(obj.toString());
    }

    public void selectAllRows() {
        if (this.getViewDef().getAttributeIndexOf("isSelected") != -1) {
            RowSetIterator rsi = this.createRowSetIterator(null);
            while (rsi.hasNext()) {
                Row row = rsi.next();
                row.setAttribute("isSelected", true);
            }
            rsi.closeRowSetIterator();
        }
    }

    public void unselectAllRows() {
        if (this.getViewDef().getAttributeIndexOf("isSelected") != -1) {
            RowSetIterator rsi = this.createRowSetIterator(null);
            while (rsi.hasNext()) {
                Row row = rsi.next();
                row.setAttribute("isSelected", false);
            }
            rsi.closeRowSetIterator();
        }
    }

    public void initAddShi() {
        XTSSalInvAMImpl am = (XTSSalInvAMImpl) this.getApplicationModule();
        this.executeEmptyRowSet();
        am.getSiSearchAddSosiExtraVO().executeEmptyRowSet();

        Row row = am.getSiSearchAddSosiExtraVO().createRow();
        am.getSiSearchAddSosiExtraVO().insertRow(row);
        am.getSiSearchAddSosiExtraVO().setCurrentRow(row);
        
        //reset VC
        this.setshpnmNoVar(null);
        this.setsopoPoNumFromVar(null);
        this.setsomsDocnbrVar(null);    
        this.setsosiSchdatFromVar(defaultSchDatFrom());
        this.setsosiSchdatToVar(defaultSchDatTo());
    }

    /**
     * Returns the variable value for cimsCuscodVar.
     * @return variable value for cimsCuscodVar
     */
    public String getcimsCuscodVar() {
        return (String) ensureVariableManager().getVariableValue("cimsCuscodVar");
    }

    /**
     * Sets <code>value</code> for variable cimsCuscodVar.
     * @param value value to bind as cimsCuscodVar
     */
    public void setcimsCuscodVar(String value) {
        ensureVariableManager().setVariableValue("cimsCuscodVar", value);
    }

    /**
     * Returns the variable value for somsDocnbrVar.
     * @return variable value for somsDocnbrVar
     */
    public String getsomsDocnbrVar() {
        return (String) ensureVariableManager().getVariableValue("somsDocnbrVar");
    }

    /**
     * Sets <code>value</code> for variable somsDocnbrVar.
     * @param value value to bind as somsDocnbrVar
     */
    public void setsomsDocnbrVar(String value) {
        ensureVariableManager().setVariableValue("somsDocnbrVar", value);
    }

    /**
     * Returns the variable value for cimsDptcodVar.
     * @return variable value for cimsDptcodVar
     */
    public String getcimsDptcodVar() {
        return (String) ensureVariableManager().getVariableValue("cimsDptcodVar");
    }

    /**
     * Sets <code>value</code> for variable cimsDptcodVar.
     * @param value value to bind as cimsDptcodVar
     */
    public void setcimsDptcodVar(String value) {
        ensureVariableManager().setVariableValue("cimsDptcodVar", value);
    }

    /**
     * Returns the variable value for sopoPoNumFromVar.
     * @return variable value for sopoPoNumFromVar
     */
    public String getsopoPoNumFromVar() {
        return (String) ensureVariableManager().getVariableValue("sopoPoNumFromVar");
    }

    /**
     * Sets <code>value</code> for variable sopoPoNumFromVar.
     * @param value value to bind as sopoPoNumFromVar
     */
    public void setsopoPoNumFromVar(String value) {
        ensureVariableManager().setVariableValue("sopoPoNumFromVar", value);
    }

    /**
     * Returns the variable value for cimsCurcodVar.
     * @return variable value for cimsCurcodVar
     */
    public String getcimsCurcodVar() {
        return (String) ensureVariableManager().getVariableValue("cimsCurcodVar");
    }

    /**
     * Sets <code>value</code> for variable cimsCurcodVar.
     * @param value value to bind as cimsCurcodVar
     */
    public void setcimsCurcodVar(String value) {
        ensureVariableManager().setVariableValue("cimsCurcodVar", value);
    }

    /**
     * Returns the variable value for cimsRunnumVar.
     * @return variable value for cimsRunnumVar
     */
    public Long getcimsRunnumVar() {
        return (Long) ensureVariableManager().getVariableValue("cimsRunnumVar");
    }

    /**
     * Sets <code>value</code> for variable cimsRunnumVar.
     * @param value value to bind as cimsRunnumVar
     */
    public void setcimsRunnumVar(Long value) {
        ensureVariableManager().setVariableValue("cimsRunnumVar", value);
    }

    /**
     * Returns the variable value for sosiSchdatFromVar.
     * @return variable value for sosiSchdatFromVar
     */
    public Date getsosiSchdatFromVar() {
        return (Date) ensureVariableManager().getVariableValue("sosiSchdatFromVar");
    }

    /**
     * Sets <code>value</code> for variable sosiSchdatFromVar.
     * @param value value to bind as sosiSchdatFromVar
     */
    public void setsosiSchdatFromVar(Date value) {
        ensureVariableManager().setVariableValue("sosiSchdatFromVar", value);
    }

    /**
     * Returns the variable value for sosiSchdatToVar.
     * @return variable value for sosiSchdatToVar
     */
    public Date getsosiSchdatToVar() {
        return (Date) ensureVariableManager().getVariableValue("sosiSchdatToVar");
    }

    /**
     * Sets <code>value</code> for variable sosiSchdatToVar.
     * @param value value to bind as sosiSchdatToVar
     */
    public void setsosiSchdatToVar(Date value) {
        ensureVariableManager().setVariableValue("sosiSchdatToVar", value);
    }

    /**
     * Returns the variable value for shpnmNoVar.
     * @return variable value for shpnmNoVar
     */
    public String getshpnmNoVar() {
        return (String) ensureVariableManager().getVariableValue("shpnmNoVar");
    }

    /**
     * Sets <code>value</code> for variable shpnmNoVar.
     * @param value value to bind as shpnmNoVar
     */
    public void setshpnmNoVar(String value) {
        ensureVariableManager().setVariableValue("shpnmNoVar", value);
    }
}


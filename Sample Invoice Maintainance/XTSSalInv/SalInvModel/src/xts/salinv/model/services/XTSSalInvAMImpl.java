package xts.salinv.model.services;


import java.sql.Clob;
import java.sql.SQLException;
import java.sql.Types;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Date;

import oracle.adf.share.logging.ADFLogger;

import oracle.jbo.server.ViewLinkImpl;

import xts.common.model.framework.GenericApplicationModuleImpl;
import xts.common.model.framework.db.PLSQLCall;

import xts.salinv.model.services.common.XTSSalInvAM;
import xts.salinv.model.views.CopyRemarkVOImpl;
import xts.salinv.model.views.DefaultValuesVOImpl;
import xts.salinv.model.views.DummyVOImpl;
import xts.salinv.model.views.LoadTemplateVOImpl;
import xts.salinv.model.views.LoadTplInputVOImpl;
import xts.salinv.model.views.SaveRemarkVOImpl;
import xts.salinv.model.views.SiFromSoExtraVOImpl;
import xts.salinv.model.views.SiFromSoVOImpl;
import xts.salinv.model.views.SiSearchAddSosiExtraVOImpl;
import xts.salinv.model.views.SiSearchAddSosiVOImpl;
import xts.salinv.model.views.TacChrtypmstVOImpl;
import xts.salinv.model.views.TciActshiamtVOImpl;
import xts.salinv.model.views.TciActshpitmVOImpl;
import xts.salinv.model.views.TciAddAmtVOImpl;
import xts.salinv.model.views.TciCusfldHeaderVOImpl;
import xts.salinv.model.views.TciCusfldShpitmVOImpl;
import xts.salinv.model.views.TciDesShpitmVOImpl;
import xts.salinv.model.views.TciDesVOImpl;
import xts.salinv.model.views.TciMstVOImpl;
import xts.salinv.model.views.TciMstVORowImpl;
import xts.salinv.model.views.TciPrtVOImpl;
import xts.salinv.model.views.TsoMstVOImpl;


// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Thu Sep 01 14:56:32 CST 2016
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class XTSSalInvAMImpl extends GenericApplicationModuleImpl implements XTSSalInvAM {

    private static final ADFLogger LOGGER = ADFLogger.createADFLogger(XTSSalInvAMImpl.class);

    /**
     * This is the default constructor (do not remove).
     */
    public XTSSalInvAMImpl() {
    }

    public void postChanges() {
        this.getTransaction().postChanges();
    }

    public String checkDpt(String dptcod, String divcod, String userid) {
        PLSQLCall dc = new PLSQLCall("? := CHECK_DPT", this.getDBTransaction());
        dc.addRet("ret", Types.VARCHAR);
        dc.addIn(dptcod);
        dc.addIn(divcod);
        dc.addIn(userid);
        dc.execute();
        Object ret = dc.getObj("ret");
        return (String) ret;
    }

    public String checkDptSetcod(String divcod, String dptcod, String cuscod, String setcod) {
        PLSQLCall dc = new PLSQLCall("? := DP_CASE.CHECK_DPT_SETCOD", this.getDBTransaction());
        dc.addRet("ret", Types.VARCHAR);
        dc.addIn(divcod);
        dc.addIn(dptcod);
        dc.addIn(cuscod);
        dc.addIn(setcod);
        dc.execute();
        Object ret = dc.getObj("ret");
        LOGGER.info(divcod + "-" + dptcod + "-" + cuscod + " DP" + setcod + ": " + ret.toString());
        return (String) ret;
    }

    public String checkSetcod(String divcod, String cuscod, String setcod) {
        PLSQLCall dc = new PLSQLCall("? := DP_CASE.CHECK_SETCOD", this.getDBTransaction());
        dc.addRet("ret", Types.VARCHAR);
        dc.addIn(divcod);
        dc.addIn(cuscod);
        dc.addIn(setcod);
        dc.execute();
        Object ret = dc.getObj("ret");
        LOGGER.info(divcod + "-" + cuscod + " OP" + setcod + ": " + ret.toString());
        return (String) ret;
    }

    public String checkApSetting(String divcod, String dptcod, String cuscod, String setcod) {
        String userid = this.getUserName();
        PLSQLCall dc = new PLSQLCall("? := DP_UTL.CHK_AP_SETTING", this.getDBTransaction());
        dc.addRet("ret", Types.VARCHAR);
        dc.addIn(userid);
        dc.addIn(divcod);
        dc.addIn(dptcod);
        dc.addIn(cuscod);
        dc.addIn(setcod);
        dc.execute();
        Object ret = dc.getObj("ret");
        LOGGER.info(userid + "-" + divcod + "-" + dptcod + "-" + cuscod + " AP" + setcod + ": " + ret.toString());
        return (String) ret;
    }
    
    public String getParaValue(String paraName) {
        PLSQLCall dc = new PLSQLCall("? := DP_UTL.F_GET_PARA_VALUE", this.getDBTransaction());
        dc.addRet("ret", Types.VARCHAR);
        dc.addIn(paraName);
        dc.execute();
        Object ret = dc.getObj("ret");
        return (String) ret;
    }

    public String getCimsCuscod() {
        TciMstVORowImpl mstRow = (TciMstVORowImpl) this.getTciMstVO().getCurrentRow();
        return mstRow.getCimsCuscod();
    }

    public String getCtycod(String userId) {
        PLSQLCall dc = new PLSQLCall("? := DP_SI.GET_CTYCOD", this.getDBTransaction());
        dc.addRet("ret", Types.VARCHAR);
        dc.addIn(userId);
        dc.execute();
        Object ret = dc.getObj("ret");
        return (String) ret;
    }

    public String getCtySysPara(String param, String ctycod) {
        PLSQLCall dc = new PLSQLCall("? := DP_UTL.chk_syspara", this.getDBTransaction());
        dc.addRet("ret", Types.VARCHAR);
        dc.addIn(param);
        dc.addIn(ctycod);
        dc.execute();
        Object ret = dc.getObj("ret");
        return (String) ret;
    }

    public String encrypt(String str) {
        PLSQLCall dc = new PLSQLCall("? := DP_UTILITIES.FL_ENCRYPT", this.getDBTransaction());
        dc.addRet("ret", Types.VARCHAR);
        dc.addIn(str);
        dc.execute();
        Object ret = dc.getObj("ret");
        return (String) ret;
    }

    public String getUsernamePwdEncryptedString(String username, String pwd) {
        String usernameE = encrypt(username);
        String pwdE = encrypt(pwd);
        return "?var1=" + usernameE + "&var2=" + pwdE;
    }

    public String getCallAttachParam(String username, String pwd, String div, Long runnum, String access, String db) {
        return "?var1=" + encrypt(username) + "&var2=" + encrypt(pwd) + "&div=" + div + "&var3=" + runnum + "&var4=" + encrypt(access) + "&db=" + encrypt(db);
    }

    public String getEditMode() {
        String editMode;
        try {
            editMode = (String) this.getUserDataEntry("custom_ACCESS_MODE");
        } catch (NullPointerException e) {
            return null;
        }
        return editMode;
    }

    public String formatDate(String date) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yy");
        SimpleDateFormat resformatter = new SimpleDateFormat("dd-MMM-yyyy");
        Date d;
        d = formatter.parse(date);
        return resformatter.format(d);
    }

    public oracle.jbo.domain.Date string2Date(String date) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
        Date d = formatter.parse(date);
        java.sql.Date sqlDate = new java.sql.Date(d.getTime());
        oracle.jbo.domain.Date jboDate = new oracle.jbo.domain.Date(sqlDate);
        return jboDate;
    }

    public oracle.jbo.domain.Date getJboDateFromSqlDate(java.sql.Date sqlDate) {
        oracle.jbo.domain.Date jboDate = null;
        if (sqlDate != null) {
            jboDate = new oracle.jbo.domain.Date(sqlDate);
        }
        return jboDate;
    }

    public String clobToString(Clob data) {
        if (data != null) {
            long len;
            String result;
            try {
                len = data.length();
                result = data.getSubString(1, (int) len);
                return result;
            } catch (SQLException e) {
                LOGGER.severe("clobToString - SQLException when getting the length of the CLOB");
                return null;
            }
        }
        return null;
    }

    /**
     * Container's getter for InsertTciMstVO.
     * @return InsertTciMstVO
     */
    public TciMstVOImpl getInsertTciMstVO() {
        return (TciMstVOImpl) findViewObject("InsertTciMstVO");
    }

    /**
     * Container's getter for TciMstVO1.
     * @return TciMstVO1
     */
    public TciMstVOImpl getTciMstVO() {
        return (TciMstVOImpl) findViewObject("TciMstVO");
    }

    /**
     * Container's getter for TciPrtVO1.
     * @return TciPrtVO1
     */
    public TciPrtVOImpl getTciPrtVO() {
        return (TciPrtVOImpl) findViewObject("TciPrtVO");
    }

    /**
     * Container's getter for TciMstVOToTciPrtVOLink1.
     * @return TciMstVOToTciPrtVOLink1
     */
    public ViewLinkImpl getTciMstVOToTciPrtVOLink() {
        return (ViewLinkImpl) findViewLink("TciMstVOToTciPrtVOLink");
    }

    /**
     * Container's getter for TciDesVO1.
     * @return TciDesVO1
     */
    public TciDesVOImpl getTciDesVO() {
        return (TciDesVOImpl) findViewObject("TciDesVO");
    }

    /**
     * Container's getter for TciMstVOToTciDesVOLink1.
     * @return TciMstVOToTciDesVOLink1
     */
    public ViewLinkImpl getTciMstVOToTciDesVOLink() {
        return (ViewLinkImpl) findViewLink("TciMstVOToTciDesVOLink");
    }

    /**
     * Container's getter for TciAddAmtVO1.
     * @return TciAddAmtVO1
     */
    public TciAddAmtVOImpl getTciAddAmtVO() {
        return (TciAddAmtVOImpl) findViewObject("TciAddAmtVO");
    }

    /**
     * Container's getter for TciMstVOToTciAddAmtVOLink1.
     * @return TciMstVOToTciAddAmtVOLink1
     */
    public ViewLinkImpl getTciMstVOToTciAddAmtVOLink() {
        return (ViewLinkImpl) findViewLink("TciMstVOToTciAddAmtVOLink");
    }

    /**
     * Container's getter for TciActshpitmVO1.
     * @return TciActshpitmVO1
     */
    public TciActshpitmVOImpl getTciActshpitmVO() {
        return (TciActshpitmVOImpl) findViewObject("TciActshpitmVO");
    }

    /**
     * Container's getter for TciMstVOToTciActshpitmVOLink1.
     * @return TciMstVOToTciActshpitmVOLink1
     */
    public ViewLinkImpl getTciMstVOToTciActshpitmVOLink() {
        return (ViewLinkImpl) findViewLink("TciMstVOToTciActshpitmVOLink");
    }

    /**
     * Container's getter for TciActshiamtVO1.
     * @return TciActshiamtVO1
     */
    public TciActshiamtVOImpl getTciActshiamtVO() {
        return (TciActshiamtVOImpl) findViewObject("TciActshiamtVO");
    }

    /**
     * Container's getter for TciActshpitmVOToTciActshiamtVOLink1.
     * @return TciActshpitmVOToTciActshiamtVOLink1
     */
    public ViewLinkImpl getTciActshpitmVOToTciActshiamtVOLink() {
        return (ViewLinkImpl) findViewLink("TciActshpitmVOToTciActshiamtVOLink");
    }

    /**
     * Container's getter for TciCusfldHeaderVO1.
     * @return TciCusfldHeaderVO1
     */
    public TciCusfldHeaderVOImpl getTciCusfldHeaderVO() {
        return (TciCusfldHeaderVOImpl) findViewObject("TciCusfldHeaderVO");
    }


    /**
     * Container's getter for SaveRemarkVO1.
     * @return SaveRemarkVO1
     */
    public SaveRemarkVOImpl getSaveRemarkVO() {
        return (SaveRemarkVOImpl) findViewObject("SaveRemarkVO");
    }

    /**
     * Container's getter for CopyRemarkVO1.
     * @return CopyRemarkVO1
     */
    public CopyRemarkVOImpl getCopyRemarkVO() {
        return (CopyRemarkVOImpl) findViewObject("CopyRemarkVO");
    }

    /**
     * Container's getter for LoadTemplateVO1.
     * @return LoadTemplateVO1
     */
    public LoadTemplateVOImpl getLoadTemplateVO() {
        return (LoadTemplateVOImpl) findViewObject("LoadTemplateVO");
    }

    /**
     * Container's getter for LoadTplInputVO1.
     * @return LoadTplInputVO1
     */
    public LoadTplInputVOImpl getLoadTplInputVO() {
        return (LoadTplInputVOImpl) findViewObject("LoadTplInputVO");
    }

    /**
     * Container's getter for TciCusfldShpitmVO1.
     * @return TciCusfldShpitmVO1
     */
    public TciCusfldShpitmVOImpl getTciCusfldShpitmVO() {
        return (TciCusfldShpitmVOImpl) findViewObject("TciCusfldShpitmVO");
    }


    /**
     * Container's getter for TciDesShpitmVO3.
     * @return TciDesShpitmVO3
     */
    public TciDesShpitmVOImpl getTciDesShpitmVO() {
        return (TciDesShpitmVOImpl) findViewObject("TciDesShpitmVO");
    }

    /**
     * Container's getter for TciActshpitmVOToTciDesVOLink3.
     * @return TciActshpitmVOToTciDesVOLink3
     */
    public ViewLinkImpl getTciActshpitmVOToTciDesVOLink() {
        return (ViewLinkImpl) findViewLink("TciActshpitmVOToTciDesVOLink");
    }

    /**
     * Container's getter for TacChrtypmstVO1.
     * @return TacChrtypmstVO1
     */
    public TacChrtypmstVOImpl getTacChrtypmstVO() {
        return (TacChrtypmstVOImpl) findViewObject("TacChrtypmstVO");
    }

    /**
     * Container's getter for SiSearchAddSosiVO1.
     * @return SiSearchAddSosiVO1
     */
    public SiSearchAddSosiVOImpl getSiSearchAddSosiVO() {
        return (SiSearchAddSosiVOImpl) findViewObject("SiSearchAddSosiVO");
    }

    /**
     * Container's getter for SiFromSoVO1.
     * @return SiFromSoVO1
     */
    public SiFromSoVOImpl getSiFromSoVO() {
        return (SiFromSoVOImpl) findViewObject("SiFromSoVO");
    }

    /**
     * Container's getter for DummyVO1.
     * @return DummyVO1
     */
    public DummyVOImpl getDummyVO() {
        return (DummyVOImpl) findViewObject("DummyVO");
    }

    /**
     * Container's getter for TsoMstVO1.
     * @return TsoMstVO1
     */
    public TsoMstVOImpl getTsoMstVO() {
        return (TsoMstVOImpl) findViewObject("TsoMstVO");
    }

    /**
     * Container's getter for SiFromSoExtraVO1.
     * @return SiFromSoExtraVO1
     */
    public SiFromSoExtraVOImpl getSiFromSoExtraVO() {
        return (SiFromSoExtraVOImpl) findViewObject("SiFromSoExtraVO");
    }

    /**
     * Container's getter for DefaultValuesVO1.
     * @return DefaultValuesVO1
     */
    public DefaultValuesVOImpl getDefaultValuesVO() {
        return (DefaultValuesVOImpl) findViewObject("DefaultValuesVO");
    }

    /**
     * Container's getter for SiSearchAddSosiExtraVO1.
     * @return SiSearchAddSosiExtraVO1
     */
    public SiSearchAddSosiExtraVOImpl getSiSearchAddSosiExtraVO() {
        return (SiSearchAddSosiExtraVOImpl) findViewObject("SiSearchAddSosiExtraVO");
    }
}


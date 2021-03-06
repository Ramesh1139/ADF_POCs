package xts.salinv.model.views;

import java.sql.Clob;
import java.sql.Types;

import oracle.adf.share.logging.ADFLogger;

import oracle.jbo.JboException;
import oracle.jbo.Row;

import xts.common.model.framework.GenericViewObjectImpl;
import xts.common.model.framework.db.PLSQLCall;

import xts.salinv.model.services.XTSSalInvAMImpl;
import xts.salinv.model.views.common.CopyRemarkVO;

// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Thu Sep 22 11:20:06 CST 2016
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class CopyRemarkVOImpl extends GenericViewObjectImpl implements CopyRemarkVO {

    private static final ADFLogger LOGGER = ADFLogger.createADFLogger(CopyRemarkVOImpl.class);

    /**
     * This is the default constructor (do not remove).
     */
    public CopyRemarkVOImpl() {
    }

    public void setCopyAttr(String loc, String rmkFld) {
        //get divcod
        XTSSalInvAMImpl am = (XTSSalInvAMImpl) this.getApplicationModule();
        TciMstVORowImpl mstRow = (TciMstVORowImpl) am.getTciMstVO().getCurrentRow();
        String divcod = mstRow.getCimsDivcod();
        LOGGER.info("Set up Copy Parameters: " + loc + "-" + rmkFld + "-" + divcod);

        boolean needInsertRow = false;

        CopyRemarkVORowImpl row = (CopyRemarkVORowImpl) this.first();
        if (row == null) {
            row = (CopyRemarkVORowImpl) this.createRow();
            needInsertRow = true;
        }
        row.setLoc(loc);
        row.setFldnam(rmkFld);
        row.setDivcod(divcod);

        if (needInsertRow)
            this.insertRow(row);
    }

    public void resetRow() {
        CopyRemarkVORowImpl row = (CopyRemarkVORowImpl) this.getCurrentRow();
        if (row != null) {
            String loc = row.getLoc();
            String fldnam = row.getFldnam();
            row.remove();
            this.setCopyAttr(loc, fldnam);
        }
    }

    public void validateRow() {
        XTSSalInvAMImpl am = (XTSSalInvAMImpl) this.getApplicationModule();
        String errmsg;
        CopyRemarkVORowImpl row = (CopyRemarkVORowImpl) this.getCurrentRow();
        if (row != null) {
            if (row.getLoc().equalsIgnoreCase("HDR")) {
                if (row.getCimsRunnum() != null) {
                    if (row.getSiNo() == null) {
                        errmsg = am.plGetMsgmst("5329");
                        throw new JboException(errmsg);
                    }
                } else {
                    if (row.getMprmRmkcod() == null) {
                        errmsg = am.plGetMsgmst("1158");
                        throw new JboException(errmsg);
                    }
                }
            } else {
                if (row.getCimsRunnum() != null) {
                    if (row.getSiNo() == null) {
                        errmsg = am.plGetMsgmst("5329");
                        throw new JboException(errmsg);
                    } else if (row.getShiseq() == null) {
                        errmsg = am.plGetMsgmst("5330");
                        throw new JboException(errmsg);
                    } else if (row.getItmseq() == null) {
                        errmsg = am.plGetMsgmst("5331");
                        throw new JboException(errmsg);
                    }
                } else {
                    if (row.getMprmRmkcod() == null) {
                        errmsg = am.plGetMsgmst("1158");
                        throw new JboException(errmsg);
                    }
                }
            }
        }
    }

    public void processCopy(String loc) {
        XTSSalInvAMImpl am = (XTSSalInvAMImpl) this.getApplicationModule();
        CopyRemarkVORowImpl row = (CopyRemarkVORowImpl) this.getCurrentRow();

        if (row != null) {
            if (row.getLoc() == null)
                row.setLoc(loc);
            this.validateRow();

            PLSQLCall dc = new PLSQLCall("? := DP_SI.F_GET_RMK", this.getDBTransaction());
            dc.addRet("ret", Types.CLOB);
            dc.addIn(row.getLoc());
            dc.addIn(row.getFldnam());
            dc.addIn(row.getCimsRunnum());
            dc.addIn(row.getSomsRunnum());
            dc.addIn(row.getShiseq());
            dc.addIn(row.getItmseq());
            dc.addIn(row.getDivcod());
            dc.addIn(row.getMprmRmkcod());
            dc.execute();
            Clob ret = (Clob) dc.getObj("ret");
            String rmk = am.clobToString(ret);

            String originalRmk;
            if (loc.equals("HDR")) {
                Row mstRow = am.getTciDesVO().getCurrentRow();
                originalRmk = (String) mstRow.getAttribute(row.getFldnam());
                if (rmk != null) {
                    if (originalRmk != null)
                        mstRow.setAttribute(row.getFldnam(), originalRmk + rmk);
                    else
                        mstRow.setAttribute(row.getFldnam(), rmk);
                }
            } else {
                Row shiRow = am.getTciDesShpitmVO().getCurrentRow();
                originalRmk = (String) shiRow.getAttribute(row.getFldnam());
                if (rmk != null) {
                    if (originalRmk != null)
                        shiRow.setAttribute(row.getFldnam(), originalRmk + rmk);
                    else
                        shiRow.setAttribute(row.getFldnam(), rmk);
                }
            }

            row.remove();
        }
    }

    public void removeCopiedRemark() {
        this.executeQuery();
    }
}


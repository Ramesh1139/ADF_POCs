package xts.salinv.model.views;

import oracle.jbo.JboException;

import xts.common.model.framework.GenericEntityImpl;
import xts.common.model.framework.GenericViewObjectImpl;
import xts.common.model.framework.db.PLSQLCall;

import xts.salinv.model.services.XTSSalInvAMImpl;
import xts.salinv.model.views.common.SaveRemarkVO;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Wed Sep 21 17:22:48 CST 2016
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class SaveRemarkVOImpl extends GenericViewObjectImpl implements SaveRemarkVO {
    /**
     * This is the default constructor (do not remove).
     */
    public SaveRemarkVOImpl() {
    }

    // loc: HDR / SHPITM
    public void setRemarkDesc(String loc, String rmkFld) {
        XTSSalInvAMImpl am = (XTSSalInvAMImpl) this.getApplicationModule();
        if (loc.equalsIgnoreCase("HDR")) {
            TciDesVORowImpl row = (TciDesVORowImpl) am.getTciDesVO().getCurrentRow();
            String remark = (String) row.getAttribute(rmkFld);

            SaveRemarkVORowImpl curRow = (SaveRemarkVORowImpl) this.createRow();
            if (remark != null)
                curRow.setRemarkDesc(remark.toString());
            this.insertRow(curRow);
        } else if (loc.equalsIgnoreCase("SHPITM")) {
            TciDesShpitmVORowImpl row = (TciDesShpitmVORowImpl) am.getTciDesShpitmVO().getCurrentRow();
            String remark = (String) row.getAttribute(rmkFld);
            SaveRemarkVORowImpl curRow = (SaveRemarkVORowImpl) this.createRow();
            if (remark != null)
                curRow.setRemarkDesc(remark.toString());
            this.insertRow(curRow);
        }
    }

    public void saveRemark() {
        SaveRemarkVORowImpl row = (SaveRemarkVORowImpl) this.getCurrentRow();

        //get divcod
        XTSSalInvAMImpl am = (XTSSalInvAMImpl) this.getApplicationModule();
        TciMstVORowImpl mstRow = (TciMstVORowImpl) am.getTciMstVO().getCurrentRow();
        String divcod = mstRow.getCimsDivcod();

        if (row != null) {
            try {
                PLSQLCall dc = new PLSQLCall("DP_SI.PL_SAVE_REMARK", this.getDBTransaction());
                dc.addIn(divcod);
                dc.addIn(row.getRemarkCode());
                dc.addIn(row.getRemarkDesc());
                dc.execute();

            } catch (JboException e) {
                ((GenericEntityImpl) mstRow.getEntityForAttribute("CimsRunnum")).convertException(e);
            }
        }
    }
}

package xts.salinv.model.views;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.Calendar;

import oracle.jbo.JboException;
import oracle.jbo.Row;
import oracle.jbo.domain.Date;

import xts.common.model.framework.GenericEntityImpl;
import xts.common.model.framework.GenericViewObjectImpl;

import xts.common.model.framework.db.PLSQLCall;

import xts.salinv.model.services.XTSSalInvAMImpl;
import xts.salinv.model.views.common.SiFromSoExtraVO;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Tue Oct 18 11:32:17 CST 2016
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class SiFromSoExtraVOImpl extends GenericViewObjectImpl implements SiFromSoExtraVO {
    /**
     * This is the default constructor (do not remove).
     */
    public SiFromSoExtraVOImpl() {
    }

    @Override
    public void insertRow(Row row) {
        row.setAttribute("CimsYear", defaultYear());
        row.setAttribute("CimsInvdat", defaultDate());
        row.setAttribute("DefAddamtTpl", true);
        super.insertRow(row);
    }
    
    private Date defaultDate() {
        Date tmp = new Date(Date.getCurrentDate());
        Object obj = tmp.dateValue();

        return Date.toDate(obj.toString());
    }

    public String defaultYear() {
        Calendar cal = Calendar.getInstance();
        DateFormat df = new SimpleDateFormat("yy");
        return df.format(cal.getTime());
    }

    public void validateSiFromSoExtra() {
        XTSSalInvAMImpl am = (XTSSalInvAMImpl) this.getApplicationModule();
        SiFromSoExtraVORowImpl row = (SiFromSoExtraVORowImpl) this.getCurrentRow();
        String errmsg;

        if (row.getCimsInvdat() == null) {
            errmsg = am.plGetMsgmst("383");
            throw new JboException(errmsg);
        } else if (row.getCimsShpdat() == null) {
            errmsg = am.plGetMsgmst("922");
            throw new JboException(errmsg);
        } else if (row.getCimsYear() == null) {
            throw new JboException("Invoice year must be inputted");
        }

        if (row.getRefSo() == null) {
            errmsg = am.plGetMsgmst("5573");
            throw new JboException(errmsg);
        } else {
            try {
                PLSQLCall dc = new PLSQLCall("DP_SI.PL_CHECK_SO", this.getDBTransaction());
                dc.addIn(row.getRefSo());
                dc.execute();
            } catch (JboException e) {
                ((GenericEntityImpl) ((TciMstVORowImpl) am.getTciMstVO().getCurrentRow()).getEntityForAttribute("CimsRunnum")).convertException(e);
            }
        }

        //MORE VALIDATIONS TO ADD!
    }

}

package xts.salinv.model.views;

import oracle.jbo.Row;
import oracle.jbo.RowSetIterator;

import xts.common.model.framework.GenericViewObjectImpl;

import xts.salinv.model.services.XTSSalInvAMImpl;
import xts.salinv.model.views.common.TciAddAmtVO;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Wed Sep 21 12:02:30 CST 2016
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class TciAddAmtVOImpl extends GenericViewObjectImpl implements TciAddAmtVO {
    /**
     * This is the default constructor (do not remove).
     */
    public TciAddAmtVOImpl() {
    }

    @Override
    public void insertRow(Row row) {
        row.setAttribute("CiaaInvseq", 0);
        row.setAttribute("CiaaFnlcus", "N");
        row.setAttribute("CiaaSeq", checkMax() + 1);
        super.insertRow(row);
    }

    public int checkMax() {
        int max = 0;
        RowSetIterator rsi = this.createRowSetIterator(null);
        while (rsi.hasNext()) {
            TciAddAmtVORowImpl row = (TciAddAmtVORowImpl) rsi.next();
            if (row.getCiaaSeq() != null) {
                if (row.getCiaaSeq() > max) {
                    max = row.getCiaaSeq();
                }
            }
        }
        rsi.closeRowSetIterator();
        return max;
    }

    public void removeRow() {
        if (this.getCurrentRow() != null) {
            Row row = this.getCurrentRow();
            row.remove();

            XTSSalInvAMImpl am = (XTSSalInvAMImpl) this.getApplicationModule();
            am.getTransaction().postChanges();
        }
    }
}

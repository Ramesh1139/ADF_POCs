package xts.xnsupr.model.events; 
import xts.xnsupr.model.helpers.XnsuprAmImplHelpers;
import xts.xnsupr.model.services.xnsuprAMImpl;

public class Blk3Approve21VOEvents {
    public Blk3Approve21VOEvents(xnsuprAMImpl am) {
        super();
this.am = am;
    }
    xnsuprAMImpl am;
    public xnsuprAMImpl getAm() {
        return am;
    }
    public void setAm(xnsuprAMImpl am) {
        this.am = am;
    }

public void prr()
{
 am.getBlk3Approve21VORow().setNewFtycod_noVal( am.getBlk1TmpPrfNewcodVORow().getMppfcId());
 am.getBlk3Approve21VORow().setNewFulnam_noVal( am.getBlk1TmpPrfNewcodVORow().getMppfcFulnam());
 am.getBlk3Approve21VORow().setNewAdr1_noVal( am.getBlk1TmpPrfNewcodVORow().getMppfcAdr1());
 am.getBlk3Approve21VORow().setNewAdr2_noVal( am.getBlk1TmpPrfNewcodVORow().getMppfcAdr2());
 am.getBlk3Approve21VORow().setNewAdr3_noVal( am.getBlk1TmpPrfNewcodVORow().getMppfcAdr3());
 am.getBlk3Approve21VORow().setNewAdr4_noVal( am.getBlk1TmpPrfNewcodVORow().getMppfcAdr4());
}

}

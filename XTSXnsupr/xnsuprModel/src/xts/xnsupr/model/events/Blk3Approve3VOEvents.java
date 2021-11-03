package xts.xnsupr.model.events; 

import xts.xnsupr.model.helpers.XnsuprAmImplHelpers;
import xts.xnsupr.model.services.xnsuprAMImpl;

public class Blk3Approve3VOEvents {
    public Blk3Approve3VOEvents(xnsuprAMImpl am) {
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

public void prb(){
 am.lib().pstdCenterWindow("win_approve");
 am.lib().showView("approve_3_can_1");
 am.lib().setBlockProperty("blk3_approve_3_1", am.lib().defaultWhere,"spal_supcod = '~@@~'");
}
public void prr()
{
 am.getBlk3Approve3VORow().setNewFulnam_noVal( am.getBlk1TmpPrfNewcodVORow().getMppfcFulnam());
 am.getBlk3Approve3VORow().setNewAdr1_noVal( am.getBlk1TmpPrfNewcodVORow().getMppfcAdr1());
 am.getBlk3Approve3VORow().setNewAdr2_noVal( am.getBlk1TmpPrfNewcodVORow().getMppfcAdr2());
 am.getBlk3Approve3VORow().setNewAdr3_noVal( am.getBlk1TmpPrfNewcodVORow().getMppfcAdr3());
 am.getBlk3Approve3VORow().setNewAdr4_noVal( am.getBlk1TmpPrfNewcodVORow().getMppfcAdr4());
 
}

}

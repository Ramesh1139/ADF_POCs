package xts.xnsupr.model.events; 
import xts.xnsupr.model.helpers.XnsuprAmImplHelpers;
import xts.xnsupr.model.services.xnsuprAMImpl;

public class Blk3Insert21VOEvents {
    public Blk3Insert21VOEvents(xnsuprAMImpl am) {
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

 am.getBlk3Insert21VORow().setNewFulnam_noVal( am.getBlk3Insert1VORow().getNewFulnam());
 am.getBlk3Insert21VORow().setNewAdr1_noVal( am.getBlk3Insert1VORow().getNewAdr1());
 am.getBlk3Insert21VORow().setNewAdr2_noVal( am.getBlk3Insert1VORow().getNewAdr2());
 am.getBlk3Insert21VORow().setNewAdr3_noVal( am.getBlk3Insert1VORow().getNewAdr3());
 am.getBlk3Insert21VORow().setNewAdr4_noVal( am.getBlk3Insert1VORow().getNewAdr4());
 
}
public void prb(){
 am.getBlk3Insert21VORow().setNewFulnam_noVal( am.getBlk3Insert1VORow().getNewFulnam());
}

}

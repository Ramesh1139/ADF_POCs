package xts.xnsupr.model.events; 

import xts.xnsupr.model.dbwrappers.DP_MIG_XNSUPR;
import xts.xnsupr.model.helpers.XnsuprAmImplHelpers;
import xts.xnsupr.model.services.xnsuprAMImpl;

public class Blk3Insert1VOEvents {
    public Blk3Insert1VOEvents(xnsuprAMImpl am) {
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
 am.lib().pstdCenterWindow("win_insert");
 am.lib().hideView("insert_2_can_1");
}
public void prr(){
try {
{
DP_MIG_XNSUPR.PL_INSERT13_PRR1_results  result = DP_MIG_XNSUPR.PL_INSERT13_PRR1(am.getDBTransaction());
 am.getBlk3Insert1VORow().setMsgBox_noVal(result.getO_MSG_BOX());
}
}
catch (Exception ex)
{ am.getBlk3Insert1VORow().setMsgBox_noVal("");
}
}

}

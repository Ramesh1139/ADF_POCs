package xts.xnsupr.model.events;

import xts.formadf.model.utils.Ops;

import xts.xnsupr.model.helpers.XnsuprAmImplHelpers;
import xts.xnsupr.model.services.xnsuprAMImpl;

public class Blk3Approve5VOEvents {
    public Blk3Approve5VOEvents(xnsuprAMImpl am) {
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

public void prb()
{
 am.lib().pstdCenterWindow("win_approve");
}
public void prr()
{
 am.getBlk3Approve5VORow().setMppfcSbmid_noVal(am.getBlk3Approve1VORow().getMppfcSbmid());
 am.getBlk3Approve5VORow().setMppfcFulnam_noVal(am.getBlk3Approve1VORow().getMppfcFulnama1());
    if (Ops.isNull(am.getBlk3Approve5VORow().getDeclineReason()))
    {
        System.out.println("Remarks field"+am.getBlk3Approve1VORow().getRemark());
        am.getBlk3Approve5VORow().setDeclineReason_noVal(am.getBlk3Approve1VORow().getRemark());
        
    }
    else
    {
       // am.getBlk3Approve5VORow().setDeclineReason_noVal("");    
    }
 
}

}

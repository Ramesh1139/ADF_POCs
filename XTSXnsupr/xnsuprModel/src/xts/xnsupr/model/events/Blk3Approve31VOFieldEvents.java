package xts.xnsupr.model.events; 

import xts.xnsupr.model.helpers.XnsuprAmImplHelpers;
import xts.xnsupr.model.services.xnsuprAMImpl;

public class Blk3Approve31VOFieldEvents {
    public Blk3Approve31VOFieldEvents(xnsuprAMImpl am) {
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
public void ButFulnam_bp(){
 am.helpers().plApproveOrderBy2("spal_fulnam");
}
public void ButPrfcod_bp(){
 am.helpers().plApproveOrderBy2("spal_supcod");
}

}

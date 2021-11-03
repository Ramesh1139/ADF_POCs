package xts.xnsupr.model.events; 

import xts.xnsupr.model.helpers.XnsuprAmImplHelpers;
import xts.xnsupr.model.services.xnsuprAMImpl;

public class Blk4PopUpVOFieldEvents {
    public Blk4PopUpVOFieldEvents(xnsuprAMImpl am) {
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
public void ButClose_bp(){
 am.lib().hideWindow("win_pop_up");
 am.lib().goBlock( am.getBlk4PopUpVORow().getPrevBlock());
}

}

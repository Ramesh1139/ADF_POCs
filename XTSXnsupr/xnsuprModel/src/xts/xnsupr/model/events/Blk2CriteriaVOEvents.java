package xts.xnsupr.model.events; 
import xts.formadf.model.exceptions.FormTriggerFailure;

import xts.formadf.model.utils.Ops;

import xts.xnsupr.model.helpers.XnsuprAmImplHelpers;
import xts.xnsupr.model.services.xnsuprAMImpl;

public class Blk2CriteriaVOEvents {
    public Blk2CriteriaVOEvents(xnsuprAMImpl am) {
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
try {
 am.lib().pstdCenterWindow("win_criteria");
 am.lib().pstdBlk2PreBlock();
}
catch (FormTriggerFailure ex)
{throw am.lib().getFormTriggerFailure();
}
catch (Exception ex)
{ 
    ex.printStackTrace();
    am.helpers().plShowUserMessage(Ops.concat("Fatal error! Please contact XTS support. Error message: ", am.lib().sqlerrm),"WARN");
   //throw am.lib().getFormTriggerFailure();
    return;
}
}
public void prr()
{
if (Ops.eq( am.lib().upper( am.getParamVORow().getPMode()),"APPROVE"))
{
    am.getBlk2CriteriaVORow().setMppfcStscod_noVal("PA");
}
else
{
if(Ops.eq( am.lib().upper( am.getParamVORow().getPMode()),"EDIT"))
{ am.getBlk2CriteriaVORow().setMppfcStscod_noVal("PA");
}
}
}

}

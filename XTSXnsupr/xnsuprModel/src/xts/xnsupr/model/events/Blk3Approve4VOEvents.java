package xts.xnsupr.model.events; 

import xts.formadf.model.exceptions.FormTriggerFailure;

import xts.formadf.model.utils.Ops;

import xts.xnsupr.model.helpers.XnsuprAmImplHelpers;
import xts.xnsupr.model.services.xnsuprAMImpl;

public class Blk3Approve4VOEvents {
    public Blk3Approve4VOEvents(xnsuprAMImpl am) {
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
}
public void prr()
{
    
    
try 
{
}
catch (FormTriggerFailure ex)
{throw am.lib().getFormTriggerFailure();
}
catch (Exception ex)
{   
    ex.printStackTrace();
    am.helpers().plShowUserMessage(Ops.concat("Fatal Error! Please contact XTS support team. Error Message: ", am.lib().sqlerrm),"WARN");
   //throw am.lib().getFormTriggerFailure();
    return;
}


}
public void ku(){
}
public void kd(){
}

}

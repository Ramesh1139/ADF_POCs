package xts.xnsupr.model.events; 
import xts.formadf.model.exceptions.FormTriggerFailure;

import xts.formadf.model.utils.Ops;

import xts.xnsupr.model.helpers.XnsuprAmImplHelpers;
import xts.xnsupr.model.services.xnsuprAMImpl;

public class Blk2CriteriaVOFieldEvents {
    public Blk2CriteriaVOFieldEvents(xnsuprAMImpl am) {
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
public void LovSbmby_bp(){
 am.helpers().plShowLov("lov_apus_userid","blk2_criteria","mppfc_sbmby","global.one");
}
public void LovApvby_bp(){
 am.helpers().plShowLov("lov_apus_userid","blk2_criteria","mppfc_apvby","global.one");
}
public void Ok_bp(){
try
{
 am.lib().goBlock("blk1_tmp_prf_newcod");
 am.getDBTransaction().rollback();
 am.lib().executeQuery();
 am.lib().executeTrigger("when-mouse-click");
 am.getBlk1TmpPrfNewcodVOEvents().mc(am.getBlk1TmpPrfNewcodVORow());
    
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
public void Reset_bp(){
 am.lib().goBlock("blk2_criteria");
 am.lib().clearBlock( am.lib().noValidate);
}
public void Cancel_bp(){
try {
 am.lib().pstdBlk2Cancel();
}
catch (FormTriggerFailure ex)
{throw am.lib().getFormTriggerFailure();
}
catch (Exception ex)
{ ex.printStackTrace();
    am.helpers().plShowUserMessage(Ops.concat("Fatal error! Please contact XTS support. Error message: ", am.lib().sqlerrm),"WARN");
 //throw am.lib().getFormTriggerFailure();
    return;
}
}

}

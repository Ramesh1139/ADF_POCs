package xts.xnsupr.model.events;

import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;

import oracle.adf.share.ADFContext;

import xts.formadf.model.exceptions.FormTriggerFailure;

import xts.formadf.model.utils.Ops;

import xts.xnsupr.model.dbwrappers.DP_MIG_XNSUPR;
import xts.xnsupr.model.helpers.XnsuprAmImplHelpers;
import xts.xnsupr.model.services.xnsuprAMImpl;

public class Blk1ControlVOFieldEvents
{
    public Blk1ControlVOFieldEvents(xnsuprAMImpl am) 
    {
        super();
   this.am = am;
    }
    xnsuprAMImpl am;
    public xnsuprAMImpl getAm()
    {
        return am;
    }
    public void setAm(xnsuprAMImpl am) {
        this.am = am;
    }
public void Blk1Delete_bp(){
try {
 am.helpers().plBlk1Delete();
}
catch (FormTriggerFailure ex)
{
    throw am.lib().getFormTriggerFailure();
}
catch (Exception ex)
{ 
    ex.printStackTrace();
    am.helpers().plShowUserMessage(Ops.concat("Fatal error! Please contact XTS support. Error message: ", am.lib().sqlerrm),"WARN");
    //throw am.lib().getFormTriggerFailure();
    return;
    
}
}

public void Blk1Insert_bp()
{
    //Ramesh
    am.enableReservedMode();
    ADFContext.getCurrent().getPageFlowScope().put("rangeStart",am.getBlk1TmpPrfNewcodVO().getRangeStart()); 
    Boolean mainFlag = false;
   
    am.getWorkItemVORow().setWErrmsg_noVal(null);
try 
{
    mainFlag = am.helpers().plBlk1ApproveRec(); 
    
    
    if(!mainFlag)
    {  
        am.Blk3Approve1VOEvents_prr();
        FacesContext facesContext = FacesContext.getCurrentInstance();
        NavigationHandler nh = facesContext.getApplication().getNavigationHandler();
        nh.handleNavigation(facesContext, null, "approve1");
        
    }
    else {
        am.getWorkItemVORow().setWErrmsg_noVal("error");
    }
    
    System.out.println("Ending of main loop");
    
    
   
}



catch (FormTriggerFailure ex)
{
    ex.printStackTrace();
    throw am.lib().getFormTriggerFailure();
}
catch (Exception ex)
{ 
    ex.printStackTrace();
   
   throw am.lib().getFormTriggerFailure();
}

    

}
public void Blk1Exit_bp(){
 am.lib().exitForm( am.lib().noCommit, am.lib().fullRollback);
}
public void Blk1Find_bp()
{
try {
 am.lib().pstdBlk1Find();
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
public void Blk1Refresh_bp(){
try {
 am.helpers().plBlk1Refresh();
}
catch (FormTriggerFailure ex)
{
    throw am.lib().getFormTriggerFailure();
}
catch (Exception ex)
{
    ex.printStackTrace();
    am.helpers().plShowUserMessage(Ops.concat("Fatal error! Please contact XTS support. Error message: ", am.lib().sqlerrm),"WARN");
   //throw am.lib().getFormTriggerFailure();
    return;
}
}
public void Blk1Resubmit_bp()
{
 am.helpers().plBlk1Insert();
{
DP_MIG_XNSUPR.PL_CONTROL1_RESUBMIT_BP1_results  result = DP_MIG_XNSUPR.PL_CONTROL1_RESUBMIT_BP1(am.getDBTransaction(), am.getBlk1TmpPrfNewcodVORow().getMppfcRunnum());
 am.getBlk3Insert1VORow().setNewFulnam_noVal(result.getO_NEW_FULNAM());
 am.getBlk3Insert1VORow().setNewRoleSupp_noVal(result.getO_NEW_ROLE_SUPP());
 am.getBlk3Insert1VORow().setNewRoleShip_noVal(result.getO_NEW_ROLE_SHIP());
 am.getBlk3Insert1VORow().setNewRoleManu_noVal(result.getO_NEW_ROLE_MANU());
 am.getBlk3Insert1VORow().setNewRoleMate_noVal(result.getO_NEW_ROLE_MATE());
 am.getBlk3Insert1VORow().setNewAdr1_noVal(result.getO_NEW_ADR1());
 am.getBlk3Insert1VORow().setNewAdr2_noVal(result.getO_NEW_ADR2());
 am.getBlk3Insert1VORow().setNewAdr3_noVal(result.getO_NEW_ADR3());
 am.getBlk3Insert1VORow().setNewAdr4_noVal(result.getO_NEW_ADR4());
 am.getBlk3Insert1VORow().setNewCtycod_noVal(result.getO_NEW_CTYCOD());
 am.getBlk3Insert1VORow().setNewLoccod_noVal(result.getO_NEW_LOCCOD());
}
}

}

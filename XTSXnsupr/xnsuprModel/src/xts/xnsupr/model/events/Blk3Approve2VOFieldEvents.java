package xts.xnsupr.model.events; 

import xts.formadf.model.exceptions.FormTriggerFailure;
import xts.formadf.model.utils.Ops;

import xts.xnsupr.model.helpers.XnsuprAmImplHelpers;
import xts.xnsupr.model.services.xnsuprAMImpl;

public class Blk3Approve2VOFieldEvents {
    public Blk3Approve2VOFieldEvents(xnsuprAMImpl am) {
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
 am.helpers().plApproveOrderBy1("spal_fulnam");
}
public void ButPrfcod_bp(){
 am.helpers().plApproveOrderBy1("mppfs_cod");
}
public void ButScore_bp(){
// am.helpers().plApproveOrderBy1("mppfs_scr");
}
public void ButReject_bp()
{
    
// am.lib().goItem("blk3_approve_2.but_next");
// am.lib().executeTrigger("when-button-pressed");
 am.Blk3Approve2VOFieldEvents_ButNext_bp(); // I added
 
// am.lib().goItem("blk3_approve_3.but_next");
// am.lib().executeTrigger("when-button-pressed");
 am.Blk3Approve3VOFieldEvents_ButNext_bp(); // I added
 
// am.lib().goItem("blk3_approve_4.but_reject");
// am.lib().executeTrigger("when-button-pressed");
 am.Blk3Approve4VOFieldEvents_ButReject_bp();  // I added
 
if (Ops.isNull( am.getBlk3Approve5VORow().getDeclineReason()))
{ 
    am.getBlk3Approve5VORow().setDeclineReason_noVal( am.getBlk3Approve1VORow().getRemark());
}

    am.Blk3Approve5VOEvents_prr(); // added

}
public void ButPrev_bp()
{
    
 am.lib().hideView("approve_2_can_1");
 am.lib().goBlock("blk3_approve_1");
 
}

    public void ButNext_bp1(){
     //am.lib().goItem("blk3_insert_2_2.spcr_adr1");
     //am.lib().goRecord("blk3_insert_2_2");
    // am.getBlk3Insert22VORow().getRowSet().next();
     am.getBlk3Approve22VORow().getRowSet().next();
     //am.lib().nextRecord();
    }
    public void ButPrev_bp1(){
    // am.lib().goItem("blk3_insert_2_2.spcr_adr1");
     //   am.lib().goBlock("blk3_insert_2_2");
   //  am.getBlk3Insert22VORow().getRowSet().previous();
        am.getBlk3Approve22VORow().getRowSet().previous();
    }


public void ButNext_bp()
{
    am.enableReservedMode();
try 
{
 am.lib().hideView("approve_2_can_1");
 am.lib().goBlock("blk3_approve_3");
    am.lib().executeQuery();
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
    am.Blk3Approve3VOEvents_prr(); // I added

}
public void ButExit_bp()
{
try {
 am.lib().formsDdl("rollback");
 //am.lib().goBlock( am.getWorkItemVORow().getWMainBlk());
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

}

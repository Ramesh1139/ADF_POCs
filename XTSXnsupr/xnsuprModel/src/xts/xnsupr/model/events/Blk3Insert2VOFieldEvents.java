package xts.xnsupr.model.events; 
import java.math.BigDecimal;

import xts.formadf.model.exceptions.FormTriggerFailure;

import xts.formadf.model.utils.Ops;

import xts.xnsupr.model.dbwrappers.DP_MIG_XNSUPR;
import xts.xnsupr.model.helpers.XnsuprAmImplHelpers;
import xts.xnsupr.model.services.xnsuprAMImpl;

public class Blk3Insert2VOFieldEvents {
    public Blk3Insert2VOFieldEvents(xnsuprAMImpl am) {
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
public void ButScore_bp(){
 //am.helpers().plInsertOrderBy("mppfs_scr");
}
public void ButFulnam_bp(){
 am.helpers().plInsertOrderBy("spal_fulnam");
}
public void ButPrfcod_bp(){
 am.helpers().plInsertOrderBy("mppfs_cod");
}
public void ButHelp_bp()
{
String  v_msg =  null;
 am.lib().pstdCenterWindow("win_pop_up");
try {
{
DP_MIG_XNSUPR.PL_INSERT23_BUTHELP_BP1_results  result = DP_MIG_XNSUPR.PL_INSERT23_BUTHELP_BP1(am.getDBTransaction());
 v_msg = result.getO_V_MSG();
}
}
catch (Exception ex)
{ v_msg = null;
}
 am.helpers().plBlk4PopUp( v_msg,"Help");
 am.lib().goBlock("blk1_tmp_prf_newcod");
 am.lib().synchronize();
 am.lib().goBlock("blk4_pop_up");
}
public void ButExit_bp(){
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
    // am.helpers().plShowUserMessage(Ops.concat("Fatal error! Please contact XTS support. Error message: ", am.lib().sqlerrm),"WARN");
  //throw am.lib().getFormTriggerFailure();
    return;
}
}
public void ButFinish_bp()
{
try {
if (Ops.eq( am.lib().fstdConfirmAction("Submit request","Are you sure?"),new BigDecimal(88)))
{BigDecimal  v_seq_mpec_runnum =  new BigDecimal(0);
String  v_rmk =  null;
if (!Ops.isNull( am.getBlk3Insert1VORow().getNewRmk()))
{// v_rmk = Ops.concat(Ops.concat(Ops.concat(Ops.concat(Ops.concat("****************************", am.lib().chr(new BigDecimal(10))),"Request Message"), am.lib().chr(new BigDecimal(10))),"****************************"), am.lib().chr(new BigDecimal(10)));
 v_rmk = Ops.concat( v_rmk, am.getBlk3Insert1VORow().getNewRmk());
}
{
DP_MIG_XNSUPR.PL_INSERT23_BUTFINISH_BP1_results  result = DP_MIG_XNSUPR.PL_INSERT23_BUTFINISH_BP1(am.getDBTransaction());
 v_seq_mpec_runnum = result.getO_V_SEQ_MPEC_RUNNUM();
}
{
DP_MIG_XNSUPR.PL_INSERT23_BUTFINISH_BP2(am.getDBTransaction(), v_seq_mpec_runnum, am.getBlk3Insert21VORow().getNewFulnam(), am.getBlk3Insert1VORow().getNewRoleSupp(), am.getBlk3Insert1VORow().getNewRoleShip(), am.getBlk3Insert1VORow().getNewRoleManu(), am.getBlk3Insert1VORow().getNewRoleMate(), v_rmk, am.getBlk3Insert1VORow().getNewAdr1(), am.getBlk3Insert1VORow().getNewAdr3(), am.getBlk3Insert1VORow().getNewAdr2(), am.getBlk3Insert1VORow().getNewCtycod(), am.getBlk3Insert1VORow().getNewAdr4(), am.getBlk3Insert1VORow().getNewLoccod(), am.getGlobalVORow().getGUserid());
}
 //am.lib().formsDdl("commit");
 am.getDBTransaction().commit();
 am.lib().goItem("blk1_control.blk1_refresh");
 am.lib().executeTrigger("when-button-pressed");
 am.lib().goBlock( am.getWorkItemVORow().getWMainBlk());
}
else
{;
}
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
public void ButPrev_bp(){
 am.lib().hideView("insert_2_can_1");
 am.lib().goItem("blk3_insert_1.new_fulnam");
}

}

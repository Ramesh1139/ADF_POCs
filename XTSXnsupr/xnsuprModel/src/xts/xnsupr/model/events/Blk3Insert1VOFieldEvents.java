package xts.xnsupr.model.events; 

import java.math.BigDecimal;

import xts.formadf.model.dbwrappers.DP_PRF_NAMOPR;
import xts.formadf.model.dbwrappers.FL_CHK_NON_ENG_CHARS;
import xts.formadf.model.exceptions.FormTriggerFailure;

import xts.formadf.model.utils.Ops;

import xts.xnsupr.model.dbwrappers.DP_MIG_XNSUPR;
import xts.xnsupr.model.helpers.XnsuprAmImplHelpers;
import xts.xnsupr.model.services.xnsuprAMImpl;

public class Blk3Insert1VOFieldEvents {
    public Blk3Insert1VOFieldEvents(xnsuprAMImpl am) {
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
{ ex.printStackTrace();
    //am.helpers().plShowUserMessage(Ops.concat("Fatal error! Please contact XTS support. Error message: ", am.lib().sqlerrm),"WARN");
    //throw am.lib().getFormTriggerFailure();
  return;
}

}
public void ButNext_bp()
{
String  v_cursor_style =  null;
BigDecimal  i =  new BigDecimal(0);
try {
 v_cursor_style =  am.lib().getApplicationProperty( am.lib().cursorStyle);
if (Ops.isNull( am.getBlk3Insert1VORow().getNewFulnam()))
{ am.helpers().plShowUserMessage( am.helpers().plGetUserMessage("2319"));
 am.lib().goItem("blk3_insert_1.new_fulnam");
return ;
}
if (! FL_CHK_NON_ENG_CHARS.FL_CHK_NON_ENG_CHARS(am.getDBTransaction(), am.getBlk3Insert1VORow().getNewFulnam()))
{ am.helpers().plShowUserMessage( am.helpers().plGetUserMessage("3443"));
 am.lib().goItem("blk3_insert_1.new_fulnam");
return ;
}
if (Ops.and(Ops.and(Ops.and(Ops.eq( am.getBlk3Insert1VORow().getNewRoleSupp(),"N"),Ops.eq( am.getBlk3Insert1VORow().getNewRoleShip(),"N")),Ops.eq( am.getBlk3Insert1VORow().getNewRoleManu(),"N")),Ops.eq( am.getBlk3Insert1VORow().getNewRoleMate(),"N")))
{ am.helpers().plShowUserMessage( am.helpers().plGetUserMessage("2332"));
 am.lib().goItem("blk3_insert_1.new_role_supp");
return ;
}
if (Ops.isNull( am.getBlk3Insert1VORow().getNewAdr1()))
{ am.helpers().plShowUserMessage( am.helpers().plGetUserMessage("814"));
 am.lib().goItem("blk3_insert_1.new_adr1");
return ;
}
if (Ops.and(Ops.isNull( am.getBlk3Insert1VORow().getNewAdr1()),Ops.or(Ops.or(!Ops.isNull( am.getBlk3Insert1VORow().getNewAdr2()),!Ops.isNull( am.getBlk3Insert1VORow().getNewAdr3())),!Ops.isNull( am.getBlk3Insert1VORow().getNewAdr4()))))
{ am.helpers().plShowUserMessage( am.helpers().plGetUserMessage("2432"));
 am.lib().goItem("blk3_insert_1.new_adr1");
return ;
}
if (Ops.isNull( am.getBlk3Insert1VORow().getNewCtycod()))
{ am.helpers().plShowUserMessage( am.helpers().plGetUserMessage("963"));
 am.lib().goItem("blk3_insert_1.NEW_CTYCOD");
return ;
}
else
{
{
DP_MIG_XNSUPR.PL_INSERT13_BUTNEXT_BP1_results  result = DP_MIG_XNSUPR.PL_INSERT13_BUTNEXT_BP1(am.getDBTransaction(), am.getBlk3Insert1VORow().getNewCtycod());
 i = result.getO_I();
}
if (Ops.eq( i,new BigDecimal(0)))
{ am.helpers().plShowUserMessage( am.helpers().plGetUserMessage("816"));
 am.lib().goItem("blk3_insert_1.NEW_CTYCOD");
return ;
}
if (!Ops.isNull( am.getBlk3Insert1VORow().getNewLoccod()))
{
{
DP_MIG_XNSUPR.PL_INSERT13_BUTNEXT_BP2_results  result = DP_MIG_XNSUPR.PL_INSERT13_BUTNEXT_BP2(am.getDBTransaction(), am.getBlk3Insert1VORow().getNewCtycod(), am.getBlk3Insert1VORow().getNewLoccod());
 i = result.getO_I();
}
if (Ops.eq( i,new BigDecimal(0)))
{ am.helpers().plShowUserMessage( am.helpers().plGetUserMessage("850"));
 am.lib().goItem("blk3_insert_1.NEW_LOCCOD");
return ;
}
}
else
{
if(Ops.isNull( am.getBlk3Insert1VORow().getNewLoccod()))
{
{
DP_MIG_XNSUPR.PL_INSERT13_BUTNEXT_BP3_results  result = DP_MIG_XNSUPR.PL_INSERT13_BUTNEXT_BP3(am.getDBTransaction(), am.getBlk3Insert1VORow().getNewCtycod());
 i = result.getO_I();
}
if (Ops.gt( i,new BigDecimal(0)))
{ am.helpers().plShowUserMessage( am.helpers().plGetUserMessage("1122"));
 am.lib().goItem("blk3_insert_1.NEW_LOCCOD");
return ;
}
}
}
}
BigDecimal  v_count = new BigDecimal(0);
String  v_spal_supcod =  null;
String  v_mppfc_id =  null;
{
DP_MIG_XNSUPR.PL_INSERT13_BUTNEXT_BP4_results  result = DP_MIG_XNSUPR.PL_INSERT13_BUTNEXT_BP4(am.getDBTransaction(), am.getBlk3Insert1VORow().getNewFulnam());
 v_count = result.getO_V_COUNT();
}
if (Ops.gt( v_count,new BigDecimal(0)))
{
{
DP_MIG_XNSUPR.PL_INSERT13_BUTNEXT_BP5_results  result = DP_MIG_XNSUPR.PL_INSERT13_BUTNEXT_BP5(am.getDBTransaction(), am.getBlk3Insert1VORow().getNewFulnam());
 v_spal_supcod = result.getO_V_SPAL_SUPCOD();
}
 am.helpers().plShowUserMessage(Ops.concat(Ops.concat( am.helpers().plGetUserMessage("2333")," "), v_spal_supcod));
return ;
}
{
DP_MIG_XNSUPR.PL_INSERT13_BUTNEXT_BP6_results  result = DP_MIG_XNSUPR.PL_INSERT13_BUTNEXT_BP6(am.getDBTransaction(), am.getBlk3Insert1VORow().getNewFulnam());
 v_count = result.getO_V_COUNT();
}
if (Ops.gt( v_count,new BigDecimal(0)))
{
{
DP_MIG_XNSUPR.PL_INSERT13_BUTNEXT_BP7_results  result = DP_MIG_XNSUPR.PL_INSERT13_BUTNEXT_BP7(am.getDBTransaction(), am.getBlk3Insert1VORow().getNewFulnam());
 v_mppfc_id = result.getO_V_MPPFC_ID();
}
 am.helpers().plShowUserMessage(Ops.concat(Ops.concat(Ops.concat( am.helpers().plGetUserMessage("2349")," "),"Submit ID = "), v_mppfc_id));
return ;
}
;
 am.lib().setApplicationProperty( am.lib().cursorStyle,"BUSY");
if (! DP_PRF_NAMOPR.SEARCH(am.getDBTransaction(), am.getParamVORow().getPProfile(), am.getBlk3Insert1VORow().getNewFulnam()))
{ am.helpers().plShowUserMessage(Ops.concat("Fatal Error! Please contact XTS support team. Searching error. Error Message: ", am.lib().sqlerrm));
return ;
}
;
 am.lib().goBlock("blk3_insert_2_1");
 am.lib().goBlock("blk3_insert_2");
 am.lib().clearBlock( am.lib().noValidate);
 am.lib().executeQuery();
 am.lib().setApplicationProperty( am.lib().cursorStyle, v_cursor_style);
}
catch (FormTriggerFailure ex)
{ am.lib().setApplicationProperty( am.lib().cursorStyle, v_cursor_style);
throw am.lib().getFormTriggerFailure();
}
catch (Exception ex)
{ 
    ex.printStackTrace();
    am.lib().setApplicationProperty( am.lib().cursorStyle, v_cursor_style);
 am.helpers().plShowUserMessage(Ops.concat("Fatal Error! Please contact XTS support team. Error Message: ", am.lib().sqlerrm),"WARN");
//throw am.lib().getFormTriggerFailure();
 return;
}
}
public void NewFulnam_pc(){
 am.getBlk3Insert1VORow().setNewFulnam_noVal( am.lib().ltrim( am.lib().rtrim( am.getBlk3Insert1VORow().getNewFulnam())));
}
public void LovLoccod_bp(){
if (Ops.isNull( am.getBlk3Insert1VORow().getNewCtycod()))
{ am.helpers().plShowUserMessage( am.helpers().plGetUserMessage("530"));
}
else
{ am.lib().setLovProperty("LOV_MPCL_LOCCOD", am.lib().groupName,"RG_MPCL_LOCCOD_INS");
 am.helpers().plShowLov("LOV_MPCL_LOCCOD","blk3_insert_1","new_loccod","global.one","blk3_insert_1","loc_des","global.two");
}
}
public void CtycodDes_pc(){
 am.getBlk3Insert1VORow().setNewFulnam_noVal( am.lib().ltrim( am.lib().rtrim( am.getBlk3Insert1VORow().getNewFulnam())));
}
public void NewLoccod_pot(){
 am.getBlk3Insert1VORow().setLocDes_noVal( am.helpers().flLocdesc( am.getBlk3Insert1VORow().getNewLoccod(), am.getBlk3Insert1VORow().getNewCtycod()));
}
public void LovCtycod_bp(){
 am.helpers().plShowLov("lov_mpcy_ctycod","blk3_insert_1","new_ctycod","global.one","blk3_insert_1","ctycod_des","global.two");
}
public void NewCtycod_pot(){
 am.getBlk3Insert1VORow().setCtycodDes_noVal( am.helpers().flCtydesc( am.getBlk3Insert1VORow().getNewCtycod()));
}

}

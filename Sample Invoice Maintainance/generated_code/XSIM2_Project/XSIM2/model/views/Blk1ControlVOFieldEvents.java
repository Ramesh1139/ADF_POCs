package model.views;
public class Blk1ControlVOFieldEvents {
    public Blk1ControlVOFieldEvents(Xsim2AmImpl am) {
        super();
this.am = am;
    }
    Xsim2AmImpl am;
    public Xsim2AmImpl getAm() {
        return am;
    }
    public void setAm(Xsim2AmImpl am) {
        this.am = am;
    }
public void BtDefaultButton_bp(){
if (Ops.eq( am.getSystemVORow().getCursorBlock(),"BLK2_CRITERIA"))
{
if (Ops.eq( am.getWorkItemVORow().getWRecordAdding(),"N"))
{ am.lib().goItem("blk2_criteria.blk2_ok");
if (! am.lib().formFailure)
{ am.lib().executeTrigger("WHEN-BUTTON-PRESSED");
}
}
}
else
{ am.lib().goItem("blk1_control.blk1_edit");
 am.lib().executeTrigger("WHEN-BUTTON-PRESSED");
}
}
public void BtBlk1ShipMode_bp(){
if (Ops.and(Ops.eq( am.getWorkItemVORow().getWRecordAdding(),"N"),Ops.eq( am.getSystemVORow().getFormStatus(),"QUERY")))
{ am.helpers().plBlk1OrderBy("SIMS_SHPMODCOD", am.getSystemVORow().getTriggerItem());
}
else
{ am.helpers().plShowUserMessage( am.helpers().plGetUserMessage("4237"));
throw am.lib().getFormTriggerFailure();
}
}
public void BtBlk1DeptCode_bp(){
if (Ops.and(Ops.eq( am.getWorkItemVORow().getWRecordAdding(),"N"),Ops.eq( am.getSystemVORow().getFormStatus(),"QUERY")))
{ am.helpers().plBlk1OrderBy("SIMS_DPTCOD", am.getSystemVORow().getTriggerItem());
}
else
{ am.helpers().plShowUserMessage( am.helpers().plGetUserMessage("4237"));
throw am.lib().getFormTriggerFailure();
}
}
public void BtBlk1Customer_bp(){
if (Ops.and(Ops.eq( am.getWorkItemVORow().getWRecordAdding(),"N"),Ops.eq( am.getSystemVORow().getFormStatus(),"QUERY")))
{ am.helpers().plBlk1OrderBy("SIMS_CUSCOD", am.getSystemVORow().getTriggerItem());
}
else
{ am.helpers().plShowUserMessage( am.helpers().plGetUserMessage("4237"));
throw am.lib().getFormTriggerFailure();
}
}
public void BtBlk1Year_bp(){
if (Ops.and(Ops.eq( am.getWorkItemVORow().getWRecordAdding(),"N"),Ops.eq( am.getSystemVORow().getFormStatus(),"QUERY")))
{ am.helpers().plBlk1OrderBy("SIMS_YEAR", am.getSystemVORow().getTriggerItem());
}
else
{ am.helpers().plShowUserMessage( am.helpers().plGetUserMessage("4237"));
throw am.lib().getFormTriggerFailure();
}
}
public void BtBlk1PortD_bp(){
if (Ops.and(Ops.eq( am.getWorkItemVORow().getWRecordAdding(),"N"),Ops.eq( am.getSystemVORow().getFormStatus(),"QUERY")))
{ am.helpers().plBlk1OrderBy("SIMS_PORT_D", am.getSystemVORow().getTriggerItem());
}
else
{ am.helpers().plShowUserMessage( am.helpers().plGetUserMessage("4237"));
throw am.lib().getFormTriggerFailure();
}
}
public void BtBlk1Shpdat_bp(){
if (Ops.and(Ops.eq( am.getWorkItemVORow().getWRecordAdding(),"N"),Ops.eq( am.getSystemVORow().getFormStatus(),"QUERY")))
{ am.helpers().plBlk1OrderBy("SIMS_SHPDAT", am.getSystemVORow().getTriggerItem());
}
else
{ am.helpers().plShowUserMessage( am.helpers().plGetUserMessage("4237"));
throw am.lib().getFormTriggerFailure();
}
}
public void BtBlk1Invdat_bp(){
if (Ops.and(Ops.eq( am.getWorkItemVORow().getWRecordAdding(),"N"),Ops.eq( am.getSystemVORow().getFormStatus(),"QUERY")))
{ am.helpers().plBlk1OrderBy("SIMS_INVDAT", am.getSystemVORow().getTriggerItem());
}
else
{ am.helpers().plShowUserMessage( am.helpers().plGetUserMessage("4237"));
throw am.lib().getFormTriggerFailure();
}
}
public void Blk1Massupd_bp(){
BigDecimal  status =  new BigDecimal(0);
BigDecimal  temp_count =  new BigDecimal(0);
 temp_count = new BigDecimal(0);
if (!Ops.isNull( am.getBlk1TsiMstVORow().getSimsSaminvrun()))
{ am.lib().goBlock("blk6_control");
 am.lib().clearBlock();
 am.lib().goBlock("BLK6_UPDLST");
 am.lib().executeQuery();
 am.lib().firstRecord();
while (true) {
if (Ops.eq( am.lib().nameIn(":system.last_record"),"TRUE")) break;
 temp_count = Ops.plus( temp_count,new BigDecimal(1));
 am.lib().nextRecord();
}
 am.getBlk6ControlVORow().setTotalCount_noVal(Ops.plus( temp_count,new BigDecimal(1)));
 am.getBlk6ControlVORow().setTotalSelected_noVal("0");
 am.lib().firstRecord();
}
else
{ am.lib().setAlertProperty("AL_STD_WARN", am.lib().alertMessageText,"No record on the list.");
 status =  am.lib().showAlert("AL_STD_WARN");
throw am.lib().getFormTriggerFailure();
/* Unreachable Statement in original code
return ;
*/}
}
public void Blk1Print_bp(){
ParamList  pl_id;
BigDecimal  status =  new BigDecimal(0);
 pl_id =  am.lib().getParameterList("tmpdata");
if (! am.lib().idNull( pl_id))
{ am.lib().destroyParameterList( pl_id);
}
 pl_id =  am.lib().createParameterList("tmpdata");
 am.lib().addParameter( pl_id,"p_sims_invno_from", am.lib().textParameter, am.lib().nameIn(Ops.concat( am.getWorkItemVORow().getWMainBlk(),".sims_invno")));
 am.lib().addParameter( pl_id,"p_sims_invno_to", am.lib().textParameter, am.lib().nameIn(Ops.concat( am.getWorkItemVORow().getWMainBlk(),".sims_invno")));
 am.lib().callForm("XSIRPM2", am.lib().noHide, no_replace, am.lib().noQueryOnly, pl_id);
}
public void Blk1Delete_bp(){
 am.lib().copy("D","work_item.w_edit_mode");
 am.lib().goBlock( am.lib().nameIn("work_item.w_main_blk"));
if (Ops.eq( am.lib().fstdConfirmAction("Confirm Delete record","Delete highlight record"),new BigDecimal(88)))
{
{
DP_MIG_XSIM2.PL_CONTROL1_DELETE_BP1(am.getDBTransaction(), am.getBlk1TsiMstVORow().getSimsSaminvrun());
}
{
DP_MIG_XSIM2.PL_CONTROL1_DELETE_BP2(am.getDBTransaction(), am.getBlk1TsiMstVORow().getSimsSaminvrun());
}
 am.lib().commit();
 am.lib().goBlock( am.lib().nameIn("work_item.w_main_blk"));
 am.lib().countQuery();
 am.lib().executeQuery();
}
}
public void BtBlk1DocNo_bp(){
if (Ops.and(Ops.eq( am.getWorkItemVORow().getWRecordAdding(),"N"),Ops.eq( am.getSystemVORow().getFormStatus(),"QUERY")))
{ am.helpers().plBlk1OrderBy("SIMS_INVNO", am.getSystemVORow().getTriggerItem());
}
else
{ am.helpers().plShowUserMessage( am.helpers().plGetUserMessage("4237"));
throw am.lib().getFormTriggerFailure();
}
}
public void Blk1Exit_bp(){
 am.lib().pstdBlk1Exit();
 am.lib().exitForm( am.lib().noValidate, am.lib().fullRollback);
}
public void Blk1Refresh_bp(){
 am.getWorkItemVORow().setWSearchMode_noVal("B");
 am.lib().goBlock("BLK1_TSI_MST");
 am.lib().executeQuery();
}
public void Blk1Find_bp(){
 am.lib().pstdBlk1Find();
 am.lib().copy( am.lib().nameIn(Ops.concat( am.getWorkItemVORow().getWCriteriaBlk(),".sims_divcod")),"global.g_divcod");
 am.lib().copy( am.lib().nameIn(Ops.concat( am.getWorkItemVORow().getWCriteriaBlk(),".sims_divcod")),"work_item.w_div");
 am.lib().copy( am.lib().nameIn(Ops.concat( am.getWorkItemVORow().getWCriteriaBlk(),".sims_divcod")),"info_blk.info_div");
}
public void Blk1Copy_bp(){
String  p_current_item =  null;
String  d_close =  null;
String  w_module =  null;
String  w_autgrp =  null;
String  p_prefix =  null;
Item  it_id;
BigDecimal  rowcnt =  new BigDecimal(0);
 am.lib().copy("C","work_item.w_edit_mode");
 am.lib().goBlock( am.lib().nameIn("work_item.w_main_blk"));
 p_current_item =  am.lib().fstdMarkRecNo(null);
 am.lib().copy( p_current_item,"work_item.w_rec_no_blk1");
 am.lib().setItemProperty("blk3_control.blk3_first_rec", am.lib().enabled, am.lib().propertyFalse);
 am.lib().setItemProperty("blk3_control.blk3_prv_rec", am.lib().enabled, am.lib().propertyFalse);
 am.lib().setItemProperty("blk3_control.blk3_next_rec", am.lib().enabled, am.lib().propertyFalse);
 am.lib().setItemProperty("blk3_control.blk3_last_rec", am.lib().enabled, am.lib().propertyFalse);
if (!Ops.isNull( am.lib().nameIn("global.g_module")))
{ w_autgrp =  am.lib().nameIn("global.g_autgrp");
 w_module =  am.lib().nameIn("global.g_module");
{
DP_MIG_XSIM2.PL_CONTROL1_COPY_BP1_results  result = DP_MIG_XSIM2.PL_CONTROL1_COPY_BP1(am.getDBTransaction(), w_autgrp, w_module);
 d_close = result.getO_D_CLOSE();
}
if (Ops.eq( d_close,"N"))
{ p_prefix = Ops.concat( am.lib().substr( am.lib().nameIn("work_item.w_first_item"),new BigDecimal(1),new BigDecimal(5)),"clsdat");
 it_id =  am.lib().findItem(Ops.concat(Ops.concat( am.lib().nameIn("work_item.w_detail_blk"),"."), p_prefix));
if ( am.lib().idNull( it_id))
{;
}
else
{ am.lib().pstdDisableItemEdit(Ops.concat(Ops.concat( am.lib().nameIn("work_item.w_detail_blk"),"."), p_prefix));
 am.lib().setItemProperty(Ops.concat(Ops.concat( am.lib().nameIn("work_item.w_detail_blk"),"."), p_prefix), am.lib().visualAttribute,"VA_READ_ONLY");
}
}
}
 am.lib().pstdChkPackageFailure();
 am.lib().goBlock("blk3_tsi_mst");
 am.lib().pstdChkPackageFailure();
 am.lib().executeQuery();
 am.lib().pstdChkPackageFailure();
 am.lib().createRecord();
 duplicate_record();
 am.lib().pstdChkPackageFailure();
 am.lib().pstdChkPackageFailure();
 am.lib().goBlock("blk4_tsi_des_d");
 am.lib().pstdChkPackageFailure();
{
DP_MIG_XSIM2.PL_CONTROL1_COPY_BP2_results  result = DP_MIG_XSIM2.PL_CONTROL1_COPY_BP2(am.getDBTransaction(), am.getBlk3TsiMstVORow().getSimsSaminvrun(), am.getGlobalVORow().getGDivcod());
 rowcnt = result.getO_ROWCNT();
}
if (Ops.ne( rowcnt,new BigDecimal(0)))
{ am.lib().executeQuery();
 am.lib().pstdChkPackageFailure();
 am.lib().createRecord();
 duplicate_record();
 am.lib().pstdChkPackageFailure();
 am.lib().copy(null,"blk4_tsi_des_d.sids_saminvrun");
}
 am.lib().pstdChkPackageFailure();
 am.lib().goBlock("blk4_tsi_des_b");
 am.lib().pstdChkPackageFailure();
{
DP_MIG_XSIM2.PL_CONTROL1_COPY_BP3_results  result = DP_MIG_XSIM2.PL_CONTROL1_COPY_BP3(am.getDBTransaction(), am.getBlk3TsiMstVORow().getSimsSaminvrun(), am.getGlobalVORow().getGDivcod());
 rowcnt = result.getO_ROWCNT();
}
if (Ops.ne( rowcnt,new BigDecimal(0)))
{ am.lib().executeQuery();
 am.lib().pstdChkPackageFailure();
 am.lib().createRecord();
 duplicate_record();
 am.lib().pstdChkPackageFailure();
 am.lib().copy(null,"blk4_tsi_des_b.sids_saminvrun");
}
 am.lib().pstdChkPackageFailure();
 am.lib().goBlock("blk4_tsi_des_q");
 am.lib().pstdChkPackageFailure();
{
DP_MIG_XSIM2.PL_CONTROL1_COPY_BP4_results  result = DP_MIG_XSIM2.PL_CONTROL1_COPY_BP4(am.getDBTransaction(), am.getBlk3TsiMstVORow().getSimsSaminvrun(), am.getGlobalVORow().getGDivcod());
 rowcnt = result.getO_ROWCNT();
}
if (Ops.ne( rowcnt,new BigDecimal(0)))
{ am.lib().executeQuery();
 am.lib().pstdChkPackageFailure();
 am.lib().createRecord();
 duplicate_record();
 am.lib().pstdChkPackageFailure();
 am.lib().copy(null,"blk4_tsi_des_q.sids_saminvrun");
}
 am.lib().pstdChkPackageFailure();
 am.lib().goBlock("blk4_tsi_des_a");
 am.lib().pstdChkPackageFailure();
{
DP_MIG_XSIM2.PL_CONTROL1_COPY_BP5_results  result = DP_MIG_XSIM2.PL_CONTROL1_COPY_BP5(am.getDBTransaction(), am.getBlk3TsiMstVORow().getSimsSaminvrun(), am.getGlobalVORow().getGDivcod());
 rowcnt = result.getO_ROWCNT();
}
if (Ops.ne( rowcnt,new BigDecimal(0)))
{ am.lib().executeQuery();
 am.lib().pstdChkPackageFailure();
 am.lib().createRecord();
 duplicate_record();
 am.lib().pstdChkPackageFailure();
 am.lib().copy(null,"blk4_tsi_des_a.sids_saminvrun");
}
 am.lib().pstdChkPackageFailure();
 am.lib().goBlock("blk5_tsi_itm");
 am.lib().pstdChkPackageFailure();
{
DP_MIG_XSIM2.PL_CONTROL1_COPY_BP6_results  result = DP_MIG_XSIM2.PL_CONTROL1_COPY_BP6(am.getDBTransaction(), am.getBlk3TsiMstVORow().getSimsSaminvrun(), am.getGlobalVORow().getGDivcod());
 rowcnt = result.getO_ROWCNT();
}
if (Ops.ne( rowcnt,new BigDecimal(0)))
{ am.lib().executeQuery();
 am.lib().firstRecord();
while (true) {
 am.lib().pstdChkPackageFailure();
 am.lib().createRecord();
 duplicate_record();
 am.lib().pstdChkPackageFailure();
 am.lib().copy(null,"blk5_tsi_itm.siit_saminvrun");
if (Ops.eq( am.lib().nameIn(":system.last_record"),"TRUE")) break;
 am.lib().nextRecord();
}
}
 am.lib().copy(null,"blk3_tsi_mst.sims_saminvrun");
 am.lib().copy(null,"blk3_tsi_mst.sims_sernum");
 am.lib().goItem(Ops.concat( am.getWorkItemVORow().getWDetailBlk(),".sims_cuscod"));
 am.lib().setItemProperty(Ops.concat( am.getWorkItemVORow().getWDetailBlk(),".sims_dptcod"), am.lib().navigable, am.lib().propertyFalse);
 am.lib().setItemProperty(Ops.concat( am.getWorkItemVORow().getWDetailBlk(),".sims_dptcod"), am.lib().updateable, am.lib().propertyFalse);
 am.lib().setItemProperty(Ops.concat( am.getWorkItemVORow().getWDetailBlk(),".blk3_lov_dptcod"), am.lib().enabled, am.lib().propertyFalse);
 am.lib().setItemProperty("blk3_control.blk3_print", am.lib().enabled, am.lib().propertyFalse);
 am.getWorkItemVORow().setWCurBlock_noVal("DTL");
 am.getWorkItemVORow().setWDtlBlock_noVal("HDR");
 am.helpers().plGoToTab("BLK3_GEN");
 am.helpers().plSetMenuSingleRec();
 am.lib().setItemProperty(Ops.concat( am.getWorkItemVORow().getWDetailBlk(),".sims_totamt"), am.lib().visualAttribute,"VA_EDIT_ONLY");
 am.lib().setItemProperty(Ops.concat( am.getWorkItemVORow().getWDetailBlk(),".sims_totamt"), am.lib().updateable, am.lib().propertyTrue);
 am.lib().setItemProperty(Ops.concat( am.getWorkItemVORow().getWDetailBlk(),".sims_invamt"), am.lib().visualAttribute,"VA_EDIT_ONLY");
 am.lib().setItemProperty(Ops.concat( am.getWorkItemVORow().getWDetailBlk(),".sims_invamt"), am.lib().updateable, am.lib().propertyTrue);
if (Ops.ne( DP_CASE.CHECK_DPT_SETCOD(am.getDBTransaction(), am.getGlobalVORow().getGDivcod(), am.getBlk3TsiMstVORow().getSimsDptcod(), am.getBlk3TsiMstVORow().getSimsCuscod(),"J73"),"N"))
{ am.lib().setItemProperty("BLK1_CONTROL.CHARGE_TYPE", am.lib().visualAttribute,"VA_PROMPT_FIELD_REQ");
}
}
public void Blk1Insert_bp(){
 am.getGlobalVORow().setSpName_noVal(new BigDecimal(0));
 am.lib().goBlock("sam_blk");
}
public void Blk1Edit_bp(){
 am.getGlobalVORow().setSpName_noVal(new BigDecimal(0));
 am.getWorkItemVORow().setWChanged_noVal("N");
 am.helpers().plLockSi( am.getGlobalVORow().getGDivcod(), am.getBlk1TsiMstVORow().getSimsSaminvrun());
 am.lib().pstdBlk1EditButtonAdv();
 am.lib().setItemProperty(Ops.concat( am.getWorkItemVORow().getWDetailBlk(),".sims_dptcod"), am.lib().navigable, am.lib().propertyFalse);
 am.lib().setItemProperty(Ops.concat( am.getWorkItemVORow().getWDetailBlk(),".sims_dptcod"), am.lib().updateable, am.lib().propertyFalse);
 am.lib().setItemProperty(Ops.concat( am.getWorkItemVORow().getWDetailBlk(),".sims_dptcod"), am.lib().insertAllowed, am.lib().propertyFalse);
 am.lib().setItemProperty(Ops.concat( am.getWorkItemVORow().getWDetailBlk(),".blk3_lov_dptcod"), am.lib().enabled, am.lib().propertyFalse);
 am.lib().setItemProperty("blk4_tsi_des_d.sids_des", am.lib().visualAttribute,"va_desc_edit_only");
 am.lib().setItemProperty("blk4_tsi_des_b.sids_des", am.lib().visualAttribute,"va_desc_edit_only");
 am.lib().setItemProperty("blk4_tsi_des_q.sids_des", am.lib().visualAttribute,"va_desc_edit_only");
 am.lib().setItemProperty("blk4_tsi_des_a.sids_des", am.lib().visualAttribute,"va_desc_edit_only");
BigDecimal  i =  new BigDecimal(0);
 am.lib().copy("E","work_item.w_edit_mode");
{
DP_MIG_XSIM2.PL_CONTROL1_EDIT_BP1_results  result = DP_MIG_XSIM2.PL_CONTROL1_EDIT_BP1(am.getDBTransaction(), am.getBlk3TsiMstVORow().getSimsSaminvrun(), am.getGlobalVORow().getGDivcod());
 i = result.getO_I();
}
if (Ops.gt( i,new BigDecimal(0)))
{ am.lib().setItemProperty(Ops.concat( am.getWorkItemVORow().getWDetailBlk(),".sims_totamt"), am.lib().visualAttribute,"VA_READ_ONLY");
 am.lib().setItemProperty(Ops.concat( am.getWorkItemVORow().getWDetailBlk(),".sims_totamt"), am.lib().updateable, am.lib().propertyFalse);
 am.lib().setItemProperty(Ops.concat( am.getWorkItemVORow().getWDetailBlk(),".sims_invamt"), am.lib().visualAttribute,"VA_READ_ONLY");
 am.lib().setItemProperty(Ops.concat( am.getWorkItemVORow().getWDetailBlk(),".sims_invamt"), am.lib().updateable, am.lib().propertyTrue);
}
else
{ am.lib().setItemProperty(Ops.concat( am.getWorkItemVORow().getWDetailBlk(),".sims_totamt"), am.lib().visualAttribute,"VA_EDIT_ONLY");
 am.lib().setItemProperty(Ops.concat( am.getWorkItemVORow().getWDetailBlk(),".sims_totamt"), am.lib().updateable, am.lib().propertyTrue);
 am.lib().setItemProperty(Ops.concat( am.getWorkItemVORow().getWDetailBlk(),".sims_invamt"), am.lib().visualAttribute,"VA_EDIT_ONLY");
 am.lib().setItemProperty(Ops.concat( am.getWorkItemVORow().getWDetailBlk(),".sims_invamt"), am.lib().updateable, am.lib().propertyTrue);
}
 am.lib().setItemProperty("blk3_control.blk3_delete", am.lib().enabled, am.lib().propertyFalse);
 am.lib().setItemProperty("blk1_control.blk1_delete", am.lib().enabled, am.lib().propertyFalse);
if (Ops.eq( DP_CASE.CHECK_SETCOD(am.getDBTransaction(), am.getGlobalVORow().getGDivcod(), am.lib().nvl( am.getBlk3TsiMstVORow().getSimsCuscod(),"*"),"J76"),"Y"))
{ am.lib().setItemProperty("BLK3_TSI_MST.sims_samsnd", am.lib().enabled, am.lib().propertyTrue);
 am.lib().setItemProperty("BLK3_TSI_MST.sims_samsnd", am.lib().updateable, am.lib().propertyTrue);
}
else
{ am.lib().setItemProperty("BLK3_TSI_MST.sims_samsnd", am.lib().enabled, am.lib().propertyFalse);
 am.lib().setItemProperty("BLK3_TSI_MST.sims_samsnd", am.lib().updateable, am.lib().propertyFalse);
}
 am.lib().setItemProperty("BLK3_TSI_MST.sims_snddat", am.lib().updateable, am.lib().propertyFalse);
 am.getWorkItemVORow().setWCurBlock_noVal("DTL");
 am.getWorkItemVORow().setWDtlBlock_noVal("HDR");
 am.helpers().plGoToTab("BLK3_GEN");
 am.lib().showView("blk4_can");
 am.lib().hideView("blk5_can");
 am.helpers().plSetMenuSingleRec();
if (Ops.ne( DP_CASE.CHECK_DPT_SETCOD(am.getDBTransaction(), am.getGlobalVORow().getGDivcod(), am.getBlk3TsiMstVORow().getSimsDptcod(), am.getBlk3TsiMstVORow().getSimsCuscod(),"J73"),"N"))
{ am.lib().setItemProperty("BLK1_CONTROL.CHARGE_TYPE", am.lib().visualAttribute,"VA_PROMPT_FIELD_REQ");
}
}

}

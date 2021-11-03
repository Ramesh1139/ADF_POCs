package model.views;
public class Blk3ControlVOFieldEvents {
    public Blk3ControlVOFieldEvents(Xsim2AmImpl am) {
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
public void Blk3Amt_bp(){
 am.lib().goItem("blk4_tsi_des_a.sids_des");
}
public void Blk3Des_bp(){
 am.lib().goItem("blk4_tsi_des_d.sids_des");
}
public void Blk3Qty_bp(){
 am.lib().goItem("blk4_tsi_des_q.sids_des");
}
public void Blk3Bot_bp(){
 am.lib().goItem("blk4_tsi_des_b.sids_des");
}
public void Blk3Gen_bp(){
 am.lib().goItem("blk3_tsi_mst.sims_chrtyp");
if (Ops.eq( am.getWorkItemVORow().getWDtlBlock(),"DTL"))
{
if (Ops.ne( am.getSystemVORow().getFormStatus(),"QUERY"))
{ am.lib().goItem("BLK3_CONTROL.blk3_save");
 am.lib().executeTrigger("WHEN-BUTTON-PRESSED");
if (Ops.eq( am.getWorkItemVORow().getBlk3Valid(),"Y"))
{ am.lib().goItem("BLK5_CONTROL.blk5_ok");
 am.lib().executeTrigger("WHEN-BUTTON-PRESSED");
}
}
}
}
public void Blk3Exit_bp(){
BigDecimal  status =  new BigDecimal(0);
String  ins_mode = "N";
 am.getWorkItemVORow().setBlk3Valid_noVal("Y");
 am.lib().showView("blk4_can");
if (Ops.or(Ops.ne( am.lib().nameIn("system.form_status"),"QUERY"),Ops.eq( am.getWorkItemVORow().getWChanged(),"Y")))
{ am.lib().setAlertProperty("al_std_3_button", am.lib().title,"Save Changes");
 am.lib().setAlertProperty("al_std_3_button", am.lib().alertMessageText,"Do you want to save the changes ?");
 status =  am.lib().showAlert("al_std_3_button");
if (Ops.eq( status,new BigDecimal(88)))
{ am.lib().goBlock( am.lib().nameIn("work_item.w_detail_blk"));
 am.lib().goItem("blk3_control.blk3_save");
 am.lib().executeTrigger("WHEN-BUTTON-PRESSED");
if (Ops.eq( am.getWorkItemVORow().getWEditMode(),"A"))
{ am.getBlk2CriteriaVORow().setSimsInvno_noVal(Ops.concat(Ops.concat(Ops.concat( am.lib().upper( am.getBlk3TsiMstVORow().getSimsDptcod()),"/"), am.lib().lpad( am.getBlk3TsiMstVORow().getSimsYear(),new BigDecimal(2),"0")), am.lib().lpad( am.getBlk3TsiMstVORow().getSimsSernum(),new BigDecimal(4),"0")));
if (Ops.eq( am.getBlk3TsiMstVORow().getSimsTyp(),"C"))
{ am.getBlk2CriteriaVORow().setSimsInvno_noVal(Ops.concat("CDN/", am.getBlk2CriteriaVORow().getSimsInvno()));
}
else
{
if(Ops.eq( am.getBlk3TsiMstVORow().getSimsTyp(),"D"))
{ am.getBlk2CriteriaVORow().setSimsInvno_noVal(Ops.concat("DBN/", am.getBlk2CriteriaVORow().getSimsInvno()));
}
else
{
if(Ops.eq( am.getBlk3TsiMstVORow().getSimsTyp(),"S"))
{ am.getBlk2CriteriaVORow().setSimsInvno_noVal(Ops.concat("SAM/", am.getBlk2CriteriaVORow().getSimsInvno()));
}
}
}
 am.getWorkItemVORow().setWNoCriteria_noVal("N");
}
if (Ops.eq( am.lib().nameIn("system.form_status"),"QUERY"))
{ am.lib().goBlock( am.lib().nameIn("work_item.w_main_blk"));
 am.lib().countQuery();
 am.lib().executeQuery();
 am.lib().goRecord( am.lib().nameIn("work_item.w_rec_no_blk1"));
 am.getGlobalVORow().setGGlobalEnd_noVal("Y");
}
}
else
{
if(Ops.eq( status,new BigDecimal(89)))
{
if (!Ops.isNull( am.lib().getApplicationProperty( am.lib().callingForm)))
{ am.lib().exitForm();
}
else
{ am.lib().goBlock("blk5_tsi_itm");
 am.lib().clearBlock( am.lib().noValidate);
 am.lib().goBlock( am.lib().nameIn("work_item.w_detail_blk"));
 am.lib().clearBlock( am.lib().noValidate);
 am.lib().goBlock( am.lib().nameIn("work_item.w_main_blk"));
 am.lib().synchronize();
 am.lib().issueRollback(null);
 am.lib().commit();
if (Ops.eq( am.getWorkItemVORow().getWNoCriteria(),"N"))
{ am.lib().countQuery();
 am.lib().executeQuery();
 am.lib().goRecord( am.lib().nameIn("work_item.w_rec_no_blk1"));
}
else
{ am.lib().goBlock("blk2_criteria");
}
 am.getGlobalVORow().setGGlobalEnd_noVal("Y");
}
}
else
{
if (Ops.eq( am.getWorkItemVORow().getWDtlBlock(),"LST"))
{ am.lib().showView("blk4_can");
 am.lib().hideView("blk5_can");
 am.lib().goItem("BLK3_TSI_MST.SIMS_CHRTYP");
}
else
{ am.lib().showView("blk5_can");
 am.lib().hideView("blk4_can");
 am.lib().goItem("BLK5_TSI_ITM.SIIT_ITMNUM");
}
 am.getWorkItemVORow().setBlk3Valid_noVal("N");
}
}
}
else
{
if (!Ops.isNull( am.lib().getApplicationProperty( am.lib().callingForm)))
{ am.lib().exitForm();
}
else
{ am.getGlobalVORow().setGGlobalEnd_noVal("Y");
 am.helpers().pstdUsrRollback();
if (Ops.eq( am.getWorkItemVORow().getWEditMode(),"A"))
{ am.getBlk2CriteriaVORow().setSimsInvno_noVal(Ops.concat(Ops.concat(Ops.concat( am.lib().upper( am.getBlk3TsiMstVORow().getSimsDptcod()),"/"), am.lib().lpad( am.getBlk3TsiMstVORow().getSimsYear(),new BigDecimal(2),"0")), am.lib().lpad( am.getBlk3TsiMstVORow().getSimsSernum(),new BigDecimal(4),"0")));
if (Ops.eq( am.getBlk3TsiMstVORow().getSimsTyp(),"C"))
{ am.getBlk2CriteriaVORow().setSimsInvno_noVal(Ops.concat("CDN/", am.getBlk2CriteriaVORow().getSimsInvno()));
}
else
{
if(Ops.eq( am.getBlk3TsiMstVORow().getSimsTyp(),"D"))
{ am.getBlk2CriteriaVORow().setSimsInvno_noVal(Ops.concat("DBN/", am.getBlk2CriteriaVORow().getSimsInvno()));
}
else
{
if(Ops.eq( am.getBlk3TsiMstVORow().getSimsTyp(),"S"))
{ am.getBlk2CriteriaVORow().setSimsInvno_noVal(Ops.concat("SAM/", am.getBlk2CriteriaVORow().getSimsInvno()));
}
}
}
if (!Ops.isNull( am.getBlk2CriteriaVORow().getSimsInvno()))
{ am.getWorkItemVORow().setWNoCriteria_noVal("N");
}
}
 am.lib().goBlock( am.lib().nameIn("work_item.w_detail_blk"));
 am.lib().goBlock( am.lib().nameIn("work_item.w_main_blk"));
 am.lib().synchronize();
if (Ops.eq( am.getWorkItemVORow().getWNoCriteria(),"N"))
{ am.lib().countQuery();
 am.lib().executeQuery();
 am.lib().goRecord( am.lib().nameIn("work_item.w_rec_no_blk1"));
}
else
{ am.lib().goBlock("blk2_criteria");
}
}
}
}
public void Blk3Additm_bp(){
boolean  i;
BigDecimal  status =  new BigDecimal(0);
 i =  am.helpers().plValidateDptcod;
 i =  am.helpers().plValidateCuscod;
 i =  am.helpers().plValidateAdrcod;
 i =  am.helpers().plValidatePortL;
 i =  am.helpers().plValidateVia;
 i =  am.helpers().plValidatePortD;
 i =  am.helpers().plValidateCarcod;
 i =  am.helpers().plValidateShpmod;
if (Ops.eq( am.getWorkItemVORow().getBlk3Valid(),"Y"))
{ am.lib().goBlock( am.lib().nameIn("work_item.w_detail_blk"));
 am.lib().executeTrigger("PRE-UPDATE");
 am.lib().synchronize();
if (!Ops.isNull( am.getWorkItemVORow().getWErritm()))
{ am.lib().setAlertProperty("AL_STD_WARN", am.lib().alertMessageText, am.getWorkItemVORow().getWErrmsg());
 status =  am.lib().showAlert("AL_STD_WARN");
 am.lib().goItem( am.getWorkItemVORow().getWErritm());
return ;
}
if (Ops.and(Ops.eq( am.getSystemVORow().getFormStatus(),"CHANGED"),Ops.ne( am.getWorkItemVORow().getWEditMode(),"E")))
{ am.lib().goBlock( am.lib().nameIn("work_item.w_detail_blk"));
 am.lib().goItem("blk3_control.blk3_exit");
 am.lib().executeTrigger("WHEN-BUTTON-PRESSED");
 am.lib().goItem("blk1_control.blk1_edit");
 am.lib().executeTrigger("WHEN-BUTTON-PRESSED");
}
 am.helpers().plGoToTab("BLK3_ADDITM");
 am.getWorkItemVORow().setWDtlBlock_noVal("DTL");
 am.helpers().plSetMenuSingleRec();
 am.lib().synchronize();
 am.lib().goBlock("blk5_tsi_itm");
 am.lib().executeQuery();
 am.lib().issueSavepoint("additm");
}
}
public void Blk3Delete_bp(){
 am.lib().copy("D","work_item.w_edit_mode");
 am.lib().showView("blk4_can");
if (Ops.eq( am.lib().fstdConfirmAction("Confirm Delete record","Delete highlight record"),new BigDecimal(88)))
{ am.lib().goBlock( am.lib().nameIn("work_item.w_detail_blk"));
 am.lib().deleteRecord();
 am.lib().commitForm();
if (Ops.ne( am.lib().nameIn("system.form_status"),"QUERY"))
{ am.lib().message("Error prevented commit");
return ;
}
if (Ops.eq( am.lib().nameIn("work_item.w_call_form"),"N"))
{ am.lib().goBlock( am.lib().nameIn("work_item.w_main_blk"));
 am.lib().executeQuery();
 am.lib().goRecord( am.lib().nameIn("work_item.w_rec_no_blk1"));
}
else
{ am.lib().exitForm( am.lib().askCommit);
}
}
}
public void Blk3Print_bp(){
BigDecimal  status =  new BigDecimal(0);
 am.lib().showView("blk4_can");
if (Ops.ne( am.lib().nameIn("system.form_status"),"QUERY"))
{ am.lib().setAlertProperty("al_std_2_button", am.lib().title,"Save Changes");
 am.lib().setAlertProperty("al_std_2_button", am.lib().alertMessageText,"Save the changes before print?");
 status =  am.lib().showAlert("al_std_2_button");
if (Ops.eq( status,new BigDecimal(88)))
{ am.lib().goBlock( am.lib().nameIn("work_item.w_detail_blk"));
 am.lib().showView("blk4_can");
 am.lib().goItem("blk3_control.blk3_save");
 am.lib().executeTrigger("WHEN-BUTTON-PRESSED");
if (Ops.ne( am.lib().nameIn("system.form_status"),"CHANGED"))
{ParamList  pl_id;
BigDecimal  status =  new BigDecimal(0);
 pl_id =  am.lib().getParameterList("tmpdata");
if (! am.lib().idNull( pl_id))
{ am.lib().destroyParameterList( pl_id);
}
 pl_id =  am.lib().createParameterList("tmpdata");
 am.lib().addParameter( pl_id,"p_sims_invno_from", am.lib().textParameter, am.lib().nameIn(Ops.concat( am.getWorkItemVORow().getWDetailBlk(),".sims_invno")));
 am.lib().addParameter( pl_id,"p_sims_invno_to", am.lib().textParameter, am.lib().nameIn(Ops.concat( am.getWorkItemVORow().getWDetailBlk(),".sims_invno")));
 am.lib().addParameter( pl_id,"p_call_from_edit", am.lib().textParameter,"Y");
 am.lib().callForm("XSIRPM2", am.lib().noHide, no_replace, am.lib().noQueryOnly, pl_id);
}
}
}
else
{ParamList  pl_id;
BigDecimal  status =  new BigDecimal(0);
 pl_id =  am.lib().getParameterList("tmpdata");
if (! am.lib().idNull( pl_id))
{ am.lib().destroyParameterList( pl_id);
}
 pl_id =  am.lib().createParameterList("tmpdata");
 am.lib().addParameter( pl_id,"p_sims_invno_from", am.lib().textParameter, am.lib().nameIn(Ops.concat( am.getWorkItemVORow().getWDetailBlk(),".sims_invno")));
 am.lib().addParameter( pl_id,"p_sims_invno_to", am.lib().textParameter, am.lib().nameIn(Ops.concat( am.getWorkItemVORow().getWDetailBlk(),".sims_invno")));
 am.lib().addParameter( pl_id,"p_call_from_edit", am.lib().textParameter,"Y");
 am.lib().callForm("XSIRPM2", am.lib().noHide, no_replace, am.lib().noQueryOnly, pl_id);
}
}
public void Blk3FirstRec_bp(){
 am.lib().pstdBlk3FirstRec();
}
public void Blk3Save_bp(){
BigDecimal  flag = new BigDecimal(0);
String  p_current_item =  null;
BigDecimal  status =  new BigDecimal(0);
boolean  i;
BigDecimal  maxretrytime = new BigDecimal(7);
BigDecimal  try = new BigDecimal(0);
 am.getWorkItemVORow().setBlk3Valid_noVal("Y");
 am.getWorkItemVORow().setWErrmsg_noVal("");
while(Ops.and(Ops.and(Ops.ne( am.getSystemVORow().getFormStatus(),"QUERY"),Ops.isNull( am.getWorkItemVORow().getWErrmsg())),Ops.lt( try, maxretrytime))) {
 am.lib().commitForm();
if (Ops.ne( am.getSystemVORow().getFormStatus(),"QUERY"))
{ am.helpers().plBlk3Save();
 am.lib().setAlertProperty("AL_STD_WARN", am.lib().alertMessageText, am.getWorkItemVORow().getWErrmsg());
 status =  am.lib().showAlert("AL_STD_WARN");
 am.lib().showView("blk3_can");
 am.getWorkItemVORow().setBlk3Valid_noVal("N");
 am.lib().goItem( am.getWorkItemVORow().getWErritm());
return ;
 p_current_item =  am.lib().getBlockProperty( am.getWorkItemVORow().getWDetailBlk(), am.lib().firstItem);
while(Ops.or(!Ops.isNull( p_current_item),Ops.eq( flag,new BigDecimal(0)))) {
 flag = new BigDecimal(1);
if (Ops.and(Ops.eq( am.lib().getItemProperty(Ops.concat(Ops.concat( am.lib().nameIn("work_item.w_detail_blk"),"."), p_current_item), am.lib().itemType),"TEXT ITEM"),Ops.eq( am.lib().getItemProperty(Ops.concat(Ops.concat( am.lib().nameIn("work_item.w_detail_blk"),"."), p_current_item), am.lib().primaryKey),"TRUE")))
{ am.lib().goItem(Ops.concat(Ops.concat( am.lib().nameIn("work_item.w_detail_blk"),"."), p_current_item));
return ;
}
 p_current_item =  am.lib().getItemProperty(Ops.concat(Ops.concat( am.lib().nameIn("work_item.w_detail_blk"),"."), p_current_item), am.lib().nextitem);
}
}
else
{ am.helpers().plBlk3Save();
{
DP_MIG_XSIM2.PL_CONTROL3_SAVE_BP1_results  result = DP_MIG_XSIM2.PL_CONTROL3_SAVE_BP1(am.getDBTransaction(), am.getBlk3TsiMstVORow().getSimsSaminvrun());
 am.getBlk3TsiMstVORow().setLstchg_noVal(result.getO_LSTCHG());
}
{
DP_MIG_XSIM2.PL_CONTROL3_SAVE_BP2_results  result = DP_MIG_XSIM2.PL_CONTROL3_SAVE_BP2(am.getDBTransaction(), am.getBlk3TsiMstVORow().getSimsSaminvrun());
 am.getBlk3TsiMstVORow().setLstby_noVal(result.getO_LSTBY());
}
if (Ops.and(Ops.eq( am.getWorkItemVORow().getWDtlBlock(),"DTL"),Ops.eq( am.getWorkItemVORow().getBlk3Valid(),"Y")))
{ am.lib().goItem("blk5_control.blk5_ok");
 am.lib().executeTrigger("WHEN-BUTTON-PRESSED");
}
}
 try = Ops.plus( try,new BigDecimal(1));
}
if (Ops.and(Ops.eq( try, maxretrytime),Ops.ne( am.getSystemVORow().getFormStatus(),"QUERY")))
{ am.getWorkItemVORow().setWErrmsg_noVal( am.lib().fstdUsrMsg("1099"));
 am.lib().setAlertProperty("AL_STD_WARN", am.lib().alertMessageText, am.getWorkItemVORow().getWErrmsg());
 status =  am.lib().showAlert("AL_STD_WARN");
 am.lib().showView("blk3_can");
 am.getWorkItemVORow().setBlk3Valid_noVal("N");
return ;
}
if (Ops.eq( try,new BigDecimal(0)))
{ am.lib().message("No change to save");
return ;
}
if (Ops.eq( am.getWorkItemVORow().getWCallForm(),"Y"))
{ am.getWorkItemVORow().setWChanged_noVal("N");
 am.lib().exitForm( am.lib().askCommit);
return ;
}
if (Ops.or(Ops.eq( am.getWorkItemVORow().getWEditMode(),"A"),Ops.eq( am.getWorkItemVORow().getWEditMode(),"C")))
{ am.lib().copy("E","work_item.w_edit_mode");
 am.lib().setItemProperty("blk3_tsi_mst.sims_dptcod", am.lib().enabled, am.lib().propertyFalse);
 am.lib().setItemProperty("blk3_tsi_mst.sims_dptcod", am.lib().visualAttribute,"VA_READ_ONLY");
 am.lib().setItemProperty("blk3_tsi_mst.blk3_lov_dptcod", am.lib().enabled, am.lib().propertyFalse);
 am.lib().setItemProperty("blk3_control.blk3_print", am.lib().enabled, am.lib().propertyTrue);
 am.lib().setItemProperty("blk3_control.blk3_delete", am.lib().enabled, am.lib().propertyFalse);
 am.lib().setItemProperty("blk1_control.blk1_delete", am.lib().enabled, am.lib().propertyFalse);
 am.getWorkItemVORow().setWChanged_noVal("N");
 am.helpers().plShowInvno();
 am.getBlk2CriteriaVORow().setSimsInvno_noVal( am.getBlk3TsiMstVORow().getSimsInvno());
 am.getWorkItemVORow().setWNoCriteria_noVal("N");
 am.helpers().plLockSi( am.getGlobalVORow().getGDivcod(), am.getBlk3TsiMstVORow().getSimsSaminvrun());
 am.lib().goBlock( am.lib().nameIn("work_item.w_main_blk"));
 am.lib().countQuery();
 am.lib().executeQuery();
 am.lib().pstdBlk1EditButtonAdv();
}
else
{ am.getWorkItemVORow().setWChanged_noVal("N");
 am.helpers().plShowInvno();
 am.helpers().plLockSi( am.getGlobalVORow().getGDivcod(), am.getBlk3TsiMstVORow().getSimsSaminvrun());
}
}
public void Blk3PrvRec_bp(){
 am.lib().pstdBlk3PrvRec();
}
public void Blk3LastRec_bp(){
 am.lib().pstdBlk3LastRec();
}
public void Blk3NextRec_bp(){
 am.lib().pstdBlk3NextRec();
}

}

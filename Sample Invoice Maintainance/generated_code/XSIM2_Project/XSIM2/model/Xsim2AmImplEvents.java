package model;
public class Xsim2AmImplEvents {
    public Xsim2AmImplEvents(Xsim2AmImpl am) {
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
public void prf(){
 am.lib().plInitFormForAdf();
}
public void cje(){
String  event_name =  am.getSystemVORow().getJavascriptEventName();
String  event_val =  am.getSystemVORow().getJavascriptEventValue();
 am.lib().plJsEventForAdf( event_name, event_val);
}
public void pof(){
 am.lib().plExitFormWithAdf();
}
public void oe(){
BigDecimal  lv_errcod =  am.lib().errorCode;
String  lv_errtyp =  am.lib().errorType;
String  lv_errtxt =  am.lib().errorText;
BigDecimal  status =  new BigDecimal(0);
String  str =  null;
 am.getWorkItemVORow().setWErrmsg_noVal("");
if (Ops.eq( lv_errcod,new BigDecimal(40919)))
{;
}
if (Ops.eq( lv_errcod,new BigDecimal(40735)))
{
if (Ops.eq( am.lib().substr( lv_errtxt,new BigDecimal(-6),new BigDecimal(5)),"00054"))
{ str =  am.lib().fstdUsrMsg("673");
 am.getWorkItemVORow().setWErrmsg_noVal(Ops.concat(Ops.concat( am.lib().substr( str,new BigDecimal(1), am.lib().instr( str," ",new BigDecimal(1),new BigDecimal(1))), am.getBlk1TsiMstVORow().getSimsInvno()), am.lib().substr( str, am.lib().instr( str," ",new BigDecimal(1),new BigDecimal(1)))));
 am.lib().setAlertProperty("AL_STD_WARN", am.lib().alertMessageText, am.getWorkItemVORow().getWErrmsg());
 status =  am.lib().showAlert("AL_STD_WARN");
return ;
}
}
else
{ am.lib().message(Ops.concat(Ops.concat(Ops.concat(Ops.concat( lv_errtyp,"-"), am.lib().toChar( lv_errcod)),": "), lv_errtxt));
throw am.lib().getFormTriggerFailure();
}
}
public void nfi(){
String  w_divcod =  null;
String  w_autgrp =  null;
String  w_userid =  null;
String  w_module =  null;
try {
 am.lib().pstdMaxWindows("WIN_MAIN");
 am.lib().pstdSetGlobal();
 am.getGlobalVORow().setGModule_noVal("SI");
 am.lib().pstdSecurity();
 am.helpers().plInitVariable();
 am.helpers().plUpdateWindowTitle();
 w_userid =  am.getWorkItemVORow().getWUser();
{
DP_MIG_XSIM2.PL_NFI1_results  result = DP_MIG_XSIM2.PL_NFI1(am.getDBTransaction(), w_userid);
 w_autgrp = result.getO_W_AUTGRP();
}
 w_module =  am.lib().nameIn("global.g_module");
{
DP_MIG_XSIM2.PL_NFI2_results  result = DP_MIG_XSIM2.PL_NFI2(am.getDBTransaction(), w_autgrp, w_module);
 am.getWorkItemVORow().setWNew_noVal(result.getO_W_NEW());
 am.getWorkItemVORow().setWEdit_noVal(result.getO_W_EDIT());
 am.getWorkItemVORow().setWEnq_noVal(result.getO_W_ENQ());
 am.getWorkItemVORow().setWDel_noVal(result.getO_W_DEL());
}
try {
{
DP_MIG_XSIM2.PL_NFI3_results  result = DP_MIG_XSIM2.PL_NFI3(am.getDBTransaction(), am.getGlobalVORow().getGUserid(), am.getGlobalVORow().getGModule());
 am.getWorkItemVORow().setWPrt_noVal(result.getO_W_PRT());
}
}
catch (Exception ex)
{ am.getWorkItemVORow().setWPrt_noVal("N");
}
 am.lib().copy(new BigDecimal(0),"global.global_trigger_status");
 am.helpers().plSetMenuMultiRec();
if (Ops.and(Ops.eq( am.getWorkItemVORow().getWEdit(),"N"),Ops.eq( am.getWorkItemVORow().getWEnq(),"Y")))
{ am.lib().setItemProperty("blk1_control.bt_edit", am.lib().enabled, am.lib().propertyTrue);
 am.lib().setItemProperty("blk1_control.bt_edit", am.lib().label,"Enquiry");
 am.getWorkItemVORow().setWEditMode_noVal("W");
}
if (!Ops.isNull( am.lib().getApplicationProperty( am.lib().callingForm)))
{ am.getWorkItemVORow().setWCallForm_noVal("Y");
 am.lib().goBlock( am.getWorkItemVORow().getWDetailBlk());
 am.lib().setItemProperty(Ops.concat( am.getWorkItemVORow().getWDetailBlk(),".blk3_lov_copy"), am.lib().displayed, am.lib().propertyTrue);
 am.lib().setItemProperty(Ops.concat( am.getWorkItemVORow().getWDetailBlk(),".blk3_lov_copy"), am.lib().enabled, am.lib().propertyTrue);
 am.lib().setItemProperty("blk3_control.blk3_first_rec", am.lib().enabled, am.lib().propertyFalse);
 am.lib().setItemProperty("blk3_control.blk3_prv_rec", am.lib().enabled, am.lib().propertyFalse);
 am.lib().setItemProperty("blk3_control.blk3_next_rec", am.lib().enabled, am.lib().propertyFalse);
 am.lib().setItemProperty("blk3_control.blk3_last_rec", am.lib().enabled, am.lib().propertyFalse);
 am.lib().setItemProperty("blk3_control.blk3_delete", am.lib().enabled, am.lib().propertyFalse);
}
else
{ am.getWorkItemVORow().setWCallForm_noVal("N");
 am.lib().goItem("BLK2_CRITERIA.BLK2_OK");
 am.lib().executeTrigger("WHEN-BUTTON-PRESSED");
}
}
catch (FormTriggerFailure ex)
{ am.lib().exitForm( am.lib().noCommit);
}
}
public void fp(){
if (Ops.eq( am.getWorkItemVORow().getWCurBlock(),"LST"))
{ am.lib().goItem("BLK1_CONTROL.blk1_PRINT");
 am.lib().executeTrigger("WHEN-BUTTON-PRESSED");
}
else
{
if(Ops.eq( am.getWorkItemVORow().getWCurBlock(),"DTL"))
{ am.lib().goItem("BLK3_CONTROL.blk3_PRINT");
 am.lib().executeTrigger("WHEN-BUTTON-PRESSED");
}
}
}
public void fcl(){
BigDecimal  v_status =  new BigDecimal(0);
if (Ops.eq( am.getWorkItemVORow().getWCurBlock(),"LST"))
{ am.helpers().plResetSelectCriteria();
 am.lib().goItem("BLK1_CONTROL.BLK1_EXIT");
 am.getGlobalVORow().setGlobalTriggerStatus_noVal(new BigDecimal(0));
 am.lib().executeTrigger("WHEN-BUTTON-PRESSED");
}
if (Ops.eq( am.getWorkItemVORow().getWCurBlock(),"DTL"))
{
if (Ops.eq( am.getWorkItemVORow().getWDtlBlock(),"HDR"))
{ am.lib().goItem("BLK3_CONTROL.BLK3_EXIT");
 am.lib().executeTrigger("WHEN-BUTTON-PRESSED");
if (Ops.eq( am.getWorkItemVORow().getBlk3Valid(),"Y"))
{ am.getWorkItemVORow().setWCurBlock_noVal("LST");
if (Ops.eq( am.getGlobalVORow().getGlobalTriggerStatus(),new BigDecimal(0)))
{ am.helpers().plSetMenuMultiRec();
 am.lib().hideView("BLK3_CAN");
 am.lib().showView("BLK1_CAN");
}
}
}
else
{ am.lib().goItem("BLK3_CONTROL.BLK3_EXIT");
 am.lib().executeTrigger("WHEN-BUTTON-PRESSED");
if (Ops.eq( am.getWorkItemVORow().getBlk3Valid(),"Y"))
{ am.getWorkItemVORow().setWCurBlock_noVal("LST");
if (Ops.eq( am.getGlobalVORow().getGlobalTriggerStatus(),new BigDecimal(0)))
{ am.helpers().plSetMenuMultiRec();
 am.lib().hideView("BLK3_CAN");
 am.lib().showView("BLK1_CAN");
}
}
}
}
}
public void wa(){
if (Ops.eq( am.getGlobalVORow().getGlobalTriggerStatus(),new BigDecimal(0)))
{ am.lib().defaultValue(null,"global.global_go_main_menu");
if (Ops.eq( am.lib().nameIn("global.global_go_main_menu"),"Y"))
{ am.lib().erase("global.global_go_main_menu");
 am.lib().executeTrigger("WHEN_FILE_CLOSE");
 am.lib().executeTrigger("WHEN_FILE_CLOSE");
}
}
}
public void ex(){
 am.lib().doKey("EXIT_FORM");
}
public void fs(){
if (Ops.eq( am.getWorkItemVORow().getWDtlBlock(),"HDR"))
{ am.lib().goItem("BLK3_CONTROL.blk3_save");
 am.lib().executeTrigger("WHEN-BUTTON-PRESSED");
}
else
{ am.lib().goItem("BLK3_CONTROL.blk3_save");
 am.lib().executeTrigger("WHEN-BUTTON-PRESSED");
 am.helpers().plGoToTab("BLK3_ADDITM");
 am.lib().goBlock("blk5_tsi_itm");
}
}
public void fnr(){
 am.lib().goItem("BLK1_CONTROL.BLK1_INSERT");
 am.lib().executeTrigger("WHEN-BUTTON-PRESSED");
}
public void fcy(){
if (!Ops.isNull( am.getBlk1TsiMstVORow().getSimsInvno()))
{ am.lib().goItem("BLK1_CONTROL.blk1_COPY");
 am.lib().executeTrigger("WHEN-BUTTON-PRESSED");
}
}
public void fd(){
if (Ops.eq( am.getWorkItemVORow().getWCurBlock(),"LST"))
{ am.lib().goItem("BLK1_CONTROL.blk1_delete");
 am.lib().executeTrigger("WHEN-BUTTON-PRESSED");
}
if (Ops.eq( am.getWorkItemVORow().getWCurBlock(),"DTL"))
{
if (Ops.eq( am.getWorkItemVORow().getWDtlBlock(),"HDR"))
{ am.lib().goItem("BLK3_CONTROL.blk3_delete");
 am.lib().executeTrigger("WHEN-BUTTON-PRESSED");
}
else
{ am.lib().goItem("BLK5_CONTROL.blk5_delete");
 am.lib().executeTrigger("WHEN-BUTTON-PRESSED");
}
}
}
public void cd(){
 am.helpers().clearAllMasterDetails();
}

}

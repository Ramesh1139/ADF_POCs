package xts.xnsupr.model.helpers;

import java.math.BigDecimal;

import java.util.StringTokenizer;

import javax.faces.application.FacesMessage;
import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;

import oracle.jbo.Row;

import oracle.jbo.server.ViewObjectImpl;

import xts.formadf.model.dbwrappers.DP_EMAIL;
import xts.formadf.model.dbwrappers.DP_EPM_CON;
import xts.formadf.model.exceptions.FormTriggerFailure;

import xts.formadf.model.exceptions.NoDataFound;

import xts.formadf.model.utils.Item;
import xts.formadf.model.utils.Lov;
import xts.formadf.model.utils.Ops;

import xts.formadf.model.utils.Relation;

import xts.xnsupr.model.dbwrappers.DP_MIG_XNSUPR;
import xts.xnsupr.model.entities.Blk1TmpPrfNewcodImpl;
import xts.xnsupr.model.services.xnsuprAMImpl;
import xts.xnsupr.model.views.Blk1TmpPrfNewcodVOImpl;
import xts.xnsupr.model.views.Blk1TmpPrfNewcodVORowImpl;

public class XnsuprAmImplHelpers {
    public XnsuprAmImplHelpers(xnsuprAMImpl am) {
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

public String plGetUserMessage(String  p_msgcod) {
String  v_msgcod =  null;
String  v_desc =  null;
try {
{
DP_MIG_XNSUPR.PL_GET_USER_MESSAGE1_results  result = DP_MIG_XNSUPR.PL_GET_USER_MESSAGE1(am.getDBTransaction(), p_msgcod);
 v_desc = result.getO_V_DESC();
}
return  v_desc;
}
catch (FormTriggerFailure ex)
{
    throw am.lib().getFormTriggerFailure();
}
catch (NoDataFound ex)
{
    return "Fatal error! Please contact XTS support. Error message: Message code cannot be found.";
}
catch (Exception ex)
{return Ops.concat("Fatal error! Please contact XTS support. Error message: ", am.lib().sqlerrm);
}
}

public Boolean plBlk1EnableInsert() {
String  v_allow_edit =  null;
try {
{
DP_MIG_XNSUPR.PL_BLK1_ENABLE_INSERT1_results  result = DP_MIG_XNSUPR.PL_BLK1_ENABLE_INSERT1(am.getDBTransaction(), am.getGlobalVORow().getGAutgrp());
 v_allow_edit = result.getO_V_ALLOW_EDIT();
}
if (Ops.eq( v_allow_edit,"Y"))
{return  true;
}
}
catch (Exception ex)
{ v_allow_edit = null;
}
if (Ops.ne( am.lib().nvl( v_allow_edit,"N"),"Y"))
{try {
{
DP_MIG_XNSUPR.PL_BLK1_ENABLE_INSERT2_results  result = DP_MIG_XNSUPR.PL_BLK1_ENABLE_INSERT2(am.getDBTransaction(), am.getGlobalVORow().getGAutgrp());
 v_allow_edit = result.getO_V_ALLOW_EDIT();
}
if (Ops.eq( v_allow_edit,"Y"))
{return  true;
}
}
catch (Exception ex)
{ v_allow_edit = null;
}
}
return  false;
}

public String flCtydesc(String  ctycod) {
String  cty_desc =  null;
try {
{
DP_MIG_XNSUPR.PL_FL_CTYDESC1_results  result = DP_MIG_XNSUPR.PL_FL_CTYDESC1(am.getDBTransaction(), ctycod);
 cty_desc = result.getO_CTY_DESC();
}
return  cty_desc;
}
catch (Exception ex)
{return null;
}
}

public String flLocdesc(String  loccod,String  ctycod) {
String  loc_desc =  null;
try {
{
DP_MIG_XNSUPR.PL_FL_LOCDESC1_results  result = DP_MIG_XNSUPR.PL_FL_LOCDESC1(am.getDBTransaction(), ctycod, loccod);
 loc_desc = result.getO_LOC_DESC();
}
return  loc_desc;
}
catch (Exception ex)
{
    return null;
}
}

public void plBlk1Insert() 
{
try {
 am.lib().goBlock( am.getWorkItemVORow().getWMainBlk());
 am.lib().executeTrigger("when-mouse-click");
if (Ops.eq( am.lib().getItemProperty("blk1_control.blk1_insert", am.lib().enabled),"FALSE"))
{ 
    am.helpers().plShowUserMessage( am.helpers().plGetUserMessage("2392"));
   throw am.lib().getFormTriggerFailure();
}
if (Ops.isNull( DP_EMAIL.GET_EMAIL_ADDR(am.getDBTransaction(), am.getGlobalVORow().getGUserid())))
{ am.helpers().plShowUserMessage( am.helpers().plGetUserMessage("2339"));
throw am.lib().getFormTriggerFailure();
}
 am.getWorkItemVORow().setWCreateFor_noVal("");
 //am.lib().setBlockProperty("blk3_insert_2", am.lib().orderBy,"mppfs_scr desc");
 am.lib().goBlock("blk3_insert_2");
 am.lib().clearBlock( am.lib().noValidate);
 am.lib().goBlock("blk3_insert_2_1");
 am.lib().clearBlock( am.lib().noValidate);
 am.lib().goBlock("blk3_insert_1");
 am.lib().clearBlock( am.lib().noValidate);
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

public void plBlk1OrderBy(String  p_order) {
try {
 am.lib().synchronize();
if (Ops.isNull( am.getBlk1TmpPrfNewcodVORow().getMppfcId()))
{return ;
}
if (Ops.eq( am.getWorkItemVORow().getWBlk1OrderByField(), p_order))
{
if (Ops.or(Ops.eq( am.getWorkItemVORow().getWBlk1OrderByAsc(),"DESC"),Ops.isNull( am.getWorkItemVORow().getWBlk1OrderByAsc())))
{ am.getWorkItemVORow().setWBlk1OrderByAsc_noVal("ASC");
 am.lib().setBlockProperty("blk1_tmp_prf_newcod", am.lib().orderBy,Ops.concat( p_order," ASC"));
}
else
{ am.getWorkItemVORow().setWBlk1OrderByAsc_noVal("DESC");
 am.lib().setBlockProperty("blk1_tmp_prf_newcod", am.lib().orderBy,Ops.concat( p_order," DESC"));
}
}
else
{ am.getWorkItemVORow().setWBlk1OrderByAsc_noVal("ASC");
 am.lib().setBlockProperty("blk1_tmp_prf_newcod", am.lib().orderBy,Ops.concat( p_order," ASC"));
}
 am.getWorkItemVORow().setWBlk1OrderByField_noVal( p_order);
 am.lib().goBlock("blk1_tmp_prf_newcod");
 am.lib().executeQuery();
 am.lib().executeTrigger("when-mouse-click");
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

public void plBlk1Refresh() {
try {
 am.lib().goBlock("blk1_tmp_prf_newcod");
 am.lib().executeQuery();
 am.lib().executeTrigger("when-mouse-click");
    //am.getBlk1TmpPrfNewcodVOEvents().mc();
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

public void plBlk1ControlSet() {
String  v_current_block =  null;
String  v_current_item =  null;
Item  v_item_id;
try {
 v_current_block = "BLK1_CONTROL";
if ( am.lib().idNull( am.lib().findBlock( v_current_block)))
{return ;
}
 v_current_item =  am.lib().getBlockProperty( v_current_block, am.lib().firstItem);
while(!Ops.isNull( v_current_item)) {
if (Ops.and(Ops.eq( am.lib().getItemProperty(Ops.concat(Ops.concat( v_current_block,"."), v_current_item), am.lib().itemType),"BUTTON"),Ops.eq( am.lib().getItemProperty(Ops.concat(Ops.concat( v_current_block,"."), v_current_item), am.lib().enabled),"TRUE")))
{ am.lib().setItemProperty(Ops.concat(Ops.concat( v_current_block,"."), v_current_item), am.lib().navigable, am.lib().propertyTrue);
}
 v_current_item =  am.lib().getItemProperty(Ops.concat(Ops.concat( v_current_block,"."), v_current_item), am.lib().nextitem);
if (Ops.or(Ops.isNull( v_current_item),Ops.eq( v_current_item, am.lib().getBlockProperty( v_current_block, am.lib().firstItem)))) break;
}
}
catch (FormTriggerFailure ex)
{throw am.lib().getFormTriggerFailure();
}
catch (Exception ex)
{// am.helpers().plShowUserMessage(Ops.concat("Fatal error! Please contact XTS support. Error message: ", am.lib().sqlerrm),"WARN");
throw am.lib().getFormTriggerFailure();
}
}

public void plInitVariable()
{
 am.getWorkItemVORow().setWDisplayHeight_noVal( am.lib().getApplicationProperty( am.lib().displayHeight));
 am.getWorkItemVORow().setWDisplayWidth_noVal( am.lib().getApplicationProperty( am.lib().displayWidth));
 am.getWorkItemVORow().setWFormId_noVal( am.lib().getApplicationProperty( am.lib().currentFormName));
 am.getWorkItemVORow().setWUser_noVal( am.getGlobalVORow().getGUserid());
 am.getWorkItemVORow().setWDiv_noVal( am.getGlobalVORow().getGDivcod());
 am.getWorkItemVORow().setWSystemName_noVal("XTS System");
 am.getWorkItemVORow().setWTableName_noVal("tfp_prf_new_cod");
 am.getWorkItemVORow().setWMainBlk_noVal("blk1_tmp_prf_newcod");
 am.getWorkItemVORow().setWControlBlk_noVal("blk1_control");
 am.getWorkItemVORow().setWCriteriaBlk_noVal("blk2_criteria");
 am.getWorkItemVORow().setWEditMode_noVal("");
 am.getWorkItemVORow().setWChg_noVal("N");
 am.getWorkItemVORow().setWRecNoBlk1_noVal(new BigDecimal(1));
 am.getWorkItemVORow().setWDefaultConsubrolDescLeng_noVal("100");
 am.getSystemVORow().setMessageLevel_noVal(new BigDecimal(15));
if (Ops.eq( am.getParamVORow().getPMode(),"EDIT"))
{ 
    am.getInfoBlkVORow().setInfoFormName_noVal("Supp/Fty Code Request");
}
else
{ am.getInfoBlkVORow().setInfoFormName_noVal("Supp/Fty Code Confirmation");
}
 am.getInfoBlkVORow().setInfoFormId_noVal( am.getWorkItemVORow().getWFormId());
 am.getInfoBlkVORow().setInfoUser_noVal( am.getWorkItemVORow().getWUser());
 am.getInfoBlkVORow().setInfoDate_noVal( am.lib().sysdate);
 am.getInfoBlkVORow().setInfoDiv_noVal( am.getWorkItemVORow().getWDiv());
am.getBlk2CriteriaVORow().setMppfcStscod_noVal("PA");  // I added
 am.helpers().plSetBrowseMode();
}

public void plApproveOrderBy2(String  p_order) {
try {
 am.lib().synchronize();
if (Ops.eq( am.getWorkItemVORow().getWApproveOrderByField2(), p_order))
{
if (Ops.or(Ops.eq( am.getWorkItemVORow().getWApproveOrderByAsc2(),"DESC"),Ops.isNull( am.getWorkItemVORow().getWApproveOrderByAsc2())))
{ am.getWorkItemVORow().setWApproveOrderByAsc2_noVal("ASC");
 am.lib().setBlockProperty("blk3_approve_3_1", am.lib().orderBy,Ops.concat( p_order," ASC"));
}
else
{ am.getWorkItemVORow().setWApproveOrderByAsc2_noVal("DESC");
 am.lib().setBlockProperty("blk3_approve_3_1", am.lib().orderBy,Ops.concat( p_order," DESC"));
}
}
else
{ am.getWorkItemVORow().setWApproveOrderByAsc2_noVal("ASC");
 am.lib().setBlockProperty("blk3_approve_3_1", am.lib().orderBy,Ops.concat( p_order," ASC"));
}
 am.getWorkItemVORow().setWApproveOrderByField2_noVal( p_order);
 am.lib().goBlock("blk3_approve_3_1");
 am.lib().executeQuery();
}
catch (FormTriggerFailure ex)
{throw am.lib().getFormTriggerFailure();
}
catch (Exception ex)
{ex.printStackTrace();
    am.helpers().plShowUserMessage(Ops.concat("Fatal error! Please contact XTS support. Error message: ", am.lib().sqlerrm),"WARN");
  
  //throw am.lib().getFormTriggerFailure();
    return;
}
}

public void plApproveOrderBy1(String  p_order) {
try {
 am.lib().synchronize();
if (Ops.eq( am.getWorkItemVORow().getWApproveOrderByField1(), p_order))
{
if (Ops.or(Ops.eq( am.getWorkItemVORow().getWApproveOrderByAsc1(),"DESC"),Ops.isNull( am.getWorkItemVORow().getWApproveOrderByAsc1())))
{ am.getWorkItemVORow().setWApproveOrderByAsc1_noVal("ASC");
 //am.lib().setBlockProperty("blk3_approve_2", am.lib().orderBy,Ops.concat( p_order," ASC"));
}
else
{ am.getWorkItemVORow().setWApproveOrderByAsc1_noVal("DESC");
 am.lib().setBlockProperty("blk3_approve_2", am.lib().orderBy,Ops.concat( p_order," DESC"));
}
}
else
{ am.getWorkItemVORow().setWApproveOrderByAsc1_noVal("ASC");
 //am.lib().setBlockProperty("blk3_approve_2", am.lib().orderBy,Ops.concat( p_order," ASC"));
}
 am.getWorkItemVORow().setWApproveOrderByField1_noVal( p_order);
 am.lib().goBlock("blk3_approve_2");
 am.lib().executeQuery();
}
catch (FormTriggerFailure ex)
{throw am.lib().getFormTriggerFailure();
}
catch (Exception ex)
{  ex.printStackTrace();
    am.helpers().plShowUserMessage(Ops.concat("Fatal error! Please contact XTS support. Error message: ", am.lib().sqlerrm),"WARN");
//throw am.lib().getFormTriggerFailure();
    return;
}
}

public void plInsertOrderBy(String  p_order) {
try {
 am.lib().synchronize();
if (Ops.eq( am.getWorkItemVORow().getWInsertOrderByField(), p_order))
{
if (Ops.or(Ops.eq( am.getWorkItemVORow().getWInsertOrderByAsc(),"DESC"),Ops.isNull( am.getWorkItemVORow().getWInsertOrderByAsc())))
{ am.getWorkItemVORow().setWInsertOrderByAsc_noVal("ASC");
 am.lib().setBlockProperty("blk3_insert_2", am.lib().orderBy,Ops.concat( p_order," ASC"));
}
else
{ am.getWorkItemVORow().setWInsertOrderByAsc_noVal("DESC");
 am.lib().setBlockProperty("blk3_insert_2", am.lib().orderBy,Ops.concat( p_order," DESC"));
}
}
else
{ am.getWorkItemVORow().setWInsertOrderByAsc_noVal("ASC");
 am.lib().setBlockProperty("blk3_insert_2", am.lib().orderBy,Ops.concat( p_order," ASC"));
}
 am.getWorkItemVORow().setWInsertOrderByField_noVal( p_order);
 am.lib().goBlock("blk3_insert_2");
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
}

public void plShowLov(String  p_lov_name,String  p_block_name1,String  p_item_name1,String  p_rtn_item_name1)
{
    plShowLov(p_lov_name,p_block_name1,p_item_name1,p_rtn_item_name1,null,null,null,null,null,null);
}

    public void plShowLov(String  p_lov_name,
                          String  p_block_name1,
                          String  p_item_name1,
                          String  p_rtn_item_name1,
                          String  p_block_name2,
                          String  p_item_name2,
                          String  p_rtn_item_name2) {
        
        plShowLov(p_lov_name,p_block_name1,p_item_name1,p_rtn_item_name1,p_block_name2 ,p_item_name2,p_rtn_item_name2,null,null,null); 
    }


public void plShowLov(String  p_lov_name,
                      String  p_block_name1,
                      String  p_item_name1,
                      String  p_rtn_item_name1,
                      String  p_block_name2/*TODO:Handle Default Value: null*/,
                      String  p_item_name2/*TODO:Handle Default Value: null*/,
                      String  p_rtn_item_name2/*TODO:Handle Default Value: null*/,
                      String  p_block_name3/*TODO:Handle Default Value: null*/,
                      String  p_item_name3/*TODO:Handle Default Value: null*/,
                      String  p_rtn_item_name3/*TODO:Handle Default Value: null*/) 
{
String  v_lov_name =  p_lov_name;
String  v_block_name1 =  p_block_name1;
String  v_item_name1 =  p_item_name1;
Item  v_item_id1;
String  v_block_name2 =  p_block_name2;
String  v_item_name2 =  p_item_name2;
Item  v_item_id2;
String  v_block_name3 =  p_block_name3;
String  v_item_name3 =  p_item_name3;
Item  v_item_id3;
BigDecimal  v_rtncod =  new BigDecimal(0);
Lov  v_lov;
try {
 v_lov =  am.lib().findLov( p_lov_name);
if ( am.lib().idNull( v_lov))
{ 
    am.helpers().plShowUserMessage(Ops.concat(Ops.concat("Fatal Error! Please contact XTS support. Error message: Cannot find the LOV '", p_lov_name),"'"),null,"WARN");
throw am.lib().getFormTriggerFailure();
}
if (Ops.eq( am.lib().populateGroup( am.lib().getLovProperty( v_lov_name, am.lib().groupName)),new BigDecimal(0)))
{ v_rtncod =  am.lib().getGroupRowCount( am.lib().getLovProperty( v_lov_name, am.lib().groupName));
if (Ops.gt( v_rtncod,new BigDecimal(0)))
{
if ( am.lib().showLov( v_lov_name))
{ v_item_id1 =  am.lib().findItem(Ops.concat(Ops.concat( v_block_name1,"."), v_item_name1));
if ( am.lib().idNull( v_item_id1))
{ am.helpers().plShowUserMessage(Ops.concat(Ops.concat(Ops.concat(Ops.concat("Fatal Error! Please contact XTS support. Error message: Cannot find the item '", v_block_name1),"."), v_item_name1),"'"),null,"WARN");
throw am.lib().getFormTriggerFailure();
}
 am.lib().copy( am.lib().nameIn( p_rtn_item_name1),Ops.concat(Ops.concat( v_block_name1,"."), v_item_name1));
if (Ops.and(Ops.and(!Ops.isNull( v_block_name2),!Ops.isNull( v_block_name2)),!Ops.isNull( p_rtn_item_name2)))
{ v_item_id2 =  am.lib().findItem(Ops.concat(Ops.concat( v_block_name2,"."), v_item_name2));
if ( am.lib().idNull( v_item_id2))
{ am.helpers().plShowUserMessage(Ops.concat(Ops.concat(Ops.concat(Ops.concat("Fatal Error! Please contact XTS support. Error message: Cannot find the item '", v_block_name2),"."), v_item_name2),"'"),null,"WARN");
throw am.lib().getFormTriggerFailure();
}
 am.lib().copy( am.lib().nameIn( p_rtn_item_name2),Ops.concat(Ops.concat( v_block_name2,"."), v_item_name2));
}
if (Ops.and(Ops.and(!Ops.isNull( v_block_name3),!Ops.isNull( v_block_name3)),!Ops.isNull( p_rtn_item_name3)))
{ v_item_id3 =  am.lib().findItem(Ops.concat(Ops.concat( v_block_name3,"."), v_item_name3));
if ( am.lib().idNull( v_item_id3))
{ am.helpers().plShowUserMessage(Ops.concat(Ops.concat(Ops.concat(Ops.concat("Fatal Error! Please contact XTS support. Error message: Cannot find the item '", v_block_name3),"."), v_item_name3),"'"),null,"WARN");
throw am.lib().getFormTriggerFailure();
}
 am.lib().copy( am.lib().nameIn( p_rtn_item_name3),Ops.concat(Ops.concat( v_block_name3,"."), v_item_name3));
}
}
}
else
{ am.helpers().plShowUserMessage( am.helpers().plGetUserMessage("530"));
throw am.lib().getFormTriggerFailure();
}
}
else
{ am.helpers().plShowUserMessage( am.helpers().plGetUserMessage("530"));
throw am.lib().getFormTriggerFailure();
}
}
catch (FormTriggerFailure ex)
{throw am.lib().getFormTriggerFailure();
}
catch (Exception ex)
{throw am.lib().getFormTriggerFailure();
}
}

public void checkPackageFailure() {
if (! am.lib().formSuccess)
{throw am.lib().getFormTriggerFailure();
}
}

public void plChkSite() {
BigDecimal  v_prms_runnum = new BigDecimal(0);
{
DP_MIG_XNSUPR.PL_CHK_SITE1_results  result = DP_MIG_XNSUPR.PL_CHK_SITE1(am.getDBTransaction());
 v_prms_runnum = result.getO_V_PRMS_RUNNUM();
}
if (Ops.ge( v_prms_runnum,new BigDecimal(500)))
{ //am.helpers().plShowUserMessage( am.helpers().plGetUserMessage("2399"));
 am.lib().exitForm( am.lib().noCommit);
}
}


public void plBlk4PopUp(String  p_msg){
    plBlk4PopUp(p_msg,"Remark");
}

public void plBlk4PopUp(String  p_msg,String  p_win_title/*TODO:Handle Default Value: "Remark"*/) 
{
try {
 am.lib().setWindowProperty("win_pop_up", am.lib().title, p_win_title);
 am.getBlk4PopUpVORow().setPrevBlock_noVal( am.getSystemVORow().getCurrentBlock());
 am.getBlk4PopUpVORow().setMsg_noVal( p_msg);
}
catch (Exception ex)
{ 
    am.helpers().plShowUserMessage(Ops.concat("Fatal error! Please contact XTS support. Error message: ", am.lib().sqlerrm),"WARN");
throw am.lib().getFormTriggerFailure();
}
}




public void plBlk1Find() {
 am.lib().goBlock("blk2_criteria");
}

public void plSetBrowseMode() {
try {
if (Ops.eq( am.lib().upper( am.getParamVORow().getPMode()),"APPROVE"))
{ am.lib().setItemProperty("blk1_control.blk1_insert", am.lib().label,"Confirm");
 am.lib().setItemProperty("blk1_control.blk1_resubmit", am.lib().enabled, am.lib().propertyFalse);
}
else
{ am.lib().setItemProperty("blk1_control.blk1_resubmit", am.lib().enabled, am.lib().propertyTrue);
}
}
catch (FormTriggerFailure ex)
{throw am.lib().getFormTriggerFailure();
}
catch (Exception ex)
{ am.helpers().plShowUserMessage(Ops.concat("Fatal error! Please contact XTS support. Error message: ", am.lib().sqlerrm),"WARN");
throw am.lib().getFormTriggerFailure();
}
}

public Boolean plBlk1ApproveRec() 
{
     Boolean flag = false;
    
try {
    
 am.lib().goBlock( am.getWorkItemVORow().getWMainBlk());
 am.lib().executeTrigger("when-mouse-click");
    

BigDecimal  v_count = new BigDecimal(0);
 
DP_MIG_XNSUPR.PL_BLK1_APPROVE_REC1_results  result = DP_MIG_XNSUPR.PL_BLK1_APPROVE_REC1(am.getDBTransaction(), am.getBlk1TmpPrfNewcodVORow().getMppfcRunnum());
 v_count = result.getO_V_COUNT();
 
if (Ops.eq(v_count,new BigDecimal(0)))
{ 
   
    flag = true;
    am.helpers().showAlertError(am.helpers().plGetUserMessage("2337"));
    return flag;
}

if (Ops.isNull(DP_EMAIL.GET_EMAIL_ADDR(am.getDBTransaction(), am.getBlk1TmpPrfNewcodVORow().getMppfcSbmby())))
{
   // am.helpers().plShowUserMessage( am.helpers().plGetUserMessage("2336"));
   flag = true;
   am.helpers().showAlertInfo(am.helpers().plGetUserMessage("2336"));
    //am.getWorkItemVORow().setWErrmsg(am.helpers().plGetUserMessage("2336"));
   //throw am.lib().getFormTriggerFailure();
    return flag;
}
    
  flag =  am.helpers().plLockRecord();
   
   // Start : Code added by Rahul 
    if(!flag) 
    {
        executeVOs();
    }
    else
    {
        am.helpers().showAlertInfo(am.helpers().plGetUserMessage("1083"));
        return flag;
    }
    // End
   
    System.out.println("submethod of main loop");
     
}

   
   
 catch (FormTriggerFailure ex)
{
   
    throw am.lib().getFormTriggerFailure();
}
catch (Exception ex)
{ 
    
    am.helpers().plShowUserMessage(Ops.concat("Fatal error! Please contact XTS support. Error message: ", am.lib().sqlerrm),"WARN");
   throw am.lib().getFormTriggerFailure();
}

return flag;
    

}
public void executeVOs()
{
    //am.lib().setBlockProperty("blk3_approve_2", am.lib().orderBy,"mppfs_scr desc");
    am.lib().goBlock("blk3_approve_2");
    am.lib().clearBlock( am.lib().noValidate);
    am.lib().goBlock("blk3_approve_2_1");
    am.lib().clearBlock( am.lib().noValidate);
    am.lib().goBlock("blk3_approve_3");
    am.lib().clearBlock( am.lib().noValidate);
    am.lib().goBlock("blk3_approve_3_1");
    am.lib().clearBlock( am.lib().noValidate);
    am.lib().goBlock("blk3_approve_4");
    am.lib().clearBlock( am.lib().noValidate);
    am.lib().goBlock("blk_consubrol");
    am.lib().executeQuery();
    am.lib().goBlock("blk3_approve_5");
    am.lib().clearBlock( am.lib().noValidate);
    am.lib().goBlock("blk3_approve_1");
    am.lib().clearBlock( am.lib().noValidate);
    am.lib().executeQuery(); // I added
}

    public void plShowUserMessage(String  p_message) 
    {
        plShowUserMessage(p_message,null);
    }
    public void plShowUserMessage(String  p_message,String  p_title) {
        plShowUserMessage(p_message,p_title,"NOTE");
    }

public void plShowUserMessage(String  p_message,String  p_title/*TODO:Handle Default Value: null*/,String  p_msgtyp/*TODO:Handle Default Value: "NOTE"*/) {
String  v_message =  am.lib().substr( p_message,new BigDecimal(1),new BigDecimal(250));
String  v_msgtyp =  p_msgtyp;
BigDecimal  v_status =  new BigDecimal(0);
try {
if (Ops.eq( am.lib().upper( v_msgtyp),"NOTE"))
{ am.lib().setAlertProperty("AL_STD_NOTE", am.lib().title, am.lib().nvl( p_title,"Information"));
 am.lib().setAlertProperty("AL_STD_NOTE", am.lib().alertMessageText, v_message);
 v_status =  am.lib().showAlert("AL_STD_NOTE");
}
else
{
if(Ops.eq( am.lib().upper( v_msgtyp),"CAUTION"))
{ am.lib().setAlertProperty("AL_STD_CAUTION", am.lib().title, am.lib().nvl( p_title,"Caution"));
 am.lib().setAlertProperty("AL_STD_CAUTION", am.lib().alertMessageText, v_message);
 v_status =  am.lib().showAlert("AL_STD_CAUTION");
}
else
{
if(Ops.eq( am.lib().upper( v_msgtyp),"WARN"))
{ am.lib().setAlertProperty("AL_STD_WARN", am.lib().title, am.lib().nvl( p_title,"Warning"));
 am.lib().setAlertProperty("AL_STD_WARN", am.lib().alertMessageText, v_message);
 v_status =  am.lib().showAlert("AL_STD_WARN");
}
else
{ am.lib().setAlertProperty("AL_STD_CAUTION", am.lib().title, am.lib().nvl( p_title,"Information"));
 am.lib().setAlertProperty("AL_STD_CAUTION", am.lib().alertMessageText, v_message);
 v_status =  am.lib().showAlert("AL_STD_CAUTION");
}
}
}
}
catch (FormTriggerFailure ex)
{throw am.lib().getFormTriggerFailure();
}
catch (Exception ex)
{ am.lib().plShowUserMessage(Ops.concat("Fatal error! Please contact XTS support. Error message: ", am.lib().sqlerrm),"WARN");
throw am.lib().getFormTriggerFailure();
}
}

public Boolean plLockRecord() 

{
    Boolean flag  = false;
BigDecimal  v_mppfc_runnum =  new BigDecimal(0);
try 
{

DP_MIG_XNSUPR.PL_LOCK_RECORD1_results  result = DP_MIG_XNSUPR.PL_LOCK_RECORD1(am.getDBTransaction(), am.getBlk1TmpPrfNewcodVORow().getMppfcRunnum());
 v_mppfc_runnum = result.getO_V_MPPFC_RUNNUM();
    
    System.out.println("Value of v_mppfc_runnum :"+v_mppfc_runnum);
  
}

catch (FormTriggerFailure ex)
{
    throw am.lib().getFormTriggerFailure();
}
catch (Exception ex)
{ 
    flag = true;
  

      // Error Message
    
    return flag;
   
 
}


return flag;
}

public void plSndRejectLetter() 
{
String  v_email_subject =  null;
String  v_email_content =  null;
String  v_consubrol_content = null;
try {
 v_email_subject =  DP_EMAIL.GET_EM_SBJ(am.getDBTransaction(),"D", am.getGlobalVORow().getGDivcod(),"SPNEWREJT");
 v_email_content =  DP_EMAIL.GET_EM_CNT(am.getDBTransaction(),"D", am.getGlobalVORow().getGDivcod(),"SPNEWREJT");
 v_email_subject =  am.lib().replace( v_email_subject,"##F01##", am.getBlk3Approve1VORow().getMppfcFulnama1());
 v_email_content =  am.lib().replace( v_email_content,"##F01##", am.getBlk3Approve1VORow().getMppfcSbmid());
 v_email_content =  am.lib().replace( v_email_content,"##F11##", am.getBlk3Approve1VORow().getMppfcFulnama1());
 v_email_content =  am.lib().replace( v_email_content,"##F12##", am.getBlk3Approve1VORow().getMppfcSup());
 v_email_content =  am.lib().replace( v_email_content,"##F13##", am.getBlk3Approve1VORow().getMppfcShp());
 v_email_content =  am.lib().replace( v_email_content,"##F14##", am.getBlk3Approve1VORow().getMppfcMan());
 v_email_content =  am.lib().replace( v_email_content,"##F32##", am.getBlk3Approve1VORow().getMppfcMat());
if (Ops.eq( am.lib().nvl( am.getBlk3Approve1VORow().getMppfcCon(),"N"),"N"))
{ v_consubrol_content = "N";
}
else
{ v_consubrol_content =  DP_EPM_CON.FL_GET_CONSUBROLNAM(am.getDBTransaction(), am.getBlk3Approve1VORow().getMppfcConsubrol());
}
 v_email_content =  am.lib().replace( v_email_content,"##F33##", v_consubrol_content);
 v_email_content =  am.lib().replace( v_email_content,"##F15##", am.getBlk3Approve1VORow().getMppfcSbmby());
// v_email_content =  am.lib().replace( v_email_content,"##F16##", am.lib().toChar( am.getBlk3Approve1VORow().getMppfcSbmdata1(),"DD-MON-YYYY HH:MI:SSAM"));
 v_email_content =  am.lib().replace( v_email_content,"##F16##", am.lib().toChar( am.getBlk3Approve1VORow().getMppfcSbmdata1(),"YYYY-MM-DD HH:MM:SS"));
if (!Ops.isNull( am.getBlk3Approve1VORow().getMppfcAdr1()))
{String  v_adr =  null;
if (!Ops.isNull( am.getBlk3Approve1VORow().getMppfcAdr1()))
{ v_adr = Ops.concat(Ops.concat( v_adr, am.getBlk3Approve1VORow().getMppfcAdr1()), am.lib().chr(new BigDecimal(10)));
}
if (!Ops.isNull( am.getBlk3Approve1VORow().getMppfcAdr2()))
{ v_adr = Ops.concat(Ops.concat( v_adr, am.getBlk3Approve1VORow().getMppfcAdr2()), am.lib().chr(new BigDecimal(10)));
}
if (!Ops.isNull( am.getBlk3Approve1VORow().getMppfcAdr3()))
{ v_adr = Ops.concat(Ops.concat( v_adr, am.getBlk3Approve1VORow().getMppfcAdr3()), am.lib().chr(new BigDecimal(10)));
}
if (!Ops.isNull( am.getBlk3Approve1VORow().getMppfcAdr4()))
{ v_adr = Ops.concat(Ops.concat( v_adr, am.getBlk3Approve1VORow().getMppfcAdr4()), am.lib().chr(new BigDecimal(10)));
}
 v_email_content =  am.lib().replace( v_email_content,"##F17##", v_adr);
}
else
{ v_email_content =  am.lib().replace( v_email_content,"##F17##",Ops.concat("N/A", am.lib().chr(new BigDecimal(10))));
}
 v_email_content =  am.lib().replace( v_email_content,"##F31##", am.getBlk3Approve5VORow().getDeclineReason());
 v_email_content =  am.lib().replace( v_email_content,"##F21##", am.getGlobalVORow().getGUserid());
 v_email_content =  am.lib().replace( v_email_content,"##F22##", am.lib().toChar( am.lib().sysdate,"YYYY-MM-DD HH:MM:SS"));
if (! DP_EMAIL.FMT_EM_TO_EM_MST(am.getDBTransaction(), am.getGlobalVORow().getGDivcod(), am.getBlk3Approve1VORow().getMppfcSbmby(),null, v_email_subject, v_email_content))
{ am.helpers().plShowUserMessage(Ops.concat("Fatal error! Please contact XTS support. Error message: ", am.lib().sqlerrm),"WARN");
throw am.lib().getFormTriggerFailure();
}
}
catch (FormTriggerFailure ex)
{throw am.lib().getFormTriggerFailure();
}
catch (Exception ex)
{
    ex.printStackTrace();
    am.helpers().plShowUserMessage(Ops.concat("Fatal error! Please contact XTS support. Error message: ", am.lib().sqlerrm),"WARN");
throw am.lib().getFormTriggerFailure();
}
}

public void plSndAppvLetter() {
String  v_email_subject =  null;
String  v_email_content =  null;
String  v_consubrol_content = null;
try {
    
 v_email_subject =  DP_EMAIL.GET_EM_SBJ(am.getDBTransaction(),"D", am.getGlobalVORow().getGDivcod(),"SPNEWCONF");
 v_email_content =  DP_EMAIL.GET_EM_CNT(am.getDBTransaction(),"D", am.getGlobalVORow().getGDivcod(),"SPNEWCONF");
 v_email_subject =  am.lib().replace( v_email_subject,"##F01##", am.getBlk3Approve4VORow().getMppfcFulnama4());
 v_email_subject =  am.lib().replace( v_email_subject,"##F02##", am.getBlk3Approve4VORow().getMppfcPrfcod());
 v_email_content =  am.lib().replace( v_email_content,"##F01##", am.getBlk3Approve1VORow().getMppfcSbmid());
 v_email_content =  am.lib().replace( v_email_content,"##F11##", am.getBlk3Approve1VORow().getMppfcFulnama1());
    
 v_email_content =  am.lib().replace( v_email_content,"##F12##", am.getBlk3Approve1VORow().getMppfcSup());
 v_email_content =  am.lib().replace( v_email_content,"##F13##", am.getBlk3Approve1VORow().getMppfcShp());
 v_email_content =  am.lib().replace( v_email_content,"##F14##", am.getBlk3Approve1VORow().getMppfcMan());
 v_email_content =  am.lib().replace( v_email_content,"##F32##", am.getBlk3Approve1VORow().getMppfcMat());
    
if (Ops.eq( am.lib().nvl( am.getBlk3Approve1VORow().getMppfcCon(),"N"),"N"))
{
    v_consubrol_content = "N";
}
else
{ v_consubrol_content =  DP_EPM_CON.FL_GET_CONSUBROLNAM(am.getDBTransaction(), am.getBlk3Approve1VORow().getMppfcConsubrol());
}
 v_email_content =  am.lib().replace( v_email_content,"##F34##", v_consubrol_content);
 v_email_content =  am.lib().replace( v_email_content,"##F15##", am.getBlk3Approve1VORow().getMppfcSbmby());
 v_email_content =  am.lib().replace( v_email_content,"##F16##", am.lib().toChar( am.getBlk3Approve1VORow().getMppfcSbmdata1(),"YYYY-MM-DD HH:MM:SS")); // YYYY-MM-DD 
// v_email_content =  am.lib().replace( v_email_content,"##F16##", am.lib().toChar(am.getBlk3Approve1VORow().getMppfcSbmdat(),"DD-MON-YYYY"));
if (!Ops.isNull( am.getBlk3Approve1VORow().getMppfcAdr1()))
{String  v_adr =  null;
if (!Ops.isNull( am.getBlk3Approve1VORow().getMppfcAdr1()))
{ v_adr = Ops.concat(Ops.concat( v_adr, am.getBlk3Approve1VORow().getMppfcAdr1()), am.lib().chr(new BigDecimal(10)));
}
if (!Ops.isNull( am.getBlk3Approve1VORow().getMppfcAdr2()))
{ v_adr = Ops.concat(Ops.concat( v_adr, am.getBlk3Approve1VORow().getMppfcAdr2()), am.lib().chr(new BigDecimal(10)));
}
if (!Ops.isNull( am.getBlk3Approve1VORow().getMppfcAdr3()))
{ v_adr = Ops.concat(Ops.concat( v_adr, am.getBlk3Approve1VORow().getMppfcAdr3()), am.lib().chr(new BigDecimal(10)));
}
if (!Ops.isNull( am.getBlk3Approve1VORow().getMppfcAdr4()))
{ v_adr = Ops.concat(Ops.concat( v_adr, am.getBlk3Approve1VORow().getMppfcAdr4()), am.lib().chr(new BigDecimal(10)));
}
 v_email_content =  am.lib().replace( v_email_content,"##F17##", v_adr);
}
else
{ v_email_content =  am.lib().replace( v_email_content,"##F17##",Ops.concat("N/A", am.lib().chr(new BigDecimal(10))));
}
 v_email_content =  am.lib().replace( v_email_content,"##F21##", am.getBlk3Approve4VORow().getMppfcPrfcod());
 v_email_content =  am.lib().replace( v_email_content,"##F22##", am.getBlk3Approve4VORow().getShortName());
 v_email_content =  am.lib().replace( v_email_content,"##F23##", am.getBlk3Approve4VORow().getMppfcFulnama4());
 v_email_content =  am.lib().replace( v_email_content,"##F24##", am.getBlk3Approve4VORow().getMppfcSup());
 v_email_content =  am.lib().replace( v_email_content,"##F25##", am.getBlk3Approve4VORow().getMppfcShp());
 v_email_content =  am.lib().replace( v_email_content,"##F26##", am.getBlk3Approve4VORow().getMppfcMan());
 v_email_content =  am.lib().replace( v_email_content,"##F33##", am.getBlk3Approve4VORow().getMppfcMat());
if (Ops.eq( am.lib().nvl( am.getBlk3Approve4VORow().getMppfcCon(),"N"),"N"))
{ v_consubrol_content = "N";
}
else
{ v_consubrol_content =  DP_EPM_CON.FL_GET_CONSUBROLNAM(am.getDBTransaction(), am.getBlk3Approve4VORow().getMppfcConsubrol());
}
 v_email_content =  am.lib().replace( v_email_content,"##F35##", v_consubrol_content);
 v_email_content =  am.lib().replace( v_email_content,"##F27##", am.getGlobalVORow().getGUserid());
 v_email_content =  am.lib().replace( v_email_content,"##F28##", am.lib().toChar( am.lib().sysdate,"YYYY-MM-DD HH:MM:SS"));
if (!Ops.isNull( am.getBlk3Approve4VORow().getMppfcAdr1()))
{String  v_adr =  null;
if (!Ops.isNull( am.getBlk3Approve4VORow().getMppfcAdr1()))
{ v_adr = Ops.concat(Ops.concat( v_adr, am.getBlk3Approve4VORow().getMppfcAdr1()), am.lib().chr(new BigDecimal(10)));
}
if (!Ops.isNull( am.getBlk3Approve4VORow().getMppfcAdr2()))
{ v_adr = Ops.concat(Ops.concat( v_adr, am.getBlk3Approve4VORow().getMppfcAdr2()), am.lib().chr(new BigDecimal(10)));
}
if (!Ops.isNull( am.getBlk3Approve4VORow().getMppfcAdr3()))
{ v_adr = Ops.concat(Ops.concat( v_adr, am.getBlk3Approve4VORow().getMppfcAdr3()), am.lib().chr(new BigDecimal(10)));
}
if (!Ops.isNull( am.getBlk3Approve4VORow().getMppfcAdr4()))
{ v_adr = Ops.concat(Ops.concat( v_adr, am.getBlk3Approve4VORow().getMppfcAdr4()), am.lib().chr(new BigDecimal(10)));
}
 v_email_content =  am.lib().replace( v_email_content,"##F29##", v_adr);
}
else
{ v_email_content =  am.lib().replace( v_email_content,"##F29##",Ops.concat("N/A", am.lib().chr(new BigDecimal(10))));
}
if (! DP_EMAIL.FMT_EM_TO_EM_MST(am.getDBTransaction(), am.getGlobalVORow().getGDivcod(), am.getBlk3Approve1VORow().getMppfcSbmby(),null, v_email_subject, v_email_content))
{ am.helpers().plShowUserMessage(Ops.concat("Fatal error! Please contact XTS support. Error message: ", am.lib().sqlerrm),"WARN");
throw am.lib().getFormTriggerFailure();
}
}
catch (FormTriggerFailure ex)
{
    ex.printStackTrace();
    throw am.lib().getFormTriggerFailure();
}
catch (Exception ex)
{ ex.printStackTrace();
    am.helpers().plShowUserMessage(Ops.concat("Fatal error! Please contact XTS support. Error message: ", am.lib().sqlerrm),"WARN");
throw am.lib().getFormTriggerFailure();
}
}

public void plBlk1Delete() {
try {
 am.lib().goBlock( am.getWorkItemVORow().getWMainBlk());
 am.lib().executeTrigger("when-mouse-click");
if (Ops.eq( am.lib().getItemProperty("blk1_control.blk1_delete", am.lib().enabled),"FALSE"))
{ am.helpers().plShowUserMessage( am.helpers().plGetUserMessage("2392"));
throw am.lib().getFormTriggerFailure();
}
BigDecimal  v_count = new BigDecimal(0);
{
DP_MIG_XNSUPR.PL_BLK1_DELETE1_results  result = DP_MIG_XNSUPR.PL_BLK1_DELETE1(am.getDBTransaction(), am.getBlk1TmpPrfNewcodVORow().getMppfcRunnum());
 v_count = result.getO_V_COUNT();
}
if (Ops.eq( v_count,new BigDecimal(0)))
{ am.helpers().plShowUserMessage( am.helpers().plGetUserMessage("2338"));
throw am.lib().getFormTriggerFailure();
}
;
 am.helpers().plLockRecord();
if (Ops.eq( am.lib().fstdConfirmAction("Confirm Delete","Delete highlighted record?"),new BigDecimal(88)))
{try {
{
DP_MIG_XNSUPR.PL_BLK1_DELETE2(am.getDBTransaction(), am.getBlk1TmpPrfNewcodVORow().getMppfcRunnum());
}
 //am.lib().formsDdl("commit");
 am.getDBTransaction().commit();
 am.lib().goItem(Ops.concat( am.getWorkItemVORow().getWControlBlk(),".blk1_refresh"));
 am.lib().executeTrigger("when-button-pressed");
}
catch (Exception ex)
{
    ex.printStackTrace();
     am.helpers().plShowUserMessage( am.helpers().plGetUserMessage("2293"),"Delete failure","WARN");
   //throw am.lib().getFormTriggerFailure();
     return;
}
}
}
catch (FormTriggerFailure ex)
{throw am.lib().getFormTriggerFailure();
}
catch (Exception ex)
{ am.helpers().plShowUserMessage(Ops.concat("Fatal error! Please contact XTS support. Error message: ", am.lib().sqlerrm),"WARN");
throw am.lib().getFormTriggerFailure();
}
}

    public void plEnDisableConsubrolBlock(String  pi_go_block,String  pi_go_item) 
    {

        BigDecimal  v_current_pos = new BigDecimal(1);
        String  v_allow_other_consubrol = "Y";
        String  v_temp_consubrol = null;
        String  v_temp_consubrol_2 = null;
        String  v_skip_flag = "N";
        if (Ops.eq( v_skip_flag,"N"))
        { 
            
            DP_EPM_CON.FL_IS_ALLOW_OTHER_CONSUB_ROLE_results results =
                new DP_EPM_CON.FL_IS_ALLOW_OTHER_CONSUB_ROLE_results();
            results =  DP_EPM_CON.FL_IS_ALLOW_OTHER_CONSUB_ROLE(am.getDBTransaction(), 
                                                                am.getBlk3Approve4VORow().getMppfcConsubrol(), 
                                                                v_temp_consubrol);
            v_allow_other_consubrol = results.getDEFAULT_RETURN();
            v_temp_consubrol =results.getDEFAULT_RETURN();
            
         am.lib().goBlock( pi_go_block);
         v_current_pos =  am.getSystemVORow().getCursorRecord();
         am.lib().firstRecord();
            int i=1;
            while (Ops.ne(am.getSystemVORow().getLastRecord(), "TRUE")) {
            // I added the below code
          
                
              String s1 = "BLK_CONSUBROL";
              String attrName = "isConSuballowed";
              String s2 = am.lib().convertCamelCase("MPTM_CHKBOX");
              String name = "";
              String code = "";
              Boolean isConallowed = false;
              String voName = am.lib().convertCamelCase(s1) + "VO";
              ViewObjectImpl vo;
              vo = (ViewObjectImpl) am.lib()
                                      .getGam()
                                      .findViewObject(voName);
              Row r = vo.getCurrentRow();
            System.out.println("Current Value :"+r.getAttribute("MptmVal1"));
            
            // added code ended
          /*  
        if (Ops.or(Ops.or(Ops.or(Ops.or(Ops.or(Ops.and(Ops.eq( v_allow_other_consubrol,"N"),Ops.ne( am.lib().nameIn(Ops.concat( pi_go_block,".MPTM_VAL1")), v_temp_consubrol)),Ops.and(Ops.and(Ops.eq( v_allow_other_consubrol,"Y"),!Ops.isNull( am.getBlk3Approve4VORow().getMppfcConsubrol())),Ops.eq( DP_EPM_CON.FL_IS_ALLOW_OTHER_CONSUB_ROLE(am.getDBTransaction(), am.lib().nameIn(Ops.concat( pi_go_block,".MPTM_VAL1")), v_temp_consubrol_2),"N"))),Ops.and(Ops.and(Ops.eq( am.lib().nameIn(Ops.concat( pi_go_block,".MPTM_CHKBOX")),"N"),Ops.eq( DP_EPM_CON.FL_IS_ALLOW_SUP_ROLE(am.getDBTransaction(), am.lib().nameIn(Ops.concat( pi_go_block,".MPTM_VAL1"))),"N")), am.lib().checkboxChecked("BLK3_APPROVE_4.MPPFC_SUP"))),Ops.and(Ops.and(Ops.eq( am.lib().nameIn(Ops.concat( pi_go_block,".MPTM_CHKBOX")),"N"),Ops.eq( DP_EPM_CON.FL_IS_ALLOW_SHP_ROLE(am.getDBTransaction(), am.lib().nameIn(Ops.concat( pi_go_block,".MPTM_VAL1"))),"N")), am.lib().checkboxChecked("BLK3_APPROVE_4.MPPFC_SHP"))),Ops.and(Ops.and(Ops.eq( am.lib().nameIn(Ops.concat( pi_go_block,".MPTM_CHKBOX")),"N"),Ops.eq( DP_EPM_CON.FL_IS_ALLOW_MAN_ROLE(am.getDBTransaction(), am.lib().nameIn(Ops.concat( pi_go_block,".MPTM_VAL1"))),"N")), am.lib().checkboxChecked("BLK3_APPROVE_4.MPPFC_MAN"))),Ops.and(Ops.and(Ops.eq( am.lib().nameIn(Ops.concat( pi_go_block,".MPTM_CHKBOX")),"N"),Ops.eq( DP_EPM_CON.FL_IS_ALLOW_MAT_ROLE(am.getDBTransaction(), am.lib().nameIn(Ops.concat( pi_go_block,".MPTM_VAL1"))),"N")), am.lib().checkboxChecked("BLK3_APPROVE_4.MPPFC_MAT"))))
        { am.lib().setItemInstanceProperty(Ops.concat( pi_go_block,".MPTM_CHKBOX"), am.lib().currentRecord, am.lib().updateAllowed, am.lib().propertyFalse);
         am.lib().setItemInstanceProperty(Ops.concat( pi_go_block,".MPTM_CHKBOX"), am.lib().currentRecord, am.lib().navigable, am.lib().propertyTrue);
         am.lib().setItemInstanceProperty(Ops.concat( pi_go_block,".MPTM_CHKBOX"), am.lib().currentRecord, am.lib().visualAttribute,"VA_READ_ONLY");
        }
        else
        { am.lib().setItemInstanceProperty(Ops.concat( pi_go_block,".MPTM_CHKBOX"), am.lib().currentRecord, am.lib().updateAllowed, am.lib().propertyTrue);
         am.lib().setItemInstanceProperty(Ops.concat( pi_go_block,".MPTM_CHKBOX"), am.lib().currentRecord, am.lib().navigable, am.lib().propertyTrue);
         am.lib().setItemInstanceProperty(Ops.concat( pi_go_block,".MPTM_CHKBOX"), am.lib().currentRecord, am.lib().visualAttribute,"VA_EDIT_ONLY");
        }
        if (Ops.eq( am.getSystemVORow().getLastRecord(),"TRUE")) break;
         am.lib().nextRecord();s
        }
         am.lib().goRecord( v_current_pos);
        }
         am.lib().goItem( pi_go_item); 
        */
            
          boolean cond1 =
              Ops.and(Ops.eq(v_allow_other_consubrol, "N"),
                      Ops.ne(am.getBlkConsubrolVORow().getMptmVal1(), v_temp_consubrol));
          
          DP_EPM_CON.FL_IS_ALLOW_OTHER_CONSUB_ROLE_results results1 =
              new DP_EPM_CON.FL_IS_ALLOW_OTHER_CONSUB_ROLE_results();
          results1 =
          DP_EPM_CON.FL_IS_ALLOW_OTHER_CONSUB_ROLE(am.getDBTransaction(),am.getBlkConsubrolVORow().getMptmVal1(),
                                                                                      v_temp_consubrol_2);
          v_temp_consubrol_2=results1.getDEFAULT_RETURN();
          
          boolean cond2 =
              Ops.and(Ops.and(Ops.eq(v_allow_other_consubrol, "Y"),
                              !Ops.isNull(am.getBlk3Approve4VORow().getMppfcConsubrol())),
                      Ops.eq(  v_temp_consubrol_2, "N"));
          
          boolean cond3 =
              Ops.and(Ops.and(Ops.eq(am.lib().nameIn(Ops.concat(pi_go_block, ".MPTM_CHKBOX")), "N"),
                              Ops.eq(DP_EPM_CON.FL_IS_ALLOW_SUP_ROLE(am.getDBTransaction(),
                                                                    am.getBlkConsubrolVORow().getMptmVal1()), "N")),
                      am.lib().checkboxChecked(am.getBlk3Approve4VORow().getMppfcSup()));
          boolean cond4 =
              Ops.and(Ops.and(Ops.eq(am.lib().nameIn(Ops.concat(pi_go_block, ".MPTM_CHKBOX")), "N"),
                              Ops.eq(DP_EPM_CON.FL_IS_ALLOW_SHP_ROLE(am.getDBTransaction(),
                                                                     am.getBlkConsubrolVORow().getMptmVal1()), "N")),
                      am.lib().checkboxChecked(am.getBlk3Approve4VORow().getMppfcShp()));

          boolean cond5 =
              Ops.and(Ops.and(Ops.eq(am.lib().nameIn(Ops.concat(pi_go_block, ".MPTM_CHKBOX")), "N"),
                              Ops.eq(DP_EPM_CON.FL_IS_ALLOW_MAN_ROLE(am.getDBTransaction(),
                                                                     am.getBlkConsubrolVORow().getMptmVal1()), "N")),
                      am.lib().checkboxChecked(am.getBlk3Approve4VORow().getMppfcMan()));
          boolean cond6 =
              Ops.and(Ops.and(Ops.eq(am.lib().nameIn(Ops.concat(pi_go_block, ".MPTM_CHKBOX")), "N"),
                              Ops.eq(DP_EPM_CON.FL_IS_ALLOW_MAT_ROLE(am.getDBTransaction(),
                                                                     am.getBlkConsubrolVORow().getMptmVal1()), "N")),
                      am.lib().checkboxChecked(am.getBlk3Approve4VORow().getMppfcMat()));
          
          //                if(Ops.eq(am.getBlkConsubrolVORow().getMptmVal1(),"H") || Ops.eq(am.getBlkConsubrolVORow().getMptmVal1(),"C") ) {
          //                    if(Ops.eq(r.getAttribute("MptmVal1"),"M") || Ops.eq(r.getAttribute("MptmVal1"),"T") ) {
          //                        cond1=true;
          //                    }
          //
          //                }
           // Began :Added     
          String Des = r.getAttribute("MptmDes").toString();
          System.out.println("Selected Des =="+Des);
          String chkbox = (String)r.getAttribute("MptmChkbox");
          System.out.println("Selected chkbox =="+chkbox);
           // End : 
                
          if (cond1 || cond2 || cond3 || cond4 || cond5 || cond6) {
              Boolean FalseFlag = false;
          //Began : Added    
          if( ((Des.equalsIgnoreCase("Mill")) && (chkbox.equals("Y")) ) || 
             ((Des.equalsIgnoreCase("Test Lab")) && (chkbox.equals("Y"))) 
             )
         {
             FalseFlag = true;
         } 
          // End
             
              r.setAttribute(attrName, FalseFlag);
          } else {
              Boolean TrueFlag = true;
              r.setAttribute(attrName, TrueFlag);
              
              
          }
        if (Ops.eq(am.getSystemVORow().getLastRecord(), "TRUE"))
            break;
        am.lib().nextRecord();
        }
        am.lib().goRecord(v_current_pos);
        
        }
        am.lib().goItem(pi_go_item);
          

          //                 if (Ops.or(Ops.or(Ops.or(Ops.or(Ops.or(Ops.and(Ops.eq(v_allow_other_consubrol, "N"),
          //                                                               Ops.ne(am.lib()
          //                                                                      .nameIn(Ops.concat(pi_go_block, ".MPTM_VAL1")),
          //                                                                      v_temp_consubrol)),
          //                                                       Ops.and(Ops.and(Ops.eq(v_allow_other_consubrol, "Y"),
          //                                                                       !Ops.isNull(am.getBlk3Insert1VORow()
          //                                                                                   .getNewConsubrol())),
          //                                                               Ops.eq(DP_EPM_CON.FL_IS_ALLOW_OTHER_CONSUB_ROLE(am.getDBTransaction(),
          //                                                                                                               am.lib()
          //                                                                                                               .nameIn(Ops.concat(pi_go_block,
          //                                                                                                                                  ".MPTM_VAL1")),
          //                                                                                                               v_temp_consubrol_2),
          //                                                                      "N"))),
          //                                                Ops.and(Ops.and(Ops.eq(am.lib()
          //                                                                       .nameIn(Ops.concat(pi_go_block, ".MPTM_CHKBOX")),
          //                                                                       "N"),
          //                                                                Ops.eq(DP_EPM_CON.FL_IS_ALLOW_SUP_ROLE(am.getDBTransaction(),
          //                                                                                                       am.lib()
          //                                                                                                       .nameIn(Ops.concat(pi_go_block,
          //                                                                                                                          ".MPTM_VAL1"))),
          //                                                                       "N")),
          //                                                        am.lib()
          //                                                        .checkboxChecked(am.getBlk3Insert1VORow().getNewRoleSupp()))),
          //                                         //, am.lib().checkboxChecked("blk3_insert_1.new_role_supp"))),
          //                                         Ops.and(Ops.and(Ops.eq(am.lib()
          //                                                                .nameIn(Ops.concat(pi_go_block, ".MPTM_CHKBOX")), "N"),
          //                                                         Ops.eq(DP_EPM_CON.FL_IS_ALLOW_SHP_ROLE(am.getDBTransaction(),
          //                                                                                                am.lib()
          //                                                                                                .nameIn(Ops.concat(pi_go_block,
          //                                                                                                                   ".MPTM_VAL1"))),
          //                                                                "N")),
          //                                                 am.lib().checkboxChecked(am.getBlk3Insert1VORow().getNewRoleShip()))),
          //                                  //am.lib().checkboxChecked("blk3_insert_1.new_role_ship"))),
          //                                  Ops.and(Ops.and(Ops.eq(am.lib().nameIn(Ops.concat(pi_go_block, ".MPTM_CHKBOX")), "N"),
          //                                                  Ops.eq(DP_EPM_CON.FL_IS_ALLOW_MAN_ROLE(am.getDBTransaction(),
          //                                                                                         am.lib()
          //                                                                                         .nameIn(Ops.concat(pi_go_block,
          //                                                                                                            ".MPTM_VAL1"))),
          //                                                         "N")),
          //                                          am.lib().checkboxChecked(am.getBlk3Insert1VORow().getNewRoleManu()))),
          //                           //am.lib().checkboxChecked("blk3_insert_1.new_role_manu"))),
          //                           Ops.and(Ops.and(Ops.eq(am.lib().nameIn(Ops.concat(pi_go_block, ".MPTM_CHKBOX")), "N"),
          //                                           Ops.eq(DP_EPM_CON.FL_IS_ALLOW_MAT_ROLE(am.getDBTransaction(),
          //                                                                                  am.lib()
          //                                                                                  .nameIn(Ops.concat(pi_go_block,
          //                                                                                                     ".MPTM_VAL1"))),
          //                                                  "N")),
          //                                   am.lib().checkboxChecked(am.getBlk3Insert1VORow().getNewRoleMate()))))
          //                //am.lib().checkboxChecked("blk3_insert_1.new_role_mate"))))
          //                {
          //
          //                    //isConallowed = (Boolean) r.getAttribute("isConSuballowed");
          //                    r.setAttribute(attrName, false);
          //
          //                  //  r.setAttribute("ConsubCheckBox", false);
          //                    //r.setAttribute(s2, "N");
          //                    //am.lib()
          //                        //.setItemInstanceProperty(Ops.concat(pi_go_block, ".MPTM_CHKBOX"), am.lib().currentRecord,
          //                                               //  am.lib().enabled, am.lib().propertyFalse);
          //                    am.lib()
          //                        .setItemInstanceProperty(Ops.concat(pi_go_block, ".MPTM_CHKBOX"), r,
          //                                                 am.lib().enabled, am.lib().propertyFalse);
          //                    am.lib()
          //                        .setItemInstanceProperty(Ops.concat(pi_go_block, ".MPTM_CHKBOX"), am.lib().currentRecord,
          //                                                 am.lib().navigable, am.lib().propertyTrue);
          //                    am.lib()
          //                        .setItemInstanceProperty(Ops.concat(pi_go_block, ".MPTM_CHKBOX"), am.lib().currentRecord,
          //                                                 am.lib().visualAttribute, "VA_READ_ONLY");
          //
          //                } else {
          //
          //                    //isConallowed = (Boolean) r.getAttribute("isConSuballowed");
          //                    r.setAttribute(attrName, true);
          //                    //r.setAttribute(s2, "N");
          //                   // r.setAttribute("ConsubCheckBox", true);
          //
          //
          //                     am.lib() .setItemInstanceProperty(Ops.concat(pi_go_block, ".MPTM_CHKBOX"), r,
          //                                                                         am.lib().enabled, am.lib().propertyTrue);
          //                    am.lib()
          //                        .setItemInstanceProperty(Ops.concat(pi_go_block, ".MPTM_CHKBOX"), am.lib().currentRecord,
          //                                                 am.lib().navigable, am.lib().propertyTrue);
          //                    am.lib()
          //                        .setItemInstanceProperty(Ops.concat(pi_go_block, ".MPTM_CHKBOX"), am.lib().currentRecord,
          //                                                 am.lib().visualAttribute, "VA_EDIT_ONLY");
          //
          //                }
          ////                if( !am.lib().checkboxChecked(am.getBlk3Insert1VORow().getNewRoleSupp()) &&
          ////                     !am.lib().checkboxChecked(am.getBlk3Insert1VORow().getNewRoleShip()) &&
          ////                    !am.lib().checkboxChecked(am.getBlk3Insert1VORow().getNewRoleManu()) &&
          ////                    !am.lib().checkboxChecked(am.getBlk3Insert1VORow().getNewRoleMate()) &&
          ////                Ops.eq(r.getAttribute(s2),"N") ) {
          ////
          ////                    r.setAttribute(attrName, false);
          ////                }
        
            
          
    }

public void plAssignBlk3Approve4() {
try {
 am.getBlk3Approve4VORow().setMppfcPrfcod_noVal( am.getWorkItemVORow().getWNewPrfcod());
 am.getBlk3Approve4VORow().setMppfcFulnam_noVal( am.getBlk1TmpPrfNewcodVORow().getMppfcFulnam());
 am.getBlk3Approve4VORow().setMppfcFulnamLocal_noVal( am.getBlk1TmpPrfNewcodVORow().getMppfcFulnamLocal());
    
 am.getBlk3Approve4VORow().setMppfcSup_noVal( am.getBlk1TmpPrfNewcodVORow().getMppfcSup());
 am.getBlk3Approve4VORow().setMppfcShp_noVal( am.getBlk1TmpPrfNewcodVORow().getMppfcShp());
 am.getBlk3Approve4VORow().setMppfcMan_noVal( am.getBlk1TmpPrfNewcodVORow().getMppfcMan());
 am.getBlk3Approve4VORow().setMppfcMat_noVal( am.getBlk1TmpPrfNewcodVORow().getMppfcMat());
 am.getBlk3Approve4VORow().setMppfcCon_noVal( am.getBlk1TmpPrfNewcodVORow().getMppfcCon());
 
    
    System.out.println("Value of SUupplier =="+am.getBlk3Approve4VORow().getMppfcSup());
    System.out.println("Value of Shipper =="+am.getBlk3Approve4VORow().getMppfcShp());
    System.out.println("Value of Manufac =="+am.getBlk3Approve4VORow().getMppfcMan());
    System.out.println("Value of Material =="+am.getBlk3Approve4VORow().getMppfcMat());
    System.out.println("Value of Consoder =="+am.getBlk3Approve4VORow().getMppfcCon());
    
 am.getBlk3Approve4VORow().setMppfcConsubrol_noVal( am.getBlk1TmpPrfNewcodVORow().getMppfcConsubrol());
if (Ops.isNull( am.getBlk3Approve4VORow().getMppfcConsubrol()))
{ 
    am.getBlk3Approve4VORow().setMppfcConsubrolDesc_noVal("");
}
else
{ 
    am.getBlk3Approve4VORow().setMppfcConsubrolDesc_noVal(Ops.concat(" - ", DP_EPM_CON.FL_GET_CONSUBROLNAM(am.getDBTransaction(), am.getBlk3Approve4VORow().getMppfcConsubrol(),new BigDecimal(55).toString())));
}
 am.helpers().plEnDisableSupplierRole();
 am.getBlk3Approve4VORow().setMppfcAdr1_noVal( am.getBlk1TmpPrfNewcodVORow().getMppfcAdr1());
 am.getBlk3Approve4VORow().setMppfcAdr2_noVal( am.getBlk1TmpPrfNewcodVORow().getMppfcAdr2());
 am.getBlk3Approve4VORow().setMppfcAdr3_noVal( am.getBlk1TmpPrfNewcodVORow().getMppfcAdr3());
 am.getBlk3Approve4VORow().setMppfcAdr4_noVal( am.getBlk1TmpPrfNewcodVORow().getMppfcAdr4());
 am.getBlk3Approve4VORow().setMppfcAdr1Local_noVal( am.getBlk1TmpPrfNewcodVORow().getMppfcAdr1Local());
 am.getBlk3Approve4VORow().setMppfcAdr2Local_noVal( am.getBlk1TmpPrfNewcodVORow().getMppfcAdr2Local());
 am.getBlk3Approve4VORow().setMppfcAdr3Local_noVal( am.getBlk1TmpPrfNewcodVORow().getMppfcAdr3Local());
 am.getBlk3Approve4VORow().setMppfcAdr4Local_noVal( am.getBlk1TmpPrfNewcodVORow().getMppfcAdr4Local());
 am.getBlk3Approve4VORow().setMppfcGrp_noVal( am.getBlk1TmpPrfNewcodVORow().getMppfcGrpDesc());
 am.getBlk3Approve4VORow().setMppfcSbmby_noVal( am.getBlk1TmpPrfNewcodVORow().getMppfcSbmby());
 am.getBlk3Approve4VORow().setMppfcSbmdat_noVal( am.getBlk1TmpPrfNewcodVORow().getMppfcSbmdat());
    
    if( am.getBlk1TmpPrfNewcodVORow().getMppfcRmk() != null)
    {
       int number = 10;
       char c = (char) number;
       String ctemp = "" + c;
       String Remarks = am.getBlk1TmpPrfNewcodVORow().getMppfcRmk();
       
     
       System.out.println("Remarks Values is === "+Remarks);
       
    int i = am.getBlk1TmpPrfNewcodVORow().getMppfcRmk().lastIndexOf(ctemp);

       am.getBlk3Approve4VORow().setMppfcRmk_noVal(
                                                  am.lib().substr(am.getBlk1TmpPrfNewcodVORow().getMppfcRmk(),
                                                  new BigDecimal(i+1)                                             
                                               
                                                  ));
       
    }
    else 
    { 
        am.getBlk3Approve4VORow().setMppfcRmk_noVal("");
        }
    
    
//    int number = 10;
//    char c = (char) number;
//    String ctemp = "" + c;
// am.getBlk3Approve4VORow().setMppfcRmk_noVal(am.lib().substr( am.getBlk1TmpPrfNewcodVORow().getMppfcRmk(),
//                                   Ops.plus(am.lib().instr(am.getBlk1TmpPrfNewcodVORow().getMppfcRmk(),
//                                                           ctemp),new BigDecimal(-1)),new BigDecimal(1)));
    
    //am.getBlk3Approve4VORow().setMppfcRmk_noVal(am.getBlk1TmpPrfNewcodVORow().getMppfcRmk()); // I added

 
 am.getBlk3Approve4VORow().setMppfcCtycod_noVal( am.getBlk1TmpPrfNewcodVORow().getMppfcCtycod());
 am.getBlk3Approve4VORow().setMppfcLoccod_noVal( am.getBlk1TmpPrfNewcodVORow().getMppfcLoccod());
 am.getBlk3Approve4VORow().setCtycodDes_noVal( am.helpers().flCtydesc( am.getBlk3Approve4VORow().getMppfcCtycod()));
 am.getBlk3Approve4VORow().setLocDes_noVal( am.helpers().flLocdesc( am.getBlk3Approve4VORow().getMppfcLoccod(), am.getBlk3Approve4VORow().getMppfcCtycod()));
BigDecimal  v_count =  new BigDecimal(0);
{
DP_MIG_XNSUPR.PL_ASSIGN_BLK3_APPROVE_41_results  result = DP_MIG_XNSUPR.PL_ASSIGN_BLK3_APPROVE_41(am.getDBTransaction(), am.getBlk3Approve4VORow().getMppfcFulnama4());
 v_count = result.getO_V_COUNT();
}
if (Ops.eq( v_count,new BigDecimal(0)))
{ 
    am.getBlk3Approve4VORow().setShortName_noVal(am.lib().substr(am.getBlk3Approve4VORow().getMppfcFulnama4(),
                                                                 new BigDecimal(0),
                                                                 new BigDecimal(15)));
}
else
{ am.getBlk3Approve4VORow().setShortName_noVal("");
}
;
try {
{
DP_MIG_XNSUPR.PL_ASSIGN_BLK3_APPROVE_42_results  result = DP_MIG_XNSUPR.PL_ASSIGN_BLK3_APPROVE_42(am.getDBTransaction(), am.getBlk3Approve4VORow().getMppfcSbmby());
 am.getBlk3Approve4VORow().setMppfcSbmbyFulnam_noVal(result.getO_MPPFC_SBMBY_FULNAM());
}
}
catch (Exception ex)
{ am.getBlk3Approve4VORow().setMppfcSbmbyFulnam_noVal("");
}
try {
{
DP_MIG_XNSUPR.PL_ASSIGN_BLK3_APPROVE_43_results  result = DP_MIG_XNSUPR.PL_ASSIGN_BLK3_APPROVE_43(am.getDBTransaction(), am.getBlk3Approve4VORow().getMppfcSbmby());
 am.getBlk3Approve4VORow().setMppfcSbmbyDivision_noVal(result.getO_MPPFC_SBMBY_DIVISION());
}
}
catch (Exception ex)
{ am.getBlk3Approve4VORow().setMppfcSbmbyDivision_noVal("");
}
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

public void plEnDisableSupplierRole()
{
String  v_cursor_item =  am.getSystemVORow().getCursorItem();
//if ( am.lib().checkboxChecked("BLK3_APPROVE_4.MPPFC_CON"))
if(am.lib().checkboxChecked(am.getBlk3Approve4VORow().getMppfcCon()))
{
if (Ops.eq( DP_EPM_CON.FL_IS_ALLOW_SUP_ROLE(am.getDBTransaction(), am.getBlk3Approve4VORow().getMppfcConsubrol()),"N"))
{ 
   // am.lib().setItemProperty("BLK3_APPROVE_4.MPPFC_SUP", am.lib().enabled, am.lib().propertyFalse);
    am.lib().setItemProperty("BLK3_APPROVE_4.MPPFC_SUP", am.lib().enabled, am.lib().propertyTrue);
}
else
{// am.lib().setItemProperty("BLK3_APPROVE_4.MPPFC_SUP", am.lib().enabled, am.lib().propertyTrue);
    am.lib().setItemProperty("BLK3_APPROVE_4.MPPFC_SUP", am.lib().enabled, am.lib().propertyFalse);
}
if (Ops.eq( DP_EPM_CON.FL_IS_ALLOW_SHP_ROLE(am.getDBTransaction(), am.getBlk3Approve4VORow().getMppfcConsubrol()),"N"))
{ 
    //am.lib().setItemProperty("BLK3_APPROVE_4.MPPFC_SHP", am.lib().enabled, am.lib().propertyFalse);
    am.lib().setItemProperty("BLK3_APPROVE_4.MPPFC_SHP", am.lib().enabled, am.lib().propertyTrue);
}
else
{ //am.lib().setItemProperty("BLK3_APPROVE_4.MPPFC_SHP", am.lib().enabled, am.lib().propertyTrue);
    am.lib().setItemProperty("BLK3_APPROVE_4.MPPFC_SHP", am.lib().enabled, am.lib().propertyFalse);
}
if (Ops.eq( DP_EPM_CON.FL_IS_ALLOW_MAN_ROLE(am.getDBTransaction(), am.getBlk3Approve4VORow().getMppfcConsubrol()),"N"))
{// am.lib().setItemProperty("BLK3_APPROVE_4.MPPFC_MAN", am.lib().enabled, am.lib().propertyFalse);
    am.lib().setItemProperty("BLK3_APPROVE_4.MPPFC_MAN", am.lib().enabled, am.lib().propertyTrue);
}
else
{// am.lib().setItemProperty("BLK3_APPROVE_4.MPPFC_MAN", am.lib().enabled, am.lib().propertyTrue);
    am.lib().setItemProperty("BLK3_APPROVE_4.MPPFC_MAN", am.lib().enabled, am.lib().propertyFalse);
}
if (Ops.eq( DP_EPM_CON.FL_IS_ALLOW_MAT_ROLE(am.getDBTransaction(), am.getBlk3Approve4VORow().getMppfcConsubrol()),"N"))
{ //am.lib().setItemProperty("BLK3_APPROVE_4.MPPFC_MAT", am.lib().enabled, am.lib().propertyFalse);
    am.lib().setItemProperty("BLK3_APPROVE_4.MPPFC_MAT", am.lib().enabled, am.lib().propertyTrue);
}
else
{ //am.lib().setItemProperty("BLK3_APPROVE_4.MPPFC_MAT", am.lib().enabled, am.lib().propertyTrue);
    am.lib().setItemProperty("BLK3_APPROVE_4.MPPFC_MAT", am.lib().enabled, am.lib().propertyFalse);
}
 //am.lib().setItemProperty("BLK3_APPROVE_4.MPPFC_CON", am.lib().enabled, am.lib().propertyTrue);
    am.lib().setItemProperty("BLK3_APPROVE_4.MPPFC_CON", am.lib().enabled, am.lib().propertyFalse);
}
else
{ 
    
// am.lib().setItemProperty("BLK3_APPROVE_4.MPPFC_SUP", am.lib().enabled, am.lib().propertyTrue);
// am.lib().setItemProperty("BLK3_APPROVE_4.MPPFC_SHP", am.lib().enabled, am.lib().propertyTrue);
// am.lib().setItemProperty("BLK3_APPROVE_4.MPPFC_MAN", am.lib().enabled, am.lib().propertyTrue);
// am.lib().setItemProperty("BLK3_APPROVE_4.MPPFC_MAT", am.lib().enabled, am.lib().propertyTrue);
// am.lib().setItemProperty("BLK3_APPROVE_4.MPPFC_CON", am.lib().enabled, am.lib().propertyFalse);

    
    am.lib().setItemProperty("BLK3_APPROVE_4.MPPFC_SUP", am.lib().enabled, am.lib().propertyFalse);
    am.lib().setItemProperty("BLK3_APPROVE_4.MPPFC_SHP", am.lib().enabled, am.lib().propertyFalse);
    am.lib().setItemProperty("BLK3_APPROVE_4.MPPFC_MAN", am.lib().enabled, am.lib().propertyFalse);
    am.lib().setItemProperty("BLK3_APPROVE_4.MPPFC_MAT", am.lib().enabled, am.lib().propertyFalse);
    am.lib().setItemProperty("BLK3_APPROVE_4.MPPFC_CON", am.lib().enabled, am.lib().propertyTrue);
}

    am.lib().goItem(v_cursor_item);
}

public void plUntickAllConsubrolBlock(String  pi_go_block,String  pi_go_item) {
 am.lib().goBlock( pi_go_block);
 am.lib().firstRecord();
//while (true) {
// am.lib().copy("N",Ops.concat( pi_go_block,".MPTM_CHKBOX"));
//if (Ops.eq( am.getSystemVORow().getLastRecord(),"TRUE")) break;
// am.lib().nextRecord();
//}
 
 
 while (Ops.ne(am.getSystemVORow().getLastRecord(), "TRUE")) 
 {
     String s1 = "BLK_CONSUBROL";
          String attrName = "ConsubCheckBox";
         
          String s2 = am.lib().convertCamelCase("MPTM_CHKBOX");
     
        
          Boolean isConallowed = false;
          String voName = am.lib().convertCamelCase(s1) + "VO";
          ViewObjectImpl vo;
          vo = (ViewObjectImpl) am.lib()
                                  .getGam()
                                  .findViewObject(voName);
          Row r = vo.getCurrentRow();
     System.out.println(" Before Value of isConallowed"+r.getAttribute(attrName));
     r.setAttribute(attrName, isConallowed);
     System.out.println("After Value of isConallowed"+r.getAttribute(attrName));
     r.setAttribute(s2, "N");
     
     if (Ops.eq(am.getSystemVORow().getLastRecord(), "TRUE"))
         break;
     am.lib().nextRecord();
     
 }
 
 
 
 am.lib().firstRecord();
 am.lib().goItem( pi_go_item);
}

public void queryMasterDetails(Relation  rel_id,String  detail) 
{
String  oldmsg =  null;
String  reldef =  null;
try {
 reldef =  am.lib().getRelationProperty( rel_id, am.lib().deferredCoordination);
 oldmsg =  am.getSystemVORow().getMessageLevel();
if (Ops.eq( reldef,"FALSE"))
{ am.lib().goBlock( detail);
 am.helpers().checkPackageFailure();
 am.getSystemVORow().setMessageLevel_noVal("10");
 am.lib().executeQuery();
 am.getSystemVORow().setMessageLevel_noVal( oldmsg);
}
else
{ am.lib().setBlockProperty( detail, am.lib().coordinationStatus, am.lib().nonCoordinated);
}
}
catch (FormTriggerFailure ex)
{ am.getSystemVORow().setMessageLevel_noVal( oldmsg);
throw am.lib().get();
}
}

public void clearAllMasterDetails() {
String  mastblk =  null;
String  coordop =  null;
String  trigblk =  null;
String  startitm =  null;
String  frmstat =  null;
String  curblk =  "master";
String  currel =  null;
String  curdtl =  null;
String  retblk =  null;
 //String curblk =  "master";
 
 currel =  am.lib().getBlockProperty( curblk, am.lib().firstMasterRelation);
while(!Ops.isNull( currel)) {
 curblk =  am.lib().getRelationProperty( currel, am.lib().detailName);
if (Ops.eq( am.lib().getBlockProperty( curblk, am.lib().status),"CHANGED"))
{
  //  return  curblk;
}
else
{ retblk =  am.lib().firstChangedBlockBelow( curblk);
if (!Ops.isNull( retblk))
{
  //  return  retblk;
}
else
{ currel =  am.lib().getRelationProperty( currel, am.lib().nextMasterRelation);
}
}
}
//return null;
try {
 mastblk =  am.getSystemVORow().getMasterBlock();
 coordop =  am.getSystemVORow().getCoordinationOperation();
 trigblk =  am.getSystemVORow().getTriggerBlock();
 startitm =  am.getSystemVORow().getCursorItem();
 frmstat =  am.getSystemVORow().getFormStatus();
if (!Ops.isIn( coordop,"CLEAR_RECORD","SYNCHRONIZE_BLOCKS"))
{
if (Ops.eq( mastblk, trigblk))
{
if (Ops.eq( frmstat,"CHANGED"))
{ 
    //curblk =  first_changed_block_below(mastblk);
if (!Ops.isNull( curblk))
{ am.lib().goBlock( curblk);
 am.helpers().checkPackageFailure();
 am.lib().clearBlock( am.lib().askCommit);
if (!Ops.or(Ops.eq( am.getSystemVORow().getFormStatus(),"QUERY"),Ops.eq( am.getSystemVORow().getBlockStatus(),"NEW")))
{throw am.lib().getFormTriggerFailure();
}
}
}
}
}
 currel =  am.lib().getBlockProperty( trigblk, am.lib().firstMasterRelation);
while(!Ops.isNull( currel)) 
{
 curdtl =  am.lib().getRelationProperty( currel, am.lib().detailName);
if (Ops.ne( am.lib().getBlockProperty( curdtl, am.lib().status),"NEW"))
{ am.lib().goBlock( curdtl);
 am.helpers().checkPackageFailure();
 am.lib().clearBlock( am.lib().noValidate);
if (Ops.ne( am.getSystemVORow().getBlockStatus(),"NEW"))
{throw am.lib().getFormTriggerFailure();
}
}
 currel =  am.lib().getRelationProperty( currel, am.lib().nextMasterRelation);
}
if (Ops.ne( am.getSystemVORow().getCursorItem(), startitm))
{ am.lib().goItem( startitm);
 am.helpers().checkPackageFailure();
}
}
catch (FormTriggerFailure ex)
{
if (Ops.ne( am.getSystemVORow().getCursorItem(), startitm))
{ am.lib().goItem( startitm);
}
throw am.lib().get();
}
}


public void plInitConsubRoleList() 
 {
//PlInitConsubRoleList_C1SubRoleVOImpl c1_sub_role = am.getPlInitConsubRoleList_C1SubRoleVO();
BigDecimal  i =  new BigDecimal(0);
}

public void first_changed_block_below(String mastblk)
{
  
}
    private String cleanUpSQLString(String input)
    {
        if(input==null || input.isEmpty())
        {
            return input;
        }
        if(input.contains("&")){
            return input.replaceAll("&", "%");
        }
        else{
            return input;
        }
    }
    public void showAlertWarning(String warnMsg) 
    {
        FacesMessage fm = new FacesMessage(warnMsg);
        fm.setSeverity(FacesMessage.SEVERITY_WARN);
        FacesContext fc = FacesContext.getCurrentInstance();
        if (fc != null) {
            fc.addMessage(null, fm);
        }
    }
    public void showAlertInfo(String infoMsg) 
    {
        FacesMessage fm = new FacesMessage(infoMsg);
        fm.setSeverity(FacesMessage.SEVERITY_INFO);
        FacesContext fc = FacesContext.getCurrentInstance();
        if (fc != null) {
            fc.addMessage(null, fm);
        }
    }
    public void showAlertError(String errorMsg) 
    {
        FacesMessage fm = new FacesMessage(errorMsg);
        fm.setSeverity(FacesMessage.SEVERITY_ERROR);
        FacesContext fc = FacesContext.getCurrentInstance();
        if (fc != null) {
            fc.addMessage(null, fm);
        }
    }
    public void dd(){
        BigDecimal  v_current_pos = new BigDecimal(1);
        if(am.getBlk3Approve4VORow().getMppfcConsubrol().contains(",")) 
        {
        StringTokenizer st = new StringTokenizer(am.getBlk3Approve4VORow().getMppfcConsubrol(),",");  
        String tempValue = st.nextToken();
             while (st.hasMoreTokens()) {  
                 System.out.println(st.nextToken());  
               
        am.lib().goBlock("BLK_CONSUBROL");
        v_current_pos =  am.getSystemVORow().getCursorRecord();
        am.lib().firstRecord();
           while (Ops.ne(am.getSystemVORow().getLastRecord(), "TRUE"))
           {
           // I added the below code
            
               
             String s1 = "BLK_CONSUBROL";
             String attrName = "isConSuballowed";
             String s2 = am.lib().convertCamelCase("MPTM_CHKBOX");
             String name = "";
             String code = "";
             Boolean isConallowed = false;
             String voName = am.lib().convertCamelCase(s1) + "VO";
             ViewObjectImpl vo;
             vo = (ViewObjectImpl) am.lib()
                                     .getGam()
                                     .findViewObject(voName);
             Row r = vo.getCurrentRow();
               r.getAttribute("MptmVal1").toString();
//               if(r.getAttribute("MptmVal1").toString()) {
//                 
//                 }
           }
        }
    }

    }
}

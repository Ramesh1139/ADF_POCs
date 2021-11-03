package model.views;
public class Blk3TsiMstVOEvents {
    public Blk3TsiMstVOEvents(Xsim2AmImpl am) {
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

public void prd(){
{
DP_MIG_XSIM2.PL_TSIMST3_PRD1(am.getDBTransaction(), am.getBlk3TsiMstVORow().getSimsSaminvrun(), am.getBlk3TsiMstVORow().getSimsDivcod());
}
{
DP_MIG_XSIM2.PL_TSIMST3_PRD2(am.getDBTransaction(), am.getBlk3TsiMstVORow().getSimsSaminvrun(), am.getBlk3TsiMstVORow().getSimsDivcod());
}
{
DP_MIG_XSIM2.PL_TSIMST3_PRD3(am.getDBTransaction(), am.getBlk3TsiMstVORow().getSimsSaminvrun(), am.getBlk3TsiMstVORow().getSimsDivcod());
}
{
DP_MIG_XSIM2.PL_TSIMST3_PRD4(am.getDBTransaction(), am.getBlk3TsiMstVORow().getSimsSaminvrun(), am.getBlk3TsiMstVORow().getSimsDivcod());
}
{
DP_MIG_XSIM2.PL_TSIMST3_PRD5(am.getDBTransaction(), am.getBlk3TsiMstVORow().getSimsDivcod(), am.getBlk3TsiMstVORow().getSimsSaminvrun());
}
}
public void kf7(){
if (Ops.eq( am.lib().upper( am.lib().nameIn("work_item.w_current_blk")),"BLK3_TSI_MST"))
{ am.lib().goItem("blk4_tsi_des_a.sids_des");
}
}
public void pd(){
String  recstat =  am.getSystemVORow().getRecordStatus();
String  startitm =  am.getSystemVORow().getCursorItem();
Relation  rel_id;
if (Ops.or(Ops.eq( recstat,"NEW"),Ops.eq( recstat,"INSERT")))
{return ;
}
if (Ops.or(!Ops.isNull( am.getBlk3TsiMstVORow().getSimsSaminvrun()),!Ops.isNull( am.getBlk3TsiMstVORow().getSimsDivcod())))
{ rel_id =  am.lib().findRelation("BLK3_TSI_MST.BLK3_TSI_MST_BLK4_TSI_DES_A");
 am.helpers().queryMasterDetails( rel_id,"BLK4_TSI_DES_A");
}
if (Ops.or(!Ops.isNull( am.getBlk3TsiMstVORow().getSimsSaminvrun()),!Ops.isNull( am.getBlk3TsiMstVORow().getSimsDivcod())))
{ rel_id =  am.lib().findRelation("BLK3_TSI_MST.BLK3_TSI_MST_BLK4_TSI_DES_Q");
 am.helpers().queryMasterDetails( rel_id,"BLK4_TSI_DES_Q");
}
if (Ops.or(!Ops.isNull( am.getBlk3TsiMstVORow().getSimsSaminvrun()),!Ops.isNull( am.getBlk3TsiMstVORow().getSimsDivcod())))
{ rel_id =  am.lib().findRelation("BLK3_TSI_MST.BLK3_TSI_MST_BLK4_TSI_DES_B");
 am.helpers().queryMasterDetails( rel_id,"BLK4_TSI_DES_B");
}
if (Ops.or(!Ops.isNull( am.getBlk3TsiMstVORow().getSimsSaminvrun()),!Ops.isNull( am.getBlk3TsiMstVORow().getSimsDivcod())))
{ rel_id =  am.lib().findRelation("BLK3_TSI_MST.BLK3_TSI_MST_BLK4_TSI_DES_D");
 am.helpers().queryMasterDetails( rel_id,"BLK4_TSI_DES_D");
}
if (Ops.or(!Ops.isNull( am.getBlk3TsiMstVORow().getSimsDivcod()),!Ops.isNull( am.getBlk3TsiMstVORow().getSimsSaminvrun())))
{ rel_id =  am.lib().findRelation("BLK3_TSI_MST.BLK3_TSI_MST_BLK5_TSI_ITM");
 am.helpers().queryMasterDetails( rel_id,"BLK5_TSI_ITM");
}
if (Ops.ne( am.getSystemVORow().getCursorItem(), startitm))
{ am.lib().goItem( startitm);
 am.helpers().checkPackageFailure();
}
}
public void prb(){
 am.lib().copy("BLK3_TSI_MST","work_item.w_current_blk");
 am.lib().showView("blk3_can");
 am.lib().showView("info_can");
if (Ops.eq( am.lib().getItemProperty("BLK1_CONTROL.BLK1_EDIT", am.lib().label),"Enquiry"))
{ am.lib().pstdDisableBlock("BLK4_TSI_DES_D");
 am.lib().pstdDisableBlock("BLK4_TSI_DES_B");
 am.lib().pstdDisableBlock("BLK4_TSI_DES_Q");
 am.lib().pstdDisableBlock("BLK4_TSI_DES_A");
}
}
public void pri(){
BigDecimal  flag =  new BigDecimal(0);
BigDecimal  i =  new BigDecimal(0);
String  p_current_item =  null;
 am.getWorkItemVORow().setWErrmsg_noVal("");
if (Ops.isNull( am.lib().nameIn(Ops.concat( am.getWorkItemVORow().getWDetailBlk(),".sims_dptcod"))))
{ am.getWorkItemVORow().setWErrmsg_noVal( am.lib().fstdUsrMsg("817"));
throw am.lib().getFormTriggerFailure();
/* Unreachable Statement in original code
return ;
*/}
if (Ops.eq( CHECK_DPT(am.getDBTransaction(), am.getBlk3TsiMstVORow().getSimsDptcod(), am.getGlobalVORow().getGDivcod(), am.getGlobalVORow().getGUserid()),"false"))
{ am.lib().copy(Ops.concat( am.getWorkItemVORow().getWDetailBlk(),".sims_dptcod"),"work_item.w_erritm");
 am.getWorkItemVORow().setWErrmsg_noVal( am.lib().fstdUsrMsg("986"));
throw am.lib().getFormTriggerFailure();
/* Unreachable Statement in original code
return ;
*/}
{
DP_MIG_XSIM2.PL_TSIMST3_PRI1_results  result = DP_MIG_XSIM2.PL_TSIMST3_PRI1(am.getDBTransaction(), am.getBlk3TsiMstVORow().getSimsDptcod(), am.getGlobalVORow().getGDivcod());
 i = result.getO_I();
}
if (Ops.eq( i,new BigDecimal(0)))
{ am.lib().copy(Ops.concat( am.getWorkItemVORow().getWDetailBlk(),".sims_dptcod"),"work_item.w_erritm");
 am.getWorkItemVORow().setWErrmsg_noVal( am.lib().fstdUsrMsg("986"));
throw am.lib().getFormTriggerFailure();
/* Unreachable Statement in original code
return ;
*/}
{
DP_MIG_XSIM2.PL_TSIMST3_PRI2_results  result = DP_MIG_XSIM2.PL_TSIMST3_PRI2(am.getDBTransaction(), am.getBlk3TsiMstVORow().getSimsCuscod(), am.getGlobalVORow().getGDivcod());
 i = result.getO_I();
}
if (Ops.and(Ops.eq( i,new BigDecimal(0)),!Ops.isNull( am.getBlk3TsiMstVORow().getSimsCuscod())))
{ am.lib().copy(Ops.concat( am.getWorkItemVORow().getWDetailBlk(),".sims_cuscod"),"work_item.w_erritm");
 am.getWorkItemVORow().setWErrmsg_noVal( am.lib().fstdUsrMsg("986"));
throw am.lib().getFormTriggerFailure();
/* Unreachable Statement in original code
return ;
*/}
{
DP_MIG_XSIM2.PL_TSIMST3_PRI3_results  result = DP_MIG_XSIM2.PL_TSIMST3_PRI3(am.getDBTransaction(), am.getBlk3TsiMstVORow().getSimsCuscod(), am.getBlk3TsiMstVORow().getSimsConpercod(), am.getGlobalVORow().getGDivcod());
 i = result.getO_I();
}
if (Ops.and(Ops.eq( i,new BigDecimal(0)),!Ops.isNull( am.getBlk3TsiMstVORow().getSimsConpercod())))
{ am.lib().copy("sims_conpercod","work_item.w_erritm");
 am.getWorkItemVORow().setWErrmsg_noVal( am.lib().fstdUsrMsg("986"));
throw am.lib().getFormTriggerFailure();
/* Unreachable Statement in original code
return ;
*/}
if (Ops.isNull( am.lib().nameIn(Ops.concat( am.getWorkItemVORow().getWDetailBlk(),".sims_invdat"))))
{ am.lib().copy(Ops.concat( am.getWorkItemVORow().getWDetailBlk(),".sims_invdat"),"work_item.w_erritm");
 am.getWorkItemVORow().setWErrmsg_noVal( am.lib().fstdUsrMsg("985"));
throw am.lib().getFormTriggerFailure();
/* Unreachable Statement in original code
return ;
*/}
if (Ops.isNull( am.lib().nameIn(Ops.concat( am.getWorkItemVORow().getWDetailBlk(),".sims_shpdat"))))
{ am.lib().copy("sims_shpdat","work_item.w_erritm");
 am.getWorkItemVORow().setWErrmsg_noVal( am.lib().fstdUsrMsg("985"));
throw am.lib().getFormTriggerFailure();
/* Unreachable Statement in original code
return ;
*/}
{
DP_MIG_XSIM2.PL_TSIMST3_PRI4_results  result = DP_MIG_XSIM2.PL_TSIMST3_PRI4(am.getDBTransaction(), am.getBlk3TsiMstVORow().getSimsPortL());
 i = result.getO_I();
}
if (Ops.and(Ops.eq( i,new BigDecimal(0)),!Ops.isNull( am.getBlk3TsiMstVORow().getSimsPortL())))
{ am.lib().copy("sims_port_l","work_item.w_erritm");
 am.getWorkItemVORow().setWErrmsg_noVal( am.lib().fstdUsrMsg("986"));
throw am.lib().getFormTriggerFailure();
/* Unreachable Statement in original code
return ;
*/}
{
DP_MIG_XSIM2.PL_TSIMST3_PRI5_results  result = DP_MIG_XSIM2.PL_TSIMST3_PRI5(am.getDBTransaction(), am.getBlk3TsiMstVORow().getSimsVia());
 i = result.getO_I();
}
if (Ops.and(Ops.eq( i,new BigDecimal(0)),!Ops.isNull( am.getBlk3TsiMstVORow().getSimsVia())))
{ am.lib().copy("sims_via","work_item.w_erritm");
 am.getWorkItemVORow().setWErrmsg_noVal( am.lib().fstdUsrMsg("986"));
throw am.lib().getFormTriggerFailure();
/* Unreachable Statement in original code
return ;
*/}
{
DP_MIG_XSIM2.PL_TSIMST3_PRI6_results  result = DP_MIG_XSIM2.PL_TSIMST3_PRI6(am.getDBTransaction(), am.getBlk3TsiMstVORow().getSimsPortD());
 i = result.getO_I();
}
if (Ops.and(Ops.eq( i,new BigDecimal(0)),!Ops.isNull( am.getBlk3TsiMstVORow().getSimsPortD())))
{ am.lib().copy("sims_port_d","work_item.w_erritm");
 am.getWorkItemVORow().setWErrmsg_noVal( am.lib().fstdUsrMsg("986"));
throw am.lib().getFormTriggerFailure();
/* Unreachable Statement in original code
return ;
*/}
{
DP_MIG_XSIM2.PL_TSIMST3_PRI7_results  result = DP_MIG_XSIM2.PL_TSIMST3_PRI7(am.getDBTransaction(), am.getBlk3TsiMstVORow().getSimsAdrcod(), am.getBlk3TsiMstVORow().getSimsCuscod(), am.getGlobalVORow().getGDivcod());
 i = result.getO_I();
}
if (Ops.and(Ops.eq( i,new BigDecimal(0)),!Ops.isNull( am.lib().nameIn(Ops.concat( am.getWorkItemVORow().getWDetailBlk(),".sims_adrcod")))))
{ am.lib().copy("sims_adrcod","work_item.w_erritm");
 am.getWorkItemVORow().setWErrmsg_noVal( am.lib().fstdUsrMsg("986"));
throw am.lib().getFormTriggerFailure();
/* Unreachable Statement in original code
return ;
*/}
{
DP_MIG_XSIM2.PL_TSIMST3_PRI8_results  result = DP_MIG_XSIM2.PL_TSIMST3_PRI8(am.getDBTransaction(), am.getBlk3TsiMstVORow().getSimsCarcod());
 i = result.getO_I();
}
if (Ops.and(Ops.eq( i,new BigDecimal(0)),!Ops.isNull( am.getBlk3TsiMstVORow().getSimsCarcod())))
{ am.lib().copy("sims_carcod","work_item.w_erritm");
 am.getWorkItemVORow().setWErrmsg_noVal( am.lib().fstdUsrMsg("986"));
throw am.lib().getFormTriggerFailure();
/* Unreachable Statement in original code
return ;
*/}
{
DP_MIG_XSIM2.PL_TSIMST3_PRI9_results  result = DP_MIG_XSIM2.PL_TSIMST3_PRI9(am.getDBTransaction(), am.getBlk3TsiMstVORow().getSimsShpmodcod());
 i = result.getO_I();
}
if (Ops.and(Ops.eq( i,new BigDecimal(0)),!Ops.isNull( am.getBlk3TsiMstVORow().getSimsShpmodcod())))
{ am.lib().copy(Ops.concat( am.getWorkItemVORow().getWDetailBlk(),".sims_shpmodcod"),"work_item.w_erritm");
 am.getWorkItemVORow().setWErrmsg_noVal( am.lib().fstdUsrMsg("863"));
throw am.lib().getFormTriggerFailure();
/* Unreachable Statement in original code
return ;
*/}
if (Ops.and(!Ops.isNull( am.getBlk3TsiMstVORow().getSimsInvamt()),Ops.isNull( am.getBlk3TsiMstVORow().getSimsCurcod())))
{ am.getWorkItemVORow().setWErritm_noVal("blk3_tsi_mst.sims_curcod");
 am.getWorkItemVORow().setWErrmsg_noVal("Please input currency code");
throw am.lib().getFormTriggerFailure();
/* Unreachable Statement in original code
return ;
*/}
if (Ops.and(Ops.isNull( am.getBlk3TsiMstVORow().getSimsInvamt()),!Ops.isNull( am.getBlk3TsiMstVORow().getSimsCurcod())))
{ am.getWorkItemVORow().setWErritm_noVal("blk3_tsi_mst.sims_invamt");
 am.getWorkItemVORow().setWErrmsg_noVal("Please input Amount");
throw am.lib().getFormTriggerFailure();
/* Unreachable Statement in original code
return ;
*/}
if (Ops.isNull( am.lib().nameIn(Ops.concat( am.getWorkItemVORow().getWDetailBlk(),".sims_year"))))
{try {
{
DP_MIG_XSIM2.PL_TSIMST3_PRI10_results  result = DP_MIG_XSIM2.PL_TSIMST3_PRI10(am.getDBTransaction(), am.getInfoBlkVORow().getInfoDiv(), am.getBlk3TsiMstVORow().getSimsDptcod());
 am.getBlk3TsiMstVORow().setSimsYear_noVal(result.getO_SIMS_YEAR());
}
}
catch (Exception ex)
{ am.getBlk3TsiMstVORow().setSimsYear_noVal( am.lib().toChar( am.lib().sysdate,"yy"));
}
}
if (Ops.isNull( am.lib().nameIn(Ops.concat( am.getWorkItemVORow().getWDetailBlk(),".sims_sernum"))))
{
{
DP_MIG_XSIM2.PL_TSIMST3_PRI11_results  result = DP_MIG_XSIM2.PL_TSIMST3_PRI11(am.getDBTransaction(), am.getBlk3TsiMstVORow().getSimsTyp(), am.getBlk3TsiMstVORow().getSimsDptcod(), am.getBlk3TsiMstVORow().getSimsYear());
 am.getBlk3TsiMstVORow().setSimsSernum_noVal(result.getO_SIMS_SERNUM());
}
}
 am.lib().copy( am.lib().nameIn("info_blk.info_div"),Ops.concat( am.lib().nameIn("work_item.w_detail_blk"),".sims_divcod"));
{
DP_MIG_XSIM2.PL_TSIMST3_PRI12_results  result = DP_MIG_XSIM2.PL_TSIMST3_PRI12(am.getDBTransaction());
 am.getBlk3TsiMstVORow().setSimsSaminvrun_noVal(result.getO_SIMS_SAMINVRUN());
}
 am.lib().copy(null,"work_item.w_erritm");
}
public void poq(){
boolean  i;
{
DP_MIG_XSIM2.PL_TSIMST3_POQ1_results  result = DP_MIG_XSIM2.PL_TSIMST3_POQ1(am.getDBTransaction(), am.getBlk3TsiMstVORow().getSimsSaminvrun());
 am.getBlk3TsiMstVORow().setLstchg_noVal(result.getO_LSTCHG());
}
{
DP_MIG_XSIM2.PL_TSIMST3_POQ2_results  result = DP_MIG_XSIM2.PL_TSIMST3_POQ2(am.getDBTransaction(), am.getBlk3TsiMstVORow().getSimsSaminvrun());
 am.getBlk3TsiMstVORow().setLstby_noVal(result.getO_LSTBY());
}
 am.getWorkItemVORow().setWCuscod_noVal( am.getBlk3TsiMstVORow().getSimsCuscod());
 i =  am.helpers().plValidateDptcod;
 i =  am.helpers().plValidateChrtyp;
 i =  am.helpers().plValidateCuscod;
 i =  am.helpers().plValidateConpercod;
 i =  am.helpers().plValidateAdrcod;
 i =  am.helpers().plValidatePortL;
 i =  am.helpers().plValidateVia;
 i =  am.helpers().plValidatePortD;
 i =  am.helpers().plValidateCarcod;
 i =  am.helpers().plValidateShpmod;
if (Ops.ne( am.lib().nameIn("WORK_ITEM.W_EDIT_MODE"),"C"))
{ am.helpers().plShowInvno();
}
}
public void prq(){
BigDecimal  flag = new BigDecimal(0);
String  p_current_item =  null;
if (Ops.and(Ops.ne( am.getWorkItemVORow().getWEditMode(),"A"),Ops.ne( am.getWorkItemVORow().getWBlk3Copy(),"Y")))
{ am.lib().copy( am.lib().nameIn(Ops.concat( am.lib().nameIn("work_item.w_main_blk"),".sims_saminvrun")),Ops.concat( am.lib().nameIn("work_item.w_detail_blk"),".sims_saminvrun"));
}
}
public void pru(){
BigDecimal  i =  new BigDecimal(0);
 am.getWorkItemVORow().setWErrmsg_noVal("");
if (Ops.isNull( am.lib().nameIn(Ops.concat( am.getWorkItemVORow().getWDetailBlk(),".sims_dptcod"))))
{ am.lib().copy(Ops.concat( am.getWorkItemVORow().getWDetailBlk(),".sims_dptcod"),"work_item.w_erritm");
 am.getWorkItemVORow().setWErrmsg_noVal( am.lib().fstdUsrMsg("817"));
throw am.lib().getFormTriggerFailure();
/* Unreachable Statement in original code
return ;
*/}
if (Ops.eq( CHECK_DPT(am.getDBTransaction(), am.getBlk3TsiMstVORow().getSimsDptcod(), am.getGlobalVORow().getGDivcod(), am.getGlobalVORow().getGUserid()),"false"))
{ am.lib().copy(Ops.concat( am.getWorkItemVORow().getWDetailBlk(),".sims_dptcod"),"work_item.w_erritm");
 am.getWorkItemVORow().setWErrmsg_noVal( am.lib().fstdUsrMsg("986"));
throw am.lib().getFormTriggerFailure();
/* Unreachable Statement in original code
return ;
*/}
{
DP_MIG_XSIM2.PL_TSIMST3_PRU1_results  result = DP_MIG_XSIM2.PL_TSIMST3_PRU1(am.getDBTransaction(), am.getBlk3TsiMstVORow().getSimsDptcod(), am.getGlobalVORow().getGDivcod());
 i = result.getO_I();
}
if (Ops.eq( i,new BigDecimal(0)))
{ am.lib().copy(Ops.concat( am.getWorkItemVORow().getWDetailBlk(),".sims_dptcod"),"work_item.w_erritm");
 am.getWorkItemVORow().setWErrmsg_noVal( am.lib().fstdUsrMsg("986"));
throw am.lib().getFormTriggerFailure();
/* Unreachable Statement in original code
return ;
*/}
{
DP_MIG_XSIM2.PL_TSIMST3_PRU2_results  result = DP_MIG_XSIM2.PL_TSIMST3_PRU2(am.getDBTransaction(), am.getBlk3TsiMstVORow().getSimsCuscod(), am.getGlobalVORow().getGDivcod());
 i = result.getO_I();
}
if (Ops.and(Ops.eq( i,new BigDecimal(0)),!Ops.isNull( am.getBlk3TsiMstVORow().getSimsCuscod())))
{ am.lib().copy(Ops.concat( am.getWorkItemVORow().getWDetailBlk(),".sims_cuscod"),"work_item.w_erritm");
 am.getWorkItemVORow().setWErrmsg_noVal( am.lib().fstdUsrMsg("986"));
throw am.lib().getFormTriggerFailure();
/* Unreachable Statement in original code
return ;
*/}
if (Ops.isNull( am.lib().nameIn(Ops.concat( am.getWorkItemVORow().getWDetailBlk(),".sims_invdat"))))
{ am.lib().copy(Ops.concat( am.getWorkItemVORow().getWDetailBlk(),".sims_invdat"),"work_item.w_erritm");
 am.getWorkItemVORow().setWErrmsg_noVal( am.lib().fstdUsrMsg("985"));
throw am.lib().getFormTriggerFailure();
/* Unreachable Statement in original code
return ;
*/}
if (Ops.isNull( am.lib().nameIn(Ops.concat( am.getWorkItemVORow().getWDetailBlk(),".sims_shpdat"))))
{ am.lib().copy(Ops.concat( am.getWorkItemVORow().getWDetailBlk(),".sims_shpdat"),"work_item.w_erritm");
 am.getWorkItemVORow().setWErrmsg_noVal( am.lib().fstdUsrMsg("985"));
throw am.lib().getFormTriggerFailure();
/* Unreachable Statement in original code
return ;
*/}
{
DP_MIG_XSIM2.PL_TSIMST3_PRU3_results  result = DP_MIG_XSIM2.PL_TSIMST3_PRU3(am.getDBTransaction(), am.getBlk3TsiMstVORow().getSimsCuscod(), am.getBlk3TsiMstVORow().getSimsConpercod(), am.getGlobalVORow().getGDivcod());
 i = result.getO_I();
}
if (Ops.and(Ops.eq( i,new BigDecimal(0)),!Ops.isNull( am.getBlk3TsiMstVORow().getSimsConpercod())))
{ am.lib().copy(Ops.concat( am.getWorkItemVORow().getWDetailBlk(),".sims_conpercod"),"work_item.w_erritm");
 am.getWorkItemVORow().setWErrmsg_noVal( am.lib().fstdUsrMsg("986"));
throw am.lib().getFormTriggerFailure();
/* Unreachable Statement in original code
return ;
*/}
{
DP_MIG_XSIM2.PL_TSIMST3_PRU4_results  result = DP_MIG_XSIM2.PL_TSIMST3_PRU4(am.getDBTransaction(), am.getBlk3TsiMstVORow().getSimsPortL());
 i = result.getO_I();
}
if (Ops.and(Ops.eq( i,new BigDecimal(0)),!Ops.isNull( am.getBlk3TsiMstVORow().getSimsPortL())))
{ am.lib().copy(Ops.concat( am.getWorkItemVORow().getWDetailBlk(),".sims_port_l"),"work_item.w_erritm");
 am.getWorkItemVORow().setWErrmsg_noVal( am.lib().fstdUsrMsg("986"));
throw am.lib().getFormTriggerFailure();
/* Unreachable Statement in original code
return ;
*/}
{
DP_MIG_XSIM2.PL_TSIMST3_PRU5_results  result = DP_MIG_XSIM2.PL_TSIMST3_PRU5(am.getDBTransaction(), am.getBlk3TsiMstVORow().getSimsVia());
 i = result.getO_I();
}
if (Ops.and(Ops.eq( i,new BigDecimal(0)),!Ops.isNull( am.getBlk3TsiMstVORow().getSimsVia())))
{ am.lib().copy(Ops.concat( am.getWorkItemVORow().getWDetailBlk(),".sims_via"),"work_item.w_erritm");
 am.getWorkItemVORow().setWErrmsg_noVal( am.lib().fstdUsrMsg("986"));
throw am.lib().getFormTriggerFailure();
/* Unreachable Statement in original code
return ;
*/}
{
DP_MIG_XSIM2.PL_TSIMST3_PRU6_results  result = DP_MIG_XSIM2.PL_TSIMST3_PRU6(am.getDBTransaction(), am.getBlk3TsiMstVORow().getSimsPortD());
 i = result.getO_I();
}
if (Ops.and(Ops.eq( i,new BigDecimal(0)),!Ops.isNull( am.getBlk3TsiMstVORow().getSimsPortD())))
{ am.lib().copy(Ops.concat( am.getWorkItemVORow().getWDetailBlk(),".sims_port_d"),"work_item.w_erritm");
 am.getWorkItemVORow().setWErrmsg_noVal( am.lib().fstdUsrMsg("986"));
throw am.lib().getFormTriggerFailure();
/* Unreachable Statement in original code
return ;
*/}
{
DP_MIG_XSIM2.PL_TSIMST3_PRU7_results  result = DP_MIG_XSIM2.PL_TSIMST3_PRU7(am.getDBTransaction(), am.getBlk3TsiMstVORow().getSimsAdrcod(), am.getBlk3TsiMstVORow().getSimsCuscod(), am.getGlobalVORow().getGDivcod());
 i = result.getO_I();
}
if (Ops.and(Ops.eq( i,new BigDecimal(0)),!Ops.isNull( am.lib().nameIn(Ops.concat( am.getWorkItemVORow().getWDetailBlk(),".sims_adrcod")))))
{ am.lib().copy(Ops.concat( am.getWorkItemVORow().getWDetailBlk(),".sims_adrcod"),"work_item.w_erritm");
 am.getWorkItemVORow().setWErrmsg_noVal( am.lib().fstdUsrMsg("986"));
throw am.lib().getFormTriggerFailure();
/* Unreachable Statement in original code
return ;
*/}
if (Ops.and(!Ops.isNull( am.getBlk3TsiMstVORow().getSimsInvamt()),Ops.isNull( am.getBlk3TsiMstVORow().getSimsCurcod())))
{ am.getWorkItemVORow().setWErritm_noVal("blk3_tsi_mst.sims_curcod");
 am.getWorkItemVORow().setWErrmsg_noVal("Please input currency code");
throw am.lib().getFormTriggerFailure();
/* Unreachable Statement in original code
return ;
*/}
if (Ops.and(Ops.isNull( am.getBlk3TsiMstVORow().getSimsInvamt()),!Ops.isNull( am.getBlk3TsiMstVORow().getSimsCurcod())))
{ am.getWorkItemVORow().setWErritm_noVal("blk3_tsi_mst.sims_invamt");
 am.getWorkItemVORow().setWErrmsg_noVal("Please input Amount");
throw am.lib().getFormTriggerFailure();
/* Unreachable Statement in original code
return ;
*/}
{
DP_MIG_XSIM2.PL_TSIMST3_PRU8_results  result = DP_MIG_XSIM2.PL_TSIMST3_PRU8(am.getDBTransaction(), am.getBlk3TsiMstVORow().getSimsCarcod());
 i = result.getO_I();
}
if (Ops.and(Ops.eq( i,new BigDecimal(0)),!Ops.isNull( am.getBlk3TsiMstVORow().getSimsCarcod())))
{ am.lib().copy(Ops.concat( am.getWorkItemVORow().getWDetailBlk(),".sims_carcod"),"work_item.w_erritm");
 am.getWorkItemVORow().setWErrmsg_noVal( am.lib().fstdUsrMsg("986"));
throw am.lib().getFormTriggerFailure();
/* Unreachable Statement in original code
return ;
*/}
{
DP_MIG_XSIM2.PL_TSIMST3_PRU9_results  result = DP_MIG_XSIM2.PL_TSIMST3_PRU9(am.getDBTransaction(), am.getBlk3TsiMstVORow().getSimsShpmodcod());
 i = result.getO_I();
}
if (Ops.and(Ops.eq( i,new BigDecimal(0)),!Ops.isNull( am.getBlk3TsiMstVORow().getSimsShpmodcod())))
{ am.lib().copy(Ops.concat( am.getWorkItemVORow().getWDetailBlk(),".sims_shpmodcod"),"work_item.w_erritm");
 am.getWorkItemVORow().setWErrmsg_noVal( am.lib().fstdUsrMsg("863"));
throw am.lib().getFormTriggerFailure();
/* Unreachable Statement in original code
return ;
*/}
 am.lib().copy(null,"work_item.w_erritm");
}
public void oe(){
BigDecimal  lv_errcod =  am.lib().errorCode;
 am.getWorkItemVORow().setWErrmsg_noVal("");
if (Ops.or(Ops.or(Ops.eq( lv_errcod,new BigDecimal(40508)),Ops.eq( lv_errcod,new BigDecimal(40509))),Ops.eq( lv_errcod,new BigDecimal(40600))))
{ am.getWorkItemVORow().setWErrmsg_noVal("");
throw am.lib().getFormTriggerFailure();
/* Unreachable Statement in original code
return ;
*/}
;
}
public void kf3(){
if (Ops.eq( am.lib().upper( am.lib().nameIn("work_item.w_current_blk")),"BLK3_TSI_MST"))
{ am.lib().goItem("blk3_tsi_mst.sims_dptcod");
}
}
public void kf4(){
if (Ops.eq( am.lib().upper( am.lib().nameIn("work_item.w_current_blk")),"BLK3_TSI_MST"))
{ am.lib().goItem("blk4_tsi_des_d.sids_des");
}
}
public void kf5(){
if (Ops.eq( am.lib().upper( am.lib().nameIn("work_item.w_current_blk")),"BLK3_TSI_MST"))
{ am.lib().goItem("blk4_tsi_des_b.sids_des");
}
}
public void kf6(){
if (Ops.eq( am.lib().upper( am.lib().nameIn("work_item.w_current_blk")),"BLK3_TSI_MST"))
{ am.lib().goItem("blk4_tsi_des_q.sids_des");
}
}

}

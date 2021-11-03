package model.views;
public class Blk2CriteriaVOFieldEvents {
    public Blk2CriteriaVOFieldEvents(Xsim2AmImpl am) {
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
public void Excel_bp(){
 am.lib().plList2xls("XSIM2","BLK1_TSI_MST");
}
public void Blk2Ok_bp(){
 am.lib().copy( am.lib().nameIn(Ops.concat( am.getWorkItemVORow().getWCriteriaBlk(),".SIMS_DIVCOD")),"GLOBAL.G_DIVCOD");
 am.lib().copy( am.lib().nameIn(Ops.concat( am.getWorkItemVORow().getWCriteriaBlk(),".SIMS_DIVCOD")),"INFO_BLK.INFO_DIV");
 am.lib().copy( am.lib().nameIn(Ops.concat( am.getWorkItemVORow().getWCriteriaBlk(),".SIMS_DIVCOD")),"WORK_ITEM.W_DIV");
 am.lib().pstdBlk2OkWithDiv("sims_divcod");
 am.getWorkItemVORow().setWNoCriteria_noVal("N");
 am.getWorkItemVORow().setWSearchMode_noVal("B");
 am.lib().goBlock("BLK1_TSI_MST");
 am.lib().executeQuery();
 am.helpers().plUpdateWindowTitle();
}
public void Blk2Reset_bp(){
 am.lib().pstdBlk2Reset();
 am.helpers().plResetSelectCriteria();
}
public void Blk2Cancel_bp(){
 am.lib().pstdBlk2Cancel();
}
public void Blk2LovPortD_bp(){
boolean  i;
BigDecimal  rc =  new BigDecimal(0);
if (Ops.eq( am.lib().populateGroup( am.lib().getLovProperty("LOV_CTYPORMST", am.lib().groupName)),new BigDecimal(0)))
{ rc =  am.lib().getGroupRowCount( am.lib().getLovProperty("LOV_CTYPORMST", am.lib().groupName));
if (Ops.gt( rc,new BigDecimal(0)))
{ am.helpers().plSetLovPosition("LOV_CTYPORMST", am.getSystemVORow().getTriggerItem());
if ( am.lib().showLov("LOV_CTYPORMST"))
{ am.lib().copy( am.getGlobalVORow().getOne(),Ops.concat( am.getWorkItemVORow().getWCriteriaBlk(),".SIMS_PORT_D"));
try {
{
DP_MIG_XSIM2.PL_CRITERIA2_LOVPORTD_BP1_results  result = DP_MIG_XSIM2.PL_CRITERIA2_LOVPORTD_BP1(am.getDBTransaction(), am.getBlk2CriteriaVORow().getSimsPortD());
 am.getBlk2CriteriaVORow().setMpcpNamD_noVal(result.getO_MPCP_NAM_D());
}
}
catch (Exception ex)
{ am.getBlk2CriteriaVORow().setMpcpNamD_noVal("");
}
}
}
else
{ am.lib().pstdNullLov();
}
}
else
{ am.lib().pstdNullLov();
}
}
public void MpsmDes_pot(){
try {
{
DP_MIG_XSIM2.PL_CRITERIA2_MPSMDES_POT1_results  result = DP_MIG_XSIM2.PL_CRITERIA2_MPSMDES_POT1(am.getDBTransaction(), am.getBlk2CriteriaVORow().getMpsmDes());
 am.getBlk2CriteriaVORow().setSimsShpmodcod_noVal(result.getO_SIMS_SHPMODCOD());
}
}
catch (Exception ex)
{ am.getBlk2CriteriaVORow().setSimsShpmodcod_noVal("");
}
}
public void Blk3LovSimsShpmodcod_bp(){
boolean  i;
BigDecimal  rc =  new BigDecimal(0);
if (Ops.eq( am.lib().populateGroup( am.lib().getLovProperty("LOV_SHPMODMST", am.lib().groupName)),new BigDecimal(0)))
{ rc =  am.lib().getGroupRowCount( am.lib().getLovProperty("LOV_SHPMODMST", am.lib().groupName));
if (Ops.gt( rc,new BigDecimal(0)))
{ am.helpers().plSetLovPosition("LOV_SHPMODMST", am.getSystemVORow().getTriggerItem());
if ( am.lib().showLov("LOV_SHPMODMST"))
{ am.lib().copy( am.getGlobalVORow().getOne(),Ops.concat( am.getWorkItemVORow().getWCriteriaBlk(),".SIMS_SHPMODCOD"));
 am.lib().copy( am.getGlobalVORow().getTwo(),Ops.concat( am.getWorkItemVORow().getWCriteriaBlk(),".MPSM_DES"));
}
}
else
{ am.lib().pstdNullLov();
}
}
else
{ am.lib().pstdNullLov();
}
}
public void Active_vi(){
BigDecimal  n_num =  new BigDecimal(0);
BigDecimal  status =  new BigDecimal(0);
if (!Ops.isNull( am.getBlk2CriteriaVORow().getActive()))
{
if (Ops.and(Ops.ne( am.lib().upper( am.getBlk2CriteriaVORow().getActive()),"Y"),Ops.ne( am.lib().upper( am.getBlk2CriteriaVORow().getActive()),"N")))
{ am.lib().setAlertProperty("AL_STD_WARN", am.lib().alertMessageText, am.lib().fstdUsrMsg("31"));
 status =  am.lib().showAlert("AL_STD_WARN");
throw am.lib().getFormTriggerFailure();
/* Unreachable Statement in original code
return ;
*/}
if (Ops.eq( am.lib().upper( am.getBlk2CriteriaVORow().getActive()),"Y"))
{ am.getBlk2CriteriaVORow().setSimsClsdat_noVal("N");
}
else
{ am.getBlk2CriteriaVORow().setSimsClsdat_noVal("Y");
}
}
}
public void SelectShpDatFr_pot(){
 am.lib().setItemProperty( am.getSystemVORow().getTriggerItem(), am.lib().formatMask,"DD-MON-YYYY");
}
public void SelectShpDatFr_vi(){
if (Ops.or(Ops.gt( am.lib().toDate( am.lib().nameIn( am.getSystemVORow().getTriggerItem()),"DD-MON-RRRR"), am.lib().toDate("31-DEC-2049","DD-MON-YYYY")),Ops.lt( am.lib().toDate( am.lib().nameIn( am.getSystemVORow().getTriggerItem()),"DD-MON-RRRR"), am.lib().toDate("1-JAN-1950","DD-MON-YYYY"))))
{ am.lib().message("Date is invalid! Input date must be between 1-JAN-1950 and 31-DEC-2049");
throw am.lib().getFormTriggerFailure();
}
}
public void SelectShpDatTo_vi(){
if (Ops.or(Ops.gt( am.lib().toDate( am.lib().nameIn( am.getSystemVORow().getTriggerItem()),"DD-MON-RRRR"), am.lib().toDate("31-DEC-2049","DD-MON-YYYY")),Ops.lt( am.lib().toDate( am.lib().nameIn( am.getSystemVORow().getTriggerItem()),"DD-MON-RRRR"), am.lib().toDate("1-JAN-1950","DD-MON-YYYY"))))
{ am.lib().message("Date is invalid! Input date must be between 1-JAN-1950 and 31-DEC-2049");
throw am.lib().getFormTriggerFailure();
}
}
public void SelectShpDatTo_pot(){
 am.lib().setItemProperty( am.getSystemVORow().getTriggerItem(), am.lib().formatMask,"DD-MON-YYYY");
}
public void MpcpNamD_pot(){
try {
{
DP_MIG_XSIM2.PL_CRITERIA2_MPCPNAMD_POT1_results  result = DP_MIG_XSIM2.PL_CRITERIA2_MPCPNAMD_POT1(am.getDBTransaction(), am.getBlk2CriteriaVORow().getMpcpNamD());
 am.getBlk2CriteriaVORow().setSimsPortD_noVal(result.getO_SIMS_PORT_D());
}
}
catch (Exception ex)
{ am.getBlk2CriteriaVORow().setSimsPortD_noVal("");
}
}
public void SelectInvDatFr_vi(){
if (Ops.or(Ops.gt( am.lib().toDate( am.lib().nameIn( am.getSystemVORow().getTriggerItem()),"DD-MON-RRRR"), am.lib().toDate("31-DEC-2049","DD-MON-YYYY")),Ops.lt( am.lib().toDate( am.lib().nameIn( am.getSystemVORow().getTriggerItem()),"DD-MON-RRRR"), am.lib().toDate("1-JAN-1950","DD-MON-YYYY"))))
{ am.lib().message("Date is invalid! Input date must be between 1-JAN-1950 and 31-DEC-2049");
throw am.lib().getFormTriggerFailure();
}
}
public void SelectInvDatFr_pot(){
 am.lib().setItemProperty( am.getSystemVORow().getTriggerItem(), am.lib().formatMask,"DD-MON-YYYY");
}
public void Blk2LovSimsDptcod_bp(){
BigDecimal  rc =  new BigDecimal(0);
 am.lib().copy( am.lib().nameIn(Ops.concat( am.getWorkItemVORow().getWCriteriaBlk(),".sims_divcod")),"global.g_divcod");
if (Ops.eq( am.lib().populateGroup( am.lib().getLovProperty("lov_blk2_opdp_dptcod", am.lib().groupName)),new BigDecimal(0)))
{ rc =  am.lib().getGroupRowCount( am.lib().getLovProperty("lov_blk2_opdp_dptcod", am.lib().groupName));
if (Ops.gt( rc,new BigDecimal(0)))
{ am.helpers().plSetLovPosition("lov_blk2_opdp_dptcod", am.getSystemVORow().getTriggerItem());
if ( am.lib().showLov("lov_blk2_opdp_dptcod"))
{ am.lib().copy( am.getGlobalVORow().getOne(),Ops.concat( am.getWorkItemVORow().getWCriteriaBlk(),".sims_dptcod"));
}
}
else
{ am.lib().pstdNullLov();
}
}
else
{ am.lib().pstdNullLov();
}
 am.lib().copy( am.lib().nameIn("work_item.w_div"),"global.g_divcod");
}
public void SelectInvDatTo_pot(){
 am.lib().setItemProperty( am.getSystemVORow().getTriggerItem(), am.lib().formatMask,"DD-MON-YYYY");
}
public void SelectInvDatTo_vi(){
if (Ops.or(Ops.gt( am.lib().toDate( am.lib().nameIn( am.getSystemVORow().getTriggerItem()),"DD-MON-RRRR"), am.lib().toDate("31-DEC-2049","DD-MON-YYYY")),Ops.lt( am.lib().toDate( am.lib().nameIn( am.getSystemVORow().getTriggerItem()),"DD-MON-RRRR"), am.lib().toDate("1-JAN-1950","DD-MON-YYYY"))))
{ am.lib().message("Date is invalid! Input date must be between 1-JAN-1950 and 31-DEC-2049");
throw am.lib().getFormTriggerFailure();
}
}
public void Blk2LovSimsInvno_bp(){
BigDecimal  rc =  new BigDecimal(0);
if (Ops.eq( am.lib().populateGroup( am.lib().getLovProperty("lov_blk2_invno", am.lib().groupName)),new BigDecimal(0)))
{ rc =  am.lib().getGroupRowCount( am.lib().getLovProperty("lov_blk2_invno", am.lib().groupName));
if (Ops.gt( rc,new BigDecimal(0)))
{ am.helpers().plSetLovPosition("lov_blk2_invno", am.getSystemVORow().getTriggerItem());
if ( am.lib().showLov("lov_blk2_invno"))
{ am.lib().copy( am.getGlobalVORow().getOne(),Ops.concat( am.getWorkItemVORow().getWCriteriaBlk(),".sims_invno"));
}
}
else
{ am.lib().pstdNullLov();
}
}
else
{ am.lib().pstdNullLov();
}
}
public void Blk2LovSimsCuscod_bp(){
BigDecimal  rc =  new BigDecimal(0);
if (Ops.eq( am.lib().populateGroup( am.lib().getLovProperty("lov_blk2_cuscod", am.lib().groupName)),new BigDecimal(0)))
{ rc =  am.lib().getGroupRowCount( am.lib().getLovProperty("lov_blk2_cuscod", am.lib().groupName));
if (Ops.gt( rc,new BigDecimal(0)))
{ am.helpers().plSetLovPosition("lov_blk2_cuscod", am.getSystemVORow().getTriggerItem());
if ( am.lib().showLov("lov_blk2_cuscod"))
{ am.lib().copy( am.getGlobalVORow().getOne(),Ops.concat( am.getWorkItemVORow().getWCriteriaBlk(),".sims_cuscod"));
}
}
else
{ am.lib().pstdNullLov();
}
}
else
{ am.lib().pstdNullLov();
}
}
public void Blk2LovSimsDivcod_bp(){
BigDecimal  rc =  new BigDecimal(0);
BigDecimal  i =  new BigDecimal(0);
if (Ops.eq( am.lib().populateGroup( am.lib().getLovProperty("lov_opdv_divcod", am.lib().groupName)),new BigDecimal(0)))
{ rc =  am.lib().getGroupRowCount( am.lib().getLovProperty("lov_opdv_divcod", am.lib().groupName));
if (Ops.gt( rc,new BigDecimal(0)))
{ am.helpers().plSetLovPosition("lov_opdv_divcod", am.getSystemVORow().getTriggerItem());
if ( am.lib().showLov("lov_opdv_divcod"))
{ am.lib().copy( am.getGlobalVORow().getOne(),Ops.concat( am.getWorkItemVORow().getWCriteriaBlk(),".sims_divcod"));
{
DP_MIG_XSIM2.PL_RITERIA2_OVSIMSDIVCOD_BP1_results  result = DP_MIG_XSIM2.PL_RITERIA2_OVSIMSDIVCOD_BP1(am.getDBTransaction(), am.getBlk2CriteriaVORow().getSimsDptcod(), am.getGlobalVORow().getOne());
 i = result.getO_I();
}
if (Ops.and(Ops.eq( i,new BigDecimal(0)),!Ops.isNull( am.getBlk2CriteriaVORow().getSimsDptcod())))
{ am.lib().copy(null,Ops.concat( am.getWorkItemVORow().getWCriteriaBlk(),".sims_dptcod"));
}
}
}
else
{ am.lib().pstdNullLov();
}
}
else
{ am.lib().pstdNullLov();
}
}

}

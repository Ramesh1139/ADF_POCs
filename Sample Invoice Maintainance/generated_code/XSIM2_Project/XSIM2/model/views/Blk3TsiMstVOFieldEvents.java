package model.views;
public class Blk3TsiMstVOFieldEvents {
    public Blk3TsiMstVOFieldEvents(Xsim2AmImpl am) {
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
public void SimsDptcod_pot(){
boolean  i;
 i =  am.helpers().plValidateDptcod;
}
public void SimsDptcod_mc(){
if (Ops.eq( am.getWorkItemVORow().getWDtlBlock(),"HDR"))
{ am.lib().hideView("blk5_can");
 am.lib().showView("blk4_can");
}
else
{ am.lib().hideView("blk4_can");
 am.lib().showView("blk5_can");
}
}
public void Blk3LovDptcod_bp(){
boolean  i;
BigDecimal  rc =  new BigDecimal(0);
if (Ops.eq( am.lib().populateGroup( am.lib().getLovProperty("LOV_OPDP_DPTCOD", am.lib().groupName)),new BigDecimal(0)))
{ rc =  am.lib().getGroupRowCount( am.lib().getLovProperty("LOV_OPDP_DPTCOD", am.lib().groupName));
if (Ops.gt( rc,new BigDecimal(0)))
{ am.helpers().plSetLovPosition("LOV_OPDP_DPTCOD", am.getSystemVORow().getTriggerItem());
if ( am.lib().showLov("LOV_OPDP_DPTCOD"))
{ am.lib().copy( am.getGlobalVORow().getOne(),Ops.concat( am.getWorkItemVORow().getWDetailBlk(),".SIMS_DPTCOD"));
 i =  am.helpers().plValidateDptcod;
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
public void OpdpNam_mc(){
if (Ops.eq( am.getWorkItemVORow().getWDtlBlock(),"HDR"))
{ am.lib().hideView("blk5_can");
 am.lib().showView("blk4_can");
}
else
{ am.lib().hideView("blk4_can");
 am.lib().showView("blk5_can");
}
}
public void SimsInvno_mc(){
if (Ops.eq( am.getWorkItemVORow().getWDtlBlock(),"HDR"))
{ am.lib().hideView("blk5_can");
 am.lib().showView("blk4_can");
}
else
{ am.lib().hideView("blk4_can");
 am.lib().showView("blk5_can");
}
}
public void SimsSamsnd_cc(){
if ( am.lib().checkboxChecked("blk6_updlst.SIMS_PRT"))
{ am.getBlk6ControlVORow().setTotalSelected_noVal(Ops.plus( am.getBlk6ControlVORow().getTotalSelected(),new BigDecimal(1)));
}
else
{ am.getBlk6ControlVORow().setTotalSelected_noVal(Ops.minus( am.getBlk6ControlVORow().getTotalSelected(),new BigDecimal(1)));
}
}
public void SimsSnddat_pot(){
 am.lib().setItemProperty( am.getSystemVORow().getTriggerItem(), am.lib().formatMask,"DD-MON-YYYY");
boolean  i;
 i =  am.helpers().plValidateCarcod;
}
public void SimsCuscod_pot(){
boolean  i;
 am.getWorkItemVORow().setWCuscod_noVal( am.getBlk3TsiMstVORow().getSimsCuscod());
 i =  am.helpers().plValidateCuscod;
if (Ops.eq( i, true))
{ am.helpers().plSetCustDefault();
}
if (Ops.eq( DP_CASE.CHECK_SETCOD(am.getDBTransaction(), am.getGlobalVORow().getGDivcod(), am.lib().nvl( am.getBlk3TsiMstVORow().getSimsCuscod(),"*"),"J76"),"Y"))
{ am.lib().setItemProperty("BLK3_TSI_MST.sims_samsnd", am.lib().enabled, am.lib().propertyTrue);
 am.lib().setItemProperty("BLK3_TSI_MST.sims_samsnd", am.lib().updateable, am.lib().propertyTrue);
}
else
{ am.lib().setItemProperty("BLK3_TSI_MST.sims_samsnd", am.lib().enabled, am.lib().propertyFalse);
 am.lib().setItemProperty("BLK3_TSI_MST.sims_samsnd", am.lib().updateable, am.lib().propertyFalse);
 am.getBlk3TsiMstVORow().setSimsSamsnd_noVal("N");
}
}
public void SimsCuscod_pc(){
 am.getWorkItemVORow().setWCuscod_noVal( am.getBlk3TsiMstVORow().getSimsCuscod());
 am.lib().copy(null,Ops.concat( am.getWorkItemVORow().getWDetailBlk(),".SIMS_CONPERCOD"));
 am.lib().copy(null,Ops.concat( am.getWorkItemVORow().getWDetailBlk(),".CPCP_CONPERNAM"));
}
public void Blk3LovCuscod_bp(){
boolean  i;
BigDecimal  rc =  new BigDecimal(0);
String  o_cuscod =  null;
 o_cuscod =  am.getBlk3TsiMstVORow().getSimsCuscod();
if (Ops.eq( am.lib().populateGroup( am.lib().getLovProperty("LOV_CUSCOD", am.lib().groupName)),new BigDecimal(0)))
{ rc =  am.lib().getGroupRowCount( am.lib().getLovProperty("LOV_CUSCOD", am.lib().groupName));
if (Ops.gt( rc,new BigDecimal(0)))
{ am.helpers().plSetLovPosition("LOV_CUSCOD", am.getSystemVORow().getTriggerItem());
if ( am.lib().showLov("LOV_CUSCOD"))
{ am.lib().copy( am.getGlobalVORow().getOne(),Ops.concat( am.getWorkItemVORow().getWDetailBlk(),".SIMS_CUSCOD"));
 am.getWorkItemVORow().setWCuscod_noVal( am.getBlk3TsiMstVORow().getSimsCuscod());
 i =  am.helpers().plValidateCuscod;
if (Ops.eq( i, true))
{ am.helpers().plSetCustDefault();
}
}
if (Ops.ne( am.getBlk3TsiMstVORow().getSimsCuscod(), o_cuscod))
{ am.lib().copy(null,Ops.concat( am.getWorkItemVORow().getWDetailBlk(),".sims_conpercod"));
 am.lib().copy(null,Ops.concat( am.getWorkItemVORow().getWDetailBlk(),".cpcp_conpernam"));
}
if (Ops.eq( DP_CASE.CHECK_SETCOD(am.getDBTransaction(), am.getGlobalVORow().getGDivcod(), am.lib().nvl( am.getBlk3TsiMstVORow().getSimsCuscod(),"*"),"J76"),"Y"))
{ am.lib().setItemProperty("BLK3_TSI_MST.sims_samsnd", am.lib().enabled, am.lib().propertyTrue);
 am.lib().setItemProperty("BLK3_TSI_MST.sims_samsnd", am.lib().updateable, am.lib().propertyTrue);
}
else
{ am.lib().setItemProperty("BLK3_TSI_MST.sims_samsnd", am.lib().enabled, am.lib().propertyFalse);
 am.lib().setItemProperty("BLK3_TSI_MST.sims_samsnd", am.lib().updateable, am.lib().propertyFalse);
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
public void SimsChrtyp_pot(){
boolean  i;
 i =  am.helpers().plValidateChrtyp;
}
public void SimsYear_pot(){
boolean  i;
 i =  am.helpers().plValidateCuscod;
if (Ops.isNull( am.lib().nameIn(Ops.concat( am.getWorkItemVORow().getWDetailBlk(),".SIMS_CUSCOD"))))
{ am.lib().copy(null,Ops.concat( am.getWorkItemVORow().getWDetailBlk(),".SIMS_ADRCOD"));
 am.lib().copy(null,Ops.concat( am.getWorkItemVORow().getWDetailBlk(),".CPAD_ADR1"));
 am.lib().copy(null,Ops.concat( am.getWorkItemVORow().getWDetailBlk(),".CPAD_ADR2"));
 am.lib().copy(null,Ops.concat( am.getWorkItemVORow().getWDetailBlk(),".CPAD_ADR3"));
 am.lib().copy(null,Ops.concat( am.getWorkItemVORow().getWDetailBlk(),".CPAD_ADR4"));
}
}
public void LovChrtyp_bp(){
boolean  i;
BigDecimal  rc =  new BigDecimal(0);
if (Ops.eq( am.lib().populateGroup( am.lib().getLovProperty("LOV_CHRTYP", am.lib().groupName)),new BigDecimal(0)))
{ rc =  am.lib().getGroupRowCount( am.lib().getLovProperty("LOV_CHRTYP", am.lib().groupName));
if (Ops.gt( rc,new BigDecimal(0)))
{ am.helpers().plSetLovPosition("LOV_CHRTYP", am.getSystemVORow().getTriggerItem());
if ( am.lib().showLov("LOV_CHRTYP"))
{ am.lib().copy( am.getGlobalVORow().getOne(),Ops.concat( am.getWorkItemVORow().getWDetailBlk(),".SIMS_CHRTYP"));
 i =  am.helpers().plValidateChrtyp;
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
public void SimsSernum_pot(){
boolean  i;
 i =  am.helpers().plValidateCuscod;
if (Ops.isNull( am.lib().nameIn(Ops.concat( am.getWorkItemVORow().getWDetailBlk(),".SIMS_CUSCOD"))))
{ am.lib().copy(null,Ops.concat( am.getWorkItemVORow().getWDetailBlk(),".SIMS_ADRCOD"));
 am.lib().copy(null,Ops.concat( am.getWorkItemVORow().getWDetailBlk(),".CPAD_ADR1"));
 am.lib().copy(null,Ops.concat( am.getWorkItemVORow().getWDetailBlk(),".CPAD_ADR2"));
 am.lib().copy(null,Ops.concat( am.getWorkItemVORow().getWDetailBlk(),".CPAD_ADR3"));
 am.lib().copy(null,Ops.concat( am.getWorkItemVORow().getWDetailBlk(),".CPAD_ADR4"));
}
}
public void SimsPortL_pot(){
boolean  i;
 i =  am.helpers().plValidatePortL;
}
public void Blk3LovPortL_bp(){
boolean  i;
BigDecimal  rc =  new BigDecimal(0);
if (Ops.eq( am.lib().populateGroup( am.lib().getLovProperty("LOV_CTYPORMST", am.lib().groupName)),new BigDecimal(0)))
{ rc =  am.lib().getGroupRowCount( am.lib().getLovProperty("LOV_CTYPORMST", am.lib().groupName));
if (Ops.gt( rc,new BigDecimal(0)))
{ am.helpers().plSetLovPosition("LOV_CTYPORMST", am.getSystemVORow().getTriggerItem());
if ( am.lib().showLov("LOV_CTYPORMST"))
{ am.lib().copy( am.getGlobalVORow().getOne(),Ops.concat( am.getWorkItemVORow().getWDetailBlk(),".SIMS_PORT_L"));
 i =  am.helpers().plValidatePortL;
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
public void SimsConpercod_pot(){
boolean  i;
 i =  am.helpers().plValidateConpercod;
}
public void Blk3LovConpercod_bp(){
boolean  i;
BigDecimal  rc =  new BigDecimal(0);
if (Ops.eq( am.lib().populateGroup( am.lib().getLovProperty("LOV_CONPERCOD", am.lib().groupName)),new BigDecimal(0)))
{ rc =  am.lib().getGroupRowCount( am.lib().getLovProperty("LOV_CONPERCOD", am.lib().groupName));
if (Ops.gt( rc,new BigDecimal(0)))
{ am.helpers().plSetLovPosition("LOV_CONPERCOD", am.getSystemVORow().getTriggerItem());
if ( am.lib().showLov("LOV_CONPERCOD"))
{ am.lib().copy( am.getGlobalVORow().getThree(),Ops.concat( am.getWorkItemVORow().getWDetailBlk(),".SIMS_CONPERCOD"));
 i =  am.helpers().plValidateConpercod;
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
public void SimsEtadat_pot(){
 am.lib().setItemProperty( am.getSystemVORow().getTriggerItem(), am.lib().formatMask,"DD-MON-YYYY");
boolean  i;
 i =  am.helpers().plValidateCarcod;
}
public void SimsEtddat_pot(){
 am.lib().setItemProperty( am.getSystemVORow().getTriggerItem(), am.lib().formatMask,"DD-MON-YYYY");
boolean  i;
 i =  am.helpers().plValidateCarcod;
}
public void Blk3LovVia_bp(){
boolean  i;
BigDecimal  rc =  new BigDecimal(0);
if (Ops.eq( am.lib().populateGroup( am.lib().getLovProperty("LOV_CTYPORMST", am.lib().groupName)),new BigDecimal(0)))
{ rc =  am.lib().getGroupRowCount( am.lib().getLovProperty("LOV_CTYPORMST", am.lib().groupName));
if (Ops.gt( rc,new BigDecimal(0)))
{ am.helpers().plSetLovPosition("LOV_CTYPORMST", am.getSystemVORow().getTriggerItem());
if ( am.lib().showLov("LOV_CTYPORMST"))
{ am.lib().copy( am.getGlobalVORow().getOne(),Ops.concat( am.getWorkItemVORow().getWDetailBlk(),".SIMS_VIA"));
 i =  am.helpers().plValidateVia;
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
public void SimsVia_pot(){
boolean  i;
 i =  am.helpers().plValidateVia;
}
public void Blk3LovPortD_bp(){
boolean  i;
BigDecimal  rc =  new BigDecimal(0);
if (Ops.eq( am.lib().populateGroup( am.lib().getLovProperty("LOV_CTYPORMST", am.lib().groupName)),new BigDecimal(0)))
{ rc =  am.lib().getGroupRowCount( am.lib().getLovProperty("LOV_CTYPORMST", am.lib().groupName));
if (Ops.gt( rc,new BigDecimal(0)))
{ am.helpers().plSetLovPosition("LOV_CTYPORMST", am.getSystemVORow().getTriggerItem());
if ( am.lib().showLov("LOV_CTYPORMST"))
{ am.lib().copy( am.getGlobalVORow().getOne(),Ops.concat( am.getWorkItemVORow().getWDetailBlk(),".SIMS_PORT_D"));
 i =  am.helpers().plValidatePortD;
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
public void SimsPortD_pot(){
boolean  i;
 i =  am.helpers().plValidatePortD;
}
public void Blk3LovCurcod_bp(){
boolean  i;
BigDecimal  rc =  new BigDecimal(0);
if (Ops.eq( am.lib().populateGroup( am.lib().getLovProperty("LOV_CURRENCY", am.lib().groupName)),new BigDecimal(0)))
{ rc =  am.lib().getGroupRowCount( am.lib().getLovProperty("LOV_CURRENCY", am.lib().groupName));
if (Ops.gt( rc,new BigDecimal(0)))
{ am.helpers().plSetLovPosition("LOV_CURRENCY", am.getSystemVORow().getTriggerItem());
if ( am.lib().showLov("LOV_CURRENCY"))
{ am.lib().copy( am.getGlobalVORow().getOne(),Ops.concat( am.getWorkItemVORow().getWDetailBlk(),".SIMS_CURCOD"));
if (Ops.and(Ops.isNull( am.getBlk3TsiMstVORow().getSimsInvamt()),!Ops.isNull( am.getBlk3TsiMstVORow().getSimsCurcod())))
{ am.getBlk3TsiMstVORow().setSimsInvamt_noVal(new BigDecimal(0));
}
if (Ops.and(!Ops.isNull( am.getBlk3TsiMstVORow().getSimsInvamt()),!Ops.isNull( am.getBlk3TsiMstVORow().getSimsCurcod())))
{ am.getBlk3TsiMstVORow().setSimsTotamt_noVal(Ops.concat(Ops.concat( am.getBlk3TsiMstVORow().getSimsCurcod()," "), am.lib().ltrim( am.lib().toChar( am.getBlk3TsiMstVORow().getSimsInvamt(),"99,999,990.00"))));
}
else
{ am.getBlk3TsiMstVORow().setSimsTotamt_noVal("");
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
public void SimsAdrcod_pot(){
boolean  i;
 i =  am.helpers().plValidateAdrcod;
}
public void SimsCurcod_vi(){
if (Ops.eq( am.getSystemVORow().getFormStatus(),"QUERY"))
{return ;
}
BigDecimal  n_count =  new BigDecimal(0);
if (!Ops.isNull( am.getBlk3TsiMstVORow().getSimsCurcod()))
{try {
{
DP_MIG_XSIM2.PL_TSIMST3_SIMSCURCOD_VI1_results  result = DP_MIG_XSIM2.PL_TSIMST3_SIMSCURCOD_VI1(am.getDBTransaction(), am.getBlk3TsiMstVORow().getSimsCurcod());
 n_count = result.getO_N_COUNT();
}
}
catch (Exception ex)
{ am.getBlk3TsiMstVORow().setSimsCurcod_noVal("");
 am.getBlk3TsiMstVORow().setSimsTotamt_noVal("");
 am.lib().goItem("blk3_tsi_mst.sims_curcod");
}
if (Ops.le( n_count,new BigDecimal(0)))
{ am.getBlk3TsiMstVORow().setSimsCurcod_noVal("");
 am.getBlk3TsiMstVORow().setSimsTotamt_noVal("");
 am.helpers().pMessage( am.lib().fstdUsrMsg("331"),"CAUTION");
throw am.lib().getFormTriggerFailure();
/* Unreachable Statement in original code
return ;
*/}
else
{
if(Ops.isNull( am.getBlk3TsiMstVORow().getSimsInvamt()))
{ am.getBlk3TsiMstVORow().setSimsInvamt_noVal(new BigDecimal(0));
}
}
}
try {
{
DP_MIG_XSIM2.PL_TSIMST3_SIMSCURCOD_VI2_results  result = DP_MIG_XSIM2.PL_TSIMST3_SIMSCURCOD_VI2(am.getDBTransaction(), am.getBlk3TsiMstVORow().getSimsSaminvrun(), am.getGlobalVORow().getGDivcod());
 n_count = result.getO_N_COUNT();
}
}
catch (Exception ex)
{ n_count = new BigDecimal(0);
}
if (Ops.and(Ops.gt( n_count,new BigDecimal(0)),Ops.isNull( am.getBlk3TsiMstVORow().getSimsCurcod())))
{ am.helpers().pMessage("Currency must be enter!","CAUTION");
 am.lib().goItem("blk3_tsi_mst.sims_curcod");
 am.getBlk3TsiMstVORow().setSimsTotamt_noVal("");
throw am.lib().getFormTriggerFailure();
/* Unreachable Statement in original code
return ;
*/}
if (Ops.and(!Ops.isNull( am.getBlk3TsiMstVORow().getSimsInvamt()),!Ops.isNull( am.getBlk3TsiMstVORow().getSimsCurcod())))
{ am.getBlk3TsiMstVORow().setSimsTotamt_noVal(Ops.concat(Ops.concat( am.getBlk3TsiMstVORow().getSimsCurcod()," "), am.lib().ltrim( am.lib().toChar( am.getBlk3TsiMstVORow().getSimsInvamt(),"99,999,990.00"),"")));
}
else
{ am.getBlk3TsiMstVORow().setSimsTotamt_noVal("");
}
;
}
public void SimsInvamt_vi(){
if (Ops.eq( am.getSystemVORow().getFormStatus(),"QUERY"))
{return ;
}
if (Ops.gt( am.getBlk3TsiMstVORow().getSimsInvamt(),new BigDecimal(99999999.99)))
{ am.getBlk3TsiMstVORow().setSimsInvamt_noVal(new BigDecimal(99999999.99));
 am.lib().message("Number cannot be larger then 99,999,999.99");
}
if (Ops.and(!Ops.isNull( am.getBlk3TsiMstVORow().getSimsInvamt()),Ops.isNull( am.getBlk3TsiMstVORow().getSimsCurcod())))
{ am.getBlk3TsiMstVORow().setSimsTotamt_noVal("");
 am.lib().goItem("blk3_tsi_mst.sims_curcod");
}
if (Ops.and(!Ops.isNull( am.getBlk3TsiMstVORow().getSimsInvamt()),!Ops.isNull( am.getBlk3TsiMstVORow().getSimsCurcod())))
{ am.getBlk3TsiMstVORow().setSimsTotamt_noVal(Ops.concat(Ops.concat( am.getBlk3TsiMstVORow().getSimsCurcod()," "), am.lib().ltrim( am.lib().toChar( am.getBlk3TsiMstVORow().getSimsInvamt(),"99,999,990.00"),"")));
}
else
{ am.getBlk3TsiMstVORow().setSimsTotamt_noVal("");
}
}
public void SimsTotamt_ken(){
 am.lib().goItem("blk4_tsi_des_d.sids_des");
}
public void MpsmDes_kni(){
 am.lib().goItem("blk4_tsi_des_d.sids_des");
}
public void Blk3LovSimsShpmodcod_bp(){
boolean  i;
BigDecimal  rc =  new BigDecimal(0);
if (Ops.eq( am.lib().populateGroup( am.lib().getLovProperty("LOV_SHPMODMST", am.lib().groupName)),new BigDecimal(0)))
{ rc =  am.lib().getGroupRowCount( am.lib().getLovProperty("LOV_SHPMODMST", am.lib().groupName));
if (Ops.gt( rc,new BigDecimal(0)))
{ am.helpers().plSetLovPosition("LOV_SHPMODMST", am.getSystemVORow().getTriggerItem());
if ( am.lib().showLov("LOV_SHPMODMST"))
{ am.lib().copy( am.getGlobalVORow().getOne(),Ops.concat( am.getWorkItemVORow().getWDetailBlk(),".SIMS_SHPMODCOD"));
 am.lib().copy( am.getGlobalVORow().getTwo(),Ops.concat( am.getWorkItemVORow().getWDetailBlk(),".MPSM_DES"));
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
public void Blk3LovCarcod_bp(){
boolean  i;
BigDecimal  rc =  new BigDecimal(0);
if (Ops.eq( am.lib().populateGroup( am.lib().getLovProperty("LOV_MPCA_CARCOD", am.lib().groupName)),new BigDecimal(0)))
{ rc =  am.lib().getGroupRowCount( am.lib().getLovProperty("LOV_MPCA_CARCOD", am.lib().groupName));
if (Ops.gt( rc,new BigDecimal(0)))
{
if (!Ops.isNull( am.getBlk3TsiMstVORow().getSimsCuscod()))
{
if (Ops.eq( am.lib().populateGroup( am.lib().getLovProperty("LOV_MPCA_CARCOD_180", am.lib().groupName)),new BigDecimal(0)))
{ rc =  am.lib().getGroupRowCount( am.lib().getLovProperty("LOV_MPCA_CARCOD_180", am.lib().groupName));
if (Ops.gt( rc,new BigDecimal(1)))
{ am.helpers().plSetLovPosition("LOV_MPCA_CARCOD_180", am.getSystemVORow().getTriggerItem());
if ( am.lib().showLov("LOV_MPCA_CARCOD_180"))
{
if (Ops.eq( am.lib().upper( am.getGlobalVORow().getOne()),"<MORE>"))
{ am.helpers().plSetLovPosition("LOV_MPCA_CARCOD", am.getSystemVORow().getTriggerItem());
if ( am.lib().showLov("LOV_MPCA_CARCOD"))
{ am.lib().copy( am.getGlobalVORow().getTwo(),Ops.concat( am.getWorkItemVORow().getWDetailBlk(),".SIMS_CARCOD"));
 i =  am.helpers().plValidateCarcod;
}
}
else
{ am.lib().copy( am.getGlobalVORow().getTwo(),Ops.concat( am.getWorkItemVORow().getWDetailBlk(),".SIMS_CARCOD"));
 i =  am.helpers().plValidateCarcod;
}
}
}
else
{ am.helpers().plSetLovPosition("LOV_MPCA_CARCOD", am.getSystemVORow().getTriggerItem());
if ( am.lib().showLov("LOV_MPCA_CARCOD"))
{ am.lib().copy( am.getGlobalVORow().getTwo(),Ops.concat( am.getWorkItemVORow().getWDetailBlk(),".SIMS_CARCOD"));
 i =  am.helpers().plValidateCarcod;
}
}
}
else
{ am.helpers().plSetLovPosition("LOV_MPCA_CARCOD", am.getSystemVORow().getTriggerItem());
if ( am.lib().showLov("LOV_MPCA_CARCOD"))
{ am.lib().copy( am.getGlobalVORow().getTwo(),Ops.concat( am.getWorkItemVORow().getWDetailBlk(),".SIMS_CARCOD"));
 i =  am.helpers().plValidateCarcod;
}
}
}
else
{ am.helpers().plSetLovPosition("LOV_MPCA_CARCOD", am.getSystemVORow().getTriggerItem());
if ( am.lib().showLov("LOV_MPCA_CARCOD"))
{ am.lib().copy( am.getGlobalVORow().getTwo(),Ops.concat( am.getWorkItemVORow().getWDetailBlk(),".SIMS_CARCOD"));
 i =  am.helpers().plValidateCarcod;
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
public void SimsShpmodcod_pot(){
boolean  i;
 i =  am.helpers().plValidateShpmod;
}
public void SimsCarcod_pot(){
boolean  i;
 i =  am.helpers().plValidateCarcod;
}
public void Blk3LovAdrcod_bp(){
boolean  i;
BigDecimal  rc =  new BigDecimal(0);
if (Ops.eq( am.lib().populateGroup( am.lib().getLovProperty("LOV_ADRCOD", am.lib().groupName)),new BigDecimal(0)))
{ rc =  am.lib().getGroupRowCount( am.lib().getLovProperty("LOV_ADRCOD", am.lib().groupName));
if (Ops.gt( rc,new BigDecimal(0)))
{ am.helpers().plSetLovPosition("LOV_ADRCOD", am.getSystemVORow().getTriggerItem());
if ( am.lib().showLov("LOV_ADRCOD"))
{ am.lib().copy( am.getGlobalVORow().getOne(),Ops.concat( am.getWorkItemVORow().getWDetailBlk(),".SIMS_ADRCOD"));
 i =  am.helpers().plValidateAdrcod;
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

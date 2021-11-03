package model.views;
public class Blk5TsiItmVOFieldEvents {
    public Blk5TsiItmVOFieldEvents(Xsim2AmImpl am) {
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
public void SiitQty_pc(){
if (Ops.and(!Ops.isNull( am.getBlk5TsiItmVORow().getSiitQty()),!Ops.isNull( am.getBlk5TsiItmVORow().getSiitItmprc())))
{ am.getBlk5TsiItmVORow().setSiitTotamt_noVal(Ops.mult( am.getBlk5TsiItmVORow().getSiitQty(), am.getBlk5TsiItmVORow().getSiitItmprc()));
}
else
{ am.getBlk5TsiItmVORow().setSiitTotamt_noVal( am.lib().toNumber(null));
}
}
public void LovSiitUom_bp(){
boolean  i;
BigDecimal  rc =  new BigDecimal(0);
if (Ops.eq( am.lib().populateGroup( am.lib().getLovProperty("LOV_MPUM", am.lib().groupName)),new BigDecimal(0)))
{ rc =  am.lib().getGroupRowCount( am.lib().getLovProperty("LOV_MPUM", am.lib().groupName));
if (Ops.gt( rc,new BigDecimal(0)))
{ am.helpers().plSetLovPosition("LOV_MPUM", am.getSystemVORow().getTriggerItem());
if ( am.lib().showLov("LOV_MPUM"))
{ am.lib().copy( am.getGlobalVORow().getOne(),"blk5_tsi_itm.siit_uom");
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
public void LovCaseno_bp(){
boolean  i;
BigDecimal  rc =  new BigDecimal(0);
if (Ops.eq( am.lib().populateGroup( am.lib().getLovProperty("LOV_PRMS_CASENO", am.lib().groupName)),new BigDecimal(0)))
{ rc =  am.lib().getGroupRowCount( am.lib().getLovProperty("LOV_PRMS_CASENO", am.lib().groupName));
if (Ops.gt( rc,new BigDecimal(0)))
{ am.helpers().plSetLovPosition("LOV_PRMS_CASENO", am.getSystemVORow().getTriggerItem());
if ( am.lib().showLov("LOV_PRMS_CASENO"))
{ am.lib().copy( am.getGlobalVORow().getOne(),"blk5_tsi_itm.case_no");
 am.lib().copy( am.getGlobalVORow().getTwo(),"blk5_tsi_itm.siit_pr_runnum");
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
public void LovSiitHarmoncod_bp(){
boolean  i;
BigDecimal  rc =  new BigDecimal(0);
if (Ops.eq( am.lib().populateGroup( am.lib().getLovProperty("LOV_MPHM", am.lib().groupName)),new BigDecimal(0)))
{ rc =  am.lib().getGroupRowCount( am.lib().getLovProperty("LOV_MPHM", am.lib().groupName));
if (Ops.gt( rc,new BigDecimal(0)))
{ am.helpers().plSetLovPosition("LOV_MPHM", am.getSystemVORow().getTriggerItem());
if ( am.lib().showLov("LOV_MPHM"))
{ am.lib().copy( am.getGlobalVORow().getOne(),"blk5_tsi_itm.siit_harmoncod");
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
public void LovSiitTyp_bp(){
boolean  i;
BigDecimal  rc =  new BigDecimal(0);
if (Ops.eq( am.lib().populateGroup( am.lib().getLovProperty("LOV_SITY", am.lib().groupName)),new BigDecimal(0)))
{ rc =  am.lib().getGroupRowCount( am.lib().getLovProperty("LOV_SITY", am.lib().groupName));
if (Ops.gt( rc,new BigDecimal(0)))
{ am.helpers().plSetLovPosition("LOV_SITY", am.getSystemVORow().getTriggerItem());
if ( am.lib().showLov("LOV_SITY"))
{ am.lib().copy( am.getGlobalVORow().getOne(),"blk5_tsi_itm.siit_typ");
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
public void SiitItmdes_ken(){
}
public void SiitItmdes_kni(){
}
public void SiitItmdes_kpi(){
}
public void SiitItmprc_pc(){
if (Ops.and(!Ops.isNull( am.getBlk5TsiItmVORow().getSiitQty()),!Ops.isNull( am.getBlk5TsiItmVORow().getSiitItmprc())))
{ am.getBlk5TsiItmVORow().setSiitTotamt_noVal(Ops.mult( am.getBlk5TsiItmVORow().getSiitQty(), am.getBlk5TsiItmVORow().getSiitItmprc()));
}
else
{ am.getBlk5TsiItmVORow().setSiitTotamt_noVal( am.lib().toNumber(null));
}
}
public void SiitItmnum_pc(){
BigDecimal  v_ipms_runnum =  new BigDecimal(0);
try {
{
DP_MIG_XSIM2.PL_TSIITM5_SIITITMNUM_PC1_results  result = DP_MIG_XSIM2.PL_TSIITM5_SIITITMNUM_PC1(am.getDBTransaction(), am.getBlk5TsiItmVORow().getSiitItmnum(), am.getGlobalVORow().getGDivcod());
 v_ipms_runnum = result.getO_V_IPMS_RUNNUM();
 am.getBlk5TsiItmVORow().setSiitHarmoncod_noVal(result.getO_SIIT_HARMONCOD());
 am.getBlk5TsiItmVORow().setSiitUom_noVal(result.getO_SIIT_UOM());
}
}
catch (Exception ex)
{ v_ipms_runnum =  am.lib().toNumber(null);
 am.getBlk5TsiItmVORow().setSiitHarmoncod_noVal("");
 am.getBlk5TsiItmVORow().setSiitUom_noVal("");
}
if (Ops.ne( am.lib().nvl( v_ipms_runnum,new BigDecimal(0)),new BigDecimal(0)))
{try {
{
DP_MIG_XSIM2.PL_TSIITM5_SIITITMNUM_PC2_results  result = DP_MIG_XSIM2.PL_TSIITM5_SIITITMNUM_PC2(am.getDBTransaction(), am.getBlk3TsiMstVORow().getSimsCurcod(), v_ipms_runnum, am.getBlk3TsiMstVORow().getSimsCuscod(), am.getGlobalVORow().getGDivcod());
 am.getBlk5TsiItmVORow().setSiitCusitm_noVal(result.getO_SIIT_CUSITM());
 am.getBlk5TsiItmVORow().setSiitItmprc_noVal(result.getO_SIIT_ITMPRC());
}
}
catch (NoDataFound ex)
{try {
{
DP_MIG_XSIM2.PL_TSIITM5_SIITITMNUM_PC3_results  result = DP_MIG_XSIM2.PL_TSIITM5_SIITITMNUM_PC3(am.getDBTransaction(), am.getBlk3TsiMstVORow().getSimsCurcod(), v_ipms_runnum, am.getGlobalVORow().getGDivcod());
 am.getBlk5TsiItmVORow().setSiitCusitm_noVal(result.getO_SIIT_CUSITM());
 am.getBlk5TsiItmVORow().setSiitItmprc_noVal(result.getO_SIIT_ITMPRC());
}
}
catch (Exception ex)
{ am.getBlk5TsiItmVORow().setSiitCusitm_noVal("");
}
}
catch (Exception ex)
{ am.getBlk5TsiItmVORow().setSiitCusitm_noVal("");
}
try {
{
DP_MIG_XSIM2.PL_TSIITM5_SIITITMNUM_PC4_results  result = DP_MIG_XSIM2.PL_TSIITM5_SIITITMNUM_PC4(am.getDBTransaction(), v_ipms_runnum, am.getBlk5TsiItmVORow().getSiitSupcod(), am.getGlobalVORow().getGDivcod());
 am.getBlk5TsiItmVORow().setSiitSupcod_noVal(result.getO_SIIT_SUPCOD());
 am.getBlk5TsiItmVORow().setSiitSupitm_noVal(result.getO_SIIT_SUPITM());
}
}
catch (NoDataFound ex)
{try {
{
DP_MIG_XSIM2.PL_TSIITM5_SIITITMNUM_PC5_results  result = DP_MIG_XSIM2.PL_TSIITM5_SIITITMNUM_PC5(am.getDBTransaction(), v_ipms_runnum, am.getGlobalVORow().getGDivcod());
 am.getBlk5TsiItmVORow().setSiitSupcod_noVal(result.getO_SIIT_SUPCOD());
 am.getBlk5TsiItmVORow().setSiitSupitm_noVal(result.getO_SIIT_SUPITM());
}
}
catch (Exception ex)
{ am.getBlk5TsiItmVORow().setSiitSupcod_noVal("");
 am.getBlk5TsiItmVORow().setSiitSupitm_noVal("");
}
}
catch (Exception ex)
{ am.getBlk5TsiItmVORow().setSiitSupcod_noVal("");
 am.getBlk5TsiItmVORow().setSiitSupitm_noVal("");
}
try {
{
DP_MIG_XSIM2.PL_TSIITM5_SIITITMNUM_PC6_results  result = DP_MIG_XSIM2.PL_TSIITM5_SIITITMNUM_PC6(am.getDBTransaction(), v_ipms_runnum, am.getGlobalVORow().getGDivcod());
 am.getBlk5TsiItmVORow().setSiitItmdes_noVal(result.getO_SIIT_ITMDES());
}
}
catch (Exception ex)
{ am.getBlk5TsiItmVORow().setSiitItmdes_noVal("");
}
}
}
public void SiitSupcod_prt(){
 am.getWorkItemVORow().setWChar_noVal( am.getBlk5TsiItmVORow().getSiitSupcod());
}
public void SiitSupcod_pot(){
if (Ops.ne( am.lib().nvl( am.getWorkItemVORow().getWChar(),"!@#$%^&*"), am.lib().nvl( am.getBlk5TsiItmVORow().getSiitSupcod(),"!@#$%^&*")))
{try {
{
DP_MIG_XSIM2.PL_TSIITM5_SIITSUPCOD_POT1_results  result = DP_MIG_XSIM2.PL_TSIITM5_SIITSUPCOD_POT1(am.getDBTransaction(), am.getBlk5TsiItmVORow().getSiitItmnum(), am.getBlk5TsiItmVORow().getSiitSupcod(), am.getGlobalVORow().getGDivcod());
 am.getBlk5TsiItmVORow().setSiitSupitm_noVal(result.getO_SIIT_SUPITM());
}
}
catch (NoDataFound ex)
{try {
{
DP_MIG_XSIM2.PL_TSIITM5_SIITSUPCOD_POT2_results  result = DP_MIG_XSIM2.PL_TSIITM5_SIITSUPCOD_POT2(am.getDBTransaction(), am.getBlk5TsiItmVORow().getSiitItmnum(), am.getGlobalVORow().getGDivcod());
 am.getBlk5TsiItmVORow().setSiitSupitm_noVal(result.getO_SIIT_SUPITM());
}
}
catch (Exception ex)
{ am.getBlk5TsiItmVORow().setSiitSupitm_noVal("");
}
}
catch (Exception ex)
{ am.getBlk5TsiItmVORow().setSiitSupitm_noVal("");
}
}
}
public void CaseNo_prt(){
 am.getWorkItemVORow().setWChar_noVal( am.getBlk5TsiItmVORow().getCaseNo());
}
public void CaseNo_pot(){
if (Ops.ne( am.lib().nvl( am.getWorkItemVORow().getWChar(),"!@#$%^&*"), am.lib().nvl( am.getBlk5TsiItmVORow().getCaseNo(),"!@#$%^&*")))
{try {
{
DP_MIG_XSIM2.PL_TSIITM5_CASENO_POT1_results  result = DP_MIG_XSIM2.PL_TSIITM5_CASENO_POT1(am.getDBTransaction(), am.getBlk5TsiItmVORow().getCaseNo(), am.getGlobalVORow().getGDivcod());
 am.getBlk5TsiItmVORow().setSiitPrRunnum_noVal(result.getO_SIIT_PR_RUNNUM());
}
}
catch (Exception ex)
{ am.getBlk5TsiItmVORow().setSiitPrRunnum_noVal( am.lib().toNumber(null));
}
}
}
public void LovSiitSupcod_bp(){
boolean  i;
BigDecimal  rc =  new BigDecimal(0);
if (Ops.eq( am.lib().populateGroup( am.lib().getLovProperty("LOV_SPMS", am.lib().groupName)),new BigDecimal(0)))
{ rc =  am.lib().getGroupRowCount( am.lib().getLovProperty("LOV_SPMS", am.lib().groupName));
if (Ops.gt( rc,new BigDecimal(0)))
{ am.helpers().plSetLovPosition("LOV_SPMS", am.getSystemVORow().getTriggerItem());
if ( am.lib().showLov("LOV_SPMS"))
{ am.lib().copy( am.getGlobalVORow().getOne(),"blk5_tsi_itm.siit_supcod");
try {
{
DP_MIG_XSIM2.PL_TSIITM5_LOVSIITSUPCOD_BP1_results  result = DP_MIG_XSIM2.PL_TSIITM5_LOVSIITSUPCOD_BP1(am.getDBTransaction(), am.getBlk5TsiItmVORow().getSiitItmnum(), am.getBlk5TsiItmVORow().getSiitSupcod(), am.getGlobalVORow().getGDivcod());
 am.getBlk5TsiItmVORow().setSiitSupitm_noVal(result.getO_SIIT_SUPITM());
}
}
catch (NoDataFound ex)
{try {
{
DP_MIG_XSIM2.PL_TSIITM5_LOVSIITSUPCOD_BP2_results  result = DP_MIG_XSIM2.PL_TSIITM5_LOVSIITSUPCOD_BP2(am.getDBTransaction(), am.getBlk5TsiItmVORow().getSiitItmnum(), am.getGlobalVORow().getGDivcod());
 am.getBlk5TsiItmVORow().setSiitSupitm_noVal(result.getO_SIIT_SUPITM());
}
}
catch (Exception ex)
{ am.getBlk5TsiItmVORow().setSiitSupitm_noVal("");
}
}
catch (Exception ex)
{ am.getBlk5TsiItmVORow().setSiitSupitm_noVal("");
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

package model.views;
public class Blk5ControlVOFieldEvents {
    public Blk5ControlVOFieldEvents(Xsim2AmImpl am) {
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
public void Blk7bCancel_bp(){
 am.lib().issueRollback("itmdes");
 am.lib().goBlock("blk5_tsi_itm");
 am.lib().goItem("blk5_tsi_itm.siit_itmnum");
 am.lib().showView("blk5_can");
 am.lib().showView("blk5_sub_can");
}
public void Blk5Desc_bp(){
BigDecimal  i = new BigDecimal(1);
 am.lib().issueSavepoint("itmdes");
 am.lib().pstdCenterWindow("win_desc");
 am.getBlk5ControlVORow().setSiitItmnum_noVal( am.getBlk5TsiItmVORow().getSiitItmnum());
 am.getBlk5ControlVORow().setSiitCusitm_noVal( am.getBlk5TsiItmVORow().getSiitCusitm());
 am.getBlk5ControlVORow().setSiitSupcod_noVal( am.getBlk5TsiItmVORow().getSiitSupcod());
 am.getBlk5ControlVORow().setSiitSupitm_noVal( am.getBlk5TsiItmVORow().getSiitSupitm());
 am.lib().goItem("blk5_tsi_itm.siit_itmdes");
 am.lib().showView("BLK5_DESC_CAN");
}
public void Blk5Ok_bp(){
 am.lib().post();
 am.lib().goBlock("blk3_tsi_mst");
 am.lib().executeQuery();
if (Ops.ne( am.getSystemVORow().getFormStatus(),"QUERY"))
{ am.lib().pstdDisplayError();
}
else
{BigDecimal  inv_amt =  new BigDecimal(0);
{
DP_MIG_XSIM2.PL_CONTROL5_OK_BP1_results  result = DP_MIG_XSIM2.PL_CONTROL5_OK_BP1(am.getDBTransaction(), am.getBlk3TsiMstVORow().getSimsSaminvrun(), am.getGlobalVORow().getGDivcod());
 am.getBlk3TsiMstVORow().setSimsTotamt_noVal(result.getO_SIMS_TOTAMT());
 inv_amt = result.getO_INV_AMT();
}
if (Ops.gt( inv_amt,new BigDecimal(99999999.99)))
{ am.getBlk3TsiMstVORow().setSimsInvamt_noVal(new BigDecimal(99999999.99));
}
else
{ am.getBlk3TsiMstVORow().setSimsInvamt_noVal( inv_amt);
}
;
BigDecimal  i =  new BigDecimal(0);
try {
{
DP_MIG_XSIM2.PL_CONTROL5_OK_BP2_results  result = DP_MIG_XSIM2.PL_CONTROL5_OK_BP2(am.getDBTransaction(), am.getBlk3TsiMstVORow().getSimsSaminvrun(), am.getGlobalVORow().getGDivcod());
 i = result.getO_I();
}
if (Ops.gt( i,new BigDecimal(0)))
{ am.lib().setItemProperty(Ops.concat( am.getWorkItemVORow().getWDetailBlk(),".sims_totamt"), am.lib().visualAttribute,"VA_READ_ONLY");
 am.lib().setItemProperty(Ops.concat( am.getWorkItemVORow().getWDetailBlk(),".sims_totamt"), am.lib().updateable, am.lib().propertyFalse);
 am.lib().setItemProperty(Ops.concat( am.getWorkItemVORow().getWDetailBlk(),".sims_invamt"), am.lib().visualAttribute,"VA_READ_ONLY");
 am.lib().setItemProperty(Ops.concat( am.getWorkItemVORow().getWDetailBlk(),".sims_invamt"), am.lib().updateable, am.lib().propertyFalse);
}
else
{ am.getBlk3TsiMstVORow().setSimsInvamt_noVal("");
 am.getBlk3TsiMstVORow().setSimsTotamt_noVal("");
 am.getBlk3TsiMstVORow().setSimsCurcod_noVal("");
 am.lib().setItemProperty(Ops.concat( am.getWorkItemVORow().getWDetailBlk(),".sims_totamt"), am.lib().visualAttribute,"VA_EDIT_ONLY");
 am.lib().setItemProperty(Ops.concat( am.getWorkItemVORow().getWDetailBlk(),".sims_totamt"), am.lib().updateable, am.lib().propertyTrue);
 am.lib().setItemProperty(Ops.concat( am.getWorkItemVORow().getWDetailBlk(),".sims_invamt"), am.lib().visualAttribute,"VA_EDIT_ONLY");
 am.lib().setItemProperty(Ops.concat( am.getWorkItemVORow().getWDetailBlk(),".sims_invamt"), am.lib().updateable, am.lib().propertyTrue);
}
}
catch (Exception ex)
{;
}
 am.getWorkItemVORow().setWChanged_noVal("Y");
 am.getWorkItemVORow().setWDtlBlock_noVal("HDR");
 am.helpers().plSetMenuSingleRec();
 am.helpers().plGoToTab("BLK3_GEN");
}
}
public void Blk5Delete_bp(){
if (Ops.eq( am.getWorkItemVORow().getWCurrentBlk(),"BLK5_TSI_ITM"))
{
if (Ops.eq( am.lib().fstdConfirmAction("Confirm Delete record","Delete highlight record"),new BigDecimal(88)))
{ am.lib().goBlock("BLK5_TSI_ITM");
 am.lib().deleteRecord();
 am.getWorkItemVORow().setWChanged_noVal("Y");
}
}
;
}
public void Blk5Cancel_bp(){
 am.lib().issueRollback("additm");
 am.lib().goBlock("blk5_tsi_itm");
 am.lib().clearBlock( am.lib().noValidate);
 am.lib().goBlock( am.getWorkItemVORow().getWDetailBlk());
 am.lib().goItem("blk3_tsi_mst.blk3_additm");
}
public void Blk7bPrev_bp(){
 am.lib().goBlock("blk5_tsi_itm");
if (Ops.eq( am.getSystemVORow().getCursorRecord(),"1"))
{ am.getWorkItemVORow().setWErrmsg_noVal( am.lib().fstdUsrMsg("875"));
 am.getWorkItemVORow().setWErritm_noVal("");
 am.lib().pstdDisplayError();
 am.lib().goItem("blk5_tsi_itm.siit_itmdes");
}
else
{ am.lib().previousRecord();
 am.lib().goItem("blk5_control.blk5_desc");
 am.lib().executeTrigger("WHEN-BUTTON-PRESSED");
 am.lib().goItem("blk5_tsi_itm.siit_itmdes");
}
}
public void Blk7bNext_bp(){
 am.lib().goBlock("blk5_tsi_itm");
if (Ops.eq( am.getSystemVORow().getLastRecord(),"TRUE"))
{ am.getWorkItemVORow().setWErrmsg_noVal( am.lib().fstdUsrMsg("876"));
 am.getWorkItemVORow().setWErritm_noVal("");
 am.lib().pstdDisplayError();
 am.lib().goItem("blk5_tsi_itm.siit_itmdes");
}
else
{ am.lib().nextRecord();
 am.lib().goItem("blk5_control.blk5_desc");
 am.lib().executeTrigger("WHEN-BUTTON-PRESSED");
 am.lib().goItem("blk5_tsi_itm.siit_itmdes");
}
}
public void Blk7bLast_bp(){
 am.lib().goBlock("blk5_tsi_itm");
if (Ops.eq( am.getSystemVORow().getLastRecord(),"TRUE"))
{ am.getWorkItemVORow().setWErrmsg_noVal( am.lib().fstdUsrMsg("876"));
 am.getWorkItemVORow().setWErritm_noVal("");
 am.lib().pstdDisplayError();
 am.lib().goItem("blk5_tsi_itm.siit_itmdes");
}
else
{ am.lib().lastRecord();
 am.lib().goItem("blk5_control.blk5_desc");
 am.lib().executeTrigger("WHEN-BUTTON-PRESSED");
 am.lib().goItem("blk5_tsi_itm.siit_itmdes");
}
}
public void Blk7bDelete_bp(){
 am.getBlk5TsiItmVORow().setSiitItmdes_noVal("");
}
public void Blk7bOk_bp(){
 am.lib().goItem("blk5_tsi_itm.siit_itmnum");
 am.lib().showView("blk5_can");
 am.lib().showView("blk5_sub_can");
}
public void Blk7bCopyDes_bp(){
 am.lib().goBlock("itmrmk_blk");
 am.lib().showView("ITMRMK_CAN");
 am.getItmrmkBlkVORow().setMprmRmkcod_noVal("");
}
public void SiitItmnum_pc(){
try {
{
DP_MIG_XSIM2.PL_CONTROL5_SIITITMNUM_PC1_results  result = DP_MIG_XSIM2.PL_CONTROL5_SIITITMNUM_PC1(am.getDBTransaction(), am.getBlk5TsiItmVORow().getSiitItmnum(), am.getGlobalVORow().getGDivcod());
 am.getBlk5TsiItmVORow().setSiitHarmoncod_noVal(result.getO_SIIT_HARMONCOD());
}
}
catch (Exception ex)
{ am.getBlk5TsiItmVORow().setSiitHarmoncod_noVal("");
}
}
public void Blk7bFirst_bp(){
 am.lib().goBlock("blk5_tsi_itm");
if (Ops.eq( am.getSystemVORow().getCursorRecord(),"1"))
{ am.getWorkItemVORow().setWErrmsg_noVal( am.lib().fstdUsrMsg("875"));
 am.getWorkItemVORow().setWErritm_noVal("");
 am.lib().pstdDisplayError();
 am.lib().goItem("blk5_tsi_itm.siit_itmdes");
}
else
{ am.lib().firstRecord();
 am.lib().goItem("blk5_control.blk5_desc");
 am.lib().executeTrigger("WHEN-BUTTON-PRESSED");
 am.lib().goItem("blk5_tsi_itm.siit_itmdes");
}
}

}

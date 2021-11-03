package model.views;
public class SamBlkVOFieldEvents {
    public SamBlkVOFieldEvents(Xsim2AmImpl am) {
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
public void SamCancel_bp(){
 am.lib().goBlock( am.getWorkItemVORow().getWMainBlk());
}
public void SamReset_bp(){
 am.lib().clearBlock();
}
public void SamOk_bp(){
BigDecimal  status =  new BigDecimal(0);
BigDecimal  i =  new BigDecimal(0);
if (Ops.isNull( am.getSamBlkVORow().getSamDptcod()))
{ am.getWorkItemVORow().setWErrmsg_noVal( am.lib().fstdUsrMsg("182"));
 am.getWorkItemVORow().setWErritm_noVal("sam_blk.sam_dptcod");
 am.lib().pstdDisplayError();
return ;
}
{
DP_MIG_XSIM2.PL_SAMSAM_SAMOK_BP1_results  result = DP_MIG_XSIM2.PL_SAMSAM_SAMOK_BP1(am.getDBTransaction(), am.getSamBlkVORow().getSamDptcod(), am.getBlk2CriteriaVORow().getSimsDivcod(), am.getGlobalVORow().getGDivcod(), am.getGlobalVORow().getGUserid());
 i = result.getO_I();
}
if (Ops.eq( i,new BigDecimal(0)))
{ am.getWorkItemVORow().setWErrmsg_noVal( am.lib().fstdUsrMsg("340"));
 am.getWorkItemVORow().setWErritm_noVal("sam_blk.sam_dptcod");
 am.lib().pstdDisplayError();
return ;
}
if (Ops.isNull( am.getSamBlkVORow().getSamYear()))
{ am.getWorkItemVORow().setWErrmsg_noVal( am.lib().fstdUsrMsg("2048"));
 am.getWorkItemVORow().setWErritm_noVal("sam_blk.sam_year");
 am.lib().pstdDisplayError();
return ;
}
if (Ops.or(Ops.or(Ops.lt( am.lib().substr( am.getSamBlkVORow().getSamYear(),new BigDecimal(1),new BigDecimal(1)),"0"),Ops.gt( am.lib().substr( am.getSamBlkVORow().getSamYear(),new BigDecimal(1),new BigDecimal(1)),"9")),Ops.or(Ops.lt( am.lib().substr( am.getSamBlkVORow().getSamYear(),new BigDecimal(2),new BigDecimal(1)),"0"),Ops.gt( am.lib().substr( am.getSamBlkVORow().getSamYear(),new BigDecimal(2),new BigDecimal(1)),"9"))))
{ am.getWorkItemVORow().setWErrmsg_noVal( am.lib().fstdUsrMsg("2049"));
 am.getWorkItemVORow().setWErritm_noVal("sam_blk.sam_year");
 am.lib().pstdDisplayError();
return ;
}
{
DP_MIG_XSIM2.PL_SAMSAM_SAMOK_BP2_results  result = DP_MIG_XSIM2.PL_SAMSAM_SAMOK_BP2(am.getDBTransaction(), am.getSamBlkVORow().getSamYear());
 am.getSamBlkVORow().setSamYear_noVal(result.getO_SAM_YEAR());
}
 am.lib().copy("A","work_item.w_edit_mode");
 am.lib().pstdDisableItemEdit("blk3_control.blk3_first_rec");
 am.lib().pstdDisableItemEdit("blk3_control.blk3_prv_rec");
 am.lib().pstdDisableItemEdit("blk3_control.blk3_next_rec");
 am.lib().pstdDisableItemEdit("blk3_control.blk3_last_rec");
 am.lib().goBlock("blk3_tsi_mst");
 am.lib().clearBlock();
 am.getBlk3TsiMstVORow().setSimsTyp_noVal( am.getSamBlkVORow().getSamType());
 am.getBlk3TsiMstVORow().setSimsDptcod_noVal( am.getSamBlkVORow().getSamDptcod());
 am.getBlk3TsiMstVORow().setSimsYear_noVal( am.getSamBlkVORow().getSamYear());
 am.lib().goItem("BLK3_TSI_MST.SIMS_CHRTYP");
 am.helpers().plGoToTab("BLK3_GEN");
 am.getWorkItemVORow().setWCurBlock_noVal("DTL");
 am.getBlk3TsiMstVORow().setSimsTyp_noVal("S");
 am.helpers().plSetMenuSingleRec();
 am.lib().setItemProperty(Ops.concat( am.getWorkItemVORow().getWDetailBlk(),".sims_totamt"), am.lib().visualAttribute,"VA_EDIT_ONLY");
 am.lib().setItemProperty(Ops.concat( am.getWorkItemVORow().getWDetailBlk(),".sims_totamt"), am.lib().updateable, am.lib().propertyTrue);
 am.lib().setItemProperty(Ops.concat( am.getWorkItemVORow().getWDetailBlk(),".sims_invamt"), am.lib().visualAttribute,"VA_EDIT_ONLY");
 am.lib().setItemProperty(Ops.concat( am.getWorkItemVORow().getWDetailBlk(),".sims_invamt"), am.lib().updateable, am.lib().propertyTrue);
if (Ops.ne( DP_CASE.CHECK_DPT_SETCOD(am.getDBTransaction(), am.getGlobalVORow().getGDivcod(), am.getBlk3TsiMstVORow().getSimsDptcod(), am.getBlk3TsiMstVORow().getSimsCuscod(),"J73"),"N"))
{ am.lib().setItemProperty("BLK1_CONTROL.CHARGE_TYPE", am.lib().visualAttribute,"VA_PROMPT_FIELD_REQ");
}
}
public void SamLovOpdp_bp(){
BigDecimal  rc =  new BigDecimal(0);
if (Ops.eq( am.lib().populateGroup( am.lib().getLovProperty("lov_blk2_opdp_dptcod", am.lib().groupName)),new BigDecimal(0)))
{ rc =  am.lib().getGroupRowCount( am.lib().getLovProperty("lov_blk2_opdp_dptcod", am.lib().groupName));
if (Ops.gt( rc,new BigDecimal(0)))
{ am.helpers().plSetLovPosition("lov_blk2_opdp_dptcod", am.getSystemVORow().getTriggerItem());
if ( am.lib().showLov("lov_blk2_opdp_dptcod"))
{ am.lib().copy( am.getGlobalVORow().getOne(),"sam_blk.sam_dptcod");
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

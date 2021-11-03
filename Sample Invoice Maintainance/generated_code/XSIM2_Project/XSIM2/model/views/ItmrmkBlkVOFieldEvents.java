package model.views;
public class ItmrmkBlkVOFieldEvents {
    public ItmrmkBlkVOFieldEvents(Xsim2AmImpl am) {
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
public void RemarkReset_bp(){
 am.lib().clearBlock();
}
public void RemarkOk_bp(){
try {
 am.lib().goItem("blk5_tsi_itm.siit_itmdes");
 am.lib().selectAll();
 am.lib().cutRegion();
{
DP_MIG_XSIM2.PL_ITMRMKITMRMK_REMARKOK_BP1_results  result = DP_MIG_XSIM2.PL_ITMRMKITMRMK_REMARKOK_BP1(am.getDBTransaction(), am.getItmrmkBlkVORow().getMprmRmkcod(), am.getGlobalVORow().getGDivcod());
 am.getBlk5TsiItmVORow().setSiitItmdes_noVal(result.getO_SIIT_ITMDES());
}
 am.lib().pasteRegion();
 am.lib().goItem("blk5_tsi_itm.siit_itmdes");
 am.lib().showView("BLK5_DESC_CAN");
}
catch (NoDataFound ex)
{ am.lib().goItem("blk5_tsi_itm.siit_itmdes");
 am.lib().showView("BLK5_DESC_CAN");
}
}
public void RemarkCancel_bp(){
 am.lib().goItem("blk5_tsi_itm.siit_itmdes");
 am.lib().showView("BLK5_DESC_CAN");
}
public void Blk3Mprm_bp(){
BigDecimal  rc =  new BigDecimal(0);
if (Ops.eq( am.lib().populateGroup( am.lib().getLovProperty("lov_mprm", am.lib().groupName)),new BigDecimal(0)))
{ rc =  am.lib().getGroupRowCount( am.lib().getLovProperty("lov_mprm", am.lib().groupName));
if (Ops.gt( rc,new BigDecimal(0)))
{ am.helpers().plSetLovPosition("lov_mprm", am.getSystemVORow().getTriggerItem());
if ( am.lib().showLov("lov_mprm"))
{ am.lib().copy( am.getGlobalVORow().getOne(),"itmrmk_blk.mprm_rmkcod");
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

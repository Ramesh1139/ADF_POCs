package model.views;
public class PrintBlkVOFieldEvents {
    public PrintBlkVOFieldEvents(Xsim2AmImpl am) {
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
public void ListDes_pc(){
 am.getRemarkBlkVORow().setInvoicenoOrRemark_noVal("C");
}
public void PrintOk_bp(){
BigDecimal  vcount = new BigDecimal(0);
BigDecimal  status =  new BigDecimal(0);
if (Ops.and(Ops.isNull( am.getPrintBlkVORow().getSimsInvnoFrom()),Ops.isNull( am.getPrintBlkVORow().getSimsInvnoTo())))
{ am.getWorkItemVORow().setWErrmsg_noVal( am.lib().fstdUsrMsg("1032"));
 am.lib().setAlertProperty("AL_STD_WARN", am.lib().alertMessageText, am.getWorkItemVORow().getWErrmsg());
 status =  am.lib().showAlert("AL_STD_WARN");
 am.lib().goItem("PRINT_BLK.SIMS_INVNO_FROM");
return ;
}
{
DP_MIG_XSIM2.PL_PRINTPRINT_PRINTOK_BP1_results  result = DP_MIG_XSIM2.PL_PRINTPRINT_PRINTOK_BP1(am.getDBTransaction(), am.getWorkItemVORow().getWDiv(), am.getPrintBlkVORow().getSimsInvnoFrom(), am.getPrintBlkVORow().getSimsInvnoTo(), am.getBlk2CriteriaVORow().getSimsClsdat());
 vcount = result.getO_VCOUNT();
}
if (Ops.gt( vcount,new BigDecimal(0)))
{ am.helpers().plPrintSaminv( am.getPrintBlkVORow().getSimsInvnoFrom(), am.getPrintBlkVORow().getSimsInvnoTo());
}
else
{ am.getWorkItemVORow().setWErrmsg_noVal( am.lib().fstdUsrMsg("1033"));
 am.lib().setAlertProperty("AL_STD_WARN", am.lib().alertMessageText, am.getWorkItemVORow().getWErrmsg());
 status =  am.lib().showAlert("AL_STD_WARN");
 am.lib().goItem("PRINT_BLK.SIMS_INVNO_FROM");
}
}
public void PrintReset_bp(){
 am.lib().clearBlock();
}
public void PrintCancel_bp(){
 am.lib().goBlock( am.getWorkItemVORow().getWCurrentBlk());
}
public void PrintLovSimsInvnoFrom_bp(){
BigDecimal  rc =  new BigDecimal(0);
if (Ops.eq( am.lib().populateGroup( am.lib().getLovProperty("lov_sims_invno", am.lib().groupName)),new BigDecimal(0)))
{ rc =  am.lib().getGroupRowCount( am.lib().getLovProperty("lov_sims_invno", am.lib().groupName));
if (Ops.gt( rc,new BigDecimal(0)))
{ am.helpers().plSetLovPosition("lov_sims_invno", am.getSystemVORow().getTriggerItem());
if ( am.lib().showLov("lov_sims_invno"))
{ am.lib().copy( am.getGlobalVORow().getTwo(),"print_blk.sims_saminvrun_from");
 am.lib().copy( am.getGlobalVORow().getOne(),"print_blk.sims_invno_from");
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
public void PrintLovSimsInvnoTo_bp(){
BigDecimal  rc =  new BigDecimal(0);
if (Ops.eq( am.lib().populateGroup( am.lib().getLovProperty("lov_sims_invno", am.lib().groupName)),new BigDecimal(0)))
{ rc =  am.lib().getGroupRowCount( am.lib().getLovProperty("lov_sims_invno", am.lib().groupName));
if (Ops.gt( rc,new BigDecimal(0)))
{ am.helpers().plSetLovPosition("lov_sims_invno", am.getSystemVORow().getTriggerItem());
if ( am.lib().showLov("lov_sims_invno"))
{ am.lib().copy( am.getGlobalVORow().getTwo(),"print_blk.sims_saminvrun_to");
 am.lib().copy( am.getGlobalVORow().getOne(),"print_blk.sims_invno_to");
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

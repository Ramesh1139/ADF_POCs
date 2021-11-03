package model.views;
public class MainBlockVOFieldEvents {
    public MainBlockVOFieldEvents(Xsirpm2AmImpl am) {
        super();
this.am = am;
    }
    Xsirpm2AmImpl am;
    public Xsirpm2AmImpl getAm() {
        return am;
    }
    public void setAm(Xsirpm2AmImpl am) {
        this.am = am;
    }
public void ButtonInvReport_bp(){
boolean  dummy;
 dummy =  am.lib().showLov("lov_invrep");
}
public void InvBackgrd_pc(){
BigDecimal  status =  new BigDecimal(0);
}
public void InvBackgrd_vi(){
if (Ops.isNull( am.getMainBlockVORow().getInvBackgrd()))
{ am.getMainBlockVORow().setInvRepcode_noVal("");
}
}
public void ButtonSimsInvnoTo_bp(){
BigDecimal  rc =  new BigDecimal(0);
if (Ops.eq( am.lib().populateGroup( am.lib().getLovProperty("lov_sims_invno", am.lib().groupName)),new BigDecimal(0)))
{ rc =  am.lib().getGroupRowCount( am.lib().getLovProperty("lov_sims_invno", am.lib().groupName));
if (Ops.gt( rc,new BigDecimal(0)))
{
if ( am.lib().showLov("lov_sims_invno"))
{ am.lib().copy( am.lib().nameIn("global.one"),Ops.concat( am.lib().nameIn("work_item.w_main_blk"),".sims_invno_to"));
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
public void ButtonSimsInvnoFrom_bp(){
BigDecimal  rc =  new BigDecimal(0);
if (Ops.eq( am.lib().populateGroup( am.lib().getLovProperty("lov_sims_invno", am.lib().groupName)),new BigDecimal(0)))
{ rc =  am.lib().getGroupRowCount( am.lib().getLovProperty("lov_sims_invno", am.lib().groupName));
if (Ops.gt( rc,new BigDecimal(0)))
{
if ( am.lib().showLov("lov_sims_invno"))
{ am.lib().copy( am.lib().nameIn("global.one"),Ops.concat( am.lib().nameIn("work_item.w_main_blk"),".sims_invno_from"));
if (Ops.isNull( am.lib().nameIn(Ops.concat( am.getWorkItemVORow().getWMainBlk(),".sims_invno_to"))))
{ am.lib().copy( am.lib().nameIn("global.one"),Ops.concat( am.lib().nameIn("work_item.w_main_blk"),".sims_invno_to"));
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
public void PrintLaser_rc(){
if (Ops.eq( am.getMainBlockVORow().getPrintLaser(),"Y"))
{ am.getMainBlockVORow().setNoCopies_noVal(new BigDecimal(5));
}
else
{ am.getMainBlockVORow().setNoCopies_noVal(new BigDecimal(1));
}
}

}

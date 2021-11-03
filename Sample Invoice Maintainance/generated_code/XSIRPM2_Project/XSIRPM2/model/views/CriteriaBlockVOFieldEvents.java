package model.views;
public class CriteriaBlockVOFieldEvents {
    public CriteriaBlockVOFieldEvents(Xsirpm2AmImpl am) {
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
public void Blk2Reset_bp(){
 am.lib().pstdBlk2Reset();
}
public void Blk2Ok_bp(){
 am.helpers().pstdBlk2OkWithDiv("SIMS_DIVCOD");
}
public void Blk2Cancel_bp(){
 am.lib().pstdBlk2Cancel();
}
public void ButtonDivcod_bp(){
BigDecimal  rc =  new BigDecimal(0);
if (Ops.eq( am.lib().populateGroup( am.lib().getLovProperty("lov_opdv_divcod", am.lib().groupName)),new BigDecimal(0)))
{ rc =  am.lib().getGroupRowCount( am.lib().getLovProperty("lov_opdv_divcod", am.lib().groupName));
if (Ops.gt( rc,new BigDecimal(0)))
{
if ( am.lib().showLov("lov_opdv_divcod"))
{ am.lib().copy( am.getGlobalVORow().getOne(),Ops.concat( am.getWorkItemVORow().getWCriteriaBlk(),".sims_divcod"));
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

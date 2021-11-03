package model.views;
public class Blk6UpdlstVOFieldEvents {
    public Blk6UpdlstVOFieldEvents(Xsim2AmImpl am) {
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
public void SimsSnddat_pot(){
 am.lib().setItemProperty( am.getSystemVORow().getTriggerItem(), am.lib().formatMask,"DD-MON-YYYY");
boolean  i;
 i =  am.helpers().plValidateCarcod;
}
public void SimsPrt_cc(){
if ( am.lib().checkboxChecked("blk6_updlst.SIMS_PRT"))
{ am.getBlk6ControlVORow().setTotalSelected_noVal(Ops.plus( am.getBlk6ControlVORow().getTotalSelected(),new BigDecimal(1)));
}
else
{ am.getBlk6ControlVORow().setTotalSelected_noVal(Ops.minus( am.getBlk6ControlVORow().getTotalSelected(),new BigDecimal(1)));
}
}

}

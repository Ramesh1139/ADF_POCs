package model.views;
public class Blk1TsiMstVOFieldEvents {
    public Blk1TsiMstVOFieldEvents(Xsim2AmImpl am) {
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
public void Blk1Chk_cc(){
if (Ops.eq( am.getBlk1TsiMstVORow().getBlk1Chk(),"Y"))
{ am.getBlk1ControlVORow().setChkBoxCheckedNum_noVal(Ops.plus( am.lib().nvl( am.getBlk1ControlVORow().getChkBoxCheckedNum(),new BigDecimal(0)),new BigDecimal(1)));
}
else
{ am.getBlk1ControlVORow().setChkBoxCheckedNum_noVal(Ops.minus( am.lib().nvl( am.getBlk1ControlVORow().getChkBoxCheckedNum(),new BigDecimal(0)),new BigDecimal(1)));
}
}

}

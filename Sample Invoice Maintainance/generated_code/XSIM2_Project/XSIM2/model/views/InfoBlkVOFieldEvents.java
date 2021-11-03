package model.views;
public class InfoBlkVOFieldEvents {
    public InfoBlkVOFieldEvents(Xsim2AmImpl am) {
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
public void InfoDiv_mc(){
if (Ops.eq( am.lib().nameIn("WORK_ITEM.W_CURRENT_BLK"),"BLK3_TSI_MST"))
{ am.lib().pstdBlk3Exit();
}
if (Ops.ne( am.getSystemVORow().getFormStatus(),"CHANGED"))
{ am.lib().pstdBlk1Find();
}
}

}

package model.views;
public class RemarkBlkVOEvents {
    public RemarkBlkVOEvents(Xsim2AmImpl am) {
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

public void prb(){
 am.lib().pstdCenterWindow("win_remark");
 am.getRemarkBlkVORow().setSimsInvno_noVal("");
 am.getRemarkBlkVORow().setMprmRmkcod_noVal("");
}

}

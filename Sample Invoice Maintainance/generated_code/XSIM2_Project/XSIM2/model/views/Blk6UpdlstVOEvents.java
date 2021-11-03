package model.views;
public class Blk6UpdlstVOEvents {
    public Blk6UpdlstVOEvents(Xsim2AmImpl am) {
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
 am.lib().copy("BLK6_UPDLST","work_item.w_current_blk");
 am.lib().showView("blk6_can");
 am.lib().showView("info_can");
}
public void poq(){
try {
{
DP_MIG_XSIM2.PL_UPDLST6_POQ1_results  result = DP_MIG_XSIM2.PL_UPDLST6_POQ1(am.getDBTransaction(), am.getBlk6UpdlstVORow().getSimsSaminvrun());
 am.getBlk6UpdlstVORow().setSimsInvno_noVal(result.getO_SIMS_INVNO());
}
}
catch (Exception ex)
{ am.getBlk6UpdlstVORow().setSimsInvno_noVal("");
}
 am.getBlk6UpdlstVORow().setSimsPrt_noVal("N");
}

}

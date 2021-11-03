package model.views;
public class InfoBlkVOEvents {
    public InfoBlkVOEvents(Xsim2AmImpl am) {
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

public void kf6(){
if (Ops.eq( am.lib().upper( am.lib().nameIn("work_item.w_current_blk")),"BLK3_TSI_MST"))
{ am.lib().goItem("blk4_tsi_des_q.sids_des");
}
}
public void kf5(){
if (Ops.eq( am.lib().upper( am.lib().nameIn("work_item.w_current_blk")),"BLK3_TSI_MST"))
{ am.lib().goItem("blk4_tsi_des_b.sids_des");
}
}
public void kf4(){
if (Ops.eq( am.lib().upper( am.lib().nameIn("work_item.w_current_blk")),"BLK3_TSI_MST"))
{ am.lib().goItem("blk4_tsi_des_d.sids_des");
}
}
public void kf3(){
if (Ops.eq( am.lib().upper( am.lib().nameIn("work_item.w_current_blk")),"BLK3_TSI_MST"))
{ am.lib().goItem("blk3_tsi_mst.sims_dptcod");
}
}
public void kf7(){
if (Ops.eq( am.lib().upper( am.lib().nameIn("work_item.w_current_blk")),"BLK3_TSI_MST"))
{ am.lib().goItem("blk4_tsi_des_a.sids_des");
}
}

}

package model.views;
public class Blk4TsiDesDVOEvents {
    public Blk4TsiDesDVOEvents(Xsim2AmImpl am) {
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

public void pri(){
 am.lib().copy( am.lib().nameIn(Ops.concat( am.getWorkItemVORow().getWDetailBlk(),".sims_divcod")),"blk4_tsi_des_d.sids_divcod");
 am.lib().copy( am.lib().nameIn(Ops.concat( am.getWorkItemVORow().getWDetailBlk(),".sims_saminvrun")),"blk4_tsi_des_d.sids_saminvrun");
 am.lib().copy("DES","blk4_tsi_des_d.sids_typ");
}
public void kf4(){
if (Ops.eq( am.lib().upper( am.lib().nameIn("work_item.w_current_blk")),"BLK3_TSI_MST"))
{ am.lib().goItem("blk4_tsi_des_d.sids_des");
}
}
public void kf5(){
if (Ops.eq( am.lib().upper( am.lib().nameIn("work_item.w_current_blk")),"BLK3_TSI_MST"))
{ am.lib().goItem("blk4_tsi_des_b.sids_des");
}
}
public void kf6(){
if (Ops.eq( am.lib().upper( am.lib().nameIn("work_item.w_current_blk")),"BLK3_TSI_MST"))
{ am.lib().goItem("blk4_tsi_des_q.sids_des");
}
}
public void kf7(){
if (Ops.eq( am.lib().upper( am.lib().nameIn("work_item.w_current_blk")),"BLK3_TSI_MST"))
{ am.lib().goItem("blk4_tsi_des_a.sids_des");
}
}
public void kf3(){
if (Ops.eq( am.lib().upper( am.lib().nameIn("work_item.w_current_blk")),"BLK3_TSI_MST"))
{ am.lib().goItem("blk3_tsi_mst.sims_dptcod");
}
}

}

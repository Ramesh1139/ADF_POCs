package model.views;
public class Blk4TsiDesBVOEvents {
    public Blk4TsiDesBVOEvents(Xsim2AmImpl am) {
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

public void pru(){
String  sids_bot_des =  null;
 sids_bot_des =  DP_UTL.F_WRAP_LONG(am.getDBTransaction(), am.getBlk4TsiDesBVORow().getSidsDes(),new BigDecimal(50));
if (Ops.eq( am.helpers().checkLineNum( am.getBlk4TsiDesBVORow().getSidsDes()), false))
{ am.lib().copy("sids_des","work_item.w_erritm");
throw am.lib().getFormTriggerFailure();
/* Unreachable Statement in original code
return ;
*/}
}
public void pri(){
String  sids_bot_des =  null;
 sids_bot_des =  DP_UTL.F_WRAP_LONG(am.getDBTransaction(), am.getBlk4TsiDesBVORow().getSidsDes(),new BigDecimal(50));
if (Ops.eq( am.helpers().checkLineNum( am.getBlk4TsiDesBVORow().getSidsDes()), false))
{ am.lib().copy("sids_des","work_item.w_erritm");
throw am.lib().getFormTriggerFailure();
/* Unreachable Statement in original code
return ;
*/}
 am.lib().copy( am.lib().nameIn(Ops.concat( am.getWorkItemVORow().getWDetailBlk(),".sims_divcod")),"blk4_tsi_des_b.sids_divcod");
 am.lib().copy( am.lib().nameIn(Ops.concat( am.getWorkItemVORow().getWDetailBlk(),".sims_saminvrun")),"blk4_tsi_des_b.sids_saminvrun");
 am.lib().copy("BOT","blk4_tsi_des_b.sids_typ");
}
public void kf7(){
if (Ops.eq( am.lib().upper( am.lib().nameIn("work_item.w_current_blk")),"BLK3_TSI_MST"))
{ am.lib().goItem("blk4_tsi_des_a.sids_des");
}
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

}

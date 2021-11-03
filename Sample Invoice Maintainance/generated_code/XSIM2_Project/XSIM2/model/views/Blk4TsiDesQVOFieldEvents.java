package model.views;
public class Blk4TsiDesQVOFieldEvents {
    public Blk4TsiDesQVOFieldEvents(Xsim2AmImpl am) {
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
public void Blk4Remark_bp(){
 am.getRemarkBlkVORow().setInvoicenoOrRemark_noVal("C");
 am.getRemarkBlkVORow().setIvloStatusDes_noVal("U");
 am.getRemarkBlkVORow().setIvloStatusBot_noVal("U");
 am.getRemarkBlkVORow().setIvloStatusQty_noVal("Q");
 am.getRemarkBlkVORow().setIvloStatusAmt_noVal("U");
 am.lib().goBlock("remark_blk");
}
public void SidsDes_kni(){
 am.lib().goItem("blk4_tsi_des_a.sids_des");
}
public void SidsDes_ken(){
 am.lib().goItem("blk4_tsi_des_a.sids_des");
}

}

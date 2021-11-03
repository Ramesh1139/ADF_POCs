package model.views;
public class Blk4TsiDesAVOFieldEvents {
    public Blk4TsiDesAVOFieldEvents(Xsim2AmImpl am) {
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
 am.getRemarkBlkVORow().setIvloStatusQty_noVal("U");
 am.getRemarkBlkVORow().setIvloStatusAmt_noVal("A");
 am.lib().goBlock("remark_blk");
}
public void SidsDes_kni(){
 am.lib().goItem("blk3_tsi_mst.sims_chrtyp");
}
public void SidsDes_ken(){
 am.lib().goItem("blk3_tsi_mst.sims_dptcod");
}

}

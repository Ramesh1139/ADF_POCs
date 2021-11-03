package model.views;
public class RemarkBlkVOFieldEvents {
    public RemarkBlkVOFieldEvents(Xsim2AmImpl am) {
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
public void MprmRmkcod_pc(){
 am.getRemarkBlkVORow().setInvoicenoOrRemark_noVal("R");
}
public void RemarkCancel_bp(){
 am.lib().goBlock( am.getWorkItemVORow().getWDetailBlk());
if (Ops.eq( am.getRemarkBlkVORow().getIvloStatusDes(),"D"))
{ am.lib().goItem("blk4_tsi_des_d.sids_des");
}
if (Ops.eq( am.getRemarkBlkVORow().getIvloStatusBot(),"B"))
{ am.lib().goItem("blk4_tsi_des_b.sids_des");
}
if (Ops.eq( am.getRemarkBlkVORow().getIvloStatusQty(),"Q"))
{ am.lib().goItem("blk4_tsi_des_q.sids_des");
}
if (Ops.eq( am.getRemarkBlkVORow().getIvloStatusAmt(),"A"))
{ am.lib().goItem("blk4_tsi_des_a.sids_des");
}
}
public void RemarkReset_bp(){
 am.lib().clearBlock();
}
public void RemarkOk_bp(){
try {
if (Ops.eq( am.getRemarkBlkVORow().getInvoicenoOrRemark(),"C"))
{
if (Ops.eq( am.getRemarkBlkVORow().getIvloStatusDes(),"D"))
{ am.lib().goItem("blk4_tsi_des_d.sids_des");
 am.lib().selectAll();
 am.lib().cutRegion();
{
DP_MIG_XSIM2.PL_REMARKREMARK_REMARKOK_BP1_results  result = DP_MIG_XSIM2.PL_REMARKREMARK_REMARKOK_BP1(am.getDBTransaction(), am.getRemarkBlkVORow().getSimsSaminvrun());
 am.getBlk4TsiDesDVORow().setSidsDes_noVal(result.getO_SIDS_DES());
}
 am.lib().pasteRegion();
}
if (Ops.eq( am.getRemarkBlkVORow().getIvloStatusBot(),"B"))
{ am.lib().goItem("blk4_tsi_des_b.sids_des");
 am.lib().selectAll();
 am.lib().cutRegion();
{
DP_MIG_XSIM2.PL_REMARKREMARK_REMARKOK_BP2_results  result = DP_MIG_XSIM2.PL_REMARKREMARK_REMARKOK_BP2(am.getDBTransaction(), am.getRemarkBlkVORow().getSimsSaminvrun());
 am.getBlk4TsiDesBVORow().setSidsDes_noVal(result.getO_SIDS_DES());
}
 am.lib().pasteRegion();
}
if (Ops.eq( am.getRemarkBlkVORow().getIvloStatusQty(),"Q"))
{ am.lib().goItem("blk4_tsi_des_q.sids_des");
 am.lib().selectAll();
 am.lib().cutRegion();
{
DP_MIG_XSIM2.PL_REMARKREMARK_REMARKOK_BP3_results  result = DP_MIG_XSIM2.PL_REMARKREMARK_REMARKOK_BP3(am.getDBTransaction(), am.getRemarkBlkVORow().getSimsSaminvrun());
 am.getBlk4TsiDesQVORow().setSidsDes_noVal(result.getO_SIDS_DES());
}
 am.lib().pasteRegion();
}
if (Ops.eq( am.getRemarkBlkVORow().getIvloStatusAmt(),"A"))
{ am.lib().goItem("blk4_tsi_des_a.sids_des");
 am.lib().selectAll();
 am.lib().cutRegion();
{
DP_MIG_XSIM2.PL_REMARKREMARK_REMARKOK_BP4_results  result = DP_MIG_XSIM2.PL_REMARKREMARK_REMARKOK_BP4(am.getDBTransaction(), am.getRemarkBlkVORow().getSimsSaminvrun());
 am.getBlk4TsiDesAVORow().setSidsDes_noVal(result.getO_SIDS_DES());
}
 am.lib().pasteRegion();
}
}
if (Ops.eq( am.getRemarkBlkVORow().getInvoicenoOrRemark(),"R"))
{
if (Ops.eq( am.getRemarkBlkVORow().getIvloStatusDes(),"D"))
{ am.lib().goItem("blk4_tsi_des_d.sids_des");
 am.lib().selectAll();
 am.lib().cutRegion();
{
DP_MIG_XSIM2.PL_REMARKREMARK_REMARKOK_BP5_results  result = DP_MIG_XSIM2.PL_REMARKREMARK_REMARKOK_BP5(am.getDBTransaction(), am.getRemarkBlkVORow().getMprmRmkcod(), am.getGlobalVORow().getGDivcod());
 am.getBlk4TsiDesDVORow().setSidsDes_noVal(result.getO_SIDS_DES());
}
 am.lib().pasteRegion();
}
if (Ops.eq( am.getRemarkBlkVORow().getIvloStatusBot(),"B"))
{ am.lib().goItem("blk4_tsi_des_b.sids_des");
 am.lib().selectAll();
 am.lib().cutRegion();
{
DP_MIG_XSIM2.PL_REMARKREMARK_REMARKOK_BP6_results  result = DP_MIG_XSIM2.PL_REMARKREMARK_REMARKOK_BP6(am.getDBTransaction(), am.getRemarkBlkVORow().getMprmRmkcod(), am.getGlobalVORow().getGDivcod());
 am.getBlk4TsiDesBVORow().setSidsDes_noVal(result.getO_SIDS_DES());
}
 am.lib().pasteRegion();
}
if (Ops.eq( am.getRemarkBlkVORow().getIvloStatusQty(),"Q"))
{ am.lib().goItem("blk4_tsi_des_q.sids_des");
 am.lib().selectAll();
 am.lib().cutRegion();
{
DP_MIG_XSIM2.PL_REMARKREMARK_REMARKOK_BP7_results  result = DP_MIG_XSIM2.PL_REMARKREMARK_REMARKOK_BP7(am.getDBTransaction(), am.getRemarkBlkVORow().getMprmRmkcod(), am.getGlobalVORow().getGDivcod());
 am.getBlk4TsiDesQVORow().setSidsDes_noVal(result.getO_SIDS_DES());
}
 am.lib().pasteRegion();
}
if (Ops.eq( am.getRemarkBlkVORow().getIvloStatusAmt(),"A"))
{ am.lib().goItem("blk4_tsi_des_a.sids_des");
 am.lib().selectAll();
 am.lib().cutRegion();
{
DP_MIG_XSIM2.PL_REMARKREMARK_REMARKOK_BP8_results  result = DP_MIG_XSIM2.PL_REMARKREMARK_REMARKOK_BP8(am.getDBTransaction(), am.getRemarkBlkVORow().getMprmRmkcod(), am.getGlobalVORow().getGDivcod());
 am.getBlk4TsiDesAVORow().setSidsDes_noVal(result.getO_SIDS_DES());
}
 am.lib().pasteRegion();
}
}
 am.lib().goBlock( am.getWorkItemVORow().getWDetailBlk());
if (Ops.eq( am.getRemarkBlkVORow().getIvloStatusDes(),"D"))
{ am.lib().goItem("blk4_tsi_des_d.sids_des");
}
if (Ops.eq( am.getRemarkBlkVORow().getIvloStatusBot(),"B"))
{ am.lib().goItem("blk4_tsi_des_b.sids_des");
}
if (Ops.eq( am.getRemarkBlkVORow().getIvloStatusQty(),"Q"))
{ am.lib().goItem("blk4_tsi_des_q.sids_des");
}
if (Ops.eq( am.getRemarkBlkVORow().getIvloStatusAmt(),"A"))
{ am.lib().goItem("blk4_tsi_des_a.sids_des");
}
}
catch (NoDataFound ex)
{
if (Ops.eq( am.getRemarkBlkVORow().getIvloStatusDes(),"D"))
{ am.lib().goItem("blk4_tsi_des_d.sids_des");
}
else
{
if(Ops.eq( am.getRemarkBlkVORow().getIvloStatusBot(),"B"))
{ am.lib().goItem("blk4_tsi_des_b.sids_des");
}
else
{
if(Ops.eq( am.getRemarkBlkVORow().getIvloStatusQty(),"Q"))
{ am.lib().goItem("blk4_tsi_des_q.sids_des");
}
else
{ am.lib().goItem("blk4_tsi_des_a.sids_des");
}
}
}
}
}
public void MprmDes_pc(){
 am.getRemarkBlkVORow().setInvoicenoOrRemark_noVal("R");
}
public void Blk3Mprm_bp(){
BigDecimal  rc =  new BigDecimal(0);
if (Ops.eq( am.lib().populateGroup( am.lib().getLovProperty("lov_mprm", am.lib().groupName)),new BigDecimal(0)))
{ rc =  am.lib().getGroupRowCount( am.lib().getLovProperty("lov_mprm", am.lib().groupName));
if (Ops.gt( rc,new BigDecimal(0)))
{ am.helpers().plSetLovPosition("lov_mprm", am.getSystemVORow().getTriggerItem());
if ( am.lib().showLov("lov_mprm"))
{ am.lib().copy( am.getGlobalVORow().getOne(),"remark_blk.mprm_rmkcod");
 am.getRemarkBlkVORow().setInvoicenoOrRemark_noVal("R");
}
}
else
{ am.lib().pstdNullLov();
}
}
else
{ am.lib().pstdNullLov();
}
}
public void RemarkLovSimsInvno_bp(){
BigDecimal  rc =  new BigDecimal(0);
if (Ops.eq( am.lib().populateGroup( am.lib().getLovProperty("lov_sims_invno", am.lib().groupName)),new BigDecimal(0)))
{ rc =  am.lib().getGroupRowCount( am.lib().getLovProperty("lov_sims_invno", am.lib().groupName));
if (Ops.gt( rc,new BigDecimal(0)))
{ am.helpers().plSetLovPosition("lov_sims_invno", am.getSystemVORow().getTriggerItem());
if ( am.lib().showLov("lov_sims_invno"))
{ am.lib().copy( am.getGlobalVORow().getTwo(),"remark_blk.sims_saminvrun");
 am.lib().copy( am.getGlobalVORow().getOne(),"remark_blk.sims_invno");
 am.getRemarkBlkVORow().setInvoicenoOrRemark_noVal("C");
}
}
else
{ am.lib().pstdNullLov();
}
}
else
{ am.lib().pstdNullLov();
}
}
public void SimsInvno_pc(){
 am.getRemarkBlkVORow().setInvoicenoOrRemark_noVal("C");
}

}

package model.views;
public class Blk1TsiMstVOEvents {
    public Blk1TsiMstVOEvents(Xsim2AmImpl am) {
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
 am.lib().copy("BLK1_TSI_MST","work_item.w_current_blk");
 am.lib().hideView("blk4_can");
 am.lib().pstdBlk1PreBlock();
}
public void prq(){
String  q_where =  null;
String  q_case =  null;
String  flag =  null;
 am.lib().copy( am.lib().nameIn("global.g_divcod"),"blk1_tsi_mst.sims_divcod");
 q_where = Ops.concat(Ops.concat(Ops.concat(Ops.concat(Ops.concat(Ops.concat("sims_divcod = '", am.lib().nvl( am.getBlk2CriteriaVORow().getSimsDivcod(), am.getGlobalVORow().getGDivcod())),"' and check_dpt(sims_dptcod, '"), am.lib().nvl( am.getBlk2CriteriaVORow().getSimsDivcod(), am.getGlobalVORow().getGDivcod())),"', '"), am.getGlobalVORow().getGUserid()),"') = 'true' and sims_typ = 'S'");
if (!Ops.isNull( am.getBlk2CriteriaVORow().getActive()))
{
if (Ops.eq( am.getBlk2CriteriaVORow().getActive(),"N"))
{ am.getBlk2CriteriaVORow().setSimsClsdat_noVal("Y");
}
else
{ am.getBlk2CriteriaVORow().setSimsClsdat_noVal("N");
}
}
else
{ am.getBlk2CriteriaVORow().setSimsClsdat_noVal("");
}
if (!Ops.isNull( am.getBlk2CriteriaVORow().getSimsClsdat()))
{
if (Ops.eq( am.getBlk2CriteriaVORow().getSimsClsdat(),"N"))
{ q_where = Ops.concat( q_where," and (nvl(sims_clsdat, to_date('01/01/2222', 'dd/mm/yyyy')) > sysdate)");
}
else
{
if(Ops.eq( am.getBlk2CriteriaVORow().getSimsClsdat(),"Y"))
{ q_where = Ops.concat( q_where," and (nvl(sims_clsdat, to_date('01/01/2222', 'dd/mm/yyyy')) < sysdate)");
}
}
}
if (!Ops.isNull( am.lib().ltrim( am.lib().rtrim( am.getBlk2CriteriaVORow().getSelectSimsYear()))))
{ q_where = Ops.concat(Ops.concat(Ops.concat( q_where," and sims_year ='"), am.lib().ltrim( am.lib().rtrim( am.getBlk2CriteriaVORow().getSelectSimsYear()))),"'");
}
if (!Ops.isNull( am.lib().ltrim( am.lib().rtrim( am.getBlk2CriteriaVORow().getSimsInvno()))))
{ q_where = Ops.concat(Ops.concat(Ops.concat( q_where," and sims_invno = '"), am.lib().ltrim( am.lib().rtrim( am.getBlk2CriteriaVORow().getSimsInvno()))),"'");
}
if (!Ops.isNull( am.lib().ltrim( am.lib().rtrim( am.getBlk2CriteriaVORow().getSimsCuscod()))))
{ q_where = Ops.concat(Ops.concat(Ops.concat( q_where," and sims_cuscod = '"), am.lib().ltrim( am.lib().rtrim( am.getBlk2CriteriaVORow().getSimsCuscod()))),"'");
}
if (!Ops.isNull( am.lib().ltrim( am.lib().rtrim( am.getBlk2CriteriaVORow().getSimsDptcod()))))
{ q_where = Ops.concat(Ops.concat(Ops.concat( q_where," and sims_dptcod = '"), am.lib().ltrim( am.lib().rtrim( am.getBlk2CriteriaVORow().getSimsDptcod()))),"'");
}
if (Ops.and(!Ops.isNull( am.lib().ltrim( am.lib().rtrim( am.getBlk2CriteriaVORow().getSelectInvDatFr()))),!Ops.isNull( am.lib().ltrim( am.lib().rtrim( am.getBlk2CriteriaVORow().getSelectInvDatTo())))))
{ q_where = Ops.concat(Ops.concat(Ops.concat(Ops.concat(Ops.concat( q_where," and (trunc(sims_invdat) >= to_date('"), am.lib().toChar( am.getBlk2CriteriaVORow().getSelectInvDatFr())),"', 'DD-MON-RR') and (trunc(sims_invdat) <= to_date('"), am.lib().toChar( am.getBlk2CriteriaVORow().getSelectInvDatTo())),"', 'DD-MON-RR')))");
}
else
{
if(Ops.and(!Ops.isNull( am.lib().ltrim( am.lib().rtrim( am.getBlk2CriteriaVORow().getSelectInvDatFr()))),Ops.isNull( am.lib().ltrim( am.lib().rtrim( am.getBlk2CriteriaVORow().getSelectInvDatTo())))))
{ q_where = Ops.concat(Ops.concat(Ops.concat( q_where," and (trunc(sims_invdat) >= to_date('"), am.lib().toChar( am.getBlk2CriteriaVORow().getSelectInvDatFr())),"', 'DD-MON-RR') or sims_invdat is null)");
}
else
{
if(Ops.and(Ops.isNull( am.lib().ltrim( am.lib().rtrim( am.getBlk2CriteriaVORow().getSelectInvDatFr()))),!Ops.isNull( am.lib().ltrim( am.lib().rtrim( am.getBlk2CriteriaVORow().getSelectInvDatTo())))))
{ q_where = Ops.concat(Ops.concat(Ops.concat( q_where," and (trunc(sims_invdat) <= to_date('"), am.lib().toChar( am.getBlk2CriteriaVORow().getSelectInvDatTo())),"', 'DD-MON-RR') or sims_invdat is null)");
}
}
}
if (Ops.and(!Ops.isNull( am.lib().ltrim( am.lib().rtrim( am.getBlk2CriteriaVORow().getSelectShpDatFr()))),!Ops.isNull( am.lib().ltrim( am.lib().rtrim( am.getBlk2CriteriaVORow().getSelectShpDatTo())))))
{ q_where = Ops.concat(Ops.concat(Ops.concat(Ops.concat(Ops.concat( q_where," and (trunc(sims_shpdat) >= to_date('"), am.lib().toChar( am.getBlk2CriteriaVORow().getSelectShpDatFr())),"', 'DD-MON-RR') and (trunc(sims_shpdat) <= to_date('"), am.lib().toChar( am.getBlk2CriteriaVORow().getSelectShpDatTo())),"', 'DD-MON-RR')))");
}
else
{
if(Ops.and(!Ops.isNull( am.lib().ltrim( am.lib().rtrim( am.getBlk2CriteriaVORow().getSelectShpDatFr()))),Ops.isNull( am.lib().ltrim( am.lib().rtrim( am.getBlk2CriteriaVORow().getSelectShpDatTo())))))
{ q_where = Ops.concat(Ops.concat(Ops.concat( q_where," and (trunc(sims_shpdat) >= to_date('"), am.lib().toChar( am.getBlk2CriteriaVORow().getSelectShpDatFr())),"', 'DD-MON-RR') or sims_shpdat is null)");
}
else
{
if(Ops.and(Ops.isNull( am.lib().ltrim( am.lib().rtrim( am.getBlk2CriteriaVORow().getSelectShpDatFr()))),!Ops.isNull( am.lib().ltrim( am.lib().rtrim( am.getBlk2CriteriaVORow().getSelectShpDatTo())))))
{ q_where = Ops.concat(Ops.concat(Ops.concat( q_where," and (trunc(sims_shpdat) <= to_date('"), am.lib().toChar( am.getBlk2CriteriaVORow().getSelectShpDatTo())),"', 'DD-MON-RR') or sims_shpdat is null)");
}
}
}
if (!Ops.isNull( am.lib().ltrim( am.lib().rtrim( am.getBlk2CriteriaVORow().getSimsPortD()))))
{ q_where = Ops.concat(Ops.concat(Ops.concat( q_where," and sims_port_d = '"), am.lib().ltrim( am.lib().rtrim( am.getBlk2CriteriaVORow().getSimsPortD()))),"'");
}
if (!Ops.isNull( am.lib().ltrim( am.lib().rtrim( am.getBlk2CriteriaVORow().getSimsShpmodcod()))))
{ q_where = Ops.concat(Ops.concat(Ops.concat( q_where," and sims_shpmodcod = '"), am.lib().ltrim( am.lib().rtrim( am.getBlk2CriteriaVORow().getSimsShpmodcod()))),"'");
}
 am.lib().setBlockProperty("BLK1_TSI_MST", am.lib().defaultWhere, q_where);
 q_where = Ops.concat( q_where," and sims_samsnd = 'Y'");
 am.lib().setBlockProperty( am.getWorkItemVORow().getWBulkupdBlk(), am.lib().defaultWhere, q_where);
if (Ops.eq( DP_CASE.CHECK_SETCOD(am.getDBTransaction(), am.getGlobalVORow().getGDivcod(), am.lib().nvl( am.getBlk2CriteriaVORow().getSimsCuscod(),"*"),"J76"),"Y"))
{ am.lib().setItemProperty("BLK1_CONTROL.blk1_massupd", am.lib().enabled, am.lib().propertyTrue);
}
else
{ am.lib().setItemProperty("BLK1_CONTROL.blk1_massupd", am.lib().enabled, am.lib().propertyFalse);
}
}
public void md(){
 am.lib().goItem("blk1_control.blk1_edit");
 am.lib().executeTrigger("WHEN-BUTTON-PRESSED");
}
public void ken(){
}
public void om(){
 am.lib().pstdBlk1OnMessage();
}
public void poq(){
 am.lib().pstdBlk1PostQuery();
 am.lib().setItemProperty("blk3_control.blk3_delete", am.lib().enabled, am.lib().propertyFalse);
 am.lib().setItemProperty("blk1_control.blk1_delete", am.lib().enabled, am.lib().propertyFalse);
if (!Ops.isNull( am.getBlk2CriteriaVORow().getActive()))
{
if (Ops.eq( am.getBlk2CriteriaVORow().getActive(),"Y"))
{ am.getBlk1TsiMstVORow().setActive_noVal("Y");
}
else
{ am.getBlk1TsiMstVORow().setActive_noVal("N");
}
}
else
{
if (Ops.gt( am.lib().nvl( am.getBlk1TsiMstVORow().getSimsClsdat(), am.lib().toDate("01/01/2222","dd/mm/yyyy")), am.lib().sysdate))
{ am.getBlk1TsiMstVORow().setActive_noVal("Y");
}
else
{ am.getBlk1TsiMstVORow().setActive_noVal("N");
}
}
if (!Ops.isNull( am.getBlk1TsiMstVORow().getSimsShpmodcod()))
{
{
DP_MIG_XSIM2.PL_TSIMST1_POQ1_results  result = DP_MIG_XSIM2.PL_TSIMST1_POQ1(am.getDBTransaction(), am.getBlk1TsiMstVORow().getSimsShpmodcod());
 am.getBlk1TsiMstVORow().setMpsmDes_noVal(result.getO_MPSM_DES());
}
}
}
public void oe(){
BigDecimal  lv_errcod =  am.lib().errorCode;
String  lv_errtyp =  am.lib().errorType;
String  lv_errtxt =  am.lib().errorText;
BigDecimal  status =  new BigDecimal(0);
String  str =  null;
 am.getWorkItemVORow().setWErrmsg_noVal("");
if (Ops.eq( lv_errcod,new BigDecimal(40919)))
{;
}
}

}

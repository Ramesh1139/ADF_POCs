package model.views;
public class Blk5TsiItmVOEvents {
    public Blk5TsiItmVOEvents(Xsim2AmImpl am) {
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

public void nri(){
}
public void pri(){
 dummy_wrappertry {
{
DP_MIG_XSIM2.PL_TSIITM5_PRI1_results  result = DP_MIG_XSIM2.PL_TSIITM5_PRI1(am.getDBTransaction(), am.getBlk5TsiItmVORow().getSiitDivcod(), am.getBlk5TsiItmVORow().getSiitSaminvrun());
 am.getBlk5TsiItmVORow().setSiitItmseq_noVal(result.getO_SIIT_ITMSEQ());
}
}
catch (Exception ex)
{ am.getBlk5TsiItmVORow().setSiitItmseq_noVal(new BigDecimal(1));
}
BigDecimal  i =  new BigDecimal(0);
 am.getWorkItemVORow().setWErritm_noVal("");
 am.getWorkItemVORow().setWErrmsg_noVal("");
 am.getWorkItemVORow().setWChanged_noVal("Y");
if (Ops.isNull( am.getBlk5TsiItmVORow().getSiitItmnum()))
{ am.getWorkItemVORow().setWErritm_noVal("siit_itmnum");
 am.getWorkItemVORow().setWErrmsg_noVal( am.lib().fstdUsrMsg("423"));
throw am.lib().getFormTriggerFailure();
/* Unreachable Statement in original code
return ;
*/}
if (Ops.le( am.lib().nvl( am.getBlk5TsiItmVORow().getSiitQty(),new BigDecimal(0)),new BigDecimal(0)))
{ am.getWorkItemVORow().setWErritm_noVal("siit_qty");
 am.getWorkItemVORow().setWErrmsg_noVal( am.lib().fstdUsrMsg("565"));
throw am.lib().getFormTriggerFailure();
/* Unreachable Statement in original code
return ;
*/}
if (Ops.or(Ops.lt( am.getBlk5TsiItmVORow().getSiitItmprc(),new BigDecimal(0)),Ops.isNull( am.getBlk5TsiItmVORow().getSiitItmprc())))
{ am.getWorkItemVORow().setWErritm_noVal("siit_itmprc");
 am.getWorkItemVORow().setWErrmsg_noVal( am.lib().fstdUsrMsg("801"));
throw am.lib().getFormTriggerFailure();
/* Unreachable Statement in original code
return ;
*/}
if (Ops.or(Ops.isNull( am.getBlk5TsiItmVORow().getSiitTyp()),Ops.eq( am.lib().nvl( am.getBlk5TsiItmVORow().getSiitTyp(),"!@#$%"),"!@#$%")))
{ am.getWorkItemVORow().setWErritm_noVal("siit_typ");
 am.getWorkItemVORow().setWErrmsg_noVal("Sample type must not be blank");
throw am.lib().getFormTriggerFailure();
/* Unreachable Statement in original code
return ;
*/}
if (Ops.and(!Ops.isNull( am.getBlk5TsiItmVORow().getSiitTyp()),Ops.ne( am.lib().nvl( am.getBlk5TsiItmVORow().getSiitTyp(),"!@#$%"),"!@#$%")))
{
{
DP_MIG_XSIM2.PL_TSIITM5_PRI2_results  result = DP_MIG_XSIM2.PL_TSIITM5_PRI2(am.getDBTransaction(), am.getBlk5TsiItmVORow().getSiitTyp());
 i = result.getO_I();
}
if (Ops.eq( i,new BigDecimal(0)))
{ am.getWorkItemVORow().setWErritm_noVal("siit_typ");
 am.getWorkItemVORow().setWErrmsg_noVal("Invalid sample type");
throw am.lib().getFormTriggerFailure();
/* Unreachable Statement in original code
return ;
*/}
}
if (!Ops.isNull( am.getBlk5TsiItmVORow().getSiitSupcod()))
{String  stscod =  null;
Date  clsdat;
String  stsmsg =  null;
String  annapprov =  null;
try {
{
DP_MIG_XSIM2.PL_TSIITM5_PRI3_results  result = DP_MIG_XSIM2.PL_TSIITM5_PRI3(am.getDBTransaction(), am.getBlk5TsiItmVORow().getSiitSupcod());
 stscod = result.getO_STSCOD();
 clsdat = result.getO_CLSDAT();
 annapprov = result.getO_ANNAPPROV();
}
}
catch (NoDataFound ex)
{ clsdat = null;
 stscod = null;
 am.getWorkItemVORow().setWErritm_noVal("siit_supcod");
 am.getWorkItemVORow().setWErrmsg_noVal(Ops.concat(Ops.concat("Supplier ", am.getBlk5TsiItmVORow().getSiitSupcod())," not found"));
throw am.lib().getFormTriggerFailure();
/* Unreachable Statement in original code
return ;
*/
}
if (Ops.and(!Ops.isNull( clsdat),Ops.lt( clsdat, am.lib().sysdate)))
{ am.getWorkItemVORow().setWErritm_noVal("siit_supcod");
 am.getWorkItemVORow().setWErrmsg_noVal(Ops.concat(Ops.concat(Ops.concat( am.lib().fstdUsrMsg("1145")," ("), am.getBlk5TsiItmVORow().getSiitSupcod()),")"));
throw am.lib().getFormTriggerFailure();
/* Unreachable Statement in original code
return ;
*/}
else
{
if(Ops.and(!Ops.isNull( stscod),Ops.ne( stscod,"AT")))
{try {
{
DP_MIG_XSIM2.PL_TSIITM5_PRI4_results  result = DP_MIG_XSIM2.PL_TSIITM5_PRI4(am.getDBTransaction(), stscod);
 stsmsg = result.getO_STSMSG();
}
if (Ops.eq( stscod,"DE"))
{ am.getWorkItemVORow().setWErrmsg_noVal(Ops.concat(Ops.concat(Ops.concat("Supplier ", am.getBlk5TsiItmVORow().getSiitSupcod())," "), stsmsg));
}
else
{ am.getWorkItemVORow().setWErrmsg_noVal(Ops.concat(Ops.concat( stsmsg," for supplier "), am.getBlk5TsiItmVORow().getSiitSupcod()));
}
}
catch (NoDataFound ex)
{ am.getWorkItemVORow().setWErrmsg_noVal(Ops.concat(Ops.concat(Ops.concat( am.lib().fstdUsrMsg("1108")," ("), am.getBlk5TsiItmVORow().getSiitSupcod()),")"));
}
 am.getWorkItemVORow().setWErritm_noVal("siit_supcod");
throw am.lib().getFormTriggerFailure();
/* Unreachable Statement in original code
return ;
*/}
else
{
if(Ops.and(!Ops.isNull( annapprov),Ops.ne( annapprov,"Y")))
{ am.getWorkItemVORow().setWErritm_noVal("siit_supcod");
 am.getWorkItemVORow().setWErrmsg_noVal(Ops.concat("Annual Review not yet approved for Supplier ", am.getBlk5TsiItmVORow().getSiitSupcod()));
throw am.lib().getFormTriggerFailure();
/* Unreachable Statement in original code
return ;
*/}
}
}
;
}
if (Ops.and(!Ops.isNull( am.getBlk5TsiItmVORow().getCaseNo()),Ops.ne( am.lib().nvl( am.getBlk5TsiItmVORow().getCaseNo(),"!@#$%"),"!@#$%")))
{
if (Ops.isNull( am.getBlk5TsiItmVORow().getSiitPrRunnum()))
{ am.getWorkItemVORow().setWErritm_noVal("CASE_NO");
 am.getWorkItemVORow().setWErrmsg_noVal( am.lib().fstdUsrMsg("312"));
throw am.lib().getFormTriggerFailure();
/* Unreachable Statement in original code
return ;
*/}
}
if (Ops.ne( DP_CASE.CHECK_SETCOD(am.getDBTransaction(), am.getGlobalVORow().getGDivcod(),"*","355"),"Y"))
{
if (Ops.and(!Ops.isNull( am.getBlk5TsiItmVORow().getSiitHarmoncod()),Ops.ne( am.lib().nvl( am.getBlk5TsiItmVORow().getSiitHarmoncod(),"!@#$%"),"!@#$%")))
{
{
DP_MIG_XSIM2.PL_TSIITM5_PRI5_results  result = DP_MIG_XSIM2.PL_TSIITM5_PRI5(am.getDBTransaction(), am.getBlk5TsiItmVORow().getSiitHarmoncod());
 i = result.getO_I();
}
if (Ops.eq( i,new BigDecimal(0)))
{ am.getWorkItemVORow().setWErritm_noVal("siit_harmoncod");
 am.getWorkItemVORow().setWErrmsg_noVal( am.lib().fstdUsrMsg("831"));
throw am.lib().getFormTriggerFailure();
/* Unreachable Statement in original code
return ;
*/}
}
}
if (Ops.and(!Ops.isNull( am.getBlk5TsiItmVORow().getSiitUom()),Ops.ne( am.lib().nvl( am.getBlk5TsiItmVORow().getSiitUom(),"!@#$%"),"!@#$%")))
{
{
DP_MIG_XSIM2.PL_TSIITM5_PRI6_results  result = DP_MIG_XSIM2.PL_TSIITM5_PRI6(am.getDBTransaction(), am.getBlk5TsiItmVORow().getSiitUom());
 i = result.getO_I();
}
if (Ops.eq( i,new BigDecimal(0)))
{ am.getWorkItemVORow().setWErritm_noVal("siit_uom");
 am.getWorkItemVORow().setWErrmsg_noVal("Invalid UOM");
throw am.lib().getFormTriggerFailure();
/* Unreachable Statement in original code
return ;
*/}
}
;
}
public void kd(){
 am.lib().nextRecord();
}
public void ku(){
 am.lib().previousRecord();
}
public void pru(){
BigDecimal  i =  new BigDecimal(0);
 am.getWorkItemVORow().setWChanged_noVal("Y");
 am.getWorkItemVORow().setWErritm_noVal("");
 am.getWorkItemVORow().setWErrmsg_noVal("");
if (Ops.isNull( am.getBlk5TsiItmVORow().getSiitItmnum()))
{ am.getWorkItemVORow().setWErritm_noVal("siit_itmnum");
 am.getWorkItemVORow().setWErrmsg_noVal( am.lib().fstdUsrMsg("423"));
throw am.lib().getFormTriggerFailure();
/* Unreachable Statement in original code
return ;
*/}
if (Ops.le( am.lib().nvl( am.getBlk5TsiItmVORow().getSiitQty(),new BigDecimal(0)),new BigDecimal(0)))
{ am.getWorkItemVORow().setWErritm_noVal("siit_qty");
 am.getWorkItemVORow().setWErrmsg_noVal( am.lib().fstdUsrMsg("565"));
throw am.lib().getFormTriggerFailure();
/* Unreachable Statement in original code
return ;
*/}
if (Ops.or(Ops.lt( am.getBlk5TsiItmVORow().getSiitItmprc(),new BigDecimal(0)),Ops.isNull( am.getBlk5TsiItmVORow().getSiitItmprc())))
{ am.getWorkItemVORow().setWErritm_noVal("siit_itmprc");
 am.getWorkItemVORow().setWErrmsg_noVal( am.lib().fstdUsrMsg("801"));
throw am.lib().getFormTriggerFailure();
/* Unreachable Statement in original code
return ;
*/}
if (Ops.or(Ops.isNull( am.getBlk5TsiItmVORow().getSiitTyp()),Ops.eq( am.lib().nvl( am.getBlk5TsiItmVORow().getSiitTyp(),"!@#$%"),"!@#$%")))
{ am.getWorkItemVORow().setWErritm_noVal("siit_typ");
 am.getWorkItemVORow().setWErrmsg_noVal("Sample type must not be blank");
throw am.lib().getFormTriggerFailure();
/* Unreachable Statement in original code
return ;
*/}
if (Ops.and(!Ops.isNull( am.getBlk5TsiItmVORow().getSiitTyp()),Ops.ne( am.lib().nvl( am.getBlk5TsiItmVORow().getSiitTyp(),"!@#$%"),"!@#$%")))
{
{
DP_MIG_XSIM2.PL_TSIITM5_PRU1_results  result = DP_MIG_XSIM2.PL_TSIITM5_PRU1(am.getDBTransaction(), am.getBlk5TsiItmVORow().getSiitTyp());
 i = result.getO_I();
}
if (Ops.eq( i,new BigDecimal(0)))
{ am.getWorkItemVORow().setWErritm_noVal("siit_typ");
 am.getWorkItemVORow().setWErrmsg_noVal("Invalid sample type");
throw am.lib().getFormTriggerFailure();
/* Unreachable Statement in original code
return ;
*/}
}
if (!Ops.isNull( am.getBlk5TsiItmVORow().getSiitSupcod()))
{String  stscod =  null;
Date  clsdat;
String  stsmsg =  null;
String  annapprov =  null;
try {
{
DP_MIG_XSIM2.PL_TSIITM5_PRU2_results  result = DP_MIG_XSIM2.PL_TSIITM5_PRU2(am.getDBTransaction(), am.getBlk5TsiItmVORow().getSiitSupcod());
 stscod = result.getO_STSCOD();
 clsdat = result.getO_CLSDAT();
 annapprov = result.getO_ANNAPPROV();
}
}
catch (NoDataFound ex)
{ clsdat = null;
 stscod = null;
 am.getWorkItemVORow().setWErritm_noVal("siit_supcod");
 am.getWorkItemVORow().setWErrmsg_noVal(Ops.concat(Ops.concat("Supplier ", am.getBlk5TsiItmVORow().getSiitSupcod())," not found"));
throw am.lib().getFormTriggerFailure();
/* Unreachable Statement in original code
return ;
*/
}
if (Ops.and(!Ops.isNull( clsdat),Ops.lt( clsdat, am.lib().sysdate)))
{ am.getWorkItemVORow().setWErritm_noVal("siit_supcod");
 am.getWorkItemVORow().setWErrmsg_noVal(Ops.concat(Ops.concat(Ops.concat( am.lib().fstdUsrMsg("1145")," ("), am.getBlk5TsiItmVORow().getSiitSupcod()),")"));
throw am.lib().getFormTriggerFailure();
/* Unreachable Statement in original code
return ;
*/}
else
{
if(Ops.and(!Ops.isNull( stscod),Ops.ne( stscod,"AT")))
{try {
{
DP_MIG_XSIM2.PL_TSIITM5_PRU3_results  result = DP_MIG_XSIM2.PL_TSIITM5_PRU3(am.getDBTransaction(), stscod);
 stsmsg = result.getO_STSMSG();
}
if (Ops.eq( stscod,"DE"))
{ am.getWorkItemVORow().setWErrmsg_noVal(Ops.concat(Ops.concat(Ops.concat("Supplier ", am.getBlk5TsiItmVORow().getSiitSupcod())," "), stsmsg));
}
else
{ am.getWorkItemVORow().setWErrmsg_noVal(Ops.concat(Ops.concat( stsmsg," for supplier "), am.getBlk5TsiItmVORow().getSiitSupcod()));
}
}
catch (NoDataFound ex)
{ am.getWorkItemVORow().setWErrmsg_noVal(Ops.concat(Ops.concat(Ops.concat( am.lib().fstdUsrMsg("1108")," ("), am.getBlk5TsiItmVORow().getSiitSupcod()),")"));
}
 am.getWorkItemVORow().setWErritm_noVal("siit_supcod");
throw am.lib().getFormTriggerFailure();
/* Unreachable Statement in original code
return ;
*/}
else
{
if(Ops.and(!Ops.isNull( annapprov),Ops.ne( annapprov,"Y")))
{ am.getWorkItemVORow().setWErritm_noVal("siit_supcod");
 am.getWorkItemVORow().setWErrmsg_noVal(Ops.concat("Annual Review not yet approved for Supplier ", am.getBlk5TsiItmVORow().getSiitSupcod()));
throw am.lib().getFormTriggerFailure();
/* Unreachable Statement in original code
return ;
*/}
}
}
;
}
if (Ops.and(!Ops.isNull( am.getBlk5TsiItmVORow().getCaseNo()),Ops.ne( am.lib().nvl( am.getBlk5TsiItmVORow().getCaseNo(),"!@#$%"),"!@#$%")))
{
if (Ops.isNull( am.getBlk5TsiItmVORow().getSiitPrRunnum()))
{ am.getWorkItemVORow().setWErritm_noVal("CASE_NO");
 am.getWorkItemVORow().setWErrmsg_noVal( am.lib().fstdUsrMsg("312"));
throw am.lib().getFormTriggerFailure();
/* Unreachable Statement in original code
return ;
*/}
}
if (Ops.ne( DP_CASE.CHECK_SETCOD(am.getDBTransaction(), am.getGlobalVORow().getGDivcod(),"*","355"),"Y"))
{
if (Ops.and(!Ops.isNull( am.getBlk5TsiItmVORow().getSiitHarmoncod()),Ops.ne( am.lib().nvl( am.getBlk5TsiItmVORow().getSiitHarmoncod(),"!@#$%"),"!@#$%")))
{
{
DP_MIG_XSIM2.PL_TSIITM5_PRU4_results  result = DP_MIG_XSIM2.PL_TSIITM5_PRU4(am.getDBTransaction(), am.getBlk5TsiItmVORow().getSiitHarmoncod());
 i = result.getO_I();
}
if (Ops.eq( i,new BigDecimal(0)))
{ am.getWorkItemVORow().setWErritm_noVal("siit_harmoncod");
 am.getWorkItemVORow().setWErrmsg_noVal( am.lib().fstdUsrMsg("831"));
throw am.lib().getFormTriggerFailure();
/* Unreachable Statement in original code
return ;
*/}
}
}
if (Ops.and(!Ops.isNull( am.getBlk5TsiItmVORow().getSiitUom()),Ops.ne( am.lib().nvl( am.getBlk5TsiItmVORow().getSiitUom(),"!@#$%"),"!@#$%")))
{
{
DP_MIG_XSIM2.PL_TSIITM5_PRU5_results  result = DP_MIG_XSIM2.PL_TSIITM5_PRU5(am.getDBTransaction(), am.getBlk5TsiItmVORow().getSiitUom());
 i = result.getO_I();
}
if (Ops.eq( i,new BigDecimal(0)))
{ am.getWorkItemVORow().setWErritm_noVal("siit_uom");
 am.getWorkItemVORow().setWErrmsg_noVal("Invalid UOM");
throw am.lib().getFormTriggerFailure();
/* Unreachable Statement in original code
return ;
*/}
}
}
public void poq(){
if (!Ops.isNull( am.getBlk5TsiItmVORow().getSiitPrRunnum()))
{try {
{
DP_MIG_XSIM2.PL_TSIITM5_POQ1_results  result = DP_MIG_XSIM2.PL_TSIITM5_POQ1(am.getDBTransaction(), am.getBlk5TsiItmVORow().getSiitPrRunnum(), am.getGlobalVORow().getGDivcod());
 am.getBlk5TsiItmVORow().setCaseNo_noVal(result.getO_CASE_NO());
}
}
catch (Exception ex)
{ am.getBlk5TsiItmVORow().setCaseNo_noVal("");
}
}
}
public void prb(){
 am.lib().copy("BLK5_TSI_ITM","work_item.w_current_blk");
}

}

package model;
public class Xsirpm2AmImplHelpers {
    public Xsirpm2AmImplHelpers(Xsirpm2AmImpl am) {
        super();
this.am = am;
    }
    Xsirpm2AmImpl am;
    public Xsirpm2AmImpl getAm() {
        return am;
    }
    public void setAm(Xsirpm2AmImpl am) {
        this.am = am;
    }

public String fDefPrtnam() {
String  usr_interface =  null;
String  prt_name =  null;
BigDecimal  prt_len =  new BigDecimal(0);
try {
 usr_interface =  am.lib().getApplicationProperty( am.lib().userInterface);
if (Ops.eq( usr_interface,"MSWINDOWS32"))
{ prt_name =  am.lib().winApiEnvironment.readRegistry("HKEY_LOCAL_MACHINE\\Config\\0001\\System\\CurrentControlSet\\Control\\Print\\Printers","Default", true);
}
else
{
if(Ops.eq( usr_interface,"MSWINDOWS"))
{ prt_len =  w31util.getprivateprofilestring("windows","device","NO", prt_name,new BigDecimal(50),"WIN.INI");
if (Ops.eq( prt_len,new BigDecimal(2)))
{throw am.lib().getNoDataFound();
}
else
{ prt_name =  am.lib().substr( prt_name,new BigDecimal(1),Ops.minus( am.lib().instr( prt_name,","),new BigDecimal(1)));
}
}
else
{;
}
}
return  prt_name;
return null;
}
catch (NoDataFound ex)
{return "Cannot get default printer Name.";
}
catch (Exception ex)
{return  am.lib().sqlerrm;
}
}

public void plShowFind() {
recordgroup  gid;
BigDecimal  status =  new BigDecimal(0);
 am.lib().pstdBlk1Find();
 am.lib().goBlock( am.getWorkItemVORow().getWCriteriaBlk());
}

public void plInitVariable() {
String  cuscod =  null;
String  dptcod =  null;
BigDecimal  j =  new BigDecimal(0);
BigDecimal  x =  new BigDecimal(0);
String  val =  null;
 am.getWorkItemVORow().setWDisplayHeight_noVal( am.lib().getApplicationProperty( am.lib().displayHeight));
 am.getWorkItemVORow().setWDisplayWidth_noVal( am.lib().getApplicationProperty( am.lib().displayWidth));
 am.getWorkItemVORow().setWFormId_noVal( am.lib().getApplicationProperty( am.lib().currentFormName));
 am.getWorkItemVORow().setWUser_noVal( am.getGlobalVORow().getGUserid());
 am.getWorkItemVORow().setWDiv_noVal( am.getGlobalVORow().getGDivcod());
 am.getWorkItemVORow().setWSystemName_noVal("XTS System");
 am.getWorkItemVORow().setWMainBlk_noVal("main_block");
 am.getWorkItemVORow().setWCriteriaBlk_noVal("criteria_block");
 am.getWorkItemVORow().setWUlevel_noVal("90");
 am.getMainBlockVORow().setWPrinterName_noVal( am.helpers().fDefPrtnam);
 am.getMainBlockVORow().setWPrinterName2_noVal( am.getMainBlockVORow().getWPrinterName());
 am.getMainBlockVORow().setNoCopies_noVal(new BigDecimal(1));
 am.getMainBlockVORow().setInvBackgrd_noVal("Li & Fung (Trading) Limited");
 am.getMainBlockVORow().setInvRepcode_noVal("IV_TRAD");
 am.getSystemVORow().setMessageLevel_noVal(new BigDecimal(15));
 am.getInfoBlkVORow().setInfoFormName_noVal("Print Sam Inv, Cr/Db Note");
 am.getInfoBlkVORow().setInfoFormId_noVal( am.getWorkItemVORow().getWFormId());
 am.getInfoBlkVORow().setInfoUser_noVal( am.getWorkItemVORow().getWUser());
 am.getInfoBlkVORow().setInfoDate_noVal( am.lib().sysdate);
 am.getInfoBlkVORow().setInfoDiv_noVal( am.getWorkItemVORow().getWDiv());
if (!Ops.isNull( am.getMainBlockVORow().getSimsInvnoFrom()))
{
{
DP_MIG_XSIRPM2.PL_INIT_VARIABLE1_results  result = DP_MIG_XSIRPM2.PL_INIT_VARIABLE1(am.getDBTransaction(), am.getWorkItemVORow().getWDiv(), am.getMainBlockVORow().getSimsInvnoFrom());
 cuscod = result.getO_CUSCOD();
 dptcod = result.getO_DPTCOD();
}
 am.getMainBlockVORow().setPrintLaser_noVal( DP_CASE.CHECK_DPT_SETCOD(am.getDBTransaction(), am.getWorkItemVORow().getWDiv(), dptcod, cuscod,"591"));
 am.getMainBlockVORow().setInvRepcode_noVal( DP_CASE.CHECK_DPT_SETCOD(am.getDBTransaction(), am.getWorkItemVORow().getWDiv(), dptcod, cuscod,"592"));
if (Ops.and(!Ops.isNull( am.getMainBlockVORow().getInvRepcode()),Ops.ne( am.lib().nvl( am.getMainBlockVORow().getInvRepcode(),"N"),"N")))
{try {
{
DP_MIG_XSIRPM2.PL_INIT_VARIABLE2_results  result = DP_MIG_XSIRPM2.PL_INIT_VARIABLE2(am.getDBTransaction(), am.getMainBlockVORow().getInvRepcode());
 am.getMainBlockVORow().setInvBackgrd_noVal(result.getO_INV_BACKGRD());
}
}
catch (Exception ex)
{ am.getMainBlockVORow().setInvBackgrd_noVal("Li & Fung (Trading) Limited");
 am.getMainBlockVORow().setInvRepcode_noVal("iv_trad");
}
}
else
{ am.getMainBlockVORow().setInvBackgrd_noVal("Li & Fung (Trading) Limited");
 am.getMainBlockVORow().setInvRepcode_noVal("iv_trad");
}
}
else
{ am.getMainBlockVORow().setPrintLaser_noVal( DP_CASE.CHECK_DPT_SETCOD(am.getDBTransaction(), am.getWorkItemVORow().getWDiv(),"*","*","591"));
}
 val =  DP_CASE.CHECK_SETCOD(am.getDBTransaction(), am.getWorkItemVORow().getWDiv(), cuscod,"J75");
if (Ops.eq( val,"N"))
{ am.getMainBlockVORow().setInvoiceType_noVal("SAM_INV");
}
else
{ am.getMainBlockVORow().setInvoiceType_noVal( val);
}
}

public void plPrintSaminv(String  invno_from/*TODO:Handle Default Value: null*/,String  invno_to/*TODO:Handle Default Value: null*/) {
PlPrintSaminv_CQyVOImpl c_qy = am.getPlPrintSaminv_CQyVO();
ParamList  pl_id;
String  vlinfeed =  null;
BigDecimal  cnt = new BigDecimal(0);
BigDecimal  status =  new BigDecimal(0);
String  l_temp_filename =  null;
String  l_temp_dir =  null;
String  web_path =  null;
String  web_rep_path =  null;
String  l_file_name =  null;
if (Ops.eq( am.getMainBlockVORow().getPrintLaser(),"Y"))
{
if (!Ops.isNull( am.getMainBlockVORow().getInvBackgrd()))
{try {
{
DP_MIG_XSIRPM2.PL_PRINT_SAMINV1_results  result = DP_MIG_XSIRPM2.PL_PRINT_SAMINV1(am.getDBTransaction(), am.getMainBlockVORow().getInvBackgrd(), am.getGlobalVORow().getGDivcod());
 am.getMainBlockVORow().setInvRepcode_noVal(result.getO_INV_REPCODE());
}
}
catch (Exception ex)
{ am.lib().setAlertProperty("AL_STD_WARN", am.lib().alertMessageText,"Invalid Background!");
 status =  am.lib().showAlert("AL_STD_WARN");
 am.getMainBlockVORow().setInvBackgrd_noVal("");
 am.getMainBlockVORow().setInvRepcode_noVal("");
 am.lib().goItem("main_block.inv_backgrd");
return ;
}
}
else
{ am.lib().setAlertProperty("AL_STD_WARN", am.lib().alertMessageText,"Invalid Background!");
 status =  am.lib().showAlert("AL_STD_WARN");
return ;
}
}
 am.helpers().plLockSampleInvoice();
/*Set bind variables for VO and prepare for loop*/
c_qy.setI_INVNO_FROM( invno_from);
c_qy.setI_INVNO_TO( invno_to);
c_qy.setI_G_DIVCOD( am.getGlobalVORow().getGDivcod());
am.lib().prepareVO(c_qy);
{
RowSetIterator rsi = c_qy.createRowSetIterator(null);
rsi.reset();
PlPrintSaminv_CQyVORowImpl c=null;
/*Ready to loop through VO Cursor*/
 while(rsi.hasNext()) {
c =(PlPrintSaminv_CQyVORowImpl) rsi.next();
if (Ops.eq( CHECK_DPT(am.getDBTransaction(), c.getSimsDptcod(), c.getSimsDivcod(), am.getGlobalVORow().getGUserid()),"true"))
{try {
{
DP_MIG_XSIRPM2.PL_PRINT_SAMINV2_results  result = DP_MIG_XSIRPM2.PL_PRINT_SAMINV2(am.getDBTransaction(), am.getWorkItemVORow().getWDiv(), c.getSimsCuscod());
 vlinfeed = result.getO_VLINFEED();
}
}
catch (Exception ex)
{ vlinfeed = null;
}
if (Ops.isNull( vlinfeed))
{try {
{
DP_MIG_XSIRPM2.PL_PRINT_SAMINV3_results  result = DP_MIG_XSIRPM2.PL_PRINT_SAMINV3(am.getDBTransaction(), am.getWorkItemVORow().getWDiv());
 vlinfeed = result.getO_VLINFEED();
}
}
catch (Exception ex)
{ vlinfeed = "7";
}
}
 pl_id =  am.lib().getParameterList("tmpdata");
if (! am.lib().idNull( pl_id))
{ am.lib().destroyParameterList( pl_id);
}
 pl_id =  am.lib().createParameterList("tmpdata");
 am.lib().addParameter( pl_id,"hdr_code", am.lib().textParameter, am.lib().toChar( c.getSimsSaminvrun()));
 am.lib().addParameter( pl_id,"hdr_div", am.lib().textParameter, am.getWorkItemVORow().getWDiv());
 am.lib().addParameter( pl_id,"hdr_by", am.lib().textParameter, am.getWorkItemVORow().getWUser());
 am.lib().addParameter( pl_id,"DESTYPE", am.lib().textParameter, am.getMainBlockVORow().getWDes());
 am.lib().addParameter( pl_id,"HDR_SKIP_LINES", am.lib().textParameter, vlinfeed);
 am.lib().addParameter( pl_id,"HDR_INVTYP", am.lib().textParameter, am.getMainBlockVORow().getInvRepcode());
 am.lib().addParameter( pl_id,"COPIES", am.lib().textParameter, am.lib().toChar( am.getMainBlockVORow().getNoCopies()));
 am.lib().addParameter( pl_id,"PARAMFORM", am.lib().textParameter,"NO");
 am.lib().addParameter( pl_id,"HDR_SAM_OR_COM", am.lib().textParameter, am.getMainBlockVORow().getInvoiceType());
if (Ops.eq( am.lib().lower( am.getWorkItemVORow().getFormsVersion()),"11g"))
{ am.lib().addParameter( pl_id,"SERVER", am.lib().textParameter,"xts3rs");
}
 l_temp_dir =  am.lib().winApiEnvironment.getTempDirectory( true);
if (Ops.eq( am.getWorkItemVORow().getWUserInterface(),"WEB"))
{ delete_parameter( pl_id,"DESTYPE");
 am.lib().addParameter( pl_id,"DESTYPE", am.lib().textParameter,"file");
 am.lib().addParameter( pl_id,"desformat", am.lib().textParameter,"pdf");
 l_temp_filename =  am.lib().winApiUtility.generateTempFilename( l_temp_dir,"REP", false);
 am.lib().winApiUtility.deleteFile( l_temp_filename, true);
 l_temp_filename =  am.lib().replace( l_temp_filename,".tmp",".pdf");
 am.lib().addParameter( pl_id,"desname", am.lib().textParameter, l_temp_filename);
if (Ops.eq( am.getMainBlockVORow().getPrintLaser(),"N"))
{ am.lib().runProduct( am.lib().reports,"xsir", am.lib().synchronous, am.lib().runtime, am.lib().filesystem, pl_id,null);
}
else
{ am.lib().runProduct( am.lib().reports,"xsirl", am.lib().synchronous, am.lib().runtime, am.lib().filesystem, pl_id,null);
}
 delete_parameter( pl_id,"desname");
 web_path =  am.lib().winApiEnvironment.readRegistry("HKEY_LOCAL_MACHINE\\SOFTWARE\\ORACLE\\HOME1","FORMS60_OUTPUT", true);
 l_file_name =  am.lib().replace( l_temp_filename,Ops.concat( l_temp_dir,"\\"),"");
 am.lib().winApiUtility.copyFile( l_temp_filename,Ops.concat(Ops.concat( web_path,"\\"), l_file_name), true, true);
 am.lib().winApiUtility.deleteFile( l_temp_filename, true);
 web_rep_path = Ops.concat( am.lib().winApiEnvironment.readRegistry("HKEY_LOCAL_MACHINE\\SOFTWARE\\ORACLE\\HOME1","SERVER_URL", true), am.lib().winApiEnvironment.readRegistry("HKEY_LOCAL_MACHINE\\SOFTWARE\\ORACLE\\HOME1","FORMS60_MAPPING", true));
 am.lib().web.showDocument(Ops.concat(Ops.concat( web_rep_path,"/"), l_file_name));
}
else
{ am.lib().addParameter( pl_id,"DESNAME", am.lib().textParameter, am.getMainBlockVORow().getWPrinterName());
if (Ops.eq( am.getMainBlockVORow().getPrintLaser(),"N"))
{ am.lib().runProduct( am.lib().reports,"xsir", am.lib().synchronous, am.lib().runtime, am.lib().filesystem, pl_id,null);
}
else
{ am.lib().runProduct( am.lib().reports,"xsirl", am.lib().synchronous, am.lib().runtime, am.lib().filesystem, pl_id,null);
}
}
 cnt = Ops.plus( cnt,new BigDecimal(1));
}
}
rsi.closeRowSetIterator();
}
am.lib().closeVO(c_qy);
 am.helpers().plLockSampleInvoice();
if (Ops.eq( cnt,new BigDecimal(0)))
{ am.getWorkItemVORow().setWErrmsg_noVal( am.lib().fstdUsrMsg("1033"));
 am.getWorkItemVORow().setWErritm_noVal("MAIN_BLOCK.SIMS_INVNO_FROM");
 am.lib().pstdDisplayError();
}
}

public void pstdBlk2OkWithDiv(String  p_divnam) {
BigDecimal  status =  new BigDecimal(0);
if (Ops.or(Ops.isNull( am.lib().nameIn(Ops.concat(Ops.concat( am.lib().nameIn("work_item.w_criteria_blk"),"."), p_divnam))),Ops.eq( DP_STD.DP_OPDV_DIVCOD_N(am.getDBTransaction(), am.lib().nameIn(Ops.concat(Ops.concat( am.lib().nameIn("work_item.w_criteria_blk"),"."), p_divnam)),null, am.lib().nameIn("global.g_userid")),new BigDecimal(0))))
{ am.lib().goItem(Ops.concat(Ops.concat( am.lib().nameIn("work_item.w_criteria_blk"),"."), p_divnam));
 am.lib().copy( am.lib().fstdUsrMsg("345"),"work_item.w_errmsg");
 am.lib().setAlertProperty("AL_STD_WARN", am.lib().alertMessageText, am.lib().nameIn("work_item.w_errmsg"));
 status =  am.lib().showAlert("AL_STD_WARN");
throw am.lib().getFormTriggerFailure();
/* Unreachable Statement in original code
return ;
*/}
else
{ am.lib().copy( am.lib().nameIn(Ops.concat(Ops.concat( am.lib().nameIn("work_item.w_criteria_blk"),"."), p_divnam)),"global.g_divcod");
 am.lib().copy( am.lib().nameIn(Ops.concat(Ops.concat( am.lib().nameIn("work_item.w_criteria_blk"),"."), p_divnam)),"info_blk.info_div");
 am.lib().pstdBlk2Ok();
}
}

public void plPrint() {
BigDecimal  vcount = new BigDecimal(0);
BigDecimal  status =  new BigDecimal(0);
 am.getWorkItemVORow().setWOuser_noVal( am.lib().getApplicationProperty( username));
 am.getWorkItemVORow().setWOpasswd_noVal( am.lib().getApplicationProperty( password));
 am.getWorkItemVORow().setWConStr_noVal( am.lib().getApplicationProperty( am.lib().connectString));
if (Ops.and(Ops.isNull( am.getMainBlockVORow().getSimsInvnoFrom()),Ops.isNull( am.getMainBlockVORow().getSimsInvnoTo())))
{ am.getWorkItemVORow().setWErrmsg_noVal( am.lib().fstdUsrMsg("1032"));
 am.lib().setAlertProperty("AL_STD_WARN", am.lib().alertMessageText, am.getWorkItemVORow().getWErrmsg());
 status =  am.lib().showAlert("AL_STD_WARN");
 am.lib().goItem("MAIN_BLOCK.SIMS_INVNO_FROM");
return ;
}
if (Ops.and(!Ops.isNull( am.getMainBlockVORow().getSimsInvnoFrom()),!Ops.isNull( am.getMainBlockVORow().getSimsInvnoTo())))
{
if (Ops.gt( am.getMainBlockVORow().getSimsInvnoFrom(), am.getMainBlockVORow().getSimsInvnoTo()))
{ am.getWorkItemVORow().setWErrmsg_noVal( am.lib().fstdUsrMsg("1029"));
 am.lib().setAlertProperty("AL_STD_WARN", am.lib().alertMessageText, am.getWorkItemVORow().getWErrmsg());
 status =  am.lib().showAlert("AL_STD_WARN");
 am.lib().goItem("MAIN_BLOCK.SIMS_INVNO_FROM");
return ;
}
}
{
DP_MIG_XSIRPM2.PL_PRINT1_results  result = DP_MIG_XSIRPM2.PL_PRINT1(am.getDBTransaction(), am.getMainBlockVORow().getSimsInvnoFrom(), am.getMainBlockVORow().getSimsInvnoTo(), am.getGlobalVORow().getGDivcod());
 vcount = result.getO_VCOUNT();
}
if (Ops.gt( vcount,new BigDecimal(0)))
{ am.helpers().plPrintSaminv( am.getMainBlockVORow().getSimsInvnoFrom(), am.getMainBlockVORow().getSimsInvnoTo());
}
else
{ am.getWorkItemVORow().setWErrmsg_noVal( am.lib().fstdUsrMsg("1033"));
 am.lib().setAlertProperty("AL_STD_WARN", am.lib().alertMessageText, am.getWorkItemVORow().getWErrmsg());
 status =  am.lib().showAlert("AL_STD_WARN");
 am.lib().goItem("MAIN_BLOCK.SIMS_INVNO_FROM");
}
}

public void plLockSampleInvoice() {
BigDecimal  saminvrun =  new BigDecimal(0);
String  temp_num =  null;
BigDecimal  status =  new BigDecimal(0);
try {
if (Ops.eq( am.lib().nvl( am.getParamVORow().getPSimsInvnoFrom(),"~!@#$%^&*()_+"), am.lib().nvl( am.getParamVORow().getPSimsInvnoTo(),"+_)(*&^%$#@!~")))
{
if (Ops.eq( am.getParamVORow().getPCallFromEdit(),"Y"))
{try {
{
DP_MIG_XSIRPM2.PL_LOCK_SAMPLE_INVOICE1_results  result = DP_MIG_XSIRPM2.PL_LOCK_SAMPLE_INVOICE1(am.getDBTransaction(), am.getParamVORow().getPSimsInvnoFrom(), am.getGlobalVORow().getGDivcod());
 saminvrun = result.getO_SAMINVRUN();
}
}
catch (Exception ex)
{;
}
try {
 am.getGlobalVORow().setGLockType_noVal("SAMPLE INVOICE");
 am.getGlobalVORow().setGLockRunnum_noVal( saminvrun);
{
DP_MIG_XSIRPM2.PL_LOCK_SAMPLE_INVOICE2_results  result = DP_MIG_XSIRPM2.PL_LOCK_SAMPLE_INVOICE2(am.getDBTransaction(), saminvrun, am.getGlobalVORow().getGDivcod());
 saminvrun = result.getO_SAMINVRUN();
}
}
catch (NoDataFound ex)
{;
}
}
}
}
catch (Exception ex)
{
if (Ops.eq( am.getGlobalVORow().getGLockType(),"SAMPLE INVOICE"))
{try {
{
DP_MIG_XSIRPM2.PL_LOCK_SAMPLE_INVOICE3_results  result = DP_MIG_XSIRPM2.PL_LOCK_SAMPLE_INVOICE3(am.getDBTransaction(), am.getGlobalVORow().getGDivcod(), am.getGlobalVORow().getGLockRunnum());
 temp_num = result.getO_TEMP_NUM();
}
}
catch (Exception ex)
{ temp_num = null;
}
}
 am.getWorkItemVORow().setWErrmsg_noVal( am.lib().fstdUsrMsg("673"));
 am.getWorkItemVORow().setWErrmsg_noVal(Ops.concat(Ops.concat( am.lib().substr( am.getWorkItemVORow().getWErrmsg(),new BigDecimal(1), am.lib().instr( am.getWorkItemVORow().getWErrmsg()," ",new BigDecimal(1),new BigDecimal(1))), temp_num), am.lib().substr( am.getWorkItemVORow().getWErrmsg(), am.lib().instr( am.getWorkItemVORow().getWErrmsg()," ",new BigDecimal(1),new BigDecimal(1)))));
 am.lib().setAlertProperty("AL_STD_WARN", am.lib().alertMessageText, am.getWorkItemVORow().getWErrmsg());
 status =  am.lib().showAlert("AL_STD_WARN");
 am.getGlobalVORow().setGIvmsLockFailure_noVal("Y");
 am.lib().exitForm( am.lib().noCommit, am.lib().noRollback);
}
}

public void plSetMenuMultiRec() {
try {
 am.lib().plUpdateXsirpm2Menu("LST", am.getGlobalVORow().getGUserid(), am.getGlobalVORow().getGAutgrp());
}
catch (FormTriggerFailure ex)
{throw am.lib().getFormTriggerFailure();
}
catch (Exception ex)
{ am.lib().plShowUserMessage(Ops.concat(Ops.concat("System Error in ","pl_set_menu_multi_rec  "), am.lib().sqlerrm));
throw am.lib().getFormTriggerFailure();
/* Unreachable Statement in original code
 am.lib().setMenuItemProperty("FILE.FILE_NEW", am.lib().visible, am.lib().propertyTrue);
*/
}
}

public void plUpdateWindowTitle() {
String  list_mode = null;
 am.lib().setWindowProperty("WIN_MAIN", am.lib().title,Ops.concat(Ops.concat("Form XSIRPM2 - Print Sample Invoice (Divsion ", am.getGlobalVORow().getGDivcod()),")"));
}

}

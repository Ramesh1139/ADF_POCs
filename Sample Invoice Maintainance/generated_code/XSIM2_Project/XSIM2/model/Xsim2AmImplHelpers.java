package model;
public class Xsim2AmImplHelpers {
    public Xsim2AmImplHelpers(Xsim2AmImpl am) {
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

public Boolean plValidateCuscod() {
BigDecimal  i =  new BigDecimal(0);
if (Ops.isNull( am.lib().nameIn(Ops.concat( am.getWorkItemVORow().getWDetailBlk(),".SIMS_CUSCOD"))))
{ am.lib().copy(null,Ops.concat( am.getWorkItemVORow().getWDetailBlk(),".CPAL_NAM"));
if (Ops.eq( am.getSystemVORow().getFormStatus(),"CHANGED"))
{
if (Ops.ne( am.getBlk3TsiMstVORow().getSimsCuscod(), am.getWorkItemVORow().getWCuscod()))
{ am.lib().copy(null,Ops.concat( am.getWorkItemVORow().getWDetailBlk(),".SIMS_ADRCOD"));
}
}
return  true;
}
else
{
{
DP_MIG_XSIM2.PL_VALIDATE_CUSCOD1_results  result = DP_MIG_XSIM2.PL_VALIDATE_CUSCOD1(am.getDBTransaction(), am.getBlk3TsiMstVORow().getSimsCuscod(), am.getGlobalVORow().getGDivcod());
 i = result.getO_I();
}
if (Ops.gt( i,new BigDecimal(0)))
{
{
DP_MIG_XSIM2.PL_VALIDATE_CUSCOD2_results  result = DP_MIG_XSIM2.PL_VALIDATE_CUSCOD2(am.getDBTransaction(), am.getBlk3TsiMstVORow().getSimsCuscod());
 am.getBlk3TsiMstVORow().setCpalNam_noVal(result.getO_CPAL_NAM());
}
if (Ops.eq( am.getSystemVORow().getFormStatus(),"CHANGED"))
{
if (Ops.ne( am.getBlk3TsiMstVORow().getSimsCuscod(), am.getWorkItemVORow().getWCuscod()))
{ am.lib().copy(null,Ops.concat( am.getWorkItemVORow().getWDetailBlk(),".SIMS_ADRCOD"));
}
}
return  true;
}
else
{ am.lib().copy(null,Ops.concat( am.getWorkItemVORow().getWDetailBlk(),".CPAL_NAM"));
if (Ops.eq( am.getSystemVORow().getFormStatus(),"CHANGED"))
{
if (Ops.ne( am.getBlk3TsiMstVORow().getSimsCuscod(), am.getWorkItemVORow().getWCuscod()))
{ am.lib().copy(null,Ops.concat( am.getWorkItemVORow().getWDetailBlk(),".SIMS_ADRCOD"));
}
}
return  false;
}
}
return null;
}

public Boolean plValidatePortL() {
BigDecimal  i =  new BigDecimal(0);
if (Ops.isNull( am.lib().nameIn(Ops.concat( am.getWorkItemVORow().getWDetailBlk(),".SIMS_PORT_L"))))
{ am.lib().copy(null,Ops.concat( am.getWorkItemVORow().getWDetailBlk(),".MPCP_NAM_L"));
return  true;
}
else
{
{
DP_MIG_XSIM2.PL_VALIDATE_PORT_L1_results  result = DP_MIG_XSIM2.PL_VALIDATE_PORT_L1(am.getDBTransaction(), am.getBlk3TsiMstVORow().getSimsPortL());
 i = result.getO_I();
}
if (Ops.gt( i,new BigDecimal(0)))
{
{
DP_MIG_XSIM2.PL_VALIDATE_PORT_L2_results  result = DP_MIG_XSIM2.PL_VALIDATE_PORT_L2(am.getDBTransaction(), am.getBlk3TsiMstVORow().getSimsPortL());
 am.getBlk3TsiMstVORow().setMpcpNamL_noVal(result.getO_MPCP_NAM_L());
}
return  true;
}
else
{ am.lib().copy(null,Ops.concat( am.getWorkItemVORow().getWDetailBlk(),".MPCP_NAM_L"));
return  false;
}
}
return null;
}

public Boolean plValidateShpmod() {
BigDecimal  i =  new BigDecimal(0);
if (Ops.isNull( am.lib().nameIn(Ops.concat( am.getWorkItemVORow().getWDetailBlk(),".SIMS_SHPMODCOD"))))
{ am.lib().copy(null,Ops.concat( am.getWorkItemVORow().getWDetailBlk(),".MPSM_DES"));
return  true;
}
else
{
{
DP_MIG_XSIM2.PL_VALIDATE_SHPMOD1_results  result = DP_MIG_XSIM2.PL_VALIDATE_SHPMOD1(am.getDBTransaction(), am.getBlk3TsiMstVORow().getSimsShpmodcod());
 i = result.getO_I();
}
if (Ops.gt( i,new BigDecimal(0)))
{
{
DP_MIG_XSIM2.PL_VALIDATE_SHPMOD2_results  result = DP_MIG_XSIM2.PL_VALIDATE_SHPMOD2(am.getDBTransaction(), am.getBlk3TsiMstVORow().getSimsShpmodcod());
 am.getBlk3TsiMstVORow().setMpsmDes_noVal(result.getO_MPSM_DES());
}
return  true;
}
else
{ am.lib().copy(null,Ops.concat( am.getWorkItemVORow().getWDetailBlk(),".MPSM_DES"));
return  false;
}
}
return null;
}

public Boolean plValidateAdrcod() {
BigDecimal  i =  new BigDecimal(0);
if (Ops.isNull( am.lib().nameIn(Ops.concat( am.getWorkItemVORow().getWDetailBlk(),".SIMS_ADRCOD"))))
{return  true;
}
else
{
{
DP_MIG_XSIM2.PL_VALIDATE_ADRCOD1_results  result = DP_MIG_XSIM2.PL_VALIDATE_ADRCOD1(am.getDBTransaction(), am.getBlk3TsiMstVORow().getSimsAdrcod(), am.getBlk3TsiMstVORow().getSimsCuscod());
 i = result.getO_I();
}
if (Ops.gt( i,new BigDecimal(0)))
{
if (Ops.or(Ops.eq( am.lib().nameIn("SYSTEM.FORM_STATUS"),"CHANGED"),Ops.and(Ops.and(Ops.and(Ops.isNull( am.getBlk3TsiMstVORow().getSimsAdr1()),Ops.isNull( am.getBlk3TsiMstVORow().getSimsAdr2())),Ops.isNull( am.getBlk3TsiMstVORow().getSimsAdr3())),Ops.isNull( am.getBlk3TsiMstVORow().getSimsAdr4()))))
{
{
DP_MIG_XSIM2.PL_VALIDATE_ADRCOD2_results  result = DP_MIG_XSIM2.PL_VALIDATE_ADRCOD2(am.getDBTransaction(), am.getBlk3TsiMstVORow().getSimsCuscod(), am.getBlk3TsiMstVORow().getSimsAdrcod());
 am.getBlk3TsiMstVORow().setSimsAdr1_noVal(result.getO_SIMS_ADR1());
 am.getBlk3TsiMstVORow().setSimsAdr2_noVal(result.getO_SIMS_ADR2());
 am.getBlk3TsiMstVORow().setSimsAdr3_noVal(result.getO_SIMS_ADR3());
 am.getBlk3TsiMstVORow().setSimsAdr4_noVal(result.getO_SIMS_ADR4());
}
}
return  true;
}
return  true;
}
return null;
}

public Boolean plValidatePortD() {
BigDecimal  i =  new BigDecimal(0);
if (Ops.isNull( am.lib().nameIn(Ops.concat( am.getWorkItemVORow().getWDetailBlk(),".SIMS_PORT_D"))))
{ am.lib().copy(null,Ops.concat( am.getWorkItemVORow().getWDetailBlk(),".MPCP_NAM_D"));
return  true;
}
else
{
{
DP_MIG_XSIM2.PL_VALIDATE_PORT_D1_results  result = DP_MIG_XSIM2.PL_VALIDATE_PORT_D1(am.getDBTransaction(), am.getBlk3TsiMstVORow().getSimsPortD());
 i = result.getO_I();
}
if (Ops.gt( i,new BigDecimal(0)))
{
{
DP_MIG_XSIM2.PL_VALIDATE_PORT_D2_results  result = DP_MIG_XSIM2.PL_VALIDATE_PORT_D2(am.getDBTransaction(), am.getBlk3TsiMstVORow().getSimsPortD());
 am.getBlk3TsiMstVORow().setMpcpNamD_noVal(result.getO_MPCP_NAM_D());
}
return  true;
}
else
{ am.lib().copy(null,Ops.concat( am.getWorkItemVORow().getWDetailBlk(),".MPCP_NAM_D"));
return  false;
}
}
return null;
}

public Boolean plValidateDptcod() {
BigDecimal  i =  new BigDecimal(0);
if (Ops.isNull( am.lib().nameIn(Ops.concat( am.getWorkItemVORow().getWDetailBlk(),".SIMS_DPTCOD"))))
{ am.lib().copy(null,Ops.concat( am.getWorkItemVORow().getWDetailBlk(),".OPDP_NAM"));
return  true;
}
else
{
{
DP_MIG_XSIM2.PL_VALIDATE_DPTCOD1_results  result = DP_MIG_XSIM2.PL_VALIDATE_DPTCOD1(am.getDBTransaction(), am.getBlk3TsiMstVORow().getSimsDptcod(), am.getWorkItemVORow().getWDiv());
 i = result.getO_I();
}
if (Ops.gt( i,new BigDecimal(0)))
{
{
DP_MIG_XSIM2.PL_VALIDATE_DPTCOD2_results  result = DP_MIG_XSIM2.PL_VALIDATE_DPTCOD2(am.getDBTransaction(), am.getBlk3TsiMstVORow().getSimsDptcod(), am.getWorkItemVORow().getWDiv());
 am.getBlk3TsiMstVORow().setOpdpNam_noVal(result.getO_OPDP_NAM());
}
return  true;
}
else
{ am.lib().copy(null,Ops.concat( am.getWorkItemVORow().getWDetailBlk(),".OPDP_NAM"));
return  false;
}
}
return null;
}

public Boolean plValidateDivcod() {
BigDecimal  i =  new BigDecimal(0);
{
DP_MIG_XSIM2.PL_VALIDATE_DIVCOD1_results  result = DP_MIG_XSIM2.PL_VALIDATE_DIVCOD1(am.getDBTransaction(), am.getBlk2CriteriaVORow().getSimsDivcod());
 i = result.getO_I();
}
if (Ops.gt( i,new BigDecimal(0)))
{return  true;
}
else
{ am.getWorkItemVORow().setWErrmsg_noVal( am.lib().fstdUsrMsg("986"));
 am.lib().setAlertProperty("AL_STD_WARN", am.lib().alertMessageText, am.getWorkItemVORow().getWErrmsg());
 i =  am.lib().showAlert("AL_STD_WARN");
return  false;
}
return null;
}

public Boolean checkLineNum(String  str_in) {
BigDecimal  cnt_line = new BigDecimal(0);
BigDecimal  num_chr = new BigDecimal(0);
if (!Ops.isNull( str_in))
{for (int i = Ops.toInt(new BigDecimal(1)); i<= Ops.toInt( am.lib().nvl( am.lib().length( str_in),new BigDecimal(0)));i++) {
 num_chr = Ops.plus( num_chr,new BigDecimal(1));
if (Ops.eq( am.lib().substr( str_in, i,new BigDecimal(1)), am.lib().chr(new BigDecimal(10))))
{ cnt_line = Ops.plus( cnt_line,new BigDecimal(1));
 num_chr = new BigDecimal(0);
}
}
if (Ops.gt(Ops.plus( cnt_line,new BigDecimal(1)),new BigDecimal(12)))
{ am.getWorkItemVORow().setWErrmsg_noVal( am.lib().fstdUsrMsg("1190"));
return  false;
}
else
{return  true;
}
}
else
{return  true;
}
return null;
}

public Boolean plValidateVia() {
BigDecimal  i =  new BigDecimal(0);
if (Ops.isNull( am.lib().nameIn(Ops.concat( am.getWorkItemVORow().getWDetailBlk(),".SIMS_VIA"))))
{ am.lib().copy(null,Ops.concat( am.getWorkItemVORow().getWDetailBlk(),".MPCP_NAM_VIA"));
return  true;
}
else
{
{
DP_MIG_XSIM2.PL_VALIDATE_VIA1_results  result = DP_MIG_XSIM2.PL_VALIDATE_VIA1(am.getDBTransaction(), am.getBlk3TsiMstVORow().getSimsVia());
 i = result.getO_I();
}
if (Ops.gt( i,new BigDecimal(0)))
{
{
DP_MIG_XSIM2.PL_VALIDATE_VIA2_results  result = DP_MIG_XSIM2.PL_VALIDATE_VIA2(am.getDBTransaction(), am.getBlk3TsiMstVORow().getSimsVia());
 am.getBlk3TsiMstVORow().setMpcpNamVia_noVal(result.getO_MPCP_NAM_VIA());
}
return  true;
}
else
{ am.lib().copy(null,Ops.concat( am.getWorkItemVORow().getWDetailBlk(),".MPCP_NAM_VIA"));
return  false;
}
}
return null;
}

public Boolean plValidateChrtyp() {
BigDecimal  i =  new BigDecimal(0);
if (Ops.isNull( am.lib().nameIn(Ops.concat( am.getWorkItemVORow().getWDetailBlk(),".SIMS_CHRTYP"))))
{ am.lib().copy(null,Ops.concat( am.getWorkItemVORow().getWDetailBlk(),".SICT_DES"));
return  true;
}
else
{
{
DP_MIG_XSIM2.PL_VALIDATE_CHRTYP1_results  result = DP_MIG_XSIM2.PL_VALIDATE_CHRTYP1(am.getDBTransaction(), am.getBlk3TsiMstVORow().getSimsChrtyp());
 i = result.getO_I();
}
if (Ops.gt( i,new BigDecimal(0)))
{
{
DP_MIG_XSIM2.PL_VALIDATE_CHRTYP2_results  result = DP_MIG_XSIM2.PL_VALIDATE_CHRTYP2(am.getDBTransaction(), am.getBlk3TsiMstVORow().getSimsChrtyp());
 am.getBlk3TsiMstVORow().setSictDes_noVal(result.getO_SICT_DES());
}
return  true;
}
else
{ am.lib().copy(null,Ops.concat( am.getWorkItemVORow().getWDetailBlk(),".SICT_DES"));
return  false;
}
}
return null;
}

public Boolean plValidateCarcod() {
BigDecimal  i =  new BigDecimal(0);
if (Ops.isNull( am.lib().nameIn(Ops.concat( am.getWorkItemVORow().getWDetailBlk(),".SIMS_CARCOD"))))
{ am.lib().copy(null,Ops.concat( am.getWorkItemVORow().getWDetailBlk(),".MPCA_CARNAM"));
return  true;
}
else
{
{
DP_MIG_XSIM2.PL_VALIDATE_CARCOD1_results  result = DP_MIG_XSIM2.PL_VALIDATE_CARCOD1(am.getDBTransaction(), am.getBlk3TsiMstVORow().getSimsCarcod());
 i = result.getO_I();
}
if (Ops.gt( i,new BigDecimal(0)))
{
{
DP_MIG_XSIM2.PL_VALIDATE_CARCOD2_results  result = DP_MIG_XSIM2.PL_VALIDATE_CARCOD2(am.getDBTransaction(), am.getBlk3TsiMstVORow().getSimsCarcod());
 am.getBlk3TsiMstVORow().setMpcaCarnam_noVal(result.getO_MPCA_CARNAM());
}
return  true;
}
else
{ am.lib().copy(null,Ops.concat( am.getWorkItemVORow().getWDetailBlk(),".MPCA_CARNAM"));
return  false;
}
}
return null;
}

public Boolean plValidateConpercod() {
BigDecimal  i =  new BigDecimal(0);
if (Ops.isNull( am.lib().nameIn(Ops.concat( am.getWorkItemVORow().getWDetailBlk(),".SIMS_CONPERCOD"))))
{ am.lib().copy(null,Ops.concat( am.getWorkItemVORow().getWDetailBlk(),".CPCP_CONPERNAM"));
return  true;
}
else
{
{
DP_MIG_XSIM2.PL_VALIDATE_CONPERCOD1_results  result = DP_MIG_XSIM2.PL_VALIDATE_CONPERCOD1(am.getDBTransaction(), am.getBlk3TsiMstVORow().getSimsCuscod(), am.getGlobalVORow().getGDivcod());
 i = result.getO_I();
}
if (Ops.gt( i,new BigDecimal(0)))
{
{
DP_MIG_XSIM2.PL_VALIDATE_CONPERCOD2_results  result = DP_MIG_XSIM2.PL_VALIDATE_CONPERCOD2(am.getDBTransaction(), am.getBlk3TsiMstVORow().getSimsCuscod(), am.getBlk3TsiMstVORow().getSimsConpercod(), am.getGlobalVORow().getGDivcod());
 am.getBlk3TsiMstVORow().setCpcpConpernam_noVal(result.getO_CPCP_CONPERNAM());
}
return  true;
}
else
{ am.lib().copy(null,Ops.concat( am.getWorkItemVORow().getWDetailBlk(),".CPCP_CONPERNAM"));
return  false;
}
}
return null;
}

public String plFormatCsvField(String  p_string) {
if (Ops.gt( am.lib().instr( p_string,","),new BigDecimal(0)))
{return Ops.concat(Ops.concat("\"", am.lib().replace( am.lib().replace( am.lib().replace( p_string, am.lib().chr(new BigDecimal(10))," "), am.lib().chr(new BigDecimal(13))," "),"\"","\"\"")),"\"");
}
else
{return  am.lib().replace( am.lib().replace( p_string, am.lib().chr(new BigDecimal(10))," "), am.lib().chr(new BigDecimal(13))," ");
}
}

public Boolean flCreateText(text_io$file_type  in_file) {
FlCreateText_CSiitVOImpl c_siit = am.getFlCreateText_CSiitVO();
BigDecimal  reccnt =  new BigDecimal(0);
String  linebuf =  null;
BigDecimal  lock_runnum =  new BigDecimal(0);
 reccnt = new BigDecimal(0);
/*Set bind variables for VO and prepare for loop*/
c_siit.setI_SIMS_SAMINVRUN( am.getBlk6UpdlstVORow().getSimsSaminvrun());
c_siit.setI_G_DIVCOD( am.getGlobalVORow().getGDivcod());
am.lib().prepareVO(c_siit);
{
RowSetIterator rsi = c_siit.createRowSetIterator(null);
rsi.reset();
FlCreateText_CSiitVORowImpl rec=null;
/*Ready to loop through VO Cursor*/
 while(rsi.hasNext()) {
rec =(FlCreateText_CSiitVORowImpl) rsi.next();
 reccnt = Ops.plus( reccnt,new BigDecimal(1));
 linebuf = Ops.concat(Ops.concat(Ops.concat(Ops.concat(Ops.concat(Ops.concat(Ops.concat(Ops.concat(Ops.concat(Ops.concat(Ops.concat(Ops.concat(Ops.concat(Ops.concat(Ops.concat(Ops.concat(Ops.concat(Ops.concat(Ops.concat(Ops.concat(Ops.concat(Ops.concat(Ops.concat(Ops.concat( am.helpers().plFormatCsvField( rec.getDivcod()),","), am.helpers().plFormatCsvField( rec.getSimsAwbref())),","), am.helpers().plFormatCsvField( rec.getSimsCuscod())),","), am.helpers().plFormatCsvField( rec.getCpcpBuyer())),","), am.helpers().plFormatCsvField( rec.getSupfulnam())),","), am.helpers().plFormatCsvField( rec.getSiitItmnum())),","), am.helpers().plFormatCsvField( rec.getSiitQty())),","), am.helpers().plFormatCsvField( rec.getItmdes())),","), am.helpers().plFormatCsvField( rec.getPortDDes())),","), am.helpers().plFormatCsvField( rec.getSimsInvno())),","), am.helpers().plFormatCsvField( rec.getShpmoddes())),","), am.helpers().plFormatCsvField( rec.getCrtbyNam())),","), am.helpers().plFormatCsvField( rec.getSimsShpdat()));
 am.lib().textIo.putLine( in_file, linebuf);
}
rsi.closeRowSetIterator();
}
am.lib().closeVO(c_siit);
if (Ops.gt( reccnt,new BigDecimal(0)))
{try {
{
DP_MIG_XSIM2.PL_FL_CREATE_TEXT1_results  result = DP_MIG_XSIM2.PL_FL_CREATE_TEXT1(am.getDBTransaction(), am.getBlk6UpdlstVORow().getSimsSaminvrun(), am.getGlobalVORow().getGDivcod());
 lock_runnum = result.getO_LOCK_RUNNUM();
}
{
DP_MIG_XSIM2.PL_FL_CREATE_TEXT2(am.getDBTransaction(), am.getBlk6UpdlstVORow().getSimsSaminvrun(), am.getGlobalVORow().getGDivcod());
}
 am.lib().commit();
}
catch (Exception ex)
{;
}
return  true;
}
return  false;
}

public String blockUpdateOk() {
BigDecimal  this_si =  new BigDecimal(0);
String  return_string = null;
BigDecimal  lock_runnum =  new BigDecimal(0);
String  sql_string =  null;
if (Ops.or(Ops.isNull( am.getBlk6ControlVORow().getSimsAwbref()),Ops.eq( am.getBlk6ControlVORow().getSimsAwbref(),"")))
{return "N";
}
 this_si =  am.getBlk6UpdlstVORow().getSimsSaminvrun();
 sql_string = "N";
if (Ops.or(!Ops.isNull( am.getBlk6ControlVORow().getSimsAwbref()),Ops.ne( am.getBlk6ControlVORow().getSimsAwbref(),"")))
{ sql_string = Ops.concat(Ops.concat(Ops.concat( sql_string,"sims_awbref = '"), am.getBlk6ControlVORow().getSimsAwbref()),"'");
}
if (Ops.ne( sql_string,"N"))
{try {
{
DP_MIG_XSIM2.PL_BLOCK_UPDATE_OK1_results  result = DP_MIG_XSIM2.PL_BLOCK_UPDATE_OK1(am.getDBTransaction(), am.getBlk6UpdlstVORow().getSimsSaminvrun(), am.getGlobalVORow().getGDivcod());
 lock_runnum = result.getO_LOCK_RUNNUM();
}
}
catch (Exception ex)
{ return_string = Ops.concat(Ops.concat( return_string,"Sample invoice in use by another user"), am.lib().chr(new BigDecimal(10)));
return Ops.concat(Ops.concat( am.lib().rpad( am.getBlk6UpdlstVORow().getSimsInvno(),new BigDecimal(17)," "),"Fail            "), return_string);
}
 sql_string =  am.lib().substr( sql_string,new BigDecimal(2));
 sql_string = Ops.concat(Ops.concat(Ops.concat(Ops.concat(Ops.concat(Ops.concat("update tsi_mst set ", sql_string)," where sims_divcod = '"), am.getGlobalVORow().getGDivcod()),"' and sims_saminvrun = to_number('"), am.lib().toChar( this_si)),"')");
try {
 DP_UTL.P_EXECUTE_SQL(am.getDBTransaction(), sql_string);
}
catch (Exception ex)
{ return_string = Ops.concat(Ops.concat( return_string,"Unable to update record.  Please contact HK ITS."), am.lib().chr(new BigDecimal(10)));
return Ops.concat(Ops.concat( am.lib().rpad( am.getBlk6UpdlstVORow().getSimsInvno(),new BigDecimal(17)," "),"Fail            "), return_string);
}
}
 am.lib().commit();
return  am.lib().nvl( return_string,"N");
}

public String plGetUserMessage(String  p_msgcod) {
String  v_msgcod =  null;
String  v_desc =  null;
try {
{
DP_MIG_XSIM2.PL_GET_USER_MESSAGE1_results  result = DP_MIG_XSIM2.PL_GET_USER_MESSAGE1(am.getDBTransaction(), p_msgcod);
 v_desc = result.getO_V_DESC();
}
return  v_desc;
}
catch (NoDataFound ex)
{return "Fatal error! Please contact XTS support. Error message: Message code cannot be found.";
}
catch (Exception ex)
{return "Fatal error! Please contact XTS support. Error message: Unknown error when retrieving error message.";
}
}

public void plInitVariable() {
 am.getWorkItemVORow().setWDisplayHeight_noVal( am.lib().getApplicationProperty( am.lib().displayHeight));
 am.getWorkItemVORow().setWDisplayWidth_noVal( am.lib().getApplicationProperty( am.lib().displayWidth));
 am.getWorkItemVORow().setWFormId_noVal( am.lib().getApplicationProperty( am.lib().currentFormName));
 am.getWorkItemVORow().setWUser_noVal( am.getGlobalVORow().getGUserid());
 am.getWorkItemVORow().setWDiv_noVal( am.getGlobalVORow().getGDivcod());
 am.getWorkItemVORow().setWSystemName_noVal("XTS System");
 am.getWorkItemVORow().setWTableName_noVal("tsi_mst");
 am.getWorkItemVORow().setWMainBlk_noVal(Ops.concat("blk1_", am.getWorkItemVORow().getWTableName()));
 am.getWorkItemVORow().setWDetailBlk_noVal(Ops.concat("blk3_", am.getWorkItemVORow().getWTableName()));
 am.getWorkItemVORow().setWControlBlk_noVal("blk1_control");
 am.getWorkItemVORow().setWCriteriaBlk_noVal("blk2_criteria");
 am.getWorkItemVORow().setWEditMode_noVal("");
 am.getWorkItemVORow().setWFirstItem_noVal("sims_dptcod");
 am.getWorkItemVORow().setWUlevel_noVal("90");
 am.getWorkItemVORow().setWBlk3Copy_noVal("N");
 am.getWorkItemVORow().setWBulkupdBlk_noVal("BLK6_UPDLST");
 am.getWorkItemVORow().setWNoCriteria_noVal("Y");
 am.getWorkItemVORow().setWCurBlock_noVal("LST");
 am.getBlk2CriteriaVORow().setSimsDivcod_noVal( am.getGlobalVORow().getGDivcod());
 am.getWorkItemVORow().setBlk3Valid_noVal("Y");
 am.getWorkItemVORow().setWDtlBlock_noVal("HDR");
 am.getSystemVORow().setMessageLevel_noVal(new BigDecimal(15));
 am.getInfoBlkVORow().setInfoFormName_noVal("S Inv, Db/Cr Note Master Maint.");
 am.getInfoBlkVORow().setInfoFormId_noVal( am.getWorkItemVORow().getWFormId());
 am.getInfoBlkVORow().setInfoUser_noVal( am.getWorkItemVORow().getWUser());
 am.getInfoBlkVORow().setInfoDate_noVal( am.lib().sysdate);
 am.getInfoBlkVORow().setInfoDiv_noVal( am.getWorkItemVORow().getWDiv());
 am.getWorkItemVORow().setWChanged_noVal("N");
 am.getSamBlkVORow().setSamYear_noVal( am.lib().toChar( am.lib().sysdate,"YY"));
 am.helpers().plResetSelectCriteria();
try {
{
DP_MIG_XSIM2.PL_INIT_VARIABLE1_results  result = DP_MIG_XSIM2.PL_INIT_VARIABLE1(am.getDBTransaction());
 am.getWorkItemVORow().setWMdate_noVal(result.getO_W_MDATE());
}
}
catch (Exception ex)
{ am.getWorkItemVORow().setWMdate_noVal(new BigDecimal(365));
}
}

public void checkPackageFailure() {
if (! am.lib().formSuccess)
{throw am.lib().getFormTriggerFailure();
}
}

public void clearAllMasterDetails() {
String  mastblk =  null;
String  coordop =  null;
String  trigblk =  null;
String  startitm =  null;
String  frmstat =  null;
String  curblk =  null;
String  currel =  null;
String  curdtl =  null;
public String firstChangedBlockBelow(String  master) {
String  curblk =  null;
String  currel =  null;
String  retblk =  null;
 curblk =  master;
 currel =  am.lib().getBlockProperty( curblk, am.lib().firstMasterRelation);
while(!Ops.isNull( currel)) {
 curblk =  am.lib().getRelationProperty( currel, am.lib().detailName);
if (Ops.isIn( am.lib().getBlockProperty( curblk, am.lib().status),"CHANGED","INSERT"))
{return  curblk;
}
else
{ retblk =  am.lib().firstChangedBlockBelow( curblk);
if (!Ops.isNull( retblk))
{return  retblk;
}
else
{ currel =  am.lib().getRelationProperty( currel, am.lib().nextMasterRelation);
}
}
}
return null;
try {
 mastblk =  am.getSystemVORow().getMasterBlock();
 coordop =  am.getSystemVORow().getCoordinationOperation();
 trigblk =  am.getSystemVORow().getTriggerBlock();
 startitm =  am.getSystemVORow().getTriggerItem();
 frmstat =  am.getSystemVORow().getFormStatus();
if (!Ops.isIn( coordop,"CLEAR_RECORD","SYNCHRONIZE_BLOCKS"))
{
if (Ops.eq( mastblk, trigblk))
{
if (Ops.eq( frmstat,"CHANGED"))
{ curblk =  first_changed_block_below( mastblk);
if (!Ops.isNull( curblk))
{ am.lib().goBlock( curblk);
 am.helpers().checkPackageFailure();
 am.lib().clearBlock( am.lib().noValidate);
if (!Ops.or(Ops.eq( am.getSystemVORow().getFormStatus(),"QUERY"),Ops.eq( am.getSystemVORow().getBlockStatus(),"NEW")))
{throw am.lib().getFormTriggerFailure();
}
}
}
}
}
 currel =  am.lib().getBlockProperty( trigblk, am.lib().firstMasterRelation);
while(!Ops.isNull( currel)) {
 curdtl =  am.lib().getRelationProperty( currel, am.lib().detailName);
if (Ops.ne( am.lib().getBlockProperty( curdtl, am.lib().status),"NEW"))
{ am.lib().goBlock( curdtl);
 am.helpers().checkPackageFailure();
 am.lib().clearBlock( am.lib().noValidate);
if (Ops.ne( am.getSystemVORow().getBlockStatus(),"NEW"))
{throw am.lib().getFormTriggerFailure();
}
}
 currel =  am.lib().getRelationProperty( currel, am.lib().nextMasterRelation);
}
if (Ops.ne( am.getSystemVORow().getCursorItem(), startitm))
{ am.lib().goItem( startitm);
 am.helpers().checkPackageFailure();
}
}
catch (FormTriggerFailure ex)
{
if (Ops.ne( am.getSystemVORow().getCursorItem(), startitm))
{ am.lib().goItem( startitm);
}
throw am.lib().get();
}
}

public void queryMasterDetails(relation  rel_id,String  detail) {
String  oldmsg =  null;
String  reldef =  null;
try {
 reldef =  am.lib().getRelationProperty( rel_id, am.lib().deferredCoordination);
 oldmsg =  am.getSystemVORow().getMessageLevel();
if (Ops.eq( reldef,"FALSE"))
{ am.lib().goBlock( detail);
 am.helpers().checkPackageFailure();
 am.getSystemVORow().setMessageLevel_noVal("10");
 am.lib().executeQuery();
 am.getSystemVORow().setMessageLevel_noVal( oldmsg);
}
else
{ am.lib().setBlockProperty( detail, am.lib().coordinationStatus, am.lib().nonCoordinated);
}
}
catch (FormTriggerFailure ex)
{ am.getSystemVORow().setMessageLevel_noVal( oldmsg);
throw am.lib().get();
}
}

public void plShowInvno() {
BigDecimal  i =  new BigDecimal(0);
{
DP_MIG_XSIM2.PL_SHOW_INVNO1_results  result = DP_MIG_XSIM2.PL_SHOW_INVNO1(am.getDBTransaction(), am.getBlk3TsiMstVORow().getSimsSaminvrun());
 i = result.getO_I();
}
if (Ops.eq( i,new BigDecimal(1)))
{
{
DP_MIG_XSIM2.PL_SHOW_INVNO2_results  result = DP_MIG_XSIM2.PL_SHOW_INVNO2(am.getDBTransaction(), am.getBlk3TsiMstVORow().getSimsSaminvrun());
 am.getBlk3TsiMstVORow().setSimsInvno_noVal(result.getO_SIMS_INVNO());
}
}
else
{ am.lib().copy(null,"BLK3_TSI_MST.SIMS_INVNO");
}
}

public void plSetCustDefault() {
String  cndsalcod =  null;
String  cndsaldes =  null;
String  paytrmcod =  null;
String  paytrmdes =  null;
String  v_cpms_invdefcsg =  null;
try {
{
DP_MIG_XSIM2.PL_SET_CUST_DEFAULT1_results  result = DP_MIG_XSIM2.PL_SET_CUST_DEFAULT1(am.getDBTransaction(), am.getBlk3TsiMstVORow().getSimsCuscod(), am.getGlobalVORow().getGDivcod());
 cndsalcod = result.getO_CNDSALCOD();
 cndsaldes = result.getO_CNDSALDES();
 paytrmcod = result.getO_PAYTRMCOD();
 paytrmdes = result.getO_PAYTRMDES();
 am.getBlk3TsiMstVORow().setSimsNam_noVal(result.getO_SIMS_NAM());
 am.getBlk3TsiMstVORow().setSimsCarcod_noVal(result.getO_SIMS_CARCOD());
 am.getBlk3TsiMstVORow().setSimsAdrcod_noVal(result.getO_SIMS_ADRCOD());
 am.getBlk3TsiMstVORow().setSimsAdr1_noVal(result.getO_SIMS_ADR1());
 am.getBlk3TsiMstVORow().setSimsAdr2_noVal(result.getO_SIMS_ADR2());
 am.getBlk3TsiMstVORow().setSimsAdr3_noVal(result.getO_SIMS_ADR3());
 am.getBlk3TsiMstVORow().setSimsAdr4_noVal(result.getO_SIMS_ADR4());
 v_cpms_invdefcsg = result.getO_V_CPMS_INVDEFCSG();
}
if (!Ops.isNull( v_cpms_invdefcsg))
{try {
{
DP_MIG_XSIM2.PL_SET_CUST_DEFAULT2_results  result = DP_MIG_XSIM2.PL_SET_CUST_DEFAULT2(am.getDBTransaction(), am.getBlk3TsiMstVORow().getSimsCuscod(), v_cpms_invdefcsg, am.getGlobalVORow().getGDivcod());
 am.getBlk3TsiMstVORow().setSimsNam_noVal(result.getO_SIMS_NAM());
 am.getBlk3TsiMstVORow().setSimsAdr1_noVal(result.getO_SIMS_ADR1());
 am.getBlk3TsiMstVORow().setSimsAdr2_noVal(result.getO_SIMS_ADR2());
 am.getBlk3TsiMstVORow().setSimsAdr3_noVal(result.getO_SIMS_ADR3());
 am.getBlk3TsiMstVORow().setSimsAdr4_noVal(result.getO_SIMS_ADR4());
}
}
catch (NoDataFound ex)
{;
}
}
}
catch (NoDataFound ex)
{ am.getBlk3TsiMstVORow().setSimsCndsal_noVal("");
 am.getBlk3TsiMstVORow().setSimsTrmcur_noVal("");
 am.getBlk3TsiMstVORow().setSimsNam_noVal("");
 am.getBlk3TsiMstVORow().setSimsCarcod_noVal("");
 am.getBlk3TsiMstVORow().setMpcaCarnam_noVal("");
 am.getBlk3TsiMstVORow().setSimsAdrcod_noVal("");
 am.getBlk3TsiMstVORow().setSimsAdr1_noVal("");
 am.getBlk3TsiMstVORow().setSimsAdr2_noVal("");
 am.getBlk3TsiMstVORow().setSimsAdr3_noVal("");
 am.getBlk3TsiMstVORow().setSimsAdr4_noVal("");
}
if (!Ops.isNull( am.lib().rtrim( am.lib().ltrim( cndsaldes))))
{ am.getBlk3TsiMstVORow().setSimsCndsal_noVal(Ops.concat(Ops.concat( cndsalcod," "), cndsaldes));
}
else
{ am.getBlk3TsiMstVORow().setSimsCndsal_noVal( cndsalcod);
}
if (!Ops.isNull( am.lib().rtrim( am.lib().ltrim( paytrmcod))))
{
if (!Ops.isNull( am.lib().rtrim( am.lib().ltrim( paytrmdes))))
{ am.getBlk3TsiMstVORow().setSimsTrmcur_noVal( am.lib().substr(Ops.concat(Ops.concat( paytrmcod," "), paytrmdes),new BigDecimal(1),new BigDecimal(25)));
}
else
{ am.getBlk3TsiMstVORow().setSimsTrmcur_noVal( paytrmcod);
}
}
else
{ am.getBlk3TsiMstVORow().setSimsTrmcur_noVal( paytrmdes);
}
if (!Ops.isNull( am.lib().rtrim( am.lib().ltrim( am.getBlk3TsiMstVORow().getSimsCarcod()))))
{try {
{
DP_MIG_XSIM2.PL_SET_CUST_DEFAULT3_results  result = DP_MIG_XSIM2.PL_SET_CUST_DEFAULT3(am.getDBTransaction(), am.getBlk3TsiMstVORow().getSimsCarcod());
 am.getBlk3TsiMstVORow().setMpcaCarnam_noVal(result.getO_MPCA_CARNAM());
}
}
catch (NoDataFound ex)
{ am.getBlk3TsiMstVORow().setMpcaCarnam_noVal("");
}
}
}

public void plPrintSaminv(String  invno_from/*TODO:Handle Default Value: null*/,String  invno_to/*TODO:Handle Default Value: null*/) {
PlPrintSaminv_CQyVOImpl c_qy = am.getPlPrintSaminv_CQyVO();
ParamList  pl_id;
String  vlinfeed =  null;
/*Set bind variables for VO and prepare for loop*/
c_qy.setI_INVNO_FROM( invno_from);
c_qy.setI_INVNO_TO( invno_to);
c_qy.setI_SIMS_CLSDAT( am.getBlk2CriteriaVORow().getSimsClsdat());
am.lib().prepareVO(c_qy);
{
RowSetIterator rsi = c_qy.createRowSetIterator(null);
rsi.reset();
PlPrintSaminv_CQyVORowImpl c=null;
/*Ready to loop through VO Cursor*/
 while(rsi.hasNext()) {
c =(PlPrintSaminv_CQyVORowImpl) rsi.next();
try {
{
DP_MIG_XSIM2.PL_PRINT_SAMINV1_results  result = DP_MIG_XSIM2.PL_PRINT_SAMINV1(am.getDBTransaction(), am.getWorkItemVORow().getWDiv(), c.getSimsCuscod());
 vlinfeed = result.getO_VLINFEED();
}
}
catch (Exception ex)
{ vlinfeed = null;
}
if (Ops.isNull( vlinfeed))
{try {
{
DP_MIG_XSIM2.PL_PRINT_SAMINV2_results  result = DP_MIG_XSIM2.PL_PRINT_SAMINV2(am.getDBTransaction(), am.getWorkItemVORow().getWDiv());
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
 am.lib().addParameter( pl_id,"HDR_SKIP_LINES", am.lib().textParameter, vlinfeed);
 am.lib().addParameter( pl_id,"DESTYPE", am.lib().textParameter, am.getPrintBlkVORow().getListDes());
 am.lib().addParameter( pl_id,"PARAMFORM", am.lib().textParameter,"NO");
 am.lib().runProduct( am.lib().reports,"xsir", am.lib().synchronous, am.lib().runtime, am.lib().filesystem, pl_id,null);
}
rsi.closeRowSetIterator();
}
am.lib().closeVO(c_qy);
}

public void pstdUsrRollback() {
String  sp_name =  null;
BigDecimal  status =  new BigDecimal(0);
if (Ops.eq( am.getGlobalVORow().getGGlobalEnd(),"Y"))
{ sp_name = null;
 am.getGlobalVORow().setSpName_noVal(new BigDecimal(0));
}
else
{ sp_name = Ops.concat("usr_", am.getGlobalVORow().getSpName());
 am.getGlobalVORow().setSpName_noVal(Ops.minus( am.lib().toNumber( am.getGlobalVORow().getSpName()),new BigDecimal(1)));
}
 am.lib().issueRollback( sp_name);
}

public void pstdUsrSavepoint() {
String  sp_name =  null;
BigDecimal  status =  new BigDecimal(0);
 am.getGlobalVORow().setSpName_noVal(Ops.plus( am.lib().toNumber( am.getGlobalVORow().getSpName()),new BigDecimal(1)));
 sp_name = Ops.concat("usr_", am.getGlobalVORow().getSpName());
 am.lib().issueSavepoint( sp_name);
}

public void plSetLovPosition(String  pi_lov_name,String  pi_item_name) {
BigDecimal  max_x =  new BigDecimal(0);
BigDecimal  max_y =  new BigDecimal(0);
BigDecimal  x =  new BigDecimal(0);
BigDecimal  y =  new BigDecimal(0);
String  v_window_name =  null;
 max_x = Ops.minus( am.lib().getWindowProperty("WIN_MAIN", am.lib().width),new BigDecimal(10));
 max_y = Ops.minus( am.lib().getWindowProperty("WIN_MAIN", am.lib().height),new BigDecimal(10));
 x = Ops.minus( am.lib().nvl( am.lib().getViewProperty( am.lib().getItemProperty( pi_item_name, am.lib().canvasName), am.lib().viewportXPos),new BigDecimal(0)),Ops.plus(Ops.plus( am.lib().nvl( am.lib().getViewProperty( am.lib().getItemProperty( pi_item_name, am.lib().canvasName), am.lib().viewportXPosOnCanvas),new BigDecimal(0)), am.lib().getItemProperty( pi_item_name, am.lib().xPos)), am.lib().getItemProperty( pi_item_name, am.lib().width)));
 y = Ops.minus( am.lib().nvl( am.lib().getViewProperty( am.lib().getItemProperty( pi_item_name, am.lib().canvasName), am.lib().viewportYPos),new BigDecimal(0)),Ops.plus(Ops.plus( am.lib().nvl( am.lib().getViewProperty( am.lib().getItemProperty( pi_item_name, am.lib().canvasName), am.lib().viewportYPosOnCanvas),new BigDecimal(0)), am.lib().getItemProperty( pi_item_name, am.lib().yPos)), am.lib().getItemProperty( pi_item_name, am.lib().height)));
 v_window_name =  am.lib().getViewProperty( am.lib().getItemProperty( pi_item_name, am.lib().canvasName), am.lib().windowName);
if (Ops.ne( v_window_name,"WIN_MAIN"))
{ x = Ops.plus( x, am.lib().getWindowProperty( v_window_name, am.lib().xPos));
 y = Ops.plus( y, am.lib().getWindowProperty( v_window_name, am.lib().yPos));
}
if (Ops.ge(Ops.plus( x, am.lib().getLovProperty( pi_lov_name, am.lib().width)), max_x))
{ x = Ops.minus( max_x, am.lib().getLovProperty( pi_lov_name, am.lib().width));
}
if (Ops.ge(Ops.plus( y, am.lib().getLovProperty( pi_lov_name, am.lib().height)), max_y))
{ y = Ops.minus( max_y, am.lib().getLovProperty( pi_lov_name, am.lib().height));
}
 am.lib().setLovProperty( pi_lov_name, am.lib().position, x, y);
}

public void plLockSi(String  divcod,BigDecimal  saminvrun) {
BigDecimal  si_run =  new BigDecimal(0);
BigDecimal  status =  new BigDecimal(0);
 am.helpers().pstdUsrSavepoint();
try {
{
DP_MIG_XSIM2.PL_LOCK_SI1_results  result = DP_MIG_XSIM2.PL_LOCK_SI1(am.getDBTransaction(), divcod, saminvrun);
 si_run = result.getO_SI_RUN();
}
}
catch (NoDataFound ex)
{ am.getWorkItemVORow().setWErrmsg_noVal( am.lib().fstdUsrMsg("1188"));
 am.lib().setAlertProperty("AL_STD_WARN", am.lib().alertMessageText, am.getWorkItemVORow().getWErrmsg());
 status =  am.lib().showAlert("AL_STD_WARN");
 am.lib().goItem("blk1_control.blk1_refresh");
 am.lib().executeTrigger("When-Button-Pressed");
throw am.lib().getFormTriggerFailure();
/* Unreachable Statement in original code
return ;
*/
}
}

public void plShowUserMessage(String  p_message,String  p_title/*TODO:Handle Default Value: null*/,String  p_msgtyp/*TODO:Handle Default Value: "NOTE"*/) {
String  v_message =  am.lib().substr( p_message,new BigDecimal(1),new BigDecimal(250));
String  v_msgtyp =  p_msgtyp;
BigDecimal  v_status =  new BigDecimal(0);
if (Ops.eq( am.lib().upper( v_msgtyp),"NOTE"))
{ am.lib().setAlertProperty("AL_STD_NOTE", am.lib().title, am.lib().nvl( p_title,"Information"));
 am.lib().setAlertProperty("AL_STD_NOTE", am.lib().alertMessageText, v_message);
 v_status =  am.lib().showAlert("AL_STD_NOTE");
}
else
{
if(Ops.eq( am.lib().upper( v_msgtyp),"CAUTION"))
{ am.lib().setAlertProperty("AL_STD_CAUTION", am.lib().title, am.lib().nvl( p_title,"Caution"));
 am.lib().setAlertProperty("AL_STD_CAUTION", am.lib().alertMessageText, v_message);
 v_status =  am.lib().showAlert("AL_STD_CAUTION");
}
else
{
if(Ops.eq( am.lib().upper( v_msgtyp),"WARN"))
{ am.lib().setAlertProperty("AL_STD_WARN", am.lib().title, am.lib().nvl( p_title,"Warning"));
 am.lib().setAlertProperty("AL_STD_WARN", am.lib().alertMessageText, v_message);
 v_status =  am.lib().showAlert("AL_STD_WARN");
}
else
{ am.lib().setAlertProperty("AL_STD_CAUTION", am.lib().title, am.lib().nvl( p_title,"Information"));
 am.lib().setAlertProperty("AL_STD_CAUTION", am.lib().alertMessageText, v_message);
 v_status =  am.lib().showAlert("AL_STD_CAUTION");
}
}
}
}

public void pMessage(String  p_message,String  p_msgtyp/*TODO:Handle Default Value: "CAUTION"*/) {
BigDecimal  v_status =  new BigDecimal(0);
if (Ops.eq( am.lib().upper( p_msgtyp),"NOTE"))
{ am.lib().setAlertProperty("AL_STD_NOTE", am.lib().title,"Information");
 am.lib().setAlertProperty("AL_STD_NOTE", am.lib().alertMessageText, p_message);
 v_status =  am.lib().showAlert("AL_STD_NOTE");
}
else
{
if(Ops.eq( am.lib().upper( p_msgtyp),"CAUTION"))
{ am.lib().setAlertProperty("AL_STD_CAUTION", am.lib().title,"Caution");
 am.lib().setAlertProperty("AL_STD_CAUTION", am.lib().alertMessageText, p_message);
 v_status =  am.lib().showAlert("AL_STD_CAUTION");
}
else
{
if(Ops.eq( am.lib().upper( p_msgtyp),"WARN"))
{ am.lib().setAlertProperty("AL_STD_WARN", am.lib().title,"Warning");
 am.lib().setAlertProperty("AL_STD_WARN", am.lib().alertMessageText, p_message);
 v_status =  am.lib().showAlert("AL_STD_WARN");
}
else
{ am.lib().setAlertProperty("AL_STD_CAUTION", am.lib().title,"Information");
 am.lib().setAlertProperty("AL_STD_CAUTION", am.lib().alertMessageText, p_message);
 v_status =  am.lib().showAlert("AL_STD_CAUTION");
}
}
}
}

public void plBlk5Save() {
BigDecimal  i =  new BigDecimal(0);
 am.lib().goBlock("blk5_tsi_itm");
 am.lib().firstRecord();
while (true) {
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
DP_MIG_XSIM2.PL_BLK5_SAVE1_results  result = DP_MIG_XSIM2.PL_BLK5_SAVE1(am.getDBTransaction(), am.getBlk5TsiItmVORow().getSiitTyp());
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
DP_MIG_XSIM2.PL_BLK5_SAVE2_results  result = DP_MIG_XSIM2.PL_BLK5_SAVE2(am.getDBTransaction(), am.getBlk5TsiItmVORow().getSiitSupcod());
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
DP_MIG_XSIM2.PL_BLK5_SAVE3_results  result = DP_MIG_XSIM2.PL_BLK5_SAVE3(am.getDBTransaction(), stscod);
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
if (Ops.and(Ops.and(!Ops.isNull( am.getBlk5TsiItmVORow().getCaseNo()),Ops.ne( am.lib().nvl( am.getBlk5TsiItmVORow().getCaseNo(),"!@#$%"),"!@#$%")),Ops.isNull( am.getBlk5TsiItmVORow().getSiitPrRunnum())))
{ am.getWorkItemVORow().setWErritm_noVal("CASE_NO");
 am.getWorkItemVORow().setWErrmsg_noVal( am.lib().fstdUsrMsg("312"));
throw am.lib().getFormTriggerFailure();
/* Unreachable Statement in original code
return ;
*/}
if (Ops.ne( DP_CASE.CHECK_SETCOD(am.getDBTransaction(), am.getGlobalVORow().getGDivcod(),"*","355"),"Y"))
{
if (Ops.and(!Ops.isNull( am.getBlk5TsiItmVORow().getSiitHarmoncod()),Ops.ne( am.lib().nvl( am.getBlk5TsiItmVORow().getSiitHarmoncod(),"!@#$%"),"!@#$%")))
{
{
DP_MIG_XSIM2.PL_BLK5_SAVE4_results  result = DP_MIG_XSIM2.PL_BLK5_SAVE4(am.getDBTransaction(), am.getBlk5TsiItmVORow().getSiitHarmoncod());
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
DP_MIG_XSIM2.PL_BLK5_SAVE5_results  result = DP_MIG_XSIM2.PL_BLK5_SAVE5(am.getDBTransaction(), am.getBlk5TsiItmVORow().getSiitUom());
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
if (Ops.eq( am.getSystemVORow().getLastRecord(),"TRUE")) break;
 am.lib().nextRecord();
}
 am.helpers().pstdUsrSavepoint();
return ;
}

public void plBlk8Enable() {
 am.lib().setItemProperty("BLK8_CONTROL.blk8_first", am.lib().enabled, am.lib().propertyTrue);
 am.lib().setItemProperty("BLK8_CONTROL.blk8_prev", am.lib().enabled, am.lib().propertyTrue);
 am.lib().setItemProperty("BLK8_CONTROL.blk8_next", am.lib().enabled, am.lib().propertyTrue);
 am.lib().setItemProperty("BLK8_CONTROL.blk8_last", am.lib().enabled, am.lib().propertyTrue);
if (Ops.and(Ops.eq( am.getGlobalVORow().getGGlobalStscod(),"AT"),Ops.or(Ops.and(Ops.eq( am.getGlobalVORow().getAuthNew(),"Y"),Ops.eq( am.getGlobalVORow().getIvmsEditMode(),"A")),Ops.eq( am.getGlobalVORow().getAuthEdit(),"Y"))))
{ am.lib().setItemProperty("BLK8_CONTROL.blk8_auto", am.lib().enabled, am.lib().propertyTrue);
 am.lib().setItemProperty("BLK8_CONTROL.blk8_auto_all", am.lib().enabled, am.lib().propertyTrue);
 am.lib().setItemProperty("BLK8_CONTROL.blk8_check_upc", am.lib().enabled, am.lib().propertyTrue);
}
else
{ am.lib().setItemProperty("BLK8_CONTROL.blk8_auto", am.lib().enabled, am.lib().propertyFalse);
 am.lib().setItemProperty("BLK8_CONTROL.blk8_auto_all", am.lib().enabled, am.lib().propertyFalse);
 am.lib().setItemProperty("BLK8_CONTROL.blk8_check_upc", am.lib().enabled, am.lib().propertyFalse);
}
}

public void plBlk3Save() {
if (Ops.ne( DP_CASE.CHECK_DPT_SETCOD(am.getDBTransaction(), am.getGlobalVORow().getGDivcod(), am.getBlk3TsiMstVORow().getSimsDptcod(), am.getBlk3TsiMstVORow().getSimsCuscod(),"J73"),"N"))
{
if (Ops.isNull( am.getBlk3TsiMstVORow().getSimsChrtyp()))
{ am.getWorkItemVORow().setWErrmsg_noVal("Charge type cannot be null");
 am.getWorkItemVORow().setWErritm_noVal(Ops.concat( am.getWorkItemVORow().getWDetailBlk(),".sims_chrtyp"));
 am.lib().pstdDisplayError();
 am.lib().goItem( am.getWorkItemVORow().getWErritm());
}
}
}

public void plCallNotepad() {
String  notepad_path =  null;
String  copy_path =  null;
String  web_path =  null;
String  web_rep_path =  null;
String  l_file_name =  null;
String  temp_rptcod =  null;
RuntimeException nolock;BigDecimal  status =  new BigDecimal(0);
 notepad_path =  f_get_notepad_path;
if (Ops.eq( am.lib().getApplicationProperty( am.lib().userInterface),"WEB"))
{ web_path =  am.lib().winApiEnvironment.readRegistry("HKEY_LOCAL_MACHINE\\SOFTWARE\\ORACLE\\HOME1","FORMS60_OUTPUT", true);
 l_file_name =  am.lib().replace( am.getGlobalVORow().getTempFilename(),Ops.concat( am.getGlobalVORow().getTempDir(),"\\"),"");
 am.lib().winApiUtility.copyFile( am.getGlobalVORow().getTempFilename(),Ops.concat(Ops.concat( web_path,"\\"), l_file_name), true, true);
 am.lib().winApiUtility.deleteFile( am.getGlobalVORow().getTempFilename(), true);
 web_rep_path = Ops.concat( am.lib().winApiEnvironment.readRegistry("HKEY_LOCAL_MACHINE\\SOFTWARE\\ORACLE\\HOME1","SERVER_URL", true), am.lib().winApiEnvironment.readRegistry("HKEY_LOCAL_MACHINE\\SOFTWARE\\ORACLE\\HOME1","FORMS60_MAPPING", true));
 am.lib().web.showDocument(Ops.concat(Ops.concat( web_rep_path,"/"), l_file_name));
}
else
{ am.lib().setAlertProperty("AL_STD_WARN", am.lib().alertMessageText,"Log file located at C:\\xsim.txt");
 status =  am.lib().showAlert("AL_STD_WARN");
 am.lib().winApiUtility.copyFile("C:\\xsim.txt",Ops.concat( am.getGlobalVORow().getTempDir(),"\\xsim.txt"), true, true);
 am.lib().winApiUtility.deleteFile("C:\\xsim.txt", true);
 am.lib().host(Ops.concat(Ops.concat(Ops.concat( notepad_path," "), am.getGlobalVORow().getTempDir()),"\\xsim.txt"));
}
}

public void plSetMenuSingleRec() {
try {
 am.lib().plUpdateXsim2Menu("DTL", am.getWorkItemVORow().getWDtlBlock(), am.getGlobalVORow().getGUserid(), am.getWorkItemVORow().getWNew(), am.getWorkItemVORow().getWEdit(), am.getWorkItemVORow().getWEnq(), am.getWorkItemVORow().getWDel(), am.getWorkItemVORow().getWPrt(), am.getGlobalVORow().getGEnquiry(), am.getGlobalVORow().getGAutgrp());
}
catch (FormTriggerFailure ex)
{throw am.lib().getFormTriggerFailure();
}
catch (Exception ex)
{ am.helpers().plShowUserMessage(Ops.concat(Ops.concat("System Error in ","pl_set_menu_single_rec  "), am.lib().sqlerrm));
throw am.lib().getFormTriggerFailure();
}
}

public void plUpdateWindowTitle() {
String  list_mode = null;
 am.lib().setWindowProperty("WIN_MAIN", am.lib().title,Ops.concat(Ops.concat("Form XSIM2 - Sample Invoice Maintenance (Divsion ", am.getGlobalVORow().getGDivcod()),")"));
}

public void plSetMenuMultiRec() {
try {
 am.lib().plUpdateXsim2Menu("LST", am.getWorkItemVORow().getWDtlBlock(), am.getGlobalVORow().getGUserid(), am.getWorkItemVORow().getWNew(), am.getWorkItemVORow().getWEdit(), am.getWorkItemVORow().getWEnq(), am.getWorkItemVORow().getWDel(), am.getWorkItemVORow().getWPrt(), am.getGlobalVORow().getGEnquiry(), am.getGlobalVORow().getGAutgrp());
}
catch (FormTriggerFailure ex)
{throw am.lib().getFormTriggerFailure();
}
catch (Exception ex)
{ am.helpers().plShowUserMessage(Ops.concat(Ops.concat("System Error in ","pl_set_menu_multi_rec  "), am.lib().sqlerrm));
throw am.lib().getFormTriggerFailure();
/* Unreachable Statement in original code
 am.lib().setMenuItemProperty("FILE.FILE_NEW", am.lib().visible, am.lib().propertyTrue);
*/
}
}

public void plCallExcel2(String  p_first_macro/*TODO:Handle Default Value: "N"*/,String  p_last_macro/*TODO:Handle Default Value: "N"*/,String  p_macro_name/*TODO:Handle Default Value: "XDRMACRO.XLS"*/) {
BigDecimal  status =  new BigDecimal(0);
String  excel_path =  null;
String  copy_path =  null;
String  web_path =  null;
String  web_rep_path =  null;
String  l_file_name =  null;
String  l_macro_path =  null;
String  temp_rptcod =  null;
String  v_bch_drpt_svr =  null;
String  excel_macro_file =  null;
BigDecimal  excel_macro_len =  new BigDecimal(0);
String  excel_file =  null;
BigDecimal  excel_len =  new BigDecimal(0);
 l_macro_path =  am.lib().winApiEnvironment.readRegistry("HKEY_LOCAL_MACHINE\\SOFTWARE\\ORACLE","MACRO_PATH", false);
if (Ops.isNull( l_macro_path))
{ l_macro_path = "f:\\project\\xts32\\excel\\";
}
else
{ l_macro_path = Ops.concat( l_macro_path,"\\");
}
 excel_path =  am.lib().winApiEnvironment.readRegistry("HKEY_LOCAL_MACHINE\\SOFTWARE\\Classes\\ExcelWorksheet\\protocol\\StdFileEditing\\server","", true);
if (Ops.isNull( excel_path))
{ am.getWorkItemVORow().setWErrmsg_noVal( am.lib().fstdUsrMsg("2030"));
 am.lib().setAlertProperty("AL_STD_WARN", am.lib().alertMessageText, am.getWorkItemVORow().getWErrmsg());
 status =  am.lib().showAlert("AL_STD_WARN");
throw am.lib().getFormTriggerFailure();
}
else
{
if (Ops.eq( am.lib().getApplicationProperty( am.lib().userInterface),"WEB"))
{try {
 excel_file = Ops.concat( am.lib().substr( am.getGlobalVORow().getTempFilename(),Ops.plus( am.lib().nvl( am.lib().length( am.getGlobalVORow().getTempDir()),new BigDecimal(0)),new BigDecimal(2)),Ops.minus(Ops.minus( am.lib().nvl( am.lib().length( am.getGlobalVORow().getTempFilename()),new BigDecimal(0)), am.lib().nvl( am.lib().length( am.getGlobalVORow().getTempDir()),new BigDecimal(0))),new BigDecimal(5))),".XLS");
 excel_macro_file = Ops.concat( am.lib().substr( excel_file,new BigDecimal(1),Ops.minus( am.lib().nvl( am.lib().length( excel_file),new BigDecimal(0)),new BigDecimal(4))),"c.xls");
if (Ops.eq( p_first_macro,"Y"))
{ am.lib().winApiUtility.copyFile( am.getGlobalVORow().getTempFilename(),Ops.concat("c:\\", excel_file), true, true);
 am.lib().winApiUtility.deleteFile( am.getGlobalVORow().getTempFilename(), true);
}
 am.lib().winApiUtility.copyFile(Ops.concat( l_macro_path, p_macro_name),Ops.concat("c:\\", excel_macro_file), true, true);
 web_path =  am.lib().winApiEnvironment.readRegistry("HKEY_LOCAL_MACHINE\\SOFTWARE\\ORACLE\\HOME1","forms60_OUTPUT", true);
 am.lib().host(Ops.concat(Ops.concat(Ops.concat( excel_path," "),"c:\\"), excel_macro_file));
if (Ops.eq( p_last_macro,"N"))
{ am.lib().winApiUtility.deleteFile(Ops.concat("C:\\", excel_macro_file), true);
}
else
{ am.lib().winApiUtility.copyFile(Ops.concat("C:\\", excel_file),Ops.concat(Ops.concat( web_path,"\\"), excel_file), true, true);
 am.lib().winApiUtility.deleteFile(Ops.concat("C:\\", excel_file), true);
 am.lib().winApiUtility.deleteFile(Ops.concat("C:\\", excel_macro_file), true);
 l_file_name =  am.lib().replace( am.getGlobalVORow().getTempFilename(),Ops.concat( am.getGlobalVORow().getTempDir(),"\\"),"");
 web_rep_path = Ops.concat( am.lib().winApiEnvironment.readRegistry("HKEY_LOCAL_MACHINE\\SOFTWARE\\ORACLE\\HOME1","SERVER_URL", true), am.lib().winApiEnvironment.readRegistry("HKEY_LOCAL_MACHINE\\SOFTWARE\\ORACLE\\HOME1","forms60_MAPPING", true));
 am.lib().web.showDocument(Ops.concat( web_rep_path, l_file_name));
}
}
catch (Exception ex)
{ am.lib().setAlertProperty("AL_STD_WARN", am.lib().alertMessageText,Ops.concat("Fatal Error! Please contact XTS support.  Oracle Message: ", am.lib().sqlerrm));
 status =  am.lib().showAlert("AL_STD_WARN");
}
}
else
{ am.lib().host(Ops.concat(Ops.concat(Ops.concat( excel_path," "), l_macro_path), p_macro_name));
if (Ops.eq( p_last_macro,"Y"))
{ am.lib().winApiUtility.copyFile("C:\\XDRTMPF.XLS",Ops.concat( am.getGlobalVORow().getTempDir(),"\\XDRTMPF.XLS"), true, true);
 am.lib().winApiUtility.deleteFile("C:\\XDRTMPF.XLS", true);
 am.lib().host(Ops.concat(Ops.concat(Ops.concat( excel_path," "), am.getGlobalVORow().getTempDir()),"\\XDRTMPF.XLS"));
}
}
}
}

public void plSetSortButtonIcon(String  p_block,String  p_button,String  p_order) {
if (Ops.eq( am.lib().upper( p_order),"ASC"))
{
if (Ops.eq( am.lib().upper( p_block),"BLK1_TSI_MST"))
{ am.lib().setItemProperty( p_button, am.lib().iconName,"sort_asc");
}
}
else
{
if(Ops.eq( am.lib().upper( p_order),"DESC"))
{
if (Ops.eq( am.lib().upper( p_block),"BLK1_TSI_MST"))
{ am.lib().setItemProperty( p_button, am.lib().iconName,"sort_desc");
}
}
}
}

public void plBlk1OrderBy(String  p_order_by,String  p_item) {
BigDecimal  status =  new BigDecimal(0);
try {
 am.lib().synchronize();
if (Ops.isNull( am.getBlk1TsiMstVORow().getSimsInvno()))
{return ;
}
 am.lib().setItemProperty("BLK1_CONTROL.BT_BLK1_DOC_NO", am.lib().iconName,"sort");
 am.lib().setItemProperty("BLK1_CONTROL.BT_BLK1_YEAR", am.lib().iconName,"sort");
 am.lib().setItemProperty("BLK1_CONTROL.BT_BLK1_CUSTOMER", am.lib().iconName,"sort");
 am.lib().setItemProperty("BLK1_CONTROL.BT_BLK1_DEPT_CODE", am.lib().iconName,"sort");
 am.lib().setItemProperty("BLK1_CONTROL.BT_BLK1_INVDAT", am.lib().iconName,"sort");
 am.lib().setItemProperty("BLK1_CONTROL.BT_BLK1_SHPDAT", am.lib().iconName,"sort");
 am.lib().setItemProperty("BLK1_CONTROL.BT_BLK1_PORT_D", am.lib().iconName,"sort");
 am.lib().setItemProperty("BLK1_CONTROL.BT_BLK1_SHIP_MODE", am.lib().iconName,"sort");
if (Ops.or(Ops.eq( am.getWorkItemVORow().getWBlk1OrderType(),"DESC"),Ops.isNull( am.getWorkItemVORow().getWBlk1OrderType())))
{ am.getWorkItemVORow().setWBlk1OrderType_noVal("ASC");
 am.lib().setBlockProperty("BLK1_TSI_MST", am.lib().orderBy,Ops.concat( p_order_by," ASC"));
 am.helpers().plSetSortButtonIcon("BLK1_TSI_MST", p_item,"ASC");
}
else
{ am.getWorkItemVORow().setWBlk1OrderType_noVal("DESC");
 am.lib().setBlockProperty("BLK1_TSI_MST", am.lib().orderBy,Ops.concat( p_order_by," DESC"));
 am.helpers().plSetSortButtonIcon("BLK1_TSI_MST", p_item,"DESC");
}
 am.getWorkItemVORow().setWSearchMode_noVal("B");
 am.lib().goBlock("BLK1_TSI_MST");
 am.lib().executeQuery();
 am.getBlk1ControlVORow().setChkBoxCheckedNum_noVal(new BigDecimal(0));
 am.lib().goItem("BLK1_TSI_MST.SIMS_INVNO");
}
catch (FormTriggerFailure ex)
{throw am.lib().getFormTriggerFailure();
}
catch (Exception ex)
{ am.lib().setAlertProperty("al_std_warn", am.lib().alertMessageText,Ops.concat("Fatal error! Please contact XTS support. Error message : ", am.lib().sqlerrm));
 status =  am.lib().showAlert("al_std_warn");
throw am.lib().getFormTriggerFailure();
}
}

public void plResetSelectCriteria() {
Date  p_date;
 am.getWorkItemVORow().setWSearchMode_noVal("B");
 am.getWorkItemVORow().setWRecordAdding_noVal("N");
 am.getBlk2CriteriaVORow().setActive_noVal("Y");
 p_date =  DP_DEF.GET_SYS_DATE(am.getDBTransaction(),new BigDecimal(-365));
 am.getBlk2CriteriaVORow().setSelectInvDatFr_noVal( p_date);
 p_date =  DP_DEF.GET_SYS_DATE(am.getDBTransaction(),new BigDecimal(10));
 am.getBlk2CriteriaVORow().setSelectInvDatTo_noVal( p_date);
 p_date =  DP_DEF.GET_SYS_DATE(am.getDBTransaction(),new BigDecimal(-365));
 am.getBlk2CriteriaVORow().setSelectShpDatFr_noVal( p_date);
 p_date =  DP_DEF.GET_SYS_DATE(am.getDBTransaction(),new BigDecimal(10));
 am.getBlk2CriteriaVORow().setSelectShpDatTo_noVal( p_date);
}

public void plGoToTab(String  pi_tab) {
 am.lib().setItemProperty("BLK3_CONTROL.BLK3_GEN", am.lib().visualAttribute,"VA_TAB_NORMAL");
 am.lib().setItemProperty("BLK3_CONTROL.BLK3_ADDITM", am.lib().visualAttribute,"VA_TAB_NORMAL");
 am.lib().setItemProperty(Ops.concat("blk3_control.", pi_tab), am.lib().visualAttribute,"VA_TAB_HIGHLIGHT");
}

}

package model.views;
public class Blk6ControlVOFieldEvents {
    public Blk6ControlVOFieldEvents(Xsim2AmImpl am) {
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
public void Blk6Exit_bp(){
 am.lib().goBlock("blk6_control");
 am.lib().clearBlock();
 am.lib().hideView("blk6_CAN");
 am.lib().showView("BLK1_CAN");
 am.lib().showView("info_can");
 am.lib().goBlock("BLK1_TSI_MST");
 am.lib().executeQuery();
}
public void PrtSelunprt_bp(){
 am.lib().goBlock("BLK6_UPDLST");
 am.getWorkItemVORow().setWSelected_noVal(new BigDecimal(0));
 am.lib().firstRecord();
while(Ops.ne( am.lib().upper( am.getSystemVORow().getLastRecord()),"TRUE")) {
if (Ops.isNull( am.getBlk6UpdlstVORow().getSimsSnddat()))
{ am.getBlk6UpdlstVORow().setSimsPrt_noVal("Y");
 am.getWorkItemVORow().setWSelected_noVal(Ops.plus( am.getWorkItemVORow().getWSelected(),new BigDecimal(1)));
}
else
{ am.getBlk6UpdlstVORow().setSimsPrt_noVal("N");
}
 am.lib().nextRecord();
}
if (Ops.isNull( am.getBlk6UpdlstVORow().getSimsSnddat()))
{ am.getBlk6UpdlstVORow().setSimsPrt_noVal("Y");
 am.getWorkItemVORow().setWSelected_noVal(Ops.plus( am.getWorkItemVORow().getWSelected(),new BigDecimal(1)));
}
else
{ am.getBlk6UpdlstVORow().setSimsPrt_noVal("N");
}
 am.getBlk6ControlVORow().setTotalSelected_noVal( am.getWorkItemVORow().getWSelected());
 am.lib().firstRecord();
}
public void PrtDesel_bp(){
 am.lib().goBlock("blk6_updlst");
 am.lib().firstRecord();
while(Ops.ne( am.lib().upper( am.getSystemVORow().getLastRecord()),"TRUE")) {
 am.getBlk6UpdlstVORow().setSimsPrt_noVal("N");
 am.lib().nextRecord();
}
 am.getBlk6UpdlstVORow().setSimsPrt_noVal("N");
 am.getWorkItemVORow().setWSelected_noVal(new BigDecimal(0));
 am.getBlk6ControlVORow().setTotalSelected_noVal( am.getWorkItemVORow().getWSelected());
 am.lib().firstRecord();
}
public void PrtSelall_bp(){
BigDecimal  status =  new BigDecimal(0);
 am.lib().goBlock("BLK6_UPDLST");
 am.getWorkItemVORow().setWSelected_noVal(new BigDecimal(0));
 am.lib().firstRecord();
while(Ops.ne( am.lib().upper( am.getSystemVORow().getLastRecord()),"TRUE")) {
if (Ops.lt( am.getWorkItemVORow().getWSelected(),new BigDecimal(10000)))
{ am.getBlk6UpdlstVORow().setSimsPrt_noVal("Y");
 am.getWorkItemVORow().setWSelected_noVal(Ops.plus( am.getWorkItemVORow().getWSelected(),new BigDecimal(1)));
 am.lib().nextRecord();
}
else
{ am.lib().firstRecord();
 am.lib().setAlertProperty("AL_STD_WARN", am.lib().alertMessageText,"Select more than 10000 case is not allow");
 status =  am.lib().showAlert("AL_STD_WARN");
throw am.lib().getFormTriggerFailure();
/* Unreachable Statement in original code
return ;
*/}
}
if (Ops.lt( am.getWorkItemVORow().getWSelected(),new BigDecimal(10000)))
{ am.getBlk6UpdlstVORow().setSimsPrt_noVal("Y");
 am.getWorkItemVORow().setWSelected_noVal(Ops.plus( am.getWorkItemVORow().getWSelected(),new BigDecimal(1)));
}
else
{ am.lib().firstRecord();
 am.lib().setAlertProperty("AL_STD_WARN", am.lib().alertMessageText,"Select more than 10000 case is not allow");
 status =  am.lib().showAlert("AL_STD_WARN");
throw am.lib().getFormTriggerFailure();
/* Unreachable Statement in original code
return ;
*/}
 am.getBlk6ControlVORow().setTotalSelected_noVal( am.getWorkItemVORow().getWSelected());
}
public void PrtExcel_bp(){
Blk6Control_PrtExcel_WhenButtonPressed_CTplVOImpl c_tpl = am.getBlk6Control_PrtExcel_WhenButtonPressed_CTplVO();
text_io$file_type  in_file;
text_io$file_type  log_file;
String  with_error =  null;
String  line_buffer =  null;
String  temp_string =  null;
BigDecimal  temp =  new BigDecimal(0);
BigDecimal  col_used =  new BigDecimal(0);
String  bformat1 =  null;
String  bwidth1 =  null;
String  bgroup1 =  null;
String  bsum1 =  null;
String  bwwrap1 =  null;
String  brow1 =  null;
String  bcol1 =  null;
String  bdata1 =  null;
String  btitle =  null;
String  bdatatype =  null;
String  first_line =  null;
BigDecimal  status =  new BigDecimal(0);
BigDecimal  cntprt = new BigDecimal(0);
if (Ops.eq( am.getBlk6ControlVORow().getTotalSelected(),new BigDecimal(0)))
{ am.lib().setAlertProperty("AL_STD_WARN", am.lib().alertMessageText,"Nothing is selected for the change");
 status =  am.lib().showAlert("AL_STD_WARN");
throw am.lib().getFormTriggerFailure();
/* Unreachable Statement in original code
return ;
*/}
 am.getGlobalVORow().setTempDir_noVal( am.lib().winApiEnvironment.getTempDirectory( true));
if (Ops.eq( am.lib().getApplicationProperty( am.lib().userInterface),"WEB"))
{ am.getGlobalVORow().setTempFilename_noVal( am.lib().winApiUtility.generateTempFilename( am.getGlobalVORow().getTempDir(),"REP", false));
 am.lib().winApiUtility.deleteFile( am.getGlobalVORow().getTempFilename(), true);
 am.getGlobalVORow().setTempFilename_noVal( am.lib().replace( am.getGlobalVORow().getTempFilename(),".tmp",".txt"));
}
try {
if (Ops.eq( am.lib().getApplicationProperty( am.lib().userInterface),"WEB"))
{ log_file =  am.lib().textIo.fopen( am.getGlobalVORow().getTempFilename(),"w");
}
else
{ log_file =  am.lib().textIo.fopen("c:\\xsim.txt","w");
}
}
catch (Exception ex)
{ am.lib().setAlertProperty("AL_STD_WARN", am.lib().alertMessageText,Ops.concat(Ops.concat("Cannot open file (xsim.txt), please confirm the file not open by another program", am.lib().chr(new BigDecimal(10))),"Please close the mass update and open it again or contact ITS if necessary"));
 status =  am.lib().showAlert("AL_STD_WARN");
throw am.lib().getFormTriggerFailure();
/* Unreachable Statement in original code
return ;
*/
}
{
DP_MIG_XSIM2.PL_CONTROL6_PRTEXCEL_BP1_results  result = DP_MIG_XSIM2.PL_CONTROL6_PRTEXCEL_BP1(am.getDBTransaction());
 line_buffer = result.getO_LINE_BUFFER();
}
 line_buffer = Ops.concat(Ops.concat("Mass update for Sample Invoice is performed on ", line_buffer)," at ");
{
DP_MIG_XSIM2.PL_CONTROL6_PRTEXCEL_BP2_results  result = DP_MIG_XSIM2.PL_CONTROL6_PRTEXCEL_BP2(am.getDBTransaction());
 temp_string = result.getO_TEMP_STRING();
}
 line_buffer = Ops.concat(Ops.concat( line_buffer, temp_string),". Result listed as follow:");
 am.lib().textIo.putLine( log_file, line_buffer);
 am.lib().textIo.putLine( log_file,"");
 line_buffer = "Document No.     Update Status   Reason            ";
 am.lib().textIo.putLine( log_file, line_buffer);
 line_buffer = "---------------- --------------- -------------------------------------------------";
 am.lib().textIo.putLine( log_file, line_buffer);
 with_error = "N";
{
DP_MIG_XSIM2.PL_CONTROL6_PRTEXCEL_BP3_results  result = DP_MIG_XSIM2.PL_CONTROL6_PRTEXCEL_BP3(am.getDBTransaction());
 col_used = result.getO_COL_USED();
}
if (Ops.eq( status,new BigDecimal(0)))
{ am.lib().setAlertProperty("AL_STD_WARN", am.lib().alertMessageText,"Invalid report");
 status =  am.lib().showAlert("AL_STD_WARN");
throw am.lib().getFormTriggerFailure();
/* Unreachable Statement in original code
return ;
*/}
 am.getGlobalVORow().setTempDir_noVal( am.lib().winApiEnvironment.getTempDirectory( true));
if (Ops.eq( am.lib().getApplicationProperty( am.lib().userInterface),"WEB"))
{ am.getGlobalVORow().setTempFilename_noVal( am.lib().winApiUtility.generateTempFilename( am.getGlobalVORow().getTempDir(),"REP", false));
 am.lib().winApiUtility.deleteFile( am.getGlobalVORow().getTempFilename(), true);
 am.getGlobalVORow().setTempFilename_noVal( am.lib().replace( am.getGlobalVORow().getTempFilename(),".tmp",".xls"));
}
 am.getGlobalVORow().setGHeadcnt_noVal( am.lib().nvl( am.lib().length( am.helpers().plFormatCsvField("Sample Invoice Report")),new BigDecimal(0)));
if (Ops.eq( am.lib().getApplicationProperty( am.lib().userInterface),"WEB"))
{ in_file =  am.lib().textIo.fopen( am.getGlobalVORow().getTempFilename(),"w");
}
else
{ in_file =  am.lib().textIo.fopen("c:\\xdrtmpf.xls","w");
}
 first_line = Ops.concat(Ops.concat(Ops.concat(Ops.concat(Ops.concat(Ops.concat(Ops.concat(Ops.concat(Ops.concat(Ops.concat(Ops.concat(Ops.concat(Ops.concat(Ops.concat(Ops.concat(Ops.concat( am.helpers().plFormatCsvField( am.lib().toChar( col_used)),","), am.helpers().plFormatCsvField("N")),","), am.helpers().plFormatCsvField(new BigDecimal(0))),","), am.helpers().plFormatCsvField("N")),","), am.helpers().plFormatCsvField( am.getGlobalVORow().getGHeadcnt())),","), am.helpers().plFormatCsvField("Username")),","), am.helpers().plFormatCsvField("Password")),","), am.helpers().plFormatCsvField( am.lib().getApplicationProperty( am.lib().connectString))),","), am.helpers().plFormatCsvField(null));
/*Set bind variables for VO and prepare for loop*/
am.lib().prepareVO(c_tpl);
{
RowSetIterator rsi = c_tpl.createRowSetIterator(null);
rsi.reset();
Blk6Control_PrtExcel_WhenButtonPressed_CTplVORowImpl rec=null;
/*Ready to loop through VO Cursor*/
 while(rsi.hasNext()) {
rec =(Blk6Control_PrtExcel_WhenButtonPressed_CTplVORowImpl) rsi.next();
 bformat1 = Ops.concat(Ops.concat( bformat1,","), am.helpers().plFormatCsvField( rec.getDrclXformat()));
 bwidth1 = Ops.concat(Ops.concat( bwidth1,","), am.helpers().plFormatCsvField( am.lib().toChar( rec.getDrclXwidth())));
 bgroup1 = Ops.concat(Ops.concat( bgroup1,","), am.helpers().plFormatCsvField( rec.getDrclGroup()));
 bsum1 = Ops.concat(Ops.concat( bsum1,","), am.helpers().plFormatCsvField( rec.getDrclSum()));
 bwwrap1 = Ops.concat(Ops.concat( bwwrap1,","), am.helpers().plFormatCsvField( rec.getDrclXwwarp()));
 brow1 = Ops.concat(Ops.concat( brow1,","), am.helpers().plFormatCsvField( am.lib().toChar( rec.getDrtcPivrow())));
 bcol1 = Ops.concat(Ops.concat( bcol1,","), am.helpers().plFormatCsvField( am.lib().toChar( rec.getDrtcPivcol())));
 bdata1 = Ops.concat(Ops.concat( bdata1,","), am.helpers().plFormatCsvField( rec.getDrtcPivdata()));
 bdatatype = Ops.concat(Ops.concat( bdatatype,","), am.helpers().plFormatCsvField( rec.getDrclDatatype()));
 btitle = Ops.concat(Ops.concat( btitle,","), am.helpers().plFormatCsvField( rec.getDrclLabel()));
}
rsi.closeRowSetIterator();
}
am.lib().closeVO(c_tpl);
 am.lib().textIo.putLine( in_file, first_line);
 am.lib().textIo.putLine( in_file, am.lib().substr( bformat1,new BigDecimal(2)));
 am.lib().textIo.putLine( in_file, am.lib().substr( bwidth1,new BigDecimal(2)));
 am.lib().textIo.putLine( in_file, am.lib().substr( bgroup1,new BigDecimal(2)));
 am.lib().textIo.putLine( in_file, am.lib().substr( bsum1,new BigDecimal(2)));
 am.lib().textIo.putLine( in_file, am.lib().substr( bwwrap1,new BigDecimal(2)));
 am.lib().textIo.putLine( in_file, am.lib().substr( brow1,new BigDecimal(2)));
 am.lib().textIo.putLine( in_file, am.lib().substr( bcol1,new BigDecimal(2)));
 am.lib().textIo.putLine( in_file, am.lib().substr( bdata1,new BigDecimal(2)));
 am.lib().textIo.putLine( in_file, am.lib().substr( bdatatype,new BigDecimal(2)));
 am.lib().textIo.putLine( in_file,"");
 am.lib().textIo.putLine( in_file, am.helpers().plFormatCsvField("Sample Invoice Report"));
 am.lib().textIo.putLine( in_file,null);
 am.lib().textIo.putLine( in_file,null);
 am.lib().textIo.putLine( in_file,"");
 am.lib().textIo.putLine( in_file, am.lib().substr( btitle,new BigDecimal(2)));
 am.lib().goBlock("blk6_updlst");
 am.lib().firstRecord();
while(Ops.ne( am.lib().upper( am.getSystemVORow().getLastRecord()),"TRUE")) {
if ( am.lib().checkboxChecked("blk6_updlst.sims_prt"))
{ line_buffer =  am.helpers().blockUpdateOk;
if (Ops.ne( line_buffer,"N"))
{ am.lib().textIo.putLine( log_file, line_buffer);
 with_error = "Y";
}
else
{
if ( am.helpers().flCreateText( in_file))
{ cntprt = Ops.plus( cntprt,new BigDecimal(1));
}
}
}
 am.lib().nextRecord();
}
if ( am.lib().checkboxChecked("blk6_updlst.sims_prt"))
{ line_buffer =  am.helpers().blockUpdateOk;
if (Ops.ne( line_buffer,"N"))
{ am.lib().textIo.putLine( log_file, line_buffer);
 with_error = "Y";
}
else
{
if ( am.helpers().flCreateText( in_file))
{ cntprt = Ops.plus( cntprt,new BigDecimal(1));
}
}
}
 am.lib().textIo.fclose( in_file);
 am.lib().textIo.fclose( log_file);
if (Ops.eq( with_error,"Y"))
{ am.helpers().plCallNotepad();
}
if (Ops.gt( cntprt,new BigDecimal(0)))
{ am.helpers().plCallExcel2("Y","Y");
 am.lib().commitForm();
}
else
{ am.lib().setAlertProperty("AL_STD_WARN", am.lib().alertMessageText,Ops.concat(Ops.concat("Insufficient item information for selected document(s). ", am.lib().chr(new BigDecimal(10))),"Nothing will be printed."));
 status =  am.lib().showAlert("AL_STD_WARN");
}
 am.lib().goItem("blk1_control.blk1_massupd");
 am.lib().executeTrigger("When-Button-Pressed");
}

}

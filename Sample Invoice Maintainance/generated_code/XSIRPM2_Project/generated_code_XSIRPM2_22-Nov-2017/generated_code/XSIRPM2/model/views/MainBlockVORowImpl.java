package model.views;
public class MainBlockVORowImpl {
public String getInvoiceType(){
   return (String)getAttributeInternal(INVOICETYPE);
}
public void setInvoiceType(String value){
   setAttributeInternal(INVOICETYPE, value);
}
public void setInvoiceType_noVal(String value){
   setAttributeInternal(INVOICETYPE, value);
}
public String getWDes(){
   return (String)getAttributeInternal(WDES);
}
public void setWDes(String value){
   setAttributeInternal(WDES, value);
}
public void setWDes_noVal(String value){
   setAttributeInternal(WDES, value);
}
public String getInvRepcode(){
   return (String)getAttributeInternal(INVREPCODE);
}
public void setInvRepcode(String value){
   setAttributeInternal(INVREPCODE, value);
}
public void setInvRepcode_noVal(String value){
   setAttributeInternal(INVREPCODE, value);
}
public String getButtonInvReport(){
   return (String)getAttributeInternal(BUTTONINVREPORT);
}
public void setButtonInvReport(String value){
   setAttributeInternal(BUTTONINVREPORT, value);
}
public void setButtonInvReport_noVal(String value){
   setAttributeInternal(BUTTONINVREPORT, value);
}
public String getInvBackgrd(){
   return (String)getAttributeInternal(INVBACKGRD);
}
public void setInvBackgrd(String value){
   String oldValue = getInvBackgrd();
   try{
       setAttributeInternal(INVBACKGRD, value);
       getAM().getMainBlockVOFieldEvents().InvBackgrd_vi();
   } catch(JboException e){
       setAttributeInternal(INVBACKGRD,oldValue);
       throw(e);
   }
}
public void setInvBackgrd_noVal(String value){
   setAttributeInternal(INVBACKGRD, value);
}
public String getSimsInvnoTo(){
   return (String)getAttributeInternal(SIMSINVNOTO);
}
public void setSimsInvnoTo(String value){
   setAttributeInternal(SIMSINVNOTO, value);
}
public void setSimsInvnoTo_noVal(String value){
   setAttributeInternal(SIMSINVNOTO, value);
}
public String getButtonSimsInvnoTo(){
   return (String)getAttributeInternal(BUTTONSIMSINVNOTO);
}
public void setButtonSimsInvnoTo(String value){
   setAttributeInternal(BUTTONSIMSINVNOTO, value);
}
public void setButtonSimsInvnoTo_noVal(String value){
   setAttributeInternal(BUTTONSIMSINVNOTO, value);
}
public String getButtonSimsInvnoFrom(){
   return (String)getAttributeInternal(BUTTONSIMSINVNOFROM);
}
public void setButtonSimsInvnoFrom(String value){
   setAttributeInternal(BUTTONSIMSINVNOFROM, value);
}
public void setButtonSimsInvnoFrom_noVal(String value){
   setAttributeInternal(BUTTONSIMSINVNOFROM, value);
}
public String getSimsInvnoFrom(){
   return (String)getAttributeInternal(SIMSINVNOFROM);
}
public void setSimsInvnoFrom(String value){
   setAttributeInternal(SIMSINVNOFROM, value);
}
public void setSimsInvnoFrom_noVal(String value){
   setAttributeInternal(SIMSINVNOFROM, value);
}
public BigDecimal getNoCopies(){
   return (BigDecimal)getAttributeInternal(NOCOPIES);
}
public void setNoCopies(BigDecimal value){
   setAttributeInternal(NOCOPIES, value);
}
public void setNoCopies_noVal(BigDecimal value){
   setAttributeInternal(NOCOPIES, value);
}
public void setNoCopies_noVal(String value){
   BigDecimal value1 = String2BigDecimal(value);
   setAttributeInternal(NOCOPIES, value1);
}
public String getPrintLaser(){
   return (String)getAttributeInternal(PRINTLASER);
}
public void setPrintLaser(String value){
   setAttributeInternal(PRINTLASER, value);
}
public void setPrintLaser_noVal(String value){
   setAttributeInternal(PRINTLASER, value);
}
public String getWPrinterName2(){
   return (String)getAttributeInternal(WPRINTERNAME2);
}
public void setWPrinterName2(String value){
   setAttributeInternal(WPRINTERNAME2, value);
}
public void setWPrinterName2_noVal(String value){
   setAttributeInternal(WPRINTERNAME2, value);
}
public String getWPrinterName(){
   return (String)getAttributeInternal(WPRINTERNAME);
}
public void setWPrinterName(String value){
   setAttributeInternal(WPRINTERNAME, value);
}
public void setWPrinterName_noVal(String value){
   setAttributeInternal(WPRINTERNAME, value);
}
}

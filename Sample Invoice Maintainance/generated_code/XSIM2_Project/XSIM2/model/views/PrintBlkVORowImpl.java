package model.views;
public class PrintBlkVORowImpl {
public String getListDes(){
   return (String)getAttributeInternal(LISTDES);
}
public void setListDes(String value){
   setAttributeInternal(LISTDES, value);
}
public void setListDes_noVal(String value){
   setAttributeInternal(LISTDES, value);
}
public String getPrintOk(){
   return (String)getAttributeInternal(PRINTOK);
}
public void setPrintOk(String value){
   setAttributeInternal(PRINTOK, value);
}
public void setPrintOk_noVal(String value){
   setAttributeInternal(PRINTOK, value);
}
public BigDecimal getSimsSaminvrunTo(){
   return (BigDecimal)getAttributeInternal(SIMSSAMINVRUNTO);
}
public void setSimsSaminvrunTo(BigDecimal value){
   setAttributeInternal(SIMSSAMINVRUNTO, value);
}
public void setSimsSaminvrunTo_noVal(BigDecimal value){
   setAttributeInternal(SIMSSAMINVRUNTO, value);
}
public void setSimsSaminvrunTo_noVal(String value){
   BigDecimal value1 = String2BigDecimal(value);
   setAttributeInternal(SIMSSAMINVRUNTO, value1);
}
public String getPrintReset(){
   return (String)getAttributeInternal(PRINTRESET);
}
public void setPrintReset(String value){
   setAttributeInternal(PRINTRESET, value);
}
public void setPrintReset_noVal(String value){
   setAttributeInternal(PRINTRESET, value);
}
public String getPrintCancel(){
   return (String)getAttributeInternal(PRINTCANCEL);
}
public void setPrintCancel(String value){
   setAttributeInternal(PRINTCANCEL, value);
}
public void setPrintCancel_noVal(String value){
   setAttributeInternal(PRINTCANCEL, value);
}
public String getPrintLovSimsInvnoFrom(){
   return (String)getAttributeInternal(PRINTLOVSIMSINVNOFROM);
}
public void setPrintLovSimsInvnoFrom(String value){
   setAttributeInternal(PRINTLOVSIMSINVNOFROM, value);
}
public void setPrintLovSimsInvnoFrom_noVal(String value){
   setAttributeInternal(PRINTLOVSIMSINVNOFROM, value);
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
public BigDecimal getSimsSaminvrunFrom(){
   return (BigDecimal)getAttributeInternal(SIMSSAMINVRUNFROM);
}
public void setSimsSaminvrunFrom(BigDecimal value){
   setAttributeInternal(SIMSSAMINVRUNFROM, value);
}
public void setSimsSaminvrunFrom_noVal(BigDecimal value){
   setAttributeInternal(SIMSSAMINVRUNFROM, value);
}
public void setSimsSaminvrunFrom_noVal(String value){
   BigDecimal value1 = String2BigDecimal(value);
   setAttributeInternal(SIMSSAMINVRUNFROM, value1);
}
public String getPrintLovSimsInvnoTo(){
   return (String)getAttributeInternal(PRINTLOVSIMSINVNOTO);
}
public void setPrintLovSimsInvnoTo(String value){
   setAttributeInternal(PRINTLOVSIMSINVNOTO, value);
}
public void setPrintLovSimsInvnoTo_noVal(String value){
   setAttributeInternal(PRINTLOVSIMSINVNOTO, value);
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
}

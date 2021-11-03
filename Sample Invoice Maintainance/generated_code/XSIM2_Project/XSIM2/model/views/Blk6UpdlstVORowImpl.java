package model.views;
public class Blk6UpdlstVORowImpl {
public String getSimsAwbref(){
   return (String)getAttributeInternal(SIMSAWBREF);
}
public void setSimsAwbref(String value){
   setAttributeInternal(SIMSAWBREF, value);
}
public void setSimsAwbref_noVal(String value){
   setAttributeInternal(SIMSAWBREF, value);
}
public Date getSimsSnddat(){
   return (Date)getAttributeInternal(SIMSSNDDAT);
}
public void setSimsSnddat(Date value){
   setAttributeInternal(SIMSSNDDAT, value);
}
public void setSimsSnddat_noVal(Date value){
   setAttributeInternal(SIMSSNDDAT, value);
}
public void setSimsSnddat_noVal(String value){
   Date value1 = String2Date(value);
   setAttributeInternal(SIMSSNDDAT, value1);
}
public String getSimsDivcod(){
   return (String)getAttributeInternal(SIMSDIVCOD);
}
public void setSimsDivcod(String value){
   setAttributeInternal(SIMSDIVCOD, value);
}
public void setSimsDivcod_noVal(String value){
   setAttributeInternal(SIMSDIVCOD, value);
}
public BigDecimal getSimsSaminvrun(){
   return (BigDecimal)getAttributeInternal(SIMSSAMINVRUN);
}
public void setSimsSaminvrun(BigDecimal value){
   setAttributeInternal(SIMSSAMINVRUN, value);
}
public void setSimsSaminvrun_noVal(BigDecimal value){
   setAttributeInternal(SIMSSAMINVRUN, value);
}
public void setSimsSaminvrun_noVal(String value){
   BigDecimal value1 = String2BigDecimal(value);
   setAttributeInternal(SIMSSAMINVRUN, value1);
}
public String getSimsPrt(){
   return (String)getAttributeInternal(SIMSPRT);
}
public void setSimsPrt(String value){
   String oldValue = getSimsPrt();
   try{
       setAttributeInternal(SIMSPRT, value);
       getAM().getBlk6UpdlstVOFieldEvents().SimsPrt_cc();
   } catch(JboException e){
       setAttributeInternal(SIMSPRT,oldValue);
       throw(e);
   }
}
public void setSimsPrt_noVal(String value){
   setAttributeInternal(SIMSPRT, value);
}
public String getSimsInvno(){
   return (String)getAttributeInternal(SIMSINVNO);
}
public void setSimsInvno(String value){
   setAttributeInternal(SIMSINVNO, value);
}
public void setSimsInvno_noVal(String value){
   setAttributeInternal(SIMSINVNO, value);
}
}

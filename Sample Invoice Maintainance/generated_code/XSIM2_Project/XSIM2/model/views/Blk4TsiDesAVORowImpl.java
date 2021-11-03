package model.views;
public class Blk4TsiDesAVORowImpl {
public String getSidsDivcod(){
   return (String)getAttributeInternal(SIDSDIVCOD);
}
public void setSidsDivcod(String value){
   setAttributeInternal(SIDSDIVCOD, value);
}
public void setSidsDivcod_noVal(String value){
   setAttributeInternal(SIDSDIVCOD, value);
}
public String getBlk4Remark(){
   return (String)getAttributeInternal(BLK4REMARK);
}
public void setBlk4Remark(String value){
   setAttributeInternal(BLK4REMARK, value);
}
public void setBlk4Remark_noVal(String value){
   setAttributeInternal(BLK4REMARK, value);
}
public BigDecimal getSidsSaminvrun(){
   return (BigDecimal)getAttributeInternal(SIDSSAMINVRUN);
}
public void setSidsSaminvrun(BigDecimal value){
   setAttributeInternal(SIDSSAMINVRUN, value);
}
public void setSidsSaminvrun_noVal(BigDecimal value){
   setAttributeInternal(SIDSSAMINVRUN, value);
}
public void setSidsSaminvrun_noVal(String value){
   BigDecimal value1 = String2BigDecimal(value);
   setAttributeInternal(SIDSSAMINVRUN, value1);
}
public String getSidsTyp(){
   return (String)getAttributeInternal(SIDSTYP);
}
public void setSidsTyp(String value){
   setAttributeInternal(SIDSTYP, value);
}
public void setSidsTyp_noVal(String value){
   setAttributeInternal(SIDSTYP, value);
}
public String getSidsDes(){
   return (String)getAttributeInternal(SIDSDES);
}
public void setSidsDes(String value){
   setAttributeInternal(SIDSDES, value);
}
public void setSidsDes_noVal(String value){
   setAttributeInternal(SIDSDES, value);
}
}

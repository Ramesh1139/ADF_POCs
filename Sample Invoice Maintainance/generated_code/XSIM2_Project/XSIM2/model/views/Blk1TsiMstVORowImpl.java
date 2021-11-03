package model.views;
public class Blk1TsiMstVORowImpl {
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
public String getSimsDivcod(){
   return (String)getAttributeInternal(SIMSDIVCOD);
}
public void setSimsDivcod(String value){
   setAttributeInternal(SIMSDIVCOD, value);
}
public void setSimsDivcod_noVal(String value){
   setAttributeInternal(SIMSDIVCOD, value);
}
public String getBlk1Chk(){
   return (String)getAttributeInternal(BLK1CHK);
}
public void setBlk1Chk(String value){
   String oldValue = getBlk1Chk();
   try{
       setAttributeInternal(BLK1CHK, value);
       getAM().getBlk1TsiMstVOFieldEvents().Blk1Chk_cc();
   } catch(JboException e){
       setAttributeInternal(BLK1CHK,oldValue);
       throw(e);
   }
}
public void setBlk1Chk_noVal(String value){
   setAttributeInternal(BLK1CHK, value);
}
public String getSimsCndsal(){
   return (String)getAttributeInternal(SIMSCNDSAL);
}
public void setSimsCndsal(String value){
   setAttributeInternal(SIMSCNDSAL, value);
}
public void setSimsCndsal_noVal(String value){
   setAttributeInternal(SIMSCNDSAL, value);
}
public String getActive(){
   return (String)getAttributeInternal(ACTIVE);
}
public void setActive(String value){
   setAttributeInternal(ACTIVE, value);
}
public void setActive_noVal(String value){
   setAttributeInternal(ACTIVE, value);
}
public Date getSimsClsdat(){
   return (Date)getAttributeInternal(SIMSCLSDAT);
}
public void setSimsClsdat(Date value){
   setAttributeInternal(SIMSCLSDAT, value);
}
public void setSimsClsdat_noVal(Date value){
   setAttributeInternal(SIMSCLSDAT, value);
}
public void setSimsClsdat_noVal(String value){
   Date value1 = String2Date(value);
   setAttributeInternal(SIMSCLSDAT, value1);
}
public String getSimsTrmcur(){
   return (String)getAttributeInternal(SIMSTRMCUR);
}
public void setSimsTrmcur(String value){
   setAttributeInternal(SIMSTRMCUR, value);
}
public void setSimsTrmcur_noVal(String value){
   setAttributeInternal(SIMSTRMCUR, value);
}
public String getSimsShpmodcod(){
   return (String)getAttributeInternal(SIMSSHPMODCOD);
}
public void setSimsShpmodcod(String value){
   setAttributeInternal(SIMSSHPMODCOD, value);
}
public void setSimsShpmodcod_noVal(String value){
   setAttributeInternal(SIMSSHPMODCOD, value);
}
public String getMpsmDes(){
   return (String)getAttributeInternal(MPSMDES);
}
public void setMpsmDes(String value){
   setAttributeInternal(MPSMDES, value);
}
public void setMpsmDes_noVal(String value){
   setAttributeInternal(MPSMDES, value);
}
public String getSimsPortD(){
   return (String)getAttributeInternal(SIMSPORTD);
}
public void setSimsPortD(String value){
   setAttributeInternal(SIMSPORTD, value);
}
public void setSimsPortD_noVal(String value){
   setAttributeInternal(SIMSPORTD, value);
}
public String getMpcpNamD(){
   return (String)getAttributeInternal(MPCPNAMD);
}
public void setMpcpNamD(String value){
   setAttributeInternal(MPCPNAMD, value);
}
public void setMpcpNamD_noVal(String value){
   setAttributeInternal(MPCPNAMD, value);
}
public Date getSimsInvdat(){
   return (Date)getAttributeInternal(SIMSINVDAT);
}
public void setSimsInvdat(Date value){
   setAttributeInternal(SIMSINVDAT, value);
}
public void setSimsInvdat_noVal(Date value){
   setAttributeInternal(SIMSINVDAT, value);
}
public void setSimsInvdat_noVal(String value){
   Date value1 = String2Date(value);
   setAttributeInternal(SIMSINVDAT, value1);
}
public Date getSimsShpdat(){
   return (Date)getAttributeInternal(SIMSSHPDAT);
}
public void setSimsShpdat(Date value){
   setAttributeInternal(SIMSSHPDAT, value);
}
public void setSimsShpdat_noVal(Date value){
   setAttributeInternal(SIMSSHPDAT, value);
}
public void setSimsShpdat_noVal(String value){
   Date value1 = String2Date(value);
   setAttributeInternal(SIMSSHPDAT, value1);
}
public String getSimsCuscod(){
   return (String)getAttributeInternal(SIMSCUSCOD);
}
public void setSimsCuscod(String value){
   setAttributeInternal(SIMSCUSCOD, value);
}
public void setSimsCuscod_noVal(String value){
   setAttributeInternal(SIMSCUSCOD, value);
}
public String getSimsDptcod(){
   return (String)getAttributeInternal(SIMSDPTCOD);
}
public void setSimsDptcod(String value){
   setAttributeInternal(SIMSDPTCOD, value);
}
public void setSimsDptcod_noVal(String value){
   setAttributeInternal(SIMSDPTCOD, value);
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
public String getSimsYear(){
   return (String)getAttributeInternal(SIMSYEAR);
}
public void setSimsYear(String value){
   setAttributeInternal(SIMSYEAR, value);
}
public void setSimsYear_noVal(String value){
   setAttributeInternal(SIMSYEAR, value);
}
}

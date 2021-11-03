package model.views;
public class Blk3TsiMstVORowImpl {
public String getSimsDptcod(){
   return (String)getAttributeInternal(SIMSDPTCOD);
}
public void setSimsDptcod(String value){
   setAttributeInternal(SIMSDPTCOD, value);
}
public void setSimsDptcod_noVal(String value){
   setAttributeInternal(SIMSDPTCOD, value);
}
public String getBlk3LovDptcod(){
   return (String)getAttributeInternal(BLK3LOVDPTCOD);
}
public void setBlk3LovDptcod(String value){
   setAttributeInternal(BLK3LOVDPTCOD, value);
}
public void setBlk3LovDptcod_noVal(String value){
   setAttributeInternal(BLK3LOVDPTCOD, value);
}
public String getOpdpNam(){
   return (String)getAttributeInternal(OPDPNAM);
}
public void setOpdpNam(String value){
   setAttributeInternal(OPDPNAM, value);
}
public void setOpdpNam_noVal(String value){
   setAttributeInternal(OPDPNAM, value);
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
public String getSimsDivcod(){
   return (String)getAttributeInternal(SIMSDIVCOD);
}
public void setSimsDivcod(String value){
   setAttributeInternal(SIMSDIVCOD, value);
}
public void setSimsDivcod_noVal(String value){
   setAttributeInternal(SIMSDIVCOD, value);
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
public String getLstby(){
   return (String)getAttributeInternal(LSTBY);
}
public void setLstby(String value){
   setAttributeInternal(LSTBY, value);
}
public void setLstby_noVal(String value){
   setAttributeInternal(LSTBY, value);
}
public String getSimsSamsnd(){
   return (String)getAttributeInternal(SIMSSAMSND);
}
public void setSimsSamsnd(String value){
   String oldValue = getSimsSamsnd();
   try{
       setAttributeInternal(SIMSSAMSND, value);
       getAM().getBlk3TsiMstVOFieldEvents().SimsSamsnd_cc();
   } catch(JboException e){
       setAttributeInternal(SIMSSAMSND,oldValue);
       throw(e);
   }
}
public void setSimsSamsnd_noVal(String value){
   setAttributeInternal(SIMSSAMSND, value);
}
public String getSimsPrt(){
   return (String)getAttributeInternal(SIMSPRT);
}
public void setSimsPrt(String value){
   setAttributeInternal(SIMSPRT, value);
}
public void setSimsPrt_noVal(String value){
   setAttributeInternal(SIMSPRT, value);
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
public String getSimsTyp(){
   return (String)getAttributeInternal(SIMSTYP);
}
public void setSimsTyp(String value){
   setAttributeInternal(SIMSTYP, value);
}
public void setSimsTyp_noVal(String value){
   setAttributeInternal(SIMSTYP, value);
}
public String getCrtby(){
   return (String)getAttributeInternal(CRTBY);
}
public void setCrtby(String value){
   setAttributeInternal(CRTBY, value);
}
public void setCrtby_noVal(String value){
   setAttributeInternal(CRTBY, value);
}
public Date getCrtdat(){
   return (Date)getAttributeInternal(CRTDAT);
}
public void setCrtdat(Date value){
   setAttributeInternal(CRTDAT, value);
}
public void setCrtdat_noVal(Date value){
   setAttributeInternal(CRTDAT, value);
}
public void setCrtdat_noVal(String value){
   Date value1 = String2Date(value);
   setAttributeInternal(CRTDAT, value1);
}
public String getSimsAdr2(){
   return (String)getAttributeInternal(SIMSADR2);
}
public void setSimsAdr2(String value){
   setAttributeInternal(SIMSADR2, value);
}
public void setSimsAdr2_noVal(String value){
   setAttributeInternal(SIMSADR2, value);
}
public String getSimsAdr3(){
   return (String)getAttributeInternal(SIMSADR3);
}
public void setSimsAdr3(String value){
   setAttributeInternal(SIMSADR3, value);
}
public void setSimsAdr3_noVal(String value){
   setAttributeInternal(SIMSADR3, value);
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
public String getSictDes(){
   return (String)getAttributeInternal(SICTDES);
}
public void setSictDes(String value){
   setAttributeInternal(SICTDES, value);
}
public void setSictDes_noVal(String value){
   setAttributeInternal(SICTDES, value);
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
public String getBlk3LovCuscod(){
   return (String)getAttributeInternal(BLK3LOVCUSCOD);
}
public void setBlk3LovCuscod(String value){
   setAttributeInternal(BLK3LOVCUSCOD, value);
}
public void setBlk3LovCuscod_noVal(String value){
   setAttributeInternal(BLK3LOVCUSCOD, value);
}
public String getSimsChrtyp(){
   return (String)getAttributeInternal(SIMSCHRTYP);
}
public void setSimsChrtyp(String value){
   setAttributeInternal(SIMSCHRTYP, value);
}
public void setSimsChrtyp_noVal(String value){
   setAttributeInternal(SIMSCHRTYP, value);
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
public String getLovChrtyp(){
   return (String)getAttributeInternal(LOVCHRTYP);
}
public void setLovChrtyp(String value){
   setAttributeInternal(LOVCHRTYP, value);
}
public void setLovChrtyp_noVal(String value){
   setAttributeInternal(LOVCHRTYP, value);
}
public String getCpcpConpernam(){
   return (String)getAttributeInternal(CPCPCONPERNAM);
}
public void setCpcpConpernam(String value){
   setAttributeInternal(CPCPCONPERNAM, value);
}
public void setCpcpConpernam_noVal(String value){
   setAttributeInternal(CPCPCONPERNAM, value);
}
public String getSimsSernum(){
   return (String)getAttributeInternal(SIMSSERNUM);
}
public void setSimsSernum(String value){
   setAttributeInternal(SIMSSERNUM, value);
}
public void setSimsSernum_noVal(String value){
   setAttributeInternal(SIMSSERNUM, value);
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
public String getSimsTrmcur(){
   return (String)getAttributeInternal(SIMSTRMCUR);
}
public void setSimsTrmcur(String value){
   setAttributeInternal(SIMSTRMCUR, value);
}
public void setSimsTrmcur_noVal(String value){
   setAttributeInternal(SIMSTRMCUR, value);
}
public String getSimsPortL(){
   return (String)getAttributeInternal(SIMSPORTL);
}
public void setSimsPortL(String value){
   setAttributeInternal(SIMSPORTL, value);
}
public void setSimsPortL_noVal(String value){
   setAttributeInternal(SIMSPORTL, value);
}
public String getBlk3LovPortL(){
   return (String)getAttributeInternal(BLK3LOVPORTL);
}
public void setBlk3LovPortL(String value){
   setAttributeInternal(BLK3LOVPORTL, value);
}
public void setBlk3LovPortL_noVal(String value){
   setAttributeInternal(BLK3LOVPORTL, value);
}
public String getCpalNam(){
   return (String)getAttributeInternal(CPALNAM);
}
public void setCpalNam(String value){
   setAttributeInternal(CPALNAM, value);
}
public void setCpalNam_noVal(String value){
   setAttributeInternal(CPALNAM, value);
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
public String getSimsConpercod(){
   return (String)getAttributeInternal(SIMSCONPERCOD);
}
public void setSimsConpercod(String value){
   setAttributeInternal(SIMSCONPERCOD, value);
}
public void setSimsConpercod_noVal(String value){
   setAttributeInternal(SIMSCONPERCOD, value);
}
public String getBlk3LovConpercod(){
   return (String)getAttributeInternal(BLK3LOVCONPERCOD);
}
public void setBlk3LovConpercod(String value){
   setAttributeInternal(BLK3LOVCONPERCOD, value);
}
public void setBlk3LovConpercod_noVal(String value){
   setAttributeInternal(BLK3LOVCONPERCOD, value);
}
public Date getSimsEtadat(){
   return (Date)getAttributeInternal(SIMSETADAT);
}
public void setSimsEtadat(Date value){
   setAttributeInternal(SIMSETADAT, value);
}
public void setSimsEtadat_noVal(Date value){
   setAttributeInternal(SIMSETADAT, value);
}
public void setSimsEtadat_noVal(String value){
   Date value1 = String2Date(value);
   setAttributeInternal(SIMSETADAT, value1);
}
public String getSimsNam(){
   return (String)getAttributeInternal(SIMSNAM);
}
public void setSimsNam(String value){
   setAttributeInternal(SIMSNAM, value);
}
public void setSimsNam_noVal(String value){
   setAttributeInternal(SIMSNAM, value);
}
public String getSimsFindst(){
   return (String)getAttributeInternal(SIMSFINDST);
}
public void setSimsFindst(String value){
   setAttributeInternal(SIMSFINDST, value);
}
public void setSimsFindst_noVal(String value){
   setAttributeInternal(SIMSFINDST, value);
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
public Date getSimsEtddat(){
   return (Date)getAttributeInternal(SIMSETDDAT);
}
public void setSimsEtddat(Date value){
   setAttributeInternal(SIMSETDDAT, value);
}
public void setSimsEtddat_noVal(Date value){
   setAttributeInternal(SIMSETDDAT, value);
}
public void setSimsEtddat_noVal(String value){
   Date value1 = String2Date(value);
   setAttributeInternal(SIMSETDDAT, value1);
}
public String getBlk3LovVia(){
   return (String)getAttributeInternal(BLK3LOVVIA);
}
public void setBlk3LovVia(String value){
   setAttributeInternal(BLK3LOVVIA, value);
}
public void setBlk3LovVia_noVal(String value){
   setAttributeInternal(BLK3LOVVIA, value);
}
public String getSimsVia(){
   return (String)getAttributeInternal(SIMSVIA);
}
public void setSimsVia(String value){
   setAttributeInternal(SIMSVIA, value);
}
public void setSimsVia_noVal(String value){
   setAttributeInternal(SIMSVIA, value);
}
public String getMpcpNamL(){
   return (String)getAttributeInternal(MPCPNAML);
}
public void setMpcpNamL(String value){
   setAttributeInternal(MPCPNAML, value);
}
public void setMpcpNamL_noVal(String value){
   setAttributeInternal(MPCPNAML, value);
}
public String getBlk3LovPortD(){
   return (String)getAttributeInternal(BLK3LOVPORTD);
}
public void setBlk3LovPortD(String value){
   setAttributeInternal(BLK3LOVPORTD, value);
}
public void setBlk3LovPortD_noVal(String value){
   setAttributeInternal(BLK3LOVPORTD, value);
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
public String getMpcpNamVia(){
   return (String)getAttributeInternal(MPCPNAMVIA);
}
public void setMpcpNamVia(String value){
   setAttributeInternal(MPCPNAMVIA, value);
}
public void setMpcpNamVia_noVal(String value){
   setAttributeInternal(MPCPNAMVIA, value);
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
public String getBlk3LovCurcod(){
   return (String)getAttributeInternal(BLK3LOVCURCOD);
}
public void setBlk3LovCurcod(String value){
   setAttributeInternal(BLK3LOVCURCOD, value);
}
public void setBlk3LovCurcod_noVal(String value){
   setAttributeInternal(BLK3LOVCURCOD, value);
}
public String getSimsAdrcod(){
   return (String)getAttributeInternal(SIMSADRCOD);
}
public void setSimsAdrcod(String value){
   setAttributeInternal(SIMSADRCOD, value);
}
public void setSimsAdrcod_noVal(String value){
   setAttributeInternal(SIMSADRCOD, value);
}
public String getSimsAttn(){
   return (String)getAttributeInternal(SIMSATTN);
}
public void setSimsAttn(String value){
   setAttributeInternal(SIMSATTN, value);
}
public void setSimsAttn_noVal(String value){
   setAttributeInternal(SIMSATTN, value);
}
public String getSimsDrfnum(){
   return (String)getAttributeInternal(SIMSDRFNUM);
}
public void setSimsDrfnum(String value){
   setAttributeInternal(SIMSDRFNUM, value);
}
public void setSimsDrfnum_noVal(String value){
   setAttributeInternal(SIMSDRFNUM, value);
}
public String getSimsAwbref(){
   return (String)getAttributeInternal(SIMSAWBREF);
}
public void setSimsAwbref(String value){
   setAttributeInternal(SIMSAWBREF, value);
}
public void setSimsAwbref_noVal(String value){
   setAttributeInternal(SIMSAWBREF, value);
}
public String getSimsCurcod(){
   return (String)getAttributeInternal(SIMSCURCOD);
}
public void setSimsCurcod(String value){
   String oldValue = getSimsCurcod();
   try{
       setAttributeInternal(SIMSCURCOD, value);
       getAM().getBlk3TsiMstVOFieldEvents().SimsCurcod_vi();
   } catch(JboException e){
       setAttributeInternal(SIMSCURCOD,oldValue);
       throw(e);
   }
}
public void setSimsCurcod_noVal(String value){
   setAttributeInternal(SIMSCURCOD, value);
}
public BigDecimal getSimsInvamt(){
   return (BigDecimal)getAttributeInternal(SIMSINVAMT);
}
public void setSimsInvamt(BigDecimal value){
   BigDecimal oldValue = getSimsInvamt();
   try{
       setAttributeInternal(SIMSINVAMT, value);
       getAM().getBlk3TsiMstVOFieldEvents().SimsInvamt_vi();
   } catch(JboException e){
       setAttributeInternal(SIMSINVAMT,oldValue);
       throw(e);
   }
}
public void setSimsInvamt_noVal(BigDecimal value){
   setAttributeInternal(SIMSINVAMT, value);
}
public void setSimsInvamt_noVal(String value){
   BigDecimal value1 = String2BigDecimal(value);
   setAttributeInternal(SIMSINVAMT, value1);
}
public String getSimsTotamt(){
   return (String)getAttributeInternal(SIMSTOTAMT);
}
public void setSimsTotamt(String value){
   setAttributeInternal(SIMSTOTAMT, value);
}
public void setSimsTotamt_noVal(String value){
   setAttributeInternal(SIMSTOTAMT, value);
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
public String getBlk3LovSimsShpmodcod(){
   return (String)getAttributeInternal(BLK3LOVSIMSSHPMODCOD);
}
public void setBlk3LovSimsShpmodcod(String value){
   setAttributeInternal(BLK3LOVSIMSSHPMODCOD, value);
}
public void setBlk3LovSimsShpmodcod_noVal(String value){
   setAttributeInternal(BLK3LOVSIMSSHPMODCOD, value);
}
public String getLstchg(){
   return (String)getAttributeInternal(LSTCHG);
}
public void setLstchg(String value){
   setAttributeInternal(LSTCHG, value);
}
public void setLstchg_noVal(String value){
   setAttributeInternal(LSTCHG, value);
}
public String getMpcaCarnam(){
   return (String)getAttributeInternal(MPCACARNAM);
}
public void setMpcaCarnam(String value){
   setAttributeInternal(MPCACARNAM, value);
}
public void setMpcaCarnam_noVal(String value){
   setAttributeInternal(MPCACARNAM, value);
}
public String getBlk3LovCarcod(){
   return (String)getAttributeInternal(BLK3LOVCARCOD);
}
public void setBlk3LovCarcod(String value){
   setAttributeInternal(BLK3LOVCARCOD, value);
}
public void setBlk3LovCarcod_noVal(String value){
   setAttributeInternal(BLK3LOVCARCOD, value);
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
public String getSimsVesnam(){
   return (String)getAttributeInternal(SIMSVESNAM);
}
public void setSimsVesnam(String value){
   setAttributeInternal(SIMSVESNAM, value);
}
public void setSimsVesnam_noVal(String value){
   setAttributeInternal(SIMSVESNAM, value);
}
public String getSimsAdr4(){
   return (String)getAttributeInternal(SIMSADR4);
}
public void setSimsAdr4(String value){
   setAttributeInternal(SIMSADR4, value);
}
public void setSimsAdr4_noVal(String value){
   setAttributeInternal(SIMSADR4, value);
}
public String getSimsCarcod(){
   return (String)getAttributeInternal(SIMSCARCOD);
}
public void setSimsCarcod(String value){
   setAttributeInternal(SIMSCARCOD, value);
}
public void setSimsCarcod_noVal(String value){
   setAttributeInternal(SIMSCARCOD, value);
}
public String getSimsAdr1(){
   return (String)getAttributeInternal(SIMSADR1);
}
public void setSimsAdr1(String value){
   setAttributeInternal(SIMSADR1, value);
}
public void setSimsAdr1_noVal(String value){
   setAttributeInternal(SIMSADR1, value);
}
public String getBlk3LovAdrcod(){
   return (String)getAttributeInternal(BLK3LOVADRCOD);
}
public void setBlk3LovAdrcod(String value){
   setAttributeInternal(BLK3LOVADRCOD, value);
}
public void setBlk3LovAdrcod_noVal(String value){
   setAttributeInternal(BLK3LOVADRCOD, value);
}
}

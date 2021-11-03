package model.views;
public class Blk2CriteriaVORowImpl {
public String getSimsPortD(){
   return (String)getAttributeInternal(SIMSPORTD);
}
public void setSimsPortD(String value){
   setAttributeInternal(SIMSPORTD, value);
}
public void setSimsPortD_noVal(String value){
   setAttributeInternal(SIMSPORTD, value);
}
public String getExcel(){
   return (String)getAttributeInternal(EXCEL);
}
public void setExcel(String value){
   setAttributeInternal(EXCEL, value);
}
public void setExcel_noVal(String value){
   setAttributeInternal(EXCEL, value);
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
public String getSimsClsdat(){
   return (String)getAttributeInternal(SIMSCLSDAT);
}
public void setSimsClsdat(String value){
   setAttributeInternal(SIMSCLSDAT, value);
}
public void setSimsClsdat_noVal(String value){
   setAttributeInternal(SIMSCLSDAT, value);
}
public String getBlk2Ok(){
   return (String)getAttributeInternal(BLK2OK);
}
public void setBlk2Ok(String value){
   setAttributeInternal(BLK2OK, value);
}
public void setBlk2Ok_noVal(String value){
   setAttributeInternal(BLK2OK, value);
}
public String getBlk2Reset(){
   return (String)getAttributeInternal(BLK2RESET);
}
public void setBlk2Reset(String value){
   setAttributeInternal(BLK2RESET, value);
}
public void setBlk2Reset_noVal(String value){
   setAttributeInternal(BLK2RESET, value);
}
public String getBlk2Cancel(){
   return (String)getAttributeInternal(BLK2CANCEL);
}
public void setBlk2Cancel(String value){
   setAttributeInternal(BLK2CANCEL, value);
}
public void setBlk2Cancel_noVal(String value){
   setAttributeInternal(BLK2CANCEL, value);
}
public String getBlk2LovPortD(){
   return (String)getAttributeInternal(BLK2LOVPORTD);
}
public void setBlk2LovPortD(String value){
   setAttributeInternal(BLK2LOVPORTD, value);
}
public void setBlk2LovPortD_noVal(String value){
   setAttributeInternal(BLK2LOVPORTD, value);
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
public String getActive(){
   return (String)getAttributeInternal(ACTIVE);
}
public void setActive(String value){
   String oldValue = getActive();
   try{
       setAttributeInternal(ACTIVE, value);
       getAM().getBlk2CriteriaVOFieldEvents().Active_vi();
   } catch(JboException e){
       setAttributeInternal(ACTIVE,oldValue);
       throw(e);
   }
}
public void setActive_noVal(String value){
   setAttributeInternal(ACTIVE, value);
}
public Date getSelectShpDatFr(){
   return (Date)getAttributeInternal(SELECTSHPDATFR);
}
public void setSelectShpDatFr(Date value){
   Date oldValue = getSelectShpDatFr();
   try{
       setAttributeInternal(SELECTSHPDATFR, value);
       getAM().getBlk2CriteriaVOFieldEvents().SelectShpDatFr_vi();
   } catch(JboException e){
       setAttributeInternal(SELECTSHPDATFR,oldValue);
       throw(e);
   }
}
public void setSelectShpDatFr_noVal(Date value){
   setAttributeInternal(SELECTSHPDATFR, value);
}
public void setSelectShpDatFr_noVal(String value){
   Date value1 = String2Date(value);
   setAttributeInternal(SELECTSHPDATFR, value1);
}
public Date getSelectShpDatTo(){
   return (Date)getAttributeInternal(SELECTSHPDATTO);
}
public void setSelectShpDatTo(Date value){
   Date oldValue = getSelectShpDatTo();
   try{
       setAttributeInternal(SELECTSHPDATTO, value);
       getAM().getBlk2CriteriaVOFieldEvents().SelectShpDatTo_vi();
   } catch(JboException e){
       setAttributeInternal(SELECTSHPDATTO,oldValue);
       throw(e);
   }
}
public void setSelectShpDatTo_noVal(Date value){
   setAttributeInternal(SELECTSHPDATTO, value);
}
public void setSelectShpDatTo_noVal(String value){
   Date value1 = String2Date(value);
   setAttributeInternal(SELECTSHPDATTO, value1);
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
public Date getSelectInvDatFr(){
   return (Date)getAttributeInternal(SELECTINVDATFR);
}
public void setSelectInvDatFr(Date value){
   Date oldValue = getSelectInvDatFr();
   try{
       setAttributeInternal(SELECTINVDATFR, value);
       getAM().getBlk2CriteriaVOFieldEvents().SelectInvDatFr_vi();
   } catch(JboException e){
       setAttributeInternal(SELECTINVDATFR,oldValue);
       throw(e);
   }
}
public void setSelectInvDatFr_noVal(Date value){
   setAttributeInternal(SELECTINVDATFR, value);
}
public void setSelectInvDatFr_noVal(String value){
   Date value1 = String2Date(value);
   setAttributeInternal(SELECTINVDATFR, value1);
}
public String getBlk2LovSimsDptcod(){
   return (String)getAttributeInternal(BLK2LOVSIMSDPTCOD);
}
public void setBlk2LovSimsDptcod(String value){
   setAttributeInternal(BLK2LOVSIMSDPTCOD, value);
}
public void setBlk2LovSimsDptcod_noVal(String value){
   setAttributeInternal(BLK2LOVSIMSDPTCOD, value);
}
public Date getSelectInvDatTo(){
   return (Date)getAttributeInternal(SELECTINVDATTO);
}
public void setSelectInvDatTo(Date value){
   Date oldValue = getSelectInvDatTo();
   try{
       setAttributeInternal(SELECTINVDATTO, value);
       getAM().getBlk2CriteriaVOFieldEvents().SelectInvDatTo_vi();
   } catch(JboException e){
       setAttributeInternal(SELECTINVDATTO,oldValue);
       throw(e);
   }
}
public void setSelectInvDatTo_noVal(Date value){
   setAttributeInternal(SELECTINVDATTO, value);
}
public void setSelectInvDatTo_noVal(String value){
   Date value1 = String2Date(value);
   setAttributeInternal(SELECTINVDATTO, value1);
}
public String getBlk2LovSimsInvno(){
   return (String)getAttributeInternal(BLK2LOVSIMSINVNO);
}
public void setBlk2LovSimsInvno(String value){
   setAttributeInternal(BLK2LOVSIMSINVNO, value);
}
public void setBlk2LovSimsInvno_noVal(String value){
   setAttributeInternal(BLK2LOVSIMSINVNO, value);
}
public String getSelectSimsYear(){
   return (String)getAttributeInternal(SELECTSIMSYEAR);
}
public void setSelectSimsYear(String value){
   setAttributeInternal(SELECTSIMSYEAR, value);
}
public void setSelectSimsYear_noVal(String value){
   setAttributeInternal(SELECTSIMSYEAR, value);
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
public String getSimsDptcod(){
   return (String)getAttributeInternal(SIMSDPTCOD);
}
public void setSimsDptcod(String value){
   setAttributeInternal(SIMSDPTCOD, value);
}
public void setSimsDptcod_noVal(String value){
   setAttributeInternal(SIMSDPTCOD, value);
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
public String getBlk2LovSimsCuscod(){
   return (String)getAttributeInternal(BLK2LOVSIMSCUSCOD);
}
public void setBlk2LovSimsCuscod(String value){
   setAttributeInternal(BLK2LOVSIMSCUSCOD, value);
}
public void setBlk2LovSimsCuscod_noVal(String value){
   setAttributeInternal(BLK2LOVSIMSCUSCOD, value);
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
public String getBlk2LovSimsDivcod(){
   return (String)getAttributeInternal(BLK2LOVSIMSDIVCOD);
}
public void setBlk2LovSimsDivcod(String value){
   setAttributeInternal(BLK2LOVSIMSDIVCOD, value);
}
public void setBlk2LovSimsDivcod_noVal(String value){
   setAttributeInternal(BLK2LOVSIMSDIVCOD, value);
}
}

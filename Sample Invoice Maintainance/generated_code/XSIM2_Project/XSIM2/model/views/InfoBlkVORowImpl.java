package model.views;
public class InfoBlkVORowImpl {
public String getInfoDiv(){
   return (String)getAttributeInternal(INFODIV);
}
public void setInfoDiv(String value){
   setAttributeInternal(INFODIV, value);
}
public void setInfoDiv_noVal(String value){
   setAttributeInternal(INFODIV, value);
}
public String getInfoFormName(){
   return (String)getAttributeInternal(INFOFORMNAME);
}
public void setInfoFormName(String value){
   setAttributeInternal(INFOFORMNAME, value);
}
public void setInfoFormName_noVal(String value){
   setAttributeInternal(INFOFORMNAME, value);
}
public Date getInfoDate(){
   return (Date)getAttributeInternal(INFODATE);
}
public void setInfoDate(Date value){
   setAttributeInternal(INFODATE, value);
}
public void setInfoDate_noVal(Date value){
   setAttributeInternal(INFODATE, value);
}
public void setInfoDate_noVal(String value){
   Date value1 = String2Date(value);
   setAttributeInternal(INFODATE, value1);
}
public String getInfoUser(){
   return (String)getAttributeInternal(INFOUSER);
}
public void setInfoUser(String value){
   setAttributeInternal(INFOUSER, value);
}
public void setInfoUser_noVal(String value){
   setAttributeInternal(INFOUSER, value);
}
public String getInfoFormId(){
   return (String)getAttributeInternal(INFOFORMID);
}
public void setInfoFormId(String value){
   setAttributeInternal(INFOFORMID, value);
}
public void setInfoFormId_noVal(String value){
   setAttributeInternal(INFOFORMID, value);
}
}

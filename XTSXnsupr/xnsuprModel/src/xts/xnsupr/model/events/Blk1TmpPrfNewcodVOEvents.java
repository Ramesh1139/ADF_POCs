package xts.xnsupr.model.events; 
import xts.formadf.model.exceptions.FormTriggerFailure;
import xts.formadf.model.utils.Ops;

import xts.xnsupr.model.helpers.XnsuprAmImplHelpers;
import xts.xnsupr.model.services.xnsuprAMImpl;
import xts.xnsupr.model.views.Blk1TmpPrfNewcodVORowImpl;

public class Blk1TmpPrfNewcodVOEvents {
    public Blk1TmpPrfNewcodVOEvents(xnsuprAMImpl am) {
        super();
        this.am = am;
    }
    xnsuprAMImpl am;
    public xnsuprAMImpl getAm() {
        return am;
    }
    public void setAm(xnsuprAMImpl am) {
        this.am = am;
    }

public void poq(Blk1TmpPrfNewcodVORowImpl voRow){
    
    // voRow == am.getBlk1TmpPrfNewcodVORow()
    if(voRow !=null)
    {
    
try {
if (Ops.eq(voRow.getMppfcStscod(),"DE"))
{ voRow.setMppfcStscodDesc_noVal("Deleted");
  
}
else
{
if(Ops.eq( voRow.getMppfcStscod(),"RJ"))
{voRow.setMppfcStscodDesc_noVal("Rejected");
}
else
{
if(Ops.eq( voRow.getMppfcStscod(),"CF"))
{ voRow.setMppfcStscodDesc_noVal("Confirmed");
}
else
{
if(Ops.eq(voRow.getMppfcStscod(),"PA"))
{
    voRow.setMppfcStscodDesc_noVal("Pending");
}
else
{ voRow.setMppfcStscodDesc_noVal(Ops.concat(Ops.concat("Err(", voRow.getMppfcStscod()),")"));
}
}
}
}
if (Ops.eq(voRow.getMppfcGrp(),"C"))
{voRow.setMppfcGrpDesc_noVal("Colby");
}
else
{voRow.setMppfcGrpDesc_noVal("Lifung");
}
    
    
    if (Ops.or(Ops.ne( voRow.getMppfcStscod(),"PA"),Ops.isNull( voRow.getMppfcRunnum())))
    {
       am.lib().setItemProperty("blk1_control.blk1_insert", am.lib().enabled, am.lib().propertyFalse);
       voRow.setdisableResubmit(true);
       System.out.println("if loop disable Value :"+voRow.getdisableResubmit());
       
      
    }
    else
    {
       am.lib().setItemProperty("blk1_control.blk1_insert", am.lib().enabled, am.lib().propertyTrue);
       voRow.setdisableResubmit(false);
       System.out.println("else loop disable Value :"+voRow.getdisableResubmit());
    }
    
}
catch (FormTriggerFailure ex)
{throw am.lib().getFormTriggerFailure();
}
catch (Exception ex)
{   
    ex.printStackTrace();
    am.helpers().plShowUserMessage(Ops.concat("Fatal error! Please contact XTS support. Error message: ", am.lib().sqlerrm),"WARN");
   //throw am.lib().getFormTriggerFailure();
    return;
}

    }

}
public void kd(){
 am.lib().nextRecord();
 am.lib().executeTrigger("when-mouse-click");
}
public void ku(){
 am.lib().previousRecord();
 am.lib().executeTrigger("when-mouse-click");
}
public void mc(Blk1TmpPrfNewcodVORowImpl row)
{
    if(row!=null)
    {
/*try {
if (Ops.eq( am.lib().upper( am.getParamVORow().getPMode()),"EDIT"))
{
if (Ops.or(Ops.or(Ops.isNull( am.getBlk1TmpPrfNewcodVORow().getMppfcRunnum()),Ops.ne( am.getBlk1TmpPrfNewcodVORow().getMppfcStscod(),"PA")),Ops.ne( am.getBlk1TmpPrfNewcodVORow().getMppfcSbmby(), am.getGlobalVORow().getGUserid())))
{ am.lib().setItemProperty("blk1_control.blk1_delete", am.lib().enabled, am.lib().propertyFalse);
}
else
{ am.lib().setItemProperty("blk1_control.blk1_delete", am.lib().enabled, am.lib().propertyTrue);
}
if (Ops.and(Ops.eq( am.getGlobalVORow().getGAutgrp(),"EDP"),Ops.eq( am.getBlk1TmpPrfNewcodVORow().getMppfcStscod(),"PA")))
{ am.lib().setItemProperty("blk1_control.blk1_delete", am.lib().enabled, am.lib().propertyTrue);
}
}
else
{
    am.lib().setItemProperty("blk1_control.blk1_delete", am.lib().enabled, am.lib().propertyFalse);
if (Ops.or(Ops.ne( am.getBlk1TmpPrfNewcodVORow().getMppfcStscod(),"PA"),Ops.isNull( am.getBlk1TmpPrfNewcodVORow().getMppfcRunnum())))
{ 
    am.lib().setItemProperty("blk1_control.blk1_insert", am.lib().enabled, am.lib().propertyFalse);
    row.setdisableResubmit(false);
   
}
else
{ 
    am.lib().setItemProperty("blk1_control.blk1_insert", am.lib().enabled, am.lib().propertyTrue);
    row.setdisableResubmit(false);
}
}
 am.helpers().plBlk1ControlSet();
} */


   

/*
catch (FormTriggerFailure ex)
{
    throw am.lib().getFormTriggerFailure();
}
catch (Exception ex)
{ //am.helpers().plShowUserMessage(Ops.concat("Fatal error! Please contact XTS support. Error message: ", am.lib().sqlerrm),"WARN");
//throw am.lib().getFormTriggerFailure();  // Loading page time getting error
}
*/
    
    
}
}
public void prq()
{
String  v_default_where =  null;
try {
    //am.lib().upper( am.getParamVORow().getPProfile())   == 'S';
 
  am.lib().setBlockProperty("blk1_tmp_prf_newcod", am.lib().defaultWhere,Ops.concat(Ops.concat("mppfc_typ = '", "S"),"'"));
  v_default_where =  am.lib().getBlockProperty("blk1_tmp_prf_newcod", am.lib().defaultWhere);

  /*  am.lib().setBlockProperty("blk1_tmp_prf_newcod", am.lib().defaultWhere,Ops.concat(Ops.concat("mppfc_typ = '", am.lib().upper( am.getParamVORow().getPProfile())),"'"));
    v_default_where =  am.lib().getBlockProperty("blk1_tmp_prf_newcod", am.lib().defaultWhere); */
    
    if(am.getBlk2CriteriaVORow() != null)
    {
    
if (!Ops.isNull( am.getBlk2CriteriaVORow().getMppfcId()))
{ 
    if (!Ops.isNull(v_default_where)) {
        v_default_where = Ops.concat(v_default_where, " and ");
    }
   
    v_default_where = Ops.concat(Ops.concat(Ops.concat( v_default_where,"  MPPFC_IDPFX || LPAD(MPPFC_RUNNUM, 8, '0') = '"), am.getBlk2CriteriaVORow().getMppfcId()),"' ");
}
if (!Ops.isNull( am.getBlk2CriteriaVORow().getMppfcFulnam()))
{ 
    if (!Ops.isNull(v_default_where)) {
        v_default_where = Ops.concat(v_default_where, " and ");
    }
    
    v_default_where = Ops.concat(Ops.concat(Ops.concat( v_default_where," mppfc_fulnam LIKE '%"), am.getBlk2CriteriaVORow().getMppfcFulnam()),"%' ");
}
if (Ops.ne( am.getBlk2CriteriaVORow().getMppfcStscod(),"AL"))
{    if (!Ops.isNull(v_default_where)) {
        v_default_where = Ops.concat(v_default_where, " and ");
    }
    v_default_where = Ops.concat(Ops.concat(Ops.concat( v_default_where," mppfc_stscod = '"), am.getBlk2CriteriaVORow().getMppfcStscod()),"' ");
}

      /*  if (Ops.ne(am.getBlk2CriteriaVORow().getMppfcStscod(), "AL")) {
            v_default_where =
                Ops.concat(Ops.concat(Ops.concat(v_default_where, " AND mppfc_stscod = '"),
                                      am.getBlk2CriteriaVORow().getMppfcStscod()), "' ");
        }*/
if (!Ops.isNull( am.getBlk2CriteriaVORow().getMppfcSbmby()))
{
    if (!Ops.isNull(v_default_where)) {
        v_default_where = Ops.concat(v_default_where, " and ");
    }
    
    v_default_where = Ops.concat(Ops.concat(Ops.concat( v_default_where," mppfc_sbmby = '"), am.getBlk2CriteriaVORow().getMppfcSbmby()),"' ");
}
if (!Ops.isNull( am.getBlk2CriteriaVORow().getMppfcApvby()))
{
    if (!Ops.isNull(v_default_where)) {
        v_default_where = Ops.concat(v_default_where, " and ");
    }
    
    v_default_where = Ops.concat(Ops.concat(Ops.concat( v_default_where," mppfc_apvby = '"), am.getBlk2CriteriaVORow().getMppfcApvby()),"' ");
}


        
            if (!Ops.isNull( am.getBlk2CriteriaVORow().getSubmitedDateFrom()))
            {
                if (!Ops.isNull(v_default_where)) {
                    v_default_where = Ops.concat(v_default_where, " and ");
                }
            v_default_where =
                Ops.concat(Ops.concat(Ops.concat(v_default_where, "(trunc(MPPFC_SBMDAT) >= to_date('"),
                                      am.lib().toChar(am.getBlk2CriteriaVORow().getSubmitedDateFrom())),
                           "', 'YYYY-MM-DD'))"); // DD-MON-RR YYYY-MM-DD
        }
        
        if (!Ops.isNull( am.getBlk2CriteriaVORow().getSubmitedDateTo()))
        {
            if (!Ops.isNull(v_default_where)) {
                v_default_where = Ops.concat(v_default_where, " and ");
            }
        v_default_where =
            Ops.concat(Ops.concat(Ops.concat(v_default_where, "(trunc(MPPFC_SBMDAT) <=  to_date('"),
                                  am.lib().toChar(am.getBlk2CriteriaVORow().getSubmitedDateTo())),
                       "', 'YYYY-MM-DD'))"); // DD-MON-RR YYYY-MM-DD
        }
        
        
        
        if (!Ops.isNull( am.getBlk2CriteriaVORow().getConfirmDateFrom()))
        {
            if (!Ops.isNull(v_default_where)) {
                v_default_where = Ops.concat(v_default_where, " and ");
            }
        v_default_where =
            Ops.concat(Ops.concat(Ops.concat(v_default_where, "(trunc(MPPFC_APVDAT) >= to_date('"),
                                  am.lib().toChar(am.getBlk2CriteriaVORow().getConfirmDateFrom())),
                       "', 'YYYY-MM-DD'))"); // DD-MON-RR YYYY-MM-DD
        }
        
        if (!Ops.isNull( am.getBlk2CriteriaVORow().getConfirmDateTo()))
        {
        if (!Ops.isNull(v_default_where)) {
            v_default_where = Ops.concat(v_default_where, " and ");
        }
        v_default_where =
        Ops.concat(Ops.concat(Ops.concat(v_default_where, "(trunc(MPPFC_APVDAT) <=  to_date('"),
                              am.lib().toChar(am.getBlk2CriteriaVORow().getConfirmDateTo())),
                   "', 'YYYY-MM-DD'))"); // DD-MON-RR YYYY-MM-DD
        }
        
        
        if (!Ops.isNull( am.getBlk2CriteriaVORow().getUpdatedDateFrom()))
        {
            if (!Ops.isNull(v_default_where)) {
                v_default_where = Ops.concat(v_default_where, " and ");
            }
        v_default_where =
            Ops.concat(Ops.concat(Ops.concat(v_default_where, "(trunc(UPDDAT) >= to_date('"),
                                  am.lib().toChar(am.getBlk2CriteriaVORow().getUpdatedDateFrom())),
                       "', 'YYYY-MM-DD') or UPDDAT is null)"); // DD-MON-RR YYYY-MM-DD
        }
        
        if (!Ops.isNull( am.getBlk2CriteriaVORow().getUpdatedDateTo()))
        {
        if (!Ops.isNull(v_default_where)) {
            v_default_where = Ops.concat(v_default_where, " and ");
        }
        v_default_where =
        Ops.concat(Ops.concat(Ops.concat(v_default_where, "(trunc(UPDDAT) <=  to_date('"),
                              am.lib().toChar(am.getBlk2CriteriaVORow().getUpdatedDateTo())),
                   "', 'YYYY-MM-DD') or UPDDAT is null)"); // DD-MON-RR YYYY-MM-DD
        }
        
     
    }
    
    
 am.lib().setBlockProperty("blk1_tmp_prf_newcod", am.lib().defaultWhere, v_default_where);
    
}
catch (FormTriggerFailure ex)
{throw am.lib().getFormTriggerFailure();
}
catch (Exception ex)
{ 
    ex.printStackTrace();
    am.helpers().plShowUserMessage(Ops.concat("Fatal error! Please contact XTS support. Error message: ", am.lib().sqlerrm),"WARN");
   //throw am.lib().getFormTriggerFailure();
    return;
}
}


  
public void prb(){
if (Ops.eq( am.lib().nvl( am.lib().getApplicationProperty( am.lib().callingForm),"XTS"),"XTS"))
{ am.lib().setItemProperty("info_blk.info_div", am.lib().enabled, am.lib().propertyTrue);
}
else
{ am.lib().setItemProperty("info_blk.info_div", am.lib().enabled, am.lib().propertyFalse);
}
 am.lib().showView("info_can");
}

}

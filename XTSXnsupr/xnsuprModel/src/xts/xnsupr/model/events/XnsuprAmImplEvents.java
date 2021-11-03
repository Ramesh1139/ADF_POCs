package xts.xnsupr.model.events; 

import java.math.BigDecimal;

import xts.formadf.model.dbwrappers.DP_UTL;
import xts.formadf.model.utils.Ops;

import xts.xnsupr.model.helpers.XnsuprAmImplHelpers;
import xts.xnsupr.model.services.xnsuprAMImpl;

public class XnsuprAmImplEvents {
    public XnsuprAmImplEvents(xnsuprAMImpl am) {
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
public void kni(){
 am.lib().nextField();
}
public void oe(){
BigDecimal  v_errcod =  am.lib().errorCode;
String  v_errtyp =  am.lib().errorType;
String  v_errtxt =  am.lib().errorText;
BigDecimal  v_db_errcod =  am.lib().dbmsErrorCode;
String  v_db_errstr =  am.lib().dbmsErrorText;
BigDecimal  v_status =  new BigDecimal(0);
String  v_msgstr =  null;
 am.getWorkItemVORow().setWErrmsg_noVal("");
if (Ops.eq( v_errcod,new BigDecimal(40735)))
{
if (Ops.eq( v_db_errcod,new BigDecimal(54)))
{ v_msgstr =  am.lib().fstdUsrMsg("673");
 am.helpers().plShowUserMessage( v_msgstr,"Record Lock","CAUTION");
throw am.lib().getFormTriggerFailure();
}
else
{ am.lib().message(Ops.concat(Ops.concat(Ops.concat(Ops.concat( v_errtyp,"-"), am.lib().toChar( v_errcod)),": "), v_errtxt));
throw am.lib().getFormTriggerFailure();
}
}
else
{
if(Ops.eq( v_errcod,new BigDecimal(41008)))
{;
}
else
{
if(Ops.eq( v_errcod,new BigDecimal(41026)))
{;
}
else
{ am.lib().message(Ops.concat(Ops.concat(Ops.concat(Ops.concat( v_errtyp,"-"), am.lib().toChar( v_errcod)),": "), v_errtxt));
throw am.lib().getFormTriggerFailure();
}
}
}
}
public void pdc(){
 am.getWorkItemVORow().setWChg_noVal("N");
}
public void pfc(){
 am.getWorkItemVORow().setWChg_noVal("Y");
}
public void cd(){
 am.helpers().clearAllMasterDetails();
}
public void nfi()
{
    
 am.lib().pstdMaxWindows("WIN_MAIN");
 am.lib().pstdSetGlobal("SP");
 am.getGlobalVORow().setGModule_noVal("SP");
 am.lib().pstdSecurity();
if (Ops.eq(am.getParamVORow().getPMode(),"EDIT"))
{
    am.lib().setItemProperty("blk1_control.blk1_insert", am.lib().enabled, am.lib().propertyTrue);
}
     am.helpers().plChkSite();
     System.out.println("Param Mode PMode :"+am.getParamVORow().getPMode());
     System.out.println("UserID :"+ am.getGlobalVORow().getGUserid());
     System.out.println("DivCod" +am.getGlobalVORow().getGDivcod());
    
if (Ops.and(Ops.eq( am.getParamVORow().getPMode(),"APPROVE"),Ops.eq( am.lib().upper(DP_UTL.CHK_AP_SETTING(am.getDBTransaction(), 
                                                                                                           am.getGlobalVORow().getGUserid(),
                                                                                                           am.getGlobalVORow().getGDivcod(),"*","*","009")),"N")))
{ 
   // am.helpers().plShowUserMessage( am.helpers().plGetUserMessage("1169"));
   System.out.println("Inside Approve block");
   am.helpers().showAlertInfo(am.helpers().plGetUserMessage("1169"));
   am.lib().exitForm( am.lib().noCommit);
}
 am.helpers().plBlk1ControlSet();
 am.helpers().plInitVariable();
 am.getWorkItemVORow().setWCallForm_noVal("N");
 am.lib().showView("info_can");
 am.lib().goBlock("blk2_criteria");
 am.lib().goItem("blk2_criteria.ok");
 am.lib().executeTrigger("when-button-pressed");
 //am.getBlk2CriteriaVOFieldEvents().Ok_bp();
 am.lib().goBlock("blk1_tmp_prf_newcod");
 am.lib().executeQuery();
 am.lib().executeTrigger("when-mouse-click");
 
 am.getBlk1TmpPrfNewcodVOEvents().mc(am.getBlk1TmpPrfNewcodVORow());
 
 //am.Blk2CriteriaVOEvents_prr();
 
 
}
public void cje(){
String  event_name =  am.getSystemVORow().getJavascriptEventName();
String  event_val =  am.getSystemVORow().getJavascriptEventValue();
 am.lib().plJsEventForAdf( event_name, event_val);
}
public void prf(){
 am.lib().plInitFormForAdf();
}
public void pof(){
 am.lib().plExitFormWithAdf();
}

}

package model;
public class Xsirpm2AmImplEvents {
    public Xsirpm2AmImplEvents(Xsirpm2AmImpl am) {
        super();
this.am = am;
    }
    Xsirpm2AmImpl am;
    public Xsirpm2AmImpl getAm() {
        return am;
    }
    public void setAm(Xsirpm2AmImpl am) {
        this.am = am;
    }
public void ex(){
 am.lib().doKey("EXIT_FORM");
}
public void fcl(){
 am.getGlobalVORow().setGlobalTriggerStatus_noVal(new BigDecimal(0));
 am.lib().exitForm( am.lib().noValidate);
}
public void nfi(){
try {
try {
 am.getWorkItemVORow().setFormsVersion_noVal( am.lib().winApiEnvironment.readRegistry("HKEY_LOCAL_MACHINE\\SOFTWARE\\ORACLE\\HOME1","FORMS_VERSION", true));
}
catch (Exception ex)
{ am.getWorkItemVORow().setFormsVersion_noVal("6i");
}
 am.lib().pstdMaxWindows("WIN_MAIN");
 am.lib().pstdSetGlobal();
 am.getGlobalVORow().setGModule_noVal("SI");
 am.lib().pstdSecurity();
 am.helpers().plUpdateWindowTitle();
 am.helpers().plInitVariable();
 am.helpers().plSetMenuMultiRec();
 am.getWorkItemVORow().setWUserInterface_noVal( am.lib().getApplicationProperty( am.lib().userInterface));
if (Ops.ne( am.getWorkItemVORow().getWUserInterface(),"MSWINDOWS32"))
{ am.lib().setItemProperty("control_main_block.prt_setup", am.lib().enabled, am.lib().propertyFalse);
}
if (Ops.eq( am.getWorkItemVORow().getWUserInterface(),"WEB"))
{ am.lib().goItem("main_block.PRINT_LASER");
 am.lib().setItemProperty("control_main_block.print", am.lib().enabled, am.lib().propertyFalse);
}
 am.lib().copy(new BigDecimal(0),"global.global_trigger_status");
String  w_prt =  null;
BigDecimal  status =  new BigDecimal(0);
try {
{
DP_MIG_XSIRPM2.PL_NFI1_results  result = DP_MIG_XSIRPM2.PL_NFI1(am.getDBTransaction(), am.getGlobalVORow().getGUserid(), am.getGlobalVORow().getGModule());
 w_prt = result.getO_W_PRT();
}
}
catch (Exception ex)
{ w_prt = "N";
}
if (Ops.eq( w_prt,"N"))
{ am.lib().setAlertProperty("AL_STD_WARN", am.lib().alertMessageText, am.lib().fstdUsrMsg("1169"));
 status =  am.lib().showAlert("AL_STD_WARN");
 am.lib().exitForm( am.lib().noCommit);
}
;
if (!Ops.isNull( am.lib().getApplicationProperty( am.lib().callingForm)))
{ am.getWorkItemVORow().setWCallForm_noVal("Y");
 am.lib().setItemProperty("INFO_BLK.INFO_DIV", am.lib().enabled, am.lib().propertyFalse);
}
else
{ am.getWorkItemVORow().setWCallForm_noVal("N");
}
 am.lib().goItem("main_block.PRINT_LASER");
 am.getGlobalVORow().setGlobalTriggerStatus_noVal(new BigDecimal(0));
}
catch (FormTriggerFailure ex)
{ am.lib().exitForm( am.lib().noCommit);
}
}

}

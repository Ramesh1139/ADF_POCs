package com.nordstrom.rpm.Backing;

import com.nordstrom.rpm.Impl.RuleParameterImpl;
import com.nordstrom.rpm.Service.RuleParameterService;

import javax.faces.event.ActionEvent;

import javax.faces.event.ValueChangeEvent;

import oracle.adf.share.ADFContext;
import oracle.adf.view.rich.component.rich.RichDialog;
import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.component.rich.data.RichColumn;
import oracle.adf.view.rich.component.rich.data.RichTable;

import oracle.adf.view.rich.component.rich.input.RichInputText;
import oracle.adf.view.rich.component.rich.nav.RichCommandButton;
import oracle.adf.view.rich.event.DialogEvent;
import oracle.adf.view.rich.event.QueryEvent;

import org.apache.myfaces.trinidad.event.SortEvent;

public class RuleParameterBean {
    private RichTable parameterTable;
    RuleParameterService ParameterService =
        (RuleParameterImpl)ADFContext.getCurrent().getViewScope().get("RuleParameterImpl");
    private RichPopup bind_cancelPopUp;
    private RichDialog bind_dialogBox;        
    private RichColumn bind_ATSWGreaterDogs;
    private RichInputText bind_ATWLGR8Dogs;
    private RichCommandButton bind_Cancel;
    private RichColumn bind_ATSWLAge;


    public RuleParameterBean() {
        super();
        ParameterService.fetchRuleParameter();
    }

    public void setParameterTable(RichTable parameterTable) {
        this.parameterTable = parameterTable;
    }

    public RichTable getParameterTable() {
        return parameterTable;
    }

    public void saveRuleParameter(ActionEvent actionEvent) {
        // Add event code here...        
        ParameterService.saveRuleParameter();
    }

    public void onParameterTableQuery(QueryEvent queryEvent) {
        ParameterService.onParameterTableQuery(queryEvent);
    }

    public void setParameterService(RuleParameterService ParameterService) {
        this.ParameterService = ParameterService;
    }

    public RuleParameterService getParameterService() {
        return ParameterService;
    }

    public void setBind_cancelPopUp(RichPopup bind_cancelPopUp) {
        this.bind_cancelPopUp = bind_cancelPopUp;
    }

    public RichPopup getBind_cancelPopUp() {
        return bind_cancelPopUp;
    }

    public void cancelChangesDialogListener(DialogEvent dialogEvent) {
        // Add event code here...
        ParameterService.cancelChangesDialogListener(dialogEvent);
    }

    public void setBind_dialogBox(RichDialog bind_dialogBox) {
        this.bind_dialogBox = bind_dialogBox;
    }

    public RichDialog getBind_dialogBox() {
        return bind_dialogBox;
    }

    public void ParameterTableSort(SortEvent sortEvent) {
        // Add event code here...
        ParameterService.ParameterTableSort(sortEvent);
    }
    
    public void wareHousecolorChange(ValueChangeEvent valueChangeEvent) {
        // Add event code here...
        ParameterService.wareHousecolorChange(valueChangeEvent);
    }

    public void setBind_ATSWGreaterDogs(RichColumn bind_ATSWGreaterDogs) {
        this.bind_ATSWGreaterDogs = bind_ATSWGreaterDogs;
    }

    public RichColumn getBind_ATSWGreaterDogs() {
        return bind_ATSWGreaterDogs;
    }

    public void ATWLGr8DogsColor(ValueChangeEvent valueChangeEvent) {
        // Add event code here...
        ParameterService.ATWLGr8DogsColor(valueChangeEvent);
    }

    public void setBind_ATWLGR8Dogs(RichInputText bind_ATWLGR8Dogs) {
        this.bind_ATWLGR8Dogs = bind_ATWLGR8Dogs;
    }

    public RichInputText getBind_ATWLGR8Dogs() {
        return bind_ATWLGR8Dogs;
    }

    public void setBind_Cancel(RichCommandButton bind_Cancel) {
        this.bind_Cancel = bind_Cancel;
    }

    public RichCommandButton getBind_Cancel() {
        return bind_Cancel;
    }

    public void StoreSOHAttributes(ValueChangeEvent valueChangeEvent) {
        // Add event code here...
        ParameterService.StoreSOHAttributes(valueChangeEvent);
    }

    public void TltSellThruDOGsAttributes(ValueChangeEvent valueChangeEvent) {
        // Add event code here...
        ParameterService.TltSellThruDOGsAttributes(valueChangeEvent);
    }

    public void ATSWSAgeAttributes(ValueChangeEvent valueChangeEvent) {
        // Add event code here...
        ParameterService.ATSWSAgeAttributes(valueChangeEvent);
    }

    public void ATSWLessDOGs(ValueChangeEvent valueChangeEvent) {
        // Add event code here...
        ParameterService.ATSWLessDOGs(valueChangeEvent);
    }

    public void ATSWPerformance(ValueChangeEvent valueChangeEvent) {
        // Add event code here...
        ParameterService.ATSWPerformance(valueChangeEvent);
    }

    public void TltSellThruPerformanceAttributes(ValueChangeEvent valueChangeEvent) {
        // Add event code here...
        ParameterService.TltSellThruPerformanceAttributes(valueChangeEvent);
    }

    public void OnOrderAgeAttributes(ValueChangeEvent valueChangeEvent) {
        // Add event code here...
        ParameterService.OnOrderAgeAttributes(valueChangeEvent);
    }

    public void WarehouseOnOrderAttributes(ValueChangeEvent valueChangeEvent) {
        // Add event code here...
        ParameterService.WarehouseOnOrderAttributes(valueChangeEvent);
    }

    public void StoreOnOrderAttributes(ValueChangeEvent valueChangeEvent) {
        // Add event code here...
        ParameterService.StoreOnOrderAttributes(valueChangeEvent);
    }

    public void SOHGreaterBrokenAttributes(ValueChangeEvent valueChangeEvent) {
        // Add event code here...
        ParameterService.SOHGreaterBrokenAttributes(valueChangeEvent);
    }

    public void SOHLessBrokenAttributes(ValueChangeEvent valueChangeEvent) {
        // Add event code here...
        ParameterService.SOHLessBrokenAttributes(valueChangeEvent);
    }

    public void OnOrderBrokenAttributes(ValueChangeEvent valueChangeEvent) {
        // Add event code here...
        ParameterService.OnOrderBrokenAttributes(valueChangeEvent);
    }

    public void BaseRetailAttributes(ValueChangeEvent valueChangeEvent) {
        // Add event code here...
        ParameterService.BaseRetailAttributes(valueChangeEvent);
    }

    public void CurrentRetailAttributes(ValueChangeEvent valueChangeEvent) {
        // Add event code here...
        ParameterService.CurrentRetailAttributes(valueChangeEvent);
    }

    public void ATSWLAgeAttributes(ValueChangeEvent valueChangeEvent) {
        // Add event code here...
        ParameterService.ATSWLAgeAttributes(valueChangeEvent);
    }

    public void setBind_ATSWLAge(RichColumn bind_ATSWLAge) {
        this.bind_ATSWLAge = bind_ATSWLAge;
    }

    public RichColumn getBind_ATSWLAge() {
        return bind_ATSWLAge;
    }
}

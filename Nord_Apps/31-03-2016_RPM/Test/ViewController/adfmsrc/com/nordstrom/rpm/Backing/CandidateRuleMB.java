package com.nordstrom.rpm.Backing;

import com.nordstrom.rpm.Service.CandidateRuleService;
import java.util.ArrayList;
import java.util.List;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import oracle.adf.share.ADFContext;
import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.component.rich.data.RichTable;
import oracle.adf.view.rich.component.rich.input.RichInputComboboxListOfValues;
import oracle.adf.view.rich.component.rich.input.RichInputDate;
import oracle.adf.view.rich.component.rich.input.RichInputText;
import oracle.adf.view.rich.component.rich.input.RichSelectBooleanCheckbox;
import oracle.adf.view.rich.component.rich.input.RichSelectManyChoice;
import oracle.adf.view.rich.component.rich.input.RichSelectOneChoice;
import oracle.adf.view.rich.component.rich.input.RichSelectOneListbox;
import oracle.adf.view.rich.component.rich.layout.RichPanelLabelAndMessage;
import oracle.adf.view.rich.component.rich.nav.RichCommandButton;
import oracle.adf.view.rich.component.rich.output.RichMessages;

public class CandidateRuleMB {
   CandidateRuleService candidateService = (CandidateRuleService)ADFContext.getCurrent().getPageFlowScope().get("CandidateRuleImpl");
    
    private List criterialist=new ArrayList();
    private String ItemListListenerValue;
private  ArrayList<String> selectedlist=new ArrayList<String>();
    private  ArrayList<String> deptAll=new ArrayList<String>();
    private String  criteriaName;
    private RichSelectOneChoice bind_parameter;
    private RichInputText bind_Value;
    private String Value;
    private Boolean isItemListValueFlag = false;
    private ArrayList<SelectItem> customlist=new ArrayList<SelectItem>();
    private String selectedCriteriaId;
    private RichSelectOneListbox listboxbinding;
    private String selectedParametername;
    private String selectedfileName;
    private String selectedOperator;
    private StringBuffer sb1 = new StringBuffer();
    private StringBuffer stb = new StringBuffer();
    private String st;
    private String st1;
    private int selectIndex;
    private ArrayList<String> arrlist = new ArrayList<String>();
    private ArrayList<String> tempArrlist = new ArrayList<String>();
    private ArrayList<String> customarrlist = new ArrayList<String>();
    private RichInputText bind_RuleID;
    private int ruleid;
    private RichPanelLabelAndMessage bind_RuleIdd;
    private RichSelectOneChoice bind_filedname;
    private RichSelectOneChoice bind_operator;
    private RichSelectBooleanCheckbox bind_pCheckbox;
    private String listingruleid;
    private List listingruleidlist=new ArrayList();
    private RichTable bind_candidateRule;
    private int deleteupdateCount;
    private ArrayList<String>   deptIdArrlist    = new ArrayList<String>();
    private ArrayList<String> deptIdArraylist_itemvalue = new ArrayList<String>();
    private List selectedValues=new ArrayList(); 
     
    private ArrayList<SelectItem> DepartmentdataList=new ArrayList<SelectItem>();
    private ArrayList<SelectItem> ruleType = new ArrayList<SelectItem>();
    private RichInputText bind_Priority;
    private RichSelectOneChoice bind_RuleType;
    private RichSelectOneChoice bind_ZoneID;
    private RichInputText bind_Description;
    private RichSelectOneChoice bind_MerchantField;
    private RichSelectOneChoice bind_MerchantOperator;
    private RichInputComboboxListOfValues bind_MerchantValue;
    
    private RichSelectManyChoice bind_Department;
    private RichInputDate bind_EffectiveDate;
    private RichInputDate bind_EndDate;
    private String paramID;
    private RichInputComboboxListOfValues bind_ValueM;
    private boolean createButton;
    private boolean updateButton;
    private RichPopup bind_deletepopup;
    private RichSelectBooleanCheckbox bind_selectRules;
    private RichCommandButton deleteCriteria;
    private RichInputDate bind_dateValue;
    private String dateValue;
    private RichCommandButton updateAdd;
    private RichCommandButton createAdd;
    private RichMessages validationMessage;    

    public CandidateRuleMB() {
        super();
    }

    public void createNewRule(ActionEvent actionEvent) {
        // Add event code here...
        candidateService.createNewRule(actionEvent);
    }
    public void candidateListingLogic_Merchant() {
        // Add event code here...
        candidateService.candidateListingLogic_Merchant();
    }
    public void candidateListingLogic_Admin() {
        candidateService.candidateListingLogic_Admin();
    }
    public void loginUser_view(){
        candidateService.loginUser_view();
    }
    
    public void UpdateRule(ActionEvent actionEvent){
        candidateService.UpdateRule(actionEvent);
    }
    public void saveNewRule(ActionEvent actionEvent) {
        candidateService.saveNewRule(actionEvent);
    }
    public String saveNewRule(){
        String saveRule = candidateService.saveNewRule(); 
        return saveRule;
    }
    public void columnNameVCL(ValueChangeEvent valueChangeEvent){
        candidateService.columnNameVCL(valueChangeEvent);
    }
    
    public void ParamNameVCL(ValueChangeEvent valueChangeEvent){
        candidateService.ParamNameVCL(valueChangeEvent);
    }
    
    public void operatorVCL(ValueChangeEvent valueChangeEvent) {
        candidateService.operatorVCL(valueChangeEvent);        
    }
    public void parametercheckBoxVCL(ValueChangeEvent valueChangeEvent){
        candidateService.parametercheckBoxVCL(valueChangeEvent);        
    }
    
    public void addCriteria(ActionEvent actionEvent) {    
        candidateService.addCriteria(actionEvent);
    }
        
    public void deleteCriteria(ActionEvent actionEvent) {
        candidateService.deleteCriteria(actionEvent);    
    }
        
     public void listBoxVCL(ValueChangeEvent valueChangeEvent){
         candidateService.listBoxVCL(valueChangeEvent);
     }    
    
    public void setCandidateService(CandidateRuleService candidateService) {
        this.candidateService = candidateService;
    }
    
    public void operatorVCLMerchant(ValueChangeEvent valueChangeEvent){
        candidateService.operatorVCLMerchant(valueChangeEvent);
    }
    
    public void columnNameVCLMerchant(ValueChangeEvent valueChangeEvent) {
        candidateService.columnNameVCLMerchant(valueChangeEvent);   
    }

    public CandidateRuleService getCandidateService() {
        return candidateService;
    }

    public void setCriterialist(List criterialist) {
        this.criterialist = criterialist;
    }

    public List getCriterialist() {
        return criterialist;
    }

    public void setCriteriaName(String criteriaName) {
        this.criteriaName = criteriaName;
    }

    public String getCriteriaName() {
        return criteriaName;
    }

    public void setBind_parameter(RichSelectOneChoice bind_parameter) {
        this.bind_parameter = bind_parameter;
    }

    public RichSelectOneChoice getBind_parameter() {
        return bind_parameter;
    }

    public void setBind_Value(RichInputText bind_Value) {
        this.bind_Value = bind_Value;
    }

    public RichInputText getBind_Value() {
        return bind_Value;
    }

    public void setValue(String Value) {
        this.Value = Value;
    }

    public String getValue() {
        return Value;
    }

    public void setCustomlist(ArrayList<SelectItem> customlist) {
        this.customlist = customlist;
    }

    public ArrayList<SelectItem> getCustomlist() {
        return customlist;
    }

    public void setListboxbinding(RichSelectOneListbox listboxbinding) {
        this.listboxbinding = listboxbinding;
    }

    public RichSelectOneListbox getListboxbinding() {
        return listboxbinding;
    }

    public void setSelectedParametername(String selectedParametername) {
        this.selectedParametername = selectedParametername;
    }

    public String getSelectedParametername() {
        return selectedParametername;
    }

    public void setSelectedfileName(String selectedfileName) {
        this.selectedfileName = selectedfileName;
    }

    public String getSelectedfileName() {
        return selectedfileName;
    }

    public void setSelectedOperator(String selectedOperator) {
        this.selectedOperator = selectedOperator;
    }

    public String getSelectedOperator() {
        return selectedOperator;
    }

    public void setSb1(StringBuffer sb1) {
        this.sb1 = sb1;
    }

    public StringBuffer getSb1() {
        return sb1;
    }

    public void setSt(String st) {
        this.st = st;
    }

    public String getSt() {
        return st;
    }

    public void setArrlist(ArrayList<String> arrlist) {
        this.arrlist = arrlist;
    }

    public ArrayList<String> getArrlist() {
        return arrlist;
    }

    public void setBind_RuleID(RichInputText bind_RuleID) {
        this.bind_RuleID = bind_RuleID;
    }

    public RichInputText getBind_RuleID() {
        return bind_RuleID;
    }

    public void setRuleid(int ruleid) {
        this.ruleid = ruleid;
    }

    public int getRuleid() {
        return ruleid;
    }


    public void setBind_RuleIdd(RichPanelLabelAndMessage bind_RuleIdd) {
        this.bind_RuleIdd = bind_RuleIdd;
    }

    public RichPanelLabelAndMessage getBind_RuleIdd() {
        return bind_RuleIdd;
    }

    public void setBind_filedname(RichSelectOneChoice bind_filedname) {
        this.bind_filedname = bind_filedname;
    }

    public RichSelectOneChoice getBind_filedname() {
        return bind_filedname;
    }

    public void setBind_operator(RichSelectOneChoice bind_operator) {
        this.bind_operator = bind_operator;
    }

    public RichSelectOneChoice getBind_operator() {
        return bind_operator;
    }

    public void setBind_pCheckbox(RichSelectBooleanCheckbox bind_pCheckbox) {
        this.bind_pCheckbox = bind_pCheckbox;
    }

    public RichSelectBooleanCheckbox getBind_pCheckbox() {
        return bind_pCheckbox;
    }

    public void setStb(StringBuffer stb) {
        this.stb = stb;
    }

    public StringBuffer getStb() {
        return stb;
    }

    public void setSt1(String st1) {
        this.st1 = st1;
    }

    public String getSt1() {
        return st1;
    }

    public void setCustomarrlist(ArrayList<String> customarrlist) {
        this.customarrlist = customarrlist;
    }

    public ArrayList<String> getCustomarrlist() {
        return customarrlist;
    }

    public void setSelectIndex(int selectIndex) {
        this.selectIndex = selectIndex;
    }

    public int getSelectIndex() {
        return selectIndex;
    }

    public void setListingruleid(String listingruleid) {
        this.listingruleid = listingruleid;
    }

    public String getListingruleid() {
        return listingruleid;
    }

    public void MarkCheckBoxVCL(ValueChangeEvent valueChangeEvent) {
        // Add event code here...
        candidateService.MarkCheckBoxVCL(valueChangeEvent);
    }

    public void setListingruleidlist(List listingruleidlist) {
        this.listingruleidlist = listingruleidlist;
    }

    public List getListingruleidlist() {
        return listingruleidlist;
    }

    public void deleteRule(ActionEvent actionEvent) {
        // Add event code here...
        candidateService.deleteRule(actionEvent);
           }

    public void setBind_candidateRule(RichTable bind_candidateRule) {
        this.bind_candidateRule = bind_candidateRule;
    }

    public RichTable getBind_candidateRule() {
        return bind_candidateRule;
    }

    public void setDeleteupdateCount(int deleteupdateCount) {
        this.deleteupdateCount = deleteupdateCount;
    }

    public int getDeleteupdateCount() {
        return deleteupdateCount;
    }

    public void setDepartmentdataList(ArrayList<SelectItem> DepartmentdataList) {
        this.DepartmentdataList = DepartmentdataList;
    }

    public ArrayList<SelectItem> getDepartmentdataList() {
        return DepartmentdataList;
    }

    public void departmentMerchant_VCL(ValueChangeEvent valueChangeEvent) {
        // Add event code here...
        candidateService.departmentMerchant_VCL(valueChangeEvent);
        
    }

    public void setDeptIdArrlist(ArrayList<String> deptIdArrlist) {
        this.deptIdArrlist = deptIdArrlist;
    }

    public ArrayList<String> getDeptIdArrlist() {
        return deptIdArrlist;
    }


    public void setSelectedValues(List selectedValues) {
        this.selectedValues = selectedValues;
    }

    public List getSelectedValues() {
        return selectedValues;
    }


    public void setSelectedlist(ArrayList<String> selectedlist) {
        this.selectedlist = selectedlist;
    }

    public ArrayList<String> getSelectedlist() {
        return selectedlist;
    }

    public void setBind_Priority(RichInputText bind_Priority) {
        this.bind_Priority = bind_Priority;
    }

    public RichInputText getBind_Priority() {
        return bind_Priority;
    }

    public void setBind_RuleType(RichSelectOneChoice bind_RuleType) {
        this.bind_RuleType = bind_RuleType;
    }

    public RichSelectOneChoice getBind_RuleType() {
        return bind_RuleType;
    }

    public void setBind_ZoneID(RichSelectOneChoice bind_ZoneID) {
        this.bind_ZoneID = bind_ZoneID;
    }

    public RichSelectOneChoice getBind_ZoneID() {
        return bind_ZoneID;
    }

    public void setBind_Description(RichInputText bind_Description) {
        this.bind_Description = bind_Description;
    }

    public RichInputText getBind_Description() {
        return bind_Description;
    }

    public void setBind_MerchantField(RichSelectOneChoice bind_MerchantField) {
        this.bind_MerchantField = bind_MerchantField;
    }

    public RichSelectOneChoice getBind_MerchantField() {
        return bind_MerchantField;
    }

    public void setBind_MerchantOperator(RichSelectOneChoice bind_MerchantOperator) {
        this.bind_MerchantOperator = bind_MerchantOperator;
    }

    public RichSelectOneChoice getBind_MerchantOperator() {
        return bind_MerchantOperator;
    }

    

    public void setBind_Department(RichSelectManyChoice bind_Department) {
        this.bind_Department = bind_Department;
    }

    public RichSelectManyChoice getBind_Department() {
        return bind_Department;
    }

    public void setBind_EffectiveDate(RichInputDate bind_EffectiveDate) {
        this.bind_EffectiveDate = bind_EffectiveDate;
    }

    public RichInputDate getBind_EffectiveDate() {
        return bind_EffectiveDate;
    }

    public void setBind_EndDate(RichInputDate bind_EndDate) {
        this.bind_EndDate = bind_EndDate;
    }

    public RichInputDate getBind_EndDate() {
        return bind_EndDate;
    }

    public void setRuleType(ArrayList<SelectItem> ruleType) {
        this.ruleType = ruleType;
    }

    public ArrayList<SelectItem> getRuleType() {
        ArrayList<SelectItem> ruleType = new ArrayList<SelectItem>();

        ruleType.add(new SelectItem("I","INCLUSIVE"));
        ruleType.add(new SelectItem("E","EXCLUSIVE"));
        
        return ruleType;
    }

    public void setParamID(String paramID) {
        this.paramID = paramID;
    }

    public String getParamID() {
        return paramID;
    }


    public void setDeptIdArraylist_itemvalue(ArrayList<String> deptIdArraylist_itemvalue) {
        this.deptIdArraylist_itemvalue = deptIdArraylist_itemvalue;
    }

    public ArrayList<String> getDeptIdArraylist_itemvalue() {
        return deptIdArraylist_itemvalue;
    }

    public void setBind_ValueM(RichInputComboboxListOfValues bind_ValueM) {
        this.bind_ValueM = bind_ValueM;
    }

    public RichInputComboboxListOfValues getBind_ValueM() {
        return bind_ValueM;
    }

    public void setBind_MerchantValue(RichInputComboboxListOfValues bind_MerchantValue) {
        this.bind_MerchantValue = bind_MerchantValue;
    }

    public RichInputComboboxListOfValues getBind_MerchantValue() {
        return bind_MerchantValue;
    }

    public void setDeptAll(ArrayList<String> deptAll) {
        this.deptAll = deptAll;
    }

    public ArrayList<String> getDeptAll() {
        return deptAll;
    }

    public void itemListvalue_VCL(ValueChangeEvent valueChangeEvent) {
        // Add event code here...
        candidateService.itemListvalue_VCL(valueChangeEvent);
    }

    public void setCreateButton(boolean createButton) {
        this.createButton = createButton;
    }

    public boolean isCreateButton() {
        return createButton;
    }

    public void setUpdateButton(boolean updateButton) {
        this.updateButton = updateButton;
    }

    public boolean isUpdateButton() {
        return updateButton;
    }

    public void yesAction(ActionEvent actionEvent) {
        // Add event code here...
        candidateService.yesAction(actionEvent);
    }

    public void NoAction(ActionEvent actionEvent) {
        // Add event code here...
        candidateService.NoAction(actionEvent);
    }

    public void setBind_deletepopup(RichPopup bind_deletepopup) {
        this.bind_deletepopup = bind_deletepopup;
    }

    public RichPopup getBind_deletepopup() {
        return bind_deletepopup;
    }

    public void setIsItemListValueFlag(Boolean isItemListValueFlag) {
        this.isItemListValueFlag = isItemListValueFlag;
    }

    public Boolean getIsItemListValueFlag() {
        return isItemListValueFlag;
    }

    public void setBind_selectRules(RichSelectBooleanCheckbox bind_selectRules) {
        this.bind_selectRules = bind_selectRules;
    }

    public RichSelectBooleanCheckbox getBind_selectRules() {
        return bind_selectRules;
    }

    public void setItemListListenerValue(String ItemListListenerValue) {
        this.ItemListListenerValue = ItemListListenerValue;
    }

    public String getItemListListenerValue() {
        return ItemListListenerValue;
    }

    public void cancelActionListener(ActionEvent actionEvent) {
        // Add event code here...
        candidateService.cancelActionListener(actionEvent);
    }

    public void setSelectedCriteriaId(String selectedCriteriaId) {
        this.selectedCriteriaId = selectedCriteriaId;
    }

    public String getSelectedCriteriaId() {
        return selectedCriteriaId;
    }

    public void setTempArrlist(ArrayList<String> tempArrlist) {
        this.tempArrlist = tempArrlist;
    }

    public ArrayList<String> getTempArrlist() {
        return tempArrlist;
    }

    public void setDeleteCriteria(RichCommandButton deleteCriteria) {
        this.deleteCriteria = deleteCriteria;
    }

    public RichCommandButton getDeleteCriteria() {
        return deleteCriteria;
    }    

    public void setBind_dateValue(RichInputDate bind_dateValue) {
        this.bind_dateValue = bind_dateValue;
    }

    public RichInputDate getBind_dateValue() {
        return bind_dateValue;
    }

    public void setDateValue(String dateValue) {
        this.dateValue = dateValue;
    }

    public String getDateValue() {
        return dateValue;
    }

    public void setUpdateAdd(RichCommandButton updateAdd) {
        this.updateAdd = updateAdd;
    }

    public RichCommandButton getUpdateAdd() {
        return updateAdd;
    }

    public void setCreateAdd(RichCommandButton createAdd) {
        this.createAdd = createAdd;
    }

    public RichCommandButton getCreateAdd() {
        return createAdd;
    }

    public void ruleTypeVCL(ValueChangeEvent valueChangeEvent) {
        // Add event code here...
        candidateService.ruleTypeVCL(valueChangeEvent);
    }

    public void setValidationMessage(RichMessages validationMessage) {
        this.validationMessage = validationMessage;
    }

    public RichMessages getValidationMessage() {
        return validationMessage;
    }    
}

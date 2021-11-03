package com.nordstrom.rpm.Service;

import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

public interface CandidateRuleService {
    public void itemListvalue_VCL(ValueChangeEvent valueChangeEvent);
    
    public void createNewRule(ActionEvent actionEvent);
    public void candidateListingLogic_Merchant();
    public void candidateListingLogic_Admin();
    public void loginUser_view();
    public void UpdateRule(ActionEvent actionEvent);
    public void saveNewRule(ActionEvent actionEvent) ;
    public String saveNewRule() ;
    public void columnNameVCL(ValueChangeEvent valueChangeEvent);
    public void ParamNameVCL(ValueChangeEvent valueChangeEvent);
    public void operatorVCL(ValueChangeEvent valueChangeEvent) ;
    public void parametercheckBoxVCL(ValueChangeEvent valueChangeEvent);
    public void addCriteria(ActionEvent actionEvent) ;
    public void deleteCriteria(ActionEvent actionEvent) ;    
    public void listBoxVCL(ValueChangeEvent valueChangeEvent);
    public void MarkCheckBoxVCL(ValueChangeEvent valueChangeEvent) ;
    public void deleteRule(ActionEvent actionEvent) ;
    public void departmentMerchant_VCL(ValueChangeEvent valueChangeEvent);
    public void operatorVCLMerchant(ValueChangeEvent valueChangeEvent);
    public void columnNameVCLMerchant(ValueChangeEvent valueChangeEvent);
   
    public void yesAction(ActionEvent actionEvent);
    public void NoAction(ActionEvent actionEvent);
    public void cancelActionListener(ActionEvent actionEvent);
    public void ruleTypeVCL(ValueChangeEvent valueChangeEvent);
   
}

package com.nordstrom.rpm.Impl;

import com.nordstrom.rpm.Backing.*;
import com.nordstrom.rpm.comboboxImpl.*;
import com.nordstrom.rpm.FacesMessageUtils;
import com.nordstrom.rpm.Service.CandidateRuleService;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.el.ELContext;
import javax.el.ExpressionFactory;
import javax.el.PropertyNotFoundException;
import javax.el.ValueExpression;
import javax.faces.application.Application;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import javax.faces.validator.ValidatorException;
import javax.servlet.http.HttpSession;
import oracle.adf.model.BindingContext;
import oracle.adf.model.binding.DCBindingContainer;
import oracle.adf.model.binding.DCIteratorBinding;
import oracle.adf.share.ADFContext;
import oracle.adf.share.logging.ADFLogger;
import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.context.AdfFacesContext;
import oracle.binding.OperationBinding;
import oracle.jbo.JboException;
import oracle.jbo.JboSQLException;
import oracle.jbo.Row;
import oracle.jbo.ViewObject;
import oracle.jbo.ViewObjectNotPreparedException;
import oracle.jbo.domain.Date;

import org.apache.myfaces.trinidad.render.ExtendedRenderKitService;
import org.apache.myfaces.trinidad.util.Service;

/****************************************************************************
 * Class Name            : CandidateRuleImpl.java
 * Language              : Oracle ADF,Java
 * Description           : This class implements the CandidateRuleService interface.
 *                         The methods in this class implement the functionalities required for the Candidate_Rule.jsff,Rule_Creation.jsff page fragments
 * Author                : Ramesh Chinta, Infosys Ltd.
 *
 * Date written          : 08-October-2015
 *
 * Modification History  :
 *
 * Description of change                 Date           Modified by
 * ---------------------                 ----           -----------
 *****************************************************************************/

public class CandidateRuleImpl implements CandidateRuleService {

    private static ADFLogger RPMlogger = ADFLogger.createADFLogger(CandidateRuleImpl.class);
    private String ruleid;
    FacesContext ctx = FacesContext.getCurrentInstance();
    AdfFacesContext adfcontext = AdfFacesContext.getCurrentInstance();
    private List ruleslist = new ArrayList();
    private ArrayList<SelectItem> valueListautosuggest = new ArrayList<SelectItem>();
    private List<SelectItem> items = new ArrayList<SelectItem>();
    private String selectedValue;    
    private java.util.Date currentSystemDate;

    public CandidateRuleImpl() {
        super();
        RPMlogger.info("Creating new instance of Candidte Rule");
        ADFContext.getCurrent().getPageFlowScope().put("selectedRules", false);
    }

    public void createNewRule(ActionEvent actionEvent) {
        RPMlogger.info("createNewRule");
        RPMlogger.log(Level.INFO, "createNewRule() Begins");
        CandidateRuleMB candidateRule = (CandidateRuleMB)ADFContext.getCurrent().getPageFlowScope().get("CandidateRuleMB");
        ExternalContext ectx = FacesContext.getCurrentInstance().getExternalContext();
        HttpSession userSession = (HttpSession)ectx.getSession(true);        
        adfcontext.getPageFlowScope().put("RuleType", null);          
        ADFContext.getCurrent().getPageFlowScope().put("createAdd", true);
        try {
            if (userSession.getAttribute("roleId").equals("80")) {
                adfcontext.getPageFlowScope().put("editFieldName", 1);
                adfcontext.getPageFlowScope().put("editOperations", 1);                
                candidateRule.setSelectedfileName("ITEM_LIST");
                candidateRule.setSelectedOperator("=");                
                departmentMerchant();
            }
            else{
                ADFContext.getCurrent().getPageFlowScope().put("activateDeleteCriteria", true);
                ADFContext.getCurrent().getPageFlowScope().put("priorityViewable", false);
            }
            
            BindingContext.getCurrent().getCurrentBindingsEntry().getOperationBinding("CreateInsert").execute();           
            candidateRule.setIsItemListValueFlag(false);
            OperationBinding oBinding = BindingContext.getCurrent().getCurrentBindingsEntry().getOperationBinding("queryExecution");
            if (oBinding != null)
                oBinding.execute();
        }
        catch (NullPointerException ne) {
            ne.printStackTrace();
        } catch (ViewObjectNotPreparedException vnpe) {
            vnpe.printStackTrace();
        }
        catch (JboException je) {
            je.printStackTrace();
            JboException ex = new JboException("getting the method in bindingContext is not binded");
            BindingContext bctx = BindingContext.getCurrent();
            ((DCBindingContainer)bctx.getCurrentBindingsEntry()).reportException(ex);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.runFinalization();

        RPMlogger.log(Level.INFO, "createNewRule() End");
    }

    public void UpdateRule(ActionEvent actionEvent) {
        RPMlogger.info("Updating the existing rule criteria");
        RPMlogger.log(Level.INFO, "UpdateRule() starts");
        try {
            CandidateRuleMB candidateRule = (CandidateRuleMB)ADFContext.getCurrent().getPageFlowScope().get("CandidateRuleMB");
            DCBindingContainer dcBinding = (DCBindingContainer)BindingContext.getCurrent().getCurrentBindingsEntry();
            ExternalContext ectx = FacesContext.getCurrentInstance().getExternalContext();
            HttpSession userSession = (HttpSession)ectx.getSession(true);            
            ADFContext.getCurrent().getPageFlowScope().put("createAdd", true);
            if (null != actionEvent) {
                DCIteratorBinding dciBinding = dcBinding.findIteratorBinding("Candidate_Rule_View1Iterator");
                ViewObject vo = dciBinding.getViewObject();
                Row r = vo.getCurrentRow();
                ruleid = r.getAttribute("RuleId").toString();
                String zoneId=r.getAttribute("ZoneId").toString();
                DCIteratorBinding dcIterator = dcBinding.findIteratorBinding("Clr_Wksht_Rule_View1Iterator");
                ViewObject voImpl = dcIterator.getViewObject();
                voImpl.setWhereClause("rule_id in " + (ruleid));
                voImpl.executeQuery();                                
                setValueToEL("#{bindings.Name.inputValue}", r.getAttribute("Name"));                  
                adfcontext.getPageFlowScope().put("zoneID", zoneId);
                oracle.jbo.domain.Number num = new oracle.jbo.domain.Number(Integer.parseInt(zoneId));              
                if(getValueFrmExpression("#{bindings.ZoneId.inputValue}").equals(""))
                    setValueToEL("#{bindings.ZoneId.inputValue}", num);
                    
                if (userSession.getAttribute("roleId").equals("80")) {
                    adfcontext.getPageFlowScope().put("RuleType", null);
                    adfcontext.getPageFlowScope().put("editFieldName", 1);
                    adfcontext.getPageFlowScope().put("editOperations", 1);
                    adfcontext.getPageFlowScope().put("itemListRuleID", ruleid);
                    setValueToEL("#{bindings.EffectiveDate.inputValue}", r.getAttribute("EffectiveDate"));
                    setValueToEL("#{bindings.EndDate.inputValue}", r.getAttribute("EndDate"));
                    candidateRule.setIsItemListValueFlag(true);
                    DCIteratorBinding fieldNameBinding = dcBinding.findIteratorBinding("Column_Name_View_Merchant1Iterator");
                    ViewObject Column_Name_VO = fieldNameBinding.getViewObject();
                    Row Column_Name_VO_data = Column_Name_VO.getRowAtRangeIndex(Integer.parseInt(adfcontext.getPageFlowScope().get("editFieldName").toString()));
                    if (Column_Name_VO_data != null)
                        candidateRule.setSelectedfileName(Column_Name_VO_data.getAttribute("ColumnName").toString());
                    DCIteratorBinding operatorBinding = dcBinding.findIteratorBinding("Operator_View_Merchant1Iterator");
                    ViewObject Operator_View_VO = operatorBinding.getViewObject();
                    Row Operator_View_VO_data = Operator_View_VO.getRowAtRangeIndex(Integer.parseInt(adfcontext.getPageFlowScope().get("editOperations").toString()));
                    if (Operator_View_VO_data != null)
                        candidateRule.setSelectedOperator(Operator_View_VO_data.getAttribute(1).toString());

                    candidateRule.setSelectedfileName("ITEM_LIST");
                    candidateRule.setSelectedOperator("="); 
                    departmentMerchant();
                    OperationBinding obinding = BindingContext.getCurrent().getCurrentBindingsEntry().getOperationBinding("departmentUpdate");
                    ArrayList<String> deptlist = new ArrayList<String>();                                
                    if (obinding != null) {
                        obinding.getParamsMap().put("ruleid", ruleid);
                        obinding.execute();
                        deptlist = (ArrayList<String>)obinding.getResult();
                        if(deptlist.size() > 0){
                            for (int i = 0; i < deptlist.size(); i++) {
                                candidateRule.getSelectedValues().add(deptlist.get(i));
                            }
                        }
                        else{                       
                            for(int j=0; j<candidateRule.getDeptAll().size(); j++)
                                candidateRule.getSelectedValues().add(candidateRule.getDeptAll().get(j));
                        }
                    } 
                } else {
                    if (adfcontext.getPageFlowScope().get("RuleType").equals("EXCLUSIVE")){
                        adfcontext.getPageFlowScope().put("RuleType", "E");
                        ADFContext.getCurrent().getPageFlowScope().put("priorityViewable", true);
                    }
                    else
                        adfcontext.getPageFlowScope().put("RuleType", "I");
                                        
                    setValueToEL("#{bindings.Priority.inputValue}", r.getAttribute("Priority"));
                    setValueToEL("#{bindings.Description.inputValue}", r.getAttribute("Description"));
                    adfcontext.getPageFlowScope().put("corporateRuleID", ruleid);                     
                }                            
                OperationBinding ob = BindingContext.getCurrent().getCurrentBindingsEntry().getOperationBinding("getRoleDefs");
                StringBuffer tempRuleCriteria = new StringBuffer();
                if (ob != null) {
                    ArrayList<String> list = new ArrayList<String>();
                    ob.getParamsMap().put("ruleId", ruleid);
                    ob.execute();
                    String paramID = "";
                    list = (ArrayList<String>)ob.getResult();
                    DCBindingContainer dcb = BindingContext.getCurrent().findBindingContainer("com_nordstrom_rpm_view_Rule_CreationPageDef");
                    for (int i = 0; i < list.size(); i++) {
                        String[] criteriaItems = list.get(i).split(" ");
                        dciBinding = dcb.findIteratorBinding("Paramater_Id_View1Iterator");
                        Row[] availableParameters = dciBinding.getAllRowsInRange();
                        if (!criteriaItems[4].equals("null")) {
                            for (int j = 1; j <= availableParameters.length; j++) {
                                if (criteriaItems[4].equalsIgnoreCase(availableParameters[j].getAttribute("ParamName").toString())) {
                                    paramID = availableParameters[j].getAttribute("ParamId").toString();
                                    break;
                                }
                            }
                        } else
                            paramID = "null";
                        
                        candidateRule.getTempArrlist().add(list.get(i));
                        tempRuleCriteria.append(criteriaItems[0]).append(" ").append(criteriaItems[1]).append(" ").append(criteriaItems[2]).append(" ").append(criteriaItems[3]).append(" ").append(paramID);
                        candidateRule.getArrlist().add(tempRuleCriteria.toString());
                        tempRuleCriteria.delete(0, tempRuleCriteria.length());        
                    }
                    for (String sr : candidateRule.getTempArrlist()) {
                        String[] st = sr.split(" ");
                        String temps = "";
                        if (st[1].equals("ITEM_LIST")) {
                            String itemListValue = st[3];
                            candidateRule.setValue(itemListValue);
                        }                        
                        if(st[3].length() > 2){
                            if(st[3].contains("-")){
                                if(st[3].charAt(2) == '-'){
                                    SimpleDateFormat format1 = new SimpleDateFormat("dd-MMM-yyyy");
                                    SimpleDateFormat format2 = new SimpleDateFormat("MM/dd/yyyy");
                                    java.util.Date utilDate;
                                    java.sql.Date javaSqlDate;
            
                                    try {
                                        utilDate = format1.parse(st[3]);
                                        String formattedDate=format2.format(utilDate);
                                        st[3]=formattedDate;
                                    }
                                    catch (ParseException f) {
                                    f.printStackTrace();
                                    }
                                }
                            }
                        }

                        for (int i = 1; i < st.length; i++) {
                            if (!st[i].equals("null")) {
                                temps += st[i];
                                temps += " ";
                            }
                        }
                        candidateRule.getCustomlist().add(new SelectItem(temps));
                        candidateRule.getCustomarrlist().add(temps);
                    }
                }
            }
        } catch (ArrayIndexOutOfBoundsException aiobe) {
            aiobe.printStackTrace();

        } catch (NullPointerException ne) {
            ne.printStackTrace();
        } catch (ViewObjectNotPreparedException vnpe) {
            vnpe.printStackTrace();
        } catch (JboSQLException jse) {
            jse.printStackTrace();
        } catch (JboException je) {
            je.printStackTrace();
            JboException ex = new JboException("");
            BindingContext bctx = BindingContext.getCurrent();
            ((DCBindingContainer)bctx.getCurrentBindingsEntry()).reportException(ex);
        }
        System.runFinalization();
        RPMlogger.log(Level.INFO, "UpdateRule() Ends");
    }

    public void saveNewRule(ActionEvent actionEvent) {
        RPMlogger.info("Saving the new rule");
        RPMlogger.log(Level.INFO, "saveNewRule() starts");
        try {
            if (actionEvent != null) {
                CandidateRuleMB candidateRule = (CandidateRuleMB)ADFContext.getCurrent().getPageFlowScope().get("CandidateRuleMB");
                String action = ADFContext.getCurrent().getPageFlowScope().get("action") + "";
                ExternalContext ectx = FacesContext.getCurrentInstance().getExternalContext();
                HttpSession userSession = (HttpSession)ectx.getSession(true);
                boolean save = true;                
                
                if (userSession.getAttribute("roleId").equals("90")) {
                    if ((candidateRule.getBind_RuleID().getValue() == null || candidateRule.getBind_RuleID().getValue().equals("")) || (candidateRule.getBind_RuleType().getValue() == null) || (candidateRule.getBind_Priority().getValue() == null) || (candidateRule.getCustomlist().isEmpty()) || ((Integer)candidateRule.getBind_ZoneID().getValue() == 0)) {
                        candidateRule.getValidationMessage().setMessage("Please enter the below fields");
                        AdfFacesContext.getCurrentInstance().addPartialTarget(candidateRule.getValidationMessage());
                        if (candidateRule.getBind_RuleID().getValue() == null || candidateRule.getBind_RuleID().getValue().equals(""))
                            FacesMessageUtils.addErrorMessage("Please enter Rule Name",
                                                              FacesMessage.SEVERITY_ERROR);
                        if ((Integer)candidateRule.getBind_ZoneID().getValue() == 0)
                            FacesMessageUtils.addErrorMessage("Please select Zone",
                                                              FacesMessage.SEVERITY_ERROR);
                        if (candidateRule.getBind_RuleType().getValue() == null)
                            FacesMessageUtils.addErrorMessage("Please select Rule Type",
                                                              FacesMessage.SEVERITY_ERROR);
                        if (candidateRule.getBind_Priority().getValue() == null)
                            FacesMessageUtils.addErrorMessage("Please enter Rule Priority",
                                                              FacesMessage.SEVERITY_ERROR);
                        if (candidateRule.getCustomlist().isEmpty())
                            FacesMessageUtils.addErrorMessage("Atleast one criterion required for Candidate rule",
                                                              FacesMessage.SEVERITY_ERROR);
                    } else {
                        if (action.equalsIgnoreCase("Update"))
                            setValueToEL("#{bindings.LastUpdateId.inputValue}",
                                         userSession.getAttribute("userName"));
                        //else
                            setValueToEL("#{bindings.CreateId.inputValue}",
                                         userSession.getAttribute("userName"));                    
                                                                    
                        if(getValueFrmExpression("#{bindings.ZoneId.inputValue}").equals("1"))
                            candidateRule.getBind_ZoneID().setValue(1);
                        if(candidateRule.getBind_Value().getValue()==null) {                
                                candidateRule.getBind_Value().setValue("");
                        }
                        ADFContext.getCurrent().getViewScope().put("ruleName", candidateRule.getBind_RuleID().getValue());
                        ADFContext.getCurrent().getViewScope().put("rulePriority", candidateRule.getBind_Priority().getValue());
                        if((Integer)candidateRule.getBind_filedname().getValue() != 0 && (Integer)candidateRule.getBind_operator().getValue() != 0 && 
                           (!candidateRule.getBind_Value().getValue().equals("") || (Integer)candidateRule.getBind_parameter().getValue() != 0 || candidateRule.getBind_dateValue().getValue()!=null))
                           save =false;                         
                        
                        if((Integer)candidateRule.getBind_filedname().getValue() != 0 || (Integer)candidateRule.getBind_operator().getValue() != 0 || (Integer)candidateRule.getBind_parameter().getValue() != 0)                                              
                            save =false; 
                        else if((Integer)candidateRule.getBind_filedname().getValue() != 0 || (Integer)candidateRule.getBind_operator().getValue() != 0 || !candidateRule.getBind_Value().getValue().equals(""))                                                                                                          
                            save =false;
                        else if((Integer)candidateRule.getBind_filedname().getValue() != 0 || (Integer)candidateRule.getBind_operator().getValue() != 0 || candidateRule.getBind_dateValue().getValue()!=null)                                               
                            save =false;
                                                
                        DCBindingContainer dcb = (DCBindingContainer)BindingContext.getCurrent().getCurrentBindingsEntry();
                        DCIteratorBinding corpRulesIterator = dcb.findIteratorBinding("CorporateRule1Iterator");                      
                        corpRulesIterator.refreshIfNeeded();
                        corpRulesIterator.setRefreshed(true);
                        corpRulesIterator.executeQuery();                                        
                        Row[] corpRulesValues = corpRulesIterator.getAllRowsInRange();
                        String corpRuleList[];
                        String corpRulesList[];
                        String corpRuleCriteriaList[];
                        Boolean corporateRule = true;
                        int count = 0;
                        for (String ruleCriteria : candidateRule.getTempArrlist()) {
                            String[] ruleCriteriaValue = ruleCriteria.split(" ");
                            if (ruleCriteriaValue[4].startsWith("$")) {
                                corporateRule = false;
                            }
                        }
                        
                        if (corporateRule) {                            
                                for (Row corpRule : corpRulesValues) {                                   
                                        if (adfcontext.getPageFlowScope().get("corporateRuleID") == null) {                                            
                                            if (corpRule.getAttribute("RuleDefinition").toString().contains(",")) {
                                                corpRulesList = corpRule.getAttribute("RuleDefinition").toString().split(",");
                                                if(candidateRule.getArrlist().size()==corpRulesList.length){
                                                for (int j = 0; j < corpRulesList.length; j++) {
                                                    
                                                    corpRuleList = corpRulesList[j].split(" ");
                                                    for(int k=0;k<candidateRule.getArrlist().size();k++){                                                       
                                                        corpRuleCriteriaList = candidateRule.getArrlist().get(k).split(" ");
                                                        if (corpRuleCriteriaList[1].equalsIgnoreCase(corpRuleList[0]) && corpRuleCriteriaList[2].equals(corpRuleList[1]) && corpRuleCriteriaList[3].equals(corpRuleList[2])) {
                                                            count++;                                                           
                                                            break;
                                                        }
                                                    }
                                                }                                                
                                                }
                                                if(count!=corpRulesList.length){
                                                    count = 0;
                                                    save = true;
                                                    ADFContext.getCurrent().getViewScope().put("SaveCorpRule", save);
                                                }
                                                else{
                                                    save = false;
                                                    ADFContext.getCurrent().getViewScope().put("SaveCorpRule", save);
                                                    break;
                                                }
                                                
                                            } else {
                                                corpRuleList = corpRule.getAttribute("RuleDefinition").toString().split(" ");
                                                corpRuleCriteriaList = candidateRule.getArrlist().get(0).split(" ");
                                                ADFContext.getCurrent().getViewScope().put("SaveCorpRule", true);
                                                if(candidateRule.getArrlist().size()==1){
                                                if (corpRuleCriteriaList[1].equalsIgnoreCase(corpRuleList[0]) && corpRuleCriteriaList[2].equals(corpRuleList[1]) && corpRuleCriteriaList[3].equals(corpRuleList[2])) {
                                                    count++;
                                                    save = false;
                                                    ADFContext.getCurrent().getViewScope().put("SaveCorpRule", save);
                                                    break;
                                                }
                                                }
                                            }
                                        } else {
                                            if (!corpRule.getAttribute("RuleId").toString().equals(adfcontext.getPageFlowScope().get("corporateRuleID"))) {                                                
                                                if (corpRule.getAttribute("RuleDefinition").toString().contains(",")) {
                                                    corpRulesList = corpRule.getAttribute("RuleDefinition").toString().split(",");
                                                    if(candidateRule.getArrlist().size()==corpRulesList.length){
                                                    for (int j = 0; j < corpRulesList.length;j++) {
                                                        
                                                        corpRuleList = corpRulesList[j].split(" ");
                                                        for(int k=0;k<candidateRule.getArrlist().size();k++){                                                            
                                                            corpRuleCriteriaList = candidateRule.getArrlist().get(k).split(" ");
                                                            if (corpRuleCriteriaList[1].equalsIgnoreCase(corpRuleList[0]) && corpRuleCriteriaList[2].equals(corpRuleList[1]) && corpRuleCriteriaList[3].equals(corpRuleList[2])) {
                                                                count++;                                                                                                                            
                                                                break;
                                                            }
                                                        }                                                        
                                                    }                                                    
                                                    }                                                    
                                                    if(count!=corpRulesList.length){
                                                        count = 0;
                                                        save = true;
                                                        ADFContext.getCurrent().getViewScope().put("SaveCorpRule", save);
                                                    }
                                                    else{
                                                        save = false;
                                                        ADFContext.getCurrent().getViewScope().put("SaveCorpRule", save);
                                                        break;
                                                    }
                                                } else {
                                                    corpRuleList = corpRule.getAttribute("RuleDefinition").toString().split(" ");
                                                    corpRuleCriteriaList = candidateRule.getArrlist().get(0).split(" ");
                                                    ADFContext.getCurrent().getViewScope().put("SaveCorpRule", true);
                                                    if(candidateRule.getArrlist().size()==1){
                                                    if (corpRuleCriteriaList[1].equalsIgnoreCase(corpRuleList[0]) && corpRuleCriteriaList[2].equals(corpRuleList[1]) && corpRuleCriteriaList[3].equals(corpRuleList[2])) {
                                                        count++;
                                                        save = false;
                                                        ADFContext.getCurrent().getViewScope().put("SaveCorpRule", save);
                                                        break;
                                                    }
                                                    }
                                                }
                                            }
                                        }                                    
                                }                                
                        }
                        if (save) {                             
                            OperationBinding ob = BindingContext.getCurrent().getCurrentBindingsEntry().getOperationBinding("addRoleDefs");
                            ob.getParamsMap().put("list", candidateRule.getArrlist());
                            ob.getParamsMap().put("listDept", candidateRule.getSelectedlist());
                            ob.getParamsMap().put("userName",
                                                  userSession.getAttribute("userName"));
                            if (ob != null)
                                ob.execute();
                        }                        
                    }
                }
                if (userSession.getAttribute("roleId").equals("80")) {                                       
                    if (((Integer)candidateRule.getBind_ZoneID().getValue() == 0) || (candidateRule.getBind_Department().getValue() == null) || (candidateRule.getSelectedValues().size() == 0) || 
                        (candidateRule.getBind_EffectiveDate().getValue() == null) || (candidateRule.getBind_EndDate().getValue() == null) || (candidateRule.getIsItemListValueFlag() == false) || 
                        (candidateRule.getValue() == "") || ((Integer)candidateRule.getBind_MerchantField().getValue() == 0) || ((Integer)candidateRule.getBind_MerchantOperator().getValue() == 0)) {
                        candidateRule.getValidationMessage().setMessage("Please enter the below fields");
                        AdfFacesContext.getCurrentInstance().addPartialTarget(candidateRule.getValidationMessage());
                        if ((Integer)candidateRule.getBind_ZoneID().getValue() == 0)
                            FacesMessageUtils.addErrorMessage("Please enter Zone",
                                                              FacesMessage.SEVERITY_ERROR);
                        if (candidateRule.getBind_Department().getValue() == null || candidateRule.getSelectedValues().size() == 0)
                            FacesMessageUtils.addErrorMessage("Please enter Department",
                                                              FacesMessage.SEVERITY_ERROR);

                        if (candidateRule.getBind_EffectiveDate().getValue() == null)
                            FacesMessageUtils.addErrorMessage("Please enter Effective Date",
                                                              FacesMessage.SEVERITY_ERROR);
                        if (candidateRule.getBind_EndDate().getValue() == null)
                            FacesMessageUtils.addErrorMessage("Please enter End Date",
                                                              FacesMessage.SEVERITY_ERROR);
                        if ((Integer)candidateRule.getBind_MerchantField().getValue() == 0)
                            FacesMessageUtils.addErrorMessage("Please enter Field name",
                                                              FacesMessage.SEVERITY_ERROR);
                        if ((Integer)candidateRule.getBind_MerchantOperator().getValue() == 0)
                            FacesMessageUtils.addErrorMessage("Please enter Operator",
                                                              FacesMessage.SEVERITY_ERROR);
                        if (candidateRule.getBind_MerchantValue().getValue() == null || candidateRule.getValue().equalsIgnoreCase("")) {
                            FacesMessageUtils.addErrorMessage("Please enter value",
                                                              FacesMessage.SEVERITY_ERROR);
                        } else if (candidateRule.getIsItemListValueFlag() == false) {
                            FacesMessageUtils.addErrorMessage("Please enter an existing value",
                                                              FacesMessage.SEVERITY_ERROR);
                        }
                    }
                    else {
                        //effective date and end date validation
                        Date effectiveDate = (Date)candidateRule.getBind_EffectiveDate().getValue();
                        Date endDate = (Date)candidateRule.getBind_EndDate().getValue();
                        ADFContext.getCurrent().getViewScope().put("effectiveDate", effectiveDate);
                        ADFContext.getCurrent().getViewScope().put("endDate", endDate);
                        //Date currentDate = new Date(Date.getCurrentDate());
                        DCBindingContainer dcbc = (DCBindingContainer)BindingContext.getCurrent().getCurrentBindingsEntry();
                        DCIteratorBinding dcib =dcbc.findIteratorBinding("V_Date_View1Iterator");
                        ViewObject viewObj = dcib.getViewObject();
                        Row dateRow = viewObj.first();
                        Date currentDate = (Date)dateRow.getAttribute("Vdate");

                        boolean flag=false;
                        if (effectiveDate.compareTo(currentDate) < 0 || endDate.compareTo(currentDate) < 0) {
                            FacesMessageUtils.addErrorMessage("The effective/end date must be on or after the current date",
                                                              FacesMessage.SEVERITY_ERROR);
                            save = false;
                            flag=true;
                        }
                        /*if (endDate.compareTo(currentDate) < 0) {
                            FacesMessageUtils.addErrorMessage("The end date input should be on or after the current date",
                                                              FacesMessage.SEVERITY_ERROR);
                            save = false;
                            flag=true;
                        }*/
                        if(flag==false){
                            if (effectiveDate.compareTo(endDate) > 0) {
                                FacesMessageUtils.addErrorMessage("The end date must be on or after the effective date",
                                                                  FacesMessage.SEVERITY_ERROR);
                                save = false;
                            }
                        }
                        candidateRule.getSelectedlist().clear();
                        for (int i = 0; i < candidateRule.getSelectedValues().size(); i++)
                            candidateRule.getSelectedlist().add(candidateRule.getSelectedValues().get(i).toString());

                        candidateRule.getBind_Department().setAutoSubmit(true);
                        AdfFacesContext.getCurrentInstance().addPartialTarget(candidateRule.getBind_Department());
                        setValueToEL("#{bindings.LastUpdateId.inputValue}",
                                     userSession.getAttribute("userName"));
                        setValueToEL("#{bindings.CreateId.inputValue}",
                                     userSession.getAttribute("userName"));
                        
                        setValueToEL("#{bindings.RuleType.inputValue}", "I"); 
                        addCriteriaMerchant();                        
                        DCBindingContainer dcb = (DCBindingContainer)BindingContext.getCurrent().getCurrentBindingsEntry();
                        DCIteratorBinding itemListIterator = dcb.findIteratorBinding("Unique_ItemList_Rule_View1Iterator"); //Unique_ItemList_Rule_View1Iterator
                        itemListIterator.executeQuery();
                        Row[] itemListValues = itemListIterator.getAllRowsInRange();
                        String deptList[];                          
                        if(save==true){
                        for (int j = 0; j < candidateRule.getArrlist().size(); j++) {
                            if (candidateRule.getArrlist().get(j).contains("DEPT")) {
                                deptList = candidateRule.getArrlist().get(j).split(" ");
                                for (int i = 0; i < itemListValues.length; i++) {
                                    save = true;
                                    ADFContext.getCurrent().getViewScope().put("SaveDeptRule", save);
                                    oracle.jbo.domain.Date startDate = (oracle.jbo.domain.Date)itemListValues[i].getAttribute("EffectiveDate");
                                    oracle.jbo.domain.Date finalDate = (oracle.jbo.domain.Date)itemListValues[i].getAttribute("EndDate");
                                    if (itemListValues[i].getAttribute("Itemlist").equals(candidateRule.getItemListListenerValue()) && 
                                        (itemListValues[i].getAttribute("ZoneId").toString().equals(adfcontext.getPageFlowScope().get("zoneID"))) && (itemListValues[i].getAttribute("Dept").equals(deptList[3]))) {
                                        if ((effectiveDate.dateValue().after(startDate.dateValue()) || effectiveDate.dateValue().compareTo(startDate.dateValue()) == 0) && (endDate.dateValue().before(finalDate.dateValue()) || endDate.dateValue().compareTo(finalDate.dateValue()) == 0)) {
                                            if (adfcontext.getPageFlowScope().get("itemListRuleID") == null) {
                                                save = false;
                                                ADFContext.getCurrent().getViewScope().put("SaveDeptRule", save);                                               
                                                break;
                                            } else {
                                                if (!itemListValues[i].getAttribute("Ruleid").toString().equals(adfcontext.getPageFlowScope().get("itemListRuleID"))) {
                                                    save = false;
                                                    ADFContext.getCurrent().getViewScope().put("SaveDeptRule", save);                                                   
                                                    break;
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        }                        
                        if (save) {                            
                            OperationBinding ob = BindingContext.getCurrent().getCurrentBindingsEntry().getOperationBinding("addRoleDefs");
                            ob.getParamsMap().put("list", candidateRule.getArrlist());
                            ob.getParamsMap().put("listDept", candidateRule.getSelectedlist());
                            ob.getParamsMap().put("userName",
                                                  userSession.getAttribute("userName"));
                            if (ob != null)
                                ob.execute();                           
                        }

                    }
                }
            }
        } catch (ArrayIndexOutOfBoundsException aiobe) {
            aiobe.printStackTrace();

        } catch (NullPointerException ne) {
            ne.printStackTrace();
        } catch (ViewObjectNotPreparedException vnpe) {
            vnpe.printStackTrace();
        } catch (JboSQLException jse) {
            jse.printStackTrace();
        } catch (JboException je) {
            je.printStackTrace();
            JboException ex = new JboException("");
            BindingContext bctx = BindingContext.getCurrent();
            ((DCBindingContainer)bctx.getCurrentBindingsEntry()).reportException(ex);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.runFinalization();
        RPMlogger.log(Level.INFO, "saveNewRule() Ends");
    }

    private String getValueFrmExpression(String data) {
        FacesContext fc = FacesContext.getCurrentInstance();
        Application app = fc.getApplication();
        ExpressionFactory elFactory = app.getExpressionFactory();
        ELContext elContext = fc.getELContext();
        ValueExpression valueExp = elFactory.createValueExpression(elContext, data, Object.class);
        String Message = null;
        Object obj = valueExp.getValue(elContext);
        if (obj != null) {
            Message = obj.toString();
        }
        return Message;
    }

    public String saveNewRule() {
        RPMlogger.info("new rule");
        RPMlogger.log(Level.INFO, "saveNewRule() starts");
        try {
            CandidateRuleMB candidateRule = (CandidateRuleMB)ADFContext.getCurrent().getPageFlowScope().get("CandidateRuleMB");
            ExternalContext ectx = FacesContext.getCurrentInstance().getExternalContext();
            HttpSession userSession = (HttpSession)ectx.getSession(true);                      
            oracle.jbo.domain.Date effectDate = (Date)ADFContext.getCurrent().getViewScope().get("effectiveDate");
            oracle.jbo.domain.Date lastDate = (Date)ADFContext.getCurrent().getViewScope().get("endDate");            
            
            if(candidateRule.getBind_Value().getValue()==null) {                
                candidateRule.getBind_Value().setValue("");
            }           
            if (userSession.getAttribute("roleId").equals("90")) {
                if ((ADFContext.getCurrent().getViewScope().get("ruleName") == null || ADFContext.getCurrent().getViewScope().get("ruleName").toString().equals("")) || ((candidateRule.getBind_ZoneID().getValue() == null) || 
                        (candidateRule.getBind_RuleType().getValue() == null) || (ADFContext.getCurrent().getViewScope().get("rulePriority") == null || ADFContext.getCurrent().getViewScope().get("rulePriority").toString().equals("")) || 
                        (candidateRule.getCustomlist().isEmpty()) || ((Integer)candidateRule.getBind_ZoneID().getValue() == 0))) { //(candidateRule.getBind_Description().getValue()==null || candidateRule.getBind_Description().getValue().equals(""))
                    return null;
                }
                if((Integer)candidateRule.getBind_filedname().getValue() != 0 && (Integer)candidateRule.getBind_operator().getValue() != 0 && 
                   (!candidateRule.getBind_Value().getValue().equals("") || (Integer)candidateRule.getBind_parameter().getValue() != 0 || 
                    candidateRule.getBind_dateValue().getValue()!=null)){
                   FacesMessageUtils.addErrorMessage("Please add the criterias in Rule Definition.", FacesMessage.SEVERITY_ERROR);
                   return null; 
                }
               
                if((Integer)candidateRule.getBind_filedname().getValue() != 0 || (Integer)candidateRule.getBind_operator().getValue() != 0 || (Integer)candidateRule.getBind_parameter().getValue() != 0){                   
                    FacesMessageUtils.addErrorMessage("The criteria fields are incomplete. Please create proper criteria or clear the criteria fields",
                                                     FacesMessage.SEVERITY_ERROR);                                      
                   
                   return null; 
                } 
                if((Integer)candidateRule.getBind_filedname().getValue() != 0 || (Integer)candidateRule.getBind_operator().getValue() != 0 || !candidateRule.getBind_Value().getValue().equals("")){                   
                    FacesMessageUtils.addErrorMessage("The criteria fields are incomplete. Please create proper criteria or clear the criteria fields",
                                                     FacesMessage.SEVERITY_ERROR);                                      
                   
                   return null; 
                }
                if((Integer)candidateRule.getBind_filedname().getValue() != 0 || (Integer)candidateRule.getBind_operator().getValue() != 0 || candidateRule.getBind_dateValue().getValue()!=null){                   
                    FacesMessageUtils.addErrorMessage("The criteria fields are incomplete. Please create proper criteria or clear the criteria fields",
                                                     FacesMessage.SEVERITY_ERROR);                                      
                   
                   return null; 
                }
                if (ADFContext.getCurrent().getViewScope().get("SaveCorpRule") != null) {
                    if (((Boolean)ADFContext.getCurrent().getViewScope().get("SaveCorpRule")) == false) {
                        FacesMessageUtils.addErrorMessage("Corporate Rule already exists.",
                                                          FacesMessage.SEVERITY_ERROR);
                        return null;
                    }
                }
            }
            if (userSession.getAttribute("roleId").equals("80")) {
                if ((candidateRule.getBind_ZoneID().getValue() == null) || (candidateRule.getBind_Department().getValue() == null) || (candidateRule.getSelectedValues().size() == 0) || ((Integer)candidateRule.getBind_MerchantField().getValue() == 0) || 
                    ((Integer)candidateRule.getBind_MerchantOperator().getValue() == 0) || (candidateRule.getBind_EffectiveDate().getValue() == null) || 
                    candidateRule.getBind_EndDate().getValue() == null || (Integer)candidateRule.getBind_ZoneID().getValue() == 0 || candidateRule.getIsItemListValueFlag() == false) {
                    return null;
                }
                if (lastDate.dateValue().compareTo(effectDate.dateValue()) < 0) {
                    return null;
                }
                //Date currentDate = new Date(Date.getCurrentDate());
                DCBindingContainer dcbc = (DCBindingContainer)BindingContext.getCurrent().getCurrentBindingsEntry();
                DCIteratorBinding dcib = dcbc.findIteratorBinding("V_Date_View1Iterator");
                ViewObject viewObj = dcib.getViewObject();
                Row dateRow = viewObj.first();
                Date currentDate = (Date)dateRow.getAttribute("Vdate");

                if (effectDate.dateValue().compareTo(currentDate.dateValue()) < 0) {
                    return null;
                }
                if (lastDate.dateValue().compareTo(currentDate.dateValue()) < 0) {
                    return null;
                }
                if (ADFContext.getCurrent().getViewScope().get("SaveDeptRule") != null) {
                    if (((Boolean)ADFContext.getCurrent().getViewScope().get("SaveDeptRule")) == false) {
                        FacesMessageUtils.addErrorMessage("Item List Rule already exists.",
                                                          FacesMessage.SEVERITY_ERROR);
                        return null;
                    }
                }
            }
            adfcontext.getPageFlowScope().put("itemListRuleID", null);
            adfcontext.getPageFlowScope().put("corporateRuleID", null);
            adfcontext.getPageFlowScope().put("zoneID", null);
            ADFContext.getCurrent().getPageFlowScope().put("activateDeleteCriteria", null);      
            ADFContext.getCurrent().getPageFlowScope().put("createAdd", false);             
            setValueToEL("#{bindings.Column_Name_View1.inputValue}", 0);
            setValueToEL("#{bindings.Operator_View1.inputValue}", 0);
            setValueToEL("#{bindings.Paramater_Id_View1.inputValue}", 0);            
            ADFContext.getCurrent().getPageFlowScope().put("CandidateRuleMB", null);
        } catch (ArrayIndexOutOfBoundsException aiobe) {
            aiobe.printStackTrace();
        } catch (NullPointerException ne) {
            ne.printStackTrace();
        } catch (ViewObjectNotPreparedException vnpe) {
            vnpe.printStackTrace();
        } catch (JboSQLException jse) {
            jse.printStackTrace();
        } catch (JboException je) {
            je.printStackTrace();
            JboException ex = new JboException("");
            BindingContext bctx = BindingContext.getCurrent();
            ((DCBindingContainer)bctx.getCurrentBindingsEntry()).reportException(ex);
        }
        RPMlogger.log(Level.INFO, "saveNewRule() ends");
        return "Save";
    }

    public void columnNameVCL(ValueChangeEvent valueChangeEvent) {
        RPMlogger.info("Fetching the Field name from the Rule_Criteria_field table");
        RPMlogger.log(Level.INFO, "columnNameVCL() starts");
        try {
            CandidateRuleMB candidateRule = (CandidateRuleMB)ADFContext.getCurrent().getPageFlowScope().get("CandidateRuleMB");
            if(null != valueChangeEvent.getNewValue()){
            int indexValue = Integer.parseInt(valueChangeEvent.getNewValue() + "");          
            if (indexValue > 0) {                                
                DCBindingContainer dcBinding = (DCBindingContainer)BindingContext.getCurrent().getCurrentBindingsEntry();
                DCIteratorBinding dciBinding = dcBinding.findIteratorBinding("Column_Name_View1Iterator");
                ViewObject vo = dciBinding.getViewObject();
                Row r = vo.getRowAtRangeIndex(indexValue);
                if (r.getAttribute("ColumnName") != null)
                {                                
                    // Value && Date fields visible condition
                    if(r.getAttribute("ColumnName").toString().endsWith("_DATE"))
                    {          
                        candidateRule.getBind_Value().resetValue();
                        candidateRule.getBind_Value().setValue(null);
                        candidateRule.getBind_Value().setVisible(false);
                        AdfFacesContext.getCurrentInstance().addPartialTarget(candidateRule.getBind_Value());
                        candidateRule.getBind_pCheckbox().setDisabled(true);
                        AdfFacesContext.getCurrentInstance().addPartialTarget(candidateRule.getBind_pCheckbox());
                        candidateRule.getBind_parameter().setVisible(false);
                        AdfFacesContext.getCurrentInstance().addPartialTarget(candidateRule.getBind_parameter());                        
                        candidateRule.getBind_dateValue().setVisible(true); 
                        candidateRule.getBind_dateValue().resetValue();
                        candidateRule.getBind_dateValue().setValue(null);
                        AdfFacesContext.getCurrentInstance().addPartialTarget(candidateRule.getBind_dateValue());
                    }
                    else
                    {
                        candidateRule.getBind_Value().setVisible(true);   
                        AdfFacesContext.getCurrentInstance().addPartialTarget(candidateRule.getBind_Value());
                        candidateRule.getBind_pCheckbox().setDisabled(false);
                        candidateRule.getBind_pCheckbox().setSelected(false);
                        AdfFacesContext.getCurrentInstance().addPartialTarget(candidateRule.getBind_pCheckbox());
                        candidateRule.getBind_dateValue().setVisible(false); 
                        AdfFacesContext.getCurrentInstance().addPartialTarget(candidateRule.getBind_dateValue());                        
                        candidateRule.getBind_parameter().resetValue();
                        candidateRule.getBind_parameter().setValue(0);
                        candidateRule.getBind_parameter().setVisible(false);
                        AdfFacesContext.getCurrentInstance().addPartialTarget(candidateRule.getBind_parameter());                        
                    }
                candidateRule.setSelectedfileName(r.getAttribute("ColumnName").toString());
                candidateRule.setSelectedCriteriaId(r.getAttribute("CriteriaId").toString());                
                System.runFinalization();
            }
            }
        }
        }catch (NullPointerException ne) {
            ne.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        RPMlogger.log(Level.INFO, "columnNameVCL() Ends");
    }

    public void columnNameVCLMerchant(ValueChangeEvent valueChangeEvent) {
        RPMlogger.info("Fetching the Field name fro merchant user from the Rule_Criteria_field table");
        RPMlogger.log(Level.INFO, "columnNameVCLMerchant() starts");
        try {
            int indexValue = Integer.parseInt(valueChangeEvent.getNewValue() + "");
            if (null != valueChangeEvent && null != valueChangeEvent.getNewValue().toString() && indexValue > 0) {
                CandidateRuleMB candidateRule = (CandidateRuleMB)ADFContext.getCurrent().getPageFlowScope().get("CandidateRuleMB");               
                DCBindingContainer dcBinding = (DCBindingContainer)BindingContext.getCurrent().getCurrentBindingsEntry();
                DCIteratorBinding dciBinding = dcBinding.findIteratorBinding("Column_Name_View_Merchant1Iterator");
                ViewObject vo = dciBinding.getViewObject();
                Row r = vo.getRowAtRangeIndex(indexValue);
                if (r.getAttribute("ColumnName") != null) {
                    candidateRule.setSelectedfileName(r.getAttribute("ColumnName").toString());
                    candidateRule.setSelectedCriteriaId(r.getAttribute("CriteriaId").toString());
                }
                System.runFinalization();
            }
        } catch (NullPointerException ne) {
            ne.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        RPMlogger.log(Level.INFO, "columnNameVCLMerchant() Ends");

    }

    public void operatorVCLMerchant(ValueChangeEvent valueChangeEvent) {
        RPMlogger.info("Fetching the Operator merchant user from the Operator View its a Query based view");
        RPMlogger.log(Level.INFO, "operatorVCLMerchant() starts");
        try {
            if(null != valueChangeEvent.getNewValue()){
            int indexValue = Integer.parseInt(valueChangeEvent.getNewValue() + "");
            if (indexValue > 0) {
                CandidateRuleMB candidateRule = (CandidateRuleMB)ADFContext.getCurrent().getPageFlowScope().get("CandidateRuleMB");
                DCBindingContainer dcBinding = (DCBindingContainer)BindingContext.getCurrent().getCurrentBindingsEntry();
                DCIteratorBinding dciBinding = dcBinding.findIteratorBinding("Operator_View_Merchant1Iterator");
                ViewObject vo = dciBinding.getViewObject();
                Row r = vo.getRowAtRangeIndex(indexValue);
                if (r.getAttribute("OperatorName") != null)
                    candidateRule.setSelectedOperator(r.getAttribute("OperatorName").toString());

                System.runFinalization();
            }
            }
        } catch (NullPointerException ne) {
            ne.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        RPMlogger.log(Level.INFO, "operatorVCLMerchant() End");

    }    

    public void ParamNameVCL(ValueChangeEvent valueChangeEvent) {
        RPMlogger.info("Fetching the Parameter name from the rule parameter header table");
        RPMlogger.log(Level.INFO, "ParamNameVCL() starts");
        try {
            CandidateRuleMB candidateRule = (CandidateRuleMB)ADFContext.getCurrent().getPageFlowScope().get("CandidateRuleMB");
            if(null != valueChangeEvent.getNewValue()){
            int indexValue = Integer.parseInt(valueChangeEvent.getNewValue() + "");          
            if (indexValue > 0) {                               
                DCBindingContainer dcBinding = (DCBindingContainer)BindingContext.getCurrent().getCurrentBindingsEntry();
                DCIteratorBinding dciBinding = dcBinding.findIteratorBinding("Paramater_Id_View1Iterator");
                ViewObject vo = dciBinding.getViewObject();
                Row r = vo.getRowAtRangeIndex(indexValue);
                if (r.getAttribute("ParamName") != null) {
                    candidateRule.setSelectedParametername(r.getAttribute("ParamName").toString());
                    candidateRule.setParamID(r.getAttribute("ParamId").toString());
                }
                System.runFinalization();
            }
            }
        } catch (NullPointerException ne) {
            ne.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        RPMlogger.log(Level.INFO, "ParamNameVCL() End");

    }

    public void operatorVCL(ValueChangeEvent valueChangeEvent) {
        RPMlogger.info("Fetching the Operator name from the rule operator Query based view");
        RPMlogger.log(Level.INFO, "operatorVCL() starts");
        try {
            CandidateRuleMB candidateRule = (CandidateRuleMB)ADFContext.getCurrent().getPageFlowScope().get("CandidateRuleMB");
            if( null != valueChangeEvent.getNewValue()){
            int indexValue = Integer.parseInt(valueChangeEvent.getNewValue() + "");           
            if (indexValue > 0) {                
                DCBindingContainer dcBinding = (DCBindingContainer)BindingContext.getCurrent().getCurrentBindingsEntry();
                DCIteratorBinding dciBinding = dcBinding.findIteratorBinding("Operator_View1Iterator");
                ViewObject vo = dciBinding.getViewObject();
                Row r = vo.getRowAtRangeIndex(indexValue);
                if (r.getAttribute("OperatorName") != null)
                    candidateRule.setSelectedOperator(r.getAttribute("OperatorName").toString());

                System.runFinalization();
            }
            }
        } catch (NullPointerException ne) {
            ne.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        RPMlogger.log(Level.INFO, "operatorVCL() starts");
    }

    public void parametercheckBoxVCL(ValueChangeEvent valueChangeEvent) {

        RPMlogger.info("Parameter and value diable functionality ");
        RPMlogger.log(Level.INFO, "parametercheckBoxVCL() starts");
        try {
            CandidateRuleMB candidateRule = (CandidateRuleMB)ADFContext.getCurrent().getPageFlowScope().get("CandidateRuleMB");                        
            Boolean checkstatus = candidateRule.getBind_pCheckbox().isSelected();
            if (checkstatus == true) {
                candidateRule.getBind_Value().setVisible(false);
                candidateRule.getBind_Value().setValue(null);                                
                candidateRule.setValue(null);
                candidateRule.setDateValue(null);
                candidateRule.getBind_dateValue().setVisible(false);
                candidateRule.getBind_parameter().setVisible(true);
                candidateRule.getBind_parameter().resetValue();                
            } else if(checkstatus == false){
                if(candidateRule.getBind_dateValue().isVisible()){
                    candidateRule.getBind_Value().setVisible(false);
                    candidateRule.getBind_parameter().setVisible(false);
                    candidateRule.getBind_parameter().setValue(null);
                    candidateRule.setSelectedParametername(null);
                }
                else{
                    candidateRule.getBind_Value().setVisible(true);
                    candidateRule.getBind_parameter().setVisible(false);
                    candidateRule.getBind_parameter().setValue(0);
                    candidateRule.getBind_parameter().resetValue();
                    candidateRule.setSelectedParametername(null);
                }
                DCBindingContainer dcBinding = (DCBindingContainer)BindingContext.getCurrent().getCurrentBindingsEntry();
                DCIteratorBinding dciBinding = dcBinding.findIteratorBinding("Paramater_Id_View1Iterator");
                ViewObject vo = dciBinding.getViewObject();
                vo.executeQuery();
            }
            System.runFinalization();
        } catch (NullPointerException ne) {
            ne.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        RPMlogger.log(Level.INFO, "parametercheckBoxVCL() End");
    }


    public void addCriteria(ActionEvent actionEvent) {
        RPMlogger.info("Addingh the rule in the list box functionaliry");
        RPMlogger.log(Level.INFO, "addCriteria() starts");
        try {                        
            CandidateRuleMB candidateRule = (CandidateRuleMB)ADFContext.getCurrent().getPageFlowScope().get("CandidateRuleMB");
            String value = "null";            
            if(candidateRule.getBind_dateValue().isVisible())            
               candidateRule.getBind_Value().setVisible(false);
            else if(candidateRule.getBind_Value().isVisible())    
                candidateRule.getBind_dateValue().setVisible(false);
            
            if(candidateRule.getBind_Value().getValue()==null)                 
                candidateRule.getBind_Value().setValue("");
                        
            String parameter = "null";
            String paramID = "null";                        
            Boolean criteriaAddable = true;
              if (!candidateRule.getArrlist().isEmpty()) 
              {                  
                  if(ADFContext.getCurrent().getPageFlowScope().get("createAdd")!=null){
                  if((Boolean)ADFContext.getCurrent().getPageFlowScope().get("createAdd")==true){
                      for (int i = 0; i < candidateRule.getArrlist().size(); i++) {
                          String[] ruleFields = candidateRule.getArrlist().get(i).split(" ");                          
                          if (ruleFields[1].equals(candidateRule.getSelectedfileName()) && ruleFields[2].equals(candidateRule.getSelectedOperator())) {
                              FacesMessageUtils.addErrorMessage("Criteria already exists",
                                                                FacesMessage.SEVERITY_ERROR);
                              criteriaAddable = false;
                              RuleCriteriaFields(candidateRule);
                              break;
                          }
                      }
                  }
                  }
                  if(ADFContext.getCurrent().getViewScope().get("updateAdd")!=null){
                  if((Boolean)ADFContext.getCurrent().getViewScope().get("updateAdd")==true){                      
                      
                          String ruleCriterias = "";
                          if((candidateRule.getSelectedfileName() != null) && ((Integer)candidateRule.getBind_operator().getValue() != 0) && 
                                (!candidateRule.getBind_Value().getValue().equals("") || candidateRule.getBind_dateValue().getValue() != null || (Integer)candidateRule.getBind_parameter().getValue() != 0)){
                          if (candidateRule.getListboxbinding().getLocalValue() != null) {
                              ruleCriterias = candidateRule.getListboxbinding().getLocalValue().toString();
                              candidateRule.getCustomlist().clear();
                              candidateRule.getCustomarrlist().clear();
                              candidateRule.getArrlist().remove(candidateRule.getSelectIndex());
                              candidateRule.getTempArrlist().remove(candidateRule.getSelectIndex());                              
                              for (String sr : candidateRule.getTempArrlist()) {
                                  String[] st = sr.split(" ");
                                  String temps = "";
								  if(st[3].contains("-")){
									SimpleDateFormat format1 = new SimpleDateFormat("dd-MMM-yyyy");
									SimpleDateFormat format2 = new SimpleDateFormat("MM/dd/yyyy");
									java.util.Date utilDate;
									java.sql.Date javaSqlDate;

									try {
										utilDate = format1.parse(st[3]);
										String formattedDate=format2.format(utilDate);
										st[3]=formattedDate;
									}
									catch (ParseException f) {
									f.printStackTrace();
									}
								}

                                  for (int i = 1; i < st.length; i++) {                                      
                                      if (!st[i].equals("null"))
                                          temps += st[i];
                                      temps += " ";
                                  }
                                  candidateRule.getCustomlist().add(new SelectItem(temps));
                                  candidateRule.getCustomarrlist().add(temps);
                              }
                          }
                          }
                          if(candidateRule.getCustomlist().isEmpty())
                            ADFContext.getCurrent().getPageFlowScope().put("activateDeleteCriteria", true);
                          else
                            ADFContext.getCurrent().getPageFlowScope().put("activateDeleteCriteria", false);
                      
                  }
                  }
              }                        
            
            if (candidateRule.getBind_Value().isVisible() && criteriaAddable)
            {
                if ((candidateRule.getSelectedfileName() == null) || ((Integer)candidateRule.getBind_operator().getValue() == 0) || candidateRule.getBind_Value().getValue().equals(""))
                {
                    FacesMessageUtils.addErrorMessage("Please enter all the fields of Select Criteria",
                                                      FacesMessage.SEVERITY_ERROR);
                    candidateRule.getBind_filedname().setValue(candidateRule.getBind_filedname().getValue());
                    candidateRule.getBind_operator().setValue(candidateRule.getBind_operator().getValue());
                }
                
            }
            if (candidateRule.getBind_dateValue().isVisible() && criteriaAddable)
            {
                if ((candidateRule.getSelectedfileName() == null) || ((Integer)candidateRule.getBind_operator().getValue() == 0) || (candidateRule.getBind_dateValue().getValue() == null))
                {
                    FacesMessageUtils.addErrorMessage("Please enter all the fields of Select Criteria",
                                                      FacesMessage.SEVERITY_ERROR);
                    candidateRule.getBind_filedname().setValue(candidateRule.getBind_filedname().getValue());
                    candidateRule.getBind_operator().setValue(candidateRule.getBind_operator().getValue());                
                }
            }
            if (candidateRule.getBind_parameter().isVisible() && criteriaAddable) {
                if ((candidateRule.getSelectedfileName() == null) || ((Integer)candidateRule.getBind_operator().getValue() == 0) || ((Integer)candidateRule.getBind_parameter().getValue() == 0)) {
                    FacesMessageUtils.addErrorMessage("Please enter all the fields of Select Criteria",
                                                      FacesMessage.SEVERITY_ERROR);
                    candidateRule.getBind_filedname().setValue(candidateRule.getBind_filedname().getValue());
                    candidateRule.getBind_operator().setValue(candidateRule.getBind_operator().getValue());
                    candidateRule.getBind_parameter().setValue(candidateRule.getBind_parameter().getValue());                    
                }
            }
            if(candidateRule.getValue()!=null){
                if (!candidateRule.getValue().equals("") && candidateRule.getValue().length() >= 0) 
                {                
                    value = candidateRule.getValue();
                    //setCorporateValue(value);
                }
                else if(candidateRule.getDateValue()!=null){
                    String seletedDate=   candidateRule.getBind_dateValue().getValue().toString();                
                    value = seletedDate;                   
                } 
                else if (candidateRule.getSelectedParametername() != null && candidateRule.getSelectedParametername().length() >= 0) {
                    parameter = candidateRule.getSelectedParametername();
                    paramID = candidateRule.getParamID();
                }
            }
            else if(candidateRule.getDateValue()!=null){
                String seletedDate=   candidateRule.getBind_dateValue().getValue().toString();                
                   value = seletedDate;
                //setCorporateValue(value); 
            }            
            else if (candidateRule.getSelectedParametername() != null && candidateRule.getSelectedParametername().length() >= 0) {
                parameter = candidateRule.getSelectedParametername();
                paramID = candidateRule.getParamID();
            }

            if (criteriaAddable) {                
                StringBuffer tempRuleCriteria = new StringBuffer();                
                if ((candidateRule.getSelectedfileName() != null) && ((Integer)candidateRule.getBind_operator().getValue() != 0)) 
                {                                    
                    if ((!candidateRule.getBind_Value().getValue().equals("")) || ((Integer)candidateRule.getBind_parameter().getValue() != 0) || candidateRule.getBind_dateValue().getValue() != null ) 
                    {                        
                        candidateRule.getSb1().append(candidateRule.getSelectedCriteriaId().trim()).append(" ").append(candidateRule.getSelectedfileName().trim()).append(" ").append(candidateRule.getSelectedOperator().trim()).append(" ").append(value).append(" ").append(paramID);
                        candidateRule.setSt(candidateRule.getSb1().toString());
                        candidateRule.getArrlist().add(candidateRule.getSt());
                        candidateRule.getTempArrlist().add((tempRuleCriteria.append(candidateRule.getSelectedCriteriaId().trim()).append(" ").append(candidateRule.getSelectedfileName().trim()).append(" ").append(candidateRule.getSelectedOperator().trim()).append(" ").append(value).append(" ").append(parameter)).toString());
                        if (!value.equals("null") && parameter.equals("null"))
                        {
                            candidateRule.getStb().append(candidateRule.getSelectedfileName().trim()).append(" ").append(candidateRule.getSelectedOperator().trim()).append(" ").append(value);
                            candidateRule.setSt1(candidateRule.getStb().toString());
                            candidateRule.getCustomlist().add(new SelectItem(candidateRule.getSt1()));
                            candidateRule.getCustomarrlist().add(candidateRule.getSt1());
                        }
                        if (value.equals("null") && !parameter.equals("null")) 
                        {
                            candidateRule.getStb().append(candidateRule.getSelectedfileName().trim()).append(" ").append(candidateRule.getSelectedOperator().trim()).append(" ").append(parameter);
                            candidateRule.setSt1(candidateRule.getStb().toString());
                            candidateRule.getCustomlist().add(new SelectItem(candidateRule.getSt1()));
                            candidateRule.getCustomarrlist().add(candidateRule.getSt1());
                        }
                        RuleCriteriaFields(candidateRule);
                        if(!candidateRule.getCustomlist().isEmpty())
                            ADFContext.getCurrent().getPageFlowScope().put("activateDeleteCriteria", false);
                        else
                            ADFContext.getCurrent().getPageFlowScope().put("activateDeleteCriteria", true);
                    }                   
                }
                
                OperationBinding oBinding = BindingContext.getCurrent().getCurrentBindingsEntry().getOperationBinding("queryExecution");
                if (oBinding != null)
                    oBinding.execute();
                
                candidateRule.getSb1().append("");
                candidateRule.getSb1().delete(0, candidateRule.getSb1().length());
                candidateRule.getStb().delete(0, candidateRule.getStb().length());
                tempRuleCriteria.delete(0, tempRuleCriteria.length());
            }            
            System.runFinalization();
        } catch (ArrayIndexOutOfBoundsException aiobe) {
            aiobe.printStackTrace();

        } catch (NullPointerException ne) {
            ne.printStackTrace();
        } catch (ViewObjectNotPreparedException vnpe) {
            vnpe.printStackTrace();
        } catch (JboSQLException jse) {
            jse.printStackTrace();
        } catch (JboException je) {
            je.printStackTrace();
            JboException ex = new JboException("");
            BindingContext bctx = BindingContext.getCurrent();
            ((DCBindingContainer)bctx.getCurrentBindingsEntry()).reportException(ex);
        }
        RPMlogger.log(Level.INFO, "addCriteria() End");

    }

    private void RuleCriteriaFields(CandidateRuleMB candidateRule) {

        try {
            candidateRule.getBind_pCheckbox().resetValue();
            candidateRule.getBind_pCheckbox().setSelected(false);
            AdfFacesContext.getCurrentInstance().addPartialTarget(candidateRule.getBind_pCheckbox());
            candidateRule.getBind_filedname().resetValue();
            candidateRule.getBind_filedname().setValue(0);
            AdfFacesContext.getCurrentInstance().addPartialTarget(candidateRule.getBind_filedname());
            candidateRule.getBind_operator().resetValue();
            candidateRule.getBind_operator().setValue(0);
            AdfFacesContext.getCurrentInstance().addPartialTarget(candidateRule.getBind_operator());
            candidateRule.setSelectedParametername(null);
            candidateRule.getBind_parameter().setValue(0);
            candidateRule.getBind_parameter().setVisible(false);
            candidateRule.getBind_parameter().resetValue();            
            AdfFacesContext.getCurrentInstance().addPartialTarget(candidateRule.getBind_parameter());
            candidateRule.getBind_Value().setVisible(true);
            candidateRule.getBind_Value().resetValue();
            candidateRule.getBind_Value().setValue("");
            AdfFacesContext.getCurrentInstance().addPartialTarget(candidateRule.getBind_Value());
            candidateRule.getBind_dateValue().setVisible(false);
            candidateRule.getBind_dateValue().resetValue();
            candidateRule.getBind_dateValue().setValue(null);
            ADFContext.getCurrent().getViewScope().put("updateAdd", false);                        
            ADFContext.getCurrent().getPageFlowScope().put("createAdd", true);            
            AdfFacesContext.getCurrentInstance().addPartialTarget(candidateRule.getBind_dateValue());
            candidateRule.getListboxbinding().setAutoSubmit(true);
            AdfFacesContext.getCurrentInstance().addPartialTarget(candidateRule.getListboxbinding());

        } catch (PropertyNotFoundException pnfe) {
            pnfe.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addCriteriaMerchant() {
        RPMlogger.info("Merchant user add criteria");
        RPMlogger.log(Level.INFO, "addCriteriaMerchant() starts");
        try {
            CandidateRuleMB candidateRule = (CandidateRuleMB)ADFContext.getCurrent().getPageFlowScope().get("CandidateRuleMB");
            ExternalContext ectx = FacesContext.getCurrentInstance().getExternalContext();
            HttpSession userSession = (HttpSession)ectx.getSession(true);
            String value = "null";
            String parameter = "null";            
            if (candidateRule.getValue() != null && candidateRule.getValue().length() >= 0) {
                String selected = candidateRule.getValue();
                String[] s = selected.trim().split("-");
                String seletedskulistValue = s[0].toString().trim();
                value = seletedskulistValue;
                candidateRule.setValue(value);
                candidateRule.setItemListListenerValue(value);                
            }

            if (candidateRule.getSelectedParametername() != null && candidateRule.getSelectedParametername().length() >= 0)
                parameter = candidateRule.getSelectedParametername();
            if (userSession.getAttribute("roleId").equals("80")) {
                setValueToEL("#{bindings.Column_Name_View_Merchant1.inputValue}",
                             adfcontext.getPageFlowScope().get("editFieldName"));
                setValueToEL("#{bindings.Operator_View_Merchant1.inputValue}",
                             adfcontext.getPageFlowScope().get("editOperations"));
            }
            if (((Integer)candidateRule.getBind_MerchantField().getValue() != 0) && ((Integer)candidateRule.getBind_MerchantOperator().getValue() != 0)) {
                if ((candidateRule.getBind_MerchantValue().getValue() != null)) {                    
                    candidateRule.setSelectedCriteriaId("16");
                    candidateRule.getSb1().append(candidateRule.getSelectedCriteriaId().trim()).append(" ").append(candidateRule.getSelectedfileName().trim()).append(" ").append(candidateRule.getSelectedOperator().trim()).append(" ").append(value).append(" ").append(parameter);
                    candidateRule.setSt(candidateRule.getSb1().toString());
                    candidateRule.getArrlist().add(candidateRule.getSt());
                    if (!value.equals("null")) {
                        candidateRule.getStb().append(candidateRule.getSelectedfileName().trim()).append(" ").append(candidateRule.getSelectedOperator().trim()).append(" ").append(value);
                        candidateRule.setSt1(candidateRule.getStb().toString());
                        candidateRule.getCustomlist().add(new SelectItem(candidateRule.getSt1()));
                        candidateRule.getCustomarrlist().add(candidateRule.getSt1());
                    }
                    if (value.equals("null")) {
                        candidateRule.getStb().append(candidateRule.getSelectedfileName().trim()).append(" ").append(candidateRule.getSelectedOperator().trim()).append(" ").append(parameter);
                        candidateRule.setSt1(candidateRule.getStb().toString());
                        candidateRule.getCustomlist().add(new SelectItem(candidateRule.getSt1()));
                        candidateRule.getCustomarrlist().add(candidateRule.getSt1());
                    }
                }
            }
            OperationBinding oBinding = BindingContext.getCurrent().getCurrentBindingsEntry().getOperationBinding("queryExecution");
            if (oBinding != null)
                oBinding.execute();

            candidateRule.getSb1().append("");
            candidateRule.getSb1().delete(0, candidateRule.getSb1().length());
            candidateRule.getStb().delete(0, candidateRule.getStb().length());
            System.runFinalization();
        }

        catch (NullPointerException ne) {
            ne.printStackTrace();
        }
        catch (JboException je) {
            je.printStackTrace();
            JboException ex = new JboException("");
            BindingContext bctx = BindingContext.getCurrent();
            ((DCBindingContainer)bctx.getCurrentBindingsEntry()).reportException(ex);
        } catch (PropertyNotFoundException pnfe) {
            pnfe.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        RPMlogger.log(Level.INFO, "addCriteriaMerchant() End");

    }

    public void deleteCriteria(ActionEvent actionEvent) {
        RPMlogger.info("Deleting the criteria in the list box ");
        RPMlogger.log(Level.INFO, "deleteCriteria() starts");

        try {
            CandidateRuleMB candidateRule = (CandidateRuleMB)ADFContext.getCurrent().getPageFlowScope().get("CandidateRuleMB");
            if (actionEvent != null) {
                String ruleCriterias = "";
                if (candidateRule.getListboxbinding().getLocalValue() != null) {
                    ruleCriterias = candidateRule.getListboxbinding().getLocalValue().toString();
                    candidateRule.getCustomlist().clear();
                    candidateRule.getCustomarrlist().clear();
                    candidateRule.getArrlist().remove(candidateRule.getSelectIndex());
                    candidateRule.getTempArrlist().remove(candidateRule.getSelectIndex());                    
                    for (String sr : candidateRule.getTempArrlist()) {
                        String[] st = sr.split(" ");
                        String temps = "";
                   if(st[3].contains("-")){
                    SimpleDateFormat format1 = new SimpleDateFormat("dd-MMM-yyyy");
                    SimpleDateFormat format2 = new SimpleDateFormat("MM/dd/yyyy");
                    java.util.Date utilDate;
                    java.sql.Date javaSqlDate;

                    try {
                        utilDate = format1.parse(st[3]);
                        String formattedDate=format2.format(utilDate);
                        st[3]=formattedDate;
                    }
                    catch (ParseException f) {
                    f.printStackTrace();
                    }
                }

                        for (int i = 1; i < st.length; i++) {                            
                            if (!st[i].equals("null"))
                                temps += st[i];
                            temps += " ";
                        }
                        candidateRule.getCustomlist().add(new SelectItem(temps));
                        candidateRule.getCustomarrlist().add(temps);
                    }
                } else {
                    FacesMessageUtils.addErrorMessage("Please select one Criteria to delete",
                                                      FacesMessage.SEVERITY_ERROR);
                }
                RuleCriteriaFields(candidateRule);                
                if(candidateRule.getCustomlist().isEmpty())
                    ADFContext.getCurrent().getPageFlowScope().put("activateDeleteCriteria", true);
                else
                    ADFContext.getCurrent().getPageFlowScope().put("activateDeleteCriteria", false);
            }           
            System.runFinalization();
        } catch (ArrayIndexOutOfBoundsException aiobe) {
            aiobe.printStackTrace();

        } catch (NumberFormatException nfe) {
            nfe.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        RPMlogger.log(Level.INFO, "deleteCriteria() End");
    }

    public void listBoxVCL(ValueChangeEvent valueChangeEvent) {

        RPMlogger.info("All criterias avaiable in the listbox");
        RPMlogger.log(Level.INFO, "listBoxVCL() starts");
        try {
            CandidateRuleMB candidateRule = (CandidateRuleMB)ADFContext.getCurrent().getPageFlowScope().get("CandidateRuleMB");                                                            
            if (null != valueChangeEvent.getNewValue()) {
                ADFContext.getCurrent().getViewScope().put("updateAdd", true);            
                ADFContext.getCurrent().getPageFlowScope().put("createAdd", false); 
                candidateRule.getBind_filedname().resetValue();
                candidateRule.getBind_operator().resetValue();                                    
                candidateRule.getBind_Value().resetValue(); 
                candidateRule.getBind_Value().setValue(null);
                candidateRule.getBind_parameter().resetValue();
                candidateRule.getBind_dateValue().resetValue();                
                String str = valueChangeEvent.getNewValue().toString();               
                candidateRule.setSelectIndex(candidateRule.getCustomarrlist().indexOf(str));                                  
                String[] format = str.split(" ");

                // format[0]  - Default Value setting

                DCBindingContainer dcb = (DCBindingContainer)BindingContext.getCurrent().getCurrentBindingsEntry();
                DCIteratorBinding fieldNameIterator = dcb.findIteratorBinding("Column_Name_View1Iterator"); //Column_Name_View1Iterator
                Row[] Fieldrows = fieldNameIterator.getAllRowsInRange();
                int Fieldcount = 1;
                for (int i = 1; i <= Fieldrows.length; i++) {
                    String currentColumnName = Fieldrows[i].getAttribute("ColumnName").toString();
                    String currentColumnCriteriaID = Fieldrows[i].getAttribute("CriteriaId").toString();
                    if (currentColumnName.equals(format[0])) {
                        candidateRule.setSelectedfileName(format[0]);
                        candidateRule.setSelectedCriteriaId(currentColumnCriteriaID);
                        setValueToEL("#{bindings.Column_Name_View1.inputValue}", Fieldcount);                        
                        break;
                    }
                    Fieldcount++;
                }

                // format[1]  - Default Value setting
                DCIteratorBinding operatorIterator = dcb.findIteratorBinding("Operator_View1Iterator"); //Column_Name_View1Iterator
                Row[] operatorrows = operatorIterator.getAllRowsInRange();
                int operatorcount = 1;
                for (int i = 1; i <= operatorrows.length; i++) {
                    String currentOperatorName = operatorrows[i].getAttribute("OperatorName").toString();
                    if (currentOperatorName.equals(format[1])) {
                        candidateRule.setSelectedOperator(format[1]);
                        setValueToEL("#{bindings.Operator_View1.inputValue}", operatorcount);                        
                        break;
                    }
                    operatorcount++;
                }
                if(format.length==3){
                    
                    if (format[2].startsWith("$")) { 
                        populateListBoxValues(candidateRule, format[2], dcb);
                    } 
                    else if(format[2].contains("/")){
                        candidateRule.getBind_pCheckbox().setDisabled(true);                                       
                        candidateRule.getBind_parameter().setVisible(false);                    
                        candidateRule.getBind_Value().setVisible(false);                    
                        candidateRule.getBind_dateValue().setVisible(true);
                        candidateRule.setDateValue(format[2]);                    
                    }
                    else {
                        candidateRule.getBind_pCheckbox().setDisabled(false);
                        candidateRule.getBind_pCheckbox().resetValue();
                        candidateRule.getBind_parameter().setVisible(false);                    
                        candidateRule.getBind_Value().setVisible(true);                    
                        candidateRule.setValue(format[2]);
                        candidateRule.getBind_Value().setValue(format[2]);                    
                        candidateRule.getBind_dateValue().setVisible(false);                    
                    }
                }
                else if(format.length==4){
                    if (format[3].startsWith("$")) { 
                        populateListBoxValues(candidateRule, format[3], dcb);
                    }
                }
            }
        }        
        catch (Exception e) {
            e.printStackTrace();
        }
        System.runFinalization();
        RPMlogger.log(Level.INFO, "listBoxVCL() End");
    }
    
    private void populateListBoxValues(CandidateRuleMB candidateRule, String format, DCBindingContainer dcb){
        candidateRule.getBind_pCheckbox().resetValue();
        candidateRule.getBind_pCheckbox().setDisabled(false);
        candidateRule.getBind_pCheckbox().setSelected(true);
        candidateRule.getBind_pCheckbox().setAutoSubmit(true);                    
        candidateRule.getBind_Value().setVisible(false);                                         
        candidateRule.getBind_parameter().setVisible(true);                       
        candidateRule.getBind_dateValue().setVisible(false);                                        
        DCIteratorBinding parameterIterator = dcb.findIteratorBinding("Paramater_Id_View1Iterator"); //Column_Name_View1Iterator
        Row[] parameterrows = parameterIterator.getAllRowsInRange();
        int parametercount = 1;
        for (int i = 1; i <= parameterrows.length; i++) {
            String currentParameterName = parameterrows[i].getAttribute("ParamName").toString();
        
            if (currentParameterName.equals(format)) {
                candidateRule.setSelectedParametername(format);
                setValueToEL("#{bindings.Paramater_Id_View1.inputValue}", parametercount);                                                    
                break;
            }
            parametercount++;
        }
    }

    public void MarkCheckBoxVCL(ValueChangeEvent valueChangeEvent) {
        RPMlogger.info("In main listing screen deleting the criteria functionality");
        RPMlogger.log(Level.INFO, "MarkCheckBoxVCL() starts");
        try {
            CandidateRuleMB candidateRule = (CandidateRuleMB)ADFContext.getCurrent().getPageFlowScope().get("CandidateRuleMB");
            if (null != valueChangeEvent.getNewValue()) {
                Boolean check = Boolean.parseBoolean(valueChangeEvent.getNewValue() + "");
                if (check == true) {
                    candidateRule.getListingruleidlist().add(candidateRule.getListingruleid());
                    ruleslist.add(candidateRule.getListingruleid());

                } else {
                    candidateRule.getListingruleidlist().remove(candidateRule.getListingruleid());
                    ruleslist.remove(candidateRule.getListingruleid());
                }
                candidateRule.setDeleteupdateCount(candidateRule.getListingruleidlist().size());
            }

            System.runFinalization();
        } catch (Exception e) {
            e.printStackTrace();
        }
        RPMlogger.log(Level.INFO, "MarkCheckBoxVCL() End");
    }

    public void deleteRule(ActionEvent actionEvent) {
        RPMlogger.info("Individual rule criteria deletion in the listbox");
        RPMlogger.log(Level.INFO, "deleteRule() starts");
        try {
            CandidateRuleMB candidateRule = (CandidateRuleMB)ADFContext.getCurrent().getPageFlowScope().get("CandidateRuleMB");
            if (ruleslist == null || ruleslist.size() == 0) {
                FacesMessageUtils.addErrorMessage("Please select Rule(s) to delete",
                                                  FacesMessage.SEVERITY_ERROR);
            } else {
                if (null != actionEvent) {                    
                    if (candidateRule.getListingruleidlist().size() >= 1) {
                        RichPopup.PopupHints hints = new RichPopup.PopupHints();
                        candidateRule.getBind_deletepopup().show(hints);
                    }                    
                }
                ruleslist.clear();
            }
            System.runFinalization();
        } catch (Exception e) {
            e.printStackTrace();
        }
        RPMlogger.log(Level.INFO, "deleteRule() End");
    }

    public void departmentMerchant() {
        RPMlogger.info("Deparmtent multiselect drop down getting the values from department table");
        RPMlogger.log(Level.INFO, "departmentMerchant() starts");
        try {
            CandidateRuleMB candidateRule = (CandidateRuleMB)ADFContext.getCurrent().getPageFlowScope().get("CandidateRuleMB");
            DCBindingContainer dcb = (DCBindingContainer)BindingContext.getCurrent().getCurrentBindingsEntry();
            DCIteratorBinding dci = dcb.findIteratorBinding("Department_View_Merchant1Iterator");
            ViewObject vo = dci.getViewObject();
            vo.reset();
            vo.executeQuery();
            if (null != vo) {
                int i = 0;
                Row rows = null;
                while (vo.hasNext()) {
                    if (i == 0) {
                        rows = vo.first();
                    } else {
                        rows = vo.next();
                    }
                    String deptId = rows.getAttribute("Department").toString();
                    String hifen = rows.getAttribute("Hifen").toString();
                    String deptName = rows.getAttribute("DepartmentName").toString();
                    candidateRule.getDepartmentdataList().add(new SelectItem(deptId + "" + hifen + "" + deptName));
                    candidateRule.getDeptAll().add(deptId + "" + hifen + "" + deptName);
                    i++;
                }
            }
        } catch (ArrayIndexOutOfBoundsException aiobe) {
            aiobe.printStackTrace();

        } catch (NullPointerException ne) {
            ne.printStackTrace();
        } catch (ViewObjectNotPreparedException vnpe) {
            vnpe.printStackTrace();
        } catch (JboSQLException jse) {
            jse.printStackTrace();
        }
        RPMlogger.log(Level.INFO, "departmentMerchant() End");
    }

    public void departmentMerchant_VCL(ValueChangeEvent valueChangeEvent) {

        RPMlogger.info("getting the selected department");
        RPMlogger.log(Level.INFO, "departmentMerchant_VCL() starts");
        try {
            CandidateRuleMB candidateRule = (CandidateRuleMB)ADFContext.getCurrent().getPageFlowScope().get("CandidateRuleMB");            
            DCBindingContainer dcb = (DCBindingContainer)BindingContext.getCurrent().getCurrentBindingsEntry();
            DCIteratorBinding dci = dcb.findIteratorBinding("Mv_Skulist_Item_View1_1Iterator");
            ViewObject itemlistvo = dci.getViewObject();
            if (null != valueChangeEvent.getNewValue()) {
                candidateRule.getArrlist().clear();
                candidateRule.getSelectedlist().clear();
                candidateRule.getDeptIdArrlist().clear();
                String deptselectedValues = valueChangeEvent.getNewValue().toString();
                String selectedValue = deptselectedValues.replace("[", "").replace("]",
                                                                                   "").trim(); //10,20
                String[] st = selectedValue.split(",");
                for (String temp : st)
                    candidateRule.getSelectedlist().add(temp);

                ArrayList<String> deptIdAll = new ArrayList<String>();
                for (int i = 0; i < st.length; i++) {
                    String[] onelist = st.toString().split("-");
                    deptIdAll.add(onelist[0]);

                }
                StringBuffer all = new StringBuffer();
                all.append("[");
                int flag = 1;
                for (String s : candidateRule.getDeptAll()) {
                    all.append(s);
                    if (!s.equals("null") && flag != candidateRule.getDeptAll().size()) {
                        all.append(",");
                        all.append(" ");
                    }
                    flag++;
                }
                all.append("]");
                String All = all.toString();
                if (deptselectedValues.equals(All)) {
                    String alldeptValue = "-1";
                    candidateRule.getDeptIdArrlist().add(alldeptValue);
                    candidateRule.setSelectedCriteriaId("15");
                    candidateRule.getArrlist().add(candidateRule.getSelectedCriteriaId() + " DEPT = " + alldeptValue.trim() + " " + "null");
                    itemlistvo.reset();
                    itemlistvo.setWhereClause(null);
                    itemlistvo.executeQuery();
                    candidateRule.getBind_MerchantValue().resetValue();
                } else {
                    String selectedmod = deptselectedValues.replace("[", "").replace("]", "");
                    selectedmod.trim();
                    String[] str = selectedmod.split(",");
                    for (String temp : str) {
                        String[] temp1 = temp.split("-");
                        candidateRule.getDeptIdArrlist().add(temp1[0]);
                        candidateRule.getDeptIdArraylist_itemvalue().add(temp1[0]);
                        itemlistvo.setWhereClause("dept in " + candidateRule.getDeptIdArrlist().toString().replace("[","(").replace("]",")"));
                        itemlistvo.executeQuery();
                        candidateRule.getBind_MerchantValue().resetValue();
                        candidateRule.getDeptIdArrlist().isEmpty();
                        candidateRule.setSelectedCriteriaId("15");
                        candidateRule.getArrlist().add(candidateRule.getSelectedCriteriaId() + " DEPT = " + temp1[0].trim() + " " + "null");
                    }
                }
            } else {
                candidateRule.getSelectedlist().clear();
                candidateRule.getSelectedValues().clear();
                candidateRule.getDeptIdArrlist().clear();
                itemlistvo.reset();
                itemlistvo.setWhereClause(null);
                itemlistvo.executeQuery();                
            }
        } catch (ArrayIndexOutOfBoundsException aiobe) {
            aiobe.printStackTrace();

        } catch (NullPointerException ne) {
            ne.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        RPMlogger.log(Level.INFO, "departmentMerchant_VCL() End");
    }

    public void ruleTypeVCL(ValueChangeEvent valueChangeEvent) {
            
            if(null !=valueChangeEvent && null !=valueChangeEvent.getNewValue().toString())
            {                                        
               CandidateRuleMB candidateRule = (CandidateRuleMB)ADFContext.getCurrent().getPageFlowScope().get("CandidateRuleMB");
               DCBindingContainer dcBinding = (DCBindingContainer)BindingContext.getCurrent().getCurrentBindingsEntry();                
               DCIteratorBinding dciBinding = dcBinding.findIteratorBinding("Clr_Wksht_Rule_View1Iterator");
               ViewObject vo = dciBinding.getViewObject();
               Row r = vo.getCurrentRow();               
                if(valueChangeEvent.getNewValue().equals("E"))
                {                    
                    ADFContext.getCurrent().getPageFlowScope().put("priorityViewable", true);                                    
                    r.setAttribute("Priority",0);
                    setValueToEL("#{bindings.Priority.inputValue}", r.getAttribute("Priority")); 
                    AdfFacesContext.getCurrentInstance().addPartialTarget(candidateRule.getBind_Priority());
                    setValueToEL("#{bindings.RuleType.inputValue}", "E"); 
                }
                else if(valueChangeEvent.getNewValue().equals("I"))
                {                    
                    setValueToEL("#{bindings.RuleType.inputValue}", "I"); 
                    ADFContext.getCurrent().getPageFlowScope().put("priorityViewable", false);
                    AdfFacesContext.getCurrentInstance().addPartialTarget(candidateRule.getBind_Priority());                    
                }
               
           }
        }

    public void loginUser_view() {
        RPMlogger.info("getting the login username");
        RPMlogger.log(Level.INFO, "loginUser_view() starts");
        ExternalContext ectx = FacesContext.getCurrentInstance().getExternalContext();
        HttpSession userSession = (HttpSession)ectx.getSession(true);
        if (userSession.getAttribute("roleId").equals("80"))
            candidateListingLogic_Merchant();
        else
            candidateListingLogic_Admin();
        RPMlogger.log(Level.INFO, "loginUser_view() End");

    }

    public void candidateListingLogic_Merchant() {
        RPMlogger.log(Level.INFO, "candidateListingLogic_Merchant() starts");
        try {
            DCBindingContainer dcb = BindingContext.getCurrent().findBindingContainer("com_nordstrom_rpm_view_Candidate_RulePageDef");
            DCIteratorBinding dci = dcb.findIteratorBinding("Candidate_Rule_View1Iterator");
            ViewObject vo = dci.getViewObject();
            String name = "Item List Rule";            
            vo.setWhereClause("Name = " + "'" + name + "'");
            vo.executeQuery();
        } catch (Exception e) {
            e.printStackTrace();
        }
        RPMlogger.log(Level.INFO, "candidateListingLogic_Merchant() End");
    }

    public void candidateListingLogic_Admin() {
        RPMlogger.log(Level.INFO, "candidateListingLogic_Admin() starts");
        try {
            DCBindingContainer dcb = BindingContext.getCurrent().findBindingContainer("com_nordstrom_rpm_view_Candidate_RulePageDef");
            DCIteratorBinding dci = dcb.findIteratorBinding("Candidate_Rule_View1Iterator");
            ViewObject vo = dci.getViewObject();
            String name = "Item List Rule";            
            vo.setWhereClause("Name != " + "'" + name + "'");
            vo.executeQuery();
        } catch (Exception e) {
            e.printStackTrace();
        }
        RPMlogger.log(Level.INFO, "candidateListingLogic_Admin() End");
    }

    public void setValueToEL(String el, Object val) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ELContext elContext = facesContext.getELContext();
        ExpressionFactory expressionFactory = facesContext.getApplication().getExpressionFactory();
        ValueExpression valueExp = expressionFactory.createValueExpression(elContext, el,
                                                                           Object.class);
        valueExp.setValue(elContext, val);
    }

    public List valueAutosuggest(String inputValue) {
        RPMlogger.info("Item list Value - AutosuggestBehaviour");
        RPMlogger.log(Level.INFO, "valueAutosuggest() starts");
        try {            
            valueListautosuggest = new ArrayList<SelectItem>();
            items = new ArrayList<SelectItem>();
            DCBindingContainer dcb = (DCBindingContainer)BindingContext.getCurrent().getCurrentBindingsEntry();
            DCIteratorBinding dci = dcb.findIteratorBinding("Mv_Skulist_Item_View1_1Iterator"); // itemlist_value_View1Iterator
            ViewObject vo = dci.getViewObject();            
            vo.reset();
            vo.executeQuery();            
            if (null != vo) {               
                Row r = null;
                int i = 0;
                while (vo.hasNext()) {
                    if (i == 0)
                        r = vo.first();
                    else
                        r = vo.next();
                    i++;

                    if (r.getAttribute("ItemList") != null && r.getAttribute("ItemListDesc") != null) {

                        String value = r.getAttribute("ItemList").toString();
                        String description = r.getAttribute("ItemListDesc").toString();
                        valueListautosuggest.add(new SelectItem(value + "-" + description));
                    }
                }
                for (SelectItem item : valueListautosuggest) {
                    if (null != item && item.getValue().toString().startsWith(inputValue)) {
                        items.add(item);
                    }
                }
            }
        } catch (PropertyNotFoundException pnfe) {
            pnfe.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        RPMlogger.log(Level.INFO, "valueAutosuggest() End");
        return items;
    } 

    public void itemListvalue_VCL(ValueChangeEvent valueChangeEvent) {
        try {
            CandidateRuleMB candidateRule = (CandidateRuleMB)ADFContext.getCurrent().getPageFlowScope().get("CandidateRuleMB");
            if (null != valueChangeEvent.getNewValue() && null != valueChangeEvent) {
                String seleted = valueChangeEvent.getNewValue().toString();
                String[] s = seleted.trim().split("-");
                String seletedskulistValue = s[0].toString().trim();                
                DCBindingContainer dcb = (DCBindingContainer)BindingContext.getCurrent().getCurrentBindingsEntry();
                DCIteratorBinding skulistIterator = dcb.findIteratorBinding("Mv_Skulist_Item_View1_1Iterator");
                ViewObject skuliistvo = skulistIterator.getViewObject();
                skuliistvo.reset();
                skuliistvo.setWhereClause(null);
                skuliistvo.executeQuery();                
                int i = 0;
                Row r = null;
                boolean flag = false;
                while (skuliistvo.hasNext()) {
                    if (i == 0) {
                        r = skuliistvo.first();

                    } else {
                        r = skuliistvo.next();
                    }
                    String skulistValue = r.getAttribute("ItemList").toString();
                    if (skulistValue.equals(seletedskulistValue)) {
                        candidateRule.setIsItemListValueFlag(true);
                        flag = true;
                        candidateRule.setValue(seletedskulistValue);
                        candidateRule.setItemListListenerValue(seletedskulistValue);
                    }
                    i++;
                }
                if (!flag)
                    candidateRule.setIsItemListValueFlag(false);
            } else {
                candidateRule.setValue(null);
            }
        } catch (ViewObjectNotPreparedException vnpe) {
            vnpe.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void yesAction(ActionEvent actionEvent) {
        RPMlogger.info("Multiple rule criteria deletion in the popup");
        RPMlogger.log(Level.INFO, "yesAction() starts");
        try {
            CandidateRuleMB candidateRule = (CandidateRuleMB)ADFContext.getCurrent().getPageFlowScope().get("CandidateRuleMB");
            OperationBinding oBinding = BindingContext.getCurrent().getCurrentBindingsEntry().getOperationBinding("deleteRuleCriteria");

            oBinding.getParamsMap().put("ruleids",
                                        candidateRule.getListingruleidlist().toString().replace("[",
                                                                                                "(").replace("]",
                                                                                                             ")"));
            oBinding.execute();
            candidateRule.getBind_deletepopup().hide();
            FacesMessageUtils.addErrorMessage("Deleted successfully", FacesMessage.SEVERITY_INFO);
            candidateRule.getBind_candidateRule().resetStampState();
            AdfFacesContext.getCurrentInstance().addPartialTarget(candidateRule.getBind_candidateRule());            
        } catch (Exception e) {
            e.printStackTrace();
        }
        RPMlogger.log(Level.INFO, "yesAction() ends");

    }

    public void NoAction(ActionEvent actionEvent) {
        RPMlogger.info("Multiple rule criteria deletion in the popup");
        RPMlogger.log(Level.INFO, "NoAction() starts");
        try {
            CandidateRuleMB candidateRule = (CandidateRuleMB)ADFContext.getCurrent().getPageFlowScope().get("CandidateRuleMB");
            if (null != actionEvent) {                
                candidateRule.getBind_deletepopup().hide();
                candidateRule.getBind_selectRules().setSelected(false);
                AdfFacesContext.getCurrentInstance().addPartialTarget(candidateRule.getBind_selectRules());
                AdfFacesContext.getCurrentInstance().addPartialTarget(candidateRule.getBind_candidateRule());
                candidateRule.getListingruleidlist().clear();
                candidateRule.getBind_candidateRule().resetStampState();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        RPMlogger.log(Level.INFO, "NoAction() ends");

    }

    public void cancelActionListener(ActionEvent actionEvent) {
        RPMlogger.info("cancel the RuleCreation/Rule Updation page");
        RPMlogger.log(Level.INFO, "cancelActionListener() starts");
        try {
            CandidateRuleMB candidateRule = (CandidateRuleMB)ADFContext.getCurrent().getPageFlowScope().get("CandidateRuleMB");
            if (null != actionEvent) {
                candidateRule.getBind_Value().setVisible(true);
                candidateRule.getBind_parameter().setVisible(false);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        RPMlogger.log(Level.INFO, "cancelActionListener() ends");

    }

    public void priorityValidator(FacesContext facesContext, UIComponent uIComponent, Object object) {
        // Add event code here...
       
            if (object != null) {
                String name = object.toString();
                if (!name.equals("0")) {                    
                    String expression = "^[0-9]+$";
                    CharSequence inputStr = name;
                    Pattern pattern = Pattern.compile(expression);
                    Matcher matcher = pattern.matcher(inputStr);
                    String msg = "Please enter only Positive numeric value for Priority";
                    if (matcher.matches()) {

                    } else {
                        throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR,
                                                                      msg, null));
                    }
                } else {
                    String msg = "Please enter only Positive numeric value for priority";
                    throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, msg,
                                                                  null));
                }
            }
       
    }

    public void resetCandidateRule(ActionEvent actionEvent) {
        // Add event code here...
        try {                       
            OperationBinding oBinding=BindingContext.getCurrent().getCurrentBindingsEntry().getOperationBinding("rollbackOrRevertRowChanges");            
            oBinding.execute();
            CandidateRuleMB candidateRule = (CandidateRuleMB)ADFContext.getCurrent().getPageFlowScope().get("CandidateRuleMB");
            int columnIndex = 0;
            adfcontext.getPageFlowScope().put("itemListRuleID", null);
            adfcontext.getPageFlowScope().put("corporateRuleID", null);
            setValueToEL("#{bindings.Column_Name_View1.inputValue}", columnIndex);
            setValueToEL("#{bindings.Operator_View1.inputValue}", columnIndex);
            setValueToEL("#{bindings.Paramater_Id_View1.inputValue}", columnIndex);
            ruleslist.clear();
            ADFContext.getCurrent().getPageFlowScope().put("selectedRules", false);
            ADFContext.getCurrent().getPageFlowScope().put("CandidateRuleMB", null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public DropdownLOVModel getLov() {

        CandidateRuleMB candidateRule = (CandidateRuleMB)ADFContext.getCurrent().getPageFlowScope().get("CandidateRuleMB");
        ArrayList<DropdownDataItem> list = new ArrayList<DropdownDataItem>();
        DCBindingContainer dcb = (DCBindingContainer)BindingContext.getCurrent().getCurrentBindingsEntry();
        DCIteratorBinding skulistIterator = dcb.findIteratorBinding("Mv_Skulist_Item_View1_1Iterator");

        ViewObject skuliistvo = skulistIterator.getViewObject();
        skuliistvo.reset();
        skuliistvo.setWhereClause(null);
        skuliistvo.executeQuery();
        if (!candidateRule.getDeptIdArrlist().isEmpty()){
            String input=candidateRule.getDeptIdArrlist().toString().replace("[","").replace("]","");
            if(input.equals("-1")){
                            skuliistvo.reset();
                            skuliistvo.setWhereClause(null);  
            }
            else{
                skuliistvo.setWhereClause("dept in " + candidateRule.getDeptIdArrlist().toString().replace("[",
                                                                                                       "(").replace("]",
                                                                                                                    ")"));
            }
        }
        skuliistvo.executeQuery();
        Row r = null;
        int i = 0;
        while (skuliistvo.hasNext()) {
            if (i == 0)
                r = skuliistvo.first();
            else
                r = skuliistvo.next();

            String SkulistValue = r.getAttribute("ItemList").toString();
            String skulistDesc = r.getAttribute("ItemListDesc").toString();
            list.add(new TestDropdownDataItem(SkulistValue, SkulistValue + "-" + skulistDesc));
            i++;
        }
        return new DropdownLOVModel(new LOVSelectionListener(selectedValue), list);
    }

    public void zoneIdVCL(ValueChangeEvent valueChangeEvent) {
        // Add event code here...
        if(null != valueChangeEvent && null != valueChangeEvent.getNewValue() ){
           int index=Integer.parseInt(valueChangeEvent.getNewValue()+"");                                     
           DCBindingContainer dcBinding = (DCBindingContainer)BindingContext.getCurrent().getCurrentBindingsEntry();
           DCIteratorBinding dciBinding = dcBinding.findIteratorBinding("Zone_View1Iterator");//Clr_Wksht_Rule_View1Iterator
           ViewObject vo = dciBinding.getViewObject();
           Row r = vo.getRowAtRangeIndex(index-1);
           if(r!=null){
           if (r.getAttribute("ZoneId") != null) 
           {              
            String zoneId=r.getAttribute("ZoneId")+"";               
            adfcontext.getPageFlowScope().put("zoneID", zoneId);
       
           }
           }
        }
    }

    public void setCurrentSystemDate(java.util.Date currentSystemDate) {
        this.currentSystemDate = currentSystemDate;
    }

    public java.util.Date getCurrentSystemDate() {
        try {
                DCBindingContainer dcbc = (DCBindingContainer)BindingContext.getCurrent().getCurrentBindingsEntry();
                DCIteratorBinding dcib =dcbc.findIteratorBinding("V_Date_View1Iterator");
                ViewObject viewObj = dcib.getViewObject();
                Row dateRow = viewObj.first();               
                Date VDate = (Date)dateRow.getAttribute("Vdate");
                java.util.Date time = VDate.dateValue();
                DateFormat formatter = new SimpleDateFormat("dd-MMM-yy");
                String currentDate = formatter.format(time);
                return formatter.parse(currentDate);
            } catch (Exception e) {
            return null;
        }        
    }

    public void disableManualDate(ValueChangeEvent valueChangeEvent) {
        // Add event code here...
        valueChangeEvent.getSource();
        FacesContext fctx = FacesContext.getCurrentInstance();
            ExtendedRenderKitService erks = null;
            erks = Service.getRenderKitService(
                         fctx, ExtendedRenderKitService.class);
            erks.addScript(fctx, "disableManualDate");
            
    }

    private class TestDropdownDataItem implements DropdownDataItem {
        private String testId, testDescription;

        public TestDropdownDataItem(String id, String description) {
            this.testId = id;
            this.testDescription = description;
        }

        public String getId() {
            return testId;
        }

        public String getDescription() {
            return testDescription;
        }

        public void setTestId(String testId) {
            this.testId = testId;
        }

        public String getTestId() {
            return testId;
        }

        public void setTestDescription(String testDescription) {
            this.testDescription = testDescription;
        }

        public String getTestDescription() {
            return testDescription;
        }
    }

    private class LOVSelectionListener implements DropdownSelectionListener {
        private String oldValue;

        private LOVSelectionListener(String oldValue) {
            this.oldValue = oldValue;
        }

        /**
         * Logic defining what to do when the user selects an item in the dropdown
         * @param selectedItem
         */
        public void valueSelected(DropdownDataItem selectedItem) {
            updateBeanValue(selectedItem);
        }

        public void updateBeanValue(DropdownDataItem selectedValue) {            
            String error_detail = "User selected Skulist : " + selectedValue.getId() + " and Skulist description: " + selectedValue.getDescription();
            /* FacesContext fc = FacesContext.getCurrentInstance();
            FacesMessage message = new FacesMessage(error_decp, error_detail);
            message.setSeverity(FacesMessage.SEVERITY_INFO);
            fc.addMessage(null, message);  */
        }
    }

}

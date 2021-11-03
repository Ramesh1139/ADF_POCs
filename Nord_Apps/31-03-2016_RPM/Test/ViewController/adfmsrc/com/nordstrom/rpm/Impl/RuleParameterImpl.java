package com.nordstrom.rpm.Impl;

import com.nordstrom.rpm.Backing.RuleParameterBean;
import com.nordstrom.rpm.FacesMessageUtils;
import com.nordstrom.rpm.QVO.ParameterDepartmentRowImpl;
import com.nordstrom.rpm.Service.RuleParameterService;


import java.util.ArrayList;
import java.util.List;

import java.util.Map;

import java.util.logging.Level;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.el.ELContext;
import javax.el.ExpressionFactory;
import javax.el.MethodExpression;

import javax.el.ValueExpression;

import javax.faces.application.Application;
import javax.faces.application.FacesMessage;

import javax.faces.component.UIComponent;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import javax.faces.event.ValueChangeEvent;

import javax.faces.validator.ValidatorException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import oracle.adf.model.BindingContext;
import oracle.adf.model.binding.DCBindingContainer;
import oracle.adf.model.binding.DCIteratorBinding;

import oracle.adf.share.ADFContext;

import oracle.adf.share.logging.ADFLogger;

import oracle.adf.view.rich.component.rich.data.RichTable;
import oracle.adf.view.rich.context.AdfFacesContext;
import oracle.adf.view.rich.event.DialogEvent;
import oracle.adf.view.rich.event.QueryEvent;
import oracle.adf.view.rich.model.FilterableQueryDescriptor;

import oracle.binding.OperationBinding;


import oracle.jbo.Key;
import oracle.jbo.NavigatableRowIterator;
import oracle.jbo.Row;
import oracle.jbo.ViewObject;

import oracle.jbo.uicli.binding.JUCtrlHierBinding;

import org.apache.myfaces.trinidad.event.SortEvent;
import org.apache.myfaces.trinidad.model.CollectionModel;
import org.apache.myfaces.trinidad.model.SortCriterion;

public class RuleParameterImpl implements RuleParameterService {
    //private List<String> departmentList = new ArrayList<String>();
    DCBindingContainer binding = (DCBindingContainer)BindingContext.getCurrent().getCurrentBindingsEntry();
    private static ADFLogger RPMlogger = ADFLogger.createADFLogger(RuleParameterImpl.class);

    public RuleParameterImpl() {
        super();
        RPMlogger.info("Creating new instance of Rule Parameter");
    }

    public void fetchRuleParameter() {
        RPMlogger.info("Fetching the Parameter value while loading the page");
        RPMlogger.log(Level.INFO, "fetchRuleParameter() starts");        
        DCBindingContainer dcb = BindingContext.getCurrent().findBindingContainer("com_nordstrom_rpm_view_Rule_ParameterPageDef");
        dcb.setRefreshed(true);
        fetchParameterResultsAfterSortOrCancel(dcb);
        RPMlogger.log(Level.INFO, "fetchRuleParameter() Ends");
    }

    public void saveRuleParameter() {
        RPMlogger.info("Saving the new Parameter value enetred by an user");
        RPMlogger.log(Level.INFO, "saveRuleParameter() starts");
        boolean equality = false;        
        ExternalContext ectx = FacesContext.getCurrentInstance().getExternalContext();
        HttpSession userSession = (HttpSession)ectx.getSession(true);
        RuleParameterBean ruleParameter = (RuleParameterBean)ADFContext.getCurrent().getViewScope().get("RuleParameterBean");
        DCIteratorBinding rule_Parameter_Iterator = binding.findIteratorBinding("Clr_Wksht_Rule_Param_Detail_View1Iterator");
        try{
        Row[] parameterValues = rule_Parameter_Iterator.getAllRowsInRange();

        DCIteratorBinding deptBinding = binding.findIteratorBinding("ParameterDepartment1Iterator");
        Row[] departmentValues = deptBinding.getAllRowsInRange();
        DCIteratorBinding paramBinding = binding.findIteratorBinding("Clr_Wksht_Rule_Param_Head_View1Iterator");
        HttpSession loggedInSession = (HttpSession)ectx.getSession(true);
        Row[] paramVlues = paramBinding.getAllRowsInRange();
        for (Row department : departmentValues) {
            //System.out.println("department name ===" +department.getAttribute("DepartmentName"));
            //System.out.println("department id ===" +department.getAttribute("Department"));
            String[] parameterName = department.getAttributeNames();
            String parametersName = "";
            for (int i = 1; i < parameterName.length; i++) {
                if (i > 1)
                    parametersName = "$" + parameterName[i];
                //System.out.println("Parameter value ===" +department.getAttribute(parameterName[i]));
                //System.out.println("Parameter ===" + parameterName[i]);
               // System.out.println("Parameter ===" + parameterName[i]);
                //System.out.println("Parameter value ===" +department.getAttribute(parameterName[i]));
                if (department.getAttribute(parameterName[i]) != null) {
                                        
                    for (Row param : paramVlues) {
                        //System.out.println("param value ====" +param.getAttribute("ParamName"));
                        if (parametersName.equalsIgnoreCase(param.getAttribute("ParamName").toString())) {
                            //System.out.println("parameter id ====" +param.getAttribute("ParamId"));

                            for (Row r : parameterValues) {
                                //System.out.println("parameter id ===" +r.getAttribute("ParamId"));
                                //System.out.println("department id ====" +r.getAttribute("Dept"));
                                if (department.getAttribute(parameterName[i]) == null || department.getAttribute(parameterName[i]).equals("")) {
                                    OperationBinding insertParamoperation = BindingContext.getCurrent().getCurrentBindingsEntry().getOperationBinding("deleteParamValues");
                                    insertParamoperation.getParamsMap().put("param_id",param.getAttribute("ParamId"));
                                    insertParamoperation.getParamsMap().put("dept_id",department.getAttribute("Department"));
                                    
                                } else {
                                    if ((department.getAttribute("Department").equals(r.getAttribute("Dept"))) && (param.getAttribute("ParamId").equals(r.getAttribute("ParamId")))) {
                                        // System.out.println("entered parameter value"+department.getAttribute("Department"));
                                        equality = true;
                                        ParameterDepartmentRowImpl row2 = (ParameterDepartmentRowImpl)department;                                        
                                        resetAllParameterColors(department, row2);
                                        OperationBinding insertParamoperation = BindingContext.getCurrent().getCurrentBindingsEntry().getOperationBinding("updateExistingParameter");
                                        insertParamoperation.getParamsMap().put("param_id",param.getAttribute("ParamId"));
                                        insertParamoperation.getParamsMap().put("dept_id",department.getAttribute("Department"));
                                        insertParamoperation.getParamsMap().put("param_value",department.getAttribute(parameterName[i]));
                                        insertParamoperation.getParamsMap().put("userName",loggedInSession.getAttribute("userName").toString());
                                        insertParamoperation.execute();
                                        break;
                                    }
                                }
                            }
                            if (equality == false) {
                                OperationBinding insertParamoperation = BindingContext.getCurrent().getCurrentBindingsEntry().getOperationBinding("insertNewParameterValue");
                                insertParamoperation.getParamsMap().put("paramID",param.getAttribute("ParamId"));
                                insertParamoperation.getParamsMap().put("deptID",department.getAttribute("Department"));
                                insertParamoperation.getParamsMap().put("param_value",department.getAttribute(parameterName[i]));
                                insertParamoperation.getParamsMap().put("userName",loggedInSession.getAttribute("userName").toString());
                                insertParamoperation.execute();

                                ParameterDepartmentRowImpl row2 = (ParameterDepartmentRowImpl)department;                                
                                resetAllParameterColors(department, row2);
                            }
                            equality = false;
                        }

                    }
                }
                if(department.getAttribute(parameterName[i]) == null || department.getAttribute(parameterName[i]).equals("")){
                    for (Row param : paramVlues) {
                        if (parametersName.equalsIgnoreCase(param.getAttribute("ParamName").toString())) {
                            for (Row r : parameterValues) {
                                if ((department.getAttribute("Department").equals(r.getAttribute("Dept"))) && (param.getAttribute("ParamId").equals(r.getAttribute("ParamId")))) {
                                    if(parameterName[i].equalsIgnoreCase("SOHLessBroken"))
                                        System.out.println("Parameter value ===" +department.getAttribute(parameterName[i]));
                                    
                                    equality = true;
                                    ParameterDepartmentRowImpl row2 = (ParameterDepartmentRowImpl)department;                                
                                    resetAllParameterColors(department, row2);
                                    OperationBinding deleteParamoperation = BindingContext.getCurrent().getCurrentBindingsEntry().getOperationBinding("deleteParamValues");
                                    deleteParamoperation.getParamsMap().put("param_id",param.getAttribute("ParamId"));
                                    deleteParamoperation.getParamsMap().put("dept_id",department.getAttribute("Department"));
                                    deleteParamoperation.execute();
                                    break;
                                }
                            }
                        }
                    }
                }
            }            
            userSession.removeAttribute("setPopupEvent");
            ruleParameter.getBind_Cancel().setDisabled(true);
            AdfFacesContext.getCurrentInstance().addPartialTarget(ruleParameter.getBind_Cancel());
            
        }
        }
        catch(NullPointerException ne){
            ne.printStackTrace();   
            RPMlogger.severe("saveRuleParameter() :Null pointer Exception");
        }
        catch(ArrayIndexOutOfBoundsException aie){
            aie.printStackTrace();
            RPMlogger.severe("saveRuleParameter() :Array out of bound exception");
        }        
        FacesMessageUtils.addErrorMessage("Data have been saved successfully",
                                          FacesMessage.SEVERITY_INFO);
        RPMlogger.log(Level.INFO, "saveRuleParameter() ends");
    }

    public void onParameterTableQuery(QueryEvent queryEvent) {
        RPMlogger.info("filtering the department to get selected departments records");
        RPMlogger.log(Level.INFO, "onParameterTableQuery() starts");        
        List<Object> departmentIdArray = new ArrayList<Object>();
        FilterableQueryDescriptor fqd = (FilterableQueryDescriptor)queryEvent.getDescriptor();

        Map<String, Object> criteriaMap = fqd.getFilterCriteria();
        //Translate DepartmentId array list to OR separate list of values
        StringBuffer deptNameFilterString = new StringBuffer();
        StringBuffer departments = new StringBuffer();
        ArrayList<Object> index = null;
        try{
        if (criteriaMap.get("Department") != null) {

            index = (ArrayList<Object>)criteriaMap.get("Department");           
            DCIteratorBinding dci = binding.findIteratorBinding("Department_View1Iterator");
            ViewObject vo = dci.getViewObject();
            for (int i = 0; i < index.size(); i++) {
                Row rows = vo.getRowAtRangeIndex(Integer.parseInt(index.get(i).toString()));
                String DeptName = rows.getAttribute("Department").toString();

                departmentIdArray.add(DeptName);
            }   
            departmentLists(departments, deptNameFilterString, departmentIdArray);
            
            deptNameFilterString.append(" OR -1");
            String departmentNames = deptNameFilterString.toString();
            criteriaMap.put("Department", departmentNames);
            fqd.setFilterCriteria(criteriaMap);
        } else {
            index = new ArrayList<Object>();
            DCIteratorBinding dci = binding.findIteratorBinding("Department_View1Iterator");
            ViewObject vo = dci.getViewObject();
            vo.reset();
            vo.executeQuery();
            //System.out.println(" Department_View1Iterator total length =="+vo.getAllRowsInRange().length);            
            for (int i = 0; i < vo.getAllRowsInRange().length; i++) {
                Row rows = vo.getRowAtRangeIndex(i);
                String DeptName = rows.getAttribute("Department").toString();

                departmentIdArray.add(DeptName);
            }  
            departmentLists(departments, deptNameFilterString, departmentIdArray);            
        }
        // preserve default query listener behavior
        //#{bindings.ParameterDepartment1Query.processQuery}

        FacesContext fctx = FacesContext.getCurrentInstance();
        Application application = fctx.getApplication();
        ExpressionFactory expressionFactory = application.getExpressionFactory();
        ELContext elctx = fctx.getELContext();
        try {
            MethodExpression methodExpression = expressionFactory.createMethodExpression(elctx,
                                                                                         "#{bindings.ParameterDepartment1Query.processQuery}",
                                                                                         Object.class,new Class[] { QueryEvent.class });
            methodExpression.invoke(elctx, new Object[] { queryEvent });
        } catch (Exception ae) {
            ae.printStackTrace();
            RPMlogger.severe("Unexpected error while filtering");
        }

        //restore filter selection done by the user. Note that this
        //needs to be saved as an ArrayList
        criteriaMap.put("Department", index);
        fqd.setFilterCriteria(criteriaMap);
        
        OperationBinding selectDepartments = BindingContext.getCurrent().getCurrentBindingsEntry().getOperationBinding("filteredList");
        selectDepartments.getParamsMap().put("departmentNames", departments.toString());
        selectDepartments.execute();
        Row[] departmentValues = (Row[])selectDepartments.getResult();

        DCIteratorBinding parameterIterator = binding.findIteratorBinding("Clr_Wksht_Rule_Param_Detail_View1Iterator");
        Row[] parameterValues = parameterIterator.getAllRowsInRange();
        DCIteratorBinding paramBinding = binding.findIteratorBinding("Clr_Wksht_Rule_Param_Head_View1Iterator");
        Row[] paramVlues = paramBinding.getAllRowsInRange();
        for (Row selectedDepartments : departmentValues) {
            String[] parameterName = selectedDepartments.getAttributeNames();
            String parametersName = "";
            for (int i = 1; i < parameterName.length; i++) {
                if (i > 1)
                    parametersName = "$" + parameterName[i];
                for (Row param : paramVlues) {
                    if (parametersName.equalsIgnoreCase(param.getAttribute("ParamName").toString())) {
                        //System.out.println("parameter id ====" +param.getAttribute("ParamId"));
                        for (Row r : parameterValues) {
                            if ((selectedDepartments.getAttribute("Department").equals(r.getAttribute("Dept"))) && (param.getAttribute("ParamId").equals(r.getAttribute("ParamId")))) {
                                selectedDepartments.setAttribute(parameterName[i],r.getAttribute("ParamValue"));
                                //System.out.println(selectedDepartments.getAttribute(parameterName[i]));
                            }
                        }
                    }
                }
            }
        }
        }
        catch(NullPointerException ne){
            ne.printStackTrace();   
            RPMlogger.severe("onParameterTableQuery() :Null pointer Exception");
        }
        catch(ArrayIndexOutOfBoundsException aie){
            aie.printStackTrace();
            RPMlogger.severe("onParameterTableQuery() :Array out of bound exception");
        }
        RPMlogger.log(Level.INFO, "onParameterTableQuery() ends");
    }

    private StringBuffer departmentLists(StringBuffer departments,
                                         StringBuffer deptNameFilterString,
                                         List<Object> departmentIdArray) {
        departments.append(" (");
        for (int argIndex = 0; argIndex < departmentIdArray.size();
             argIndex++) {


            if (argIndex == 0) {
                String departmentName = (String)departmentIdArray.get(argIndex);
                deptNameFilterString.append(departmentName);
                if (departmentName.contains("'")) {
                    departments.append("q'(" + departmentName + ")'");
                } else
                    departments.append("'" + departmentName + "'");

            } else {
                deptNameFilterString.append(" OR ");
                departments.append(",");
                String departmentName = (String)departmentIdArray.get(argIndex);
                deptNameFilterString.append(departmentName);
                if (departmentName.contains("'")) {
                    departments.append("q'(" + departmentName + ")'");
                } else
                    departments.append("'" + departmentName + "'");
            }
        }
        departments.append(")");

        return departments;
    }

    public void cancelChangesDialogListener(DialogEvent dialogEvent) {
        RuleParameterBean ruleParameter = (RuleParameterBean)ADFContext.getCurrent().getViewScope().get("RuleParameterBean");        
        ExternalContext ectx = FacesContext.getCurrentInstance().getExternalContext();        
        HttpSession userSession = (HttpSession)ectx.getSession(true);
        if (dialogEvent.getOutcome() == DialogEvent.Outcome.ok) {

            fetchParameterResultsAfterSortOrCancel(binding);
            userSession.removeAttribute("setPopupEvent");            
            ruleParameter.getBind_Cancel().setDisabled(true);
            AdfFacesContext.getCurrentInstance().addPartialTarget(ruleParameter.getBind_Cancel());
            AdfFacesContext.getCurrentInstance().addPartialTarget(ruleParameter.getParameterTable());

            System.runFinalization();

        }
    }

    public void ParameterTableSort(SortEvent sortEvent) {
        RPMlogger.info("Sorting the department to get all departments in ascending/descending order");
        RPMlogger.log(Level.INFO, "onParameterTableQuery() starts");
        RuleParameterBean ruleParameter = (RuleParameterBean)ADFContext.getCurrent().getViewScope().get("RuleParameterBean");
        List<SortCriterion> sortList = sortEvent.getSortCriteria();
        SortCriterion sc = sortList.get(0);

        if ((sc.getProperty()).equalsIgnoreCase("Department")) {

        sortList = new ArrayList<SortCriterion>();
        SortCriterion sc2 = new SortCriterion("Department",sc.isAscending());        
        sortList.add(sc2);

        RichTable ct = (RichTable)sortEvent.getComponent();
        ct.setSortCriteria(sortList);
        AdfFacesContext.getCurrentInstance().addPartialTarget(ruleParameter.getParameterTable());
        DCIteratorBinding departmentBinding = binding.findIteratorBinding("ParameterDepartment1Iterator");
        Row[] departmentValues = departmentBinding.getAllRowsInRange();
        DCIteratorBinding parameterIterator = binding.findIteratorBinding("Clr_Wksht_Rule_Param_Detail_View1Iterator");
        Row[] parameterValues = parameterIterator.getAllRowsInRange();
        DCIteratorBinding paramBinding = binding.findIteratorBinding("Clr_Wksht_Rule_Param_Head_View1Iterator");
        Row[] paramVlues = paramBinding.getAllRowsInRange();
        for(Row selectedDepartments: departmentValues){
            String[] parameterName = selectedDepartments.getAttributeNames();
            String parametersName = "";
            for (int i = 1; i < parameterName.length; i++) {
                if(i>1)
                    parametersName = "$"+parameterName[i];
                for (Row param : paramVlues) {
                    if (parametersName.equalsIgnoreCase(param.getAttribute("ParamName").toString())) {
                        //System.out.println("parameter id ====" +param.getAttribute("ParamId"));
                        for (Row r : parameterValues) {
                            if ((selectedDepartments.getAttribute("Department").equals(r.getAttribute("Dept"))) &&
                                (param.getAttribute("ParamId").equals(r.getAttribute("ParamId")))) {
                                selectedDepartments.setAttribute(parameterName[i], r.getAttribute("ParamValue"));
                                System.out.println(selectedDepartments.getAttribute(parameterName[i]));
                            }
                        }
                    }
                }
            }
        }           
        }            
        RPMlogger.log(Level.INFO, "onParameterTableQuery() ends");
    }

    private void fetchParameterResultsAfterSortOrCancel(DCBindingContainer binding) {
        //DCBindingContainer binding = (DCBindingContainer)BindingContext.getCurrent().getCurrentBindingsEntry();
        DCIteratorBinding parameterIterator = binding.findIteratorBinding("Clr_Wksht_Rule_Param_Detail_View1Iterator");
        Row[] parameterValues = parameterIterator.getAllRowsInRange();
        DCIteratorBinding departmentBinding = binding.findIteratorBinding("ParameterDepartment1Iterator");
        departmentBinding.releaseData();
        departmentBinding.invalidateCache();
        departmentBinding.executeQuery();
        
        Row[] departmentValues = departmentBinding.getAllRowsInRange();
        DCIteratorBinding paramBinding = binding.findIteratorBinding("Clr_Wksht_Rule_Param_Head_View1Iterator");

        Row[] paramVlues = paramBinding.getAllRowsInRange();
        for (Row department : departmentValues) {
            //System.out.println("department name ===" +department.getAttribute("DepartmentName"));
            //System.out.println("department id ===" +department.getAttribute("Department"));
            String[] parameterName = department.getAttributeNames();
            String parametersName = "";
            for (int i = 1; i < parameterName.length; i++) {
                if (i > 1)
                    parametersName = "$" + parameterName[i];
                for (Row param : paramVlues) {
                    if (parametersName.equalsIgnoreCase(param.getAttribute("ParamName").toString())) {
                        //System.out.println("parameter id ====" +param.getAttribute("ParamId"));
                        department.setAttribute(parameterName[i], null);
                        for (Row r : parameterValues) {
                            if ((department.getAttribute("Department").equals(r.getAttribute("Dept"))) && (param.getAttribute("ParamId").equals(r.getAttribute("ParamId")))) {
                                department.setAttribute(parameterName[i],
                                                        r.getAttribute("ParamValue"));
                                //System.out.println("department param value ==="+department.getAttribute(parameterName[i]));
                                //System.out.println(department.getAttribute(parameterName[i]));
                            }
                        }
                    }
                }
            }
        }
    }
    
    private void resetAllParameterColors(Row department,ParameterDepartmentRowImpl row2){        
        row2.setCurrentRetailColor("");        
        row2.setBaseRetailColor("");        
        row2.setOnOrderBrokenColor("");       
        row2.setSOHLessBrokenColor("");        
        row2.setSOHGr8BrokenColor("");        
        row2.setStoreOnOrderColor("");       
        row2.setOnOrderAgeColor("");        
        row2.setTltSellThruPerformanceColor("");        
        row2.setATSWPerformanceColor("");        
        row2.setATSWLessDogsColor("");        
        row2.setATSWGr8DogsColor("");        
        row2.setWareHouseColor("");        
        row2.setTltSellThruDogsColor("");        
        row2.setStoreSOHColor("");        
        row2.setWareHouseOnOrderColor("");        
        row2.setParamValueChange("");       
        row2.setATSWSAgeColor("");     
    }
    public void wareHousecolorChange(ValueChangeEvent valueChangeEvent) {
        Row row = makeRowCurrent(valueChangeEvent);
        Row[] paramVlues = ruleParameterHeadResults();
        String paramValue = "";
        int deptID = 0;
        int paramID = 0;
        for (Row param : paramVlues) {
            if ("$WarehouseSOH".equals(param.getAttribute("ParamName").toString())) {
                paramID = Integer.parseInt(param.getAttribute("ParamId").toString());
                deptID = Integer.parseInt(row.getAttribute("Department").toString());
                paramValue = ParameterValuesResult(paramID, deptID);

                break;
            }
        }
        setParameterValuesColorChange(paramID,valueChangeEvent, paramValue, deptID);
        setContextAttribute(paramID, valueChangeEvent, paramValue, deptID);
    }

    public void ATWLGr8DogsColor(ValueChangeEvent valueChangeEvent) {
        Row row = makeRowCurrent(valueChangeEvent);        
        Row[] paramVlues = ruleParameterHeadResults();
        String paramValue = "";
        int deptID = 0;
        int paramID = 0;
        for (Row param : paramVlues) {
            if ("$ATSWGreaterDOGs".equals(param.getAttribute("ParamName").toString())) {
                paramID = Integer.parseInt(param.getAttribute("ParamId").toString());
                deptID = Integer.parseInt(row.getAttribute("Department").toString());
                paramValue = ParameterValuesResult(paramID, deptID);

                break;
            }
        }
        setParameterValuesColorChange(paramID,valueChangeEvent, paramValue, deptID);
        setContextAttribute(paramID, valueChangeEvent, paramValue, deptID);
    }

    public void StoreSOHAttributes(ValueChangeEvent valueChangeEvent) {
        Row row = makeRowCurrent(valueChangeEvent);

        Row[] paramVlues = ruleParameterHeadResults();
        String paramValue = "";
        int deptID = 0;
        int paramID = 0;
        for (Row param : paramVlues) {
            if ("$StoreSOH".equals(param.getAttribute("ParamName").toString())) {
                paramID = Integer.parseInt(param.getAttribute("ParamId").toString());
                deptID = Integer.parseInt(row.getAttribute("Department").toString());
                paramValue = ParameterValuesResult(paramID, deptID);

                break;
            }
        }
        setParameterValuesColorChange(paramID,valueChangeEvent, paramValue, deptID);
        setContextAttribute(paramID, valueChangeEvent, paramValue, deptID);
    }

    public void TltSellThruDOGsAttributes(ValueChangeEvent valueChangeEvent) {
        Row row = makeRowCurrent(valueChangeEvent);

        Row[] paramVlues = ruleParameterHeadResults();
        String paramValue = "";
        int deptID = 0;
        int paramID = 0;
        for (Row param : paramVlues) {
            if ("$TltSellThruDOGs".equals(param.getAttribute("ParamName").toString())) {
                paramID = Integer.parseInt(param.getAttribute("ParamId").toString());
                deptID = Integer.parseInt(row.getAttribute("Department").toString());
                paramValue = ParameterValuesResult(paramID, deptID);

                break;
            }
        }
        setParameterValuesColorChange(paramID,valueChangeEvent, paramValue, deptID);
        setContextAttribute(paramID, valueChangeEvent, paramValue, deptID);
    }

    public void ATSWSAgeAttributes(ValueChangeEvent valueChangeEvent) {
        Row row = makeRowCurrent(valueChangeEvent);

        Row[] paramVlues = ruleParameterHeadResults();
        String paramValue = "";
        int deptID = 0;
        int paramID = 0;
        for (Row param : paramVlues) {
            if ("$ATSWSAge".equals(param.getAttribute("ParamName").toString())) {
                paramID = Integer.parseInt(param.getAttribute("ParamId").toString());
                deptID = Integer.parseInt(row.getAttribute("Department").toString());
                paramValue = ParameterValuesResult(paramID, deptID);

                break;
            }
        }
        setParameterValuesColorChange(paramID,valueChangeEvent, paramValue, deptID);
        setContextAttribute(paramID, valueChangeEvent, paramValue, deptID);
    }
   
    public void ATSWLessDOGs(ValueChangeEvent valueChangeEvent) {
        Row row = makeRowCurrent(valueChangeEvent);

        Row[] paramVlues = ruleParameterHeadResults();
        String paramValue = "";
        int deptID = 0;
        int paramID = 0;
        for (Row param : paramVlues) {
            if ("$ATSWLessDOGs".equals(param.getAttribute("ParamName").toString())) {
                paramID = Integer.parseInt(param.getAttribute("ParamId").toString());
                deptID = Integer.parseInt(row.getAttribute("Department").toString());
                paramValue = ParameterValuesResult(paramID, deptID);

                break;
            }
        }
        setParameterValuesColorChange(paramID,valueChangeEvent, paramValue, deptID);
        setContextAttribute(paramID, valueChangeEvent, paramValue, deptID);
    }

    public void ATSWPerformance(ValueChangeEvent valueChangeEvent) {
        Row row = makeRowCurrent(valueChangeEvent);

        Row[] paramVlues = ruleParameterHeadResults();
        String paramValue = "";
        int deptID = 0;
        int paramID = 0;
        for (Row param : paramVlues) {
            if ("$ATSWPerformance".equals(param.getAttribute("ParamName").toString())) {
                paramID = Integer.parseInt(param.getAttribute("ParamId").toString());
                deptID = Integer.parseInt(row.getAttribute("Department").toString());
                paramValue = ParameterValuesResult(paramID, deptID);

                break;
            }
        }
        setParameterValuesColorChange(paramID,valueChangeEvent, paramValue, deptID);
        setContextAttribute(paramID, valueChangeEvent, paramValue, deptID);
    }

    public void TltSellThruPerformanceAttributes(ValueChangeEvent valueChangeEvent) {
        Row row = makeRowCurrent(valueChangeEvent);

        Row[] paramVlues = ruleParameterHeadResults();
        String paramValue = "";
        int deptID = 0;
        int paramID = 0;
        for (Row param : paramVlues) {
            if ("$TltSellThruPerformance".equals(param.getAttribute("ParamName").toString())) {
                paramID = Integer.parseInt(param.getAttribute("ParamId").toString());
                deptID = Integer.parseInt(row.getAttribute("Department").toString());
                paramValue = ParameterValuesResult(paramID, deptID);

                break;
            }
        }
        setParameterValuesColorChange(paramID,valueChangeEvent, paramValue, deptID);
        setContextAttribute(paramID, valueChangeEvent, paramValue, deptID);
    }

    public void OnOrderAgeAttributes(ValueChangeEvent valueChangeEvent) {
        Row row = makeRowCurrent(valueChangeEvent);

        Row[] paramVlues = ruleParameterHeadResults();
        String paramValue = "";
        int deptID = 0;
        int paramID = 0;
        for (Row param : paramVlues) {
            if ("$OnOrderAge".equals(param.getAttribute("ParamName").toString())) {
                paramID = Integer.parseInt(param.getAttribute("ParamId").toString());
                deptID = Integer.parseInt(row.getAttribute("Department").toString());
                paramValue = ParameterValuesResult(paramID, deptID);

                break;
            }
        }
        setParameterValuesColorChange(paramID,valueChangeEvent, paramValue, deptID);
        setContextAttribute(paramID, valueChangeEvent, paramValue, deptID);
    }

    public void WarehouseOnOrderAttributes(ValueChangeEvent valueChangeEvent) {
        Row row = makeRowCurrent(valueChangeEvent);

        Row[] paramVlues = ruleParameterHeadResults();
        String paramValue = "";
        int deptID = 0;
        int paramID = 0;
        for (Row param : paramVlues) {
            if ("$WarehouseOnOrder".equals(param.getAttribute("ParamName").toString())) {
                paramID = Integer.parseInt(param.getAttribute("ParamId").toString());
                deptID = Integer.parseInt(row.getAttribute("Department").toString());
                paramValue = ParameterValuesResult(paramID, deptID);

                break;
            }
        }
        setParameterValuesColorChange(paramID,valueChangeEvent, paramValue, deptID);
        setContextAttribute(paramID, valueChangeEvent, paramValue, deptID);
    }

    public void StoreOnOrderAttributes(ValueChangeEvent valueChangeEvent) {
        Row row = makeRowCurrent(valueChangeEvent);

        Row[] paramVlues = ruleParameterHeadResults();
        String paramValue = "";
        int deptID = 0;
        int paramID = 0;
        for (Row param : paramVlues) {
            if ("$StoreOnOrder".equals(param.getAttribute("ParamName").toString())) {
                paramID = Integer.parseInt(param.getAttribute("ParamId").toString());
                deptID = Integer.parseInt(row.getAttribute("Department").toString());
                paramValue = ParameterValuesResult(paramID, deptID);

                break;
            }
        }
        setParameterValuesColorChange(paramID,valueChangeEvent, paramValue, deptID);
        setContextAttribute(paramID, valueChangeEvent, paramValue, deptID);
    }

    public void SOHGreaterBrokenAttributes(ValueChangeEvent valueChangeEvent) {
        Row row = makeRowCurrent(valueChangeEvent);

        Row[] paramVlues = ruleParameterHeadResults();
        String paramValue = "";
        int deptID = 0;
        int paramID = 0;
        for (Row param : paramVlues) {
            if ("$SOHGreaterBroken".equals(param.getAttribute("ParamName").toString())) {
                paramID = Integer.parseInt(param.getAttribute("ParamId").toString());
                deptID = Integer.parseInt(row.getAttribute("Department").toString());
                paramValue = ParameterValuesResult(paramID, deptID);

                break;
            }
        }
        setParameterValuesColorChange(paramID,valueChangeEvent, paramValue, deptID);
        setContextAttribute(paramID, valueChangeEvent, paramValue, deptID);
    }

    public void SOHLessBrokenAttributes(ValueChangeEvent valueChangeEvent) {
        Row row = makeRowCurrent(valueChangeEvent);

        Row[] paramVlues = ruleParameterHeadResults();
        String paramValue = "";
        int deptID = 0;
        int paramID = 0;
        for (Row param : paramVlues) {
            if ("$SOHLessBroken".equals(param.getAttribute("ParamName").toString())) {
                paramID = Integer.parseInt(param.getAttribute("ParamId").toString());
                deptID = Integer.parseInt(row.getAttribute("Department").toString());
                paramValue = ParameterValuesResult(paramID, deptID);

                break;
            }
        }
        setParameterValuesColorChange(paramID,valueChangeEvent, paramValue, deptID);
        setContextAttribute(paramID, valueChangeEvent, paramValue, deptID);
    }

    public void OnOrderBrokenAttributes(ValueChangeEvent valueChangeEvent) {
        Row row = makeRowCurrent(valueChangeEvent);

        Row[] paramVlues = ruleParameterHeadResults();
        String paramValue = "";
        int deptID = 0;
        int paramID = 0;
        for (Row param : paramVlues) {
            if ("$OnOrderBroken".equals(param.getAttribute("ParamName").toString())) {
                paramID = Integer.parseInt(param.getAttribute("ParamId").toString());
                deptID = Integer.parseInt(row.getAttribute("Department").toString());
                paramValue = ParameterValuesResult(paramID, deptID);

                break;
            }
        }
        setParameterValuesColorChange(paramID,valueChangeEvent, paramValue, deptID);
        setContextAttribute(paramID, valueChangeEvent, paramValue, deptID);
    }

    public void BaseRetailAttributes(ValueChangeEvent valueChangeEvent) {
        Row row = makeRowCurrent(valueChangeEvent);

        Row[] paramVlues = ruleParameterHeadResults();
        String paramValue = "";
        int deptID = 0;
        int paramID = 0;
        for (Row param : paramVlues) {
            if ("$BaseRetail".equals(param.getAttribute("ParamName").toString())) {
                paramID = Integer.parseInt(param.getAttribute("ParamId").toString());
                deptID = Integer.parseInt(row.getAttribute("Department").toString());
                paramValue = ParameterValuesResult(paramID, deptID);

                break;
            }
        }
        setParameterValuesColorChange(paramID,valueChangeEvent, paramValue, deptID);
        setContextAttribute(paramID, valueChangeEvent, paramValue, deptID);
    }

    public void CurrentRetailAttributes(ValueChangeEvent valueChangeEvent) {
        Row row = makeRowCurrent(valueChangeEvent);

        Row[] paramVlues = ruleParameterHeadResults();
        String paramValue = "";
        int deptID = 0;
        int paramID = 0;
        for (Row param : paramVlues) {
            if ("$CurrentRetail".equals(param.getAttribute("ParamName").toString())) {
                paramID = Integer.parseInt(param.getAttribute("ParamId").toString());
                deptID = Integer.parseInt(row.getAttribute("Department").toString());
                paramValue = ParameterValuesResult(paramID, deptID);

                break;
            }
        }
        setParameterValuesColorChange(paramID,valueChangeEvent, paramValue, deptID);
        setContextAttribute(paramID, valueChangeEvent, paramValue, deptID);
    }

    public void ATSWLAgeAttributes(ValueChangeEvent valueChangeEvent) {
        Row row = makeRowCurrent(valueChangeEvent);        
        Row[] paramVlues = ruleParameterHeadResults();
        String paramValue = "";
        int deptID = 0;
        int paramID = 0;
        for (Row param : paramVlues) {
            if ("$ATSWLAge".equals(param.getAttribute("ParamName").toString())) {
                paramID = Integer.parseInt(param.getAttribute("ParamId").toString());
                deptID = Integer.parseInt(row.getAttribute("Department").toString());
                paramValue = ParameterValuesResult(paramID, deptID);

                break;
            }
        }
        setParameterValuesColorChange(paramID,valueChangeEvent, paramValue, deptID);
        //ruleParameter.getBind_ATSWLAge().setInlineStyle("background-color: #ffaaaa;");
        setContextAttribute(paramID, valueChangeEvent, paramValue, deptID);
    }    

    private void setParameterValuesColorChange(int paramID, ValueChangeEvent valueChangeEvent,
                                               String paramValue, int deptID) {
        if (valueChangeEvent.getNewValue() != paramValue) {
            DCIteratorBinding departmentBinding = binding.findIteratorBinding("ParameterDepartment1Iterator");
            DCIteratorBinding paramBinding = binding.findIteratorBinding("Clr_Wksht_Rule_Param_Head_View1Iterator");
            Row[] paramVlues = paramBinding.getAllRowsInRange();
            ViewObject parameterVO = departmentBinding.getViewObject();
            Row[] ParameterAttributes = parameterVO.getAllRowsInRange();
            for (int k = 0; k < ParameterAttributes.length; k++) {
                ParameterDepartmentRowImpl row2 = (ParameterDepartmentRowImpl)ParameterAttributes[k];                
                if (Integer.parseInt(ParameterAttributes[k].getAttribute("Department").toString()) == deptID) {                    
                    //for(Row paramid: paramVlues){
                        //if(paramID==Integer.parseInt(paramid.getAttribute("ParamId").toString()))
                        if(paramID==17)
                            row2.setCurrentRetailColor("CurrentRetailY");
                        else if(paramID==16)
                            row2.setBaseRetailColor("BaseRetailY");
                        else if(paramID==14)
                            row2.setOnOrderBrokenColor("OnOrderBrokenY");
                        else if(paramID==13) 
                            row2.setSOHLessBrokenColor("SOHLessBrokenY");
                        else if(paramID==12) 
                            row2.setSOHGr8BrokenColor("SOHGreaterBrokenY");
                        else if(paramID==10)
                            row2.setStoreOnOrderColor("StoreOnOrderY");
                        else if(paramID==11)
                            row2.setOnOrderAgeColor("OnOrderAgeY");
                        else if(paramID==8) 
                            row2.setTltSellThruPerformanceColor("TltSellThruPerformanceY");
                        else if(paramID==7) 
                            row2.setATSWPerformanceColor("ATSWPerformanceY");
                        else if(paramID==6)
                            row2.setATSWLessDogsColor("ATSWLessDOGsY");
                        else if(paramID==5) 
                            row2.setATSWGr8DogsColor("ATSWGreaterDOGsY");
                        else if(paramID==1)
                            row2.setWareHouseColor("WarehouseSOHY");
                        else if(paramID==3) 
                            row2.setTltSellThruDogsColor("TltSellThruDOGsY");
                        else if(paramID==2)
                            row2.setStoreSOHColor("StoreSOHY");
                        else if(paramID==9)
                            row2.setWareHouseOnOrderColor("WarehouseOnOrderY");
                        else if(paramID==15)
                            row2.setParamValueChange("ATSWLAgeY");
                        else
                            row2.setATSWSAgeColor("ATSWSAgeY");                                            
                            
                        
                        break;
                    //}
                }
            }

        }
    }

    private Row makeRowCurrent(ValueChangeEvent valueChangeEvent) {
        RuleParameterBean ruleParameter = (RuleParameterBean)ADFContext.getCurrent().getViewScope().get("RuleParameterBean");
        oracle.jbo.domain.Number employeeId = (oracle.jbo.domain.Number)valueChangeEvent.getComponent().getAttributes().get("rowKey");
        JUCtrlHierBinding adfModel = (JUCtrlHierBinding)((CollectionModel)ruleParameter.getParameterTable().getValue()).getWrappedData();
        DCIteratorBinding dciter = adfModel.getDCIteratorBinding();
        NavigatableRowIterator nav = dciter.getNavigatableRowIterator();
        Object[] objKey = new Object[1];
        objKey[0] = employeeId;
        Key key = new Key(objKey);
        nav.setCurrentRow(nav.getRow(key));        
        NavigatableRowIterator nav2 = dciter.getNavigatableRowIterator();
        Row row = nav2.getCurrentRow();

        return row;
    }

    private Row[] ruleParameterHeadResults() {
        RuleParameterBean ruleParameter = (RuleParameterBean)ADFContext.getCurrent().getViewScope().get("RuleParameterBean");
        ruleParameter.getBind_Cancel().setDisabled(false);
        AdfFacesContext.getCurrentInstance().addPartialTarget(ruleParameter.getBind_Cancel());
        DCIteratorBinding paramBinding = binding.findIteratorBinding("Clr_Wksht_Rule_Param_Head_View1Iterator");
        Row[] paramVlues = paramBinding.getAllRowsInRange();
        return paramVlues;
    }

    private String ParameterValuesResult(int paramID, int deptID) {
        String paramValue = "";
        OperationBinding parameterSelected = BindingContext.getCurrent().getCurrentBindingsEntry().getOperationBinding("getParameterValues");
        parameterSelected.getParamsMap().put("paramID", paramID);
        parameterSelected.getParamsMap().put("deptID", deptID);
        parameterSelected.execute();
        Row selectedParameter = (Row)parameterSelected.getResult();
        if (selectedParameter != null)
            paramValue = selectedParameter.getAttribute("ParamValue").toString();

        return paramValue;
    }

    private void setContextAttribute(int paramID, ValueChangeEvent valueChangeEvent,
                                     String paramValue, int deptID) {
        RuleParameterBean ruleParameter = (RuleParameterBean)ADFContext.getCurrent().getViewScope().get("RuleParameterBean");
        DCIteratorBinding departmentBinding = binding.findIteratorBinding("ParameterDepartment1Iterator");
        FacesContext ctx = FacesContext.getCurrentInstance();
        ExternalContext ectx = FacesContext.getCurrentInstance().getExternalContext();
        HttpServletRequest request = (HttpServletRequest)ctx.getExternalContext().getRequest();
        HttpSession userSession = (HttpSession)ectx.getSession(true);
        ViewObject parameterVO = departmentBinding.getViewObject();
        Row[] ParameterAttributes = parameterVO.getAllRowsInRange();
        if(!valueChangeEvent.getNewValue().equals(paramValue)){           
            ADFContext.getCurrent().getPageFlowScope().put("setPopupEvent", "clear");
            request.setAttribute("setPopupEvent", "clear");
            userSession.setAttribute("setPopupEvent", "clear");
            ruleParameter.getBind_Cancel().setDisabled(false);
            AdfFacesContext.getCurrentInstance().addPartialTarget(ruleParameter.getBind_Cancel());
        }
        if(valueChangeEvent.getNewValue().equals(paramValue)) {
            for (int k = 0; k < ParameterAttributes.length; k++) {
                ParameterDepartmentRowImpl row2 = (ParameterDepartmentRowImpl)ParameterAttributes[k];
                if (Integer.parseInt(ParameterAttributes[k].getAttribute("Department").toString()) == deptID){
                    
                    if(paramID==17)
                        row2.setCurrentRetailColor("");
                    else if(paramID==16)
                        row2.setBaseRetailColor("");
                    else if(paramID==14)
                        row2.setOnOrderBrokenColor("");
                    else if(paramID==13) 
                        row2.setSOHLessBrokenColor("");
                    else if(paramID==12) 
                        row2.setSOHGr8BrokenColor("");
                    else if(paramID==10)
                        row2.setStoreOnOrderColor("");
                    else if(paramID==11)
                        row2.setOnOrderAgeColor("");
                    else if(paramID==8) 
                        row2.setTltSellThruPerformanceColor("");
                    else if(paramID==7) 
                        row2.setATSWPerformanceColor("");
                    else if(paramID==6)
                        row2.setATSWLessDogsColor("");
                    else if(paramID==5) 
                        row2.setATSWGr8DogsColor("");
                    else if(paramID==1)
                        row2.setWareHouseColor("");
                    else if(paramID==3) 
                        row2.setTltSellThruDogsColor("");
                    else if(paramID==2)
                        row2.setStoreSOHColor("");
                    else if(paramID==9)
                        row2.setWareHouseOnOrderColor("");
                    else if(paramID==15)
                        row2.setParamValueChange("");
                    else
                        row2.setATSWSAgeColor("");                                            
                    
                    break;
                }
            }
            userSession.removeAttribute("setPopupEvent");
            ruleParameter.getBind_Cancel().setDisabled(true);
            AdfFacesContext.getCurrentInstance().addPartialTarget(ruleParameter.getBind_Cancel());
        }        
    }
    
    private String getValueFrmExpression(String data) {
        FacesContext fc = FacesContext.getCurrentInstance();
        Application app = fc.getApplication();
        ExpressionFactory elFactory = app.getExpressionFactory();
        ELContext elContext = fc.getELContext();
        ValueExpression valueExp = elFactory.createValueExpression(elContext,
                                                                   data,
                                                                   Object.class);
        String Message = null;
        Object obj = valueExp.getValue(elContext);
        if (obj != null) {
            Message = obj.toString();
        }
        return Message;
    }

    public void ParameterValuesvalidate(FacesContext facesContext, UIComponent uIComponent, Object object) {
        // Add event code here...
        if(object!=null){
            String name=object.toString();            
            if(!name.equals("0")){
                //String name=object.toString();
                String expression="^[0-9]+$";
                CharSequence inputStr=name;
                Pattern pattern=Pattern.compile(expression);
                Matcher matcher=pattern.matcher(inputStr);
                String msg="Please enter only Positive numeric value for Parameter Values";
                if(matcher.matches()){
                   
                }
                else{
                    throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR,msg,null));
                }
            }
            else{
                String msg="Please enter only Positive numeric value for priority";
                   throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR,msg,null));
            }
        }
    }
}

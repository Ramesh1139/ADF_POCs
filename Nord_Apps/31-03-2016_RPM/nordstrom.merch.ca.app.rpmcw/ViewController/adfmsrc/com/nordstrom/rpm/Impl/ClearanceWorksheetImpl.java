package com.nordstrom.rpm.Impl;

import com.nordstrom.rpm.Backing.ClearanceWorksheetMB;
import com.nordstrom.rpm.FacesMessageUtils;
import com.nordstrom.rpm.Service.ClearanceWorksheetService;

import java.sql.SQLException;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Calendar;


import java.util.Iterator;
import java.util.List;

import java.util.Map;

import java.util.logging.Level;

import javax.el.ELContext;
import javax.el.ExpressionFactory;
import javax.el.MethodExpression;

import javax.el.MethodNotFoundException;
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

import javax.servlet.http.HttpSession;

import oracle.adf.model.BindingContext;
import oracle.adf.model.OperationBinding;
import oracle.adf.model.binding.DCBindingContainer;

import oracle.adf.model.binding.DCIteratorBinding;
import oracle.adf.share.ADFContext;
import oracle.adf.share.logging.ADFLogger;
import oracle.adf.view.rich.component.rich.RichDialog;
import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.component.rich.data.RichColumn;
import oracle.adf.view.rich.component.rich.input.RichInputText;
import oracle.adf.view.rich.component.rich.input.RichSelectBooleanCheckbox;
import oracle.adf.view.rich.component.rich.layout.RichPanelTabbed;


import oracle.adf.view.rich.context.AdfFacesContext;

import oracle.adf.view.rich.event.DialogEvent;
import oracle.adf.view.rich.event.PopupFetchEvent;
import oracle.adf.view.rich.event.QueryEvent;
import oracle.adf.view.rich.model.FilterableQueryDescriptor;

import oracle.adfdt.model.objects.IteratorBinding;

import oracle.jbo.JboException;
import oracle.jbo.JboSQLException;
import oracle.jbo.Row;
import oracle.jbo.ViewObject;


import oracle.jbo.ViewObjectNotPreparedException;
import oracle.jbo.domain.Date;
import oracle.jbo.domain.Number;

import org.apache.myfaces.trinidad.event.DisclosureEvent;

/****************************************************************************
 * Class Name            : ClearanceWorksheetImpl.java
 * Language              : Java
 * Description           : This class implements the ClearanceWorksheetService interface.
 *                         The methods in this class implement the functionalities required for the Clearance_Worksheet.jsff page
 * Author                : Ramesh Chinta, Infosys Ltd.
 *                         Lasya Yadati, Infosys Ltd.
 * Date written          : 31-Aug-2015
 *
 * Modification History  :
 *
 * Description of change                 Date           Modified by
 * ---------------------                 ----           -----------
 *****************************************************************************/

public class ClearanceWorksheetImpl implements ClearanceWorksheetService {

    private static ADFLogger RPMlogger =
        ADFLogger.createADFLogger(CandidateRuleImpl.class);
    ExternalContext ectx =
        FacesContext.getCurrentInstance().getExternalContext();
    HttpSession userSession = (HttpSession)ectx.getSession(true);
    private double totalmdamountcandidate = 0.0;
    private double totalinventorycandidate = 0.0;
    private double totalMdAmountSavedTakes = 0.0;
    private int totalInventorySavedTakes = 0;
    private int cccount_totalsiftaken;
    private double pcamount_totalsiftaken;
    private double pcU_totalsiftaken;
    private int cccount_totalsTaken;
    private double pcamount_totalsTaken;
    private int pcunit__totalsTaken;
    private int ccCount_currentSelection_savedTakes;
    private double pcAmount_currentSelection_savedTakes;
    private int pcUnit_currentSelection_savedTakes;
    private String pageSizeCandidatesTab;
    private String pageSizeViewSavedTakesTab;
    //This variable is set to true if the no of MD candidates are less than or equal to 80000
    private boolean searchCountValid;
    private boolean newRtlErrorMsg = false;
    private boolean newMDPctErrorMsg = false;
    private boolean newEffectiveDateErrorMsg = false;
    private boolean restrictTakeAndSendToRPM=false;
    private Double newRtlValue;
    private Double newMdPctValue;
    private Date newEffectiveDateValue;
    private boolean newRtlMDPctEffectiveDateChange=false;
    private boolean allowTake = true; 
    private boolean allowUnTake = true; 
    private List departmentsSelected=new ArrayList();
    DCBindingContainer dbc = (DCBindingContainer)BindingContext.getCurrent().getCurrentBindingsEntry();

    public ClearanceWorksheetImpl() {
        super();
        RPMlogger.info("Creating new instance of Clearance Worksheet");

    }

    /* This is an action event method. This method is used to search the MD candidiates for the
 * selected department and zone in the Clearance Worksheet page.
 * This method is called when the Search button is clicked on the clearance worksheet page
 */

    public void searchActionListener(ActionEvent actionEvent) {
        RPMlogger.info("searchActionListener");
        RPMlogger.log(Level.INFO, "searchActionListener() Begins");
        ClearanceWorksheetMB clearanceWorksheet = (ClearanceWorksheetMB)ADFContext.getCurrent().getViewScope().get("ClearanceWorksheetMB");        
        //checking if the no of MD candidates under the selected department and zone exceeds 80000. 
        //If the no of records exceeds 80000, the user will be allowed to change the search criteria.        
        try
        {
        //resetting the totals and current selection sections
        resetCurrentSelectionViewSavedTakesTab(clearanceWorksheet);
        resetCurrentSelectionCandidateTab(clearanceWorksheet);        

        this.searchCountValid= validateMdSearchCount();
        if(searchCountValid==false){
            FacesMessageUtils.addErrorMessage("Search results exceed the maximum limit, please change the search criteria",
                                              FacesMessage.SEVERITY_INFO);
            return;
        }
            clearanceWorksheet.getPanelTab().setVisible(true); // The visibility of the panel tab is set to true(panel tab displays the MD candidates. It has two tabs.)            
            rollbackForClearButton(); 
            DCIteratorBinding dib = dbc.findIteratorBinding("Clr_Wksht_Rule_Item_View1Iterator");
            ViewObject voRuleItems = dib.getViewObject(); //Fetching the Clr_Wksht_Rule_Item_View1 View Object            
            //getting the selected zone value           
            DCIteratorBinding dib1 = dbc.findIteratorBinding("Zone_View1Iterator");
            ViewObject viewObj = dib1.getViewObject();
            Row row = viewObj.getCurrentRow();
            clearanceWorksheet.setZoneId(row.getAttribute("ZoneId").toString());
            DCIteratorBinding classIterator = dbc.findIteratorBinding("Class_Filter_LOV_View1Iterator");
            ViewObject classVO = classIterator.getViewObject();
            DCIteratorBinding deptIterator = dbc.findIteratorBinding("Filter_Department_View1Iterator");
            ViewObject deptVO = deptIterator.getViewObject();
            DCIteratorBinding subClassIterator = dbc.findIteratorBinding("Subclass_Filter_LOV_View1Iterator");
            ViewObject subClassVO = subClassIterator.getViewObject();
            DCIteratorBinding supplierNameIterator = dbc.findIteratorBinding("SupplierName_Filter_LOV_View1Iterator");
            ViewObject supplierNameVO = supplierNameIterator.getViewObject();
            DCIteratorBinding supplierLabelIterator = dbc.findIteratorBinding("SuppLabel_Filter_LOV_View1Iterator");
            ViewObject supplierLabelVO = supplierLabelIterator.getViewObject();
            DCIteratorBinding vpnIterator = dbc.findIteratorBinding("VPN_Filter_LOV_View1Iterator");
            ViewObject vpnVO = vpnIterator.getViewObject();
            DCIteratorBinding itemIterator = dbc.findIteratorBinding("Item_Filter_LOV_View1Iterator");
            ViewObject itemVO = itemIterator.getViewObject();
            DCIteratorBinding supplierColorIterator = dbc.findIteratorBinding("SuppColor_Filter_LOV_View1Iterator");
            ViewObject supplierColorVO = supplierColorIterator.getViewObject();
            DCIteratorBinding divisionIterator = dbc.findIteratorBinding("Filter_Division_View1Iterator");
            ViewObject divisionVO = divisionIterator.getViewObject();
            DCIteratorBinding NRFColorIterator = dbc.findIteratorBinding("NRFColor_Filter_LOV1Iterator");
            ViewObject NRFColorVO = NRFColorIterator.getViewObject();
            DCIteratorBinding deptAccess = dbc.findIteratorBinding("Clr_Wksht_Dept_AccessView1Iterator");
            if (clearanceWorksheet.getCandidateTab().isDisclosed()) {
                clearanceWorksheet.getBind_CandidateBox().setVisible(true); //The totals if taken and current selection sections are made visible if the candidate tab is disclosed
                voRuleItems.setWhereClause("dept in " + clearanceWorksheet.getDeptList() +
                                  "and zone = " + clearanceWorksheet.getZoneId() +
                                  "and upper(status)='N'"); //Performing the search operation on the view object when candidate tab is opened
              filterAttributeValues(clearanceWorksheet, 'N', classVO, deptVO, subClassVO, supplierNameVO, supplierLabelVO, 
                                    vpnVO, itemVO, supplierColorVO, divisionVO, NRFColorVO);              
            } else if (clearanceWorksheet.getViewSavedTakesTab().isDisclosed()) {
                voRuleItems.setWhereClause("dept in " + clearanceWorksheet.getDeptList() +
                                  "and zone = " + clearanceWorksheet.getZoneId() +
                                  "and upper(status)!='N'"); //Performing the search operation on the view object when View saved takes tab is opened
              filterAttributeValues(clearanceWorksheet, 'T', classVO, deptVO, subClassVO, supplierNameVO, supplierLabelVO, 
                                    vpnVO, itemVO, supplierColorVO, divisionVO, NRFColorVO);              
            }
            voRuleItems.executeQuery();                                                                                                                       
            clearanceWorksheet.getBt1().resetStampState();
            AdfFacesContext.getCurrentInstance().addPartialTarget(clearanceWorksheet.getBt1());
            clearanceWorksheet.getBt2().resetStampState();
            AdfFacesContext.getCurrentInstance().addPartialTarget(clearanceWorksheet.getBt2());                
            generateSavedListCandidateTab(userSession);
            generateSavedListViewSavedTakesTab(userSession);
            BindingContext.getCurrent().getCurrentBindingsEntry().getOperationBinding("getLoginUsercandidte").execute();
            BindingContext.getCurrent().getCurrentBindingsEntry().getOperationBinding("getLoginUserSavedtab").execute();            
            OperationBinding refresh_Dept_Access = (OperationBinding)BindingContext.getCurrent().getCurrentBindingsEntry().getOperationBinding("refreshDepartmentsSearch");
            refresh_Dept_Access.getParamsMap().put("userID", userSession.getAttribute("userName"));
            refresh_Dept_Access.getParamsMap().put("deptAccessIterator", "Clr_Wksht_Dept_AccessView1Iterator");
            refresh_Dept_Access.execute();                             
            String selectedDepts[];            
            selectedDepts = clearanceWorksheet.getDeptList().replace("(", "").replace(")", "").split(",");            
            if(selectedDepts != null){
                departmentsSelected.clear();
                for(int j=0;j<selectedDepts.length;j++){
                    departmentsSelected.add(selectedDepts[j].trim());
                }
            }            
            OperationBinding Dept_Access = (OperationBinding)BindingContext.getCurrent().getCurrentBindingsEntry().getOperationBinding("userLevelDeptAccess");
            Dept_Access.getParamsMap().put("deptList", departmentsSelected);            
            Dept_Access.getParamsMap().put("deptRecord", clearanceWorksheet.getDeptList());            
            Dept_Access.getParamsMap().put("deptAccessIterator", "Clr_Wksht_Dept_AccessView1Iterator");
            Dept_Access.execute();            
            boolean access = Boolean.valueOf(Dept_Access.getResult().toString());
            ADFContext.getCurrent().getRequestScope().put("deptAccessDenied", access);
            Row[] deptRows = deptAccess.getAllRowsInRange();
            for(Row deptRow: deptRows){            
                if(deptRows.length >= 1 && !userSession.getAttribute("userName").toString().equalsIgnoreCase(deptRow.getAttribute("UserId").toString()) && access){
                        RichPopup.PopupHints hints = new RichPopup.PopupHints();
                        clearanceWorksheet.getBind_DeptAccess_PopUp().show(hints);  
                       ADFContext.getCurrent().getViewScope().put("deptAccess", access);                       
                       enableCandidateViewSaveTakeButtons(clearanceWorksheet, true);
                       ADFContext.getCurrent().getViewScope().put("disableNewEffDateRetailMdPct", true);
                        break;
                   }
                else{                    
                    enableCandidateViewSaveTakeButtons(clearanceWorksheet, false);
                    ADFContext.getCurrent().getViewScope().put("deptAccess", false);
                    ADFContext.getCurrent().getViewScope().put("disableNewEffDateRetailMdPct", false);
                }           
            }            
            System.runFinalization();

        } catch (ViewObjectNotPreparedException vnpe) {
            vnpe.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        RPMlogger.log(Level.INFO, "searchActionListener() Ends");
    }
    
    private void resetCurrentSelectionCandidateTab(ClearanceWorksheetMB clearanceWorksheet){
        clearanceWorksheet.setTotalRowsselectedcandidate(0);
        clearanceWorksheet.setSummdamountcandidate(0);
        clearanceWorksheet.setSuminventorycandidate(0);
        this.totalmdamountcandidate = 0;
        this.totalinventorycandidate = 0;
        clearanceWorksheet.getBind_headerCheckBox_candidateTab().setValue(false);                    
        clearanceWorksheet.getFtseqidlist().clear();
        clearanceWorksheet.setUpdateCount(0); // to disable the take button once the take action is done and if no records are selected
        clearanceWorksheet.getBt1().resetStampState();
        AdfFacesContext.getCurrentInstance().addPartialTarget(clearanceWorksheet.getBt1());
        resetAll();
    }

    private void filterAttributeValues(ClearanceWorksheetMB clearanceWorksheet, char status, ViewObject classVO, ViewObject deptVO,
                                       ViewObject subClassVO, ViewObject supplierNameVO, ViewObject supplierLabelVO,
                                        ViewObject vpnVO, ViewObject itemVO, ViewObject supplierColorVO, ViewObject divisionVO, 
                                       ViewObject NRFColorVO){
        if(status == 'N'){
            classVO.setWhereClause("dept in" +clearanceWorksheet.getDeptList()+ "and upper(status)='"+ status +"'");             
            subClassVO.setWhereClause("dept in" +clearanceWorksheet.getDeptList()+ "and upper(status)='"+ status +"'");            
            supplierNameVO.setWhereClause("supp_name is not null and dept in" +clearanceWorksheet.getDeptList()+ "and upper(status)='"+ status +"'");            
            supplierLabelVO.setWhereClause("supp_label is not null and dept in" +clearanceWorksheet.getDeptList()+ "and upper(status)='"+ status +"'"); 
            vpnVO.setWhereClause("vpn is not null and dept in" +clearanceWorksheet.getDeptList()+ "and upper(status)='"+ status +"'");   
            itemVO.setWhereClause("item is not null and dept in" +clearanceWorksheet.getDeptList()+ "and upper(status)='"+ status +"'");
            supplierColorVO.setWhereClause("supp_color is not null and dept in" +clearanceWorksheet.getDeptList()+ "and upper(status)='"+ status +"'");            
            NRFColorVO.setWhereClause("dept in" +clearanceWorksheet.getDeptList()+ "and upper(status)='"+ status +"'");            
        }
        else{
            classVO.setWhereClause("dept in" +clearanceWorksheet.getDeptList()+ "and upper(status)!='N'");             
            subClassVO.setWhereClause("dept in" +clearanceWorksheet.getDeptList()+ "and upper(status)!='N'");   
            supplierNameVO.setWhereClause("supp_name is not null and dept in" +clearanceWorksheet.getDeptList()+ "and upper(status)!='N'");
            supplierLabelVO.setWhereClause("supp_label is not null and dept in" +clearanceWorksheet.getDeptList()+ "and upper(status)!='N'");
            vpnVO.setWhereClause("vpn is not null and dept in" +clearanceWorksheet.getDeptList()+ "and upper(status)!='N'");  
            itemVO.setWhereClause("item is not null and dept in" +clearanceWorksheet.getDeptList()+ "and upper(status)!='N'");    
            supplierColorVO.setWhereClause("supp_color is not null and dept in" +clearanceWorksheet.getDeptList()+ "and upper(status)!='N'");
            NRFColorVO.setWhereClause("dept in" +clearanceWorksheet.getDeptList()+ "and upper(status)!='N'");            
        }
        deptVO.setWhereClause("Department in" +clearanceWorksheet.getDeptList());
        divisionVO.setWhereClause("dept in" +clearanceWorksheet.getDeptList());  
        classVO.executeQuery();                
        subClassVO.executeQuery();
        supplierNameVO.executeQuery();
        supplierLabelVO.executeQuery();
        vpnVO.executeQuery();
        itemVO.executeQuery();
        supplierColorVO.executeQuery();        
        NRFColorVO.executeQuery();
        deptVO.executeQuery();
        divisionVO.executeQuery();
    }
    private void enableCandidateViewSaveTakeButtons(ClearanceWorksheetMB clearanceWorksheet, boolean status){
        clearanceWorksheet.getBind_SaveCandidates().setDisabled(status);
        AdfFacesContext.getCurrentInstance().addPartialTarget(clearanceWorksheet.getBind_SaveCandidates()); 
        clearanceWorksheet.getBind_SaveCandidates2().setDisabled(status);
        AdfFacesContext.getCurrentInstance().addPartialTarget(clearanceWorksheet.getBind_SaveCandidates2());
        clearanceWorksheet.getBind_rowCheckbox_candidateTab().setDisabled(status);
        AdfFacesContext.getCurrentInstance().addPartialTarget(clearanceWorksheet.getBind_rowCheckbox_candidateTab());
        clearanceWorksheet.getBind_headerCheckBox_candidateTab().setDisabled(status);
        AdfFacesContext.getCurrentInstance().addPartialTarget(clearanceWorksheet.getBind_headerCheckBox_candidateTab()); 
        clearanceWorksheet.getBind_SaveTakesCandidates().setDisabled(status);
        AdfFacesContext.getCurrentInstance().addPartialTarget(clearanceWorksheet.getBind_SaveTakesCandidates());
        clearanceWorksheet.getBind_SaveTakesCandidates2().setDisabled(status);
        AdfFacesContext.getCurrentInstance().addPartialTarget(clearanceWorksheet.getBind_SaveTakesCandidates2());              
        clearanceWorksheet.getBind_rowCheckbox_ViewSavedTakesTab().setDisabled(status);
        AdfFacesContext.getCurrentInstance().addPartialTarget(clearanceWorksheet.getBind_rowCheckbox_ViewSavedTakesTab()); 
        clearanceWorksheet.getBind_headerCheckBox_SavedTakesTab().setDisabled(status);
        AdfFacesContext.getCurrentInstance().addPartialTarget(clearanceWorksheet.getBind_headerCheckBox_SavedTakesTab());
    }
    /*
 * This is validation method.
 * This method is called from the searchActionListener method.
 * This method will check if the no of MD under the selected department and zone exceed 80000 or not.
 * Return type is false when MDs exceed 80000 and return type is true if the no of MD candidates are less than or equal to 80000
 */

    public boolean validateMdSearchCount() {
        boolean searchFlag = false;
        ClearanceWorksheetMB clearanceWorksheet =
            (ClearanceWorksheetMB)ADFContext.getCurrent().getViewScope().get("ClearanceWorksheetMB");
        try {            
            DCIteratorBinding dib = dbc.findIteratorBinding("Clr_Wksht_Rule_Item_View1Iterator");
            ViewObject vo = dib.getViewObject(); //Fetching the Clr_Wksht_Rule_Item_View1 View Object

            //getting the selected zone value            
            DCIteratorBinding dib1 = dbc.findIteratorBinding("Zone_View1Iterator");
            ViewObject viewObj = dib1.getViewObject();
            Row row = viewObj.getCurrentRow();
            clearanceWorksheet.setZoneId(row.getAttribute("ZoneId").toString());
            vo.setWhereClause("dept in " + clearanceWorksheet.getDeptList() +
                              "and zone = " + clearanceWorksheet.getZoneId());
            vo.executeQuery();
            //if the MD candidates exceed 80000, search operation is not allowed
            if (vo.getRowCount() > 80000) 
                searchFlag = false;
            else
                searchFlag = true;
            
            vo.executeEmptyRowSet();

        } catch (ViewObjectNotPreparedException vnpe) {
            vnpe.printStackTrace();
        } catch (JboSQLException jse) {
            jse.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return searchFlag;
    }
    /*
 * This is a ValueChange Event method.
 * This method is called when the divisions are selected in the division dropdown.
 * This method is used to populate the department dropdown menu as per the selected division values.
 * If no divisions are selected, all the departments are displayed in the department dropdown menu.
 */

    public void getDeptFrmDivisionVCL(ValueChangeEvent valueChangeEvent) {
        RPMlogger.info("getDeptFrmDivisionVCL");
        RPMlogger.log(Level.INFO, "getDeptFrmDivisionVCL() Begins");
        ClearanceWorksheetMB clearanceWorksheet =
            (ClearanceWorksheetMB)ADFContext.getCurrent().getViewScope().get("ClearanceWorksheetMB");
        try {
            clearanceWorksheet.getDivisionValuesSelected().clear();
            if (valueChangeEvent.getNewValue() != null) {
                String divisionValue = valueChangeEvent.getNewValue().toString();
                String[] str = divisionValue.replace("[", "").replace("]", "").trim().split(",");                
                DCIteratorBinding dci = dbc.findIteratorBinding("Division_View1Iterator");
                ViewObject vo = dci.getViewObject();
                for (int i = 0; i < str.length; i++) {
                    Row rows = vo.getRowAtRangeIndex(Integer.parseInt(str[i].trim()));
                    clearanceWorksheet.setDivision(rows.getAttribute("Division").toString());
                    clearanceWorksheet.getDivisionValuesSelected().add(clearanceWorksheet.getDivision());
                }
            }
            String divisionId = clearanceWorksheet.getDivisionValuesSelected().toString().replace("[",
                                                                                  "").replace("]",
                                                                                              "");           
            DCIteratorBinding dcib = dbc.findIteratorBinding("Department_View1Iterator");
            ViewObject viewObj = dcib.getViewObject();
            if (!divisionId.equals("")) {
                viewObj.setWhereClause("division in (" + divisionId + ")");
                clearanceWorksheet.getBind_Dept().setValue("");
            } else {
                viewObj.setWhereClause("division is not null");
                clearanceWorksheet.getBind_Dept().setValue("");
            }
            viewObj.executeQuery();            

        } catch (PropertyNotFoundException pnfe) {
            pnfe.printStackTrace();
        } catch (ArrayIndexOutOfBoundsException aiobe) {
            aiobe.printStackTrace();

        } catch (Exception e) {
            e.printStackTrace();
        }
        RPMlogger.log(Level.INFO, "getDeptFrmDivisionVCL() Ends");
    }

    /*
 * This is a value change event method.
 * This method is called when the department values are selected in the department dropdown menu.
 * This method is used to fetch the values of the selected departments in the department dropdown menu.
 * These values are used to search for the MD candidates in both the tabs.
 */

    public void getDepartmentVCL(ValueChangeEvent valueChangeEvent) {
        RPMlogger.info("getDepartmentVCL");
        RPMlogger.log(Level.INFO, "getDepartmentVCL() Begins");
        ClearanceWorksheetMB clearanceWorksheet =
            (ClearanceWorksheetMB)ADFContext.getCurrent().getViewScope().get("ClearanceWorksheetMB");
        try {
            if (valueChangeEvent.getNewValue() != null) {
                clearanceWorksheet.getDeptValuesSelected().clear();
                String index = valueChangeEvent.getNewValue().toString();
                String[] str = index.replace("[", "").replace("]", "").trim().split(",");               
                DCIteratorBinding dci = dbc.findIteratorBinding("Department_View1Iterator");
                ViewObject vo = dci.getViewObject();
                for (int i = 0; i < str.length; i++) {
                    Row rows = vo.getRowAtRangeIndex(Integer.parseInt(str[i].trim()));
                    clearanceWorksheet.setDept(rows.getAttribute("Department").toString());
                    clearanceWorksheet.getDeptValuesSelected().add(clearanceWorksheet.getDept());
                    clearanceWorksheet.setDeptList(clearanceWorksheet.getDeptValuesSelected().toString().replace("[",
                                                                                                                 "(").replace("]",
                                                                                                                              ")"));
                }                
            }            
        } catch (ViewObjectNotPreparedException vnpe) {
            vnpe.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        RPMlogger.log(Level.INFO, "getDepartmentVCL() Ends");
    }

    /*
 * This is a ValueChange Event method.
 * This method is called when the zone group values are selected in the zone group dropdown.
 * This method is used to populate the zone dropdown menu as per the selected zone group values.
 * If no zone groups are selected, all the zones are displayed in the zone dropdown menu.
 */

    public void getZoneFromZoneGroupVCL(ValueChangeEvent valueChangeEvent) {
        RPMlogger.info("getZoneFromZoneGroupVCL");
        RPMlogger.log(Level.INFO, "getZoneFromZoneGroupVCL() Begins");
        ClearanceWorksheetMB clearanceWorksheet =
            (ClearanceWorksheetMB)ADFContext.getCurrent().getViewScope().get("ClearanceWorksheetMB");

        try {
            if (valueChangeEvent.getNewValue() != null) {
                int i = Integer.parseInt(valueChangeEvent.getNewValue() + "");                
                DCIteratorBinding dci = dbc.findIteratorBinding("ZoneGroup_View1Iterator");
                ViewObject vo = dci.getViewObject();
                Row row = vo.getRowAtRangeIndex(i);
                clearanceWorksheet.setZoneGroupId(row.getAttribute(0).toString());
                DCIteratorBinding dci1 = dbc.findIteratorBinding("Zone_View1Iterator");
                ViewObject vo1 = dci1.getViewObject();
                vo1.setWhereClause("zone_group_id = " +clearanceWorksheet.getZoneGroupId());
                vo1.executeQuery();
            }

        } catch (ViewObjectNotPreparedException vnpe) {
            vnpe.printStackTrace();
        } catch (JboSQLException jse) {
            jse.printStackTrace();
        }
        RPMlogger.log(Level.INFO, "getZoneFromZoneGroupVCL() Ends");
    }

    /*
 * This is a value change event method.
 * This method is called when the zone values are selected in the zone dropdown menu.
 * This method is used to fetch the values of the selected zones in the zone dropdown menu.
 * These values are used to search for the MD candidates in both the tabs.
 */

    public void getZoneVCL(ValueChangeEvent valueChangeEvent) {
        RPMlogger.info("getZoneVCL");
        RPMlogger.log(Level.INFO, "getZoneVCL() Begins");
        ClearanceWorksheetMB clearanceWorksheet =
            (ClearanceWorksheetMB)ADFContext.getCurrent().getViewScope().get("ClearanceWorksheetMB");
        try {

            if (valueChangeEvent.getNewValue() != null) {
                int i = Integer.parseInt(valueChangeEvent.getNewValue() + "");

                DCBindingContainer dcb =
                    (DCBindingContainer)BindingContext.getCurrent().getCurrentBindingsEntry();
                DCIteratorBinding dci =
                    dcb.findIteratorBinding("Zone_View1Iterator");
                ViewObject vo = dci.getViewObject();
                Row row = vo.getRowAtRangeIndex(0);
                clearanceWorksheet.setZoneId(row.getAttribute(1).toString());
            }

        } catch (ViewObjectNotPreparedException vnpe) {
            vnpe.printStackTrace();
        } catch (JboSQLException jse) {
            jse.printStackTrace();
        }
        RPMlogger.log(Level.INFO, "getZoneVCL() Ends");
    }

    /* This is a Disclosure Event method.
 * This method is used to display the totals if taken, current selection and bulk update sections when the candidate tab is opened
 * This method is called when the candidate tab is opened on the clearance worksheet page
 */

    public void candidatesTabDisclosureListener(DisclosureEvent disclosureEvent) {
        RPMlogger.info("candidatesTabDisclosureListener");
        RPMlogger.log(Level.INFO, "candidatesTabDisclosureListener() Begins");
        ClearanceWorksheetMB clearanceWorksheet =
            (ClearanceWorksheetMB)ADFContext.getCurrent().getViewScope().get("ClearanceWorksheetMB");

        try {

            //removing the applied filters on tab changing in the clearance worksheet screen
            filterReset();

            //checking if the no of MD candidates under the selected department and zone exceeds 80000.
            //If the no of records exceeds 80000, the user will be allowed to change the search criteria.
            if (searchCountValid == false) {
                return;
            }

            clearanceWorksheet.getBind_CandidateBox().setVisible(true);
            clearanceWorksheet.getBind_ViewSavedBox().setVisible(false);

            //clearanceWorksheet.setShowCandidateTab(clearanceWorksheet.getCandidateTab().isDisclosed());
            DCBindingContainer dbc =
                (DCBindingContainer)BindingContext.getCurrent().getCurrentBindingsEntry();
            DCIteratorBinding dib =
                dbc.findIteratorBinding("Clr_Wksht_Rule_Item_View1Iterator");
            ViewObject vo = dib.getViewObject();

            vo.setWhereClause("dept in " + clearanceWorksheet.getDeptList() +
                              "and zone = " + clearanceWorksheet.getZoneId() +
                              "and upper(status)='N'");
            // vo.setWhereClause("upper(status)='N'");
            vo.executeQuery();
            clearanceWorksheet.getBt1().resetStampState();
            AdfFacesContext.getCurrentInstance().addPartialTarget(clearanceWorksheet.getBt1());

            //making the header checkbox unchecked and the user switches the tab after selecting all records
            clearanceWorksheet.getBind_headerCheckBox_candidateTab().setValue(false);
            clearanceWorksheet.setSuminventorycandidate(0);
            clearanceWorksheet.setSummdamountcandidate(0.0);
            clearanceWorksheet.setTotalRowsselectedcandidate(0);
            clearanceWorksheet.getBind_headerCheckBox_SavedTakesTab().resetValue();
            System.runFinalization();
        } catch (ViewObjectNotPreparedException vnpe) {
            vnpe.printStackTrace();
        } catch (JboSQLException jse) {
            jse.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        RPMlogger.log(Level.INFO, "candidatesTabDisclosureListener() Ends");
    }

    /* This is a Disclosure Event method.
* This method is used to display the totals taken, current selection and bulk update sections when the view saved takes tab is opened
* This method is called when the view saved takes tab is opened on the clearance worksheet page
*/

    public void viewSavedTakesTabDisclosureListener(DisclosureEvent disclosureEvent) {
        RPMlogger.info("viewSavedTakesTabDisclosureListener");
        RPMlogger.log(Level.INFO,
                      "viewSavedTakesTabDisclosureListener() Begins");
        ClearanceWorksheetMB clearanceWorksheet =
            (ClearanceWorksheetMB)ADFContext.getCurrent().getViewScope().get("ClearanceWorksheetMB");
        try {
            //removing the applied filters on tab changing in the clearance worksheet screen
            filterReset();
            //checking if the no of MD candidates under the selected department and zone exceeds 80000.
            //If the no of records exceeds 80000, the user will be allowed to change the search criteria.
            if (this.searchCountValid == false) {
                return;
            }

            clearanceWorksheet.getBind_CandidateBox().setVisible(false);
            clearanceWorksheet.getBind_ViewSavedBox().setVisible(true);

            //clearanceWorksheet.setShowViewSavedTakesTab(clearanceWorksheet.getViewSavedTakesTab().isDisclosed());

            DCBindingContainer dbc =
                (DCBindingContainer)BindingContext.getCurrent().getCurrentBindingsEntry();
            DCIteratorBinding dib =
                dbc.findIteratorBinding("Clr_Wksht_Rule_Item_View1Iterator");
            ViewObject vo = dib.getViewObject();
            vo.setWhereClause("dept in " + clearanceWorksheet.getDeptList() +
                              "and zone = " + clearanceWorksheet.getZoneId() +
                              "and upper(status)!='N'");

            vo.executeQuery();
            clearanceWorksheet.getBt2().resetStampState();
            AdfFacesContext.getCurrentInstance().addPartialTarget(clearanceWorksheet.getBt2());
            clearanceWorksheet.getBind_headerCheckBox_candidateTab().resetValue();
            clearanceWorksheet.getBind_headerCheckBox_SavedTakesTab().setValue(false);
            clearanceWorksheet.setSumMdAmountSavedTakesTab(0.0);
            clearanceWorksheet.setSumInventorySavedTakesTab(0);
            clearanceWorksheet.setTotalRowsSelectedSavedTakesTab(0);
            System.runFinalization();
        } catch (Exception e) {
            e.printStackTrace();
        }
        RPMlogger.log(Level.INFO,
                      "viewSavedTakesTabDisclosureListener() Ends");
    }

    /* This is a valuechange event method.
 * This method is called when the header checkbox is checked/unchecked in the candiates tab
 * This method is used to select all the rows when the header checkbox is selected and deselect all the rows
 * when the header checkbox is deselected
 * This method also has calculations for the totals if taken and current selection sections
 */

    public void candidateTabHeaderCheckBoxVCL(ValueChangeEvent valueChangeEvent) {
        RPMlogger.info("fthcheckBoxValueChangeListener");
        RPMlogger.log(Level.INFO, "fthcheckBoxValueChangeListener() Begins");
        ClearanceWorksheetMB clearanceWorksheet =
            (ClearanceWorksheetMB)ADFContext.getCurrent().getViewScope().get("ClearanceWorksheetMB");                
        try {
            if (valueChangeEvent.getNewValue() != null) {
                clearanceWorksheet.getFtseqidlist().clear();
                this.totalmdamountcandidate = 0.0;
                this.totalinventorycandidate = 0;
               
                DCIteratorBinding dci = dbc.findIteratorBinding("Clr_Wksht_Rule_Item_View1Iterator");
                ViewObject vo = dci.getViewObject();
                //fetching the records whose status is 'N' (new MD candidates) for the selected department and zone
                vo.setWhereClause("upper(status)='N' and dept in " + clearanceWorksheet.getDeptList() +
                                  " and zone =" + clearanceWorksheet.getZoneId());
                vo.executeQuery();
                vo.first();
                Row row = vo.getCurrentRow();
                if (row != null) {
                    row.setAttribute("Mark",
                                     Boolean.parseBoolean(valueChangeEvent.getNewValue() +
                                                          ""));
                    Boolean checked = (Boolean)row.getAttribute("Mark");
                    if (checked) {
                        clearanceWorksheet.getFtseqidlist().add(row.getAttribute("SeqNo").toString());
                        this.totalmdamountcandidate += Double.parseDouble(row.getAttribute("MkdnAmount").toString());
                        this.totalinventorycandidate += Double.parseDouble(row.getAttribute("TotalQty").toString());
                    }
                    while (vo.hasNext()) {
                        row = vo.next();
                        row.setAttribute("Mark",
                                         Boolean.parseBoolean(valueChangeEvent.getNewValue() +
                                                              ""));
                        Boolean checkRows = (Boolean)row.getAttribute("Mark");
                        if (checkRows) {
                            clearanceWorksheet.getFtseqidlist().add(row.getAttribute("SeqNo").toString());
                            this.totalmdamountcandidate += Double.parseDouble(row.getAttribute("MkdnAmount").toString());
                            this.totalinventorycandidate += Double.parseDouble(row.getAttribute("TotalQty").toString());
                        }
                    }
                    Boolean checkStatus =
                        Boolean.parseBoolean(valueChangeEvent.getNewValue() +
                                             "");
                    if (!checkStatus) {
                        clearanceWorksheet.getFtseqidlist().clear();
                        this.totalmdamountcandidate = 0.0;
                        this.totalinventorycandidate = 0;
                    }
                    else
                        deptAccessEnablement(clearanceWorksheet);
                }
                AdfFacesContext.getCurrentInstance().addPartialTarget(clearanceWorksheet.getBt1());
                clearanceWorksheet.setSummdamountcandidate(totalmdamountcandidate);
                clearanceWorksheet.setSuminventorycandidate(totalinventorycandidate);
                clearanceWorksheet.setTotalRowsselectedcandidate(clearanceWorksheet.getFtseqidlist().size());                                    
                deptAccessComponentsEnablement(clearanceWorksheet);
            }
            System.runFinalization();
        } catch (ViewObjectNotPreparedException vnpe) {
            vnpe.printStackTrace();
        } catch (JboSQLException jse) {
            jse.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();


        }
        RPMlogger.log(Level.INFO, "fthcheckBoxValueChangeListener() Ends");
    }
    
    private void deptAccessEnablement(ClearanceWorksheetMB clearanceWorksheet){
        DCIteratorBinding dib = dbc.findIteratorBinding("Clr_Wksht_Dept_AccessView1Iterator");
        boolean access = false;
        int flag =0;
        Row[] deptRows = dib.getAllRowsInRange();
        if(deptRows.length >= 1){
            for(int i=0;i<clearanceWorksheet.getDeptValuesSelected().size();i++){                            
            for(Row row2: deptRows){               
                if(!userSession.getAttribute("userName").toString().equalsIgnoreCase(row2.getAttribute("UserId").toString()) &&
                        row2.getAttribute("Dept").equals(clearanceWorksheet.getDeptValuesSelected().get(i))){
                    access = true; 
                    flag = 1;
                    break;     
                }
                else
                    access = false;
                                                
            }
            ADFContext.getCurrent().getViewScope().put("deptAccess", access);
            if(flag == 1)
                break;
        }
        }
    }
    
    private void deptAccessComponentsEnablement(ClearanceWorksheetMB clearanceWorksheet){
        if(ADFContext.getCurrent().getViewScope().get("deptAccess") != null){
            if(ADFContext.getCurrent().getViewScope().get("deptAccess").equals(true))
                clearanceWorksheet.setUpdateCount(0);                                                                                 
            else{
                clearanceWorksheet.setUpdateCount(clearanceWorksheet.getFtseqidlist().size());
                clearanceWorksheet.getBind_SaveCandidates().setDisabled(false);
                AdfFacesContext.getCurrentInstance().addPartialTarget(clearanceWorksheet.getBind_SaveCandidates());            
                clearanceWorksheet.getBind_SaveCandidates2().setDisabled(false);
                AdfFacesContext.getCurrentInstance().addPartialTarget(clearanceWorksheet.getBind_SaveCandidates2());            
            }
        }
        else
            clearanceWorksheet.setUpdateCount(clearanceWorksheet.getFtseqidlist().size());
    }
    /*
 * This is a valuechange event method.
 * This method is called when the row checkbox in the table is checked/unchecked in the candiates tab
 * This method is used to display the totals if taken and current selection data in the candidate tab
 */

    public void candidateTabRowCheckBoxVCL(ValueChangeEvent valueChangeEvent) {
            RPMlogger.info("ftrowCheckValueChangeListener");
            RPMlogger.log(Level.INFO, "ftrowCheckValueChangeListener() Begins");
            ClearanceWorksheetMB clearanceWorksheet =
                (ClearanceWorksheetMB)ADFContext.getCurrent().getViewScope().get("ClearanceWorksheetMB");                    
            try {
                if (valueChangeEvent.getNewValue() != null &&
                    clearanceWorksheet.getFtheaderBstatus() != null &&
                    clearanceWorksheet.getFtheaderBstatus()) {
                    clearanceWorksheet.setFtheaderBstatus(Boolean.parseBoolean(valueChangeEvent.getNewValue() +
                                                                               ""));
                    clearanceWorksheet.getFtseqidlist().remove(clearanceWorksheet.getFtseqid().trim());
                    totalmdamountcandidate -= Double.parseDouble(clearanceWorksheet.getMdamountcandidate());
                    totalinventorycandidate -= Double.parseDouble(clearanceWorksheet.getInventory());
                }
                //start:checked Row count
                if (valueChangeEvent != null) {
                    resetAll();
                    /*ADFContext.getCurrent().getViewScope().put("NewRetail", null);
                    ADFContext.getCurrent().getViewScope().put("NewMDPercentage", null);
                    ADFContext.getCurrent().getViewScope().put("NewEffectiveDate", null);*/
                    boolean checked = Boolean.parseBoolean(valueChangeEvent.getNewValue() + "");
                    if (checked) {
                        if(!clearanceWorksheet.getFtseqidlist().contains(clearanceWorksheet.getFtseqid())){
                            clearanceWorksheet.getFtseqidlist().add(clearanceWorksheet.getFtseqid().trim());
                            this.totalmdamountcandidate += Double.parseDouble(clearanceWorksheet.getMdamountcandidate());
                            this.totalinventorycandidate += Double.parseDouble(clearanceWorksheet.getInventory());
                        }                         
                        deptAccessEnablement(clearanceWorksheet);

                    } else if (clearanceWorksheet.getFtseqidlist().contains(clearanceWorksheet.getFtseqid())) {
                        clearanceWorksheet.getFtseqidlist().remove(clearanceWorksheet.getFtseqid().trim());
                        totalmdamountcandidate -= Double.parseDouble(clearanceWorksheet.getMdamountcandidate());
                        totalinventorycandidate -= Double.parseDouble(clearanceWorksheet.getInventory());                        
                    }
                    //if none of the records are selected in the candidate tab, the PC amount and PC units are set to 0
                    if (clearanceWorksheet.getFtseqidlist().isEmpty()) {
                        totalmdamountcandidate = 0.0;
                        totalinventorycandidate = 0;                        
                        ADFContext.getCurrent().getViewScope().put("deptAccess", true);
                    }
                    clearanceWorksheet.setSummdamountcandidate(totalmdamountcandidate);
                    clearanceWorksheet.setSuminventorycandidate(totalinventorycandidate);
                    clearanceWorksheet.setTotalRowsselectedcandidate(clearanceWorksheet.getFtseqidlist().size());                   
                    //System.out.println("ADFContext.getCurrent().getViewScope().get(\"deptAccess\")"+ADFContext.getCurrent().getViewScope().get("deptAccess"));
                    deptAccessComponentsEnablement(clearanceWorksheet);                   
                    
                    System.runFinalization();

                }
            } catch (ViewObjectNotPreparedException vnpe) {
                vnpe.printStackTrace();
            } catch (JboSQLException jse) {
                jse.printStackTrace();
            }
            RPMlogger.log(Level.INFO, "ftrowCheckValueChangeListener() Ends");
        }

    /* This is a valuechange event method.
 * This method is called when the header checkbox is checked/unchecked in the view Saved takes tab
 * This method is used to select all the rows when the header checkbox is selected and deselect all the rows
 * when the header checkbox is deselected
 * This method also has calculations for the totals taken and the current selection sections in the view saved takes tab
 * */

    public void viewSavedTakesTabHeaderCheckBoxVCL(ValueChangeEvent valueChangeEvent) {
        RPMlogger.info("sthcheckBoxValueChangeListener");
        RPMlogger.log(Level.INFO, "sthcheckBoxValueChangeListener() Begins");
        ClearanceWorksheetMB clearanceWorksheet =
            (ClearanceWorksheetMB)ADFContext.getCurrent().getViewScope().get("ClearanceWorksheetMB");

        try {
            if (valueChangeEvent.getNewValue() != null) {
                clearanceWorksheet.getStseqidlist().clear(); //clearing the seq id list initially.
                this.totalMdAmountSavedTakes = 0.0;
                this.totalInventorySavedTakes = 0;               
                DCIteratorBinding dci = dbc.findIteratorBinding("Clr_Wksht_Rule_Item_View1Iterator");
                ViewObject vo = dci.getViewObject();
                //Row rows[] = vo.getFilteredRows("Mark", null);
                //fetching view saved takes tab candidates and seq no is populated in the seq id list only if status is taken 'T'
                vo.setWhereClause("upper(status)!='N' and dept in " +
                                  clearanceWorksheet.getDeptList() +
                                  " and zone =" +
                                  clearanceWorksheet.getZoneId()); //fetching the records whose status is 'T' (taken MD candidates)
                vo.executeQuery();
                vo.first();
                Row row = vo.getCurrentRow();
                if (row != null) {
                    if (row.getAttribute("Status").toString().equalsIgnoreCase("T")) {
                        row.setAttribute("Mark",
                                         Boolean.parseBoolean(valueChangeEvent.getNewValue() +
                                                              ""));
                        Boolean checked = (Boolean)row.getAttribute("Mark");
                        if (checked) {
                            clearanceWorksheet.getStseqidlist().add(row.getAttribute("SeqNo").toString());
                            this.totalMdAmountSavedTakes += Double.parseDouble(row.getAttribute("MkdnAmount").toString());
                            this.totalInventorySavedTakes += Integer.parseInt(row.getAttribute("TotalQty").toString());
                        }
                    }
                }
                while (vo.hasNext()) {
                    row = vo.next();
                    if (row.getAttribute("Status").toString().equalsIgnoreCase("T")) {
                        row.setAttribute("Mark",
                                         Boolean.parseBoolean(valueChangeEvent.getNewValue() +
                                                              ""));
                        Boolean checkRows = (Boolean)row.getAttribute("Mark");
                        if (checkRows) {
                            clearanceWorksheet.getStseqidlist().add(row.getAttribute("SeqNo").toString());
                            this.totalMdAmountSavedTakes += Double.parseDouble(row.getAttribute("MkdnAmount").toString());
                            this.totalInventorySavedTakes += Integer.parseInt(row.getAttribute("TotalQty").toString());
                        }
                    }
                }
                Boolean checkStatus = Boolean.parseBoolean(valueChangeEvent.getNewValue() + "");
                if (!checkStatus) {
                    clearanceWorksheet.getStseqidlist().clear();
                    this.totalMdAmountSavedTakes = 0.0;
                    this.totalInventorySavedTakes = 0;
                }
                AdfFacesContext.getCurrentInstance().addPartialTarget(clearanceWorksheet.getBt2());
                clearanceWorksheet.setSumMdAmountSavedTakesTab(totalMdAmountSavedTakes);
                clearanceWorksheet.setSumInventorySavedTakesTab(totalInventorySavedTakes);
                clearanceWorksheet.setTotalRowsSelectedSavedTakesTab(clearanceWorksheet.getStseqidlist().size());
                clearanceWorksheet.setUpdateCount1(clearanceWorksheet.getStseqidlist().size());
            }
            System.runFinalization();
        } catch (ViewObjectNotPreparedException vnpe) {
            vnpe.printStackTrace();
        } catch (JboSQLException jse) {
            jse.printStackTrace();
        }
        RPMlogger.log(Level.INFO, "sthcheckBoxValueChangeListener() Ends");
    }

    /* This is a valuechange event method.
 * This method is called when the row checkbox in the table is checked/unchecked in the view Saved takes tab
 * This method has calculations for the totals taken and the current selection sections in the view saved takes tab
 *
*/

    public void viewSavedTakesTabRowCheckBoxVCL(ValueChangeEvent valueChangeEvent) {
        RPMlogger.info("strowCheckValueChangeListener");
        RPMlogger.log(Level.INFO, "strowCheckValueChangeListener() Begins");
        // starting: Beloow if condition for selectAll/Deselect All condition
        ClearanceWorksheetMB clearanceWorksheet =
            (ClearanceWorksheetMB)ADFContext.getCurrent().getViewScope().get("ClearanceWorksheetMB");
        try {
            if (valueChangeEvent.getNewValue() != null &&
                clearanceWorksheet.getStheaderBstatus() != null &&
                clearanceWorksheet.getStheaderBstatus()) {
                clearanceWorksheet.setStheaderBstatus(Boolean.parseBoolean(valueChangeEvent.getNewValue() +
                                                                           ""));
                clearanceWorksheet.getStseqidlist().remove(clearanceWorksheet.getStseqid().trim());
                this.totalMdAmountSavedTakes -= Double.parseDouble(clearanceWorksheet.getMdAmountSavedTakesTab());
                this.totalInventorySavedTakes -= Integer.parseInt(clearanceWorksheet.getInventorySavedTakesTab());
            }

            // Ending: Above if condition for selectAll/Deselect All condition
            // Start:Select Get the Selected checkBox Sequence.
            if (valueChangeEvent.getNewValue() != null) {
                resetAll();
                /*ADFContext.getCurrent().getViewScope().put("NewSavedRetail", null);
                ADFContext.getCurrent().getViewScope().put("NewSavedMdPct", null);
                ADFContext.getCurrent().getViewScope().put("NewSavedEffectiveDate", null);*/
                boolean checked = Boolean.parseBoolean(valueChangeEvent.getNewValue() + "");
                if (checked) {
                    clearanceWorksheet.getStseqidlist().add(clearanceWorksheet.getStseqid().trim());
                    this.totalMdAmountSavedTakes += Double.parseDouble(clearanceWorksheet.getMdAmountSavedTakesTab());
                    this.totalInventorySavedTakes += Integer.parseInt(clearanceWorksheet.getInventorySavedTakesTab());
                    ADFContext.getCurrent().getViewScope().put("deptAccess", false);

                } else if (clearanceWorksheet.getStseqidlist().contains(clearanceWorksheet.getStseqid())) {
                    clearanceWorksheet.getStseqidlist().remove(clearanceWorksheet.getStseqid().trim());
                    this.totalMdAmountSavedTakes -= Double.parseDouble(clearanceWorksheet.getMdAmountSavedTakesTab());
                    this.totalInventorySavedTakes -= Integer.parseInt(clearanceWorksheet.getInventorySavedTakesTab());   
                    ADFContext.getCurrent().getViewScope().put("deptAccess", false);
                }
                //if none of the records are selected in the view saved takes tab
                if (clearanceWorksheet.getStseqidlist().isEmpty()) {
                    this.totalMdAmountSavedTakes = 0.0;
                    this.totalInventorySavedTakes = 0;   
                    ADFContext.getCurrent().getViewScope().put("deptAccess", true);
                }
                clearanceWorksheet.setSumMdAmountSavedTakesTab(totalMdAmountSavedTakes);
                clearanceWorksheet.setSumInventorySavedTakesTab(totalInventorySavedTakes);
                clearanceWorksheet.setTotalRowsSelectedSavedTakesTab(clearanceWorksheet.getStseqidlist().size());
                clearanceWorksheet.setUpdateCount1(clearanceWorksheet.getStseqidlist().size());
                System.runFinalization();
            }
        } catch (ViewObjectNotPreparedException vnpe) {
            vnpe.printStackTrace();
        } catch (JboSQLException jse) {
            jse.printStackTrace();
        }
        RPMlogger.log(Level.INFO, "strowCheckValueChangeListener() Ends");
    }

    /*
 * This is an action event method.
 * This method is called when the Update button is pressed on the clearance worksheet screen and when the candiate tab is opened
 * This method is used to update the new retail, new MD% and new effective date for the multiple MD candidates
 * which belong to the candidate tab.
 */

    public void candidateTabBulkUpdate(ActionEvent actionEvent) {
        RPMlogger.info("updateftBulk");
        RPMlogger.log(Level.INFO, "updateftBulk() Begins");
        ClearanceWorksheetMB clearanceWorksheet =
            (ClearanceWorksheetMB)ADFContext.getCurrent().getViewScope().get("ClearanceWorksheetMB");
        //validations for new retail and new MD % start here
        try {
            String seqNo =
                clearanceWorksheet.getFtseqidlist().toString().replace("[",
                                                                       "(").replace("]",
                                                                                    ")");
            Integer listSize = clearanceWorksheet.getFtseqidlist().size();           
            DCIteratorBinding dci = dbc.findIteratorBinding("Clr_Wksht_Rule_Item_View1Iterator");
            ViewObject vo = dci.getViewObject();
            vo.setWhereClause("seq_no in " + seqNo);

            vo.executeQuery();
            Double enteredRetail = 0.0, enteredMdPercent = 0.0;            
            Row row = null;           
            int validationCountOne = 0, validationCountTwo = 0, validationCountThree = 0, validationCountFour = 0, flag = 0, var = 0;            
            if (vo != null) {
                for (int i = 0; i < vo.getRowCount(); i++) {
                    if (var == 0) {
                        row = vo.first();
                        var = 1;
                    } else {
                        row = vo.next();
                    }
                    Double currentRetail = (Double)row.getAttribute("CurrentRetail");
                    Double baseRetail = (Double)row.getAttribute("BaseRetail");
                    /*
             * validation 1 for new retail
             * checking if the new retail is greater than the base retail
             */
                    if (clearanceWorksheet.getBind_newRetail_candidateTab_bulkUpdate().getValue() != null) {
                        enteredRetail = Double.parseDouble(clearanceWorksheet.getBind_newRetail_candidateTab_bulkUpdate().getValue().toString());
                        if (enteredRetail >= baseRetail) {
                            flag = 1;
                            validationCountOne = validationCountOne + 1;
                        }
                    }
                    /*
            * checking if New clearance retail is the same as previous clearance retail or New retail is higher than previous retail
            */
                    if (clearanceWorksheet.getBind_newRetail_candidateTab_bulkUpdate().getValue() != null) {
                        enteredRetail = Double.parseDouble(clearanceWorksheet.getBind_newRetail_candidateTab_bulkUpdate().getValue().toString());
                        if (enteredRetail >= currentRetail) {
                            flag = 1;
                            validationCountTwo = validationCountTwo + 1;
                        }
                    }

                    /*
                * validation 2 for MD %
                * checking if the new MD% is between 0 and 100
                */
                    if (clearanceWorksheet.getBind_newMdPercent_candidateTab_bulkUpdate().getValue() != null) {
                        enteredMdPercent = Double.parseDouble(clearanceWorksheet.getBind_newMdPercent_candidateTab_bulkUpdate().getValue().toString());
                        if (enteredMdPercent <= 0.0 ||
                            enteredMdPercent > 100.0) {
                            flag = 1;
                            validationCountThree = validationCountThree + 1;

                        }
                    }
                    /*
                * validation 3 for new effective date
                * checking if Clearance Effective Date is not greater than Current Date + 3 days
                */
                    if (clearanceWorksheet.getBind_nextEffectiveDate_canddiateTab_bulkUpdate().getValue() != null) {
                        //fetching the vdate as the current date                        
                        DCIteratorBinding dcib = dbc.findIteratorBinding("V_Date_View1Iterator");
                        ViewObject viewObj = dcib.getViewObject();
                        Row dateRow = viewObj.first();
                        Date currentDate = (Date)dateRow.getAttribute("Vdate"); // this is a oracle.jbo.domain.date
                        java.util.Date nextEffectiveDate = (java.util.Date)clearanceWorksheet.getBind_nextEffectiveDate_canddiateTab_bulkUpdate().getValue(); //this is a java.util.date

                        //converting java.util.date type next effective date to oracle.jbo.domain.date
                        long utilDateTime = nextEffectiveDate.getTime();
                        java.sql.Date sqlDateTime = new java.sql.Date(utilDateTime);
                        Date oracleDateTime = new Date(sqlDateTime);

                        //calculating the days difference between the new effective date and current date
                        long nextEffectiveDateTime = oracleDateTime.getValue().getTime();
                        long currentDateTime = currentDate.getValue().getTime();
                        long daysDiff =(nextEffectiveDateTime - currentDateTime) / (24 * 60 * 60 * 1000);
                        if (daysDiff <= 3) {
                            flag = 1;
                            validationCountFour = validationCountFour + 1;
                        }
                    }
                }                
                if (validationCountTwo >= 1 || validationCountOne >= 1) {
                    //when the new clearance retail is same or higher than the previous clearance retail or Base retail
                    FacesMessageUtils.addErrorMessage("New retail is equal to or greater than current retail. Clearance retail must decrease.",
                                                      FacesMessage.SEVERITY_ERROR);
                }
                if (validationCountThree >= 1) {
                    //when the entered MD % is not between 0 and 100
                    FacesMessageUtils.addErrorMessage("The new MD% value should be between 1 and 100", FacesMessage.SEVERITY_ERROR);
                }
                if (validationCountFour >= 1) {
                    //when the new effective date is not greater than current date plus 3 days
                    FacesMessageUtils.addErrorMessage("Clearance Effective Date should be greater than Current Date + 3 days ",
                                                      FacesMessage.SEVERITY_ERROR);
                }
            }

            //The calculations of the new retail, new MD % and MD amount start here
            if (flag == 0) {
                Row[] candidates = vo.getAllRowsInRange();
                int var2 = 0;
                Row row1 = null;
                OperationBinding ob = null;
                if (vo != null) {
                    for (int i = 0; i < candidates.length; i++) {
                        row1 = candidates[i];
                        Float compareAtRtl = (Float)row1.getAttribute("OriginalRetail");
                        Double newRetail = (Double)row1.getAttribute("NewRetail");
                        //Double currentRetail = (Double)row.getAttribute("CurrentRetail");
                        Double mdPercent = (Double)row1.getAttribute("ChangePercent");
                        Double baseRetail = (Double)row1.getAttribute("BaseRetail");
                        Integer inventory = (Integer)row1.getAttribute("TotalQty");
                        Double mdAmount = (Double)row1.getAttribute("MkdnAmount");
                        Integer seq_id = (Integer)row1.getAttribute("SeqNo");

                        //checking what inputs the user has given i.e. only next effective date or next effective date and new retail or next effective date and new MD %
                        // If only next effective date is given as input in candidate tab bulk update section and new retail and new MD % are blank
                        if (clearanceWorksheet.getNexteffectivedate() != null &&
                            clearanceWorksheet.getBind_newRetail_candidateTab_bulkUpdate().getValue() == null &&
                            clearanceWorksheet.getBind_newMdPercent_candidateTab_bulkUpdate().getValue() == null) {
                            ob = (OperationBinding)BindingContext.getCurrent().getCurrentBindingsEntry().getOperationBinding("newEffectiveDateInputBulkUpdate");
                            SimpleDateFormat format = new SimpleDateFormat("dd-MMM-yyyy");


                            ob.getParamsMap().put("effectiveDate",
                                                  format.format(clearanceWorksheet.getNexteffectivedate()));
                            ob.getParamsMap().put("seqId", seq_id.toString());
                            //setting the update id as user id
                            setValueToEL("#{bindings.UpdateId.inputValue}",
                                         userSession.getAttribute("userName"));
                            String updateId =
                                getValueFrmExpression("#{bindings.UpdateId.inputValue}");
                            ob.getParamsMap().put("updateId", updateId);
                            ob.execute();
                        }
                        //The user can enter either new retail or new MD % only one at a time along with the next effective date
                        else if (clearanceWorksheet.getRacknewretail() != null) {
                            ob = (OperationBinding)BindingContext.getCurrent().getCurrentBindingsEntry().getOperationBinding("onNewRetailInputBulkUpdate"); //if the new retail value is given as an input, calculations are done based on the new retail input value
                            mdAmount = (baseRetail - enteredRetail) * inventory;
                            if(compareAtRtl==null){
                            mdPercent = 100 - (100 * (enteredRetail / baseRetail));
                            }
                            else{
                            mdPercent = 100 - (100 * (enteredRetail / compareAtRtl));
                            }

                            //checking if the user has entered new effective date along with the new retail value
                            SimpleDateFormat format = new SimpleDateFormat("dd-MMM-yyyy");
                            if (clearanceWorksheet.getNexteffectivedate() !=
                                null) {
                                ob.getParamsMap().put("effectiveDate",
                                                      format.format(clearanceWorksheet.getNexteffectivedate()));
                            } else 
                                ob.getParamsMap().put("effectiveDate", null);                            

                            ob.getParamsMap().put("seqId", seq_id.toString());
                            ob.getParamsMap().put("newRetail", enteredRetail.toString());
                            ob.getParamsMap().put("newMdPercent", mdPercent.toString());
                            ob.getParamsMap().put("mdAmount", mdAmount.toString());

                            //setting the update id as user id
                            setValueToEL("#{bindings.UpdateId.inputValue}", userSession.getAttribute("userName"));
                            String updateId = getValueFrmExpression("#{bindings.UpdateId.inputValue}");
                            ob.getParamsMap().put("updateId", updateId);
                            ob.execute();
                        } else if (clearanceWorksheet.getBind_newMdPercent_candidateTab_bulkUpdate().getValue() != null) {
                            ob = (OperationBinding)BindingContext.getCurrent().getCurrentBindingsEntry().getOperationBinding("onNewMDPercentInputBulkUpdate"); //if the new MD % value is given as an input, calculations are done based on the new MD % input value
                            if(compareAtRtl==null){
                            newRetail = (100 - enteredMdPercent) * 0.01 * baseRetail;
                            }
                            else{
                            newRetail = (100 - enteredMdPercent) * 0.01 * compareAtRtl;
                            }
                            mdAmount = (baseRetail - newRetail) * inventory;
                            //checking if the user has entered new effective date along with the new MD % value
                            SimpleDateFormat format = new SimpleDateFormat("dd-MMM-yyyy");
                            if (clearanceWorksheet.getNexteffectivedate() != null) 
                                ob.getParamsMap().put("effectiveDate", format.format(clearanceWorksheet.getNexteffectivedate()));
                            else 
                                ob.getParamsMap().put("effectiveDate", null);
                            
                            ob.getParamsMap().put("seqId", seq_id.toString());
                            ob.getParamsMap().put("newRetail", newRetail.toString());
                            ob.getParamsMap().put("newMdPercent", enteredMdPercent.toString());
                            ob.getParamsMap().put("mdAmount", mdAmount.toString());
                            //setting the update id as user id
                            setValueToEL("#{bindings.UpdateId.inputValue}", userSession.getAttribute("userName"));
                            String updateId = getValueFrmExpression("#{bindings.UpdateId.inputValue}");
                            ob.getParamsMap().put("updateId", updateId);
                            ob.execute();
                        }                        
                    }
                }
            }

            //after update the view Object is reset
            vo.setWhereClause("upper(status)='N' and dept in " +
                              clearanceWorksheet.getDeptList() +
                              " and zone =" + clearanceWorksheet.getZoneId());
            vo.executeQuery();

            clearanceWorksheet.setNexteffectivedate(null);
            clearanceWorksheet.setRacknewretail(null);

            clearanceWorksheet.setRacknewpercentMD(null);
            clearanceWorksheet.getFtseqidlist().clear();
            clearanceWorksheet.setUpdateCount(0);
            clearanceWorksheet.getBind_headerCheckBox_candidateTab().setValue(false);

            clearanceWorksheet.setTotalRowsselectedcandidate(0);
            clearanceWorksheet.setSummdamountcandidate(0.0);
            clearanceWorksheet.setSuminventorycandidate(0.0);

            AdfFacesContext.getCurrentInstance().addPartialTarget(clearanceWorksheet.getBt1());
            System.runFinalization();

        } catch (ViewObjectNotPreparedException vnpe) {
            vnpe.printStackTrace();
        } catch (JboSQLException jse) {
            jse.printStackTrace();
        } catch (JboException je) {
            je.printStackTrace();
            JboException ex = new JboException("");
            BindingContext bctx = BindingContext.getCurrent();
            ((DCBindingContainer)bctx.getCurrentBindingsEntry()).reportException(ex);
        } catch (PropertyNotFoundException pnfe) {
            pnfe.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        RPMlogger.log(Level.INFO, "updateftBulk() Ends");
    }

    /*
 * This is an action event method. This method is called when the clear button is clicked in the candidates tab.
 * This method is used to clear the applied filters and edited fields in the candidate tab.
 */

    public void clearChangesCandidateTab(ActionEvent actionEvent) {
        RPMlogger.info("ftclearEmpQbeFilter");
        RPMlogger.log(Level.INFO, "ftclearEmpQbeFilter() Begins");
        ClearanceWorksheetMB clearanceWorksheet =
            (ClearanceWorksheetMB)ADFContext.getCurrent().getViewScope().get("ClearanceWorksheetMB");
        try {
            FilterableQueryDescriptor qd =
                (FilterableQueryDescriptor)clearanceWorksheet.getBt1().getFilterModel();
            if (qd != null && qd.getFilterCriteria() != null) {
                qd.getFilterCriteria().clear();
                clearanceWorksheet.getBt1().queueEvent(new QueryEvent(clearanceWorksheet.getBt1(),
                                                                      qd));
            }           
            resetAppliedFiltersCandidateTab(qd);
            //totals making to 0
            clearanceWorksheet.setTotalRowsselectedcandidate(0);
            clearanceWorksheet.setSummdamountcandidate(0);
            clearanceWorksheet.setSuminventorycandidate(0);
            this.totalmdamountcandidate = 0;
            this.totalinventorycandidate = 0;
            clearanceWorksheet.getFtseqidlist().clear();
            clearanceWorksheet.setUpdateCount(0);
            //ADFContext.getCurrent().getViewScope().put("deptAccess", true);
            clearanceWorksheet.getBind_headerCheckBox_candidateTab().resetValue();
            clearanceWorksheet.setFtheaderBstatus(false);
            clearanceWorksheet.getBind_rowCheckbox_candidateTab().resetValue();
            clearanceWorksheet.getBind_rowCheckbox_candidateTab().setValue(false);
            clearanceWorksheet.getBind_rowCheckbox_candidateTab().setSelected(false);
            clearanceWorksheet.getBind_rowCheckbox_candidateTab().setAutoSubmit(true);            
            AdfFacesContext.getCurrentInstance().addPartialTarget(clearanceWorksheet.getBind_headerCheckBox_candidateTab());            
            AdfFacesContext.getCurrentInstance().addPartialTarget(clearanceWorksheet.getBind_rowCheckbox_candidateTab());
            clearanceWorksheet.getBt1().resetStampState();
            AdfFacesContext.getCurrentInstance().addPartialTarget(clearanceWorksheet.getBt1());
            clearanceWorksheet.getBind_SupplierName_filter().resetValue();             
            clearanceWorksheet.getBind_SupplierName_filter().setAutoSubmit(true);            
            clearanceWorksheet.getBind_Class_filter().resetValue();
            clearanceWorksheet.getBind_Class_filter().setAutoSubmit(true);            
            clearanceWorksheet.getBind_Dept_filter().resetValue();
            clearanceWorksheet.getBind_Dept_filter().setAutoSubmit(true);            
            clearanceWorksheet.getBind_Division_filter().resetValue();
            clearanceWorksheet.getBind_Division_filter().setAutoSubmit(true);            
            clearanceWorksheet.getBind_Item_filter().resetValue();
            clearanceWorksheet.getBind_Item_filter().setAutoSubmit(true);            
            clearanceWorksheet.getBind_SubClass_filter().resetValue();
            clearanceWorksheet.getBind_SubClass_filter().setAutoSubmit(true);            
            clearanceWorksheet.getBind_SupplierColor_filter().resetValue();
            clearanceWorksheet.getBind_SupplierColor_filter().setAutoSubmit(true);
            clearanceWorksheet.getBind_VPN_filter().resetValue();
            clearanceWorksheet.getBind_VPN_filter().setAutoSubmit(true);
            clearanceWorksheet.getBind_Label_filter().resetValue();
            clearanceWorksheet.getBind_Label_filter().setAutoSubmit(true);
            clearanceWorksheet.getBind_NRFColor_Filter().resetValue();
            clearanceWorksheet.getBind_NRFColor_Filter().setAutoSubmit(true);
            clearanceWorksheet.getBind_EffectiveDate_filter().resetValue();
            clearanceWorksheet.getBind_EffectiveDate_filter().setAutoSubmit(true);
            clearanceWorksheet.getBind_NewEffectiveDate_filter().resetValue();
            clearanceWorksheet.getBind_NewEffectiveDate_filter().setAutoSubmit(true);
            clearanceWorksheet.getBind_LastMkdnDate_filter().resetValue();
            clearanceWorksheet.getBind_LastMkdnDate_filter().setAutoSubmit(true);
            clearanceWorksheet.getBind_LastReceiptDate_filter().resetValue();
            clearanceWorksheet.getBind_LastReceiptDate_filter().setAutoSubmit(true);             
            rollbackForClearButton();    
            resetAll();
            clearNewRtlMDPctEffDateScopes();
            System.runFinalization();
        } catch (Exception e) {
            e.printStackTrace();
        }
        RPMlogger.log(Level.INFO, "ftclearEmpQbeFilter() Ends");
    }


    public void resetAppliedFiltersCandidateTab(FilterableQueryDescriptor qd) {
        ClearanceWorksheetMB clearanceWorksheet =
            (ClearanceWorksheetMB)ADFContext.getCurrent().getViewScope().get("ClearanceWorksheetMB");
        try {

            FacesContext fctx = FacesContext.getCurrentInstance();
            Application application = fctx.getApplication();
            ExpressionFactory expressionFactory =
                application.getExpressionFactory();
            ELContext elctx = fctx.getELContext();

            MethodExpression methodExpression =
                expressionFactory.createMethodExpression(elctx,
                                                         "#{bindings.Clr_Wksht_Rule_Item_View1Query.processQuery}",
                                                         Object.class,
                                                         new Class[] { QueryEvent.class });
            QueryEvent qe = new QueryEvent(clearanceWorksheet.getBt1(), qd);

            methodExpression.invoke(elctx, new Object[] { qe });            
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void resetAppliedFiltersViewSavedTakesTab(FilterableQueryDescriptor qd) {
        ClearanceWorksheetMB clearanceWorksheet =
            (ClearanceWorksheetMB)ADFContext.getCurrent().getViewScope().get("ClearanceWorksheetMB");
        try {
            FacesContext fctx = FacesContext.getCurrentInstance();
            Application application = fctx.getApplication();
            ExpressionFactory expressionFactory =
                application.getExpressionFactory();
            ELContext elctx = fctx.getELContext();

            MethodExpression methodExpression =
                expressionFactory.createMethodExpression(elctx,
                                                         "#{bindings.Clr_Wksht_Rule_Item_View1Query.processQuery}",
                                                         Object.class,
                                                         new Class[] { QueryEvent.class });
            QueryEvent qe = new QueryEvent(clearanceWorksheet.getBt2(), qd);

            methodExpression.invoke(elctx, new Object[] { qe });
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /*
 * This is an action event method. This method is called when the clear button is clicked in the view saved takes tab
 * This method is used to clear the applied filters and edited fields in the view saved takes tab.
 */

    public void clearChangesViewSavedTakesTab(ActionEvent actionEvent) {
        RPMlogger.info("stclearEmpQbeFilter");
        RPMlogger.log(Level.INFO, "stclearEmpQbeFilter() Begins");
        ClearanceWorksheetMB clearanceWorksheet =
            (ClearanceWorksheetMB)ADFContext.getCurrent().getViewScope().get("ClearanceWorksheetMB");
        try {
            FilterableQueryDescriptor qd =
                (FilterableQueryDescriptor)clearanceWorksheet.getBt2().getFilterModel();
            if (qd != null && qd.getFilterCriteria() != null) {
                qd.getFilterCriteria().clear();
                clearanceWorksheet.getBt2().queueEvent(new QueryEvent(clearanceWorksheet.getBt2(),
                                                                      qd));
            }
            resetAppliedFiltersViewSavedTakesTab(qd);
            clearanceWorksheet.getBt2().resetStampState();
            clearanceWorksheet.getStseqidlist().clear();
            clearanceWorksheet.setTotalRowsSelectedSavedTakesTab(0);
            clearanceWorksheet.setSumMdAmountSavedTakesTab(0.0);
            clearanceWorksheet.setSumInventorySavedTakesTab(0);
            clearanceWorksheet.getBind_headerCheckBox_SavedTakesTab().resetValue();
            clearanceWorksheet.setStheaderBstatus(false);
            clearanceWorksheet.setUpdateCount1(0);
            clearanceWorksheet.getBind_rowCheckbox_ViewSavedTakesTab().resetValue();
            clearanceWorksheet.getBind_rowCheckbox_ViewSavedTakesTab().setValue(false);
            clearanceWorksheet.getBind_rowCheckbox_ViewSavedTakesTab().setSelected(false);
            clearanceWorksheet.getBind_rowCheckbox_ViewSavedTakesTab().setAutoSubmit(true);
            AdfFacesContext.getCurrentInstance().addPartialTarget(clearanceWorksheet.getBt2());
            clearanceWorksheet.getBind_SavedDivision_filter().resetValue();
            clearanceWorksheet.getBind_SavedDivision_filter().setAutoSubmit(true);
            clearanceWorksheet.getBind_SavedDept_filter().resetValue();
            clearanceWorksheet.getBind_SavedDept_filter().setAutoSubmit(true);
            clearanceWorksheet.getBind_SavedClass_filter().resetValue();
            clearanceWorksheet.getBind_SavedClass_filter().setAutoSubmit(true);
            clearanceWorksheet.getBind_SavedSubClass_filter().resetValue();
            clearanceWorksheet.getBind_SavedSubClass_filter().setAutoSubmit(true);
            clearanceWorksheet.getBind_SavedSupColor_filter().resetValue();
            clearanceWorksheet.getBind_SavedSupColor_filter().setAutoSubmit(true);
            clearanceWorksheet.getBind_SavedSupName_filter().resetValue();
            clearanceWorksheet.getBind_SavedSupName_filter().setAutoSubmit(true);
            clearanceWorksheet.getBind_SavedVPN_filter().resetValue();
            clearanceWorksheet.getBind_SavedVPN_filter().setAutoSubmit(true);
            clearanceWorksheet.getBind_SavedItem_filter().resetValue();
            clearanceWorksheet.getBind_SavedItem_filter().setAutoSubmit(true);
            clearanceWorksheet.getBind_SavedNrfColor_filter().resetValue();
            clearanceWorksheet.getBind_SavedNrfColor_filter().setAutoSubmit(true);
            clearanceWorksheet.getBind_SavedEffDate_filter().resetValue();
            clearanceWorksheet.getBind_SavedEffDate_filter().setAutoSubmit(true);
            clearanceWorksheet.getBind_SavedLastRctDt_filter().resetValue();
            clearanceWorksheet.getBind_SavedLastRctDt_filter().setAutoSubmit(true);
            clearanceWorksheet.getBind_SavedNwEffDate_filter().resetValue();
            clearanceWorksheet.getBind_SavedNwEffDate_filter().setAutoSubmit(true);
            clearanceWorksheet.getBind_LastMDnDt_filter().resetValue();
            clearanceWorksheet.getBind_LastMDnDt_filter().setAutoSubmit(true);
            rollbackForClearButton();
            resetAll();
            clearNewSavedRtlMdPctEffDateScopes();
            System.runFinalization();
        } catch (Exception e) {
            e.printStackTrace();
        }
        RPMlogger.log(Level.INFO, "stclearEmpQbeFilter() Ends");
    }


    public void rollbackForClearButton() {
        OperationBinding oBinding =
            (OperationBinding)BindingContext.getCurrent().getCurrentBindingsEntry().getOperationBinding("rollbackorrevertRowChanges");
        if (oBinding != null)
            oBinding.execute();
    }

    /*
 * This is an action event method. This method is called when the Untake button is clicked in the View saved takes tab.
 * This method is used to untake the taken MD candidates from the view Saved takes tab.
 */

    
    /*
 * This is an action event method. This method is called when the Send To RPM button is clicked in the View saved takes tab.
 * The taken MD candidates from the view saved tab are sent to RPM using this method.
 */

    public void sendToRpmActionListener(ActionEvent actionEvent) {
        RPMlogger.info("rpmActionListener");
        RPMlogger.log(Level.INFO, "rpmActionListener() Begins");
        ClearanceWorksheetMB clearanceWorksheet =
            (ClearanceWorksheetMB)ADFContext.getCurrent().getViewScope().get("ClearanceWorksheetMB");
        // validations to display the confirmation message when the user clicks on send to RPM button
        try {

            DCBindingContainer dbc =
                (DCBindingContainer)BindingContext.getCurrent().getCurrentBindingsEntry();
            DCIteratorBinding dci =
                dbc.findIteratorBinding("Clr_Wksht_Rule_Item_View1Iterator");
            ViewObject vo = dci.getViewObject();
            vo.setWhereClause("seq_no in " +
                              clearanceWorksheet.getStseqidlist().toString().replace("[",
                                                                                     "(").replace("]",
                                                                                                  ")"));
            vo.executeQuery();

            Row[] rows = vo.getAllRowsInRange();
            Row row = null;
            int count = 0;
            for (int i = 0; i < rows.length; i++) {
                row = rows[i];
                /*validation to check if the % off original retail is greater than or equal to 95%
                            * This is performed to display to confirmation message to the user when the Send to RPM button is clicked.
                            **/
                Double percentOffOrigRetail =
                    (Double)row.getAttribute("PctOffOrigRetail");
                if (percentOffOrigRetail >= 95) {
                    count = count + 1;
                }

            }
            if (count > 0) {
                FacesMessageUtils.addErrorMessage("The original retail is >= 95%",
                                                  FacesMessage.SEVERITY_INFO);
            }
            //after performing validations the view saved takes tab is again refreshed
            vo.setWhereClause("upper(status)!='N' and dept in " +
                              clearanceWorksheet.getDeptList() +
                              " and zone =" +
                              clearanceWorksheet.getZoneId()); //fetching the records whose status is 'T' (taken MD candidates)
            vo.executeQuery();
            clearanceWorksheet.setUpdateCount1(0); //to disable the send to RPM button once the action is done


            System.runFinalization();
        } catch (ViewObjectNotPreparedException vnpe) {
            vnpe.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        RPMlogger.log(Level.INFO, "rpmActionListener() Ends");

    }


    /*
 * This is an action event method.
 * This method is called when the Update button is pressed on the clearance worksheet screen and when the view saved takes tab is opened
 * This method is used to update the new retail, new MD% and new effective date for the multiple MD candidates
 * which belong to the view saved takes tab.
 */

    public void viewSavedTakesTabBulkUpdate(ActionEvent actionEvent) {
        RPMlogger.info("updatestBulk");
        RPMlogger.log(Level.INFO, "updatestBulk() Begins");
        ClearanceWorksheetMB clearanceWorksheet =
            (ClearanceWorksheetMB)ADFContext.getCurrent().getViewScope().get("ClearanceWorksheetMB");

        try {
            //validations for new retail and new MD % start here
            String seqNo =
                clearanceWorksheet.getStseqidlist().toString().replace("[",
                                                                       "(").replace("]",
                                                                                    ")");
            DCBindingContainer dcb =
                (DCBindingContainer)BindingContext.getCurrent().getCurrentBindingsEntry();
            DCIteratorBinding dci =
                dcb.findIteratorBinding("Clr_Wksht_Rule_Item_View1Iterator");
            ViewObject vo = dci.getViewObject();
            vo.setWhereClause("seq_no in " + seqNo);

            vo.executeQuery();

            Double enteredRetail = 0.0;
            Double enteredMdPercent = 0.0;
            Row row = null;
            int flag = 0;
            int var = 0;
            int validationCountOne = 0;
            int validationCountTwo = 0;
            int validationCountThree = 0;
            int validationCountFour = 0;
            if (vo != null) {
                for (int i = 0; i < vo.getRowCount(); i++) {
                    if (var == 0) {
                        row = vo.first();
                        var = 1;
                    } else {
                        row = vo.next();
                    }
                    Double currentRetail =
                        (Double)row.getAttribute("CurrentRetail");
                    Double baseRetail = (Double)row.getAttribute("BaseRetail");
                    //user entered values
                    /*
             * validation 1 for new retail
             * checking if the new retail is greater than the base retail
             */
                    if (clearanceWorksheet.getBind_newRetail_savedTakesTab_bulkUpdate().getValue() !=
                        null) {
                        enteredRetail =
                                Double.parseDouble(clearanceWorksheet.getBind_newRetail_savedTakesTab_bulkUpdate().getValue().toString());
                        if (enteredRetail >= baseRetail) {
                            flag = 1;
                            validationCountOne = validationCountOne + 1;

                        }
                    }
                    /*
              * checking if New clearance retail is the same as previous clearance retail or New retail is higher than previous retail
                */

                    if (clearanceWorksheet.getBind_newRetail_savedTakesTab_bulkUpdate().getValue() !=
                        null) {
                        enteredRetail =
                                Double.parseDouble(clearanceWorksheet.getBind_newRetail_savedTakesTab_bulkUpdate().getValue().toString());
                        if (enteredRetail >= currentRetail) {
                            flag = 1;
                            validationCountTwo = validationCountTwo + 1;

                        }
                    }

                    /*
                * validation 2 for MD %
                * checking if the new MD% is between 0 and 100
                */
                    if (clearanceWorksheet.getBind_newMdPercent_savedTakesTab_bulkUpdate().getValue() !=
                        null) {
                        enteredMdPercent =
                                Double.parseDouble(clearanceWorksheet.getBind_newMdPercent_savedTakesTab_bulkUpdate().getValue().toString());
                        if (enteredMdPercent <= 0.0 ||
                            enteredMdPercent > 100.0) {
                            flag = 1;
                            validationCountThree = validationCountThree + 1;
                        }
                    }


                    /*
               * validation 3 for new effective date
               * checking if Clearance Effective Date is > Current Date + 3 days
               */
                    if (clearanceWorksheet.getBind_nextEffectiveDate_savedTakesTab_bulkUpdate().getValue() !=
                        null) {
                        //fetching the vdate as the current date
                        DCBindingContainer dcbc =
                            (DCBindingContainer)BindingContext.getCurrent().getCurrentBindingsEntry();
                        DCIteratorBinding dcib =
                            dcbc.findIteratorBinding("V_Date_View1Iterator");
                        ViewObject viewObj = dcib.getViewObject();
                        Row dateRow = viewObj.first();
                        Date currentDate =
                            (Date)dateRow.getAttribute("Vdate"); // vdate is in oracle.jbo.domain.date type
                        java.util.Date nextEffectiveDate =
                            (java.util.Date)clearanceWorksheet.getBind_nextEffectiveDate_savedTakesTab_bulkUpdate().getValue(); //next effective date is in java.util.date type

                        //converting java.util.date next effective date to oracle.jbo.domain.date type
                        long utilDateTime = nextEffectiveDate.getTime();
                        java.sql.Date sqlDateTime =
                            new java.sql.Date(utilDateTime);
                        Date oracleDateTime = new Date(sqlDateTime);

                        //calculating the days difference between new effective date and current date
                        long nextEffectiveDateTime =
                            oracleDateTime.getValue().getTime();
                        long currentDateTime =
                            currentDate.getValue().getTime();
                        long daysDiff =
                            (nextEffectiveDateTime - currentDateTime) /
                            (24 * 60 * 60 * 1000);
                        if (daysDiff <= 3) {
                            flag = 1;
                            validationCountFour = validationCountFour + 1;
                        }
                    }
                }                
                if (validationCountTwo >= 1 || validationCountOne >= 1) {
                    //when the new clearance retail is same or higher than the previous clearance retail
                    FacesMessageUtils.addErrorMessage("New retail is equal to or greater than current retail. Clearance retail must decrease.",
                                                      FacesMessage.SEVERITY_ERROR);
                }
                if (validationCountThree >= 1) {
                    //when the entered MD % is not between 0 and 100
                    FacesMessageUtils.addErrorMessage("The new MD% value should be between 1 and 100",
                                                      FacesMessage.SEVERITY_ERROR);
                }
                if (validationCountFour >= 1) {
                    //when the new effective date is not greater than current date plus 3 days
                    FacesMessageUtils.addErrorMessage("Clearance Effective Date should be greater than Current Date + 3 days ",
                                                      FacesMessage.SEVERITY_ERROR);
                }
            }

            //The calculations of the new retail, new MD % and MD amount start here
            if (flag == 0) {
                Row[] candidates = vo.getAllRowsInRange();
                int var2 = 0;
                Row row1 = null;
                OperationBinding ob = null;
                if (vo != null) {
                    for (int i = 0; i < candidates.length; i++) {
                        row1 = candidates[i];
                        Float compareAtRtl = (Float)row1.getAttribute("OriginalRetail");
                        Double newRetail =
                            (Double)row1.getAttribute("NewRetail");
                        //Double currentRetail = (Double)row.getAttribute("CurrentRetail");
                        Double mdPercent =
                            (Double)row1.getAttribute("ChangePercent");
                        Double baseRetail =
                            (Double)row1.getAttribute("BaseRetail");
                        Integer inventory =
                            (Integer)row1.getAttribute("TotalQty");
                        Double mdAmount =
                            (Double)row1.getAttribute("MkdnAmount");
                        Integer seq_id = (Integer)row1.getAttribute("SeqNo");

                        //checking what inputs the user has given i.e. only next effective date or next effective date and new retail or next effective date and new MD %
                        // If only next effective date is given as input in view saved takes tab bulk update section and new retail and new MD % are blank
                        if (clearanceWorksheet.getStnexteffectivedate() !=
                            null &&
                            clearanceWorksheet.getBind_newRetail_savedTakesTab_bulkUpdate().getValue() ==
                            null &&
                            clearanceWorksheet.getBind_newMdPercent_savedTakesTab_bulkUpdate().getValue() ==
                            null) {
                            ob =
 (OperationBinding)BindingContext.getCurrent().getCurrentBindingsEntry().getOperationBinding("newEffectiveDateInputBulkUpdate");
                            SimpleDateFormat format =
                                new SimpleDateFormat("dd-MMM-yyyy");
                            ob.getParamsMap().put("effectiveDate",
                                                  format.format(clearanceWorksheet.getStnexteffectivedate()));
                            ob.getParamsMap().put("seqId", seq_id.toString());
                            //setting the update id as user id
                            setValueToEL("#{bindings.UpdateId.inputValue}",
                                         userSession.getAttribute("userName"));
                            String updateId =
                                getValueFrmExpression("#{bindings.UpdateId.inputValue}");
                            ob.getParamsMap().put("updateId", updateId);
                            ob.execute();
                        }
                        //The user can enter either new retail or new MD % only one at a time along with the next effective date
                        else if (clearanceWorksheet.getBind_newRetail_savedTakesTab_bulkUpdate().getValue() !=
                                 null) {
                            ob =
 (OperationBinding)BindingContext.getCurrent().getCurrentBindingsEntry().getOperationBinding("onNewRetailInputBulkUpdate"); //if the new retail value is given as an input, calculations are done based on the new retail input value
                            mdAmount =
                                    (baseRetail - enteredRetail) * inventory;
                            if(compareAtRtl==null){
                            mdPercent =100 - (100 * (enteredRetail / baseRetail));
                            }
                            else{
                            mdPercent =100 - (100 * (enteredRetail / compareAtRtl));
                            }

                            //checking if the user has entered new effective date along with the new retail value
                            SimpleDateFormat format =
                                new SimpleDateFormat("dd-MMM-yyyy");
                            if (clearanceWorksheet.getStnexteffectivedate() !=
                                null) {
                                ob.getParamsMap().put("effectiveDate",
                                                      format.format(clearanceWorksheet.getStnexteffectivedate()));
                            } else {
                                ob.getParamsMap().put("effectiveDate", null);
                            }

                            ob.getParamsMap().put("seqId", seq_id.toString());
                            ob.getParamsMap().put("newRetail",
                                                  enteredRetail.toString());
                            ob.getParamsMap().put("newMdPercent",
                                                  mdPercent.toString());
                            ob.getParamsMap().put("mdAmount",
                                                  mdAmount.toString());
                            //setting the update id as user id
                            setValueToEL("#{bindings.UpdateId.inputValue}",
                                         userSession.getAttribute("userName"));
                            String updateId =
                                getValueFrmExpression("#{bindings.UpdateId.inputValue}");
                            ob.getParamsMap().put("updateId", updateId);
                            ob.execute();
                        } else if (clearanceWorksheet.getBind_newMdPercent_savedTakesTab_bulkUpdate().getValue() !=
                                   null) {
                            ob =
 (OperationBinding)BindingContext.getCurrent().getCurrentBindingsEntry().getOperationBinding("onNewMDPercentInputBulkUpdate"); //if the new MD % value is given as an input, calculations are done based on the new MD % input value
                          if(compareAtRtl==null){
                            newRetail =(100 - enteredMdPercent) * 0.01 * baseRetail;
                          }
                          else{
                            newRetail =(100 - enteredMdPercent) * 0.01 * compareAtRtl;
                          }
                            mdAmount = (baseRetail - newRetail) * inventory;
                            //checking if the user has entered new effective date along with the new MD % value
                            SimpleDateFormat format =
                                new SimpleDateFormat("dd-MMM-yyyy");
                            if (clearanceWorksheet.getStnexteffectivedate() !=
                                null) {
                                ob.getParamsMap().put("effectiveDate",
                                                      format.format(clearanceWorksheet.getStnexteffectivedate()));
                            } else {
                                ob.getParamsMap().put("effectiveDate", null);
                            }
                            ob.getParamsMap().put("seqId", seq_id.toString());
                            ob.getParamsMap().put("newRetail",
                                                  newRetail.toString());
                            ob.getParamsMap().put("newMdPercent",
                                                  enteredMdPercent.toString());
                            ob.getParamsMap().put("mdAmount",
                                                  mdAmount.toString());
                            //setting the update id as user id
                            setValueToEL("#{bindings.UpdateId.inputValue}",
                                         userSession.getAttribute("userName"));
                            String updateId =
                                getValueFrmExpression("#{bindings.UpdateId.inputValue}");
                            ob.getParamsMap().put("updateId", updateId);
                            ob.execute();
                        }

                    }
                }
            }
            //after update the view Object is reset
            vo.setWhereClause("upper(status)!='N' and dept in " +
                              clearanceWorksheet.getDeptList() +
                              " and zone =" + clearanceWorksheet.getZoneId());
            vo.executeQuery();

            clearanceWorksheet.setStnexteffectivedate(null);
            clearanceWorksheet.setStracknewretail(null);

            clearanceWorksheet.setStracknewpercentMD(null);
            clearanceWorksheet.getStseqidlist().clear();
            clearanceWorksheet.setUpdateCount1(0);
            clearanceWorksheet.getBind_headerCheckBox_SavedTakesTab().setValue(false);

            clearanceWorksheet.setTotalRowsSelectedSavedTakesTab(0);
            clearanceWorksheet.setSumMdAmountSavedTakesTab(0.0);
            clearanceWorksheet.setSumInventorySavedTakesTab(0);
            AdfFacesContext.getCurrentInstance().addPartialTarget(clearanceWorksheet.getBt2());
            System.runFinalization();

        } catch (ViewObjectNotPreparedException vnpe) {
            vnpe.printStackTrace();
        } catch (ArrayIndexOutOfBoundsException aiobe) {
            aiobe.printStackTrace();

        } catch (Exception e) {
            e.printStackTrace();
        }
        RPMlogger.log(Level.INFO, "updatestBulk() Ends");
    }

    /*
 * This is an action event method.
 * This method is called when the Save View button is clicked in the candidate tab on the clearance worksheet screen
 * This method is used to save the view when the users rearrange the columns in the candidate tab
 */


    public void saveViewInCandidateTab(ActionEvent actionEvent) {
        RPMlogger.info("saveviewActionListener");
        RPMlogger.log(Level.INFO, "saveviewActionListener() Begins");
        // Add event code here...
        ClearanceWorksheetMB clearanceWorksheet =
            (ClearanceWorksheetMB)ADFContext.getCurrent().getViewScope().get("ClearanceWorksheetMB");
        try {
            oracle.binding.OperationBinding ob =
                BindingContext.getCurrent().getCurrentBindingsEntry().getOperationBinding("storeHistory");
            Map map = ob.getParamsMap();
            map.put("name", clearanceWorksheet.getTaoname());
            map.put("order", getColumnsOrderCandidateTab());
            ob.execute();
            generateSavedListCandidateTab(userSession);
            clearanceWorksheet.setTaoname("");
        } catch (MethodNotFoundException mnfe) {
            mnfe.printStackTrace();

        } catch (Exception e) {
            e.printStackTrace();
        }
        RPMlogger.log(Level.INFO, "saveviewActionListener() Ends");

    }

    /*
 * This method is used to get the display index of the columns in the order in which the columns are arranged in the candidate tab.
 */

    public String getColumnsOrderCandidateTab() {
        RPMlogger.info("getColumnsOrder");
        RPMlogger.log(Level.INFO, "getColumnsOrder() Begins");
        ClearanceWorksheetMB clearanceWorksheet =
            (ClearanceWorksheetMB)ADFContext.getCurrent().getViewScope().get("ClearanceWorksheetMB");
        String order = "";
        try {

            for (UIComponent c : clearanceWorksheet.getBt1().getChildren()) {
                RichColumn rc = (RichColumn)c;
                order += rc.getDisplayIndex() + "-" + rc.getWidth() + ";";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        RPMlogger.log(Level.INFO, "getColumnsOrder() Ends");
        return order;

    }

    /*
 * This method is used to get the display index of the columns in the order in which the columns are arranged in the view saved takes tab.
 */

    public String getColumnsOrderViewSavedTakesTab() {
        RPMlogger.info("getColumnsOrderst");
        RPMlogger.log(Level.INFO, "getColumnsOrderst() Begins");
        ClearanceWorksheetMB clearanceWorksheet =
            (ClearanceWorksheetMB)ADFContext.getCurrent().getViewScope().get("ClearanceWorksheetMB");
        String order = "";
        try {
            for (UIComponent c : clearanceWorksheet.getBt2().getChildren()) {
                RichColumn rc = (RichColumn)c;
                order += rc.getDisplayIndex() + "-" + rc.getWidth() + ";";
            }
            RPMlogger.log(Level.INFO, "getColumnsOrderst() Ends");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return order;

    }

    /*
 * This method will generate the list of saved views for the candidate tab.
 */

    public void generateSavedListCandidateTab(HttpSession userSession) {
        RPMlogger.info("generateSavedList");
        RPMlogger.log(Level.INFO, "generateSavedList() Begins");
        ClearanceWorksheetMB clearanceWorksheet =
            (ClearanceWorksheetMB)ADFContext.getCurrent().getViewScope().get("ClearanceWorksheetMB");
        try {
            DCBindingContainer dcb =
                (DCBindingContainer)BindingContext.getCurrent().getCurrentBindingsEntry();
            DCIteratorBinding dci =
                dcb.findIteratorBinding("Clr_Wrks_Savedhistory_Ft_View1Iterator");
            ViewObject vo = dci.getViewObject();
            clearanceWorksheet.getSavedList().clear();
            vo.setWhereClause("Username = "+"'"+userSession.getAttribute("userName")+"'"); 
            vo.executeQuery();
            if (vo != null) {
                while (vo.hasNext()) {
                    Row row = vo.next();

                    clearanceWorksheet.getSavedList().add(new SelectItem(row.getAttribute("Columnorder") +
                                                                         "",
                                                                         row.getAttribute("Customname") +
                                                                         ""));

                    //vo.executeQuery();

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        RPMlogger.log(Level.INFO, "generateSavedList() Ends");
    }

    /*
 * This method will generate the list of saved views for the view saved takes tab.
 */

    public void generateSavedListViewSavedTakesTab(HttpSession userSession) {
        RPMlogger.info("generateSavedListst");
        RPMlogger.log(Level.INFO, "generateSavedListst() Begins");
        ClearanceWorksheetMB clearanceWorksheet =
            (ClearanceWorksheetMB)ADFContext.getCurrent().getViewScope().get("ClearanceWorksheetMB");
        try {
            DCBindingContainer dcb =
                (DCBindingContainer)BindingContext.getCurrent().getCurrentBindingsEntry();
            DCIteratorBinding dci =
                dcb.findIteratorBinding("Clr_Wrks_Savedhistory_St_View1Iterator");
            ViewObject vo = dci.getViewObject();
            clearanceWorksheet.getSavedListst().clear();
            vo.setWhereClause("Username = "+"'"+userSession.getAttribute("userName")+"'"); 
            vo.executeQuery();
            if (vo != null) {
                while (vo.hasNext()) {
                    Row row = vo.next();

                    clearanceWorksheet.getSavedListst().add(new SelectItem(row.getAttribute("Columnorder") +
                                                                           "",
                                                                           row.getAttribute("Customname") +
                                                                           ""));

                    //vo.executeQuery();

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        RPMlogger.log(Level.INFO, "generateSavedListst() Ends");
    }

    /*
 * This is a value change event method
 * This method is called when the user selects any option in the saved list dropdown in the candidate tab
 * This method is used to display the chosen view in the candidate tab.
 */

    public void chooseViewInCandidateTabVCL(ValueChangeEvent valueChangeEvent) {
        // Add event code here...
        RPMlogger.info("chooseViewValueChangeListener");
        RPMlogger.log(Level.INFO, "chooseViewValueChangeListener() Begins");
        ClearanceWorksheetMB clearanceWorksheet =
            (ClearanceWorksheetMB)ADFContext.getCurrent().getViewScope().get("ClearanceWorksheetMB");
        String colmnorder = valueChangeEvent.getNewValue() + "";
        try {

            String defaultView =
                "0-25;1-30;2-40;3-180;4-40;5-45;6-152;7-100;8-50;9-70;10-223;11-100;12-70;13-80;14-83;15-77;16-78;17-83;18-81;19-82;20-122;21-84;22-80;23-89;24-85;25-107;26-108;27-79;28-100;29-66;30-68;31-100;32-94;33-66;34-38;35-47;36-61;37-64;38-95;39-42;40-42;";
            if (colmnorder.equals("null")) {
                colmnorder = defaultView;
            }

            colmnorder = colmnorder.trim();
            String cols[] = colmnorder.split(";");

            int ch = 0;
            for (UIComponent c : clearanceWorksheet.getBt1().getChildren()) {
                String col[] = cols[ch].split("-");
                if (c instanceof RichColumn) {
                    RichColumn rc = (RichColumn)c;
                    rc.setDisplayIndex(Integer.parseInt(col[0]));
                    rc.setWidth(col[1]);
                    ch++;
                }
            }
            AdfFacesContext.getCurrentInstance().addPartialTarget(clearanceWorksheet.getBt1());
        } catch (Exception e) {
            e.printStackTrace();
        }
        RPMlogger.log(Level.INFO, "chooseViewValueChangeListener() Ends");
    }


    /*
 * This is an action event method.
 * This method is used to save the changes made by the user
 * and to update the data in the totals taken and current selection in the view saved takes tab
 */

    public void saveChangesViewSavedTakesTab(ActionEvent actionEvent) {
        RPMlogger.info("saveViewSavedTakesTabAL");
        RPMlogger.log(Level.INFO, "saveViewSavedTakesTabAL() Begins");
        ClearanceWorksheetMB clearanceWorksheet =
            (ClearanceWorksheetMB)ADFContext.getCurrent().getViewScope().get("ClearanceWorksheetMB");
        try {
            clearanceWorksheet.getBt2().resetStampState();
            clearanceWorksheet.getStseqidlist().clear();
            this.totalInventorySavedTakes = 0;
            this.totalMdAmountSavedTakes = 0.0;
            clearanceWorksheet.setTotalRowsSelectedSavedTakesTab(0);
            clearanceWorksheet.setSumMdAmountSavedTakesTab(0.0);
            clearanceWorksheet.setSumInventorySavedTakesTab(0);
            clearanceWorksheet.getBind_headerCheckBox_SavedTakesTab().resetValue();
            clearanceWorksheet.setStheaderBstatus(false);
            clearanceWorksheet.getBind_rowCheckbox_ViewSavedTakesTab().resetValue();
            clearanceWorksheet.getBind_rowCheckbox_ViewSavedTakesTab().setValue(false);
            clearanceWorksheet.setUpdateCount1(0);
            AdfFacesContext.getCurrentInstance().addPartialTarget(clearanceWorksheet.getBt2());
            OperationBinding oBinding = (OperationBinding)BindingContext.getCurrent().getCurrentBindingsEntry().getOperationBinding("Commit");
            oBinding.execute();            
            DCIteratorBinding dci = dbc.findIteratorBinding("Totals_Taken_View1Iterator");
            dci.getViewObject().executeQuery();
            DCIteratorBinding dci1 = dbc.findIteratorBinding("Current_Selection_Saved_Takes_View1Iterator");
            dci1.getViewObject().executeQuery();
            resetAll(); 
            clearNewSavedRtlMdPctEffDateScopes();
        } catch (Exception e) {
            e.printStackTrace();
        }
        RPMlogger.log(Level.INFO, "saveViewSavedTakesTabAL() Ends");
    }

    private void clearNewSavedRtlMdPctEffDateScopes(){
        ADFContext.getCurrent().getViewScope().put("NewSavedRetail", null);            
        ADFContext.getCurrent().getViewScope().put("NewSavedMdPct", null);      
        ADFContext.getCurrent().getViewScope().put("NewSavedEffectiveDate", null);
    }
    /*
 * This method is used to update the totals taken and totals if taken sections.
 */
    public void totalsTaken() {
        RPMlogger.info("totalsTaken");
        RPMlogger.log(Level.INFO, "totalsTaken() Begins");
        ClearanceWorksheetMB clearanceWorksheet =
            (ClearanceWorksheetMB)ADFContext.getCurrent().getViewScope().get("ClearanceWorksheetMB");
        try {
            totalsTakenParameters();
            //checking if the no of MD candidates under the selected department and zone exceeds 80000.
            //If the no of records exceeds 80000, the user will be allowed to change the search criteria.
            if (searchCountValid == false) {
                totalsTakenParameters();
                return;
            }
           
            DCIteratorBinding dci = dbc.findIteratorBinding("Totals_Taken_View1Iterator");
            ViewObject vo = dci.getViewObject();
            // vo.setWhereClause("");
            if (vo != null) {
                if (clearanceWorksheet.getDeptList() != null &&
                    clearanceWorksheet.getZoneId() != null)
                    vo.setWhereClause("dept in " +
                                      clearanceWorksheet.getDeptList() +
                                      " and zone = " +
                                      clearanceWorksheet.getZoneId());
                vo.executeQuery();
                Row r = vo.first();
                if (r != null) {
                    cccount_totalsTaken = Integer.parseInt(r.getAttribute(2).toString());
                    pcamount_totalsTaken = Double.parseDouble(r.getAttribute(3).toString());
                    pcunit__totalsTaken = Integer.parseInt(r.getAttribute(4).toString());
                }

                while (vo.hasNext()) {
                    r = vo.next();
                    if (r != null) {
                        cccount_totalsTaken =
                                cccount_totalsTaken + Integer.parseInt(r.getAttribute(2).toString());
                        pcamount_totalsTaken =
                                pcamount_totalsTaken + Double.parseDouble(r.getAttribute(3).toString());
                        pcunit__totalsTaken =
                                pcunit__totalsTaken + Integer.parseInt(r.getAttribute(4).toString());
                    }

                }

                cccount_totalsiftaken =
                        cccount_totalsTaken + clearanceWorksheet.getTotalRowsselectedcandidate();
                pcamount_totalsiftaken =
                        pcamount_totalsTaken + Double.parseDouble(clearanceWorksheet.getSummdamountcandidate() +
                                                                  "");

                pcU_totalsiftaken =
                        pcunit__totalsTaken + Double.parseDouble(clearanceWorksheet.getSuminventorycandidate() +
                                                                 "");
            }
        } catch (ArithmeticException ae) {
            ae.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        RPMlogger.log(Level.INFO, "totalsTaken() Ends");
    }
    
    private void totalsTakenParameters(){
        cccount_totalsTaken = 0;
        pcamount_totalsTaken = 0;
        pcunit__totalsTaken = 0;
        cccount_totalsiftaken = 0;
        pcamount_totalsiftaken = 0;
        pcU_totalsiftaken = 0;
    }
    /*
 * This method is used to display the data in the current selection section in the view saved takes tab
 */

    public void currentSelectionViewSavedTakes() {
        RPMlogger.info("currentSelectionViewSavedTakes");
        RPMlogger.log(Level.INFO, "currentSelectionViewSavedTakes() Begins");
        //checking if the no of MD candidates under the selected department and zone exceeds 80000.
        //If the no of records exceeds 80000, the user will be allowed to change the search criteria.

        try {
            if (searchCountValid == false) {
                cccount_totalsTaken = 0;
                pcamount_totalsTaken = 0;
                pcunit__totalsTaken = 0;
                cccount_totalsiftaken = 0;
                pcamount_totalsiftaken = 0;
                pcU_totalsiftaken = 0;
                return;
            }
            ClearanceWorksheetMB clearanceWorksheet =
                (ClearanceWorksheetMB)ADFContext.getCurrent().getViewScope().get("ClearanceWorksheetMB");
            this.ccCount_currentSelection_savedTakes = 0;
            this.pcAmount_currentSelection_savedTakes = 0.0;
            this.pcUnit_currentSelection_savedTakes = 0;           
            this.ccCount_currentSelection_savedTakes = clearanceWorksheet.getTotalRowsSelectedSavedTakesTab();
            this.pcAmount_currentSelection_savedTakes = clearanceWorksheet.getSumMdAmountSavedTakesTab();
            this.pcUnit_currentSelection_savedTakes = clearanceWorksheet.getSumInventorySavedTakesTab();
        } catch (ArithmeticException ae) {
            ae.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        RPMlogger.log(Level.INFO, "currentSelectionViewSavedTakes() Ends");

    }


    public void setCccount_totalsiftaken(int cccount_totalsiftaken) {
        this.cccount_totalsiftaken = cccount_totalsiftaken;
    }

    public int getCccount_totalsiftaken() {
        totalsTaken();
        return cccount_totalsiftaken;
    }


    public void setPcU_totalsiftaken(double pcU_totalsiftaken) {
        this.pcU_totalsiftaken = pcU_totalsiftaken;
    }

    public double getPcU_totalsiftaken() {
        totalsTaken();
        return pcU_totalsiftaken;
    }

    public void setCccount_totalsTaken(int cccount_totalsTaken) {
        this.cccount_totalsTaken = cccount_totalsTaken;
    }

    public int getCccount_totalsTaken() {
        totalsTaken();
        return cccount_totalsTaken;
    }

    public void setPcamount_totalsiftaken(double pcamount_totalsiftaken) {
        this.pcamount_totalsiftaken = pcamount_totalsiftaken;
    }

    public double getPcamount_totalsiftaken() {
        totalsTaken();
        return pcamount_totalsiftaken;
    }

    public void setPcamount_totalsTaken(double pcamount_totalsTaken) {
        this.pcamount_totalsTaken = pcamount_totalsTaken;
    }

    public double getPcamount_totalsTaken() {
        totalsTaken();

        return pcamount_totalsTaken;
    }

    public void setPcunit__totalsTaken(int pcunit__totalsTaken) {
        this.pcunit__totalsTaken = pcunit__totalsTaken;
    }

    public int getPcunit__totalsTaken() {
        totalsTaken();
        return pcunit__totalsTaken;
    }


    public void setCcCount_currentSelection_savedTakes(int ccCount_currentSelection_savedTakes) {
        this.ccCount_currentSelection_savedTakes =
                ccCount_currentSelection_savedTakes;
    }

    public int getCcCount_currentSelection_savedTakes() {
        currentSelectionViewSavedTakes();
        return ccCount_currentSelection_savedTakes;
    }

    public void setPcAmount_currentSelection_savedTakes(double pcAmount_currentSelection_savedTakes) {
        this.pcAmount_currentSelection_savedTakes =
                pcAmount_currentSelection_savedTakes;
    }

    public double getPcAmount_currentSelection_savedTakes() {
        currentSelectionViewSavedTakes();
        return pcAmount_currentSelection_savedTakes;
    }

    public void setPcUnit_currentSelection_savedTakes(int pcUnit_currentSelection_savedTakes) {
        this.pcUnit_currentSelection_savedTakes =
                pcUnit_currentSelection_savedTakes;
    }

    public int getPcUnit_currentSelection_savedTakes() {
        currentSelectionViewSavedTakes();
        return pcUnit_currentSelection_savedTakes;
    }

    /*
     * This is an action event method.
     * This method is called when the Save View button is clicked in the view saved takes tab on the clearance worksheet screen
     * This method is used to save the view when the users rearrange the columns in the view saved takes tab
     */

    public void saveViewInViewSavedTakesTab(ActionEvent actionEvent) {
        RPMlogger.info("saveviewActionListenerst");
        RPMlogger.log(Level.INFO, "saveviewActionListenerst() Begins");
        ClearanceWorksheetMB clearanceWorksheet =
            (ClearanceWorksheetMB)ADFContext.getCurrent().getViewScope().get("ClearanceWorksheetMB");

        try {
            oracle.binding.OperationBinding ob =
                BindingContext.getCurrent().getCurrentBindingsEntry().getOperationBinding("storeHistoryst");
            Map map = ob.getParamsMap();
            map.put("name", clearanceWorksheet.getTaonamest());
            map.put("order", getColumnsOrderViewSavedTakesTab());
            ob.execute();
            generateSavedListViewSavedTakesTab(userSession);
            clearanceWorksheet.setTaonamest("");
        } catch (MethodNotFoundException mnfe) {
            mnfe.printStackTrace();
        }
        RPMlogger.log(Level.INFO, "saveviewActionListenerst() Ends");

    }

    /*
 * This is a value change event method
 * This method is called when the user selects any option in the saved list dropdown in the view saved takes tab
 * This method is used to display the chosen view in the view saved takes tab.
 */

    public void chooseViewInViewSavedTakesTabVCL(ValueChangeEvent valueChangeEvent) {
        RPMlogger.info("chooseViewValueChangeListenerst");
        RPMlogger.log(Level.INFO, "chooseViewValueChangeListenerst() Begins");
        ClearanceWorksheetMB clearanceWorksheet =
            (ClearanceWorksheetMB)ADFContext.getCurrent().getViewScope().get("ClearanceWorksheetMB");
        try {
            String colmnorder = valueChangeEvent.getNewValue() + "";
            String defaultView =
                "0-25;1-30;2-40;3-40;4-40;5-45;6-152;7-100;8-50;9-70;10-223;11-100;12-70;13-80;14-83;15-77;16-78;17-83;18-81;19-82;20-122;21-84;22-80;23-89;24-85;25-107;26-108;27-79;28-100;29-66;30-68;31-100;32-94;33-66;34-38;35-47;36-61;37-64;38-95;39-42;40-42;";
            if (colmnorder.equals("null")) {
                colmnorder = defaultView;
            }
            colmnorder = colmnorder.trim();
            String cols[] = colmnorder.split(";");

            int ch = 0;
            for (UIComponent c : clearanceWorksheet.getBt2().getChildren()) {
                String col[] = cols[ch].split("-");
                if (c instanceof RichColumn) {
                    RichColumn rc = (RichColumn)c;
                    rc.setDisplayIndex(Integer.parseInt(col[0]));
                    rc.setWidth(col[1]);
                    ch++;
                }
            }
            AdfFacesContext.getCurrentInstance().addPartialTarget(clearanceWorksheet.getBt2());
        } catch (Exception e) {
            e.printStackTrace();
        }
        RPMlogger.log(Level.INFO, "chooseViewValueChangeListenerst() Ends");
    }


    public void candTabrangesizeVCL(ValueChangeEvent valueChangeEvent) {
        RPMlogger.info("candTabrangesizeVCL");
        RPMlogger.log(Level.INFO, "candTabrangesizeVCL() Begins");
        ClearanceWorksheetMB clearanceWorksheet =
            (ClearanceWorksheetMB)ADFContext.getCurrent().getViewScope().get("ClearanceWorksheetMB");
        try {
            if (valueChangeEvent.getNewValue() != null) {
                int i = Integer.parseInt(valueChangeEvent.getNewValue() + "");
                DCBindingContainer dcb =
                    (DCBindingContainer)BindingContext.getCurrent().getCurrentBindingsEntry();
                DCIteratorBinding dci =
                    dcb.findIteratorBinding("CandidteTab_TableRangeSizeView1Iterator");
                ViewObject vo = dci.getViewObject();
                Row row = vo.getRowAtRangeIndex(i);
                pageSizeCandidatesTab = row.getAttribute(0).toString();

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        RPMlogger.log(Level.INFO, "candTabrangesizeVCL() Ends");
    }

    public void viewSavedTakesTabRangeSizeVCL(ValueChangeEvent valueChangeEvent) {
        RPMlogger.info("viewSavedTakesTabRangeSizeVCL");
        RPMlogger.log(Level.INFO, "viewSavedTakesTabRangeSizeVCL() Begins");
        ClearanceWorksheetMB clearanceWorksheet =
            (ClearanceWorksheetMB)ADFContext.getCurrent().getViewScope().get("ClearanceWorksheetMB");
        try {
            if (valueChangeEvent.getNewValue() != null) {
                int i = Integer.parseInt(valueChangeEvent.getNewValue() + "");
                DCBindingContainer dcb =
                    (DCBindingContainer)BindingContext.getCurrent().getCurrentBindingsEntry();
                DCIteratorBinding dci =
                    dcb.findIteratorBinding("CandidteTab_TableRangeSizeView1Iterator");
                ViewObject vo = dci.getViewObject();
                Row row = vo.getRowAtRangeIndex(i);
                pageSizeViewSavedTakesTab = row.getAttribute(0).toString();


            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        RPMlogger.log(Level.INFO, "viewSavedTakesTabRangeSizeVCL() Ends");

    }


    public void setPageSizeCandidatesTab(String pageSizeCandidatesTab) {
        this.pageSizeCandidatesTab = pageSizeCandidatesTab;
    }

    public String getPageSizeCandidatesTab() {
        return pageSizeCandidatesTab;
    }

    public void setPageSizeViewSavedTakesTab(String pageSizeViewSavedTakesTab) {
        this.pageSizeViewSavedTakesTab = pageSizeViewSavedTakesTab;
    }

    public String getPageSizeViewSavedTakesTab() {
        return pageSizeViewSavedTakesTab;
    }

    /*
 * This is a query event method
 * This method is called when the users select any values in the filters section in the candidate tab
 */

    private String filterAttributes(StringBuffer filterBuffers, List filterIdArray, ArrayList<Object> attr_index, String iterator, String filterAttrName, DCBindingContainer bindContainer){
        DCIteratorBinding dci7 = bindContainer.findIteratorBinding(iterator);
        ViewObject vo_division = dci7.getViewObject();
        for (int i = 0; i < attr_index.size(); i++) {
            Row rows =
                vo_division.getRowAtRangeIndex(Integer.parseInt(attr_index.get(i).toString()));
            String attrId = rows.getAttribute(filterAttrName).toString();
            filterIdArray.add(attrId);
        }
        for (int argIndex = 0; argIndex < filterIdArray.size();
             argIndex++) {
            if (argIndex == 0) {
                String filterId = (String)filterIdArray.get(argIndex);
                filterBuffers.append(filterId);
            } else {
                filterBuffers.append(" OR ");
                String filterId = (String)filterIdArray.get(argIndex);
                filterBuffers.append(filterId);
            }
        }
        filterBuffers.append(" OR -1");
        String filterIds = filterBuffers.toString();
        
        return filterIds;
    }
   
    public void candidateTabFilterQuery(QueryEvent queryEvent) {
        RPMlogger.info("candidateTabFilterQuery");
        RPMlogger.log(Level.INFO, "candidateTabFilterQuery() Begins");
      try
      {
        FilterableQueryDescriptor fqd = (FilterableQueryDescriptor)queryEvent.getDescriptor();       
        Map<String, Object> criteriaMap  = fqd.getFilterCriteria();
        //Initialized divisionIdArray and divIdFilterString for the implementation of department select many choice filter
        List divisionIdArray = new ArrayList();
        StringBuffer divIdFilterString = new StringBuffer();
        //Initialized departmentIdArray and deptIdFilterString for the implementation of department select many choice filter
        List departmentIdArray = new ArrayList();
        StringBuffer deptIdFilterString = new StringBuffer();
        //Initialized classIdArray and classIdFilterString for the implementation of class select many choice filter
        List classIdArray = new ArrayList();
        StringBuffer classIdFilterString = new StringBuffer();
        //Initialized subClassIdArray and subClassIdFilterString for the implementation of subclass select many choice filter
        List subClassIdArray = new ArrayList();
        StringBuffer subClassIdFilterString = new StringBuffer();
        //Initialized labelArray and labelFilterString for the implementation of label select many choice filter
        List labelArray = new ArrayList();
        StringBuffer labelFilterString = new StringBuffer();
        //Initialized suppNameArray and suppNameFilterString for the implementation of supplier name select many choice filter
        List suppNameArray = new ArrayList();
        StringBuffer suppNameFilterString = new StringBuffer();
        //Initialized vpnArray and vpnFilterString for the implementation of VPN select many choice filter
        List vpnArray = new ArrayList();
        StringBuffer vpnFilterString = new StringBuffer();
        //Initialized itemArray and itemFilterString for the implementation of supplier color select many choice filter
        List itemArray = new ArrayList();
        StringBuffer itemFilterString = new StringBuffer();  
      List nrfColorArray = new ArrayList();
      StringBuffer nrfColorFilterString = new StringBuffer();
        //Initialized suppColorArray and suppColorFilterString for the implementation of supplier color select many choice filter
        List suppColorArray = new ArrayList();
        StringBuffer suppColorFilterString = new StringBuffer();
        StringBuffer StoreQtyFilterString = new StringBuffer();           
        StringBuffer itemDescFilterString = new StringBuffer();
        StringBuffer sixMnSellThruPctFilterString = new StringBuffer();
        StringBuffer storeOnOrderQtyFilterString = new StringBuffer();
        StringBuffer totalQtyFilterString = new StringBuffer();
        StringBuffer atsWeekFilterString = new StringBuffer();
        StringBuffer OriginalRetailFilterString = new StringBuffer();
        StringBuffer BaseRetailFilterString = new StringBuffer();
        StringBuffer CurrentRetailFilterString = new StringBuffer();
        StringBuffer PctOffOrigRetailFilterString = new StringBuffer();
        StringBuffer SuggestedRetailFilterString = new StringBuffer();
        StringBuffer SuggestedPctOffOrigRetailFilterString = new StringBuffer();
        StringBuffer MkdnAmountFilterString = new StringBuffer();
        StringBuffer WhQtyFilterString = new StringBuffer();
        StringBuffer WhOnOrderQtyFilterString = new StringBuffer();        
        StringBuffer ItemParentFilterString = new StringBuffer();
        StringBuffer SupplierFilterString = new StringBuffer();
        StringBuffer ZoneFilterString = new StringBuffer();
        StringBuffer NewRetailFilterString = new StringBuffer();
        StringBuffer ChangePercentFilterString = new StringBuffer();
        StringBuffer LastMkdnNbrFilterString = new StringBuffer();
        StringBuffer RpIndFilterString = new StringBuffer();
        StringBuffer RuleNameFilterString = new StringBuffer();
        StringBuffer ItemListConcatFilterString = new StringBuffer();        

        //the following code is the implementation of division select many choice filter in the clearance worksheet screen
        ArrayList<Object> index_dept = null;
        ArrayList<Object> index_item = null;
        ArrayList<Object> index_division = null;
        ArrayList<Object> index_class = null;
        ArrayList<Object> index_subClass = null;
        ArrayList<Object> index_label = null;
        ArrayList<Object> index_vpn = null;
        ArrayList<Object> index_suppName = null;
        ArrayList<Object> index_suppColor = null;
        ArrayList<Object> index_nrfColor = null;
        String index_storeQty = null;
        String index_ItemDesc = null;
        String index_SixMnSellThruPct = null;
        String index_StoreOnOrderQty = null;
        String index_TotalQty = null;
        String index_AtsWeek = null;
        String index_OriginalRetail = null;
        String index_BaseRetail = null;
        String index_CurrentRetail = null;
        String index_PctOffOrigRetail = null;
        String index_SuggestedRetail = null;
        String index_SuggestedPctOffOrigRetail = null;
        String index_MkdnAmount = null;
        String index_WhQty = null;
        String index_WhOnOrderQty = null;        
        String index_ItemParent = null;
        String index_Supplier = null;
        String index_Zone = null;
        String index_ChangePercent = null;
        String index_NewRetail = null;
        String index_LastMkdnNbr = null;
        String index_RpInd = null;
        String index_RuleName = null;
        String index_ItemListConcat = null;        
        String attributeId = "";
        String attributeIds = "";
        DCBindingContainer dcb7 = (DCBindingContainer)BindingContext.getCurrent().getCurrentBindingsEntry();
     
        
        if(criteriaMap.get("StoreQty") != null){            
            if(!criteriaMap.get("StoreQty").equals(""))
            {                               
                index_storeQty = (String)criteriaMap.get("StoreQty");
                attributeId = criteriaMap.get("StoreQty").toString();                                            
                StoreQtyFilterString.append(attributeId);                  
            }
            else{
                index_storeQty = "";                
                StoreQtyFilterString.append(attributeId);
                StoreQtyFilterString.delete(0, StoreQtyFilterString.length());                
            }
            StoreQtyFilterString.append(" OR -1");                        
            attributeIds = StoreQtyFilterString.toString();
            criteriaMap.put("StoreQty", attributeIds);
            fqd.setFilterCriteria(criteriaMap);
        }
      if(criteriaMap.get("ItemDesc") != null){                     
          if(!criteriaMap.get("ItemDesc").equals(""))
          {                               
              index_ItemDesc = (String)criteriaMap.get("ItemDesc");
              attributeId = criteriaMap.get("ItemDesc").toString();                                            
              itemDescFilterString.append(attributeId);                                                          
          }
          else{  
              index_ItemDesc = "";
              itemDescFilterString.append(attributeId);
              itemDescFilterString.delete(0, itemDescFilterString.length());              
          }
          itemDescFilterString.append(" OR -1"); 
          attributeIds = itemDescFilterString.toString();
          criteriaMap.put("ItemDesc", attributeIds);
          fqd.setFilterCriteria(criteriaMap);
      }
      if(criteriaMap.get("SixMnSellThruPct") != null){                      
          if(!criteriaMap.get("SixMnSellThruPct").equals(""))
          {                               
              index_SixMnSellThruPct = (String)criteriaMap.get("SixMnSellThruPct");
              attributeId = criteriaMap.get("SixMnSellThruPct").toString();                                            
              sixMnSellThruPctFilterString.append(attributeId);                                                                    
          }
          else{  
              index_SixMnSellThruPct = "";
              sixMnSellThruPctFilterString.append(attributeId);
              sixMnSellThruPctFilterString.delete(0, sixMnSellThruPctFilterString.length());              
          }
          sixMnSellThruPctFilterString.append(" OR -1");
          attributeIds = sixMnSellThruPctFilterString.toString();
          criteriaMap.put("SixMnSellThruPct", attributeIds);
          fqd.setFilterCriteria(criteriaMap);
      }
      if(criteriaMap.get("StoreOnOrderQty") != null){                      
          if(!criteriaMap.get("StoreOnOrderQty").equals(""))
          {                               
              index_StoreOnOrderQty = (String)criteriaMap.get("StoreOnOrderQty");
              attributeId = criteriaMap.get("StoreOnOrderQty").toString();                                            
              storeOnOrderQtyFilterString.append(attributeId);                                                                    
          }
          else{  
              index_StoreOnOrderQty = "";
              storeOnOrderQtyFilterString.append(attributeId);
              storeOnOrderQtyFilterString.delete(0, storeOnOrderQtyFilterString.length());              
          }
          storeOnOrderQtyFilterString.append(" OR -1");
          attributeIds = storeOnOrderQtyFilterString.toString();
          criteriaMap.put("StoreOnOrderQty", attributeIds);
          fqd.setFilterCriteria(criteriaMap);
      }
      if(criteriaMap.get("TotalQty") != null){                      
          if(!criteriaMap.get("TotalQty").equals(""))
          {                               
              index_TotalQty = (String)criteriaMap.get("TotalQty");
              attributeId = criteriaMap.get("TotalQty").toString();                                            
              totalQtyFilterString.append(attributeId);                                                                    
          }
          else{  
              index_TotalQty = "";
              totalQtyFilterString.append(attributeId);
              totalQtyFilterString.delete(0, totalQtyFilterString.length());              
          }
          totalQtyFilterString.append(" OR -1");
          attributeIds = totalQtyFilterString.toString();
          criteriaMap.put("TotalQty", attributeIds);
          fqd.setFilterCriteria(criteriaMap);
      }
      if(criteriaMap.get("AtsWeek") != null){                     
          if(!criteriaMap.get("AtsWeek").equals(""))
          {                               
              index_AtsWeek = (String)criteriaMap.get("AtsWeek");
              attributeId = criteriaMap.get("AtsWeek").toString();                                            
              atsWeekFilterString.append(attributeId);                                                                    
          }
          else{    
              index_AtsWeek = "";
              atsWeekFilterString.append(attributeId);
              atsWeekFilterString.delete(0, atsWeekFilterString.length());              
          }
          atsWeekFilterString.append(" OR -1");
          attributeIds = atsWeekFilterString.toString();
          criteriaMap.put("AtsWeek", attributeIds);
          fqd.setFilterCriteria(criteriaMap);
      }
      if(criteriaMap.get("OriginalRetail") != null){                      
          if(!criteriaMap.get("OriginalRetail").equals(""))
          {              
              index_OriginalRetail = (String)criteriaMap.get("OriginalRetail");
              attributeId = criteriaMap.get("OriginalRetail").toString();                                            
              OriginalRetailFilterString.append(attributeId);                                                                    
          }
          else{    
              index_OriginalRetail = "";
              OriginalRetailFilterString.append(attributeId);
              OriginalRetailFilterString.delete(0, OriginalRetailFilterString.length());              
          }
          OriginalRetailFilterString.append(" OR -1");
          attributeIds = OriginalRetailFilterString.toString();
          criteriaMap.put("OriginalRetail", attributeIds);
          fqd.setFilterCriteria(criteriaMap);
      }
      if(criteriaMap.get("BaseRetail") != null){                      
          if(!criteriaMap.get("BaseRetail").equals(""))
          {                    
              index_BaseRetail = (String)criteriaMap.get("BaseRetail");
              attributeId = criteriaMap.get("BaseRetail").toString();                                            
              BaseRetailFilterString.append(attributeId);                                                                    
          }
          else{ 
              index_BaseRetail = "";
              BaseRetailFilterString.append(attributeId);
              BaseRetailFilterString.delete(0, BaseRetailFilterString.length());              
          }
          BaseRetailFilterString.append(" OR -1");
          attributeIds = BaseRetailFilterString.toString();
          criteriaMap.put("BaseRetail", attributeIds);
          fqd.setFilterCriteria(criteriaMap);
      }
      if(criteriaMap.get("CurrentRetail") != null){                     
          if(!criteriaMap.get("CurrentRetail").equals(""))
          {                      
              index_CurrentRetail = (String)criteriaMap.get("CurrentRetail");
              attributeId = criteriaMap.get("CurrentRetail").toString();                                            
              CurrentRetailFilterString.append(attributeId);                                                                    
          }
          else{  
              index_CurrentRetail = "";
              CurrentRetailFilterString.append(attributeId);
              CurrentRetailFilterString.delete(0, CurrentRetailFilterString.length());              
          }
          CurrentRetailFilterString.append(" OR -1");
          attributeIds = CurrentRetailFilterString.toString();
          criteriaMap.put("CurrentRetail", attributeIds);
          fqd.setFilterCriteria(criteriaMap);
      }
      if(criteriaMap.get("PctOffOrigRetail") != null){                     
          if(!criteriaMap.get("PctOffOrigRetail").equals(""))
          {                 
              index_PctOffOrigRetail = (String)criteriaMap.get("PctOffOrigRetail");
              attributeId = criteriaMap.get("PctOffOrigRetail").toString();                                            
              PctOffOrigRetailFilterString.append(attributeId);                                                                    
          }
          else{              
              index_PctOffOrigRetail = "";
              PctOffOrigRetailFilterString.append(attributeId);
              PctOffOrigRetailFilterString.delete(0, PctOffOrigRetailFilterString.length());              
          }
          PctOffOrigRetailFilterString.append(" OR -1");
          attributeIds = PctOffOrigRetailFilterString.toString();
          criteriaMap.put("PctOffOrigRetail", attributeIds);
          fqd.setFilterCriteria(criteriaMap);
      }
      if(criteriaMap.get("SuggestedRetail") != null){                      
          if(!criteriaMap.get("SuggestedRetail").equals(""))
          {                 
              index_SuggestedRetail = (String)criteriaMap.get("SuggestedRetail");
              attributeId = criteriaMap.get("SuggestedRetail").toString();                                            
              SuggestedRetailFilterString.append(attributeId);                                                                    
          }
          else{              
              index_SuggestedRetail = "";
              SuggestedRetailFilterString.append(attributeId);
              SuggestedRetailFilterString.delete(0, SuggestedRetailFilterString.length());              
          }
          SuggestedRetailFilterString.append(" OR -1");
          attributeIds = SuggestedRetailFilterString.toString();
          criteriaMap.put("SuggestedRetail", attributeIds);
          fqd.setFilterCriteria(criteriaMap);
      }
          if(criteriaMap.get("SuggestedPctOffOrigRetail") != null){                          
              if(!criteriaMap.get("SuggestedPctOffOrigRetail").equals(""))
              {                 
                  index_SuggestedPctOffOrigRetail = (String)criteriaMap.get("SuggestedPctOffOrigRetail");
                  attributeId = criteriaMap.get("SuggestedPctOffOrigRetail").toString();                                            
                  SuggestedPctOffOrigRetailFilterString.append(attributeId);                                                                        
              }
              else{              
                  index_SuggestedPctOffOrigRetail = "";
                  SuggestedPctOffOrigRetailFilterString.append(attributeId);
                  SuggestedPctOffOrigRetailFilterString.delete(0, SuggestedPctOffOrigRetailFilterString.length());                  
              }
              SuggestedPctOffOrigRetailFilterString.append(" OR -1");
              attributeIds = SuggestedPctOffOrigRetailFilterString.toString();
              criteriaMap.put("SuggestedPctOffOrigRetail", attributeIds);
              fqd.setFilterCriteria(criteriaMap);
          }
          if(criteriaMap.get("MkdnAmount") != null){                         
              if(!criteriaMap.get("MkdnAmount").equals(""))
              {                 
                  index_MkdnAmount = (String)criteriaMap.get("MkdnAmount");
                  attributeId = criteriaMap.get("MkdnAmount").toString();                                            
                  MkdnAmountFilterString.append(attributeId);                                                                        
              }
              else{              
                  index_MkdnAmount = "";
                  MkdnAmountFilterString.append(attributeId);
                  MkdnAmountFilterString.delete(0, MkdnAmountFilterString.length());                  
              }
              MkdnAmountFilterString.append(" OR -1");
              attributeIds = MkdnAmountFilterString.toString();
              criteriaMap.put("MkdnAmount", attributeIds);
              fqd.setFilterCriteria(criteriaMap);
          }
          if(criteriaMap.get("WhQty") != null){                         
              if(!criteriaMap.get("WhQty").equals(""))
              {                 
                  index_WhQty = (String)criteriaMap.get("WhQty");
                  attributeId = criteriaMap.get("WhQty").toString();                                            
                  WhQtyFilterString.append(attributeId);                                                                        
              }
              else{              
                  index_WhQty = "";
                  WhQtyFilterString.append(attributeId);
                  WhQtyFilterString.delete(0, WhQtyFilterString.length());                  
              }
              WhQtyFilterString.append(" OR -1");
              attributeIds = WhQtyFilterString.toString();
              criteriaMap.put("WhQty", attributeIds);
              fqd.setFilterCriteria(criteriaMap);
          }
          if(criteriaMap.get("WhOnOrderQty") != null){                         
              if(!criteriaMap.get("WhOnOrderQty").equals(""))
              {                 
                  index_WhOnOrderQty = (String)criteriaMap.get("WhOnOrderQty");
                  attributeId = criteriaMap.get("WhOnOrderQty").toString();                                            
                  WhOnOrderQtyFilterString.append(attributeId);                                                                        
              }
              else{              
                  index_WhOnOrderQty = "";
                  WhOnOrderQtyFilterString.append(attributeId);
                  WhOnOrderQtyFilterString.delete(0, WhOnOrderQtyFilterString.length());                  
              }
              WhOnOrderQtyFilterString.append(" OR -1");
              attributeIds = WhOnOrderQtyFilterString.toString();
              criteriaMap.put("WhOnOrderQty", attributeIds);
              fqd.setFilterCriteria(criteriaMap);
          }          
          if(criteriaMap.get("ItemParent") != null){                         
              if(!criteriaMap.get("ItemParent").equals(""))
              {                 
                  index_ItemParent = (String)criteriaMap.get("ItemParent");
                  attributeId = criteriaMap.get("ItemParent").toString();                                            
                  ItemParentFilterString.append(attributeId);                                                                        
              }
              else{              
                  index_ItemParent = "";
                  ItemParentFilterString.append(attributeId);
                  ItemParentFilterString.delete(0, ItemParentFilterString.length());                  
              }
              ItemParentFilterString.append(" OR -1");
              attributeIds = ItemParentFilterString.toString();
              criteriaMap.put("ItemParent", attributeIds);
              fqd.setFilterCriteria(criteriaMap);
          }
          if(criteriaMap.get("Supplier") != null){                         
              if(!criteriaMap.get("Supplier").equals(""))
              {                 
                  index_Supplier = (String)criteriaMap.get("Supplier");
                  attributeId = criteriaMap.get("Supplier").toString();                                            
                  SupplierFilterString.append(attributeId);                                                                        
              }
              else{              
                  index_Supplier = "";
                  SupplierFilterString.append(attributeId);
                  SupplierFilterString.delete(0, SupplierFilterString.length());                  
              }
              SupplierFilterString.append(" OR -1");
              attributeIds = SupplierFilterString.toString();
              criteriaMap.put("Supplier", attributeIds);
              fqd.setFilterCriteria(criteriaMap);
          }
          if(criteriaMap.get("Zone") != null){                         
              if(!criteriaMap.get("Zone").equals(""))
              {                 
                  index_Zone = (String)criteriaMap.get("Zone");
                  attributeId = criteriaMap.get("Zone").toString();                                            
                  ZoneFilterString.append(attributeId);                                                                        
              }
              else{              
                  index_Zone = "";
                  ZoneFilterString.append(attributeId);
                  ZoneFilterString.delete(0, ZoneFilterString.length());                  
              }
              ZoneFilterString.append(" OR -1");
              attributeIds = ZoneFilterString.toString();
              criteriaMap.put("Zone", attributeIds);
              fqd.setFilterCriteria(criteriaMap);
          }
          if(criteriaMap.get("ChangePercent") != null){                         
              if(!criteriaMap.get("ChangePercent").equals(""))
              {                 
                  index_ChangePercent = (String)criteriaMap.get("ChangePercent");
                  attributeId = criteriaMap.get("ChangePercent").toString();                                            
                  ChangePercentFilterString.append(attributeId);                                                                        
              }
              else{              
                  index_ChangePercent = "";
                  ChangePercentFilterString.append(attributeId);
                  ChangePercentFilterString.delete(0, ChangePercentFilterString.length());                  
              }
              ChangePercentFilterString.append(" OR -1");
              attributeIds = ChangePercentFilterString.toString();
              criteriaMap.put("ChangePercent", attributeIds);
              fqd.setFilterCriteria(criteriaMap);
          }
          if(criteriaMap.get("NewRetail") != null){                         
              if(!criteriaMap.get("NewRetail").equals(""))
              {                 
                  index_NewRetail = (String)criteriaMap.get("NewRetail");
                  attributeId = criteriaMap.get("NewRetail").toString();                                            
                  NewRetailFilterString.append(attributeId);                                                                        
              }
              else{              
                  index_NewRetail = "";
                  NewRetailFilterString.append(attributeId);
                  NewRetailFilterString.delete(0, NewRetailFilterString.length());                  
              }
              NewRetailFilterString.append(" OR -1");
              attributeIds = NewRetailFilterString.toString();
              criteriaMap.put("NewRetail", attributeIds);
              fqd.setFilterCriteria(criteriaMap);
          }
          if(criteriaMap.get("LastMkdnNbr") != null){                         
              if(!criteriaMap.get("LastMkdnNbr").equals(""))
              {                 
                  index_LastMkdnNbr = (String)criteriaMap.get("LastMkdnNbr");
                  attributeId = criteriaMap.get("LastMkdnNbr").toString();                                            
                  LastMkdnNbrFilterString.append(attributeId);                                                                        
              }
              else{              
                  index_LastMkdnNbr = "";
                  LastMkdnNbrFilterString.append(attributeId);
                  LastMkdnNbrFilterString.delete(0, LastMkdnNbrFilterString.length());                  
              }
              LastMkdnNbrFilterString.append(" OR -1");
              attributeIds = LastMkdnNbrFilterString.toString();
              criteriaMap.put("LastMkdnNbr", attributeIds);
              fqd.setFilterCriteria(criteriaMap);
          }
          if(criteriaMap.get("RpInd") != null){                         
              if(!criteriaMap.get("RpInd").equals(""))
              {                 
                  index_RpInd = (String)criteriaMap.get("RpInd");
                  attributeId = criteriaMap.get("RpInd").toString();                                            
                  RpIndFilterString.append(attributeId);                                                                        
              }
              else{              
                  index_RpInd = "";
                  RpIndFilterString.append(attributeId);
                  RpIndFilterString.delete(0, RpIndFilterString.length());                  
              }
              RpIndFilterString.append(" OR -1");
              attributeIds = RpIndFilterString.toString();
              criteriaMap.put("RpInd", attributeIds);
              fqd.setFilterCriteria(criteriaMap);
          }
          if(criteriaMap.get("RuleName") != null){                         
              if(!criteriaMap.get("RuleName").equals(""))
              {                 
                  index_RuleName = (String)criteriaMap.get("RuleName");
                  attributeId = criteriaMap.get("RuleName").toString();                                            
                  RuleNameFilterString.append(attributeId);                                                                        
              }
              else{              
                  index_RuleName = "";
                  RuleNameFilterString.append(attributeId);
                  RuleNameFilterString.delete(0, RuleNameFilterString.length());                  
              }
              RuleNameFilterString.append(" OR -1");
              attributeIds = RuleNameFilterString.toString();
              criteriaMap.put("RuleName", attributeIds);
              fqd.setFilterCriteria(criteriaMap);
          }
          if(criteriaMap.get("ItemListConcat") != null){                         
              if(!criteriaMap.get("ItemListConcat").equals(""))
              {                 
                  index_ItemListConcat = (String)criteriaMap.get("ItemListConcat");
                  attributeId = criteriaMap.get("ItemListConcat").toString();                                            
                  ItemListConcatFilterString.append(attributeId);                                                                        
              }
              else{              
                  index_ItemListConcat = "";
                  ItemListConcatFilterString.append(attributeId);
                  ItemListConcatFilterString.delete(0, ItemListConcatFilterString.length());                  
              }
              ItemListConcatFilterString.append(" OR -1");
              attributeIds = ItemListConcatFilterString.toString();
              criteriaMap.put("ItemListConcat", attributeIds);
              fqd.setFilterCriteria(criteriaMap);
          }         
          
        if (criteriaMap.get("Division") != null) {
            index_division = (ArrayList<Object>)criteriaMap.get("Division");            
            String divisionIds = filterAttributes(divIdFilterString, divisionIdArray, index_division, "Filter_Division_View1Iterator", "Division", dcb7);
            criteriaMap.put("Division", divisionIds);
            fqd.setFilterCriteria(criteriaMap);
        }
        //the following code is the implementation of dept select many choice filter in the clearance worksheet screen
        if (criteriaMap.get("Dept") != null) {            
            index_dept = (ArrayList<Object>)criteriaMap.get("Dept");            
            String departmentIds = filterAttributes(deptIdFilterString, departmentIdArray, index_dept, "Filter_Department_View1Iterator", "Department", dcb7);       
            criteriaMap.put("Dept", departmentIds);
            fqd.setFilterCriteria(criteriaMap);
        }
        //the following code is the implementation of class select many choice filter in the candidates tab
        if (criteriaMap.get("Class1") != null) {            
            index_class = (ArrayList<Object>)criteriaMap.get("Class1");            
            String classIds = filterAttributes(classIdFilterString, classIdArray, index_class, "Class_Filter_LOV_View1Iterator", "Class1", dcb7);       
            criteriaMap.put("Class1", classIds);
            fqd.setFilterCriteria(criteriaMap);
        }
        //the following code is the implementation of subclass select many choice filter in the candidates tab
        if (criteriaMap.get("Subclass") != null) {            
            index_subClass = (ArrayList<Object>)criteriaMap.get("Subclass");            
            String subClassIds = filterAttributes(subClassIdFilterString, subClassIdArray, index_subClass, "Subclass_Filter_LOV_View1Iterator", "Subclass", dcb7);       
            criteriaMap.put("Subclass", subClassIds);
            fqd.setFilterCriteria(criteriaMap);
        }
        //the following code is the implementation of supplier label select many choice filter in the candidates tab
        if (criteriaMap.get("SuppLabel") != null) {            
            index_label = (ArrayList<Object>)criteriaMap.get("SuppLabel");           
            String labels = filterAttributes(labelFilterString, labelArray, index_label, "SuppLabel_Filter_LOV_View1Iterator", "SuppLabel", dcb7);       
            criteriaMap.put("SuppLabel", labels);
            fqd.setFilterCriteria(criteriaMap);
        }        
        //the following code is the implementation of VPN select many choice filter in the clearance worksheet screen
        if (criteriaMap.get("Vpn") != null) {            
            index_vpn = (ArrayList<Object>)criteriaMap.get("Vpn");           
            String vpnSelected = filterAttributes(vpnFilterString, vpnArray, index_vpn, "VPN_Filter_LOV_View1Iterator", "Vpn", dcb7);       
            criteriaMap.put("Vpn", vpnSelected);
            fqd.setFilterCriteria(criteriaMap);
        }            
        //the following code is the implementation of supplier name select many choice filter in the candidates tab
        if (criteriaMap.get("SuppName") != null) {            
            index_suppName = (ArrayList<Object>)criteriaMap.get("SuppName");            
            String names = filterAttributes(suppNameFilterString, suppNameArray, index_suppName, "SupplierName_Filter_LOV_View1Iterator", "SuppName", dcb7);       
            criteriaMap.put("SuppName", names);            
            fqd.setFilterCriteria(criteriaMap);            
        }        
        //the following code is the implementation of supplier color select many choice filter in the candidates tab
        if (criteriaMap.get("SuppColor") != null) {            
            index_suppColor = (ArrayList<Object>)criteriaMap.get("SuppColor");            
            String colors = filterAttributes(suppColorFilterString, suppColorArray, index_suppColor, "SuppColor_Filter_LOV_View1Iterator", "SuppColor", dcb7);       
            criteriaMap.put("SuppColor", colors);            
            fqd.setFilterCriteria(criteriaMap);
        }
        //the following code is the implementation of Item select many choice filter in the clearance worksheet screen
        if (criteriaMap.get("Item") != null) {            
            index_item = (ArrayList<Object>)criteriaMap.get("Item");            
            String itemsSelected = filterAttributes(itemFilterString, itemArray, index_item, "Item_Filter_LOV_View1Iterator", "Item", dcb7);       
            criteriaMap.put("Item", itemsSelected);
            fqd.setFilterCriteria(criteriaMap);
        } 
      if (criteriaMap.get("NrfColor") != null) {            
          index_nrfColor = (ArrayList<Object>)criteriaMap.get("NrfColor");            
          String nrfColorSelected = filterAttributes(nrfColorFilterString, nrfColorArray, index_nrfColor, "NRFColor_Filter_LOV1Iterator", "NrfColor", dcb7);       
          criteriaMap.put("NrfColor", nrfColorSelected);
          fqd.setFilterCriteria(criteriaMap);
      } 
        if (criteriaMap.get("Division") != null || criteriaMap.get("Dept") != null || criteriaMap.get("Class1") != null ||
            criteriaMap.get("Subclass") != null || criteriaMap.get("SuppLabel") != null || criteriaMap.get("Vpn") !=null ||
            criteriaMap.get("SuppName") != null || criteriaMap.get("SuppColor") != null || criteriaMap.get("Item") != null || 
            criteriaMap.get("StoreQty") != null  || criteriaMap.get("ItemDesc") != null ||
            criteriaMap.get("SixMnSellThruPct") != null || criteriaMap.get("StoreOnOrderQty") != null || criteriaMap.get("TotalQty") != null ||
            criteriaMap.get("AtsWeek") != null || criteriaMap.get("OriginalRetail") != null || criteriaMap.get("BaseRetail") != null ||
            criteriaMap.get("CurrentRetail") != null || criteriaMap.get("PctOffOrigRetail") != null || criteriaMap.get("SuggestedRetail") != null ||
            criteriaMap.get("SuggestedPctOffOrigRetail") != null || criteriaMap.get("MkdnAmount") != null || criteriaMap.get("WhQty") != null ||
            criteriaMap.get("WhOnOrderQty") != null || criteriaMap.get("NrfColor") != null || criteriaMap.get("ItemParent") != null ||
            criteriaMap.get("Supplier") != null || criteriaMap.get("Zone") != null || criteriaMap.get("NewRetail") != null ||
            criteriaMap.get("ChangePercent") != null || criteriaMap.get("LastMkdnNbr") != null || 
            criteriaMap.get("RpInd") != null || criteriaMap.get("RuleName") != null || criteriaMap.get("ItemListConcat") != null) {
            FacesContext fctx = FacesContext.getCurrentInstance();
            Application application = fctx.getApplication();
            ExpressionFactory expressionFactory =
                application.getExpressionFactory();
            ELContext elctx = fctx.getELContext();
            MethodExpression methodExpression = expressionFactory.createMethodExpression(elctx,
                                                         "#{bindings.Clr_Wksht_Rule_Item_View1Query.processQuery}",
                                                         Object.class,
                                                         new Class[] { QueryEvent.class });
            methodExpression.invoke(elctx, new Object[] { queryEvent });

            criteriaMap.put("Division", index_division);            
            criteriaMap.put("Dept", index_dept);
            criteriaMap.put("Class1", index_class);
            criteriaMap.put("Subclass", index_subClass);
            criteriaMap.put("SuppLabel", index_label);
            criteriaMap.put("SuppName", index_suppName);
            criteriaMap.put("SuppColor", index_suppColor);
            criteriaMap.put("Vpn", index_vpn);            
            criteriaMap.put("Item", index_item);                
            criteriaMap.put("StoreQty", index_storeQty);                                               
            criteriaMap.put("SixMnSellThruPct", index_SixMnSellThruPct);                
            criteriaMap.put("StoreOnOrderQty", index_StoreOnOrderQty);
            criteriaMap.put("TotalQty", index_TotalQty);
            criteriaMap.put("ItemDesc", index_ItemDesc);
            criteriaMap.put("AtsWeek", index_AtsWeek);
            criteriaMap.put("OriginalRetail", index_OriginalRetail);
            criteriaMap.put("BaseRetail", index_BaseRetail);
            criteriaMap.put("CurrentRetail", index_CurrentRetail);
            criteriaMap.put("PctOffOrigRetail", index_PctOffOrigRetail);
            criteriaMap.put("SuggestedRetail", index_SuggestedRetail);
            criteriaMap.put("SuggestedPctOffOrigRetail", index_SuggestedPctOffOrigRetail);            
            criteriaMap.put("MkdnAmount", index_MkdnAmount);
            criteriaMap.put("WhQty", index_WhQty);
            criteriaMap.put("WhOnOrderQty", index_WhOnOrderQty);
            criteriaMap.put("NrfColor", index_nrfColor);
            criteriaMap.put("ItemParent", index_ItemParent);
            criteriaMap.put("Supplier", index_Supplier);
            criteriaMap.put("Zone", index_Zone);
            criteriaMap.put("NewRetail", index_NewRetail);
            criteriaMap.put("ChangePercent", index_ChangePercent);            
            criteriaMap.put("LastMkdnNbr", index_LastMkdnNbr);
            criteriaMap.put("RpInd", index_RpInd);
            criteriaMap.put("RuleName", index_RuleName);
            criteriaMap.put("ItemListConcat", index_ItemListConcat);
            //criteriaMap.put("NewEffectiveDate", index_NewEffectiveDate);            
            fqd.setFilterCriteria(criteriaMap);            
        } 
        }
      
        catch(ArrayIndexOutOfBoundsException aiobe)
                {
                    aiobe.printStackTrace();
                    
                }
        catch(ViewObjectNotPreparedException vnpe)
                {
                    vnpe.printStackTrace();
                }
        catch(PropertyNotFoundException pnfe){
                  pnfe.printStackTrace();
              }
      catch(Exception e){
          e.printStackTrace();
      }
        RPMlogger.log(Level.INFO, "candidateTabFilterQuery() Ends");
    }


    /*
 * This is a value change event method.
 * This method is called when the user changes the new retail value in the candidate tab
 * This is used to calculate the MD Amount and MD Percent based on the new retail value.
 */

    public void newRetailCandidateTabVCL(ValueChangeEvent valueChangeEvent) {
        RPMlogger.info("newRetailCandidateTabVCL");
        RPMlogger.log(Level.INFO, "newRetailCandidateTabVCL() Begins");
        ClearanceWorksheetMB clearanceWorksheet =
            (ClearanceWorksheetMB)ADFContext.getCurrent().getViewScope().get("ClearanceWorksheetMB");
        try {
            this.newRtlValue=0.0;
            if (valueChangeEvent.getNewValue() != null) {
                this.restrictTakeAndSendToRPM=false;                
                Double newRetail = (Double)valueChangeEvent.getNewValue();
               this.newRtlValue=newRetail;
                Double baseRetail = (Double)clearanceWorksheet.getBind_baseRetail().getValue();
                Float compareAtRtl = (Float)clearanceWorksheet.getBind_compareAtRtl().getValue();
                Integer inventory = (Integer)clearanceWorksheet.getBind_inventory().getValue();
                Double currentRetail = (Double)clearanceWorksheet.getBind_currentRetail().getValue();
                if(newRetail > baseRetail || newRetail > currentRetail){
                    this.newRtlMDPctEffectiveDateChange = false;
                    this.restrictTakeAndSendToRPM=true;
                }
                if (newRetail < baseRetail || newRetail < currentRetail) {
                    this.restrictTakeAndSendToRPM=false;
                    this.newRtlMDPctEffectiveDateChange=true;
                    Double mdAmount = new Double(0.0);
                    Double mdpercent = new Double(0.0);
                    mdAmount = (baseRetail - newRetail) * inventory;
                    if(compareAtRtl==null){
                    mdpercent = 100 - (100 * (newRetail / baseRetail));
                    }
                    else{
                    mdpercent = 100 - (100 * (newRetail / compareAtRtl));
                    }
                    clearanceWorksheet.getBind_mdPercent().setValue(mdpercent);
                    clearanceWorksheet.getBind_mdAmount().setValue(mdAmount);
                }                
                ADFContext.getCurrent().getViewScope().put("NewRetail", newRetail);
            }
            else{
                this.newRtlValue=null;
                this.restrictTakeAndSendToRPM=true;
            }
        } catch (ArithmeticException ae) {
            ae.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        RPMlogger.log(Level.INFO, "newRetailCandidateTabVCL() Ends");
    }


    /*
 * This is a value change event method.
 * This method is called when the user changes the new MD percentage value in the candidate tab
 * This is used to calculate the MD Amount and new retail based on the new MD percentage value.
 */

    public void newMdPercentCandidateTabVCL(ValueChangeEvent valueChangeEvent) {
        RPMlogger.info("newMdPercentCandidateTabVCL");
        RPMlogger.log(Level.INFO, "newMdPercentCandidateTabVCL() Begins");
        ClearanceWorksheetMB clearanceWorksheet =
            (ClearanceWorksheetMB)ADFContext.getCurrent().getViewScope().get("ClearanceWorksheetMB");
        try {
            this.newMdPctValue=0.0;
            if (valueChangeEvent.getNewValue() != null) {                            
                Double newMdPercent = (Double)valueChangeEvent.getNewValue();
                this.newMdPctValue=newMdPercent;
                Double baseRetail = (Double)clearanceWorksheet.getBind_baseRetail().getValue();
                Float compareAtRtl = (Float)clearanceWorksheet.getBind_compareAtRtl().getValue();
                Integer inventory = (Integer)clearanceWorksheet.getBind_inventory().getValue();
                if(newMdPercent < 1.0 || newMdPercent > 100.0){
                    this.newRtlMDPctEffectiveDateChange = false;
                    this.restrictTakeAndSendToRPM=true;
                }
                if (newMdPercent >= 1.0 && newMdPercent <= 100.0) {
                    this.restrictTakeAndSendToRPM=false;
                    this.newRtlMDPctEffectiveDateChange = true;
                    Double mdAmount = new Double(0.0);
                    Double newRetail = new Double(0.0);
                    if(compareAtRtl==null){
                    newRetail = (100 - newMdPercent) * 0.01 * baseRetail;
                    }
                    else{
                    newRetail = (100 - newMdPercent) * 0.01 * compareAtRtl;
                    }
                    mdAmount = (baseRetail - newRetail) * inventory;
                    clearanceWorksheet.getBind_mdAmount().setValue(mdAmount);
                    clearanceWorksheet.getBind_newRetail().setValue(newRetail);                    
                }                                                    
                ADFContext.getCurrent().getViewScope().put("NewMDPercentage", newMdPercent);
            }
            else{
                this.newMdPctValue=null;
                this.restrictTakeAndSendToRPM=true;
            }
        } catch (ArithmeticException ae) {
            ae.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        RPMlogger.log(Level.INFO, "newMdPercentCandidateTabVCL() Ends");
    }

    /*
 * This is a value change event method.
 * This method is called when the user changes the new retail value in the saved takes tab
 * This is used to calculate the MD Amount and MD Percent based on the new retail value.
 */

    public void newRetailSavedTakesTabVCL(ValueChangeEvent valueChangeEvent) {
        RPMlogger.info("newRetailSavedTakesTabVCL");
        RPMlogger.log(Level.INFO, "newRetailSavedTakesTabVCL() Begins");
        ClearanceWorksheetMB clearanceWorksheet =
            (ClearanceWorksheetMB)ADFContext.getCurrent().getViewScope().get("ClearanceWorksheetMB");
        try {
            this.newRtlValue=0.0;
            if (valueChangeEvent.getNewValue() != null) {
                this.restrictTakeAndSendToRPM=false;
                Double newRetail = (Double)valueChangeEvent.getNewValue();
                this.newRtlValue=newRetail;
                Double baseRetail =
                    (Double)clearanceWorksheet.getBind_baseRetail_savedTakes().getValue();
                Float compareAtRtl = (Float)clearanceWorksheet.getBind_compareAtRtl_SavedTakes().getValue();
                Integer inventory =
                    (Integer)clearanceWorksheet.getBind_inventory_savedTakes().getValue();
                Double currentRetail =
                    (Double)clearanceWorksheet.getBind_currentRetail_savedTakes().getValue();
                if (newRetail < baseRetail || newRetail < currentRetail) {
                    this.restrictTakeAndSendToRPM=false;
                    this.newRtlMDPctEffectiveDateChange=true;
                    Double mdAmount = new Double(0.0);
                    Double mdpercent = new Double(0.0);
                    mdAmount = (baseRetail - newRetail) * inventory;
                    if(compareAtRtl==null){
                    mdpercent = 100 - (100 * (newRetail / baseRetail));
                    }
                    else{
                    mdpercent = 100 - (100 * (newRetail / compareAtRtl));
                    }
                    clearanceWorksheet.getBind_mdPercent_savedTakes().setValue(mdpercent);
                    clearanceWorksheet.getBind_mdAmount_savedTakes().setValue(mdAmount);
                }
                else{
                    this.restrictTakeAndSendToRPM=true;
                    this.newRtlMDPctEffectiveDateChange=false;
                }
                ADFContext.getCurrent().getViewScope().put("NewSavedRetail", newRetail);
            }
            else{
                this.newRtlValue=null;
                this.restrictTakeAndSendToRPM=true;
            }            
        } catch (ArithmeticException ae) {
            ae.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        RPMlogger.log(Level.INFO, "newRetailSavedTakesTabVCL() Ends");
    }

    /*
 * This is a value change event method.
 * This method is called when the user changes the new MD percentage value in the view saved takes tab
 * This is used to calculate the MD Amount and new retail based on the new MD percentage value.
 */

    public void newMdPercentSavedTakesTabVCL(ValueChangeEvent valueChangeEvent) {
        RPMlogger.info("newMdPercentSavedTakesTabVCL");
        RPMlogger.log(Level.INFO, "newMdPercentSavedTakesTabVCL() Begins");
        ClearanceWorksheetMB clearanceWorksheet =
            (ClearanceWorksheetMB)ADFContext.getCurrent().getViewScope().get("ClearanceWorksheetMB");
        try {
            this.newMdPctValue=0.0;
            if (valueChangeEvent.getNewValue() != null) {
                this.restrictTakeAndSendToRPM=false;
                Double newMdPercent = (Double)valueChangeEvent.getNewValue();
                this.newMdPctValue=newMdPercent;
                Double baseRetail =
                    (Double)clearanceWorksheet.getBind_baseRetail_savedTakes().getValue();
                Float compareAtRtl = (Float)clearanceWorksheet.getBind_compareAtRtl_SavedTakes().getValue();
                Integer inventory =
                    (Integer)clearanceWorksheet.getBind_inventory_savedTakes().getValue();
                if (newMdPercent >= 1.0 || newMdPercent <= 100.0) {
                    this.restrictTakeAndSendToRPM=false;
                    this.newRtlMDPctEffectiveDateChange=true;
                    Double mdAmount = new Double(0.0);
                    Double newRetail = new Double(0.0);
                    if(compareAtRtl==null){
                    newRetail = (100 - newMdPercent) * 0.01 * baseRetail;
                    }
                    else{
                    newRetail = (100 - newMdPercent) * 0.01 * compareAtRtl;
                    }
                    mdAmount = (baseRetail - newRetail) * inventory;
                    clearanceWorksheet.getBind_mdAmount_savedTakes().setValue(mdAmount);
                    clearanceWorksheet.getBind_newRetail_savedTakes().setValue(newRetail);
                }
                else{
                    this.restrictTakeAndSendToRPM=true;
                    this.newRtlMDPctEffectiveDateChange=false;
                }
                ADFContext.getCurrent().getViewScope().put("NewSavedMdPct", newMdPercent);
            }
            else{
                this.newMdPctValue=null;
                this.restrictTakeAndSendToRPM=true;
            }            
        } catch (ArithmeticException ae) {
            ae.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        RPMlogger.log(Level.INFO, "newMdPercentSavedTakesTabVCL() Ends");
    }

    /*
 * This is a value change event method.
 * This method is called when the user enters the new retail value in the bulk update section in the candidate tab
 * This method is used to remove the value in the new MD percentage input field in the bulk update section in the candidate tab
 */

    public void newRetailCandidateTabBulkUpdateVCL(ValueChangeEvent valueChangeEvent) {
        RPMlogger.info("newRetailCandidateTabBulkUpdateVCL");
        RPMlogger.log(Level.INFO,
                      "newRetailCandidateTabBulkUpdateVCL() Begins");
        ClearanceWorksheetMB clearanceWorksheet =
            (ClearanceWorksheetMB)ADFContext.getCurrent().getViewScope().get("ClearanceWorksheetMB");
        try {
            if (valueChangeEvent.getNewValue().toString() != null) {
                String newRetail = valueChangeEvent.getNewValue().toString();
                if (!newRetail.equals("")) {
                    clearanceWorksheet.getBind_newMdPercent_candidateTab_bulkUpdate().setValue(null);
                    AdfFacesContext.getCurrentInstance().addPartialTarget(clearanceWorksheet.getBind_newMdPercent_candidateTab_bulkUpdate());
                } else {
                    AdfFacesContext.getCurrentInstance().addPartialTarget(clearanceWorksheet.getBind_newMdPercent_candidateTab_bulkUpdate());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        RPMlogger.log(Level.INFO, "newRetailCandidateTabBulkUpdateVCL() Ends");
    }

    /*
 * This is a value change event method.
 * This method is called when the user enters the new MD percentage value in the bulk update section in the candidate tab
 * This method is used to remove the new retail input field value in the bulk update section in the candidate tab
 */

    public void newMdPercentCandidateTabBulkUpdateVCL(ValueChangeEvent valueChangeEvent) {
        RPMlogger.info("newMdPercentCandidateTabBulkUpdateVCL");
        RPMlogger.log(Level.INFO, "newMdPercentCandidateTabBulkUpdateVCL() Begins");
        ClearanceWorksheetMB clearanceWorksheet =
            (ClearanceWorksheetMB)ADFContext.getCurrent().getViewScope().get("ClearanceWorksheetMB");
        try {

            if (valueChangeEvent.getNewValue().toString() != null) {
                String newMdPercent = valueChangeEvent.getNewValue().toString();
                if (!newMdPercent.equals("")) {
                    clearanceWorksheet.getBind_newRetail_candidateTab_bulkUpdate().setValue(null);
                    AdfFacesContext.getCurrentInstance().addPartialTarget(clearanceWorksheet.getBind_newRetail_candidateTab_bulkUpdate());
                } else {
                    AdfFacesContext.getCurrentInstance().addPartialTarget(clearanceWorksheet.getBind_newRetail_candidateTab_bulkUpdate());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        RPMlogger.log(Level.INFO, "newMdPercentCandidateTabBulkUpdateVCL() Ends");
    }

    /*
 * This is a value change event method.
 * This method is called when the user enters the new retail value in the bulk update section in the view saved takes tab
 * This method is used to remove the value in the new MD percentage input field in the bulk update section in the view saved takes tab
 *
 */

    public void newRetailSavedTakesTabBulkUpdateVCL(ValueChangeEvent valueChangeEvent) {
        RPMlogger.info("newRetailSavedTakesTabBulkUpdateVCL");
        RPMlogger.log(Level.INFO,
                      "newRetailSavedTakesTabBulkUpdateVCL() Begins");
        ClearanceWorksheetMB clearanceWorksheet =
            (ClearanceWorksheetMB)ADFContext.getCurrent().getViewScope().get("ClearanceWorksheetMB");
        try {
            if (valueChangeEvent.getNewValue().toString() != null) {
                String newRetail = valueChangeEvent.getNewValue().toString();
                if (!newRetail.equals("")) {
                    clearanceWorksheet.getBind_newMdPercent_savedTakesTab_bulkUpdate().setValue(null);
                    AdfFacesContext.getCurrentInstance().addPartialTarget(clearanceWorksheet.getBind_newMdPercent_savedTakesTab_bulkUpdate());
                } else {
                    AdfFacesContext.getCurrentInstance().addPartialTarget(clearanceWorksheet.getBind_newMdPercent_savedTakesTab_bulkUpdate());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        RPMlogger.log(Level.INFO, "newRetailSavedTakesTabBulkUpdateVCL() Ends");
    }

    /*
 * This is a value change event method.
 *  This method is called when the user enters the new MD percentage value in the bulk update section in the view saved takes tab
 *  This method is used to remove the new retail input field value in the bulk update section in the view saved takes tab
 */

    public void newMdPercentSavedTakesTabBulkUpdateVCL(ValueChangeEvent valueChangeEvent) {
        RPMlogger.info("newMdPercentSavedTakesTabBulkUpdateVCL");
        RPMlogger.log(Level.INFO, "newMdPercentSavedTakesTabBulkUpdateVCL() Begins");
        ClearanceWorksheetMB clearanceWorksheet =
            (ClearanceWorksheetMB)ADFContext.getCurrent().getViewScope().get("ClearanceWorksheetMB");
        try {
            if (valueChangeEvent.getNewValue().toString() != null) {
                String newMdPercent = valueChangeEvent.getNewValue().toString();
                if (!newMdPercent.equals("")) {
                    clearanceWorksheet.getBind_newRetail_savedTakesTab_bulkUpdate().setValue(null);
                    AdfFacesContext.getCurrentInstance().addPartialTarget(clearanceWorksheet.getBind_newRetail_savedTakesTab_bulkUpdate());
                } else {
                    AdfFacesContext.getCurrentInstance().addPartialTarget(clearanceWorksheet.getBind_newRetail_savedTakesTab_bulkUpdate());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        RPMlogger.log(Level.INFO, "newMdPercentSavedTakesTabBulkUpdateVCL() Ends");
    }

    /*
 * This is a dialog event method
 * This method is called when the user performs any action in the dialog box which is displayed when the user clicks on send to RPM
 * If the user gives OK in the dialog box, the MD candidates will be sent to RPM.
 */

    public void sendToRPMDialogListener(DialogEvent dialogEvent) {
        RPMlogger.info("sendToRPMDialogListener");
        RPMlogger.log(Level.INFO, "sendToRPMDialogListener() Begins");
        ClearanceWorksheetMB clearanceWorksheet =
            (ClearanceWorksheetMB)ADFContext.getCurrent().getViewScope().get("ClearanceWorksheetMB");
        try {            
            if (dialogEvent.getOutcome() == DialogEvent.Outcome.yes) {
                if (clearanceWorksheet.getBind_dialog_sendToRPM().getAffirmativeTextAndAccessKey().equalsIgnoreCase("Yes")) {
                    //clearing the unsaved changes on proceeding with the send to RPM operation.
                    rollbackForClearButton();
                    OperationBinding ob = (OperationBinding)BindingContext.getCurrent().getCurrentBindingsEntry().getOperationBinding("rpmAction");
                    ob.getParamsMap().put("SeqId",
                                          clearanceWorksheet.getStseqidlist().toString().replace("[",
                                                                                                 "(").replace("]",
                                                                                                              ")"));
                    //setting the update id as user id
                    setValueToEL("#{bindings.UpdateId.inputValue}", userSession.getAttribute("userName"));
                    String updateId = getValueFrmExpression("#{bindings.UpdateId.inputValue}");
                    ob.getParamsMap().put("updateId", updateId);
                    ob.execute();                    
                    resetCurrentSelectionViewSavedTakesTab(clearanceWorksheet);
                } else if (clearanceWorksheet.getBind_dialog_sendToRPM().getAffirmativeTextAndAccessKey().equalsIgnoreCase("Show error")) {                    
                    showErrorWhileSendToRPMOrUntake(clearanceWorksheet.getBind_popUp_sendToRPM(), clearanceWorksheet.getBind_dialog_sendToRPM(),
                                                    newRtlErrorMsg, newEffectiveDateErrorMsg);
                }
                else if(clearanceWorksheet.getBind_dialog_sendToRPM().getAffirmativeTextAndAccessKey().equalsIgnoreCase("Ok")){
                    clearanceWorksheet.getBind_dialog_sendToRPM().setAffirmativeTextAndAccessKey("Yes");
                    AdfFacesContext.getCurrentInstance().addPartialTarget(clearanceWorksheet.getBind_dialog_sendToRPM());
                }
                System.runFinalization();
            }
            else if(dialogEvent.getOutcome() == DialogEvent.Outcome.no){               
                resetAll();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        RPMlogger.log(Level.INFO, "sendToRPMDialogListener() Ends");
    }

    private void resetCurrentSelectionViewSavedTakesTab(ClearanceWorksheetMB clearanceWorksheet){
        clearanceWorksheet.getStseqidlist().clear();
        clearanceWorksheet.setTotalRowsSelectedSavedTakesTab(0);
        clearanceWorksheet.setSumMdAmountSavedTakesTab(0.0);
        clearanceWorksheet.setSumInventorySavedTakesTab(0);
        clearanceWorksheet.getBind_headerCheckBox_SavedTakesTab().setValue(false);
        clearanceWorksheet.setUpdateCount1(clearanceWorksheet.getStseqidlist().size());
        AdfFacesContext.getCurrentInstance().addPartialTarget(clearanceWorksheet.getBt2());
        resetAll();
    }
    /*
 * This is a popup fetch event
 * This method is called when the user clicks on send to RPM button before the popup is displayed.
 * This method has all the validations to be done before sending the MD candidates to the RPM
 */

    public void validationBeforeSendToRPM(PopupFetchEvent popupFetchEvent) {
        RPMlogger.info("validationBeforeSendToRPM");
        RPMlogger.log(Level.INFO, "validationBeforeSendToRPM() Begins");
        ClearanceWorksheetMB clearanceWorksheet =
            (ClearanceWorksheetMB)ADFContext.getCurrent().getViewScope().get("ClearanceWorksheetMB");
        //System.out.println("list selected ==="+clearanceWorksheet.getFtseqidlist());
        try {           
            DCIteratorBinding dci = dbc.findIteratorBinding("Clr_Wksht_Rule_Item_View1Iterator");
            ViewObject vo = dci.getViewObject();
            vo.reset();
            if (clearanceWorksheet.getCandidateTab().isDisclosed()) {
                vo.setWhereClause("seq_no in " +
                                  clearanceWorksheet.getFtseqidlist().toString().replace("[",
                                                                                         "(").replace("]",
                                                                                                      ")"));
            } else if (clearanceWorksheet.getViewSavedTakesTab().isDisclosed()) {
                vo.setWhereClause("seq_no in " +
                                  clearanceWorksheet.getStseqidlist().toString().replace("[",
                                                                                         "(").replace("]",
                                                                                                      ")"));
            }
            vo.executeQuery();
            Row[] rows = vo.getAllRowsInRange();
            Row row = null;
            int count1 = 0, count2 = 0, count3 = 0, count4 = 0, count5 = 0, count6 = 0, errorFlag = 0;                                                                      
            String messageOne = "", messageTwo = "", messageThree = "";                        
            for (int i = 0; i < rows.length; i++) {
                row = rows[i];
                /* Hard Stop validation 1 -validation to check if the new retail is less than or equal to the base retail
        * This is performed to display an error message to the user when the Send to RPM button is clicked.
        **/
                Double newretail = (Double)row.getAttribute("NewRetail");
                Double baseRetail = (Double)row.getAttribute("BaseRetail");
                Double currentRetail = (Double)row.getAttribute("CurrentRetail");
                Double mkDnPercent = (Double)row.getAttribute("ChangePercent");
                if (newretail >= baseRetail) {
                    count3 = count3 + 1;
                    errorFlag = 1;
                    validationAttributes();                    
                }
                /* Hard Stop validation 2 -validation to check if the new retail is less than or equal to the current retail
     * This is performed to display an error message to the user when the Send to RPM button is clicked.
     **/
                if (newretail >= currentRetail) {
                    count5 = count5 + 1;
                    errorFlag = 1;                    
                    validationAttributes();
                }
                if(ADFContext.getCurrent().getViewScope().get("NewMDPercentage") != null){
                    if(Double.parseDouble(ADFContext.getCurrent().getViewScope().get("NewMDPercentage").toString())  < 1.0 || 
                       Double.parseDouble(ADFContext.getCurrent().getViewScope().get("NewMDPercentage").toString()) > 100.0){                           
                           validationAttributes();                           
                       }
                    if(Double.parseDouble(ADFContext.getCurrent().getViewScope().get("NewMDPercentage").toString()) == mkDnPercent){
                        this.newRtlMDPctEffectiveDateChange = true;
                        this.allowTake = false;
                        this.allowUnTake = true;
                    }
                }
                if(ADFContext.getCurrent().getViewScope().get("NewRetail") != null){
                    if(Double.parseDouble(ADFContext.getCurrent().getViewScope().get("NewRetail").toString()) >= baseRetail ||
                       Double.parseDouble(ADFContext.getCurrent().getViewScope().get("NewRetail").toString()) >= currentRetail){                           
                           validationAttributes();                           
                       }
                    if(Double.parseDouble(ADFContext.getCurrent().getViewScope().get("NewRetail").toString()) == newretail){
                        this.newRtlMDPctEffectiveDateChange = true;
                        this.allowTake = false;
                        this.allowUnTake = true;
                    }
                        
                }               
                if(ADFContext.getCurrent().getViewScope().get("NewSavedRetail") != null){
                    if(Double.parseDouble(ADFContext.getCurrent().getViewScope().get("NewSavedRetail").toString()) >= baseRetail ||
                       Double.parseDouble(ADFContext.getCurrent().getViewScope().get("NewSavedRetail").toString()) >= currentRetail){                           
                           validationAttributes();
                       }
                    if(Double.parseDouble(ADFContext.getCurrent().getViewScope().get("NewSavedRetail").toString()) == newretail){
                        this.newRtlMDPctEffectiveDateChange = true;
                        this.allowTake = false;
                        this.allowUnTake = true;
                    }
                }
                if(ADFContext.getCurrent().getViewScope().get("NewSavedMdPct") != null){
                    if(Double.parseDouble(ADFContext.getCurrent().getViewScope().get("NewSavedMdPct").toString()) < 1.0 ||
                       Double.parseDouble(ADFContext.getCurrent().getViewScope().get("NewSavedMdPct").toString()) > 100){
                           validationAttributes();
                       }
                    if(Double.parseDouble(ADFContext.getCurrent().getViewScope().get("NewSavedMdPct").toString()) == mkDnPercent){
                        this.newRtlMDPctEffectiveDateChange = true;
                        this.allowTake = false;
                        this.allowUnTake = true;
                    }
                }
                
                /* Hard Stop validation 3 -validation to check if the new effective date is greater than currentdate + 3 days
    * This is performed to display an error message to the user when the Send to RPM button is clicked.
    **/               
                DCIteratorBinding dcib = dbc.findIteratorBinding("V_Date_View1Iterator");
                ViewObject viewObj = dcib.getViewObject();
                Row row1 = viewObj.first();
                Date currentDate = (Date)row1.getAttribute("Vdate");
                Date newEffectiveDate = (Date)row.getAttribute("NewEffectiveDate");
                long daysDiff = (newEffectiveDate.getValue().getTime() - currentDate.getValue().getTime()) / (24 * 60 * 60 * 1000);
                if (daysDiff <= 3) {
                    count6 = count6 + 1;
                    errorFlag = 1;
                    this.newRtlMDPctEffectiveDateChange = false;
                    this.newEffectiveDateErrorMsg = true;
                    this.restrictTakeAndSendToRPM = true;
                    this.allowTake = false;
                    this.allowUnTake = false;
                }                
                if(newEffectiveDate==null)
                    this.newEffectiveDateErrorMsg = true;
                if(ADFContext.getCurrent().getViewScope().get("NewEffectiveDate") != null){
                    long daysDiff2 = (((Date)ADFContext.getCurrent().getViewScope().get("NewEffectiveDate")).getValue().getTime() - currentDate.getValue().getTime()) / (24 * 60 * 60 * 1000);
                    if(daysDiff2 <= 3){
                        this.newRtlMDPctEffectiveDateChange = false;
                        this.newEffectiveDateErrorMsg = true;                                            
                        this.restrictTakeAndSendToRPM=true;
                        this.allowTake = false;
                        this.allowUnTake = false;
                    }
                    if(((Date)ADFContext.getCurrent().getViewScope().get("NewEffectiveDate")).dateValue().compareTo(newEffectiveDate.dateValue()) == 0){
                        this.newRtlMDPctEffectiveDateChange = true;
                        this.allowTake = false;
                        this.allowUnTake = true;
                    }
                }
                if(ADFContext.getCurrent().getViewScope().get("NewSavedEffectiveDate") != null){
                    long daysDiff3 = (((Date)ADFContext.getCurrent().getViewScope().get("NewSavedEffectiveDate")).getValue().getTime() - currentDate.getValue().getTime()) / (24 * 60 * 60 * 1000);
                    if(daysDiff3 <= 3){
                        this.newEffectiveDateErrorMsg = true;
                        this.newRtlMDPctEffectiveDateChange = false;                        
                        this.restrictTakeAndSendToRPM = true;
                        this.allowTake = false;
                        this.allowUnTake = false;
                    }
                    if(((Date)ADFContext.getCurrent().getViewScope().get("NewSavedEffectiveDate")).dateValue().compareTo(newEffectiveDate.dateValue()) == 0){
                        this.newRtlMDPctEffectiveDateChange = true;
                        this.allowTake = false;
                        this.allowUnTake = true;
                    }
                }
                /* soft check validation 1 -validation to check if the % off original retail is greater than or equal to 95%
    * This is performed to display a confirmation message to the user when the Send to RPM button is clicked.
    **/
                Double percentOffOrigRetail = (Double)row.getAttribute("PctOffOrigRetail");
                if (percentOffOrigRetail >= 95) 
                    count1 = count1 + 1;
                
                /* soft check validation 2 -validation to check if the new effective date entered by the user is same as existing effective date
     * This is performed to display to confirmation message to the user when the Send to RPM button is clicked.
     **/
                Date existingEffectiveDate = (Date)row.getAttribute("EffectiveDate");
                if (newEffectiveDate.compareTo(existingEffectiveDate) == 0) 
                    count2 = count2 + 1;
                
                /* soft check validation 3 -validation to check if the new effective date is greater than current date + 90
     * This is performed to display a confirmation message to the user when the Send to RPM button is clicked.
    **/
                if (daysDiff >= 90) {
                    count4 = count4 + 1;
                    if(this.restrictTakeAndSendToRPM)
                        allowTake = false;
                    else
                        allowTake = true;
                }
            }
            //error messages to be displayed in case of hard stop validation failures            
            if (count5 > 0 || count3 > 0) {
                //New retail is equal to or greater than current retail.
                FacesMessageUtils.addErrorMessage("New retail is equal to or greater than current retail. Clearance retail must decrease.",
                                                  FacesMessage.SEVERITY_ERROR);
            }

            if (count6 > 0) {
                //Clearance Effective Date is > Current Date + 3 days.
                FacesMessageUtils.addErrorMessage("Clearance New Effective Date is > Current Date + 3 days.",
                                                  FacesMessage.SEVERITY_ERROR);
            }
            //informational messages to be displayed in the popup incase of softstop validation failures
            if (count1 > 0) 
                messageOne = "The original retail is >= 95%"; //setting the message after the validation
            
            if (count2 > 0) 
                messageTwo = "The new effective date is same as the default effective date";
            
            if (count4 > 0) 
                messageThree = "MDs staged for 90 days in the future. Please verify effective date.";            

            if (errorFlag == 1 || this.restrictTakeAndSendToRPM==true) {
                if (clearanceWorksheet.getViewSavedTakesTab().isDisclosed()) {
                    clearanceWorksheet.getBind_dialog_sendToRPM().setAffirmativeTextAndAccessKey("Show error");
                    AdfFacesContext.getCurrentInstance().addPartialTarget(clearanceWorksheet.getBind_dialog_sendToRPM());
                    clearanceWorksheet.getBind_Untake().setAffirmativeTextAndAccessKey("Show error");
                    AdfFacesContext.getCurrentInstance().addPartialTarget(clearanceWorksheet.getBind_Untake());
                } else if (clearanceWorksheet.getCandidateTab().isDisclosed()) {
                    clearanceWorksheet.getBind_dialog_Take().setAffirmativeTextAndAccessKey("Show error");
                    AdfFacesContext.getCurrentInstance().addPartialTarget(clearanceWorksheet.getBind_dialog_Take());
                }               
            }            
            else{
                if (clearanceWorksheet.getViewSavedTakesTab().isDisclosed()) {
                    if(this.newRtlMDPctEffectiveDateChange){
                        clearanceWorksheet.getBind_dialog_sendToRPM().setAffirmativeTextAndAccessKey("Ok");                        
                        AdfFacesContext.getCurrentInstance().addPartialTarget(clearanceWorksheet.getBind_dialog_sendToRPM()); 
                        clearanceWorksheet.getBind_Untake().setAffirmativeTextAndAccessKey("Yes");
                        AdfFacesContext.getCurrentInstance().addPartialTarget(clearanceWorksheet.getBind_Untake()); 
                    }
                    else{
                        clearanceWorksheet.getBind_dialog_sendToRPM().setAffirmativeTextAndAccessKey("Yes");
                        AdfFacesContext.getCurrentInstance().addPartialTarget(clearanceWorksheet.getBind_dialog_sendToRPM());
                        clearanceWorksheet.getBind_Untake().setAffirmativeTextAndAccessKey("Yes");
                        AdfFacesContext.getCurrentInstance().addPartialTarget(clearanceWorksheet.getBind_Untake()); 
                    }
                } else if (clearanceWorksheet.getCandidateTab().isDisclosed()) {
                    if(this.newRtlMDPctEffectiveDateChange){
                        clearanceWorksheet.getBind_dialog_Take().setAffirmativeTextAndAccessKey("Ok");                        
                        AdfFacesContext.getCurrentInstance().addPartialTarget(clearanceWorksheet.getBind_dialog_Take());                        
                    }
                    else{
                        clearanceWorksheet.getBind_dialog_Take().setAffirmativeTextAndAccessKey("Yes");
                        AdfFacesContext.getCurrentInstance().addPartialTarget(clearanceWorksheet.getBind_dialog_Take());
                    }
                } 
            }
            clearanceWorksheet.setFinalMessageOne(messageOne); //concatenating all the messages to show in the popup
            clearanceWorksheet.setFinalMessageTwo(messageTwo);
            clearanceWorksheet.setFinalMessageThree(messageThree);
            //after performing validations the view saved takes tab is again refreshed
            if (clearanceWorksheet.getCandidateTab().isDisclosed()) {
                vo.setWhereClause("upper(status)='N' and dept in " + clearanceWorksheet.getDeptList() +
                                  " and zone =" + clearanceWorksheet.getZoneId()); //fetching the records whose status is 'T' and 'S' (taken and sent to RPM MD candidates)
            } else if (clearanceWorksheet.getViewSavedTakesTab().isDisclosed()) {
                vo.setWhereClause("upper(status)!='N' and dept in " + clearanceWorksheet.getDeptList() +
                                  " and zone =" + clearanceWorksheet.getZoneId()); //fetching the records whose status is 'T' and 'S' (taken and sent to RPM MD candidates)
            }
            vo.executeQuery();
        } catch (ArithmeticException ae) {
            ae.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        RPMlogger.log(Level.INFO, "validationBeforeSendToRPM() Ends");
    }

    private void validationAttributes(){
        this.newRtlMDPctEffectiveDateChange = false;
        this.newRtlErrorMsg = true;
        this.restrictTakeAndSendToRPM = true;
        this.allowTake = false;
        this.allowUnTake = false;
    }
    public void filterReset() {
        ClearanceWorksheetMB clearanceWorksheet =
            (ClearanceWorksheetMB)ADFContext.getCurrent().getViewScope().get("ClearanceWorksheetMB");
        try {
            FilterableQueryDescriptor qd = (FilterableQueryDescriptor)clearanceWorksheet.getBt1().getFilterModel();            
            if (qd != null && qd.getFilterCriteria() != null) {
                qd.getFilterCriteria().clear();
                clearanceWorksheet.getBt1().queueEvent(new QueryEvent(clearanceWorksheet.getBt1(), qd));
            }

            FacesContext fctx = FacesContext.getCurrentInstance();
            Application application = fctx.getApplication();
            ExpressionFactory expressionFactory = application.getExpressionFactory();
            ELContext elctx = fctx.getELContext();
            MethodExpression methodExpression =
                expressionFactory.createMethodExpression(elctx,
                                                         "#{bindings.Clr_Wksht_Rule_Item_View1Query.processQuery}",
                                                         Object.class,
                                                         new Class[] { QueryEvent.class });
            QueryEvent qe = new QueryEvent(clearanceWorksheet.getBt1(), qd);
            methodExpression.invoke(elctx, new Object[] { qe });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setValueToEL(String el, Object val) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ELContext elContext = facesContext.getELContext();
        ExpressionFactory expressionFactory = facesContext.getApplication().getExpressionFactory();
        ValueExpression valueExp =
            expressionFactory.createValueExpression(elContext, el,
                                                    Object.class);
        valueExp.setValue(elContext, val);
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

    public void takeMDCandidatesDialogListener(DialogEvent dialogEvent) {
        ClearanceWorksheetMB clearanceWorksheet =
            (ClearanceWorksheetMB)ADFContext.getCurrent().getViewScope().get("ClearanceWorksheetMB");
        try {                      
            if (dialogEvent.getOutcome() == DialogEvent.Outcome.yes) {
                if (clearanceWorksheet.getBind_dialog_Take().getAffirmativeTextAndAccessKey().equalsIgnoreCase("Yes")) {
                    //clearing the unsaved changes on proceeding with the take operation
                    rollbackForClearButton();
                    setValueToEL("#{bindings.UpdateId.inputValue}", userSession.getAttribute("userName"));
                    OperationBinding ob = (OperationBinding)BindingContext.getCurrent().getCurrentBindingsEntry().getOperationBinding("takeAction");
                    ob.getParamsMap().put("SeqId",
                                          clearanceWorksheet.getFtseqidlist().toString().replace("[",
                                                                                                 "(").replace("]",
                                                                                                              ")"));
                    String updateId = getValueFrmExpression("#{bindings.UpdateId.inputValue}");
                    ob.getParamsMap().put("updateId", updateId);
                    ob.execute();
                    clearanceWorksheet.setTotalRowsselectedcandidate(0);
                    clearanceWorksheet.setSummdamountcandidate(0);
                    clearanceWorksheet.setSuminventorycandidate(0);
                    this.totalmdamountcandidate = 0;
                    this.totalinventorycandidate = 0;
                    clearanceWorksheet.getBind_headerCheckBox_candidateTab().setValue(false);                    
                    clearanceWorksheet.getFtseqidlist().clear();
                    clearanceWorksheet.setUpdateCount(0); // to disable the take button once the take action is done and if no records are selected
                    clearanceWorksheet.getBt1().resetStampState();
                    AdfFacesContext.getCurrentInstance().addPartialTarget(clearanceWorksheet.getBt1());
                    resetAll();
                
                } else if (clearanceWorksheet.getBind_dialog_Take().getAffirmativeTextAndAccessKey().equalsIgnoreCase("Show error")) {                    
                    showErrorWhileSendToRPMOrUntake(clearanceWorksheet.getBind_popUp_Take(), clearanceWorksheet.getBind_dialog_Take(),
                                                    newRtlErrorMsg, newEffectiveDateErrorMsg);
                }
                else if(clearanceWorksheet.getBind_dialog_Take().getAffirmativeTextAndAccessKey().equalsIgnoreCase("Ok")){                    
                    clearanceWorksheet.getBind_dialog_Take().setAffirmativeTextAndAccessKey("Yes");
                    AdfFacesContext.getCurrentInstance().addPartialTarget(clearanceWorksheet.getBind_dialog_Take());
                }
                System.runFinalization();
            }
            else if(dialogEvent.getOutcome() == DialogEvent.Outcome.no)
                resetAll();               
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void resetAll(){
        this.newRtlMDPctEffectiveDateChange = false;
        this.newRtlErrorMsg = false;
        this.newMDPctErrorMsg = false;
        this.newEffectiveDateErrorMsg = false;
        this.restrictTakeAndSendToRPM = false;
        this.allowTake = true;  
        this.allowUnTake = true;
    }
    public void saveChangesCandidateTab(ActionEvent actionEvent) {
        ClearanceWorksheetMB clearanceWorksheet =
            (ClearanceWorksheetMB)ADFContext.getCurrent().getViewScope().get("ClearanceWorksheetMB");
        try {
            OperationBinding oBinding = (OperationBinding)BindingContext.getCurrent().getCurrentBindingsEntry().getOperationBinding("Commit");
            oBinding.execute();
            this.totalmdamountcandidate = 0;
            this.totalinventorycandidate = 0;
            clearanceWorksheet.setTotalRowsselectedcandidate(0);
            clearanceWorksheet.setSummdamountcandidate(0);
            clearanceWorksheet.setSuminventorycandidate(0);
            clearanceWorksheet.getBt1().resetStampState();
            clearanceWorksheet.getFtseqidlist().clear();
            clearanceWorksheet.setUpdateCount(0);
            clearanceWorksheet.getBind_headerCheckBox_candidateTab().resetValue();
            clearanceWorksheet.setFtheaderBstatus(false);
            clearanceWorksheet.getBind_rowCheckbox_candidateTab().resetValue();
            clearanceWorksheet.getBind_rowCheckbox_candidateTab().setValue(false);
            AdfFacesContext.getCurrentInstance().addPartialTarget(clearanceWorksheet.getBt1());
            AdfFacesContext.getCurrentInstance().addPartialTarget(clearanceWorksheet.getBind_headerCheckBox_candidateTab());
            AdfFacesContext.getCurrentInstance().addPartialTarget(clearanceWorksheet.getBind_rowCheckbox_candidateTab());
            resetAll();
            clearNewRtlMDPctEffDateScopes();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void clearNewRtlMDPctEffDateScopes(){
        ADFContext.getCurrent().getViewScope().put("NewRetail", null);
        ADFContext.getCurrent().getViewScope().put("NewMDPercentage", null);
        ADFContext.getCurrent().getViewScope().put("NewEffectiveDate", null);
    }
    public void newEffectiveDateCandidateTabVCL(ValueChangeEvent valueChangeEvent) {
        if(valueChangeEvent.getNewValue()!=null){
        Date newEffectiveDate = (Date)valueChangeEvent.getNewValue(); //this is a oracle.jbo.domain.date
        this.newEffectiveDateValue=newEffectiveDate;       
        //fetching the vdate as the current date        
        DCIteratorBinding dcib = dbc.findIteratorBinding("V_Date_View1Iterator");
        ViewObject viewObj = dcib.getViewObject();
        Row dateRow = viewObj.first();
        Date currentDate = (Date)dateRow.getAttribute("Vdate"); // this is a oracle.jbo.domain.date

        //calculating the days difference between the new effective date and current date
        long nextEffectiveDateTime = newEffectiveDate.getValue().getTime();
        long currentDateTime = currentDate.getValue().getTime();
        long daysDiff = (nextEffectiveDateTime - currentDateTime) / (24 * 60 * 60 * 1000);
        if (daysDiff <= 3) {
            this.restrictTakeAndSendToRPM=true;
            this.newRtlMDPctEffectiveDateChange=false;
        }
        else{
            this.restrictTakeAndSendToRPM=false;
            this.newRtlMDPctEffectiveDateChange=true;
        }
        ADFContext.getCurrent().getViewScope().put("NewEffectiveDate", newEffectiveDate);
    }
        else{
            this.restrictTakeAndSendToRPM=true;
            this.newEffectiveDateErrorMsg = true;
            this.newEffectiveDateValue=null;
        }        
    }

    public void newEffectiveDateSavedTakesTabVCL(ValueChangeEvent valueChangeEvent) {
        if(valueChangeEvent.getNewValue()!=null){
            Date newEffectiveDate = (Date)valueChangeEvent.getNewValue(); //this is a oracle.jbo.domain.date
            this.newEffectiveDateValue=newEffectiveDate;
            //fetching the vdate as the current date        
            DCIteratorBinding dcib = dbc.findIteratorBinding("V_Date_View1Iterator");
            ViewObject viewObj = dcib.getViewObject();
            Row dateRow = viewObj.first();
            Date currentDate = (Date)dateRow.getAttribute("Vdate"); // this is a oracle.jbo.domain.date
            //calculating the days difference between the new effective date and current date
            long nextEffectiveDateTime = newEffectiveDate.getValue().getTime();
            long currentDateTime = currentDate.getValue().getTime();
            long daysDiff = (nextEffectiveDateTime - currentDateTime) / (24 * 60 * 60 * 1000);
            if (daysDiff <= 3) {
                this.restrictTakeAndSendToRPM=true;
                this.newRtlMDPctEffectiveDateChange=false;
            }
            else{
                this.restrictTakeAndSendToRPM=false;
                this.newRtlMDPctEffectiveDateChange=true;
            }
            ADFContext.getCurrent().getViewScope().put("NewSavedEffectiveDate", newEffectiveDate);
        }   
        else{
            this.restrictTakeAndSendToRPM=true;
            this.newEffectiveDateErrorMsg = true;
            this.newEffectiveDateValue=null;
        }        
    }

    public void untakeMDCandidatesDialogListener(DialogEvent dialogEvent) {
        ClearanceWorksheetMB clearanceWorksheet =
            (ClearanceWorksheetMB)ADFContext.getCurrent().getViewScope().get("ClearanceWorksheetMB");       
        try {
            if (dialogEvent.getOutcome() == DialogEvent.Outcome.yes) {
                if (clearanceWorksheet.getBind_Untake().getAffirmativeTextAndAccessKey().equalsIgnoreCase("Yes")) {
                    //clearing the unsaved changes
                    rollbackForClearButton();               
                    //setting the update id as user id
                    setValueToEL("#{bindings.UpdateId.inputValue}", userSession.getAttribute("userName"));
                    String updateId = getValueFrmExpression("#{bindings.UpdateId.inputValue}");    
                    OperationBinding ob = (OperationBinding)BindingContext.getCurrent().getCurrentBindingsEntry().getOperationBinding("untakeAction");
                    ob.getParamsMap().put("SeqId",
                                          clearanceWorksheet.getStseqidlist().toString().replace("[",
                                                                                                 "(").replace("]",
                                                                                                              ")"));
                    ob.getParamsMap().put("updateId", updateId);
                    ob.execute();                   
                    resetCurrentSelectionViewSavedTakesTab(clearanceWorksheet);
                }
                else if (clearanceWorksheet.getBind_Untake().getAffirmativeTextAndAccessKey().equalsIgnoreCase("Show error")) {                    
                    showErrorWhileSendToRPMOrUntake(clearanceWorksheet.getBind_PopUp_Untake(), clearanceWorksheet.getBind_Untake(),
                                                    newRtlErrorMsg, newEffectiveDateErrorMsg);
                }
            }
            else if(dialogEvent.getOutcome() == DialogEvent.Outcome.no){               
                resetAll();
            }
         System.runFinalization();
        }
        catch (MethodNotFoundException mnfe) {
                    mnfe.printStackTrace();
        }
        catch (Exception e) {
        e.printStackTrace();
        }
    }
    
    private void showErrorWhileSendToRPMOrUntake(RichPopup popUp, RichDialog dialog, boolean newRtlErrorMsg, boolean newEffectiveDateErrorMsg){
        popUp.cancel();
        AdfFacesContext.getCurrentInstance().addPartialTarget(popUp);
        if (newRtlErrorMsg == true) {
            FacesMessageUtils.addErrorMessage("New retail is greater than or equal to base retail or current retail. New Clearance retail must decrease",
                                              FacesMessage.SEVERITY_ERROR);
        }
        if (newEffectiveDateErrorMsg == true) {
            FacesMessageUtils.addErrorMessage("Clearance Effective Date is > Current Date + 3 days.",
                                              FacesMessage.SEVERITY_ERROR);
        }                                       
        resetAll();
        dialog.setAffirmativeTextAndAccessKey("Yes");
        AdfFacesContext.getCurrentInstance().addPartialTarget(dialog);
    }
    public void setNewRtlMDPctEffectiveDateChange(boolean newRtlMDPctEffectiveDateChange) {
        this.newRtlMDPctEffectiveDateChange = newRtlMDPctEffectiveDateChange;
    }

    public boolean isNewRtlMDPctEffectiveDateChange() {
        return newRtlMDPctEffectiveDateChange;
    }

    public void setRestrictTakeAndSendToRPM(boolean restrictTakeAndSendToRPM) {
        this.restrictTakeAndSendToRPM = restrictTakeAndSendToRPM;
    }

    public boolean isRestrictTakeAndSendToRPM() {
        return restrictTakeAndSendToRPM;
    }

    public void commonDeptsAccess(PopupFetchEvent popupFetchEvent) {
        // Add event code here...
        ClearanceWorksheetMB clearanceWorksheet = (ClearanceWorksheetMB)ADFContext.getCurrent().getViewScope().get("ClearanceWorksheetMB");
        String messageOne = "";
        String messageTwo = "";        
        DCIteratorBinding deptAccess = dbc.findIteratorBinding("Clr_Wksht_Dept_AccessView1Iterator");        
        ArrayList usersAccessOnDepts = new ArrayList();       
        boolean access = Boolean.valueOf(ADFContext.getCurrent().getRequestScope().get("deptAccessDenied").toString());
        Row[] deptRows = deptAccess.getAllRowsInRange();
        ArrayList commonDepts = (ArrayList)ADFContext.getCurrent().getViewScope().get("CommonDepts");       
        for(int i=0;i<commonDepts.size();i++){
            for(Row deptRow: deptRows){
                if(deptRow.getAttribute("Dept").equals(commonDepts.get(i))){ 
                    if(usersAccessOnDepts.size()==0)
                        usersAccessOnDepts.add(deptRow.getAttribute("UserId").toString().toUpperCase());
                    else{
                        if(!deptRow.getAttribute("UserId").toString().toUpperCase().equalsIgnoreCase(usersAccessOnDepts.get(i-i).toString()))                        
                            usersAccessOnDepts.add(deptRow.getAttribute("UserId"));
                    }
                                        
                    break;
                }
            }            
        }        
        for(Row deptRow: deptRows){        
            if(deptRows.length >= 1 && !userSession.getAttribute("userName").toString().equalsIgnoreCase(deptRow.getAttribute("UserId").toString()) && access){
                   messageOne = "Department "+ commonDepts.toString().replace("[", "").replace("]", "") +" is/are on use by '"
                                + usersAccessOnDepts.toString().replace("[", "").replace("]", "") +"'";
                   messageTwo = "Changes cannot be made for any of the selected department(s).";
                   clearanceWorksheet.setFinalMessageOne(messageOne);
                   clearanceWorksheet.setFinalMessageTwo(messageTwo);
                   clearanceWorksheet.getBind_SaveCandidates().setDisabled(true);
                   AdfFacesContext.getCurrentInstance().addPartialTarget(clearanceWorksheet.getBind_SaveCandidates());
                   break;
               }            
        }
    }

    public void setAllowTake(boolean allowTake) {
        this.allowTake = allowTake;
    }

    public boolean isAllowTake() {
        return allowTake;
    }   

    public void setAllowUnTake(boolean allowUnTake) {
        this.allowUnTake = allowUnTake;
    }

    public boolean isAllowUnTake() {
        return allowUnTake;
    }
}

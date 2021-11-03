package com.nordstrom.rpm.Backing;

import com.nordstrom.rpm.Service.ClearanceWorksheetService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.event.ActionEvent;

import javax.faces.event.ValueChangeEvent;

import javax.faces.model.SelectItem;

import oracle.adf.share.ADFContext;
import oracle.adf.view.rich.component.rich.RichDialog;
import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.component.rich.data.RichColumn;
import oracle.adf.view.rich.component.rich.data.RichTable;
import oracle.adf.view.rich.component.rich.input.RichInputDate;
import oracle.adf.view.rich.component.rich.input.RichInputText;
import oracle.adf.view.rich.component.rich.input.RichSelectBooleanCheckbox;
import oracle.adf.view.rich.component.rich.input.RichSelectManyChoice;
import oracle.adf.view.rich.component.rich.input.RichSelectOneChoice;
import oracle.adf.view.rich.component.rich.layout.RichPanelBox;
import oracle.adf.view.rich.component.rich.layout.RichPanelGroupLayout;
import oracle.adf.view.rich.component.rich.layout.RichPanelTabbed;
import oracle.adf.view.rich.component.rich.layout.RichShowDetailItem;

import oracle.adf.view.rich.component.rich.nav.RichCommandButton;
import oracle.adf.view.rich.event.DialogEvent;
import oracle.adf.view.rich.event.PopupFetchEvent;
import oracle.adf.view.rich.event.QueryEvent;

import org.apache.myfaces.trinidad.event.DisclosureEvent;

/****************************************************************************
* Class Name            : ClearanceWorksheetMB.java 
* Language              : Java
* Description           : This is a backing bean class which has all the bind properties of the Clearance_Worksheet.jsff page.
*                         The properties of the Clearance_Worksheet.jsff page can be accessed in this class.
* Author                : Ramesh Chinta, Infosys Ltd.
*                         Lasya Yadati, Infosys Ltd.
* Date written          : 31-Aug-2015 
*
* Modification History  :
*
* Description of change                 Date           Modified by 
* ---------------------                 ----           -----------
*****************************************************************************/

public class ClearanceWorksheetMB {
    ClearanceWorksheetService clearanceService = (ClearanceWorksheetService)ADFContext.getCurrent().getViewScope().get("ClearanceWorksheetImpl");
    // First table Candidate  Variables
    private RichPanelTabbed panelTab;
    private Boolean showCandidatesTab;
    private Boolean showViewSavedTakesTab;
    private RichShowDetailItem candidatesTab;
    private RichShowDetailItem viewSavedTakesTab;
    private RichTable bt1;
    private RichTable bt2;
    private Boolean ftheaderBstatus;
    private Boolean stheaderBstatus;
    private String ftseqid;
    private List ftseqidlist = new ArrayList();
    private Date nexteffectivedate;
    private String racknewretail;
    private String racknewpercentMD;
    private int updateCount;
    private String  dept;
    private List<String> divisionValuesSelected=new ArrayList<String>();
    private String deptList;
    private String zoneId;
    private String zoneGroupId;
    private String division;
    private String mdamountcandidate;
    private String inventory;
    private double summdamountcandidate;
    private double suminventorycandidate;
    private int totalRowsselectedcandidate;
  
   
   

// Second table View saved Takes Variables
    private String stseqid;
    private List stseqidlist = new ArrayList();
    private int updateCount1;
    private Date stnexteffectivedate;
    private String stracknewretail;
    private String stracknewpercentMD;
    private List deptValuesSelected=new ArrayList();
    private String finalMessageOne;
    private String finalMessageTwo;
    private String finalMessageThree;
    private String finalMessageFour;
    private double sumMdAmountSavedTakesTab;
    private int sumInventorySavedTakesTab;
    private int totalRowsSelectedSavedTakesTab;
    private String mdAmountSavedTakesTab;
    private String inventorySavedTakesTab;
    
    // View Saved Dropdown Values Varialbles
    
    private String taoname;
    private String taonamest;
    private List<SelectItem> savedList = new ArrayList<SelectItem>();
    private List<SelectItem> savedListst = new ArrayList<SelectItem>();
    
    private String ftsaveview;
    private RichSelectOneChoice bind_savedlistCandidate;
    private RichSelectOneChoice bind_savedlistCandidatest;
    private RichPanelGroupLayout bind_CandidateBox;
    private RichPanelGroupLayout bind_ViewSavedBox;
    private RichSelectManyChoice bind_Dept;
    private RichSelectOneChoice bind_candtabRangesize;
    private RichSelectOneChoice bind_viewSavedTakesTabRangeSize;
    private RichInputText bind_baseRetail;
    private RichInputText bind_inventory;
    private RichInputText bind_mdAmount;
    private RichInputText bind_mdPercent;
    private RichInputText bind_newRetail;
    private RichInputText bind_newRetail_savedTakes;
    private RichInputText bind_baseRetail_savedTakes;
    private RichInputText bind_inventory_savedTakes;
    private RichInputText bind_mdAmount_savedTakes;
    private RichInputText bind_mdPercent_savedTakes;
    private RichInputText bind_newRetail_candidateTab_bulkUpdate;
    private RichInputText bind_newMdPercent_candidateTab_bulkUpdate;
    private RichInputText bind_newRetail_savedTakesTab_bulkUpdate;
    private RichInputText bind_newMdPercent_savedTakesTab_bulkUpdate;
    private RichSelectBooleanCheckbox bind_headerCheckBox_candidateTab;
    private RichSelectBooleanCheckbox bind_headerCheckBox_SavedTakesTab;
    private RichInputDate bind_nextEffectiveDate_canddiateTab_bulkUpdate;
    private RichInputDate bind_nextEffectiveDate_savedTakesTab_bulkUpdate;
    private RichInputText bind_currentRetail;
    private RichInputText bind_currentRetail_savedTakes;
    private RichSelectBooleanCheckbox bind_rowCheckbox_candidateTab;
    private RichSelectBooleanCheckbox bind_rowCheckbox_ViewSavedTakesTab;
    private RichDialog bind_dialog_sendToRPM;
    private RichPopup bind_popUp_sendToRPM;
    private RichPopup bind_popUp_Take;
    private RichDialog bind_dialog_Take;
    private RichInputDate bind_nextEffectiveDate_CandidateTab;
    private RichInputDate bind_nextEffectiveDate_SavedTakesTab;
    private RichSelectManyChoice bind_SupplierName_filter;
    private RichSelectManyChoice bind_Division_filter;
    private RichSelectManyChoice bind_Dept_filter;
    private RichSelectManyChoice bind_Class_filter;
    private RichSelectManyChoice bind_SubClass_filter;
    private RichSelectManyChoice bind_VPN_filter;
    private RichSelectManyChoice bind_Item_filter;
    private RichSelectManyChoice bind_SupplierColor_filter;
    private RichSelectManyChoice bind_Label_filter;
    private RichSelectManyChoice bind_NRFColor_Filter;
    private RichCommandButton bind_SaveCandidates;
    private RichPopup bind_DeptAccess_PopUp;
    private RichInputDate bind_EffectiveDate_filter;
    private RichInputDate bind_NewEffectiveDate_filter;
    private RichInputDate bind_LastReceiptDate_filter;
    private RichInputDate bind_LastMkdnDate_filter;
    private RichSelectManyChoice bind_SavedDivision_filter;
    private RichSelectManyChoice bind_SavedDept_filter;
    private RichSelectManyChoice bind_SavedClass_filter;
    private RichSelectManyChoice bind_SavedSubClass_filter;
    private RichSelectManyChoice bind_SavedSupName_filter;
    private RichSelectManyChoice bind_SupLabel_filter;
    private RichSelectManyChoice bind_SavedVPN_filter;
    private RichSelectManyChoice bind_SavedItem_filter;
    private RichSelectManyChoice bind_SavedSupColor_filter;
    private RichInputDate bind_SavedEffDate_filter;
    private RichInputDate bind_SavedNwEffDate_filter;
    private RichInputDate bind_SavedLastRctDt_filter;
    private RichInputDate bind_LastMDnDt_filter;
    private RichSelectManyChoice bind_SavedNrfColor_filter;
    private RichCommandButton bind_SaveCandidates2;
    private RichCommandButton bind_SaveTakesCandidates;
    private RichCommandButton bind_SaveTakesCandidates2;
    private RichDialog bind_Untake;
    private RichPopup bind_PopUp_Untake;
    private RichInputText bind_compareAtRtl;
    private RichInputText bind_compareAtRtl_SavedTakes;


    public ClearanceWorksheetMB() {
        super();
    //System.out.println("bind value base retail"+this.getBind_baseRetail());
     
    }
    
    public void setPanelTab(RichPanelTabbed panelTab) {
        this.panelTab = panelTab;
    }

    public RichPanelTabbed getPanelTab() {
        return panelTab;
    }

    public void setClearanceService(ClearanceWorksheetService clearanceService) {
        this.clearanceService = clearanceService;
    }
    public void getDepartmentVCL(ValueChangeEvent valueChangeEvent) {
        // Add event code here...
        clearanceService.getDepartmentVCL(valueChangeEvent);
        
    }
    
    

    public ClearanceWorksheetService getClearanceService() {
        return clearanceService;
    }

    public void setShowCandidateTab(Boolean showCandidatesTab) {
        this.showCandidatesTab = showCandidatesTab;
    }

    public Boolean getShowCandidatesTab() {
        return showCandidatesTab;
    }

    public void setShowViewSavedTakesTab(Boolean showViewSavedTakesTab) {
        this.showViewSavedTakesTab = showViewSavedTakesTab;
    }

    public Boolean getShowViewSavedTakesTab() {
        return showViewSavedTakesTab;
    }

    public void setCandidateTab(RichShowDetailItem candidatesTab) {
        this.candidatesTab = candidatesTab;
    }

    public RichShowDetailItem getCandidateTab() {
        return candidatesTab;
    }

    public void setViewSavedTakesTab(RichShowDetailItem viewSavedTakesTab) {
        this.viewSavedTakesTab = viewSavedTakesTab;
    }

    public RichShowDetailItem getViewSavedTakesTab() {
        return viewSavedTakesTab;
    }
    
    public void searchActionListener(ActionEvent actionEvent) {
        // Add event code here...
        clearanceService.searchActionListener(actionEvent);
    }

    public void getDeptFrmDivisionVCL(ValueChangeEvent valueChangeEvent) {
        clearanceService.getDeptFrmDivisionVCL(valueChangeEvent);
    }

    public void candidatesTabDisclosureListener(DisclosureEvent disclosureEvent) {
        // Add event code here...
        clearanceService.candidatesTabDisclosureListener(disclosureEvent);
        
    }

    public void viewSavedTakesTabDisclosureListener(DisclosureEvent disclosureEvent) {
        // Add event code here...
        clearanceService.viewSavedTakesTabDisclosureListener(disclosureEvent);
    }

    public void setBt1(RichTable bt1) {
        this.bt1 = bt1;
    }

    public RichTable getBt1() {
        return bt1;
    }

    public void setBt2(RichTable bt2) {
        this.bt2 = bt2;
    }

    public RichTable getBt2() {
        return bt2;
    }

    public void candidateTabHeaderCheckBoxVCL(ValueChangeEvent valueChangeEvent) {
        // Add event code here...
        clearanceService.candidateTabHeaderCheckBoxVCL(valueChangeEvent);
    }

    public void setFtheaderBstatus(Boolean ftheaderBstatus) {
        this.ftheaderBstatus = ftheaderBstatus;
    }

    public Boolean getFtheaderBstatus() {
        return ftheaderBstatus;
    }

    public void candidateTabRowCheckBoxVCL(ValueChangeEvent valueChangeEvent) {
        // Add event code here...
        clearanceService.candidateTabRowCheckBoxVCL(valueChangeEvent);
    }

    public void viewSavedTakesTabHeaderCheckBoxVCL(ValueChangeEvent valueChangeEvent) {
        // Add event code here...
        clearanceService.viewSavedTakesTabHeaderCheckBoxVCL(valueChangeEvent);
    }

    public void setStheaderBstatus(Boolean stheaderBstatus) {
        this.stheaderBstatus = stheaderBstatus;
    }

    public Boolean getStheaderBstatus() {
        return stheaderBstatus;
    }

    public void viewSavedTakesTabRowCheckBoxVCL(ValueChangeEvent valueChangeEvent) {
        // Add event code here...
        clearanceService.viewSavedTakesTabRowCheckBoxVCL(valueChangeEvent);
    }

    public void setFtseqid(String ftseqid) {
        this.ftseqid = ftseqid;
    }

    public String getFtseqid() {
        return ftseqid;
    }

    public void setFtseqidlist(List ftseqidlist) {
        this.ftseqidlist = ftseqidlist;
    }

    public List getFtseqidlist() {
        return ftseqidlist;
    }

    public void setNexteffectivedate(Date nexteffectivedate) {
        this.nexteffectivedate = nexteffectivedate;
    }

    public Date getNexteffectivedate() {
        return nexteffectivedate;
    }

    public void setRacknewretail(String racknewretail) {
        this.racknewretail = racknewretail;
    }

    public String getRacknewretail() {
        return racknewretail;
    }

    public void setRacknewpercentMD(String racknewpercentMD) {
        this.racknewpercentMD = racknewpercentMD;
    }

    public String getRacknewpercentMD() {
        return racknewpercentMD;
    }

    public void setUpdateCount(int updateCount) {
        this.updateCount = updateCount;
    }

    public int getUpdateCount() {
        return updateCount;
    }

    public void candidateTabBulkUpdate(ActionEvent actionEvent) {
        // Add event code here...
        clearanceService.candidateTabBulkUpdate(actionEvent);
    }
    public void viewSavedTakesTabBulkUpdate(ActionEvent actionEvent) {
        // Add event code here...
        clearanceService.viewSavedTakesTabBulkUpdate(actionEvent);
    }

    public void clearChangesCandidateTab(ActionEvent actionEvent) {
        // Add event code here...
        clearanceService.clearChangesCandidateTab(actionEvent);
    }
    public void clearChangesViewSavedTakesTab(ActionEvent actionEvent) {
        // Add event code here...
        clearanceService.clearChangesViewSavedTakesTab(actionEvent);
    }
       
    public void setStseqid(String stseqid) {
        this.stseqid = stseqid;
    }

    public String getStseqid() {
        return stseqid;
    }

    public void setStseqidlist(List stseqidlist) {
        this.stseqidlist = stseqidlist;
    }

    public List getStseqidlist() {
        return stseqidlist;
    }

    public void setUpdateCount1(int updateCount1) {
        this.updateCount1 = updateCount1;
    }

    public int getUpdateCount1() {
        return updateCount1;
    }

    public void sendToRpmActionListener(ActionEvent actionEvent) {
        // Add event code here...
        clearanceService.sendToRpmActionListener(actionEvent);
    }

    public void setStnexteffectivedate(Date stnexteffectivedate) {
        this.stnexteffectivedate = stnexteffectivedate;
    }

    public Date getStnexteffectivedate() {
        return stnexteffectivedate;
    }

    public void setStracknewretail(String stracknewretail) {
        this.stracknewretail = stracknewretail;
    }

    public String getStracknewretail() {
        return stracknewretail;
    }

    public void setStracknewpercentMD(String stracknewpercentMD) {
        this.stracknewpercentMD = stracknewpercentMD;
    }

    public String getStracknewpercentMD() {
        return stracknewpercentMD;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public String getDept() {
        return dept;
    }

    public void setDeptValuesSelected(List deptValuesSelected) {
        this.deptValuesSelected = deptValuesSelected;
    }

    public List getDeptValuesSelected() {
        return deptValuesSelected;
    }


  

    public void getZoneFromZoneGroupVCL(ValueChangeEvent valueChangeEvent) {
        // Add event code here...
        clearanceService.getZoneFromZoneGroupVCL(valueChangeEvent);
    }
    public void getZoneVCL(ValueChangeEvent valueChangeEvent) {
        // Add event code here...
        clearanceService.getZoneVCL(valueChangeEvent);
    }

    public void setDeptList(String deptList) {
        this.deptList = deptList;
    }

    public String getDeptList() {
        return deptList;
    }

    public void setZoneId(String zoneId) {
        this.zoneId = zoneId;
    }

    public String getZoneId() {
        return zoneId;
    }

    public void setZoneGroupId(String zoneGroupId) {
        this.zoneGroupId = zoneGroupId;
    }

    public String getZoneGroupId() {
        return zoneGroupId;
    }

    public void setTaoname(String taoname) {
        this.taoname = taoname;
    }

    public String getTaoname() {
        return taoname;
    }

    public void setSavedList(List<SelectItem> savedList) {
        this.savedList = savedList;
    }

    public List<SelectItem> getSavedList() {
        return savedList;
    }
    public void saveViewInCandidateTab(ActionEvent actionEvent) {
        clearanceService.saveViewInCandidateTab(actionEvent);
    }
    public void saveViewInViewSavedTakesTab(ActionEvent actionEvent) {
        clearanceService.saveViewInViewSavedTakesTab(actionEvent);
    }
    public void chooseViewInCandidateTabVCL(ValueChangeEvent valueChangeEvent){
        clearanceService.chooseViewInCandidateTabVCL(valueChangeEvent);
    }
    public void chooseViewInViewSavedTakesTabVCL(ValueChangeEvent valueChangeEvent){
        clearanceService.chooseViewInViewSavedTakesTabVCL(valueChangeEvent);
    }

    public void setFtsaveview(String ftsaveview) {
        this.ftsaveview = ftsaveview;
    }

    public String getFtsaveview() {
        return ftsaveview;
    }

    public void setBind_savedlistCandidate(RichSelectOneChoice bind_savedlistCandidate) {
        this.bind_savedlistCandidate = bind_savedlistCandidate;
    }

    public RichSelectOneChoice getBind_savedlistCandidate() {
        return bind_savedlistCandidate;
    }

    public void setDivision(String division) {
        this.division = division;
    }

    public String getDivision() {
        return division;
    }

    public void setDivisionValuesSelected(List<String> divisionValuesSelected) {
        this.divisionValuesSelected = divisionValuesSelected;
    }

    public List<String> getDivisionValuesSelected() {
        return divisionValuesSelected;
    }


    public void setMdamountcandidate(String mdamountcandidate) {
        this.mdamountcandidate = mdamountcandidate;
    }

    public String getMdamountcandidate() {
        return mdamountcandidate;
    }

    public void setSummdamountcandidate(double summdamountcandidate) {
        this.summdamountcandidate = summdamountcandidate;
    }

    public double getSummdamountcandidate() {
        return summdamountcandidate;
    }

    public void setTotalRowsselectedcandidate(int totalRowsselectedcandidate) {
        this.totalRowsselectedcandidate = totalRowsselectedcandidate;
    }

    public int getTotalRowsselectedcandidate() {
        return totalRowsselectedcandidate;
    }

    public void saveChangesViewSavedTakesTab(ActionEvent actionEvent) {
        // Add event code here...
        clearanceService.saveChangesViewSavedTakesTab(actionEvent);
    }

    public void setBind_CandidateBox(RichPanelGroupLayout bind_CandidateBox) {
        this.bind_CandidateBox = bind_CandidateBox;
    }

    public RichPanelGroupLayout getBind_CandidateBox() {
        return bind_CandidateBox;
    }

    public void setBind_ViewSavedBox(RichPanelGroupLayout bind_ViewSavedBox) {
        this.bind_ViewSavedBox = bind_ViewSavedBox;
    }

    public RichPanelGroupLayout getBind_ViewSavedBox() {
        return bind_ViewSavedBox;
    }

    public void setInventory(String inventory) {
        this.inventory = inventory;
    }

    public String getInventory() {
        return inventory;
    }

    public void setSuminventorycandidate(double suminventorycandidate) {
        this.suminventorycandidate = suminventorycandidate;
    }

    public double getSuminventorycandidate() {
        return suminventorycandidate;
    }

    public void setBind_Dept(RichSelectManyChoice bind_Dept) {
        this.bind_Dept = bind_Dept;
    }

    public RichSelectManyChoice getBind_Dept() {
        return bind_Dept;
    }

    public void setTaonamest(String taonamest) {
        this.taonamest = taonamest;
    }

    public String getTaonamest() {
        return taonamest;
    }

    public void setSavedListst(List<SelectItem> savedListst) {
        this.savedListst = savedListst;
    }

    public List<SelectItem> getSavedListst() {
        return savedListst;
    }

    public void setBind_savedlistCandidatest(RichSelectOneChoice bind_savedlistCandidatest) {
        this.bind_savedlistCandidatest = bind_savedlistCandidatest;
    }

    public RichSelectOneChoice getBind_savedlistCandidatest() {
        return bind_savedlistCandidatest;
    }

    public void setBind_candtabRangesize(RichSelectOneChoice bind_candtabRangesize) {
        this.bind_candtabRangesize = bind_candtabRangesize;
    }

    public RichSelectOneChoice getBind_candtabRangesize() {
        return bind_candtabRangesize;
    }

    public void candTabrangesizeVCL(ValueChangeEvent valueChangeEvent) {
        // Add event code here...
        clearanceService.candTabrangesizeVCL(valueChangeEvent);
    }

    public void setBind_viewSavedTakesTabRangeSize(RichSelectOneChoice bind_viewSavedTakesTabRangeSize) {
        this.bind_viewSavedTakesTabRangeSize = bind_viewSavedTakesTabRangeSize;
    }

    public RichSelectOneChoice getBind_viewSavedTakesTabRangeSize() {
        return bind_viewSavedTakesTabRangeSize;
    }

    public void viewSavedTakesTabRangeSizeVCL(ValueChangeEvent valueChangeEvent) {
        // Add event code here...
        clearanceService.viewSavedTakesTabRangeSizeVCL(valueChangeEvent);
    }
    public void candidateTabFilterQuery(QueryEvent queryEvent){
        clearanceService.candidateTabFilterQuery(queryEvent);
    }

   

    public void newRetailCandidateTabVCL(ValueChangeEvent valueChangeEvent) {
        // Add event code here...
        clearanceService.newRetailCandidateTabVCL(valueChangeEvent);
    }

    public void setBind_baseRetail(RichInputText bind_baseRetail) {
        this.bind_baseRetail = bind_baseRetail;
    }

    public RichInputText getBind_baseRetail() {
        return bind_baseRetail;
    }

    public void setBind_inventory(RichInputText bind_inventory) {
        this.bind_inventory = bind_inventory;
    }

    public RichInputText getBind_inventory() {
        return bind_inventory;
    }

    public void setBind_mdAmount(RichInputText bind_mdAmount) {
        this.bind_mdAmount = bind_mdAmount;
    }

    public RichInputText getBind_mdAmount() {
        return bind_mdAmount;
    }

    public void setBind_mdPercent(RichInputText bind_mdPercent) {
        this.bind_mdPercent = bind_mdPercent;
    }

    public RichInputText getBind_mdPercent() {
        return bind_mdPercent;
    }

    public void newMdPercentCandidateTabVCL(ValueChangeEvent valueChangeEvent) {
        // Add event code here...
        clearanceService.newMdPercentCandidateTabVCL(valueChangeEvent);
    }

    public void setBind_newRetail(RichInputText bind_newRetail) {
        this.bind_newRetail = bind_newRetail;
    }

    public RichInputText getBind_newRetail() {
        return bind_newRetail;
    }

    public void setBind_newRetail_savedTakes(RichInputText bind_newRetail_savedTakes) {
        this.bind_newRetail_savedTakes = bind_newRetail_savedTakes;
    }

    public RichInputText getBind_newRetail_savedTakes() {
        return bind_newRetail_savedTakes;
    }

    public void setBind_baseRetail_savedTakes(RichInputText bind_baseRetail_savedTakes) {
        this.bind_baseRetail_savedTakes = bind_baseRetail_savedTakes;
    }

    public RichInputText getBind_baseRetail_savedTakes() {
        return bind_baseRetail_savedTakes;
    }

    public void setBind_inventory_savedTakes(RichInputText bind_inventory_savedTakes) {
        this.bind_inventory_savedTakes = bind_inventory_savedTakes;
    }

    public RichInputText getBind_inventory_savedTakes() {
        return bind_inventory_savedTakes;
    }

    public void setBind_mdAmount_savedTakes(RichInputText bind_mdAmount_savedTakes) {
        this.bind_mdAmount_savedTakes = bind_mdAmount_savedTakes;
    }

    public RichInputText getBind_mdAmount_savedTakes() {
        return bind_mdAmount_savedTakes;
    }

    public void setBind_mdPercent_savedTakes(RichInputText bind_mdPercent_savedTakes) {
        this.bind_mdPercent_savedTakes = bind_mdPercent_savedTakes;
    }

    public RichInputText getBind_mdPercent_savedTakes() {
        return bind_mdPercent_savedTakes;
    }

    public void newRetailSavedTakesTabVCL(ValueChangeEvent valueChangeEvent) {
        // Add event code here...
        clearanceService.newRetailSavedTakesTabVCL(valueChangeEvent);
        
    }

    public void newMdPercentSavedTakesTabVCL(ValueChangeEvent valueChangeEvent) {
        // Add event code here...
        clearanceService.newMdPercentSavedTakesTabVCL(valueChangeEvent);
    }

    public void setBind_newRetail_candidateTab_bulkUpdate(RichInputText bind_newRetail_candidateTab_bulkUpdate) {
        this.bind_newRetail_candidateTab_bulkUpdate = bind_newRetail_candidateTab_bulkUpdate;
    }

    public RichInputText getBind_newRetail_candidateTab_bulkUpdate() {
        return bind_newRetail_candidateTab_bulkUpdate;
    }

    public void setBind_newMdPercent_candidateTab_bulkUpdate(RichInputText bind_newMdPercent_candidateTab_bulkUpdate) {
        this.bind_newMdPercent_candidateTab_bulkUpdate = bind_newMdPercent_candidateTab_bulkUpdate;
    }

    public RichInputText getBind_newMdPercent_candidateTab_bulkUpdate() {
        return bind_newMdPercent_candidateTab_bulkUpdate;
    }

    public void newRetailCandidateTabBulkUpdateVCL(ValueChangeEvent valueChangeEvent) {
        // Add event code here...
        clearanceService.newRetailCandidateTabBulkUpdateVCL(valueChangeEvent);
    }

    public void newMdPercentCandidateTabBulkUpdateVCL(ValueChangeEvent valueChangeEvent) {
        // Add event code here...
        clearanceService.newMdPercentCandidateTabBulkUpdateVCL(valueChangeEvent);
    }

    public void setBind_newRetail_savedTakesTab_bulkUpdate(RichInputText bind_newRetail_savedTakesTab_bulkUpdate) {
        this.bind_newRetail_savedTakesTab_bulkUpdate = bind_newRetail_savedTakesTab_bulkUpdate;
    }

    public RichInputText getBind_newRetail_savedTakesTab_bulkUpdate() {
        return bind_newRetail_savedTakesTab_bulkUpdate;
    }

    public void setBind_newMdPercent_savedTakesTab_bulkUpdate(RichInputText bind_newMdPercent_savedTakesTab_bulkUpdate) {
        this.bind_newMdPercent_savedTakesTab_bulkUpdate = bind_newMdPercent_savedTakesTab_bulkUpdate;
    }

    public RichInputText getBind_newMdPercent_savedTakesTab_bulkUpdate() {
        return bind_newMdPercent_savedTakesTab_bulkUpdate;
    }

    public void newRetailSavedTakesTabBulkUpdateVCL(ValueChangeEvent valueChangeEvent) {
        // Add event code here...
        clearanceService.newRetailSavedTakesTabBulkUpdateVCL(valueChangeEvent);
    }

    public void newMdPercentSavedTakesTabBulkUpdateVCL(ValueChangeEvent valueChangeEvent) {
        // Add event code here...
        clearanceService.newMdPercentSavedTakesTabBulkUpdateVCL(valueChangeEvent);
    }

    public void setBind_headerCheckBox_candidateTab(RichSelectBooleanCheckbox bind_headerCheckBox_candidateTab) {
        this.bind_headerCheckBox_candidateTab = bind_headerCheckBox_candidateTab;
    }

    public RichSelectBooleanCheckbox getBind_headerCheckBox_candidateTab() {
        return bind_headerCheckBox_candidateTab;
    }

    public void setBind_headerCheckBox_SavedTakesTab(RichSelectBooleanCheckbox bind_headerCheckBox_SavedTakesTab) {
        this.bind_headerCheckBox_SavedTakesTab = bind_headerCheckBox_SavedTakesTab;
    }

    public RichSelectBooleanCheckbox getBind_headerCheckBox_SavedTakesTab() {
        return bind_headerCheckBox_SavedTakesTab;
    }

    public void setSumMdAmountSavedTakesTab(double sumMdAmountSavedTakesTab) {
        this.sumMdAmountSavedTakesTab = sumMdAmountSavedTakesTab;
    }

    public double getSumMdAmountSavedTakesTab() {
        return sumMdAmountSavedTakesTab;
    }

 

    public void setTotalRowsSelectedSavedTakesTab(int totalRowsSelectedSavedTakesTab) {
        this.totalRowsSelectedSavedTakesTab = totalRowsSelectedSavedTakesTab;
    }

    public int getTotalRowsSelectedSavedTakesTab() {
        return totalRowsSelectedSavedTakesTab;
    }

    public void setMdAmountSavedTakesTab(String mdAmountSavedTakesTab) {
        this.mdAmountSavedTakesTab = mdAmountSavedTakesTab;
    }

    public String getMdAmountSavedTakesTab() {
        return mdAmountSavedTakesTab;
    }

    public void setInventorySavedTakesTab(String inventorySavedTakesTab) {
        this.inventorySavedTakesTab = inventorySavedTakesTab;
    }

    public String getInventorySavedTakesTab() {
        return inventorySavedTakesTab;
    }

    public void setSumInventorySavedTakesTab(int sumInventorySavedTakesTab) {
        this.sumInventorySavedTakesTab = sumInventorySavedTakesTab;
    }

    public int getSumInventorySavedTakesTab() {
        return sumInventorySavedTakesTab;
    }
    
    public void sendToRPMDialogListener(DialogEvent dialogEvent) {
        // Add event code here...
        clearanceService.sendToRPMDialogListener(dialogEvent);
        
    }

    public void validationBeforeSendToRPM(PopupFetchEvent popupFetchEvent) {
        // Add event code here...
        clearanceService.validationBeforeSendToRPM(popupFetchEvent);
    }


    public void setFinalMessageOne(String finalMessageOne) {
        this.finalMessageOne = finalMessageOne;
    }

    public String getFinalMessageOne() {
        return finalMessageOne;
    }

    public void setFinalMessageTwo(String finalMessageTwo) {
        this.finalMessageTwo = finalMessageTwo;
    }

    public String getFinalMessageTwo() {
        return finalMessageTwo;
    }

    public void setFinalMessageThree(String finalMessageThree) {
        this.finalMessageThree = finalMessageThree;
    }

    public String getFinalMessageThree() {
        return finalMessageThree;
    }

    public void setFinalMessageFour(String finalMessageFour) {
        this.finalMessageFour = finalMessageFour;
    }

    public String getFinalMessageFour() {
        return finalMessageFour;
    }

    public void setBind_nextEffectiveDate_canddiateTab_bulkUpdate(RichInputDate bind_nextEffectiveDate_canddiateTab_bulkUpdate) {
        this.bind_nextEffectiveDate_canddiateTab_bulkUpdate = bind_nextEffectiveDate_canddiateTab_bulkUpdate;
    }

    public RichInputDate getBind_nextEffectiveDate_canddiateTab_bulkUpdate() {
        return bind_nextEffectiveDate_canddiateTab_bulkUpdate;
    }

    public void setBind_nextEffectiveDate_savedTakesTab_bulkUpdate(RichInputDate bind_nextEffectiveDate_savedTakesTab_bulkUpdate) {
        this.bind_nextEffectiveDate_savedTakesTab_bulkUpdate = bind_nextEffectiveDate_savedTakesTab_bulkUpdate;
    }

    public RichInputDate getBind_nextEffectiveDate_savedTakesTab_bulkUpdate() {
        return bind_nextEffectiveDate_savedTakesTab_bulkUpdate;
    }

    public void setBind_currentRetail(RichInputText bind_currentRetail) {
        this.bind_currentRetail = bind_currentRetail;
    }

    public RichInputText getBind_currentRetail() {
        return bind_currentRetail;
    }

    public void setBind_currentRetail_savedTakes(RichInputText bind_currentRetail_savedTakes) {
        this.bind_currentRetail_savedTakes = bind_currentRetail_savedTakes;
    }

    public RichInputText getBind_currentRetail_savedTakes() {
        return bind_currentRetail_savedTakes;
    }

    public void takeMDCandidatesDialogListener(DialogEvent dialogEvent) {
        // Add event code here...
        clearanceService.takeMDCandidatesDialogListener(dialogEvent);
    }

    public void saveChangesCandidateTab(ActionEvent actionEvent) {
        // Add event code here...
        clearanceService.saveChangesCandidateTab(actionEvent);
    }

    public void setBind_rowCheckbox_candidateTab(RichSelectBooleanCheckbox bind_rowCheckbox_candidateTab) {
        this.bind_rowCheckbox_candidateTab = bind_rowCheckbox_candidateTab;
    }

    public RichSelectBooleanCheckbox getBind_rowCheckbox_candidateTab() {
        return bind_rowCheckbox_candidateTab;
    }

    public void setBind_rowCheckbox_ViewSavedTakesTab(RichSelectBooleanCheckbox bind_rowCheckbox_ViewSavedTakesTab) {
        this.bind_rowCheckbox_ViewSavedTakesTab = bind_rowCheckbox_ViewSavedTakesTab;
    }

    public RichSelectBooleanCheckbox getBind_rowCheckbox_ViewSavedTakesTab() {
        return bind_rowCheckbox_ViewSavedTakesTab;
    }

    public void setBind_dialog_sendToRPM(RichDialog bind_dialog_sendToRPM) {
        this.bind_dialog_sendToRPM = bind_dialog_sendToRPM;
    }

    public RichDialog getBind_dialog_sendToRPM() {
        return bind_dialog_sendToRPM;
    }

    public void setBind_popUp_sendToRPM(RichPopup bind_popUp_sendToRPM) {
        this.bind_popUp_sendToRPM = bind_popUp_sendToRPM;
    }

    public RichPopup getBind_popUp_sendToRPM() {
        return bind_popUp_sendToRPM;
    }

    public void setBind_popUp_Take(RichPopup bind_popUp_Take) {
        this.bind_popUp_Take = bind_popUp_Take;
    }

    public RichPopup getBind_popUp_Take() {
        return bind_popUp_Take;
    }

    public void setBind_dialog_Take(RichDialog bind_dialog_Take) {
        this.bind_dialog_Take = bind_dialog_Take;
    }

    public RichDialog getBind_dialog_Take() {
        return bind_dialog_Take;
    }

    public void newEffectiveDateCandidateTabVCL(ValueChangeEvent valueChangeEvent) {
        // Add event code here...
        clearanceService.newEffectiveDateCandidateTabVCL(valueChangeEvent);
    }

    public void newEffectiveDateSavedTakesTabVCL(ValueChangeEvent valueChangeEvent) {
        // Add event code here...
        clearanceService.newEffectiveDateSavedTakesTabVCL(valueChangeEvent);
    }

    public void setBind_nextEffectiveDate_CandidateTab(RichInputDate bind_nextEffectiveDate_CandidateTab) {
        this.bind_nextEffectiveDate_CandidateTab = bind_nextEffectiveDate_CandidateTab;
    }

    public RichInputDate getBind_nextEffectiveDate_CandidateTab() {
        return bind_nextEffectiveDate_CandidateTab;
    }

    public void setBind_nextEffectiveDate_SavedTakesTab(RichInputDate bind_nextEffectiveDate_SavedTakesTab) {
        this.bind_nextEffectiveDate_SavedTakesTab = bind_nextEffectiveDate_SavedTakesTab;
    }

    public RichInputDate getBind_nextEffectiveDate_SavedTakesTab() {
        return bind_nextEffectiveDate_SavedTakesTab;
    }

    public void untakeMDCandidatesDialogListener(DialogEvent dialogEvent) {
        // Add event code here...
        clearanceService.untakeMDCandidatesDialogListener(dialogEvent);
    }

    public void setBind_SupplierName_filter(RichSelectManyChoice bind_SupplierName_filter) {
        this.bind_SupplierName_filter = bind_SupplierName_filter;
    }

    public RichSelectManyChoice getBind_SupplierName_filter() {
        return bind_SupplierName_filter;
    }

    public void setBind_Division_filter(RichSelectManyChoice bind_Division_filter) {
        this.bind_Division_filter = bind_Division_filter;
    }

    public RichSelectManyChoice getBind_Division_filter() {
        return bind_Division_filter;
    }

    public void setBind_Dept_filter(RichSelectManyChoice bind_Dept_filter) {
        this.bind_Dept_filter = bind_Dept_filter;
    }

    public RichSelectManyChoice getBind_Dept_filter() {
        return bind_Dept_filter;
    }

    public void setBind_Class_filter(RichSelectManyChoice bind_Class_filter) {
        this.bind_Class_filter = bind_Class_filter;
    }

    public RichSelectManyChoice getBind_Class_filter() {
        return bind_Class_filter;
    }

    public void setBind_SubClass_filter(RichSelectManyChoice bind_SubClass_filter) {
        this.bind_SubClass_filter = bind_SubClass_filter;
    }

    public RichSelectManyChoice getBind_SubClass_filter() {
        return bind_SubClass_filter;
    }

    public void setBind_VPN_filter(RichSelectManyChoice bind_VPN_filter) {
        this.bind_VPN_filter = bind_VPN_filter;
    }

    public RichSelectManyChoice getBind_VPN_filter() {
        return bind_VPN_filter;
    }

    public void setBind_Item_filter(RichSelectManyChoice bind_Item_filter) {
        this.bind_Item_filter = bind_Item_filter;
    }

    public RichSelectManyChoice getBind_Item_filter() {
        return bind_Item_filter;
    }

    public void setBind_SupplierColor_filter(RichSelectManyChoice bind_SupplierColor_filter) {
        this.bind_SupplierColor_filter = bind_SupplierColor_filter;
    }

    public RichSelectManyChoice getBind_SupplierColor_filter() {
        return bind_SupplierColor_filter;
    }

    public void setBind_Label_filter(RichSelectManyChoice bind_Label_filter) {
        this.bind_Label_filter = bind_Label_filter;
    }

    public RichSelectManyChoice getBind_Label_filter() {
        return bind_Label_filter;
    }

    public void setBind_NRFColor_Filter(RichSelectManyChoice bind_NRFColor_Filter) {
        this.bind_NRFColor_Filter = bind_NRFColor_Filter;
    }

    public RichSelectManyChoice getBind_NRFColor_Filter() {
        return bind_NRFColor_Filter;
    }

    public void setBind_SaveCandidates(RichCommandButton bind_SaveCandidates) {
        this.bind_SaveCandidates = bind_SaveCandidates;
    }

    public RichCommandButton getBind_SaveCandidates() {
        return bind_SaveCandidates;
    }

    public void setBind_DeptAccess_PopUp(RichPopup bind_DeptAccess_PopUp) {
        this.bind_DeptAccess_PopUp = bind_DeptAccess_PopUp;
    }

    public RichPopup getBind_DeptAccess_PopUp() {
        return bind_DeptAccess_PopUp;
    }

    public void setBind_EffectiveDate_filter(RichInputDate bind_EffectiveDate_filter) {
        this.bind_EffectiveDate_filter = bind_EffectiveDate_filter;
    }

    public RichInputDate getBind_EffectiveDate_filter() {
        return bind_EffectiveDate_filter;
    }

    public void setBind_NewEffectiveDate_filter(RichInputDate bind_NewEffectiveDate_filter) {
        this.bind_NewEffectiveDate_filter = bind_NewEffectiveDate_filter;
    }

    public RichInputDate getBind_NewEffectiveDate_filter() {
        return bind_NewEffectiveDate_filter;
    }

    public void setBind_LastReceiptDate_filter(RichInputDate bind_LastReceiptDate_filter) {
        this.bind_LastReceiptDate_filter = bind_LastReceiptDate_filter;
    }

    public RichInputDate getBind_LastReceiptDate_filter() {
        return bind_LastReceiptDate_filter;
    }

    public void setBind_LastMkdnDate_filter(RichInputDate bind_LastMkdnDate_filter) {
        this.bind_LastMkdnDate_filter = bind_LastMkdnDate_filter;
    }

    public RichInputDate getBind_LastMkdnDate_filter() {
        return bind_LastMkdnDate_filter;
    }

    public void setBind_SavedDivision_filter(RichSelectManyChoice bind_SavedDivision_filter) {
        this.bind_SavedDivision_filter = bind_SavedDivision_filter;
    }

    public RichSelectManyChoice getBind_SavedDivision_filter() {
        return bind_SavedDivision_filter;
    }

    public void setBind_SavedDept_filter(RichSelectManyChoice bind_SavedDept_filter) {
        this.bind_SavedDept_filter = bind_SavedDept_filter;
    }

    public RichSelectManyChoice getBind_SavedDept_filter() {
        return bind_SavedDept_filter;
    }

    public void setBind_SavedClass_filter(RichSelectManyChoice bind_SavedClass_filter) {
        this.bind_SavedClass_filter = bind_SavedClass_filter;
    }

    public RichSelectManyChoice getBind_SavedClass_filter() {
        return bind_SavedClass_filter;
    }

    public void setBind_SavedSubClass_filter(RichSelectManyChoice bind_SavedSubClass_filter) {
        this.bind_SavedSubClass_filter = bind_SavedSubClass_filter;
    }

    public RichSelectManyChoice getBind_SavedSubClass_filter() {
        return bind_SavedSubClass_filter;
    }

    public void setBind_SavedSupName_filter(RichSelectManyChoice bind_SavedSupName_filter) {
        this.bind_SavedSupName_filter = bind_SavedSupName_filter;
    }

    public RichSelectManyChoice getBind_SavedSupName_filter() {
        return bind_SavedSupName_filter;
    }

    public void setBind_SupLabel_filter(RichSelectManyChoice bind_SupLabel_filter) {
        this.bind_SupLabel_filter = bind_SupLabel_filter;
    }

    public RichSelectManyChoice getBind_SupLabel_filter() {
        return bind_SupLabel_filter;
    }

    public void setBind_SavedVPN_filter(RichSelectManyChoice bind_SavedVPN_filter) {
        this.bind_SavedVPN_filter = bind_SavedVPN_filter;
    }

    public RichSelectManyChoice getBind_SavedVPN_filter() {
        return bind_SavedVPN_filter;
    }

    public void setBind_SavedItem_filter(RichSelectManyChoice bind_SavedItem_filter) {
        this.bind_SavedItem_filter = bind_SavedItem_filter;
    }

    public RichSelectManyChoice getBind_SavedItem_filter() {
        return bind_SavedItem_filter;
    }

    public void setBind_SavedSupColor_filter(RichSelectManyChoice bind_SavedSupColor_filter) {
        this.bind_SavedSupColor_filter = bind_SavedSupColor_filter;
    }

    public RichSelectManyChoice getBind_SavedSupColor_filter() {
        return bind_SavedSupColor_filter;
    }

    public void setBind_SavedEffDate_filter(RichInputDate bind_SavedEffDate_filter) {
        this.bind_SavedEffDate_filter = bind_SavedEffDate_filter;
    }

    public RichInputDate getBind_SavedEffDate_filter() {
        return bind_SavedEffDate_filter;
    }

    public void setBind_SavedNwEffDate_filter(RichInputDate bind_SavedNwEffDate_filter) {
        this.bind_SavedNwEffDate_filter = bind_SavedNwEffDate_filter;
    }

    public RichInputDate getBind_SavedNwEffDate_filter() {
        return bind_SavedNwEffDate_filter;
    }

    public void setBind_SavedLastRctDt_filter(RichInputDate bind_SavedLastRctDt_filter) {
        this.bind_SavedLastRctDt_filter = bind_SavedLastRctDt_filter;
    }

    public RichInputDate getBind_SavedLastRctDt_filter() {
        return bind_SavedLastRctDt_filter;
    }

    public void setBind_LastMDnDt_filter(RichInputDate bind_LastMDnDt_filter) {
        this.bind_LastMDnDt_filter = bind_LastMDnDt_filter;
    }

    public RichInputDate getBind_LastMDnDt_filter() {
        return bind_LastMDnDt_filter;
    }

    public void setBind_SavedNrfColor_filter(RichSelectManyChoice bind_SavedNrfColor_filter) {
        this.bind_SavedNrfColor_filter = bind_SavedNrfColor_filter;
    }

    public RichSelectManyChoice getBind_SavedNrfColor_filter() {
        return bind_SavedNrfColor_filter;
    }

    public void setBind_SaveCandidates2(RichCommandButton bind_SaveCandidates2) {
        this.bind_SaveCandidates2 = bind_SaveCandidates2;
    }

    public RichCommandButton getBind_SaveCandidates2() {
        return bind_SaveCandidates2;
    }

    public void setBind_SaveTakesCandidates(RichCommandButton bind_SaveTakesCandidates) {
        this.bind_SaveTakesCandidates = bind_SaveTakesCandidates;
    }

    public RichCommandButton getBind_SaveTakesCandidates() {
        return bind_SaveTakesCandidates;
    }

    public void setBind_SaveTakesCandidates2(RichCommandButton bind_SaveTakesCandidates2) {
        this.bind_SaveTakesCandidates2 = bind_SaveTakesCandidates2;
    }

    public RichCommandButton getBind_SaveTakesCandidates2() {
        return bind_SaveTakesCandidates2;
    }

    public void setBind_Untake(RichDialog bind_Untake) {
        this.bind_Untake = bind_Untake;
    }

    public RichDialog getBind_Untake() {
        return bind_Untake;
    }

    public void setBind_PopUp_Untake(RichPopup bind_PopUp_Untake) {
        this.bind_PopUp_Untake = bind_PopUp_Untake;
    }

    public RichPopup getBind_PopUp_Untake() {
        return bind_PopUp_Untake;
    }

    public void setBind_compareAtRtl(RichInputText bind_compareAtRtl) {
        this.bind_compareAtRtl = bind_compareAtRtl;
    }

    public RichInputText getBind_compareAtRtl() {
        return bind_compareAtRtl;
    }

    public void setBind_compareAtRtl_SavedTakes(RichInputText bind_compareAtRtl_SavedTakes) {
        this.bind_compareAtRtl_SavedTakes = bind_compareAtRtl_SavedTakes;
    }

    public RichInputText getBind_compareAtRtl_SavedTakes() {
        return bind_compareAtRtl_SavedTakes;
    }
}

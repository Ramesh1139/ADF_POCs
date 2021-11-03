package com.nordstrom.rpm.Service;

import java.util.List;

import javax.faces.event.ActionEvent;

import javax.faces.event.ValueChangeEvent;

import oracle.adf.view.rich.event.DialogEvent;
import oracle.adf.view.rich.event.PopupFetchEvent;
import oracle.adf.view.rich.event.QueryEvent;

import org.apache.myfaces.trinidad.event.DisclosureEvent;

/****************************************************************************
* Interface Name        : ClearanceWorksheetService.java 
* Language              : Java
* Description           : The abstract methods in this interface are implemented in ClearanceWorksheetImpl.java 
* Author                : Ramesh Chinta, Infosys Ltd.
*                         Lasya Yadati, Infosys Ltd.
* Date written          : 31-Aug-2015 
*
* Modification History  :
*
* Description of change                 Date           Modified by 
* ---------------------                 ----           -----------
*****************************************************************************/
public interface ClearanceWorksheetService {
    
    public void searchActionListener(ActionEvent actionEvent);
    public void candidatesTabDisclosureListener(DisclosureEvent disclosureEvent);
    public void viewSavedTakesTabDisclosureListener(DisclosureEvent disclosureEvent);
    public void getDeptFrmDivisionVCL(ValueChangeEvent valueChangeEvent);
    //public void fthcheckBoxValueChangeListener(ValueChangeEvent valueChangeEvent);
   // public void ftrowCheckValueChangeListener(ValueChangeEvent valueChangeEvent);
   // public void sthcheckBoxValueChangeListener(ValueChangeEvent valueChangeEvent);
   // public void strowCheckValueChangeListener(ValueChangeEvent valueChangeEvent);
    //public void updateftBulk(ActionEvent actionEvent);
    //public void updatestBulk(ActionEvent actionEvent);
   // public void ftclearEmpQbeFilter(ActionEvent actionEvent);
    //public void stclearEmpQbeFilter(ActionEvent actionEvent);
   // public void takeActionListener(ActionEvent actionEvent);
    //public void untakeActionListener(ActionEvent actionEvent);
    //public void rpmActionListener(ActionEvent actionEvent);
    public void getDepartmentVCL(ValueChangeEvent valueChangeEvent);
    public void getZoneFromZoneGroupVCL(ValueChangeEvent valueChangeEvent);
    public void getZoneVCL(ValueChangeEvent valueChangeEvent);
    //public void saveviewActionListener(ActionEvent actionEvent);
    //public void chooseViewValueChangeListener(ValueChangeEvent valueChangeEvent);
    //public void saveViewSavedTakesTabAL(ActionEvent actionEvent);
    //public void saveviewActionListenerst(ActionEvent actionEvent);
    //public void chooseViewValueChangeListenerst(ValueChangeEvent valueChangeEvent);
    public void candTabrangesizeVCL(ValueChangeEvent valueChangeEvent);
    public void viewSavedTakesTabRangeSizeVCL(ValueChangeEvent valueChangeEvent);
    public void candidateTabFilterQuery(QueryEvent queryEvent);
    public void newRetailCandidateTabVCL(ValueChangeEvent valueChangeEvent);
    public void newMdPercentCandidateTabVCL(ValueChangeEvent valueChangeEvent);
    public void newRetailSavedTakesTabVCL(ValueChangeEvent valueChangeEvent);
    public void newMdPercentSavedTakesTabVCL(ValueChangeEvent valueChangeEvent);
    public void newRetailCandidateTabBulkUpdateVCL(ValueChangeEvent valueChangeEvent);
    public void newMdPercentCandidateTabBulkUpdateVCL(ValueChangeEvent valueChangeEvent);
    public void newRetailSavedTakesTabBulkUpdateVCL(ValueChangeEvent valueChangeEvent);
    public void newMdPercentSavedTakesTabBulkUpdateVCL(ValueChangeEvent valueChangeEvent);
    public void sendToRPMDialogListener(DialogEvent dialogEvent);

    public void validationBeforeSendToRPM(PopupFetchEvent popupFetchEvent);

    public void candidateTabHeaderCheckBoxVCL(ValueChangeEvent valueChangeEvent);

    public void candidateTabRowCheckBoxVCL(ValueChangeEvent valueChangeEvent);

    public void viewSavedTakesTabHeaderCheckBoxVCL(ValueChangeEvent valueChangeEvent);

    public void viewSavedTakesTabRowCheckBoxVCL(ValueChangeEvent valueChangeEvent);

    public void candidateTabBulkUpdate(ActionEvent actionEvent);

    public void clearChangesCandidateTab(ActionEvent actionEvent);

    public void clearChangesViewSavedTakesTab(ActionEvent actionEvent);

    public void sendToRpmActionListener(ActionEvent actionEvent);

    public void viewSavedTakesTabBulkUpdate(ActionEvent actionEvent);

    public void saveChangesViewSavedTakesTab(ActionEvent actionEvent);

    public void saveViewInViewSavedTakesTab(ActionEvent actionEvent);

    public void saveViewInCandidateTab(ActionEvent actionEvent);

    public void chooseViewInViewSavedTakesTabVCL(ValueChangeEvent valueChangeEvent);

    public void chooseViewInCandidateTabVCL(ValueChangeEvent valueChangeEvent);

    public void takeMDCandidatesDialogListener(DialogEvent dialogEvent);

    public void saveChangesCandidateTab(ActionEvent actionEvent);

    public void newEffectiveDateCandidateTabVCL(ValueChangeEvent valueChangeEvent);

    public void newEffectiveDateSavedTakesTabVCL(ValueChangeEvent valueChangeEvent);

    public void untakeMDCandidatesDialogListener(DialogEvent dialogEvent);
}

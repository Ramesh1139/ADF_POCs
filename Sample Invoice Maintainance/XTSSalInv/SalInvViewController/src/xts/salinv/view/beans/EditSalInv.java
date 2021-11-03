package xts.salinv.view.beans;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import oracle.adf.controller.TaskFlowId;
import oracle.adf.model.binding.DCIteratorBinding;
import oracle.adf.share.ADFContext;
import oracle.adf.share.logging.ADFLogger;
import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.component.rich.data.RichTable;
import oracle.adf.view.rich.component.rich.input.RichInputDate;
import oracle.adf.view.rich.component.rich.input.RichInputText;
import oracle.adf.view.rich.component.rich.layout.RichPanelFormLayout;
import oracle.adf.view.rich.component.rich.layout.RichPanelGroupLayout;
import oracle.adf.view.rich.component.rich.nav.RichButton;
import oracle.adf.view.rich.context.AdfFacesContext;
import oracle.adf.view.rich.event.LaunchPopupEvent;
import oracle.adf.view.rich.event.PopupCanceledEvent;
import oracle.adf.view.rich.render.ClientEvent;

import oracle.binding.OperationBinding;

import oracle.jbo.Row;
import oracle.jbo.ViewObject;

import org.apache.myfaces.trinidad.event.SelectionEvent;

import xts.common.view.framework.beans.DisabledLogicContainer;
import xts.common.view.framework.beans.GenericEdit;
import xts.common.view.framework.utils.ADFUtils;
import xts.common.view.framework.utils.JSFUtils;

public class EditSalInv extends GenericEdit {

    private static final ADFLogger LOGGER = ADFLogger.createADFLogger(EditSalInv.class);

    private RichPopup saveRemarkPopup;
    private RichInputText remarkDesc;
    private RichPopup copyRemarkPopup;
    private RichPopup templatePopup;
    private RichTable addAmtTable;
    private RichInputText cimsEdiSndver;
    private RichInputText cimsEdiComdat;
    private RichInputText ciptTotqtyctn;
    private RichPopup delShpitmPopup;
    private RichTable shpitmTable;
    private RichPopup chrInfoPopup;
    private RichPanelFormLayout chrInfoShiForm;
    private RichTable actshiamtTab;
    private RichPopup addShiPopup;
    private RichButton hdrManageTplButton;
    private RichTable siSearchSosiTable;
    private RichInputDate cimsDuedate;
    private RichInputDate cimsBildat;
    private RichPopup applyDefaultAddamtPopup;
    private RichPanelGroupLayout shpItmButtons;

    public EditSalInv() {
        super();
    }

    private String getDptSetcod(String divcod, String dptcod, String cuscod, String setcod) {

        OperationBinding ob = ADFUtils.findOperation("checkDptSetcod");
        ob.getParamsMap().put("divcod", divcod);
        ob.getParamsMap().put("dptcod", dptcod);
        ob.getParamsMap().put("cuscod", cuscod);
        ob.getParamsMap().put("setcod", setcod);
        ob.execute();

        return (String) ob.getResult();
    }

    private String getSetcod(String divcod, String cuscod, String setcod) {

        OperationBinding ob = ADFUtils.findOperation("checkSetcod");
        ob.getParamsMap().put("divcod", divcod);
        ob.getParamsMap().put("cuscod", cuscod);
        ob.getParamsMap().put("setcod", setcod);
        ob.execute();

        return (String) ob.getResult();
    }

    private String getApSetting(String divcod, String dptcod, String cuscod, String setcod) {

        OperationBinding ob = ADFUtils.findOperation("checkApSetting");
        ob.getParamsMap().put("divcod", divcod);
        ob.getParamsMap().put("dptcod", dptcod);
        ob.getParamsMap().put("cuscod", cuscod);
        ob.getParamsMap().put("setcod", setcod);
        ob.execute();

        return (String) ob.getResult();
    }


    @Override
    public void genericEdit(ActionEvent actionEvent) {

        // Reset Disabled Logic Container
        DisabledLogicContainer dlc = (DisabledLogicContainer) ADFContext.getCurrent()
                                                                        .getPageFlowScope()
                                                                        .get("XTSDisabledLogicContainerBean");
        dlc.resetData();

        OperationBinding ob = ADFUtils.findOperation("prepareDisable");
        ob.execute();

        ob = ADFUtils.findOperation("setDisableCiaiActqty");
        ob.getParamsMap().put("value", "N");
        ob.execute();

        super.genericEdit(actionEvent);
    }

    @Override
    public void genericSave(ActionEvent actionEvent) {

        OperationBinding ob = ADFUtils.findOperation("commitSi");
        ob.execute();

        if (ob.getErrors().size() == 0) {
            super.genericSave(actionEvent);

            ob = ADFUtils.findOperation("reexeQuery");
            ob.execute();

            ob = ADFUtils.findOperation("reExeCusfld");
            ob.execute();
        }

    }

    @Override
    public void genericClose(ActionEvent actionEvent) {
        OperationBinding ob = ADFUtils.findOperation("delRecentUseLogAutonomous");
        ob.execute();
        super.genericClose(actionEvent);
    }

    @Override
    public void genericSaveClose(ActionEvent actionEvent) {

        OperationBinding ob = ADFUtils.findOperation("commitSi");
        ob.execute();

        if (ob.getErrors().size() == 0) {
            super.genericSaveClose(actionEvent);

            ob = ADFUtils.findOperation("reexeQuery");
            ob.execute();

            ob = ADFUtils.findOperation("reExeCusfld");
            ob.execute();
        }
    }

    public void setSaveRemarkPopup(RichPopup saveRemarkPopup) {
        this.saveRemarkPopup = saveRemarkPopup;
    }

    public RichPopup getSaveRemarkPopup() {
        return saveRemarkPopup;
    }

    public void openSaveRemark(ActionEvent actionEvent) {

        UIComponent triggerItem = actionEvent.getComponent();
        String fldnam = (String) triggerItem.getAttributes().get("remarkFld");
        String loc = (String) triggerItem.getAttributes().get("loc");

        OperationBinding ob = ADFUtils.findOperation("setRemarkDesc");
        ob.getParamsMap().put("loc", loc);
        ob.getParamsMap().put("rmkFld", fldnam);
        ob.execute();

        RichPopup.PopupHints hints = new RichPopup.PopupHints();
        this.getSaveRemarkPopup().show(hints);
    }

    public void setRemarkDesc(RichInputText remarkDesc) {
        this.remarkDesc = remarkDesc;
    }

    public RichInputText getRemarkDesc() {
        return remarkDesc;
    }

    public void saveRemark(ActionEvent actionEvent) {
        OperationBinding ob = ADFUtils.findOperation("saveRemark");
        ob.execute();

        if (ob.getErrors().size() == 0) {
            this.getSaveRemarkPopup().hide();
        }
    }

    public void setCopyRemarkPopup(RichPopup copyRemarkPopup) {
        this.copyRemarkPopup = copyRemarkPopup;
    }

    public RichPopup getCopyRemarkPopup() {
        return copyRemarkPopup;
    }

    public void openCopyRemarkPopup(ActionEvent actionEvent) {

        UIComponent triggerItem = actionEvent.getComponent();
        String fldnam = (String) triggerItem.getAttributes().get("remarkFld");
        String loc = (String) triggerItem.getAttributes().get("loc");

        OperationBinding ob = ADFUtils.findOperation("setCopyAttr");
        ob.getParamsMap().put("loc", loc);
        ob.getParamsMap().put("rmkFld", fldnam);
        ob.execute();

        RichPopup.PopupHints hints = new RichPopup.PopupHints();
        this.getCopyRemarkPopup().show(hints);
    }

    public void resetCopyRemark(ActionEvent actionEvent) {
        OperationBinding ob = ADFUtils.findOperation("resetRow");
        ob.execute();
    }

    public void processCopy(ActionEvent actionEvent) {
        UIComponent triggerItem = actionEvent.getComponent();
        String loc = (String) triggerItem.getAttributes().get("loc");

        OperationBinding ob = ADFUtils.findOperation("processCopy");
        ob.getParamsMap().put("loc", loc);
        ob.execute();

        if (ob.getErrors().size() == 0) {
            this.getCopyRemarkPopup().hide();
        }
    }

    public void copyRemarkCloseListener(PopupCanceledEvent popupCanceledEvent) {
        OperationBinding ob = ADFUtils.findOperation("removeCopiedRemark");
        ob.execute();
    }

    public void openTemplatePopup(ActionEvent actionEvent) {
        OperationBinding ob = ADFUtils.findOperation("setDefaultVar");
        ob.execute();

        if (ob.getErrors().size() == 0) {
            RichPopup.PopupHints hints = new RichPopup.PopupHints();
            this.getTemplatePopup().show(hints);
        }
    }

    public void setTemplatePopup(RichPopup templatePopup) {
        this.templatePopup = templatePopup;
    }

    public RichPopup getTemplatePopup() {
        return templatePopup;
    }

    public void proecssLoadTemplate(ActionEvent actionEvent) {
        UIComponent triggerItem = actionEvent.getComponent();
        String loc = (String) triggerItem.getAttributes().get("loc");

        OperationBinding ob = ADFUtils.findOperation("proecssLoadTemplate");
        ob.getParamsMap().put("loc", loc);
        ob.execute();

        if (loc.equalsIgnoreCase("HDR"))
            AdfFacesContext.getCurrentInstance().addPartialTarget(this.getAddAmtTable());
        else
            AdfFacesContext.getCurrentInstance().addPartialTarget(this.getActshiamtTab());
        templatePopup.hide();

    }

    public void setAddAmtTable(RichTable addAmtTable) {
        this.addAmtTable = addAmtTable;
    }

    public RichTable getAddAmtTable() {
        return addAmtTable;
    }

    public void removeChrTyp(ActionEvent actionEvent) {
        OperationBinding ob = ADFUtils.findOperation("removeRow");
        ob.execute();
    }

    public void ediComdatClr(ActionEvent actionEvent) {
        OperationBinding ob = ADFUtils.findOperation("ediComdatClr");
        ob.execute();

        if (ob.getErrors().size() == 0) {
            AdfFacesContext.getCurrentInstance().addPartialTarget(this.getCimsEdiSndver());
            AdfFacesContext.getCurrentInstance().addPartialTarget(this.getCimsEdiComdat());
        }
    }

    public void setCimsEdiSndver(RichInputText cimsEdiSndver) {
        this.cimsEdiSndver = cimsEdiSndver;
    }

    public RichInputText getCimsEdiSndver() {
        return cimsEdiSndver;
    }

    public void setCimsEdiComdat(RichInputText cimsEdiComdat) {
        this.cimsEdiComdat = cimsEdiComdat;
    }

    public RichInputText getCimsEdiComdat() {
        return cimsEdiComdat;
    }

    public void autoQtyctn(ActionEvent actionEvent) {
        OperationBinding ob = ADFUtils.findOperation("autoQtyctn");
        ob.execute();

        if (ob.getErrors().size() == 0) {
            AdfFacesContext.getCurrentInstance().addPartialTarget(this.getCiptTotqtyctn());
        }
    }

    public void setCiptTotqtyctn(RichInputText ciptTotqtyctn) {
        this.ciptTotqtyctn = ciptTotqtyctn;
    }

    public RichInputText getCiptTotqtyctn() {
        return ciptTotqtyctn;
    }

    public void cimsCuscodChgListener(ValueChangeEvent valueChangeEvent) {
        OperationBinding ob;
        Object ret;
        String oldVal = (String) valueChangeEvent.getOldValue();
        String newVal = (String) valueChangeEvent.getNewValue();

        if ((oldVal == null && newVal != null) || (oldVal != null && !oldVal.equalsIgnoreCase(newVal))) {
            LOGGER.info("Old Value: " + oldVal + " New Val: " + newVal);
            valueChangeEvent.getComponent().processUpdates(FacesContext.getCurrentInstance()); //Update Model layer before processing
            LOGGER.info("New Val after processUpdates: " + (String) valueChangeEvent.getNewValue());
            ob = ADFUtils.findOperation("plAddamtTpldefChkAll");
            ob.getParamsMap().put("viewInstance", "TciMstVO");
            ob.execute();
            ret = ob.getResult();

            if (ret != null) {
                ob = ADFUtils.findOperation("plGetMsgmst");
                ob.getParamsMap().put("param", "2617");
                ob.execute();
                ret = ob.getResult();

                if (ret != null) {
                    JSFUtils.setManagedBeanValue("pageFlowScope.applyDefaultMsg", (String) ret);
                    RichPopup.PopupHints hints = new RichPopup.PopupHints();
                    this.getApplyDefaultAddamtPopup().show(hints);
                }
            }
        }
    }

    public void openDelShpitmPopup(ActionEvent actionEvent) {
        OperationBinding ob;
        EditShipment dynRegContr = (EditShipment) ADFContext.getCurrent()
                                                            .getPageFlowScope()
                                                            .get("EditShipmentBean");
        TaskFlowId page = dynRegContr.getDynamicTaskFlowId();
        String currentPage = page.toString();

        if ("/WEB-INF/flows/shpitm/editShpitmCusfld-tf.xml#editShpitmCusfld-tf".equals(currentPage)) {
            ob = ADFUtils.findOperation("shiValidateUpdate");
            ob.execute();

            if (ob.getErrors().size() == 0) {
                ob = ADFUtils.findOperation("postChanges");
                ob.execute();
            }
        } else {
            ob = ADFUtils.findOperation("postChanges");
            ob.execute();
        }

        if (ob.getErrors().size() == 0) {
            RichPopup.PopupHints hints = new RichPopup.PopupHints();
            this.getDelShpitmPopup().show(hints);
        }
    }

    public void setDelShpitmPopup(RichPopup delShpitmPopup) {
        this.delShpitmPopup = delShpitmPopup;
    }

    public RichPopup getDelShpitmPopup() {
        return delShpitmPopup;
    }

    public void processDelShpitm(ActionEvent actionEvent) {
        OperationBinding ob = ADFUtils.findOperation("processDelShpitm");
        ob.execute();

        if (ob.getErrors().size() == 0) {
            AdfFacesContext.getCurrentInstance().addPartialTarget(this.getShpitmTable());
            AdfFacesContext.getCurrentInstance().addPartialTarget(this.getShpItmButtons());
            this.getDelShpitmPopup().hide();
        }
    }

    public void setShpitmTable(RichTable shpitmTable) {
        this.shpitmTable = shpitmTable;
    }

    public RichTable getShpitmTable() {
        return shpitmTable;
    }

    public String getParamsManagedTemplates() {
        String mpmtMenuGrp = "SYS_MTCE";
        String mpmtMenuItem = "ADD_AMT_TPL";
        String pPerformQuery = "Y";
        Long pCimsRunnum = (Long) ADFUtils.getBoundAttributeValue("CimsRunnum");
        String pCimsDivcod = (String) ADFUtils.getBoundAttributeValue("CimsDivcod");
        String pLockMode = "Y";
        return "MPMT_MENUGRP=" + mpmtMenuGrp + "&MPMT_MENUITEM=" + mpmtMenuItem + "&P_PERFORM_QUERY=" + pPerformQuery + "&P_CIMS_RUNNUM=" + pCimsRunnum +
               "&P_LOCK_MODE=" + pLockMode + "&P_ADF_DIVCOD=" + pCimsDivcod;
    }

    public void removeShpitmAddamt(ActionEvent actionEvent) {
        OperationBinding ob = ADFUtils.findOperation("removeRow");
        ob.execute();
    }

    public void openChrtypInfo(ActionEvent actionEvent) {
        UIComponent triggerItem = actionEvent.getComponent();
        String loc = (String) triggerItem.getAttributes().get("loc");
        DCIteratorBinding dcIter;

        if (loc.equalsIgnoreCase("SHPITM"))
            dcIter = ADFUtils.findIterator("TciActshiamtVOIterator");
        else
            dcIter = ADFUtils.findIterator("TciAddAmtVOIterator");

        // Get an object representing the table and what may be selected within it
        ViewObject voTableData = dcIter.getViewObject();

        // Get selected row
        Row row = voTableData.getCurrentRow();

        if (row != null) {
            String chrtyp;

            if (loc.equalsIgnoreCase("SHPITM"))
                chrtyp = (String) row.getAttribute("CisaChrtyp");
            else
                chrtyp = (String) row.getAttribute("CiaaChrtyp");

            OperationBinding ob = ADFUtils.findOperation("setVarExe");
            ob.getParamsMap().put("chrTyp", chrtyp);
            ob.execute();

            RichPopup.PopupHints hints = new RichPopup.PopupHints();
            this.getChrInfoPopup().show(hints);

        }
    }

    public void setChrInfoPopup(RichPopup chrInfoPopup) {
        this.chrInfoPopup = chrInfoPopup;
    }

    public RichPopup getChrInfoPopup() {
        return chrInfoPopup;
    }

    public void setChrInfoShiForm(RichPanelFormLayout chrInfoShiForm) {
        this.chrInfoShiForm = chrInfoShiForm;
    }

    public RichPanelFormLayout getChrInfoShiForm() {
        return chrInfoShiForm;
    }

    public void setActshiamtTab(RichTable actshiamtTab) {
        this.actshiamtTab = actshiamtTab;
    }

    public RichTable getActshiamtTab() {
        return actshiamtTab;
    }

    public void openAddShiPopup(ActionEvent actionEvent) {
        OperationBinding ob;
        EditShipment dynRegContr = (EditShipment) ADFContext.getCurrent()
                                                            .getPageFlowScope()
                                                            .get("EditShipmentBean");
        TaskFlowId page = dynRegContr.getDynamicTaskFlowId();
        String currentPage = page.toString();

        if ("/WEB-INF/flows/shpitm/editShpitmCusfld-tf.xml#editShpitmCusfld-tf".equals(currentPage)) {
            ob = ADFUtils.findOperation("shiValidateUpdate");
            ob.execute();

            if (ob.getErrors().size() == 0) {
                ob = ADFUtils.findOperation("postChanges");
                ob.execute();
            }
        } else {
            ob = ADFUtils.findOperation("postChanges");
            ob.execute();
        }

        if (ob.getErrors().size() == 0) {
            ob = ADFUtils.findOperation("initAddShi");
            ob.execute();

            RichPopup.PopupHints hints = new RichPopup.PopupHints();
            this.getAddShiPopup().show(hints);
        }
    }

    public void setAddShiPopup(RichPopup addShiPopup) {
        this.addShiPopup = addShiPopup;
    }

    public RichPopup getAddShiPopup() {
        return addShiPopup;
    }

    public void addShiDialogButtonListener(ActionEvent actionEvent) {

        OperationBinding ob = ADFUtils.findOperation("addShipmentItems");
        ob.execute();

        if ((ob.getResult() != null)) {
            if ((ob.getErrors().isEmpty())) {
                AdfFacesContext.getCurrentInstance().addPartialTarget(this.getShpitmTable());
                AdfFacesContext.getCurrentInstance().addPartialTarget(this.getShpItmButtons());
                addShiPopup.hide();
            }
        }
    }

    public String getFormConnectionString() {
        String formConnectionString = (String) JSFUtils.getManagedBeanValue("UserProfileBean.formConnectionString");
        if (formConnectionString == null) {
            OperationBinding obPlGetSyspara = ADFUtils.findOperation("plGetSyspara");
            obPlGetSyspara.getParamsMap().put("param", "P_FORM_CONN_STR");
            formConnectionString = (String) obPlGetSyspara.execute();
        }

        return formConnectionString;
    }

    @Override
    public String getAttachmentLink() {
        OperationBinding ob;
        String div = (String) ADFUtils.getBoundAttributeValue("CimsDivcod");
        Long runnum = (Long) ADFUtils.getBoundAttributeValue("CimsRunnum");
        String username = (String) ADFContext.getCurrent()
                                             .getSessionScope()
                                             .get("userName");
        String userPassword = (String) ADFContext.getCurrent()
                                                 .getSessionScope()
                                                 .get("userPassword");

        //Post changes
        ob = ADFUtils.findOperation("postChanges");
        ob.execute();

        if (ob.getErrors().size() == 0) {
            //Get Access Right
            ob = ADFUtils.findOperation("getAttachmentAccessRight");
            ob.execute();
            Object ret = ob.getResult();
            String accessRight = (String) ret;

            //Get Form Connection String
            String formConnectionString = getFormConnectionString();
            LOGGER.info("getAttachmentLink Step 2 - formConnectionString: " + formConnectionString);

            //Get Country Code
            ob = ADFUtils.findOperation("getCtycod");
            ob.getParamsMap().put("userId", username);
            ob.execute();
            ret = ob.getResult();
            String ctycod = (String) ret;
            LOGGER.info("getAttachmentLink Step 3 - USERID: " + username + " COUNTRY CODE: " + ctycod);

            //Call Module
            String callModule;
            if ((ctycod != null && !ctycod.equalsIgnoreCase("HKG")) ||
                ((!formConnectionString.equalsIgnoreCase("xtso")) && (!formConnectionString.equalsIgnoreCase("xtsu")) &&
                 (!formConnectionString.equalsIgnoreCase("xts_dev")))) {
                callModule = "P_URL_SIATO";
            } else {
                callModule = "P_URL_SIATL";
            }
            LOGGER.info("getAttachmentLink Step 4 - callModule: " + callModule);

            //Define DB
            ob = ADFUtils.findOperation("plGetSyspara");
            ob.getParamsMap().put("param", "P_SYSTEM_TYP");
            ob.execute();
            ret = ob.getResult();
            String theDb = (String) ret;
            if (theDb == null)
                theDb = "xtso";
            else if (theDb.equalsIgnoreCase("Development"))
                theDb = "xtsd";
            else if (theDb.equalsIgnoreCase("UAT/Training"))
                theDb = "xtsu";
            else
                theDb = "COPY";
            LOGGER.info("getAttachmentLink Step 5 - theDb: " + theDb);

            if (!"COPY".equalsIgnoreCase(theDb)) {
                //Check Access Right
                ob = ADFUtils.findOperation("chkAttachmentAccess");
                ob.getParamsMap().put("accessRight", accessRight);
                ob.getParamsMap().put("callModule", callModule);
                ob.execute();

                if (ob.getErrors().size() == 0) {
                    //Get URL
                    ob = ADFUtils.findOperation("getCtySysPara");
                    ob.getParamsMap().put("param", callModule);
                    ob.getParamsMap().put("ctycod", ctycod);
                    ob.execute();
                    ret = ob.getResult();
                    String urlString = (String) ret;

                    //Get Params for URL
                    ob = ADFUtils.findOperation("getCallAttachParam");
                    ob.getParamsMap().put("username", username);
                    ob.getParamsMap().put("pwd", userPassword);
                    ob.getParamsMap().put("div", div);
                    ob.getParamsMap().put("runnum", runnum);
                    ob.getParamsMap().put("access", accessRight);
                    ob.getParamsMap().put("db", theDb);
                    ob.execute();
                    ret = ob.getResult();
                    String params = (String) ret;

                    urlString = urlString + params;
                    LOGGER.info("FULL URL: " + urlString);

                    return urlString;
                }
            }
        }
        return super.getAttachmentLink();
    }

    ///// Value Change Listeners
    public void ciptNamChgListener(ValueChangeEvent valueChangeEvent) {
        valueChangeEvent.getComponent().processUpdates(FacesContext.getCurrentInstance());
        OperationBinding ob = ADFUtils.findOperation("setCiptAdr");
        ob.execute();
    }

    ///// Accessors
    public void setHdrManageTplButton(RichButton hdrManageTplButton) {
        this.hdrManageTplButton = hdrManageTplButton;
    }

    public RichButton getHdrManageTplButton() {
        return hdrManageTplButton;
    }

    public void setSiSearchSosiTable(RichTable siSearchSosiTable) {
        this.siSearchSosiTable = siSearchSosiTable;
    }

    public RichTable getSiSearchSosiTable() {
        return siSearchSosiTable;
    }

    public void setCimsDuedate(RichInputDate cimsDuedate) {
        this.cimsDuedate = cimsDuedate;
    }

    public RichInputDate getCimsDuedate() {
        return cimsDuedate;
    }

    public void setCimsBildat(RichInputDate cimsBildat) {
        this.cimsBildat = cimsBildat;
    }

    public RichInputDate getCimsBildat() {
        return cimsBildat;
    }

    public void setApplyDefaultAddamtPopup(RichPopup applyDefaultAddamtPopup) {
        this.applyDefaultAddamtPopup = applyDefaultAddamtPopup;
    }

    public RichPopup getApplyDefaultAddamtPopup() {
        return applyDefaultAddamtPopup;
    }

    public void setShpItmButtons(RichPanelGroupLayout shpItmButtons) {
        this.shpItmButtons = shpItmButtons;
    }

    public RichPanelGroupLayout getShpItmButtons() {
        return shpItmButtons;
    }

    public String goShpitm() {

        OperationBinding ob = ADFUtils.findOperation("hdrValidateUpdate");
        ob.execute();

        ob = ADFUtils.findOperation("postChanges");
        ob.execute();

        if (ob.getErrors().size() == 0) {
            ob = ADFUtils.findOperation("validateAllBlocks");
            ob.execute();
        }

        if (ob.getErrors().size() == 0) {
            //Reset Dynamic Region to "Edit Shipment Item" when going to the Shipment Item page
            EditShipment dynRegContr = (EditShipment) ADFContext.getCurrent()
                                                                .getPageFlowScope()
                                                                .get("EditShipmentBean");
            dynRegContr.setDynamicTaskFlowId("/WEB-INF/flows/shpitm/editShpitm-tf.xml#editShpitm-tf");

            return "shpitm";
        }
        return null;
    }

    public String goHeader() {
        OperationBinding ob = ADFUtils.findOperation("shiValidateUpdate");
        ob.execute();

        ob = ADFUtils.findOperation("postChanges");
        ob.execute();

        if (ob.getErrors().size() == 0) {
            ob = ADFUtils.findOperation("reexeQuery");
            ob.execute();

            return "header";
        }
        return null;
    }

    public void addShiUnselectAll(ActionEvent actionEvent) {
        OperationBinding ob = ADFUtils.findOperation("unselectAllRows");
        ob.execute();
    }

    public void addShiSelectAll(ActionEvent actionEvent) {
        OperationBinding ob = ADFUtils.findOperation("selectAllRows");
        ob.execute();
    }

    public void processTciActshpitmClick(ClientEvent clientEvent) {
        OperationBinding ob = ADFUtils.findOperation("shiSelectionChkCusfld");
        ob.execute();
    }

    public void cusfldHeaderLovListener(LaunchPopupEvent launchPopupEvent) {
        OperationBinding ob = ADFUtils.findOperation("hdrChkForMstLst");
        ob.execute();
        Object ret = ob.getResult();

        String withMstLst = "N";
        if (ret != null)
            withMstLst = (String) ret;
        LOGGER.info("***[CUSFLD HEADER] with master list: " + withMstLst);

        if ("N".equals(withMstLst))
            launchPopupEvent.setLaunchPopup(false);

    }

    public void cusfldShiLovListener(LaunchPopupEvent launchPopupEvent) {
        OperationBinding ob = ADFUtils.findOperation("shiChkForMstLst");
        ob.execute();
        Object ret = ob.getResult();

        String withMstLst = "N";
        if (ret != null)
            withMstLst = (String) ret;
        LOGGER.info("***[CUSFLD SHPITM] with master list: " + withMstLst);

        if ("N".equals(withMstLst))
            launchPopupEvent.setLaunchPopup(false);
    }

    public void shpitmTableSelectionListener(SelectionEvent selectionEvent) {
        JSFUtils.invokeMethodExpression("#{bindings.TciActshpitmVO.collectionModel.makeCurrent}", Object.class, new Class[] { SelectionEvent.class },
                                        new Object[] { selectionEvent });

        OperationBinding ob = ADFUtils.findOperation("shiSelectionChkCusfld");
        ob.execute();
    }
}

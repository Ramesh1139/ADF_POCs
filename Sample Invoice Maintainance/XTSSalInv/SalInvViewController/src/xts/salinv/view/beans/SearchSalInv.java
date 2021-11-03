package xts.salinv.view.beans;

import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import oracle.adf.share.ADFContext;
import oracle.adf.share.logging.ADFLogger;
import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.component.rich.data.RichTable;
import oracle.adf.view.rich.component.rich.input.RichInputText;
import oracle.adf.view.rich.component.rich.nav.RichCommandMenuItem;
import oracle.adf.view.rich.context.AdfFacesContext;
import oracle.adf.view.rich.event.PopupCanceledEvent;

import oracle.binding.OperationBinding;

import org.apache.myfaces.trinidad.render.ExtendedRenderKitService;
import org.apache.myfaces.trinidad.util.Service;

import xts.common.view.framework.beans.DisabledLogicContainer;
import xts.common.view.framework.beans.GenericSearch;
import xts.common.view.framework.utils.ADFUtils;
import xts.common.view.framework.utils.JSFUtils;

public class SearchSalInv extends GenericSearch {
    private static final String vcName = "CoreTciMstVOCriteria";
    private static final String vcqName = "CoreTciMstVOCriteriaQuery";
    private RichPopup insertPopup;
    private RichPopup changeStatusToDeletePopup;
    private RichCommandMenuItem deleteStatusMenuItem;
    private RichInputText deleteReasonForChangeStatus;
    private RichPopup siFromSoPopup;
    private RichTable siFromSoSearchResult;
    private RichPopup applyDefaultAddamtPopup;

    private static final ADFLogger logger = ADFLogger.createADFLogger(SearchSalInv.class);

    public SearchSalInv() {
        super(vcName, vcqName);
    }

    public void handleParams() {
        //Handle Custom Parameters from SO
        String customParams = (String) getPageFlowScopeValue("customParams");
        logger.info("***customParams from SO: " + customParams);
        if (customParams != null) {
            String[] params = customParams.split("\\&");
            for (String paramPair : params) {
                if (paramPair.indexOf("=") >= 0) {
                    String paramName = paramPair.substring(0, paramPair.indexOf("=")).trim();
                    String paramValue = paramPair.substring(paramPair.indexOf("=") + 1).trim();

                    logger.info("***" + paramName + ": " + formatParamName(paramName));
                    ADFContext.getCurrent()
                              .getPageFlowScope()
                              .put(formatParamName(paramName), paramValue);
                }
            }
        }
    }

    private String formatParamName(String paramName) {
        String[] words = paramName.toLowerCase().split("\\_");
        String result = null;
        for (String word : words) {
            if (result == null)
                result = word;
            else
                result = result.concat(word.replaceFirst(word.substring(0, 1), Character.toString(word.charAt(0)).toUpperCase()));
        }
        return result;
    }

    @Override
    public void genericSearchEdit(ActionEvent actionEvent) {
        long start = 0;
        if (logger.isFinest()) {
            start = System.currentTimeMillis();
        }

        super.genericSearchEdit(actionEvent);

        OperationBinding ob;
        ob = ADFUtils.findOperation("cusfldHdrExeQry");
        ob.execute();

        ob = ADFUtils.findOperation("cusfldShiExeQry");
        ob.execute();

        ob = ADFUtils.findOperation("setCustomEditMode");
        ob.getParamsMap().put("edit", "E");
        ob.execute();

        ob = ADFUtils.findOperation("insertRecentUseLogAutonomous");
        ob.execute();

        if (logger.isFinest()) {
            long end = System.currentTimeMillis();
            long timeDiff = end - start;
            logger.finest("Execute time: " + timeDiff);
        }
    }

    @Override
    public void genericCreate(ActionEvent actionEvent) {

        OperationBinding ob = ADFUtils.findOperation("createTciMst");
        ob.execute();

        RichPopup.PopupHints hints = new RichPopup.PopupHints();
        this.getInsertPopup().show(hints);
    }

    @Override
    public String genericCreateAction() {
        return null;
    }

    public void setInsertPopup(RichPopup insertPopup) {
        this.insertPopup = insertPopup;
    }

    public RichPopup getInsertPopup() {
        return insertPopup;
    }

    public String insertTciMst() {
        OperationBinding ob = ADFUtils.findOperation("insertTciMst");
        ob.execute();

        int errors = ob.getErrors().size();
        if (errors == 0) {
            Object ret = ob.getResult();
            Long cimsRunnum;
            if (ret != null) {
                cimsRunnum = (Long) ret;
                ob = ADFUtils.findOperation("plAddamtTpldefChkAll");
                ob.getParamsMap().put("viewInstance", "InsertTciMstVO");
                ob.getParamsMap().put("cimsRunnum", cimsRunnum);
                ob.execute();
                ret = ob.getResult();

                if (ret != null) {
                    ob = ADFUtils.findOperation("plGetMsgmst");
                    ob.getParamsMap().put("param", "2617");
                    ob.execute();
                    ret = ob.getResult();
                    if (ret != null) {
                        JSFUtils.setManagedBeanValue("pageFlowScope.applyDefaultMsg", (String) ret);
                        JSFUtils.setManagedBeanValue("pageFlowScope.cimsRunnum", cimsRunnum);
                        RichPopup.PopupHints hints = new RichPopup.PopupHints();
                        this.getApplyDefaultAddamtPopup().show(hints);
                        return null;
                    }
                }
            }

            ob = ADFUtils.findOperation("cusfldHdrExeQry");
            ob.execute();

            ob = ADFUtils.findOperation("cusfldShiExeQry");
            ob.execute();

            ob = ADFUtils.findOperation("insertRecentUseLogAutonomous");
            ob.execute();

            getSetcodForNewRec();
            return "edit";
        }

        return "";
    }

    public void insertPopupCloseListener(PopupCanceledEvent popupCanceledEvent) {
        OperationBinding ob = ADFUtils.findOperation("removeCreatedTciMst");
        ob.execute();
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

    public void getSetcodForNewRec() {
        DisabledLogicContainer dlc = (DisabledLogicContainer) ADFContext.getCurrent()
                                                                        .getPageFlowScope()
                                                                        .get("XTSDisabledLogicContainerBean");
        dlc.resetData();
        OperationBinding ob = ADFUtils.findOperation("prepareDisable");
        ob.execute();
    }

    public void changeStatusFromMenu(ActionEvent actionEvent) {
        changeStatus((String) actionEvent.getComponent()
                                         .getAttributes()
                                         .get("STSCOD"), null);
    }

    private void changeStatus(String changeTo, String deleteReason) {
        OperationBinding ob = ADFUtils.findOperation("changeStatus");
        ob.getParamsMap().put("changeTo", changeTo);
        ob.getParamsMap().put("deleteReason", deleteReason);
        ob.execute();

        // If change status success, hide the dialog box and refresh the record
        if (Boolean.TRUE.equals(ob.getResult())) {
            if (changeTo.equalsIgnoreCase("DE")) {
                this.changeStatusToDeletePopup.hide();
            }
        }
        AdfFacesContext.getCurrentInstance().addPartialTarget(this.getMasterTable());
        AdfFacesContext.getCurrentInstance().addPartialTarget(this.getDetailTable());
    }

    public void setChangeStatusToDeletePopup(RichPopup changeStatusToDeletePopup) {
        this.changeStatusToDeletePopup = changeStatusToDeletePopup;
    }

    public RichPopup getChangeStatusToDeletePopup() {
        return changeStatusToDeletePopup;
    }

    public void changeStatusToDeleteBtn(ActionEvent actionEvent) {
        changeStatus((String) this.getDeleteStatusMenuItem()
                                  .getAttributes()
                                  .get("STSCOD"), (String) this.getDeleteReasonForChangeStatus().getValue());
        this.getDeleteReasonForChangeStatus().resetValue();
    }

    public void setDeleteStatusMenuItem(RichCommandMenuItem deleteStatusMenuItem) {
        this.deleteStatusMenuItem = deleteStatusMenuItem;
    }

    public RichCommandMenuItem getDeleteStatusMenuItem() {
        return deleteStatusMenuItem;
    }

    public void setDeleteReasonForChangeStatus(RichInputText deleteReasonForChangeStatus) {
        this.deleteReasonForChangeStatus = deleteReasonForChangeStatus;
    }

    public RichInputText getDeleteReasonForChangeStatus() {
        return deleteReasonForChangeStatus;
    }

    public String getLoadSiFromSoPopup() {
        String siFromThisSo;
        String siFromSo;
        Long somsRunnum;

        siFromThisSo = (String) getPageFlowScopeValue("newSiFromThisSo");
        siFromSo = (String) getPageFlowScopeValue("newSiFromSo");

        if ((String) getPageFlowScopeValue("somsRunnum") != null) {
            somsRunnum = Long.valueOf((String) ADFContext.getCurrent()
                                                         .getPageFlowScope()
                                                         .get("somsRunnum"));
        } else
            somsRunnum = null;

        if ("Y".equalsIgnoreCase(siFromThisSo) || "Y".equalsIgnoreCase(siFromSo)) {
            OperationBinding ob = ADFUtils.findOperation("siFromSoSetBindVar");
            ob.getParamsMap().put("somsRunnum", somsRunnum);
            ob.execute();

            //Clear pageFlowScope variables for new si from so
            clearPageFlowScopeVarFromSo();

            if (ob.getErrors().size() == 0) {
                RichPopup.PopupHints hints = new RichPopup.PopupHints();
                siFromSoPopup.show(hints);
            }
        }
        return null;
    }

    private Object getPageFlowScopeValue(String name) {
        ADFContext adfCtx = ADFContext.getCurrent();
        Map pageFlowScope = adfCtx.getPageFlowScope();
        Object val = pageFlowScope.get(name);

        if (val == null)
            return null;
        else
            return val;
    }

    public void setSiFromSoPopup(RichPopup siFromSoPopup) {
        this.siFromSoPopup = siFromSoPopup;
    }

    public RichPopup getSiFromSoPopup() {
        return siFromSoPopup;
    }

    public void setSiFromSoSearchResult(RichTable siFromSoSearchResult) {
        this.siFromSoSearchResult = siFromSoSearchResult;
    }

    public RichTable getSiFromSoSearchResult() {
        return siFromSoSearchResult;
    }

    public String siFromSoDialogButton() {
        //Call Validation
        OperationBinding ob = ADFUtils.findOperation("fValidateSiFrmSo");
        ob.execute();
        String ret = (String) ob.getResult();

        //Call PLSQL to insert record
        if ((ret != null && "Y".equalsIgnoreCase(ret)) && (ob.getErrors().size() == 0)) {
            ob = ADFUtils.findOperation("insertSiFromSo");
            ob.execute();
            //Hide Popup
            this.getSiFromSoPopup().hide();

            ob = ADFUtils.findOperation("cusfldHdrExeQry");
            ob.execute();

            ob = ADFUtils.findOperation("cusfldShiExeQry");
            ob.execute();

            //Get all necessary values (e.g. set codes) for DisabledLogicBean
            getSetcodForNewRec();
            return "edit";
        } else if (ret != null && "N".equalsIgnoreCase(ret)) {
            FacesMessage fm = new FacesMessage("<html><body>Terms on selected SOs are different.<br>Select \"Shortlist Unique Terms\" or continue to shortlist unique terms.</body></html>");
            fm.setSeverity(FacesMessage.SEVERITY_INFO);
            FacesContext fctx = FacesContext.getCurrentInstance();
            fctx.addMessage(null, fm);
        }
        return null;
    }

    public void clearPageFlowScopeVarFromSo() {
        String siFromThisSo;
        String siFromSo;
        String somsRunnum;
        String customParams;

        customParams = (String) getPageFlowScopeValue("customParams");
        siFromThisSo = (String) getPageFlowScopeValue("newSiFromThisSo");
        siFromSo = (String) getPageFlowScopeValue("newSiFromSo");
        somsRunnum = (String) getPageFlowScopeValue("somsRunnum");

        if (customParams != null)
            ADFContext.getCurrent()
                      .getPageFlowScope()
                      .remove("customParams");
        if (siFromThisSo != null)
            ADFContext.getCurrent()
                      .getPageFlowScope()
                      .remove("newSiFromThisSo");
        if (siFromSo != null)
            ADFContext.getCurrent()
                      .getPageFlowScope()
                      .remove("newSiFromSo");
        if (somsRunnum != null)
            ADFContext.getCurrent()
                      .getPageFlowScope()
                      .remove("somsRunnum");

    }

    public void setApplyDefaultAddamtPopup(RichPopup applyDefaultAddamtPopup) {
        this.applyDefaultAddamtPopup = applyDefaultAddamtPopup;
    }

    public RichPopup getApplyDefaultAddamtPopup() {
        return applyDefaultAddamtPopup;
    }

    public String applyDefaultListener() {

        Long cimsRunnum;
        if (ADFContext.getCurrent()
                      .getPageFlowScope()
                      .get("cimsRunnum") != null) {
            cimsRunnum = (Long) ADFContext.getCurrent()
                                          .getPageFlowScope()
                                          .get("cimsRunnum");
        } else
            cimsRunnum = null;


        OperationBinding ob = ADFUtils.findOperation("applyDefault");
        ob.getParamsMap().put("cimsRunnum", cimsRunnum);
        ob.execute();

        if (ob.getErrors().size() == 0) {
            ob = ADFUtils.findOperation("cusfldHdrExeQry");
            ob.execute();

            ob = ADFUtils.findOperation("cusfldShiExeQry");
            ob.execute();
            getSetcodForNewRec();
            return "edit";
        }

        return null;
    }

    public String cancelDefaultAddamt() {
        OperationBinding ob;
        ob = ADFUtils.findOperation("cusfldHdrExeQry");
        ob.execute();

        ob = ADFUtils.findOperation("cusfldShiExeQry");
        ob.execute();
        getSetcodForNewRec();
        return "edit";
    }

    @Override
    public void genericPrint(ActionEvent actionEvent) {
        super.genericPrint(actionEvent);
    }

    public void callPrint(ActionEvent actionEvent) {
        FacesContext fctx = FacesContext.getCurrentInstance();
        ExtendedRenderKitService erks = Service.getRenderKitService(fctx, ExtendedRenderKitService.class);
        String printFormParams;

        OperationBinding ob = ADFUtils.findOperation("plPrintSi");
        ob.execute();
        Object ret = ob.getResult();

        if (ob.getErrors().size() == 0) {
            printFormParams = (String) ret;

            if (printFormParams != null)
                erks.addScript(fctx, "callFormsFromAdfActLsnr('" + printFormParams + "')");
        }
    }

    public void siFromSoSelectAll(ActionEvent actionEvent) {
        OperationBinding ob = ADFUtils.findOperation("selectAllRows");
        ob.execute();
    }

    public void siFromSoDeselectAll(ActionEvent actionEvent) {
        OperationBinding ob = ADFUtils.findOperation("unselectAllRows");
        ob.execute();
    }

    public void selectAllValueChangeListener(ValueChangeEvent valueChangeEvent) {
        boolean isSelected = ((Boolean) valueChangeEvent.getNewValue()).booleanValue();

        OperationBinding ob = ADFUtils.findOperation("selectAllValueChange");
        ob.getParamsMap().put("isSelect", isSelected);
        ob.execute();
        String ret = (String) ob.getResult();
        if (ret != null) {
            showAlert(ret);
        }

        //Refresh the table
        AdfFacesContext.getCurrentInstance().addPartialTarget(this.getMasterTable());
    }

    private void showAlert(String msg) {
        FacesMessage fm = new FacesMessage(msg);
        fm.setSeverity(FacesMessage.SEVERITY_INFO);
        FacesContext fctx = FacesContext.getCurrentInstance();
        fctx.addMessage(null, fm);
    }
}

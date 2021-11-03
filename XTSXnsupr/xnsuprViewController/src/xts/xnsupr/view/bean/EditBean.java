package xts.xnsupr.view.bean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.el.ELContext;
import javax.el.ExpressionFactory;
import javax.el.ValueExpression;

import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import javax.faces.event.ValueChangeEvent;

import oracle.adf.model.BindingContext;
import oracle.adf.model.binding.DCBindingContainer;
import oracle.adf.model.binding.DCIteratorBinding;
import oracle.adf.share.ADFContext;

import oracle.adf.share.logging.ADFLogger;

import oracle.adf.view.rich.component.rich.RichPopup;

import oracle.adf.view.rich.component.rich.data.RichTable;
import oracle.adf.view.rich.component.rich.output.RichOutputText;

import oracle.adf.view.rich.context.AdfFacesContext;
import oracle.adf.view.rich.event.DialogEvent;

import oracle.adf.view.rich.event.QueryEvent;
import oracle.adf.view.rich.model.AttributeCriterion;
import oracle.adf.view.rich.model.ConjunctionCriterion;
import oracle.adf.view.rich.model.Criterion;
import oracle.adf.view.rich.model.FilterableQueryDescriptor;

import oracle.binding.OperationBinding;

import oracle.jbo.Key;
import oracle.jbo.Row;
import oracle.jbo.ViewCriteria;
import oracle.jbo.ViewCriteriaRow;
import oracle.jbo.ViewObject;
import oracle.jbo.server.ViewObjectImpl;

import org.apache.myfaces.trinidad.event.RangeChangeEvent;

import xts.common.view.framework.beans.GenericEdit;
import xts.common.view.framework.utils.ADFUtils;
import xts.common.view.framework.utils.JSFUtils;

public class EditBean extends GenericEdit{
    
    private String masterIteratorName = "Blk1TmpPrfNewcodVOIterator";
    private RichPopup bind_confirmpopup;
    private RichOutputText bind_consDesc;
    private RichTable bind_consubrolTable;
    private RichTable table1;
    String  dcIteratorToRefresh = "Blk1TmpPrfNewcodVOIterator";
    private RichTable bind_rejectReasontable;
    private RichPopup bind_declateMessagepopup;
    private RichPopup bind_popup;
    private RichPopup bind_rejectpop;
    private static final ADFLogger logger = ADFLogger.createADFLogger(EditBean.class);
    FacesContext context = FacesContext.getCurrentInstance();
//    SupplierViewBean supplbean =
//        (SupplierViewBean) context.getApplication().evaluateExpressionGet(context, "#{SupplierViewBean}", SupplierViewBean.class);
    //SupplierViewBean Supplbean1 = (SupplierViewBean)ADFContext.getCurrent().getPageFlowScope().get("SupplierViewBean");
    
   
    
    public EditBean() {
        
        super();
       
    }
    
   

    @Override
    public String genericCloseAction() {
        // TODO Implement this method
        operaionBindingMetod("Blk3Approve1VOFieldEvents_ButExit_bp");
       // AdfFacesContext.getCurrentInstance().addPartialTarget(table1);
           hightlightRow();
           
    
           
           return  "close";
       // return super.genericCloseAction();
    }

    @Override
    public void genericClose(ActionEvent actionEvent) {
        // TODO Implement this method
        //super.genericClose(actionEvent);
    }

    public void operaionBindingMetod(String methodName) {
        OperationBinding ob1 = ADFUtils.findOperation(methodName);
        ob1.execute();
    }
    
    public void rejectApprove1(ActionEvent actionEvent) {
      
        operaionBindingMetod("Blk3Approve1VOFieldEvents_ButReject_bp");
       
    }
    public void nextApprove1(ActionEvent actionEvent) {


        operaionBindingMetod("Blk3Approve1VOFieldEvents_ButNext_bp");
        

    }
    public void cancelApprove1(ActionEvent actionEvent) {
     operaionBindingMetod("Blk3Approve1VOFieldEvents_ButExit_bp");
        hightlightRow();
    }
    public void hightlightRow() {
        DCBindingContainer bindings = (DCBindingContainer) BindingContext.getCurrent().getCurrentBindingsEntry();
        DCIteratorBinding dcIter = bindings.findIteratorBinding(masterIteratorName);
       // DCIteratorBinding dcIter = ADFUtils.findIterator(masterIteratorName);

        String operation = (String) ADFContext.getCurrent()
                                              .getPageFlowScope()
                                              .get(masterIteratorName + "Operation");

        if ("EDIT".equals(operation)) {
            DCBindingContainer binding = (DCBindingContainer) BindingContext.getCurrent().getCurrentBindingsEntry();
            DCIteratorBinding edititor = binding.findIteratorBinding("WorkItemVOIterator");
            Row editIterCurrentRow = edititor.getViewObject().getCurrentRow();
            String harmoncode = (String) editIterCurrentRow.getAttribute("HarmonCode");

                if (harmoncode != null && !("".equals(harmoncode))) {
                    ViewObjectImpl searchIterVO = (ViewObjectImpl) dcIter.getViewObject();
                    ViewCriteria searchIterVC = searchIterVO.createViewCriteria();
                    ViewCriteriaRow searchIterVCRow = searchIterVC.createViewCriteriaRow();
                    searchIterVCRow.setAttribute("MphmHarmoncod", harmoncode);
                    searchIterVC.add(searchIterVCRow);
                    searchIterVO.executeQuery();
                    searchIterVO.applyViewCriteria(searchIterVC);
                    searchIterVO.executeQuery();
                    long rowcnt = searchIterVO.getEstimatedRowCount();
                    searchIterVO.removeViewCriteria(searchIterVC.getName());
                    if (rowcnt <= 0) {
                        //searchIterVO.executeQuery();
                        Integer startRange = (Integer) ADFContext.getCurrent()
                                                                 .getPageFlowScope()
                                                                 .get(masterIteratorName + "RangeStart");
                        String keyStr = (String) ADFContext.getCurrent()
                                                           .getPageFlowScope()
                                                           .get(masterIteratorName + "CurrentKeyString");
                        if (keyStr != null) {
                            dcIter.executeQuery();
                            dcIter.setRangeStart(startRange);
                            dcIter.setCurrentRowWithKey(keyStr);
                        } 
                    }
                }else{
                    Integer startRange = (Integer) ADFContext.getCurrent()
                                                             .getPageFlowScope()
                                                             .get(masterIteratorName + "RangeStart");
                    String keyStr = (String) ADFContext.getCurrent()
                                                       .getPageFlowScope()
                                                       .get(masterIteratorName + "CurrentKeyString");
                    if (keyStr != null) {
                        dcIter.executeQuery();
                        dcIter.setRangeStart(startRange);
                        dcIter.setCurrentRowWithKey(keyStr);
                    }
                }
            
        } else {
            if(null!=dcIter)
            {
           Row r1 = dcIter.getCurrentRow();
           Key keyCurrent =   r1.getKey();
           // Key keyCurrent = dcIter.getCurrentRow().getKey();
            Key keyCache = (Key) ADFContext.getCurrent()
                                           .getPageFlowScope()
                                           .get(masterIteratorName + "currentKey");

            Integer startRange = (Integer) ADFContext.getCurrent()
                                                     .getPageFlowScope()
                                                     .get(masterIteratorName + "RangeStart");


            if (keyCurrent.equals(keyCache)) {
                // refresh
                dcIter.executeQuery();
                dcIter.setRangeStart(startRange);
                // set back current row
                String currentKeyString = (String) ADFContext.getCurrent()
                                                             .getPageFlowScope()
                                                             .get(masterIteratorName + "CurrentKeyString");

                Row row = dcIter.findRowByKeyString(currentKeyString);


                if (row != null) {
                    dcIter.setCurrentRowWithKey(currentKeyString);
                } else {
                    logger.info("Current row not found for key: " + keyCache);
                }
            } 
            else if (!keyCurrent.equals(keyCache)) {
                // refresh, when new row inserted

                //                DCIteratorBinding masterIter2 = bindings.findIteratorBinding("Blk3TmpHarmonmstVOIterator");
                //                String currentKeyString = masterIter2.getCurrentRowKeyString();
                //                String currentKeyString2 = dcIter.getCurrentRowKeyString();
                //
                String keyStr = (String) ADFContext.getCurrent()
                                                   .getPageFlowScope()
                                                   .get(masterIteratorName + "CurrentKeyString");
                if (keyStr != null) {
                    dcIter.executeQuery();
                    dcIter.setRangeStart(startRange);
                    dcIter.setCurrentRowWithKey(keyStr);
                } else {
                    dcIter.executeQuery();
                    dcIter.setRangeStart(startRange);
                }
            }
            
        }
        }

    }
    
    public void rejectApprove2(ActionEvent actionEvent) {
        
        operaionBindingMetod("Blk3Approve2VOFieldEvents_ButReject_bp");
        
    }
    public void previousApprove2(ActionEvent actionEvent) {
        // Add event code here...
        operaionBindingMetod("Blk3Approve2VOFieldEvents_ButPrev_bp");
    }
    public void nextApprove2(ActionEvent actionEvent) {
        // Add event code here...
        
        operaionBindingMetod("Blk3Approve2VOFieldEvents_ButNext_bp");
    }
    
    public void cancelApprove2(ActionEvent actionEvent) {
        // Add event code here...
        
       operaionBindingMetod("Blk3Approve2VOFieldEvents_ButExit_bp");
        hightlightRow();
    }
    public Map getBoundAtributeValue() 
    {
            return new HashMap()
                {
                @Override
                public Object get(Object attrName) 
                {
                    Object attrVal = ADFUtils.getBoundAttributeValue((String) attrName);
                    return attrVal;
                }
     
                public Object put(Object attrName, Object attrVal)
                {
                    ADFUtils.setBoundAttributeValue((String) attrName, attrVal);
                    return null;
                }
            };
        }
    public void rejectApprove3(ActionEvent actionEvent) {
        // Add event code here...
        
        operaionBindingMetod("Blk3Approve3VOFieldEvents_ButReject_bp");
        
    }
    public void previousApprove3(ActionEvent actionEvent) {
        // Add event code here...
        
        operaionBindingMetod("Blk3Approve3VOFieldEvents_ButPrev_bp");
    }
    public void nextApprove3(ActionEvent actionEvent) {
        // Add event code here...
        operaionBindingMetod("Blk3Approve3VOFieldEvents_ButNext_bp");
        
    }
    public void findApprove3(ActionEvent actionEvent) {
        operaionBindingMetod("Blk3Approve3VOFieldEvents_ButFind_bp1");
        
    }
    public void resetApprove3(ActionEvent actionEvent) {
        // Add event code here...
        operaionBindingMetod("Blk3Approve3VOFieldEvents_ButReset_bp1");
        
    }
    public void rejectApprove4(ActionEvent actionEvent) {
        // Add event code here...
        
        operaionBindingMetod("Blk3Approve4VOFieldEvents_ButReject_bp");
       // JSFUtils.setExpressionValue("#{bindings.DeclineReason.inputValue}","");
    }
    public void previousApprove4(ActionEvent actionEvent) {
        // Add event code here...
        operaionBindingMetod("Blk3Approve4VOFieldEvents_ButPrev_bp");
    }
    public void confirmApprove4(ActionEvent actionEvent) {
        // Add event code here...
        //Boolean validationFlag = false;
         OperationBinding ob = ADFUtils.findOperation("Blk3Approve4VOFieldEvents_ButApprove_bp");
         ob.getParamsMap().put("status", "nothing");  
        //String validationflag = ob.getResult().toString();
         ob.execute();
           
                         Boolean validationFlag = (Boolean) ob.getResult(); 
                         
                         System.out.println("valueof FLag1"+validationFlag);
                if(validationFlag)
                {
                 System.out.println("valueof FLag2"+validationFlag);
                    RichPopup.PopupHints hints = new RichPopup.PopupHints();
                              this.getBind_confirmpopup().show(hints);
                }
            
        
    }

    public void setBind_confirmpopup(RichPopup bind_confirmpopup) {
        this.bind_confirmpopup = bind_confirmpopup;
    }

    public RichPopup getBind_confirmpopup() {
        return bind_confirmpopup;
    }
    public void setBind_consDesc(RichOutputText bind_consDesc) {
        this.bind_consDesc = bind_consDesc;
    }

    public RichOutputText getBind_consDesc() {
        return bind_consDesc;
    }
    public void setBind_consubrolTable(RichTable bind_consubrolTable) {
        this.bind_consubrolTable = bind_consubrolTable;
    }

    public RichTable getBind_consubrolTable() {
        return bind_consubrolTable;
    }
    public void consubrolCheckBox(ValueChangeEvent valueChangeEvent) {
      Boolean checkNewValue = (Boolean) valueChangeEvent.getNewValue();   
        if (!checkNewValue) 
        {
       // getResetValue().resetValue();
        
        }
        DCBindingContainer bindings = (DCBindingContainer)BindingContext.getCurrent().getCurrentBindingsEntry();
            DCIteratorBinding dcItteratorBindings =bindings.findIteratorBinding("BlkConsubrolVOIterator");//BlkConsubrolVOIterator
        
            ViewObject voTableData = dcItteratorBindings.getViewObject();
            //Row r = voTableData.getCurrentRow();
         int currentIndex=voTableData.getCurrentRowIndex();
            System.out.println( "consubrolCheckBox " + voTableData.getCurrentRowIndex());
        
               OperationBinding ob1 = ADFUtils.findOperation("BlkConsubrolVOFieldEvents_MptmChkbox_cc");//BlkConsubrolVOFieldEvents_MptmChkbox_cc
               ob1.getParamsMap().put("checkNewValue", checkNewValue);
               ob1.execute();
        System.out.println( "consubrolCheckBox " + voTableData.getCurrentRowIndex());
        voTableData.setCurrentRowAtRangeIndex(currentIndex);
        System.out.println( "consubrolCheckBox " + voTableData.getCurrentRowIndex());
        
    System.out.println("Value of ConsubDes :"+bind_consDesc.getValue());
    
     AdfFacesContext.getCurrentInstance().addPartialTarget(bind_consubrolTable);
            
      
    }

    public void locationVCL(ValueChangeEvent valueChangeEvent)
    {
        if(null!=valueChangeEvent) 
        {
         String locationCode =   valueChangeEvent.getNewValue().toString(); 
         System.out.println("Selected Location ID :"+locationCode);
         
        }
    }
    
    public void confirmpopup(DialogEvent dialogEvent) {
        // Add event code here...
        
        if(dialogEvent.getOutcome() == DialogEvent.Outcome.ok)
        {
            
            FacesContext facesContext = FacesContext.getCurrentInstance();
            NavigationHandler nh = facesContext.getApplication().getNavigationHandler();
           
            OperationBinding ob = ADFUtils.findOperation("Blk3Approve4VOFieldEvents_ButApprove_bp");
            ob.getParamsMap().put("status", "ok");  
             ob.execute();
            resetDashboardTable();
          //  table1 =  Supplbean1.getTable1();
          // resetPageNumber(dcIteratorToRefresh,table1); 
           
            nh.handleNavigation(facesContext, null, "close");
        }
        else
        {
            
        }
        
        
    }
    
    public void resetDashboardTable()
    {
        //Start : Reset of Dashboard table
        DCBindingContainer bindings = (DCBindingContainer)BindingContext.getCurrent().getCurrentBindingsEntry();
        DCIteratorBinding dcItteratorBindings =bindings.findIteratorBinding(masterIteratorName);
        ViewObject voTableData = dcItteratorBindings.getViewObject();
        voTableData.reset();
       // Row r = voTableData.first();
        //voTableData.setCurrentRow(r);
        voTableData.executeQuery(); 
       //table1 = Supplbean1.getTable1();
       // table1.resetStampState();
//        AdfFacesContext.getCurrentInstance().addPartialTarget(table1);
        //End :  
        
      
    }

    public void setTable1(RichTable table1) {
        this.table1 = table1;
    }

    public RichTable getTable1() {
        return table1;
    }
    public void resetPageNumber(String IteratorName,RichTable tableBinding) 
    {
           DCIteratorBinding dcIter = (DCIteratorBinding)(BindingContext.getCurrent().getCurrentBindingsEntry()).get(IteratorName);

           int taskIndex = 0; // It will navigate to the page where 1st element exist.

           int range = dcIter.getRangeSize();

           int oldStart = dcIter.getRangeStart();

           int newStart = taskIndex-(taskIndex % range);

           dcIter.getRowSetIterator().setRangeStart(newStart);

           dcIter.setRangeStart(newStart);

           RangeChangeEvent event = new RangeChangeEvent(tableBinding, oldStart, oldStart+range, newStart, newStart+range);

           tableBinding.broadcast(event);

           dcIter.getRowSetIterator().setCurrentRowAtRangeIndex(taskIndex % range);

           dcIter.setCurrentRowIndexInRange(taskIndex % range);
           
           AdfFacesContext.getCurrentInstance().addPartialTarget(tableBinding);
           
           
       }
    public void previousApprove5(ActionEvent actionEvent) {
        // Add event code here...
        
        operaionBindingMetod("Blk3Approve5VOFieldEvents_ButPrev_bp");
    }
    public void rejectApprove5(ActionEvent actionEvent) {
        // Add event code here...
        
        System.out.println("rejectApprove5");
       //System.out.println("Value of Declaine :"+bind_declareMessage.getValue());     
        DCBindingContainer dcb1 = (DCBindingContainer) evaluateEL("#{bindings}");
        DCIteratorBinding dciter1 = dcb1.findIteratorBinding("Blk3Approve5VOIterator");
        ViewObject vo1 = dciter1.getViewObject();
        Row r1 = vo1.getCurrentRow();
       String declaineMessage = (String)r1.getAttribute("DeclineReason");
      
        //if(bind_declareMessage.getValue() == null || bind_declareMessage.getValue() == "")
       if(declaineMessage == null || declaineMessage == "")
        {
            RichPopup.PopupHints hints = new RichPopup.PopupHints();
                      this.getBind_declateMessagepopup().show(hints);
        }
        else
        {
            RichPopup.PopupHints hints = new RichPopup.PopupHints();
                      this.getBind_rejectpop().show(hints);
        }
        
        
        
        
       // operaionBindingMetod("Blk3Approve5VOFieldEvents_ButConfirm_bp");
    }

    public void getMessageAction(ActionEvent actionEvent) {
    
        
         FilterableQueryDescriptor queryDescriptor = (FilterableQueryDescriptor) getBind_rejectReasontable().getFilterModel();
               if (queryDescriptor != null && queryDescriptor.getFilterConjunctionCriterion() != null)
               {
                   ConjunctionCriterion cc = queryDescriptor.getFilterConjunctionCriterion();
                    
                   List<Criterion> lc = cc.getCriterionList();
                   for (Criterion c : lc) {
                       if (c instanceof AttributeCriterion) {
                           AttributeCriterion ac = (AttributeCriterion) c;
                           ac.setValue(null);
                       }
                   }
                   getBind_rejectReasontable().queueEvent(new QueryEvent(getBind_rejectReasontable(), queryDescriptor));
               }
               
        RichPopup.PopupHints hints = new RichPopup.PopupHints();
        this.getBind_popup().show(hints);
        
                 
    }
    private static Object evaluateEL(String el) 
    {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ELContext elContext = facesContext.getELContext();
        ExpressionFactory expressionFactory = facesContext.getApplication().getExpressionFactory();
        ValueExpression exp = expressionFactory.createValueExpression(elContext, el, Object.class);
        return exp.getValue(elContext);
    }


    public void setBind_rejectReasontable(RichTable bind_rejectReasontable) {
        this.bind_rejectReasontable = bind_rejectReasontable;
    }

    public RichTable getBind_rejectReasontable() {
        return bind_rejectReasontable;
    }

    public void setBind_declateMessagepopup(RichPopup bind_declateMessagepopup) {
        this.bind_declateMessagepopup = bind_declateMessagepopup;
    }

    public RichPopup getBind_declateMessagepopup() {
        return bind_declateMessagepopup;
    }

    public void setBind_popup(RichPopup bind_popup) {
        this.bind_popup = bind_popup;
    }

    public RichPopup getBind_popup() {
        return bind_popup;
    }

    public void setBind_rejectpop(RichPopup bind_rejectpop) {
        this.bind_rejectpop = bind_rejectpop;
    }

    public RichPopup getBind_rejectpop() {
        return bind_rejectpop;
    }
    public void popupOkActionListener(ActionEvent actionEvent) 
    {
       
       
       
        DCBindingContainer dcb = (DCBindingContainer) evaluateEL("#{bindings}");
        DCIteratorBinding dciter = dcb.findIteratorBinding("MpmxTxtLovVOIterator");
        ViewObject vo = dciter.getViewObject();
        Row r = vo.getCurrentRow();
        String code = (String) r.getAttribute("MpmxDes");
        String desc = (String) r.getAttribute("MpmxTxt");
        

        System.out.println("GetMessage Code ==" + code);
        System.out.println("GetMessage Description ==" + desc);
        
        
       JSFUtils.setExpressionValue("#{bindings.DeclineReason.inputValue}", desc);
      /*  DCBindingContainer dcb1 = (DCBindingContainer) evaluateEL("#{bindings}");
        DCIteratorBinding dciter1 = dcb1.findIteratorBinding("Blk3Approve5VOIterator");
        ViewObject vo1 = dciter1.getViewObject();
        Row r1 = vo1.getCurrentRow();
        r1.setAttribute("DeclineReason", desc); */
        
        
        RichPopup.PopupHints hints = new RichPopup.PopupHints();
        this.getBind_popup().hide();
        
        
        
    }
    public void popupCancelActionListener(ActionEvent actionEvent) {
        

       // JSFUtils.setExpressionValue("#{bindings.DeclineReason.inputValue}", "");

        this.getBind_popup().hide();
    }
    public void rejectpopDialog(DialogEvent dialogEvent) {
        if(dialogEvent.getOutcome() == DialogEvent.Outcome.ok)
        {
       //Blk1TmpPrfNewcodVOIterator
           // setCurrentSelectedRecord();
           OperationBinding ob1 = ADFUtils.findOperation("Blk3Approve5VOFieldEvents_ButConfirm_bp");
           ob1.execute();
         Boolean status = (Boolean) ob1.getResult();
         if(status)
         {
           
              //operaionBindingMetod("Blk3Approve5VOFieldEvents_ButConfirm_bp");
            //resetDashboardTable();
           // table1 =  Supplbean1.getTable1();
            
           // resetPageNumber(dcIteratorToRefresh,table1);
            // Refresh of Dashboard page 
         /*     ADFContext.getCurrent()
                   .getbackingBeanScope()
                   .put("Blk1TmpPrfNewcodVOIteratorRangeStart",null);
          String value = (String)ADFContext.getCurrent().getbackingBeanScope().get("Blk1TmpPrfNewcodVOIteratorRangeStart");
          System.out.println("scope value =="+value);
               //Blk1TmpPrfNewcodVOIteratorRangeStart
                BindingContext bindingContext = BindingContext.getCurrent();
           // @SuppressWarnings("deprecation")
            DCDataControl dc = bindingContext.findDataControl("xnsuprAMDataControl"); 
                xnsuprAMImpl am = (xnsuprAMImpl) dc.getDataProvider();
            am.lib().goBlock("blk1_tmp_prf_newcod");
            am.lib().executeQuery(); 
               
            ResetUtils.reset(table1); */
            
               
               
             // Navigation to Dashboard page
            FacesContext facesContext = FacesContext.getCurrentInstance();
            NavigationHandler nh = facesContext.getApplication().getNavigationHandler();
            nh.handleNavigation(facesContext, null, "close");
            
         }
            
           
        }
        
    }

}

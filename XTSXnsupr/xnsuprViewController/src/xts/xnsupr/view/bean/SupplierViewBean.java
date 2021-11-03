package xts.xnsupr.view.bean;

import java.math.BigDecimal;

import oracle.adf.view.rich.component.rich.output.RichOutputText;

import org.apache.myfaces.trinidad.event.SelectionEvent;
import org.apache.myfaces.trinidad.render.ExtendedRenderKitService;
import org.apache.myfaces.trinidad.util.Service;
import oracle.adf.view.rich.util.ResetUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


import javax.el.ELContext;
import javax.el.ExpressionFactory;
import javax.el.MethodExpression;
import javax.el.ValueExpression;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import javax.faces.application.NavigationHandler;

import javax.faces.application.ViewHandler;
import javax.faces.component.UIViewRoot;
import javax.faces.event.ValueChangeEvent;

import oracle.adf.model.BindingContext;
import oracle.adf.model.binding.DCBindingContainer;
import oracle.adf.model.binding.DCDataControl;
import oracle.adf.model.binding.DCIteratorBinding;
import oracle.adf.share.ADFContext;
import oracle.adf.share.logging.ADFLogger;
import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.component.rich.data.RichTable;
import oracle.adf.view.rich.component.rich.input.RichInputText;
import oracle.adf.view.rich.component.rich.input.RichSelectBooleanCheckbox;
import oracle.adf.view.rich.context.AdfFacesContext;

import oracle.adf.view.rich.event.DialogEvent;

import oracle.adf.view.rich.event.PopupFetchEvent;

import oracle.adf.view.rich.event.QueryEvent;
import oracle.adf.view.rich.model.AttributeCriterion;
import oracle.adf.view.rich.model.ConjunctionCriterion;

import oracle.adf.view.rich.model.Criterion;

import oracle.binding.BindingContainer;
import oracle.binding.OperationBinding;

import oracle.jbo.RowSetIterator;
import oracle.jbo.Row;

import oracle.jbo.ViewObject;

import org.apache.myfaces.trinidad.event.SelectionEvent;

import xts.common.view.framework.utils.ADFUtils;
import xts.common.view.framework.utils.JSFUtils;
import oracle.adf.view.rich.model.FilterableQueryDescriptor;

import oracle.jbo.Key;
import oracle.jbo.ViewCriteria;
import oracle.jbo.ViewCriteriaRow;
import oracle.jbo.server.ViewObjectImpl;

import org.apache.myfaces.trinidad.event.RangeChangeEvent;

import xts.common.model.framework.GenericViewObjectImpl;

import xts.common.view.framework.beans.GenericSearch;

import xts.xnsupr.model.services.xnsuprAMImpl;
import xts.xnsupr.model.views.WorkItemVORowImpl;

public class SupplierViewBean extends GenericSearch{
  
    private RichInputText bind_declareMessage;
   private Boolean flag = false;
    String  dcIteratorToRefresh = "Blk1TmpPrfNewcodVOIterator";
    private RichSelectBooleanCheckbox resetValue;
    private RichTable table1;
    private RichTable bind_rejectReasontable;
   
   private  String selectedRecordstatuscode;
  
   private String masterIteratorName = "Blk1TmpPrfNewcodVOIterator"; //Blk1TmpPrfNewcodVOIterator
    private static final ADFLogger logger = ADFLogger.createADFLogger(SupplierViewBean.class);
    
    private String consumableTableName = "BlkConsubrolVOIterator";//BlkConsubrolVOIterator
   
    private RichSelectBooleanCheckbox resetConsub;
  
    
    public SupplierViewBean() 
    {
        super("","");
    }

    

    @Override
    public void searchListener(ActionEvent actionEvent) {
     
        operaionBindingMetod("Blk2CriteriaVOFieldEvents_Ok_bp");
        AdfFacesContext.getCurrentInstance().addPartialTarget(table1); 
       
        
       // super.searchListener(actionEvent);
    }

    @Override
    public void genericClose(ActionEvent actionEvent) {
        // TODO Implement this method
        super.genericClose(actionEvent);
    }

    @Override
    public String genericCloseAction() {
        // TODO Implement this method
        return super.genericCloseAction();
    }


    public void setFlag(Boolean flag) {
        this.flag = flag;
    }

    public Boolean getFlag() {
        return flag;
    }

   


    public void confirmActionListerner(ActionEvent actionEvent) {
        if (actionEvent != null)
        {
            
           operaionBindingMetod("Blk1ControlVOFieldEvents_Blk1Insert_bp");
            
          /*  DCBindingContainer dcb = (DCBindingContainer) evaluateEL("#{bindings}");
            DCIteratorBinding ib = dcb.findIteratorBinding("Blk1TmpPrfNewcodVOIterator");
            ViewObject vo = ib.getViewObject();
            String key = ib.getCurrentRowKeyString();
            ADFContext.getCurrent().getbackingBeanScope().put("currentKey", key);
            System.out.println(key);
           int rangeStart = ib.getRangeStart();
            ADFContext.getCurrent().getbackingBeanScope().put("rangeStart", rangeStart);
            System.out.println(rangeStart); */
    
          
          DCBindingContainer bindings = (DCBindingContainer) BindingContext.getCurrent().getCurrentBindingsEntry();
          DCIteratorBinding masterIter = bindings.findIteratorBinding(masterIteratorName);
            DCIteratorBinding workIterator = bindings.findIteratorBinding("WorkItemVOIterator");
            
            WorkItemVORowImpl row  = (WorkItemVORowImpl) workIterator.getCurrentRow();
            if(row!=null && row.getWErrmsg()!=null) {
                System.out.println("The value of row.getWErrmsg():- "+row.getWErrmsg());
                row.setWErrmsg_noVal(null);
                return;
            }
          
          String ketString  = masterIter.getCurrentRowKeyString();
          int rangeStart = masterIter.getRangeStart();
          ADFContext.getCurrent()
                    .getPageFlowScope()
                    .put(masterIteratorName+"CurrentKeyString", ketString);
          ADFContext.getCurrent()
                    .getPageFlowScope()
                    .put(masterIteratorName+"CurrentKey", masterIter.getCurrentRow().getKey());
          ADFContext.getCurrent()
                    .getPageFlowScope()
                    .put(masterIteratorName+"RangeStart", rangeStart);
          
             ADFContext.getCurrent()
                        .getPageFlowScope()
                        .put(masterIteratorName+"Operation", "CONFIRM"); 
             
            
         masterIter.executeQuery();
          masterIter.setCurrentRowWithKey(ketString);
          
           
        }
    }

    public void searchActionListener(ActionEvent actionEvent) 
    {
      
        operaionBindingMetod("Blk2CriteriaVOFieldEvents_Ok_bp");
       
      /*  DCBindingContainer bindings = (DCBindingContainer)BindingContext.getCurrent().getCurrentBindingsEntry();
        DCIteratorBinding dciter = bindings.findIteratorBinding("Blk1TmpPrfNewcodVOIterator");
        ViewObject vo = dciter.getViewObject();
        int i= 1;
        
       vo.setCurrentRowAtRangeIndex(i);
       
        //Row r = vo.getCurrentRow();
        
       // System.out.println("Disable Value ==="+r.getAttribute("disableResubmit")); */
      //hightlightRowFirstSection();
     
        AdfFacesContext.getCurrentInstance().addPartialTarget(table1); 
      //  table1.resetStampState();
        
       
        
    }
        public void setCurrentSelectedRecord(){
            System.out.println("******* Start Time ********"+ System.currentTimeMillis());        
            String dcIteratorToUnLock = "Blk1TmpPrfNewcodVOIterator"; //Blk1TmpPrfNewcodVOIterator   
            String dcIteratorToRefresh = "Blk1TmpPrfNewcodVOIterator";
            if (dcIteratorToUnLock != null) {
                    DCIteratorBinding dcIter = ADFUtils.findIterator(dcIteratorToRefresh);
                ViewObject voBlk1 = dcIter.getViewObject();
                voBlk1.reset();
              //  voBlk1.setAccessMode(GenericViewObjectImpl.RANGE_PAGING);
                byte accessMode = 1;
                voBlk1.setAccessMode(accessMode);
            // refresh on Close
            if(dcIter==null || dcIter.getCurrentRow()==null )  {
                return ;
            }
            Key keyCurrent = dcIter.getCurrentRow().getKey();
            Key keyCache = (Key) ADFContext.getCurrent()
                                           .getPageFlowScope()
                                           .get(dcIteratorToRefresh + "CurrentKey");
            System.out.println("keyCurrent:"+keyCurrent+"::keyCache"+keyCache);
            if (keyCache!=null) {
                // refresh
                dcIter.executeQuery();

                // set back current row
                String currentKeyString = (String) ADFContext.getCurrent()
                                                             .getPageFlowScope()
                                                             .get(dcIteratorToRefresh + "CurrentKeyString");
                Row row = dcIter.findRowByKeyString(currentKeyString);
                if (row != null) {
                    dcIter.setCurrentRowWithKey(currentKeyString);
                } else {
                    //logger.info("Current row not found for key: " + keyCache);
                }
            } else if (!keyCurrent.equals(keyCache)) {
                // refresh, when new row inserted
                dcIter.executeQuery();
            }
            }
            System.out.println("******* End Time ********"+ System.currentTimeMillis());                
        }


    public void cancelApprove1(ActionEvent actionEvent) {
     operaionBindingMetod("Blk3Approve1VOFieldEvents_ButExit_bp");
       // hightlightRow();
    }

    public void nextApprove1(ActionEvent actionEvent) {


        operaionBindingMetod("Blk3Approve1VOFieldEvents_ButNext_bp");
        

    }

    public void rejectApprove1(ActionEvent actionEvent) {
      
        operaionBindingMetod("Blk3Approve1VOFieldEvents_ButReject_bp");
       
    }


    private static Object evaluateEL(String el) 
    {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ELContext elContext = facesContext.getELContext();
        ExpressionFactory expressionFactory = facesContext.getApplication().getExpressionFactory();
        ValueExpression exp = expressionFactory.createValueExpression(elContext, el, Object.class);
        return exp.getValue(elContext);
    }

   

   

    public void cancelApprove5(ActionEvent actionEvent)
    {
        
        JSFUtils.setExpressionValue("#{bindings.DeclineReason.inputValue}","");
        
        operaionBindingMetod("Blk3Approve5VOFieldEvents_ButExit_bp");
       // hightlightRow();

    }

    public void operaionBindingMetod(String methodName) {
        OperationBinding ob1 = ADFUtils.findOperation(methodName);
        ob1.execute();
    }

    public void cancelApprove2(ActionEvent actionEvent) {
        // Add event code here...
        
       operaionBindingMetod("Blk3Approve2VOFieldEvents_ButExit_bp");
       // hightlightRow();
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

    public void cancelApprove3(ActionEvent actionEvent) {
        // Add event code here...
        
        operaionBindingMetod("Blk3Approve3VOFieldEvents_ButExit_bp");
       // hightlightRow();
    }
   

    public void cancelApprove4(ActionEvent actionEvent) {
        // Add event code here...
        operaionBindingMetod("Blk3Approve4VOFieldEvents_ButExit_bp");
       // hightlightRow();
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


   

  
   
    
    private void showAlert(String msg)
    {
        FacesMessage fm = new FacesMessage(msg);
        fm.setSeverity(FacesMessage.SEVERITY_INFO);
        FacesContext fctx = FacesContext.getCurrentInstance();
        fctx.addMessage(null, fm);
    }
    
    private void showWarning(String msg)
    {
        FacesMessage fm = new FacesMessage(msg);
        fm.setSeverity(FacesMessage.SEVERITY_WARN);
        FacesContext fctx = FacesContext.getCurrentInstance();
        fctx.addMessage(null, fm);
    }

   
    public void setBind_declareMessage(RichInputText bind_declareMessage) {
        this.bind_declareMessage = bind_declareMessage;
    }

    public RichInputText getBind_declareMessage() {
        return bind_declareMessage;
    }

   
    public void setResetValue(RichSelectBooleanCheckbox resetValue) {
        this.resetValue = resetValue;
    }

    public RichSelectBooleanCheckbox getResetValue() {
        return resetValue;
    }

  
   

   

    public void setTable1(RichTable table1) {
        this.table1 = table1;
    }

    public RichTable getTable1() {
        return table1;
    }

    public void statusCheckbox(ValueChangeEvent valueChangeEvent) 
    {
        
        if(null!=valueChangeEvent)
        {
       Object selectedValue = valueChangeEvent.getNewValue();
       System.out.println("Empty Value is "+selectedValue);
       System.out.println("Empty Value is trim : "+selectedValue.toString().trim());
           if(selectedValue.equals(""))
           {
               System.out.println("Seleted the Value is Empty");
                   
           }
            if(selectedValue.equals(null))
            {
                System.out.println("Seleted the Value is NULL");
                    
            }
            if(selectedValue.equals(" "))
            {
                System.out.println("Seleted the Value is Empty space");
                    
            }
            
            
            
        }
     /*  DCBindingContainer bindings = (DCBindingContainer)BindingContext.getCurrent().getCurrentBindingsEntry();
            DCIteratorBinding dcItteratorBindings =bindings.findIteratorBinding("Blk1TmpPrfNewcodVOIterator");
        ViewObject vo = dcItteratorBindings.getViewObject();
        vo.executeQuery(); */
        
//     operaionBindingMetod("Blk2CriteriaVOFieldEvents_Ok_bp");
//     AdfFacesContext.getCurrentInstance().addPartialTarget(table1);
//     table1.resetStampState();
        
      
    }

    public void getMessagePopup(PopupFetchEvent popupFetchEvent) 
    {
        // Add event code here...
        DCBindingContainer bindings = (DCBindingContainer)BindingContext.getCurrent().getCurrentBindingsEntry();
             DCIteratorBinding dcItteratorBindings =bindings.findIteratorBinding("MpmxTxtLovVOIterator");
         ViewObject vo = dcItteratorBindings.getViewObject();
         vo.executeQuery();  
         bind_rejectReasontable.resetStampState();
    
      
    }

    public void setBind_rejectReasontable(RichTable bind_rejectReasontable) {
        this.bind_rejectReasontable = bind_rejectReasontable;
    }

    public RichTable getBind_rejectReasontable() {
        return bind_rejectReasontable;
    }

   
  /*  public void masterTableSelectionListener(SelectionEvent selectionEvent) {
       
    
        
        invokeEL("#{bindings.Blk1TmpPrfNewcodVO.collectionModel.makeCurrent}", new Class[] {SelectionEvent.class},
                                new Object[] { selectionEvent });
               // get the selected row , by this you can get any attribute of that row
               Row selectedRow =
                   (Row)evaluateEL("#{bindings.Blk1TmpPrfNewcodVOIterator.currentRow}"); // get the current selected row
               //to show popup, you can write your logic here , what you wanna do
               
               
         selectedRecordstatuscode = selectedRow.getAttribute("MppfcStscod").toString(); ;
        System.out.println("Selected Recored Stauts :"+selectedRecordstatuscode); 
       
       
    } */
    public static Object evaluateEL1(String el) {
    FacesContext facesContext = FacesContext.getCurrentInstance();
    ELContext elContext = facesContext.getELContext();
    ExpressionFactory expressionFactory =
    facesContext.getApplication().getExpressionFactory();
    ValueExpression exp =
    expressionFactory.createValueExpression(elContext, el,
    Object.class);

    return exp.getValue(elContext);
    }
    public static Object invokeEL(String el, Class[] paramTypes,
    Object[] params) {
    FacesContext facesContext = FacesContext.getCurrentInstance();
    ELContext elContext = facesContext.getELContext();
    ExpressionFactory expressionFactory =
    facesContext.getApplication().getExpressionFactory();
    MethodExpression exp =
    expressionFactory.createMethodExpression(elContext, el,
    Object.class, paramTypes);

    return exp.invoke(elContext, params);
    }

    public void setSelectedRecordstatuscode(String selectedRecordstatuscode) {
        this.selectedRecordstatuscode = selectedRecordstatuscode;
    }

    public String getSelectedRecordstatuscode() {
        return selectedRecordstatuscode;
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
    
    public void hightlightRowFirstSection() 
    {
           // backingBeanScope.Blk1TmpPrfNewcodVOIteratorRangeStart 
           int i = 0;
           AdfFacesContext.getCurrentInstance().getPageFlowScope().put("Blk1TmpPrfNewcodVOIteratorRangeStart",i );
           
           int Value = (Integer)AdfFacesContext.getCurrentInstance().getPageFlowScope().get("Blk1TmpPrfNewcodVOIteratorRangeStart");
           System.out.println("RangeStarts Value === "+Value);
               
        
        }

   

    public String firstButton() {
       
    DCBindingContainer binding = (DCBindingContainer) BindingContext.getCurrent().getCurrentBindingsEntry();
                     DCIteratorBinding masterIter2 = binding.findIteratorBinding(masterIteratorName);
                      //RowSetIterator currentRow = masterIter2.getRowSetIterator();
                  ViewObject vo =    masterIter2.getViewObject();
              Row currentRow =    vo.first();
       String rno = currentRow.getAttribute("MppfcRunnum").toString();
       System.out.println("FIrst : "+rno);
           
        vo.setCurrentRow(currentRow);
        vo.executeQuery();
        
                
                   if(null!=vo)
                   {
                         
//                          masterIter2.setCurrentRowIndexInRange(1);
//                          masterIter2.refreshIfNeeded();
                          AdfFacesContext.getCurrentInstance().addPartialTarget(table1);
                             table1.resetStampState();
                         // refreshPage();
                          
                          return "supplierView";
                      }
      
        return null;
    }
  /*  public void refreshPage() 
    {
          FacesContext fc = FacesContext.getCurrentInstance();
          String refreshpage = fc.getViewRoot().getViewId();
          ViewHandler ViewH =fc.getApplication().getViewHandler();
          UIViewRoot UIV = ViewH.createView(fc,refreshpage);
          UIV.setViewId(refreshpage);
          fc.setViewRoot(UIV);
    } */
    
    public void refreshPage()
    {
     FacesContext fc = FacesContext.getCurrentInstance();
    String refreshpage = fc.getViewRoot().getViewId();
     ViewHandler viewH =  fc.getApplication().getViewHandler();
       UIViewRoot UIV =  viewH.createView(fc, refreshpage);
       UIV.setViewId(refreshpage);
       fc.setViewRoot(UIV);
    }

   
    public void conVCL(ValueChangeEvent valueChangeEvent)
    {
        Boolean selectedValue = (Boolean)valueChangeEvent.getNewValue();
        System.out.println("Selected Value : "+selectedValue);
        
        //JSFUtils.setExpressionValue("#{row.bindings.MptmChkbox.inputValue}", selectedValue);
        DCBindingContainer binding = (DCBindingContainer) BindingContext.getCurrent().getCurrentBindingsEntry();
                         DCIteratorBinding iterator = binding.findIteratorBinding(consumableTableName);
                         
                      ViewObject vo =    iterator.getViewObject();
                      Row r= null;
                      int i = 1;
                    
                      while(vo.hasNext())
                      {
                          
                              if(i == 1)
                              {
                                  r = vo.first();
                                  r.setAttribute("MptmChkbox",false);
                              
                              }
                          
                              else
                              {   
                                  r =  vo.next();
                                  r.setAttribute("MptmChkbox",false);
                              }
                          i++;
                         
                      }
        
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

    public void setResetConsub(RichSelectBooleanCheckbox resetConsub) {
        this.resetConsub = resetConsub;
    }

    public RichSelectBooleanCheckbox getResetConsub() {
        return resetConsub;
    }
    public void abc(){
        getTable1();
    }
   
}


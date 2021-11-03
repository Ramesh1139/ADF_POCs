package com.nordstrom.rpm.Backing;

import java.io.IOException;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.http.HttpSession;

import oracle.adf.model.BindingContext;
import oracle.adf.model.OperationBinding;
import oracle.adf.share.ADFContext;
import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.context.AdfFacesContext;

import oracle.adf.view.rich.event.DialogEvent;

import org.apache.myfaces.trinidad.component.UIXSwitcher;

public class HomeMB {
    private String facetName;
    private UIXSwitcher bswticher;
    FacesContext ctx = FacesContext.getCurrentInstance();
    ExternalContext ectx = FacesContext.getCurrentInstance().getExternalContext();
    HttpSession userSession = (HttpSession)ectx.getSession(true);
    private RichPopup fetchPopUp;

    public HomeMB() {
        super();
    }

    public void setFacetName(String facetName) {
        this.facetName = facetName;
    }

    public String getFacetName() {
        return facetName;
    }
   
    public void setBswticher(UIXSwitcher bswticher) {
        this.bswticher = bswticher;
    }

    public UIXSwitcher getBswticher() {
        return bswticher;
    }
    
    public String currentFacet(){
        if (getFacetName()!=null && userSession.getAttribute("setPopupEvent")==null){            
            bswticher.setFacetName(getFacetName());
            AdfFacesContext.getCurrentInstance().addPartialTarget(bswticher);             
        }        
        return null;
    }

    public void RPM_Logout(ActionEvent actionEvent) {
        // Add event code here...
        try{
            ExternalContext ectx = FacesContext.getCurrentInstance().getExternalContext();
            HttpServletResponse response = (HttpServletResponse)ectx.getResponse();
            HttpSession session = (HttpSession)ectx.getSession(false);
            if(session.getAttribute("roleId").equals("80")){
                OperationBinding userToBeInvalidated = (OperationBinding)BindingContext.getCurrent().getCurrentBindingsEntry().getOperationBinding("deleteDeptAccessOnLogout");                
                userToBeInvalidated.getParamsMap().put("deptAccessIterator", "Clr_Wksht_Dept_AccessView1Iterator");
                userToBeInvalidated.execute();
            }
            session.invalidate();            
            response.sendRedirect("Login.jspx");
            //return null;
        }
        catch(IOException io){
            io.printStackTrace();
        }
    }

    public void cancelParameterListener(DialogEvent dialogEvent) {
        // Add event code here...
        if(dialogEvent.getOutcome() == DialogEvent.Outcome.ok){                                                        
            setFacetName("Candidate_Rule_facet");
            userSession.removeAttribute("setPopupEvent");
            currentFacet();           
        }        
    }
    
    public String defaultHomePage(){
        if(userSession.getAttribute("roleId").equals("90"))            
            currentFacet();
        
        else            
            currentFacet();
        
        return null;
    }

    public void setFetchPopUp(RichPopup fetchPopUp) {
        this.fetchPopUp = fetchPopUp;
    }

    public RichPopup getFetchPopUp() {
        return fetchPopUp;
    }

    public void showfetchPopUp(ActionEvent actionEvent) {
        // Add event code here...
        FacesContext ctx = FacesContext.getCurrentInstance();
        ExternalContext ectx = FacesContext.getCurrentInstance().getExternalContext();
        HttpServletRequest request = (HttpServletRequest)ctx.getExternalContext().getRequest();
        HttpSession userSession = (HttpSession)ectx.getSession(true);        
        if(userSession.getAttribute("setPopupEvent")!=null) {           
                RichPopup.PopupHints hints = new RichPopup.PopupHints();
                this.getFetchPopUp().show(hints);
        }   
        AdfFacesContext.getCurrentInstance().addPartialTarget(getFetchPopUp());   
        OperationBinding oBinding = (OperationBinding)BindingContext.getCurrent().getCurrentBindingsEntry().getOperationBinding("rollbackOrRevertRowChanges");            
        oBinding.execute();
    }

    public void candidateRuleLinkActionListener(ActionEvent actionEvent) {
        // Add event code here...
        OperationBinding oBinding = (OperationBinding)BindingContext.getCurrent().getCurrentBindingsEntry().getOperationBinding("rollbackOrRevertRowChanges");            
        oBinding.execute();
    }
}

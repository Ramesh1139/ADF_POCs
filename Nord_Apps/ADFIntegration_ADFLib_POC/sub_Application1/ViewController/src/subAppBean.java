import java.io.IOException;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import oracle.adf.controller.ControllerContext;

public class subAppBean {
    public subAppBean() {
    }

    public String page1() {
        // Add event code here...
        return "go";
    }
    public String page2() {
        // Add event code here...
        return "back";
    }

    public String logOut() {
        // Add event code here...
        FacesContext fctx = FacesContext.getCurrentInstance();
           ExternalContext ectx = fctx.getExternalContext();
         
           String viewId = "/faces/MainPage.jspx";
           ControllerContext controllerCtx = null;
           controllerCtx = ControllerContext.getInstance();
           String activityURL = controllerCtx.getGlobalViewActivityURL(viewId);
           try{
             ectx.redirect(activityURL);
           } catch (IOException e) {
             //Can't redirect
             e.printStackTrace();
           }
        
        return null;
      
    }
}

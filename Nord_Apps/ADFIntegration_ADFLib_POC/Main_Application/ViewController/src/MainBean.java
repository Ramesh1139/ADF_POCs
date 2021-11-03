import java.io.IOException;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import oracle.adf.controller.ControllerContext;

public class MainBean {
    public MainBean() {
    }

    public String mainGoAction() {
        // Add event code here...
        
            FacesContext fctx = FacesContext.getCurrentInstance();
               ExternalContext ectx = fctx.getExternalContext();
             
               String viewId = "/faces/subMainPage.jspx";
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

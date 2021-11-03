import java.io.IOException;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import oracle.adf.controller.ControllerContext;

public class RPMBean {
    public RPMBean() {
    }

    public void logOut_ActionListener(ActionEvent actionEvent) {
        // Add event code here...
        
        if(null!=actionEvent) 
        {
            FacesContext fctx = FacesContext.getCurrentInstance();
               ExternalContext ectx = fctx.getExternalContext();
             
               String viewId = "/faces/Pages/Login.jspx";
               ControllerContext controllerCtx = null;
               controllerCtx = ControllerContext.getInstance();
               String activityURL = controllerCtx.getGlobalViewActivityURL(viewId);
               try{
                 ectx.redirect(activityURL);
               } catch (IOException e) {
                 //Can't redirect
                 e.printStackTrace();
               }
        }
    }
}

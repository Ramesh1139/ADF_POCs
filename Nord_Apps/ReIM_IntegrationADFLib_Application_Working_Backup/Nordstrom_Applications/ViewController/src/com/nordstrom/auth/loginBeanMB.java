package com.nordstrom.auth;

import com.nordstrom.auth.LoginService;
import com.nordstrom.auth.LoginServiceFactory;
import com.nordstrom.auth.UserBean;

import com.nordstrom.utils.FacesMessageUtils;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;


import javax.faces.event.ValueChangeEvent;

import javax.naming.directory.DirContext;

import javax.servlet.http.HttpServletRequest;


import javax.servlet.http.HttpServletResponse;

import javax.servlet.http.HttpSession;

import oracle.adf.model.BindingContext;
import oracle.adf.model.binding.DCBindingContainer;
import oracle.adf.model.binding.DCIteratorBinding;
import oracle.adf.share.ADFContext;
import oracle.adf.view.rich.component.rich.input.RichInputText;
import oracle.adf.view.rich.component.rich.input.RichSelectOneChoice;


public class loginBeanMB {

    private String _username, _password;
    private String selected_application = "RPM";
    private boolean isRPM = false;
    private boolean isReIM = false;
    private boolean isSIM = false;
    private String loginContextPath;
    private String navigateURL;


    public loginBeanMB() {
        super();
    }

    public void setUsername(String _username) {
        this._username = _username;
    }

    public String getUsername() {
        return _username;
    }

    public void setPassword(String _password) {
        this._password = _password;
    }

    public String getPassword() {
        return _password;
    }

    public String doLogin() {
        FacesContext ctx = FacesContext.getCurrentInstance();
        HttpServletRequest request =
            (HttpServletRequest)ctx.getExternalContext().getRequest();
        ExternalContext ectx =
            FacesContext.getCurrentInstance().getExternalContext();
        ADFContext.getCurrent().getPageFlowScope().put("action", "login");
        boolean connectDB = false;

        UserBean user =
            (UserBean)ADFContext.getCurrent().getRequestScope().get("UserBean");

        LoginService loginservice =
            LoginServiceFactory.getLoginInstance(getSelected_application(),
                                                 user);
        connectDB =
                loginservice.authenticateUserWithLDAP(user.getUserid(), user.getPassword().toCharArray());

        if (connectDB) {
            try {

                HttpServletResponse response =
                    (HttpServletResponse)ctx.getExternalContext().getResponse();
                HttpSession loggedInSession =
                    (HttpSession)ectx.getSession(true);
                request.setAttribute("user", user.getUserid());

                loggedInSession.setAttribute("userName",
                                             request.getAttribute("user"));
                loggedInSession.setAttribute("RoleId",
                                             request.getAttribute("RoleId"));
                loggedInSession.setAttribute("serverName",
                                             request.getServerName());
                loggedInSession.setAttribute("serverPort",
                                             request.getServerPort());

                if (selected_application.equals("RPM"))
                    isRPM = loginservice.autherizeUser_RPM();
                else if (selected_application.equals("ReIM"))
                    isReIM = loginservice.autherizeUser_ReIM();

                if (isRPM) {

                    FacesContext fctx = FacesContext.getCurrentInstance();  
                        ExternalContext ectx1 = fctx.getExternalContext();  
                    loginContextPath = ectx1.getRequestContextPath();
                    
                    if(loginContextPath.endsWith("/nrd_apps"))
                        navigateURL = "/faces/Pages/RPM_Home.jspx";
                    else if(loginContextPath.endsWith("/ReIM_Upload"))
                        navigateURL = "/faces/Pages/Home.jspx";
                    

                    response.sendRedirect(ctx.getExternalContext().getRequestContextPath() +
                                          navigateURL);
                    ctx.responseComplete();
                } else if (isReIM) {
                
                
                    FacesContext fctx = FacesContext.getCurrentInstance();  
                        ExternalContext ectx1 = fctx.getExternalContext();  
                    loginContextPath = ectx1.getRequestContextPath();
                    
                    if(loginContextPath.endsWith("/nrd_apps"))
                        navigateURL = "/faces/Pages/ReIM_Home.jspx";
                    else if(loginContextPath.endsWith("/ReIM_Upload"))
                        navigateURL = "/faces/Pages/Home.jspx";
                    

                    response.sendRedirect(ctx.getExternalContext().getRequestContextPath() +
                                          navigateURL);
                    ctx.responseComplete();
                }

                else {
                    FacesMessageUtils.addErrorMessage("Not an authorised user!",
                                                      FacesMessage.SEVERITY_ERROR);
                }
            } catch (Exception ae) {
                ae.printStackTrace();
                FacesMessageUtils.addErrorMessage("Not an authorised user!",
                                                  FacesMessage.SEVERITY_ERROR);
            }
        }
        return null;
    }

    public void applicationType_VCL(ValueChangeEvent valueChangeEvent) {
        // Add event code here...

        if (null != valueChangeEvent.getNewValue()) {
            selected_application = valueChangeEvent.getNewValue().toString();
            System.out.println("selected Application ::" +
                               selected_application);
            setSelected_application(selected_application);
        }
    }

    public void setSelected_application(String selected_application) {
        this.selected_application = selected_application;
    }

    public String getSelected_application() {
        return selected_application;
    }

    public void setIsRPM(boolean isRPM) {
        this.isRPM = isRPM;
    }

    public boolean isIsRPM() {
        return isRPM;
    }

    public void setIsReIM(boolean isReIM) {
        this.isReIM = isReIM;
    }

    public boolean isIsReIM() {
        return isReIM;
    }

    public void setIsSIM(boolean isSIM) {
        this.isSIM = isSIM;
    }

    public boolean isIsSIM() {
        return isSIM;
    }
}

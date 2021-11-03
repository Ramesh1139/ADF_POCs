package com.nordstrom.rpm;

import com.nordstrom.auth.LoginService;
import com.nordstrom.auth.LoginServiceFactory;
import com.nordstrom.auth.UserBean;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;


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
    //private UserBean users;    

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
        HttpServletRequest request = (HttpServletRequest)ctx.getExternalContext().getRequest();
        ExternalContext ectx = FacesContext.getCurrentInstance().getExternalContext();               
        ADFContext.getCurrent().getPageFlowScope().put("action", "login");
        boolean connectDB = false;       
        //LoginServiceFactory loginService = (LoginServiceFactory)ADFContext.getCurrent().getRequestScope().get("LoginServiceFactory");
        UserBean user = (UserBean)ADFContext.getCurrent().getRequestScope().get("UserBean");
        
        LoginService loginservice = LoginServiceFactory.getLoginInstance(user.getApplicationSelection().getValue(), user);        
        connectDB = loginservice.authenticateUserWithLDAP(user.getUserid(), user.getPassword().toCharArray());
        //if(authenticationValid.equals("validUser")){
        if(connectDB){
            try {
                
                HttpServletResponse response = (HttpServletResponse)ctx.getExternalContext().getResponse();                                                                              
                HttpSession loggedInSession = (HttpSession)ectx.getSession(true);
                request.setAttribute("user", user.getUserid());
                //System.out.println("role Id ==="+request.getAttribute("RoleId"));
                loggedInSession.setAttribute("userName", request.getAttribute("user"));
                loggedInSession.setAttribute("RoleId", request.getAttribute("RoleId"));
                loggedInSession.setAttribute("serverName", request.getServerName());
                loggedInSession.setAttribute("serverPort", request.getServerPort());
                if(loginservice.autherizeUser()){    
                    String loginUrl = "/faces/Home_Page.jspx";
                         
                    response.sendRedirect(ctx.getExternalContext().getRequestContextPath() +
                                  loginUrl);
                    ctx.responseComplete();
                }
                else{
                    FacesMessageUtils.addErrorMessage("Not an authorised user.",
                                                      FacesMessage.SEVERITY_ERROR);
                }
            }
            catch(Exception ae){
                ae.printStackTrace();
                FacesMessageUtils.addErrorMessage("Not an authorised user.",
                                                  FacesMessage.SEVERITY_ERROR);               
            }
        }
        return null;
    }
}

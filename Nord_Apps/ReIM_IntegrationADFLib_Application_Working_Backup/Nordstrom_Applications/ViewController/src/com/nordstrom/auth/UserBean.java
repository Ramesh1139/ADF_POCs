package com.nordstrom.auth;

import java.util.ArrayList;

import oracle.adf.share.ADFContext;
import oracle.adf.view.rich.component.rich.input.RichInputText;
import oracle.adf.view.rich.component.rich.input.RichSelectOneChoice;

public class UserBean {

    String userid;
    String password;
    private RichInputText userName;
    private RichInputText upassword;
    private RichSelectOneChoice applicationSelection =
        new RichSelectOneChoice();
    private ArrayList availableApplication = new ArrayList();
    String action =
        ADFContext.getCurrent().getPageFlowScope().get("action") + "";
    /* public UserBean(String userid, String password) {
        super();
        this.userid = userid;
        this.password = password;
    }*/

    public UserBean() {
        super();
        if (action.equals("null"))
            getApplicationSelection().setValue(getAvailableApplication().get(0));
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getUserid() {
        return userid;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setUserName(RichInputText userName) {
        this.userName = userName;
    }

    public RichInputText getUserName() {
        return userName;
    }

    public void setUpassword(RichInputText upassword) {
        this.upassword = upassword;
    }

    public RichInputText getUpassword() {
        return upassword;
    }

    public void setApplicationSelection(RichSelectOneChoice applicationSelection) {
        this.applicationSelection = applicationSelection;
    }

    public RichSelectOneChoice getApplicationSelection() {
        return applicationSelection;
    }

    public void setAvailableApplication(ArrayList availableApplication) {
        this.availableApplication = availableApplication;
    }

    public ArrayList getAvailableApplication() {
        availableApplication.add("RPM");
        availableApplication.add("ReIM");
        availableApplication.add("SIM");
        availableApplication.add("RMS");
        return availableApplication;
    }
}

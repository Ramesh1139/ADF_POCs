package com.nordstrom.auth;

import javax.naming.directory.DirContext;

public abstract class LoginService {
    
    private String appType = null;
    private UserBean user = null;
    
    public LoginService(String appType, UserBean user ) {
           this.appType = appType;
           this.user= user;
       }
    public abstract DirContext connectLDAP();
    public abstract boolean connectLoginService();
    public abstract String authenticateUser(DirContext dirCtx,UserBean user);
    public abstract boolean autherizeUser();
    public abstract boolean authenticateUserWithLDAP(String username, char[] password);
    

    public void setAppType(String appType) {
        this.appType = appType;
    }

    public String getAppType() {
        return appType;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }

    public UserBean getUser() {
        return user;
    }
}

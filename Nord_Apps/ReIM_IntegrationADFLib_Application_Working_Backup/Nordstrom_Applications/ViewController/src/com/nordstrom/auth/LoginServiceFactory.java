package com.nordstrom.auth;

public class LoginServiceFactory {


    public static LoginService getLoginInstance(String apptype,
                                                UserBean user) {
        LoginService loginService = null;
        String appType = apptype;

        char app = 'N';
        if (appType.equals("RPM"))
            app = 'R';
        else if (appType.equals("SIM"))
            app = 'S';
        else if (appType.equals("ReIM"))
            app = 'I';
        else if (appType.equals("RMS"))
            app = 'M';
        switch (app) {
        case 'R':
            loginService =
                    (LoginService)new RPMLoginServiceImpl(apptype, user);
            break;

        case 'S':
            loginService =
                    (LoginService)new RPMLoginServiceImpl(apptype, user);
            break;

        case 'I':
            loginService =
                    (LoginService)new RPMLoginServiceImpl(apptype, user);
            break;

        case 'M':
            loginService =
                    (LoginService)new RPMLoginServiceImpl(apptype, user);
            break;

        default:

            break;
        }
        return loginService;
    }
}

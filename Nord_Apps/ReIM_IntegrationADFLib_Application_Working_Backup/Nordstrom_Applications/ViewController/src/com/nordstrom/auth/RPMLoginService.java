package com.nordstrom.auth;

import javax.naming.directory.DirContext;

public interface RPMLoginService {
    public DirContext connectLDAP();

    public boolean connectLoginService();

    public String authenticateUser(DirContext dirCtx, UserBean user);

    public boolean autherizeUser_RPM();

    public boolean autherizeUser_ReIM();

    public boolean authenticateUserWithLDAP(String username, char[] password);
}

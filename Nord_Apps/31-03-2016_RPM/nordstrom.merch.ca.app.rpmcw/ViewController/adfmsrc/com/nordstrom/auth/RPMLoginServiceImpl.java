package com.nordstrom.auth;

import com.nordstrom.rpm.FacesMessageUtils;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Hashtable;
import java.util.Properties;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.naming.directory.SearchResult;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathException;
import javax.xml.xpath.XPathFactory;
import oracle.adf.model.BindingContext;
import oracle.adf.share.ADFContext;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.SearchControls;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import oracle.adf.model.binding.DCBindingContainer;
import oracle.adf.model.binding.DCIteratorBinding;
import oracle.jbo.Row;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class RPMLoginServiceImpl extends LoginService implements RPMLoginService {

    public final String LDAP_DN = "OU=Users,OU=Accounts,DC=nordstrom,DC=net";
    RPMLoginServiceImpl(Object appType,UserBean user) {
            super(appType.toString(), user);
        }

    public DirContext connectLDAP(){
        Hashtable env = new Hashtable();
        boolean connect = false;       
        Properties ldapDetails = LDAPDetails();
                
        env.put(Context.INITIAL_CONTEXT_FACTORY, ldapDetails.get(Context.INITIAL_CONTEXT_FACTORY));
        env.put(Context.PROVIDER_URL, ldapDetails.get(Context.PROVIDER_URL));
        env.put(Context.SECURITY_AUTHENTICATION, ldapDetails.get(Context.SECURITY_AUTHENTICATION));
        env.put(Context.SECURITY_PRINCIPAL, ldapDetails.get(Context.SECURITY_PRINCIPAL).toString());
        env.put(Context.SECURITY_CREDENTIALS, ldapDetails.get(Context.SECURITY_CREDENTIALS));
                
        DirContext ctx = null;       
        try {
            ctx = new InitialDirContext(env);
            } catch (Throwable e) {
                e.printStackTrace();
                FacesMessageUtils.addErrorMessage("Authentication of user failed!",FacesMessage.SEVERITY_ERROR);
            } 
            if(ctx!=null)
                connect = true;                   
        return ctx;
    }
    
    public boolean authenticateUserWithLDAP(String username, char[] password) {            
            boolean hasFoundUser = false;
            String cn = null;                                
            Properties ldapDetails = LDAPDetails();
            //String nordAd = ldapDetails.get("java.naming.security.principal2").toString(); 
            Hashtable<String, String> env = new Hashtable<String, String>();
            env.put(Context.INITIAL_CONTEXT_FACTORY, ldapDetails.get(Context.INITIAL_CONTEXT_FACTORY).toString());
            env.put(Context.PROVIDER_URL, ldapDetails.get(Context.PROVIDER_URL).toString());
            env.put(Context.SECURITY_AUTHENTICATION, ldapDetails.get(Context.SECURITY_AUTHENTICATION).toString());
            env.put(Context.SECURITY_PRINCIPAL, ldapDetails.get(Context.SECURITY_PRINCIPAL).toString().toString());
            env.put(Context.SECURITY_CREDENTIALS, ldapDetails.get(Context.SECURITY_CREDENTIALS).toString());
            DirContext ctx = null;
            NamingEnumeration results = null;
            try {
                ctx = new InitialDirContext(env);
                SearchControls controls = new SearchControls();
                controls.setSearchScope(SearchControls.SUBTREE_SCOPE);
                results = ctx.search("", "(sAMAccountName=" + username + ")", controls);
                while (results.hasMore()) {
                    SearchResult searchResult = (SearchResult) results.next();
                    Attributes attributes = searchResult.getAttributes();
                    //System.out.println(" Person Common Name = " + attributes.get("cn"));
                    //System.out.println(" Person Display Name = " + attributes.get("displayName"));                
                    //System.out.println(" Person MemberOf = " + attributes.get("memberOf"));        
                    NamingEnumeration namingEnumeration = attributes.get("memberof").getAll();
                    while (namingEnumeration.hasMoreElements()) {
                        //String matchedAd = (String) namingEnumeration.nextElement();                    
                        hasFoundUser = true;                        
                        break;                        
                    }
                    Attribute attr = attributes.get("cn");
                    cn = (String) attr.get();
                    //System.out.println("cn is  "+cn);
                }
            } 
            catch (Throwable ex) {
            ex.printStackTrace();                       
        } finally {
            if (results != null) {
                try {
                    results.close();
                } catch (Exception ae) {
                    ae.printStackTrace();
                }
            }
            if (ctx != null) {
                try {
                    ctx.close();                    
                } catch (Exception be) {
                    be.printStackTrace();
                }
            }
        }
        if (hasFoundUser==false) {
            FacesMessageUtils.addErrorMessage("Authentication of user failed!",FacesMessage.SEVERITY_ERROR);
            return hasFoundUser;          
        }
        // try to match the provided user and password
        try {
            env.put(Context.SECURITY_PRINCIPAL, cn);
            env.put(Context.SECURITY_CREDENTIALS, String.valueOf(password));
            ctx = new InitialDirContext(env);
            //System.out.println("ctx is  "+ctx.getNameInNamespace());            
        } catch (Throwable e) {
            e.printStackTrace();
            FacesMessageUtils.addErrorMessage("Invalid Username or Password",
                                              FacesMessage.SEVERITY_ERROR);
            hasFoundUser = false;
        } finally {
            if (results != null) {
                try {
                    results.close();
                } catch (Exception ae) {
                    ae.printStackTrace();
                }
            }
            if (ctx != null) {
                try {
                    ctx.close();                    
                } catch (Exception be) {
                    be.printStackTrace();
                }
            }
        }
        return hasFoundUser;
        }
    private Properties LDAPDetails(){        
        Properties ldapDetails = new Properties();
        try{        
        File fXmlFile = new File((FacesContext.getCurrentInstance().getExternalContext().getResource("/WEB-INF/security.xml")).toURI());                
        Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(fXmlFile);
        document.getDocumentElement().normalize();
        NodeList nList = document.getElementsByTagName("applicationType");
                
       for (int temp = 0; temp < nList.getLength(); temp++) {
               Node nNode = nList.item(temp);                                                      
               if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                       Element eElement = (Element) nNode;                       
                       if(eElement.getAttribute("name").equalsIgnoreCase("RPM")){                                  
                           ldapDetails.put("java.naming.factory.initial", eElement.getElementsByTagName("java.naming.factory.initial").item(0).getTextContent());                                   
                           ldapDetails.put("java.naming.provider.url", eElement.getElementsByTagName("java.naming.provider.url").item(0).getTextContent());                                   
                           ldapDetails.put("java.naming.security.authentication", eElement.getElementsByTagName("java.naming.security.authentication").item(0).getTextContent());                                   
                           ldapDetails.put("java.naming.security.principal", eElement.getElementsByTagName("java.naming.security.principal").item(0).getTextContent());
                           ldapDetails.put("java.naming.security.principal2", eElement.getElementsByTagName("java.naming.security.principal2").item(0).getTextContent());
                           ldapDetails.put("java.naming.security.credentials", eElement.getElementsByTagName("java.naming.security.credentials").item(0).getTextContent());                           
                           break;
                       }
               }
       }
            
        }
        catch(SAXException sa){
            sa.printStackTrace();
        }
        catch(ParserConfigurationException parse){
            parse.printStackTrace();
        }
        catch(IOException io){
         io.printStackTrace();   
        }
        catch(URISyntaxException ue){
            ue.printStackTrace();
        }
        return ldapDetails;
    }
    public boolean connectLoginService(){
        boolean connected = false;
        try{
            Connection conn = null;
            Properties connectionCredentials = new Properties();                        
            File fXmlFile = new File("D:\\nordstrom.merch.ca.app.rpmcw\\ViewController\\public_html\\WEB-INF\\security.xml");
            //Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new InputSource(input));
            Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(fXmlFile);
            XPath xpath = XPathFactory.newInstance().newXPath();
            String url = (String) xpath.compile("//config//jdbc//url").evaluate(document, XPathConstants.STRING);            
            String username = (String) xpath.compile("//config//jdbc//username").evaluate(document, XPathConstants.STRING);
            String password = (String) xpath.compile("//config//jdbc//password").evaluate(document, XPathConstants.STRING);
            connectionCredentials.put("user", username);
            connectionCredentials.put("password",password);            
            conn = DriverManager.getConnection(url, connectionCredentials);
            connected = conn.isValid(10);
            System.out.println("db metadata ===="+ conn.getMetaData());    
            /*tnsnames file location*/
            //C:\oraclexe\app\oracle\product\10.2.0\server\NETWORK\ADMIN
        }
        catch(SAXException sa){
            sa.printStackTrace();
        }
        catch(ParserConfigurationException parse){
            parse.printStackTrace();
        }
        catch(XPathException xPath){
            xPath.printStackTrace();
        }
        catch(IOException io){
         io.printStackTrace();   
        }
        catch(SQLException sql){
         sql.printStackTrace();   
        }
       return connected;
    }    

    @SuppressWarnings({ "oracle.jdeveloper.java.constructor-deprecated", "oracle.jdeveloper.java.semantic-warning" })
    public String authenticateUser(DirContext ctx, UserBean user) {         
      
        String error = "";
        Hashtable env2 = new Hashtable();
        ADFContext.getCurrent().getPageFlowScope().put("LoginUsername",user.getUserid());
        String loggedInUser = (String)ADFContext.getCurrent().getPageFlowScope().get("LoginUsername");
        NamingEnumeration results = null;
        DirContext ct2x = null;
        try {                    
            SearchControls controls = new SearchControls();
            controls.setSearchScope(SearchControls.SUBTREE_SCOPE);
            results = ctx.search("", "(sAMAccountName="+ loggedInUser +")", controls);
            while (results.hasMore()) {
                SearchResult searchResult = (SearchResult) results.next();
                Attributes attributes = searchResult.getAttributes();
                Attribute attr = attributes.get("cn");
                String cn = (String) attr.get();
                //System.out.println(" Person Common Name = " + attributes.get("cn"));
                //System.out.println(" Person Display Name = " + attributes.get("displayName"));                
                //System.out.println(" Person MemberOf = " + attributes.get("memberOf"));               
                Properties ldapDetails = LDAPDetails();                
                env2.put(Context.INITIAL_CONTEXT_FACTORY, ldapDetails.get(Context.INITIAL_CONTEXT_FACTORY));                   
                env2.put(Context.PROVIDER_URL, ldapDetails.get(Context.PROVIDER_URL));                            
                env2.put(Context.SECURITY_PRINCIPAL, cn);
                env2.put(Context.SECURITY_CREDENTIALS, user.getPassword());
                ct2x = new InitialDirContext(env2);
                //System.out.println("ct2x value ==="+ct2x);
            }
        } catch (Throwable e) {
            e.printStackTrace();
            FacesMessageUtils.addErrorMessage("Invalid Username or Password",
                                              FacesMessage.SEVERITY_ERROR);            
        } finally {
            if (results != null) {
                try {
                    results.close();
                } catch (Exception ae) {
                    ae.printStackTrace();
                }
            }
            if (ct2x != null) {
                try {
                    ctx.close();
                    error = "validUser";
                } catch (Exception be) {
                    be.printStackTrace();
                }
            }
        }          
        return error;
    }

    public boolean autherizeUser() {
        boolean validUser = false;        
        FacesContext ctx = FacesContext.getCurrentInstance();
        ExternalContext ectx = FacesContext.getCurrentInstance().getExternalContext();
        HttpServletRequest request = (HttpServletRequest)ctx.getExternalContext().getRequest();
        HttpSession userSession = (HttpSession)ectx.getSession(true);
        UserBean user = (UserBean)ADFContext.getCurrent().getRequestScope().get("UserBean");
        //OperationBinding ob =BindingContext.getCurrent().getCurrentBindingsEntry().getOperationBinding("getAllValidUsers");
        DCBindingContainer binding = (DCBindingContainer)BindingContext.getCurrent().getCurrentBindingsEntry();
        DCIteratorBinding userRolesBinding = binding.findIteratorBinding("Clr_Wksht_User_RoleView1Iterator");
        DCIteratorBinding userPermission = binding.findIteratorBinding("Clr_Wksht_Role_Named_PermissionIterator");
        userRolesBinding.executeQuery();
        userRolesBinding.setRefreshed(true);
               
        Row[] userRoleValues = userRolesBinding.getAllRowsInRange();
        Row[] userPermitValue = userPermission.getAllRowsInRange();
        for(Row users:userRoleValues){            
            if(user.getUserid().equalsIgnoreCase(users.getAttribute("UserId").toString())){
                for(Row userPermit:userPermitValue){				
                    if(users.getAttribute("RoleId").equals(userPermit.getAttribute("RoleId"))) {                        
                        if(getAppType().equals("app.rpm.clr_wksht")){
                            request.setAttribute("roleId", users.getAttribute("RoleId"));
                            userSession.setAttribute("roleId", request.getAttribute("roleId"));                            
                            userSession.setAttribute("userViewPermission", userPermit.getAttribute("IsView"));
                            userSession.setAttribute("userEditPermission", userPermit.getAttribute("IsEdit"));
                            userSession.setAttribute("userSubmitPermission", userPermit.getAttribute("IsSubmit"));
                            userSession.setAttribute("userApprovePermission", userPermit.getAttribute("IsApprove"));
                            validUser = true;
                        }
                    }
                }
            }
        }        
        return validUser;
    }
}

package com.nordstrom.utils;

import java.text.MessageFormat;

import java.util.Locale;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;


public class FacesMessageUtils {


    private static final String RESOURCE_BUNDLE = "errorMessages";
    private static final String PRESENTATION_RESOURCE_BUNDLE =
        "presentationMessages";
    public static final String DEVELOPMENT_ERROR = "development.error";
    public static final String REPORT_ERROR = "report.error";

    /**
     *
     * @param errorMessageKey
     * @param severity
     */
    public static void addErrorMessageByCode(String errorMessageKey,
                                             Severity severity) {

        FacesContext facesContext = FacesContext.getCurrentInstance();
        PropertyResourceBundle bundle =
            (PropertyResourceBundle)ResourceBundle.getBundle(RESOURCE_BUNDLE,
                                                             facesContext.getExternalContext().getRequestLocale(),
                                                             Thread.currentThread().getContextClassLoader());
        String errorMessage = bundle.getString(errorMessageKey);
        facesContext.addMessage(null,
                                new FacesMessage(severity, errorMessage, errorMessage));
    }

    /**
     *
     * @param errorMessageKey
     * @param params
     * @param severity
     */
    public static void addErrorMessageByCode(String errorMessageKey,
                                             String[] params,
                                             Severity severity) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        PropertyResourceBundle bundle =
            (PropertyResourceBundle)ResourceBundle.getBundle(RESOURCE_BUNDLE,
                                                             facesContext.getExternalContext().getRequestLocale(),
                                                             Thread.currentThread().getContextClassLoader());
        if (params != null)
            for (int i = 0; i < params.length; i++)
                if (params[i].startsWith("res_"))
                    params[i] =
                            bundle.getString(params[i].substring(4, params[i].length()));
        String errorMessage =
            MessageFormat.format(bundle.getString(errorMessageKey),
                                 (Object[])params);
        facesContext.addMessage(null,
                                new FacesMessage(severity, errorMessage, errorMessage));
    }

    /**
     *
     * @param errorMessage
     * @param severity
     */
    public static void addErrorMessage(String errorMessage,
                                       Severity severity) {
        try {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            facesContext.addMessage(null,
                                    new FacesMessage(severity, errorMessage,
                                                     errorMessage));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
	 *
	 * @param exception
	 * @return
	 */
    //	public static BaseException localizeException(BaseException exception){
    //		try{
    //			FacesContext facesContext = FacesContext.getCurrentInstance();
    //			PropertyResourceBundle bundle = (PropertyResourceBundle) ResourceBundle.getBundle(RESOURCE_BUNDLE, facesContext
    //					.getExternalContext().getRequestLocale(), Thread
    //					.currentThread().getContextClassLoader());
    //			String errorMessage = bundle.getString(exception.getErrorCode());
    //			exception.setMessage(errorMessage);
    //			return exception;
    //		} catch (Exception e) {
    //			return null;
    //		}
    //	}
    /**
	 *
	 * @param exception
	 */
    //	public static void addExceptionMessage(BaseException exception){
    //		try{
    //            if (exception.getMessage()!= null){
    //                boolean flag= true;
    //                while(FacesContext.getCurrentInstance().getMessages().hasNext()){
    //                    if(FacesContext.getCurrentInstance().getMessages().next().equals(exception));{
    //                        flag=false;
    //                        break;
    //                    }
    //                }
    //                if(flag)
    //                    addErrorMessage(exception.getMessage(),FacesMessage.SEVERITY_ERROR);
    //            }
    //			else
    //				addErrorMessageByCode(exception.getErrorCode(), exception.getParams(),FacesMessage.SEVERITY_ERROR);
    //		} catch (Exception e) { }
    //	}

    /**
     * message to be retrieved from presentationMessages.properties
     * @param errorMessageKey
     * @param severity
     */
    public static void addMessageByCode(String errorMessageKey,
                                        Severity severity) {

        FacesContext facesContext = FacesContext.getCurrentInstance();
        PropertyResourceBundle bundle =
            (PropertyResourceBundle)ResourceBundle.getBundle(PRESENTATION_RESOURCE_BUNDLE,
                                                             facesContext.getExternalContext().getRequestLocale(),
                                                             Thread.currentThread().getContextClassLoader());
        String errorMessage = bundle.getString(errorMessageKey);
        facesContext.addMessage(null,
                                new FacesMessage(severity, errorMessage, errorMessage));
    }

    /**
     * To retrieve a message from resource bundle using a key
     * @param resourceKey
     */
    public static String getMessageByKey(String resourceKey) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        PropertyResourceBundle bundle =
            (PropertyResourceBundle)ResourceBundle.getBundle(PRESENTATION_RESOURCE_BUNDLE,
                                                             facesContext.getExternalContext().getRequestLocale(),
                                                             Thread.currentThread().getContextClassLoader());
        String message = bundle.getString(resourceKey);

        return message == null ? "" : message;
    }

    public static String getErrorMessageByKey(String errorMessageKey) {

        FacesContext facesContext = FacesContext.getCurrentInstance();
        PropertyResourceBundle bundle =
            (PropertyResourceBundle)ResourceBundle.getBundle(RESOURCE_BUNDLE,
                                                             facesContext.getExternalContext().getRequestLocale(),
                                                             Thread.currentThread().getContextClassLoader());
        return bundle.getString(errorMessageKey);
    }

    public static void clearErrorMessages() {

    }

    public static String getMessageByKeyForLocale(String resourceKey,
                                                  Locale locale) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        PropertyResourceBundle bundle =
            (PropertyResourceBundle)ResourceBundle.getBundle(PRESENTATION_RESOURCE_BUNDLE,
                                                             locale,
                                                             Thread.currentThread().getContextClassLoader());
        String message = bundle.getString(resourceKey);

        return message == null ? "" : message;
    }
}

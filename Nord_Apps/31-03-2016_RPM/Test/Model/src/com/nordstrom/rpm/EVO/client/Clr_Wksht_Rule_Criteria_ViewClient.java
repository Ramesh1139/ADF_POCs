package com.nordstrom.rpm.EVO.client;

import com.nordstrom.rpm.EVO.common.Clr_Wksht_Rule_Criteria_View;

import oracle.jbo.client.remote.ViewUsageImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Tue Dec 29 18:50:11 IST 2015
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class Clr_Wksht_Rule_Criteria_ViewClient extends ViewUsageImpl implements Clr_Wksht_Rule_Criteria_View {
    /**
     * This is the default constructor (do not remove).
     */
    public Clr_Wksht_Rule_Criteria_ViewClient() {
    }

    public void rollbackorrevertRowChanges() {
        Object _ret = getApplicationModuleProxy().riInvokeExportedMethod(this,"rollbackorrevertRowChanges",null,null);
        return;
    }
}

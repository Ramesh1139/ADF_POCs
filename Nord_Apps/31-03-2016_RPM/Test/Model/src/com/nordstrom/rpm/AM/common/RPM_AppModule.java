package com.nordstrom.rpm.AM.common;

import java.util.ArrayList;

import oracle.jbo.ApplicationModule;
import oracle.jbo.Row;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Mon Oct 05 11:52:37 IST 2015
// ---------------------------------------------------------------------
public interface RPM_AppModule extends ApplicationModule {
    void ftupdate(String Nexteffectivedate, String RackNewRetail,
                  String RackNewMdPersontage, String SeqId);


    void stupdate(String Nexteffectivedate, String RackNewRetail,
                  String RackNewMdPersontage, String SeqId);

    void storeHistory(String username, String name, String order);

    void insertNewParameterValue(int paramID, int deptID, int param_value,
                                 String userName);

    void updateExistingParameter(int param_id, int dept_id, int param_value,
                                 String userName);

    void storeHistoryst(String username, String name, String order);

    void getLoginUsercandidte(String loginUsername);

    void getLoginUserSavedtab(String loginUsername);


    void deleteRuleCriteria(String ruleids);


    void queryExecution();


    void MIAdminColumnName();


    Row[] filteredList(String departmentNames);


    void addRoleDefs(ArrayList list, ArrayList listDept, String userName);


    Row getParameterValues(int paramID, int deptID);

    void deleteParamValues(int param_id, int dept_id);


    void rpmAction(String SeqId, String updateId);

    void takeAction(String SeqId, String updateId);

    void untakeAction(String SeqId, String updateId);

    void newEffectiveDateInputBulkUpdate(String effectiveDate, String seqId,
                                         String updateId);

    void onNewRetailInputBulkUpdate(String effectiveDate, String newRetail,
                                    String newMdPercent, String seqId,
                                    String mdAmount, String updateId);

    void onNewMDPercentInputBulkUpdate(String effectiveDate, String newRetail,
                                       String newMdPercent, String seqId,
                                       String mdAmount, String updateId);
	
	void resetCandidateRule(String iteratorName);


    boolean userLevelDeptAccess(java.util.List deptList, String deptRecord, String userName, String deptAccessIterator);

    void refreshDepartmentsSearch(String userID, String deptAccessIterator);

    void deleteDeptAccessOnLogout(String userID, String deptAccessIterator);
}

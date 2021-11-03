package com.nordstrom.rpm.AM;

import com.nordstrom.rpm.AM.common.RPM_AppModule;
import com.nordstrom.rpm.EVO.Clr_Wksht_Rule_ViewImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import oracle.adf.model.BindingContext;
import oracle.adf.model.binding.DCBindingContainer;
import oracle.adf.model.binding.DCIteratorBinding;
import oracle.adf.share.ADFContext;

import oracle.jbo.Row;
import oracle.jbo.ViewObject;
import oracle.jbo.domain.Date;
import oracle.jbo.domain.Number;
import oracle.jbo.server.ApplicationModuleImpl;
import oracle.jbo.server.ViewObjectImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Mon Oct 05 11:13:21 IST 2015
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class RPM_AppModuleImpl extends ApplicationModuleImpl implements RPM_AppModule {
    /**
     * This is the default constructor (do not remove).
     */
    public RPM_AppModuleImpl() {
    }

    public void onNewRetailInputBulkUpdate(String effectiveDate, String newRetail, String newMdPercent, String seqId, String mdAmount, String updateId) {

        int exe = 0;

        if (effectiveDate != null && newRetail != null) {
            exe = executeCommand("update clr_wksht_rule_item set new_effective_date='" + effectiveDate + "',new_retail=" + newRetail + ",change_percent=" + newMdPercent + ",mkdn_amount=" + mdAmount + ",update_id = '" + updateId + "', update_datetime = sysdate where seq_no=" + seqId);

        } else if (effectiveDate == null && newRetail != null) {
            exe = executeCommand("update clr_wksht_rule_item set new_retail=" + newRetail + ",change_percent=" + newMdPercent + ",mkdn_amount=" + mdAmount + ",update_id = '" + updateId + "', update_datetime = sysdate where seq_no =" + seqId);

        }
        getDBTransaction().commit();

        this.getClr_Wksht_Rule_Item_View1().executeQuery();
        this.getTotals_Taken_View1().executeQuery();
        this.getCurrent_Selection_Saved_Takes_View1().executeQuery();
    }

    public void onNewMDPercentInputBulkUpdate(String effectiveDate, String newRetail, String newMdPercent, String seqId, String mdAmount, String updateId) {
        int exe = 0;
        if (effectiveDate != null && newMdPercent != null) {
            exe = executeCommand("update clr_wksht_rule_item set new_effective_date='" + effectiveDate + "',new_retail=" + newRetail + ",change_percent=" + newMdPercent + ",mkdn_amount=" + mdAmount + ",update_id = '" + updateId + "', update_datetime = sysdate where seq_no =" + seqId);
        } else if (effectiveDate == null && newMdPercent != null) {
            exe = executeCommand("update clr_wksht_rule_item set new_retail=" + newRetail + ",change_percent=" + newMdPercent + ",mkdn_amount=" + mdAmount + ",update_id = '" + updateId + "', update_datetime = sysdate where seq_no =" + seqId);
        }

        getDBTransaction().commit();

        this.getClr_Wksht_Rule_Item_View1().executeQuery();
        this.getTotals_Taken_View1().executeQuery();
        this.getCurrent_Selection_Saved_Takes_View1().executeQuery();
    }

    public void newEffectiveDateInputBulkUpdate(String effectiveDate, String seqId, String updateId) {
        int exe = 0;
        if (effectiveDate != null) {
            exe = executeCommand("update clr_wksht_rule_item set new_effective_date='" + effectiveDate + "',update_id = '" + updateId + "', update_datetime = sysdate where seq_no in (" + seqId + ")");

        }
        getDBTransaction().commit();
        this.getClr_Wksht_Rule_Item_View1().executeQuery();
    }

    public void ftupdate(String Nexteffectivedate, String RackNewRetail, String RackNewMdPersontage, String SeqId) {
        int exe = 0;
        if (Nexteffectivedate != null && RackNewRetail != null && RackNewMdPersontage != null) {
            exe = executeCommand("update clr_wksht_rule_item set new_effective_date='" + Nexteffectivedate + "',new_retail=" + RackNewRetail + ",change_percent=" + RackNewMdPersontage + " where seq_no in " + SeqId);
        } else if (Nexteffectivedate != null && RackNewRetail == null && RackNewMdPersontage == null) {
            exe = executeCommand("update clr_wksht_rule_item set new_effective_date='" + Nexteffectivedate + "' where seq_no in " + SeqId);
        } else if (RackNewRetail != null && Nexteffectivedate == null && RackNewMdPersontage == null) {
            exe = executeCommand("update clr_wksht_rule_item set new_retail=" + RackNewRetail + " where seq_no in " + SeqId);
        } else if (RackNewMdPersontage != null && Nexteffectivedate == null && RackNewRetail == null) {
            exe = executeCommand("update clr_wksht_rule_item set change_percent=" + RackNewMdPersontage + " where seq_no in " + SeqId);
        } else if (Nexteffectivedate != null && RackNewRetail != null) {
            exe = executeCommand("update clr_wksht_rule_item set new_effective_date='" + Nexteffectivedate + "',new_retail=" + RackNewRetail + " where seq_no in " + SeqId);
        } else if (RackNewRetail != null && RackNewMdPersontage != null) {
            exe = executeCommand("update clr_wksht_rule_item set new_retail=" + RackNewRetail + ",change_percent=" + RackNewMdPersontage + " where seq_no in " + SeqId);
        } else if (Nexteffectivedate != null && RackNewMdPersontage != null) {
            exe = executeCommand("update clr_wksht_rule_item set new_effective_date='" + Nexteffectivedate + "',change_percent=" + RackNewMdPersontage + " where seq_no in " + SeqId);
        } else if (Nexteffectivedate == null && RackNewRetail == null && RackNewMdPersontage == null) {
        }
        getDBTransaction().commit();

        this.getClr_Wksht_Rule_Item_View1().executeQuery();
        this.getTotals_Taken_View1().executeQuery();
        this.getCurrent_Selection_Saved_Takes_View1().executeQuery();


    }

    public void stupdate(String Nexteffectivedate, String RackNewRetail, String RackNewMdPersontage, String SeqId) {

        int exe = 0;
        String[] sequenceIDs;
        sequenceIDs = SeqId.replace("(", "").replace(")", "").split(", ");
        for (int i = 0; i < sequenceIDs.length; i++) {
            if (Nexteffectivedate != null && RackNewRetail != null && RackNewMdPersontage != null)
                exe = executeCommand("update clr_wksht_rule_item set new_effective_date='" + Nexteffectivedate + "',new_retail=" + RackNewRetail + ",change_percent=" + RackNewMdPersontage + " where seq_no=" + sequenceIDs[i]);
            else if (Nexteffectivedate != null && RackNewRetail == null && RackNewMdPersontage == null)
                exe = executeCommand("update clr_wksht_rule_item set new_effective_date='" + Nexteffectivedate + "' where seq_no=" + sequenceIDs[i]);
            else if (RackNewRetail != null && Nexteffectivedate == null && RackNewMdPersontage == null)
                exe = executeCommand("update clr_wksht_rule_item set new_retail=" + RackNewRetail + "where seq_no=" + sequenceIDs[i]);
            else if (RackNewMdPersontage != null && Nexteffectivedate == null && RackNewRetail == null)
                exe = executeCommand("update clr_wksht_rule_item set change_percent=" + RackNewMdPersontage + " where seq_no=" + sequenceIDs[i]);
            else if (Nexteffectivedate != null && RackNewRetail != null)
                exe = executeCommand("update clr_wksht_rule_item set new_effective_date='" + Nexteffectivedate + "',new_retail=" + RackNewRetail + " where seq_no=" + sequenceIDs[i]);
            else if (RackNewRetail != null && RackNewMdPersontage != null)
                exe = executeCommand("update clr_wksht_rule_item set new_retail=" + RackNewRetail + ",change_percent=" + RackNewMdPersontage + " where seq_no=" + sequenceIDs[i]);
            else if (Nexteffectivedate != null && RackNewMdPersontage != null)
                exe = executeCommand("update clr_wksht_rule_item set new_effective_date='" + Nexteffectivedate + "',change_percent=" + RackNewMdPersontage + " where seq_no=" + sequenceIDs[i]);
            else if (Nexteffectivedate == null && RackNewRetail == null && RackNewMdPersontage == null) {
            }
        }

        getDBTransaction().commit();
        this.getClr_Wksht_Rule_Item_View1().executeQuery();

        this.getTotals_Taken_View1().executeQuery();
        this.getCurrent_Selection_Saved_Takes_View1().executeQuery();

    }

    public void takeAction(String SeqId, String updateId) {
        int exe = executeCommand("update clr_wksht_rule_item set status='T',update_id = '" + updateId + "', update_datetime = sysdate where seq_no in " + SeqId);

        getDBTransaction().commit();
        this.getClr_Wksht_Rule_Item_View1().executeQuery();
        this.getTotals_Taken_View1().executeQuery();
        this.getCurrent_Selection_Saved_Takes_View1().executeQuery();
    }

    public void untakeAction(String SeqId, String updateId) {
        int exe = executeCommand("update clr_wksht_rule_item set status='N',update_id = '" + updateId + "', update_datetime = sysdate where seq_no in " + SeqId);

        getDBTransaction().commit();
        this.getClr_Wksht_Rule_Item_View1().executeQuery();
        this.getTotals_Taken_View1().executeQuery();
        this.getCurrent_Selection_Saved_Takes_View1().executeQuery();
    }

    public void rpmAction(String SeqId, String updateId) {
        int exe = executeCommand("update clr_wksht_rule_item set status='S',update_id = '" + updateId + "', update_datetime = sysdate where seq_no in " + SeqId);

        getDBTransaction().commit();
        this.getClr_Wksht_Rule_Item_View1().executeQuery();
        this.getTotals_Taken_View1().executeQuery();
        this.getCurrent_Selection_Saved_Takes_View1().executeQuery();
    }

    public void storeHistory(String username, String name, String order) {
        ViewObject vo = this.getClr_Wrks_Savedhistory_Ft_View1();
        Row row = vo.createRow();
        row.setAttribute("Username", username);
        row.setAttribute("Customname", name);
        row.setAttribute("Columnorder", order);
        vo.insertRow(row);
        getDBTransaction().commit();
        vo.executeQuery();

    }

    public void storeHistoryst(String username, String name, String order) {
        ViewObject vo = this.getClr_Wrks_Savedhistory_St_View1();
        Row row = vo.createRow();
        row.setAttribute("Username", username);
        row.setAttribute("Customname", name);
        row.setAttribute("Columnorder", order);
        vo.insertRow(row);
        getDBTransaction().commit();
        vo.executeQuery();

    }

    public void getLoginUsercandidte(String loginUsername) {
        ViewObject vo = getClr_Wrks_Savedhistory_Ft_View1();
        vo.setWhereClause("username ='" + loginUsername + "'");
        vo.executeQuery();
    }

    public void getLoginUserSavedtab(String loginUsername) {
        ViewObject vo = this.getClr_Wrks_Savedhistory_St_View1();
        vo.setWhereClause("username ='" + loginUsername + "'");
        vo.executeQuery();
    }


    /**
     * Container's getter for Clr_Wksht_Rule_Item_View1.
     * @return Clr_Wksht_Rule_Item_View1
     */
    public ViewObjectImpl getClr_Wksht_Rule_Item_View1() {
        return (ViewObjectImpl)findViewObject("Clr_Wksht_Rule_Item_View1");
    }


    /**
     * Container's getter for Clr_Wksht_Rule_Param_Detail_View1.
     * @return Clr_Wksht_Rule_Param_Detail_View1
     */
    public ViewObjectImpl getClr_Wksht_Rule_Param_Detail_View1() {
        return (ViewObjectImpl)findViewObject("Clr_Wksht_Rule_Param_Detail_View1");
    }


    /**
     * Container's getter for Column_Name_View1.
     * @return Column_Name_View1
     */
    public ViewObjectImpl getColumn_Name_View1() {
        return (ViewObjectImpl)findViewObject("Column_Name_View1");
    }

    /**
     * Container's getter for Paramater_Id_View1.
     * @return Paramater_Id_View1
     */
    public ViewObjectImpl getParamater_Id_View1() {
        return (ViewObjectImpl)findViewObject("Paramater_Id_View1");
    }

    /**
     * Container's getter for Operator_View1.
     * @return Operator_View1
     */
    public ViewObjectImpl getOperator_View1() {
        return (ViewObjectImpl)findViewObject("Operator_View1");
    }

    /**
     * Container's getter for Candidate_Rule_View1.
     * @return Candidate_Rule_View1
     */
    public ViewObjectImpl getCandidate_Rule_View1() {
        return (ViewObjectImpl)findViewObject("Candidate_Rule_View1");
    }

    /**
     * Container's getter for Division_View1.
     * @return Division_View1
     */
    public ViewObjectImpl getDivision_View1() {
        return (ViewObjectImpl)findViewObject("Division_View1");
    }

    /**
     * Container's getter for Department_View1.
     * @return Department_View1
     */
    public ViewObjectImpl getDepartment_View1() {
        return (ViewObjectImpl)findViewObject("Department_View1");
    }

    /**
     * Container's getter for Zone_View1.
     * @return Zone_View1
     */
    public ViewObjectImpl getZone_View1() {
        return (ViewObjectImpl)findViewObject("Zone_View1");
    }

    /**
     * Container's getter for ZoneGroup_View1.
     * @return ZoneGroup_View1
     */
    public ViewObjectImpl getZoneGroup_View1() {
        return (ViewObjectImpl)findViewObject("ZoneGroup_View1");
    }

    /**
     * Container's getter for Clr_Wrks_Savedhistory_Ft_View1.
     * @return Clr_Wrks_Savedhistory_Ft_View1
     */
    public ViewObjectImpl getClr_Wrks_Savedhistory_Ft_View1() {
        return (ViewObjectImpl)findViewObject("Clr_Wrks_Savedhistory_Ft_View1");
    }

    /**
     * Container's getter for Clr_Wksht_User_RoleView1.
     * @return Clr_Wksht_User_RoleView1
     */
    public ViewObjectImpl getClr_Wksht_User_RoleView1() {
        return (ViewObjectImpl)findViewObject("Clr_Wksht_User_RoleView1");
    }


    public void insertNewParameterValue(int paramID, int deptID, int param_value, String userName) {
        executeCommand("insert into CLR_WKSHT_RULE_PARAM_DETAIL values (" + paramID + "," + deptID + "," + param_value + "," + null + "," + null + "," + null + "," + null + ")");
        getDBTransaction().commit();
        getClr_Wksht_Rule_Param_Detail_View1().executeQuery();
    }

    public void updateExistingParameter(int param_id, int dept_id, int param_value, String userName) {
        ViewObjectImpl voImpl = getParameterDepartment1();
        executeCommand("update CLR_WKSHT_RULE_PARAM_DETAIL set param_value=" + param_value + "where PARAM_ID=" + param_id + "and DEPT=" + dept_id);
        /*ViewCriteria voCritera = voImpl.getViewCriteria("Clr_Wksht_Rule_Param_Detail_ViewCriteria");
        voImpl.applyViewCriteria(voCritera);
        voImpl.setNamedWhereClauseParam("bind_Dept", dept_id);
        voImpl.setNamedWhereClauseParam("bind_ParamId", param_id);
        voImpl.executeQuery();*/
        getDBTransaction().commit();
        getClr_Wksht_Rule_Param_Detail_View1().executeQuery();
    }

    /**
     * Container's getter for Totals_Taken_View1.
     * @return Totals_Taken_View1
     */
    public ViewObjectImpl getTotals_Taken_View1() {
        return (ViewObjectImpl)findViewObject("Totals_Taken_View1");
    }

    /**
     * Container's getter for Current_Selection_Saved_Takes_View1.
     * @return Current_Selection_Saved_Takes_View1
     */
    public ViewObjectImpl getCurrent_Selection_Saved_Takes_View1() {
        return (ViewObjectImpl)findViewObject("Current_Selection_Saved_Takes_View1");
    }

    /**
     * Container's getter for Clr_Wksht_Role_Named_PermissionView1.
     * @return Clr_Wksht_Role_Named_PermissionView1
     */
    public ViewObjectImpl getClr_Wksht_Role_Named_PermissionView1() {
        return (ViewObjectImpl)findViewObject("Clr_Wksht_Role_Named_PermissionView1");
    }

    /**
     * Container's getter for Clr_Wrks_Savedhistory_St_View1.
     * @return Clr_Wrks_Savedhistory_St_View1
     */
    public ViewObjectImpl getClr_Wrks_Savedhistory_St_View1() {
        return (ViewObjectImpl)findViewObject("Clr_Wrks_Savedhistory_St_View1");
    }

    /**
     * Container's getter for CandidteTab_TableRangeSizeView1.
     * @return CandidteTab_TableRangeSizeView1
     */
    public ViewObjectImpl getCandidteTab_TableRangeSizeView1() {
        return (ViewObjectImpl)findViewObject("CandidteTab_TableRangeSizeView1");
    }

    public ArrayList<String> getRoleDefs(String ruleId) {
        ArrayList<String> list = new ArrayList<String>();

        ViewObject rule_criteria_temp = this.getcriteria_temp1();
        rule_criteria_temp.setWhereClause("rule_id = " + ruleId);
        rule_criteria_temp.executeQuery();
        while (rule_criteria_temp.hasNext()) {
            Row row = rule_criteria_temp.next();
            list.add(row.getAttribute(0).toString());
        }
        return list;
    }

    public void deleteRuleCriteria(String ruleids) {
        int exe = executeCommand("delete from clr_wksht_rule_criteria where rule_id in " + ruleids);

        //this.getDBTransaction().commit();
        int exe1 = executeCommand("delete from clr_wksht_rule where rule_id in " + ruleids);
        this.getDBTransaction().commit();
        getClr_Wksht_Rule_Criteria_View1().executeQuery();
        getClr_Wksht_Rule_View1().executeQuery();
        this.getUnique_ItemList_Rule_View1().reset();
        this.getUnique_ItemList_Rule_View1().executeQuery();
        this.getCandidate_Rule_View1().executeQuery();
    }


    public void addRoleDefs(ArrayList list, ArrayList listDept, String userName) {

        Map sessionScope = ADFContext.getCurrent().getSessionScope();
        oracle.jbo.domain.Number userRole = (Number)sessionScope.get("roleId");
        ViewObject rule = this.getClr_Wksht_Rule_View1();
        String ruleid = rule.getCurrentRow().getAttribute("RuleId").toString();
        int priority = Integer.parseInt(rule.getCurrentRow().getAttribute("Priority").toString());
        rule.clearCache();
        rule.reset();
        rule.setWhereClause(null);
        rule.executeQuery();

        rule.setWhereClause("rule_id != " + ruleid);
        rule.executeQuery();


        int ii = 0;
        Row r1 = null;


        if (priority != -1) {

            System.err.println("NO.of rows ======= " + rule.getRowCount());
            while (rule.hasNext()) {
                if (ii == 0)
                    r1 = rule.first();
                else
                    r1 = rule.next();
                int existPriority = Integer.parseInt(r1.getAttribute("Priority").toString());
                String ruleType = (String)r1.getAttribute("RuleType");
                if(existPriority==priority && ruleType.equals("I")){
                    existPriority = Integer.parseInt(r1.getAttribute("Priority").toString());
                    existPriority += 1;

                    oracle.jbo.domain.Number num = new oracle.jbo.domain.Number(existPriority);
                    r1.setAttribute("Priority", num);

                    priority = existPriority;
                }

                ii++;
            }
        }


        if (userRole.intValue() == 80) {
            if (list.size() > 2) {

                for (int i = 1; i < list.size(); i++) {
                    if (list.size() > 2)
                        if (list.get(i - 1).toString().contains("ITEM_LIST")) {
                            list.remove(i - 1);
                            i--;

                        }
                }

            }
        }

        int e1 = executeCommand("delete from clr_wksht_rule_criteria where rule_id =" + ruleid);
        System.out.println("e1 ::" + e1);

        getDBTransaction().commit();

        ViewObject rule_criteria = this.getClr_Wksht_Rule_Criteria_View1();
        rule_criteria.setWhereClause(null);
        rule_criteria.executeQuery();

        int count = 0;
        int valueofCount = count + 1;
        for (Object rowStr : list) {
            String cols[] = rowStr.toString().split(" ");
            Row row = rule_criteria.createRow();
            row.setAttribute("CriteriaId", cols[0]);
            row.setAttribute("RuleId", ruleid);
            row.setAttribute("SeqNo", valueofCount);

            // History columns
            Date d = new Date();
            //createDate=    d.getCurrentDate()
            if (userName != null) {
                row.setAttribute("CreateId", userName);
                row.setAttribute("LastUpdateId", userName);
            }
            row.setAttribute("CreateDatetime", d.getCurrentDate());
            row.setAttribute("LastUpdateDatetime", d.getCurrentDate());


            // History columns

            for (int i = 2; i < 6; i++) {

                if (!cols[i - 1].equals("null")) {

                    if (i == 2)
                        row.setAttribute("CriteriaColumnName", cols[i - 1]);
                    else if (i == 3)
                        row.setAttribute("CriteriaOperator", cols[i - 1]);
                    else if (i == 4)
                        row.setAttribute("CriteriaValue", cols[i - 1]);
                    else if (i == 5)

                        row.setAttribute("ParamId", cols[i - 1]);
                }
            }

            rule_criteria.insertRow(row);
            valueofCount++;

        }

        getDBTransaction().commit();
        this.getUnique_ItemList_Rule_View1().reset();
        this.getUnique_ItemList_Rule_View1().executeQuery();

        this.getCandidate_Rule_View1().executeQuery();
    }

    public void queryExecution() {
        this.getColumn_Name_View1().executeQuery();
        this.getOperator_View1().executeQuery();
        this.getParamater_Id_View1().executeQuery();


        ViewObject vo = this.getMv_Skulist_Item_View1_1();
        vo.reset();
        vo.setWhereClause(null);
        vo.executeQuery();


    }

    public void deleteParamValues(int param_id, int dept_id) {
        int e1 = executeCommand("delete from clr_wksht_rule_param_detail where param_id =" + param_id + "and dept=" + dept_id);
        getDBTransaction().commit();
        getClr_Wksht_Rule_Param_Detail_View1().reset();
        getClr_Wksht_Rule_Param_Detail_View1().executeQuery();
    }

    /**
     * Container's getter for criteria_temp1.
     * @return criteria_temp1
     */
    public ViewObjectImpl getcriteria_temp1() {
        return (ViewObjectImpl)findViewObject("criteria_temp1");
    }

    public void MIAdminColumnName() {
        int e = executeCommand("select null as column_name from dual union select column_name from CLR_WKSHT_CRITERIA_FIELDS where column_name not in ('DEPT','ITEM_LIST') order by column_name nulls first");

    }

    /**
     * Container's getter for Column_Name_View_Merchant1.
     * @return Column_Name_View_Merchant1
     */
    public ViewObjectImpl getColumn_Name_View_Merchant1() {
        return (ViewObjectImpl)findViewObject("Column_Name_View_Merchant1");
    }

    /**
     * Container's getter for Operator_View_Merchant1.
     * @return Operator_View_Merchant1
     */
    public ViewObjectImpl getOperator_View_Merchant1() {
        return (ViewObjectImpl)findViewObject("Operator_View_Merchant1");
    }

    public Row[] filteredList(String departmentNames) {

        ViewObject vo = getParameterDepartment1();
        /* ViewCriteria vc = voImpl.getViewCriteria("FilteredDepartmentCriteria");
        voImpl.applyViewCriteria(vc);
        voImpl.setNamedWhereClauseParam("bind_Dept_Name", departmentNames);*/
        vo.setWhereClause("Department IN" + departmentNames);
        vo.executeQuery();
        Row[] departments = vo.getAllRowsInRange();
        return departments;
    }

    /**
     * Container's getter for Department_View_Merchant1.
     * @return Department_View_Merchant1
     */
    public ViewObjectImpl getDepartment_View_Merchant1() {
        return (ViewObjectImpl)findViewObject("Department_View_Merchant1");
    }

    /**
     * Container's getter for Clr_Wksht_UpdDeptRule_View1.
     * @return Clr_Wksht_UpdDeptRule_View1
     */
    public ViewObjectImpl getClr_Wksht_UpdDeptRule_View1() {
        return (ViewObjectImpl)findViewObject("Clr_Wksht_UpdDeptRule_View1");
    }

    public ArrayList<String> departmentUpdate(String ruleid) {
        ArrayList<String> deptArrayList = new ArrayList<String>();

        ViewObject vo = this.getDepartment_Merchant_default_Value1();
        //ViewObject vo= this.getClr_Wksht_UpdDeptRule_View1();

        vo.setWhereClause("rule_id = " + ruleid);
        vo.executeQuery();
        while (vo.hasNext()) {
            Row row = vo.next();
            deptArrayList.add(row.getAttribute("Dept").toString().trim());
        }

        return deptArrayList;

    }

    public Row getParameterValues(int paramID, int deptID) {
        ViewObject vo = getClr_Wksht_Rule_Param_Detail_View1();
        vo.setWhereClause("param_id =" + paramID + " and dept =" + deptID);
        vo.executeQuery();
        //System.out.println("vo count ==="+vo.getRowCount());
        Row selectedParameter = vo.first();
        vo.reset();
        vo.setWhereClause(null);
        vo.executeQuery();
        //System.out.println("vo count ==="+vo.getRowCount());
        return selectedParameter;
    }

    public void resetCandidateRule(String iteratorName) {
        DCIteratorBinding iterator = ((DCBindingContainer)BindingContext.getCurrent().getCurrentBindingsEntry()).findIteratorBinding(iteratorName);

        ViewObject vo = iterator.getViewObject();
        if (vo instanceof Clr_Wksht_Rule_ViewImpl) {
            ((Clr_Wksht_Rule_ViewImpl)vo).clearAllCache();
        }
    }    
    
    public void refreshDepartmentsSearch(String userID, String deptAccessIterator){
        ViewObject deptAccess = this.getClr_Wksht_Dept_AccessView1();
        deptAccess.reset();
        deptAccess.setWhereClause(null);
        deptAccess.executeQuery();
        DCIteratorBinding iterator = ((DCBindingContainer)BindingContext.getCurrent().getCurrentBindingsEntry()).findIteratorBinding(deptAccessIterator);
        iterator.setRefreshed(true);
        iterator.executeQuery();
        System.out.println(deptAccess.getRowCount());
        Row[] deptRows = iterator.getAllRowsInRange();
        if(deptRows.length > 0){
            for(Row row: deptRows){
                if(row.getAttribute("UserId").toString().equalsIgnoreCase(userID)){
                    int deleted = executeCommand("delete from CLR_WKSHT_DEPT_ACCESS where USER_ID ='"+userID.toUpperCase()+"'");
                    getDBTransaction().commit();
                    break;
                }
            }
        }
        deptAccess.reset();
        deptAccess.executeQuery();
    }
    public void deleteDeptAccessOnLogout(String userID, String deptAccessIterator){        
        DCIteratorBinding iterator = ((DCBindingContainer)BindingContext.getCurrent().getCurrentBindingsEntry()).findIteratorBinding(deptAccessIterator);
        iterator.setRefreshed(true);
        iterator.executeQuery();
        Row[] deptRows = iterator.getAllRowsInRange();
        for(Row row: deptRows){
            if(row.getAttribute("UserId").toString().equalsIgnoreCase(userID)){
               int deleted = executeCommand("delete from CLR_WKSHT_DEPT_ACCESS where USER_ID ='"+userID.toUpperCase()+"'");               
               getDBTransaction().commit();
                break;
            }            
        }                
    }
    public boolean userLevelDeptAccess(List deptList, String deptRecord, String userName, String deptAccessIterator){
            boolean access = true;
            Map sessionScope = ADFContext.getCurrent().getSessionScope();
            int callerFlag = 0;  
            List commonDeptsSelected = new ArrayList();
            List uncommonDeptsSelected = new ArrayList();
            List tempDeptList = new ArrayList();             
            tempDeptList = deptList;            
            DCIteratorBinding iterator = ((DCBindingContainer)BindingContext.getCurrent().getCurrentBindingsEntry()).findIteratorBinding(deptAccessIterator);
            iterator.setRefreshed(true);
            iterator.executeQuery();          
            ViewObject deptAccess2 = this.getClr_Wksht_Dept_AccessView1();
            deptAccess2.reset();
            deptAccess2.resetExecuted();
            deptAccess2.setWhereClause(null);
            deptAccess2.executeQuery();
            Row[] deptRows = iterator.getAllRowsInRange();        
            if(deptRows.length==0)  {
                for(int i=0;i<deptList.size();i++){
                    Row deptRow = deptAccess2.createRow();
                    deptRow.setAttribute("Dept", deptList.get(i));
                    deptRow.setAttribute("AccessStrt", Date.getCurrentDate());
                    deptRow.setAttribute("UserId", userName.toUpperCase());
                    deptAccess2.insertRow(deptRow);
                    callerFlag = 1;
                }
            }
            else{
                for(int i=0;i<tempDeptList.size();i++){
                    for(Row row: deptRows){                
                        if(row.getAttribute("Dept") != null){  
                            if(tempDeptList.size() > 0){
                                if(row.getAttribute("Dept").equals(tempDeptList.get(i)) && !sessionScope.get("userName").toString().equalsIgnoreCase(row.getAttribute("UserId").toString())){
                                    callerFlag = 1;     
                                    commonDeptsSelected.add(tempDeptList.get(i));
                                    tempDeptList.remove(i);
                                    i--;
                                    break;
                                }
                            }
                        }                        
                    }                   
                }
                if(callerFlag == 1){
                    for(int i=0;i<tempDeptList.size();i++){
                        for(int k=0; k<commonDeptsSelected.size(); k++){                                                                    
                            if(!tempDeptList.get(i).equals(commonDeptsSelected.get(k))){
                                uncommonDeptsSelected.add(tempDeptList.get(i));
                                break;
                            }                                                
                        }
                    }
                }
                else
                    uncommonDeptsSelected = tempDeptList;
                
                ADFContext.getCurrent().getViewScope().put("CommonDepts", commonDeptsSelected);
                //for(int j=0;j<tempDeptList.size();j++){
                for(int j=0;j<uncommonDeptsSelected.size();j++){
                    Row deptRow = deptAccess2.createRow();
                    deptRow.setAttribute("Dept", uncommonDeptsSelected.get(j));
                    deptRow.setAttribute("AccessStrt", Date.getCurrentDate());
                    deptRow.setAttribute("UserId", userName.toUpperCase());
                    deptAccess2.insertRow(deptRow);
                }
            }            
            getDBTransaction().commit();
            if(callerFlag ==1)
                return access = true;
            else
                return access = false;
        }

    /**
     * Container's getter for item_list_view1.
     * @return item_list_view1
     */
    public ViewObjectImpl getitem_list_view1() {
        return (ViewObjectImpl)findViewObject("item_list_view1");
    }

    /**
     * Container's getter for itemlist_value_View1.
     * @return itemlist_value_View1
     */
    public ViewObjectImpl getitemlist_value_View1() {
        return (ViewObjectImpl)findViewObject("itemlist_value_View1");
    }


    /**
     * Container's getter for V_Date_View1.
     * @return V_Date_View1
     */
    public ViewObjectImpl getV_Date_View1() {
        return (ViewObjectImpl)findViewObject("V_Date_View1");
    }

    /**
     * Container's getter for Unique_ItemList_Rule_View1.
     * @return Unique_ItemList_Rule_View1
     */
    public ViewObjectImpl getUnique_ItemList_Rule_View1() {
        return (ViewObjectImpl)findViewObject("Unique_ItemList_Rule_View1");
    }

    /**
     * Container's getter for ReplaceParamValue_View1.
     * @return ReplaceParamValue_View1
     */
    public ViewObjectImpl getReplaceParamValue_View1() {
        return (ViewObjectImpl)findViewObject("ReplaceParamValue_View1");
    }

    /**
     * Container's getter for ParameterDepartment1.
     * @return ParameterDepartment1
     */
    public ViewObjectImpl getParameterDepartment1() {
        return (ViewObjectImpl)findViewObject("ParameterDepartment1");
    }

    /**
     * Container's getter for Clr_Wksht_Rule_Criteria_View1.
     * @return Clr_Wksht_Rule_Criteria_View1
     */
    public ViewObjectImpl getClr_Wksht_Rule_Criteria_View1() {
        return (ViewObjectImpl)findViewObject("Clr_Wksht_Rule_Criteria_View1");
    }

    /**
     * Container's getter for Clr_Wksht_Rule_View1.
     * @return Clr_Wksht_Rule_View1
     */
    public ViewObjectImpl getClr_Wksht_Rule_View1() {
        return (ViewObjectImpl)findViewObject("Clr_Wksht_Rule_View1");
    }

    /**
     * Container's getter for Clr_Wksht_Criteria_Fields_View1.
     * @return Clr_Wksht_Criteria_Fields_View1
     */
    public ViewObjectImpl getClr_Wksht_Criteria_Fields_View1() {
        return (ViewObjectImpl)findViewObject("Clr_Wksht_Criteria_Fields_View1");
    }

    /**
     * Container's getter for Clr_Wksht_Rule_Param_Head_View1.
     * @return Clr_Wksht_Rule_Param_Head_View1
     */
    public ViewObjectImpl getClr_Wksht_Rule_Param_Head_View1() {
        return (ViewObjectImpl)findViewObject("Clr_Wksht_Rule_Param_Head_View1");
    }

    /**
     * Container's getter for Department_Merchant_default_Value1.
     * @return Department_Merchant_default_Value1
     */
    public ViewObjectImpl getDepartment_Merchant_default_Value1() {
        return (ViewObjectImpl)findViewObject("Department_Merchant_default_Value1");
    }


    /**
     * Container's getter for CorporateRule1.
     * @return CorporateRule1
     */
    public ViewObjectImpl getCorporateRule1() {
        return (ViewObjectImpl)findViewObject("CorporateRule1");
    }

    /**
     * Container's getter for Subclass_Filter_LOV_View1.
     * @return Subclass_Filter_LOV_View1
     */
    public ViewObjectImpl getSubclass_Filter_LOV_View1() {
        return (ViewObjectImpl)findViewObject("Subclass_Filter_LOV_View1");
    }

    /**
     * Container's getter for SuppLabel_Filter_LOV_View1.
     * @return SuppLabel_Filter_LOV_View1
     */
    public ViewObjectImpl getSuppLabel_Filter_LOV_View1() {
        return (ViewObjectImpl)findViewObject("SuppLabel_Filter_LOV_View1");
    }

    /**
     * Container's getter for Class_Filter_LOV_View1.
     * @return Class_Filter_LOV_View1
     */
    public ViewObjectImpl getClass_Filter_LOV_View1() {
        return (ViewObjectImpl)findViewObject("Class_Filter_LOV_View1");
    }

    /**
     * Container's getter for SuppColor_Filter_LOV_View1.
     * @return SuppColor_Filter_LOV_View1
     */
    public ViewObjectImpl getSuppColor_Filter_LOV_View1() {
        return (ViewObjectImpl)findViewObject("SuppColor_Filter_LOV_View1");
    }

    /**
     * Container's getter for SupplierName_Filter_LOV_View1.
     * @return SupplierName_Filter_LOV_View1
     */
    public ViewObjectImpl getSupplierName_Filter_LOV_View1() {
        return (ViewObjectImpl)findViewObject("SupplierName_Filter_LOV_View1");
    }

    /**
     * Container's getter for VPN_Filter_LOV_View1.
     * @return VPN_Filter_LOV_View1
     */
    public ViewObjectImpl getVPN_Filter_LOV_View1() {
        return (ViewObjectImpl)findViewObject("VPN_Filter_LOV_View1");
    }

    /**
     * Container's getter for Item_Filter_LOV_View1.
     * @return Item_Filter_LOV_View1
     */
    public ViewObjectImpl getItem_Filter_LOV_View1() {
        return (ViewObjectImpl)findViewObject("Item_Filter_LOV_View1");
    }

    /**
     * Container's getter for Mv_Skulist_Item_View1_1.
     * @return Mv_Skulist_Item_View1_1
     */
    public ViewObjectImpl getMv_Skulist_Item_View1_1() {
        return (ViewObjectImpl)findViewObject("Mv_Skulist_Item_View1_1");
    }

    /**
     * Container's getter for Filter_Department_View1.
     * @return Filter_Department_View1
     */
    public ViewObjectImpl getFilter_Department_View1() {
        return (ViewObjectImpl)findViewObject("Filter_Department_View1");
    }

    /**
     * Container's getter for Clr_Wksht_Dept_AccessView1.
     * @return Clr_Wksht_Dept_AccessView1
     */
   /* public ViewObjectImpl getClr_Wksht_Dept_AccessView1() {
        return (ViewObjectImpl)findViewObject("Clr_Wksht_Dept_AccessView1");
    }*/

    /**
     * Container's getter for NRFColor_Filter_LOV1.
     * @return NRFColor_Filter_LOV1
     */
    public ViewObjectImpl getNRFColor_Filter_LOV1() {
        return (ViewObjectImpl)findViewObject("NRFColor_Filter_LOV1");
    }

    /**
     * Container's getter for Clr_Wksht_Dept_AccessView1.
     * @return Clr_Wksht_Dept_AccessView1
     */
    public ViewObjectImpl getClr_Wksht_Dept_AccessView1() {
        return (ViewObjectImpl)findViewObject("Clr_Wksht_Dept_AccessView1");
    }

    /**
     * Container's getter for Filter_Division_View1.
     * @return Filter_Division_View1
     */
    public ViewObjectImpl getFilter_Division_View1() {
        return (ViewObjectImpl)findViewObject("Filter_Division_View1");
    }
}

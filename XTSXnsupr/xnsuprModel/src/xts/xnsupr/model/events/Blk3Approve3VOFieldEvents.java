package xts.xnsupr.model.events;

import xts.formadf.model.dbwrappers.DP_PRF_NAMOPR;
import xts.formadf.model.exceptions.FormTriggerFailure;
import xts.formadf.model.utils.Ops;

import xts.xnsupr.model.helpers.XnsuprAmImplHelpers;
import xts.xnsupr.model.services.xnsuprAMImpl;

public class Blk3Approve3VOFieldEvents {
    public Blk3Approve3VOFieldEvents(xnsuprAMImpl am) {
        super();
        this.am = am;
    }
    xnsuprAMImpl am;

    public xnsuprAMImpl getAm() {
        return am;
    }

    public void setAm(xnsuprAMImpl am) {
        this.am = am;
    }

    public void ButFind_bp() 
    {

        String v_cursor_style = null;
        String v_where_condition = null;
        try {
            v_cursor_style = am.lib().getApplicationProperty(am.lib().cursorStyle);
            am.lib().setApplicationProperty(am.lib().cursorStyle, "BUSY");
            if (Ops.and(Ops.and(Ops.isNull(am.getBlk3Approve3VORow().getSearchNam1()),
                                Ops.isNull(am.getBlk3Approve3VORow().getSearchNam2())),
                        Ops.isNull(am.getBlk3Approve3VORow().getSearchNam3()))) {
                
               
                am.lib().setApplicationProperty(am.lib().cursorStyle, v_cursor_style);
               // am.helpers().plShowUserMessage(am.helpers().plGetUserMessage("2322"));
                am.helpers().showAlertInfo(am.helpers().plGetUserMessage("2322"));
                am.lib().goItem("blk3_approve_3.search_nam1");
                return;
            } else {
                if (!Ops.isNull(am.getBlk3Approve3VORow().getSearchNam1())) {
                    v_where_condition =
                        Ops.concat(Ops.concat(" UPPER(OLD_FULNAM) like '%' || '",
                                              am.getBlk3Approve3VORow().getSearchNam1()), "' || '%'");
                    // UPPER(spal_fulnam)  == UPPER(OLD_FULNAM)
                }
                if (!Ops.isNull(am.getBlk3Approve3VORow().getSearchNam2())) {
                    if (Ops.isNull(v_where_condition)) {
                        v_where_condition =
                            Ops.concat(Ops.concat(" UPPER(OLD_FULNAM) like '%' || '",
                                                  am.getBlk3Approve3VORow().getSearchNam2()), "' || '%'");
                    } else {
                        v_where_condition =
                            Ops.concat(Ops.concat(Ops.concat(v_where_condition, " AND UPPER(OLD_FULNAM) like '%' || '"),
                                                  am.getBlk3Approve3VORow().getSearchNam2()), "' || '%'");
                    }
                }
                if (!Ops.isNull(am.getBlk3Approve3VORow().getSearchNam3())) {
                    if (Ops.isNull(v_where_condition)) {
                        v_where_condition =
                            Ops.concat(Ops.concat(" UPPER(OLD_FULNAM) like '%' || '",
                                                  am.getBlk3Approve3VORow().getSearchNam3()), "' || '%'");
                    } else {
                        v_where_condition =
                            Ops.concat(Ops.concat(Ops.concat(v_where_condition, " AND UPPER(OLD_FULNAM) like '%' || '"),
                                                  am.getBlk3Approve3VORow().getSearchNam3()), "' || '%'");
                    }
                }
                am.lib().setBlockProperty("blk3_approve_3_1", am.lib().defaultWhere, v_where_condition);
                am.lib().goBlock("blk3_approve_3_1");
                am.lib().clearBlock(am.lib().noValidate);
                am.lib().executeQuery();
            }

            am.lib().setApplicationProperty(am.lib().cursorStyle, v_cursor_style);

        } catch (FormTriggerFailure ex) {
            am.lib().setApplicationProperty(am.lib().cursorStyle, v_cursor_style);
            throw am.lib().getFormTriggerFailure();
        } catch (Exception ex) {
            ex.printStackTrace();
            am.lib().setApplicationProperty(am.lib().cursorStyle, v_cursor_style);
            am.helpers().plShowUserMessage(Ops.concat("Fatal Error! Please contact XTS support team. Error Message: ", am.lib().sqlerrm), "WARN");
            //throw am.lib().getFormTriggerFailure();
            return;
        }
    }

    public void ButExit_bp() {
        try {
            am.lib().formsDdl("rollback");
            //am.lib().goBlock(am.getWorkItemVORow().getWMainBlk());
        } catch (FormTriggerFailure ex) {
            throw am.lib().getFormTriggerFailure();
        } catch (Exception ex) {
            ex.printStackTrace();
           am.helpers() .plShowUserMessage(Ops.concat("Fatal error! Please contact XTS support. Error message: ", am.lib().sqlerrm), "WARN");
            //throw am.lib().getFormTriggerFailure();
           return;
        }
    }
    
    public void ButNext_bp1(){
     //am.lib().goItem("blk3_insert_2_2.spcr_adr1");
     //am.lib().goRecord("blk3_insert_2_2");
    // am.getBlk3Insert22VORow().getRowSet().next();
    // am.getBlk3Approve22VORow().getRowSet().next();
     am.getBlk3Approve32VORow().getRowSet().next();
     //am.lib().nextRecord();
    }
    public void ButPrev_bp1(){
    // am.lib().goItem("blk3_insert_2_2.spcr_adr1");
     //   am.lib().goBlock("blk3_insert_2_2");
    //  am.getBlk3Insert22VORow().getRowSet().previous();
        am.getBlk3Approve32VORow().getRowSet().previous();
    }

    public void ButNext_bp() {
        am.enableReservedMode();
     

        try {
            DP_PRF_NAMOPR.GENERATE_PROF_COD_results results = new DP_PRF_NAMOPR.GENERATE_PROF_COD_results();
            results =
                DP_PRF_NAMOPR.GENERATE_PROF_COD(am.getDBTransaction(), am.getParamVORow().getPProfile(),
                                                am.getBlk3Approve3VORow().getNewFulnam());
          am.getWorkItemVORow().setWNewPrfcod_noVal(results.getP_COD());
            //  if (! DP_PRF_NAMOPR.GENERATE_PROF_COD(am.getDBTransaction(), am.getParamVORow().getPProfile(), am.getBlk3Approve3VORow().getNewFulnam(), am.getWorkItemVORow().getWNewPrfcod()))
            if (!results.getDEFAULT_RETURN()) {
                am.helpers()
                    .plShowUserMessage("Fatal Error! Please contact XTS support team. Error Message: Function 'dp_prf_namopr.generate_prof_code' return FALSE");
                return;
            }
        } catch (FormTriggerFailure ex) {
            throw am.lib().getFormTriggerFailure();
        } catch (Exception ex) {
            ex.printStackTrace();
            am.helpers()
                .plShowUserMessage(Ops.concat("Fatal error! Please contact XTS support. Error message: ",
                                              am.lib().sqlerrm), "WARN");
            //throw am.lib().getFormTriggerFailure();
            return;
        }

        am.lib().hideView("approve_3_can_1");
        am.lib().goBlock("blk3_approve_4");
        am.helpers().plAssignBlk3Approve4();
        am.lib().goBlock("BLK_CONSUBROL");
        am.lib().executeQuery();
        am.lib().goItem("blk3_approve_4.MPPFC_FULNAM");
        am.helpers().plEnDisableConsubrolBlock("BLK_CONSUBROL", am.getSystemVORow().getCursorItem());
       //am.Blk3Approve4VOEvents_prr(); // I added


    }

    public void ButReject_bp()
    {
//        am.lib().goItem("blk3_approve_3.but_next");
//        am.lib().executeTrigger("when-button-pressed");
        am.getBlk3Approve3VOFieldEvents().ButNext_bp();
//        am.lib().goItem("blk3_approve_4.but_reject");
//        am.lib().executeTrigger("when-button-pressed");
        am.getBlk3Approve4VOFieldEvents().ButReject_bp();
        if (Ops.isNull(am.getBlk3Approve5VORow().getDeclineReason())) 
        {
            am.getBlk3Approve5VORow().setDeclineReason_noVal(am.getBlk3Approve1VORow().getRemark());
        }
        
        am.Blk3Approve5VOEvents_prr(); // added
    }

    public void ButPrev_bp() {
        am.lib().hideView("approve_3_can_1");
        am.lib().goBlock("blk3_approve_2");
    }

    public void ButReset_bp() 
    {
        am.getBlk3Approve3VORow().setSearchNam1_noVal("");
        am.getBlk3Approve3VORow().setSearchNam2_noVal("");
        am.getBlk3Approve3VORow().setSearchNam3_noVal("");
    }

}

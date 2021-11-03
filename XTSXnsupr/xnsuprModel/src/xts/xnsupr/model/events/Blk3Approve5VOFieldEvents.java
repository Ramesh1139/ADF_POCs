package xts.xnsupr.model.events;


import xts.formadf.model.exceptions.FormTriggerFailure;
import xts.formadf.model.utils.Ops;
import xts.xnsupr.model.dbwrappers.DP_MIG_XNSUPR;
import xts.xnsupr.model.services.xnsuprAMImpl;


public class Blk3Approve5VOFieldEvents {
    public Blk3Approve5VOFieldEvents(xnsuprAMImpl am) {
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

    public void GetMessage_bp() 
    {
        am.helpers().plShowLov("lov_mpmx_txt", "blk3_approve_5", "decline_reason", "global.one");
    }

    public void ButPrev_bp() 
    {
        am.lib().goBlock("blk3_approve_4");
        am.helpers().plAssignBlk3Approve4();
        am.lib().goBlock("BLK_CONSUBROL");
        am.lib().executeQuery();
        am.lib().goItem("blk3_approve_4.MPPFC_FULNAM");
        am.helpers().plEnDisableConsubrolBlock("BLK_CONSUBROL", am.getSystemVORow().getCursorItem());
    }

    public Boolean ButConfirm_bp() {
        //am.enableReservedMode();
    
       Boolean validationFlag = false;
        try {
            
           /* if (Ops.isNull(am.getBlk3Approve5VORow().getDeclineReason()))
            {
                am.helpers().plShowUserMessage(am.helpers().plGetUserMessage("2331"));
                am.lib().goItem("blk3_approve_5.decline_reason");
               // throw am.lib().getFormTriggerFailure();
            } */
          //  if (Ops.eq(am.lib().fstdConfirmAction("Confirm Reject", "Are you confirm?"), new BigDecimal(88))) 
              
            {
                
                
           am.helpers().plSndRejectLetter();
           am.disableReservedMode();
                try {
                    {
                        DP_MIG_XNSUPR.PL_APPROVE53_BUTCONFIRM_BP1(am.getDBTransaction(),
                                                                  am.getBlk1TmpPrfNewcodVORow().getMppfcRunnum(),
                                                                  am.getBlk3Approve5VORow().getDeclineReason(),
                                                                  am.getGlobalVORow().getGUserid());
                    }
                    
                   
                    //am.lib().formsDdl("commit");
                    am.getDBTransaction().commit(); // I added
                    //am.helpers().executeVOs();
                    am.lib().goBlock("blk1_tmp_prf_newcod");
                    am.lib().executeQuery(); 
                    
                    
                    am.lib().goItem(Ops.concat(am.getWorkItemVORow().getWControlBlk(), ".blk1_refresh"));
                    am.lib().executeTrigger("when-button-pressed");
                    
                    
                } catch (FormTriggerFailure ex) {
                    ex.printStackTrace();
                 System.out.println("Before Rollback4");
                    am.lib().formsDdl("rollback");
                    throw am.lib().getFormTriggerFailure();
                } catch (Exception ex)
                {
                    validationFlag=false;
                    ex.printStackTrace();
//                    am.helpers()
//                        .plShowUserMessage(Ops.concat("Fatal error! Please contact XTS support. Error message: ",
//                                                      am.lib().sqlerrm), "WARN");
//                    
                    
                    
                    System.out.println("Before Rollback1");
                    am.lib().formsDdl("rollback");
                    //throw am.lib().getFormTriggerFailure();
                    return validationFlag;
                    
                }
                am.lib().goBlock(am.getWorkItemVORow().getWMainBlk());
            } 
            
//            else {
//                return;
//            }
       
        }// catch (FormTriggerFailure ex) {
            //ex.printStackTrace();
            //throw am.lib().getFormTriggerFailure();
       // }
    catch (Exception ex) {
            validationFlag=false;
            ex.printStackTrace();
//            am.helpers()
//                .plShowUserMessage(Ops.concat("Fatal Error! Please contact XTS support team. Error Message: ",
//                                              am.lib().sqlerrm), "WARN");
            
            
            return validationFlag;
            //throw am.lib().getFormTriggerFailure();
        }
        validationFlag = true;
        return validationFlag;
    }

    public void ButExit_bp() {
        try {
        am.disableReservedMode();
            System.out.println(" Before : Decline Reason :"+am.getBlk3Approve5VORow().getDeclineReason());
             am.getBlk3Approve5VORow().setDeclineReason_noVal("");
           // JSFUtils.setExpressionValue("#{bindings.DeclineReason.inputValue}","");
            System.out.println("After :Decline Reason :"+am.getBlk3Approve5VORow().getDeclineReason());
           
           // am.lib().formsDdl("rollback");
            am.getDBTransaction().rollback();
            System.out.println("After rollback:Decline Reason :"+am.getBlk3Approve5VORow().getDeclineReason());
            //am.lib().goBlock(am.getWorkItemVORow().getWMainBlk());
        } catch (FormTriggerFailure ex) {
            throw am.lib().getFormTriggerFailure();
        } catch (Exception ex) {
            ex.printStackTrace();
           // am.helpers().plShowUserMessage(Ops.concat("Fatal error! Please contact XTS support. Error message: ", am.lib().sqlerrm), "WARN");
            //throw am.lib().getFormTriggerFailure();
            return;
        }
    }

}

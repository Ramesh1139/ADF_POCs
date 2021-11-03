package xts.xnsupr.model.events;

import java.math.BigDecimal;

import xts.formadf.model.dbwrappers.FL_CHK_NON_ENG_CHARS;
import xts.formadf.model.dbwrappers.TRANSFORM_TO_ALPHABET;
import xts.formadf.model.exceptions.FormTriggerFailure;
import xts.formadf.model.exceptions.VExceptionLock;
import xts.formadf.model.utils.Ops;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import xts.xnsupr.model.dbwrappers.DP_MIG_XNSUPR;
import xts.xnsupr.model.helpers.XnsuprAmImplHelpers;
import xts.xnsupr.model.services.xnsuprAMImpl;
import xts.xnsupr.model.views.cursors.Blk3Approve4_ButApprove_WhenButtonPressed_CkeyVOImpl;
import xts.xnsupr.model.views.cursors.Blk3Approve4_ButApprove_WhenButtonPressed_CkeyVORowImpl;

public class Blk3Approve4VOFieldEvents {
    public Blk3Approve4VOFieldEvents(xnsuprAMImpl am) {
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

    public void MppfcSup_kni() {
        if (Ops.eq(am.lib().getItemProperty("BLK3_APPROVE_4.MPPFC_SHP", am.lib().enabled), "TRUE")) {
            am.lib().goItem("BLK3_APPROVE_4.MPPFC_SHP");
        } else {
            if (Ops.eq(am.lib().getItemProperty("BLK3_APPROVE_4.MPPFC_MAN", am.lib().enabled), "TRUE")) {
                am.lib().goItem("BLK3_APPROVE_4.MPPFC_MAN");
            } else {
                if (Ops.eq(am.lib().getItemProperty("BLK3_APPROVE_4.MPPFC_MAT", am.lib().enabled), "TRUE")) {
                    am.lib().goItem("BLK3_APPROVE_4.MPPFC_MAT");
                } else {
                    if (Ops.eq(am.lib().getItemProperty("BLK3_APPROVE_4.MPPFC_CON", am.lib().enabled), "TRUE")) {
                        am.lib().goItem("BLK3_APPROVE_4.MPPFC_CON");
                    } else {
                        if (Ops.eq(am.lib().getItemProperty("BLK_CONSUBROL.MPTM_CHKBOX", am.lib().enabled), "TRUE")) {
                            am.lib().goItem("BLK_CONSUBROL.MPTM_CHKBOX");
                        } else {
                            am.lib().goItem("BLK_CONSUBROL.MPTM_DES");
                        }
                    }
                }
            }
        }
    }

    public void MppfcSup_cc() {
          am.helpers().plEnDisableConsubrolBlock("BLK_CONSUBROL", am.getSystemVORow().getCursorItem());
    }

    public void MppfcSup_kpi() {
        am.lib().goItem("BLK3_APPROVE_4.SHORT_NAME");
    }

    public void MppfcSup_ken() {
        am.lib().executeTrigger("KEY-NEXT-ITEM");
    }

    public void ShortName_kni() {
        if (Ops.eq(am.lib().getItemProperty("BLK3_APPROVE_4.MPPFC_SUP", am.lib().enabled), "TRUE")) {
            am.lib().goItem("BLK3_APPROVE_4.MPPFC_SUP");
        } else {
            if (Ops.eq(am.lib().getItemProperty("BLK3_APPROVE_4.MPPFC_SHP", am.lib().enabled), "TRUE")) {
                am.lib().goItem("BLK3_APPROVE_4.MPPFC_SHP");
            } else {
                if (Ops.eq(am.lib().getItemProperty("BLK3_APPROVE_4.MPPFC_MAN", am.lib().enabled), "TRUE")) {
                    am.lib().goItem("BLK3_APPROVE_4.MPPFC_MAN");
                } else {
                    if (Ops.eq(am.lib().getItemProperty("BLK3_APPROVE_4.MPPFC_MAT", am.lib().enabled), "TRUE")) {
                        am.lib().goItem("BLK3_APPROVE_4.MPPFC_MAT");
                    } else {
                        if (Ops.eq(am.lib().getItemProperty("BLK3_APPROVE_4.MPPFC_CON", am.lib().enabled), "TRUE")) {
                            am.lib().goItem("BLK3_APPROVE_4.MPPFC_CON");
                        } else {
                            if (Ops.eq(am.lib().getItemProperty("BLK_CONSUBROL.MPTM_CHKBOX", am.lib().enabled),
                                       "TRUE")) {
                                am.lib().goItem("BLK_CONSUBROL.MPTM_CHKBOX");
                            } else {
                                am.lib().goItem("BLK_CONSUBROL.MPTM_DES");
                            }
                        }
                    }
                }
            }
        }
    }

    public void ShortName_ken() {
        am.lib().executeTrigger("KEY-NEXT-ITEM");
    }

    public void MppfcMat_kpi() {
        if (Ops.eq(am.lib().getItemProperty("BLK3_APPROVE_4.MPPFC_MAN", am.lib().enabled), "TRUE")) {
            am.lib().goItem("BLK3_APPROVE_4.MPPFC_MAN");
        } else {
            if (Ops.eq(am.lib().getItemProperty("BLK3_APPROVE_4.MPPFC_SHP", am.lib().enabled), "TRUE")) {
                am.lib().goItem("BLK3_APPROVE_4.MPPFC_SHP");
            } else {
                if (Ops.eq(am.lib().getItemProperty("BLK3_APPROVE_4.MPPFC_SUP", am.lib().enabled), "TRUE")) {
                    am.lib().goItem("BLK3_APPROVE_4.MPPFC_SUP");
                } else {
                    if (Ops.eq(am.lib().getItemProperty("BLK3_APPROVE_4.SHORT_NAME", am.lib().enabled), "TRUE")) {
                        am.lib().goItem("BLK3_APPROVE_4.SHORT_NAME");
                    }
                }
            }
        }
    }

    public void MppfcMat_ken() {
        am.lib().executeTrigger("KEY-NEXT-ITEM");
    }

    public void MppfcMat_kni() {
        if (Ops.eq(am.lib().getItemProperty("BLK3_APPROVE_4.MPPFC_CON", am.lib().enabled), "TRUE")) {
            am.lib().goItem("BLK3_APPROVE_4.MPPFC_CON");
        } else {
            if (Ops.eq(am.lib().getItemProperty("BLK_CONSUBROL.MPTM_CHKBOX", am.lib().enabled), "TRUE")) {
                am.lib().goItem("BLK_CONSUBROL.MPTM_CHKBOX");
            } else {
                am.lib().goItem("BLK_CONSUBROL.MPTM_DES");
            }
        }
    }

    public void MppfcMat_cc() 
    {
        am.helpers().plEnDisableConsubrolBlock("BLK_CONSUBROL", am.getSystemVORow().getCursorItem());
    }

    public void MppfcShp_cc() {
        am.helpers().plEnDisableConsubrolBlock("BLK_CONSUBROL", am.getSystemVORow().getCursorItem());
    }

    public void MppfcShp_kpi() {
        if (Ops.eq(am.lib().getItemProperty("BLK3_APPROVE_4.MPPFC_SUP", am.lib().enabled), "TRUE")) {
            am.lib().goItem("BLK3_APPROVE_4.MPPFC_SUP");
        } else {
            if (Ops.eq(am.lib().getItemProperty("BLK3_APPROVE_4.SHORT_NAME", am.lib().enabled), "TRUE")) {
                am.lib().goItem("BLK3_APPROVE_4.SHORT_NAME");
            }
        }
    }

    public void MppfcShp_ken() {
        am.lib().executeTrigger("KEY-NEXT-ITEM");
    }

    public void MppfcShp_kni() {
        if (Ops.eq(am.lib().getItemProperty("BLK3_APPROVE_4.MPPFC_MAN", am.lib().enabled), "TRUE")) {
            am.lib().goItem("BLK3_APPROVE_4.MPPFC_MAN");
        } else {
            if (Ops.eq(am.lib().getItemProperty("BLK3_APPROVE_4.MPPFC_MAT", am.lib().enabled), "TRUE")) {
                am.lib().goItem("BLK3_APPROVE_4.MPPFC_MAT");
            } else {
                if (Ops.eq(am.lib().getItemProperty("BLK3_APPROVE_4.MPPFC_CON", am.lib().enabled), "TRUE")) {
                    am.lib().goItem("BLK3_APPROVE_4.MPPFC_CON");
                } else {
                    if (Ops.eq(am.lib().getItemProperty("BLK_CONSUBROL.MPTM_CHKBOX", am.lib().enabled), "TRUE")) {
                        am.lib().goItem("BLK_CONSUBROL.MPTM_CHKBOX");
                    } else {
                        am.lib().goItem("BLK_CONSUBROL.MPTM_DES");
                    }
                }
            }
        }
    }

    public void MppfcMan_kpi() {
        if (Ops.eq(am.lib().getItemProperty("BLK3_APPROVE_4.MPPFC_SHP", am.lib().enabled), "TRUE")) {
            am.lib().goItem("BLK3_APPROVE_4.MPPFC_SHP");
        } else {
            if (Ops.eq(am.lib().getItemProperty("BLK3_APPROVE_4.MPPFC_SUP", am.lib().enabled), "TRUE")) {
                am.lib().goItem("BLK3_APPROVE_4.MPPFC_SUP");
            } else {
                if (Ops.eq(am.lib().getItemProperty("BLK3_APPROVE_4.SHORT_NAME", am.lib().enabled), "TRUE")) {
                    am.lib().goItem("BLK3_APPROVE_4.SHORT_NAME");
                }
            }
        }
    }

    public void MppfcMan_ken() {
        am.lib().executeTrigger("KEY-NEXT-ITEM");
    }

    public void MppfcMan_cc() {
        am.helpers().plEnDisableConsubrolBlock("BLK_CONSUBROL", am.getSystemVORow().getCursorItem());
    }

    public void MppfcMan_kni() {
        if (Ops.eq(am.lib().getItemProperty("BLK3_APPROVE_4.MPPFC_MAT", am.lib().enabled), "TRUE")) {
            am.lib().goItem("BLK3_APPROVE_4.MPPFC_MAT");
        } else {
            if (Ops.eq(am.lib().getItemProperty("BLK3_APPROVE_4.MPPFC_CON", am.lib().enabled), "TRUE")) {
                am.lib().goItem("BLK3_APPROVE_4.MPPFC_CON");
            } else {
                if (Ops.eq(am.lib().getItemProperty("BLK_CONSUBROL.MPTM_CHKBOX", am.lib().enabled), "TRUE")) {
                    am.lib().goItem("BLK_CONSUBROL.MPTM_CHKBOX");
                } else {
                    am.lib().goItem("BLK_CONSUBROL.MPTM_DES");
                }
            }
        }
    }

    public void MppfcAdr1_kpi() {
        am.lib().goItem("BLK_CONSUBROL.MPTM_DES");
    }

    public void MppfcCon_cc() 
    {
        if (Ops.eq(am.getBlk3Approve4VORow().getMppfcCon(), "N")) 
        {
            
            am.getBlk3Approve4VORow().setMppfcConsubrol_noVal("");
            am.getBlk3Approve4VORow().setMppfcConsubrolDesc_noVal("");
            am.helpers().plUntickAllConsubrolBlock("BLK_CONSUBROL", "BLK3_APPROVE_4.MPPFC_CON");
        }
        am.lib().goItem("BLK3_APPROVE_4.SHORT_NAME");
        am.helpers().plEnDisableSupplierRole();
        am.helpers().plEnDisableConsubrolBlock("BLK_CONSUBROL", am.getSystemVORow().getCursorItem());
        am.lib().goItem("BLK_CONSUBROL.MPTM_CHKBOX");
    }

    public void MppfcCon_kpi() {
        if (Ops.eq(am.lib().getItemProperty("BLK3_APPROVE_4.MPPFC_MAT", am.lib().enabled), "TRUE")) {
            am.lib().goItem("BLK3_APPROVE_4.MPPFC_MAT");
        } else {
            if (Ops.eq(am.lib().getItemProperty("BLK3_APPROVE_4.MPPFC_MAN", am.lib().enabled), "TRUE")) {
                am.lib().goItem("BLK3_APPROVE_4.MPPFC_MAN");
            } else {
                if (Ops.eq(am.lib().getItemProperty("BLK3_APPROVE_4.MPPFC_SHP", am.lib().enabled), "TRUE")) {
                    am.lib().goItem("BLK3_APPROVE_4.MPPFC_SHP");
                } else {
                    if (Ops.eq(am.lib().getItemProperty("BLK3_APPROVE_4.MPPFC_SUP", am.lib().enabled), "TRUE")) {
                        am.lib().goItem("BLK3_APPROVE_4.MPPFC_SUP");
                    } else {
                        if (Ops.eq(am.lib().getItemProperty("BLK3_APPROVE_4.SHORT_NAME", am.lib().enabled), "TRUE")) {
                            am.lib().goItem("BLK3_APPROVE_4.SHORT_NAME");
                        }
                    }
                }
            }
        }
    }

    public void MppfcCon_kni() {
        if (Ops.eq(am.lib().getItemProperty("BLK_CONSUBROL.MPTM_CHKBOX", am.lib().enabled), "TRUE")) {
            am.lib().goItem("BLK_CONSUBROL.MPTM_CHKBOX");
        } else {
            am.lib().goItem("BLK_CONSUBROL.MPTM_DES");
        }
    }

    public void MppfcCon_ken() {
        am.lib().executeTrigger("KEY-NEXT-ITEM");
    }

    public void MppfcTmpConsubrol_lic() {
    }

    public void ButReject_bp()
    {

        am.lib().goBlock("blk3_approve_5");
        am.Blk3Approve5VOEvents_prr(); // added

    }

    public void MppfcCtycod_pot() 
    {
        am.getBlk3Approve4VORow().setCtycodDes_noVal(am.helpers().flCtydesc(am.getBlk3Approve4VORow().getMppfcCtycod()));
    }

    public void LovCtycod_bp() {
        am.helpers()
            .plShowLov("lov_mpcy_ctycod", "BLK3_APPROVE_4", "MPPFC_ctycod", "global.one", "BLK3_APPROVE_4",
                       "ctycod_des", "global.two");
    }

    public void MppfcLoccod_pot() {
        am.getBlk3Approve4VORow()
            .setLocDes_noVal(am.helpers()
                             .flLocdesc(am.getBlk3Approve4VORow().getMppfcLoccod(),
                                        am.getBlk3Approve4VORow().getMppfcCtycod()));
        
      
    }

    public void LovLoccod_bp() {
        if (Ops.isNull(am.getBlk3Approve4VORow().getMppfcCtycod())) {
            am.helpers().plShowUserMessage(am.helpers().plGetUserMessage("530"));
        } else {
            am.lib().setLovProperty("LOV_MPCL_LOCCOD", am.lib().groupName, "RG_MPCL_LOCCOD_APV");
            am.helpers()
                .plShowLov("LOV_MPCL_LOCCOD", "BLK3_APPROVE_4", "MPPFC_loccod", "global.one", "BLK3_APPROVE_4",
                           "loc_des", "global.two");
        }
    }

    public void ButPrev_bp() {
        am.lib().goBlock("blk3_approve_3");
    }


    public Boolean ButApprove_bp(String status) 
    {
     am.enableReservedMode();  
        Boolean validationFlag = false ;
        
       if(!status.equals("ok"))
       {

        BigDecimal i = new BigDecimal(0);
        try {
            if (Ops.isNull(am.getBlk3Approve4VORow().getMppfcPrfcod())) 
            {
                validationFlag = false;
                //am.helpers().plShowUserMessage(am.helpers().plGetUserMessage("2219"));
                am.helpers().showAlertInfo(am.helpers().plGetUserMessage("2219"));
                am.lib().goItem("blk3_approve_4.mppfc_prfcod");
                return validationFlag;
               // throw am.lib().getFormTriggerFailure();
               
            }
            if (Ops.isNull(am.getBlk3Approve4VORow().getMppfcFulnama4()))

            {
                validationFlag = false;
               // am.helpers().plShowUserMessage(am.helpers().plGetUserMessage("2402"));
                am.helpers().showAlertInfo(am.helpers().plGetUserMessage("2402"));
                am.lib().goItem("blk3_approve_4.mppfc_fulnam");
                //throw am.lib().getFormTriggerFailure();
                return validationFlag;
            }
            if (!FL_CHK_NON_ENG_CHARS.FL_CHK_NON_ENG_CHARS(am.getDBTransaction(),
                                                           am.getBlk3Approve4VORow().getMppfcFulnama4())) {
                validationFlag = false;

              //  am.helpers().plShowUserMessage(am.helpers().plGetUserMessage("3443"));
                am.helpers().showAlertInfo(am.helpers().plGetUserMessage("3443"));
                am.lib().goItem("blk3_approve_4.mppfc_fulnam");
                //throw am.lib().getFormTriggerFailure();
                return validationFlag;
            }
            if (Ops.isNull(am.getBlk3Approve4VORow().getShortName())) {
                validationFlag = false;

               // am.helpers().plShowUserMessage(am.helpers().plGetUserMessage("2403"));
                am.helpers().showAlertInfo(am.helpers().plGetUserMessage("2403"));
                am.lib().goItem("blk3_approve_4.short_name");
                //throw am.lib().getFormTriggerFailure();
                return validationFlag;
            }
            if (!FL_CHK_NON_ENG_CHARS.FL_CHK_NON_ENG_CHARS(am.getDBTransaction(),
                                                           am.getBlk3Approve4VORow().getShortName())) {

                validationFlag = false;
               // am.helpers().plShowUserMessage(am.helpers().plGetUserMessage("3443"));
                am.helpers().showAlertInfo(am.helpers().plGetUserMessage("3443"));
                am.lib().goItem("blk3_approve_4.short_name");
              //  throw am.lib().getFormTriggerFailure();
                return validationFlag;
            }
          /*  if (Ops.and(Ops.and(Ops.and(Ops.and(!am.lib().checkboxChecked("blk3_approve_4.mppfc_sup"),
                                                !am.lib().checkboxChecked("blk3_approve_4.mppfc_shp")),
                                        !am.lib().checkboxChecked("blk3_approve_4.mppfc_man")),
                                !am.lib().checkboxChecked("blk3_approve_4.mppfc_mat")),
                        !am.lib().checkboxChecked("blk3_approve_4.mppfc_con"))) {
                validationFlag = false;
                //am.helpers().plShowUserMessage(am.helpers().plGetUserMessage("2332"));
                am.helpers().showAlertInfo(am.helpers().plGetUserMessage("2332"));
                am.lib().goItem("blk3_approve_4.mppfc_sup");
               // am.getBlk3Approve4VORow().getMppfcSup();
                //throw am.lib().getFormTriggerFailure();
                return validationFlag;
            } */
          if (Ops.and(Ops.and(Ops.and(Ops.and(!am.lib().checkboxChecked(am.getBlk3Approve4VORow().getMppfcSup()),
                                              !am.lib().checkboxChecked(am.getBlk3Approve4VORow().getMppfcShp())),
                                      !am.lib().checkboxChecked(am.getBlk3Approve4VORow().getMppfcMan())),
                              !am.lib().checkboxChecked(am.getBlk3Approve4VORow().getMppfcMat())),
                      !am.lib().checkboxChecked(am.getBlk3Approve4VORow().getMppfcCon()))) {
              validationFlag = false;
              //am.helpers().plShowUserMessage(am.helpers().plGetUserMessage("2332"));
              am.helpers().showAlertInfo(am.helpers().plGetUserMessage("2332"));
              am.lib().goItem("blk3_approve_4.mppfc_sup");
             // am.getBlk3Approve4VORow().getMppfcSup();
              //throw am.lib().getFormTriggerFailure();
             
              return validationFlag;
          }
            if (Ops.isNull(am.getBlk3Approve4VORow().getMppfcAdr1())) {
                validationFlag = false;
               // am.helpers().plShowUserMessage(am.helpers().plGetUserMessage("814"));
                am.helpers().showAlertInfo(am.helpers().plGetUserMessage("814"));
                am.lib().goItem("blk3_approve_4.mppfc_adr1");
               // throw am.lib().getFormTriggerFailure();
                return validationFlag;
            }
            if (Ops.and(Ops.isNull(am.getBlk3Approve4VORow().getMppfcAdr1()),
                        Ops.or(Ops.or(!Ops.isNull(am.getBlk3Approve4VORow().getMppfcAdr2()),
                                      !Ops.isNull(am.getBlk3Approve4VORow().getMppfcAdr3())),
                               !Ops.isNull(am.getBlk3Approve4VORow().getMppfcAdr4())))) {
                validationFlag = false;
               // am.helpers().plShowUserMessage(am.helpers().plGetUserMessage("2432"));
               am.helpers().showAlertInfo(am.helpers().plGetUserMessage("2432"));
                am.lib().goItem("blk3_approve_4.mppfc_adr1");
               // throw am.lib().getFormTriggerFailure();
                return validationFlag;
            }
            if (Ops.isNull(am.getBlk3Approve4VORow().getMppfcCtycod())) {
                
                validationFlag = false;
               // am.helpers().plShowUserMessage(am.helpers().plGetUserMessage("963"));
               am.helpers().showAlertInfo(am.helpers().plGetUserMessage("963"));
                am.lib().goItem("blk3_approve_4.mppfc_CTYCOD");
                return  validationFlag;
            } else {
                {
                    DP_MIG_XNSUPR.PL_APPROVE43_BUTAPPROVE_BP1_results result =
                        DP_MIG_XNSUPR.PL_APPROVE43_BUTAPPROVE_BP1(am.getDBTransaction(),
                                                                  am.getBlk3Approve4VORow().getMppfcCtycod());
                    i = result.getO_I();
                }
                if (Ops.eq(i, new BigDecimal(0))) {
                    validationFlag = false;
                   // am.helpers().plShowUserMessage(am.helpers().plGetUserMessage("816"));
                    am.helpers().showAlertInfo(am.helpers().plGetUserMessage("816"));
                    am.lib().goItem("blk3_approve_4.mppfc_CTYCOD");
                    return validationFlag;
                }
                else{
                    
                }
                if (!Ops.isNull(am.getBlk3Approve4VORow().getMppfcLoccod())) {
                    {
                        DP_MIG_XNSUPR.PL_APPROVE43_BUTAPPROVE_BP2_results result =
                            DP_MIG_XNSUPR.PL_APPROVE43_BUTAPPROVE_BP2(am.getDBTransaction(),
                                                                      am.getBlk3Approve4VORow().getMppfcCtycod(),
                                                                      am.getBlk3Approve4VORow().getMppfcLoccod());
                        i = result.getO_I();
                    }
                    if (Ops.eq(i, new BigDecimal(0))) {
                        validationFlag = false;
                       // am.helpers().plShowUserMessage(am.helpers().plGetUserMessage("850"));
                        am.helpers().showAlertInfo(am.helpers().plGetUserMessage("850"));
                        am.lib().goItem("blk3_approve_4.mppfc_loccod");
                        return validationFlag;
                    }
                } else {
                    if (Ops.isNull(am.getBlk3Approve4VORow().getMppfcLoccod())) {
                        {
                            DP_MIG_XNSUPR.PL_APPROVE43_BUTAPPROVE_BP3_results result =
                                DP_MIG_XNSUPR.PL_APPROVE43_BUTAPPROVE_BP3(am.getDBTransaction(),
                                                                          am.getBlk3Approve4VORow().getMppfcCtycod());
                            i = result.getO_I();
                        }
                        if (Ops.gt(i, new BigDecimal(0))) {
                            validationFlag = false;
                           // am.helpers().plShowUserMessage(am.helpers().plGetUserMessage("1122"));
                            am.helpers().showAlertInfo(am.helpers().plGetUserMessage("1122"));
                            am.lib().goItem("blk3_approve_4.mppfc_loccod");
                            return validationFlag ;
                        }
                    }
                }
            }
            if (Ops.ne(am.lib().length(am.getBlk3Approve4VORow().getMppfcPrfcod()), new BigDecimal(6))) {
               // am.helpers().plShowUserMessage(am.helpers().plGetUserMessage("2418"));
                am.helpers().showAlertInfo(am.helpers().plGetUserMessage("2418"));
                am.lib().goItem("blk3_approve_4.mppfc_prfcod");
                //throw am.lib().getFormTriggerFailure();
                return false;
            }
            if (Ops.or(Ops.ne(TRANSFORM_TO_ALPHABET.TRANSFORM_TO_ALPHABET(am.getDBTransaction(),
                                                                          am.getBlk3Approve4VORow().getMppfcPrfcod(),
                                                                          "N", "Y"),
                              am.getBlk3Approve4VORow().getMppfcPrfcod()),
                       Ops.isNull(TRANSFORM_TO_ALPHABET.TRANSFORM_TO_ALPHABET(am.getDBTransaction(),
                                                                              am.getBlk3Approve4VORow()
                                                                              .getMppfcPrfcod(), "N", "Y")))) {
                am.helpers().showAlertInfo(am.helpers().plGetUserMessage("371"));
                //am.helpers().plShowUserMessage(am.helpers().plGetUserMessage("371"));
                am.lib().goItem("blk3_approve_4.mppfc_prfcod");
               // throw am.lib().getFormTriggerFailure();
                return false;
            }
            BigDecimal v_count = new BigDecimal(0);
            {
                DP_MIG_XNSUPR.PL_APPROVE43_BUTAPPROVE_BP4_results result =
                    DP_MIG_XNSUPR.PL_APPROVE43_BUTAPPROVE_BP4(am.getDBTransaction(),
                                                              am.getBlk3Approve4VORow().getMppfcPrfcod());
                v_count = result.getO_V_COUNT();
            }
            if (Ops.gt(v_count, new BigDecimal(0))) {
                validationFlag = false;
               // am.helpers().plShowUserMessage(am.helpers().plGetUserMessage("2330"));
                am.helpers().showAlertInfo(am.helpers().plGetUserMessage("2330"));
                am.lib().goItem("blk3_approve_4.mppfc_prfcod");
                //throw am.lib().getFormTriggerFailure();
                return validationFlag;
            }
            ;
            BigDecimal v_count1 = new BigDecimal(0);
            {
                DP_MIG_XNSUPR.PL_APPROVE43_BUTAPPROVE_BP5_results result =
                    DP_MIG_XNSUPR.PL_APPROVE43_BUTAPPROVE_BP5(am.getDBTransaction(),
                                                              am.getBlk3Approve4VORow().getMppfcFulnama4());
                v_count1 = result.getO_V_COUNT();
            }
            if (Ops.gt(v_count, new BigDecimal(0))) {
                validationFlag = false;
               // am.helpers().plShowUserMessage(am.helpers().plGetUserMessage("2334"));
                am.helpers().showAlertInfo(am.helpers().plGetUserMessage("2334"));
                am.lib().goItem("blk3_approve_4.mppfc_fulnam");
               // throw am.lib().getFormTriggerFailure();
                return validationFlag;
            }
            ;
            BigDecimal v_count2 = new BigDecimal(0);
            {
                DP_MIG_XNSUPR.PL_APPROVE43_BUTAPPROVE_BP6_results result =
                    DP_MIG_XNSUPR.PL_APPROVE43_BUTAPPROVE_BP6(am.getDBTransaction(),
                                                              am.getBlk3Approve4VORow().getShortName());
                v_count2 = result.getO_V_COUNT();
            }
            if (Ops.gt(v_count, new BigDecimal(0))) {
                validationFlag = false;
               // am.helpers().plShowUserMessage(am.helpers().plGetUserMessage("2335"));
                am.helpers().showAlertInfo(am.helpers().plGetUserMessage("2335"));
                am.lib().goItem("blk3_approve_4.short_name");
               // throw am.lib().getFormTriggerFailure();
                return validationFlag;
                
            }
            ;
            
        }        
        catch (Exception ex) 
        {
            validationFlag = false;
            ex.printStackTrace();
//            am.helpers()
//                .plShowUserMessage(Ops.concat("Fatal Error! Please contact XTS support team. Error Message: ",
//                                              am.lib().sqlerrm), "WARN");
           // throw am.lib().getFormTriggerFailure();
            return validationFlag;
        }
        
           validationFlag = true;
       }
       
       
       else
       {
           
           confirm(status);
       }
        
        
        
     return validationFlag;
        
}


    public void confirm(String status){
        //BigDecimal status = new BigDecimal(0);
          
        //            am.lib().setAlertProperty("al_std_2_button", am.lib().title, "Confirmation");
        //            am.lib().setAlertProperty("al_std_2_button", am.lib().alertMessageText, "Are you confirm to create the supplier / factory code?");
        //            status = am.lib().showAlert("al_std_2_button");
          if (status.equals("ok"))
          {
          
          
          
         // if (Ops.eq(am.lib().fstdConfirmAction("Confirmation", "Are you confirm to create the supplier / factory code?"),
           //         new BigDecimal(88)))
          
          
         // {
              am.helpers().plSndAppvLetter();
          am.disableReservedMode();
              try {
                  {
                      DP_MIG_XNSUPR.PL_APPROVE43_BUTAPPROVE_BP7(am.getDBTransaction(),
                                                                am.getBlk1TmpPrfNewcodVORow().getMppfcRunnum(),
                                                                am.getBlk3Approve4VORow().getMppfcPrfcod(),
                                                                am.getBlk3Approve4VORow().getMppfcFulnama4(),
                                                                am.getGlobalVORow().getGUserid());
                  }
                  {
                      DP_MIG_XNSUPR.PL_APPROVE43_BUTAPPROVE_BP8(am.getDBTransaction(),
                                                                am.getBlk3Approve4VORow().getMppfcPrfcod(),
                                                                am.getBlk3Approve4VORow().getMppfcFulnamLocala4(),
                                                                am.getBlk3Approve4VORow().getMppfcFulnama4(),
                                                                am.getBlk3Approve4VORow().getShortName(),
                                                                am.getBlk3Approve4VORow().getMppfcMan(),
                                                                am.getBlk3Approve4VORow().getMppfcMat(),
                                                                am.getBlk3Approve4VORow().getMppfcSup(),
                                                                am.getBlk3Approve4VORow().getMppfcShp(),
                                                                am.getBlk3Approve4VORow().getMppfcAdr1Local(),
                                                                am.getBlk3Approve4VORow().getMppfcCon(),
                                                                am.getBlk3Approve4VORow().getMppfcConsubrol(),
                                                                am.getBlk3Approve4VORow().getMppfcAdr4Local(),
                                                                am.getBlk3Approve4VORow().getMppfcAdr2Local(),
                                                                am.getBlk3Approve4VORow().getMppfcAdr3Local());
                  }
                  if (!Ops.isNull(am.getBlk3Approve4VORow().getMppfcAdr1())) {
                      {
                          DP_MIG_XNSUPR.PL_APPROVE43_BUTAPPROVE_BP9(am.getDBTransaction(),
                                                                    am.getBlk3Approve4VORow().getMppfcPrfcod(),
                                                                    am.getBlk3Approve4VORow().getMppfcAdr1(),
                                                                    am.getBlk3Approve4VORow().getMppfcAdr3(),
                                                                    am.getBlk3Approve4VORow().getMppfcAdr2(),
                                                                    am.getBlk3Approve4VORow().getMppfcAdr1Local(),
                                                                    am.getBlk3Approve4VORow().getMppfcAdr4(),
                                                                    am.getBlk3Approve4VORow().getMppfcAdr3Local(),
                                                                    am.getBlk3Approve4VORow().getMppfcAdr2Local(),
                                                                    am.getBlk3Approve4VORow().getMppfcCtycod(),
                                                                    am.getBlk3Approve4VORow().getMppfcAdr4Local(),
                                                                    am.getBlk3Approve4VORow().getMppfcLoccod());
                      }
                  }
                  Blk3Approve4_ButApprove_WhenButtonPressed_CkeyVOImpl ckey =
                      am.getBlk3Approve4_ButApprove_WhenButtonPressed_CkeyVO();
                  //rkey;
                  String c_errmsg = null;
                  BigDecimal n_max_outxmlseq = new BigDecimal(0);
                  RuntimeException v_exception_lock;
                  // v_exception_lock,new BigDecimal(-0054)RuntimeException insert_xml_outgoing_exception;

                  try {
                      /*Set bind variables for VO & Prepare for fetch*/
                      ckey.setI_MPPFC_RUNNUM(am.getBlk1TmpPrfNewcodVORow()
                                               .getMppfcRunnum()
                                               .toString());
                      /*Passing cursor parameters*/
                      am.lib().prepareVO(ckey);
                      /*VO is ready for fetch*/
                      {
                          Blk3Approve4_ButApprove_WhenButtonPressed_CkeyVORowImpl curRow =
                              (Blk3Approve4_ButApprove_WhenButtonPressed_CkeyVORowImpl) ckey.next();
                          //rkey = curRow.getCol1();
                          //rkey = curRow.getVpeftFtyId().toString();

                      }
                      am.lib().closeVO(ckey);

                      /*  if (!Ops.isNull( rkey.vpeft_runnum))
        {
        itsepm.dp_evp_utl.pl_upd_factory_code( rkey.vpeft_runnum, rkey.vpeft_fty_id, am.getBlk3Approve4VORow().getMppfcPrfcod(), am.getGlobalVORow().getGDivcod(), am.getGlobalVORow().getGUserid(), c_errmsg);

        if (!Ops.isNull( c_errmsg))
        {
        am.helpers().plShowUserMessage( c_errmsg,"WARN");
        throw am.lib().getFormTriggerFailure();
        }
        {
        DP_MIG_XNSUPR.PL_APPROVE43_BUTAPPROVE_BP10(am.getDBTransaction(), am.getBlk3Approve4VORow().getMppfcPrfcod());
        }
        } */


                  }
                  catch (VExceptionLock ex) 
                  {
                      am.helpers().plShowUserMessage(am.lib().fstdUsrMsg("673"), "WARN");
                      throw am.lib().getFormTriggerFailure();
                  }
                  //am.lib().formsDdl("commit");
                  am.getDBTransaction().commit();
                  am.lib().goItem(Ops.concat(am.getWorkItemVORow().getWControlBlk(), ".blk1_refresh"));
                  am.lib().executeTrigger("when-button-pressed");
              } catch (FormTriggerFailure ex) {
                System.out.println("Before RollBack2");
                  am.lib().formsDdl("rollback");
                  throw am.lib().getFormTriggerFailure();
              } catch (Exception ex)
              {
                  ex.printStackTrace();
                 am.helpers().plShowUserMessage(Ops.concat("Fatal error! Please contact XTS support. Error message: ", am.lib().sqlerrm), "WARN");
                  System.out.println("Before Rollback3");
                  am.lib().formsDdl("rollback");
                  //throw am.lib().getFormTriggerFailure();
                  return;
              }
              //am.lib().goBlock(am.getWorkItemVORow().getWMainBlk());
          }
          
          
          
          else 
          {
              return;
          }
          

    }

    public void ButExit_bp() {
        am.lib().formsDdl("rollback");
       // am.lib().goBlock(am.getWorkItemVORow().getWMainBlk());
    }
    
   


}

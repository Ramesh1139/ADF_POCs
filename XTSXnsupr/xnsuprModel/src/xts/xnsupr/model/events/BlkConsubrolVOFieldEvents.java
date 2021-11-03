package xts.xnsupr.model.events;

import java.math.BigDecimal;

import xts.formadf.model.dbwrappers.DP_COLVAL;
import xts.formadf.model.dbwrappers.DP_EPM_CON;
import xts.formadf.model.utils.Ops;

import xts.xnsupr.model.helpers.XnsuprAmImplHelpers;
import xts.xnsupr.model.services.xnsuprAMImpl;

public class BlkConsubrolVOFieldEvents {
    public BlkConsubrolVOFieldEvents(xnsuprAMImpl am) {
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

    public void MptmChkbox_cc(Boolean checkNewValue)
    {
        if(checkNewValue)
        {
        am.getBlkConsubrolVORow().setMptmChkbox("Y");
        }
          else 
        {
              am.getBlkConsubrolVORow().setMptmChkbox("N");
          }
        
    if (Ops.eq( am.getBlkConsubrolVORow().getMptmChkbox(),"N"))
    { 
            // am.getBlk3Approve4VORow().getMppfcConsubrol();
            am.getBlk3Approve4VORow().setMppfcConsubrol_noVal( am.lib().ltrim( am.lib().rtrim( am.lib().replace(Ops.concat(Ops.concat(",", am.getBlk3Approve4VORow().getMppfcConsubrol()),","),Ops.concat(Ops.concat(",", am.getBlkConsubrolVORow().getMptmVal1()),","),","),","),","));
            if (Ops.isNull( am.getBlk3Approve4VORow().getMppfcConsubrol()))
            {
                am.getBlk3Approve4VORow().setMppfcCon_noVal("N");
                am.getBlk3Approve4VORow().setMppfcConsubrolDesc_noVal("");
            }
            else
            { 
                am.getBlk3Approve4VORow().setMppfcConsubrolDesc_noVal(Ops.concat(" - ", DP_EPM_CON.FL_GET_CONSUBROLNAM(am.getDBTransaction(), am.getBlk3Approve4VORow().getMppfcConsubrol(),new BigDecimal(55).toString())));
            }
             am.lib().goItem("BLK3_APPROVE_4.SHORT_NAME");
             am.helpers().plEnDisableSupplierRole();
             am.helpers().plEnDisableConsubrolBlock("BLK_CONSUBROL", am.getSystemVORow().getCursorItem());
             am.lib().goItem("BLK_CONSUBROL.MPTM_CHKBOX");
    }
    else
    {
            if (Ops.or(Ops.or(Ops.or(Ops.and(Ops.eq( DP_EPM_CON.FL_IS_ALLOW_SUP_ROLE(am.getDBTransaction(), am.getBlkConsubrolVORow().getMptmVal1()),"N"), am.lib().checkboxChecked("BLK3_APPROVE_4.MPPFC_SUP")),Ops.and(Ops.eq( DP_EPM_CON.FL_IS_ALLOW_SHP_ROLE(am.getDBTransaction(), am.getBlkConsubrolVORow().getMptmVal1()),"N"), am.lib().checkboxChecked("BLK3_APPROVE_4.MPPFC_SHP"))),Ops.and(Ops.eq( DP_EPM_CON.FL_IS_ALLOW_MAN_ROLE(am.getDBTransaction(), am.getBlkConsubrolVORow().getMptmVal1()),"N"), am.lib().checkboxChecked("BLK3_APPROVE_4.MPPFC_MAN"))),Ops.and(Ops.eq( DP_EPM_CON.FL_IS_ALLOW_MAT_ROLE(am.getDBTransaction(), am.getBlkConsubrolVORow().getMptmVal1()),"N"), am.lib().checkboxChecked("BLK3_APPROVE_4.MPPFC_MAT"))))
            { am.helpers().plShowUserMessage( am.lib().replace( am.helpers().plGetUserMessage("5618"),"##F01##", DP_COLVAL.GET(am.getDBTransaction(),"TMP_TYPMST","MPTM_DES","MPTM_COLUMN","MPTM_VAL1","SPAL_CONSUBROL", am.getBlkConsubrolVORow().getMptmVal1())),"WARN");
             am.getBlkConsubrolVORow().setMptmChkbox_noVal("N");
            throw am.lib().getFormTriggerFailure();
            }
            else
            {
            if (Ops.isNull( am.getBlk3Approve4VORow().getMppfcConsubrol()))
            { am.getBlk3Approve4VORow().setMppfcConsubrol_noVal( am.getBlkConsubrolVORow().getMptmVal1());
             am.getBlk3Approve4VORow().setMppfcCon_noVal("Y");
            }
            else
            { am.getBlk3Approve4VORow().setMppfcConsubrol_noVal(Ops.concat(Ops.concat( am.getBlk3Approve4VORow().getMppfcConsubrol(),","), am.getBlkConsubrolVORow().getMptmVal1()));
             am.getBlk3Approve4VORow().setMppfcConsubrol_noVal( DP_EPM_CON.FL_SORT_CONSUBROL(am.getDBTransaction(), am.getBlk3Approve4VORow().getMppfcConsubrol()));
            }
             am.getBlk3Approve4VORow().setMppfcConsubrolDesc_noVal(Ops.concat(" - ", DP_EPM_CON.FL_GET_CONSUBROLNAM(am.getDBTransaction(), am.getBlk3Approve4VORow().getMppfcConsubrol(),new BigDecimal(55).toString())));
             am.lib().goItem("BLK3_APPROVE_4.SHORT_NAME");
             am.helpers().plEnDisableSupplierRole();
             am.helpers().plEnDisableConsubrolBlock("BLK_CONSUBROL", am.getSystemVORow().getCursorItem());
             am.lib().goItem("BLK_CONSUBROL.MPTM_CHKBOX");
            }
      }
    }

    public void MptmChkbox_kpi() {
        if (Ops.eq(am.lib().getItemProperty("BLK3_APPROVE_4.MPPFC_CON", am.lib().enabled), "TRUE")) {
            am.lib().goItem("BLK3_APPROVE_4.MPPFC_CON");
        } else {
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
                            if (Ops.eq(am.lib().getItemProperty("BLK3_APPROVE_4.SHORT_NAME", am.lib().enabled),
                                       "TRUE")) {
                                am.lib().goItem("BLK3_APPROVE_4.SHORT_NAME");
                            }
                        }
                    }
                }
            }
        }
    }

    public void MptmDes_kni() {
        am.lib().goItem("BLK3_APPROVE_4.MPPFC_ADR1");
    }

    public void MptmDes_kpi() {
        if (Ops.eq(am.lib().getItemProperty("BLK_CONSUBROL.MPTM_CHKBOX", am.lib().enabled), "TRUE")) {
            am.lib().goItem("BLK_CONSUBROL.MPTM_CHKBOX");
        } else {
            if (Ops.eq(am.lib().getItemProperty("BLK3_APPROVE_4.MPPFC_CON", am.lib().enabled), "TRUE")) {
                am.lib().goItem("BLK3_APPROVE_4.MPPFC_CON");
            } else {
                if (Ops.eq(am.lib().getItemProperty("BLK3_APPROVE_4.MPPFC_MAT", am.lib().enabled), "TRUE")) {
                    am.lib().goItem("BLK3_APPROVE_4.MPPFC_MAT");
                } else {
                    if (Ops.eq(am.lib().getItemProperty("BLK3_APPROVE_4.MPPFC_MAN", am.lib().enabled), "TRUE")) {
                        am.lib().goItem("BLK3_APPROVE_4.MPPFC_MAN");
                    } else {
                        if (Ops.eq(am.lib().getItemProperty("BLK3_APPROVE_4.MPPFC_SHP", am.lib().enabled), "TRUE")) {
                            am.lib().goItem("BLK3_APPROVE_4.MPPFC_SHP");
                        } else {
                            if (Ops.eq(am.lib().getItemProperty("BLK3_APPROVE_4.MPPFC_SUP", am.lib().enabled),
                                       "TRUE")) {
                                am.lib().goItem("BLK3_APPROVE_4.MPPFC_SUP");
                            } else {
                                if (Ops.eq(am.lib().getItemProperty("BLK3_APPROVE_4.SHORT_NAME", am.lib().enabled),
                                           "TRUE")) {
                                    am.lib().goItem("BLK3_APPROVE_4.SHORT_NAME");
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public void MptmDes_ken() {
        am.lib().executeTrigger("KEY-NEXT-ITEM");
    }

}

package xts.xnsupr.model.events; 
import java.math.BigDecimal;

import xts.formadf.model.dbwrappers.DP_EPM_CON;
import xts.formadf.model.utils.Ops;

import xts.xnsupr.model.dbwrappers.DP_MIG_XNSUPR;
import xts.xnsupr.model.helpers.XnsuprAmImplHelpers;
import xts.xnsupr.model.services.xnsuprAMImpl;

public class Blk3Approve1VOEvents {
    public Blk3Approve1VOEvents(xnsuprAMImpl am) {
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

public void prr()
{
 am.getBlk3Approve1VORow().setMppfcSbmid_noVal( am.getBlk1TmpPrfNewcodVORow().getMppfcId());
 am.getBlk3Approve1VORow().setMppfcFulnam_noVal( am.getBlk1TmpPrfNewcodVORow().getMppfcFulnam());
 am.getBlk3Approve1VORow().setMppfcFulnamLocal_noVal( am.getBlk1TmpPrfNewcodVORow().getMppfcFulnamLocal());
 am.getBlk3Approve1VORow().setMppfcSbmby_noVal( am.getBlk1TmpPrfNewcodVORow().getMppfcSbmby());
 am.getBlk3Approve1VORow().setMppfcSbmdat_noVal( am.getBlk1TmpPrfNewcodVORow().getMppfcSbmdat());
 am.getBlk3Approve1VORow().setMppfcSup_noVal( am.getBlk1TmpPrfNewcodVORow().getMppfcSup());
 am.getBlk3Approve1VORow().setMppfcShp_noVal( am.getBlk1TmpPrfNewcodVORow().getMppfcShp());
 am.getBlk3Approve1VORow().setMppfcMan_noVal( am.getBlk1TmpPrfNewcodVORow().getMppfcMan());
 am.getBlk3Approve1VORow().setMppfcMat_noVal( am.getBlk1TmpPrfNewcodVORow().getMppfcMat());
 am.getBlk3Approve1VORow().setMppfcCon_noVal( am.getBlk1TmpPrfNewcodVORow().getMppfcCon());
 am.getBlk3Approve1VORow().setMppfcConsubrol_noVal( am.getBlk1TmpPrfNewcodVORow().getMppfcConsubrol());
if (Ops.isNull( am.getBlk1TmpPrfNewcodVORow().getMppfcConsubrol()))
{ 
    am.getBlk3Approve1VORow().setMppfcConsubrolDesc_noVal("");
}
else
{
    am.getBlk3Approve1VORow().setMppfcConsubrolDesc_noVal(Ops.concat(" - ", DP_EPM_CON.FL_GET_CONSUBROLNAM(am.getDBTransaction(), am.getBlk1TmpPrfNewcodVORow().getMppfcConsubrol(),new BigDecimal(55).toString())));
}
 am.getBlk3Approve1VORow().setMppfcAdr1_noVal( am.getBlk1TmpPrfNewcodVORow().getMppfcAdr1());
 am.getBlk3Approve1VORow().setMppfcAdr2_noVal( am.getBlk1TmpPrfNewcodVORow().getMppfcAdr2());
 am.getBlk3Approve1VORow().setMppfcAdr3_noVal( am.getBlk1TmpPrfNewcodVORow().getMppfcAdr3());
 am.getBlk3Approve1VORow().setMppfcAdr4_noVal( am.getBlk1TmpPrfNewcodVORow().getMppfcAdr4());
 am.getBlk3Approve1VORow().setMppfcAdr1Local_noVal( am.getBlk1TmpPrfNewcodVORow().getMppfcAdr1Local());
 am.getBlk3Approve1VORow().setMppfcAdr2Local_noVal( am.getBlk1TmpPrfNewcodVORow().getMppfcAdr2Local());
 am.getBlk3Approve1VORow().setMppfcAdr3Local_noVal( am.getBlk1TmpPrfNewcodVORow().getMppfcAdr3Local());
 am.getBlk3Approve1VORow().setMppfcAdr4Local_noVal( am.getBlk1TmpPrfNewcodVORow().getMppfcAdr4Local());
 if(am.getBlk1TmpPrfNewcodVORow().getMppfcRmk() != null)
 {
    int number = 10;
    char c = (char) number;
    String ctemp = "" + c;
    String Remarks = am.getBlk1TmpPrfNewcodVORow().getMppfcRmk();

int i = am.getBlk1TmpPrfNewcodVORow().getMppfcRmk().lastIndexOf(ctemp);

    am.getBlk3Approve1VORow().setMppfcRmk_noVal(
                                               am.lib().substr(Remarks,
                                               new BigDecimal(i+1)                                        
                                            
                                              
                                                ));
    
 }
 else 
 { 
     am.getBlk3Approve1VORow().setMppfcRmk_noVal("");
     }
 
 System.out.println(" Value of Remarks is : "+ am.getBlk3Approve1VORow().getMppfcRmk());
 
// am.getBlk3Approve1VORow().setMppfcRmk_noVal(
//                                            am.lib().substr(am.getBlk1TmpPrfNewcodVORow().getMppfcRmk(),
//                                            Ops.plus(am.lib().instr(am.getBlk1TmpPrfNewcodVORow().getMppfcRmk(),
//                                            ctemp, //am.lib().chr(new BigDecimal(10))
//                                            new BigDecimal(0)),
//                                            new BigDecimal(1)))
//                                             );
 
   //am.getBlk3Approve1VORow().setMppfcRmk_noVal(am.getBlk1TmpPrfNewcodVORow().getMppfcRmk()); // I added
 am.getBlk3Approve1VORow().setMppfcCtycod_noVal( am.getBlk1TmpPrfNewcodVORow().getMppfcCtycod());
 am.getBlk3Approve1VORow().setMppfcLoccod_noVal( am.getBlk1TmpPrfNewcodVORow().getMppfcLoccod());
 am.getBlk3Approve1VORow().setCtycodDes_noVal( am.helpers().flCtydesc( am.getBlk3Approve1VORow().getMppfcCtycod()));
 am.getBlk3Approve1VORow().setLocDes_noVal( am.helpers().flLocdesc( am.getBlk3Approve1VORow().getMppfcLoccod(), am.getBlk3Approve1VORow().getMppfcCtycod()));
BigDecimal  v_count =  new BigDecimal(0);
{
    System.out.println("Full Name === "+ am.getBlk3Approve1VORow().getMppfcFulnama1());
DP_MIG_XNSUPR.PL_APPROVE13_PRR1_results  result = DP_MIG_XNSUPR.PL_APPROVE13_PRR1(am.getDBTransaction(), 
                                                                                  am.getBlk3Approve1VORow().getMppfcFulnama1());
 v_count = result.getO_V_COUNT();
   
}
if (Ops.gt( v_count,new BigDecimal(0)))
{ 
    am.getBlk3Approve1VORow().setRemark_noVal(am.helpers().plGetUserMessage("2400"));
    
    System.out.println("Remark Message :"+am.helpers().plGetUserMessage("2400"));
    am.getBlk3Approve1VORow().setRemark(am.helpers().plGetUserMessage("2400"));
   String RemarkValue = am.getBlk3Approve1VORow().getRemark();
   System.out.println("RemarkValue :"+RemarkValue);
   // am.getWorkItemVORow().setWErrmsg(am.helpers().plGetUserMessage("2400"));
    //am.helpers().
    //throw am.lib().getFormTriggerFailure();
    
}

try 
{
    System.out.println("Submitted By === "+am.getBlk3Approve1VORow().getMppfcSbmby());
DP_MIG_XNSUPR.PL_APPROVE13_PRR2_results  result = DP_MIG_XNSUPR.PL_APPROVE13_PRR2(am.getDBTransaction(), am.getBlk3Approve1VORow().getMppfcSbmby());
 am.getBlk3Approve1VORow().setMppfcSbmbyFulnam_noVal(result.getO_MPPFC_SBMBY_FULNAM());

}
catch (Exception ex)
{
    am.getBlk3Approve1VORow().setMppfcSbmbyFulnam_noVal("");
}
try {
System.out.println("Approve Submitted by === "+ am.getBlk3Approve1VORow().getMppfcSbmby());
DP_MIG_XNSUPR.PL_APPROVE13_PRR3_results  result = DP_MIG_XNSUPR.PL_APPROVE13_PRR3(am.getDBTransaction(), am.getBlk3Approve1VORow().getMppfcSbmby());
 am.getBlk3Approve1VORow().setMppfcSbmbyDivision_noVal(result.getO_MPPFC_SBMBY_DIVISION());

}
catch (Exception ex)
{ am.getBlk3Approve1VORow().setMppfcSbmbyDivision_noVal("");
}
}


public void prb()
{
 am.lib().pstdCenterWindow("win_approve");
 am.lib().hideView("approve_2_can_1");
 am.lib().hideView("approve_3_can_1");
}

}

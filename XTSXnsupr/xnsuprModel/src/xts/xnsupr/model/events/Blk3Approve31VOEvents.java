package xts.xnsupr.model.events; 
import xts.formadf.model.utils.Relation;

import xts.formadf.model.utils.Ops;

import xts.xnsupr.model.dbwrappers.DP_MIG_XNSUPR;
import xts.xnsupr.model.helpers.XnsuprAmImplHelpers;
import xts.xnsupr.model.services.xnsuprAMImpl;
import xts.xnsupr.model.views.Blk3Approve31VORowImpl;


public class Blk3Approve31VOEvents {
    public Blk3Approve31VOEvents(xnsuprAMImpl am) {
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

public void cdm()
{
    /*

String  dummy_define =  null;//Blk3Approve31_OnCheckDeleteMaster_Blk3Approve32CurVO
Blk3Approve31_OnCheckDeleteMaster_Blk3Approve32CurVOImpl blk3_approve_3_2_cur = am.getBlk3Approve31_OnCheckDeleteMaster_Blk3Approve32CurVO();

//Set bind variables for VO & Prepare for fetch
//blk3_approve_3_2_cur.setI_OLD_PRFCOD1( am.getBlk3Approve31VORow().getOldPrfcod());//getOldPrfcod
//blk3_approve_3_2_cur.setI_SPCR_SUPCOD1( am.getBlk3Approve32VORow().getSpcrSupcod());
    blk3_approve_3_2_cur.setI_OLD_PRFCOD1(am.getBlk3Approve31VORow().getOldPrfcod());
    blk3_approve_3_2_cur.setI_SPCR_SUPCOD1(am.getBlk3Approve32VORow().getSpcrSupcod());
    

//Passing cursor parameters
am.lib().prepareVO(blk3_approve_3_2_cur);
//VO is ready for fetch
{
Blk3Approve31_OnCheckDeleteMaster_Blk3Approve32CurVORowImpl curRow = (Blk3Approve31_OnCheckDeleteMaster_Blk3Approve32CurVORowImpl) blk3_approve_3_2_cur.next();
 //dummy_define = curRow.getCol1();
 dummy_define = curRow.getJ1().toString();
}
if (am.lib().isVORowFound( blk3_approve_3_2_cur))
{ am.lib().message("Cannot delete master record when matching detail records exist.");
am.lib().closeVO( blk3_approve_3_2_cur);
throw am.lib().getFormTriggerFailure();
}
am.lib().closeVO( blk3_approve_3_2_cur);

*/

}
public void pd(){
String  recstat =  am.getSystemVORow().getRecordStatus();
String  startitm =  am.getSystemVORow().getCursorItem();
Relation  rel_id;
if (Ops.or(Ops.eq( recstat,"NEW"),Ops.eq( recstat,"INSERT")))
{return ;
}
if (!Ops.isNull( am.getBlk3Approve31VORow().getOldPrfcod()))
{ rel_id = (Relation)am.lib().findRelation("BLK3_APPROVE_3_1.BLK3_APPROVE_3__BLK3_APPROVE_3");
 am.helpers().queryMasterDetails( rel_id,"BLK3_APPROVE_3_2");
}
if (Ops.ne( am.getSystemVORow().getCursorItem(), startitm))
{ am.lib().goItem( startitm);
 am.helpers().checkPackageFailure();
}
}
public void poq(Blk3Approve31VORowImpl voRow)
{
    // voRow ==  am.getBlk3Approve31VORow()
    if(voRow !=null)
    {
{
DP_MIG_XNSUPR.PL_APPROVE313_POQ1_results  result = DP_MIG_XNSUPR.PL_APPROVE313_POQ1(am.getDBTransaction(), voRow.getOldPrfcod());
 voRow.setCountTotal_noVal(result.getO_COUNT_TOTAL());
}
    }

}

}

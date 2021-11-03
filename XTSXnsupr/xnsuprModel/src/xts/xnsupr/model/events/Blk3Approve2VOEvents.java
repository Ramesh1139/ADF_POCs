package xts.xnsupr.model.events; 

import xts.formadf.model.utils.Ops;

import xts.formadf.model.utils.Relation;

import xts.xnsupr.model.dbwrappers.DP_MIG_XNSUPR;
import xts.xnsupr.model.helpers.XnsuprAmImplHelpers;
import xts.xnsupr.model.services.xnsuprAMImpl;
import xts.xnsupr.model.views.Blk3Approve2VORowImpl;
import xts.xnsupr.model.views.cursors.Blk3Approve2_OnCheckDeleteMaster_Blk3Approve22CurVOImpl;
import xts.xnsupr.model.views.cursors.Blk3Approve2_OnCheckDeleteMaster_Blk3Approve22CurVORowImpl;

public class Blk3Approve2VOEvents {
    public Blk3Approve2VOEvents(xnsuprAMImpl am) {
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

public void prb(){
 am.lib().pstdCenterWindow("win_approve");
 am.lib().showView("approve_2_can_1");
}
public void poq(Blk3Approve2VORowImpl voRow)
// voRow == am.getBlk3Approve2VORow()

{
if(voRow != null)
{
System.out.println("mppfccod is === "+voRow.getMppfsCod());
    
DP_MIG_XNSUPR.PL_APPROVE23_POQ1_results  result = DP_MIG_XNSUPR.PL_APPROVE23_POQ1(am.getDBTransaction(),voRow.getMppfsCod());//getMppfsCod
System.out.println("Value of Results of Get count total === "+result.getO_COUNT_TOTAL());
 voRow.setCountTotal_noVal(result.getO_COUNT_TOTAL());
 System.out.println(voRow.getCountTotal());
}
}
public void cdm()
{
    
    
String  dummy_define =  null;
Blk3Approve2_OnCheckDeleteMaster_Blk3Approve22CurVOImpl blk3_approve_2_2_cur = am.getBlk3Approve2_OnCheckDeleteMaster_Blk3Approve22CurVO();
//Set bind variables for VO & Prepare for fetch
blk3_approve_2_2_cur.setI_SPCR_SUPCOD( am.getBlk3Approve22VORow().getSpcrSupcod());//getSpcrSupcod
blk3_approve_2_2_cur.setI_MPPFS_COD( am.getBlk3Approve2VORow().getMppfsCod());
//Passing cursor parameters
am.lib().prepareVO(blk3_approve_2_2_cur);
//VO is ready for fetch
{
Blk3Approve2_OnCheckDeleteMaster_Blk3Approve22CurVORowImpl curRow = (Blk3Approve2_OnCheckDeleteMaster_Blk3Approve22CurVORowImpl) blk3_approve_2_2_cur.next();
 //dummy_define = curRow.getCol1();
    dummy_define = curRow.getJ1().toString();
}
if (am.lib().isVORowFound( blk3_approve_2_2_cur))
{ am.lib().message("Cannot delete master record when matching detail records exist.");
am.lib().closeVO( blk3_approve_2_2_cur);
throw am.lib().getFormTriggerFailure();
}
am.lib().closeVO( blk3_approve_2_2_cur);

 
}
public void pd(){
String  recstat =  am.getSystemVORow().getRecordStatus();
String  startitm =  am.getSystemVORow().getCursorItem();
Relation  rel_id;
if (Ops.or(Ops.eq( recstat,"NEW"),Ops.eq( recstat,"INSERT")))
{return ;
}
if (!Ops.isNull( am.getBlk3Approve2VORow().getMppfsCod()))
{ rel_id = am.lib().findRelation("BLK3_APPROVE_2.BLK3_APPROVE_2_BLK3_APPROVE_2");
 am.helpers().queryMasterDetails(rel_id, "BLK3_APPROVE_2_2");
}
if (Ops.ne( am.getSystemVORow().getCursorItem(), startitm))
{ am.lib().goItem( startitm);
 am.helpers().checkPackageFailure();
}
}

}

package xts.xnsupr.model.events; 
import xts.formadf.model.utils.Ops;
import xts.formadf.model.utils.Relation;

import xts.xnsupr.model.dbwrappers.DP_MIG_XNSUPR;
import xts.xnsupr.model.helpers.XnsuprAmImplHelpers;
import xts.xnsupr.model.services.xnsuprAMImpl;
import xts.xnsupr.model.views.Blk3Insert2VORowImpl;
import xts.xnsupr.model.views.cursors.Blk3Insert2_OnCheckDeleteMaster_Blk3Insert22CurVOImpl;
import xts.xnsupr.model.views.cursors.Blk3Insert2_OnCheckDeleteMaster_Blk3Insert22CurVORowImpl;

public class Blk3Insert2VOEvents {
    public Blk3Insert2VOEvents(xnsuprAMImpl am) {
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

public void cdm(){
    
    
String  dummy_define =  null;
Blk3Insert2_OnCheckDeleteMaster_Blk3Insert22CurVOImpl blk3_insert_2_2_cur = am.getBlk3Insert2_OnCheckDeleteMaster_Blk3Insert22CurVO();
//Set bind variables for VO & Prepare for fetch
blk3_insert_2_2_cur.setI_OLD_PRFCOD( am.getBlk3Insert2VORow().getOldPrfcod());
//Passing cursor parameters
am.lib().prepareVO(blk3_insert_2_2_cur);
//VO is ready for fetch
{
Blk3Insert2_OnCheckDeleteMaster_Blk3Insert22CurVORowImpl curRow = (Blk3Insert2_OnCheckDeleteMaster_Blk3Insert22CurVORowImpl) blk3_insert_2_2_cur.next();
 //dummy_define = curRow.getCol1();
   dummy_define  = curRow.getJ1().toString();
}
if (am.lib().isVORowFound( blk3_insert_2_2_cur))
{ am.lib().message("Cannot delete master record when matching detail records exist.");
am.lib().closeVO( blk3_insert_2_2_cur);
throw am.lib().getFormTriggerFailure();
}
am.lib().closeVO( blk3_insert_2_2_cur);

}
public void pd(){
String  recstat =  am.getSystemVORow().getRecordStatus();
String  startitm =  am.getSystemVORow().getCursorItem();
Relation  rel_id;
if (Ops.or(Ops.eq( recstat,"NEW"),Ops.eq( recstat,"INSERT")))
{return ;
}
if (!Ops.isNull( am.getBlk3Insert2VORow().getOldPrfcod()))
{ rel_id =  am.lib().findRelation("BLK3_INSERT_2.BLK3_INSERT_2_BLK3_INSERT_2_2");
 am.helpers().queryMasterDetails( rel_id,"BLK3_INSERT_2_2");
}
if (Ops.ne( am.getSystemVORow().getCursorItem(), startitm))
{ am.lib().goItem( startitm);
 am.helpers().checkPackageFailure();
}
}
public void prb(){
 am.lib().pstdCenterWindow("win_insert");
 am.lib().showView("insert_2_can_1");
}
public void poq(Blk3Insert2VORowImpl voRow)
{
   // voRow ==  am.getBlk3Insert2VORow()
    if(voRow != null)
    {
{
DP_MIG_XNSUPR.PL_INSERT23_POQ1_results  result = DP_MIG_XNSUPR.PL_INSERT23_POQ1(am.getDBTransaction(),voRow.getOldPrfcod());
voRow.setCountTotal_noVal(result.getO_COUNT_TOTAL());
}
    }


}

}

package xts.xnsupr.model.events; 

import xts.formadf.model.dbwrappers.DP_EPM_CON;
import xts.formadf.model.utils.Ops;

import xts.xnsupr.model.helpers.XnsuprAmImplHelpers;
import xts.xnsupr.model.services.xnsuprAMImpl;
import xts.xnsupr.model.views.BlkConsubrolVORowImpl;

public class BlkConsubrolVOEvents {
    public BlkConsubrolVOEvents(xnsuprAMImpl am) {
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

public void ksd(){
 am.lib().scrollDown();
}
public void kd(){
 //down();
}
public void ksu(){
 am.lib().scrollUp();
}
public void poq(BlkConsubrolVORowImpl voRow)
{
    Boolean TrueFlag = true;
    Boolean FalseFlag = false;
    // voRow == am.getBlkConsubrolVORow()
    if(voRow != null)
    {
if (Ops.eq( DP_EPM_CON.FL_CHK_CONSUBROL(am.getDBTransaction(), am.getBlk3Approve4VORow().getMppfcConsubrol(),voRow.getMptmVal1()),"Y"))//getMptmVal1 
{ 
    voRow.setMptmChkbox_noVal("Y");
    voRow.setConsubCheckBox(TrueFlag);
}
else
{ 
    voRow.setMptmChkbox_noVal("N");
    voRow.setConsubCheckBox(FalseFlag);
}
    }


}
public void ku()
{
// up();
}
}

package xts.xnsupr.model.events; 

import java.math.BigDecimal;

import xts.formadf.model.utils.Ops;

import xts.xnsupr.model.helpers.XnsuprAmImplHelpers;
import xts.xnsupr.model.services.xnsuprAMImpl;

public class InfoBlkVOFieldEvents {
    public InfoBlkVOFieldEvents(xnsuprAMImpl am) {
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
public void InfoDiv_mc(){
BigDecimal  v_rtn_cod =  new BigDecimal(0);
if (Ops.eq( am.lib().populateGroup( am.lib().getLovProperty("lov_opdv_divcod", am.lib().groupName)),new BigDecimal(0)))
{ v_rtn_cod =  am.lib().getGroupRowCount( am.lib().getLovProperty("lov_opdv_divcod", am.lib().groupName));
if (Ops.gt( v_rtn_cod,new BigDecimal(0)))
{
if ( am.lib().showLov("lov_opdv_divcod"))
{
if (Ops.ne( am.getGlobalVORow().getOne(), am.getInfoBlkVORow().getInfoDiv()))
{ am.lib().copy( am.getGlobalVORow().getOne(),"info_blk.info_div");
 am.lib().copy( am.getGlobalVORow().getOne(),"global.g_divcod");
}
}
}
else
{ am.helpers().plShowUserMessage( am.helpers().plGetUserMessage("530"),null,"NOTE");
}
}
else
{ am.helpers().plShowUserMessage( am.helpers().plGetUserMessage("530"),null,"NOTE");
}
}

}

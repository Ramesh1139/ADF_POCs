package xts.xnsupr.model.events; 
import xts.xnsupr.model.helpers.XnsuprAmImplHelpers;
import xts.xnsupr.model.services.xnsuprAMImpl;

public class Blk1TmpPrfNewcodVOFieldEvents {
    public Blk1TmpPrfNewcodVOFieldEvents(xnsuprAMImpl am) {
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
public void PbMan_bp(){
 am.helpers().plBlk1OrderBy("mppfc_man");
}
public void PbShp_bp(){
 am.helpers().plBlk1OrderBy("mppfc_shp");
}
public void PbSup_bp(){
 am.helpers().plBlk1OrderBy("mppfc_sup");
}
public void PbStscod_bp(){
 am.helpers().plBlk1OrderBy("mppfc_stscod");
}
public void PbFulnam_bp(){
 am.helpers().plBlk1OrderBy("mppfc_fulnam");
}
public void PbId_bp(){
 am.helpers().plBlk1OrderBy("mppfc_idpfx || lpad(mppfc_runnum, 8, '0')");
}
public void PbUpddat_bp(){
 am.helpers().plBlk1OrderBy("upddat");
}
public void PbRmk_bp(){
 am.helpers().plBlk1OrderBy("mppfc_rmk");
}
public void PbCfmby_bp(){
 am.helpers().plBlk1OrderBy("mppfc_apvby");
}
public void PbCfmdat_bp(){
 am.helpers().plBlk1OrderBy("mppfc_apvdat");
}
public void PbUpdby_bp(){
 am.helpers().plBlk1OrderBy("updby");
}
public void PbGrp_bp(){
 am.helpers().plBlk1OrderBy("mppfc_grp");
}
public void PbSbmby_bp(){
 am.helpers().plBlk1OrderBy("mppfc_sbmby");
}
public void PbSbmdat_bp(){
 am.helpers().plBlk1OrderBy("mppfc_sbmdat");
}
public void PbMat_bp(){
 am.helpers().plBlk1OrderBy("mppfc_mat");
}
public void PbCon_bp(){
 am.helpers().plBlk1OrderBy("nvl(mppfc_con,'N')");
}
public void PbAdr_bp(){
 am.helpers().plBlk1OrderBy("MPPFC_ADR1 || ' ' || MPPFC_ADR2 || ' ' || MPPFC_ADR3 || ' ' || MPPFC_ADR4");
}
public void MppfcRmk_md(){
 am.lib().pstdCenterWindow("win_pop_up");
 am.helpers().plBlk4PopUp( am.getBlk1TmpPrfNewcodVORow().getMppfcRmk());
 am.lib().goBlock("blk4_pop_up");
}

}

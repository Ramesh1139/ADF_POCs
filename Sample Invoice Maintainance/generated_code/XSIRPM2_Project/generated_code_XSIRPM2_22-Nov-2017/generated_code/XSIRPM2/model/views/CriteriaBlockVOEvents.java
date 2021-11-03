package model.views;
public class CriteriaBlockVOEvents {
    public CriteriaBlockVOEvents(Xsirpm2AmImpl am) {
        super();
this.am = am;
    }
    Xsirpm2AmImpl am;
    public Xsirpm2AmImpl getAm() {
        return am;
    }
    public void setAm(Xsirpm2AmImpl am) {
        this.am = am;
    }

public void prb(){
 am.lib().pstdBlk2PreBlock();
}

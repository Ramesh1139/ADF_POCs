package model.views;
public class Blk1ControlVOFieldEvents {
    public Blk1ControlVOFieldEvents(Xsirpm2AmImpl am) {
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
public void Blk1Exit_bp(){
 am.lib().exitForm( am.lib().noValidate, am.lib().fullRollback);
}

}

package model.views;
public class ControlMainBlockVOFieldEvents {
    public ControlMainBlockVOFieldEvents(Xsirpm2AmImpl am) {
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
public void Exit_bp(){
 am.lib().exitForm( am.lib().noCommit, am.lib().noRollback);
}
public void PrtSetup_bp(){
  printer_name;
  prt_port;
 am.lib().winApiDialog.selectPrinter( printer_name, prt_port, false);
 am.getSessionVORow().setWPrinterName_noVal( printer_name);
if (Ops.isNull( am.getMainBlockVORow().getWPrinterName()))
{ am.getMainBlockVORow().setWPrinterName_noVal( am.getMainBlockVORow().getWPrinterName2());
}
else
{ am.getMainBlockVORow().setWPrinterName2_noVal( am.getMainBlockVORow().getWPrinterName());
}
 am.lib().message(Ops.concat("PRINTER:", am.getSessionVORow().getWPrinterName()));
}
public void Print_bp(){
 am.getMainBlockVORow().setWDes_noVal("printer");
 am.helpers().plPrint();
}
public void Preview_bp(){
 am.getMainBlockVORow().setWDes_noVal("preview");
 am.helpers().plPrint();
}

}

package model;
public class Xsirpm2AmImpl {
    lib amLib;

    public lib lib() {
        if (amLib == null) {
            amLib = new lib(this);
        }
        return amLib;
    }

    public Xsirpm2AmImplHelpers helpers() {
        return new Xsirpm2AmImplHelpers(this);
    }

public void AmStartup(String PCallFromEdit,String PSimsInvnoFrom,String PSimsInvnoTo,String PMode){

ensureOneRow(getMainBlockVO());
ensureOneRow(getBlk1ControlVO());
ensureOneRow(getCriteriaBlockVO());
ensureOneRow(getControlMainBlockVO());
ensureOneRow(getWorkItemVO());
ensureOneRow(getInfoBlkVO());
ensureOneRow(getParamVO());){
getParamVORow().setPCallFromEdit(PCallFromEdit);
getParamVORow().setPSimsInvnoFrom(PSimsInvnoFrom);
getParamVORow().setPSimsInvnoTo(PSimsInvnoTo);
getParamVORow().setPMode(PMode);
am.getXsirpm2AmImplEvents().nfi();
}
public ParamVORowImpl getParamVORow(){
 return (ParamVORowImpl) getParamVO().getCurrentRow();
}

public MainBlockVORowImpl getMainBlockVORow(){
 return (MainBlockVORowImpl) getMainBlockVO().getVoRow();
}

public Blk1ControlVORowImpl getBlk1ControlVORow(){
 return (Blk1ControlVORowImpl) getBlk1ControlVO().getVoRow();
}

public CriteriaBlockVORowImpl getCriteriaBlockVORow(){
 return (CriteriaBlockVORowImpl) getCriteriaBlockVO().getVoRow();
}

public ControlMainBlockVORowImpl getControlMainBlockVORow(){
 return (ControlMainBlockVORowImpl) getControlMainBlockVO().getVoRow();
}

public WorkItemVORowImpl getWorkItemVORow(){
 return (WorkItemVORowImpl) getWorkItemVO().getVoRow();
}

public InfoBlkVORowImpl getInfoBlkVORow(){
 return (InfoBlkVORowImpl) getInfoBlkVO().getVoRow();
}

}

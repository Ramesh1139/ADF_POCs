package model;
public class Xsim2AmImpl {
    lib amLib;

    public lib lib() {
        if (amLib == null) {
            amLib = new lib(this);
        }
        return amLib;
    }

    public Xsim2AmImplHelpers helpers() {
        return new Xsim2AmImplHelpers(this);
    }

public void AmStartup(String PMode,String PCommonGlobalFromAdfMenu,String PFormGlobalFromAdfMenu){

ensureOneRow(getSamBlkVO());
ensureOneRow(getBlk1ControlVO());
ensureOneRow(getBlk2CriteriaVO());
ensureOneRow(getBlk3ControlVO());
ensureOneRow(getBlk5ControlVO());
ensureOneRow(getBlk6ControlVO());
ensureOneRow(getWorkItemVO());
ensureOneRow(getRemarkBlkVO());
ensureOneRow(getInfoBlkVO());
ensureOneRow(getItmrmkBlkVO());
ensureOneRow(getPrintBlkVO());
ensureOneRow(getParamVO());){
getParamVORow().setPMode(PMode);
getParamVORow().setPCommonGlobalFromAdfMenu(PCommonGlobalFromAdfMenu);
getParamVORow().setPFormGlobalFromAdfMenu(PFormGlobalFromAdfMenu);
am.getXsim2AmImplEvents().nfi();
}
public ParamVORowImpl getParamVORow(){
 return (ParamVORowImpl) getParamVO().getCurrentRow();
}

public SamBlkVORowImpl getSamBlkVORow(){
 return (SamBlkVORowImpl) getSamBlkVO().getVoRow();
}

public Blk1TsiMstVORowImpl getBlk1TsiMstVORow(){
 return (Blk1TsiMstVORowImpl) getBlk1TsiMstVO().getVoRow();
}

public Blk1ControlVORowImpl getBlk1ControlVORow(){
 return (Blk1ControlVORowImpl) getBlk1ControlVO().getVoRow();
}

public Blk2CriteriaVORowImpl getBlk2CriteriaVORow(){
 return (Blk2CriteriaVORowImpl) getBlk2CriteriaVO().getVoRow();
}

public Blk3TsiMstVORowImpl getBlk3TsiMstVORow(){
 return (Blk3TsiMstVORowImpl) getBlk3TsiMstVO().getVoRow();
}

public Blk3ControlVORowImpl getBlk3ControlVORow(){
 return (Blk3ControlVORowImpl) getBlk3ControlVO().getVoRow();
}

public Blk4TsiDesDVORowImpl getBlk4TsiDesDVORow(){
 return (Blk4TsiDesDVORowImpl) getBlk4TsiDesDVO().getVoRow();
}

public Blk4TsiDesAVORowImpl getBlk4TsiDesAVORow(){
 return (Blk4TsiDesAVORowImpl) getBlk4TsiDesAVO().getVoRow();
}

public Blk4TsiDesBVORowImpl getBlk4TsiDesBVORow(){
 return (Blk4TsiDesBVORowImpl) getBlk4TsiDesBVO().getVoRow();
}

public Blk4TsiDesQVORowImpl getBlk4TsiDesQVORow(){
 return (Blk4TsiDesQVORowImpl) getBlk4TsiDesQVO().getVoRow();
}

public Blk5ControlVORowImpl getBlk5ControlVORow(){
 return (Blk5ControlVORowImpl) getBlk5ControlVO().getVoRow();
}

public Blk5TsiItmVORowImpl getBlk5TsiItmVORow(){
 return (Blk5TsiItmVORowImpl) getBlk5TsiItmVO().getVoRow();
}

public Blk6ControlVORowImpl getBlk6ControlVORow(){
 return (Blk6ControlVORowImpl) getBlk6ControlVO().getVoRow();
}

public Blk6UpdlstVORowImpl getBlk6UpdlstVORow(){
 return (Blk6UpdlstVORowImpl) getBlk6UpdlstVO().getVoRow();
}

public WorkItemVORowImpl getWorkItemVORow(){
 return (WorkItemVORowImpl) getWorkItemVO().getVoRow();
}

public RemarkBlkVORowImpl getRemarkBlkVORow(){
 return (RemarkBlkVORowImpl) getRemarkBlkVO().getVoRow();
}

public InfoBlkVORowImpl getInfoBlkVORow(){
 return (InfoBlkVORowImpl) getInfoBlkVO().getVoRow();
}

public ItmrmkBlkVORowImpl getItmrmkBlkVORow(){
 return (ItmrmkBlkVORowImpl) getItmrmkBlkVO().getVoRow();
}

public PrintBlkVORowImpl getPrintBlkVORow(){
 return (PrintBlkVORowImpl) getPrintBlkVO().getVoRow();
}

}

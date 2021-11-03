package xts.xnsupr.model.events;

import xts.formadf.model.dbwrappers.DP_PRF_NAMOPR;

import xts.formadf.model.exceptions.FormTriggerFailure;
import xts.formadf.model.utils.Ops;

import xts.xnsupr.model.helpers.XnsuprAmImplHelpers;
import xts.xnsupr.model.services.xnsuprAMImpl;

public class Blk3Approve1VOFieldEvents {
    public Blk3Approve1VOFieldEvents(xnsuprAMImpl am) {
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
/*
    public void ButNext_bp() {


        String v_cursor_style = null;
        try {
            v_cursor_style = am.lib().getApplicationProperty(am.lib().cursorStyle);
            am.lib().setApplicationProperty(am.lib().cursorStyle, "BUSY");
            am.lib().synchronize();
            System.out.println("Profile Value : ====" + am.getParamVORow().getPProfile());
            am.getParamVORow().setPProfile("S");
            System.out.println("Full Name === " + am.getBlk1TmpPrfNewcodVORow().getMppfcFulnam());
            // am.getBlk1TmpPrfNewcodVORow().getMppfcFulnam().replace("&", "&'||'");
            String mppfcFulnam = am.getBlk1TmpPrfNewcodVORow().getMppfcFulnam();
          //  mppfcFulnam = cleanUpSQLString(mppfcFulnam);

            if (!DP_PRF_NAMOPR.SEARCH(am.getDBTransaction(), am.getParamVORow().getPProfile(),mppfcFulnam)) 
                //if (!DP_PRF_NAMOPR.SEARCH(am.getDBTransaction(), am.getParamVORow().getPProfile(),am.getBlk1TmpPrfNewcodVORow().getMppfcFulnam())) 
            {
                //  am.helpers().plShowUserMessage(Ops.concat("Fatal Error! Please contact XTS support team. Error Message: ", am.lib().sqlerrm));
                return;
            }

            am.lib().goBlock("blk3_approve_2_1");
            am.lib().goBlock("blk3_approve_2");
            am.lib().clearBlock(am.lib().noValidate);
            am.lib().executeQuery();
            //am.lib().setApplicationProperty( am.lib().cursorStyle, v_cursor_style);
        } catch (FormTriggerFailure ex) {
            am.lib().setApplicationProperty(am.lib().cursorStyle, v_cursor_style);
            throw am.lib().getFormTriggerFailure();
        } catch (Exception ex) {
            //am.lib().setApplicationProperty( am.lib().cursorStyle, v_cursor_style);
            //am.helpers().plShowUserMessage(Ops.concat("Fatal Error! Please contact XTS support team. Error Message: ", am.lib().sqlerrm),"WARN");
            throw am.lib().getFormTriggerFailure();
        }
        am.lib().goBlock("blk3_approve_2");
    } */
    public void ButNext_bp()
    {
        am.enableReservedMode();
        
    String  v_cursor_style =  null;
    try {
     v_cursor_style =  am.lib().getApplicationProperty( am.lib().cursorStyle);
     am.lib().setApplicationProperty( am.lib().cursorStyle,"BUSY");
     am.lib().synchronize();
        am.getParamVORow().setPProfile("S");
    if (! DP_PRF_NAMOPR.SEARCH(am.getDBTransaction(), am.getParamVORow().getPProfile(), am.getBlk1TmpPrfNewcodVORow().getMppfcFulnam()))
    { 
       am.helpers().plShowUserMessage(Ops.concat("Fatal Error! Please contact XTS support team. Error Message: ", am.lib().sqlerrm));
       return ;
    }
    
     am.lib().goBlock("blk3_approve_2_1");
       
     am.lib().goBlock("blk3_approve_2");
       // am.helpers().plApproveOrderBy1("mppfs_scr");
        am.lib().setBlockProperty("blk3_approve_2", am.lib().orderBy,Ops.concat("mppfs_scr"," DESC"));
        
     am.lib().clearBlock( am.lib().noValidate);
     am.lib().executeQuery();
     am.lib().setApplicationProperty( am.lib().cursorStyle, v_cursor_style);
    }
    catch (FormTriggerFailure ex)
    { am.lib().setApplicationProperty( am.lib().cursorStyle, v_cursor_style);
    throw am.lib().getFormTriggerFailure();
    }
    catch (Exception ex)
    {
        ex.printStackTrace();
        am.lib().setApplicationProperty( am.lib().cursorStyle, v_cursor_style);
     am.helpers().plShowUserMessage(Ops.concat("Fatal Error! Please contact XTS support team. Error Message: ", am.lib().sqlerrm),"WARN");
    //throw am.lib().getFormTriggerFailure();
     return;
    }
     am.lib().goBlock("blk3_approve_2");
     am.lib().executeQuery(); // I added
     
     
     am.Blk3Approve21VOEvents_prr(); // I added
     
     
    }

    public void ButReject_bp() 
    {

//        am.lib().goItem("blk3_approve_1.but_next");
//        am.lib().executeTrigger("when-button-pressed");
       am.Blk3Approve1VOFieldEvents_ButNext_bp();  // I added
        
       
//        am.lib().goItem("blk3_approve_2.but_next");
//        am.lib().executeTrigger("when-button-pressed");
       am.Blk3Approve2VOFieldEvents_ButNext_bp(); // I added
        
//        am.lib().goItem("blk3_approve_3.but_next");
//        am.lib().executeTrigger("when-button-pressed");
        am.Blk3Approve3VOFieldEvents_ButNext_bp(); // I added
        
//        am.lib().goItem("blk3_approve_4.but_reject");
//        am.lib().executeTrigger("when-button-pressed");
        am.Blk3Approve4VOFieldEvents_ButReject_bp();  // I added
        
       
        
        am.Blk3Approve5VOEvents_prr(); // added
    }

    public void ButExit_bp()
    {
        try 
        {
            am.lib().formsDdl("rollback");
           // am.lib().goBlock(am.getWorkItemVORow().getWMainBlk());
            
        } catch (FormTriggerFailure ex) {
            throw am.lib().getFormTriggerFailure();
        } catch (Exception ex) {
            ex.printStackTrace();
            //am.helpers().plShowUserMessage(Ops.concat("Fatal error! Please contact XTS support. Error message: ", am.lib().sqlerrm), "WARN");
           // throw am.lib().getFormTriggerFailure();
            return;
        }
        
        am.getBlk1TmpPrfNewcodVO().executeQuery();
    }
    
    private String cleanUpSQLString(String input)
    {
        if(input==null || input.isEmpty())
        {
            return input;
        }
        if(input.contains("&")){
            return input.replaceAll("&", "%");
        }
        else{
            return input;
        }
    }

}

package xts.salinv.view.beans;

import java.io.Serializable;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import oracle.adf.share.logging.ADFLogger;
import oracle.adf.view.rich.component.rich.RichPopup;

import oracle.binding.OperationBinding;

import xts.common.view.framework.utils.ADFUtils;
import xts.common.view.framework.utils.JSFUtils;

public class RefreshAddamt implements Serializable {
    @SuppressWarnings("compatibility:-2964931627204234029")
    private static final long serialVersionUID = 4370727198329413934L;

    private static final ADFLogger LOGGER = ADFLogger.createADFLogger(RefreshAddamt.class);

    private RichPopup applyDefaultAddamtPopup;

    public RefreshAddamt() {
    }

    public void applyDefaultListener(ActionEvent actionEvent) {
        OperationBinding ob = ADFUtils.findOperation("applyDefault");
        ob.getParamsMap().put("cimsRunnum", null);
        ob.execute();

        if (ob.getErrors().size() == 0) {
            this.getApplyDefaultAddamtPopup().hide();
        }
    }

    public void cimsCuscodChgListener(ValueChangeEvent valueChangeEvent) {
        OperationBinding ob;
        Object ret;
        String oldVal = (String) valueChangeEvent.getOldValue();
        String newVal = (String) valueChangeEvent.getNewValue();

        if ((oldVal == null && newVal != null) || (oldVal != null && !oldVal.equalsIgnoreCase(newVal))) {
            LOGGER.info("Old Value: " + oldVal + " New Val: " + newVal);
            valueChangeEvent.getComponent().processUpdates(FacesContext.getCurrentInstance()); //Update Model layer before processing

            //Assign Customer Default Values, delete cusfld hdr rec
            ob = ADFUtils.findOperation("cuscodChgActions");
            ob.execute();
            
            ob = ADFUtils.findOperation("cusfldHdrExeQry");
            ob.execute();

            //Apply Default Addition Amount Template
            ob = ADFUtils.findOperation("plAddamtTpldefChkAll");
            ob.getParamsMap().put("viewInstance", "TciMstVO");
            ob.execute();
            ret = ob.getResult();

            if (ret != null) {
                ob = ADFUtils.findOperation("plGetMsgmst");
                ob.getParamsMap().put("param", "2617");
                ob.execute();
                ret = ob.getResult();

                if (ret != null) {
                    JSFUtils.setManagedBeanValue("pageFlowScope.applyDefaultMsg", (String) ret);
                    RichPopup.PopupHints hints = new RichPopup.PopupHints();
                    this.getApplyDefaultAddamtPopup().show(hints);
                }
            }

        }
    }

    public void setApplyDefaultAddamtPopup(RichPopup applyDefaultAddamtPopup) {
        this.applyDefaultAddamtPopup = applyDefaultAddamtPopup;
    }

    public RichPopup getApplyDefaultAddamtPopup() {
        return applyDefaultAddamtPopup;
    }
}

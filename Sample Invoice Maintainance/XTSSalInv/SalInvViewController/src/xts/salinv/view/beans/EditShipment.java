package xts.salinv.view.beans;

import java.io.Serializable;

import oracle.adf.controller.TaskFlowId;

import oracle.binding.OperationBinding;

import xts.common.view.framework.utils.ADFUtils;

public class EditShipment implements Serializable {
    private String taskFlowId = "/WEB-INF/flows/shpitm/editShpitm-tf.xml#editShpitm-tf";
    private String cusfldTaskFlowId = "/WEB-INF/flows/shpitm/editShpitmCusfld-tf.xml#editShpitmCusfld-tf";

    public EditShipment() {
    }

    public TaskFlowId getDynamicTaskFlowId() {
        return TaskFlowId.parse(taskFlowId);
    }

    public void setDynamicTaskFlowId(String taskFlowId) {
        this.taskFlowId = taskFlowId;
    }

    public String editshipitemstf() {
        //declarations
        OperationBinding ob;
        //check cusfld
        String currentPage = this.getDynamicTaskFlowId().toString();
        if (cusfldTaskFlowId.equals(currentPage)) {
            ob = ADFUtils.findOperation("shiValidateUpdate");
            ob.execute();

            if (ob.getErrors().size() == 0) {
                //post
                ob = ADFUtils.findOperation("postChanges");
                ob.execute();
            }
        } else {
            //post
            ob = ADFUtils.findOperation("postChanges");
            ob.execute();
        }
        //enable CiaiActqty
        if (ob.getErrors().size() == 0) {
            ob = ADFUtils.findOperation("setDisableCiaiActqty");
            ob.getParamsMap().put("value", "N");
            ob.execute();
            //go to page
            setDynamicTaskFlowId("/WEB-INF/flows/shpitm/editShpitm-tf.xml#editShpitm-tf");
        }
        return null;
    }

    public String editdesctf() {
        OperationBinding ob;
        //check cusfld
        String currentPage = this.getDynamicTaskFlowId().toString();
        if (cusfldTaskFlowId.equals(currentPage)) {
            ob = ADFUtils.findOperation("shiValidateUpdate");
            ob.execute();
            if (ob.getErrors().size() == 0) {
                //post
                ob = ADFUtils.findOperation("postChanges");
                ob.execute();
            }
        } else {
            //post
            ob = ADFUtils.findOperation("postChanges");
            ob.execute();
        }

        if (ob.getErrors().size() == 0) {
            ob = ADFUtils.findOperation("setDisableCiaiActqty");
            ob.getParamsMap().put("value", "Y");
            ob.execute();
            setDynamicTaskFlowId("/WEB-INF/flows/shpitm/editShpitmDes-tf.xml#editShpitmDes-tf");
        }
        return null;
    }

    public String editcusfldtf() {
        OperationBinding ob = ADFUtils.findOperation("postChanges");
        ob.execute();

        if (ob.getErrors().size() == 0) {
            ob = ADFUtils.findOperation("setDisableCiaiActqty");
            ob.getParamsMap().put("value", "Y");
            ob.execute();
            
            //Re-execute Shipment Item Customized Field section
            ob = ADFUtils.findOperation("cusfldShiExeQry");
            ob.execute();

            setDynamicTaskFlowId("/WEB-INF/flows/shpitm/editShpitmCusfld-tf.xml#editShpitmCusfld-tf");
        }
        return null;
    }

    public String editaddamttf() {
        OperationBinding ob;
        //check cusfld
        String currentPage = this.getDynamicTaskFlowId().toString();
        if (cusfldTaskFlowId.equals(currentPage)) {
            ob = ADFUtils.findOperation("shiValidateUpdate");
            ob.execute();
            if (ob.getErrors().size() == 0) {
                //post
                ob = ADFUtils.findOperation("postChanges");
                ob.execute();
            }
        } else {
            //post
            ob = ADFUtils.findOperation("postChanges");
            ob.execute();
        }

        if (ob.getErrors().size() == 0) {
            ob = ADFUtils.findOperation("setDisableCiaiActqty");
            ob.getParamsMap().put("value", "Y");
            ob.execute();

            setDynamicTaskFlowId("/WEB-INF/flows/shpitm/editShpitmAddamt-tf.xml#editShpitmAddamt-tf");
        }
        return null;
    }
}

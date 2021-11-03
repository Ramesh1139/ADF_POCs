package com.infosys.nordstrom.reim;

import oracle.adf.controller.TaskFlowId;

public class HomeBean 
{


    private String taskFlowId = "/WEB-INF/taskflows/UploadInvoice_BTF.xml#UploadInvoice_BTF";

    public HomeBean() {
    }


    public TaskFlowId getDynamicTaskFlowId() {
        return TaskFlowId.parse(taskFlowId);
    }

    public String uploadInvoice_BTF() {
        taskFlowId = "/WEB-INF/taskflows/UploadInvoice_BTF.xml#UploadInvoice_BTF";
        return null;
    }

    public String uploadStatus_BTF() {
        taskFlowId = "/WEB-INF/taskflows/UploadStatus_BTF.xml#UploadStatus_BTF";
        return null;
    }

    public String auditHistory_BTF() {
        taskFlowId = "/WEB-INF/taskflows/AuditHistory_BTF.xml#AuditHistory_BTF";
        return null;
    }
}

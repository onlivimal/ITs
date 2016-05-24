package com.vimal.its.action.requester;

import com.opensymphony.xwork2.ActionContext;
import com.vimal.its.dao.DB;
import java.io.InputStream;

public class SubmitDeviceRequestAction {


    DB db = new DB();
    private int deviceId;
    private String dept;

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public int getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(int deviceId) {
        this.deviceId = deviceId;
    }

    public SubmitDeviceRequestAction() {
    }

    public String execute() throws Exception {
        //String dept = ActionContext.getContext().getSession().get("emp_dept").toString();
        if(db.submitRequestForDevice(deviceId, dept))
            return "success";
        return "failure";
    }
}

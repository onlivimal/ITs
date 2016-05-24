package com.vimal.its.action.procurement;

import com.vimal.its.dao.DB;

/**
 *
 * @author Vimal
 */
public class DeleteDeviceByIdAction {

    private int deviceId;

    public int getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(int deviceId) {
        this.deviceId = deviceId;
    }

    private DB db = new DB();

    public DeleteDeviceByIdAction(){

    }

    public String execute(){
        if(db.deleteDeviceById(deviceId))
            return "success";
        return "failure";
    }

}

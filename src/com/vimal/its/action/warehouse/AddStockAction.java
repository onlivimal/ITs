package com.vimal.its.action.warehouse;

import com.vimal.its.dao.DB;

/**
 *
 * @author Vimal
 */
public class AddStockAction {

    public AddStockAction() {
    }
    private int deviceId;
    private String serialNo;
    private DB db = new DB();

    public String getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo;
    }

    public int getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(int deviceId) {
        this.deviceId = deviceId;
    }

    public String execute() {
        if (db.addStock(deviceId, serialNo)) {
            return "success";
        }
        return "failure";
    }
}

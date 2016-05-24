package com.vimal.its.action.warehouse;

import com.vimal.its.dao.DB;

/**
 *
 * @author Vimal
 */
public class AssignStockToRequestAction {

    public AssignStockToRequestAction() {
    }
    private DB db = new DB();
    private int reqId;
    private int stockId;

    public int getReqId() {
        return reqId;
    }

    public void setReqId(int reqId) {
        this.reqId = reqId;
    }

    public int getStockId() {
        return stockId;
    }

    public void setStockId(int stockId) {
        this.stockId = stockId;
    }

    public String execute() {
        if (db.assignStockToRequest(stockId, reqId)) {
            return "success";
        }
        return "failure";
    }
}

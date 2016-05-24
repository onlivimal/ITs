package com.vimal.its.action.approver;

import com.vimal.its.dao.DB;

/**
 *
 * @author Vimal
 */
public class ApprovalAction {

    private int reqId;
    private String appr;
    private DB db = new DB();

    public ApprovalAction() {
    }

    public String getAppr() {
        return appr;
    }

    public void setAppr(String appr) {
        this.appr = appr;
    }

    public int getReqId() {
        return reqId;
    }

    public void setReqId(int reqId) {
        this.reqId = reqId;
    }

    public String execute() {
        if (db.approval(getAppr(), getReqId())) {
            return "success";
        }
        return "failure";
    }
}

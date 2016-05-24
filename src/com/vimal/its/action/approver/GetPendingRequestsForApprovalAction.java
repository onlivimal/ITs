package com.vimal.its.action.approver;

import com.vimal.its.dao.DB;
import com.vimal.its.dto.Device;
import com.vimal.its.dto.Request;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;

/**
 *
 * @author Vimal
 */
public class GetPendingRequestsForApprovalAction {

    DB db = new DB();
    InputStream inputStream;

    public GetPendingRequestsForApprovalAction() {
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public String execute() throws Exception {
        String output = "";
        List<Request> requests = db.getPendingRequestsForApproval();
        if (requests.isEmpty()) {
            output = "<h4>You don't have any pending requests</h4>";
        } else {
            output = "<div class='myTable'><table><tr><td>Department</td><td>Category</td><td>Make</td><td>Model</td><td>Price</td><td>Approve</td><td>Reject</td></tr>";
            for (int i = 0; i < requests.size(); i++) {
                String approve = "<button id ='approve' class='approve' rel=\"reqId=" + requests.get(i).getId() + "&appr=YES\" ><span class=\"ui-icon ui-icon-circle-check\"></span></button>";
                String reject = "<button id='reject' class='reject' rel=\"reqId=" + requests.get(i).getId() + "&appr=NO\"><span class=\"ui-icon ui-icon-circle-close\"></span></button>";
                Device device = requests.get(i).getDevice();
                output = output + "<tr><td>" + requests.get(i).getDept() + "</td>"
                        + "<td>" + device.getCategory() + "</td>"
                        + "<td>" + device.getMake() + "</td>"
                        + "<td>" + device.getModel() + "</td>"
                        + "<td>" + device.getPrice() + "</td>"
                        + "<td>" + approve + "</td>"
                        + "<td>" + reject + "</td>";
                output = output + "</tr>";
            }
            output = output + "<table></div>";
        }
        inputStream = new ByteArrayInputStream(output.getBytes("UTF-8"));
        return "success";
    }
}

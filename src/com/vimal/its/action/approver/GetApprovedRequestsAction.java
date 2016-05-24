package com.vimal.its.action.approver;

import com.vimal.its.dao.DB;
import com.vimal.its.dto.Device;
import com.vimal.its.dto.Request;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 *
 * @author Vimal
 */
public class GetApprovedRequestsAction {

    DB db = new DB();
    private InputStream inputStream;

    public InputStream getInputStream() {
        return inputStream;
    }

    public GetApprovedRequestsAction() {
    }

    public String execute() throws UnsupportedEncodingException {
        String output = "";
        List<Request> requests = db.getApprovedRequests();
        if (requests.isEmpty()) {
            output = "<h4>You don't have any approved requests</h4>";
        } else {
            output = "<div class='myTable'><table><tr><td>Department</td><td>Category</td><td>Make</td><td>Model</td><td>Price</td><td>Approval</td></tr>";
            for (int i = 0; i < requests.size(); i++) {
                Device device = requests.get(i).getDevice();
                output = output + "<tr><td>" + requests.get(i).getDept() + "</td>"
                        + "<td>" + device.getCategory() + "</td>"
                        + "<td>" + device.getMake() + "</td>"
                        + "<td>" + device.getModel() + "</td>"
                        + "<td>" + device.getPrice() + "</td>"
                        + "<td><span class=\"ui-icon ui-icon-circle-check\"></span></td>";
                output = output + "</tr>";
            }
            output = output + "<table></div>";
        }
        inputStream = new ByteArrayInputStream(output.getBytes("UTF-8"));
        return "success";
    }
}

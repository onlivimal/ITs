package com.vimal.its.action.procurement;

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
public class GetRequestsToAssignAction {

    private DB db = new DB();
    InputStream inputStream;

    public InputStream getInputStream() {
        return inputStream;
    }

    public GetRequestsToAssignAction() {
    }

    public String execute() throws UnsupportedEncodingException {
        List<Request> requests = db.getRequestsToAssign();
        String output = "";
        if (requests.isEmpty()) {
            output = "<h4>You don't have any devices at the moment</h4>";
        } else {
            output = "<div class='myTable'><table><tr><td>Department</td><td>Category</td><td>Make</td><td>Model</td><td>Price</td><td>Assign</td></tr>";

            for (int i = 0; i < requests.size(); i++) {
                Device device = new Device();
                device = requests.get(i).getDevice();
                output = output + "<tr><td>" + requests.get(i).getDept() + "</td>"
                        + "<td>" + device.getCategory() + "</td>"
                        + "<td>" + device.getMake() + "</td>"
                        + "<td>" + device.getModel() + "</td>"
                        + "<td>" + device.getPrice() + "</td>"
                        + "<td>" + "<button class=assign deviceid='" + device.getId() + "' reqid='" + requests.get(i).getId() + "' dept='" + requests.get(i).getDept() + "' device='" + device.getMake() + " " + device.getModel() + "'><span class=\"ui-icon ui-icon-transferthick-e-w\"></span></button>" + "</td></tr>";
            }
            output = output + "</table></div>";
            
        }
        inputStream = new ByteArrayInputStream(output.getBytes("UTF-8"));
        return "success";
    }
}

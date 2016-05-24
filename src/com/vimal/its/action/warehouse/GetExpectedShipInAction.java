package com.vimal.its.action.warehouse;

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
public class GetExpectedShipInAction {

    private InputStream inputStream;
    private DB db = new DB();

    public InputStream getInputStream() {
        return inputStream;
    }

    public GetExpectedShipInAction() {
    }

    public String execute() throws UnsupportedEncodingException {
        String output = "";
        List<Request> requests = db.getExpectedShipIn();
        if (requests.isEmpty()) {
            output = "<h4>You don't have any devices at the moment</h4>";
        } else {
            output = "<div class='myTable'><table><tr><td>Category</td><td>Make</td><td>Model</td><td>Ship In</td></tr>";
            for (int i = 0; i < requests.size(); i++) {
                Device device = new Device();
                device = requests.get(i).getDevice();
                output = output
                        + "<td>" + device.getCategory() + "</td>"
                        + "<td>" + device.getMake() + "</td>"
                        + "<td>" + device.getModel() + "</td>"
                        + "<td>" + "<button class=assign deviceid='" + device.getId() + "' device='" + device.getMake() + " " + device.getModel() + "'><span class=\"ui-icon ui-icon-cart\"></span></button>" + "</td></tr>";
            }
            output = output + "</table></div>";
        }
        inputStream = new ByteArrayInputStream(output.getBytes("UTF-8"));
        return "success";
    }
}

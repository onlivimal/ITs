package com.vimal.its.action.procurement;

import com.vimal.its.dao.DB;
import com.vimal.its.dto.Device;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 *
 * @author Vimal
 */
public class GetAllDevicesAction {

    private InputStream inputStream;
    private DB db = new DB();

    public GetAllDevicesAction() {
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public String execute() throws UnsupportedEncodingException {
        List<Device> devices = db.getAllDevices();
        String output="";
        if (devices.isEmpty()) {
            output = "<h4>No devices at the moment</h4>";
        } else {
            output = "<div class='myTable'><table><tr><td>Make</td><td>Model</td><td>Price</th><td>Category</td><td></td></tr>";
            for (int i = 0; i < devices.size(); i++) {
                output = output + "<tr><td>" + devices.get(i).getMake() + "</td><td>" + devices.get(i).getModel() + "</td><td>"
                        + devices.get(i).getPrice() + "</td><td>" + devices.get(i).getCategory() + "</td>"
                        + "<td><button class='rem' rel='" + devices.get(i).getId() + "'><span class=\"ui-icon ui-icon-trash\"></span></button></td></tr>";
            }
            output = output + "</table></div>";
        }
        inputStream = new ByteArrayInputStream(output.getBytes("UTF-8"));
        return "success";
    }
}

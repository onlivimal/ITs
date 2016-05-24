package com.vimal.its.action.procurement;

import com.vimal.its.dao.DB;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 *
 * @author Vimal
 */
public class GetStocksByDeviceIdAction {

    private int deviceId;
    private InputStream inputStream;
    private DB db = new DB();

    public InputStream getInputStream() {
        return inputStream;
    }

    public int getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(int deviceId) {
        this.deviceId = deviceId;
    }

    public GetStocksByDeviceIdAction() {
    }

    public String execute() throws UnsupportedEncodingException {
        List ls = db.getStockByDeviceId(deviceId);
        String output = "";
        if (ls.isEmpty()) {
            output = "<h4>No stock for this device at the moment</h4>";
        } else {
            output = "<div class='myTable'><table><tr><td>Available stock</td><td>Stockid</td></tr>";

            for (int i = 0; i < ls.size(); i++) {
                output = output + "<tr><td><input type='radio' name='stockId' value='" + ls.get(i) + "' /></td><td>" + ls.get(i) + "</td></tr>";
            }
            output = output + "</table></div>";
        }
        inputStream = new ByteArrayInputStream(output.getBytes("UTF-8"));
        return "success";
    }
}

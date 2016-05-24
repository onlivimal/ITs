package com.vimal.its.action.requester;

import com.opensymphony.xwork2.ActionContext;
import com.vimal.its.dao.DB;
import com.vimal.its.dto.Device;
import com.vimal.its.dto.Request;
import com.vimal.its.dto.Stock;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;

public class GetAssignedDevicesAction {

    
    DB db = new DB();
    private InputStream inputStream;

    public InputStream getInputStream() {
        return inputStream;
    }

    public GetAssignedDevicesAction() {
    }

    public String execute() throws Exception {
        String output = "";
        String dept = ActionContext.getContext().getSession().get("emp_dept").toString();
        List<Request> requests = db.getAssignedRequestsByDept(dept);
        if (requests.isEmpty()) {
            output = "<h4>You don't have any device(s) assigned to your department.</h4>";
        } else {
            output = "<div class='myTable'><table><tr><td>Category</td><td>Make</td><td>Model</td><td>Stock Id</td><td>Seial No</td></tr>";
            for (int i = 0; i < requests.size(); i++) {
                Device device = new Device();
                Stock stock = new Stock();
                device = requests.get(i).getDevice();
                stock = requests.get(i).getStock();
                int stock_id = 0;
                String serialNo = "";
                if (stock != null){
                    stock_id = stock.getId();
                    serialNo = stock.getSerialNo();
                }
                output = output + "<tr>"
                        + "<td>" + device.getCategory() + "</td>"
                        + "<td>" + device.getMake() + "</td>"
                        + "<td>" + device.getModel() + "</td>"
                        + "<td>" + stock_id + "</td>"
                        + "<td>" + serialNo + "</td>";
                output = output + "</tr>";
            }
            output = output + "<table></div>";
        }
        inputStream = new ByteArrayInputStream(output.getBytes("UTF-8"));
        return "success";
    }
}

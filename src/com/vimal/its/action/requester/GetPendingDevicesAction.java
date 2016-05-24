package com.vimal.its.action.requester;

import com.opensymphony.xwork2.ActionContext;
import com.vimal.its.dao.DB;
import com.vimal.its.dto.Device;
import com.vimal.its.dto.Request;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;

public class GetPendingDevicesAction {


    DB db = new DB();
    private InputStream inputStream;

    public InputStream getInputStream() {
        return inputStream;
    }

    public GetPendingDevicesAction() {
    }

    public String execute() throws Exception {
        String output = "";
        String dept = ActionContext.getContext().getSession().get("emp_dept").toString();
        List<Request> requests = db.getPendingRequestsByDept(dept);
        if (requests.isEmpty()) {
            output = "<h4>You don't have any device(s) pending request.</h4>";
        } else {
            output = "<div class='myTable'><table><tr><td>Category</td><td>Make</td><td>Model</td><td>Approved</td><td>Stock Id</td></tr>";
            for (int i = 0; i < requests.size(); i++) {
                Device device = new Device();
                String approved="";
                    if(requests.get(i).getApproved() == null) approved = "<span class=\"ui-icon ui-icon-help\"></span>";
                    else if (requests.get(i).getApproved().equals("YES")) approved = "<span class=\"ui-icon ui-icon-check\"></span>";
                    else approved = "<span class=\"ui-icon ui-icon-close\"></span>";
                device = requests.get(i).getDevice();

                output = output + "<tr>"
                        + "<td>" + device.getCategory() + "</td>"
                        + "<td>" + device.getMake() + "</td>"
                        + "<td>" + device.getModel() + "</td>"
                        + "<td align=center>" + approved + "</td>"
                        + "<td align=center><span class=\"ui-icon ui-icon-help\"></span></td>";
                output = output + "</tr>";
            }
            output = output + "<table></div>";
        }
        inputStream = new ByteArrayInputStream(output.getBytes("UTF-8"));
        return "success";
    }
}

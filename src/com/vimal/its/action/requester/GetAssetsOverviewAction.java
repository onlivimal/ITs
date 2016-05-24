package com.vimal.its.action.requester;

import com.opensymphony.xwork2.ActionContext;
import com.vimal.its.dao.DB;
import com.vimal.its.dto.Device;
import com.vimal.its.dto.Request;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class GetAssetsOverviewAction {

    DB db = new DB();
    private InputStream inputStream;

    public InputStream getInputStream() {
        return inputStream;
    }

    public GetAssetsOverviewAction() {
    }

    public String execute() throws Exception {
        String output = "";
        Map<String, Integer> myMap = new HashMap<String, Integer>();
        String dept = ActionContext.getContext().getSession().get("emp_dept").toString();
        List<Request> requests = db.getAssignedRequestsByDept(dept);
        if (requests.isEmpty()) {
            output = "<h4>You don't have any device(s) assigned to your department.</h4>";
        } else {
            for (int i = 0; i < requests.size(); i++) {
                Device device = new Device();
                device = requests.get(i).getDevice();
                if (myMap.containsKey(device.getCategory())) {
                    int newVal = myMap.get(device.getCategory()) + 1;
                    myMap.remove(device.getCategory());
                    myMap.put(device.getCategory(), newVal);
                } else {
                    myMap.put(device.getCategory(), 1);
                }
            }
            output = "{";
            Iterator it = myMap.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry pairs = (Map.Entry) it.next();
                System.out.println(pairs.getKey() + " = " + pairs.getValue());
                output = output + "\"" + pairs.getKey() + "\":" + pairs.getValue() + ",";
                it.remove();
            }
            output = output.substring(0, output.length()-1);
            output = output + "}";
            System.out.println(output);
        }
        inputStream = new ByteArrayInputStream(output.getBytes("UTF-8"));
        return "success";
    }
}

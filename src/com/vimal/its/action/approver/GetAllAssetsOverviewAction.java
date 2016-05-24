package com.vimal.its.action.approver;

import com.vimal.its.dao.DB;
import com.vimal.its.dto.Device;
import com.vimal.its.dto.Request;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class GetAllAssetsOverviewAction {

    DB db = new DB();
    private InputStream inputStream;

    public InputStream getInputStream() {
        return inputStream;
    }

    public GetAllAssetsOverviewAction() {
    }

    public String execute() throws Exception {
        String output = "";
        DecimalFormat df = new DecimalFormat("#.##");
        Map<String, Float> myMap = new HashMap<String, Float>();
        //String dept = ActionContext.getContext().getSession().get("emp_dept").toString();
        List<Request> requests = db.getAllAssignedRequests();
        if (requests.isEmpty()) {
            output = "<h4>You don't have any device(s) assigned to your department.</h4>";
        } else {
            for (int i = 0; i < requests.size(); i++) {
                Device device = new Device();
                device = requests.get(i).getDevice();
                

                if (myMap.containsKey(requests.get(i).getDept())) {
                    Float newVal = myMap.get(requests.get(i).getDept()) + Float.parseFloat(device.getPrice());
                    myMap.remove(requests.get(i).getDept());
                    myMap.put(requests.get(i).getDept(), newVal);
                } else {
                    myMap.put(requests.get(i).getDept(), Float.parseFloat(device.getPrice()));
                }
            }
            output = "{";
            Iterator it = myMap.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry pairs = (Map.Entry) it.next();
                System.out.println(pairs.getKey() + " = " + df.format(pairs.getValue()));
                output = output + "\"" + pairs.getKey() + "\":" + df.format(pairs.getValue()) + ",";
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

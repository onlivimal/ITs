package com.vimal.its.action.requester;

import com.vimal.its.dao.DB;
import com.vimal.its.dto.Device;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;

public class GetDevicesByCategoryAction {

    DB db = new DB();
    private InputStream inputStream;
    private String category;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public GetDevicesByCategoryAction() {
    }

    public String execute() throws Exception {
        String output = "";
        List<Device> devices = db.getDevicesByCategory(category);
        for (int i = 0; i < devices.size(); i++) {
            output = output + "<option value='" + devices.get(i).getId() + "'>" + devices.get(i).getMake() + " " + devices.get(i).getModel() + "</option>";
        }
        inputStream = new ByteArrayInputStream(output.getBytes("UTF-8"));
        return "success";
    }
}

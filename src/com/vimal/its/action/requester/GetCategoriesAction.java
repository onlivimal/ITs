package com.vimal.its.action.requester;

import com.vimal.its.dao.DB;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;

public class GetCategoriesAction {

    DB db = new DB();

    private InputStream inputStream;

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }
    public GetCategoriesAction() {
    }

    public String execute() throws Exception {
        String output = "";
        List<String> categories = db.getDistinctCategories();

        for (int i = 0; i < categories.size(); i++) {
            output = output + "<option value='" + categories.get(i) + "'>" + categories.get(i) + "</option>";
        }
        inputStream = new ByteArrayInputStream(output.getBytes("UTF-8"));
        return "success";
    }
}

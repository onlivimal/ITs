package com.vimal.its.action.procurement;

import com.vimal.its.dao.DB;

/**
 *
 * @author Vimal
 */
public class AddNewDeviceAction {
    private String make;
    private String model;
    private String category;
    private String price;
    private DB db = new DB();
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
    public AddNewDeviceAction(){

    }

    public String execute(){
        if(db.addNewDevice(make, model, category, price))
            return "success";
        return "failure";
    }

}

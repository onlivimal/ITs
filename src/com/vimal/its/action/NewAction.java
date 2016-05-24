package com.vimal.its.action;

public class NewAction {
    
    public NewAction() {
    }
    
    public String execute() throws Exception {
        System.out.println("coming from execute");
        return "success";
    }
    
}

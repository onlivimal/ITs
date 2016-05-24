package com.vimal.its.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.vimal.its.dao.DB;
import com.vimal.its.dto.Emp;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;

public class LoginAction extends ActionSupport {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int emp_id;
    private String password;

    public LoginAction() {
    }

    @Override
    public String execute() throws Exception {
        String result = "failure";
        DB db = new DB();
        Emp emp = new Emp();
        Map session = ActionContext.getContext().getSession();
        if (!session.isEmpty()) {
            if (session.containsKey("authenticated")) {
                result = "loggedin";
                System.out.println("User is already logged in");
                return result;
            }
        }
        if (getEmp_id() != 0 && !StringUtils.isEmpty(password)) {
            if (db.verifyUser(emp_id, password)) {
                emp = db.getUserInfo(emp_id);

                session.put("authenticated", true);
                session.put("emp_name", emp.getName());
                session.put("emp_dept", emp.getDept());
                session.put("emp_id", emp.getId());
                session.put("emp_role", emp.getRoles());
                result = "success";
            }
            return result;
        }
        return result;
    }

    public int getEmp_id() {
        return emp_id;
    }

    public void setEmp_id(int emp_id) {
        this.emp_id = emp_id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

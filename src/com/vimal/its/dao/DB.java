package com.vimal.its.dao;

import com.vimal.its.dto.Device;
import com.vimal.its.dto.Emp;
import com.vimal.its.dto.Request;
import com.vimal.its.dto.Stock;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import com.vimal.its.util.HibernateUtil;

/**
 *
 * @author Vimal
 */
public class DB {

    Session session = null;
    Query query = null;

    // From here is Requester Bean 
    public List<Device> getAllDevices() {
        session = HibernateUtil.getSession();
        session.beginTransaction();
        query = session.createQuery("from Device where obsolete is null");
        List devices = query.list();
        session.getTransaction().commit();
        session.close();
        return devices;
    }

    public List<Device> getDevicesByCategory(String category) {
        session = HibernateUtil.getSession();
        session.beginTransaction();
        query = session.createQuery("from Device d where d.category = :category and obsolete is null");
        query.setParameter("category", category);
        List devices = query.list();
        session.getTransaction().commit();
        session.close();
        return devices;
    }

    public List<String> getDistinctCategories() {
        session = HibernateUtil.getSession();
        session.beginTransaction();
        query = session.createQuery("select distinct category from Device where obsolete is null");
        List<String> categories = query.list();
        session.getTransaction().commit();
        session.close();
        return categories;
    }

    public boolean submitRequestForDevice(int device_id, String dept) {
        Request request = new Request();
        request.setDept(dept);
        session = HibernateUtil.getSession();
        session.beginTransaction();
        Device device = (Device) session.get("com.vimal.its.dto.Device", device_id);
        request.setDevice(device);
        session.save(request);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    public List<Request> getAssignedRequestsByDept(String dept) {
        session = HibernateUtil.getSession();
        session.beginTransaction();
        query = session.createQuery("from Request where dept = :dept and approved = :approval and stock_id is not null");
        query.setParameter("dept", dept);
        query.setParameter("approval", "YES");
        List requests = query.list();
        session.getTransaction().commit();
        session.close();
        return requests;
    }

    public List<Request> getPendingRequestsByDept(String dept) {

        session = HibernateUtil.getSession();
        session.beginTransaction();
        query = session.createQuery("from Request where dept = :dept and (approved is null or stock_id is null )");
        query.setParameter("dept", dept);
        List requests = query.list();
        session.getTransaction().commit();
        session.close();
        return requests;
    }

    // From here is Approver Bean 
    public List<Request> getApprovedRequests() {
        session = HibernateUtil.getSession();
        session.beginTransaction();
        query = session.createQuery("from Request where approved=:approval");
        query.setParameter("approval", "YES");
        List requests = query.list();
        session.getTransaction().commit();
        session.close();
        return requests;
    }

        public List<Request> getAllAssignedRequests() {
        session = HibernateUtil.getSession();
        session.beginTransaction();
        query = session.createQuery("from Request where approved=:approval and stock_id is not null");
        query.setParameter("approval", "YES");
        List requests = query.list();
        session.getTransaction().commit();
        session.close();
        return requests;
    }

    public List<Request> getRejectedRequests() {
        session = HibernateUtil.getSession();
        session.beginTransaction();
        query = session.createQuery("from Request where approved=:approval");
        query.setParameter("approval", "NO");
        List requests = query.list();
        session.getTransaction().commit();
        session.close();
        return requests;
    }

    public List<Request> getPendingRequestsForApproval() {
        session = HibernateUtil.getSession();
        session.beginTransaction();
        query = session.createQuery("from Request where approved is null");
        List requests = query.list();
        session.getTransaction().commit();
        session.close();
        return requests;
    }

    public boolean approval(String approval, int request_id) {
        session = HibernateUtil.getSession();
        session.beginTransaction();
        Request request = (Request) session.get("com.vimal.its.dto.Request", request_id);
        request.setApproved(approval);
        session.save(request);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    // From here is Procurement Bean 
    public boolean deleteDeviceById(int id) {
        session = HibernateUtil.getSession();
        session.beginTransaction();
        Device device = (Device) session.get(Device.class, id);
        device.setObsolete("true");
        session.update(device);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    // getAllDevices already in..
    public List<Request> getRequestsToAssign() {
        session = HibernateUtil.getSession();
        session.beginTransaction();
        query = session.createQuery("from Request where approved = :approval and stock_id is null");
        query.setParameter("approval", "YES");
        List requests = query.list();
        session.getTransaction().commit();
        session.close();
        return requests;
    }

    public boolean addNewDevice(String make, String model, String category, String price) {
        Device device = new Device();
        device.setCategory(category);
        device.setMake(make);
        device.setModel(model);
        device.setPrice(price);
        device.setInfo("Default info.");

        session = HibernateUtil.getSession();
        session.beginTransaction();
        session.save(device);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    public List getStockByDeviceId(int deviceId) {
        session = HibernateUtil.getSession();
        session.beginTransaction();
        query = session.createQuery("select id from Stock where device_id = :deviceId and serialNo is not null and request_id is null");
        query.setParameter("deviceId", deviceId);
        List ls = query.list();
        session.getTransaction().commit();
        session.close();
        return ls;
    }

    public boolean assignStockToRequest(int stock_id, int request_id) {
        session = HibernateUtil.getSession();
        session.beginTransaction();
        Stock stock = (Stock) session.get(Stock.class, stock_id);
        Request request = (Request) session.get(Request.class, request_id);
        request.setStock(stock);
        stock.setRequest(request);
        session.save(request);
        session.save(stock);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    //Warehousebean
    public boolean addStock(int device_id, String serialNo) {
        Stock stock = new Stock();
        stock.setSerialNo(serialNo);
        session = HibernateUtil.getSession();
        session.beginTransaction();
        Device device = (Device) session.get(Device.class, device_id);
        stock.setDevice(device);
        session.save(stock);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    public List<Request> getExpectedShipIn() {
        session = HibernateUtil.getSession();
        session.beginTransaction();
        query = session.createQuery("from Request where approved = :approval and stock_id is null");
        query.setParameter("approval", "YES");
        List requests = query.list();
        session.getTransaction().commit();
        session.close();
        return requests;
    }

    public boolean verifyUser(int user_id, String password) {
        boolean isUser = false;
        session = HibernateUtil.getSession();
        session.beginTransaction();
        Emp emp = (Emp) session.get(Emp.class, user_id);
        session.getTransaction().commit();
        session.close();
        if (emp != null) {
            if (emp.getPassword().equals(password)) {
                isUser = true;
            }
        }
        return isUser;
    }

    public Emp getUserInfo(int user_id) {
        session = HibernateUtil.getSession();
        session.beginTransaction();
        Emp emp = (Emp) session.get(Emp.class, user_id);
        session.getTransaction().commit();
        session.close();
        return emp;
    }

}

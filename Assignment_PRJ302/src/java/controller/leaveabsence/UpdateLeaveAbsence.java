/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.leaveabsence;

import Model.BaseModel;
import Model.Employee;
import Model.LeaveRequest;
import Model.LeaveType;
import Model.User;
import controller.authentication.BaseRecordAccessControlByOwnerController;
import dal.LeaveRequestDBContext;
import dal.LeaveTypeDBContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.*;
import java.io.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Phanh
 */
public class UpdateLeaveAbsence extends BaseRecordAccessControlByOwnerController<LeaveRequest> {

    @Override
    protected LeaveRequest getModel(int id) {
        LeaveRequestDBContext db = new LeaveRequestDBContext();
        return db.get(id);
    }

    @Override
    protected String getAccessDeniedMessage(User u, LeaveRequest model) {
        return u.getDisplayname() + " is not the author of leave request " + model.getId();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp, User user) throws ServletException, IOException {
        
        LeaveRequest model = new LeaveRequest();
        int lrid = Integer.parseInt(req.getParameter("lrid"));
        LeaveRequestDBContext db = new LeaveRequestDBContext();
        LeaveTypeDBContext ltdb = new LeaveTypeDBContext();

        model = db.get(lrid);
        ArrayList<LeaveType> leaveTypes = null;
        try {
            leaveTypes = ltdb.getAllLeaveType();
        } catch (SQLException ex) {
            Logger.getLogger(UpdateLeaveAbsence.class.getName()).log(Level.SEVERE, null, ex);
        }

        ArrayList<Employee> employees = new ArrayList<>();
        employees.add(user.getEmployee());
        for (Employee staff : user.getEmployee().getStaffs()) {
            employees.add(staff);
        }
        req.setAttribute("employees", employees);

        req.setAttribute("model", model);
        req.setAttribute("leaveTypes", leaveTypes);
        req.getRequestDispatcher("/View/LeaveRequest/UpdateForm.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp, User user) throws ServletException, IOException {
        try {

            LeaveRequest lr = new LeaveRequest();
            lr.setLrid(Integer.parseInt(req.getParameter("lrid")));

            lr.setTitle(req.getParameter("title"));
            lr.setReason(req.getParameter("reason"));
            lr.setFrom(Date.valueOf(req.getParameter("from")));
            lr.setTo(Date.valueOf(req.getParameter("to")));

            Employee owner = new Employee();
            owner.setId(Integer.parseInt(req.getParameter("eid")));
            lr.setOwnerEid(owner);

            LeaveType lt = new LeaveType();
            lt.setLeaveTypeID(Integer.parseInt(req.getParameter("ltid")));
            lr.setLeaveType(lt);
            LeaveRequestDBContext db = new LeaveRequestDBContext();
            db.update(lr);

            req.getRequestDispatcher("/View/Noti/UpdateSuccessful.jsp").forward(req, resp);

        } catch (Exception ex) {
            ex.printStackTrace();
            resp.getWriter().println("Error updating leave request: " + ex.getMessage());
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp, User user, LeaveRequest model) throws ServletException, IOException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp, User user, LeaveRequest model) throws ServletException, IOException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}

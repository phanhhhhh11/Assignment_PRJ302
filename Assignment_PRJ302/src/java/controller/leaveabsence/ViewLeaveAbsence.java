/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.leaveabsence;

import Model.Employee;
import dal.LeaveRequestDBContext;
import Model.LeaveRequest;
import Model.LeaveType;
import Model.User;
import controller.authentication.BaseRequiredAuthenticationController;
import dal.LeaveTypeDBContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.lang.*;
import java.util.*;
import java.io.*;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Phanh
 */
public class ViewLeaveAbsence extends BaseRequiredAuthenticationController {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp, User user) throws ServletException, IOException {
        String action = req.getParameter("action");
        int lrid = Integer.parseInt(req.getParameter("lrid"));
        if ("delete".equalsIgnoreCase(action)) {
            LeaveRequestDBContext db = new LeaveRequestDBContext();
            LeaveRequest lr = new LeaveRequest();
            lr.setLrid(lrid);
            db.delete(lr);
            resp.sendRedirect("View/Noti/DeleteSuccessful.jsp");
        } else if ("update".equalsIgnoreCase(action)) {

            resp.sendRedirect("Update?lrid=" + lrid);
        } else {
            doGet(req, resp, user);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp, User user) throws ServletException, IOException {
        try {
            LeaveTypeDBContext leaveTypeDB = new LeaveTypeDBContext();
            LeaveRequestDBContext db = new LeaveRequestDBContext();
            ArrayList<Employee> directStaff = user.getEmployee().getDirectstaffs();
            Set<Integer> seenRequestIds = new HashSet<>();
            ArrayList<LeaveRequest> list = new ArrayList<>();

// Đơn của cấp dưới
            for (Employee e : directStaff) {
                ArrayList<LeaveRequest> subLeaves = db.getleaverequeststaff(e.getId());
                if (subLeaves != null) {
                    for (LeaveRequest lr : subLeaves) {
                        if (seenRequestIds.add(lr.getLrid())) {
                            list.add(lr);
                        }
                    }
                }
            }

// Đơn của chính supervisor
            ArrayList<LeaveRequest> ownLeaves = db.getleaverequeststaff(user.getEmployee().getId());
            if (ownLeaves != null) {
                for (LeaveRequest lr : ownLeaves) {
                    if (seenRequestIds.add(lr.getLrid())) {
                        list.add(lr);
                    }
                }
            }

            ArrayList<LeaveType> leaveTypes = leaveTypeDB.getAllLeaveType();
            req.setAttribute("leaveTypes", leaveTypes);
            req.setAttribute("leaveRequests", list);
            req.getRequestDispatcher("/View/LeaveRequest/ListForm.jsp").forward(req, resp);
        } catch (SQLException ex) {
            Logger.getLogger(ViewLeaveAbsence.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}

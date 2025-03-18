/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.leaveabsence;

import dal.LeaveRequestDBContext;
import dal.LeaveTypeDBContext;
import Model.LeaveRequest;
import Model.User;
import Model.LeaveType;
import Model.Employee;
import controller.authentication.BaseRequiredAuthenticationController;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.*;
import java.util.*;
import java.io.*;
import java.sql.*;
import java.sql.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Phanh
 */
public class CreateLeaveAbsence extends BaseRequiredAuthenticationController {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp, User user) throws ServletException, IOException {
        try {
            LeaveRequest lr = new LeaveRequest();
            lr.setTitle(req.getParameter("Title"));
            lr.setReason(req.getParameter("Reason"));
            lr.setFrom(Date.valueOf(req.getParameter("Start Date")));
            lr.setTo(Date.valueOf(req.getParameter("End Date")));
            lr.setCreatedby(user);

            Employee owner = new Employee();
            owner.setId(Integer.parseInt(req.getParameter("eid")));
            lr.setOwnerEid(owner);

            LeaveRequestDBContext db = new LeaveRequestDBContext();
            db.insert(lr);
            resp.getWriter().println("Insert successfully for " + lr.getId());
            resp.sendRedirect("View/Noti/InsertSuccessful.html");
            
        } catch (IOException e) {
            req.setAttribute("error", "Error: " + e.getMessage());
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp, User user) throws ServletException, IOException {
        try {
            ArrayList<LeaveType> leaveTypeDB = leaveTypeDBContext.getAllLeaveType();
            ArrayList<LeaveType> leaveTypes = leaveTypeDB;

            req.setAttribute("leaveTypes", leaveTypes);

            ArrayList<Employee> employees = new ArrayList<>();
            employees.add(user.getEmployee());
            for (Employee staff : user.getEmployee().getStaffs()) {
                employees.add(staff);
            }
            req.setAttribute("employees", employees);
            req.getRequestDispatcher("/View/LeaveRequest/AbsenceForm.jsp").forward(req, resp);
        } catch (SQLException ex) {
            Logger.getLogger(CreateLeaveAbsence.class.getName()).log(Level.SEVERE, null, ex);

        }
    }

    private LeaveRequestDBContext leaveRequestDBContext = new LeaveRequestDBContext();
    private LeaveTypeDBContext leaveTypeDBContext = new LeaveTypeDBContext();

}

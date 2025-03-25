/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.leaveabsence;

import dal.LeaveRequestDBContext;
import dal.LeaveTypeDBContext;
import Model.LeaveRequest;
import Model.LeaveType;
import Model.Employee;
import Model.User;
import controller.authentication.BaseRequiredAuthenticationController;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Phanh
 */
public class CreateLeaveAbsence extends BaseRequiredAuthenticationController {

    private static final Logger logger = Logger.getLogger(CreateLeaveAbsence.class.getName());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp, User user) throws ServletException, IOException {
        try {
            LeaveTypeDBContext leaveTypeDB = new LeaveTypeDBContext();
            ArrayList<LeaveType> leaveTypes = leaveTypeDB.getAllLeaveType();
            req.setAttribute("leaveTypes", leaveTypes);

            ArrayList<Employee> employees = new ArrayList<>();
            employees.add(user.getEmployee());
            for (Employee staff : user.getEmployee().getStaffs()) {
                employees.add(staff);
            }
            req.setAttribute("employees", employees);

            req.getRequestDispatcher("/View/LeaveRequest/AbsenceForm.jsp").forward(req, resp);
        } catch (Exception ex) {
            logger.log(Level.SEVERE, "Error loading form", ex);
            resp.getWriter().println("Error loading form: " + ex.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp, User user) throws ServletException, IOException {
        try {
            LeaveRequest lr = new LeaveRequest();
            lr.setTitle(req.getParameter("title"));
            lr.setReason(req.getParameter("reason"));
            lr.setFrom(Date.valueOf(req.getParameter("from")));
            lr.setTo(Date.valueOf(req.getParameter("to")));
            lr.setCreatedby(user);

            Employee owner = new Employee();
            owner.setId(Integer.parseInt(req.getParameter("eid")));
            lr.setOwnerEid(owner);

            LeaveType lt = new LeaveType();
            lt.setLeaveTypeID(Integer.parseInt(req.getParameter("ltid")));
            lr.setLeaveType(lt);

            LeaveRequestDBContext db = new LeaveRequestDBContext();
            db.insert(lr);

            req.setAttribute("message", "Leave request submitted successfully.");
            req.getRequestDispatcher("/View/Noti/InsertSuccessful.jsp").forward(req, resp);
        } catch (Exception ex) {
            logger.log(Level.SEVERE, "Error submitting leave request", ex);
            resp.getWriter().println("Error submitting leave request: " + ex.getMessage());
        }
    }

}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.leaveabsence;

import dal.LeaveRequestDBContext;
import Model.LeaveRequest;
import Model.Role;
import Model.User;
import controller.authentication.BaseRecordAccessControlByOwnerController;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.lang.*;
import java.util.*;
import java.io.*;

/**
 *
 * @author Phanh
 */
public class UpdateApproval extends BaseRecordAccessControlByOwnerController<LeaveRequest> {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp, User user) throws ServletException, IOException {
        try {

            int lrid = Integer.parseInt(req.getParameter("lrid"));
            String role="";
            for (Role r : user.getRoles()) {
                if ("Supervisor".equalsIgnoreCase(r.getName())) {
                    role = "Supervisor";
                    break;
                }
            }
            String decision = req.getParameter("action"); // approved / rejected

            LeaveRequest lr = new LeaveRequest();
            LeaveRequestDBContext db = new LeaveRequestDBContext();
            lr = db.get(lrid);

            // Cập nhật theo vai trò
            if ("Supervisor".equalsIgnoreCase(role)) {
                lr.setSupervisorApprove(decision);
            }
            if ("HR".equalsIgnoreCase(user.getEmployee().getDept().getName())) {
                lr.setHRApprove(decision);
            }

            // Cập nhật status
            String hrStatus = lr.getHRApprove();
            String supStatus = lr.getSupervisorApprove();

            if ("approve".equalsIgnoreCase(supStatus) && "approve".equalsIgnoreCase(hrStatus)) {
                lr.setStatus(2); // Both approved
            } else if (("reject".equalsIgnoreCase(supStatus) && "reject".equalsIgnoreCase(hrStatus))) {
                lr.setStatus(0); // Both rejected
            } else {
                lr.setStatus(1); // Pending or partial approval
            }

            db.updateApproval(lr);

        req.getRequestDispatcher("/View/Noti/ApprovalSuccessful.jsp").forward(req, resp);
        } catch (Exception ex) {
            ex.printStackTrace();
            resp.getWriter().println("Error updating approval: " + ex.getMessage());
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp, User user) throws ServletException, IOException {
        LeaveRequestDBContext db = new LeaveRequestDBContext();
        ArrayList<LeaveRequest> list = db.listByUser(user);
        req.setAttribute("leaveRequests", list);
        req.getRequestDispatcher("/View/LeaveRequest/ListApproval.jsp").forward(req, resp);
    }

    @Override
    protected LeaveRequest getModel(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    protected String getAccessDeniedMessage(User u, LeaveRequest model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
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

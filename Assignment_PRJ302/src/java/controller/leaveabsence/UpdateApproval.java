/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.leaveabsence;

import dal.LeaveRequestDBContext;
import Model.LeaveRequest;
import Model.Role;
import Model.User;
import controller.authentication.BaseRequiredAuthenticationController;
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
public class UpdateApproval extends BaseRequiredAuthenticationController {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp, User user) throws ServletException, IOException {
        try {
            int lrid = Integer.parseInt(req.getParameter("lrid"));
            String action = req.getParameter("action"); // approve or reject

            LeaveRequestDBContext db = new LeaveRequestDBContext();
            LeaveRequest lr = db.get(lrid);

            boolean isHR = false;
            boolean isSupervisor = false;

            for (Role role : user.getRoles()) {
                if ("HR".equalsIgnoreCase(role.getName())) {
                    isHR = true;
                }
                if ("Supervisor".equalsIgnoreCase(role.getName())) {
                    isSupervisor = true;
                }
            }

            if (isHR) {
                if ("approve".equalsIgnoreCase(action)) {
                    lr.setHRApprove("APPROVED");
                } else if ("reject".equalsIgnoreCase(action)) {
                    lr.setHRApprove("REJECTED");
                }
            }

            if (isSupervisor) {
                if ("approve".equalsIgnoreCase(action)) {
                    lr.setSupervisorApprove("APPROVED");
                } else if ("reject".equalsIgnoreCase(action)) {
                    lr.setSupervisorApprove("REJECTED");
                }
            }

            // Update status based on both approvals
            if ("REJECTED".equalsIgnoreCase(lr.getHRApprove()) || "REJECTED".equalsIgnoreCase(lr.getSupervisorApprove())) {
                lr.setStatus("0"); // Rejected
            } else if ("APPROVED".equalsIgnoreCase(lr.getHRApprove()) && "APPROVED".equalsIgnoreCase(lr.getSupervisorApprove())) {
                lr.setStatus("2"); // Approved
            } else {
                lr.setStatus("1"); // Still pending
            }

            db.update(lr);
            resp.sendRedirect("ListApproval");

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
        req.getRequestDispatcher("/View/LeaveRequest/Approval.jsp").forward(req, resp);
    }

}

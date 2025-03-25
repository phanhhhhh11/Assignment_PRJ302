/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.leaveabsence;

import Model.Employee;
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
            String decision = req.getParameter("action"); // approve / reject

            LeaveRequestDBContext db = new LeaveRequestDBContext();
            LeaveRequest lr = db.get(lrid);

            // Xác định vai trò
            boolean isDirector = db.isDirector(user.getEmployee());
            boolean isHR = "HR".equalsIgnoreCase(user.getEmployee().getDept().getName());
            boolean isSupervisor = false;

            for (Role r : user.getRoles()) {
                if ("Supervisor".equalsIgnoreCase(r.getName())) {
                    isSupervisor = true;
                    break;
                }
            }

            // Cập nhật theo vai trò
            if (isSupervisor) {
                lr.setSupervisorApprove(decision);
            }
            if (isHR) {
                lr.setHRApprove(decision);
            }

            // Cập nhật status tổng hợp
            String hrStatus = lr.getHRApprove();
            String supStatus = lr.getSupervisorApprove();

            if ("approve".equalsIgnoreCase(supStatus) && "approve".equalsIgnoreCase(hrStatus)) {
                lr.setStatus(2); // Both approved
            } else if ("reject".equalsIgnoreCase(supStatus) && "reject".equalsIgnoreCase(hrStatus)) {
                lr.setStatus(0); // Both rejected
            } else {
                lr.setStatus(1); // Pending or partial
            }

            if (isDirector) {
                lr.setStatus(2); // Director duyệt là auto approve
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
        ArrayList<LeaveRequest> list;

        boolean isDirector = db.isDirector(user.getEmployee());
        boolean isHR = "HR".equalsIgnoreCase(user.getEmployee().getDept().getName());
        boolean isSupervisor = false;

        for (Role role : user.getRoles()) {
            if ("Supervisor".equalsIgnoreCase(role.getName())) {
                isSupervisor = true;
                break;
            }
        }

        if (isDirector || isHR) {
            list = db.list();
        }
        else if (isSupervisor) {
            ArrayList<Employee> directStaff = user.getEmployee().getDirectstaffs();
            Set<Integer> seenRequestIds = new HashSet<>();
            list = new ArrayList<>();

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

        } else {
            list = db.getleaverequeststaff(user.getEmployee().getId());
        }

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

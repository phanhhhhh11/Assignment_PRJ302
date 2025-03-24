/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package controller.leaveabsence;
import dal.LeaveRequestDBContext;
import Model.LeaveRequest;
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
public class ViewLeaveAbsence extends BaseRequiredAuthenticationController{

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
            
            resp.sendRedirect("Update?lrid="+lrid);
        } else {
            doGet(req, resp, user);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp, User user) throws ServletException, IOException {
        LeaveRequestDBContext db = new LeaveRequestDBContext();
        ArrayList<LeaveRequest> list = db.listByUser(user);
        req.setAttribute("leaveRequests", list);
        req.getRequestDispatcher("/View/LeaveRequest/ListForm.jsp").forward(req, resp);
    }

}

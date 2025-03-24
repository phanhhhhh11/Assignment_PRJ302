/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package controller.authentication;

import Model.Employee;
import dal.UserDBContext;
import Model.User;
import dal.EmployeeDBContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.Cookie;
import java.lang.*;
import java.util.*;
import java.io.*;
/**
 *
 * @author Phanh
 */
public class LoginController extends HttpServlet{

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        
        String userID = req.getParameter("userID");
        String password = req.getParameter("password");
        UserDBContext db = new UserDBContext();
        User user = db.get(userID, password);
        if(user != null)
        {
            EmployeeDBContext edb = new EmployeeDBContext();
            Employee profile = edb.get(user.getEmployee().getId());
            profile.setManager(user.getEmployee().getManager());
            user.setEmployee(profile);
            HttpSession session = req.getSession();
            session.setAttribute("user", user);
            resp.sendRedirect("Dashboard");
        }
        else
        {
            resp.sendRedirect("View/Noti/LoginFailed.jsp");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("View/Login.jsp").forward(req, resp);
    }

    
}

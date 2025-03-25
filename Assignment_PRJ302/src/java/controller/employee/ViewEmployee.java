/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package controller.employee;

import Model.Employee;
import Model.Role;
import Model.User;
import controller.authentication.BaseRequiredAuthenticationController;
import dal.EmployeeDBContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.lang.*;
import java.util.*;
import java.io.*;
/**
 *
 * @author Phanh
 */
public class ViewEmployee extends BaseRequiredAuthenticationController{

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp, User user) throws ServletException, IOException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp, User user) throws ServletException, IOException {
 EmployeeDBContext db = new EmployeeDBContext();
        ArrayList<Employee> employees;

        boolean isDirector = false;
        boolean isHR = false;
        boolean isSupervisor = false;

        for (Role r : user.getRoles()) {
            if ("Director".equalsIgnoreCase(r.getName())) {
                isDirector = true;
            }
            if ("HR".equalsIgnoreCase(r.getName())) {
                isHR = true;
            }
            if ("Supervisor".equalsIgnoreCase(r.getName())) {
                isSupervisor = true;
            }
        }

//        if (isDirector || isHR) {
//            employees = db.getAllEmployees;
//        } else if (isSupervisor) {
//            employees = db.getEmployeesByManagerId(user.getEmployee().getId());
//        } else {
//            employees = new ArrayList<>(); // Nếu không có quyền
//        }

        //req.setAttribute("employees", employees);
        req.getRequestDispatcher("/View/Employee/List.jsp").forward(req, resp);
    }    }

    
    


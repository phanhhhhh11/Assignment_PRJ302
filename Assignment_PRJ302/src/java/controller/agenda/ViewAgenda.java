package controller.agenda;

import Model.Employee;
import Model.LeaveRequest;
import Model.User;
import controller.authentication.BaseRequiredAuthenticationController;
import dal.LeaveRequestDBContext;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Date;
import java.util.*;

/**
 *
 * @author Phanh
 */
public class ViewAgenda extends BaseRequiredAuthenticationController {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response,User user) throws ServletException, IOException {
        LeaveRequestDBContext leaveRequestDAO = new LeaveRequestDBContext();
        HttpSession session = request.getSession();
        
        ArrayList<Employee> staffList = user.getEmployee().getDirectstaffs();
        Map<Employee, List<LeaveRequest>> staffLeaves = new HashMap<>();

        for (Employee staff : staffList) {
            ArrayList<LeaveRequest> leaves = leaveRequestDAO.getleaverequeststaff(staff.getId());
            staffLeaves.put(staff, leaves);
        }

        // Lấy offset từ URL, dùng để điều hướng các tuần
        int weekOffset = 0;
        try {
            String offsetParam = request.getParameter("offset");
            if (offsetParam != null) {
                weekOffset = Integer.parseInt(offsetParam);
            }
        } catch (NumberFormatException ignored) {
        }

        request.setAttribute("staffLeaves", staffLeaves);
        request.setAttribute("weekOffset", weekOffset);

        RequestDispatcher dispatcher = request.getRequestDispatcher("View/Agenda/A"
                + "genda.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp, User user) throws ServletException, IOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}

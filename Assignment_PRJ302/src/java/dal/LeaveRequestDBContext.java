/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import Model.LeaveRequest;
import Model.Employee;
import Model.LeaveType;
import Model.User;
import Model.Role;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Phanh
 */
public class LeaveRequestDBContext extends DBContext<LeaveRequest> {

    public Map<Date, List<LeaveRequest>> getRequestsByMonth(int month, int year) {
        Map<Date, List<LeaveRequest>> map = new HashMap<>();
        String sql = "SELECT lr.lrid, lr.title, lr.reason, lr.[from], lr.[to], "
                + "lt.leaveTypeID, lt.leaveTypeName, e.eid, e.ename "
                + "FROM LeaveRequests lr "
                + "INNER JOIN LeaveType lt ON lr.leaveTypeID = lt.leaveTypeID "
                + "INNER JOIN Employee e ON lr.owner_eid = e.eid "
                + "WHERE lr.[from] <= ? AND lr.[to] >= ? AND status = 2";

        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            java.util.Calendar cal = java.util.Calendar.getInstance();
            cal.set(year, month - 1, 1);
            Date startDate = new Date(cal.getTimeInMillis());
            cal.set(java.util.Calendar.DAY_OF_MONTH, cal.getActualMaximum(java.util.Calendar.DAY_OF_MONTH));
            Date endDate = new Date(cal.getTimeInMillis());

            stm.setDate(1, endDate);
            stm.setDate(2, startDate);

            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                LeaveRequest lr = new LeaveRequest();
                lr.setLrid(rs.getInt("lrid"));
                lr.setTitle(rs.getString("title"));
                lr.setReason(rs.getString("reason"));
                Date from = rs.getDate("from");
                Date to = rs.getDate("to");
                lr.setFrom(from);
                lr.setTo(to);

                LeaveType lt = new LeaveType();
                lt.setLeaveTypeID(rs.getInt("leaveTypeID"));
                lt.setLeaveTypeName(rs.getString("leaveTypeName"));
                lr.setLeaveType(lt);

                Employee e = new Employee();
                e.setId(rs.getInt("eid"));
                e.setName(rs.getString("ename"));
                lr.setOwnerEid(e);

                // Thêm tất cả các ngày từ 'from' đến 'to' vào map
                java.util.Calendar loopCal = java.util.Calendar.getInstance();
                loopCal.setTime(from);
                while (!loopCal.getTime().after(to)) {
                    Date current = new Date(loopCal.getTimeInMillis());

                    // Chỉ thêm những ngày trong tháng đang xem
                    if (current.compareTo(startDate) >= 0 && current.compareTo(endDate) <= 0) {
                        map.putIfAbsent(current, new ArrayList<>());
                        map.get(current).add(lr);
                    }

                    loopCal.add(java.util.Calendar.DATE, 1);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return map;
    }

    @Override
    public ArrayList<LeaveRequest> list() {
        ArrayList<LeaveRequest> list = new ArrayList<>();
        String sql = "SELECT * FROM LeaveRequests";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                LeaveRequest lr = new LeaveRequest();
                lr.setLrid(rs.getInt("lrid"));
                lr.setTitle(rs.getString("title"));
                lr.setReason(rs.getString("reason"));
                lr.setFrom(rs.getDate("from"));
                lr.setTo(rs.getDate("to"));
                User u = new User();
                u.setUserId(rs.getString("createdby"));
                lr.setCreatedby(u);
                lr.setCreateddate(rs.getDate("createddate"));
                Employee e = new Employee();
                e.setId(rs.getInt("owner_eid"));
                lr.setOwnerEid(e);
                LeaveType lt = new LeaveType();
                lt.setLeaveTypeID(rs.getInt("leaveTypeID"));
                lr.setLeaveType(lt);
                lr.setHRApprove(rs.getString("HRApprove"));
                lr.setSupervisorApprove(rs.getString("SupervisorApprove"));
                lr.setStatus(rs.getInt("status"));
                list.add(lr);
            }
        } catch (SQLException ex) {
            Logger.getLogger(LeaveRequestDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    @Override
    public LeaveRequest get(int id) {
        String sql = "SELECT * FROM LeaveRequests WHERE lrid=?";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                LeaveRequest lr = new LeaveRequest();
                lr.setLrid(rs.getInt("lrid"));
                lr.setTitle(rs.getString("title"));
                lr.setReason(rs.getString("reason"));
                lr.setFrom(rs.getDate("from"));
                lr.setTo(rs.getDate("to"));
                User u = new User();
                u.setUserId(rs.getString("createdby"));
                lr.setCreatedby(u);
                lr.setCreateddate(rs.getDate("createddate"));
                Employee e = new Employee();
                e.setId(rs.getInt("owner_eid"));
                lr.setOwnerEid(e);
                LeaveType lt = new LeaveType();
                lt.setLeaveTypeID(rs.getInt("leaveTypeID"));
                lr.setLeaveType(lt);
                lr.setHRApprove(rs.getString("HRApprove"));
                lr.setSupervisorApprove(rs.getString("SupervisorApprove"));
                lr.setStatus(rs.getInt("status"));
                return lr;
            }
        } catch (SQLException ex) {
            Logger.getLogger(LeaveRequestDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public ArrayList<LeaveRequest> getleaverequeststaff(int eid) {
        ArrayList<LeaveRequest> leaverequests = new ArrayList<>();
        String sql = "SELECT l.* FROM Employees e left join LeaveRequests l on e.eid = l.owner_eid WHERE e.eid =?";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, eid);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                if (rs.getInt("lrid") != 0) {
                    LeaveRequest lr = new LeaveRequest();
                    lr.setLrid(rs.getInt("lrid"));
                    lr.setTitle(rs.getString("title"));
                    lr.setReason(rs.getString("reason"));
                    lr.setFrom(rs.getDate("from"));
                    lr.setTo(rs.getDate("to"));
                    User u = new User();
                    u.setUserId(rs.getString("createdby"));
                    lr.setCreatedby(u);
                    lr.setCreateddate(rs.getDate("createddate"));
                    Employee e = new Employee();
                    e.setId(rs.getInt("owner_eid"));
                    lr.setOwnerEid(e);
                    LeaveType lt = new LeaveType();
                    lt.setLeaveTypeID(rs.getInt("leaveTypeID"));
                    lr.setLeaveType(lt);
                    lr.setHRApprove(rs.getString("HRApprove"));
                    lr.setSupervisorApprove(rs.getString("SupervisorApprove"));
                    lr.setStatus(rs.getInt("status"));
                    leaverequests.add(lr);
                }
            }
            return leaverequests;
        } catch (SQLException ex) {
            Logger.getLogger(LeaveRequestDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public ArrayList<LeaveRequest> listBySubordinates(ArrayList<Employee> staffList) {
        ArrayList<LeaveRequest> all = new ArrayList<>();
        if (staffList == null) {
            return all;
        }

        for (Employee e : staffList) {
            ArrayList<LeaveRequest> requests = getleaverequeststaff(e.getId());
            if (requests != null) {
                all.addAll(requests);
            }
        }

        return all;
    }

    @Override
    public void insert(LeaveRequest model) {
        String sql = "INSERT INTO LeaveRequests (title, reason, [from], [to], createdby, createddate, owner_eid, leaveTypeID, HRApprove, SupervisorApprove, status) "
                + "VALUES (?, ?, ?, ?, ?, GETDATE(), ?, ?, NULL, NULL, 0)";
        try {
            PreparedStatement stm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stm.setString(1, model.getTitle());
            stm.setString(2, model.getReason());
            stm.setDate(3, model.getFrom());
            stm.setDate(4, model.getTo());
            stm.setString(5, model.getCreatedby().getUserId());
            stm.setInt(6, model.getOwnerEid().getId());
            stm.setInt(7, model.getLeaveType().getLeaveTypeID());
            stm.executeUpdate();

            ResultSet rs = stm.getGeneratedKeys();
            if (rs.next()) {
                model.setLrid(rs.getInt(1));
            }

            connection.commit();
        } catch (SQLException ex) {
            Logger.getLogger(LeaveRequestDBContext.class.getName()).log(Level.SEVERE, null, ex);
            try {
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void update(LeaveRequest model) {
        try {
            connection.setAutoCommit(false);
            String sql = "UPDATE [LeaveRequests]\n"
                    + "   SET [title] = ?,\n"
                    + "       [reason] = ?,\n"
                    + "       [from] = ?,\n"
                    + "       [to] = ?,\n"
                    + "       [leaveTypeID] = ?,\n"
                    + "       [owner_eid] = ?\n"
                    + " WHERE lrid = ?";

            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, model.getTitle());
            stm.setString(2, model.getReason());
            stm.setDate(3, model.getFrom());
            stm.setDate(4, model.getTo());
            stm.setInt(5, model.getLeaveType().getLeaveTypeID());
            stm.setInt(6, model.getOwnerEid().getId());
            stm.setInt(7, model.getLrid());

            stm.executeUpdate();
            connection.commit();

        } catch (SQLException ex) {
            Logger.getLogger(LeaveRequestDBContext.class.getName()).log(Level.SEVERE, null, ex);
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(LeaveRequestDBContext.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
    }

    @Override
    public void delete(LeaveRequest model) {
        String sql = "DELETE FROM LeaveRequests WHERE lrid=?";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, model.getLrid());
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(LeaveRequestDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ArrayList<LeaveRequest> listByUser(User user) {
        ArrayList<LeaveRequest> filteredList = new ArrayList<>();
        ArrayList<LeaveRequest> all = list();

        boolean isHR = false;
        boolean isSupervisor = false;
        boolean isHRSupervisor = false;

        for (Role role : user.getRoles()) {
            if ("Supervisor".equalsIgnoreCase(role.getName())) {
                isSupervisor = true;
            }
        }
        if ("HR".equalsIgnoreCase(user.getEmployee().getDept().getName())) {
            isHR = true;
        }
        if (isDirector(user.getEmployee()) || isHR) {
            filteredList = list();
            return filteredList;
        }

        for (LeaveRequest lr : all) {
            // Nhân viên thường: chỉ thấy request mình tạo ra
            if (!isHR && !isSupervisor) {
                if (lr.getCreatedby().getUserId().equals(user.getUserId())) {
                    filteredList.add(lr);
                }
            } // Supervisor thường: request của họ + nhân viên dưới quyền
            else if (isSupervisor && !isHRSupervisor) {
                if (lr.getCreatedby().getUserId().equals(user.getUserId())
                        || isMyStaff(user.getEmployee(), lr.getOwnerEid())) {
                    filteredList.add(lr);
                }
            }

        }

        return filteredList;
    }

    private boolean isMyStaff(Employee supervisor, Employee staff) {
        if (supervisor.getStaffs() == null) {
            return false;
        }
        for (Employee e : supervisor.getStaffs()) {
            if (e.getId() == staff.getId()) {
                return true;
            }
        }
        return false;
    }

    public boolean isDirector(Employee e) {
        return e.getManager() == null;
    }

    public void updateApproval(LeaveRequest lr) throws SQLException {
        String sql = "UPDATE LeaveRequests SET "
                + "HRApprove = ?, SupervisorApprove = ?, status = ? "
                + "WHERE lrid = ?";
        PreparedStatement stm = connection.prepareStatement(sql);
        stm.setString(1, lr.getHRApprove());
        stm.setString(2, lr.getSupervisorApprove());
        stm.setInt(3, lr.getStatus());
        stm.setInt(4, lr.getLrid());
        stm.executeUpdate();
    }
}

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

/**
 *
 * @author Phanh
 */
public class LeaveRequestDBContext extends DBContext<LeaveRequest> {

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
                lr.setLeaveTypeID(lt);
                lr.setHRApprove(rs.getString("HRApprove"));
                lr.setSupervisorApprove(rs.getString("SupervisorApprove"));
                lr.setStatus(String.valueOf(rs.getInt("status")));
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
                lr.setLeaveTypeID(lt);
                lr.setHRApprove(rs.getString("HRApprove"));
                lr.setSupervisorApprove(rs.getString("SupervisorApprove"));
                lr.setStatus(String.valueOf(rs.getInt("status")));
                return lr;
            }
        } catch (SQLException ex) {
            Logger.getLogger(LeaveRequestDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
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

    @Override
    public void update(LeaveRequest model) {
        String sql = "UPDATE LeaveRequests SET title=?, reason=?, [from]=?, [to]=?, leaveTypeID=?, HRApprove=?, SupervisorApprove=?, status=? WHERE lrid=?";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, model.getTitle());
            stm.setString(2, model.getReason());
            stm.setDate(3, model.getFrom());
            stm.setDate(4, model.getTo());
            stm.setInt(5, model.getLeaveType().getLeaveTypeID());
            stm.setString(6, model.getHRApprove());
            stm.setString(7, model.getSupervisorApprove());
            stm.setInt(8, model.getStatus().equals("1") ? 1 : 0);
            stm.setInt(9, model.getLrid());
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(LeaveRequestDBContext.class.getName()).log(Level.SEVERE, null, ex);
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
            if ("HR".equalsIgnoreCase(role.getName())) {
                isHR = true;
            }
            if ("Supervisor".equalsIgnoreCase(role.getName())) {
                isSupervisor = true;
                if ("HR".equalsIgnoreCase(user.getEmployee().getDept().getName())) {
                    isHRSupervisor = true;
                }
            }
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
            } // HR: tất cả trừ phòng HR + của chính họ
            else if (isHR && !isHRSupervisor) {
                if (!"HR".equalsIgnoreCase(lr.getOwnerEid().getDept().getName())
                        || lr.getCreatedby().getUserId().equals(user.getUserId())) {
                    filteredList.add(lr);
                }
            } // Supervisor phòng HR: tất cả trừ request của giám đốc
            else if (isHRSupervisor) {
                if (!isDirector(lr.getOwnerEid())
                        || lr.getCreatedby().getUserId().equals(user.getUserId())) {
                    filteredList.add(lr);
                }
            } else if (!isDirector(lr.getOwnerEid()) || lr.getCreatedby().getUserId().equals(user.getUserId())) {
                filteredList.add(lr);
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

    private boolean isDirector(Employee e) {
        return e.getManager() == null;
    }

}

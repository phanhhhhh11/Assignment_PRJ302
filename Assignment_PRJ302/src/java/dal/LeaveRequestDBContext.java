/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package dal;
import java.sql.*;
import java.sql.Date;
import Model.LeaveRequest;
import java.lang.*;
import java.util.*;
import java.io.*;
import java.sql.SQLException;
import Model.LeaveRequest;
import Model.Employee;
import Model.LeaveType;
/**
 *
 * @author Phanh
 */
public class LeaveRequestDBContext extends DBContext<LeaveRequest>{

    @Override
    public ArrayList<LeaveRequest> list() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public LeaveRequest get(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void insert(LeaveRequest model) {
        try {
            connection.setAutoCommit(false);
            String sql = "INSERT INTO LeaveRequest (title, reason, [from], [to], [status], createdby, createddate, owner_eid, leaveTypeID) " +
                     "VALUES (?, ?, ?, ?, ?, ?, GETDATE(), ?, ?)";
            PreparedStatement stm = connection.prepareStatement(sql);
            
            stm.setString(1, model.getTitle());
            stm.setString(2, model.getReason());
            stm.setDate(3, new java.sql.Date(model.getFrom().getTime()));
            stm.setDate(4, new java.sql.Date(model.getTo().getTime()));
            stm.setString(5, model.getStatus());
            stm.setString(6, model.getCreatedby().getUsername());
            stm.setInt(7, model.getOwnerEid().getId());
            stm.setInt(8, model.getLeaveTypeID().getLeaveTypeID());
            stm.executeUpdate();
            
            String sql_getid = "SELECT @@IDENTITY as lrid";
            PreparedStatement stm_getid = connection.prepareStatement(sql_getid);
            ResultSet rs = stm_getid.executeQuery();
            if (rs.next()) {
                model.setId(rs.getInt("lrid"));
            }
            
            connection.commit();
        } catch (SQLException ex) {
            
        }
    }

    @Override
    public void update(LeaveRequest model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(LeaveRequest model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import Model.LeaveType;
import java.sql.*;
import java.lang.*;
import java.util.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Phanh
 */
public class LeaveTypeDBContext extends DBContext<LeaveType> {

    public ArrayList<LeaveType> getAllLeaveType() throws SQLException {
        ArrayList<LeaveType> leaveTypes = new ArrayList<>();
        try {
            String sql = "SELECT [leaveTypeID], [typeName] FROM [LeaveType]";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                int leaveType = rs.getInt("leaveTypeID");
                String leaveTypeName = rs.getString("typeName");

                LeaveType leavetype = new LeaveType();
                leavetype.setLeaveTypeID(leaveType);
                leavetype.setLeaveTypeName(leaveTypeName);
                leaveTypes.add(leavetype);
            }
        } catch (SQLException ex) {
            Logger.getLogger(LeaveRequestDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }

        return leaveTypes;
    }

    @Override
    public ArrayList<LeaveType> list() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public LeaveType get(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody

    }

    @Override
    public void insert(LeaveType model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(LeaveType model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(LeaveType model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package Model;


import java.lang.*;
import java.util.*;
import java.io.*;
/**
 *
 * @author Phanh
 */
public class LeaveRequest {
    private String employeeId;
    private String employeeName;
    private String managerName;
    private String department;
    private String startDate;
    private String endDate;
    private String evidenceUrl;
    private String status;

    public LeaveRequest(String employeeId, String employeeName, String managerName, String department, String startDate, String endDate, String evidenceUrl, String status) {
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.managerName = managerName;
        this.department = department;
        this.startDate = startDate;
        this.endDate = endDate;
        this.evidenceUrl = evidenceUrl;
        this.status = status;
    }

    // Getters
    public String getEmployeeId() { return employeeId; }
    public String getEmployeeName() { return employeeName; }
    public String getManagerName() { return managerName; }
    public String getDepartment() { return department; }
    public String getStartDate() { return startDate; }
    public String getEndDate() { return endDate; }
    public String getEvidenceUrl() { return evidenceUrl; }
    public String getStatus() { return status; }
}

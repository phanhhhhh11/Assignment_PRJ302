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
public class LeaveRequest extends BaseModel{
    private int lrid;
    private String title;
    private String reason;
    private Date from;
    private Date to;
    private String status;
    private User createdby;
    private Date createddate;
    private Employee ownerEid;
    private LeaveType leaveTypeID;
    private String HRApprove;
    private String SupervisorApprove;

    public int getLrid() {
        return lrid;
    }

    public void setLrid(int lrid) {
        this.lrid = lrid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Date getFrom() {
        return from;
    }

    public void setFrom(Date from) {
        this.from = from;
    }

    public Date getTo() {
        return to;
    }

    public void setTo(Date to) {
        this.to = to;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public User getCreatedby() {
        return createdby;
    }

    public void setCreatedby(User createdby) {
        this.createdby = createdby;
    }

    public Date getCreateddate() {
        return createddate;
    }

    public void setCreateddate(Date createddate) {
        this.createddate = createddate;
    }

    public Employee getOwnerEid() {
        return ownerEid;
    }

    public void setOwnerEid(Employee ownerEid) {
        this.ownerEid = ownerEid;
    }

    public LeaveType getLeaveTypeID() {
        return leaveTypeID;
    }

    public void setLeaveTypeID(LeaveType leaveTypeID) {
        this.leaveTypeID = leaveTypeID;
    }

    public String getHRApprove() {
        return HRApprove;
    }

    public void setHRApprove(String HRApprove) {
        this.HRApprove = HRApprove;
    }

    public String getSupervisorApprove() {
        return SupervisorApprove;
    }

    public void setSupervisorApprove(String SupervisorApprove) {
        this.SupervisorApprove = SupervisorApprove;
    }
    
    
    


}

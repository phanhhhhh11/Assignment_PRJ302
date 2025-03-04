<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="Model.LeaveRequest" %>
<%
    List<LeaveRequest> leaveRequests = new ArrayList<>();
    leaveRequests.add(new LeaveRequest("E001", "Nguyen Van A", "Tran Van B", "IT", "2025-03-10", "2025-03-15", "#", "Pending"));
    leaveRequests.add(new LeaveRequest("E002", "Le Thi C", "Tran Van B", "HR", "2025-03-12", "2025-03-14", "#", "Approved"));
    leaveRequests.add(new LeaveRequest("E003", "Pham Van D", "Nguyen Van E", "Finance", "2025-03-20", "2025-03-25", "#", "Rejected"));
%>
<!DOCTYPE html>
<html>
    <head>
        <title>Leave Requests</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <style>
            body {
                font-family: Arial, sans-serif;
                margin: 0;
                padding: 0;
                background-color: #f4f4f4;
            }
            .container {
                max-width: 1200px;
                margin: 50px auto;
                padding: 20px;
                background: white;
                border-radius: 10px;
                box-shadow: 0 0 10px rgba(0,0,0,0.1);
            }
            h2 {
                text-align: center;
                margin-bottom: 20px;
            }
            .request-card {
                background: #fff;
                padding: 15px;
                margin: 15px 0;
                border-radius: 8px;
                box-shadow: 0 0 5px rgba(0,0,0,0.1);
                display: flex;
                flex-direction: column;
            }
            .request-info {
                padding: 10px;
            }
            .status {
                font-weight: bold;
                color: blue;
            }
            .btn-group {
                margin-top: 10px;
                text-align: center;
            }
            .btn {
                padding: 10px 15px;
                border: none;
                border-radius: 5px;
                cursor: pointer;
                margin: 5px;
            }
            .btn-approve {
                background: green;
                color: white;
            }
            .btn-reject {
                background: red;
                color: white;
            }
        </style>
    </head>
    <body>

        <div class="container">
            <h2>Leave Requests</h2>

            <% for (LeaveRequest LeaveRequest : leaveRequests) { %>
            <div class="request-card">
                <div class="request-info">
                    <p><strong>Employee ID:</strong> <%= LeaveRequest.getEmployeeId() %></p>
                    <p><strong>Name:</strong> <%= LeaveRequest.getEmployeeName() %></p>
                    <p><strong>Manager:</strong> <%= LeaveRequest.getManagerName() %></p>
                    <p><strong>Department:</strong> <%= LeaveRequest.getDepartment() %></p>
                    <p><strong>Start Date:</strong> <%= LeaveRequest.getStartDate() %></p>
                    <p><strong>End Date:</strong> <%= LeaveRequest.getEndDate() %></p>
                    <p><strong>Evidence:</strong> <a href="<%= LeaveRequest.getEvidenceUrl() %>" target="_blank">View</a></p>
                    <p><strong>Status:</strong> <span class="status"><%= LeaveRequest.getStatus() %></span></p>
                </div>
                <div class="btn-group">
                    <button class="btn btn-approve">Approve</button>
                    <button class="btn btn-reject">Reject</button>
                </div>
            </div>
            <% } %>
    

        </div>

    </body>
</html>

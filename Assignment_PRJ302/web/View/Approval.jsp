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
    <title>Leave Requests Dashboard</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <style>
        @import url('https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;600&display=swap');

        body {
            font-family: 'Poppins', sans-serif;
            background: linear-gradient(135deg, #3F72AF, #112D4E);
            margin: 0;
            padding: 20px;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }

        .dashboard-container {
            width: 90%;
            max-width: 1100px;
            background: rgba(255, 255, 255, 0.2);
            backdrop-filter: blur(10px);
            padding: 20px;
            border-radius: 15px;
            box-shadow: 0 8px 16px rgba(0, 0, 0, 0.2);
            text-align: center;
        }

        h2 {
            color: white;
            margin-bottom: 20px;
            font-weight: 600;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 10px;
            border-radius: 10px;
            overflow: hidden;
        }

        th, td {
            padding: 12px;
            text-align: center;
            border-bottom: 1px solid rgba(255, 255, 255, 0.2);
            color: white;
        }

        th {
            background: rgba(255, 255, 255, 0.3);
            font-weight: 600;
        }

        tr:hover {
            background: rgba(255, 255, 255, 0.3);
            transition: 0.3s ease-in-out;
        }

        .status {
            font-weight: bold;
            padding: 5px 10px;
            border-radius: 10px;
            display: inline-block;
        }

        .pending { background: rgba(255, 165, 0, 0.3); color: #FFA500; }
        .approved { background: rgba(0, 128, 0, 0.3); color: #008000; }
        .rejected { background: rgba(255, 0, 0, 0.3); color: #FF0000; }

        .btn {
            padding: 8px 12px;
            border: none;
            border-radius: 10px;
            cursor: pointer;
            font-weight: bold;
            transition: 0.3s;
            display: flex;
            align-items: center;
            justify-content: center;
            gap: 5px;
        }

        .btn-approve { background: green; color: white; }
        .btn-reject { background: red; color: white; }
        
        .btn:hover {
            transform: scale(1.1);
        }
    </style>
</head>
<body>

<div class="dashboard-container">
    <h2>Leave Requests Dashboard</h2>
    <table>
        <tr>
            <th>Employee ID</th>
            <th>Name</th>
            <th>Manager</th>
            <th>Department</th>
            <th>Start Date</th>
            <th>End Date</th>
            <th>Evidence</th>
            <th>Status</th>
            <th>Actions</th>
        </tr>
        <% for (LeaveRequest LeaveRequest : leaveRequests) { %>
        <tr>
            <td><%= LeaveRequest.getEmployeeId() %></td>
            <td><%= LeaveRequest.getEmployeeName() %></td>
            <td><%= LeaveRequest.getManagerName() %></td>
            <td><%= LeaveRequest.getDepartment() %></td>
            <td><%= LeaveRequest.getStartDate() %></td>
            <td><%= LeaveRequest.getEndDate() %></td>
            <td><a href="<%= LeaveRequest.getEvidenceUrl() %>" target="_blank">View</a></td>
            <td class="status 
                <% if ("Pending".equals(LeaveRequest.getStatus())) { %> pending <% } %>
                <% if ("Approved".equals(LeaveRequest.getStatus())) { %> approved <% } %>
                <% if ("Rejected".equals(LeaveRequest.getStatus())) { %> rejected <% } %>">
                <%= LeaveRequest.getStatus() %>
            </td>
            <td>
                <button class="btn btn-approve">Approve</button>
                <button class="btn btn-reject">Reject</button>
            </td>
        </tr>
        <% } %>
    </table>
</div>

</body>
</html>

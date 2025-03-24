<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <title>Leave Request Approval</title>
        <style>

            body {
                margin: 0;
                padding: 0;
                font-family: 'Poppins', sans-serif; /* Áp dụng Poppins */
                background: #f0f2f5; /* Nền xám nhạt cho toàn bộ trang */
            }

            h3{
                font-family: 'Poppins', sans-serif;
            }
            .header {
                display: flex;
                justify-content: space-between;
                align-items: center;
                padding: 20px 20px;
                background: #2c2c2c;
            }

            .header .logo {
                font-size: 24px;
                font-weight: 700;
                color: #fff;
            }

            .header .nav {
                display: flex;
                gap: 20px;
            }

            .header .nav a {
                color: #ccc;
                text-decoration: none;
                font-size: 16px;
                font-weight: 400;
            }

            .header .nav a:hover {
                color: #fff;
            }

            .header .user {
                display: flex;
                align-items: center;
                gap: 10px;
            }

            .header .user img {
                width: 30px;
                height: 30px;
                border-radius: 50%;
            }

            .header .user span {
                font-weight: 400;
            }

            .top-section {
                background: #1a1a1a;
                padding: 50px;
            }

            .main-content {
                padding: 20px;
                background: #f0f2f5;
            }
            .greeting {
                font-size: 24px;
                font-weight: 700;
                color: #fff;
                margin-bottom: 20px;
            }
            .card-grid {
                display: grid;
                grid-template-columns: repeat(5, 1fr);
                gap: 20px;
                margin-bottom: 20px;
            }
            .card {
                background: #fff;
                color: #333;
                padding: 20px;
                border-radius: 10px;
                text-align: center;
                box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
            }
            .status {
                font-weight: bold;
                padding: 4px 12px;
                border-radius: 12px;
                display: inline-block;
                margin-bottom: 12px;
            }
            .status.rejected {
                background-color: #f2f2f2;
                color: #000;
            }
            .status.pending {
                background-color: #ffcc00;
                color: #000;
            }
            .status.approved {
                background-color: #00cc66;
                color: #fff;
            }
            .info-label {
                font-family: 'Poppins', sans-serif;
                font-weight: bold;
                color: #aaa;
            }
            .action-buttons form {
                display: inline-block;
                margin-top: 12px;
            }
            .action-buttons button {
                padding: 8px 16px;
                border: none;
                border-radius: 8px;
                margin-right: 6px;
                cursor: pointer;
                font-weight: bold;
            }
            .approve-btn {
                background-color: #28a745;
                color: white;
            }
            .reject-btn {
                background-color: #dc3545;
                color: white;
            }
        </style>
    </head>
    <body>
        <div class="header">
            <div class="logo">MAGIX</div>
            <div class="nav">
                <a href="#">Employees</a>
                <a href="View">View</a>
                <a href="Approval">Approval</a>
                <a href="Create">Leaves</a> 
                <a href="Update">Update</a>
            </div>
            <div class="user">
                <span>Andrew Niles</span>
            </div>
        </div>

        <div class="top-section">
            <div class="greeting">
                Hello Andrew, <br>Good Morning
            </div>
        </div>
        <div class="main-content">
            <div class="card-grid">
                <c:forEach var="lr" items="${leaveRequests}">
                    <div class="card">
                        <h3>${lr.title}</h3>
                        <div class="status
                             <c:choose>
                                 <c:when test="${lr.status == '2'}">approved</c:when>
                                 <c:otherwise>pending</c:otherwise>
                             </c:choose>">
                            <c:choose>
                                <c:when test="${lr.status == '2'}">APPROVED</c:when>
                                <c:otherwise>PENDING</c:otherwise>
                            </c:choose>
                        </div>
                        <p><span class="info-label">Reason:</span> ${lr.reason}</p>
                        <p><span class="info-label">From:</span> ${lr.from}</p>
                        <p><span class="info-label">To:</span> ${lr.to}</p>
                        <p><span class="info-label">Created by:</span> ${lr.createdby.userId}</p>
                        <p><span class="info-label">Leave Type:</span> ${lr.leaveType.leaveTypeID}</p>
                        <div class="action-buttons">
                            <form method="post" action="View">
                                <input type="hidden" name="lrid" value="${lr.lrid}" />
                                <input type="hidden" name="action" value="update" />
                                <button class="update-btn" type="submit">Update</button>
                            </form>

                            <form method="post" action="View" onsubmit="return confirm('Are you sure?');">
                                <input type="hidden" name="lrid" value="${lr.lrid}" />
                                <input type="hidden" name="action" value="delete" />
                                <button class="delete-btn" type="submit">Delete</button>
                            </form>

                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
    </body>
</html>

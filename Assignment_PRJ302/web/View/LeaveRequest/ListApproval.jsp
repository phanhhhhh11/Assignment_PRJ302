<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Leave Request Approval</title>
        <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;600;700&display=swap" rel="stylesheet">
        <style>
            .body {
                margin: 0;
                padding: 0;
                font-family: 'Poppins', sans-serif;
                background: #f0f2f5;
            }

            .header {
                background-color: #1c1c1c;
                color: white;
                padding: 15px 35px;
                display: flex;
                justify-content: space-between;
                align-items: center;
            }

            .header .logo {
                font-size: 24px;
                font-weight: 700;
                color: #fff;
            }

            .header .nav {
                display: flex;
                gap: 20px;
                align-items: center;
            }

            .header .nav a {
                color: white;
                text-decoration: none;
                font-weight: 500;
                padding: 7px 17px;
                border-radius: 20px;
            }

            .header .nav a:hover {
                background-color: rgba(255,255,255,0.1);
            }

            .user-profile {
                position: relative;
            }
            .user-profile .name {
                cursor: pointer;
                display: flex;
                align-items: center;
                gap: 10px;
                background-color: #374151;
                padding: 6px 12px;
                border-radius: 20px;
            }
            .user-profile .user-box {
                display: none;
                position: absolute;
                top: 45px;
                right: 0;
                background: #fff;
                color: #000;
                padding: 15px;
                border-radius: 10px;
                box-shadow: 0 2px 8px rgba(0,0,0,0.2);
                width: 200px;
                z-index: 10;
            }
            .user-profile:hover .user-box {
                display: block;
            }

            .top-section {
                background: #1a1a1a;
                padding: 70px;

            }

            .greeting {
                font-size: 40px;
                font-weight: 900;
                color: #fff;
                margin-bottom: 30px;
            }

            .main-content {
                padding: 20px;
                background: #f0f2f5;
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
    <body class="body">
        <div class="header">
            <div class="logo">MAGIX</div>
            <div class="nav">
                <a href="Dashboard">Dashboard</a>
                <a href="View">View</a>
                <a href="Create">Leaves</a>
                <c:if test="${sessionScope.approvalAccess}">
                    <a href="Approval">Approval</a>
                </c:if>
                <c:if test="${sessionScope.approvalAccess}">
                    <a href="Agenda">Agenda</a>
                </c:if>
            </div>
            <div class="user-profile">
                <div class="name">
                    <span>${sessionScope.user.userId}</span>
                </div>
                <div class="user-box">
                    <p><strong>Username: </strong> ${sessionScope.user.username}</p>
                    <p><strong>Role:</strong>
                        <c:forEach var="r" items="${sessionScope.user.roles}" varStatus="loop">
                            ${r.name} <c:if test="${!loop.last}">, </c:if>
                        </c:forEach>
                    </p>
                    <p><a href="#">Logout</a></p>
                </div>
            </div>
        </div>
        <div class="top-section">
            <div class="greeting">
                <p>Hello <label>${sessionScope.user.username}</label></p>
                <label>Good Morning</label>

            </div>
        </div>
        <div class="main-content">
            <div class="card-grid">
                <c:forEach var="lr" items="${leaveRequests}">
                    <div class="card">
                        <h3 >${lr.title}</h3>
                        <div class="status
                             <c:choose>
                                 <c:when test="${lr.status == '0'}">rejected</c:when>
                                 <c:when test="${lr.status == '2'}">approved</c:when>
                                 <c:otherwise>pending</c:otherwise>
                             </c:choose>">
                            <c:choose>
                                <c:when test="${lr.status == '0'}">REJECTED</c:when>
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
                            <form method="post" action="Approval">
                                <input type="hidden" name="lrid" value="${lr.lrid}" />
                                <input type="hidden" name="action" value="approve" />
                                <button type="submit" class="approve-btn">Approve</button>
                            </form>
                            <form method="post" action="Approval">
                                <input type="hidden" name="lrid" value="${lr.lrid}" />
                                <input type="hidden" name="action" value="reject" />
                                <button type="submit" class="reject-btn">Reject</button>
                            </form>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
    </body>
</html>

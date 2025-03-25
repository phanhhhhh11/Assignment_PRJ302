<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Dashboard</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/View/css/dashboard.css" type="text/css"/>
        <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;600;700&display=swap" rel="stylesheet">

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
            <div class="stats">
                <div class="stat-card">
                    <div class="icon">👥</div>
                    <h3>Total Employees</h3>
                    <p>200/200</p>
                </div>
                <div class="stat-card">
                    <div class="icon">🌴</div>
                    <h3>On Leave</h3>
                    <p>12/200</p>
                </div>
                <div class="stat-card">
                    <div class="icon">🆕</div>
                    <h3>New Joinee</h3>
                    <p>15/200</p>
                </div>
                <div class="stat-card">
                    <div class="icon">😊</div>
                    <h3>Happiness Rate</h3>
                    <p>80%</p>
                </div>
                <div class="stat-card">
                    <div class="icon">🏢</div>
                    <h3>200</h3>
                    <p class="small">23% Remote</p>
                    <p class="small">77% Office</p>
                </div>
            </div>

            <div class="chart-section">
                <div class="attendance-chart">
                    <h3>Attendance Overview</h3>
                    <div class="chart-placeholder">Chart Placeholder (On Time, Late Arrival, Absent)</div>
                </div>
                <div class="news-events">
                    <h3>News & Events</h3>
                    <div class="event">
                        <div class="date">18 <span>FEB</span></div>
                        <div class="details">Board meeting: Attend all project...</div>
                    </div>
                    <div class="event">
                        <div class="date">10 <span>FEB</span></div>
                        <div class="details">Updated company policy: It is long established...</div>
                    </div>
                    <div class="event">
                        <div class="date">08 <span>FEB</span></div>
                        <div class="details">Updated company policy: It is long established...</div>
                    </div>
                    <div class="event">
                        <div class="date">05 <span>FEB</span></div>
                        <div class="details">Updated leave policy: It is long established...</div>
                    </div>
                </div>
            </div>


        </div>
    </body>
</html>
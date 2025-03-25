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
                <a href="${pageContext.request.contextPath}/Logout" style="color: red;">Logout</a>

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
                </div>
            </div>
        </div>
        <div class="top-section">
            <div class="greeting">
                <p>Hello <label>${sessionScope.user.username}</label></p>
                <label>Good Morning</label>

            </div>
        </div>


    </body>
</html>
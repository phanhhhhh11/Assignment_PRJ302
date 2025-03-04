<%-- 
    Document   : Dashboard
    Created on : Mar 4, 2025, 8:33:24 PM
    Author     : Phanh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%--
    String role = (String) session.getAttribute("role");

    if (role == null) {
        response.sendRedirect("login.jsp"); // Nếu chưa đăng nhập, chuyển về trang login
        return;
    }
--%>
<!DOCTYPE html>
<html>
    <style>
        body {
            font-family: Inter , sans-serif;
            margin: 0;
            padding: 0;
            box-sizing: border-box;
            color: white;
        }

        .header {
            width:100%;
            margin:auto;

            border-radius: 25px;
            position:relative;
            text-align: center;
        }
        .header img {
            width: 100%;
            height: auto;
        }
        .header-text {
            position: absolute;
            top: 50%;
            left: 50%;
            transform: translate(-50%, -50%);
            background: rgba(0, 0, 0, 0.5);
            color: white;
            padding: 10px 20px;
            font-size: 30px;
        }

        .grid {
            display: flex;
            justify-content: center;
            flex-wrap: wrap;
            width: 100%;
            max-width: 80%;
            margin: auto;
            gap: 20px;
            align-items: stretch;
            position: relative;
            top: -100px;
        }
        .card {
            background: #3F72AF;
            padding: 30px;
            border-radius: 8px;
            text-align: center;
            width: 250px;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
        }
        .card img {
            width: 100%;
            height: 250px;
            object-fit: cover;
            border-radius: 8px;
        }
        .card button {
            background: #112D4E;
            border: none;
            padding: 10px;
            margin-top: 10px;
            cursor: pointer;
            border-radius: 5px;
            width: 100%;
            color: white
        }
    </style>
</head>
<body>


    <div class="header">
        <img src="Image/Banner3.jpg" alt="Architecture">
        <div class="header-text">Hi User</div>
    </div>

    <div class="grid">
        <div class="card">
            <img src="Image/Form.jpg" alt="Request Form">
            <h2>Request Form</h2>
            <p>Fill out the leave absence form</p>
            <button>Go</button>
        </div>
        <div class="card">
            <img src="Image/Status.jpg" alt="Check Status">
            <h2>Check Status</h2>
            <p>Check the status of the form</p>
            <button>Go</button>
        </div>

        <div class="card">
            <img src="Image/Fix.jpg" alt="Fix Form">
            <h2>Fix Form</h2>
            <p>Fix the request form</p>
            <button>Go</button>
        </div>
        <%--
        <% if ("manager".equals(role)) { %>
        <div class="card">
            <img src="../Image/Approval.jpg" alt="Approval">
            <h2>Approval</h2>
            <p>Approve employee leave forms</p>
            <button class="btn">Go</button>
        </div>
        
        <div class="card">
            <img src="../Image/Agenda.jpg" alt="Agenda">
            <h2>Agenda</h2>
            <p>Check Agenda</p>
            <button class="btn">Go</button>
        </div>
        
        <% } %>
        
        <% if ("HR".equals(role)) { %>
        <div class="card">
            <img src="../Image/Approval.jpg.jpg" alt="Approval">
            <h2>Approval</h2>
            <p>Approve employee leave forms</p>
            <button class="btn">Go</button>
        </div>

            <% } %>
            
             <% if ("director".equals(role)) { %>
            <div class="card">
                <img src="../Image/Approval.jpg" alt="Approval">
                <h2>Approval</h2>
                <p>Approve employee leave forms</p>
                <button class="btn">Go</button>
            </div>
            
            <div class="card">
                <img src="../Image/Agenda.jpg" alt="Agenda">
                <h2>Agenda</h2>
                <p>Check Agenda</p>
                <button class="btn">Go</button>
            </div>
            
            <% } %>
        --%>

        <div class="card">
            <img src="Image/Approval.jpg" alt="Approval">
            <h2>Approval</h2>
            <p>Approve employee leave forms</p>
            <button class="btn">Go</button>
        </div>

        <div class="card">
            <img src="Image/Agenda.jpg" alt="Agenda">
            <h2>Agenda</h2>
            <p>Check Agenda</p>
            <button class="btn">Go</button>
        </div>


    </div>
</body>
</html>

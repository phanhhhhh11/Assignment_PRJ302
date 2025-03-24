<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <title>Update Leave Request</title>
        <link href="https://fonts.googleapis.com/css2?family=Poppins&display=swap" rel="stylesheet">
        <style>
            body {
                font-family: 'Poppins', sans-serif;
                background-color: #f2f2f2;
                padding: 30px;
            }
            .form-container {
                max-width: 600px;
                margin: auto;
                background: white;
                padding: 30px;
                border-radius: 12px;
                box-shadow: 0 4px 10px rgba(0,0,0,0.1);
            }
            h2 {
                text-align: center;
                margin-bottom: 25px;
            }
            label {
                display: block;
                margin-top: 15px;
                font-weight: 600;
            }
            input[type="text"], input[type="date"], select, textarea {
                width: 100%;
                padding: 10px;
                margin-top: 5px;
                border: 1px solid #ccc;
                border-radius: 6px;
            }
            textarea {
                resize: vertical;
            }
            button {
                margin-top: 25px;
                padding: 12px 20px;
                border: none;
                background-color: #007bff;
                color: white;
                font-weight: bold;
                border-radius: 8px;
                cursor: pointer;
                width: 100%;
            }
            button:hover {
                background-color: #2c2c2c;
            }
        </style>
    </head>
    <body>
        <div class="form-container">
            <h2>Update Leave Request</h2>
            <form action="Update" method="post" >
                <input type="hidden" name="lrid" value="${model.lrid}" />

                <label for="title">Title</label>
                <input type="text" name="title" id="title" value="${requestScope.model.title}" required />

                <label for="reason">Reason</label>
                <textarea name="reason" id="reason" rows="4" required>${requestScope.model.reason}</textarea>

                <label for="from">From Date</label>
                <input type="date" name="from" id="from" value="${requestScope.model.from}" required />

                <label for="to">To Date</label>
                <input type="date" name="to" id="to" value="${requestScope.model.to}" required />

                <label class="formbold-form-label" for="eid">Choose The Employee</label>
                    <select name="eid" id="eid" class="formbold-form-select" required>
                        <option value="" disabled selected>Select Employee</option>
                        <c:forEach items="${requestScope.employees}" var="e">
                            <option value="${e.id}">${e.name}</option>
                        </c:forEach>
                    </select>
                
                <label for="leaveTypeID">Leave Type</label>
                <select name="ltid" id="ltid" required>
                    <c:forEach var="ltype" items="${requestScope.leaveTypes}">
                        <option value="${ltype.leaveTypeID}">${ltype.leaveTypeName}</option>
                    </c:forEach>
                </select>
                <button type="submit">Update Request</button>
            </form>
        </div>
    </body>
</html>

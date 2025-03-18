<%-- 
    Document   : request_form
    Created on : Mar 4, 2025, 8:33:43 PM
    Author     : Phanh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Leave of Absence Form</title>
        <style>
            @import url('https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;600;700&display=swap');
            * {
                margin: 0;
                padding: 0;
                box-sizing: border-box;
            }
            body {
                font-family: "Poppins", sans-serif;
            }
            .formbold-main-wrapper {
                display: flex;
                align-items: center;
                justify-content: center;
                padding: 48px;
            }

            .formbold-form-wrapper {
                margin: 0 auto;
                max-width: 550px;
                width: 100%;
                background: white;
                padding: 20px;
                border-radius: 8px;
                box-shadow: 0px 3px 8px rgba(0, 0, 0, 0.1);
            }

            .formbold-form-input {
                width: 100%;
                padding: 10px;
                border-radius: 5px;
                border: 1px solid #DDE3EC;
                background: #FFFFFF;
                font-size: 16px;
                color: #07074D;
                outline: none;
                resize: none;
                margin-bottom: 15px;
                font-family: "Poppins", sans-serif;
            }
            
            .formbold-form-label {
                color: #07074D;
                font-size: 14px;
                display: block;
                margin-bottom: 5px;
                
            }

            .formbold-btn {
                font-family: "Poppins", sans-serif;
                text-align: center;
                width: 100%;
                font-size: 16px;
                border-radius: 5px;
                padding: 20px;
                border: none;
                font-weight: 500;
                background-color:#2c2c2c;
                color: white;
                cursor: pointer;
                margin-top: 15px;
            }
            .formbold-btn:hover {
                box-shadow: 0px 3px 8px rgba(0, 0, 0, 0.05);
            }

            .formbold-form-select {
                font-family: "Poppins", sans-serif;
                width: 100%;
                padding: 10px;
                border-radius: 5px;
                border: 1px solid #DDE3EC;
                background: #FFFFFF;
                font-size: 16px;
                color: #07074D;
                outline: none;
                margin-bottom: 15px;
            }
        </style>
    </head>
    <body>
        <div class="formbold-main-wrapper">
            <div class="formbold-form-wrapper">
                <form action="Create" method="POST" enctype="multipart/form-data">
                    <label class="formbold-form-label" for="title">Title</label>
                    <input type="text" name="title" id="title" class="formbold-form-input" required placeholder="e.g. Annual Leave Request">

                    <label class="formbold-form-label" for="reason">Reason</label>
                    <textarea name="reason" id="reason" class="formbold-form-input" required placeholder="Please provide the reason for your leave"></textarea>

                    <label class="formbold-form-label" for="reason">Choose Leave Type</label>
                    <select name="leaveTypeID" id="leaveTypeID" class="formbold-form-select" required>
                        <option value="" disabled selected>Select Leave Type</option>
                        <c:forEach items="${requestScope.leaveTypes}" var="leaveType">
                            <option value="${leaveType.leaveTypeID}">${leaveType.leaveTypeName}</option>
                        </c:forEach>
                    </select>
                    
                    <label class="formbold-form-label" for="reason">Choose The Employee</label>
                    <select name="eid" id="eid" class="formbold-form-select" required >
                        <option value="" disabled selected>Select Employee</option>
                        <c:forEach items="${requestScope.employees}" var="e">
                            <option value="${e.id}">${e.name}</option>
                        </c:forEach>
                    </select>
                    
                    <label class="formbold-form-label" for="start_date">Start Date</label>
                    <input type="date" name="Start Date" id="start_date" class="formbold-form-input" required>

                    <label class="formbold-form-label" for="end_date">End Date</label>
                    <input type="date" name="End Date" id="end_date" class="formbold-form-input" required>

                    <label class="formbold-form-label" for="leaveTypeID">Leave Type</label>
                    

                    <button class="formbold-btn" type="submit">Submit Request</button>
                </form>
            </div>
        </div>
    </body>
</html>
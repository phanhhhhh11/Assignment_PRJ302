<%-- 
    Document   : AbsenceForm
    Created on : Mar 4, 2025, 8:33:43 PM
    Author     : Phanh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Leave of Absence Form</title>
        <style>
            @import url('https://fonts.googleapis.com/css2?family=Inter:wght@400;500;600;700&display=swap');
            * {
                margin: 0;
                padding: 0;
                box-sizing: border-box;
            }
            body {
                font-family: "Inter", sans-serif;
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
            }
            
            .formbold-form-label {
                color: #07074D;
                font-size: 14px;
                display: block;
                margin-bottom: 5px;
            }

            .formbold-btn {
                text-align: center;
                width: 100%;
                font-size: 16px;
                border-radius: 5px;
                padding: 12px;
                border: none;
                font-weight: 500;
                background-color: #6A64F1;
                color: white;
                cursor: pointer;
                margin-top: 15px;
            }
            .formbold-btn:hover {
                box-shadow: 0px 3px 8px rgba(0, 0, 0, 0.05);
            }
        </style>
    </head>
    <body>
        <div class="formbold-main-wrapper">
            <div class="formbold-form-wrapper">
                <form action="submit_absence_form" method="POST">
                    <label class="formbold-form-label" for="employee_id">Employee ID</label>
                    <input type="text" name="employee_id" id="employee_id" class="formbold-form-input" required>
                    
                    <label class="formbold-form-label" for="name">Name</label>
                    <input type="text" name="name" id="name" class="formbold-form-input" required>
                    
                    <label class="formbold-form-label" for="manager">Manager</label>
                    <input type="text" name="manager" id="manager" class="formbold-form-input" required>
                    
                    <label class="formbold-form-label" for="department">Department</label>
                    <input type="text" name="department" id="department" class="formbold-form-input" required>
                    
                    <label class="formbold-form-label" for="start_date">Start Date</label>
                    <input type="date" name="start_date" id="start_date" class="formbold-form-input" required>
                    
                    <label class="formbold-form-label" for="end_date">End Date</label>
                    <input type="date" name="end_date" id="end_date" class="formbold-form-input" required>
                    
                    <label class="formbold-form-label" for="evidence">Evidence (optional)</label>
                    <input type="file" name="evidence" id="evidence" class="formbold-form-input">
                    
                    <label class="formbold-form-label" for="name">Owner</label>
                    <input type="text" name="Owner" id="Owner" class="formbold-form-input" required>
                    
                    <button class="formbold-btn" type="submit">Submit Request</button>
                </form>
            </div>
        </div>
    </body>
</html>

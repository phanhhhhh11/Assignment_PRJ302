<%-- 
    Document   : InsertSuccessful
    Created on : Mar 19, 2025, 8:53:59 AM
    Author     : Phanh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Inserted</title>
        <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;600;700&display=swap" rel="stylesheet">

        <style>
            /* CSS để làm đẹp giao diện */
            h1{
                font-family: "Poppins", sans-serif;
                font-weight: 700;
                color: #f4f4f4
            }
            body {
                font-family: "Poppins", sans-serif;
                display: flex;
                justify-content: center;
                align-items: center;
                height: 100vh;
                margin: 0;
                background-color: #f4f4f4;
            }
            .error-container {
                background-color: #2c2c2c;
                padding: 120px 150px;
                border-radius: 8px;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
                text-align: center;
            }
            .error-message {
                color: #fff;
                font-size: 18px;
                margin-bottom: 20px;
            }
            .back-button {
                padding: 10px 20px;
                background-color: #fff;
                color: black;
                border: none;
                border-radius: 5px;
                cursor: pointer;
                text-decoration: none;
            }
            .back-button:hover {
                background-color: #f0f2f5;
            }
        </style>
    </head>
    <body>
        <div class="error-container">
            <h1 >Update Successful</h1>
            <a href="${pageContext.request.contextPath}/Dashboard" class="back-button">Back to Dashboard</a>
        </div>
    </body>
</html>

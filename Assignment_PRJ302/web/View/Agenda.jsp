<%-- 
    Document   : Agenda
    Created on : Mar 5, 2025, 6:15:58 AM
    Author     : Phanh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
   
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Agenda</title>
    <style>
        body {
            font-family: "Poppins", sans-serif;
            text-align: center;
            background-color: #f4f4f4;
            margin: 20px;
        }
        h2 {
            color: #333;
        }
        .container {
            width: 90%;
            max-width: 900px;
            margin: auto;
            background: white;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }
        th, td {
            border: 1px solid #ddd;
            padding: 12px;
            text-align: center;
        }
        th {
            background-color: #007bff;
            color: white;
        }
        .working {
            background-color: #28a745;
            color: white;
        }
        .leave {
            background-color: #dc3545;
            color: white;
        }
        button {
            background-color: #007bff;
            color: white;
            padding: 10px 15px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            margin-top: 10px;
        }
        button:hover {
            background-color: #0056b3;
        }
        input {
            padding: 8px;
            margin: 5px;
            border: 1px solid #ddd;
            border-radius: 5px;
        }
    </style>
    <script>
        function fetchData() {
            const startDate = document.getElementById("startDate").value;
            const endDate = document.getElementById("endDate").value;
            
            if (!startDate || !endDate) {
                alert("Vui lòng chọn ngày bắt đầu và ngày kết thúc");
                return;
            }
            
            const exampleData = [
                { name: "Mr A", attendance: ["working", "working", "leave", "working", "leave", "working", "working", "working", "working"] },
                { name: "Mr B", attendance: ["working", "working", "working", "working", "working", "leave", "leave", "working", "working"] },
                { name: "Mr C", attendance: ["working", "working", "leave", "working", "working", "working", "working", "working", "working"] },
                { name: "Mr D", attendance: ["working", "working", "working", "leave", "working", "working", "working", "working", "working"] }
            ];
            
            const tableBody = document.getElementById("agendaBody");
            tableBody.innerHTML = "";
            
            exampleData.forEach(row => {
                let tr = document.createElement("tr");
                let tdName = document.createElement("td");
                tdName.textContent = row.name;
                tr.appendChild(tdName);
                
                row.attendance.forEach(status => {
                    let td = document.createElement("td");
                    td.className = status === "working" ? "working" : "leave";
                    td.textContent = status === "working" ? "✔" : "✖";
                    tr.appendChild(td);
                });
                tableBody.appendChild(tr);
            });
        }
    </script>
</head>
<body>
    <div class="container">
        <h2>Agenda</h2>
        <label>Start Date: <input type="date" id="startDate"></label>
        <label>End Date: <input type="date" id="endDate"></label>
        <button onclick="fetchData()">Load Data</button>
        
        <table>
            <thead>
                <tr>
                    <th>Nhân sự</th>
                    <th>Ngày 1</th>
                    <th>Ngày 2</th>
                    <th>Ngày 3</th>
                    <th>Ngày 4</th>
                    <th>Ngày 5</th>
                    <th>Ngày 6</th>
                    <th>Ngày 7</th>
                    <th>Ngày 8</th>
                    <th>Ngày 9</th>
                </tr>
            </thead>
            <tbody id="agendaBody">
            </tbody>
        </table>
    </div>
</body>
</html>


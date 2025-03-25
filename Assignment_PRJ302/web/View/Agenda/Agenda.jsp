<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="Model.Employee" %>
<%@ page import="Model.LeaveRequest" %>
<%@ page import="java.util.*, java.time.*, java.time.format.*" %>

<%
    Map<Employee, List<LeaveRequest>> staffLeaves = (Map<Employee, List<LeaveRequest>>) request.getAttribute("staffLeaves");
    int weekOffset = request.getAttribute("weekOffset") != null ? (Integer) request.getAttribute("weekOffset") : 0;

    // Tính ngày đầu tuần (thứ 2) + danh sách các ngày trong tuần
    LocalDate today = LocalDate.now().plusWeeks(weekOffset);
    LocalDate monday = today.with(DayOfWeek.MONDAY);
    List<LocalDate> weekDates = new ArrayList<>();
    for (int i = 0; i < 7; i++) {
        weekDates.add(monday.plusDays(i));
    }

    DateTimeFormatter dayFmt = DateTimeFormatter.ofPattern("dd/MM");
    DateTimeFormatter monthYearFmt = DateTimeFormatter.ofPattern("MMMM yyyy", Locale.ENGLISH);
%>

<html>
<head>
    <title>Agenda - Weekly Absence</title>
    <style>
        table { border-collapse: collapse; width: 100%; text-align: center; }
        th, td { border: 1px solid #ccc; padding: 10px; }
        .absent { background-color: #ffaaaa; }
        .nav-buttons { text-align: center; margin: 20px 0; }
        .nav-buttons a { text-decoration: none; padding: 8px 15px; background: #eee; margin: 0 10px; border-radius: 5px; color: black; }
        h2 { text-align: center; }
    </style>
</head>
<body>
<div class="nav-buttons">
    <a href="Dashboard" style="background-color: #cce5ff; color: black;">&larr; Quay lại Dashboard</a>
</div>
<h2>Lịch nghỉ trong tuần - <%= monday.format(monthYearFmt) %></h2>

<div class="nav-buttons">
    <a href="Agenda?offset=<%= weekOffset - 1 %>">&laquo; Tuần trước</a>
    <a href="Agenda?offset=0">Tuần này</a>
    <a href="Agenda?offset=<%= weekOffset + 1 %>">Tuần sau &raquo;</a>
</div>

<table>
    <tr>
        <th>Nhân viên</th>
        <% for (LocalDate date : weekDates) { %>
            <th>
                <%= date.getDayOfWeek().getDisplayName(TextStyle.SHORT, Locale.ENGLISH) %><br>
                <%= date.format(dayFmt) %>
            </th>
        <% } %>
    </tr>

    <% for (Map.Entry<Employee, List<LeaveRequest>> entry : staffLeaves.entrySet()) {
        Employee staff = entry.getKey();
        List<LeaveRequest> leaves = entry.getValue();
    %>
    <tr>
        <td><%= staff.getName() %></td>
        <% for (LocalDate day : weekDates) {
            boolean isAbsent = false;

            for (LeaveRequest leave : leaves) {
                if (leave.getFrom() != null && leave.getTo() != null) {
                    LocalDate from = leave.getFrom().toLocalDate();
                    LocalDate to = leave.getTo().toLocalDate();

                    if ((day.isEqual(from) || day.isAfter(from)) && (day.isEqual(to) || day.isBefore(to))) {
                        isAbsent = true;
                        break;
                    }
                }
            }
        %>
        <td class="<%= isAbsent ? "absent" : "" %>"></td>
        <% } %>
    </tr>
    <% } %>
</table>

</body>
</html>
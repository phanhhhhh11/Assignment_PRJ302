<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Dashboard</title>
    
    <style>
        body {
            margin: 0;
            padding: 0;
            font-family: 'Poppins', sans-serif; /* Áp dụng Poppins */
            background: #f0f2f5; /* Nền xám nhạt cho toàn bộ trang */
        }

        .header {
            display: flex;
            justify-content: space-between;
            align-items: center;
            padding: 20px 20px;
            background: #2c2c2c; 
        }

        .header .logo {
            font-size: 24px;
            font-weight: 700; 
            color: #fff;
        }

        .header .nav {
            display: flex;
            gap: 20px;
        }

        .header .nav a {
            color: #ccc;
            text-decoration: none;
            font-size: 16px;
            font-weight: 400; 
        }

        .header .nav a:hover {
            color: #fff;
        }

        .header .user {
            display: flex;
            align-items: center;
            gap: 10px;
        }

        .header .user img {
            width: 30px;
            height: 30px;
            border-radius: 50%;
        }

        .header .user span {
            font-weight: 400; 
        }

        .top-section {
            background: #1a1a1a; 
            padding: 50px;
        }

        .greeting {
            font-size: 24px;
            font-weight: 700; 
            color: #fff;
            margin-bottom: 20px;
        }

        .main-content {
            padding: 20px;
            background: #f0f2f5;
        }

        .stats {
            display: grid;
            grid-template-columns: repeat(5, 1fr);
            gap: 20px;
            margin-bottom: 20px;
        }

        .stat-card {
            background: #fff;
            color: #333;
            padding: 20px;
            border-radius: 10px;
            text-align: center;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
        }

        .stat-card .icon {
            font-size: 24px;
            margin-bottom: 10px;
        }

        .stat-card h3 {
            font-size: 16px;
            font-weight: 400; /* Poppins Regular cho nhãn */
            margin-bottom: 5px;
        }

        .stat-card p {
            font-size: 24px;
            font-weight: 600; /* Poppins SemiBold cho số liệu lớn */
        }

        .stat-card p.small {
            font-size: 14px;
            font-weight: 400; /* Poppins Regular cho số liệu nhỏ */
        }

        .chart-section {
            display: grid;
            grid-template-columns: 2fr 1fr;
            gap: 20px;
            margin-bottom: 20px;
        }

        .attendance-chart {
            background: #fff;
            color: #333;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
        }

        .attendance-chart h3 {
            font-size: 18px;
            font-weight: 600; /* Poppins SemiBold cho tiêu đề */
            margin-bottom: 20px;
        }

        .attendance-chart .chart-placeholder {
            height: 200px;
            background: #f0f0f0;
            border-radius: 5px;
            display: flex;
            align-items: center;
            justify-content: center;
            color: #999;
            font-weight: 400; /* Poppins Regular cho placeholder */
        }

        .news-events {
            background: #fff;
            color: #333;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
        }

        .news-events h3 {
            font-size: 18px;
            font-weight: 600; /* Poppins SemiBold cho tiêu đề */
            margin-bottom: 20px;
        }

        .news-events .event {
            display: flex;
            justify-content: space-between;
            margin-bottom: 10px;
            padding-bottom: 10px;
            border-bottom: 1px solid #eee;
        }

        .news-events .event .date {
            font-size: 24px;
            font-weight: 700; /* Poppins Bold cho ngày */
            color: #999;
        }

        .news-events .event .date span {
            font-size: 14px;
            font-weight: 400; /* Poppins Regular cho tháng */
        }

        .news-events .event .details {
            flex-grow: 1;
            margin-left: 10px;
            font-weight: 400; /* Poppins Regular cho nội dung sự kiện */
        }

        .bottom-section {
            display: grid;
            grid-template-columns: 1fr 2fr 1fr;
            gap: 20px;
        }

        .job-applications, .interviews, .project-overview {
            background: #fff;
            color: #333;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
        }

        .job-applications h3, .interviews h3, .project-overview h3 {
            font-size: 18px;
            font-weight: 600; /* Poppins SemiBold cho tiêu đề */
            margin-bottom: 20px;
        }

        .job-applications .application, .interviews .interview {
            display: flex;
            align-items: center;
            margin-bottom: 15px;
        }

        .job-applications .application img, .interviews .interview img {
            width: 40px;
            height: 40px;
            border-radius: 50%;
            margin-right: 10px;
        }

        .job-applications .application .details, .interviews .interview .details {
            flex-grow: 1;
            font-weight: 400; /* Poppins Regular cho nội dung */
        }

        .job-applications .application .details div:first-child {
            font-weight: 600; /* Poppins SemiBold cho tên */
        }

        .interviews .interview .details div:first-child {
            font-weight: 600; /* Poppins SemiBold cho tên */
        }

        .interviews .tabs {
            display: flex;
            gap: 10px;
            margin-bottom: 20px;
        }

        .interviews .tabs button {
            padding: 5px 15px;
            border: none;
            border-radius: 5px;
            background: #eee;
            cursor: pointer;
            font-weight: 400; /* Poppins Regular cho tab */
        }

        .interviews .tabs button.active {
            background: #333;
            color: #fff;
            font-weight: 600; 
        }

        .project-overview .stat {
            margin-bottom: 15px;
        }

        .project-overview .stat p {
            font-size: 24px;
            font-weight: 700; 
        }

        .project-overview .stat span {
            font-size: 14px;
            color: #999;
            font-weight: 400; 
        }

        @media (max-width: 768px) {
            .stats, .chart-section, .bottom-section {
                grid-template-columns: 1fr;
            }
        }
    </style>
</head>
<body>
    <div class="header">
        <div class="logo">MAGIX</div>
        <div class="nav">
            <a href="#">Dashboard</a>
            <a href="#">Employees</a>
            <a href="#">Jobs</a>
            <a href="Approval">Approval</a>
            <a href="Create">Leaves</a> 
        </div>
        <div class="user">
            <img src="https://via.placeholder.com/30" alt="User">
            <span>Andrew Niles</span>
        </div>
    </div>

    <div class="top-section">
        <div class="greeting">
            Hello Andrew, <br>Good Morning
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

        <div class="bottom-section">
            <div class="job-applications">
                <h3>Recent Job Applications</h3>
                <div class="application">
                    <img src="https://via.placeholder.com/40" alt="Applicant">
                    <div class="details">
                        <div>Guy Hawkins</div>
                        <div>USA</div>
                        <div>UI/UX Designer</div>
                    </div>
                </div>
                <div class="application">
                    <img src="https://via.placeholder.com/40" alt="Applicant">
                    <div class="details">
                        <div>Floyd Miles</div>
                        <div>Japan</div>
                        <div>Python Developer</div>
                    </div>
                </div>
                <div class="application">
                    <img src="https://via.placeholder.com/40" alt="Applicant">
                    <div class="details">
                        <div>Robert Fox</div>
                        <div>Germany</div>
                        <div>Laravel Developer</div>
                    </div>
                </div>
                <div class="application">
                    <img src="https://via.placeholder.com/40" alt="Applicant">
                    <div class="details">
                        <div>Kristin Jones</div>
                        <div>Canada</div>
                        <div>Product Designer</div>
                    </div>
                </div>
            </div>
            <div class="interviews">
                <h3>Upcoming Interviews</h3>
                <div class="tabs">
                    <button class="active">Today</button>
                    <button>Tomorrow</button>
                </div>
                <div class="interview">
                    <img src="https://via.placeholder.com/40" alt="Candidate">
                    <div class="details">
                        <div>Guy Hawkins</div>
                        <div>UI/UX Designer</div>
                    </div>
                    <div>10:00 - 11:00</div>
                </div>
                <div class="interview">
                    <img src="https://via.placeholder.com/40" alt="Candidate">
                    <div class="details">
                        <div>Floyd Miles</div>
                        <div>Python Developer</div>
                    </div>
                    <div>10:00 - 12:00</div>
                </div>
            </div>
            <div class="project-overview">
                <h3>Project Overview</h3>
                <div class="stat">
                    <p>125</p>
                    <span>Total Projects</span>
                </div>
                <div class="stat">
                    <p>13</p>
                    <span>Projects on HOLD</span>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
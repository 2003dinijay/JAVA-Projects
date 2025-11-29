<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="Model.Internship" %>
<%@ page import="DAO.InternshipDAO" %>
<%@ page import="Model.Application" %>
<%@ page import="DAO.ApplicationDAO" %>
<%@ page import="java.text.SimpleDateFormat" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Student Dashboard - Internship Management System</title>
    <link rel="stylesheet" href="css/styles.css">
</head>
<body>
<%
    if(session == null || session.getAttribute("userId") == null ||
            !session.getAttribute("role").equals("Student")) {
        response.sendRedirect("login.jsp?error=not_logged_in");
        return;
    }

    int studentId = (int) session.getAttribute("userId");
    String studentName = (String) session.getAttribute("userName");

    InternshipDAO internshipDAO = new InternshipDAO();
    List<Internship> internships = internshipDAO.getAllInternships();

    ApplicationDAO applicationDAO = new ApplicationDAO();
    List<Application> applications = applicationDAO.getApplicationsByStudent(studentId);

    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
%>

<div class="container">
    <div class="header">
        <h2>Student Dashboard</h2>
        <div class="user-info">
            <p>Welcome, <%= studentName %></p>
            <a href="logout" class="btn btn-small">Logout</a>
        </div>
    </div>

    <% if(request.getParameter("applied") != null) { %>
    <div class="success-message">
        Application submitted successfully!
    </div>
    <% } %>

    <div class="dashboard-content">
        <div class="section">
            <h3>Apply for Internships</h3>
            <a href="applyInternship.jsp" class="btn">Apply Now</a>
        </div>

        <div class="section">
            <h3>My Applications</h3>
            <% if(applications != null && !applications.isEmpty()) { %>
            <table class="data-table">
                <thead>
                <tr>
                    <th>Internship ID</th>
                    <th>Status</th>
                </tr>
                </thead>
                <tbody>
                <% for(Application app : applications) { %>
                <tr>
                    <td><%= app.getInternshipId() %></td>
                    <td><%= app.getStatus() %></td>
                </tr>
                <% } %>
                </tbody>
            </table>
            <% } else { %>
            <p>No applications yet.</p>
            <% } %>
        </div>

        <div class="section">
            <h3>Available Internships</h3>
            <% if(internships != null && !internships.isEmpty()) { %>
            <table class="data-table">
                <thead>
                <tr>
                    <th>Title</th>
                    <th>Deadline</th>
                </tr>
                </thead>
                <tbody>
                <% for(Internship internship : internships) { %>
                <tr>
                    <td><%= internship.getTitle() %></td>
                    <td><%= dateFormat.format(internship.getDeadline()) %></td>
                </tr>
                <% } %>
                </tbody>
            </table>
            <% } else { %>
            <p>No internships available.</p>
            <% } %>
        </div>
    </div>
</div>
</body>
</html>
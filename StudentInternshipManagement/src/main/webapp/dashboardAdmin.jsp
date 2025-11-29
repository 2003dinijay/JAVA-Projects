<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="Model.User" %>
<%@ page import="DAO.UserDAO" %>
<%@ page import="Model.Internship" %>
<%@ page import="DAO.InternshipDAO" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Admin Dashboard - Internship Management System</title>
    <link rel="stylesheet" href="css/styles.css">
</head>
<body>

<%
    if(session == null || session.getAttribute("userId") == null ||
            !session.getAttribute("role").equals("Admin")) {
        response.sendRedirect("login.jsp?error=not_logged_in");
        return;
    }

    String adminName = (String) session.getAttribute("userName");

    UserDAO userDAO = new UserDAO();
    List<User> users = userDAO.getAllUsers();

    InternshipDAO internshipDAO = new InternshipDAO();
    List<Internship> internships = internshipDAO.getAllInternships();
%>

<div class="container">
    <div class="header">
        <h2>Admin Dashboard</h2>
        <div class="user-info">
            <p>Welcome, <%= adminName %></p>
            <a href="logout" class="btn btn-small">Logout</a>
        </div>
    </div>

    <div class="dashboard-content">
        <div class="section">
            <h3>System Statistics</h3>
            <div class="stats">
                <div class="stat-item">
                    <h4>Users</h4>
                    <p><%= users != null ? users.size() : 0 %></p>
                </div>
                <div class="stat-item">
                    <h4>Internships</h4>
                    <p><%= internships != null ? internships.size() : 0 %></p>
                </div>
            </div>
        </div>

        <div class="section">
            <h3>Manage Applications</h3>
            <a href="viewApplications.jsp" class="btn">View Applications</a>
        </div>

        <div class="section">
            <h3>User Management</h3>
            <% if(users != null && !users.isEmpty()) { %>
            <table class="data-table">
                <thead>
                <tr>
                    <th>Name</th>
                    <th>Email</th>
                    <th>Role</th>
                </tr>
                </thead>
                <tbody>
                <% for(User user : users) { %>
                <tr>
                    <td><%= user.getName() %></td>
                    <td><%= user.getEmail() %></td>
                    <td><%= user.getRole() %></td>
                </tr>
                <% } %>
                </tbody>
            </table>
            <% } else { %>
            <p>No users found.</p>
            <% } %>
        </div>
    </div>
</div>
</body>
</html>
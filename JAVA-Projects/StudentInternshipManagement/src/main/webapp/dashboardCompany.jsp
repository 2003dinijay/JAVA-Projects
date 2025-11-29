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
    <title>Company Dashboard - Internship Management System</title>
    <link rel="stylesheet" href="css/styles.css">
</head>
<body>
<%
    if(session == null || session.getAttribute("userId") == null ||
            !session.getAttribute("role").equals("Company")) {
        response.sendRedirect("login.jsp?error=not_logged_in");
        return;
    }

    int companyId = (int) session.getAttribute("userId");
    String companyName = (String) session.getAttribute("userName");

    InternshipDAO internshipDAO = new InternshipDAO();
    List<Internship> internships = internshipDAO.getInternshipsByCompany(companyId);

    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
%>

<div class="container">
    <div class="header">
        <h2>Company Dashboard</h2>
        <div class="user-info">
            <p>Welcome, <%= companyName %></p>
            <a href="logout" class="btn btn-small">Logout</a>
        </div>
    </div>

    <% if(request.getParameter("posted") != null) { %>
    <div class="success-message">
        Internship posted successfully!
    </div>
    <% } else if(request.getParameter("deleted") != null) { %>
    <div class="success-message">
        Internship deleted successfully!
    </div>
    <% } %>

    <% if(request.getParameter("error") != null) { %>
    <div class="error-message">
        <% if(request.getParameter("error").equals("delete_failed")) { %>
        Failed to delete internship.
        <% } else if(request.getParameter("error").equals("not_found")) { %>
        Internship not found.
        <% } %>
    </div>
    <% } %>

    <div class="dashboard-content">
        <div class="section">
            <h3>Post New Internship</h3>
            <a href="postInternship.jsp" class="btn">Post Internship</a>
        </div>

        <div class="section">
            <h3>My Internships</h3>
            <% if(internships != null && !internships.isEmpty()) { %>
            <table class="data-table">
                <thead>
                <tr>
                    <th>Title</th>
                    <th>Deadline</th>
                    <th>Actions</th>
                </tr>
                </thead>
                <tbody>
                <% for(Internship internship : internships) { %>
                <tr>
                    <td><%= internship.getTitle() %></td>
                    <td><%= dateFormat.format(internship.getDeadline()) %></td>
                    <td>
                        <a href="postInternship?action=edit&id=<%= internship.getId() %>" class="btn btn-small">Edit</a>
                        <a href="postInternship?action=delete&id=<%= internship.getId() %>" class="btn btn-small btn-danger" onclick="return confirm('Are you sure you want to delete this internship?')">Delete</a>
                    </td>
                </tr>
                <% } %>
                </tbody>
            </table>
            <% } else { %>
            <p>No internships posted yet.</p>
            <% } %>
        </div>
    </div>
</div>
</body>
</html>
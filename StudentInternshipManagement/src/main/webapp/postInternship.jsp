<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Post Internship - Internship Management System</title>
    <link rel="stylesheet" href="css/styles.css">
    <script src="js/validations.js"></script>
</head>
<body>
<%
    if(session == null || session.getAttribute("userId") == null ||
            !session.getAttribute("role").equals("Company")) {
        response.sendRedirect("login.jsp?error=not_logged_in");
        return;
    }
%>

<div class="container">
    <h2>Post Internship</h2>

    <% if(request.getAttribute("error") != null) { %>
    <div class="error-message">
        <%= request.getAttribute("error") %>
    </div>
    <% } %>

    <form action="postInternship" method="post" onsubmit="return validateInternshipForm()">
        <div class="form-group">
            <label for="title">Title:</label>
            <input type="text" id="title" name="title" required>
        </div>
        <div class="form-group">
            <label for="description">Description:</label>
            <textarea id="description" name="description" rows="5" required></textarea>
        </div>
        <div class="form-group">
            <label for="deadline">Deadline:</label>
            <input type="date" id="deadline" name="deadline" required>
        </div>
        <button type="submit" class="btn">Post Internship</button>
    </form>

    <div class="links">
        <p><a href="dashboardCompany.jsp">Back to Dashboard</a></p>
    </div>
</div>
</body>
</html>
<%--
  Created by IntelliJ IDEA.
  User: iamcy
  Date: 5/9/2025
  Time: 3:59 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login - Internship Management System</title>
    <link rel="stylesheet" href="css/styles.css">
    <script src="js/validations.js"></script>
</head>
<body>
<div class="container">
    <h2>Login</h2>
    <% if(request.getParameter("error") != null) { %>
    <div class="error-message">
        <% if(request.getParameter("error").equals("1")) { %>
        Invalid email or password.
        <% } else if(request.getParameter("error").equals("not_logged_in")) { %>
        Please login to access the page.
        <% } else if(request.getParameter("error").equals("unauthorized")) { %>
        You are not authorized to access this resource.
        <% } %>
    </div>
    <% } %>

    <% if(request.getParameter("registered") != null) { %>
    <div class="success-message">
        Registration successful! Please login.
    </div>
    <% } %>

    <form action="login" method="post" onsubmit="return validateLogin()">
        <div class="form-group">
            <label for="email">Email:</label>
            <input type="email" id="email" name="email" required>
        </div>
        <div class="form-group">
            <label for="password">Password:</label>
            <input type="password" id="password" name="password" required>
        </div>
        <button type="submit" class="btn">Login</button>
    </form>

    <div class="links">
        <p>Don't have an account? <a href="registerStudent.jsp">Register</a></p>
        <p><a href="index.jsp">Back to Home</a></p>
    </div>
</div>
</body>
</html>

<%--
  Created by IntelliJ IDEA.
  User: iamcy
  Date: 5/9/2025
  Time: 4:00 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Register - Internship Management System</title>
    <link rel="stylesheet" href="css/styles.css">
    <script src="js/validations.js"></script>
</head>
<body>
<div class="container">
    <h2>Register</h2>

    <% if(request.getAttribute("error") != null) { %>
    <div class="error-message">
        <%= request.getAttribute("error") %>
    </div>
    <% } %>

    <form action="register" method="post" onsubmit="return validateRegistration()">
        <div class="form-group">
            <label for="name">Full Name:</label>
            <input type="text" id="name" name="name" required>
        </div>
        <div class="form-group">
            <label for="email">Email:</label>
            <input type="email" id="email" name="email" required>
        </div>
        <div class="form-group">
            <label for="password">Password:</label>
            <input type="password" id="password" name="password" required>
        </div>
        <div class="form-group">
            <label for="confirm-password">Confirm Password:</label>
            <input type="password" id="confirm-password" name="confirm-password" required>
        </div>
        <div class="form-group">
            <label for="role">Role:</label>
            <select id="role" name="role" required>
                <option value="Student">Student</option>
                <option value="Company">Company</option>
            </select>
        </div>
        <button type="submit" class="btn">Register</button>
    </form>

    <div class="links">
        <p>Already have an account? <a href="login.jsp">Login</a></p>
        <p><a href="index.jsp">Back to Home</a></p>
    </div>
</div>
</body>
</html>

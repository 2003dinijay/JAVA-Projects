<%-- 
    Document   : dashboard
    Created on : Apr 25, 2025, 9:40:33 AM
    Author     : thari
--%>

<%@page import="model.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="/library/style.css" />
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Dashboard</h1>
        
        
        
        <% 
            User user = (User) session.getAttribute("user"); 
            if (user == null) { 
            System.out.println("login.jsp trigered");
              response.sendRedirect("/library/login.jsp"); 
              return;
            } 
            
          %> 
          
          <h2>Welcome, <%= user.getUsername() %> [<%= user.getRole() %>]</h2> 
          <div>
            <% if ("Student".equals(user.getRole())) { %> 
            <a href="searchBook.jsp">Search & Reserve Books</a> 
            <% } else if ("Admin".equals(user.getRole())) { %> 
            <a href="addBook.jsp">Add Books</a> |  
            <a href="viewReservations.jsp">View Reservations</a> |
            <% } %> 
            <a href="logout">Logout</a>   
          </div>
            
        
        
        
        
        
    </body>
</html>

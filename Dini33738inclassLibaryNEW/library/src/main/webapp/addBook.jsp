<%-- 
    Document   : addBokk
    Created on : Apr 25, 2025, 9:42:44 AM
    Author     : thari
--%>

<%@page import="model.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="/library/style.css" />
        <title>JSP Page</title>
    </head>
    <body>
        <h1>add Book</h1>
        <%
//            User user = (User)request.getSession().getAttribute("user");
//            if (!(user.getRole().endsWith("Admin"))){
//            response.sendRedirect("error.jsp");
//            return;
//            }
        %>
        
        <form action="addBook" method="post" onsubmit="return validateBookForm()"> 
    Title: <input type="text" name="title" id="title"/><br/> 
    Author: <input type="text" name="author" id="author"/><br/> 
    <input type="submit" value="Add Book"/> 
</form> 

 
        <script src="js/validation.js" ></script>
        
    </body>
</html>

<%-- 
    Document   : reserveForm
    Created on : Apr 11, 2025, 4:15:17 PM
    Author     : thari
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="/library/style.css" />
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Reserve Form</h1>
        <form action="reserve" method="post"> 
            <input type="hidden" name="bookId" value="${param.bookId}"/> 
            Student Name: <input type="text" name="studentName"/><br/> 
            Student ID: <input type="text" name="studentId"/><br/> 
            <input type="submit" value="Reserve"/> 
        </form>
    </body>
</html>

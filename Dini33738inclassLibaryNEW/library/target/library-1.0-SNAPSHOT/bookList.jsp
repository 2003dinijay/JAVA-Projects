<%-- 
    Document   : bookList
    Created on : Apr 11, 2025, 4:14:24 PM
    Author     : thari
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="/library/style.css" />
        <title>Book Page</title>
    </head>
    <body>
        <h1>Book List</h1>
        
        <c:forEach var="book" items="${bookList}"> 
            <p>${book.title} - ${book.author} - ${book.status} 
            <a href="reserveForm.jsp?bookId=${book.id}">Reserve</a></p> 
        </c:forEach> 
    </body>
</html>

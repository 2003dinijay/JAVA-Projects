<%-- 
    Document   : viewReservations
    Created on : Apr 25, 2025, 9:43:22 AM
    Author     : thari
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="model.Reservation"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="/library/style.css" />
        <title></title>
    </head>
    <body>
        <h1>Reservation</h1>
        
        <%
            List<Reservation> reList = (List<Reservation>) request.getAttribute("reservationList");
            if (reList == null) {
                response.sendRedirect("/library/reserve");
                return;
            }
            %>
        
        
        <c:forEach var="r" items="${reservationList}"> 
            <p>${r.studentName} reserved Book ID: ${r.bookId} on ${r.reservationDate}</p> 
        </c:forEach> 
    </body>
</html>

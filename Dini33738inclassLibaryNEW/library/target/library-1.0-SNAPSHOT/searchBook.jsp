<%-- 
    Document   : searchBook
    Created on : Apr 11, 2025, 4:14:03 PM
    Author     : thari
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="/library/style.css" />
        <title>Search Book</title>
    </head>
    <body>
        <h1>Search Book</h1>
<!--        <form action="search" method="post"> 
            <input type="text" name="title" placeholder="Enter book title"/> 
            <input type="submit" value="Search"/> 
        </form> -->
        <script src="js/validation.js"></script> 
        <input type="text" id="searchBox" onkeyup="searchBooks()" placeholder="Search books..."> 
        <br/>
        <br/>
        <div id="results"></div>
    </body> 
</html>

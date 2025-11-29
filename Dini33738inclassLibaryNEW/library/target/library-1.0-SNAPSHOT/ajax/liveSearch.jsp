<%-- 
    Document   : liveSearch
    Created on : May 1, 2025, 12:04:36 AM
    Author     : thari
--%>

<%@page import="java.util.List"%>
<%@page import="dao.BookDAO"%>
<%@page import="model.Book"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

        <% 
        String q = request.getParameter("q"); 
        List<Book> books = new BookDAO().searchBooks(q); 
        for (Book book : books) { 
        %> 
        <form action="/library/reserve"  method="POST">
            <p><%= book.getTitle() %> - <%= book.getAuthor() %></p>
            <p><%= book.getStatus() %></p>  
            <input value="<%= book.getId() %>" hidden name="bookId"/> 
            <input type="submit" value="Reserve"/>
        </form> 
        <% } %> 

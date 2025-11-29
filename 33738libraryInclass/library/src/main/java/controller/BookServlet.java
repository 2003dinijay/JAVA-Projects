/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.BookDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Book;


@WebServlet(name = "BookServlet", urlPatterns = {"/search","/addBook"})
public class BookServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        if (request.getRequestURI().contains("/search")) {
        System.out.println("search");
        
        String title = request.getParameter("title");
        BookDAO bookDao = new BookDAO();
        List<Book> bookSet = bookDao.searchBooks(title);
        request.setAttribute("bookList", bookSet);
        request.getRequestDispatcher("bookList.jsp").forward(request, response);
        }
        else if( request.getRequestURI().contains("/addBook")){
            
        String title = request.getParameter("title"); 
        String author = request.getParameter("author"); 
        
        
    if (title == null || title.trim().isEmpty() || author == null || author.trim().isEmpty()) { 
        request.setAttribute("error", "Invalid input!"); 
        request.getRequestDispatcher("addBook.jsp").forward(request, response); 
        return; 
        }     

        
        
        
        
        
        
        
        Book book = new Book(); 
        book.setTitle(title); 
        book.setAuthor(author); 
        book.setStatus("Available"); 
 
        BookDAO dao = new BookDAO(); 
        dao.addBook(book); 
 
        response.sendRedirect("dashboard.jsp"); 
            
        }
        
        // Retrieve title from form, call DAO, forward to bookList.jsp
    }

}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.ReservationDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Reservation;
import model.User;


@WebServlet(name = "ReservationServlet", urlPatterns = {"/reserve"})
public class ReservationServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ReservationDAO rdao = new ReservationDAO();
        List<Reservation> resList = rdao.getReserveBooks();
        request.setAttribute("reservationList", resList);
        
        request.getRequestDispatcher("viewReservations.jsp").forward(request, response);

        // Get form data, reserve book, update status, redirect to success.jsp
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
            User user = (User) request.getSession().getAttribute("user");
        
            if (user != null) {  
                ReservationDAO rdao = new ReservationDAO();
                Reservation rese = new Reservation();

                rese.setBookId(Integer.parseInt(request.getParameter("bookId")));
                rese.setStudentId(String.valueOf(user.getId()));
                rese.setStudentName(user.getUsername());

                if(rdao.reserveBook(rese) == 0){
                    response.getWriter().println("Book is not Availble");
                }else{
                    response.sendRedirect("success.jsp");
                }  
                
            } else { 
                response.sendRedirect("error.jsp"); 
            }
        
            
             
        

        
    }


}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import com.google.protobuf.Timestamp;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Book;
import model.Reservation;

/**
 *
 * @author thari
 */
public class ReservationDAO {
    
            private String dbDriver = "com.mysql.cj.jdbc.Driver"; 
        private String jdbcURL = "jdbc:mysql://localhost:3306/libarydb"; 
        private String jdbcUsername = "root"; 
        private String jdbcPassword = "root"; 

        private boolean driverLoad(){
                    System.out.println("Loading driver ...");
            try {
                Class.forName(dbDriver);
                System.out.println("Driver loaded!");
            } catch (ClassNotFoundException e) {
                System.out.println("Driver Not found");
                return false;
            }
                return true;
        }
    
    
    
    
    public int reserveBook(Reservation reservation) { 
            driverLoad();
            BookDAO bookDao = new BookDAO();
            int result = 0;
            try {
            java.sql.Connection con = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
            System.out.println("set connection");
            int bookId = reservation.getBookId();
            
            System.out.println("book id : " + bookId);
            Book book = bookDao.getBookById(bookId);
            
            if(!(book.getStatus().equals("Available"))){
                System.out.println("Book Not availble"+ book.getStatus());
                return result;
            };
            PreparedStatement st = con.prepareStatement("INSERT INTO reservations (student_name, student_id, book_id) VALUES (?, ?, ?) ");
            st.setString(1, reservation.getStudentName());
            st.setString(2,reservation.getStudentId());
            st.setInt(3,reservation.getBookId());
            result = st.executeUpdate();
            st.close(); 
            con.close(); 
            if((bookDao.updateBookStatus(reservation.getBookId(), "Unavailable")) > 0){
                System.out.println("Book Statment updated");
            };
            
            } catch (SQLException ex) {
                ex.printStackTrace();
                System.out.println("connection failed");
            }
            return result;
    // INSERT INTO reservations (...) VALUES (?, ?, ?) 
    }
    
        public List<Reservation> getReserveBooks() { 
            driverLoad();

            try {
            java.sql.Connection con = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
            System.out.println("set connection");

            PreparedStatement st = con.prepareStatement("SELECT * FROM reservations");
            ResultSet rs = st.executeQuery();
            List<Reservation> reservations = new ArrayList<>();

            while(rs.next()){
                Reservation resv = new Reservation();
                
                resv.setId(rs.getInt("id"));
                resv.setBookId(rs.getInt("book_id"));
                resv.setStudentId(rs.getString("student_id"));
                resv.setStudentName(rs.getString("student_name"));
                
                java.sql.Timestamp sqlTimestamp = rs.getTimestamp("reservation_date");
                
                resv.setReservationDate(convertToProtoTimestamp(sqlTimestamp));
                
                reservations.add(resv);
                System.out.println(rs.getInt("id")+" "+rs.getString("student_name"));
            }
            
            
            
            st.close(); 
            con.close(); 
            
            return reservations;
            
            } catch (SQLException ex) {
                ex.printStackTrace();
                System.out.println("connection failed");
            }
            return null;
    // INSERT INTO reservations (...) VALUES (?, ?, ?) 
    }

  public static com.google.protobuf.Timestamp convertToProtoTimestamp(java.sql.Timestamp sqlTimestamp) {
    if (sqlTimestamp == null) {
        return com.google.protobuf.Timestamp.getDefaultInstance();
    }

    return com.google.protobuf.Timestamp.newBuilder()
            .setSeconds(sqlTimestamp.getTime() / 1000)
            .setNanos((int) (sqlTimestamp.getTime() % 1000 * 1000000))
            .build();
}
    
    
    
    
    
    
    
    
    
    
    
    
    
}

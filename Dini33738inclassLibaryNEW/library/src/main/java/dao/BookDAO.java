/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import com.mysql.cj.xdevapi.Result;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Book;

/**
 *
 * @author thari
 */
public class BookDAO {
    
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
    
    
    public List<Book> searchBooks(String title) { 
        
        
            driverLoad();
            try {
            java.sql.Connection con = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
            System.out.println("set connection");
            PreparedStatement st = con.prepareStatement("SELECT * FROM books WHERE title LIKE ?");
            st.setString(1,"%"+title+"%");
            
            ResultSet rs = st.executeQuery();
            List<Book> bookSet = new ArrayList<Book>();
            while(rs.next()){
                Book book = new Book();
                book.setId( rs.getInt("id"));
                book.setAuthor(rs.getString("title"));
                book.setTitle(rs.getString("author"));
                book.setStatus(rs.getString("status"));
                bookSet.add(book);
            }
            st.close(); 
            con.close(); 
            return bookSet;
            
            } catch (SQLException ex) {
                ex.printStackTrace();
                System.out.println("connection failed");
            }
        return null;
    // SELECT * FROM books WHERE title LIKE ? 
    
    } 
    public Book getBookById(int id) { 
            driverLoad();
            try {
            java.sql.Connection con = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
            System.out.println("set connection");
            PreparedStatement st = con.prepareStatement("SELECT * FROM books WHERE id = ?");
            
            st.setInt(1, id);
            
            ResultSet rs = st.executeQuery();
            Book book = new Book();
            while(rs.next()){
            book.setId(rs.getInt("id"));
            book.setTitle(rs.getString("title"));
            book.setAuthor(rs.getString("author"));
            book.setStatus(rs.getString("status"));
            }
            st.close(); 
            con.close(); 
            
            return book;
            
            } catch (SQLException ex) {
                ex.printStackTrace();
                System.out.println("connection failed");
            }
        return null;
    // SELECT * FROM books WHERE id = ? 
    } 
    public int updateBookStatus(int bookId, String status) { 
            driverLoad();
            int result = 0;
            try {
            java.sql.Connection con = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
            System.out.println("set connection");
            PreparedStatement st = con.prepareStatement("UPDATE books SET status = ? WHERE id = ? ");
            st.setString(1, status);
            st.setInt(2,bookId);
            
           result = st.executeUpdate();
            st.close(); 
            con.close(); 
            
            } catch (SQLException ex) {
                ex.printStackTrace();
                System.out.println("connection failed");
            }
            return result;
    // UPDATE books SET status = ? WHERE id = ? 
    } 

    public void addBook(Book book) {
    
            driverLoad();
            int result = 0;
            try {
            java.sql.Connection con = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
            System.out.println("set connection");
            PreparedStatement st = con.prepareStatement("insert into books (title, author,status) values(?, ?, ?) ");
            st.setString(1, book.getTitle());
            st.setString(2, book.getAuthor());
            st.setString(3, book.getStatus());
            
           result = st.executeUpdate();
            st.close(); 
            con.close(); 
            
            } catch (SQLException ex) {
                ex.printStackTrace();
                System.out.println("connection failed");
            }
    
            System.out.println("add book");
    
    
    
    
    }

    
    
    
    
    
    
}

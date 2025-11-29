/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.User;

/**
 *
 * @author thari
 */
public class UserDAO {
    
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
    
     
    public User validateUser(String username, String password) { 
        
        driverLoad();
        try {
            java.sql.Connection con = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
            System.out.println("set connection");
            PreparedStatement st = con.prepareStatement("SELECT * FROM users WHERE username=? AND password=? ");
            st.setString(1, username);
            st.setString(2,password);
            
            ResultSet rs = st.executeQuery();
           
            User user = new User();
            
            while(rs.next()){
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setRole(rs.getString("role"));
            }
            st.close(); 
            con.close(); 
            return user;
            } catch (SQLException ex) {
                ex.printStackTrace();
                System.out.println("connection failed");
            }
        
        return null;
        // SELECT * FROM users WHERE username=? AND password=? 
    } 
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}

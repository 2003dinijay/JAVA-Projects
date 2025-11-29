package Controller;

import DAO.UserDAO;
import Model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserDAO userDAO = new UserDAO();

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String role = request.getParameter("role");

        if(name == null || name.trim().isEmpty() ||
                email == null || email.trim().isEmpty() || !email.contains("@") ||
                password == null || password.trim().isEmpty() ||
                role == null || role.trim().isEmpty()) {

            request.setAttribute("error", "All fields are required and must be valid");
            request.getRequestDispatcher("registerStudent.jsp").forward(request, response);
            return;
        }

        User user = new User(name, email, password, role);

        boolean success = userDAO.addUser(user);

        if(success) {
            response.sendRedirect("login.jsp?registered=true");
        } else {
            request.setAttribute("error", "Registration failed, please try again");
            request.getRequestDispatcher("registerStudent.jsp").forward(request, response);
        }
    }
}

package Controller;

import DAO.ApplicationDAO;
import Model.Application;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/ApplicationServlet")
public class ApplicationServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ApplicationDAO applicationDAO = new ApplicationDAO();

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        if(session == null || session.getAttribute("userId") == null ||
                !session.getAttribute("role").equals("Student")) {
            response.sendRedirect("login.jsp?error=unauthorized");
            return;
        }

        int internshipId = Integer.parseInt(request.getParameter("internshipId"));
        int studentId = (int) session.getAttribute("userId");

        Application application = new Application(internshipId, studentId, "Applied");

        boolean success = applicationDAO.addApplication(application);

        if(success) {
            response.sendRedirect("dashboardStudent.jsp?applied=true");
        } else {
            request.setAttribute("error", "Failed to apply for internship");
            request.getRequestDispatcher("applyInternship.jsp").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        if(session == null || session.getAttribute("userId") == null ||
                !session.getAttribute("role").equals("Admin")) {
            response.sendRedirect("login.jsp?error=unauthorized");
            return;
        }

        String action = request.getParameter("action");
        int id = Integer.parseInt(request.getParameter("id"));
        String status = "Applied";

        if("approve".equals(action)) {
            status = "Approved";
        } else if("reject".equals(action)) {
            status = "Rejected";
        }

        boolean success = applicationDAO.updateApplicationStatus(id, status);

        if(success) {
            response.sendRedirect("viewApplications.jsp?updated=true");
        } else {
            response.sendRedirect("viewApplications.jsp?error=update_failed");
        }
    }
}

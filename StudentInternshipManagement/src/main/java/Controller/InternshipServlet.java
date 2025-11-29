package Controller;

import DAO.InternshipDAO;
import Model.Internship;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Date;

@WebServlet("/postInternship")
public class InternshipServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private InternshipDAO internshipDAO = new InternshipDAO();

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        if(session == null || session.getAttribute("userId") == null ||
                !session.getAttribute("role").equals("Company")) {
            response.sendRedirect("login.jsp?error=unauthorized");
            return;
        }

        String title = request.getParameter("title");
        String description = request.getParameter("description");
        String deadlineStr = request.getParameter("deadline");
        int companyId = (int) session.getAttribute("userId");

        if(title == null || title.trim().isEmpty() ||
                description == null || description.trim().isEmpty() ||
                deadlineStr == null || deadlineStr.trim().isEmpty()) {

            request.setAttribute("error", "All fields are required");
            request.getRequestDispatcher("postInternship.jsp").forward(request, response);
            return;
        }

        try {
            Date deadline = Date.valueOf(deadlineStr);

            Internship internship = new Internship(title, description, companyId, deadline);

            boolean success = internshipDAO.addInternship(internship);

            if(success) {
                response.sendRedirect("dashboardCompany.jsp?posted=true");
            } else {
                request.setAttribute("error", "Failed to post internship");
                request.getRequestDispatcher("postInternship.jsp").forward(request, response);
            }
        } catch(IllegalArgumentException e) {
            request.setAttribute("error", "Invalid date format");
            request.getRequestDispatcher("postInternship.jsp").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        if(session == null || session.getAttribute("userId") == null ||
                !session.getAttribute("role").equals("Company")) {
            response.sendRedirect("login.jsp?error=unauthorized");
            return;
        }

        String action = request.getParameter("action");
        int id = Integer.parseInt(request.getParameter("id"));

        if("delete".equals(action)) {
            boolean success = internshipDAO.deleteInternship(id);

            if(success) {
                response.sendRedirect("dashboardCompany.jsp?deleted=true");
            } else {
                response.sendRedirect("dashboardCompany.jsp?error=delete_failed");
            }
        } else if("edit".equals(action)) {
            Internship internship = internshipDAO.getInternshipById(id);

            if(internship != null) {
                request.setAttribute("internship", internship);
                request.getRequestDispatcher("editInternship.jsp").forward(request, response);
            } else {
                response.sendRedirect("dashboardCompany.jsp?error=not_found");
            }
        }
    }
}

package Controller.Filters;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

public class AuthFilter implements Filter {
    public void init(FilterConfig filterConfig) throws ServletException {
    }
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        HttpSession session = request.getSession(false);
        String path = request.getRequestURI();

        if(path.endsWith("login.jsp") ||
                path.endsWith("registerStudent.jsp") ||
                path.endsWith("index.jsp") ||
                path.contains("/js/") ||
                path.contains("/css/") ||
                path.endsWith("/login") ||
                path.endsWith("/register")) {

            chain.doFilter(req, res);
            return;
        }

        if(session == null || session.getAttribute("userId") == null) {
            response.sendRedirect(request.getContextPath() + "/login.jsp?error=not_logged_in");
            return;
        }

        String role = (String) session.getAttribute("role");

        if((path.contains("admin") || path.contains("viewApplications")) &&
                !"Admin".equals(role)) {
            response.sendRedirect(request.getContextPath() + "/error.jsp?error=unauthorized");
            return;
        }

        if(path.contains("Student") && !"Student".equals(role)) {
            response.sendRedirect(request.getContextPath() + "/error.jsp?error=unauthorized");
            return;
        }

        if((path.contains("Company") || path.contains("postInternship")) &&
                !"Company".equals(role)) {
            response.sendRedirect(request.getContextPath() + "/error.jsp?error=unauthorized");
            return;
        }

        chain.doFilter(req, res);
    }

    public void destroy() {
    }
}

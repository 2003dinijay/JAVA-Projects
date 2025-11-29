<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<%@ page import="Util.DBConfig" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>View Applications</title>
    <link rel="stylesheet" href="css/styles.css">
    <script src="js/validations.js"></script>
</head>
<body>
<div class="container">
    <h1>Internship Applications</h1>

    <div class="filter-section">
        <label for="statusFilter">Filter by Status:</label>
        <select id="statusFilter" onchange="filterApplications()">
            <option value="All">All</option>
            <option value="Applied">Applied</option>
            <option value="Approved">Approved</option>
            <option value="Rejected">Rejected</option>
        </select>
    </div>

    <div class="table-container">
        <table class="data-table">
            <thead>
            <tr>
                <th>Student</th>
                <th>Internship</th>
                <th>Company</th>
                <th>Applied Date</th>
                <th>Status</th>
                <th>Actions</th>
            </tr>
            </thead>
            <tbody id="applicationsTable">
            <%
                try (Connection con = DBConfig.getConnection()) {
                    String query = "SELECT a.id, u.name AS student, i.title AS internship, " +
                            "c.name AS company, a.apply_date, a.status " +
                            "FROM applications a " +
                            "JOIN users u ON a.student_id = u.id " +
                            "JOIN internships i ON a.internship_id = i.id " +
                            "JOIN users c ON i.company_id = c.id " +
                            "ORDER BY a.apply_date DESC";

                    Statement st = con.createStatement();
                    ResultSet rs = st.executeQuery(query);

                    while (rs.next()) {
            %>
            <tr class="application-row" data-status="<%= rs.getString("status") %>">
                <td><%= rs.getString("student") %></td>
                <td><%= rs.getString("internship") %></td>
                <td><%= rs.getString("company") %></td>
                <td><%= rs.getDate("apply_date") %></td>
                <td class="status-<%= rs.getString("status").toLowerCase() %>">
                    <%= rs.getString("status") %>
                </td>
                <td class="action-buttons">
                    <% if (rs.getString("status").equals("Applied")) { %>
                    <a href="ApplicationServlet?action=approve&id=<%= rs.getInt("id") %>"
                       class="btn btn-approve" onclick="return confirmAction('approve')">Approve</a>
                    <a href="ApplicationServlet?action=reject&id=<%= rs.getInt("id") %>"
                       class="btn btn-reject" onclick="return confirmAction('reject')">Reject</a>
                    <% } else { %>
                    <span class="status-finalized">Finalized</span>
                    <% } %>
                </td>
            </tr>
            <%
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    out.println("<tr><td colspan='6'>Error loading applications: " + e.getMessage() + "</td></tr>");
                }
            %>
            </tbody>
        </table>
    </div>

    <div class="button-group">
        <button class="btn btn-secondary" onclick="window.location.href='dashboardAdmin.jsp'">Back to Dashboard</button>
        <button class="btn btn-primary" onclick="exportApplications()">Export to CSV</button>
    </div>
</div>

<script>
    function confirmAction(action) {
        return confirm("Are you sure you want to " + action + " this application?");
    }

    function filterApplications() {
        const status = document.getElementById('statusFilter').value;
        const rows = document.querySelectorAll('.application-row');

        rows.forEach(row => {
            if (status === 'All' || row.getAttribute('data-status') === status) {
                row.style.display = '';
            } else {
                row.style.display = 'none';
            }
        });
    }

    function exportApplications() {
        alert("Export functionality will be implemented here");
    }
</script>
</body>
</html>
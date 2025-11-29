<%--
  Created by IntelliJ IDEA.
  User: iamcy
  Date: 5/9/2025
  Time: 4:05 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<%@ page import="Util.DBConfig" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.Map" %>

<%
    try {
        Connection con = DBConfig.getConnection();
        int studentId = 0;
        if (session.getAttribute("userId") != null) {
            studentId = (Integer) session.getAttribute("userId");
        }

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String currentDate = sdf.format(new Date());

        String query = "SELECT i.id, i.title, u.name AS company, i.description, i.deadline, " +
                "(SELECT COUNT(*) FROM applications a WHERE a.internship_id = i.id AND a.student_id = ?) AS hasApplied " +
                "FROM internships i " +
                "JOIN users u ON i.company_id = u.id " +
                "WHERE i.deadline >= ? " +
                "ORDER BY i.deadline ASC";

        PreparedStatement ps = con.prepareStatement(query);
        ps.setInt(1, studentId);
        ps.setString(2, currentDate);

        ResultSet rs = ps.executeQuery();

        ArrayList<Map<String, Object>> internshipsList = new ArrayList<>();

        while (rs.next()) {
            Map<String, Object> internship = new HashMap<>();
            internship.put("id", rs.getInt("id"));
            internship.put("title", rs.getString("title"));
            internship.put("company", rs.getString("company"));
            internship.put("description", rs.getString("description"));
            internship.put("deadline", rs.getDate("deadline"));
            internship.put("hasApplied", rs.getInt("hasApplied") > 0);
            internshipsList.add(internship);
        }

        boolean hasInternships = !internshipsList.isEmpty();
%>

<div class="form-group">
    <label for="internshipId">Select Internship:</label>
    <select name="internshipId" id="internshipId" onchange="showInternshipDetails(this.value)" required>
    <option value="">-- Select an Internship --</option>
    <%
        if (internshipsList != null && !internshipsList.isEmpty()) {
            for (Map<String, Object> internship : internshipsList) {
                boolean alreadyApplied = (Boolean) internship.get("hasApplied");
                String id = internship.get("id").toString();
                String title = internship.get("title").toString();
                String company = internship.get("company").toString();
    %>
    <option value="<%= id %>" <%= alreadyApplied ? "disabled" : "" %>>
        <%= title %> at <%= company %> <%= alreadyApplied ? "(Already Applied)" : "" %>
    </option>
    <%
            }
        } else {
    %>
    <option value="" disabled>No available internships found</option>
    <%
        }
    %>
</select>

</div>

<div id="internshipDetails" class="internship-details">
    <p>Select an internship to view details</p>
</div>

<script>
    var internshipsData = {};

    <% for(Map<String, Object> internship : internshipsList) {
        String descriptionEscaped = ((String)internship.get("description"))
            .replace("\n", "<br>")
            .replace("\"", "\\\"");
    %>
    internshipsData["<%= internship.get("id") %>"] = {
        "title": "<%= internship.get("title") %>",
        "company": "<%= internship.get("company") %>",
        "description": "<%= descriptionEscaped %>",
        "deadline": "<%= internship.get("deadline") %>",
        "hasApplied": <%= internship.get("hasApplied") %>
    };
    <% } %>

    function showInternshipDetails(internshipId) {
        var coverLetterInput = document.getElementById("coverLetter");

        if (coverLetterInput) {
            coverLetterInput.disabled = false;
        }

        if (internshipId === "") {
            document.getElementById("internshipDetails").innerHTML = "<p>Select an internship to view details</p>";
            return;
        }

        const internship = internshipsData[internshipId];
        if (!internship) {
            document.getElementById("internshipDetails").innerHTML = "<p>Internship details not available</p>";
            return;
        }

        let detailsHtml = `
            <div class="internship-card">
                <h3>${internship.title}</h3>
                <p class="company"><strong>Company:</strong> ${internship.company}</p>
                <p class="deadline"><strong>Deadline:</strong> ${internship.deadline}</p>
                <div class="description">
                    <strong>Description:</strong><br>
                    ${internship.description}
                </div>
            </div>
        `;

        document.getElementById("internshipDetails").innerHTML = detailsHtml;
    }
</script>

<%
        if (rs != null) rs.close();
        if (ps != null) ps.close();
        if (con != null) con.close();
    } catch (Exception e) {
        out.println("<div class='error-message'>Error loading internships: " + e.getMessage() + "</div>");
        e.printStackTrace();
    }
%>

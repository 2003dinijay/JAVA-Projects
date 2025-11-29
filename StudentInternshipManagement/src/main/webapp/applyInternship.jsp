<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Apply for Internships</title>
    <link rel="stylesheet" href="css/styles.css">
    <script src="js/validations.js"></script>
</head>
<body>
<div class="container">
    <h1>Available Internships</h1>

    <div class="notification" id="notification"></div>

    <form action="${pageContext.request.contextPath}/ApplicationServlet" method="post" id="applicationForm" onsubmit="return validateApplication()">
        <div id="internshipDropdown">
            <p>Loading internships...</p>
        </div>

        <div class="form-group">
            <label for="coverLetter">Cover Letter:</label>
            <textarea name="coverLetter" id="coverLetter" rows="5" placeholder="Why are you interested in this internship?"></textarea>
        </div>

        <div class="button-group">
            <button type="submit" class="btn btn-primary">Apply</button>
            <button type="button" class="btn btn-secondary" onclick="window.location.href='dashboardStudent.jsp'">Back to Dashboard</button>
        </div>
    </form>
</div>

<script>
    window.onload = function() {
        loadInternships();

        if (typeof showInternshipDetails !== 'function') {
            function showInternshipDetails(internshipId) {
                console.log("Internship selected: " + internshipId);
            }
        }
    }
</script>
</body>
</html>
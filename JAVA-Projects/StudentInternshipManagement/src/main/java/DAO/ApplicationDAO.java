package DAO;

import Model.Application;
import Util.DBConfig;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ApplicationDAO {
    public boolean addApplication(Application application) {
        try (Connection conn = DBConfig.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(
                    "INSERT INTO applications(internship_id, student_id, status) VALUES (?, ?, ?)"
            );
            ps.setInt(1, application.getInternshipId());
            ps.setInt(2, application.getStudentId());
            ps.setString(3, application.getStatus());

            int result = ps.executeUpdate();
            return result > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateApplicationStatus(int id, String status) {
        try (Connection conn = DBConfig.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(
                    "UPDATE applications SET status=? WHERE id=?"
            );
            ps.setString(1, status);
            ps.setInt(2, id);

            int result = ps.executeUpdate();
            return result > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Application getApplicationById(int id) {
        try (Connection conn = DBConfig.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(
                    "SELECT * FROM applications WHERE id=?"
            );
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Application application = new Application();
                application.setId(rs.getInt("id"));
                application.setInternshipId(rs.getInt("internship_id"));
                application.setStudentId(rs.getInt("student_id"));
                application.setStatus(rs.getString("status"));
                return application;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Application> getApplicationsByStudent(int studentId) {
        List<Application> applications = new ArrayList<>();
        try (Connection conn = DBConfig.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(
                    "SELECT * FROM applications WHERE student_id=?"
            );
            ps.setInt(1, studentId);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Application application = new Application();
                application.setId(rs.getInt("id"));
                application.setInternshipId(rs.getInt("internship_id"));
                application.setStudentId(rs.getInt("student_id"));
                application.setStatus(rs.getString("status"));
                applications.add(application);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return applications;
    }

    public List<Application> getApplicationsByInternship(int internshipId) {
        List<Application> applications = new ArrayList<>();
        try (Connection conn = DBConfig.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(
                    "SELECT * FROM applications WHERE internship_id=?"
            );
            ps.setInt(1, internshipId);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Application application = new Application();
                application.setId(rs.getInt("id"));
                application.setInternshipId(rs.getInt("internship_id"));
                application.setStudentId(rs.getInt("student_id"));
                application.setStatus(rs.getString("status"));
                applications.add(application);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return applications;
    }
    public class ApplicationDetails {
        private int id;
        private String studentName;
        private String internshipTitle;
        private String status;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getStudentName() {
            return studentName;
        }

        public void setStudentName(String studentName) {
            this.studentName = studentName;
        }

        public String getInternshipTitle() {
            return internshipTitle;
        }

        public void setInternshipTitle(String internshipTitle) {
            this.internshipTitle = internshipTitle;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }

    public List<ApplicationDetails> getAllApplicationDetails() {
        List<ApplicationDetails> details = new ArrayList<>();
        try (Connection conn = DBConfig.getConnection()) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(
                    "SELECT a.id, u.name AS student, i.title AS internship, a.status " +
                            "FROM applications a " +
                            "JOIN users u ON a.student_id = u.id " +
                            "JOIN internships i ON a.internship_id = i.id"
            );

            while (rs.next()) {
                ApplicationDetails detail = new ApplicationDetails();
                detail.setId(rs.getInt("id"));
                detail.setStudentName(rs.getString("student"));
                detail.setInternshipTitle(rs.getString("internship"));
                detail.setStatus(rs.getString("status"));
                details.add(detail);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return details;
    }
}

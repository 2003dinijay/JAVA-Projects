package DAO;

import Model.Internship;
import Util.DBConfig;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class InternshipDAO {
    public boolean addInternship(Internship internship) {
        try (Connection conn = DBConfig.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(
                    "INSERT INTO internships(title, description, company_id, deadline) VALUES (?, ?, ?, ?)"
            );
            ps.setString(1, internship.getTitle());
            ps.setString(2, internship.getDescription());
            ps.setInt(3, internship.getCompanyId());
            ps.setDate(4, internship.getDeadline());

            int result = ps.executeUpdate();
            return result > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Internship getInternshipById(int id) {
        try (Connection conn = DBConfig.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(
                    "SELECT * FROM internships WHERE id=?"
            );
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Internship internship = new Internship();
                internship.setId(rs.getInt("id"));
                internship.setTitle(rs.getString("title"));
                internship.setDescription(rs.getString("description"));
                internship.setCompanyId(rs.getInt("company_id"));
                internship.setDeadline(rs.getDate("deadline"));
                return internship;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Internship> getAllInternships() {
        List<Internship> internships = new ArrayList<>();
        try (Connection conn = DBConfig.getConnection()) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM internships");

            while (rs.next()) {
                Internship internship = new Internship();
                internship.setId(rs.getInt("id"));
                internship.setTitle(rs.getString("title"));
                internship.setDescription(rs.getString("description"));
                internship.setCompanyId(rs.getInt("company_id"));
                internship.setDeadline(rs.getDate("deadline"));
                internships.add(internship);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return internships;
    }

    public List<Internship> getInternshipsByCompany(int companyId) {
        List<Internship> internships = new ArrayList<>();
        try (Connection conn = DBConfig.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(
                    "SELECT * FROM internships WHERE company_id=?"
            );
            ps.setInt(1, companyId);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Internship internship = new Internship();
                internship.setId(rs.getInt("id"));
                internship.setTitle(rs.getString("title"));
                internship.setDescription(rs.getString("description"));
                internship.setCompanyId(rs.getInt("company_id"));
                internship.setDeadline(rs.getDate("deadline"));
                internships.add(internship);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return internships;
    }

    public boolean updateInternship(Internship internship) {
        try (Connection conn = DBConfig.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(
                    "UPDATE internships SET title=?, description=?, deadline=? WHERE id=?"
            );
            ps.setString(1, internship.getTitle());
            ps.setString(2, internship.getDescription());
            ps.setDate(3, internship.getDeadline());
            ps.setInt(4, internship.getId());

            int result = ps.executeUpdate();
            return result > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteInternship(int id) {
        try (Connection conn = DBConfig.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(
                    "DELETE FROM internships WHERE id=?"
            );
            ps.setInt(1, id);

            int result = ps.executeUpdate();
            return result > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}

package org.project.model;

import org.project.bean.RatingBean;
import org.project.cli.actions.LoginAction;
import org.project.enums.ProjectStatus;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RatingModel {

    public static void create(RatingBean rating, Connection con) throws SQLException {
        PreparedStatement ps;
        ps = con.prepareStatement("INSERT INTO rating ("
                + "	ratingvalue, ratingdescription, freelancerid, hirerid)"
                + "	VALUES (?, ?, ?, ?);");
        ps.setInt(1, rating.getRatingValue());
        ps.setString(2, rating.getRatingDescription());
        ps.setInt(3, rating.getFreelancerId());
        ps.setInt(4, rating.getHirerId());
        ps.execute();
        ps.close();
    }

    public static int delete(RatingBean rating, Connection con) throws SQLException {
        try (PreparedStatement ps = con.prepareStatement(
                "DELETE FROM rating WHERE ratingid=?;"
        )) {
            ps.setInt(1, rating.getRatingId());

            return ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
            throw e;
        }
    }

    public static boolean listAllByHirer(Connection con) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        int hirerId = LoginAction.getLoggedUser();

        try {
            ps = con.prepareStatement("SELECT * FROM rating WHERE hirerid = ? ORDER BY ratingid");
            ps.setInt(1, hirerId);
            rs = ps.executeQuery();

            boolean hasRatings = false;

            while (rs.next()) {
                hasRatings = true;
                int ratingId = rs.getInt("ratingid");
                int ratingValue = rs.getInt("ratingvalue");
                String ratingDescription = rs.getString("ratingdescription");

                System.out.println("Rating ID: " + ratingId);
                System.out.println("Value: " + ratingValue);
                System.out.println("Description: " + ratingDescription);
                System.out.println("------------------------");
            }
            if (!hasRatings) {
                System.out.println("\nThere are no ratings created.");
            }
            return hasRatings;
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
            throw e;
        } finally {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
        }
    }

    public static void listAllByFreelancer(Connection con) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        int freelancerId = LoginAction.getLoggedUser();

        try {
            ps = con.prepareStatement("SELECT * FROM rating WHERE freelancerId = ? ORDER BY ratingid");
            ps.setInt(1, freelancerId);
            rs = ps.executeQuery();

            boolean hasRatings = false;

            while (rs.next()) {
                hasRatings = true;
                int ratingId = rs.getInt("ratingid");
                int ratingValue = rs.getInt("ratingvalue");
                String ratingDescription = rs.getString("ratingdescription");

                System.out.println("Rating ID: " + ratingId);
                System.out.println("Value: " + ratingValue);
                System.out.println("Description: " + ratingDescription);
                System.out.println("------------------------");
            }
            if (!hasRatings) {
                System.out.println("\nThere are no ratings created.");
            }
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
            throw e;
        } finally {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
        }
    }

    public static boolean listFreelancersInProjects(Connection con) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;

        boolean hasRelatedFreelancer = false;
        try {
            ps = con.prepareStatement("SELECT DISTINCT fr.freelancerid, fr.freelancername, fr.freelancerphone "
                    + "FROM freelancer fr "
                    + "INNER JOIN proposal pl ON pl.freelancerid = fr.freelancerid "
                    + "INNER JOIN project pr ON pr.projectid = pl.projectid "
                    + "LEFT JOIN rating rt ON rt.freelancerid = fr.freelancerid AND rt.hirerid = pr.hirerid "
                    + "WHERE pr.hirerid = ? "
                    + "AND rt.ratingid IS NULL");
            ps.setInt(1, LoginAction.getLoggedUser());
            rs = ps.executeQuery();

            while (rs.next()) {
                hasRelatedFreelancer = true;
                int freelancerId = rs.getInt("freelancerid");
                String freelancerName = rs.getString("freelancername");
                String freelancerPhone = rs.getString("freelancerphone");

                System.out.println("Freelancer ID: " + freelancerId);
                System.out.println("Name: " + freelancerName);
                System.out.println("Phone: " + freelancerPhone);
                System.out.println("------------------------");
            }
            if (!hasRelatedFreelancer) {
                System.out.println("\nThere are no related freelancer.");
            }
            return hasRelatedFreelancer;
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
            throw e;
        } finally {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
        }
    }
}
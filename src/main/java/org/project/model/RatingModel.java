package org.project.model;

import org.project.bean.ProposalBean;
import org.project.bean.RatingBean;
import org.project.enums.ProposalStatusEnum;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RatingModel {

    public static void create(RatingBean rating, Connection con) throws SQLException {
        PreparedStatement ps;
        ps = con.prepareStatement("INSERT INTO proposal("
                + "	proposalvalue, proposalmessage, freelancerid)"
                + "	VALUES (?, ?, ?);");
        ps.setInt(1, rating.getRatingValue());
        ps.setString(2, rating.getRatingMessage());
        ps.setInt(3, rating.getFreelancerId());
        ps.execute();
        ps.close();
    }

    public static void update(RatingBean rating, Connection con) throws SQLException {
        PreparedStatement ps;
        ps = con.prepareStatement("UPDATE rating"
                + "	SET ratingvalue=?, ratingmessage=?"
                + "	WHERE ratingid=?;");
        ps.setInt(1, rating.getRatingValue());
        ps.setString(2, rating.getRatingMessage());
        ps.setInt(3, rating.getRatingId());
        ps.execute();
        ps.close();
    }

    public static void delete(RatingBean rating, Connection con) throws SQLException {
        PreparedStatement ps;
        ps = con.prepareStatement("DELETE FROM rating"
                + "	WHERE ratingid=?;");
        ps.setInt(1, rating.getRatingId());
        ps.execute();
        ps.close();
    }

    public static void listAll(Connection con) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = con.prepareStatement("SELECT * FROM proposal ORDER BY proposalid");
            rs = ps.executeQuery();

            while (rs.next()) {
                int proposalId = rs.getInt("proposalid");
                String proposalName = rs.getString("proposalvalue");
                String proposalMessage = rs.getString("proposalmessage");
                String proposalStatus = rs.getString("proposalstatus");

                System.out.println("Proposal ID: " + proposalId);
                System.out.println("Value: " + proposalName);
                System.out.println("Message: " + proposalMessage);
                System.out.println("Status: " + proposalStatus);
                System.out.println("------------------------");
            }
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
            throw e;
        } finally {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
        }
    }
}
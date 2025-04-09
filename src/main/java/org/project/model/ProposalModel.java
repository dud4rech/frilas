package org.project.model;

import org.project.bean.ProposalBean;
import org.project.enums.ProposalStatusEnum;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProposalModel {

    public static void create(ProposalBean proposal, Connection con) throws SQLException {
        PreparedStatement ps;
        ps = con.prepareStatement("INSERT INTO proposal("
                + "	proposalvalue, proposalmessage, proposalstatus, freelancerid, projectid)"
                + "	VALUES (?, ?, ?, ?, ?);");
        ps.setInt(1, proposal.getProposalValue());
        ps.setString(2, proposal.getProposalMessage());
        ps.setInt(3, ProposalStatusEnum.CREATED.getValue());
        ps.setInt(4, proposal.getFreelancerId());
        ps.setInt(5, proposal.getProjectId());
        ps.execute();
        ps.close();
    }

    public static void update(ProposalBean proposal, Connection con) throws SQLException {
        PreparedStatement ps;
        ps = con.prepareStatement("UPDATE proposal"
                + "	SET proposalvalue=?, proposalmessage=?"
                + "	WHERE proposalid=?;");
        ps.setInt(1, proposal.getProposalValue());
        ps.setString(2, proposal.getProposalMessage());
        ps.setInt(3, proposal.getProposalId());
        ps.execute();
        ps.close();
    }

    public static void delete(ProposalBean proposal, Connection con) throws SQLException {
        PreparedStatement ps;
        ps = con.prepareStatement("DELETE FROM proposal"
                + "	WHERE proposalid=?;");
        ps.setInt(1, proposal.getProposalId());
        ps.execute();
        ps.close();
    }

    public static void updateStatus(ProposalBean proposal, Connection con) throws SQLException {
        PreparedStatement ps;
        ps = con.prepareStatement("UPDATE proposal"
                + "	SET proposalstatus=?"
                + "	WHERE proposalid=?;");
        ps.setInt(1, ProposalStatusEnum.ACCEPTED.getValue());
        ps.setInt(2, proposal.getProposalId());
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

    public static void listAllByProject(int projectId, Connection con) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = con.prepareStatement("SELECT * FROM proposal WHERE projectid = ? ORDER BY proposalid");
            ps.setInt(1, projectId);
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

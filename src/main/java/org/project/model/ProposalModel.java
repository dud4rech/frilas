package org.project.model;

import org.project.bean.ProposalBean;
import org.project.cli.actions.LoginAction;
import org.project.enums.ProposalStatus;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class ProposalModel {

    public static void create(ProposalBean proposal, Connection con) throws SQLException {
        PreparedStatement ps;
        ps = con.prepareStatement("INSERT INTO proposal("
                + "	proposalvalue, proposaldescription, proposalstatus, freelancerid, projectid)"
                + "	VALUES (?, ?, ?, ?, ?);");
        ps.setInt(1, proposal.getProposalValue());
        ps.setString(2, proposal.getproposalDescription());
        ps.setInt(3, ProposalStatus.CREATED.getValue());
        ps.setInt(4, proposal.getFreelancerId());
        ps.setInt(5, proposal.getProjectId());
        ps.execute();
        ps.close();
    }

    public static int update(ProposalBean proposal, Connection con) throws SQLException {
        try (PreparedStatement ps = con.prepareStatement(
                "UPDATE proposal"
                        + "	SET proposalvalue=?, proposaldescription=?"
                        + "	WHERE proposalid=? AND proposalstatus NOT IN (?);"
        )) {
            ps.setInt(1, proposal.getProposalValue());
            ps.setString(2, proposal.getproposalDescription());
            ps.setInt(3, proposal.getProposalId());
            ps.setInt(4, ProposalStatus.ACCEPTED.getValue());

            return ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
            throw e;
        }
    }

    public static int delete(ProposalBean proposal, Connection con) throws SQLException {
        try (PreparedStatement ps = con.prepareStatement(
                "DELETE FROM proposal " +
                        "WHERE proposalid = ? " +
                        "AND proposalstatus NOT IN (?)"
        )) {
            ps.setInt(1, proposal.getProposalId());
            ps.setInt(2, ProposalStatus.ACCEPTED.getValue());

            return ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
            throw e;
        }
    }

    public static int updateStatus(ProposalBean proposal, Connection con) throws SQLException {
        String sql = "UPDATE proposal"
                + "	SET proposalstatus=?"
                + "	WHERE proposalid=?;";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, ProposalStatus.ACCEPTED.getValue());
            ps.setInt(2, proposal.getProposalId());

            return ps.executeUpdate();
        }
    }

    public static Map<String, Object> findProjectAndProposalDetails(int proposalId, Connection con) throws SQLException {
        String sql = "SELECT freelancerid, proposalvalue, projectdeadline, projectid FROM proposal pl " +
                "INNER JOIN project pt ON pt.projectid = pl.projectid " +
                "WHERE proposalid = ?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, proposalId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Map<String, Object> details = new HashMap<>();
                    details.put("freelancerid", rs.getInt("freelancerid"));
                    details.put("proposalvalue", rs.getInt("proposalvalue"));
                    details.put("projectdeadline", rs.getString("projectdeadline"));
                    details.put("projectid", rs.getInt("projectid"));

                    return details;
                } else {
                    System.out.println("\nProposal not found!\n");
                    return null;
                }
            }
        }
    }

    public static boolean listAll(Connection con) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;

        boolean hasProposals = false;
        try {
            ps = con.prepareStatement("SELECT * FROM proposal ORDER BY proposalid");
            rs = ps.executeQuery();

            while (rs.next()) {
                hasProposals = true;
                int proposalId = rs.getInt("proposalid");
                String proposalName = rs.getString("proposalvalue");
                String proposalDescription = rs.getString("proposaldescription");
                int proposalStatus = rs.getInt("proposalstatus");

                String statusDescription = ProposalStatus.fromValue(proposalStatus).getDescription();

                System.out.println("Proposal ID: " + proposalId);
                System.out.println("Value: " + proposalName);
                System.out.println("Description: " + proposalDescription);
                System.out.println("Status: " + statusDescription);
                System.out.println("------------------------");
            }
            if (!hasProposals) {
                System.out.println("\nThere are no proposals available.");
            }
            return hasProposals;
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
            throw e;
        } finally {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
        }
    }

    public static boolean listAllNotAccepted(Connection con) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        int freelancerId = LoginAction.getLoggedUser();

        boolean hasProposals = false;
        try {
            ps = con.prepareStatement("SELECT * FROM proposal " +
                    "WHERE proposalstatus != ? " +
                    "AND freelancerid = ? " +
                    "ORDER BY proposalid");
            ps.setInt(1, ProposalStatus.ACCEPTED.getValue());
            ps.setInt(2, freelancerId);
            rs = ps.executeQuery();

            while (rs.next()) {
                hasProposals = true;
                int proposalId = rs.getInt("proposalid");
                String proposalName = rs.getString("proposalvalue");
                String proposalDescription = rs.getString("proposaldescription");
                int proposalStatus = rs.getInt("proposalstatus");

                String statusDescription = ProposalStatus.fromValue(proposalStatus).getDescription();

                System.out.println("Proposal ID: " + proposalId);
                System.out.println("Value: " + proposalName);
                System.out.println("Description: " + proposalDescription);
                System.out.println("Status: " + statusDescription);
                System.out.println("------------------------");
            }
            if (!hasProposals) {
                System.out.println("\nThere are no proposals available.");
            }
            return hasProposals;
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
            throw e;
        } finally {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
        }
    }

    public static boolean listAllByProject(int projectId, Connection con) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;

        boolean hasProposals = false;
        try {
            ps = con.prepareStatement("SELECT * FROM proposal " +
                    "WHERE projectid = ? " +
                    "ORDER BY proposalid");
            ps.setInt(1, projectId);
            rs = ps.executeQuery();

            while (rs.next()) {
                hasProposals = true;
                int proposalId = rs.getInt("proposalid");
                String proposalName = rs.getString("proposalvalue");
                String proposalDescription = rs.getString("proposaldescription");
                int proposalStatus = rs.getInt("proposalstatus");

                String statusDescription = ProposalStatus.fromValue(proposalStatus).getDescription();

                System.out.println("Proposal ID: " + proposalId);
                System.out.println("Value: " + proposalName);
                System.out.println("Description: " + proposalDescription);
                System.out.println("Status: " + statusDescription);
                System.out.println("------------------------");
            }
            if (!hasProposals) {
                System.out.println("\nThere are no proposals created.");
            }
            return hasProposals;
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
            throw e;
        } finally {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
        }
    }

    public static void listAllByUser(Connection con) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        int hirerId = LoginAction.getLoggedUser();

        boolean hasProposals = false;
        try {
            ps = con.prepareStatement("SELECT * FROM proposal pl " +
                    "INNER JOIN project pt ON pt.projectid = pl.projectid " +
                    "WHERE pt.hirerid = ? " +
                    "ORDER BY proposalid");
            ps.setInt(1, hirerId);
            rs = ps.executeQuery();

            while (rs.next()) {
                hasProposals = true;
                int proposalId = rs.getInt("proposalid");
                String proposalName = rs.getString("proposalvalue");
                String proposalDescription = rs.getString("proposaldescription");
                int proposalStatus = rs.getInt("proposalstatus");

                String statusDescription = ProposalStatus.fromValue(proposalStatus).getDescription();

                System.out.println("Proposal ID: " + proposalId);
                System.out.println("Value: " + proposalName);
                System.out.println("Description: " + proposalDescription);
                System.out.println("Status: " + statusDescription);
                System.out.println("------------------------");
            }
            if (!hasProposals) {
                System.out.println("\nThere are no proposals created.");
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

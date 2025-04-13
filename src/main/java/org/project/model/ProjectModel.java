package org.project.model;

import org.project.bean.ProjectBean;
import org.project.cli.actions.LoginAction;
import org.project.enums.ProjectStatus;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProjectModel {

    public static void create(ProjectBean project, Connection con) throws SQLException {
        PreparedStatement ps;
        ps = con.prepareStatement("INSERT INTO project("
                + "	projectname, projectdescription, projectdeadline, projectbudget, projectstatus, hirerid)"
                + "	VALUES (?, ?, ?, ?, ?, ?);");
        ps.setString(1, project.getProjectName());
        ps.setString(2, project.getProjectDescription());
        ps.setString(3, project.getProjectDeadline());
        ps.setInt(4, project.getProjectBudget());
        ps.setInt(5, ProjectStatus.START.getValue());
        ps.setInt(6, project.getHirerId());
        ps.execute();
        ps.close();
    }

    public static int update(ProjectBean project, Connection con) throws SQLException {
        try (PreparedStatement ps = con.prepareStatement(
                "UPDATE project"
                        + "	SET projectname=?, projectdescription=?, projectdeadline=?, projectbudget=?"
                        + "	WHERE projectid=? AND projectstatus NOT IN (?,?);"
        )) {
            ps.setString(1, project.getProjectName());
            ps.setString(2, project.getProjectDescription());
            ps.setString(3, project.getProjectDeadline());
            ps.setInt(4, project.getProjectBudget());
            ps.setInt(5, project.getProjectId());
            ps.setInt(6, ProjectStatus.ONGOING.getValue());
            ps.setInt(7, ProjectStatus.FINISHED.getValue());

            return ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
            throw e;
        }
    }

    public static int delete(ProjectBean project, Connection con) throws SQLException {
        try (PreparedStatement ps = con.prepareStatement(
                "DELETE FROM project " +
                        "WHERE projectid = ? " +
                        "AND projectstatus NOT IN (?, ?)"
        )) {
            ps.setInt(1, project.getProjectId());
            ps.setInt(2, ProjectStatus.ONGOING.getValue());
            ps.setInt(3, ProjectStatus.FINISHED.getValue());

            return ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
            throw e;
        }
    }

    public static int updateStatus(int projectId, Connection con) throws SQLException {
        String sql = "UPDATE project"
                + "	SET projectstatus=?"
                + "	WHERE projectid=?;";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, ProjectStatus.ONGOING.getValue());
            ps.setInt(2, projectId);

            return ps.executeUpdate();
        }
    }

    public static int finishProject(int paymentId, Connection con) throws SQLException {
        String sql = "UPDATE project pr "
                + "SET projectstatus = ? "
                + "FROM payment pt "
                + "WHERE pt.projectid = pr.projectid "
                + "AND pt.paymentid = ?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, ProjectStatus.FINISHED.getValue());
            ps.setInt(2, paymentId);

            return ps.executeUpdate();
        }
    }

    public static boolean listAll(Connection con) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = con.prepareStatement("SELECT * FROM project " +
                    "WHERE projectstatus = ? " +
                    "ORDER BY projectid");
            ps.setInt(1, ProjectStatus.START.getValue());
            rs = ps.executeQuery();

            boolean hasProjects = false;

            while (rs.next()) {
                hasProjects = true;
                int projectId = rs.getInt("projectid");
                String projectName = rs.getString("projectname");
                String projectDescription = rs.getString("projectdescription");
                String projectDeadline = rs.getString("projectdeadline");
                int projectBudget = rs.getInt("projectbudget");
                int projectStatus = rs.getInt("projectstatus");

                String statusDescription = ProjectStatus.fromValue(projectStatus).getDescription();

                System.out.println("\nProject ID: " + projectId);
                System.out.println("Name: " + projectName);
                System.out.println("Description: " + projectDescription);
                System.out.println("Deadline: " + projectDeadline);
                System.out.println("Budget: " + projectBudget);
                System.out.println("Status: " + statusDescription);
                System.out.println("------------------------");
            }
            if (!hasProjects) {
                System.out.println("\nThere are no projects available.");
            }
            return hasProjects;
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
            throw e;
        } finally {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
        }
    }

    public static boolean listAllByUser(Connection con) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;

        int hirerId = LoginAction.getLoggedUser();
        try {
            ps = con.prepareStatement("SELECT * FROM project " +
                    "WHERE hirerid = ? AND projectstatus NOT IN (?, ?) " +
                    "ORDER BY projectid");
            ps.setInt(1, hirerId);
            ps.setInt(2, ProjectStatus.FINISHED.getValue());
            ps.setInt(3, ProjectStatus.ONGOING.getValue());

            rs = ps.executeQuery();

            boolean hasProjects = false;

            while (rs.next()) {
                hasProjects = true;
                int projectId = rs.getInt("projectid");
                String projectName = rs.getString("projectname");
                String projectDescription = rs.getString("projectdescription");
                String projectDeadline = rs.getString("projectdeadline");
                int projectBudget = rs.getInt("projectbudget");
                int projectStatus = rs.getInt("projectstatus");

                String statusDescription = ProjectStatus.fromValue(projectStatus).getDescription();

                System.out.println("\nProject ID: " + projectId);
                System.out.println("Name: " + projectName);
                System.out.println("Description: " + projectDescription);
                System.out.println("Deadline: " + projectDeadline);
                System.out.println("Budget: " + projectBudget);
                System.out.println("Status: " + statusDescription);
                System.out.println("------------------------");
            }
            if (!hasProjects) {
                System.out.println("\nThere are no projects available.");
            }
            return hasProjects;
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
            throw e;
        } finally {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
        }
    }
}

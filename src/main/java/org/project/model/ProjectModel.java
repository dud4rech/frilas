package org.project.model;

import org.project.bean.ProjectBean;
import org.project.cli.actions.LoginAction;
import org.project.enums.ProjectStatusEnum;

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
        ps.setInt(5, ProjectStatusEnum.START.getValue());
        ps.setInt(6, project.getHirerId());
        ps.execute();
        ps.close();
    }

    public static void update(ProjectBean project, Connection con) throws SQLException {
        PreparedStatement ps;
        ps = con.prepareStatement("UPDATE project"
                + "	SET projectname=?, projectdescription=?, projectdeadline=?, projectbudget=?"
                + "	WHERE projectid=?;");
        ps.setString(1, project.getProjectName());
        ps.setString(2, project.getProjectDescription());
        ps.setString(3, project.getProjectDeadline());
        ps.setInt(4, project.getProjectBudget());
        ps.setInt(5, project.getProjectId());
        ps.execute();
        ps.close();
    }

    public static void delete(ProjectBean project, Connection con) throws SQLException {
        PreparedStatement ps;
        ps = con.prepareStatement("DELETE FROM project"
                + "	WHERE projectid=?;");
        ps.setInt(1, project.getProjectId());
        ps.execute();
        ps.close();
    }

    public static void listAll(Connection con) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = con.prepareStatement("SELECT * FROM project ORDER BY projectid");
            rs = ps.executeQuery();

            while (rs.next()) {
                int projectId = rs.getInt("projectid");
                String projectName = rs.getString("projectname");
                String projectDescription = rs.getString("projectdescription");
                String projectDeadline = rs.getString("projectdeadline");
                int projectBudget = rs.getInt("projectbudget");
                String projectStatus = rs.getString("projectstatus");

                System.out.println("\nProject ID: " + projectId);
                System.out.println("Name: " + projectName);
                System.out.println("Description: " + projectDescription);
                System.out.println("Deadline: " + projectDeadline);
                System.out.println("Budget: " + projectBudget);
                System.out.println("Status: " + projectStatus);
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

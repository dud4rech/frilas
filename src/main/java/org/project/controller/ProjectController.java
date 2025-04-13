package org.project.controller;

import org.project.bean.ProjectBean;
import org.project.cli.actions.LoginAction;
import org.project.model.ProjectModel;
import org.project.utils.Utils;

import java.sql.Connection;
import java.sql.SQLException;

public class ProjectController {

    public static void createProject(Connection con) throws SQLException {
        System.out.println("=== Creating a new project ===");

        System.out.print("Enter the project name: ");
        String projectName = Utils.readString();

        System.out.print("Enter the project description: ");
        String projectDescription = Utils.readString();

        System.out.print("Enter the project deadline (format YYYY-MM-DD, e.g., 2025-12-31): ");
        String projectDeadline = Utils.readString();

        System.out.print("Enter the project budget (as a whole number): ");
        int projectBudget = Utils.readInt();

        int hirerId = LoginAction.getLoggedUser();

        ProjectBean project = new ProjectBean(projectName, projectDescription, projectDeadline, projectBudget, hirerId);
        ProjectModel.create(project, con);

        System.out.println("\nProject created successfully!");
    }

    public static void updateProject(Connection con) throws SQLException {
        System.out.println("=== Editing a project ===");

        System.out.print("Enter the project id: ");
        int projectId = Utils.readInt();

        System.out.print("Enter the project name: ");
        String projectName = Utils.readString();

        System.out.print("Enter the project description: ");
        String projectDescription = Utils.readString();

        System.out.print("Enter the project deadline (format YYYY-MM-DD, e.g., 2025-12-31): ");
        String projectDeadline = Utils.readString();

        System.out.print("Enter the project budget (as a whole number): ");
        int projectBudget = Utils.readInt();

        ProjectBean project = new ProjectBean(projectId, projectName, projectDescription, projectDeadline, projectBudget);
        int projectUpdated = ProjectModel.update(project, con);

        if (projectUpdated == 0) {
            System.out.println("\nProject not edited or it doesn't exist.");
        } else {
            System.out.println("\nProject edited successfully!");
        }
    }

    public static void deleteProject(Connection con) throws SQLException {
        System.out.println("=== Deleting a project ===");

        System.out.print("Enter the project id: ");
        int projectId = Utils.readInt();

        ProjectBean project = new ProjectBean(projectId);
        int projectDeleted = ProjectModel.delete(project, con);

        if (projectDeleted == 0) {
            System.out.println("\nProject not deleted or it doesn't exist.");
        } else {
            System.out.println("\nProject deleted successfully!");
        }
    }
}

package org.project.cli.actions;

import org.project.cli.ActionLineInterface;
import org.project.controller.ProjectController;

import org.project.enums.UserEnum;
import org.project.model.ProjectModel;
import org.project.utils.Utils;

import java.sql.Connection;
import java.sql.SQLException;

public class HirerAction {

    public static void execute(Connection con) throws SQLException {
        int command;

        do {
            System.out.println("1 - Create project");
            System.out.println("2 - Edit project");
            System.out.println("3 - Delete project");
            System.out.println("4 - List projects");
            System.out.println("5 - Rating");
            System.out.println("6 - Reports");
            System.out.println("7 - Back to main menu");

            command = Utils.readInt();

            switch (command) {
                case 1:
                    ProjectController.createProject(con);
                    break;
                case 2:
                    ProjectModel.listAll(con);
                    ProjectController.updateProject(con);
                    break;
                case 3:
                    ProjectModel.listAll(con);
                    ProjectController.deleteProject(con);
                    break;
                case 4:
                    ProjectModel.listAll(con);
                    ListAction.execute(con, UserEnum.HIRER.getValue());
                    break;
                case 5:
                    return;
                case 6:
                    ReportAction.execute(UserEnum.HIRER.getValue(), con);
                case 7:
                    ActionLineInterface.execute(con);
                    break;
                default:
                    System.out.println("Invalid option.");
                    break;
            }
        } while (command != 0);
    }
}

package org.project.cli.actions;

import jdk.jshell.execution.Util;
import org.project.controller.ProposalController;
import org.project.enums.UserEnum;
import org.project.model.ProjectModel;
import org.project.model.ProposalModel;
import org.project.utils.Utils;

import java.sql.Connection;
import java.sql.SQLException;

public class ListAction {

    public static void execute(Connection con, int user) throws SQLException {

        if (UserEnum.HIRER.getValue() == user) {
            showHirerMenu(con);
        } else {
            showFreelancerMenu(con);
        }
    }

    private static void showFreelancerMenu(Connection con) throws SQLException {
        System.out.println("1 - Make proposal");
        System.out.println("2 - Edit proposal");
        System.out.println("3 - Delete proposal");
        System.out.println("4 - Back");

        int command;
        do {
            command = Utils.readInt();

            switch (command) {
                case 1:
                    ProposalController.createProposal(con);
                    return;
                case 2:
                    ProposalModel.listAll(con);
                    ProposalController.updateProposal(con);
                    return;
                case 3:
                    ProposalModel.listAll(con);
                    ProposalController.deleteProposal(con);
                    return;
                case 4:
                    FreelancerAction.execute(con);
                default:
                    System.out.println("Invalid option.");
                    break;
            }
        } while (command != 0);
    }

    private static void showHirerMenu(Connection con) throws SQLException {
        System.out.println("1 - Choose proposal");
        System.out.println("2 - Back");

        int command;
        do {
            command = Utils.readInt();

            switch (command) {
                case 1:
                    System.out.println("Enter the project id: ");
                    int projectId = Utils.readInt();

                    ProposalModel.listAllByProject(projectId, con);
                    ProposalController.acceptProposal(con);
                    break;
                case 2:
                    HirerAction.execute(con);
                    break;
                default:
                    System.out.println("Invalid option.");
                    break;
            }
        } while (command != 0);
    }
}

package org.project.cli.actions;

import org.project.controller.ProposalController;
import org.project.enums.UserEnum;
import org.project.model.ProposalModel;
import org.project.utils.Utils;

import java.sql.Connection;
import java.sql.SQLException;

public class ReportAction {

    public static void execute(int user, Connection con) throws SQLException {

        System.out.println("Which report would you like to generate?");

        if (UserEnum.HIRER.getValue() == user) {
            showHirerMenu(con);
        } else {
            showFreelancerMenu(con);
        }
    }

    private static void showFreelancerMenu(Connection con) throws SQLException {
        System.out.println("1 - Ratings report");
        System.out.println("2 - Back");

        int command;
        do {
            command = Utils.readInt();

            switch (command) {
                case 1:
                    ProposalController.createProposal(con);
                    break;
                case 2:
                    FreelancerAction.execute(con);
                default:
                    System.out.println("Invalid option.");
                    break;
            }
        } while (command != 0);
    }

    private static void showHirerMenu(Connection con) throws SQLException {
        System.out.println("1 - Proposals report");
        System.out.println("2 - Payments report");

        int command;
        do {
            command = Utils.readInt();

            switch (command) {
                case 1:
                    // listar quais propostas?
                    break;
                case 2:
                    break;
                default:
                    System.out.println("Invalid option.");
                    break;
            }
        } while (command != 0);
    }
}

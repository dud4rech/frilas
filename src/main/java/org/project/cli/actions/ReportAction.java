package org.project.cli.actions;

import org.project.enums.UserType;
import org.project.model.PaymentModel;
import org.project.model.ProposalModel;
import org.project.model.RatingModel;
import org.project.utils.Utils;

import java.sql.Connection;
import java.sql.SQLException;

public class ReportAction {

    public static void execute(int user, Connection con) throws SQLException {

        System.out.println("\nWhich report would you like to generate?");

        if (UserType.HIRER.getValue() == user) {
            showHirerMenu(con);
        } else {
            showFreelancerMenu(con);
        }
    }

    private static void showFreelancerMenu(Connection con) throws SQLException {
        System.out.println("\n1 - Ratings report");
        System.out.println("2 - Back\n");

        int command;

        do {
            command = Utils.readInt();

            switch (command) {
                case 1:
                    System.out.println(" --- RATINGS REPORT ---");
                    RatingModel.listAllByFreelancer(con);
                    return;
                case 2:
                    FreelancerAction.execute(con);
                default:
                    System.out.println("Invalid option.");
                    break;
            }
        } while (command != 0);
    }

    private static void showHirerMenu(Connection con) throws SQLException {
        System.out.println("\n1 - Proposals report");
        System.out.println("2 - Payments report");
        System.out.println("3 - Back\n");

        int command;
        do {
            command = Utils.readInt();

            switch (command) {
                case 1:
                    System.out.println(" --- PROPOSALS REPORT ---");
                    ProposalModel.listAllByUser(con);
                    return;
                case 2:
                    System.out.println(" --- PAYMENTS REPORT ---");
                    PaymentModel.listAllByUser(con);
                    return;
                case 3:
                    HirerAction.execute(con);
                    return;
                default:
                    System.out.println("Invalid option.");
                    break;
            }
        } while (command != 0);
    }
}

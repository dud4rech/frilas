package org.project.cli.actions;

import org.project.cli.ActionLineInterface;
import org.project.controller.FreelancerController;
import org.project.controller.HirerController;
import org.project.utils.Utils;

import javax.swing.Action;
import java.sql.Connection;
import java.sql.SQLException;

public class RegisterAction {

    public static void execute(Connection con) throws SQLException {
        int command;

        do {
            System.out.println("xxxxx?");
            System.out.println("1 - Freelancer");
            System.out.println("2 - Hirer");
//            System.out.println("3 - Project");
//            System.out.println("4 - Proposal");
            System.out.println("5 - Back to main menu");

            command = Utils.readInt();

            switch (command) {
                case 1:
                    FreelancerController.createFreelancer(con);
                    break;
                case 2:
                    HirerController.createHirer(con);
                    break;
                case 5:
                    ActionLineInterface.execute(con);
                    break;
                default:
                    System.out.println("Invalid option");
                    break;
            }
        } while (command != 5);
    }
}

package org.project.cli;

import org.project.cli.actions.FreelancerAction;
import org.project.cli.actions.HirerAction;
import org.project.cli.actions.LoginAction;
import org.project.cli.actions.RegisterAction;
import org.project.utils.Utils;

import java.sql.Connection;
import java.sql.SQLException;

public class ActionLineInterface {

    public static void execute(Connection con) throws SQLException {
        System.out.println("\nWelcome!");

        int command;

        do {
            System.out.println("1 - Login");
            System.out.println("2 - Register");
            System.out.println("3 - Exit");

            command = Utils.readInt();

            switch (command) {
                case 1:
                    LoginAction.execute(con);
                    break;
                case 2:
                    RegisterAction.execute(con);
                    break;
                case 0:
                    System.out.println("Bye bye...");
                    break;
                default:
                    System.out.println("Invalid option");
                    break;
            }
        } while (command != 0);
    }
}

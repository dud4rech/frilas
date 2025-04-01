package org.project.cli;

import org.project.cli.actions.RegisterAction;
import org.project.utils.Utils;

import java.sql.Connection;
import java.sql.SQLException;

public class ActionLineInterface {

    public static void execute(Connection con) throws SQLException {
        System.out.println("Welcome!");

        int command;

        do {
            System.out.println("xxxxxr:");
            System.out.println("1 - Register");
//            System.out.println("2 - Update");
//            System.out.println("3 - Delete");
//            System.out.println("4 - List");
//            System.out.println("5 - Reports");
            System.out.println("6 - Exit");

            command = Utils.readInt();

            switch (command) {
                case 1:
                    RegisterAction.execute(con);
                    break;
//                case 2:
//                    UpdateAction.updateMenu(con);
//                    break;
//                case 3:
//                    DeleteAction.deleteMenu(con);
//                    break;
//                case 4:
//                    ListAction.listMenu(con);
//                    break;
//                case 5:
//                    ReportAction.reportMenu(con);
//                    break;
                case 6:
                    System.out.println("Bye bye...");
                    return;
                default:
                    System.out.println("Invalid option");
                    break;
            }
        } while (command != 0);
    }
}

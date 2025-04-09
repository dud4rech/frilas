package org.project.cli.actions;

import org.project.cli.ActionLineInterface;
import org.project.enums.UserEnum;
import org.project.model.ProjectModel;
import org.project.utils.Utils;

import java.sql.Connection;
import java.sql.SQLException;

public class FreelancerAction {

    public static void execute(Connection con) throws SQLException {
        int command;

        do {
            System.out.println("1 - List projects");
            System.out.println("2 - Reports");
            System.out.println("3 - Back");

            command = Utils.readInt();

            switch (command) {
                case 1:
                    ProjectModel.listAll(con);
                    ListAction.execute(con, UserEnum.FREELANCER.getValue());
                    break;
                case 2:
                    ReportAction.execute(UserEnum.FREELANCER.getValue(), con);
                case 3:
                    ActionLineInterface.execute(con);
                    break;
                default:
                    System.out.println("Invalid option.");
                    break;
            }
        } while (command != 0);
    }
}

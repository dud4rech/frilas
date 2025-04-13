package org.project.cli.actions;

import org.project.cli.ActionLineInterface;
import org.project.controller.RatingController;
import org.project.model.RatingModel;
import org.project.utils.Utils;

import java.sql.Connection;
import java.sql.SQLException;

public class RatingAction {

    public static void execute(Connection con) throws SQLException {
        int command;

        do {
            System.out.println("1 - Create rating");
            System.out.println("2 - Delete rating");
            System.out.println("3 - Back");

            command = Utils.readInt();

            switch (command) {
                case 1:
                    boolean hasRelatedFreelancer = RatingModel.listFreelancersInProjects(con);
                    if (hasRelatedFreelancer) RatingController.createRating(con);
                    return;
                case 2:
                    boolean hasRatings = RatingModel.listAllByHirer(con);
                    if (hasRatings) RatingController.deleteRating(con);
                    return;
                case 3:
                    ActionLineInterface.execute(con);
                default:
                    System.out.println("Invalid option.");
                    break;
            }
        } while (command != 0);
    }
}

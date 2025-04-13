package org.project.controller;

import org.project.bean.RatingBean;
import org.project.cli.actions.LoginAction;
import org.project.model.ProjectModel;
import org.project.model.RatingModel;
import org.project.utils.Utils;

import java.sql.Connection;
import java.sql.SQLException;

public class RatingController {

    public static void createRating(Connection con) throws SQLException {
        System.out.println("=== Creating a new rating ===");

        System.out.print("Enter the freelancer id: ");
        int freelancerId = Utils.readInt();

        System.out.print("Enter the rating value: ");
        int ratingValue = Utils.readInt();

        System.out.print("Enter the rating description: ");
        String ratingDescription = Utils.readString();

        int hirerId = LoginAction.getLoggedUser();

        RatingBean rating = new RatingBean(ratingValue, ratingDescription, freelancerId, hirerId);
        RatingModel.create(rating, con);

        System.out.println("\nRating created successfully!");
    }

    public static void deleteRating(Connection con) throws SQLException {
        System.out.println("=== Deleting a rating ===");

        System.out.print("Enter the rating id ");
        int ratingId = Utils.readInt();

        RatingBean proposal = new RatingBean(ratingId);

        int ratingDeleted = RatingModel.delete(proposal, con);

        if (ratingDeleted == 0) {
            System.out.println("\nRating not deleted or it doesn't exist.");
        } else {
            System.out.println("\nRating deleted successfully!");
        }
    }
}
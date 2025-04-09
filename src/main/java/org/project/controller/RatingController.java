package org.project.controller;

import org.project.bean.RatingBean;
import org.project.model.RatingModel;
import org.project.utils.Utils;

import java.sql.Connection;
import java.sql.SQLException;

public class RatingController {

    public static void createRating(Connection con) throws SQLException {
        System.out.println("\n=== Creating a new rating ===\n");

        System.out.print("Enter the freelancer name: ");
        String freelancerName = Utils.readString();
        // find by name

        System.out.print("Enter the rating value: ");
        int ratingValue = Utils.readInt();

        System.out.print("Enter the rating message: ");
        String ratingMessage = Utils.readString();

        RatingBean rating = new RatingBean(ratingValue, ratingMessage, freelancerId);
        RatingModel.create(rating, con);

        System.out.println("\nRating created successfully!\n");
    }

    public static void updateRating(Connection con) throws SQLException {
        System.out.println("\n=== Editing a rating ===\n");

        System.out.print("Enter the rating id: ");
        int ratingId = Utils.readInt();

        System.out.print("Enter the rating value: ");
        int proposalValue = Utils.readInt();

        System.out.print("Enter the rating message: ");
        String proposalMessage = Utils.readString();

        RatingBean rating = new RatingBean(ratingId, proposalValue, proposalMessage);
        RatingModel.update(rating, con);

        System.out.println("\nRating edited successfully!\n");
    }

    public static void deleteRating(Connection con) throws SQLException {
        System.out.println("\n=== Deleting a rating===\n");

        System.out.print("Enter the rating id ");
        int ratingId = Utils.readInt();

        RatingBean proposal= new RatingBean(ratingId);
        RatingModel.delete(proposal, con);

        System.out.println("\nRating deleted successfully!\n");
    }
}
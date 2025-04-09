package org.project.controller;


import org.project.bean.FreelancerBean;
import org.project.bean.HirerBean;
import org.project.model.FreelancerModel;
import org.project.model.HirerModel;
import org.project.utils.Utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class HirerController {

    public static void createHirer(Connection con) throws SQLException {
        System.out.println("=== Creating a new hirer ===");

        System.out.print("Enter the hirer’s name: ");
        String hirerName = Utils.readString();

        System.out.print("Enter the hirer’s phone number: ");
        String hirerPhone = Utils.readString();

        System.out.print("Enter the hirer’s email: ");
        String hirerMail = Utils.readString();

        HirerBean hirer = new HirerBean(hirerName, hirerPhone, hirerMail);
        try {
            HirerModel.create(hirer, con);
            System.out.println("\nNew account created successfully!\n");
        } catch (SQLException e) {
            if (e.getSQLState().equals("23505")) {
                System.out.println("\nThe email provided is already in use. Try another one.\n");
            } else {
                System.out.println("\nError: " + e.getMessage() + "\n");
                throw e;
            }
        }
    }
}

package org.project.controller;

import org.project.bean.PaymentBean;
import org.project.cli.actions.LoginAction;
import org.project.model.PaymentModel;
import org.project.model.ProjectModel;
import org.project.utils.Utils;

import java.sql.Connection;
import java.sql.SQLException;

public class PaymentController {

    public static void createPayment(int freelancerId, int proposalValue, String projectDeadline, int projectId, Connection con) throws SQLException {
        int hirerId = LoginAction.getLoggedUser();

        PaymentBean payment = new PaymentBean(proposalValue, projectDeadline, projectId, freelancerId, hirerId);
        PaymentModel.create(payment, con);

        System.out.println("\nPayment created successfully!");
    }

    public static void makePayment(Connection con) throws SQLException {
        System.out.println("\n=== Making a payment ===\n");

        System.out.print("Enter the payment id: ");
        int paymentId = Utils.readInt();

        PaymentBean payment = new PaymentBean(paymentId);
        PaymentModel.updateStatus(payment, con);
        ProjectModel.finishProject(paymentId, con);

        System.out.println("\nPayment made successfully!");
    }
}
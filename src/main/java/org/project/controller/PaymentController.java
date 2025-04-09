package org.project.controller;

import org.project.bean.PaymentBean;
import org.project.model.PaymentModel;
import org.project.utils.Utils;

import java.sql.Connection;
import java.sql.SQLException;

public class PaymentController {

    public static void createPayment(Connection con) throws SQLException {
        System.out.println("\n=== Creating a new rating ===\n");

        // pegar freelancer e hirer id

        System.out.print("Enter the payment value: ");
        int paymentValue = Utils.readInt();

        System.out.print("Enter the date: ");
        String paymentDate = Utils.readString();

        PaymentBean payment = new PaymentBean(paymentValue, paymentDate, freelancerId, hirerId);
        PaymentModel.create(payment, con);

        System.out.println("\nPayment created successfully!\n");
    }
}
package org.project.model;

import org.project.bean.PaymentBean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PaymentModel {

    public static void create(PaymentBean payment, Connection con) throws SQLException {
        PreparedStatement ps;
        ps = con.prepareStatement("INSERT INTO payment("
                + "	paymentvalue, paymentdate, freelancerid, hirerid)"
                + "	VALUES (?, ?, ?, ?);");
        ps.setInt(1, payment.getPaymentValue());
        ps.setString(2, payment.getPaymentDate());
        ps.setInt(3, payment.getFreelancerId());
        ps.setInt(4, payment.getHirerid());
        ps.execute();
        ps.close();
    }
}
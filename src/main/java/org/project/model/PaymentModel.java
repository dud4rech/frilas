package org.project.model;

import org.project.bean.PaymentBean;
import org.project.cli.actions.LoginAction;
import org.project.enums.PaymentStatus;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PaymentModel {

    public static void create(PaymentBean payment, Connection con) throws SQLException {
        PreparedStatement ps;
        ps = con.prepareStatement("INSERT INTO payment("
                + "	paymentvalue, paymentdate, freelancerid, hirerid, paymentstatus)"
                + "	VALUES (?, ?, ?, ?, ?);");
        ps.setInt(1, payment.getPaymentValue());
        ps.setString(2, payment.getPaymentDate());
        ps.setInt(3, payment.getFreelancerId());
        ps.setInt(4, payment.getHirerid());
        ps.setInt(5, PaymentStatus.PENDING.getValue());
        ps.execute();
        ps.close();
    }

    public static int updateStatus(PaymentBean payment, Connection con) throws SQLException {
        String sql = "UPDATE payment"
                + "	SET paymentstatus=?"
                + "	WHERE paymentid=?;";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, PaymentStatus.PAYED.getValue());
            ps.setInt(2, payment.getPaymentId());

            return ps.executeUpdate();
        }
    }

    public static boolean listAllPendingPaymentByUser(Connection con) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        int hirerId = LoginAction.getLoggedUser();

        try {
            boolean hasPayments = false;

            ps = con.prepareStatement("SELECT * FROM payment " +
                    "WHERE hirerid = ? " +
                    "AND paymentstatus = ? " +
                    "ORDER BY paymentid");
            ps.setInt(1, hirerId);
            ps.setInt(2, PaymentStatus.PENDING.getValue());
            rs = ps.executeQuery();

            while (rs.next()) {
                hasPayments = true;
                int paymentId = rs.getInt("paymentid");
                String paymentName = rs.getString("paymentvalue");
                String paymentMessage = rs.getString("paymentdate");
                int paymentStatus = rs.getInt("paymentstatus");

                String statusDescription = PaymentStatus.fromValue(paymentStatus).getDescription();

                System.out.println("Payment ID: " + paymentId);
                System.out.println("Value: " + paymentName);
                System.out.println("Date: " + paymentMessage);
                System.out.println("Status: " + statusDescription);
                System.out.println("------------------------");
            }
            if (!hasPayments) {
                System.out.println("\nThere are no payments created.");
            }
            return hasPayments;
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
            throw e;
        } finally {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
        }
    }

    public static boolean listAllByUser(Connection con) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        int hirerId = LoginAction.getLoggedUser();

        try {
            ps = con.prepareStatement("SELECT * FROM payment " +
                    "WHERE hirerid = ? " +
                    "ORDER BY paymentid");
            ps.setInt(1, hirerId);
            rs = ps.executeQuery();

            boolean hasPayments = false;

            while (rs.next()) {
                hasPayments = true;
                int paymentId = rs.getInt("paymentid");
                String paymentName = rs.getString("paymentvalue");
                String paymentMessage = rs.getString("paymentdate");
                int paymentStatus = rs.getInt("paymentstatus");

                String statusDescription = PaymentStatus.fromValue(paymentStatus).getDescription();

                System.out.println("Payment ID: " + paymentId);
                System.out.println("Value: " + paymentName);
                System.out.println("Date: " + paymentMessage);
                System.out.println("Status: " + statusDescription);
                System.out.println("------------------------");
            }
            if (!hasPayments) {
                System.out.println("\nThere are no payments created.");
            }
            return hasPayments;
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
            throw e;
        } finally {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
        }
    }
}
package org.project.cli.actions;

import org.project.utils.Utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginAction {
    private static int loggedUser;

    public static void execute(Connection con) throws SQLException {
        System.out.println("Are you a Freelancer (1) or a Hirer (2)?");
        int command = Utils.readInt();

        System.out.print("E-mail: ");
        String email = Utils.readString();

        if (command == 1) {
            if (authenticateFreelancer(email, con)) {
                System.out.println("Login was successful!");
                FreelancerAction.execute(con);
            }
        } else if (command == 2) {
            if (authenticateHirer(email, con)) {
                System.out.println("Login was successful!");
                HirerAction.execute(con);
            }
        }
        System.out.println("Login failed\n");
    }

    private static boolean authenticateFreelancer(String email, Connection con) throws SQLException {
        String sql = "SELECT freelancerid FROM freelancer WHERE freelancermail = ?";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setString(1, email);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            loggedUser = rs.getInt("freelancerid");
            rs.close();
            stmt.close();
            return true;
        }
        rs.close();
        stmt.close();
        return false;
    }

    private static boolean authenticateHirer(String email, Connection con) throws SQLException {
        String sql = "SELECT hirerid FROM hirer WHERE hirermail = ?";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setString(1, email);
        ResultSet rs = stmt.executeQuery();


        if (rs.next()) {
            loggedUser = rs.getInt("hirerid");
            rs.close();
            stmt.close();
            return true;
        }
        rs.close();
        stmt.close();
        return false;
    }

    public static int getLoggedUser() {
        return loggedUser;
    }
}

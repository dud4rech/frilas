package org.project.model;

import org.project.bean.FreelancerBean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FreelancerModel {

    public static void create(FreelancerBean freelancer, Connection con) throws SQLException {
        PreparedStatement ps;
        ps = con.prepareStatement("INSERT INTO freelancer("
                + "	freelancername, freelancerphone, freelancermail, isActive)"
                + "	VALUES (?, ?, ?, ?);");
        ps.setString(1, freelancer.getFreelancerName());
        ps.setString(2, freelancer.getFreelancerPhone());
        ps.setString(3, freelancer.getFreelancerMail());
        ps.setBoolean(4, true);
        ps.execute();
        ps.close();
    }

    public static void update(FreelancerBean freelancer, Connection con) throws SQLException {
        PreparedStatement ps;
        ps = con.prepareStatement("UPDATE freelancer"
                + "	SET freelancername=?, freelancerphone=?, freelancermail=?"
                + "	WHERE freelancerid=?;");
        ps.setString(1, freelancer.getFreelancerName());
        ps.setString(2, freelancer.getFreelancerPhone());
        ps.setString(3, freelancer.getFreelancerMail());
        ps.execute();
        ps.close();
    }

    public static void delete(FreelancerBean freelancer, Connection con) throws SQLException {
        PreparedStatement ps;
        ps = con.prepareStatement("UPDATE freelancer"
                + " SET isActive = ?"
                + "	WHERE freelancerid=?;");
        ps.setBoolean(1, false);
        ps.setInt(2, freelancer.getFreelancerId());
        ps.execute();
        ps.close();
    }
}

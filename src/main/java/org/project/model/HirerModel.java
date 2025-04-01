package org.project.model;

import org.project.bean.HirerBean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class HirerModel {

    public static void create(HirerBean hirer, Connection connection) throws SQLException {
        PreparedStatement ps;
        ps = connection.prepareStatement("INSERT INTO hirer("
                + "	hirername, hirerphone, hirermail, isActive)"
                + "	VALUES (?, ?, ?, ?);");
        ps.setString(1, hirer.getHirerName());
        ps.setString(2, hirer.getHirerPhone());
        ps.setString(3, hirer.getHirerMail());
        ps.setBoolean(4, true);
        ps.execute();
        ps.close();
    }

    public static void update(HirerBean hirer, Connection connection) throws SQLException {
        PreparedStatement ps;
        ps = connection.prepareStatement("UPDATE hirer"
                + "	SET hirername=?, hirerphone=?, hirermail=?"
                + "	WHERE hirerid=?;");
        ps.setString(1, hirer.getHirerName());
        ps.setString(2, hirer.getHirerPhone());
        ps.setString(3, hirer.getHirerMail());
        ps.execute();
        ps.close();
    }

    public static void delete(HirerBean hirer, Connection connection) throws SQLException {
        PreparedStatement ps;
        ps = connection.prepareStatement("UPDATE hirer"
                + " SET isActive = ?"
                + "	WHERE hirerid=?;");
        ps.setBoolean(1, false);
        ps.setInt(2, hirer.getHirerId());
        ps.execute();
        ps.close();
    }
}

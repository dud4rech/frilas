package org.project;

import org.project.cli.ActionLineInterface;
import org.project.connection.DBConnection;

import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        DBConnection connection = new DBConnection();
        Connection con = connection.getConnection();

        ActionLineInterface.execute(con);
        con.close();
    }
}
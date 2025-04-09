package org.project.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBConnection {
    private Connection con;
    private String loggedUserEmail;
    private static final Scanner scanner = new Scanner(System.in);

    public DBConnection() {
        String driver = "org.postgresql.Driver";
        String user = "postgres";
        String senha = "123";
        String url = "jdbc:postgresql://localhost:5432/frilas";

        try {
            Class.forName(driver);
            this.con = (Connection) DriverManager.getConnection(url, user, senha);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
            System.exit(1);
        } catch (SQLException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
            System.exit(1);
        }
    }

    public Connection getConnection() {
        return con;
    }

    public void closeConnection(){
        try {
            this.con.close();
        } catch (SQLException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
            System.exit(1);
        }
    }
}

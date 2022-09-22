package src;

import java.sql.*;

public class Main{
    public static void main(String[] args) {

        System.out.println("test run java");

        boolean connectionEstablished = false;
        while (!connectionEstablished) {
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                Connection conn = DriverManager.getConnection("jdbc:mysql://10.97.96.180:3306/", "root", "root");
                if (conn != null) {
                    connectionEstablished = true;
                    System.out.println("Connection to db successful!");
                    conn.createStatement().execute("SET GLOBAL max_connections = 2000;");
                    conn.createStatement().execute("USE db;");
                    conn.createStatement().execute("CREATE TABLE IF NOT EXISTS time(seconds int, PRIMARY KEY (seconds));");
                    conn.createStatement().executeUpdate("TRUNCATE TABLE time");
                }
                conn.close();
            } catch (Exception e) {
                System.err.println("Got an exception!");
                System.err.println(e.getMessage());
            }
            if (!connectionEstablished) {
                System.err.println("Could not connect to the database!");
            }
        }

        System.out.println("Got through the connection code!");

    }

}
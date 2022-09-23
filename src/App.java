package src;

import java.sql.*;

public class App{
    public static void main(String[] args) {

        boolean connectionEstablished = false;
        Connection conn;
        int secondsFromDb = -1;
        int nextSecondsValue;

        while (!connectionEstablished) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                conn = DriverManager.getConnection("jdbc:mysql://db:3306/", "root", "");
                if (conn != null) {
                    connectionEstablished = true;
                    conn.createStatement().execute("SET GLOBAL max_connections = 2000;");
                    conn.createStatement().execute("CREATE DATABASE IF NOT EXISTS db;");
                    conn.createStatement().execute("USE db;");
                    conn.createStatement().execute("CREATE TABLE IF NOT EXISTS time(seconds int, PRIMARY KEY (seconds));");
                    //insert zero if no value present otherwise update to zero
                    ResultSet r = conn.createStatement().executeQuery("SELECT seconds from time");
                    if (r.next() == false){
                        conn.createStatement().execute("INSERT into time (seconds) VALUES (0);");
                    } else {
                        conn.createStatement().executeUpdate("UPDATE time SET seconds = 0;");
                    }
                    //read write loop
                    while (true) {
                        Thread.sleep(1000);
                        ResultSet rs = conn.createStatement().executeQuery("SELECT seconds from time");
                        while (rs.next()) {
                            secondsFromDb = rs.getInt("seconds");
                        }
                        secondsFromDb++;
                        nextSecondsValue = secondsFromDb;
                        conn.createStatement().executeUpdate("UPDATE time SET seconds = " + nextSecondsValue + ";");
                    }
                }
                conn.close();
            } catch (Exception e) {
                System.err.println("Got an exception in conn part!");
                System.err.println(e.getMessage());
            }
            if (!connectionEstablished) {
                System.err.println("Could not connect to the database!");
            }
        }
    }

}
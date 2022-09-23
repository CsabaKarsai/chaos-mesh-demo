package src;

import java.sql.*;

public class Reader {

    public static void main(String[] args) {
        
        boolean connectionEstablished = false;
        Connection conn;
        int secondsFromDb = -1;

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
                    conn.createStatement().execute("USE db;");
                    //read loop
                    while (true) {
                        Thread.sleep(1000);
                        ResultSet rs = conn.createStatement().executeQuery("SELECT seconds from time");
                        while (rs.next()) {
                            secondsFromDb = rs.getInt("seconds");
                        }
                        System.out.println("Seconds: " + secondsFromDb);
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

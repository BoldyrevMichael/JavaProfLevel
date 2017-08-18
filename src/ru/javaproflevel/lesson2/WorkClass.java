package ru.javaproflevel.lesson2;

import java.sql.*;

public class WorkClass {

    private static Connection connection;
    private static Statement stmt;
    private static PreparedStatement ps;


    public static void main(String[] args) throws Exception {
        connect();
        stmt = connection.createStatement();
        ps = connection.prepareStatement("INSERT INTO Products (prodid, title, cost) VALUES (?, ?, ?)");

        stmt.execute("CREATE TABLE IF NOT EXISTS Products (\n" +
                "    id      INTEGER PRIMARY KEY AUTOINCREMENT, \n" +
                "    prodid  INTEGER,\n" +
                "    title   TEXT,\n" +
                "    cost    DECIMAL(9,2));");

        stmt.execute("DELETE FROM Products");
        stmt.execute("UPDATE SQLITE_SEQUENCE SET SEQ=0 WHERE NAME='Products';");


        connection.setAutoCommit(false);

        for (int i = 1; i < 10000; i++) {
            ps.setInt(1, i);
            String s = "товар" + i;
            ps.setString(2, s);
            Double c = (double)i*10;
            ps.setDouble(3, c);
            ps.executeUpdate();
        }
        connection.commit();
        ResultSet res = stmt.executeQuery("SELECT * FROM Products");

        while (res.next()) {
            System.out.println(
                    res.getInt("id")
                            + " " + res.getInt("prodid")
                            + " " + res.getString("title")
                            + " " + res.getDouble("cost")
            );
        }

        disconnect();
    }

    public static void connect() {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:MainDb.db");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

    }

    public static void disconnect() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}

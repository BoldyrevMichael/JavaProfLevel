package ru.javaproflevel.lesson2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.Arrays;

public class WorkClassLesson2 {

    private static Connection connection;
    private static Statement stmt;
    private static PreparedStatement ps;
    public static final String DELIMITER = " ";
    public static final String COST_REQUEST = "/цена";
    public static final String CHANGE_COST_REQUEST = "/сменитьцену";
    public static final String COST_RANGE_REQUEST = "/товарыпоцене";
    public static final String EXIT = "/выход";

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

        for (int i = 1; i < 15; i++) {
            ps.setInt(1, i);
            ps.setString(2, "товар" + i);
            ps.setDouble(3, (double) i * 10);
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

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String s;
        while ((s = in.readLine()) != null && s.length() != 0)
            handleRequest(in, s);

        disconnect();
    }

    public static void handleRequest(BufferedReader in, String msg) throws Exception {

        String[] tokens = msg.split(DELIMITER);
        if (tokens.length < 2 || tokens.length > 3 || tokens[0].equals(EXIT)) {
            //in.close();
            System.out.println("Ошибка в команде");
            return;
        }
        String type = tokens[0];
        switch (type) {
            case COST_REQUEST:
                ps = connection.prepareStatement("SELECT * FROM Products WHERE title = ?");
                ps.setString(1, tokens[1]);
                ResultSet res1 = ps.executeQuery();
                if (!res1.next()) {
                    System.out.println("Такого товара нет");
                }
                ResultSet res11 = ps.executeQuery();
                while (res11.next()) {
                    System.out.println(
                            res11.getInt("id")
                                    + " " + res11.getInt("prodid")
                                    + " " + res11.getString("title")
                                    + " " + res11.getDouble("cost")
                    );
                }
                break;
            case CHANGE_COST_REQUEST:
                ps = connection.prepareStatement("SELECT * FROM Products WHERE title = ?");
                ps.setString(1, tokens[1]);
                ResultSet res2 = ps.executeQuery();
                if (!res2.next()) {
                    System.out.println("Такого товара нет");
                }
                if (isNumber(tokens[2])) {
                    ps = connection.prepareStatement("UPDATE Products SET cost = ? WHERE title = ?");
                    ps.setDouble(1, Double.parseDouble(tokens[2]));
                    ps.setString(2, tokens[1]);
                    ps.executeUpdate();
                    ps = connection.prepareStatement("SELECT * FROM Products WHERE title = ?");
                    ps.setString(1, tokens[1]);
                    ResultSet res22 = ps.executeQuery();
                    while (res22.next()) {
                        System.out.println(
                                res22.getInt("id")
                                        + " " + res22.getInt("prodid")
                                        + " " + res22.getString("title")
                                        + " " + res22.getDouble("cost")
                        );
                    }
                } else System.out.println("Ошибка в команде");
                break;
            case COST_RANGE_REQUEST:
                if (isNumber(tokens[1]) && isNumber(tokens[2])) {
                    ps = connection.prepareStatement("SELECT * FROM Products WHERE cost >= ? AND cost <= ?");
                    ps.setDouble(1, Double.parseDouble(tokens[1]));
                    ps.setDouble(2, Double.parseDouble(tokens[2]));
                    ResultSet res3 = ps.executeQuery();
                    while (res3.next()) {
                        System.out.println(
                                res3.getInt("id")
                                        + " " + res3.getInt("prodid")
                                        + " " + res3.getString("title")
                                        + " " + res3.getDouble("cost")
                        );
                    }

                } else System.out.println("Ошибка в команде");
                break;
            default:
                System.out.println("Неизвестная команда");
        }
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

    public static boolean isNumber(String str) {
        if (str == null || str.isEmpty()) return false;
        for (int i = 0; i < str.length(); i++) {
            if (!Character.isDigit(str.charAt(i))) return false;
        }
        return true;
    }

}

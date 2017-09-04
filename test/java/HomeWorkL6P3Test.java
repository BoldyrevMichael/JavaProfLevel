import com.sun.org.apache.xpath.internal.SourceTree;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.*;

public class HomeWorkL6P3Test {
    private static Connection connection;
    private static Statement stmt;
    private static PreparedStatement ps;
    //private static Savepoint sp;
    private static HomeWorkL6P3Test homeWorkL6P3Test;
    private static int markOfTestStudent;

    @Before
    public void createAndconnect() throws Exception {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:MainDbL6.db");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        stmt = connection.createStatement();
        stmt.execute("CREATE TABLE IF NOT EXISTS Students (\n" +
                "    id      INTEGER PRIMARY KEY AUTOINCREMENT, \n" +
                "    surname  TEXT,\n" +
                "    mark   INTEGER);");

        stmt.execute("DELETE FROM Students");
        stmt.execute("UPDATE SQLITE_SEQUENCE SET SEQ=0 WHERE NAME='Students';");
        connection.setAutoCommit(false);
        ps = connection.prepareStatement("INSERT INTO Students (id, surname, mark) VALUES (?, ?, ?)");
        for (int i = 1; i < 10; i++) {
            ps.setInt(1, i);
            ps.setString(2, "Студент" + i);
            ps.setInt(3, (int) (Math.random() * 100));
            ps.executeUpdate();
        }
        connection.commit();
       // sp = connection.setSavepoint();

        ps = connection.prepareStatement("SELECT mark  FROM Students WHERE surname = ?");
        ps.setString(1, "Студент1");
        ResultSet res = ps.executeQuery();
        markOfTestStudent = res.getInt("mark");
        homeWorkL6P3Test = new HomeWorkL6P3Test();
    }

    @Test
    public void testInsert() throws Exception {
        String s = "Иванов55";
        Assert.assertArrayEquals(s.toCharArray(), homeWorkL6P3Test.insert("Иванов", 55));
        //connection.rollback(sp);
    }


    @Test
    public void testSelect() throws Exception {
        String s = "Студент1" + markOfTestStudent;
        Assert.assertArrayEquals(s.toCharArray(), homeWorkL6P3Test.select("Студент1"));
    }

    @Test
    public void testUpdate() throws Exception {
        String s = "Студент1" + 99;
        Assert.assertArrayEquals(s.toCharArray(), homeWorkL6P3Test.update("Студент1", 99));
        //connection.rollback(sp);
    }

    @After
    public void disconnect() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public char[] insert(String fam, int m) throws Exception {

        StringBuilder sb = new StringBuilder();

        ps = connection.prepareStatement("INSERT INTO Students (surname, mark) VALUES (?, ?)");
        ps.setString(1, fam);
        ps.setInt(2, m);
        ps.executeUpdate();

        ResultSet res = stmt.executeQuery("SELECT surname, mark  FROM Students WHERE surname = '" + fam + "'");
        while (res.next()) {
            sb.append(res.getString("surname"));
            sb.append(res.getInt("mark"));
        }
        return sb.toString().toCharArray();
    }

    public char[] select(String fam) throws Exception {
        StringBuilder sb = new StringBuilder();

        ps = connection.prepareStatement("SELECT surname, mark  FROM Students WHERE surname = ?");
        ps.setString(1, fam);
        ResultSet res = ps.executeQuery();

        while (res.next()) {
            sb.append(res.getString("surname"));
            sb.append(res.getInt("mark"));
        }
        return sb.toString().toCharArray();
    }

    public char[] update(String fam, int m) throws Exception {
        StringBuilder sb = new StringBuilder();

        ps = connection.prepareStatement("UPDATE Students SET mark = ? WHERE surname = ?");
        ps.setInt(1, m);
        ps.setString(2, fam);
        ps.executeUpdate();

        ResultSet res = stmt.executeQuery("SELECT surname, mark  FROM Students WHERE surname = '" + fam + "'");
        while (res.next()) {
            sb.append(res.getString("surname"));
            sb.append(res.getInt("mark"));
        }
        return sb.toString().toCharArray();
    }

}

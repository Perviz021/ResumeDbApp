package dao.inter;

import java.sql.Connection;
import java.sql.DriverManager;

public class AbstractDao {
    public static Connection connect() throws Exception {
        //Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/resume";
        String username = "root";
        String password = "113355";
        return DriverManager.getConnection(url, username, password);
    }
}

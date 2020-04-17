package realational;

import java.io.File;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    // 用户信息
    public static class User {
        Integer id;
        String name;

        @Override
        public String toString() {
            return "User{" + "id=" + id + ", name='" + name + '\'' + ", tel='" + '}';
        }
    }

    public static List<User> getUsers(Connection dbConnection) throws SQLException {
        List<User> users = new ArrayList<>();
        try (PreparedStatement statement = dbConnection.prepareStatement("select * from user")) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                User user = new User();
                user.id = resultSet.getInt(1);
                user.name = resultSet.getString(2);
                users.add(user);
            }
        }
        return users;
    }

    public static void main(String[] args) throws SQLException {
        File projectDir = new File(System.getProperty("basedir", System.getProperty("user.dir")));
        String jdbcUrl = "jdbc:h2:file:" + new File(projectDir, "target/").getAbsolutePath();
        System.out.println(jdbcUrl);
        try (Connection connection = DriverManager.getConnection(jdbcUrl, "root", "123456")) {

        }
    }
}

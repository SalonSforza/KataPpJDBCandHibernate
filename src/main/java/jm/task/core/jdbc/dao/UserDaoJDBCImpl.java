package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {



    private static final String CREATE_USERS_TABLE = """
            CREATE TABLE IF NOT EXISTS users (
            id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
            userName TEXT NOT NULL,
            lastName TEXT NOT NULL,
            age TINYINT NOT NULL);
            """;

    private static final String DROP_USERS_TABLE = """
            DROP TABLE IF EXISTS users;
            """;

    private static final String SAVE_USER = """
            INSERT INTO users (userName, lastName, age) values (?, ?, ?);
            """;

    private static final String REMOVE_BY_ID = """
            DELETE FROM users WHERE id = ?;
            """;

    private static final String GET_ALL_USERS = """
            SELECT userName, lastName, age FROM users;
            """;

    private static final String CLEAN_USERS = """
            TRUNCATE TABLE users;
            """;
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
    openConnectionAndPrepStatement(CREATE_USERS_TABLE);
    }

    public void dropUsersTable() {
   openConnectionAndPrepStatement(DROP_USERS_TABLE);
    }

    public void saveUser(String name, String lastName, byte age) {
        try (Connection connection = Util.open();
             PreparedStatement preparedStatement = connection.prepareStatement(SAVE_USER);) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void removeUserById(long id) {
        try (Connection connection = Util.open();
             PreparedStatement preparedStatement = connection.prepareStatement(REMOVE_BY_ID)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<User> getAllUsers() {
        try (Connection connection = Util.open();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_USERS);) {
            ResultSet resultSet = preparedStatement.executeQuery();
            List<User> users = new ArrayList<>();
            while (resultSet.next()) {
                users.add(new User(
                        resultSet.getString("userName"),
                        resultSet.getString("lastName"),
                        resultSet.getByte("age")
                ));
            }
            return users;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void cleanUsersTable() {
        openConnectionAndPrepStatement(CLEAN_USERS);
    }

    public void openConnectionAndPrepStatement(String sql) {
        try (Connection connection = Util.open();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);) {
            preparedStatement.execute(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

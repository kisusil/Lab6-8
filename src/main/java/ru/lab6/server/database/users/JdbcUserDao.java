package ru.lab6.server.database.users;

import ru.lab6.common.user.User;

import java.sql.*;

public class JdbcUserDao implements UserDao {
    private final PreparedStatement saveStatement, getStatement;


    public JdbcUserDao() {
        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/lab7", "postgres", "111");

            String saveQuery = "INSERT INTO users(login, password) VALUES(?, ?)";
            saveStatement = connection.prepareStatement(saveQuery);

            String getQuery = "SELECT * From users WHERE login=?";
            getStatement = connection.prepareStatement(getQuery);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public synchronized void save(User user) {
        try {
            saveStatement.setString(1, user.getLogin());
            saveStatement.setString(2, user.getPassword());
            saveStatement.execute();
        } catch (SQLException throwables) {
            throw new RuntimeException(throwables);
        }
    }

    @Override
    public void update(User user) {

    }

    @Override
    public void delete(User user) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public void removeById(int id) {

    }

    @Override
    public synchronized User get(String login) {
        ResultSet resultSet;
        try {
            getStatement.setString(1, login);
            resultSet = getStatement.executeQuery();

            if (!resultSet.next()) {
                return null;
            }

            int userId = resultSet.getInt("id");
            String userLogin = resultSet.getString("login");
            String password = resultSet.getString("password");

            User user = new User();
            user.setId(userId);
            user.setLogin(userLogin);
            user.setPassword(password);

            return user;
        } catch (SQLException throwables) {
            throw new RuntimeException(throwables);
        }
    }
}

package ru.lab6.server.database.users;

import ru.lab6.common.user.User;

import java.sql.*;

public class JdbcUserDao implements UserDao {
    private final Connection connection;


    public JdbcUserDao() {
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/lab7", "postgres", "111");
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void save(User user) {
        String query = String.format("INSERT INTO users(login, password) VALUES('%s', '%s')", user.getLogin(), user.getPassword());
        try {
            Statement statement = connection.createStatement();
            statement.execute(query);
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
    public User get(String login) {
        String query = "SELECT * FROM users WHERE login='" + login + "'";
        ResultSet resultSet;
        try {
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery(query);

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

package ru.lab6.server.database.users;

public interface UserDao {
    public void save(User user);
    public void update(User user);
    public void delete(User user);
}

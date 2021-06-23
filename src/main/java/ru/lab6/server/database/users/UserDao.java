package ru.lab6.server.database.users;

import ru.lab6.common.user.User;
import ru.lab6.common.humanbeing.HumanBeing;

import java.util.List;

public interface UserDao {
    void save(User user);
    void update(User user);
    void delete(User user);
    void deleteAll();
    void removeById(int id);
    User get(String login);
}

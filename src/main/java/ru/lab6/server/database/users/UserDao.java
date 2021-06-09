package ru.lab6.server.database.users;

import ru.lab6.common.humanbeing.HumanBeing;

import java.util.List;

public interface UserDao {
    void save(User user);
    void save(HumanBeing humanBeing);
    void update(HumanBeing humanBeing);
    void update(User user);
    void delete(User user);
    void deleteAll();
    void removeById(int id);
    void saveAll(List<HumanBeing> collection);
    User get(String login);
}

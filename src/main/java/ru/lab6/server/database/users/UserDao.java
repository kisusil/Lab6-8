package ru.lab6.server.database.users;

import ru.lab6.common.humanbeing.HumanBeing;

import java.util.List;

public interface UserDao {
    public void save(User user);
    public void save(HumanBeing humanBeing);
    public void update(HumanBeing humanBeing);
    public void update(User user);
    public void delete(User user);
    public void deleteAll();
    public void removeById(int id);
    public void saveAll(List<HumanBeing> collection);
}

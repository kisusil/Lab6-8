package ru.lab6.server.database.humanbeings;

import ru.lab6.common.humanbeing.HumanBeing;

import java.util.List;

public interface HumanBeingDao {
    void save(HumanBeing humanBeing);
    void update(HumanBeing humanBeing);
    void saveAll(List<HumanBeing> collection);
    List<HumanBeing> getAll();
}

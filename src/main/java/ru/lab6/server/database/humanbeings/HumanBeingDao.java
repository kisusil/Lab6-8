package ru.lab6.server.database.humanbeings;

import ru.lab6.common.humanbeing.HumanBeing;

public interface HumanBeingDao {
    public void update(HumanBeing humanBeing);
    public void delete(HumanBeing humanBeing);
    public void save(HumanBeing humanBeing);
}

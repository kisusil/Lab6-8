package ru.lab6.server;

import org.hibernate.Session;
import org.hibernate.Transaction;
import ru.lab6.common.humanbeing.HumanBeing;

import java.util.List;

public interface HumanBeingDao {
    public void update(HumanBeing humanBeing);
    public void delete(HumanBeing humanBeing);
    public void save(HumanBeing humanBeing);
}

package ru.lab6.server.model.collection;

import ru.lab6.common.humanbeing.HumanBeing;

import java.util.List;

public interface Repository {
    void add(HumanBeing humanBeing);
    void delete(int id) throws RepositoryException;
    void deleteAll();
    HumanBeing get(int id);
    List<HumanBeing> getAll();
    void read(List<HumanBeing> collection);
    CollectionInfo getInfo();
}

package ru.lab6.server.model;

import ru.lab6.common.humanbeing.HumanBeing;

import java.time.LocalDateTime;
import java.util.*;

public class HumanBeingRepository implements Repository {
    private final CollectionInfo collectionInfo;
    private final LinkedHashSet<HumanBeing> linkedHashSet;

    public HumanBeingRepository() {
        collectionInfo = new CollectionInfo("LinkedHashSet", LocalDateTime.now(), 0);
        linkedHashSet = new LinkedHashSet<>();
    }

    public HumanBeingRepository(CollectionInfo collectionInfo, LinkedHashSet<HumanBeing> linkedHashSet) {
        this.collectionInfo = collectionInfo;
        this.linkedHashSet = linkedHashSet;
    }

    public void add(HumanBeing humanBeing) {
        linkedHashSet.add(humanBeing);
        collectionInfo.setSize(collectionInfo.getSize() + 1);
    }

    public void delete(int id) throws RepositoryException {
        HumanBeing humanBeing = get(id);

        if (humanBeing != null) {
            linkedHashSet.remove(humanBeing);
            collectionInfo.setSize(collectionInfo.getSize() - 1);
        } else {
            throw new RepositoryException("Человека с таким id не существует");
        }
    }

    public void deleteAll() {
        linkedHashSet.clear();
        collectionInfo.setSize(0);
    }

    public HumanBeing get(int id) {
        return linkedHashSet
                    .stream()
                    .filter(humanBeing -> humanBeing.getId() == id)
                    .findAny()
                    .orElse(null);
    }

    public List<HumanBeing> getAll() {
        List<HumanBeing> humanBeingList = new ArrayList<>(linkedHashSet);
        Collections.sort(humanBeingList);
        return humanBeingList;
    }

    public CollectionInfo getInfo() {
        return collectionInfo;
    }


}
package ru.lab6.server.model.collection;

import java.time.LocalDateTime;

public class CollectionInfo {
    private final String type;
    private LocalDateTime creationDate;
    private int size;

    public CollectionInfo(String type, LocalDateTime creationDate, int size) {
        this.type = type;
        this.creationDate = creationDate;
        this.size = size;
    }

    public String getType() {
        return type;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "Информация о коллекции:\n" +
               "\tтип коллекции: " + type + "\n" +
               "\tдата создания коллекции: " + creationDate + "\n" +
               "\tразмер коллекции: " + size;
    }
}
package ru.lab6.server;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.lab6.server.model.Repository;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;

public class CollectionSaver {
    private final String fileName;


    public CollectionSaver(String fileName) {
        this.fileName = fileName;
    }


    public void save(Repository repository) throws CollectionSaverException {
        createNewFileIfNotExist();

        try(OutputStreamWriter outputStreamWriter = new OutputStreamWriter(new FileOutputStream(fileName), StandardCharsets.UTF_8)) {
            Gson gson =
                    new GsonBuilder()
                    .setPrettyPrinting()
                    .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeJsonSerializer())
                    .create();

            String jsonString = gson.toJson(repository);
            outputStreamWriter.write(jsonString);
            outputStreamWriter.flush();
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Что-то пошло не так...");
        } catch (IOException e) {
            throw new CollectionSaverException("Ошибка сохранения в файл");
        }
    }

    private void createNewFileIfNotExist() throws CollectionSaverException {
        File file = new File(fileName);

        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new CollectionSaverException("Не удается создать файл");
            }
        }
    }
}

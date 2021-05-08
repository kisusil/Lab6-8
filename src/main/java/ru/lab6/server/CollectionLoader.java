package ru.lab6.server;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

import ru.lab6.server.io.IO;
import ru.lab6.server.model.HumanBeingRepository;
import ru.lab6.server.model.Repository;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;

public class CollectionLoader {
    private final String fileName;
    private final IO io;


    public CollectionLoader(String fileName, IO io) {
        this.fileName = fileName;
        this.io = io;
    }


    public Repository load() throws CollectionLoaderException {
        try(InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(fileName), StandardCharsets.UTF_8)) {
            Gson gson =
                    new GsonBuilder()
                    .registerTypeAdapter(HumanBeingRepository.class, new HumanBeingRepositoryJsonDeserializer())
                    .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeJsonDeserializer())
                    .create();

            return gson.fromJson(inputStreamReader, HumanBeingRepository.class);
        } catch (FileNotFoundException e) {
            io.println("Файла с таким именем не существует. Создаем новую коллекцию");
            return new HumanBeingRepository();
        } catch (JsonIOException e) {
            throw new CollectionLoaderException("Файл существует, но к нему нет доступа");
        } catch (JsonSyntaxException e) {
            throw new CollectionLoaderException("Ошибки JSON-синтаксиса");
        } catch (IOException e) {
            throw new CollectionLoaderException("Ошибка загрузки из файла");
        }
    }
}

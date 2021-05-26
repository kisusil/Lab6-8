package ru.lab6.client.controller;

import com.google.gson.Gson;
import ru.lab6.client.Client;
import ru.lab6.common.humanbeing.Car;
import ru.lab6.common.humanbeing.Coordinates;
import ru.lab6.common.humanbeing.Mood;
import ru.lab6.common.humanbeing.WeaponType;
import ru.lab6.common.parameters.*;
import ru.lab6.common.request.Request;
import ru.lab6.common.response.Response;

import java.net.Socket;

public class MyController implements Controller {
    private final Client client;
    private final Gson gson;

    public MyController(Client client) {
        this.client = client;
        this.gson = new Gson();
    }


    @Override
    public String add (String name, Coordinates coordinates, Boolean realHero, boolean hasToothpick, float impactSpeed, Long minutesOfWaiting, WeaponType weaponType, Mood mood, Car car) {
        CreationParameters parameters = new CreationParameters ();

        parameters.name = name;
        parameters.car = car;
        parameters.coordinates = coordinates;
        parameters.realHero = realHero;
        parameters.hasToothpick = hasToothpick;
        parameters.impactSpeed = impactSpeed;
        parameters.minutesOfWaiting = minutesOfWaiting;
        parameters.weaponType = weaponType;
        parameters.mood = mood;

        Socket socket = client.sendRequest(new Request("add", parameters));

        if (socket == null) {
            return "Не удалось отправить запрос";
        }

        Response response = client.receiveResponse(socket);

        return response.json();
    }

    @Override
    public String clear() {
        EmptyParameters parameters = new EmptyParameters();

        Socket socket = client.sendRequest(new Request("clear", parameters));

        if (socket == null) {
            return "Не удалось отправить запрос";
        }

        Response response = client.receiveResponse(socket);

        return response.json();
    }

    @Override
    public String executeScript(String fileName) {
        ExecuteScriptParameters parameters = new ExecuteScriptParameters();

        parameters.fileName = fileName;

        Socket socket = client.sendRequest(new Request("execute_script", parameters));

        if (socket == null) {
            return "Не удалось отправить запрос";
        }

        Response response = client.receiveResponse(socket);

        return response.json();
    }

    @Override
    public String info() {
        EmptyParameters parameters = new EmptyParameters();

        Socket socket = client.sendRequest(new Request("info", parameters));

        if (socket == null) {
            return "Не удалось отправить запрос";
        }

        Response response = client.receiveResponse(socket);

        return response.json();
    }

    @Override
    public String  removeById(int id) {
        IdParameters parameters = new IdParameters();

        parameters.id = id;

        Socket socket = client.sendRequest(new Request("remove_by_id", parameters));

        if (socket == null) {
            return "Не удалось отправить запрос";
        }


        Response response = client.receiveResponse(socket);

        return response.json();
    }

    @Override
    public String removeLower(String name, Coordinates coordinates, Boolean realHero, boolean hasToothpick, float impactSpeed, Long minutesOfWaiting, WeaponType weaponType, Mood mood, Car car) {
        CreationParameters parameters = new CreationParameters();

        parameters.name = name;
        parameters.car = car;
        parameters.coordinates = coordinates;
        parameters.realHero = realHero;
        parameters.hasToothpick = hasToothpick;
        parameters.impactSpeed = impactSpeed;
        parameters.minutesOfWaiting = minutesOfWaiting;
        parameters.weaponType = weaponType;
        parameters.mood = mood;

        Socket socket = client.sendRequest(new Request("remove_lower", parameters));

        if (socket == null) {
            return "Не удалось отправить запрос";
        }

        Response response = client.receiveResponse(socket);

        return response.json();
    }

    @Override
    public String save() {
        EmptyParameters parameters = new EmptyParameters();

        Socket socket = client.sendRequest(new Request("save", parameters));

        if (socket == null) {
            return "Не удалось отправить запрос";
        }

        Response response = client.receiveResponse(socket);

        return response.json();
    }

    @Override
    public String show() {
        EmptyParameters parameters = new EmptyParameters();

        Socket socket = client.sendRequest(new Request("show", parameters));

        if (socket == null) {
            return "Не удалось отправить запрос";
        }

        Response response = client.receiveResponse(socket);

        return response.json();
    }

    @Override
    public String update(int id, String name, Integer coordinateX, double coordinateY, Boolean realHero, boolean hasToothpick, float impactSpeed, Long minutesOfWaiting, WeaponType weaponType, Mood mood, String carName) {
        UpdateParameters parameters = new UpdateParameters();

        parameters.id = id;
        parameters.name = name;
        parameters.carName = carName;
        parameters.coordinateX = coordinateX;
        parameters.coordinateY = coordinateY;
        parameters.realHero = realHero;
        parameters.hasToothpick = hasToothpick;
        parameters.impactSpeed = impactSpeed;
        parameters.minutesOfWaiting = minutesOfWaiting;
        parameters.weaponType = weaponType;
        parameters.mood = mood;

        Socket socket = client.sendRequest(new Request("update", parameters));

        if (socket == null) {
            return "Не удалось отправить запрос";
        }

        Response response = client.receiveResponse(socket);

        return response.json();
    }

    @Override
    public String addIfMax(String name, Coordinates coordinates, Boolean realHero, boolean hasToothpick, float impactSpeed, Long minutesOfWaiting, WeaponType weaponType, Mood mood, Car car) {
        CreationParameters parameters = new CreationParameters();

        parameters.name = name;
        parameters.car = car;
        parameters.coordinates = coordinates;
        parameters.realHero = realHero;
        parameters.hasToothpick = hasToothpick;
        parameters.impactSpeed = impactSpeed;
        parameters.minutesOfWaiting = minutesOfWaiting;
        parameters.weaponType = weaponType;
        parameters.mood = mood;

        Socket socket = client.sendRequest(new Request("add_if_max", parameters));

        if (socket == null) {
            return "Не удалось отправить запрос";
        }

        Response response = client.receiveResponse(socket);

        return response.json();
    }

    @Override
    public String countByMood(Mood mood) {
        MoodParameters parameters = new MoodParameters();
        parameters.mood = mood;

        Socket socket = client.sendRequest(new Request("count_by_mood", parameters));

        if (socket == null) {
            return "Не удалось отправить запрос";
        }

        Response response = client.receiveResponse(socket);

        return response.json();
    }

    @Override
    public String filterGreaterThanMood(Mood mood) {
        MoodParameters parameters = new MoodParameters();
        parameters.mood = mood;

        Socket socket = client.sendRequest(new Request("filter_greater_than_mood", parameters));

        if (socket == null) {
            return "Не удалось отправить запрос";
        }

        Response response = client.receiveResponse(socket);

        return response.json();
    }

    @Override
    public String printAscending() {
        EmptyParameters parameters = new EmptyParameters();

        Socket socket = client.sendRequest(new Request("print_ascending", parameters));

        if (socket == null) {
            return "Не удалось отправить запрос";
        }

        Response response = client.receiveResponse(socket);

        return response.json();
    }

    @Override
    public String help() {
        EmptyParameters parameters = new EmptyParameters();

        Socket socket = client.sendRequest(new Request("help", parameters));

        if (socket == null) {
            return "Не удалось отправить запрос";
        }

        Response response = client.receiveResponse(socket);

        return response.json();
    }
}

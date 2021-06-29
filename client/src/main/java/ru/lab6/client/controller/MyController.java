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
    private String login;
    private String password;

    public MyController(Client client) {
        this.client = client;
        this.gson = new Gson();
    }

    @Override
    public Response register(String login, String password) {

        LoginParameters parameters = new LoginParameters ();

        parameters.login = login;
        parameters.password = password;

        Socket socket = client.sendRequest(new Request("register", parameters, login, password));

        if (socket == null) {
            Response response = new Response();
            response.setErrorResponse("error", "Server is unavailable");
            return response;
        }

        Response response = client.receiveResponse(socket);

        if (response.getStatus().equals("ok")) {
            this.login = login;
            this.password = password;
        }

        return response;
    }

    @Override
    public Response login(String login, String password) {

        LoginParameters parameters = new LoginParameters ();

        parameters.login = login;
        parameters.password = password;

        Socket socket = client.sendRequest(new Request("login", parameters, login, password));

        if (socket == null) {
            Response response = new Response();
            response.setErrorResponse("error", "Server is unavailable");
            return response;
        }

        Response response = client.receiveResponse(socket);

        if (response.getStatus().equals("ok")) {
            this.login = login;
            this.password = password;
        }

        return response;
    }

    @Override
    public Response add (String name, Coordinates coordinates, Boolean realHero, boolean hasToothpick, float impactSpeed, Long minutesOfWaiting, WeaponType weaponType, Mood mood, Car car) {
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
        parameters.login = login;
        parameters.password = password;

        Socket socket = client.sendRequest(new Request("add", parameters, login, password));

        if (socket == null) {
            Response response = new Response();
            response.setErrorResponse("error", "Server is unavailable");
            return response;
        }

        Response response = client.receiveResponse(socket);

        return response;
    }

    @Override
    public Response clear() {
        EmptyParameters parameters = new EmptyParameters();

        parameters.login = login;
        parameters.password = password;

        Socket socket = client.sendRequest(new Request("clear", parameters, login, password));

        if (socket == null) {
            Response response = new Response();
            response.setErrorResponse("error", "Server is unavailable");
            return response;
        }

        Response response = client.receiveResponse(socket);

        return response;
    }

    @Override
    public Response executeScript(String fileName) {
        ExecuteScriptParameters parameters = new ExecuteScriptParameters();

        parameters.fileName = fileName;
        parameters.login = login;
        parameters.password = password;

        Socket socket = client.sendRequest(new Request("execute_script", parameters, login, password));

        if (socket == null) {
            Response response = new Response();
            response.setErrorResponse("error", "Server is unavailable");
            return response;
        }

        Response response = client.receiveResponse(socket);

        return response;
    }

    @Override
    public Response info() {
        EmptyParameters parameters = new EmptyParameters();

        parameters.login = login;
        parameters.password = password;

        Socket socket = client.sendRequest(new Request("info", parameters, login, password));

        if (socket == null) {
            Response response = new Response();
            response.setErrorResponse("error", "Server is unavailable");
            return response;
        }

        Response response = client.receiveResponse(socket);

        return response;
    }

    @Override
    public Response  removeById(int id) {
        IdParameters parameters = new IdParameters();

        parameters.id = id;
        parameters.login = login;
        parameters.password = password;

        Socket socket = client.sendRequest(new Request("remove_by_id", parameters, login, password));

        if (socket == null) {
            Response response = new Response();
            response.setErrorResponse("error", "Server is unavailable");
            return response;
        }


        Response response = client.receiveResponse(socket);

        return response;
    }

    @Override
    public Response removeLower(String name, Coordinates coordinates, Boolean realHero, boolean hasToothpick, float impactSpeed, Long minutesOfWaiting, WeaponType weaponType, Mood mood, Car car) {
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
        parameters.login = login;
        parameters.password = password;

        Socket socket = client.sendRequest(new Request("remove_lower", parameters, login, password));

        if (socket == null) {
            Response response = new Response();
            response.setErrorResponse("error", "Server is unavailable");
            return response;
        }

        Response response = client.receiveResponse(socket);

        return response;
    }

    @Override
    public Response save() {
        EmptyParameters parameters = new EmptyParameters();

        parameters.login = login;
        parameters.password = password;

        Socket socket = client.sendRequest(new Request("save", parameters, login, password));

        if (socket == null) {
            Response response = new Response();
            response.setErrorResponse("error", "Server is unavailable");
            return response;
        }

        Response response = client.receiveResponse(socket);

        return response;
    }

    @Override
    public Response show() {
        EmptyParameters parameters = new EmptyParameters();

        parameters.login = login;
        parameters.password = password;

        Socket socket = client.sendRequest(new Request("show", parameters, login, password));

        if (socket == null) {
            Response response = new Response();
            response.setErrorResponse("error", "Server is unavailable");
            return response;
        }

        Response response = client.receiveResponse(socket);

        return response;
    }

    @Override
    public Response update(int id, String name, Integer coordinateX, double coordinateY, Boolean realHero, boolean hasToothpick, float impactSpeed, Long minutesOfWaiting, WeaponType weaponType, Mood mood, String carName) {
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
        parameters.login = login;
        parameters.password = password;

        Socket socket = client.sendRequest(new Request("update", parameters, login, password));

        if (socket == null) {
            Response response = new Response();
            response.setErrorResponse("error", "Server is unavailable");
            return response;
        }

        Response response = client.receiveResponse(socket);

        return response;
    }

    @Override
    public Response addIfMax(String name, Coordinates coordinates, Boolean realHero, boolean hasToothpick, float impactSpeed, Long minutesOfWaiting, WeaponType weaponType, Mood mood, Car car) {
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
        parameters.login = login;
        parameters.password = password;

        Socket socket = client.sendRequest(new Request("add_if_max", parameters, login, password));

        if (socket == null) {
            Response response = new Response();
            response.setErrorResponse("error", "Server is unavailable");
            return response;
        }

        Response response = client.receiveResponse(socket);

        return response;
    }

    @Override
    public Response countByMood(Mood mood) {
        MoodParameters parameters = new MoodParameters();
        parameters.mood = mood;
        parameters.login = login;
        parameters.password = password;

        Socket socket = client.sendRequest(new Request("count_by_mood", parameters, login, password));

        if (socket == null) {
            Response response = new Response();
            response.setErrorResponse("error", "Server is unavailable");
            return response;
        }

        Response response = client.receiveResponse(socket);

        return response;
    }

    @Override
    public Response filterGreaterThanMood(Mood mood) {
        MoodParameters parameters = new MoodParameters();
        parameters.mood = mood;
        parameters.login = login;
        parameters.password = password;

        Socket socket = client.sendRequest(new Request("filter_greater_than_mood", parameters, login, password));

        if (socket == null) {
            Response response = new Response();
            response.setErrorResponse("error", "Server is unavailable");
            return response;
        }

        Response response = client.receiveResponse(socket);

        return response;
    }

    @Override
    public Response printAscending() {
        EmptyParameters parameters = new EmptyParameters();

        parameters.login = login;
        parameters.password = password;

        Socket socket = client.sendRequest(new Request("print_ascending", parameters, login, password));

        if (socket == null) {
            Response response = new Response();
            response.setErrorResponse("error", "Server is unavailable");
            return response;
        }

        Response response = client.receiveResponse(socket);

        return response;
    }

    @Override
    public Response help() {
        EmptyParameters parameters = new EmptyParameters();

        parameters.login = login;
        parameters.password = password;

        Socket socket = client.sendRequest(new Request("help", parameters, login, password));

        if (socket == null) {
            Response response = new Response();
            response.setErrorResponse("error", "Server is unavailable");
            return response;
        }

        Response response = client.receiveResponse(socket);

        return response;
    }
}

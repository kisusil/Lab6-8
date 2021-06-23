package ru.lab6.client;

import ru.lab6.client.controller.Controller;
import ru.lab6.client.controller.MyController;
import ru.lab6.client.view.Console;
import ru.lab6.client.view.IO;
import ru.lab6.client.view.MyApplication;

public class Main {
    public static void main(String[] args){
        IO io = new Console(System.in, System.out);
        Client client = new Client(Integer.parseInt(args[0]), io);
        Controller controller = new MyController(client);
        MyApplication app = new MyApplication(io, controller);
        app.start();
    }
}

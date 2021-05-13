package ru.lab6.client;

import ru.lab6.client.view.Console;
import ru.lab6.client.view.IO;
import ru.lab6.client.view.MyApplication;

public class Main {
    public static void main(String[] args){
        IO io = new Console(System.in, System.out);
        MyApplication app = new MyApplication(io);
        app.start();
    }
}

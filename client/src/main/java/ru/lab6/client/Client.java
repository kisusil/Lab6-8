package ru.lab6.client;

import ru.lab6.common.request.Request;
import ru.lab6.common.response.Response;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ConnectException;
import java.net.InetSocketAddress;
import java.net.Socket;

public class Client {
    private final int port;
    private final String ip;

    public Client(String ip, int port){
        this.port = port;
        this.ip = ip;
    }

    public Socket sendRequest(Request request) {
        try {
            Socket socket = new Socket();
            socket.connect(new InetSocketAddress(ip, port));
            ObjectOutputStream outStream = new ObjectOutputStream(socket.getOutputStream());
            outStream.writeObject(request);
            return socket;
        } catch (ConnectException e) {
            return null;
        } catch (IOException e){
            //не забыть сделать правильно
            throw new RuntimeException(e);
        }
    }

    public Response receiveResponse(Socket socket) {
        try {
            ObjectInputStream inStream = new ObjectInputStream(socket.getInputStream());
            Response response = (Response) inStream.readObject();
            socket.close();
            return response;
        } catch (IOException | ClassNotFoundException e) {
            //не забыть сделать правильно
            throw new RuntimeException(e);
        }
    }
}

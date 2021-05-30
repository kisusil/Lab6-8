package ru.lab6.client;

import com.google.gson.Gson;
import ru.lab6.client.view.IO;
import ru.lab6.common.request.Request;
import ru.lab6.common.response.Response;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ConnectException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;

public class Client {
    private final IO io;
    private final int port;

    public Client(int port, IO io){
        this.io = io;
        this.port = port;
    }

    public Socket sendRequest(Request request){
        try {
            Socket socket = new Socket();
            socket.connect(new InetSocketAddress("127.0.0.1", port));
            ObjectOutputStream outStream = new ObjectOutputStream(socket.getOutputStream());
            outStream.writeObject(request);
            return socket;
        } catch (ConnectException e) {
            io.println("Сервер временно недоступен");
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

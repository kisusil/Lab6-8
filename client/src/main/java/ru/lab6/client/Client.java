package ru.lab6.client;

import com.google.gson.Gson;
import ru.lab6.client.view.IO;
import ru.lab6.common.request.Request;
import ru.lab6.common.response.Response;

import java.io.IOException;
import java.net.ConnectException;
import java.net.InetSocketAddress;
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

    public SocketChannel sendRequest(Request request){
        try {
            SocketChannel socketChannel = SocketChannel.open();
            socketChannel.connect(new InetSocketAddress("127.0.0.1", port));
            socketChannel.configureBlocking(false);
            byte[] bytes = request.json().getBytes(StandardCharsets.UTF_8);
            ByteBuffer byteBuffer = ByteBuffer.wrap(bytes);
            socketChannel.write(byteBuffer);
            return socketChannel;
        } catch (ConnectException e) {
            io.println("Сервер временно недоступен");
            return null;
        } catch (IOException e){
            //не забыть сделать правильно
            throw new RuntimeException(e);
        }
    }

    public Response receiveResponse(SocketChannel socketChannel) {
        try {
            ByteBuffer buf = ByteBuffer.allocate(4096);

            int bytesAmount = socketChannel.read(buf);

            while (bytesAmount <= 0) {
                bytesAmount = socketChannel.read(buf);

                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            buf.flip();

            socketChannel.close();

            byte[] bytes = new byte[bytesAmount];
            buf.get(bytes);

            String jsonString = new String(bytes, StandardCharsets.UTF_8);

            return new Gson().fromJson(jsonString, Response.class);
        } catch (IOException e) {
            //не забыть сделать правильно
            throw new RuntimeException(e);
        }
    }
}

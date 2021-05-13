package ru.lab6.client;

import com.google.gson.Gson;
import ru.lab6.common.request.Request;
import ru.lab6.common.response.Response;
import ru.lab6.common.response.ResponseImpl;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class Client {
    private int port;

    Client(int port){
        this.port = port;
    }
    public SocketChannel sendRequest(Request request){
        try {
            SocketChannel socketChannel = SocketChannel.open();
            socketChannel.configureBlocking(false);
            socketChannel.connect(new InetSocketAddress("localhost", port));
            byte[] bytes = request.json().getBytes(StandardCharsets.UTF_8);
            ByteBuffer byteBuffer = ByteBuffer.wrap(bytes);
            socketChannel.write(byteBuffer);
            return socketChannel;
        } catch (IOException e){
            //не забыть сделать правильно
            throw new RuntimeException(e);
        }
    }

    public Response receiveResponse(SocketChannel socketChannel) {
        try {
            ByteBuffer buf = ByteBuffer.allocate(1024);

            int bytesAmount = socketChannel.read(buf);
            buf.flip();

            socketChannel.close();

            byte[] bytes = new byte[bytesAmount];
            buf.get(bytes);

            String jsonString = new String(bytes, StandardCharsets.UTF_8);

            return new Gson().fromJson(jsonString, ResponseImpl.class);
        } catch (IOException e) {
            //не забыть сделать правильно
            throw new RuntimeException(e);
        }
    }
}

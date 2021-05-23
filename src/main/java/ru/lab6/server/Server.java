package ru.lab6.server;

import com.google.gson.Gson;
import ru.lab6.common.request.Request;
import ru.lab6.common.response.Response;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;


public class Server {
    private final ServerSocketChannel serverSocketChannel;
    private SocketChannel socketChannel;
    private final int port;

    public Server(int port){
        this.port = port;
        serverSocketChannel = createServerSocketChannel();
    }

    public Request receiveRequest() {
        try {
            ByteBuffer buf = ByteBuffer.allocate(1024);

            int bytesAmount = socketChannel.read(buf);
            buf.flip();

            byte[] bytes = new byte[bytesAmount];
            buf.get(bytes);

            String jsonString = new String(bytes, StandardCharsets.UTF_8);

            return new Gson().fromJson(jsonString, Request.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void sendResponse (Response response) {
        try {
            byte[] bytes = response.json().getBytes(StandardCharsets.UTF_8);
            ByteBuffer byteBuffer = ByteBuffer.wrap(bytes);
            socketChannel.write(byteBuffer);
        } catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    public ServerSocketChannel createServerSocketChannel() {
        try {
            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.socket().bind(new InetSocketAddress(port));
            return serverSocketChannel;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void acceptNewClient() {
        try {
            socketChannel = serverSocketChannel.accept();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void closeConnection() {
        try {
            socketChannel.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
package ru.lab6.server.connection;

import com.google.gson.Gson;
import ru.lab6.common.request.Request;
import ru.lab6.common.response.Response;
import ru.lab6.common.response.ResponseImpl;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;


public class Server {
    private ServerSocketChannel serverSocketChannel;
    private SocketChannel socketChannel;
    private int port;

    public Server(int port){
        try {
            this.port = port;
            serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.socket().bind(new InetSocketAddress(port));
            socketChannel = serverSocketChannel.accept();
            receiveRequest(socketChannel);
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public Request receiveRequest(SocketChannel socketChannel) {
        try {
            ByteBuffer buf = ByteBuffer.allocate(1024);

            int bytesAmount = socketChannel.read(buf);
            buf.flip();

            socketChannel.close();

            byte[] bytes = new byte[bytesAmount];
            buf.get(bytes);

            String jsonString = new String(bytes, StandardCharsets.UTF_8);

            return new Gson().fromJson(jsonString, Request.class);
        } catch (IOException e) {
            sendResponse(socketChannel,
                    new ResponseImpl.Builder().setErrorResponse("ioexception", "").create());
            throw new RuntimeException(e);
        }
    }

    public void sendResponse (SocketChannel socketChannel, Response response) {
        try {
            byte[] bytes = response.json().getBytes(StandardCharsets.UTF_8);
            ByteBuffer byteBuffer = ByteBuffer.wrap(bytes);
            socketChannel.write(byteBuffer);
        } catch (IOException e){
            sendResponse(socketChannel,
                    new ResponseImpl.Builder().setErrorResponse("ioexception", "").create());
            throw new RuntimeException(e);
        }
    }

    public ServerSocketChannel createServerSocketChannel() {
        try {
            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.socket().bind(new InetSocketAddress(port));
            return serverSocketChannel;
        } catch (IOException e) {
            sendResponse(socketChannel,
                    new ResponseImpl.Builder().setErrorResponse("ioexception", "").create());
            throw new RuntimeException(e);
        }
    }
}
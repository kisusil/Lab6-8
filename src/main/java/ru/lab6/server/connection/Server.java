package ru.lab6.server.connection;

import com.google.gson.JsonObject;
import ru.lab6.common.request.Request;
import ru.lab6.server.response.Response;
import ru.lab6.server.response.ResponseImpl;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;


public class Server {
    private ServerSocketChannel serverSocketChannel;
    private SocketChannel socketChannel;
    private int port;

    public Server(int port){
        try {
            serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.socket().bind(new InetSocketAddress(port));
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public ByteBuffer receiveRequest() throws IOException, ClassNotFoundException {
        socketChannel = createSocketChannel();
        ByteBuffer buf = ByteBuffer.allocate(1024);
        socketChannel.read(buf);
        buf.flip();
        socketChannel.close();
        return buf;
    }

    private SocketChannel createSocketChannel() throws IOException {
        socketChannel = serverSocketChannel.accept();
        return socketChannel;
    }

    public void sendSuccessfulResponse(String result){
        String response = new ResponseImpl.Builder().setSuccessfulResponse(result).create().json();
        try {
            byte[] bytes = response.getBytes();
            ByteBuffer byteBuffer = ByteBuffer.wrap(bytes);
            socketChannel.write(byteBuffer);
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public void sendErrorResponse(String errorName, String description){
        String response = new ResponseImpl.Builder().setErrorResponse(errorName, description).create().json();
        try {
            byte[] bytes = response.getBytes();
            ByteBuffer byteBuffer = ByteBuffer.wrap(bytes);
            socketChannel.write(byteBuffer);
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
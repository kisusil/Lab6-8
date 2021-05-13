package ru.lab6.server.connection;

import com.google.gson.JsonObject;
import ru.lab6.common.request.Request;
import ru.lab6.server.response.Response;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;


public class Server {
    private SocketChannel socketChannel;

    public ByteBuffer receiveRequest() throws IOException, ClassNotFoundException {
        socketChannel = createSocketChannel();
        ByteBuffer buf = ByteBuffer.allocate(1024);
        socketChannel.read(buf);
        buf.flip();
        socketChannel.close();
        return buf;
    }

    public void sendResponse (String answer) {
        String request = new Response(answer).json();
        try {
            SocketChannel socketChannel = SocketChannel.open();
            socketChannel.configureBlocking(false);
            socketChannel.connect(new InetSocketAddress("localhost", 8000));
            byte[] bytes = request.getBytes();
            ByteBuffer byteBuffer = ByteBuffer.wrap(bytes);
            socketChannel.write(byteBuffer);
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    private SocketChannel createSocketChannel() throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.socket().bind(new InetSocketAddress(8000));
        socketChannel = serverSocketChannel.accept();
        return socketChannel;
    }
}
package ru.lab6.client;

import ru.lab6.common.request.Request;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class Client {
    public void sendRequest(String command, String params){
        String request = new Request(command, params).json();
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

    public ByteBuffer receiveResponse() throws IOException, ClassNotFoundException {
        socketChannel = createSocketChannel();
        ByteBuffer buf = ByteBuffer.allocate(1024);
        socketChannel.read(buf);
        buf.flip();
        socketChannel.close();
        return buf;
    }
}

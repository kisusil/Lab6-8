package ru.lab6.server;

import com.google.gson.Gson;
import org.apache.logging.log4j.LogManager;
import ru.lab6.common.request.Request;
import ru.lab6.common.response.Response;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;

import org.apache.logging.log4j.*;


public class Server {
    private final ServerSocketChannel serverSocketChannel;
    private SocketChannel socketChannel;
    private final int port;
    private static Logger logger = LogManager.getLogger(Server.class);

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
            logger.info("Request received.");
            return new Gson().fromJson(jsonString, Request.class);
        } catch (IOException e) {
            logger.error("Error while receiving request.");
            throw new RuntimeException(e);
        }
    }

    public void sendResponse (Response response) {
        try {
            byte[] bytes = response.json().getBytes(StandardCharsets.UTF_8);
            ByteBuffer byteBuffer = ByteBuffer.wrap(bytes);
            socketChannel.write(byteBuffer);
            logger.info("Response sent.");
        } catch (IOException e){
            logger.error("Error while response sending.");
            throw new RuntimeException(e);
        }
    }

    public ServerSocketChannel createServerSocketChannel() {
        try {
            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.socket().bind(new InetSocketAddress(port));
            logger.info("Server socket channel created.");
            return serverSocketChannel;
        } catch (IOException e) {
            logger.fatal("Error while server socket channel created.");
            throw new RuntimeException(e);
        }
    }

    public void acceptNewClient() {
        try {
            socketChannel = serverSocketChannel.accept();
            logger.info("Client connected.");
        } catch (IOException e) {
            logger.error("Error while connecting client.");
            throw new RuntimeException(e);
        }
    }

    public void closeConnection() {
        try {
            socketChannel.close();
            logger.info("Connection stopped.");
        } catch (IOException e) {
            logger.error("Error while stopping connection.");
            throw new RuntimeException(e);
        }
    }
}
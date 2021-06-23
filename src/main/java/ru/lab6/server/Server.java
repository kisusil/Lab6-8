package ru.lab6.server;

import com.google.gson.Gson;
import org.apache.logging.log4j.LogManager;
import ru.lab6.common.request.Request;
import ru.lab6.common.response.Response;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ForkJoinPool;

import org.apache.logging.log4j.*;


public class Server {
    private final ServerSocket serverSocket;
    private Socket socket;
    private final int port;
    private static Logger logger = LogManager.getLogger(Server.class);

    public Server(int port){
        this.port = port;
        serverSocket = createServerSocket();
    }

    public Request receiveRequest() {
        try {
            ObjectInputStream inStream = new ObjectInputStream(socket.getInputStream());
            Request request = (Request) inStream.readObject();
            logger.info("Request received.");
            return request;
        } catch (IOException | ClassNotFoundException e) {
            logger.error("Error while receiving request.");
            throw new RuntimeException(e);
        }
    }

    public void sendResponse (Response response) {
        try {
            ObjectOutputStream outStream = new ObjectOutputStream(socket.getOutputStream());
            outStream.writeObject(response);
            logger.info("Response sent.");
        } catch (IOException e){
            logger.error("Error while response sending.");
            throw new RuntimeException(e);
        }
    }

    public ServerSocket createServerSocket() {
        try {
            ServerSocket serverSocket = new ServerSocket();
            serverSocket.bind(new InetSocketAddress(port));
            logger.info("Server socket channel created.");
            return serverSocket;
        } catch (IOException e) {
            logger.fatal("Error while server socket channel created.");
            throw new RuntimeException(e);
        }
    }

    public void acceptNewClient() {
        try {
            socket = serverSocket.accept();
            logger.info("Client connected.");
        } catch (IOException e) {
            logger.error("Error while connecting client.");
            throw new RuntimeException(e);
        }
    }

    public void closeConnection() {
        try {
            socket.close();
            logger.info("Connection stopped.");
        } catch (IOException e) {
            logger.error("Error while stopping connection.");
            throw new RuntimeException(e);
        }
    }
}
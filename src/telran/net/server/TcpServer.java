package telran.net.server;

import telran.net.common.ProtocolJava;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TcpServer implements Runnable {
    //сокет для получения данных от клиента
    ServerSocket serverSocket;
    int port;

    //протокол интерфейса
    ProtocolJava protocol;

    //открываю сервер
    public TcpServer(int port, ProtocolJava protocol) {
        this.port = port;
        this.protocol = protocol;

        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            throw new RuntimeException("Port in use " + port);
        }
    }

    //запускаю сервер
    @Override
    public void run() {
        System.out.println("Server is listening on port " + port);

        try {
            while (true) {
                Socket socket = serverSocket.accept();
                ClientSessionHandler client = new ClientSessionHandler(socket, protocol);
                client.run();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

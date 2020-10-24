package telran.net.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerJava implements Runnable {
    //сокет для получения данных от клиента
    ServerSocket serverSocket;
    int port;

    //протокол интерфейса
    ProtocolJava protocol;

    //открываю сервер
    public ServerJava(int port, ProtocolJava protocol) {
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
                ServerClientJava client = new ServerClientJava(socket, protocol);
                client.run();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

package server;

import telran.net.server.ServerJava;

public class ServerGameAppl {
    private static final int PORT = 4000;

    public static void main(String[] args) {
        ServerJava server = new ServerJava(PORT, new ProtocolGame());

        server.run();
    }

}

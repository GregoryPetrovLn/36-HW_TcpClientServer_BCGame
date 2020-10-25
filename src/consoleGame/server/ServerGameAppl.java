package consoleGame.server;

import consoleGame.common.TcpCommon;
import telran.net.server.TcpServer;

public class ServerGameAppl {
    public static void main(String[] args) {
        TcpServer server = new TcpServer(TcpCommon.SERVER_PORT, new ProtocolGame());

        server.run();
    }

}

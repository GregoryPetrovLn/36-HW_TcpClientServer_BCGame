package consoleGame.client;

import telran.net.common.TcpClient;

public class TcpClientGame extends TcpClient {

    protected TcpClientGame(String hostname, int port) {
        super(hostname, port);
    }

    public String send(String type, String line){
        return sendRequest(type, line).toString();
    }
}

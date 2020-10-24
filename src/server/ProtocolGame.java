package server;

import game.BullsCowsGame;
import telran.net.RequestJava;
import telran.net.ResponseJava;
import telran.net.TcpResponseCode;
import telran.net.server.ProtocolJava;

import java.io.Serializable;


public class ProtocolGame implements ProtocolJava {
    BullsCowsGame game = new BullsCowsGame();

    @Override
    public ResponseJava getResponse(RequestJava request) {
        try {
            switch (request.requestType) {
                case "start":
                    return new ResponseJava(TcpResponseCode.OK, game.startGame());

                case "prompt":
                    return new ResponseJava(TcpResponseCode.OK, game.prompt());

                case "move":
                    return new ResponseJava(TcpResponseCode.OK, game.move((String) request.requestData));

                case "isFinished":
                    String isFinished = game.isFinished()? "finish": "game";
                    return new ResponseJava(TcpResponseCode.OK, isFinished);

                case "getResults":
                    return new ResponseJava(TcpResponseCode.OK, game.getResults());


                default:
                     throw new Exception("unknown request " + request.requestType);
            }
        }catch (Exception e){
            return new ResponseJava(TcpResponseCode.WRONG_REQUEST, e.getMessage());
        }
    }


}

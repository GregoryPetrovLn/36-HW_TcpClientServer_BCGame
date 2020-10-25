package consoleGame.server;

import consoleGame.game.BullsCowsGame;
import telran.net.common.ProtocolRequest;
import telran.net.common.ProtocolResponse;
import telran.net.common.ResponseCode;
import telran.net.common.ProtocolJava;


public class ProtocolGame implements ProtocolJava {
    BullsCowsGame game = new BullsCowsGame();

    @Override
    public ProtocolResponse getResponse(ProtocolRequest request) {
        try {
            switch (request.requestType) {
                case "start":
                    return new ProtocolResponse(ResponseCode.OK, game.startGame());

                case "prompt":
                    return new ProtocolResponse(ResponseCode.OK, game.prompt());

                case "move":
                    return new ProtocolResponse(ResponseCode.OK, game.move((String) request.requestData));

                case "isFinished":
                    String isFinished = game.isFinished()? "finish": "game";
                    return new ProtocolResponse(ResponseCode.OK, isFinished);

                case "getResults":
                    return new ProtocolResponse(ResponseCode.OK, game.getResults());


                default:
                     throw new Exception("unknown request " + request.requestType);
            }
        }catch (Exception e){
            return new ProtocolResponse(ResponseCode.WRONG_REQUEST, e.getMessage());
        }
    }


}

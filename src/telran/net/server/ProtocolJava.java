package telran.net.server;

import telran.net.RequestJava;
import telran.net.ResponseJava;

public interface ProtocolJava {
    //интерфейс для ответов сервера
    ResponseJava getResponse(RequestJava request);
}

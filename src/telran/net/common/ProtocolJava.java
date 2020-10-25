package telran.net.common;

public interface ProtocolJava {
    //интерфейс для ответов сервера
    ProtocolResponse getResponse(ProtocolRequest request);
}

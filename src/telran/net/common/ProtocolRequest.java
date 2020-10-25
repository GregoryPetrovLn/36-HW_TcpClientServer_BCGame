package telran.net.common;

import java.io.Serializable;

//закпрос на сервер
public class ProtocolRequest implements Serializable{
    private static final long serialVersionUID = 1l;

    //тип запроса
    public String requestType;

    //данные в запросе
    public Serializable requestData;

    //формируем объект запроса для отправки на сервер
    public ProtocolRequest(String requestType, Serializable requestData) {
        super();
        this.requestType = requestType;
        this.requestData = requestData;
    }
}

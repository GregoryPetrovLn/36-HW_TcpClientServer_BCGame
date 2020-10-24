package telran.net;

import java.io.Serializable;

//закпрос на сервер
public class RequestJava implements Serializable{
    private static final long serialVersionUID = 1l;

    //тип запроса
    public String requestType;

    //данные в запросе
    public Serializable requestData;

    //формируем объект запроса для отправки на сервер
    public RequestJava(String requestType, Serializable requestData) {
        super();
        this.requestType = requestType;
        this.requestData = requestData;
    }
}

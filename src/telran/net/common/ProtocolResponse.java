package telran.net.common;

import java.io.Serializable;

//ответ с сервера
public class ProtocolResponse implements Serializable{
    private static final long serialVersionUID = 1L;
    //тип ответа
    ResponseCode code;

    //данные в ответе
    Serializable responseData;

    //создаем объект ответа с сервера
    public ProtocolResponse(ResponseCode code, Serializable responseData) {
        super();
        this.code = code;
        this.responseData = responseData;
    }
}

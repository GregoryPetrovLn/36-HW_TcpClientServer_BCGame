package telran.net;

import java.io.Serializable;

//ответ с сервера
public class ResponseJava implements Serializable{
    private static final long serialVersionUID = 1L;
    //тип ответа
    TcpResponseCode code;

    //данные в ответе
    Serializable responseData;

    //создаем объект ответа с сервера
    public ResponseJava(TcpResponseCode code, Serializable responseData) {
        super();
        this.code = code;
        this.responseData = responseData;
    }
}

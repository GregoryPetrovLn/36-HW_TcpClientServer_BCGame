package telran.net;
import telran.net.RequestJava;
import telran.net.ResponseJava;

import java.io.*;
import java.net.*;

//Tcp протокод управления передачей данных
// протокол клиента
public class TcpClient implements Closeable {
    //отправка на сервер
    ObjectOutputStream outputStream;

    //получение от сервера
    ObjectInputStream inputStream;

    Socket socket;

    protected TcpClient(String hostname, int port){
        try {
            socket = new Socket(hostname, port);
            outputStream = new ObjectOutputStream(socket.getOutputStream());
            inputStream = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    @Override
    public void close() throws IOException {
        socket.close();
    }

    protected <T> T sendRequest(String requestType, Serializable requestData){

        //формирует запрос объект RequestJava с полями : requestType, requestData
        RequestJava request = new RequestJava(requestType, requestData);

        try{
            outputStream.writeObject(request);
            ResponseJava response = (ResponseJava) inputStream.readObject();


            if(response.code != TcpResponseCode.OK){
                throw new Exception(response.responseData.toString());
            }

            return (T) response.responseData;

        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }
}






















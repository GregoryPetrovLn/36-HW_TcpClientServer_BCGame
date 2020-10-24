package telran.net.server;

import telran.net.RequestJava;
import telran.net.ResponseJava;

import java.io.*;
import java.net.*;

public class ServerClientJava implements Runnable {
    //получаем данные от клиента
    ObjectInputStream input;

    //отправляем данные обратно
    ObjectOutputStream output;

    //ссылка на интерфейс
    ProtocolJava protocol;

    //формирование протокола для соединения сервер-клиент
    public ServerClientJava(Socket socket, ProtocolJava protocol) {
        try {
            input = new ObjectInputStream(socket.getInputStream());
            output = new ObjectOutputStream(socket.getOutputStream());
            //формирование протокола ответа(ссылка на интерфейс)
            this.protocol = protocol;

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void run() {
        while (true) {
            //формируем запрос у сервера и отдаем ему данные из сокета клиента
            try {
                //читаем из сокета
                RequestJava request = (RequestJava) input.readObject();

                //отдаем в интерфейс с протоколом и получаем от сервера ответ
                ResponseJava response = protocol.getResponse(request);

                //отправляем ответ клиенту
                output.writeObject(response);
            } catch (EOFException e) {
                System.out.println("client closed connection");
                break;
            } catch (Exception e) {
                System.out.println("client abnormally closed connection " + e.getMessage());
                break;
            }
        }

    }
}

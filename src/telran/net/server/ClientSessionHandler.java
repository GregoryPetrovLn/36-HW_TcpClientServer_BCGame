package telran.net.server;

import telran.net.common.ProtocolRequest;
import telran.net.common.ProtocolResponse;
import telran.net.common.ProtocolJava;

import java.io.*;
import java.net.*;

public class ClientSessionHandler implements Runnable {
    //получаем данные от клиента
    ObjectInputStream input;

    //отправляем данные обратно
    ObjectOutputStream output;

    //ссылка на интерфейс
    ProtocolJava protocol;

    //формирование протокола для соединения сервер-клиент
    public ClientSessionHandler(Socket socket, ProtocolJava protocol) {
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
                ProtocolRequest request = (ProtocolRequest) input.readObject();

                //отдаем в интерфейс с протоколом и получаем от сервера ответ
                ProtocolResponse response = protocol.getResponse(request);

                //отправляем ответ клиенту
                output.writeObject(response);
            } catch (EOFException e) {
                System.out.println("consoleGame.client closed connection");
                break;
            } catch (Exception e) {
                System.out.println("consoleGame.client abnormally closed connection " + e.getMessage());
                break;
            }
        }

    }
}

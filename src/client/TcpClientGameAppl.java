package client;

import game.Move;

import javax.xml.stream.events.StartDocument;
import java.io.IOException;
import java.util.*;

public class TcpClientGameAppl {
    private static final String START = "start";
    private static final String PROMPT = "prompt";
    private static final String MOVE = "move";
    private static final String IS_FINISHED = "isFinished";
    private static final String EMPTY_REQUEST_DATA = null;
    private static final String GET_RESULTS = "getResults";

    private static final int PORT = 4000;
    private static final String HOST = "localhost";

    public static void main(String[] args) throws IOException {
        TcpClientGame client = new TcpClientGame(HOST, PORT);
        String answer;
        String line;
        Scanner scanner = new Scanner(System.in);

        System.out.println("Are you ready to start new game? yes/no");


        String isStarted = scanner.nextLine();

        if (isStarted.equalsIgnoreCase("yes")) {
            System.out.println("\nYou can stop the game at any time. \nJust enter \"exit\"\n");

            answer = client.send(START, EMPTY_REQUEST_DATA);
            System.out.println(answer + " -- test key\n");

            while (true) {
                try {
                    answer = client.send(PROMPT, EMPTY_REQUEST_DATA);
                    System.out.println(answer);

                    line = scanner.nextLine();

                    if (line.equalsIgnoreCase("exit")) {
                        break;
                    }

                    answer = client.send(MOVE, line);

                    System.out.println("\t" + answer);


                    if (client.send(IS_FINISHED, EMPTY_REQUEST_DATA).equalsIgnoreCase("finish")) {
                        client.send(GET_RESULTS, EMPTY_REQUEST_DATA);

                        System.out.println("Nice game! One more time?yes/no");
                        isStarted = scanner.nextLine();
                        if (isStarted.equalsIgnoreCase("yes")) {
                            answer = client.send(START, EMPTY_REQUEST_DATA);
                            System.out.println(answer + " -- test key\n");
                        } else if (isStarted.equalsIgnoreCase("no")) {
                            System.out.println("Okay, as you wish.\nclient closed connection...");
                            break;
                        } else {
                            System.out.println("Unknown request");
                            break;
                        }

                    }


                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }


            }

        } else {
            System.out.println("Okay, as you wish.\nclient closed connection...");
            scanner.close();
            client.close();
        }
    }
}

package consoleGame.game;

import java.io.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;


public class BullsCowsGame implements GuessGame {
    private final String pathToFolder = "./src/consoleGame/consoleGame.gameResultsFolder/";
    private static Character[] number;
    public static boolean isFinished;
    LinkedList<Move> listResults = new LinkedList<>();

    public static Character[] getNumber() {
        return number;
    }


    private static void generateNumber() {
        number = new Random()
                .ints('1', '9' + 1)
                .distinct()
                .limit(4)
                .mapToObj(n -> (char) n).toArray(Character[]::new);
    }


    @Override
    public String startGame() {
        isFinished = false;
        generateNumber();
        return Arrays.toString(number);
    }

    @Override
    public String prompt() {
        return "Enter any number of 4 unrepeated digits[1-9]";
    }

    @Override
    public String move(String userInput) {
        Integer bulls = 0;
        Integer cows = 0;
        int length = userInput.length();

        int limit = length < 4 ? length : 4;
        for (int i = 0; i < limit; i++) {
            int index = userInput.indexOf(number[i]);
            if (index >= 0) {
                if (index == i) {
                    bulls++;
                } else {
                    cows++;
                }
            }
        }

        Move move = new Move(new int[]{bulls, cows}, userInput);

        listResults.add(move);
        if (bulls == 4 && cows == 0) {
            isFinished = true;
        }

        return move.toString();
    }

    @Override
    public boolean isFinished() {
        return isFinished;
    }

    public boolean getResults() {
        try (PrintWriter pw = new PrintWriter(pathToFolder + getFileName())) {
            int i = 0;
            for (Move move : listResults) {
                ++i;
                pw.println(i + ")" + move.toString());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return true;
    }

    private String getFileName() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH_mm");
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now) + "_" + listResults.size() + ".txt";
    }
}

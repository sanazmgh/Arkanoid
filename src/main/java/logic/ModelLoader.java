package logic;

import models.Board;
import models.Player;
import models.ball.Ball;
import models.brick.Brick;
import models.prize.Prize;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class ModelLoader {

    public ModelLoader() {
    }

    public int FindID(String userName) {
        File file = new File("playersDirectory/gameChart/Chart.txt");
        int LastID = 0;

        try
        {
            Scanner scanner = new Scanner(file);

            while (scanner.hasNext()) {
                String currentName = scanner.next();
                if (currentName.equals(userName)) {
                    return scanner.nextInt();
                }

                LastID = Math.max(scanner.nextInt(), LastID);
                scanner.next();
            }

            PrintStream printStream = new PrintStream(new FileOutputStream(file, true));
            printStream.println(userName + " " + (LastID + 1) + " " + 0);
            printStream.close();
            Files.createDirectories(Paths.get("playersDirectory/playersArchive/" + (LastID+1)));
        }

        catch (Exception e) {
            e.printStackTrace();
        }

        return LastID + 1;
    }

    public File[] userGames(int ID) {
        File playersArchive = new File("playersDirectory/playersArchive");
        for (File file : playersArchive.listFiles())
            if (file.getName().equals(Integer.toString(ID)))
                return file.listFiles();

        return null;
    }

    public Board LoadBoard(Player player , String game)
    {
        File file;

        if (game.equals("New game"))
            file = new File("playersDirectory/New game/NewGame.txt");

        else
            file = new File("playersDirectory/playersArchive/" + player.getID() + "/" + game + ".txt");

        try
        {
            Scanner scanner = new Scanner(file);
            String boardStr = "";

            while (scanner.hasNext()) {
                boardStr += scanner.next();
                boardStr += "\n";
            }

            Board board = BoardBuilder.Build(boardStr , player);
            return board;

        }

        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }

        return null;
    }
}

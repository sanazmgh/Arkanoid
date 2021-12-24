package logic;

import graphic.GameFrame;
import models.Board;
import models.Panel;
import models.Player;
import models.ball.Ball;
import models.brick.Brick;
import models.prize.Prize;

import java.io.*;
import java.util.LinkedList;
import java.util.Scanner;

public class LogicalAgent {

    ModelLoader modelLoader ;
    Board board;
    String nameOfGame="";
    int ID;

    public LogicalAgent()
    {
        this.modelLoader = new ModelLoader();
    }

    public String getNameOfGame() {
        return nameOfGame;
    }

    public File[] GamesList(String name)
    {
        int ID = modelLoader.FindID(name);
        return modelLoader.userGames(ID);
    }

    public LinkedList<String> getRanking()
    {
        LinkedList<String> chart = new LinkedList<>();
        File file = new File("playersDirectory/gameChart/Chart.txt");

        try
        {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNext())
            {
                String currentScore = "";
                currentScore = scanner.next() + " : " ;
                scanner.next();
                currentScore += scanner.next();
                chart.add(currentScore);
            }

            scanner.close();
        }

        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }

        return chart;
    }

    public void LoadGame(String gameName , String name)
    {
        int ID = modelLoader.FindID(name);

        this.nameOfGame = gameName;
        this.ID = ID;

        Player player = new Player(name , ID , 0 , 0);
        this.board = modelLoader.LoadBoard(player , gameName);
        GameFrame gameFrame = new GameFrame(this);
    }

    public void saveGame(String name)
    {
        File file = new File("playersDirectory/playersArchive/" + this.ID + "/" + name +".txt");

        LinkedList<Brick> availableBricks = new LinkedList<>();
        LinkedList<Ball> availableBalls = new LinkedList<>();

        for(Brick brick : board.getBricks())
            if(!brick.isRemoved())
                availableBricks.add(brick);

        for (Ball ball : board.getPlayer().getBalls())
            if(!ball.isRemoved())
                availableBalls.add(ball);

        try
        {
            PrintStream printStream = new PrintStream(new FileOutputStream(file));
            printStream.println(board.getPlayer().getScore() + " " + board.getPlayer().getChance());
            printStream.println("Brick " + availableBricks.size());

            for(Brick brick : availableBricks)
            {
                if(!brick.isRemoved())
                {
                    if (brick.getType() == 0)
                        printStream.println("B " + brick.getX() + " " + brick.getY());

                    if (brick.getType() == 1)
                        printStream.println("F " + brick.getX() + " " + brick.getY());

                    if (brick.getType() == 2)
                        printStream.println("I " + brick.getX() + " " + brick.getY());

                    if (brick.getType() == 3)
                        printStream.println("P " + brick.getX() + " " + brick.getY());

                    if (brick.getType() == 4)
                        printStream.println("W " + brick.getX() + " " + brick.getY());
                }
            }

            printStream.println("Ball " + availableBalls.size());
            printStream.close();

            UpdateChart();
        }

        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
    }

    public void UpdateChart()
    {
        String chart = "";
        File file = new File("playersDirectory/gameChart/Chart.txt");

        try
        {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNext())
            {
                String currentName = scanner.next();
                int currentID = scanner.nextInt();
                int Score = scanner.nextInt();

                if(currentID == this.ID && board.getPlayer().getScore() > Score)
                    chart += board.getPlayer().getName() + " " + this.ID + " " + board.getPlayer().getScore() + "\n";

                else
                    chart += currentName + " " + currentID + " " + Score + "\n";
            }

            scanner.close();

            PrintStream printStream = new PrintStream(new FileOutputStream(file));
            printStream.print(chart);
            printStream.close();
        }

        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
    }

    public Board getBoard() {
        return board;
    }

    public void checkOutOfBall()
    {
        int countBalls = 0;

        for(Ball ball : this.board.getPlayer().getBalls())
            if(!ball.isRemoved())
                countBalls ++ ;

        if(countBalls==0)
        {
            Panel panel = this.board.getPlayer().getPanel();
            Ball ball = new Ball(panel.getX()+panel.getWeight()/2 , this.board.getPlayer().getPanel().getY()-20 , 0);
            this.board.getPlayer().setBalls(ball);
            //this.board.getPlayer().LoseChance(1);
            ball.setBoard(board);
        }
    }

    private boolean checkEndGame()
    {
        if(this.board.getPlayer().getChance() < 0)
            return true;

        for(Brick brick : this.board.getBricks())
            if(!brick.isRemoved())
                if(brick.getHeight() + brick.getY() >= 700)
                    return true;

        return false;
    }

    public boolean updateModels()
    {
        this.board.addRow();
        //Iterator<Ball> iterator = this.board.getPlayer().getBalls().iterator(); iterator.hasNext();
        for (Ball ball : this.board.getPlayer().getBalls())
            if(!ball.isRemoved())
                ball.updatePosition();

        for(Brick brick : this.board.getBricks())
            if(!brick.isRemoved())
                brick.isCrashed();

        for(Prize prize : this.board.getPrizes())
            if(!prize.isRemoved())
                prize.isHit();

        for(Prize prize : this.board.getPlayer().getPrizes())
            if(!prize.isTimedOut())
                prize.StopPrize();

        checkOutOfBall();
        return checkEndGame();
    }

}
package logic;

import models.Board;
import models.Panel;
import models.Player;
import models.ball.Ball;
import models.brick.*;
import models.prize.Prize;

public class BoardBuilder {
    public static Board Build(String str , Player player)
    {
        Board board = new Board(player);
        String[] words = str.split("\n| ");
        int ind = 0;

        board.getPlayer().setScore(Integer.parseInt(words[ind]));
        ind++;

        board.getPlayer().setChance(Integer.parseInt(words[ind]));

        ind += 2;

        int n = Integer.parseInt(words[ind]);
        for(int i=0 ; i<n ; i++)
        {
            ind++;
            Brick brick;
            int x = Integer.parseInt(words[ind+1]) ;
            int y = Integer.parseInt(words[ind+2]) ;

            brick = switch (words[ind]) {
                case "B" -> new Brick(x, y);
                case "W" -> new WoodenBrick(x, y);
                case "F" -> new FlasherBrick(x, y);
                case "I" -> new InvisibleBrick(x, y);
                default -> new PrizedBrick(x, y);
            };

            board.setBricks(brick);
            brick.setBoard(board);

            ind += 2;
        }

        for(int i=0 ; i<Integer.parseInt(words[ind+2]) ; i++)
        {
            Ball ball = new Ball(490+5*i , 660 , 0);
            board.getPlayer().setBalls(ball);
            ball.setBoard(board);
        }

        Panel panel = new Panel(345 , 680);
        board.getPlayer().setPanel(panel);
        Prize.setBoard(board);
        return board;
    }
}

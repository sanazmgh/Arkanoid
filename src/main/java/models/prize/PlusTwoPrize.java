package models.prize;

import models.ball.Ball;

public class PlusTwoPrize extends Prize {
    public PlusTwoPrize(int x , int y)
    {
        super(x , y);
        this.setType(4);
    }

    @Override
    public void getPrize()
    {
        int panelX = (int)Prize.getBoard().getPlayer().getPanel().getX();
        int panelY = (int)Prize.getBoard().getPlayer().getPanel().getY();

        Ball ball1 = new Ball(panelX+5 , panelY-20 , 0);
        Ball ball2 = new Ball(panelX+10 , panelY-20 , 0);

        Prize.getBoard().getPlayer().setBalls(ball1);
        Prize.getBoard().getPlayer().setBalls(ball2);

        ball1.setBoard(Prize.getBoard());
        ball2.setBoard(Prize.getBoard());

        Prize.getBoard().getPlayer().AddOnScore(5);
        Prize.getBoard().getPlayer().AddOnChance(2);
    }
}
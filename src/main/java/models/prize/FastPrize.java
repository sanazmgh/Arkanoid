package models.prize;

import models.ball.Ball;

public class FastPrize extends Prize{
    public FastPrize(int x , int y)
    {
        super(x , y);
        this.setType(1);
    }

    @Override
    public void getPrize()
    {
        for(Ball ball : Prize.getBoard().getPlayer().getBalls())
        {
            if(!ball.isRemoved())
                ball.setVelocityFactor(2);
        }

        Prize.getBoard().getPlayer().setPrizes(this);
        this.setStartTime(System.currentTimeMillis());
        Prize.getBoard().getPlayer().AddOnScore(5);
    }

    @Override
    public void StopPrize()
    {
        if(System.currentTimeMillis()-this.getStartTime() < 5000)
            return;

        for(Ball ball : Prize.getBoard().getPlayer().getBalls())
        {
            if(!ball.isRemoved())
                ball.setVelocityFactor(1);
        }
        this.setTimedOut(true);
    }
}

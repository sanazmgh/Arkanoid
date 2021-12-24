package models.brick;

import models.prize.*;

import java.util.Random;

public class PrizedBrick extends Brick {
    public PrizedBrick (int x , int y)
    {
        super(x , y);
        this.type = 3 ;

        Random random = new Random();
        int prizeID = random.nextInt(8);
        Prize prize;

        if(prizeID == 0)
            prize = new DizzyPrize(x , y);

        else if(prizeID == 1)
            prize = new FastPrize(x , y);

        else if(prizeID == 2)
            prize = new FirePrize(x , y);

        else if(prizeID == 3)
            prize = new LargeBoardPrize(x , y);

        else if(prizeID == 4)
            prize = new PlusTwoPrize(x , y);

        else if(prizeID == 5)
            prize = new RandomPrize(x , y);

        else if(prizeID == 6)
            prize = new SlowPrize(x , y);

        else
            prize = new SmallBoardPrize(x , y);

        this.setPrize(prize);
    }

    @Override
    public boolean isCrashed() {
        if(hit == 1)
        {
            this.setRemoved(true);
            this.prize.setX(this.getX());
            this.prize.setY(this.getY());
            this.board.setPrizes(prize);
        }

        return (hit == 1);
    }
}


package models.prize;

import java.util.Random;

public class RandomPrize extends Prize {
    public RandomPrize (int x , int y)
    {
        super(x , y);
        this.setType(5);
    }

    @Override
    public void getPrize()
    {
        Random random = new Random();
        int prizeID = random.nextInt(7);

        if(prizeID == 0)
        {
            DizzyPrize prize = new DizzyPrize(0 , 0);
            prize.getPrize();
        }

        else if(prizeID == 1)
        {
            FastPrize prize = new FastPrize(0 , 0);
            prize.getPrize();
        }

        else if(prizeID == 2)
        {
            FirePrize prize = new FirePrize(0 , 0);
            prize.getPrize();
        }

        else if(prizeID == 3)
        {
            LargeBoardPrize prize = new LargeBoardPrize(0 , 0);
            prize.getPrize();
        }

        else if(prizeID == 4)
        {
            PlusTwoPrize prize = new PlusTwoPrize(0 , 0);
            prize.getPrize();
        }

        else if(prizeID == 5)
        {
            SlowPrize prize = new SlowPrize(0 , 0);
            prize.getPrize();
        }

        else
        {
            SmallBoardPrize prize = new SmallBoardPrize(0 , 0);
            prize.getPrize();
        }

    }

}

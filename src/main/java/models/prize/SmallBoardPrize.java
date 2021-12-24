package models.prize;

public class SmallBoardPrize extends Prize {
    public SmallBoardPrize(int x , int y)
    {
        super(x , y);
        this.setType(7);
    }

    @Override
    public void getPrize()
    {
        Prize.getBoard().getPlayer().getPanel().setSize(1);
        Prize.getBoard().getPlayer().setPrizes(this);
        this.setStartTime(System.currentTimeMillis());
        Prize.getBoard().getPlayer().AddOnScore(5);
    }

    @Override
    public void StopPrize()
    {
        if(System.currentTimeMillis()-this.getStartTime() < 5000)
            return;

        Prize.getBoard().getPlayer().getPanel().setSize(2);
        this.setTimedOut(true);
    }
}

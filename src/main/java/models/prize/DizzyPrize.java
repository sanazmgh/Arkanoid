package models.prize;

public class DizzyPrize extends Prize {
    public DizzyPrize (int x , int y)
    {
        super(x , y);
        this.setType(0);
    }

    @Override
    public void getPrize()
    {
        Prize.getBoard().getPlayer().getPanel().setReverse(true);
        Prize.getBoard().getPlayer().setPrizes(this);
        this.setStartTime(System.currentTimeMillis());
        Prize.getBoard().getPlayer().AddOnScore(5);
    }

    @Override
    public void StopPrize()
    {
        if(System.currentTimeMillis()-this.getStartTime() < 5000)
            return;

        Prize.getBoard().getPlayer().getPanel().setReverse(false);
        this.setTimedOut(true);
    }
}

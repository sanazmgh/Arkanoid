package models.brick;

public class WoodenBrick extends Brick{
    public WoodenBrick (int x , int y)
    {
        super(x , y);
        this.type = 4;
    }

    @Override
    public boolean isCrashed()
    {
        if(this.hit == 2)
            this.setRemoved(true);

        return (this.hit == 2);
    }
}

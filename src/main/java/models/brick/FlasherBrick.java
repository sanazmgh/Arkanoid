package models.brick;

public class FlasherBrick extends Brick {

    public long LastTime;
    public boolean visible;

    public FlasherBrick (int x , int y)
    {
        super(x , y );
        this.type = 1 ;
        this.LastTime = System.currentTimeMillis();
        this.visible = true;
    }

    @Override
    public boolean isVisible() {
        long currentTime = System.currentTimeMillis();

        if(currentTime - LastTime >= 3000)
        {
            this.visible = !this.visible;
            LastTime = System.currentTimeMillis();
        }

        return this.visible;
    }
}

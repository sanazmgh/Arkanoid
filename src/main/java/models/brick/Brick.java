package models.brick;

import models.Board;
import models.ball.Ball;
import models.prize.Prize;

public class Brick {
    protected int X;
    protected int Y;
    protected int height = 50;
    protected int weight = 100;
    protected int hit;
    protected boolean visible ;
    protected boolean isRemoved = false;
    protected int type;
    protected Prize prize;
    protected Board board;

    public Brick(int x , int y )
    {
        this.X = x;
        this.Y = y;
        this.visible = true;
        this.type = 0;
    }

    public int getX() {
        return X;
    }

    public void setX(int x) {
        X = x;
    }

    public int getY() {
        return Y;
    }

    public void setY(int y) {
        Y = y;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getHit() {
        return hit;
    }

    public void setHit(int hit) {
        this.hit = hit;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public boolean isRemoved() {
        return isRemoved;
    }

    public void setRemoved(boolean removed) {
        isRemoved = removed;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Prize getPrize() {
        return prize;
    }

    public void setPrize(Prize prize) {
        this.prize = prize;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public boolean isCrashed()
    {
        if(hit == 1)
            this.setRemoved(true);

        return (hit == 1);
    }

    public boolean BallHit(Ball ball)
    {
        if(!this.isVisible() && this.type==1)
            return false;

        if(this.Y-ball.getR() <= ball.getY() && ball.getY() <= this.Y+ height && ball.getX()+ball.getR() >= this.X && ball.getX()+ball.getR() <= this.X+20)
        {
            //System.out.println("left");
            if(ball.getType() == 1)
            {
                this.setRemoved(true);
                return true;
            }

            this.hit++;
            this.isCrashed();
            ball.HitAnSurface(-1 , 1);

            return true;
        }

        else if(this.Y-ball.getR() <= ball.getY() && ball.getY() <= this.Y+ height && ball.getX() <= this.X+ weight && ball.getX() >= this.X+ weight-20)
        {
            //System.out.println("right");
            if(ball.getType() == 1)
            {
                this.setRemoved(true);
                return true;
            }

            this.hit++;
            this.isCrashed();
            ball.HitAnSurface(-1 , 1);

            return true;
        }

        else if(this.X-ball.getR() <= ball.getX() && ball.getX() <= this.X+ weight && ball.getY()+ball.getR() >= this.Y &&  ball.getY()+ball.getR() <= this.Y +20)
        {
            //System.out.println("up");
            if(ball.getType() == 1)
            {
                this.setRemoved(true);
                return true;
            }

            this.hit++;
            this.isCrashed();
            ball.HitAnSurface(1 , -1);

            return true;
        }

        else if(this.X-ball.getR() <= ball.getX() && ball.getX() <= this.X+ weight && ball.getY() <= this.Y + height && ball.getY() >= this.Y + height-20)
        {
            //System.out.println("down");
            if(ball.getType() == 1)
            {
                this.setRemoved(true);
                return true;
            }

            this.hit++;
            this.isCrashed();
            ball.HitAnSurface(1 , -1);

            return true;
        }

        return false;
    }

    public void MoveDown()
    {
        this.Y += height;
    }
}

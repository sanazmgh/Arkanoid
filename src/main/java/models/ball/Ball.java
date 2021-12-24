package models.ball;

import models.Board;
import models.brick.Brick;

public class Ball {
    private double X;
    private double Y;
    private double r = 20;
    private double XVelocity = 6;
    private double YVelocity = 6;
    private double velocityFactor;
    private boolean isRemoved = false;
    private int Type = 0;
    private Board board;

    public Ball(double x , double y , int type )
    {
        this.X = x;
        this.Y = y;
        this.Type = type;
        this.velocityFactor = 1 ;
    }

    public double getX() {
        return X;
    }

    public void setX(int x) {
        X = x;
    }

    public double getY() {
        return Y;
    }

    public double getR() {
        return r;
    }

    public void setR(double r) {
        this.r = r;
    }

    public void setY(int y) {
        Y = y;
    }

    public double getXVelocity() {
        return XVelocity;
    }

    public void setXVelocity(double XVelocity) {
        this.XVelocity = XVelocity;
    }

    public double getYVelocity() {
        return YVelocity;
    }

    public void setYVelocity(double YVelocity) {
        this.YVelocity = YVelocity;
    }

    public double getVelocityFactor() {
        return velocityFactor;
    }

    public void setVelocityFactor(double velocityFactor) {
        this.velocityFactor = velocityFactor;
    }

    public boolean isRemoved() {
        return isRemoved;
    }

    public void setRemoved(boolean removed) {
        isRemoved = removed;
    }

    public int getType() {
        return Type;
    }

    public void setType(int type) {
        Type = type;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public void updatePosition()
    {
        boolean alreadyHit = false;
        alreadyHit = this.board.getPlayer().getPanel().BallHit(this);

        if(!alreadyHit)
            for(Brick brick : this.board.getBricks())
            {
                if(!brick.isRemoved())
                    if(brick.BallHit(this))
                    {
                        alreadyHit = true;
                        break;
                    }
            }

        if(this.getY()+this.getR() >= 700 && !alreadyHit)
        {
            this.board.getPlayer().LoseChance(1);
            this.setRemoved(true);
            alreadyHit = true;
        }

        if(this.getY() <= 0 && !alreadyHit)
        {
            this.HitAnSurface(1, -1);
            alreadyHit = true;
        }

        if(this.getX() <= 0 && !alreadyHit)
        {
            this.HitAnSurface(-1, +1);
            alreadyHit = true;
        }

        if(this.getX()+this.getR() >= 1000 && !alreadyHit)
        {
            this.HitAnSurface(-1, 1);
            alreadyHit = true;
        }

        if(alreadyHit)
            this.board.getPlayer().AddOnScore(3);

        this.X += XVelocity*velocityFactor;
        this.Y += YVelocity*velocityFactor;
    }

    public void HitAnSurface(double mulX , double mulY)
    {
        this.XVelocity *= mulX;
        this.YVelocity *= mulY;

        //System.out.println(this.XVelocity*this.XVelocity + this.YVelocity*this.YVelocity);
    }
}
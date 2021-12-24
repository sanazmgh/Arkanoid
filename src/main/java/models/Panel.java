package models;

import models.ball.Ball;

public class Panel {
    private double x;
    private double y;
    private int velocity = 50;
    private double weight = 200;
    private int size;
    private boolean isReverse;

    public Panel(int x , int y)
    {
        this.x = x;
        this.y = y;
        this.size = 2;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;

        if(this.size == 1)
            weight = 150;

        if(this.size == 2)
            weight = 200;

        if(this.size == 3)
            weight = 250;

    }

    public boolean isReverse() {
        return isReverse;
    }

    public void setReverse(boolean reverse) {
        isReverse = reverse;
    }

    public boolean BallHit(Ball ball)
    {
        if(ball.getX()+ ball.getR() >= this.getX() && ball.getX() <= this.getX() + this.weight && ball.getY()+ ball.getR() >= this.getY() && ball.getY()+ ball.getR() <= this.getY()+5)
        {
            /*
            if(ball.getX()+ ball.getR() <= x+(double)weight/4)
                ball.HitAnSurface((double)1/2 , -(double)3/2);

            else if (ball.getX() >= x + weight * (double)3 / 4)
                ball.HitAnSurface((double)3/2, (double)-1/2);

            else
                ball.HitAnSurface(1, -1);

            return true;
             */

            double l = Math.abs(ball.getX() + ball.getR()/2 - this.getX() - this.getWeight()/2);
            double lowerFactor = (this.weight/2) / (this.getWeight()/2 + l);

            if(ball.getX() < this.getWeight()/2 + this.getX())
            {
                double higherFactor = 1 + (ball.getXVelocity() * ball.getXVelocity() * (1-lowerFactor*lowerFactor))/(ball.getYVelocity() * ball.getYVelocity()) ;
                higherFactor = Math.sqrt(higherFactor);

                ball.HitAnSurface(lowerFactor, -higherFactor);
            }

            else
            {
                double higherFactor = 1 + (ball.getYVelocity() * ball.getYVelocity() * (1-lowerFactor*lowerFactor))/(ball.getXVelocity() * ball.getXVelocity()) ;
                higherFactor = Math.sqrt(higherFactor);

                ball.HitAnSurface(higherFactor, -lowerFactor);
            }

        }

        return false;
    }

    public void moveTo(boolean right)
    {
        if((right && !this.isReverse) || (!right && this.isReverse))
            this.x = Math.min(this.x+this.velocity , 1000-this.weight);

        else
            this.x = Math.max(this.x-this.velocity , 0);
    }
}

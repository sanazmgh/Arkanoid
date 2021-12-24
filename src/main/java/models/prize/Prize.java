package models.prize;

import models.Board;

public class Prize {
    private int x ;
    private int y;
    private int r = 30;
    private int type;
    private int velocity = 5;
    private boolean isRemoved = false;
    private boolean timedOut = false;
    private static Board board;
    private long startTime;

    public Prize(int x , int y)
    {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getVelocity() {
        return velocity;
    }

    public int getR() {
        return r;
    }

    public void setR(int r) {
        this.r = r;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public void setVelocity(int velocity) {
        this.velocity = velocity;
    }

    public boolean isRemoved() {
        return isRemoved;
    }

    public void setRemoved(boolean removed) {
        isRemoved = removed;
    }

    public boolean isTimedOut() {
        return timedOut;
    }

    public void setTimedOut(boolean timedOut) {
        this.timedOut = timedOut;
    }

    public static Board getBoard() {
        return board;
    }

    public static void setBoard(Board board) {
        Prize.board = board;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public boolean isDisabled()
    {
        long currentTime = System.currentTimeMillis();

        return (currentTime - this.startTime >= 30000);
    }

    public void getPrize() {}
    public void StopPrize() {}

    public boolean isHit()
    {
        int panelX = (int)Prize.board.getPlayer().getPanel().getX();
        int panelY = (int)Prize.board.getPlayer().getPanel().getY();
        int panelWeight = (int)Prize.board.getPlayer().getPanel().getWeight();

        if(this.y+this.r >= panelY  && this.x >= panelX && this.x <= panelX + panelWeight)
        {
            this.setRemoved(true);
            this.getPrize();
            return true;
        }

        else if(this.y+this.r >= panelY)
        {
            this.setRemoved(true);
            return false;
        }

        this.y += velocity;
        return false;
    }
}

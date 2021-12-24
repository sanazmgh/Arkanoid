package models;

import models.brick.*;
import models.prize.Prize;

import java.util.LinkedList;

public class Board {
    private boolean keyBoardReverseMode = true;
    private Player player ;
    private long LastTime ;
    private LinkedList<Brick> bricks = new LinkedList<>();
    private LinkedList<Prize> prizes = new LinkedList<>();

    public Board(Player player)
    {
        this.player = player;
        LastTime = System.currentTimeMillis();
    }

    public boolean isKeyBoardReverseMode() {
        return keyBoardReverseMode;
    }

    public void setKeyBoardReverseMode(boolean keyBoardReverseMode) {
        this.keyBoardReverseMode = keyBoardReverseMode;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public LinkedList<Brick> getBricks()
    {
        return bricks;
    }

    public LinkedList<Prize> getPrizes() {
        return prizes;
    }

    public void setPrizes(Prize prizes) {
        this.prizes.add(prizes);
    }

    public void setBricks(Brick bricks)
    {
        this.bricks.add(bricks);
    }

    /*
    public void removeBrick(Brick brick)
    {
        this.bricks.remove(brick);
    }
    */

    /*
    public void removePrizes(Prize prize)
    {
        this.prizes.remove(prize);
    }
    */

    public void addRow()
    {
        if(System.currentTimeMillis() - LastTime < 20000)
            return;

        for(Brick brick : this.getBricks())
            if(!brick.isRemoved())
                brick.MoveDown();

        Brick brick0 = new Brick(0 , 0);
        Brick brick1 = new Brick(100 , 0);
        Brick brick3 = new WoodenBrick(200 , 0);
        Brick brick2 = new InvisibleBrick(300 , 0);
        Brick brick5 = new PrizedBrick(400 , 0);
        Brick brick4 = new PrizedBrick(500 , 0);
        Brick brick6 = new FlasherBrick(600 , 0);
        Brick brick7 = new WoodenBrick(700 , 0);
        Brick brick8 = new Brick(800 , 0);
        Brick brick9 = new Brick(900 , 0);

        /*
        System.out.println(brick0.getType());
        System.out.println(brick1.getType());
        System.out.println(brick2.getType());
        System.out.println(brick3.getType());
        System.out.println(brick4.getType());
        System.out.println(brick5.getType());
        System.out.println(brick6.getType());
        System.out.println(brick7.getType());
        System.out.println(brick8.getType());
        System.out.println(brick9.getType());
        */

        this.setBricks(brick0);
        this.setBricks(brick1);
        this.setBricks(brick2);
        this.setBricks(brick3);
        this.setBricks(brick4);
        this.setBricks(brick5);
        this.setBricks(brick6);
        this.setBricks(brick7);
        this.setBricks(brick8);
        this.setBricks(brick9);

        brick0.setBoard(this);
        brick1.setBoard(this);
        brick2.setBoard(this);
        brick3.setBoard(this);
        brick4.setBoard(this);
        brick5.setBoard(this);
        brick6.setBoard(this);
        brick7.setBoard(this);
        brick8.setBoard(this);
        brick9.setBoard(this);

        LastTime = System.currentTimeMillis();
    }
}

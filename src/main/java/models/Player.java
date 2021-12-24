package models;

import models.ball.Ball;
import models.prize.Prize;

import java.util.LinkedList;

public class Player {
    private String name;
    private int ID;
    private LinkedList<Ball> balls = new LinkedList<>();
    private LinkedList<Prize> prizes = new LinkedList<>();
    private Panel panel;
    private int score ;
    private int mostScore;
    private int chance;

    public Player(String name , int ID , int score , int chance)
    {
        this.name = name;
        this.ID = ID;
        this.score = score;
        this.chance = chance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public LinkedList<Ball> getBalls() {
        return balls;
    }

    public void setBalls(Ball ball) {
        this.balls.add(ball);
    }

    public LinkedList<Prize> getPrizes() {
        return prizes;
    }

    public void setPrizes(Prize prizes) {
        this.prizes.add(prizes);
    }

    public Panel getPanel() {
        return panel;
    }

    public void setPanel(Panel panel) {
        this.panel = panel;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setChance(int chance) {
        this.chance = chance;
    }

    public int getChance() {
        return chance;
    }

    public void AddOnChance(int chance) {
        this.chance += chance;
    }

    public void LoseChance(int lose)
    {
        this.chance -= lose;

    }

    /*
    public void LoseBall(Ball ball)
    {
        this.balls.remove(ball);
    }
     */

    /*
    public void LosePrize(Prize prize)
    {
        this.prizes.remove(prize);
    }
     */

    public void AddOnScore(int x)
    {
        this.score += x;
    }
}

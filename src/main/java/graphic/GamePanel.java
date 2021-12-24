package graphic;

import logic.LogicalAgent;
import models.Panel;
import models.ball.Ball;
import models.brick.Brick;
import models.prize.Prize;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GamePanel extends JPanel implements ActionListener {
    Action rightKey;
    Action leftKey;

    JPanel panel;
    JFrame frame;
    JButton pauseButton;
    JButton exitButton;
    JButton chartButton;

    private final int PanelWidth = 1000;
    private final int PanelHeight = 700;
    private LogicalAgent logicalAgent;

    String str = "./src/main/resources/";
    Image Ball = new ImageIcon(str + "Ball.png").getImage();
    Image paddle = new ImageIcon(str + "Paddle.png").getImage();
    Image SlowPrize = new ImageIcon(str + "SlowPrize.png").getImage();
    Image FastPrize = new ImageIcon(str + "FastPrize.png").getImage();
    Image FirePrize = new ImageIcon(str + "FirePrize.png").getImage();
    Image FiredBall = new ImageIcon(str + "FiredBall.png").getImage();
    Image SmallPrize = new ImageIcon(str + "SmallPrize.png").getImage();
    Image DizzyPrize = new ImageIcon(str + "DizzyPrize.png").getImage();
    Image LargePrize = new ImageIcon(str + "LargePrize.png").getImage();
    Image RandomPrize = new ImageIcon(str + "RandomPrize.png").getImage();
    Image GlassyBrick = new ImageIcon(str + "GlassyBrick.png").getImage();
    Image WoodenBrick = new ImageIcon(str + "WoodenBrick.png").getImage();
    Image TwoBallsPrize = new ImageIcon(str + "TwoBallsPrize.png").getImage();

    Timer timer;
    Timer addBricksTimer;

    public GamePanel(LogicalAgent logicalAgent)
    {
        this.logicalAgent = logicalAgent;
        panel = new JPanel();
        timer = new Timer(20 , this);

        rightKey = new RightKey();
        leftKey = new LeftKey();

        this.getInputMap().put(KeyStroke.getKeyStroke("LEFT") , "LeftAction");
        this.getActionMap().put("LeftAction" , leftKey);
        this.getInputMap().put(KeyStroke.getKeyStroke("RIGHT") , "RightAction");
        this.getActionMap().put("RightAction" , rightKey);

        pauseButton = new JButton("Start/Pause");
        pauseButton.setFont(new Font(null , Font.PLAIN , 5));
        pauseButton.setForeground(Color.gray);
        pauseButton.addActionListener(this);
        pauseButton.setFocusable(false);
        pauseButton.setBounds(0 , 675 , 80 , 25);
        pauseButton.setBackground(Color.black);

        exitButton = new JButton("Exit & save");
        exitButton.setFont(new Font(null , Font.PLAIN , 8));
        exitButton.setForeground(Color.gray);
        exitButton.addActionListener(this);
        exitButton.setFocusable(false);
        exitButton.setBounds(80 , 675 , 80 , 25);
        exitButton.setBackground(Color.black);

        chartButton = new JButton("Chart");
        chartButton.setFont(new Font(null , Font.PLAIN , 8));
        chartButton.setForeground(Color.gray);
        chartButton.addActionListener(this);
        chartButton.setFocusable(false);
        chartButton.setBounds(160 , 675 , 80 , 25);
        chartButton.setBackground(Color.black);

        this.setLayout(null);

        this.setPreferredSize(new Dimension(PanelWidth , PanelHeight));
        this.setBackground(new Color(33, 33, 33));

        this.add(pauseButton);
        this.add(exitButton);
        this.add(chartButton);

        //timer.start();
    }

    public void setFrame(JFrame frame)
    {
        this.frame = frame;
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D) g;

        for(Brick brick : logicalAgent.getBoard().getBricks())
        {
            if(!brick.isRemoved())
            {
                if (brick.getType() == 0 || brick.getType() == 3)
                    g2D.drawImage(GlassyBrick, brick.getX(), brick.getY(), brick.getWeight(), brick.getHeight(), null);

                else if (brick.getType() == 1 && brick.isVisible())
                    g2D.drawImage(GlassyBrick, brick.getX(), brick.getY(), brick.getWeight(), brick.getHeight(), null);

                else if (brick.getType() == 4)
                    g2D.drawImage(WoodenBrick, brick.getX(), brick.getY(), brick.getWeight(), brick.getHeight(), null);
            }
        }

        for (Prize prize : logicalAgent.getBoard().getPrizes()) {
            if (!prize.isRemoved())
            {
                if (prize.getType() == 0)
                    g2D.drawImage(DizzyPrize, prize.getX(), prize.getY(), prize.getR(), prize.getR(), null);

                if (prize.getType() == 1)
                    g2D.drawImage(FastPrize, prize.getX(), prize.getY(), prize.getR(), prize.getR(), null);

                if (prize.getType() == 2)
                    g2D.drawImage(FirePrize, prize.getX(), prize.getY(), prize.getR(), prize.getR(), null);

                if (prize.getType() == 3)
                    g2D.drawImage(LargePrize, prize.getX(), prize.getY(), prize.getR(), prize.getR(), null);

                if (prize.getType() == 4)
                    g2D.drawImage(TwoBallsPrize, prize.getX(), prize.getY(), prize.getR(), prize.getR(), null);

                if (prize.getType() == 5)
                    g2D.drawImage(RandomPrize, prize.getX(), prize.getY(), prize.getR(), prize.getR(), null);

                if (prize.getType() == 6)
                    g2D.drawImage(SlowPrize, prize.getX(), prize.getY(), prize.getR(), prize.getR(), null);

                if (prize.getType() == 7)
                    g2D.drawImage(SmallPrize, prize.getX(), prize.getY(), prize.getR(), prize.getR(), null);
            }
        }

        for(Ball ball : logicalAgent.getBoard().getPlayer().getBalls())
        {
            if(!ball.isRemoved())
            {
                if (ball.getType() == 0)
                    g2D.drawImage(Ball, (int) ball.getX(), (int) ball.getY(), (int) ball.getR(), (int) ball.getR(), null);

                if (ball.getType() == 1)
                    g2D.drawImage(FiredBall, (int) ball.getX(), (int) ball.getY(), (int) ball.getR(), (int) ball.getR(), null);
            }
        }

        Panel panel = logicalAgent.getBoard().getPlayer().getPanel();
        g2D.drawImage(paddle , (int)panel.getX() , (int)panel.getY() , (int)panel.getWeight() , 20 , null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == timer)
        {
            if(logicalAgent.updateModels())
            {
                timer.stop();
                new EndGameFrame(logicalAgent);
                frame.dispose();
            }

            else
            {
                repaint();
                revalidate();
            }
        }

        if(e.getSource() == pauseButton)
        {
            if(timer.isRunning())
                timer.stop();

            else
                timer.start();
        }

        if(e.getSource() == chartButton)
        {
            timer.stop();
            new ChartFrame(this.logicalAgent);
        }

        if(e.getSource() == exitButton)
        {
            timer.stop();
            frame.dispose();
            new ExitFrame(this.logicalAgent);
        }
    }

    public class RightKey extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            logicalAgent.getBoard().getPlayer().getPanel().moveTo(true);
        }
    }

    public class LeftKey extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            logicalAgent.getBoard().getPlayer().getPanel().moveTo(false);
        }
    }
}

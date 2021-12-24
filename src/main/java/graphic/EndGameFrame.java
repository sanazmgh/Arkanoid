package graphic;

import logic.LogicalAgent;

import javax.swing.*;
import java.awt.*;

public class EndGameFrame extends JFrame {

    JFrame frame;
    JLabel text;

    public EndGameFrame(LogicalAgent logicalAgent)
    {
        logicalAgent.UpdateChart();

        frame = new JFrame();
        text = new JLabel("Game over(you cant save this game. you lost! loser.)");
        text.setFont(new Font(null , Font.PLAIN , 15));

        frame = new JFrame();
        frame.add(text);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());
        frame.pack();
        frame.setVisible(true);
    }
}

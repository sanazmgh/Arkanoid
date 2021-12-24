package graphic;

import logic.LogicalAgent;

import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame {
    JFrame frame ;
    GamePanel panel;
    LogicalAgent logicalAgent;

    public GameFrame(LogicalAgent logicalAgent)
    {
        frame = new JFrame();
        panel = new GamePanel(logicalAgent);
        panel.setFrame(frame);
        this.logicalAgent = logicalAgent;

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.add(panel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}


package graphic;

import logic.LogicalAgent;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

public class ChartFrame extends JFrame {

    JFrame frame;
    JLabel text;
    LogicalAgent logicalAgent;

    public ChartFrame(LogicalAgent logicalAgent)
    {
        frame = new JFrame();
        frame.setLayout(new FlowLayout());
        this.logicalAgent = logicalAgent;

        frame = new JFrame();
        Box box = Box.createVerticalBox();
        LinkedList<String> chart = logicalAgent.getRanking();

        for(String str : chart)
        {
            text = new JLabel(str);
            text.setFont(new Font(null , Font.PLAIN , 15));
            box.add(text);

            System.out.println(str);
        }

        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(box);
        frame.pack();
        frame.setVisible(true);
    }
}


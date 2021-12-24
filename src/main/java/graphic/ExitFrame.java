package graphic;

import logic.LogicalAgent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class ExitFrame extends JFrame implements ActionListener {

    private JFrame frame ;
    private JButton submitButton;
    private JTextField enterName;
    private LogicalAgent logicalAgent;
    private JLabel text ;
    private String name;

    public ExitFrame(LogicalAgent logicalAgent)
    {
        this.logicalAgent = logicalAgent;
        this.name = logicalAgent.getNameOfGame();

        if(name.equals("New game"))
            text = new JLabel("Choose a name for your game:");

        else
            text = new JLabel("Enter " + name + " to save this game in existing file \n or enter a new name to create a new file");

        text.setFont(new Font(null , Font.PLAIN , 12));
        text.setVerticalTextPosition(JLabel.TOP);

        submitButton = new JButton("Submit");
        submitButton.addActionListener(this);
        submitButton.setFocusable(false);

        enterName = new JTextField();
        enterName.setPreferredSize(new Dimension(130 , 30));

        frame = new JFrame();
        frame.add(text);
        frame.add(submitButton);
        frame.add(enterName);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());
        frame.pack();
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == submitButton) {
           logicalAgent.saveGame(enterName.getText());
           frame.dispose();
        }
    }
}

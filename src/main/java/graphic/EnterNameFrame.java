package graphic;

import logic.LogicalAgent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class EnterNameFrame extends JFrame implements ActionListener {

    private JFrame frame ;
    private JButton submitButton;
    private JTextField enterName;
    private String name;
    private JLabel text ;
    private LogicalAgent logicalAgent;

    public EnterNameFrame()
    {
        this.logicalAgent = new LogicalAgent();

        text = new JLabel("Enter your name here:");
        text.setFont(new Font(null , Font.PLAIN , 15));
        //text.setVerticalTextPosition(JLabel.TOP);

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
            name = enterName.getText();
            File[] files = this.logicalAgent.GamesList(name);
            frame.dispose();
            new ChooseGameFrame(files , name , logicalAgent);
        }
    }
}

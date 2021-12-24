package graphic;

import logic.LogicalAgent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class ChooseGameFrame extends JFrame implements ActionListener {

    private JFrame frame;
    private JComboBox GamesList;
    private JLabel text ;
    private JButton submitButton;
    private File[] files ;
    private String name;
    private LogicalAgent logicalAgent;

    public ChooseGameFrame (File[] files , String name , LogicalAgent logicalAgent)
    {
        frame = new JFrame();
        this.files = files;
        this.name = name;
        this.logicalAgent = logicalAgent;

        String[] gameNames = new String[this.files.length + 1];
        gameNames[0] = "New game";
        for(int i=0 ; i<this.files.length ; i++)
            gameNames[i+1] = files[i].getName().substring(0 , files[i].getName().length()-4);

        text = new JLabel("Choose a game to play:");
        text.setFont(new Font(null , Font.PLAIN , 15));
        text.setHorizontalAlignment(JLabel.LEFT);

        submitButton = new JButton("Submit");
        submitButton.addActionListener(this);
        submitButton.setFocusable(false);

        this.GamesList = new JComboBox(gameNames);

        frame.add(submitButton);
        frame.add(text);
        frame.add(GamesList);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());
        frame.pack();
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == submitButton)
        {
            String game = GamesList.getSelectedItem().toString();
            logicalAgent.LoadGame(game , name);
            frame.dispose();
        }
    }
}

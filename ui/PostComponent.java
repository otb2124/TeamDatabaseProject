import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class PostComponent implements Component{
    
    JPanel panel;
    

    public PostComponent(int y, ImageIcon[] icons, String fullName, String userName, String description, String date, String commentNumber){
        
        panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createLineBorder(Color.BLACK)); 

        JPanel icoPanel = new JPanel();
        icoPanel.add(new JLabel(icons[4]));

        JPanel namePanel = new JPanel(new GridLayout(0, 1));
        namePanel.add(new JLabel(fullName));
        namePanel.add(new JLabel(userName));

        JPanel creatorInfo = new JPanel(new BorderLayout());
        creatorInfo.add(icoPanel, BorderLayout.WEST);
        creatorInfo.add(namePanel);
        panel.add(creatorInfo, BorderLayout.NORTH);

        JTextArea descriptionArea = new JTextArea(description);
        descriptionArea.setLineWrap(true);
        descriptionArea.setWrapStyleWord(true);
        descriptionArea.setEditable(false);
        JScrollPane descriptionScrollPane = new JScrollPane(descriptionArea);
        descriptionScrollPane.setBorder(null);
        panel.add(descriptionScrollPane);


        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        bottomPanel.add(new JLabel(date), BorderLayout.WEST);

        JPanel commentPanel = new JPanel(new BorderLayout());
        commentPanel.add(new JLabel(commentNumber), BorderLayout.WEST);
        commentPanel.add(Box.createHorizontalStrut(10));
        commentPanel.add(new JLabel(icons[5]), BorderLayout.EAST);
        bottomPanel.add(commentPanel, BorderLayout.EAST);
        
        panel.setBounds(0, y, 315, 200 + descriptionArea.getHeight());
        panel.add(bottomPanel, BorderLayout.SOUTH);

    }


    @Override
    public JComponent getComponent() {
        
        return panel;
    }

}

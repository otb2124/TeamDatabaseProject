import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.*;

public class PostComponent implements Component{
    
    JPanel panel;
    

    public PostComponent(int y, ImageIcon icon, String fullName, String userName, String description){
        
        panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createLineBorder(Color.BLACK)); 

        JPanel icoPanel = new JPanel();
        icoPanel.add(new JLabel(icon));

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

        
        panel.setBounds(0, y, 315, 200 + descriptionArea.getHeight());

    }


    @Override
    public JComponent getComponent() {
        
        return panel;
    }

}

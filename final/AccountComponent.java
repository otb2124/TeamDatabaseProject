import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class AccountComponent implements Component{

    JPanel panel;

    public AccountComponent(int y, ImageIcon[] icons, String fullName, String userName, ImageManager im) {

        panel = new JPanel(new BorderLayout());

        JPanel icoPanel = new JPanel();
        icoPanel.add(new JLabel(icons[4]));
        panel.add(icoPanel, BorderLayout.WEST);

        JPanel namePanel = new JPanel();
        namePanel.setLayout(new BoxLayout(namePanel, BoxLayout.Y_AXIS));

        JLabel nameLabel = new JLabel(fullName);
        nameLabel.setFont(im.createCustomFont(16, "Roboto-Bold.ttf"));

        JLabel userNameLabel = new JLabel(userName);
        userNameLabel.setFont(im.createCustomFont(14, "Roboto-Regular.ttf"));
        userNameLabel.setForeground(new Color(132, 132, 132));

        namePanel.add(nameLabel);
        namePanel.add(userNameLabel);
        panel.add(namePanel);

        panel.setPreferredSize(new Dimension(315, 60));
        panel.setBounds(0, y, 315, 60);
    }

    @Override
    public JComponent getComponent() {
        
        return panel;
    }
    
}

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class NotificationComponent implements Component{

    JPanel panel;

    public NotificationComponent(int y, ImageIcon[] icons, String fullName, String userName, String commentString) {

        panel = new JPanel(new BorderLayout());

        JPanel icoPanel = new JPanel();
        icoPanel.add(new JLabel(icons[4]));
        panel.add(icoPanel, BorderLayout.WEST);

        JPanel textPanel = new JPanel();
        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));

        JTextArea textArea = new JTextArea(fullName + "(" + userName + ") comments your post: \"" + commentString + "\"");
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setEditable(false);
        JScrollPane descriptionScrollPane = new JScrollPane(textArea);
        descriptionScrollPane.setBorder(null);
        panel.add(descriptionScrollPane);
        textPanel.add(textArea);
        panel.add(textPanel);

        panel.setPreferredSize(new Dimension(315, 60));
        panel.setBounds(0, y, 315, 60);
    }

    @Override
    public JComponent getComponent() {
        
        return panel;
    }
    
}

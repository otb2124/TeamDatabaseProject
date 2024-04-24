import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class ProfileComponent implements Component {

    JPanel pfPage;

    public ProfileComponent(ImageManager im) {

        pfPage = new JPanel(new BorderLayout());
    
        pfPage.add(createProfile(0, im.getImgs(), "Orest", "otb2124", "RIT Croatia, Dubrovnik", "Student"), BorderLayout.NORTH);
    
        JPanel postsPanel = new JPanel();
        postsPanel.setLayout(new BoxLayout(postsPanel, BoxLayout.Y_AXIS));
        postsPanel.add(new PostComponent(0, im.getImgs(), "Orest", "otb2124",
                "Dear All,\nHello, my name is Orest. I'm an RIT student.\nI like video games.", "1/4/2024", "5")
                .getComponent());
        postsPanel.add(new PostComponent(110, im.getImgs(), "Orest", "otb2124", "Second Post", "2/4/2024", "10")
                .getComponent());
        postsPanel.add(new PostComponent(220, im.getImgs(), "Orest", "otb2124", "Third Post", "3/4/2024", "15")
                .getComponent());

        
    
        // Add postsPanel wrapped in JScrollPane to the center position
        JScrollPane postScrollPane = new JScrollPane(postsPanel);
        pfPage.add(postScrollPane, BorderLayout.CENTER);



        
        addPost(110, im.getImgs(), "Orest", "otb2124", "Fourth Post\n Good Job\nPlain Text..", "4/4/2024", "20");
        addPost(220, im.getImgs(), "Orest", "otb2124", "Fourth Post\n Good Job\nPlain Text..", "4/4/2024", "20");


    }



    private JPanel createProfile(int y, ImageIcon[] icons, String fullName, String userName, String location, String status){
        
        JPanel panel = new JPanel(new BorderLayout());

        JPanel icoPanel = new JPanel();
        icoPanel.add(new JLabel(icons[7]));
        panel.add(icoPanel, BorderLayout.WEST);

        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.add(new JLabel(fullName));
        infoPanel.add(new JLabel(userName));
        infoPanel.add(new JLabel(location));
        infoPanel.add(new JLabel(status));
        panel.add(infoPanel);

        panel.setPreferredSize(new Dimension(315, 110));

        return panel;
    }







    public void addPost(int y, ImageIcon[] icons, String fullName, String userName, String description, String date, String commentNumber){
        
        JScrollPane recentScrollPane = (JScrollPane) pfPage.getComponent(1);
        JPanel recentPanel = (JPanel) recentScrollPane.getViewport().getView();

        recentPanel.add(new PostComponent(y, icons, fullName, userName, description, date, commentNumber)
        .getComponent());

        recentScrollPane.setViewportView(recentPanel);

        pfPage.add(recentScrollPane);
        
    }

    @Override
    public JComponent getComponent() {
        return pfPage;
    }
}

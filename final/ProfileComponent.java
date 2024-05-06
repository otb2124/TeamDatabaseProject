import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class ProfileComponent implements Component {

    JPanel pfPage;
    ImageManager im;
    ActionListener al;

    public ProfileComponent(ImageManager im, ActionListener al, String[] credentials) {
        this.im = im;
        this.al = al;
    
        pfPage = new JPanel(new BorderLayout());
    
        // Create the profile panel and add it to pfPage
        JPanel profilePanel = createProfile(0, im.getImgs(), credentials[0], "RIT Croatia, Dubrovnik", credentials[1]);
        pfPage.add(profilePanel, BorderLayout.NORTH);
    
        JPanel postsPanel = new JPanel();
        postsPanel.setLayout(new BoxLayout(postsPanel, BoxLayout.Y_AXIS));
    
        // Add postsPanel wrapped in JScrollPane to the center position
        JScrollPane postScrollPane = new JScrollPane(postsPanel);
        pfPage.add(postScrollPane, BorderLayout.CENTER);
        
    }
    
    private JPanel createProfile(int y, ImageIcon[] icons, String fullName, String location, String status) {
        JPanel panel = new JPanel(new BorderLayout());
    
        JPanel icoPanel = new JPanel();
        icoPanel.add(new JLabel(icons[7]));
        panel.add(icoPanel, BorderLayout.WEST);
    
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.add(new JLabel(fullName));
        infoPanel.add(new JLabel(location));
        infoPanel.add(new JLabel(status));
    
        JButton addButton = new JButton("Add Post");
        addButton.addActionListener(al);

        if(status.equals("Professor")){
            infoPanel.add(addButton);
        }
        
    
        panel.add(infoPanel, BorderLayout.CENTER); // Corrected layout manager
        
                 
        panel.setPreferredSize(new Dimension(315, 110));
    
       

        return panel;
    }






    public void addPost(String fullName, String title, String description) {

        JScrollPane recentScrollPane = (JScrollPane) pfPage.getComponent(1);
        JPanel postspanel = (JPanel) recentScrollPane.getViewport().getView();

        int y = postspanel.getComponentCount() * 200;

        postspanel.add(new PostComponent(y, im.getImgs()[4], fullName, title, description).getComponent());
    
        recentScrollPane.setViewportView(postspanel);

        pfPage.add(recentScrollPane);
    }

    @Override
    public JComponent getComponent() {
        return pfPage;
    }





}

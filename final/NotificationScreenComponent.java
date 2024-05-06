
import java.awt.Dimension;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

public class NotificationScreenComponent implements Component{

    private JScrollPane notifPage;
    private ImageManager im;


    public NotificationScreenComponent(ImageManager im) {

        this.im = im;


        JPanel postsPanel = new JPanel();
        postsPanel.setLayout(null);
        postsPanel.setPreferredSize(new Dimension(315, 800));

        notifPage = new JScrollPane();
        notifPage.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        notifPage.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        notifPage.setViewportView(postsPanel);


        addPost(0, im.getImgs(), "Toni Njiric", "tn1999", "Well done great Project");
        addPost(60, im.getImgs(), "Toni Njiric", "tn1999", "Well done great Project");
        addPost(60, im.getImgs(), "Toni Njiric", "tn1999", "Well done great Project");
    }

    @Override
    public JComponent getComponent() {

        return notifPage;
    }


    public void addPost(int y, ImageIcon[] icons, String fullName, String userName, String description){
        
        JPanel postspanel = (JPanel) notifPage.getViewport().getView();

        postspanel.add(new NotificationComponent(y, icons, fullName, userName, description)
        .getComponent());

        notifPage.setViewportView(postspanel);
        
    }
        
    

}
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

public class HomeComponent implements Component{

    private JScrollPane homePage;
    private ImageManager im;


    public HomeComponent(ImageManager im) {

        this.im = im;


        JPanel postsPanel = new JPanel();
        postsPanel.setLayout(null);

        postsPanel.setPreferredSize(new Dimension(315, 800));

        homePage = new JScrollPane();
        homePage.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        homePage.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        homePage.setViewportView(postsPanel);

        addPost(0, im.getImgs(), "Orest", "otb2124", "Hello! \nBlahBlagBlahBlah\nBlaBla\nBlBlBlBlBlB", "1/4/2024", "5");

    }

    @Override
    public JComponent getComponent() {

        return homePage;
    }


    public void addPost(int y, ImageIcon[] icons, String fullName, String userName, String description, String date, String commentNumber){
        
        JPanel postspanel = (JPanel) homePage.getViewport().getView();

        postspanel.add(new PostComponent(y, icons, fullName, userName, description, date, commentNumber)
        .getComponent());

        homePage.setViewportView(postspanel);
        
    }

}
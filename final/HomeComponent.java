import java.awt.Dimension;
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

        postsPanel.setPreferredSize(new Dimension(315, 2000));

        homePage = new JScrollPane();
        homePage.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        homePage.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        homePage.setViewportView(postsPanel);

    }

    @Override
    public JComponent getComponent() {

        return homePage;
    }


    public void addPost(String fullName, String title, String description) {
        JPanel postspanel = (JPanel) homePage.getViewport().getView();

        int y = postspanel.getComponentCount() * 200;

        postspanel.add(new PostComponent(y, im.getImgs()[4], fullName, title, description).getComponent());
    
        homePage.setViewportView(postspanel);
    }

    

}
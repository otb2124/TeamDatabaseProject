import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;


public class SearchComponent implements Component{

    private JPanel searchPage;
    private ImageManager im;


    public SearchComponent(ImageManager im) {

        this.im = im;


        JPanel searchBarPanelLeft = new JPanel();
        searchBarPanelLeft.add(new JLabel(im.getImgs()[6]));

        RoundedCornerTextField txtf = new RoundedCornerTextField(10);
        txtf.setBorder(null);
        txtf.setBackground(new Color(220, 220, 220));


        JPanel searchBarPanel = new JPanel(new BorderLayout());
        searchBarPanel.add(searchBarPanelLeft, BorderLayout.WEST);

        JLabel recentLabel = new JLabel("Recent");
        recentLabel.setFont(im.createCustomFont(16, "Roboto-Bold.ttf"));

        searchBarPanel.add(recentLabel, BorderLayout.SOUTH);
        searchBarPanel.add(txtf);

        searchBarPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));

        JScrollPane recentScroll = new JScrollPane();
        recentScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        recentScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        JPanel recentPanel = new JPanel();
        recentPanel.setLayout(null);
        recentPanel.setPreferredSize(new Dimension(315, 550));

        recentScroll.setViewportView(recentPanel);

        searchPage = new JPanel(new BorderLayout());

        searchPage.add(searchBarPanel, BorderLayout.NORTH);
        searchPage.add(recentScroll, BorderLayout.SOUTH);


        addRecent(0, im.getImgs(), "Orest Brukhal", "otb2324");
        addRecent(60, im.getImgs(), "Orest Brukhal", "otb2324");
        addRecent(120, im.getImgs(), "Orest Brukhal", "otb2324");
        addRecent(180, im.getImgs(), "Orest Brukhal", "otb2324");

    }

    @Override
    public JComponent getComponent() {

        return searchPage;
    }


    public void addRecent(int y, ImageIcon[] icons, String fullName, String userName){
        
        JScrollPane recentScrollPane = (JScrollPane) searchPage.getComponent(1);
        JPanel recentPanel = (JPanel) recentScrollPane.getViewport().getView();

        recentPanel.add(new AccountComponent(y, icons, fullName, userName, im)
        .getComponent());

        recentScrollPane.setViewportView(recentPanel);

        searchPage.add(recentScrollPane);
        
    }

}
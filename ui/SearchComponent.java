import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;


public class SearchComponent implements Component{

    private JPanel searchPage;
    private ImageManager im;


    public SearchComponent(ImageManager im, ActionListener al) {

        this.im = im;


        JPanel searchBarPanelLeft = new JPanel();
        searchBarPanelLeft.add(new JLabel(im.getImgs()[6]));

        RoundedCornerTextField txtf = new RoundedCornerTextField(10);
        txtf.setBorder(null);
        txtf.setBackground(new Color(220, 220, 220));


        JPanel searchBarPanel = new JPanel(new BorderLayout());
        searchBarPanel.add(searchBarPanelLeft, BorderLayout.WEST);

        //JLabel recentLabel = new JLabel("Recent");
        //recentLabel.setFont(im.createCustomFont(16, "Roboto-Bold.ttf"));

        //searchBarPanel.add(recentLabel, BorderLayout.SOUTH);

        JButton searchButton = new JButton("Search");
        searchButton.addActionListener(al);
        searchButton.setPreferredSize(new Dimension(100, 30)); // Set fixed size for the login button
        searchBarPanel.add(searchButton, BorderLayout.SOUTH);
        
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

    }

    @Override
    public JComponent getComponent() {

        return searchPage;
    }



    public String getSearchBarString(){

        String textf = "null";

        JPanel searchBarPanel = (JPanel) searchPage.getComponent(0);
        textf = ((RoundedCornerTextField) searchBarPanel.getComponent(2)).getText();

        return textf;
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
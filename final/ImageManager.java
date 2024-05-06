import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.swing.ImageIcon;

public class ImageManager {
    
    private ImageIcon[] image = new ImageIcon[10];


    public ImageManager() {

        setImgs();
    }

    private void setImgs() {
        ImageIcon home = getScaledIcon("icon1.png", 30); 
        ImageIcon bell = getScaledIcon("bell.png",  30);
        ImageIcon profile = getScaledIcon("profile.png",  47);
        ImageIcon profileLesser = getScaledIcon("profile.png",  30);
        ImageIcon profileBigger = getScaledIcon("profile.png",  100);
        ImageIcon comment = getScaledIcon("comment.png",  20);
        ImageIcon search = getScaledIcon("search.png",  30);
        ImageIcon lesserSearch = getScaledIcon("search.png",  20);
        

        // Assign icons to the image array
        image[0] = home;
        image[1] = search;
        image[2] = bell;
        image[3] = profileLesser;
        image[4] = profile;
        image[5] = comment;
        image[6] = lesserSearch;
        image[7] = profileBigger;
    }

    public ImageIcon[] getImgs(){
        return image;
    }

    private ImageIcon getScaledIcon(String imagePath, int size) {
        ImageIcon originalIcon = new ImageIcon("res/" + imagePath);
        Image img = originalIcon.getImage();
        Image scaledImage = img.getScaledInstance(size, size, Image.SCALE_SMOOTH);
        return new ImageIcon(scaledImage);
    }


    public ImageIcon addPadding(ImageIcon icon, int padding) {
        Image image = icon.getImage();
        int w = image.getWidth(null) + padding * 2;
        int h = image.getHeight(null) + padding * 2;
        BufferedImage paddedImage = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = paddedImage.createGraphics();
        g2d.setColor(new Color(242,242,242,0)); // Set padding color
        g2d.fillRect(0, 0, w, h); // Fill background with padding color
        g2d.drawImage(image, padding, padding, null); // Draw original image with padding
        g2d.dispose();
        return new ImageIcon(paddedImage);
    }



    public Font createCustomFont(int size, String path) {
        Font robotoFont = null;
        try {
            // Load the Roboto font from the resources
            InputStream is = getClass().getResourceAsStream("res/" + path);
            robotoFont = Font.createFont(Font.TRUETYPE_FONT, is).deriveFont(Font.BOLD, size);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(robotoFont);
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
            // If loading font fails, use default font
            robotoFont = new Font("SansSerif", Font.BOLD, size);
        }
        return robotoFont;
    }
}

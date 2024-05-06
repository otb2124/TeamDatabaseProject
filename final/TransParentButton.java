import java.awt.*;
import javax.swing.*;

class TransParentButton extends JButton {
    public TransParentButton(String text) {
        super(text);
        setOpaque(false);
        setContentAreaFilled(false);
        setForeground(Color.BLUE);
    }

    @Override
    protected void paintComponent(Graphics g) {
        if (!isOpaque() && getBackground().getAlpha() < 255) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setColor(getBackground());
            g2.fillRect(0, 0, getWidth(), getHeight());
            g2.dispose();
        }
        super.paintComponent(g);
    }
}
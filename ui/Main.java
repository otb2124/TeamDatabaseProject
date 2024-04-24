import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import com.formdev.flatlaf.FlatLightLaf;

public class Main {
    public static JFrame window;
    public static SwingUI sg;

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
        } catch (Exception ex) {
            System.err.println("Failed to initialize theme. Using fallback.");
        }

        SwingUtilities.invokeLater(() -> {
            window = new JFrame("DatabaseConnectivityAndAccess Project | Skokic, Fernandez, Sinistaj, Brukhal");
            window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            window.setSize(340, 700);
            window.setResizable(false);

            sg = new SwingUI(window);
            window.setVisible(true);
        });
    }
}

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class SwingUI {

    private static JFrame window;
    private static JPanel loginPanel;
    private static JPanel signUpPanel;
    private static JTabbedPane tabPanel;
    private static ImageManager im;
    private static ActionListener al;
    private static UtilityManager uManager;

    public SwingUI(JFrame window) {

        SwingUI.window = window;

        im = new ImageManager();
        uManager = new UtilityManager();

        initializeUI();

    }

    private void initializeUI() {
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLayout(new BorderLayout());

        // Create the login panel
        addActionListener();
        createLogin();

        window.setLocationRelativeTo(null); 
        window.setVisible(true);
    }



    public static void createLogin() {

        loginPanel = (JPanel) new LoginComponent(im, al).getComponent();
        load(loginPanel);
        window.setSize(340, 125);
    }


    public static void createSignUp() {

        signUpPanel = (JPanel) new SignUpComponent(im, al).getComponent();
        load(signUpPanel);
        window.setSize(340, 175);
    }



    // Setting tabs after successful login/* */
    private static void createTabs() {
        tabPanel = new JTabbedPane(JTabbedPane.BOTTOM);

        tabPanel.addTab(null, im.addPadding(im.getImgs()[0], 11), new HomeComponent(im).getComponent());
        tabPanel.addTab(null, im.addPadding(im.getImgs()[1], 11), new SearchComponent(im).getComponent());
        tabPanel.addTab(null, im.addPadding(im.getImgs()[2], 11), new NotificationScreenComponent(im).getComponent());
        tabPanel.addTab(null, im.addPadding(im.getImgs()[3], 11), new ProfileComponent(im).getComponent());

        load(tabPanel);
        window.setSize(340, 700);
    }

    

    


    public static void addActionListener() {
        al = new ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent e) {
                JButton b = (JButton) e.getSource();

                // LOGIN BUTTON AT LOGIN WINDOW
                if (b.getText().equals("Login")) {


                    String username = UtilityManager.getTextFieldText(window.getContentPane(), "Username:");
                    String password = UtilityManager.getTextFieldText(window.getContentPane(), "Password:");

                    // Check login credentials
                    if (username.equals("example") && password.equals("password")) {
                        createTabs();
                    } else {
                        uManager.showErrorDialog("Invalid username or password", window);
                    }
                }

                // SIGNUP BUTTON AT LOGIN WINDOW
                if (b.getText().equals("Sign Up")) {
                    createSignUp();
                }

                // Back to Login BUTTON AT SIGNUP WINDOW
                if (b.getText().equals("Back to Login")) {
                    createLogin();
                }

                // SIGNUP BUTTON AT SIGNUP WINDOW
                if (b.getText().equals("Sign Up ")) {
                    createTabs();
                }

            }
        };
    }








    private static void load(JComponent component) {

        window.getContentPane().removeAll();
        window.getContentPane().add(component, BorderLayout.CENTER);
        window.revalidate();
        window.repaint();
    }

}

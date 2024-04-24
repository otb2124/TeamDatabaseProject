import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SwingUI {

    private JFrame window;
    private JPanel loginPanel;
    private JPanel signUpPanel;
    private JTabbedPane tabPanel;
    private ImageManager im;

    public SwingUI(JFrame window) {
        this.window = window;

        im = new ImageManager();

        initializeUI();
    }

    private void initializeUI() {
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLayout(new BorderLayout());

        // Create the login panel
        createLoginPanel();

        // Add login panel to the window initially
        window.add(loginPanel, BorderLayout.CENTER);

        window.pack();
        window.setLocationRelativeTo(null); // Center the window
        window.setVisible(true);
    }

    private void createLoginPanel() {
        loginPanel = new JPanel(new BorderLayout());
    
        // Add components to the login panel
        JLabel usernameLabel = new JLabel("Username:");
        JTextField usernameField = new JTextField(20);
        JLabel passwordLabel = new JLabel("Password:");
        JPasswordField passwordField = new JPasswordField(20);
        JButton loginButton = new JButton("Login");
        TransparentButton signUpButton = new TransparentButton("Sign Up"); // Add Sign Up button
    
        // Layout components
        JPanel formPanel = new JPanel(new GridLayout(2, 2, 5, 5));
        formPanel.add(usernameLabel);
        formPanel.add(usernameField);
        formPanel.add(passwordLabel);
        formPanel.add(passwordField);
    
        JPanel buttonPanel = new JPanel(); // Use FlowLayout for centering
        buttonPanel.add(signUpButton); // Add Sign Up button
        buttonPanel.add(loginButton); // Add Login button
    
        loginPanel.add(formPanel, BorderLayout.CENTER); // Add form panel to the center
        loginPanel.add(buttonPanel, BorderLayout.SOUTH); // Add button panel to the bottom
    
        // Add action listener to the login button
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get the entered username and password
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
    
                // Check login credentials
                if (username.equals("example") && password.equals("password")) {
                    // If login successful, switch to the main UI
                    initializeTabs();
                    window.getContentPane().remove(loginPanel);
                    window.getContentPane().add(tabPanel, BorderLayout.CENTER);
    
                    // Set the window size based on the preferred size of the tabbed pane
                    window.setSize(340, 700);
    
                    window.revalidate();
                    window.repaint();
                } else {
                    // If login unsuccessful, display error message
                    showErrorDialog("Invalid username or password");
                }
            }
        });
    
        // Add action listener to the sign-up button
        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Switch to the sign-up UI
                createSignUpPanel();
            }
        });
    }
    
    private void createSignUpPanel() {
        signUpPanel = new JPanel(new BorderLayout());
    
        // Add components to the sign-up panel
        JLabel usernameLabel = new JLabel("Username:");
        JTextField usernameField = new JTextField(20);
        JLabel passwordLabel = new JLabel("Password:");
        JPasswordField passwordField = new JPasswordField(20);
        JButton signUpButton = new JButton("Sign Up");
        TransparentButton backButton = new TransparentButton("Back to Login"); // Add Back to Login button
    
        // Layout components
        JPanel formPanel = new JPanel(new GridLayout(2, 2, 5, 5));
        formPanel.add(usernameLabel);
        formPanel.add(usernameField);
        formPanel.add(passwordLabel);
        formPanel.add(passwordField);
    
        JPanel buttonPanel = new JPanel(); // Use FlowLayout for centering
        buttonPanel.add(backButton, BorderLayout.WEST); // Add Back to Login button
        buttonPanel.add(signUpButton); // Add Sign Up button
    
        signUpPanel.add(formPanel, BorderLayout.CENTER); // Add form panel to the center
        signUpPanel.add(buttonPanel, BorderLayout.SOUTH); // Add button panel to the bottom
    
        // Add action listener to the sign-up button
        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get the entered username and passwor
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
    
                // Perform sign-up logic here
            }
        });
    
        // Add action listener to the back button
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Switch back to the login UI
                window.getContentPane().removeAll();
                createLoginPanel(); // Re-create the login panel
            }
        });
    
        // Set the sign-up panel as the current content pane
        window.getContentPane().removeAll();
        window.getContentPane().add(signUpPanel, BorderLayout.CENTER);
        window.revalidate();
        window.repaint();
    }
    
    









  

    // Setting tabs after successful login
    private void initializeTabs() {
        tabPanel = new JTabbedPane(JTabbedPane.BOTTOM);

        tabPanel.addTab(null, im.addPadding(im.getImgs()[0], 11), new HomeComponent(im).getComponent());
        tabPanel.addTab(null, im.addPadding(im.getImgs()[1], 11), new SearchComponent(im).getComponent());
        tabPanel.addTab(null, im.addPadding(im.getImgs()[2], 11), new NotificationScreenComponent(im).getComponent());
        tabPanel.addTab(null, im.addPadding(im.getImgs()[3], 11), new ProfileComponent(im).getComponent());
    }













    private void showErrorDialog(String message) {
        JFrame parentFrame = (JFrame) SwingUtilities.getRoot(window);
        JDialog errorDialog = new JDialog(parentFrame, "Error", true);
        JLabel errorMessageLabel = new JLabel(message);
        JButton okButton = new JButton("OK");

        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                errorDialog.dispose(); // Close the dialog when OK button is clicked
            }
        });

        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        contentPanel.add(errorMessageLabel, BorderLayout.CENTER);
        contentPanel.add(okButton, BorderLayout.SOUTH);

        errorDialog.setContentPane(contentPanel);
        errorDialog.pack();
        errorDialog.setLocationRelativeTo(parentFrame);
        errorDialog.setVisible(true);
    }

}

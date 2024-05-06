import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class LoginComponent implements Component {

    private JPanel loginPanel;
    private ImageManager im;

    private JTextField usernameField;
    private JPasswordField passwordField;

    public LoginComponent(ImageManager im, ActionListener listener) {

        loginPanel = new JPanel(new BorderLayout());

        // Add components to the login panel
        JLabel usernameLabel = new JLabel("Email:");
        usernameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        usernameLabel.setFont(im.createCustomFont(13, "Roboto-Bold.ttf"));
        usernameField = new JTextField(20);
        usernameField.setPreferredSize(new Dimension(200, 30)); // Set fixed size for the username field
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setHorizontalAlignment(SwingConstants.CENTER);
        passwordLabel.setFont(im.createCustomFont(13, "Roboto-Bold.ttf"));
        passwordField = new JPasswordField(20);
        passwordField.setPreferredSize(new Dimension(200, 30)); // Set fixed size for the password field
        JButton loginButton = new JButton("Login");
        loginButton.setPreferredSize(new Dimension(100, 30)); // Set fixed size for the login button
        TransParentButton signUpButton = new TransParentButton("Sign Up"); // Add Sign Up button
        signUpButton.setPreferredSize(new Dimension(100, 30)); // Set fixed size for the Sign Up button

        // Layout components
        JPanel formPanel = new JPanel(new GridLayout(2, 2, 5, 5));
        formPanel.add(usernameLabel);
        formPanel.add(usernameField);
        formPanel.add(passwordLabel);
        formPanel.add(passwordField);

        JPanel buttonPanel = new JPanel(); // Use FlowLayout for centering
        signUpButton.addActionListener(listener);
        loginButton.addActionListener(listener);
        buttonPanel.add(signUpButton); // Add Sign Up button
        buttonPanel.add(loginButton); // Add Login button

        loginPanel.add(formPanel, BorderLayout.CENTER); // Add form panel to the center
        loginPanel.add(buttonPanel, BorderLayout.SOUTH); // Add button panel to the bottom
    }

    @Override
    public JComponent getComponent() {
        return loginPanel;
    }

}

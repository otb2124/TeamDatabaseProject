import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class SignUpComponent implements Component {

    private JPanel signUpPanel;

    public SignUpComponent(ImageManager im, ActionListener listener) {
        signUpPanel = new JPanel();
        signUpPanel.setLayout(new BoxLayout(signUpPanel, BoxLayout.Y_AXIS)); // Use BoxLayout with Y_AXIS
        
        // Add components to the sign-up panel
        JLabel usernameLabel = new JLabel("Username:");
        JTextField usernameField = new JTextField(20);
        JLabel passwordLabel = new JLabel("Password:");
        JPasswordField passwordField = new JPasswordField(20);
        JLabel fullNameLabel = new JLabel("Full Name:");
        JTextField fullNameField = new JTextField(20);
        JButton signUpButton = new JButton("Sign Up ");
        TransparentButton backButton = new TransparentButton("Back to Login"); // Add Back to Login button

        // Layout components
        JPanel formPanel = new JPanel(new GridLayout(3, 2, 5, 5)); // Increased rows to accommodate full name field and claimer
        formPanel.add(usernameLabel);
        formPanel.add(usernameField);
        formPanel.add(passwordLabel);
        formPanel.add(passwordField);
        formPanel.add(fullNameLabel);
        formPanel.add(fullNameField);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        backButton.addActionListener(listener);
        buttonPanel.add(backButton); 
        signUpButton.addActionListener(listener);
        buttonPanel.add(signUpButton); 

        signUpPanel.add(formPanel);

        // Add radio buttons for the choice between "Student" and "Professor"
        JRadioButton studentButton = new JRadioButton("Student");
        JRadioButton professorButton = new JRadioButton("Professor");
        ButtonGroup choiceGroup = new ButtonGroup();
        choiceGroup.add(studentButton);
        choiceGroup.add(professorButton);

        // Layout the radio buttons
        JPanel choicePanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        choicePanel.add(studentButton);
        choicePanel.add(professorButton);
        signUpPanel.add(choicePanel); 

        signUpPanel.add(buttonPanel); 
    }

    @Override
    public JComponent getComponent() {
        return signUpPanel;
    }
}

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class SignUpComponent implements Component {

    private JPanel signUpPanel;


    private JTextField fnameField;
    private JTextField lnameField;
    private JTextField emailField;
    private JTextField passField;
    private JRadioButton studentButton;
    private JRadioButton professorButton;
    private JRadioButton publicButton;

    public SignUpComponent(ImageManager im, ActionListener listener) {
        signUpPanel = new JPanel();
        signUpPanel.setLayout(new BoxLayout(signUpPanel, BoxLayout.Y_AXIS)); // Use BoxLayout with Y_AXIS

        // Add components to the sign-up panel
        JLabel fnameLabel = new JLabel("First Name:");
        fnameField = new JTextField(20);
        JLabel lnameLabel = new JLabel("Last Name:");
        lnameField = new JTextField(20);
        JLabel emailLabel = new JLabel("Email:");
        emailField = new JTextField(20);
        JLabel passLabel = new JLabel("Password:");
        passField = new JPasswordField(20);
        JButton signUpButton = new JButton("Sign Up ");
        TransparentButton backButton = new TransparentButton("Back to Login"); // Add Back to Login button

        // Layout components
        JPanel formPanel = new JPanel(new GridLayout(4, 2, 5, 5)); // Increased rows to accommodate full name field and
                                                                   // claimer
        formPanel.add(fnameLabel);
        formPanel.add(fnameField);
        formPanel.add(lnameLabel);
        formPanel.add(lnameField);
        formPanel.add(emailLabel);
        formPanel.add(emailField);
        formPanel.add(passLabel);
        formPanel.add(passField);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        backButton.addActionListener(listener);
        buttonPanel.add(backButton);
        signUpButton.addActionListener(listener);
        buttonPanel.add(signUpButton);

        signUpPanel.add(formPanel);

        // Add radio buttons for the choice between "Student" and "Professor"
        studentButton = new JRadioButton("Student");
        professorButton = new JRadioButton("Professor");
        publicButton = new JRadioButton("Public");
        ButtonGroup choiceGroup = new ButtonGroup();
        choiceGroup.add(studentButton);
        choiceGroup.add(professorButton);
        choiceGroup.add(publicButton);

        // Layout the radio buttons
        JPanel choicePanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        choicePanel.add(studentButton);
        choicePanel.add(professorButton);
        choicePanel.add(publicButton);
        signUpPanel.add(choicePanel);

        signUpPanel.add(buttonPanel);

    }

    @Override
    public JComponent getComponent() {
        return signUpPanel;
    }



    public UserData getInputData() {
        String firstName = fnameField.getText();
        String lastName = lnameField.getText();
        String email = emailField.getText();
        String password = passField.getText();
        String choice = "";
        if (studentButton.isSelected()) {
            choice = studentButton.getText();
        } else if (professorButton.isSelected()) {
            choice = professorButton.getText();
        } else if (publicButton.isSelected()) {
            choice = publicButton.getText();
        }
        return new UserData(firstName, lastName, email, password, choice);
    }
}

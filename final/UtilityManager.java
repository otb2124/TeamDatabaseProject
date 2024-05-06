import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Component;

public class UtilityManager {
    
    private ActionListener al;


    public UtilityManager(ActionListener al) {
        this.al = al;
    }




    public static void showErrorDialog(String message, JFrame window) {
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



    public JDialog AddPost(JFrame window) {
    
        JDialog addPostDialog = new JDialog(window, "Add Post", true);
    
        // Components
        JLabel titleLabel = new JLabel("Title:");
        JTextField titleField = new JTextField(20);
        JLabel descriptionLabel = new JLabel("Description:");
        JTextArea descriptionArea = new JTextArea(5, 20);
        JButton addButton = new JButton("Add");
    
        // Button ActionListener
        addButton.addActionListener(al);
    
        // Layout
        JPanel contentPanel = new JPanel(new BorderLayout(10, 10));
        JPanel inputPanel = new JPanel(new GridLayout(2, 2, 10, 10));
    
        inputPanel.add(titleLabel);
        inputPanel.add(titleField);
        inputPanel.add(descriptionLabel);
        inputPanel.add(descriptionArea);
    
        contentPanel.add(inputPanel, BorderLayout.CENTER);
        contentPanel.add(addButton, BorderLayout.SOUTH);
        contentPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
         addPostDialog.pack();
        addPostDialog.setContentPane(contentPanel);
        //addPostDialog.setLocationRelativeTo(window);
        addPostDialog.setSize(300, 140);
        //addPostDialog.setVisible(true);

        return addPostDialog;
    }
    







    public static String getTextFieldText(Container container, String labelName) {

        JPanel panel = (JPanel) container.getComponents()[0];
        JPanel panel2 = (JPanel) panel.getComponents()[0];
        Component[] components = panel2.getComponents();

        for (int i = 0; i < components.length - 1; i++) {
            if (components[i] instanceof JLabel && components[i + 1] instanceof JTextField) {
                JLabel label = (JLabel) components[i];
                JTextField textField = (JTextField) components[i + 1];
                
                // Check if the label's text matches the target label name
                if (label.getText().equals(labelName)) {
                    return textField.getText();
                }
            }
        }

        // Return null if the label with the specified name is not found
        return null;
    }
}

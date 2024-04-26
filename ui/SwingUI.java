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

    private static HomeComponent homeComponent;
    private static SearchComponent searchComponent;
    private static ProfileComponent profileComponent;

    private static SignUpComponent signUpComponent;
    private static LoginComponent loginComponent;

    public SwingUI(JFrame window) {

        SwingUI.window = window;

        addActionListener();
        im = new ImageManager();

        initializeUI();

    }

    private void initializeUI() {
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLayout(new BorderLayout());

        createLogin();
        homeComponent = new HomeComponent(im);
        searchComponent = new SearchComponent(im, al);
        profileComponent = new ProfileComponent(im, al, new String[] {"null", "null"});

        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }

    public static void createLogin() {

        loginComponent = new LoginComponent(im, al);
        loginPanel = (JPanel) loginComponent.getComponent();
        load(loginPanel);
        window.setSize(340, 125);
    }

    public static void createSignUp() {

        signUpComponent = new SignUpComponent(im, al);
        signUpPanel = (JPanel) signUpComponent.getComponent();
        load(signUpPanel);
        window.setSize(340, 200);
    }

    private static void createTabs(int id) {

        tabPanel = new JTabbedPane(JTabbedPane.BOTTOM);

        tabPanel.addTab(null, im.addPadding(im.getImgs()[0], 11), homeComponent.getComponent());
        tabPanel.addTab(null, im.addPadding(im.getImgs()[1], 11), searchComponent.getComponent());
        // tabPanel.addTab(null, im.addPadding(im.getImgs()[2], 11), new
        // NotificationScreenComponent(im).getComponent());


        profileComponent = new ProfileComponent(im, al, new String[]{getFullName(), getUserStatus()});


        if (!getUserStatus().equals("Public")){
            tabPanel.addTab(null, im.addPadding(im.getImgs()[3], 11), profileComponent.getComponent());
        }
        

        tabPanel.setSelectedIndex(id);
        load(tabPanel);
        window.setSize(340, 700);
    }

    public static void addActionListener() {
        al = new ActionListener() {

            JDialog dialog;

            public void actionPerformed(java.awt.event.ActionEvent e) {
                JButton b = (JButton) e.getSource();

                if (b.getText().equals("Login")) {

                    String username = UtilityManager.getTextFieldText(window.getContentPane(), "Email:");
                    String password = UtilityManager.getTextFieldText(window.getContentPane(), "Password:");

                    // Check login credentials
                    if (username.equals("example") && password.equals("password")) {
                        createTabs(1);
                    } else {
                        new UtilityManager(al).showErrorDialog("Invalid username or password", window);
                    }
                }

                if (b.getText().equals("Sign Up ")) {

                    //SIGNUP DATA OBJECT
                    UserData sd = signUpComponent.getInputData();
                    createTabs(1);
                }

                if (b.getText().equals("Search")) {

                    //SEARCH STRING
                    searchComponent.getSearchBarString();


                    //ADD POST
                    homeComponent = new HomeComponent(im);
                    homeComponent.addPost("Orest", "Title2", "Blahbalhlablablalblalb\naddsadadad");
                    createTabs(0);
                }


                //ADD POST OBJECT
                if (b.getText().equals("Add")) {
                    profileComponent.addPost(getNewPostContent(dialog)[0], getNewPostContent(dialog)[1], getNewPostContent(dialog)[2]);
                }




                






                if (b.getText().equals("Back to Login")) {
                    createLogin();
                }

                if (b.getText().equals("Sign Up")) {
                    createSignUp();
                }

                if (b.getText().equals("Add Post")) {
                    dialog = new UtilityManager(al).AddPost(window);
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

    private static String[] getNewPostContent(JDialog dialog) {

        JPanel panel = (JPanel) dialog.getContentPane().getComponent(0);
        JTextField textField = (JTextField) panel.getComponent(1);
        JTextArea textArea = (JTextArea) panel.getComponent(3);

        return new String[] { "Orest", textField.getText(), textArea.getText() };
    }


    private static String getUserStatus(){


        String choice = "";

        if(signUpComponent == null){


            //get login data
        }
        else{
            choice = signUpComponent.getInputData().getChoice();
        }

        return choice;
        
    }


    private static String getFullName(){

        String name = "";

        if(signUpComponent == null){


            //get login data
        }
        else{
            name = signUpComponent.getInputData().getFirstName() + " " + signUpComponent.getInputData().getLastName();
        }

        return name;
        
    }

}

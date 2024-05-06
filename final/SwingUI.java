//package finalExam;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;


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
   public static UserData loginData;
   
   private static int counter = 0;
   private static JDialog dialog;
   
   public SwingUI(JFrame window) {
   
      SwingUI.window = window;
      try {
         addActionListener();
      } catch(Exception ex) {}
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

   public static void addActionListener() throws Exception{
      al = 
         new ActionListener() {
         
            
         
            public void actionPerformed(java.awt.event.ActionEvent e) {
               JButton b = (JButton) e.getSource();
            
               if (b.getText().equals("Login")) {
               
                  try {
                     String username = UtilityManager.getTextFieldText(window.getContentPane(), "Email:");
                     String password = UtilityManager.getTextFieldText(window.getContentPane(), "Password:");
                  
                     int choice = UserAuth.getRoleByEmail(username);
                     String fname = UserAuth.getfNameByEmail(username);
                     String lname = UserAuth.getlNameByEmail(username);
                  
                     loginData = new UserData();
                     loginData.firstName = fname;
                     loginData.lastName = lname;
                  
                     String choiceStr = null;
                     switch(choice) {
                        case 1: 
                           choiceStr = "Professor";
                           break;
                        case 2:
                           choiceStr = "Student";
                           break;
                        case 3:
                           choiceStr = "Public";
                           break;
                     }
                  
                     loginData.choice = choiceStr;
                  
                  // Check login credentials
                     if (UserAuth.login(username, password)) {
                        createTabs(1);
                     } else {
                        new UtilityManager(al).showErrorDialog("Invalid username or password", window);
                     }
                  } catch(Exception ex) {}
               }
            
               if (b.getText().equals("Sign Up ")) {
               
               //SIGNUP DATA OBJECT
               
                  if(signUpComponent.checkIfEmpty() == 0 || signUpComponent.checkIfChecked() == 0) 
                     return;
                	
                  UserData sd = signUpComponent.getInputData();
                  int choice = 0;
                  switch(sd.getChoice()) {
                     case "Professor": 
                        choice = 1;
                        break;
                     case "Student":
                        choice = 2;
                        break;
                     case "Public":
                        choice = 3;
                        break;
                  }
               
                  UserAuth.signup(sd.getFirstName(), sd.getLastName(), sd.getEmail(), sd.getPassword(), choice);
                  createTabs(1);
               }
            
               if (b.getText().equals("Search")) {
               
               //SEARCH STRING
                  String key = searchComponent.getSearchBarString();
               
               
                  List<PaperInfo> papers = UserAuth.searchByKeyword(key);
               //ADD POST
               
               
               
                  for (PaperInfo paper : papers) {
                     homeComponent = new HomeComponent(im);
                     homeComponent.addPost(paper.getAuthor(), paper.getTitle(), paper.getAbstractText());
                     createTabs(0);
                  
                  }
               
               }
            
            
            //ADD POST OBJECT
               if (b.getText().equals("Add")) {
              
                 int id = UserAuth.getFacultyIdByFName(getNewPostContent()[0]);
                  if(!UserAuth.insertPaper(id, getNewPostContent()[1], getNewPostContent()[2])){
                     UserAuth.updatePaper(id, getNewPostContent()[1], getNewPostContent()[2]);
                  }
             

                     //profileComponent.addPost(getNewPostContent()[0], getNewPostContent()[1], getNewPostContent()[2]);
                 
      
                     profileComponent.addPost(getNewPostContent()[0], getNewPostContent()[1], getNewPostContent()[2]);

           
               }
            
            
            
            
            
            
            
            
            
            
               if (b.getText().equals("Back to Login")) {
                  createLogin();
               }
            
               if (b.getText().equals("Sign Up")) {
                  createSignUp();
               }
            
               if (b.getText().equals("Add Post")) {
              
                  dialog = new UtilityManager(al).AddPost(window);
                  dialog.setLocationRelativeTo(window);
                  dialog.setVisible(true);

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

   private static String[] getNewPostContent() {
   
      try {
         JPanel panel = (JPanel) dialog.getContentPane().getComponent(0);
         JTextField textField = (JTextField) panel.getComponent(1);
         JTextArea textArea = (JTextArea) panel.getComponent(3);
         return new String[] { loginData.firstName, textField.getText(), textArea.getText() };
      
      } catch(Exception ex) {
         return null;}
   
   }

        
   private static String getUserStatus(){
   
   
      String choice = "";
   
      if(signUpComponent == null){
      
         
         choice = loginData.getChoice();        }
      else{
         choice = signUpComponent.getInputData().getChoice();
      }
   
      return choice;
      
   }


   private static String getFullName(){
   
      String name = "";
   
      if(signUpComponent == null){
      
      
         name = loginData.getFirstName() +" " + loginData.getLastName();
      }
      else{
         name = signUpComponent.getInputData().getFirstName() + " " + signUpComponent.getInputData().getLastName();
      }
   
      return name;
      
   }

}

import java.awt.Button;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LoginPage implements ActionListener {
  // set font
  Font fontGreeting = new Font("Arial", Font.BOLD, 34);
  Font subGreetingfont = new Font("Arial", Font.PLAIN, 14);
  Font fontTextfield = new Font("Arial", Font.PLAIN, 25);
  Font fontTextLabel = new Font("Arial", Font.PLAIN, 18);
  Font fontButton = new Font("Arial", Font.PLAIN, 18);
  Frame frame = new Frame("Login Page");
  Label greetingLabel = new Label("Login your account");
  Label subGreetingLabel = new Label(
    "Login your account using username and password"
  );
  Label errorMessage = new Label();
  Label userLabel = new Label("Username");
  Label passwordLabel = new Label("Password");
  TextField userField = new TextField(45);
  TextField passwordField = new TextField(45);
  Button loginButton = new Button("Login");
  Button signupButton = new Button("Sign up");

  private List<String> readUserFile() throws IOException {
    List<String> users = new ArrayList<>();

    BufferedReader reader = new BufferedReader(new FileReader("users.txt"));
    String line = reader.readLine();
    while (line != null) {
      users.add(line);
      line = reader.readLine();
    }
    reader.close();

    return users;
  }

  public LoginPage() {
    // add frame

    frame.setSize(550, 600);
    frame.setLayout(null);
    frame.setVisible(true);
    frame.setResizable(false);

    // add label
    greetingLabel.setBounds(70, 70, 500, 50);
    subGreetingLabel.setBounds(70, 120, 500, 70);
    userLabel.setBounds(70, 200, 120, 40);
    passwordLabel.setBounds(70, 300, 120, 40);

    // add textfield
    userField.setBounds(70, 240, 400, 40);
    passwordField.setBounds(70, 340, 400, 40);
    passwordField.setEchoChar('*');

    // add buttons
    loginButton.setBounds(70, 430, 400, 40);
    loginButton.setFocusable(false);
    loginButton.addActionListener(this);
    signupButton.setBounds(70, 490, 400, 40);
    signupButton.setFocusable(false);
    signupButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
    signupButton.addActionListener(this);

    // adjust fonts
    greetingLabel.setFont(fontGreeting);
    subGreetingLabel.setFont(subGreetingfont);

    userLabel.setFont(fontTextLabel);
    passwordLabel.setFont(fontTextLabel);

    userField.setFont(fontTextfield);
    passwordField.setFont(fontTextfield);

    loginButton.setFont(fontButton);
    signupButton.setFont(fontButton);
    loginButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

    loginButton.addActionListener(
      new ActionListener() {

        public void actionPerformed(ActionEvent e) {
          String username = userField.getText();
          String password = passwordField.getText();

          try {
            List<String> users = readUserFile();
            boolean found = false;
            for (String user : users) {
              //tokenize the user string
              String[] parts = user.split(",");
              //parts[0] is the username from the file
              //parts[1] is the password from the file

              //more likely the username and password are stored in the parts array
              //visualization
              //parts[] = {"username", "password"}
              if (username.equals(parts[0]) && password.equals(parts[1])) {
                found = true;
                break;
              }
            }

            if (found) {
              errorMessage.setForeground(Color.GREEN);
              errorMessage.setBounds(70, 170, 300, 40);
              errorMessage.setText("Logging in...");
              errorMessage.setFont(fontButton);
              System.out.println("Logging in...");
              try {
                Thread.sleep(2000);
                new HomePage(username);
              } catch (InterruptedException e1) {
                errorMessage.setText("Error Logging in");
                e1.printStackTrace();
              }
              frame.dispose();
            } else {
              errorMessage.setForeground(Color.RED);
              errorMessage.setBounds(70, 170, 300, 40);
              errorMessage.setText("Username or password incorrect");
              errorMessage.setFont(fontButton);
              System.out.println("Username or password incorrect");
            }
          } catch (IOException ex) {
            ex.printStackTrace();
          }
        }
      }
    );
    // add components
    frame.add(errorMessage);
    frame.add(greetingLabel);
    frame.add(subGreetingLabel);
    frame.add(userLabel);
    frame.add(userField);
    frame.add(passwordLabel);
    frame.add(passwordField);
    frame.add(loginButton);
    frame.add(signupButton);

    // add window closing function
    frame.addWindowListener(
      new WindowAdapter() {

        @Override
        public void windowClosing(WindowEvent e) {
          frame.dispose();
        }
      }
    );
    // add error success messages
    // Label errorMessage = new Label();

    // if(logininfo.containsKey(userID)){
    //    if(logininfo.get(userID).equals(password)){
    //       errorMessage.setForeground(Color.green);
    //       errorMessage.setText("Login successful");
    //       //call window page here
    //    }
    //    else{
    //       errorMessage.setForeground(Color.red);
    //       errorMessage.setText("Wrong password");
    //    }
    // }
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == signupButton) {
      signupPage signupPage = new signupPage();
      frame.dispose();
    }
  }
}

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
import java.io.IOException;

public class signupPage implements ActionListener {
  Frame frame = new Frame("Signup Page");
  Font fontGreeting = new Font("Arial", Font.BOLD, 34);
  Font subGreetingfont = new Font("Arial", Font.PLAIN, 14);
  Font fontTextfield = new Font("Arial", Font.PLAIN, 25);
  Font fontTextLabel = new Font("Arial", Font.PLAIN, 18);
  Font fontButton = new Font("Arial", Font.PLAIN, 18);
  Font errorFont = new Font("Arial", Font.BOLD, 20);
  Label greetingLabel = new Label("Create new account");
  Label subGreetingLabel = new Label(
    "Create your account using username and password"
  );
  Label userLabel = new Label("Username");
  Label passwordLabel = new Label("Password");
  Label confirmLabel = new Label("Confirm password");
  TextField userField = new TextField(45);
  TextField passwordField = new TextField(45);
  TextField confirmTextfield = new TextField();
  Label errorMessage = new Label();
  Button loginButton = new Button("I already have an account");
  Button signupButton = new Button("Create Account");

  public signupPage() {
    // add frame
    frame.setSize(550, 600);
    frame.setLayout(null);
    frame.setVisible(true);
    frame.setResizable(false);

    // add labels
    greetingLabel.setBounds(70, 40, 500, 50);
    subGreetingLabel.setBounds(70, 80, 500, 70);
    userLabel.setBounds(70, 150, 120, 40);
    passwordLabel.setBounds(70, 240, 120, 40);
    confirmLabel.setBounds(70, 320, 300, 40);

    // add textfield
    userField.setBounds(70, 200, 400, 40);
    passwordField.setBounds(70, 280, 400, 40);
    passwordField.setEchoChar('*');

    confirmTextfield.setBounds(70, 360, 400, 40);
    confirmTextfield.setEchoChar('*');

    // add buttons
    loginButton.setBounds(70, 430, 400, 40);
    loginButton.setFocusable(false);
    // loginButton.addActionListener(this);
    signupButton.setBounds(70, 490, 400, 40);
    signupButton.setFocusable(false);
    signupButton.addActionListener(this);

    // adjust fonts
    greetingLabel.setFont(fontGreeting);
    subGreetingLabel.setFont(subGreetingfont);

    userLabel.setFont(fontTextLabel);
    passwordLabel.setFont(fontTextLabel);
    confirmLabel.setFont(fontTextLabel);

    userField.setFont(fontTextfield);
    passwordField.setFont(fontTextfield);
    confirmTextfield.setFont(fontTextfield);

    loginButton.setFont(fontButton);
    loginButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

    loginButton.addActionListener(
      new ActionListener() {

        public void actionPerformed(ActionEvent e) {
          new LoginPage();
          frame.dispose();
        }
      }
    );

    signupButton.setFont(fontButton);
    signupButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

    signupButton.addActionListener(
      new ActionListener() {

        public void actionPerformed(ActionEvent e) {
          String username = userField.getText();
          String password = passwordField.getText();
          String confirmPassword = confirmTextfield.getText();

          if (!password.equals(confirmPassword)) {
            errorMessage.setForeground(Color.RED);
            errorMessage.setBounds(70, 120, 300, 40);
            errorMessage.setText("Incorrect password");
            errorMessage.setFont(fontButton);
            // errorMessage.setFont(fontButton);
            System.out.println("Password doesn't match!");
          } else {
            //use the modules registerUser method to register the user
            //if the user is registered successfully, show the login page
            //else show the error message
            RegisterUser new_user = new RegisterUserImpl(username, password);
            if (new_user.register()) {
              try {
                Thread.sleep(2000);
                new LoginPage();
                frame.dispose();
              } catch (InterruptedException e1) {
                e1.printStackTrace();
              }
            } else {
              errorMessage.setForeground(Color.RED);
              errorMessage.setBounds(70, 120, 300, 40);
              errorMessage.setText("Username already taken");
              errorMessage.setFont(fontButton);
            }
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
    frame.add(confirmLabel);
    frame.add(confirmTextfield);
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
    if (e.getSource() == loginButton) {
      LoginPage login = new LoginPage();
      frame.dispose();
    }
  }
}

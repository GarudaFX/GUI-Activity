import java.awt.Button;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Label;
import java.awt.event.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

public class HomePage {
  private static String username;

  Button logoutButton = new Button("Logout");
  Label errorMessage = new Label();
  Frame frame = new Frame("Home Page");

  public HomePage(String username) {
    // add frame
    HomePage.username = username;

    frame.setSize(550, 600);
    frame.setLayout(null);
    frame.setResizable(false);
    frame.setVisible(true);

    // set font
    Font fontGreeting = new Font("Arial", Font.BOLD, 34);
    Font subGreetingfont = new Font("Arial", Font.PLAIN, 14);
    Font fontButton = new Font("Arial", Font.PLAIN, 18);
    Font intro = new Font("Arial", Font.BOLD, 25);

    // add label
    Label greetingLabel = new Label("Welcome " + HomePage.username + "!");
    greetingLabel.setBounds(70, 70, 500, 50);
    //  Label name = new Label(HomePage.username);
    //  name.setBounds(120, 70, 500, 50);

    Label GroupName = new Label("Group Name: The F5");
    GroupName.setBounds(150, 140, 500, 50);

    Label Leader = new Label("Leader: ");
    Leader.setBounds(175, 200, 500, 20);

    Label LeaderName = new Label("Lenor James L. Jamero");
    LeaderName.setBounds(225, 220, 500, 20);

    Label Members = new Label("Members: ");
    Members.setBounds(175, 260, 500, 20);

    Label Member1 = new Label("Angelo A. Galope");
    Member1.setBounds(225, 280, 500, 20);

    Label Member2 = new Label("Patrick James Dionen");
    Member2.setBounds(225, 300, 500, 20);

    Label Member3 = new Label("Francis John Saul Tin-ao");
    Member3.setBounds(225, 320, 500, 20);

    Label Member4 = new Label("Peter Carmen");
    Member4.setBounds(225, 340, 500, 20);

    // add buttons

    logoutButton.setBounds(70, 500, 400, 40);

    // adjust fonts
    greetingLabel.setFont(fontGreeting);
    Leader.setFont(subGreetingfont);
    Members.setFont(subGreetingfont);
    GroupName.setFont(intro);

    logoutButton.setFont(fontButton);
    logoutButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

    logoutButton.addActionListener(
      new ActionListener() {

        public void actionPerformed(ActionEvent e) {
          try {
            errorMessage.setForeground(Color.GREEN);
            errorMessage.setBounds(70, 120, 300, 25);
            errorMessage.setText("Logging out...");
            errorMessage.setFont(fontButton);
            Thread.sleep(2000);
            new LoginPage();
            frame.dispose();
          } catch (InterruptedException e1) {
            e1.printStackTrace();
          }
        }
      }
    );

    // add components
    //  frame.add(name);
    frame.add(errorMessage);
    frame.add(greetingLabel);
    frame.add(GroupName);
    frame.add(Leader);
    frame.add(LeaderName);
    frame.add(Members);
    frame.add(Member1);
    frame.add(Member2);
    frame.add(Member3);
    frame.add(Member4);
    frame.add(logoutButton);

    // add window closing function
    frame.addWindowListener(
      new WindowAdapter() {

        @Override
        public void windowClosing(WindowEvent e) {
          frame.dispose();
        }
      }
    );
  }
  // public static void main(String[] args) {
}

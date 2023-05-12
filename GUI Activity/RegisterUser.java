import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

abstract class RegisterUser {
  private String username;
  private String password;

  public RegisterUser(String username, String password) {
    this.username = username;
    this.password = password;
  }

  public String getUsername() {
    return this.username;
  }

  public String getPassword() {
    return this.password;
  }

  public abstract boolean register();
}

class RegisterUserImpl extends RegisterUser {

  public RegisterUserImpl(String username, String password) {
    super(username, password);
  }

  @Override
  public boolean register() {
    // Read existing users from file, if any
    ArrayList<String> users = new ArrayList<>();
    try {
      File file = new File("users.txt");
      if (file.exists()) {
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
          String line = scanner.nextLine();
          users.add(line);
        }
        scanner.close();
      }
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }

    //Check if username already exists
    for (String user : users) {
      String[] parts = user.split(",");
      if (parts[0].equals(getUsername())) {
        System.out.println("Username already taken");
        return false;
      }
    }

    // Add new user to list
    users.add(getUsername() + "," + getPassword());

    // Write list back to file
    try {
      FileWriter writer = new FileWriter("users.txt");
      for (String user : users) {
        writer.write(user + System.lineSeparator());
      }
      writer.close();
    } catch (IOException e) {
      e.printStackTrace();
    }

    return true;
  }
}

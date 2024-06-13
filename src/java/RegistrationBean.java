import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.*;

@ManagedBean
@ViewScoped
public class RegistrationBean {
    private String username;
    private String email;
    private String password;
    private String confirmPassword;

    // Getters and setters for bean properties

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    // Method to handle registration form submission
   public String register() {
    // Add logic to validate registration data
    if (!password.equals(confirmPassword)) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Password and confirm password do not match", null));
        return null;
    }

    // Add logic to check if user with entered email already exists
    try {
        // Load MySQL JDBC driver
        Class.forName("com.mysql.jdbc.Driver");

        // Establish database connection
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/todo", "root", "");

        // Prepare SQL statement to check if user with entered email exists
        PreparedStatement checkStatement = connection.prepareStatement("SELECT * FROM user WHERE email = ?");
        checkStatement.setString(1, email);
        ResultSet resultSet = checkStatement.executeQuery();

        // If user with entered email already exists, display error message and return
        if (resultSet.next()) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "User with entered email already exists", null));
            resultSet.close();
            checkStatement.close();
            connection.close();
            return null;
        }

        // Close resources
        resultSet.close();
        checkStatement.close();

        // Prepare SQL statement to insert data into the user table
        PreparedStatement insertStatement = connection.prepareStatement("INSERT INTO user (uname, email, password) VALUES (?, ?, ?)");
        insertStatement.setString(1, username);
        insertStatement.setString(2, email);
        insertStatement.setString(3, password);

        // Execute the SQL statement to insert data
        insertStatement.executeUpdate();

        // Close resources
        insertStatement.close();
        connection.close();
    } catch (Exception e) {
        e.printStackTrace();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "An error occurred while registering. Please try again later.", null));
        return null;
    }

    // Redirect the user to the login page with success message
    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Registered successfully!", null));
    return "login.xhtml?faces-redirect=true";
}
}

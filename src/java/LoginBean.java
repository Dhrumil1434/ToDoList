import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@ManagedBean
@ViewScoped
public class LoginBean {
    private String username;
    private String password;

    // Getters and setters for bean properties

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // Method to handle login form submission
    public String login() {
        // Add logic to check if user exists with entered email
        try {
            // Load MySQL JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            // Establish database connection
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/todo", "root", "");

            // Prepare SQL statement to check if user with entered email exists
            PreparedStatement checkStatement = connection.prepareStatement("SELECT id FROM user WHERE email = ? AND password = ?");
            checkStatement.setString(1, username);
            checkStatement.setString(2, password);
            ResultSet resultSet = checkStatement.executeQuery();

            // If user with entered email exists and password is correct
           if (resultSet.next()) {
    // Retrieve user ID
    int userId = resultSet.getInt("id");

    // Set HTTP session with email
    HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
    session.setAttribute("uid", userId); // Set user ID as session attribute
    session.setAttribute("email", username); // Set the email as a session attribute

    // Close resources
    resultSet.close();
    checkStatement.close();
    connection.close();

    // Redirect to index.xhtml
    return "index.jsp";
}

            // Close resources
            resultSet.close();
            checkStatement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "An error occurred. Please try again later.", null));
            return null;
        }

        // If user doesn't exist or password is incorrect, display error message and stay on login page
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid username or password. Please try again.", null));
        return null;
    }
}

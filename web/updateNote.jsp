<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>

<%
    // Retrieve parameters from the request
    int noteId = Integer.parseInt(request.getParameter("noteId"));
    String newText = request.getParameter("newText");

    // Database connection details
    String url = "jdbc:mysql://localhost:3306/todo";
    String dbUsername = "root";
    String dbPassword = "";

    // Update note in the database
    try {
        // Load the MySQL JDBC driver
        Class.forName("com.mysql.jdbc.Driver");

        // Establish the connection to the database
        Connection connection = DriverManager.getConnection(url, dbUsername, dbPassword);

        // Prepare SQL statement to update the note text
        String sql = "UPDATE note SET text = ? WHERE nid = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, newText);
        statement.setInt(2, noteId);

        // Execute the update query
        int rowsAffected = statement.executeUpdate();

        // Close resources
        statement.close();
        connection.close();

        // Send response based on the success of the update operation
        if (rowsAffected > 0) {
            out.println("success");
        } else {
            out.println("error");
        }
    } catch (Exception e) {
        // Print stack trace for any exceptions
        e.printStackTrace();
        out.println("error");
    }
%>

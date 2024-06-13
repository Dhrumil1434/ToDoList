<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.sql.*" %>

<%
    // Get the note ID (nid) to delete from the request
    int noteId = Integer.parseInt(request.getParameter("noteId"));

    // Define database connection parameters
    String url = "jdbc:mysql://localhost:3306/todo";
    String dbUsername = "root";
    String dbPassword = "";

    // Define SQL query to delete the note
    String deleteQuery = "DELETE FROM note WHERE nid = ?";

    try {
        // Establish database connection
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection(url, dbUsername, dbPassword);

        // Prepare the delete statement
        PreparedStatement deleteStatement = connection.prepareStatement(deleteQuery);
        deleteStatement.setInt(1, noteId);

        // Execute the delete statement
        deleteStatement.executeUpdate();

        // Close resources
        deleteStatement.close();
        connection.close();

        // Send response indicating successful deletion
        out.println("Note deleted successfully!");
    } catch (Exception e) {
        // Handle any errors
        out.println("Error deleting note: " + e.getMessage());
    }
%>

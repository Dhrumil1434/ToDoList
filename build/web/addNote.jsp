<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>

<%
    // Retrieve note data from the request
    String note = request.getParameter("note");
    
    // Get user ID based on the email from session
    String email = (String) session.getAttribute("email");
    int userId = 0;
    try {
        Class.forName("com.mysql.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/todo";
        String dbUsername = "root";
        String dbPassword = "";
        Connection connection = DriverManager.getConnection(url, dbUsername, dbPassword);
        String sql = "SELECT id FROM user WHERE email = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, email);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            userId = resultSet.getInt("id");
        }
        resultSet.close();
        statement.close();
        
        // Insert the note into the database
        sql = "INSERT INTO note (uid, text) VALUES (?, ?)";
        statement = connection.prepareStatement(sql);
        statement.setInt(1, userId);
        statement.setString(2, note);
        statement.executeUpdate();
        
        statement.close();
        connection.close();
    } catch (Exception e) {
        e.printStackTrace();
    }
%>

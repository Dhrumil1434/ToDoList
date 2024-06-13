<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Todo List</title>
    <style>
        /* Styles remain unchanged */
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background-color: #f5f5f5;
            margin: 0;
            padding: 20px;
            color: #333;
        }

        .container {
            max-width: 800px;
            margin: 20px auto;
            padding: 20px;
            background-color: #fff;
            border-radius: 8px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
        }

        .header {
            text-align: center;
            margin-bottom: 20px;
        }

        .user-info {
            text-align: right;
            margin-bottom: 20px;
        }

        .note-form {
            margin-bottom: 20px;
        }

        .note-input {
            width: 100%;
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 4px;
            margin-bottom: 10px;
            box-sizing: border-box;
            font-size: 16px;
        }

        .add-note-btn {
            width: 100%;
            padding: 12px;
            background-color: #007bff;
            color: #fff;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            transition: background-color 0.3s ease;
            font-size: 16px;
        }

        .add-note-btn:hover {
            background-color: #0056b3;
        }

        .note-item {
            background-color: #f9f9f9;
            border-radius: 8px;
            margin-bottom: 15px;
            padding: 15px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
            transition: transform 0.5s ease;
        }

        .note-item.deleted {
            transform: translateX(-100%);
        }

        .note-text {
            font-size: 18px;
        }

        .note-table-container {
            max-height: 300px;
            overflow-y: auto;
        }

        .note-table {
            width: 100%;
            border-collapse: collapse;
            table-layout: fixed;
        }

        .note-table th,
        .note-table td {
            padding: 10px;
            border: 1px solid #ddd;
            text-align: left;
        }

        .logout-btn {
            background-color: #dc3545;
            color: #fff;
            border: none;
            border-radius: 4px;
            padding: 10px 20px;
            font-size: 16px;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }

        .logout-btn:hover {
            background-color: #c82333;
        }

        .username {
            margin-bottom: 10px;
            font-weight: bold;
            font-size: 18px;
        }

        .delete-btn {
            background-color: #dc3545;
            color: #fff;
            border: none;
            border-radius: 4px;
            padding: 8px 12px;
            font-size: 14px;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }

        .delete-btn:hover {
            background-color: #c82333;
        }

        .edit-note-input {
            width: calc(100% - 75px); /* Adjusted width to accommodate the button */
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 4px;
            margin-bottom: 10px;
            box-sizing: border-box;
            font-size: 16px;
        }

        .save-note-btn {
            width: 75px; /* Adjusted width for the button */
            padding: 12px;
            background-color: #28a745;
            color: #fff;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            transition: background-color 0.3s ease;
            font-size: 16px;
        }

        .save-note-btn:hover {
            background-color: #218838;
        }
    </style>
</head>
<body>
<div class="container">
    <div class="header">
        <h1>Simple Note</h1>
    </div>
    <% 
        String email = (String) session.getAttribute("email");
        if(email != null) {
    %>
        <div class="user-info">
            <%@page import="java.sql.*" %>
            <%@page import="java.util.*" %>
            <% 
                String username = "";
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    String url = "jdbc:mysql://localhost:3306/todo";
                    String dbUsername = "root";
                    String dbPassword = "";
                    Connection connection = DriverManager.getConnection(url, dbUsername, dbPassword);

                    PreparedStatement idStatement = connection.prepareStatement("SELECT uname FROM user WHERE email = ?");
                    idStatement.setString(1, email);
                    ResultSet idResultSet = idStatement.executeQuery();

                    if (idResultSet.next()) {
                        username = idResultSet.getString("uname");
                    }

                    idResultSet.close();
                    idStatement.close();
                    connection.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            %>
            <div class="username">
                Welcome, <%= username %>!
            </div>
            <form action="logout.jsp" method="post">
                <button type="submit" class="logout-btn">Logout</button>
            </form>
        </div>
        <div class="note-form">
            <h3>Add Note:</h3>
            <form id="noteForm">
                <input type="text" id="noteInput" class="note-input" placeholder="Enter your note...">
                <button type="button" class="add-note-btn" onclick="addNote()">Add Note</button>
            </form>
        </div>

        <div class="note-table-container">
            <%
                String userData = ""; // Placeholder for user data
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    String url = "jdbc:mysql://localhost:3306/todo";
                    String dbUsername = "root";
                    String dbPassword = "";
                    Connection connection = DriverManager.getConnection(url, dbUsername, dbPassword);

                    PreparedStatement idStatement = connection.prepareStatement("SELECT id FROM user WHERE email = ?");
                    idStatement.setString(1, email);
                    ResultSet idResultSet = idStatement.executeQuery();

                    if (idResultSet.next()) {
                        int userId = idResultSet.getInt("id");

                        PreparedStatement noteStatement = connection.prepareStatement("SELECT nid, text, time FROM note WHERE uid = ? ORDER BY time DESC");
                        noteStatement.setInt(1, userId);
                        ResultSet noteResultSet = noteStatement.executeQuery();

                        StringBuilder notes = new StringBuilder(); // Initialize StringBuilder for notes
                        while (noteResultSet.next()) {
                            int noteId = noteResultSet.getInt("nid");
                            String noteText = noteResultSet.getString("text");
                            Timestamp timestamp = noteResultSet.getTimestamp("time");

                            long currentTimeMillis = System.currentTimeMillis();
                            long noteTimeMillis = timestamp.getTime();
                            long timeDifferenceSeconds = (currentTimeMillis - noteTimeMillis) / 1000;
                            String timeDifferenceFormatted;

                            if (timeDifferenceSeconds < 60) {
                                timeDifferenceFormatted = timeDifferenceSeconds + " seconds ago";
                            } else if (timeDifferenceSeconds < 60 * 60) {
                                long minutes = timeDifferenceSeconds / 60;
                                timeDifferenceFormatted = minutes + " minutes ago";
                            } else if (timeDifferenceSeconds < 24 * 60 * 60) {
                                long hours = timeDifferenceSeconds / (60 * 60);
                                timeDifferenceFormatted = hours + " hours ago";
                            } else {
                                long days = timeDifferenceSeconds / (24 * 60 * 60);
                                timeDifferenceFormatted = days + " day ago";
                            }

                            notes.append("<tr class='note-item' data-note-id='").append(noteId).append("'><td class='note-text' ondblclick='editNoteText(this)'>").append(noteText).append("</td><td>").append(timeDifferenceFormatted).append("</td><td><button class='delete-btn' onclick='deleteNote(this, ").append(noteId).append(")'>Delete</button></td></tr>");
                        }

                        userData = notes.toString(); // Convert StringBuilder to string
                    }

                    idResultSet.close();
                    idStatement.close();
                    connection.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            %>
            <table class="note-table">
                <thead>
                <tr>
                    <th>Note</th>
                    <th>Time</th>
                    <th>Action</th>
                </tr>
                </thead>
                <tbody id="noteList">
                <%= userData %>
                </tbody>
            </table>
        </div>
    <% } else { %>
        <p>Please <a href="login.xhtml">login</a> to access this page.</p>
    <% } %>

    <script>
        // JavaScript code here
        function addNote() {
            var noteInput = document.getElementById("noteInput").value.trim();
            if (noteInput !== "") {
                var xhr = new XMLHttpRequest();
                xhr.open("POST", "addNote.jsp", true);
                xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
                xhr.onreadystatechange = function () {
                    if (xhr.readyState === 4 && xhr.status === 200) {
                        // Add the new note to the table dynamically
                        var noteList = document.getElementById("noteList");
                        var newRow = document.createElement("tr");
                        newRow.className = "note-item";
                        newRow.innerHTML = "<td class='note-text' ondblclick='editNoteText(this)'>" + noteInput + "</td><td>Just now</td><td><button class='delete-btn' onclick='deleteNote(this, " + xhr.responseText + ")'>Delete</button></td>";
                        noteList.insertBefore(newRow, noteList.firstChild);

                        // Update the time dynamically after a one-second delay
                        setTimeout(function() {
                            newRow.getElementsByTagName("td")[1].textContent = "1 second ago";
                        }, 1000);
                    }
                };
                xhr.send("note=" + encodeURIComponent(noteInput));
            }
        }

        function deleteNote(button, noteId) {
            if (confirm("Are you sure you want to delete this note?")) {
                var row = button.closest('.note-item');
                row.classList.add('deleted');
                setTimeout(function() {
                    row.remove();
                }, 500); // Animation duration in milliseconds
                var xhr = new XMLHttpRequest();
                xhr.open("POST", "deleteNote.jsp", true);
                xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
                xhr.onreadystatechange = function () {
                    if (xhr.readyState === 4 && xhr.status === 200) {
                        // No need to refresh the page after deleting note, it's already removed dynamically
                    }
                };
                xhr.send("noteId=" + encodeURIComponent(noteId));
            }
        }

        function editNoteText(cell) {
            var noteText = cell.textContent.trim();
            var input = document.createElement('input');
            input.type = 'text';
            input.value = noteText; // Set the input value to the current note text
            input.className = 'edit-note-input';
            cell.textContent = '';
            cell.appendChild(input);

            var saveBtn = document.createElement('button');
            saveBtn.textContent = 'Save';
            saveBtn.className = 'save-note-btn';
            cell.appendChild(saveBtn);

            input.focus();

            saveBtn.onclick = function () {
                var newText = input.value.trim();
                if (newText !== noteText) {
                    var noteId = cell.parentNode.dataset.noteId;
                    updateNoteText(noteId, newText);
                } else {
                    // Restore original text if unchanged
                    cell.textContent = noteText;
                }
            };
        }

        function updateNoteText(noteId, newText) {
            var xhr = new XMLHttpRequest();
            xhr.open('POST', 'updateNote.jsp', true);
            xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
            xhr.onreadystatechange = function () {
                if (xhr.readyState === 4 && xhr.status === 200) {
                    var response = xhr.responseText;
                    if (response === 'success') {
                        // Update the table cell with new text
                        var cell = document.querySelector('[data-note-id="' + noteId + '"] .note-text');
                        cell.textContent = newText;
                    } 
                }
            };
            xhr.send('noteId=' + encodeURIComponent(noteId) + '&newText=' + encodeURIComponent(newText));
        }

        document.addEventListener('DOMContentLoaded', function () {
            var noteItems = document.querySelectorAll('.note-item');
            noteItems.forEach(function (item) {
                var noteTextCell = item.querySelector('.note-text');
                noteTextCell.addEventListener('dblclick', function () {
                    editNoteText(noteTextCell);
                });
            });
        });
    </script>

</body>
</html>

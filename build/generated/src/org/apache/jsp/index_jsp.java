package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.sql.*;
import java.util.*;

public final class index_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html; charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("<head>\n");
      out.write("    <title>Todo List</title>\n");
      out.write("    <style>\n");
      out.write("        /* Styles remain unchanged */\n");
      out.write("        body {\n");
      out.write("            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;\n");
      out.write("            background-color: #f5f5f5;\n");
      out.write("            margin: 0;\n");
      out.write("            padding: 20px;\n");
      out.write("            color: #333;\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        .container {\n");
      out.write("            max-width: 800px;\n");
      out.write("            margin: 20px auto;\n");
      out.write("            padding: 20px;\n");
      out.write("            background-color: #fff;\n");
      out.write("            border-radius: 8px;\n");
      out.write("            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        .header {\n");
      out.write("            text-align: center;\n");
      out.write("            margin-bottom: 20px;\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        .user-info {\n");
      out.write("            text-align: right;\n");
      out.write("            margin-bottom: 20px;\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        .note-form {\n");
      out.write("            margin-bottom: 20px;\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        .note-input {\n");
      out.write("            width: 100%;\n");
      out.write("            padding: 10px;\n");
      out.write("            border: 1px solid #ccc;\n");
      out.write("            border-radius: 4px;\n");
      out.write("            margin-bottom: 10px;\n");
      out.write("            box-sizing: border-box;\n");
      out.write("            font-size: 16px;\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        .add-note-btn {\n");
      out.write("            width: 100%;\n");
      out.write("            padding: 12px;\n");
      out.write("            background-color: #007bff;\n");
      out.write("            color: #fff;\n");
      out.write("            border: none;\n");
      out.write("            border-radius: 4px;\n");
      out.write("            cursor: pointer;\n");
      out.write("            transition: background-color 0.3s ease;\n");
      out.write("            font-size: 16px;\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        .add-note-btn:hover {\n");
      out.write("            background-color: #0056b3;\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        .note-item {\n");
      out.write("            background-color: #f9f9f9;\n");
      out.write("            border-radius: 8px;\n");
      out.write("            margin-bottom: 15px;\n");
      out.write("            padding: 15px;\n");
      out.write("            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);\n");
      out.write("            transition: transform 0.5s ease;\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        .note-item.deleted {\n");
      out.write("            transform: translateX(-100%);\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        .note-text {\n");
      out.write("            font-size: 18px;\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        .note-table-container {\n");
      out.write("            max-height: 300px;\n");
      out.write("            overflow-y: auto;\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        .note-table {\n");
      out.write("            width: 100%;\n");
      out.write("            border-collapse: collapse;\n");
      out.write("            table-layout: fixed;\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        .note-table th,\n");
      out.write("        .note-table td {\n");
      out.write("            padding: 10px;\n");
      out.write("            border: 1px solid #ddd;\n");
      out.write("            text-align: left;\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        .logout-btn {\n");
      out.write("            background-color: #dc3545;\n");
      out.write("            color: #fff;\n");
      out.write("            border: none;\n");
      out.write("            border-radius: 4px;\n");
      out.write("            padding: 10px 20px;\n");
      out.write("            font-size: 16px;\n");
      out.write("            cursor: pointer;\n");
      out.write("            transition: background-color 0.3s ease;\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        .logout-btn:hover {\n");
      out.write("            background-color: #c82333;\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        .username {\n");
      out.write("            margin-bottom: 10px;\n");
      out.write("            font-weight: bold;\n");
      out.write("            font-size: 18px;\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        .delete-btn {\n");
      out.write("            background-color: #dc3545;\n");
      out.write("            color: #fff;\n");
      out.write("            border: none;\n");
      out.write("            border-radius: 4px;\n");
      out.write("            padding: 8px 12px;\n");
      out.write("            font-size: 14px;\n");
      out.write("            cursor: pointer;\n");
      out.write("            transition: background-color 0.3s ease;\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        .delete-btn:hover {\n");
      out.write("            background-color: #c82333;\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        .edit-note-input {\n");
      out.write("            width: calc(100% - 75px); /* Adjusted width to accommodate the button */\n");
      out.write("            padding: 10px;\n");
      out.write("            border: 1px solid #ccc;\n");
      out.write("            border-radius: 4px;\n");
      out.write("            margin-bottom: 10px;\n");
      out.write("            box-sizing: border-box;\n");
      out.write("            font-size: 16px;\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        .save-note-btn {\n");
      out.write("            width: 75px; /* Adjusted width for the button */\n");
      out.write("            padding: 12px;\n");
      out.write("            background-color: #28a745;\n");
      out.write("            color: #fff;\n");
      out.write("            border: none;\n");
      out.write("            border-radius: 4px;\n");
      out.write("            cursor: pointer;\n");
      out.write("            transition: background-color 0.3s ease;\n");
      out.write("            font-size: 16px;\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        .save-note-btn:hover {\n");
      out.write("            background-color: #218838;\n");
      out.write("        }\n");
      out.write("    </style>\n");
      out.write("</head>\n");
      out.write("<body>\n");
      out.write("<div class=\"container\">\n");
      out.write("    <div class=\"header\">\n");
      out.write("        <h1>Simple Note</h1>\n");
      out.write("    </div>\n");
      out.write("    ");
 
        String email = (String) session.getAttribute("email");
        if(email != null) {
    
      out.write("\n");
      out.write("        <div class=\"user-info\">\n");
      out.write("            \n");
      out.write("            \n");
      out.write("            ");
 
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
            
      out.write("\n");
      out.write("            <div class=\"username\">\n");
      out.write("                Welcome, ");
      out.print( username );
      out.write("!\n");
      out.write("            </div>\n");
      out.write("            <form action=\"logout.jsp\" method=\"post\">\n");
      out.write("                <button type=\"submit\" class=\"logout-btn\">Logout</button>\n");
      out.write("            </form>\n");
      out.write("        </div>\n");
      out.write("        <div class=\"note-form\">\n");
      out.write("            <h3>Add Note:</h3>\n");
      out.write("            <form id=\"noteForm\">\n");
      out.write("                <input type=\"text\" id=\"noteInput\" class=\"note-input\" placeholder=\"Enter your note...\">\n");
      out.write("                <button type=\"button\" class=\"add-note-btn\" onclick=\"addNote()\">Add Note</button>\n");
      out.write("            </form>\n");
      out.write("        </div>\n");
      out.write("\n");
      out.write("        <div class=\"note-table-container\">\n");
      out.write("            ");

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
            
      out.write("\n");
      out.write("            <table class=\"note-table\">\n");
      out.write("                <thead>\n");
      out.write("                <tr>\n");
      out.write("                    <th>Note</th>\n");
      out.write("                    <th>Time</th>\n");
      out.write("                    <th>Action</th>\n");
      out.write("                </tr>\n");
      out.write("                </thead>\n");
      out.write("                <tbody id=\"noteList\">\n");
      out.write("                ");
      out.print( userData );
      out.write("\n");
      out.write("                </tbody>\n");
      out.write("            </table>\n");
      out.write("        </div>\n");
      out.write("    ");
 } else { 
      out.write("\n");
      out.write("        <p>Please <a href=\"login.xhtml\">login</a> to access this page.</p>\n");
      out.write("    ");
 } 
      out.write("\n");
      out.write("\n");
      out.write("    <script>\n");
      out.write("        // JavaScript code here\n");
      out.write("        function addNote() {\n");
      out.write("            var noteInput = document.getElementById(\"noteInput\").value.trim();\n");
      out.write("            if (noteInput !== \"\") {\n");
      out.write("                var xhr = new XMLHttpRequest();\n");
      out.write("                xhr.open(\"POST\", \"addNote.jsp\", true);\n");
      out.write("                xhr.setRequestHeader(\"Content-Type\", \"application/x-www-form-urlencoded\");\n");
      out.write("                xhr.onreadystatechange = function () {\n");
      out.write("                    if (xhr.readyState === 4 && xhr.status === 200) {\n");
      out.write("                        // Add the new note to the table dynamically\n");
      out.write("                        var noteList = document.getElementById(\"noteList\");\n");
      out.write("                        var newRow = document.createElement(\"tr\");\n");
      out.write("                        newRow.className = \"note-item\";\n");
      out.write("                        newRow.innerHTML = \"<td class='note-text' ondblclick='editNoteText(this)'>\" + noteInput + \"</td><td>Just now</td><td><button class='delete-btn' onclick='deleteNote(this, \" + xhr.responseText + \")'>Delete</button></td>\";\n");
      out.write("                        noteList.insertBefore(newRow, noteList.firstChild);\n");
      out.write("\n");
      out.write("                        // Update the time dynamically after a one-second delay\n");
      out.write("                        setTimeout(function() {\n");
      out.write("                            newRow.getElementsByTagName(\"td\")[1].textContent = \"1 second ago\";\n");
      out.write("                        }, 1000);\n");
      out.write("                    }\n");
      out.write("                };\n");
      out.write("                xhr.send(\"note=\" + encodeURIComponent(noteInput));\n");
      out.write("            }\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        function deleteNote(button, noteId) {\n");
      out.write("            if (confirm(\"Are you sure you want to delete this note?\")) {\n");
      out.write("                var row = button.closest('.note-item');\n");
      out.write("                row.classList.add('deleted');\n");
      out.write("                setTimeout(function() {\n");
      out.write("                    row.remove();\n");
      out.write("                }, 500); // Animation duration in milliseconds\n");
      out.write("                var xhr = new XMLHttpRequest();\n");
      out.write("                xhr.open(\"POST\", \"deleteNote.jsp\", true);\n");
      out.write("                xhr.setRequestHeader(\"Content-Type\", \"application/x-www-form-urlencoded\");\n");
      out.write("                xhr.onreadystatechange = function () {\n");
      out.write("                    if (xhr.readyState === 4 && xhr.status === 200) {\n");
      out.write("                        // No need to refresh the page after deleting note, it's already removed dynamically\n");
      out.write("                    }\n");
      out.write("                };\n");
      out.write("                xhr.send(\"noteId=\" + encodeURIComponent(noteId));\n");
      out.write("            }\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        function editNoteText(cell) {\n");
      out.write("            var noteText = cell.textContent.trim();\n");
      out.write("            var input = document.createElement('input');\n");
      out.write("            input.type = 'text';\n");
      out.write("            input.value = noteText; // Set the input value to the current note text\n");
      out.write("            input.className = 'edit-note-input';\n");
      out.write("            cell.textContent = '';\n");
      out.write("            cell.appendChild(input);\n");
      out.write("\n");
      out.write("            var saveBtn = document.createElement('button');\n");
      out.write("            saveBtn.textContent = 'Save';\n");
      out.write("            saveBtn.className = 'save-note-btn';\n");
      out.write("            cell.appendChild(saveBtn);\n");
      out.write("\n");
      out.write("            input.focus();\n");
      out.write("\n");
      out.write("            saveBtn.onclick = function () {\n");
      out.write("                var newText = input.value.trim();\n");
      out.write("                if (newText !== noteText) {\n");
      out.write("                    var noteId = cell.parentNode.dataset.noteId;\n");
      out.write("                    updateNoteText(noteId, newText);\n");
      out.write("                } else {\n");
      out.write("                    // Restore original text if unchanged\n");
      out.write("                    cell.textContent = noteText;\n");
      out.write("                }\n");
      out.write("            };\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        function updateNoteText(noteId, newText) {\n");
      out.write("            var xhr = new XMLHttpRequest();\n");
      out.write("            xhr.open('POST', 'updateNote.jsp', true);\n");
      out.write("            xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');\n");
      out.write("            xhr.onreadystatechange = function () {\n");
      out.write("                if (xhr.readyState === 4 && xhr.status === 200) {\n");
      out.write("                    var response = xhr.responseText;\n");
      out.write("                    if (response === 'success') {\n");
      out.write("                        // Update the table cell with new text\n");
      out.write("                        var cell = document.querySelector('[data-note-id=\"' + noteId + '\"] .note-text');\n");
      out.write("                        cell.textContent = newText;\n");
      out.write("                    } \n");
      out.write("                }\n");
      out.write("            };\n");
      out.write("            xhr.send('noteId=' + encodeURIComponent(noteId) + '&newText=' + encodeURIComponent(newText));\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        document.addEventListener('DOMContentLoaded', function () {\n");
      out.write("            var noteItems = document.querySelectorAll('.note-item');\n");
      out.write("            noteItems.forEach(function (item) {\n");
      out.write("                var noteTextCell = item.querySelector('.note-text');\n");
      out.write("                noteTextCell.addEventListener('dblclick', function () {\n");
      out.write("                    editNoteText(noteTextCell);\n");
      out.write("                });\n");
      out.write("            });\n");
      out.write("        });\n");
      out.write("    </script>\n");
      out.write("\n");
      out.write("</body>\n");
      out.write("</html>\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}

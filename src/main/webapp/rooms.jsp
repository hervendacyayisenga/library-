<%@ page import="java.util.List" %>
<%@ page import="model.Room" %>  

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Room List</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 20px;
        }
        .container {
            max-width: 800px;
            margin: auto;
            background: #fff;
            padding: 20px;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        h2, h3 {
            text-align: center;
            color: #333;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-bottom: 20px;
        }
        table, th, td {
            border: 1px solid #ddd;
        }
        th, td {
            padding: 8px;
            text-align: left;
        }
        th {
            background-color: #0066cc;
            color: white;
        }
        .btn {
            padding: 5px 10px;
            color: white;
            background-color: #0066cc;
            border: none;
            border-radius: 5px;
            text-decoration: none;
            cursor: pointer;
        }
        .btn:hover {
            background-color: #5a67d8;
        }
        .form-group {
            margin-bottom: 15px;
        }
        input[type="text"] {
            width: calc(100% - 20px);
            padding: 10px;
            border: 1px solid #ddd;
            border-radius: 5px;
        }
        input[type="submit"] {
            background-color: #0066cc;
            color: white;
            border: none;
            padding: 10px 15px;
            border-radius: 5px;
            cursor: pointer;
            width: 100%;
        }
        input[type="submit"]:hover {
            background-color: #5a67d8;
        }
        .edit-form {
            margin-bottom: 20px;
        }
    </style>
</head>
<body>
<div class="container">
    <h2>Room List</h2>

    <!-- Display Rooms in a Table -->
    <table>
        <thead>
            <tr>
                <th>Room ID</th>
                <th>Room Name</th>
                <th>Room Code</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
            <% 
                List<Room> rooms = (List<Room>) request.getAttribute("rooms");
                if (rooms != null && !rooms.isEmpty()) {
                    for (Room room : rooms) {
            %>
                        <tr>
                            <td><%= room.getRoomId() %></td>
                            <td><%= room.getRoomName() %></td>
                            <td><%= room.getRoomCode() %></td>
                            <td>
                                <form action="Rooms" method="post" style="display:inline;">
                                    <input type="hidden" name="action" value="delete">
                                    <input type="hidden" name="id" value="<%= room.getRoomId() %>">
                                    <input type="submit" value="Delete" class="btn" onclick="return confirm('Are you sure you want to delete this room?');">
                                </form>
                                <button class="btn" onclick="document.getElementById('edit-form-<%= room.getRoomId() %>').style.display='block';">Edit</button>
                            </td>
                        </tr>
                        <!-- Edit Room Form (Hidden initially) -->
                        <tr id="edit-form-<%= room.getRoomId() %>" class="edit-form" style="display: none;">
                            <td colspan="4">
                                <form action="Rooms" method="post">
                                    <input type="hidden" name="action" value="update">
                                    <input type="hidden" name="id" value="<%= room.getRoomId() %>">
                                    <div class="form-group">
                                        <label for="roomName">Room Name:</label>
                                        <input type="text" name="roomName" value="<%= room.getRoomName() %>" required>
                                    </div>
                                    <div class="form-group">
                                        <label for="roomCode">Room Code:</label>
                                        <input type="text" name="roomCode" value="<%= room.getRoomCode() %>" required>
                                    </div>
                                    <input type="submit" value="Update Room">
                                </form>
                            </td>
                        </tr>
            <% 
                    }
                } else { 
            %>
                <tr><td colspan="4">No rooms available.</td></tr>
            <% 
                } 
            %>
        </tbody>
    </table>

    <!-- Add New Room Form -->
    <h3>Add New Room</h3>
    <form action="Rooms" method="post">
        <input type="hidden" name="action" value="add">
        <div class="form-group">
            <label for="roomName">Room Name:</label>
            <input type="text" id="roomName" name="roomName" required>
        </div>
        <div class="form-group">
            <label for="roomCode">Room Code:</label>
            <input type="text" id="roomCode" name="roomCode" required>
        </div>
        <input type="submit" value="Add Room">
    </form>
</div>
</body>
</html>

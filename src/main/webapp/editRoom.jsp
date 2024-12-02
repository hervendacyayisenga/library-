<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit Room</title>
</head>
<body>
<h2>Edit Room</h2>
<form action="Rooms" method="post">
    <input type="hidden" name="action" value="update">
    <input type="hidden" name="id" value="${room.roomId}">
    <label for="roomName">Room Name:</label>
    <input type="text" name="roomName" value="${room.roomName}">
    <label for="roomCode">Room Code:</label>
    <input type="text" name="roomCode" value="${room.roomCode}">
    <input type="submit" value="Update Room">
</form>
<a href="Rooms">Back to Room List</a>
</body>
</html>

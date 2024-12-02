package ServletView;

import dao.RoomDAO;
import model.Room;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/Rooms")
public class RoomServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private RoomDAO roomDAO;

    @Override
    public void init() {
        roomDAO = new RoomDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action == null) {
            // Retrieve all rooms
            List<Room> rooms = roomDAO.findAll();
            request.setAttribute("rooms", rooms);
            request.getRequestDispatcher("/rooms.jsp").forward(request, response);
        } else if ("edit".equals(action)) {
            // Edit room
            Long roomId = Long.valueOf(request.getParameter("id"));
            Room room = roomDAO.findById(roomId);
            request.setAttribute("room", room);
            request.getRequestDispatcher("/editRoom.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Action parameter is missing.");
            return;
        }

        switch (action) {
            case "add":
                // Add new room
                String roomName = request.getParameter("roomName");
                String roomCode = request.getParameter("roomCode");
                Room room = new Room();
                room.setRoomName(roomName);
                room.setRoomCode(roomCode);
                roomDAO.save(room);
                response.sendRedirect("Rooms"); // Redirect to list of rooms
                break;

            case "update":
                // Update room
                Long roomId = Long.valueOf(request.getParameter("id")); 
                roomName = request.getParameter("roomName");
                roomCode = request.getParameter("roomCode");
                room = new Room();
                room.setRoomId(roomId); // Set the room ID here
                room.setRoomName(roomName);
                room.setRoomCode(roomCode);
                roomDAO.update(room);
                response.sendRedirect("Rooms"); // Redirect to list of rooms
                break;

            case "delete":
                // Delete room
                roomId = Long.valueOf(request.getParameter("id"));
                roomDAO.delete(roomId);
                response.sendRedirect("Rooms"); // Redirect to list of rooms
                break;

            default:
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Unknown action: " + action);
                break;
        }
    }
}

package ServletView;

import dao.ShelfDAO;
import model.Shelf;
import model.Room;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/Submit-shelf")
public class ShelfServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ShelfDAO shelfDAO;

    @Override
    public void init() {
        shelfDAO = new ShelfDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Retrieve form data
            int availableStock = Integer.parseInt(request.getParameter("availableStock"));
            String bookCategory = request.getParameter("bookCategory");
            int borrowedNumber = Integer.parseInt(request.getParameter("borrowedNumber"));
            int initialStock = Integer.parseInt(request.getParameter("initialStock"));
            Long roomId = Long.parseLong(request.getParameter("room"));

            // Lookup Room entity if needed
            Room room = new Room(); // Assuming Room is an entity and managed by ShelfDAO or set externally
            room.setRoomId(roomId);

            // Create new Shelf entity
            Shelf shelf = new Shelf();
            shelf.setAvailableStock(availableStock);
            shelf.setBookCategory(bookCategory);
            shelf.setBorrowedNumber(borrowedNumber);
            shelf.setInitialStock(initialStock);
            shelf.setRoom(room);

            // Use ShelfDAO to save the Shelf entity
            shelfDAO.save(shelf);

            // Redirect to a success page
            response.sendRedirect("Location_success.html");

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("error.html");
        }
    }
}

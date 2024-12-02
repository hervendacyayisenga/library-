package ServletView;

import dao.LocationDAO;
import model.Location;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/locationServlet")
public class LocationServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private LocationDAO locationDAO;

    @Override
    public void init() throws ServletException {
        locationDAO = new LocationDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve form data
        String locationCode = request.getParameter("locationCode");
        String locationName = request.getParameter("locationName");
        String province = request.getParameter("province");
        String district = request.getParameter("district");
        String sector = request.getParameter("sector");
        String cell = request.getParameter("cell");
        String village = request.getParameter("village");

        // Check if locationCode already exists
        Location existingLocation = locationDAO.findByLocationCode(locationCode);
        if (existingLocation != null) {
            // Notify user that location already exists
            String message = "Error: Location with this code already exists.";
            response.setContentType("text/html");
            response.getWriter().write("<html><body>");
            response.getWriter().write("<h3>" + message + "</h3>");
            response.getWriter().write("<a href='locationForm.html'>Back to Form</a>");
            response.getWriter().write("</body></html>");
            return;
        }

        // Create Location object and set attributes
        Location location = new Location();
        location.setLocationCode(locationCode);
        location.setLocationName(locationName);
        location.setProvince(province);
        location.setDistrict(district);
        location.setSector(sector);
        location.setCell(cell);
        location.setVillage(village);

        // Save location and prepare response message
        String message;
        try {
            locationDAO.save(location);
            message = "Location registered successfully.";
            response.sendRedirect("Location_success.html"); // Redirect on success
        } catch (Exception e) {
            e.printStackTrace();
            message = "Error: Could not register location. Please try again.";
            response.setContentType("text/html");
            response.getWriter().write("<html><body>");
            response.getWriter().write("<h3>" + message + "</h3>");
            response.getWriter().write("<a href='locationForm.html'>Back to Form</a>");
            response.getWriter().write("</body></html>");
        }
    }
}

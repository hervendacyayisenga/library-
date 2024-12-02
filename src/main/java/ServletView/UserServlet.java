package ServletView;

import dao.UserDAO;
import model.User;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/personUserServlet")
public class UserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserDAO personUserDAO;

    @Override
    public void init() throws ServletException {
        personUserDAO = new UserDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve form data
        String userId = request.getParameter("userId");
        String firstname = request.getParameter("firstname");
        String lastname = request.getParameter("lastname");
        String gender = request.getParameter("gender");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String role = request.getParameter("role");
        String phoneNumber = request.getParameter("phoneNumber");

        // Create a new PersonUser object and set its attributes
        User personUser = new User();
        personUser.setUserId(userId);
        personUser.setFirstname(firstname);
        personUser.setLastname(lastname);
        personUser.setGender(gender);
        personUser.setUsername(username);
        personUser.setPassword(password);  // Ensure you hash the password in the DAO or service layer
        personUser.setRole(role);
        personUser.setPhoneNumber(phoneNumber);

        // Attempt to save the new PersonUser
        try {
            personUserDAO.save(personUser);
            response.sendRedirect("MainLogin.html"); // Redirect on success
        } catch (Exception e) {
            e.printStackTrace(); // Log the error
            
            // Send an error response with the message
            response.setContentType("text/html");
            response.getWriter().write("<html><body>");
            response.getWriter().write("<h3>Data could not be saved. Please try again.</h3>");
            response.getWriter().write("<a href='personUserForm.html'>Back to Form</a>");
            response.getWriter().write("</body></html>");
        }
    }
}

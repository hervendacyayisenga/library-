
package ServletView;

import dao.LoginDAO;
import dao.UserDAO;
import model.User;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private LoginDAO loginDAO;

    @Override
    public void init() {
        loginDAO = new LoginDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve login parameters from the request
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String role = request.getParameter("role");
        

        // Validate the role input
        if (!isValidRole(role)) {
            request.setAttribute("errorMessage", "Invalid role selected.");
            request.getRequestDispatcher("MainLogin.html").forward(request, response);
            return;
        }
        User user;
        UserDAO dao = new UserDAO();
        user = dao.findByUsername(username);
        String hashedPassword = dao.hashPassword(password);
        // Authenticate user

        if (user != null) {
            // If authentication is successful, set session attributes and redirect based on role
            if(user.getPassword().equals(hashedPassword)) {
            	HttpSession session = request.getSession();
            session.setAttribute("user", user);
            session.setAttribute("role", user.getRole());

            switch (role) {
                case "STUDENT":
                    response.sendRedirect("HomePage.jsp");
                    break;
                case "MANAGER":
                    response.sendRedirect("LibrarianHomePage.jsp");
                    break;
                case "TEACHER":
                    response.sendRedirect("HomePage.jsp");
                    break;
                case "DEAN":
                    response.sendRedirect("Student_dashboard.html");
                    break;
                case "HOD":
                    response.sendRedirect("HomePage.jsp");
                    break;
                case "LIBRARIAN":
                    response.sendRedirect("LibrarianHomePage.jsp");
                    break;
                default:
                    response.sendRedirect("MainLogin.html");  // General home page for any other roles
                    break;
            }
        	
        	
            }
        } else {
            // If authentication fails, forward to login with error message
            request.setAttribute("errorMessage", "Invalid username, password, or role.");
            request.getRequestDispatcher("MainLogin.html").forward(request, response);
        }
    }

    private boolean isValidRole(String role) {
        return role.equals("STUDENT") || role.equals("MANAGER") || role.equals("TEACHER") || 
               role.equals("DEAN") || role.equals("HOD") || role.equals("LIBRARIAN");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Redirect to login page on GET request
        response.sendRedirect("MainLogin.html");
    }
}

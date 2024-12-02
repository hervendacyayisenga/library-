package ServletView;

import dao.BorrowerDAO;
import model.Borrower;
import model.User;
import model.Book;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/BorrowerServlet")
public class BorrowerServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private BorrowerDAO borrowerDAO;

    @Override
    public void init() throws ServletException {
        borrowerDAO = new BorrowerDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve form data
        String dueDate = request.getParameter("dueDate");
        String fineAmount = request.getParameter("fineAmount");
        String borrowDate = request.getParameter("borrowDate");
        String returnDate = request.getParameter("returnDate");

        // Create a new Borrower object
        Borrower borrower = new Borrower();
        borrower.setDue_date(dueDate);
        borrower.setFine_amount(fineAmount);
        borrower.setBorrow_date(borrowDate);
        borrower.setReturn_date(returnDate);

        // Optionally set the user and book if provided
        String userIdParam = request.getParameter("userId");
        String bookIdParam = request.getParameter("bookId");
        
        if (userIdParam != null && !userIdParam.isEmpty()) {
            User user = new User();
            user.setPersonId(Long.parseLong(userIdParam)); // Assuming User has this method
            borrower.setPersonUser(user);
        }
        
        if (bookIdParam != null && !bookIdParam.isEmpty()) {
            Book book = new Book();
            book.setBookId(Long.parseLong(bookIdParam)); // Assuming Book has this method
            borrower.setBook(book);
        }

        // Attempt to save the new Borrower and provide feedback
        String message;
        try {
            borrowerDAO.saveBorrower(borrower);
            message = "Borrower registered successfully.";
            response.sendRedirect(" BorrowerSuccess.html"); // Redirect to a success page
        } catch (Exception e) {
            e.printStackTrace();
            message = "Registration failed. Please try again.";
            
            // Sending an error message response
            response.setContentType("text/html");
            response.getWriter().write("<html><body>");
            response.getWriter().write("<h3>" + message + "</h3>");
            response.getWriter().write("<a href='borrowerRegistration.html'>Back to Form</a>");
            response.getWriter().write("</body></html>");
        }
    }
}
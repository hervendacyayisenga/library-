package ServletView;

import model.Book;
import model.BookStatus;
import model.Shelf;
import dao.BookDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/Submit-book")
public class BookServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private BookDAO bookDAO;

    @Override
    public void init() {
        bookDAO = new BookDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Book> books = bookDAO.findAll();
        request.setAttribute("books", books);
        request.getRequestDispatcher("/books.jsp").forward(request, response);
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
                handleAddBook(request);
                break;

            case "update":
                updateBook(request);
                break;

            case "delete":
                Long bookId = Long.valueOf(request.getParameter("id"));
                bookDAO.delete(bookId);
                break;

            default:
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid action.");
                return;
        }

        // Refresh the book list and forward to books.jsp
        List<Book> books = bookDAO.findAll();
        request.setAttribute("books", books);
        request.getRequestDispatcher("/books.jsp").forward(request, response);
    }

    private void handleAddBook(HttpServletRequest request) {
        Book book = createBookFromRequest(request);
        String message;
        try {
            bookDAO.save(book);
            message = "Data saved successfully!";
        } catch (Exception e) {
            message = "Failed to save data. Please try again.";
        }
        request.setAttribute("message", message);
    }

    private void updateBook(HttpServletRequest request) {
        Long bookId = Long.valueOf(request.getParameter("id"));
        Book book = createBookFromRequest(request);
        book.setBookId(bookId);
        bookDAO.update(book);
    }

    private Book createBookFromRequest(HttpServletRequest request) {
        String title = request.getParameter("title");
        String edition = request.getParameter("edition");
        String isbnCode = request.getParameter("isbncode");
        String publicationYear = request.getParameter("publicationYear");
        String publisherName = request.getParameter("publisherName");
        BookStatus bookStatus = BookStatus.valueOf(request.getParameter("bookStatus").toUpperCase());

        Shelf shelf = new Shelf();
        String shelfIdStr = request.getParameter("shelf");
        if (shelfIdStr != null && !shelfIdStr.trim().isEmpty()) {
            shelf.setShelfId(Long.parseLong(shelfIdStr));
        }

        Book book = new Book();
        book.setTitle(title);
        book.setEdition(edition);
        book.setISBNcode(isbnCode);
        book.setPublicationYear(publicationYear);
        book.setPublisherName(publisherName);
        book.setBookStatus(bookStatus);
        book.setShelf(shelf);

        return book;
    }
}

package ServletView;

import dao.MembershipTypeBookDAO;
import model.MembershipTypeBook;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/MembershipTypeBookServlet")
public class MembershipTypeBookServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private MembershipTypeBookDAO membershipTypeBookDAO;

    @Override
    public void init() throws ServletException {
        membershipTypeBookDAO = new MembershipTypeBookDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) action = "list";

        switch (action) {
            case "new":
                showNewForm(request, response);
                break;
            case "edit":
                showEditForm(request, response);
                break;
            case "delete":
                deleteMembershipTypeBook(request, response);
                break;
            default:
                listMembershipTypes(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) action = "list";

        switch (action) {
            case "create":
                createMembershipTypeBook(request, response);
                break;
            case "update":
                updateMembershipTypeBook(request, response);
                break;
            default:
                listMembershipTypes(request, response);
                break;
        }
    }

    private void listMembershipTypes(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<MembershipTypeBook> membershipTypes = membershipTypeBookDAO.findAll();
        request.setAttribute("membershipTypes", membershipTypes);
        request.getRequestDispatcher("membership-type-list.jsp").forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("membership-type-form.jsp").forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        MembershipTypeBook existingMembershipTypeBook = membershipTypeBookDAO.findById(id);
        request.setAttribute("membershipTypeBook", existingMembershipTypeBook);
        request.getRequestDispatcher("membership-type-form.jsp").forward(request, response);
    }

    private void createMembershipTypeBook(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String membershipName = request.getParameter("membershipName");
        int maxBooks = Integer.parseInt(request.getParameter("maxBooks"));
        int price = Integer.parseInt(request.getParameter("price"));

        MembershipTypeBook membershipTypeBook = new MembershipTypeBook();
        membershipTypeBook.setMembershipName(membershipName);
        membershipTypeBook.setMaxBooks(maxBooks);
        membershipTypeBook.setPrice(price);

        membershipTypeBookDAO.save(membershipTypeBook);
        response.sendRedirect("MembershipTypeBookServlet?action=list");
    }

    private void updateMembershipTypeBook(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        String membershipName = request.getParameter("membershipName");
        int maxBooks = Integer.parseInt(request.getParameter("maxBooks"));
        int price = Integer.parseInt(request.getParameter("price"));

        MembershipTypeBook membershipTypeBook = membershipTypeBookDAO.findById(id);
        if (membershipTypeBook != null) {
            membershipTypeBook.setMembershipName(membershipName);
            membershipTypeBook.setMaxBooks(maxBooks);
            membershipTypeBook.setPrice(price);
            membershipTypeBookDAO.update(membershipTypeBook);
        }
        response.sendRedirect("MembershipTypeBookServlet?action=list");
    }

    private void deleteMembershipTypeBook(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        membershipTypeBookDAO.delete(id);
        response.sendRedirect("MembershipTypeBookServlet?action=list");
    }
}

package ServletView;

import model.Membership;
import dao.MembershipDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/MembershipServlet")
public class MembershipServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private MembershipDAO membershipDAO;

    @Override
    public void init() {
        membershipDAO = new MembershipDAO(); // Assuming you have a default constructor in your DAO
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("insert".equals(action)) {
            try {
                insertMembership(request, response);
            } catch (Exception e) {
                e.printStackTrace();
                response.sendRedirect("errorPage.jsp"); // Redirect to an error page
            }
        } 
        // Add other actions as needed
    }

    private void insertMembership(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String membershipCode = request.getParameter("membershipCode");
        String registrationDate = request.getParameter("registrationDate");
        String membershipStatus = request.getParameter("membershipStatus");
        String membershipType = request.getParameter("membershipType");
        String expirationDate = request.getParameter("expirationDate");

        // Validate input parameters
        if (membershipCode == null || membershipCode.isEmpty() || 
            registrationDate == null || registrationDate.isEmpty() || 
            membershipStatus == null || membershipStatus.isEmpty() || 
            membershipType == null || membershipType.isEmpty() || 
            expirationDate == null || expirationDate.isEmpty()) {
            
            response.sendRedirect("errorPage.jsp"); // Redirect to an error page for missing data
            return;
        }

        // Create and populate the new Membership object
        Membership newMembership = new Membership();
        newMembership.setMembershipCode(membershipCode);
        newMembership.setRegistration_Date(registrationDate);
        newMembership.setMembershipStatus(membershipStatus);
        newMembership.setMembership_Type(membershipType);
        newMembership.setExpiration_Date(expirationDate);

        // Attempt to save the new membership
        try {
            membershipDAO.save(newMembership); // Save the new membership
            response.sendRedirect("MembershipSuccess.html"); // Redirect to the success page
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("errorPage.jsp"); // Redirect to an error page if save fails
        }
    }
}

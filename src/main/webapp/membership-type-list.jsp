<%@ page import="model.MembershipTypeBook" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Membership Type List</title>
    <style>
        /* Add some basic styling */
        body { font-family: Arial, sans-serif; background-color: #f4f4f4; padding: 20px; }
        .container { max-width: 800px; margin: auto; background: #fff; padding: 20px; border-radius: 5px; box-shadow: 0 0 10px rgba(0,0,0,0.1); }
        table { width: 100%; border-collapse: collapse; margin-bottom: 20px; }
        th, td { border: 1px solid #ddd; padding: 8px; text-align: left; }
        th { background-color: #0066cc; color: white; }
        .btn { padding: 5px 10px; color: white; background-color: #0066cc; text-decoration: none; border-radius: 5px; cursor: pointer; }
        .btn:hover { background-color: #5a67d8; }
    </style>
</head>
<body>
<div class="container">
    <h2>Membership Type List</h2>
    <a href="MembershipTypeBookServlet?action=new" class="btn">Add New Membership Type</a>
    <table>
        <thead>
            <tr>
                <th>ID</th>
                <th>Membership Name</th>
                <th>Max Books</th>
                <th>Price</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
            <%
                List<MembershipTypeBook> membershipTypes = (List<MembershipTypeBook>) request.getAttribute("membershipTypes");
                if (membershipTypes != null && !membershipTypes.isEmpty()) {
                    for (MembershipTypeBook membershipType : membershipTypes) {
            %>
                        <tr>
                            <td><%= membershipType.getMembership_book_Id() %></td>
                            <td><%= membershipType.getMembershipName() %></td>
                            <td><%= membershipType.getMaxBooks() %></td>
                            <td><%= membershipType.getPrice() %></td>
                            <td>
                                <a href="MembershipTypeBookServlet?action=edit&id=<%= membershipType.getMembership_book_Id() %>" class="btn">Edit</a>
                                <a href="MembershipTypeBookServlet?action=delete&id=<%= membershipType.getMembership_book_Id() %>" class="btn" onclick="return confirm('Are you sure you want to delete this membership type?');">Delete</a>
                            </td>
                        </tr>
            <%
                    }
                } else {
            %>
                <tr><td colspan="5">No membership types available.</td></tr>
            <%
                }
            %>
        </tbody>
    </table>
</div>
</body>
</html>

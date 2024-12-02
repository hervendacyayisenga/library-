<%@ page import="model.Membership" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Memberships</title>
    <style>
        body { font-family: Arial, sans-serif; background-color: #f4f4f4; }
        .container { max-width: 800px; margin: 20px auto; padding: 20px; background: white; border-radius: 5px; box-shadow: 0 0 10px rgba(0,0,0,0.1); }
        table { width: 100%; border-collapse: collapse; }
        th, td { padding: 10px; border-bottom: 1px solid #ddd; text-align: left; }
        th { background-color: #0066cc; color: white; }
        a { text-decoration: none; color: #0066cc; }
    </style>
</head>
<body>
<div class="container">
    <h1>Memberships</h1>
    <a href="membership?action=new">Add New Membership</a>
    <table>
        <tr>
            <th>Code</th>
            <th>Registration Date</th>
            <th>Status</th>
            <th>Type</th>
            <th>Expiration Date</th>
            <th>Actions</th>
        </tr>
        <% 
            List<Membership> memberships = (List<Membership>) request.getAttribute("memberships");
            if (memberships != null) {
                for (Membership membership : memberships) {
        %>
                    <tr>
                        <td><%= membership.getMembershipCode() %></td>
                        <td><%= membership.getRegistration_Date() %></td>
                        <td><%= membership.getMembershipStatus() %></td>
                        <td><%= membership.getMembership_Type() %></td>
                        <td><%= membership.getExpiration_Date() %></td>
                        <td>
                            <a href="membership?action=edit&id=<%= membership.getMembershipId() %>">Edit</a> |
                            <a href="membership?action=delete&id=<%= membership.getMembershipId() %>" onclick="return confirm('Are you sure?');">Delete</a>
                        </td>
                    </tr>
        <% 
                }
            } 
        %>
    </table>
</div>
</body>
</html>

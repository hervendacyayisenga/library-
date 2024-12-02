<%@ page import="model.Membership" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Membership Form</title>
    <style>
        body { font-family: Arial, sans-serif; background-color: #f4f4f4; padding: 20px; }
        .container { max-width: 500px; margin: auto; background: #fff; padding: 20px; border-radius: 5px; box-shadow: 0 0 10px rgba(0,0,0,0.1); }
        .form-group { margin-bottom: 15px; }
        label { display: block; margin-bottom: 5px; }
        input[type="text"], input[type="date"] { width: 100%; padding: 10px; border: 1px solid #ddd; border-radius: 5px; }
        input[type="submit"] { width: 100%; padding: 10px; background-color: #0066cc; color: white; border: none; border-radius: 5px; cursor: pointer; }
    </style>
</head>
<body>
<div class="container">
    <h2><%= request.getAttribute("membership") == null ? "Add New Membership" : "Edit Membership" %></h2>
    <form action="Membership" method="post">
        <%
            Membership membership = (Membership) request.getAttribute("membership");
            boolean isEdit = membership != null;
        %>
        <input type="hidden" name="action" value="<%= isEdit ? "update" : "insert" %>">
        <% if (isEdit) { %>
            <input type="hidden" name="membershipId" value="<%= membership.getMembershipId() %>">
        <% } %>

        <div class="form-group">
            <label for="membershipCode">Membership Code:</label>
            <input type="text" id="membershipCode" name="membershipCode" value="<%= isEdit ? membership.getMembershipCode() : "" %>" required>
        </div>

        <div class="form-group">
            <label for="registrationDate">Registration Date:</label>
            <input type="date" id="registrationDate" name="registrationDate" value="<%= isEdit ? membership.getRegistration_Date() : "" %>" required>
        </div>

        <div class="form-group">
            <label for="membershipStatus">Status:</label>
            <input type="text" id="membershipStatus" name="membershipStatus" value="<%= isEdit ? membership.getMembershipStatus() : "" %>" required>
        </div>

        <div class="form-group">
            <label for="membershipType">Type:</label>
            <input type="text" id="membershipType" name="membershipType" value="<%= isEdit ? membership.getMembership_Type() : "" %>" required>
        </div>

        <div class="form-group">
            <label for="expirationDate">Expiration Date:</label>
            <input type="date" id="expirationDate" name="expirationDate" value="<%= isEdit ? membership.getExpiration_Date() : "" %>" required>
        </div>

        <div class="form-group">
            <label for="personUserId">User ID:</label>
            <input type="number" id="personUserId" name="personUserId" required>
        </div>

        <div class="form-group">
            <label for="membershipTypeId">Membership Type ID:</label>
            <input type="number" id="membershipTypeId" name="membershipTypeId" required>
        </div>

        <input type="submit" value="<%= isEdit ? "Update Membership" : "Add Membership" %>">
    </form>
</div>
</body>
</html>

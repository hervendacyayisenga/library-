<%@ page import="model.MembershipTypeBook" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Membership Type Form</title>
    <style>
        /* Basic Styling */
        body { 
            font-family: Arial, sans-serif; 
            background-color: #f0f2f5; 
            margin: 0; 
            padding: 20px; 
            display: flex; 
            justify-content: center; 
            align-items: center; 
            height: 100vh;
        }
        .container { 
            max-width: 500px; 
            background: #fff; 
            padding: 30px; 
            border-radius: 8px; 
            box-shadow: 0 4px 12px rgba(0,0,0,0.15); 
        }
        h2 { 
            text-align: center; 
            color: #333; 
            margin-bottom: 20px; 
        }
        .form-group { 
            margin-bottom: 20px; 
        }
        label { 
            display: block; 
            margin-bottom: 8px; 
            font-weight: bold; 
            color: #333;
        }
        select, input[type="text"], input[type="number"] { 
            width: 100%; 
            padding: 10px; 
            border: 1px solid #ccc; 
            border-radius: 5px; 
            font-size: 16px;
        }
        input[type="submit"] { 
            width: 100%; 
            padding: 12px; 
            background-color: #5a67d8; 
            color: white; 
            font-size: 16px; 
            border: none; 
            border-radius: 5px; 
            cursor: pointer; 
            font-weight: bold; 
        }
        input[type="submit"]:hover { 
            background-color: #5a67d8; 
        }
        .form-group select { 
            color: #555; 
            background: #f9f9f9; 
        }
        .form-group select option { 
            padding: 8px;
        }
    </style>
</head>
<body>
<div class="container">
    <h2><%= request.getAttribute("membershipTypeBook") == null ? "Add New Membership Type" : "Edit Membership Type" %></h2>
    <form action="MembershipTypeBookServlet" method="post">
        <% 
            MembershipTypeBook membershipTypeBook = (MembershipTypeBook) request.getAttribute("membershipTypeBook"); 
            boolean isEdit = membershipTypeBook != null;
        %>
        <input type="hidden" name="action" value="<%= isEdit ? "update" : "create" %>">
        <% if (isEdit) { %>
            <input type="hidden" name="id" value="<%= membershipTypeBook.getMembership_book_Id() %>">
        <% } %>
        
        <div class="form-group">
            <label for="membershipName">Membership Type:</label>
            <select id="membershipName" name="membershipName" required>
                <option value="">Select Membership</option>
                <option value="Gold" <%= isEdit && "Gold".equals(membershipTypeBook.getMembershipName()) ? "selected" : "" %> >Gold - 50 Rwf/day, 5 books max</option>
                <option value="Silver" <%= isEdit && "Silver".equals(membershipTypeBook.getMembershipName()) ? "selected" : "" %> >Silver - 30 Rwf/day, 3 books max</option>
                <option value="Striver" <%= isEdit && "Striver".equals(membershipTypeBook.getMembershipName()) ? "selected" : "" %> >Striver - 10 Rwf/day, 2 books max</option>
            </select>
        </div>

        <div class="form-group">
            <label for="maxBooks">Max Books:</label>
            <input type="number" id="maxBooks" name="maxBooks" value="<%= isEdit ? membershipTypeBook.getMaxBooks() : "" %>" required>
        </div>

        <div class="form-group">
            <label for="price">Price:</label>
            <input type="number" id="price" name="price" value="<%= isEdit ? membershipTypeBook.getPrice() : "" %>" required>
        </div>

        <input type="submit" value="<%= isEdit ? "Update Membership Type" : "Add Membership Type" %>">
    </form>
</div>
</body>
</html>

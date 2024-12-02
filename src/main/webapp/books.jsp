<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="model.Book" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Book List</title>
    <style>
        /* General styling */
        body {
            font-family: Arial, sans-serif;
            background-color: #f9f9f9;
            color: #333;
            margin: 0;
            padding: 20px;
        }
        
        .container {
            max-width: 800px;
            margin: 0 auto;
            padding: 20px;
            background-color: #ffffff;
            border-radius: 8px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }
        
        h1 {
            text-align: center;
            color: #333;
        }
        
        /* Button styling */
        .btn {
            padding: 8px 16px;
            margin: 5px;
            font-size: 14px;
            font-weight: bold;
            color: #fff;
            background-color: #007bff;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            transition: background-color 0.3s;
        }
        
        .btn:hover {
            background-color: #0056b3;
        }
        
        .delete-btn {
            background-color: #dc3545;
        }
        
        .delete-btn:hover {
            background-color: #c82333;
        }
        
        .update-btn {
            background-color: #28a745;
        }
        
        .update-btn:hover {
            background-color: #218838;
        }
        
        /* Table styling */
        .book-table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }
        
        .book-table th, .book-table td {
            padding: 12px 15px;
            border: 1px solid #ddd;
            text-align: center;
        }
        
        .book-table th {
            background-color: #007bff;
            color: #ffffff;
        }
        
        .book-table tbody tr:nth-child(even) {
            background-color: #f2f2f2;
        }
        
        .book-table tbody tr:hover {
            background-color: #e0e0e0;
        }
        
        .no-books {
            text-align: center;
            font-style: italic;
            color: #888;
        }
        
        /* Form styling */
        .form-container {
            margin-top: 30px;
        }
        
        .form-container label {
            display: block;
            margin-bottom: 5px;
            font-weight: bold;
        }
        
        .form-container input, .form-container select {
            width: 100%;
            padding: 8px;
            margin-bottom: 15px;
            border: 1px solid #ddd;
            border-radius: 4px;
        }
        
        .form-container button {
            width: 100%;
            padding: 10px;
            background-color: #007bff;
            color: #fff;
            border: none;
            border-radius: 4px;
            font-size: 16px;
            cursor: pointer;
            transition: background-color 0.3s;
        }
        
        .form-container button:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Book List</h1>
        
        <!-- Button to retrieve and display the list of books -->
        <form action="Submit-book" method="get">
            <button type="submit" class="btn">Show Books</button>
        </form>
        
        <!-- Book List Table -->
        <table class="book-table">
            <thead>
                <tr>
                    <th>Book ID</th>
                    <th>Title</th>
                    <th>Edition</th>
                    <th>ISBN Code</th>
                    <th>Publication Year</th>
                    <th>Publisher Name</th>
                    <th>Book Status</th>
                    <th>Shelf ID</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <%
                    // Get the list of books from the request attribute
                    List<Book> books = (List<Book>) request.getAttribute("books");
                    if (books != null && !books.isEmpty()) {
                        for (Book book : books) {
                %>
                    <tr>
                        <td><%= book.getBookId() %></td>
                        <td><%= book.getTitle() %></td>
                        <td><%= book.getEdition() %></td>
                        <td><%= book.getISBNcode() %></td>
                        <td><%= book.getPublicationYear() %></td>
                        <td><%= book.getPublisherName() %></td>
                        <td><%= book.getBookStatus() %></td>
                        <td><%= book.getShelf() != null ? book.getShelf().getShelfId() : "N/A" %></td>
                        <td>
                            <form action="Submit-book" method="post" style="display:inline;">
                                <input type="hidden" name="action" value="delete">
                                <input type="hidden" name="id" value="<%= book.getBookId() %>">
                                <button type="submit" class="btn delete-btn">Delete</button>
                            </form>
                            <a href="editBook.jsp?id=<%= book.getBookId() %>" class="btn update-btn">Update</a>
                        </td>
                    </tr>
                <%
                        }
                    } else {
                %>
                    <tr>
                        <td colspan="9" class="no-books">No books available.</td>
                    </tr>
                <%
                    }
                %>
            </tbody>
        </table>

        <!-- Add New Book Form -->
<div class="form-container">
    <h2>Add New Book</h2>
    
    <!-- Display success or failure message -->
    <c:if test="${not empty message}">
        <p style="color: green;">${message}</p>
    </c:if>
    
    <form action="Submit-book" method="post">
        <input type="hidden" name="action" value="add">
        
        <label for="title">Title:</label>
        <input type="text" id="title" name="title" required>
        
        <label for="edition">Edition:</label>
        <input type="text" id="edition" name="edition" required>
        
        <label for="isbncode">ISBN Code:</label>
        <input type="text" id="isbncode" name="isbncode" required>
        
        <label for="publicationYear">Publication Year:</label>
        <input type="text" id="publicationYear" name="publicationYear" required>
        
        <label for="publisherName">Publisher Name:</label>
        <input type="text" id="publisherName" name="publisherName" required>
        
        <label for="bookStatus">Book Status:</label>
        <select id="bookStatus" name="bookStatus" required>
            <option value="AVAILABLE">Available</option>
            <option value="CHECKED_OUT">Checked Out</option>
        </select>
        
        <label for="shelf">Shelf ID:</label>
        <input type="text" id="shelf" name="shelf">
        
        <button type="submit">Add Book</button>
    </form>
</div>

    </div>
</body>
</html>

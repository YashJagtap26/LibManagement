import java.sql.*;
import java.util.Scanner;

public class IssueBookDAO {
    private Connection conn;

    public IssueBookDAO() {
        conn = DBConnection.getConnection();
    }

    public void issueBook(int userId, int bookId) {
        String query = "INSERT INTO issued_books (user_id, book_id) VALUES (?, ?)";
        String updateBook = "UPDATE books SET quantity = quantity - 1 WHERE book_id = ? AND quantity > 0";

        try (PreparedStatement stmt = conn.prepareStatement(query);
             PreparedStatement bookStmt = conn.prepareStatement(updateBook)) {
             
            stmt.setInt(1, userId);
            stmt.setInt(2, bookId);

            bookStmt.setInt(1, bookId);
            int updated = bookStmt.executeUpdate();

            if (updated > 0) {
                stmt.executeUpdate();
                System.out.println("Book issued successfully!");
            } else {
                System.out.println("Book not available.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void returnBook(int issueId) {
        String query = "DELETE FROM issued_books WHERE issue_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, issueId);
            int rows = stmt.executeUpdate();
            if (rows > 0) System.out.println("Book returned successfully!");
            else System.out.println("Record not found.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

import java.sql.*;
import java.util.Scanner;

public class BookDAO {
    private Connection conn;

    public BookDAO() {
        conn = DBConnection.getConnection();
    }

    public void addBook(String title, String author, int quantity) {
        String query = "INSERT INTO books (title, author, quantity) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, title);
            stmt.setString(2, author);
            stmt.setInt(3, quantity);
            stmt.executeUpdate();
            System.out.println("Book added successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void displayBooks() {
        String query = "SELECT * FROM books";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            System.out.println("Book ID | Title | Author | Quantity");
            while (rs.next()) {
                System.out.printf("%d | %s | %s | %d%n",
                        rs.getInt("book_id"), rs.getString("title"),
                        rs.getString("author"), rs.getInt("quantity"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteBook(int bookId) {
        String query = "DELETE FROM books WHERE book_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, bookId);
            int rows = stmt.executeUpdate();
            if (rows > 0) System.out.println("Book deleted successfully!");
            else System.out.println("Book not found.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

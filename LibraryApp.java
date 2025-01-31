import java.util.Scanner;

public class LibraryApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BookDAO bookDAO = new BookDAO();
        IssueBookDAO issueDAO = new IssueBookDAO();

        while (true) {
            System.out.println("\nLibrary Management System");
            System.out.println("1. Add Book");
            System.out.println("2. Display Books");
            System.out.println("3. Delete Book");
            System.out.println("4. Issue Book");
            System.out.println("5. Return Book");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    System.out.print("Enter Book Title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter Author: ");
                    String author = scanner.nextLine();
                    System.out.print("Enter Quantity: ");
                    int quantity = scanner.nextInt();
                    bookDAO.addBook(title, author, quantity);
                    break;

                case 2:
                    bookDAO.displayBooks();
                    break;

                case 3:
                    System.out.print("Enter Book ID to delete: ");
                    int bookId = scanner.nextInt();
                    bookDAO.deleteBook(bookId);
                    break;

                case 4:
                    System.out.print("Enter User ID: ");
                    int userId = scanner.nextInt();
                    System.out.print("Enter Book ID: ");
                    int bookIssueId = scanner.nextInt();
                    issueDAO.issueBook(userId, bookIssueId);
                    break;

                case 5:
                    System.out.print("Enter Issue ID: ");
                    int issueId = scanner.nextInt();
                    issueDAO.returnBook(issueId);
                    break;

                case 6:
                    System.out.println("Exiting...");
                    scanner.close();
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
}

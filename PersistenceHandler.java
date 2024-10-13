package LMS;

import java.util.List;

public abstract class PersistenceHandler {
    // Abstract method to insert data (e.g., Book)
    public abstract void insertBook(Book book);
    public abstract void deleteBook(int id);
	public abstract void closeConnection();
	public abstract void readBooksFromFile();
	public abstract void updateBook(int bookID, boolean isLoaned);
	 public abstract void insertUser(user u1);
	 public abstract void deleteUser(int id);
	 public abstract void updateUser(int id, double loanFees);
	 public abstract List<Book> getAllBooks();
	 public abstract List<user> getAllUsers();
	 public abstract void logLoanTransaction(int bookID, int userID, int loanDate);
	 public abstract void logReturnTransaction(int bookID, int userID, String returnDate);
	 public abstract void readUsers();
	 public abstract void  UpdateTransaction(int bookID, int userID, int days);

	 
	 
}


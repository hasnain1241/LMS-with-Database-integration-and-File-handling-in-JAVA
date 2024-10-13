package LMS;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SQLHandler extends PersistenceHandler {
    private Connection connection; // Keep the connection alive throughout the session

    public SQLHandler() {
        // Initialize the connection in the constructor
        String url = "jdbc:mysql://localhost:3306/library"; // Adjust database name if necessary
        String user = "root";  // Replace with your DB username
        String password = "Hasnain334";  // Replace with your DB password

        try {
            connection = DriverManager.getConnection(url, user, password);
            System.out.println("Connection to the database was successful!");
        } catch (SQLException e) {
            System.out.println("Failed to connect to the database.");
            e.printStackTrace();
        }
    }

    @Override
    public void insertBook(Book book) {
        PreparedStatement pstmt = null;

        try {
            String query = "INSERT INTO book (title, author, isbn, publicationyear, genre, baseLoanFee, isloaned) VALUES (?, ?, ?, ?, ?, ?, ?)";
            pstmt = connection.prepareStatement(query);

            // Set values for the query
            pstmt.setString(1, book.getTitle());
            pstmt.setString(2, book.getAuthor());
            pstmt.setString(3, book.getIsbn());
            pstmt.setInt(4, book.getPublicationYear());
            pstmt.setString(5, book.getGenre());
            pstmt.setDouble(6, book.getBaseLoanFee());
            pstmt.setBoolean(7, false);

            // Execute the query
            pstmt.executeUpdate();
            System.out.println("Book inserted successfully.");
        } catch (SQLException e) {
            System.out.println("Failed to insert book.");
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null) pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
    public void deleteBook(int id) {
        String sql = "DELETE FROM book WHERE id = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Book with id " + id + " deleted successfully!");
            } else {
                System.out.println("Book with id " + id + " not found!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    // Close connection when done
    public void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Connection closed.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

	@Override
	public void readBooksFromFile() {
		// TODO Auto-generated method stub
		
	}

	
	public void updateBook(int bookID, boolean isLoaned) {
	    String sql = "UPDATE book SET isloaned = ? WHERE id = ?"; // 

	    try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
	        pstmt.setBoolean(1, isLoaned);
	        pstmt.setInt(2, bookID);

	        int rowsAffected = pstmt.executeUpdate();

	        if (rowsAffected > 0) {
	            System.out.println("Book with ID " + bookID + " updated successfully! Loan status set to: " + isLoaned);
	        } else {
	            System.out.println("Book with ID " + bookID + " not found!");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}

	@Override
	public void insertUser(user u1) {
		 PreparedStatement pstmt = null;

	        try {
	            String query = "INSERT INTO user (name, email, address, phonenumber, loanFees) VALUES (?, ?, ?, ?, ?)";
	            pstmt = connection.prepareStatement(query);

	            // Set values for the query
	            pstmt.setString(1, u1.getName());
	            pstmt.setString(2, u1.getEmail());
	            pstmt.setString(3, u1.getAddress());
	            pstmt.setString(4, u1.getPhoneNumber());
	           
	            pstmt.setDouble(5, u1.getTotalLoanFees());
	          

	            // Execute the query
	            pstmt.executeUpdate();
	            System.out.println("User inserted successfully.");
	        } catch (SQLException e) {
	            System.out.println("Failed to insert User.");
	            e.printStackTrace();
	        } finally {
	            try {
	                if (pstmt != null) pstmt.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
		
	}

	@Override
	public void deleteUser(int id) {
		 String sql = "DELETE FROM user WHERE id = ?";

	        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
	            pstmt.setInt(1, id);
	            int rowsAffected = pstmt.executeUpdate();

	            if (rowsAffected > 0) {
	                System.out.println("User with id " + id + " deleted successfully!");
	            } else {
	                System.out.println("User with id " + id + " not found!");
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
		
	}

	@Override
	public void updateUser(int id, double loanFees) {
		String sql = "UPDATE user SET loanFees = ? WHERE id = ?"; // 

	    try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
	        pstmt.setDouble(1, loanFees);
	        pstmt.setInt(2, id);

	        int rowsAffected = pstmt.executeUpdate();

	        if (rowsAffected > 0) {
	            System.out.println("User with ID " + id + " updated successfully! Loan Fees set to: " + loanFees);
	        } else {
	            System.out.println("User with ID " + id + " not found!");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
		
	}
	
	
	
	public List<Book> getAllBooks() {
	    List<Book> books = new ArrayList<>();
	    String sql = "SELECT * FROM book";
	    int maxID = 0;  // To keep track of the highest book ID

	    try (Statement stmt = connection.createStatement();
	         ResultSet rs = stmt.executeQuery(sql)) {

	        while (rs.next()) {
	            int id = rs.getInt("id");
	            String title = rs.getString("title");
	            String author = rs.getString("author");
	            String isbn = rs.getString("isbn");
	            int year = rs.getInt("publicationyear");
	            String genre = rs.getString("genre");
	            double fee = rs.getDouble("baseLoanFee");
	            boolean isLoaned = rs.getBoolean("isLoaned");

	            // Assuming all books are Textbooks for simplicity; you may need to handle different book types.
	            Book book = new Textbook(id, title, author, isbn, year, genre, fee);
	            book.setLoanStatus(isLoaned);
	            books.add(book);

	            // Update the maxID
	            if (id > maxID) {
	                maxID = id;
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    // Update the nextBookID to be one more than the highest ID found in the database
	    Book.setNextBookID(maxID + 1);

	    return books;
	}

	@Override
	public List<user> getAllUsers() {
		 List<user> users = new ArrayList<>();
		    String sql = "SELECT * FROM user";
		    int maxID = 0;  // To keep track of the highest book ID

		    try (Statement stmt = connection.createStatement();
		         ResultSet rs = stmt.executeQuery(sql)) {

		        while (rs.next()) {
		            int id = rs.getInt("id");
		            String name = rs.getString("name");
		            String email = rs.getString("email");
		            String address = rs.getString("address");
		            
		            String phoneNumber = rs.getString("phonenumber");
		            double fee = rs.getDouble("loanFees");
		         

		            // Assuming all books are Textbooks for simplicity; you may need to handle different book types.
		            user u1 = new Students(id, name, email, phoneNumber, address);
		            
		           users.add(u1);

		            // Update the maxID
		            if (id > maxID) {
		                maxID = id;
		            }
		        }
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }

		    // Update the nextBookID to be one more than the highest ID found in the database
		   user.setNextUserID(maxID+1);

		    return users;
	}

	public void logLoanTransaction(int bookID, int userID, int loanduration) {
	    String sql = "INSERT INTO transaction (bookID, userID, loanduration, ActionPerformed) VALUES (?, ?, ?, ?)";

	    try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
	        pstmt.setInt(1, bookID);
	        pstmt.setInt(2, userID);
	        pstmt.setInt(3, loanduration);
	        pstmt.setString(4, "Loaned");
	        pstmt.executeUpdate();
	        
	        System.out.println("The Transaction has been logged");
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	
	public void logReturnTransaction(int bookID, int userID, String returnDate) {
	    String sql = "UPDATE transaction SET returnDate = ?, ActionPerformed = ? WHERE bookID = ? AND userID = ? AND returnDate IS NULL";

	    try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
	        pstmt.setString(1, returnDate);       // Set returnDate
	        pstmt.setString(2, "Returned");        // Set ActionPerformed
	        pstmt.setInt(3, bookID);               // Set bookID
	        pstmt.setInt(4, userID);               // Set userID
	        pstmt.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}


	public List<String> getLoanHistoryForUser(int userID) {
	    List<String> loanHistory = new ArrayList<>();
	    String sql = "SELECT * FROM transaction WHERE userID = ?";

	    try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
	        pstmt.setInt(1, userID);
	        ResultSet rs = pstmt.executeQuery();

	        while (rs.next()) {
	            int bookID = rs.getInt("bookID");
	            String loanDate = rs.getString("loanDate");
	            String returnDate = rs.getString("returnDate");

	            String record = "BookID: " + bookID + ", Loan Date: " + loanDate + ", Return Date: " + (returnDate == null ? "Not Returned" : returnDate);
	            loanHistory.add(record);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return loanHistory;
	}

	@Override
	public void readUsers() {
		// TODO Auto-generated method stub
		
	}
	
	public void UpdateTransaction(int bookID, int userID, int days) {
	    String fetchSql = "SELECT loanduration FROM transaction WHERE bookID = ? AND userID = ?";
	    String updateSql = "UPDATE transaction SET loanduration = ?, ActionPerformed = ? WHERE bookID = ? AND userID = ? AND ActionPerformed=Loaned";

	    try (PreparedStatement fetchPstmt = connection.prepareStatement(fetchSql)) {
	        fetchPstmt.setInt(1, bookID);
	        fetchPstmt.setInt(2, userID);
	        
	        ResultSet rs = fetchPstmt.executeQuery();
	        if (rs.next()) {
	            int currentDuration = rs.getInt("loanduration");
	            int newDuration = currentDuration + days;

	            try (PreparedStatement updatePstmt = connection.prepareStatement(updateSql)) {
	                updatePstmt.setInt(1, newDuration); // Set new loan duration
	                updatePstmt.setString(2, "Extended"); // Set ActionPerformed
	                updatePstmt.setInt(3, bookID); // Set bookID
	                updatePstmt.setInt(4, userID); // Set userID
	                updatePstmt.executeUpdate();
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}




}

package LMS;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class FileHandler extends PersistenceHandler {
    @Override
    public void insertBook(Book book) {
        // Code to write the book information to a file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("books.txt", true))) {
            writer.write(book.getBookID() + "," + book.getTitle() + "," + book.getAuthor()+","+book.getIsbn()+","+book.getPublicationYear()+","+book.getGenre()+","+book.getBaseLoanFee());
            writer.newLine();
            System.out.println("Book saved to file.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

	@Override
	public void closeConnection() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteBook(int id) {
		// TODO Auto-generated method stub
		
	}
	
	public void readBooksFromFile() {
	    String filePath = "C:\\Users\\Hasnain\\OneDrive - FAST National University\\Semester 5 2024\\SDA\\Assignments\\Assignment 2\\i221241_A_A2\\LibraryManagementSystem_file_db\\books.txt";
	    System.out.println("Attempting to read from file: " + filePath);
	    
	    File file = new File(filePath);
	    if (!file.exists()) {
	        System.out.println("File does not exist: " + file.getAbsolutePath());
	        return;
	    }
	    
	    try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
	        String line;
	        System.out.println("Starting to read books from the file...");
	        
	        while ((line = reader.readLine()) != null) {
	            if (line.trim().isEmpty()) {
	                continue; // Skip empty lines
	            }

	            // Assuming the format is: BookID, Title, Author, ISBN, PublicationYear, Genre, BaseLoanFee
	            String[] bookData = line.split(",");
	            if (bookData.length == 7) { // Ensure there are 7 fields
	                int bookID = Integer.parseInt(bookData[0].trim());
	                String title = bookData[1].trim();
	                String author = bookData[2].trim();
	                String isbn = bookData[3].trim();
	                int publicationYear = Integer.parseInt(bookData[4].trim());
	                String genre = bookData[5].trim();
	                double baseLoanFee = Double.parseDouble(bookData[6].trim());

	                // Create a Book object (assume a Textbook type for example)
	                Book book = new Textbook(title, author, isbn, publicationYear, genre, baseLoanFee);
	                System.out.println("Read Book ID: " + bookID + ", Title: " + title);
	                // Add to library or any other collection
	            } else {
	                System.out.println("Invalid book data format: " + line);
	            }
	        }
	    } catch (IOException e) {
	        System.out.println("IOException occurred: " + e.getMessage());
	    } catch (NumberFormatException e) {
	        System.out.println("NumberFormatException: " + e.getMessage());
	    }
	}

	@Override
	public void updateBook(int bookID, boolean isLoaned) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void insertUser(user u1) {
		 try (BufferedWriter writer = new BufferedWriter(new FileWriter("user.txt", true))) {
	            writer.write(u1.getusersID()+","+u1.getName() + "," + u1.getAddress() + "," + u1.getEmail()+","+u1.getPhoneNumber()+","+u1.getTotalLoanFees());
	            writer.newLine();
	            System.out.println("user saved to file.");
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
		
	}

	@Override
	public void deleteUser(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateUser(int id, double loanFees) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Book> getAllBooks() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<user> getAllUsers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void logLoanTransaction(int bookID, int userID, int loanDate) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void logReturnTransaction(int bookID, int userID, String returnDate) {
		// TODO Auto-generated method stub
		
	}

	public void readUsers() {
	    try (BufferedReader reader = new BufferedReader(new FileReader("user.txt"))) {
	        String line;
	        System.out.println("Users in file:");
	        while ((line = reader.readLine()) != null) {
	            String[] userDetails = line.split(",");
	            System.out.println("ID: " + userDetails[0] + ", Name: " + userDetails[1] + 
	                               ", Address: " + userDetails[2] + ", Email: " + 
	                               userDetails[3] + ", Phone Number: " + userDetails[4] + 
	                               ", Total Loan Fees: $" + userDetails[5]);
	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}

	@Override
	public void UpdateTransaction(int bookID, int userID, int days) {
		// TODO Auto-generated method stub
		
	}

	
	
    
    //public abstract void closeConnection();
}


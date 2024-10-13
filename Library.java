package LMS;

import java.util.ArrayList;
import java.util.List;

import java.util.Collections;
import java.util.Comparator;

public class Library implements loanableItems {
    private List<Book> books;
    private List<user> users;
    private static final double LATE_FEE_PER_DAY = 5.0; // Flat rate for late return

    // Constructor
    public Library() {
        books = new ArrayList<>();
        users = new ArrayList<>();
    }

    // Book Management Methods (same as before)
    public void addBook(Book book) {
        books.add(book);
    }

    public void displayAvailableBooks() {
        System.out.println("Available Books:");
        for (Book book : books) {
            if (!book.isLoaned()) {
                System.out.println("ID: " + book.getBookID() + ", Title: " + book.getTitle() +" , Author :"+ book.getAuthor());
            }
        }
    }

    public void removeBook(int bookID) {
        Book bookToRemove = findBook(bookID);

        if (bookToRemove != null && !bookToRemove.isLoaned()) {
            books.remove(bookToRemove);
            System.out.println("Book with ID " + bookID + " has been removed.");
        } else if (bookToRemove != null && bookToRemove.isLoaned()) {
            System.out.println("Book with ID " + bookID + " is currently loaned and cannot be removed.");
        } else {
            System.out.println("Book with ID " + bookID + " not found.");
        }
    }
    
    public void removeUser(int userID) {
        user userToRemove = finduser(userID);

        if (userToRemove != null && userToRemove.getLoanedBooks().isEmpty()) {
            users.remove(userToRemove);
            System.out.println("User with ID " + userID + " has been removed.");
        } else if (userToRemove != null && !userToRemove.getLoanedBooks().isEmpty()) {
            System.out.println("User with ID " + userID + " has active loans and cannot be removed.");
        } else {
            System.out.println("User with ID " + userID + " not found.");
        }
    }


    // user Management
    public void adduser(user user) {
        users.add(user);
    }

    public void displayusers() {
        System.out.println("users in Library:");
        for (user user : users) {
            System.out.println("ID: " + user.getusersID() + ", Name: " + user.getName() + ", Email: " + user.getEmail());
        }
    }

    // Loan Transactions
    public void loanBook(int userID, int bookID, int loanDuration) {
        user user = finduser(userID);
        Book book = findBook(bookID);

        if (user != null && book != null && !book.isLoaned()) {
            if (user.getLoanedBooks().size() < user.getMaxLoanLimit()) {
                double loanFee = book.calculateLoanFee(loanDuration);
                if (user instanceof PublicMember) {
                    loanFee *= ((PublicMember) user).getHigherBaseFee();
                }
                book.setLoanStatus(true); // Mark the book as loaned
                user.addLoanedBook(book, loanFee);
                System.out.println("Book '" + book.getTitle() + "' has been loaned to " + user.getName());
                System.out.println("Total loan fee: $" + loanFee);
            } else {
                System.out.println(user.getName() + " has already reached the loan limit.");
            }
        } else {
            System.out.println("Book is already loaned or not found.");
        }
    }

    public void returnBook(int userID, int bookID, int daysLate) {
        user user = finduser(userID);
        Book book = findBook(bookID);

        if (user != null && book != null && book.isLoaned()) {
            user.returnBook(book);
            if (daysLate > 0) {
                double lateFee = daysLate * LATE_FEE_PER_DAY;
                System.out.println("Late fee for returning book '" + book.getTitle() + "': $" + lateFee);
            }
            System.out.println("Book '" + book.getTitle() + "' has been returned by " + user.getName());
        } else {
            System.out.println("Book is not currently loaned or user not found.");
        }
    }

    public void displayLoanDetails(int userID) {
        user user = finduser(userID);
        if (user != null) {
            System.out.println("Loan Details for " + user.getName() + ":");
            user.displayLoanedBooks();
            System.out.println("Total Loan Fees: $" + user.getTotalLoanFees());
        } else {
            System.out.println("user not found.");
        }
    }

    // Helper methods
    public user finduser(int userID) {
        for (user user : users) {
            if (user.getusersID() == userID) {
                return user;
            }
        }
        return null;
    }

    public Book findBook(int bookID) {
        for (Book book : books) {
            if (book.getBookID() == bookID) {
                return book;
            }
        }
        return null;
    }

	@Override
	public void displayLoanBooks() {
		// TODO Auto-generated method stub
		 System.out.println("Loaned Books:");
	        for (Book book : books) {
	            if (book.isLoaned()) {
	                System.out.println("ID: " + book.getBookID() + ", Title: " + book.getTitle() +" , Author :"+ book.getAuthor());
	            }
	        }
		
		
	}
	
	
	public void displayBooksAlphabetically() {
	    Collections.sort(books, new Comparator<Book>() {
	        @Override
	        public int compare(Book b1, Book b2) {
	            return b1.getTitle().compareToIgnoreCase(b2.getTitle());
	        }
	    });

	    System.out.println("Books in Alphabetical Order:");
	    for (Book book : books) {
	        System.out.println("ID: " + book.getBookID() + ", Title: " + book.getTitle() + ", Author: " + book.getAuthor());
	    }
	}
	

	@Override
	public double calculateLoanFee1(int duration) {
		return 25.5;
	}

	@Override
	public boolean isExtendable1() {
		// TODO Auto-generated method stub
		return false;
	}
	
	

	
}

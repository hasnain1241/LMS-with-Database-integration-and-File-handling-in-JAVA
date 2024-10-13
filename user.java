package LMS;

import java.util.ArrayList;
import java.util.List;

public abstract class user {
	 private static int nextUserId = 1;
    private int usersID;
    private String name;
    private String email;
    private String phoneNumber;
    private String address;
    private List<Book> loanedBooks;
    private double totalLoanFees;

    // Constructor
    public user( String name, String email, String phoneNumber, String address) {
        this.usersID = nextUserId++;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.loanedBooks = new ArrayList<>();
        this.totalLoanFees = 0.0;
    }
    
    public user(int id, String name, String email, String phoneNumber, String address) {
        this.usersID = id;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.loanedBooks = new ArrayList<>();
        this.totalLoanFees = 0.0;
    }
    
    public static void setNextUserID(int nextID) {
    	nextUserId = nextID;
    }


    // Getters
    public int getusersID() {
        return usersID;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public List<Book> getLoanedBooks() {
        return loanedBooks;
    }

    public double getTotalLoanFees() {
        return totalLoanFees;
    }

    // Method to loan a book
    public void loanBook(Book book) {
        if (!loanedBooks.contains(book)) {
            loanedBooks.add(book);
            book.setLoanStatus(true);  // Mark the book as loaned
        }
    }

    // Method to return a book
    public void returnBook(Book book) {
        if (loanedBooks.contains(book)) {
            loanedBooks.remove(book);
            book.setLoanStatus(false);  // Mark the book as available
        }
    }
    
    public void addLoanedBook(Book book, double loanFee) {
        loanedBooks.add(book);
        totalLoanFees += loanFee;
    }

    // Method to update total loan fees
    public void addLoanFee(double fee) {
        this.totalLoanFees += fee;
    }
    
    public void displayLoanedBooks() {
        System.out.println("Loaned Books:");
        for (Book book : loanedBooks) {
            System.out.println("Title: " + book.getTitle() + ", ID: " + book.getBookID());
        }
    }

    // Abstract method to define in subclasses (limit for loaning books)
    public abstract int getMaxLoanLimit();
    
    
    public void saveUser(PersistenceHandler persHandler) {
        persHandler.insertUser(this);  // Delegating the insertion to the handler
    }
}





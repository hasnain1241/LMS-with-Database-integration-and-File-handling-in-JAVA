package LMS;

public abstract class Book {
    private static int nextBookID = 1; // Static variable to generate unique book IDs
    private int bookID;
    private String title;
    private String author;
    private String isbn;
    private int publicationYear;
    private String genre;
    private boolean isLoaned;
    private double baseLoanFee;

    // Constructor
    public Book(String title, String author, String isbn, int publicationYear, String genre, double baseLoanFee) {
        this.bookID = nextBookID++;  // Assign a unique book ID and increment the counter
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.publicationYear = publicationYear;
        this.genre = genre;
        this.baseLoanFee = baseLoanFee;
        this.isLoaned = false;
    }
    
    public Book(int id,String title, String author, String isbn, int publicationYear, String genre, double baseLoanFee) {
        this.bookID = id; // Assign a unique book ID and increment the counter
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.publicationYear = publicationYear;
        this.genre = genre;
        this.baseLoanFee = baseLoanFee;
        this.isLoaned = false;
    }
    
 // Setter for nextBookID (used when loading books from the database)
    public static void setNextBookID(int nextID) {
        nextBookID = nextID;
    }


    // Getters and Setters
    public int getBookID() {
        return bookID;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getIsbn() {
        return isbn;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    public String getGenre() {
        return genre;
    }

    public boolean isLoaned() {
        return isLoaned;
    }

    public void setLoanStatus(boolean isLoaned) {
        this.isLoaned = isLoaned;
    }

    public double getBaseLoanFee() {
        return baseLoanFee;
    }
    
    
    public void save(PersistenceHandler persHandler) {
        persHandler.insertBook(this);  // Delegating the insertion to the handler
    }
    
    public String toString() {
        return "Book[ID=" + this.bookID + ", Title=" + this.title + ", Author=" + this.author +
               ", ISBN=" + this.isbn + ", Year=" + this.publicationYear + ", Genre=" + this.genre +
               ", Loaned=" + this.isLoaned + "]";
    }
   

    // Abstract method for loan calculation
    public abstract double calculateLoanFee(int duration);

    // Abstract method for extendable status
    public abstract boolean isExtendable();
}

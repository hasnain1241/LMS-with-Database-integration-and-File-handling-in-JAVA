package LMS;

public class Novel extends Book {

    public Novel(String title, String author, String isbn, int publicationYear, String genre, double baseLoanFee) {
        super(title, author, isbn, publicationYear, genre, baseLoanFee);
    }

    @Override
    public double calculateLoanFee(int duration) {
        return getBaseLoanFee(); // Flat rate for Novel loans
    }

    @Override
    public boolean isExtendable() {
        return false;
    }
}

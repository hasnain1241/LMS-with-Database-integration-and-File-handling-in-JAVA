package LMS;

public class ReferenceBook extends Book {

    public ReferenceBook( String title, String author, String isbn, int publicationYear, String genre, double baseLoanFee) {
        super( title, author, isbn, publicationYear, genre, baseLoanFee);
    }

    @Override
    public double calculateLoanFee(int duration) {
        return 0; // Reference books cannot be loaned
    }

    @Override
    public boolean isExtendable() {
        return false;
    }
}

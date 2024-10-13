package LMS;

public class Faculty extends user {
    private static final int MAX_LOAN_LIMIT = 10; // Faculty can borrow up to 10 books

    public Faculty( String name, String email, String phoneNumber, String address) {
        super( name, email, phoneNumber, address);
    }
    
    public Faculty( int id,String name, String email, String phoneNumber, String address) {
        super( id,name, email, phoneNumber, address);
    }

    @Override
    public int getMaxLoanLimit() {
        return MAX_LOAN_LIMIT;
    }
}

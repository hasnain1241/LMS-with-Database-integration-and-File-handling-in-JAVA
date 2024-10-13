package LMS;

public class Students extends user {
   
	private static final int MAX_LOAN_LIMIT = 5; // Student can borrow up to 5 books

    public Students( String name, String email, String phoneNumber, String address) {
        super( name, email, phoneNumber, address);
    }
    
    public Students( int id,String name, String email, String phoneNumber, String address) {
        super( id,name, email, phoneNumber, address);
    }

    @Override
    public int getMaxLoanLimit() {
        return MAX_LOAN_LIMIT;
    }
}

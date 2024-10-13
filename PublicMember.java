package LMS;

public class PublicMember extends user {
    private static final int MAX_LOAN_LIMIT = 3; // Public Member can borrow up to 3 books
    private static final double HIGHER_BASE_FEE = 1.5; // Higher base fee for public members
    
    // I have used final variables here, because these values would not be changed. 

    public PublicMember(String name, String email, String phoneNumber, String address) {
        super( name, email, phoneNumber, address);
    }
    
    public PublicMember(int id,String name, String email, String phoneNumber, String address) {
        super(  id, name, email, phoneNumber, address);
    }

    @Override
    public int getMaxLoanLimit() {
        return MAX_LOAN_LIMIT;
    }
    
    public double getHigherBaseFee() {
        return HIGHER_BASE_FEE;
    }
}




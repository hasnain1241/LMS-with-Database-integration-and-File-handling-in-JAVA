package LMS;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class LibraryManagementSystem {
    public static void main(String[] args) {
        Library library = new Library();
        
        Scanner scanner = new Scanner(System.in);
        
        
        PersistenceHandler handler = new SQLHandler();  
        PersistenceHandler handler1 = new FileHandler(); 
        
        
       
        
        
        
        List<Book> booksFromDB = handler.getAllBooks();
        for (Book book : booksFromDB) {
            library.addBook(book);
        }
        
        System.out.println("Library loaded with books from the database.");
        
        List<user> usersFromDB = handler.getAllUsers();
        for (user User : usersFromDB) {
            library.adduser(User);
        }
        
        System.out.println("Library loaded with Users from the database.");
        
        
        

        // Pre-populate some books
        
        /*
        
        // Below can be taken as an example of polymorphism, one book many types....
        Book textbook = new Textbook( "SDA BY ABR", "ABR USMAN", "ISBN12345", 2022, "Education", 50.0);
      
     // textbook.save(handler1); 
        
        Book novel = new Novel("The Great Grizzly", "F. Ferrari King", "ISBN54321", 1990, "Fiction", 20.0);
       // novel.save(handler1); 
        Book referenceBook = new ReferenceBook("Numerical Computing", "Imran Ashraf", "ISBN67890", 2015, "Reference", 0);
       // referenceBook.save(handler1); 
       
        library.addBook(textbook);
        
        library.addBook(novel);
        
        library.addBook(referenceBook);*/
        

        // Pre-populate some users
        
        /*
        user Students = new Students( "Hasnain", "Hasnain@gmail.com", "123456789", "123 F-10 St13");
        
      
        
        user faculty = new Faculty( "Dr. Jawad", "djawad@nu.edu.com", "987654321", "456 G13 St");
        
       
        
        user publicMember = new PublicMember("Bilal Raza P", "P.Bilal.B@gmailPublic.com", "555555555", "789 D-12 St");
       
        
        library.adduser(Students);
        
        library.adduser(faculty);
        
        library.adduser(publicMember);
        */
        
        int FileChoice;
        

        while (true) 
        {
        	
            System.out.println("\n--- Library Management System ---");
            
            System.out.println("\nChoose your mode of File Saving");
            
            System.out.println("before you choose remember File will show the all time data when u insert a user while DB handles all the other work");
            
            System.out.println("\n 1.Db \n 2. File");
            
            FileChoice=scanner.nextInt();
            
            
            System.out.println("1. Add new book");
            
            System.out.println("2. Display available books");
            
            System.out.println("3. Remove a book");
            
            System.out.println("4. Add new user");
            
            System.out.println("5. Display users");
            
            System.out.println("6. Loan a book");
            
            System.out.println("7. Return a book");
            
            System.out.println("8. Display loan details");
            
            System.out.println("9. Exit");
            
            System.out.println("10. Display loan books");
            
            System.out.println("11. Remove a User");
            
            System.out.println("12. Display Books Alphabatically");
            
            System.out.println("13. Read From File For both user and book");
            
            System.out.println("14. Extension of a Books Loan");
            
            
            System.out.print("Choose an option: ");
            
            int choice = scanner.nextInt();
            

            switch (choice) {
                case 1:
                    // Add new book
                    System.out.println("Enter Book Type (1 for Textbook, 2 for Novel, 3 for ReferenceBook): ");
                    int bookType = scanner.nextInt();
                   
                    scanner.nextLine(); // consume newline
                    System.out.println("Enter Book Title: ");
                    String title = scanner.nextLine();
                    System.out.println("Enter Author: ");
                    String author = scanner.nextLine();
                    System.out.println("Enter ISBN: ");
                    String isbn = scanner.nextLine();
                    System.out.println("Enter Publication Year: ");
                    int year = scanner.nextInt();
                    scanner.nextLine(); // consume newline
                    System.out.println("Enter Genre: ");
                    String genre = scanner.nextLine();
                    System.out.println("Enter Base Loan Fee: ");
                    double fee = scanner.nextDouble();

                    Book newBook;
                    if (bookType == 1) {
                        newBook = new Textbook( title, author, isbn, year, genre, fee);
                        if(FileChoice==1)
                        newBook.save(handler);
                        else
                        	newBook.save(handler1);
                        
                    } else if (bookType == 2) {
                        newBook = new Novel(title, author, isbn, year, genre, fee);
                        if(FileChoice==1)
                            newBook.save(handler);
                            else
                            	newBook.save(handler1);
                    } else {
                        newBook = new ReferenceBook(title, author, isbn, year, genre, fee);
                        if(FileChoice==1)
                            newBook.save(handler);
                            else
                            	newBook.save(handler1);
                    }
                    library.addBook(newBook);
                    System.out.println("Book added successfully.");
                    break;

                case 2:
                    // Display available books
                    library.displayAvailableBooks();
                    break;

                case 3:
                    // Remove a book
                    System.out.println("Enter Book ID to remove: ");
                    int removeBookID = scanner.nextInt();
                    //handler.deleteBook(removeBookID);
                    library.removeBook(removeBookID);
                    
                    if(library.findBook(removeBookID)==null)
                    {
                    	handler.deleteBook(removeBookID);
                    }
                    
                    else
                    	continue;
                   
                    break;

                case 4:
                    // Add new user
                    System.out.println("Enter user Type (1 for Students, 2 for Faculty, 3 for Public Member): ");
                    int userType = scanner.nextInt();
                   
                    scanner.nextLine(); // consume newline
                    System.out.println("Enter Name: ");
                    String name = scanner.nextLine();
                    System.out.println("Enter Email: ");
                    String email = scanner.nextLine();
                    System.out.println("Enter Phone Number: ");
                    String phoneNumber = scanner.nextLine();
                    System.out.println("Enter Address: ");
                    String address = scanner.nextLine();

                    user newuser;
                    if (userType == 1) {
                        newuser = new Students( name, email, phoneNumber, address);
                        if(FileChoice==1)
                            newuser.saveUser(handler);
                            else
                            	newuser.saveUser(handler1);
                       
                        
                    } else if (userType == 2) {
                        newuser = new Faculty(name, email, phoneNumber, address);
                        
                        if(FileChoice==1)
                            newuser.saveUser(handler);
                            else
                            	newuser.saveUser(handler1);
                        
                    } else {
                        newuser = new PublicMember( name, email, phoneNumber, address);
                        
                        if(FileChoice==1)
                            newuser.saveUser(handler);
                            else
                            	newuser.saveUser(handler1);
                        
                    }
                    library.adduser(newuser);
                    System.out.println("user added successfully.");
                    break;

                case 5:
                    // Display users
                    library.displayusers();
                    break;

                case 6:
                    // Loan a book
                    System.out.println("Enter user ID: ");
                    int loanuserID = scanner.nextInt();
                    System.out.println("Enter Book ID: ");
                    int loanBookID = scanner.nextInt();
                    System.out.println("Enter Loan Duration (in days): ");
                    int loanDuration = scanner.nextInt();
                    library.loanBook(loanuserID, loanBookID, loanDuration);
                   handler.updateBook(loanBookID, true);
                   
                   Book b1=library.findBook(loanBookID);
                   double p1=b1.getBaseLoanFee();
                   p1=p1*loanDuration;
                   handler.updateUser(loanuserID, p1);
                   
                   
                   handler.logLoanTransaction(loanBookID, loanuserID, loanDuration);
                    
                    break;

                case 7:
                    // Return a book
                    System.out.println("Enter user ID: ");
                    int returnuserID = scanner.nextInt();
                    System.out.println("Enter Book ID: ");
                    int returnBookID = scanner.nextInt();
                    System.out.println("Enter Number of Days Late (if any): ");
                    int daysLate = scanner.nextInt();
                    library.returnBook(returnuserID, returnBookID, daysLate);
                    handler.updateBook(returnBookID, false);
                    
                    String returnDate = LocalDate.now().toString();
                    
                    handler.logReturnTransaction(returnBookID, returnuserID, returnDate);
                    
                    break;

                case 8:
                    // Display loan details
                    System.out.println("Enter user ID: ");
                    int loanDetailsuserID = scanner.nextInt();
                    library.displayLoanDetails(loanDetailsuserID);
                    break;
                    
                case 10:
                	library.displayLoanBooks();
                	break;

                case 9:
                    // Exit
                    System.out.println("Exiting the system.");
                    scanner.close();
                    handler.closeConnection();
                    return;
                    
                    
                case 11:
                	System.out.println("Enter user ID: ");
                    int userID = scanner.nextInt();
                    
                    library.removeUser(userID);
                    
                    if(library.finduser(userID)==null)
                    {
                    	handler.deleteUser(userID);
                    	
                    }
                    
                    else
                    	continue;
                    break;
                    
                case 12:
                	library.displayBooksAlphabetically();
                	break;
                	
                case 13:
                	System.out.println("Books File ");
                	handler1.readBooksFromFile();
                	
                	System.out.println("Users File ");
                	handler1.readUsers();
                	
                	break;
                	
                	
                case 14:
                	 System.out.println("Enter user ID: ");
                     int returnuserID1 = scanner.nextInt();
                     System.out.println("Enter Book ID: ");
                     int returnBookID1 = scanner.nextInt();
                     System.out.println("Enter Number of Days Extension");
                     int daysLate1 = scanner.nextInt();
                     
                     handler.UpdateTransaction(returnBookID1, returnuserID1, daysLate1);

                     break;
                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
            
           
           
        }
      
        
    }
    
}

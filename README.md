# Library Management System with DB INTERGRATION

This project is a **Library Management System** built in Java, designed to handle books, users, and loan transactions. The system offers two types of storage mechanisms: **Database Storage** (using MySQL) and **File-based Storage** (text files). The program is flexible, allowing users to choose between these storage options at runtime.

## Features:
- Add, remove, and display books and users
- Loan and return books
- Extend loan durations
- Keep track of transactions
- Support for both DB and file-based data storage

## Technologies Used:
- Java
- MySQL (Database)
- File handling (Text files)

## How to Run:
1. Clone this repository.
2. Set up MySQL with the appropriate tables (refer to schema).
3. Modify the database connection settings in the `SQLHandler` class.
4. Compile and run the program in your favorite IDE or command line.

## Usage:
Upon running the program, you'll be prompted to select a storage method (either **DB** or **File-based**). Based on your selection, the data will either be stored in the database or in text files. You can manage the library’s books and users, handle loan transactions, and more.

## Future Enhancements:
- Add graphical user interface (GUI)
- Add user authentication for library staff

Feel free to fork this project and contribute!

### Author:
Hassan (Your Name Here)


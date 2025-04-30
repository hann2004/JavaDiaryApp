Personal Diary App - Java & PostgreSQL
A simple command-line diary application built with Java and PostgreSQL, allowing users to create, view, and delete diary entries.

📌 Features
✅ Add new entries – Save diary entries with a title and content.
✅ View all entries – List all saved entries with IDs, titles, dates, and content.
✅ View single entry – Fetch a specific entry by its ID.
✅ Delete entries – Remove entries by their ID.
✅ User-friendly menu – Navigate easily with a numbered menu system.
✅ Error handling – Validates user input and handles database errors.

⚙️ Prerequisites
Before running the app, ensure you have:

Java JDK 8+ 

PostgreSQL 

PostgreSQL JDBC Driver (Add to your project if not using Maven/Gradle)

🚀 Setup & Run
1. Database Setup
Create a PostgreSQL database:

sql
CREATE DATABASE Personal_diary;
Create the diary_entries table:

sql
CREATE TABLE diary_entries (
    id SERIAL PRIMARY KEY,
    title VARCHAR(100) NOT NULL,
    content TEXT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
2. Configure Database Connection
Modify the connection details in Main.java:

java
static String url = "jdbc:postgresql://localhost:5432/Personal_diary";
static String user = "postgres";  // Your PostgreSQL username
static String password = "your_password";  // Your PostgreSQL password
3. Run the Application
Compile & execute:

bash
javac Main.java
java Main
📋 Usage
Main Menu Options

1 → Add a new diary entry.

2 → View all entries.

3 → View a single entry by ID.

4 → Delete an entry by ID.

5 → Exit the app.

After Each Operation

Press # to return to the main menu.

Press * to exit.

🔧 Future Improvements
User authentication (login system).

Search functionality (filter entries by keyword).

Edit existing entries.

Export entries to PDF/text file.

📦PersonalDiaryApp
├── 📜Main.java            # Main application logic
├── 📜README.md           # Documentation
└── 📜postgres-setup.sql  # SQL for DB setup (optional)
📜 License
This project is open-source under the MIT License

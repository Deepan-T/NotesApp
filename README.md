# NotesApp (Java)  

A simple console-based notes application in Java that demonstrates basic **file I/O** using `FileWriter` and `FileReader` / `BufferedReader`.

---

## 📝 Overview

This application allows the user to:

- Add a new note (which is appended to a text file)  
- List all previously added notes (by reading the file)  
- Exit the application  

Each note is stored as a plain text line in a file (`notes.txt`).  

---

## 📂 How It Works — Code Explanation

### 1. File for storage

- We use a file (e.g. `notes.txt`) to persist user notes across runs.
- When the file doesn’t exist yet, writing in append mode will create it.

### 2. Writing a note — `FileWriter` + `BufferedWriter`

In the `addNote(...)` method:

```java
try (FileWriter fw = new FileWriter(NOTES_FILE, true);
     BufferedWriter bw = new BufferedWriter(fw)) {
    bw.write(note);
    bw.newLine();
    // (auto-closes bw and fw)
}
. new FileWriter(NOTES_FILE, true) opens the file in append mode (so existing content is preserved).

. Wrapping in BufferedWriter helps reduce the number of I/O operations by buffering writes internally. 

. bw.newLine() writes a platform-specific newline after the note.

. The try-with-resources block ensures the writer is closed automatically, even if exceptions occur.

3. Reading all notes — FileReader + BufferedReader
In the listNotes() method:

java
Copy code
try (FileReader fr = new FileReader(file);
     BufferedReader br = new BufferedReader(fr)) {
    String line;
    while ((line = br.readLine()) != null) {
        // print note
    }
}
. FileReader is a character stream class for reading text files.

. BufferedReader wraps it to read lines efficiently (via readLine()), instead of character-by-character. 

. The loop reads each line until null is encountered (end of file).

. Again, try-with-resources ensures proper closing.

4. The main interaction loop
. The main(...) method uses a simple menu-driven loop:

. Display menu: Add / List / Exit

. Read user’s choice

. Call addNote(...) or listNotes() accordingly

. Exit when user selects the exit option

. This keeps the app interactive in the console.

✅ Key Points & Best Practices
. Always match a public class name with its filename (e.g. NotesApp.java for public class NotesApp).

. Use append mode (FileWriter(..., true)) when you want to add on to existing file contents rather than overwrite.

. Wrap streams in buffered variants (BufferedWriter, BufferedReader) to improve performance by reducing frequent disk I/O. 

. Use try-with-resources to automatically close I/O streams and avoid resource leaks.

. Handle IOException (or related exceptions) to make the program robust.

📦 How to Run (for README)
. Clone/download the repository.

. Ensure the JDK is installed and environment variables (javac, java) are set.

. Open terminal / command prompt in the directory containing NotesApp.java.

Compile:
javac NotesApp.java
Run:
java NotesApp
Interact via the console menu: choose 1 to add note, 2 to list notes, 3 to exit.

🏷 What You Can Improve / Extend
. Add editing or deleting notes

. Store structured data (with timestamps, titles) instead of just plain lines

. Use JSON, CSV, or serialized objects to represent more complex note data

. Add search or filtering for notes

. Build a GUI (Swing / JavaFX) or a web front end

. Handle concurrency if multiple users or threads access notes simultaneously

📚 References & Further Reading
. Java’s FileWriter / FileReader and BufferedWriter / BufferedReader classes provide character-based I/O for text. 
Cratecode

. Buffered streams reduce the number of I/O calls by buffering internally, improving performance. 


Try-with-resources (Java 7+) is the recommended pattern for safe handling of closable resources.


import java.io.*;
import java.util.Scanner;

public class NotesApp {
    private static final String NOTES_FILE = "notes.txt";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("\n--- Notes App ---");
            System.out.println("1. Add Note");
            System.out.println("2. List Notes");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            String choice = sc.nextLine();

            switch (choice) {
                case "1":
                    addNote(sc);
                    break;
                case "2":
                    listNotes();
                    break;
                case "3":
                    System.out.println("Exiting. Bye!");
                    sc.close();
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private static void addNote(Scanner sc) {
        System.out.print("Enter your note: ");
        String note = sc.nextLine();
        try {
            // true => append mode, so existing notes aren't overwritten
            try (FileWriter fw = new FileWriter(NOTES_FILE, true);
                 BufferedWriter bw = new BufferedWriter(fw)) {
                bw.write(note);
                bw.newLine();
                System.out.println("Note saved.");
            }
        } catch (IOException e) {
            System.err.println("Error writing note: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void listNotes() {
        File file = new File(NOTES_FILE);
        if (!file.exists()) {
            System.out.println("No notes yet.");
            return;
        }
        try (FileReader fr = new FileReader(file);
             BufferedReader br = new BufferedReader(fr)) {
            System.out.println("\nYour notes:");
            String line;
            int idx = 1;
            while ((line = br.readLine()) != null) {
                System.out.printf("%d) %s%n", idx, line);
                idx++;
            }
        } catch (IOException e) {
            System.err.println("Error reading notes: " + e.getMessage());
            e.printStackTrace();
        }
    }
}

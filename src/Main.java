import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        journalApp();
    }

    public static void journalApp() {
        boolean running = true;
        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to Journal Application");
        System.out.print("Enter filename: ");
        String filename = sc.nextLine();
        JournalManager journalManager = new JournalManager(filename);

        while(running) {
            System.out.println("1. Add new Entry");
            System.out.println("2. Read Entries");
            System.out.println("3. Delete Entry");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1 -> {
                    System.out.println("Type 'exit' to exit");
                    System.out.print("Enter your notes: ");
                    String notes = sc.nextLine();
                    if(notes.equals("exit")) {
                        running = false;
                        break;
                    }
                    if (notes.isBlank() || notes.isEmpty()) {
                        System.out.println("Notes cannot be empty");
                        break;
                    }
                    journalManager.writeEntry(new JournalEntry(notes));
                    System.out.println("Entry added successfully\n");
                }
                case 2 -> {
                    List<JournalEntry> entries = journalManager.readEntries();
                    for (JournalEntry entry : entries) {
                        System.out.println(entry);
                    }
                    System.out.println();
                }
                case 3 -> {
                    System.out.println("Type 'exit' to exit");
                    System.out.println("Timestamp format: yyyy/MM/dd HH:mm:ss");
                    System.out.print("Enter timestamp to delete: ");
                    String timestamp = sc.nextLine();
                    if (timestamp.equals("exit")) {
                        running = false;
                        break;
                    }
                    if (timestamp.isBlank() || timestamp.isEmpty()) {
                        System.out.println("Timestamp cannot be empty");
                        break;
                    }
                    journalManager.deleteEntry(LocalDateTime.parse(timestamp, DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss")));
                    System.out.println("Entry deleted successfully\n");
                }
                case 4 -> running = false;
            }
        }
    }


}
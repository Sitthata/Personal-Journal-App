import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


public class JournalManager {
    private final File journalFile;

    public JournalManager(String filename) {
        journalFile = new File(filename);
    }

    public void writeEntry(JournalEntry entry) {
        try (FileWriter writer = new FileWriter(journalFile, true);
             BufferedWriter bw = new BufferedWriter(writer)) {
            // String builder
            StringBuilder sb = new StringBuilder();
            sb.append(entry.getTimestamp().format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss")) + " - ")
                    .append(entry.getNotes()).append('\n');
            // Write File
            bw.write(sb.toString());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public List<JournalEntry> readEntries() {
        List<JournalEntry> entries = new ArrayList<>();

        try (FileReader reader = new FileReader(journalFile);
             BufferedReader br = new BufferedReader(reader)) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("-");
                if (parts.length >= 2) {
                    String timestamp = parts[0].trim();
                    String notes = parts[1].trim();
                    // parse timestamp
                    LocalDateTime dateTime = LocalDateTime.parse(timestamp, DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"));
                    entries.add(new JournalEntry(notes, dateTime));
                }
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return entries;
    }


    public void deleteEntry(LocalDateTime timestamp) {
        List<JournalEntry> entries = readEntries();
        entries.removeIf(entry -> entry.getTimestamp().equals(timestamp));
        entries.sort(Comparator.comparing(JournalEntry::getTimestamp));
        clearFile();
        for (JournalEntry entry : entries) {
            this.writeEntry(entry);
        }
    }

    private void clearFile() {
        try (FileWriter writer = new FileWriter(journalFile);
        BufferedWriter bw = new BufferedWriter(writer)) {
            bw.write("");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}

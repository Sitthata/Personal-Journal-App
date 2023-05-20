import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class JournalEntry {
    private final LocalDateTime timestamp;
    private final String notes;

    public JournalEntry(String notes) {
        this.timestamp = LocalDateTime.now();
        this.notes = notes;
    }

    public JournalEntry(String notes, LocalDateTime timestamp) {
        this.timestamp = timestamp;
        this.notes = notes;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public String getNotes() {
        return notes;
    }

    @Override
    public String toString() {
        return timestamp.format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss")) + " - " + notes;
    }
}

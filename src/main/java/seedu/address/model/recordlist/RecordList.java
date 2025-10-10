package seedu.address.model.recordlist;

import java.util.ArrayList;

/**
 * Represents the list of student records.
 */
public class RecordList {
    private static final int WEEKS = 13;
    private final ArrayList<Record> records;

    public RecordList() {
        this.records = new ArrayList<>();
    }
}

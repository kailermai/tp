package seedu.address.model.recordlist;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the list of student records.
 */
public class RecordList {
    private static final int WEEKS = 13;
    public final List<Record> records;

    public RecordList() {
        this.records = new ArrayList<>();
    }

    public RecordList(List<Record> records) {
        this.records = records;
    }
}

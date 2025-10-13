package seedu.address.model.recordlist;

import java.util.ArrayList;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.model.record.Record;
import seedu.address.model.record.WeekNumber;

/**
 * Represents the list of student records.
 */
public class RecordList {
    private static final int WEEKS = 13;
    public final List<Record> records;

    /**
     * Constructs a {@code RecordList} object with an empty list of records.
     * The list is initialized to have a size equal to the maximum number of weeks in a typical semester,
     * as defined by {@code WeekNumber.MAX_WEEK_NUMBER}.
     * Each element in the list is initially set to {@code null}.
     */
    public RecordList() {
        ArrayList<Record> records = new ArrayList<>();
        for (int i = 0; i < WeekNumber.MAX_WEEK_NUMBER; i++) {
            records.add(null);
        }

        this.records = records;
    }

    public RecordList(List<Record> records) {
        this.records = records;
    }

    public Record getRecord(Index index) {
        return records.get(index.getZeroBased());
    }

    public void setRecord(Index index, Record record) {
        records.set(index.getZeroBased(), record);
    }
}

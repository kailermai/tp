package seedu.address.model.recordlist;

import seedu.address.commons.core.index.Index;
import seedu.address.model.record.Record;
import seedu.address.model.record.WeekNumber;

/**
 * Represents the list of student records.
 */
public class RecordList {
    public final Record[] records;

    /**
     * Constructs a {@code RecordList} object with an empty list of records.
     * The list is initialized to have a size equal to the maximum number of weeks in a typical semester,
     * as defined by {@code WeekNumber.MAX_WEEK_NUMBER}.
     * Each element in the list is initially {@code null}.
     */
    public RecordList() {
        this.records = new Record[WeekNumber.MAX_WEEK_NUMBER];
    }

    public RecordList(Record[] records) {
        this.records = records;
    }

    public Record getRecord(Index index) {
        return records[index.getZeroBased()];
    }

    public void setRecord(Index index, Record record) {
        records[index.getZeroBased()] = record;
    }
}

package seedu.address.model.recordlist;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.function.Function;

import seedu.address.commons.core.index.Index;
import seedu.address.model.record.Record;
import seedu.address.model.record.WeekNumber;

/**
 * Represents the list of student records.
 */
public class RecordList {
    public final Record[] records;

    private final PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

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
        int idx = index.getZeroBased();
        records[idx] = record;
        // notify listeners that a record changed (payload is the index)
        propertyChangeSupport.firePropertyChange("recordChanged", null, idx);
    }

    /**
    * Returns an array of scores for each week in the record list.
    * The array is filled with null values if the corresponding week has no record.
    * @param func the function to apply to each record to get the score
    * @return an array of scores for each week in the record list
    */
    public Integer[] getScores(Function<Record, Integer> func) {
        Integer[] scores = new Integer[WeekNumber.MAX_WEEK_NUMBER];

        for (int i = 0; i < this.records.length; i++) {
            if (records[i] == null) {
                continue;
            }
            scores[i] = func.apply(records[i]);
        }

        return scores;
    }

    public RecordList clone() {
        return new RecordList(this.records.clone());
    }

    public void addChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.addPropertyChangeListener(listener);
    }

    public void removeChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.removePropertyChangeListener(listener);
    }
}

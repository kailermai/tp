package seedu.address.model.recordlist;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static seedu.address.logic.commands.CommandTestUtil.VALID_RECORD_AMY;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.model.record.Record;
import seedu.address.model.record.WeekNumber;

public class RecordListTest {

    @Test
    public void getScores_emptyRecordList_emptyIntegerArray() {
        RecordList recordList = new RecordList();
        Integer[] scores = recordList.getScores(record -> 100);

        for (Integer score : scores) {
            assertNull(score);
        }
    }

    @Test
    public void getScores_nonEmptyRecordList_correctIntegerArray() {

        Record[] nonEmptyRecords = new Record[WeekNumber.MAX_WEEK_NUMBER];

        Arrays.fill(nonEmptyRecords, VALID_RECORD_AMY);

        RecordList recordList = new RecordList(nonEmptyRecords);
        Integer[] scores = recordList.getScores(record -> 100);

        assertEquals(nonEmptyRecords.length, scores.length);

        for (Integer score : scores) {
            assertEquals(100, score);
        }
    }
}

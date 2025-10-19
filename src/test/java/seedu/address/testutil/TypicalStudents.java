package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_RECORD_LIST_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_RECORD_LIST_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STUDENT_NUMBER_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STUDENT_NUMBER_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TELEGRAM_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TELEGRAM_BOB;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.recordlist.RecordList;
import seedu.address.model.student.Student;

/**
 * A utility class containing a list of {@code Student} objects to be used in tests.
 */
public class TypicalStudents {

    public static final Student ALICE = new StudentBuilder().withName("Alice Pauline")
            .withEmail("alice@example.com").withPhone("94351253").withStudentNumber("A2345678Z")
            .withTags("friends").withRecordList(new RecordList()).withTelegram("alice_pauline").build();
    public static final Student BENSON = new StudentBuilder().withName("Benson Meier")
            .withEmail("johnd@example.com").withPhone("98765432").withStudentNumber("A3456789Z")
            .withTags("owesMoney", "friends").withRecordList(VALID_RECORD_LIST_BOB)
            .withTelegram("benson_meier").build();
    public static final Student CARL = new StudentBuilder().withName("Carl Kurz").withPhone("95352563")
            .withStudentNumber("A4567890Z").withEmail("heinz@example.com")
            .withTelegram("carl_kurz").withRecordList(new RecordList()).build();
    public static final Student DANIEL = new StudentBuilder().withName("Daniel Meier").withPhone("87652533")
            .withStudentNumber("A5678901Z").withEmail("cornelia@example.com")
            .withTelegram("daniel_meier").withTags("friends").withRecordList(new RecordList()).build();
    public static final Student ELLE = new StudentBuilder().withName("Elle Meyer").withPhone("9482224")
            .withStudentNumber("A6789012Z").withEmail("werner@example.com")
            .withTelegram("elle_meyer").withRecordList(new RecordList()).build();
    public static final Student FIONA = new StudentBuilder().withName("Fiona Kunz").withPhone("9482427")
            .withStudentNumber("A7890123Z").withEmail("lydia@example.com")
            .withTelegram("fiona_kunz").withRecordList(new RecordList()).build();
    public static final Student GEORGE = new StudentBuilder().withName("George Best").withPhone("9482442")
            .withEmail("anna@example.com").withStudentNumber("A8901234Z")
            .withTelegram("george_best").withRecordList(new RecordList()).build();

    // Manually added
    public static final Student HOON = new StudentBuilder().withName("Hoon Meier").withPhone("8482424")
            .withEmail("stefan@example.com").withStudentNumber("A9012345Z").withTelegram("hoon_meier")
            .withRecordList(new RecordList()).build();
    public static final Student IDA = new StudentBuilder().withName("Ida Mueller").withPhone("8482131")
            .withEmail("hans@example.com").withStudentNumber("A0123456Y").withTelegram("ida_mueller")
            .withRecordList(new RecordList()).build();

    // Manually added - Student's details found in {@code CommandTestUtil}
    public static final Student AMY = new StudentBuilder().withName(VALID_NAME_AMY).withPhone(VALID_PHONE_AMY)
            .withEmail(VALID_EMAIL_AMY).withTags(VALID_TAG_FRIEND).withTelegram(VALID_TELEGRAM_AMY)
            .withStudentNumber(VALID_STUDENT_NUMBER_AMY).withRecordList(VALID_RECORD_LIST_AMY).build();
    public static final Student BOB = new StudentBuilder().withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
            .withEmail(VALID_EMAIL_BOB).withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND).withTelegram(VALID_TELEGRAM_BOB)
            .withStudentNumber(VALID_STUDENT_NUMBER_BOB).withRecordList(new RecordList()).build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalStudents() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical students.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Student student : getTypicalStudents()) {
            ab.addStudent(student);
        }
        return ab;
    }

    public static List<Student> getTypicalStudents() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}

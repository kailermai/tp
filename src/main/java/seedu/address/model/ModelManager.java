package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.Arrays;
import java.util.Comparator;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.core.index.Index;
import seedu.address.model.record.Record;
import seedu.address.model.student.Student;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final AddressBook addressBook;
    private final UserPrefs userPrefs;
    private final FilteredList<Student> filteredStudents;

    /**
     * Initializes a ModelManager with the given addressBook and userPrefs.
     */
    public ModelManager(ReadOnlyAddressBook addressBook, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(addressBook, userPrefs);

        logger.fine("Initializing with address book: " + addressBook + " and user prefs " + userPrefs);

        this.addressBook = new AddressBook(addressBook);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredStudents = new FilteredList<>(this.addressBook.getStudentList());
    }

    public ModelManager() {
        this(new AddressBook(), new UserPrefs());
    }

    //=========== UserPrefs ==================================================================================

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    @Override
    public Path getAddressBookFilePath() {
        return userPrefs.getAddressBookFilePath();
    }

    @Override
    public void setAddressBookFilePath(Path addressBookFilePath) {
        requireNonNull(addressBookFilePath);
        userPrefs.setAddressBookFilePath(addressBookFilePath);
    }

    //=========== AddressBook ================================================================================

    @Override
    public void setAddressBook(ReadOnlyAddressBook addressBook) {
        this.addressBook.resetData(addressBook);
    }

    @Override
    public ReadOnlyAddressBook getAddressBook() {
        return addressBook;
    }

    @Override
    public boolean hasStudent(Student student) {
        requireNonNull(student);
        return addressBook.hasStudent(student);
    }

    @Override
    public void deleteStudent(Student target) {
        addressBook.removeStudent(target);
    }

    @Override
    public void addStudent(Student student) {
        addressBook.addStudent(student);
        updateFilteredStudentList(PREDICATE_SHOW_ALL_STUDENTS);
    }

    @Override
    public void setStudent(Student target, Student editedStudent) {
        requireAllNonNull(target, editedStudent);

        addressBook.setStudent(target, editedStudent);
    }

    //=========== Filtered Student List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Student} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Student> getFilteredStudentList() {
        return filteredStudents;
    }

    @Override
    public void updateFilteredStudentList(Predicate<Student> predicate) {
        requireNonNull(predicate);
        filteredStudents.setPredicate(predicate);
    }

    //=========== Sorting students =============================================================
    @Override
    public void sortStudentByAttendance() {
        ObservableList<Student> studentList = addressBook.getModifiableStudentList();
        studentList.sort(Comparator
                .comparingDouble(this::getAttendancePercentage)
                .reversed());
    }

    @Override
    public void sortStudentByParticipation() {
        ObservableList<Student> studentList = addressBook.getModifiableStudentList();
        studentList.sort(Comparator
                .comparingDouble(this::getParticipationPercentage)
                .reversed());
    }

    @Override
    public void sortStudentBySubmission() {
        ObservableList<Student> studentList = addressBook.getModifiableStudentList();
        studentList.sort(Comparator
                .comparingDouble(this::getSubmissionPercentage)
                .reversed());
    }


    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ModelManager)) {
            return false;
        }

        ModelManager otherModelManager = (ModelManager) other;
        return addressBook.equals(otherModelManager.addressBook)
                && userPrefs.equals(otherModelManager.userPrefs)
                && filteredStudents.equals(otherModelManager.filteredStudents);
    }

    /**
     * Calculates the sum of all attendance scores for a given student.
     * Returns -Double.MIN_VALUE if the student or their record list is null.
     */
    private double getTotalAttendance(Student student) {
        seedu.address.model.record.Record[] records = student.getRecordList().records;
        return Arrays.stream(records)
                .mapToDouble(record -> {
                    try {
                        return record.getAttendanceScore();
                    } catch (NullPointerException e) {
                        return -Double.MIN_VALUE;
                    }
                })
                .sum();
    }

    /**
     * Calculates the sum of all participation scores for a given student.
     * Returns -Double.MIN_VALUE if the student or their record list is null.
     */
    private double getTotalParticipation(Student student) {
        seedu.address.model.record.Record[] records = student.getRecordList().records;
        return Arrays.stream(records)
                .mapToDouble(record -> {
                    try {
                        return record.getParticipationScore();
                    } catch (NullPointerException e) {
                        return -Double.MIN_VALUE;
                    }
                })
                .sum();
    }

    /**
     * Calculates the sum of all submission scores for a given student.
     * Returns -Double.MIN_VALUE if the student or their record list is null.
     */
    private double getTotalSubmission(Student student) {
        seedu.address.model.record.Record[] records = student.getRecordList().records;
        return Arrays.stream(records)
                .mapToDouble(record -> {
                    try {
                        return record.getSubmissionScore();
                    } catch (NullPointerException e) {
                        return -Double.MIN_VALUE;
                    }
                })
                .sum();
    }

    /**
     * Calculates the total number of valid records of a student.
     */
    private int getTotalRecord(Student student) {
        int totalRecord = 0;
        for (int i = 0; i < student.getRecordList().records.length; i++) {
            Index currentIndex = Index.fromZeroBased(i);
            Record record = student.getRecordList().getRecord(currentIndex);
            if (record == null) {
                continue;
            }
            totalRecord += 1;
        }
        return totalRecord;
    }

    /**
     * Calculates the attendance percentage of a student.
     */
    private double getAttendancePercentage(Student student) {
        double totalAttendance = this.getTotalAttendance(student);
        int totalRecord = this.getTotalRecord(student);
        return totalRecord == 0 ? 0 : totalAttendance / totalRecord;
    }

    /**
     * Calculates the participation percentage of a student.
     */
    private double getParticipationPercentage(Student student) {
        double totalParticipation = this.getTotalParticipation(student);
        int totalRecord = this.getTotalRecord(student);
        return totalRecord == 0 ? 0 : totalParticipation / totalRecord;
    }

    /**
     * Calculates the submission percentage of a student.
     */
    private double getSubmissionPercentage(Student student) {
        double totalSubmission = this.getTotalSubmission(student);
        int totalRecord = this.getTotalRecord(student);
        return totalRecord == 0 ? 0 : totalSubmission / totalRecord;
    }

}

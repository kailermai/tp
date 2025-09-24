package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_REMARK_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_REMARK_BOB;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.RemarkCommand.MESSAGE_ADD_REMARK_SUCCESS;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.model.person.Remark;

public class RemarkCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute() {
        final Remark remark = new Remark("Some remark");
        Person personToRemark = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        Person newPerson = new Person(personToRemark.getName(), personToRemark.getPhone(),
                personToRemark.getEmail(), personToRemark.getAddress(), personToRemark.getTags(), remark);
        String expectedMessage = String.format(MESSAGE_ADD_REMARK_SUCCESS, Messages.format(newPerson));
        expectedModel.setPerson(personToRemark, newPerson);
        assertCommandSuccess(new RemarkCommand(INDEX_FIRST_PERSON, remark), model,
                expectedMessage, expectedModel);
    }

    @Test
    public void equals() {
        final RemarkCommand standaradCommand = new RemarkCommand(INDEX_FIRST_PERSON, new Remark(VALID_ADDRESS_AMY));

        RemarkCommand commandWithSameValues = new RemarkCommand(INDEX_FIRST_PERSON, new Remark(VALID_ADDRESS_AMY));

        // same values => true
        assertTrue(standaradCommand.equals(commandWithSameValues));

        // same object => true
        assertTrue(standaradCommand.equals(standaradCommand));

        // object != null
        assertFalse(standaradCommand.equals(null));

        // different command => not the same
        assertFalse(standaradCommand.equals(new ClearCommand()));

        // different index
        assertFalse(standaradCommand.equals(new RemarkCommand(INDEX_SECOND_PERSON, new Remark(VALID_REMARK_AMY))));

        // different remark
        assertFalse(standaradCommand.equals(new RemarkCommand(INDEX_FIRST_PERSON, new Remark(VALID_REMARK_BOB))));
    }
}

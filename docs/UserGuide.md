---
layout: page
title: User Guide
---

![Introduction UI](images/IntroductionUI.png)

TAHub is a **desktop application designed for NUS Computer Science teaching assistants to efficiently manage student contacts and monitor engagement**. It is optimised for users who are familiar with Command Line Interface (CLI) applications, while still providing the convenience and clarity of a Graphical User Interface (GUI). For users who type quickly, TAHub enables student management tasks to be completed faster than with traditional GUI-based applications.

### Table of Contents
* Table of Contents
{:toc}

## Guide Usage

<div markdown="block" class="alert alert-info">

**For New Users**

* New to the Command Line Interface (CLI)? Start with the [Quick Start](#quick-start) section.
</div>

<div markdown="block" class="alert alert-success">

**For Amateurs**

* To start navigating the guide, head to the [Table of Contents](#table-of-contents).
</div>

<div markdown="block" class="alert alert-warning">

**For Experts**

* Need a quick overview of the commands? See the [Command summary](#command-summary).
</div>

--------------------------------------------------------------------------------------------------------------------
<div style="page-break-after: always;"></div>

## Quick start

1. Ensure you have Java `17` or above installed in your Computer.<br>
   **Mac users:** Ensure you have the precise JDK version prescribed [here](https://se-education.org/guides/tutorials/javaInstallationMac.html).

2. Download the latest `.jar` file from [here](https://github.com/AY2526S1-CS2103T-T16-2/tp/releases).

3. Copy the file to the folder you want to use as the _home folder_ for your TAHub.

4. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar tahub.jar` command to run the application.<br>
   A GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

5. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * `list` : Lists all students.

   * `add n/John Doe sn/A1234567J p/98765432 e/johnd@example.com tele/john_doe` : Adds a student named `John Doe` to TAHub.

   * `delete 3` : Deletes the 3rd student shown in the current list.

   * `clear` : Deletes all students.

   * `exit` : Exits the app.

6. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.

* Items in square brackets are optional.<br>
  e.g. `n/NAME [t/TAG]` can be used as `n/John Doe t/friend` or as `n/John Doe`.

* Items in curly braces mean “choose exactly one”.
  e.g. `sort {/a|/p|/s}` can be used as `sort /a`, `sort /p` or as `sort /s`.

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[t/TAG]…​` can be used as ` ` (i.e. 0 times), `t/friend`, `t/friend t/family` etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit`, `trend` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

* If you are using a PDF version of this document, be careful when copying and pasting commands that span multiple lines as space characters surrounding line-breaks may be omitted when copied over to the application.
</div>

<div markdown="block" class="alert alert-info">

**:desktop_computer: Note on panel persistence:**<br>

The right-hand panel is utilised by the `help`, `view`, and `trend` commands to display their respective content (Command List, Student Records, Trend Overview). Once populated, the information in this panel is persistent; it will remain visible even when executing commands that only modify the student list in the left panel (e.g., `add`, `delete`, `find`, `list`, or `sort`).
</div>

<div style="page-break-after: always;"></div>

### Viewing help : `help`

Displays the help window on the right side panel.

Format: `help`

![help message](images/helpMessage.png)

<div markdown="block" class="alert alert-info">:information_source: **Note:**
Opening the help window using `F1`, or clicking the help button in the header, will not display a command result in the command box, as these actions do not execute a command.
</div>

<div style="page-break-after: always;"></div>

### Adding a student: `add`

Adds a student to TAHub.

Format: `add n/NAME sn/STUDENT_NUMBER p/PHONE_NUMBER e/EMAIL tele/TELEGRAM [t/TAG]…​`

![result for 'add n/jean-grey sn/A2222222R p/(65) 9494 e/jean@example tele/jean_grey'](images/AddJeanGreySuccess.png)


* Adds a student with the specified `NAME`, `STUDENT_NUMBER`, `PHONE_NUMBER`, `EMAIL`, `TELEGRAM` and `TAG`
* All the fields are required except for `TAG`.

<div style="page-break-after: always;"></div>

**Student Parameter Constraints:**

| Parameter Name    | Constraint                                                                                                                                                                                                                                                                                                                                                               | Notes                                                                                                                                                                 |
|-------------------|--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|-----------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| `NAME`            | Alphanumeric characters and spaces, not blank, max 100 characters. May include `/` if not preceded by a parameter-like prefix (eg. `sn/`, `tele/`).                                                                                                                                                                                                                      | Non-standard characters (hyphens `-`, apostrophes `'`, periods`.`, accents) trigger a warning, but are accepted.                                                      |
| `STUDENT_NUMBER`  | Format: `AXXXXXXXZ` where `A` is the character 'A', `X` is any digit 0-9, and `Z` is any letter.                                                                                                                                                                                                                                                                         | First and last letters are case-insensitive.                                                                                                                          |
| `PHONE_NUMBER`    | At least 3 digits. May start with `+`, and contain optional `-` separators.                                                                                                                                                                                                                                                                                               | Non-standard characters (parentheses `()`, periods `.` spaces) trigger a warning, but are accepted.<br/><br/>Non-standard formats trigger a warning but are accepted. |
| `EMAIL`           | Format: `local-part@domain`.<br/>`local-part`: Alphanumeric with special characters: `+_.-` (cannot start/end with special characters).<br/>`domain`: One or more labels separated by `.`.<br/>Label: Start/end with alphanumeric characters, may contain `-` in between. Final label must be 2+ characters long.<br/>Examples: `example@gmail.com`, `test@u.nus.edu.sg` | Must follow the exact format.                                                                                                                                         |
| `TELEGRAM`        | Alphanumeric and underscores only, not blank, max 100 characters.                                                                                                                                                                                                                                                                                                        | Must follow the exact format.                                                                                                                                         |
| `TAG`             | Alphanumeric characters only.                                                                                                                                                                                                                                                                                                                                            | Multiple tags allowed.                                                                                                                                                |

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
A student can have any number of tags (including 0)
</div>

<div markdown="span" class="alert alert-info">:information_source: **Note:**
Each student is uniquely identified by their Student Number, meaning you cannot add multiple students with the same Student Number.
</div>

<div markdown="span" class="alert alert-warning">:exclamation: **Important:**
While names can include `/` for valid formats, using parameter-like sequences that match parameters in the same command (e.g., `sn/` or `tele/` in an `add` command) within the name field will result in an error.
</div>

Examples:
* `add n/John Doe sn/A0123456Z p/98765432 e/johnd@example.com tele/john_doe`
* `add n/Betsy Crowe t/friend e/betsycrowe@example.com tele/betsy_crowe sn/A1234567G p/1234567 t/criminal`
* `add n/jean-grey sn/A2222222R p/(65) 9494 e/jean@example tele/jean_grey`

### Listing all students : `list`

Shows a list of all students in TAHub.

Format: `list`

### Editing a student : `edit`

Edits an existing student in TAHub.

Format: `edit INDEX [n/NAME] [sn/STUDENT_NUMBER] [p/PHONE] [e/EMAIL] [tele/TELEGRAM] [t/TAG]…​`

* Edits the student at the specified `INDEX`. The index refers to the index number shown in the displayed student list. The index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Refer to [Student Parameter Constraints](#adding-a-student-add) for the individual parameter constraints.
* Existing values will be updated to the input values.
* When editing tags, the existing tags of the student will be removed i.e adding of tags is not cumulative.
* You can remove all the student’s tags by typing `t/` without
    specifying any tags after it.

Examples:
*  `edit 1 p/91234567 e/johndoe@example.com` Edits the phone number and email address of the 1st student to be `91234567` and `johndoe@example.com` respectively.
*  `edit 2 n/Betsy Crower t/` Edits the name of the 2nd student to be `Betsy Crower` and clears all existing tags.

### Locating students by name: `find`

Finds students whose names contain any of the given keywords.

Format: `find KEYWORD [MORE_KEYWORDS]`

![result for 'find alex david'](images/findAlexDavidResult.png)

* The search is case-insensitive. e.g. `hans` will match `Hans`
* The order of the keywords does not matter. e.g. `Hans Bo` will match `Bo Hans`
* Only the name is searched.
* Only full words will be matched e.g. `Han` will not match `Hans`
* Students matching at least one keyword will be returned (i.e. `OR` search).
  e.g. `Hans Bo` will return `Hans Gruber`, `Bo Yang`

<div markdown="block" class="alert alert-info">

:information_source: **Note:**<br>

* The `find` command updates the student list shown in the left panel. Subsequent commands that uses `INDEX` (e.g. `delete 1`, `view 2`) will refer to the index shown in this **filtered list**.

* Keywords for the find command are not restricted to alphabetic characters. 

</div>

Examples:
* `find John` returns `john` and `John Doe`
* `find alex david` returns `Alex Yeoh`, `David Li`<br>

<div style="page-break-after: always;"></div>

### Deleting a student : `delete`

Deletes the specified student from TAHub.

Format: `delete INDEX`

* Deletes the student at the specified `INDEX`.
* The index refers to the index number shown in the displayed student list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `list` followed by `delete 2` deletes the 2nd student in TAHub.
* `find Betsy` followed by `delete 1` deletes the 1st student in the results of the `find` command.

### Adding/Editing a student record: `record`
Creates or updates the weekly record for a specific student.

Format: `record INDEX week/WEEK_NUMBER att/ATTENDANCE_SCORE sub/SUBMISSION_SCORE part/PARTICIPATION_SCORE`

![result for 'record 1 week/1 part/1 sub/1 att/1'](images/RecordResult.png)

* **To create a new record:** Run the command using a `WEEK_NUMBER` that currently has no associated record for the specified student.
* **To modify an existing record:** Run the command with a target student's `INDEX` and the record's `WEEK_NUMBER`. The existing record for that week will be replaced by the new score values provided in the command.
* All parameters must be included when executing the record command, regardless of whether you are creating a new record or editing an existing one.

<div style="page-break-after: always;"></div>

**Record Parameter Constraints**

|Parameter| Constraints                                | Notes                                                                                                                                                                |
|---------|:-------------------------------------------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------|
|`INDEX`| Positive integer 1, 2, 3, …​               |                                                                                                                                                                      |
|`WEEK_NUMBER`| Integer from **1** to **13** (inclusive)   |                                                                                                                                                                      |
|`ATTENDANCE_SCORE`| **0** (absent) or **1** (present)          |                                                                                                                                                                      |
|`SUBMISSION_SCORE`| **0** (not submitted) or **1** (submitted) | Indicates overall completion status of all assignments. The TA determines what constitutes 1 (e.g. all assignments submitted) and 0 (e.g. partial or no submission). |
|`PARTICIPATION_SCORE`| Integer from **0** to **5** (inclusive)    | Serves as a personal assessment scale for the TA. Its meaning is determined by the TA and does not necessarily reflect any external, module defined score range.     |

Examples:
* `record 1 week/1 att/1 sub/0 part/4`
* `record 2 week/5 att/0 sub/1 part/5`<br>


### Removing a student record: `record`
Removes an existing record on a particular week for a specific student.

Format: `record INDEX week/WEEK_NUMBER`

- The `INDEX` and `WEEK_NUMBER` parameters have the same constraints as [adding a record](#addingediting-a-student-record-record).
- All of `ATTENDANCE_SCORE`, `SUBMISSION_SCORE` and `PARTICIPATION_SCORE` are not required and should not be specified when deleting a record.

Examples:
* `record 1 week/1`
* `record 2 week/5`

<div markdown="span" class="alert alert-primary"> :bulb: **Tip:** 
This command is useful for correcting data entry errors, such as when a record was accidentally created for the wrong week (e.g. keying a record into Week 1 when tutorials start in Week 3). Deletion removes the record completely, unlike the edit functionality, which would require replacing all values. 
</div>

<div style="page-break-after: always;"></div>

### Viewing student record: `view`

Displays the weekly records of a specific student.

Format: `view INDEX`

![result for 'list' followed by 'view 2'](images/ViewResult.png)

Examples:
* `find Betsy` followed by `view 1` views the 1st student in the results of the `find` command.<br>
* `list` followed by `view 2` views the 2nd student in TAHub.<br>

<div style="page-break-after: always;"></div>

### Viewing overall trend of all student records: `trend`

Displays the trend overview of records for all students.

Format: `trend`<br>

![result for 'list' followed by 'view 2'](images/TrendResult.png)

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
The Trend view (right panel) always reflects the students currently shown in the left panel—any sorting or filtering (e.g., via sort or list) updates the trends immediately.
</div>

<div style="page-break-after: always;"></div>

### Sorting students: `sort`

Sort students based on their attendance, participation, or submission percentages, in descending order.

Format:
* By attendance: `sort /a` 
* By participation: `sort /p` 
* By submission: `sort /s`

![result for 'sort /a'](images/SortResult.png)

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
The sort command reorders the student list (left panel). The right panel updates only if it is already showing Trend (i.e., The trend window is opened).    
</div>

### Clearing all entries : `clear`

Clears all entries from TAHub.

Format: `clear`

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
This command is irreversible, permanently deleting all student entries and records from TAHub.
</div>

### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Saving the data

TAHub data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

TAHub data are saved automatically as a JSON file `[JAR file location]/data/TAHub.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, TAHub will discard all data and start with an empty data file at the next run. Hence, it is recommended to take a backup of the file before editing it.<br>
Furthermore, certain edits can cause the TAHub to behave in unexpected ways (e.g., if a value entered is outside of the acceptable range). Therefore, edit the data file only if you are confident that you can update it correctly.
</div>

--------------------------------------------------------------------------------------------------------------------

<div style="page-break-after: always;"></div>

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous TAHub home folder.

**Q**: When double-clicking on the jar file, I get an error message from the Java Virtual Machine Launcher.<br>
![error message](images/javaVMLError.jpg)<br>
**A**: Instead of double-clicking on the jar file, run the jar file using the command `java -jar tahub.jar` in the terminal. Refer to the [Quick Start](#quick-start) section for details.

--------------------------------------------------------------------------------------------------------------------

## Known issues

1. **When using multiple screens**, if you move the application to a secondary screen, and later switch to using only the primary screen, the GUI will open off-screen. The remedy is to delete the `preferences.json` file created by the application before running the application again.

--------------------------------------------------------------------------------------------------------------------

<div style="page-break-after: always;"></div>

## Command summary

Action | Format, Examples
--------|------------------
**Add** | `add n/NAME sn/STUDENT_NUMBER p/PHONE_NUMBER e/EMAIL tele/TELEGRAM [t/TAG]…​` <br> e.g., `add n/James Ho sn/A1234567z p/22224444 e/jamesho@example.com tele/hames_ho t/friend t/colleague`
**Clear** | `clear`
**Delete** | `delete INDEX`<br> e.g., `delete 3`
**Edit** | `edit INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [sn/STUDENT_NUMBER] [tele/TELEGRAM] [t/TAG]…​`<br> e.g.,`edit 2 n/James Lee e/jameslee@example.com`
**Record** (Add/Edit)| `record INDEX week/WEEK_NUMBER att/ATTENDANCE_SCORE sub/SUBMISSION_SCORE part/PARTICIPATION_SCORE`<br> e.g., `record 1 week/1 att/1 sub/0 part/4`
**Record** (Remove)| `record INDEX week/WEEK_NUMBER` <br> e.g., `record 1 week/1`
**Find** | `find KEYWORD [MORE_KEYWORDS]`<br> e.g., `find James Jake`
**List** | `list`
**View** | `view INDEX`<br> e.g., `view 2`
**Trend** | `trend`
**Sort** | `sort /a` / `sort /p` / `sort /s`
**Exit** | `exit`
**Help** | `help`

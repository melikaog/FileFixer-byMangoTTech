package ttech.mango;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.ArrayList;

public class SubmissionsTest extends SetUpTest{

    @Test
    public void AllValidSubmissionsTest(){
        // Scenario 1 - 49 names to rename, all valid

        ToRenameCollection root = new ToRenameCollection(currentDirectory, folderName, renamedFilesFolderName, noSubmissionListFileName);
        root.initFilesToRenameDir(); //unzipping
        ArrayList <Student> students = root.readCSV();
        
        int numFilesRenamed = root.processPDFs(); // the actual for the 'assert' tests -> the no. of files renamed is returned
        int expected = 49;  // 49 students are expected to be renamed after submissions
        root.createDidNotSubmit(students);

        assertEquals(expected, numFilesRenamed);
    }

    @Test
    public void NoPDFsToRenameTest(){
        // Scenario 2 - 0 PDFs to renamed, 49 missing submissions listed

        ToRenameCollection root = new ToRenameCollection(currentDirectory, folderName, renamedFilesFolderName, noSubmissionListFileName);
        root.initFilesToRenameDir(); //unzipping
        ArrayList <Student> students = root.readCSV();
        
        int numFilesRenamed = root.processPDFs(); // the actual for the 'assert' tests -> the no. of files renamed is returned
        int expected = 0; // The number of students that is expected to be renamed from 0 submissions
        root.createDidNotSubmit(students);

        assertEquals(expected, numFilesRenamed);

        if(expected == numFilesRenamed) 
            printDidNotSubmit();; // 49 students are expected to be listed for missing submissions
    }

    @Test
    public void SomeInvalidFlaggedSubmissionsTest(){
        // Scenario 3 - 49 names to rename, 30 valid and 19 invalid

        ToRenameCollection root = new ToRenameCollection(currentDirectory, folderName, renamedFilesFolderName, noSubmissionListFileName);
        root.initFilesToRenameDir(); //unzipping
        ArrayList <Student> students = root.readCSV();
        int numFilesRenamed = root.processPDFs(); // the actual for the 'assert' tests -> the no. of files renamed is returned

        int expected = 30; // 30 students are expected to be renamed after submissions
        root.createDidNotSubmit(students);
        
        assertEquals(expected, numFilesRenamed);

        if(expected == numFilesRenamed) 
            printDidNotSubmit();; // 19 students are expected to be listed for flagged submissions
    }

    @Test
    public void SomeInvalidSubmissionsListTest(){
        // Scenario 4

        ToRenameCollection root = new ToRenameCollection(currentDirectory, folderName, renamedFilesFolderName, noSubmissionListFileName);
        root.initFilesToRenameDir(); //unzipping
        ArrayList <Student> students = root.readCSV();
        int numFilesRenamed = root.processPDFs(); // the actual for the 'assert' tests -> the no. of files renamed is returned

        int expected = 30; // 30 students are expected to be renamed after submissions
        root.createDidNotSubmit(students);
        
        assertEquals(expected, numFilesRenamed);

        if(expected == numFilesRenamed) 
            printDidNotSubmit(); // 19 Students are expected to be listed
    }

    @Test
    public void AllInvalidFlaggedSubmissionsTest(){
        // Scenario 5

        ToRenameCollection root = new ToRenameCollection(currentDirectory, folderName, renamedFilesFolderName, noSubmissionListFileName);
        root.initFilesToRenameDir(); //unzipping
        ArrayList <Student> students = root.readCSV();
        
        int numFilesRenamed = root.processPDFs(); // the actual for the 'assert' tests -> the no. of files renamed is returned
        int expected = 0;  // 49 submissions are expected to be invalid, therefore ZERO are renamed
        root.createDidNotSubmit(students);

        assertEquals(expected, numFilesRenamed);

        if(expected == numFilesRenamed) 
            printDidNotSubmit(); // 49 Students are expected to be listed
    }
}
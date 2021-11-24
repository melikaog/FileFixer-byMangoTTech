// Scenario 3 - tests for the 30 valid, 19 invalid. Flags the 19 invalid

package ttech.mango;

import java.io.File;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
// import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;

//import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;

public class InvalidFlaggedSubmissionsTest extends SetUpTest{

    @Test
    public void runInvalidFlaggedSubmissions(){
        // sample 3 case_i.zip file needs to be used   

        ToRenameCollection root = new ToRenameCollection(currentDirectory, folderName, renamedFilesFolderName, noSubmissionListFileName);
        root.initFilesToRenameDir(); //unzipping
        ArrayList <Student> students = root.readCSV();
        
        int numFilesRenamed = root.processPDFs(); // the actual for the 'assert' tests -> the no. of files renamed is returned
        int expected = 30;
        root.createDidNotSubmit(students);
        boolean flag = false;

        try{ 
            assertEquals(expected, numFilesRenamed);
        }
        catch(AssertionError ae){
            flag = true;
            System.out.println("The number of files in the CSV ** DOES NOT ** equal the number of files renamed. ");
        }

        if (!flag){
            String print = "The number of files in the CSV == The number of files renamed. \n\nExpected: ";
            print += expected + "\nActual: " + numFilesRenamed + "\n";

            System.out.println(print);
        }
    }
}
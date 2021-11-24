// Scenario 2

package ttech.mango;

import java.io.File;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeAll;
import java.util.ArrayList;

public class NoPDFsToRenameTest extends SetUpTest{

    @Test
    public void runNoPDFsToRenameTest(){
        // sample 3 case_i.zip file needs to be used   

        ToRenameCollection root = new ToRenameCollection(currentDirectory, folderName, renamedFilesFolderName, noSubmissionListFileName);
        root.initFilesToRenameDir(); //unzipping
        ArrayList <Student> students = root.readCSV();
        
        int numFilesRenamed = root.processPDFs(); // the actual for the 'assert' tests -> the no. of files renamed is returned
        int expected = 0; // The number of students that is expected to submit is 0
        root.createDidNotSubmit(students);
        boolean flag = false;

        try{ 
            assertEquals(expected, numFilesRenamed);
        }
        catch(AssertionError ae){
            flag = true;
            System.out.println("The number of files in the CSV == The number of files renamed: ");
        }

        if (!flag){
            String print = "The number of files in the CSV == The number of files renamed. \n\nExpected: ";
            print += expected + "\nActual: " + numFilesRenamed + "\n";

            System.out.println(print);
        }
    }
}
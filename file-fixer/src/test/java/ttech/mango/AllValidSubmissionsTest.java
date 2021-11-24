// Scenario 1

package ttech.mango;

// import java.io.File;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
// import org.junit.jupiter.api.BeforeEach;

//import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;

public class AllValidSubmissionsTest extends SetUpTest{

    @Test
    public void runAllValidSubmissions(){
        // sample 3 case_i.zip file needs to be used   

        ToRenameCollection root = new ToRenameCollection(currentDirectory, folderName, renamedFilesFolderName, noSubmissionListFileName);
        root.initFilesToRenameDir(); //unzipping
        ArrayList <Student> students = root.readCSV();
        
        int numFilesRenamed = root.processPDFs(); // the actual for the 'assert' tests -> the no. of files renamed is returned
        int expected = 49;
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

    // public static void main(String[] args){
    //     AllValidSubmissionsTest run = new AllValidSubmissionsTest();
    //     run.runAllValidSubmissions();
    // }
}
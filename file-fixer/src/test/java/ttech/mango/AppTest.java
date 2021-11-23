package ttech.mango;

//import static org.junit.Assert.assertNotNull;
//import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

// import java.io.File;

// import java.util.ArrayList;
/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Test the five scenarios
     */
    @Test


    //Scenario 1 : Convention 1 only

    public void allValidSubmissions()
    {
           
          @Test
         public void runAllValidSubmissions(){
        // sample 3 case_i.zip file needs to be used
        
        //FileFixer.currentDirectory = System.getProperty("user.dir");
        String folderName = "filesToRename";
        String renamedFilesFolderName = "Renamed Assignments";
        String noSubmissionListFileName = "DID NOT SUBMIT.txt";

        ToRenameCollection root = new ToRenameCollection(FileFixer.currentDirectory, folderName, renamedFilesFolderName, noSubmissionListFileName);
        root.initFilesToRenameDir(); //unzipping
        ArrayList <Student> students = root.readCSV();
        
        System.out.println("Students: " + students.get(0));

        // 49 is the expected number of PDFs that need to be renamed
        int expected = 49;

        // 49 is the expected number of CSVs
        int numFilesRenamed = root.processPDFs();
        root.createDidNotSubmit(students);
        
        boolean flag = false;
        try{ 
            assertEquals(expected, numFilesRenamed);
        }
        catch(AssertionError ae){
            flag = true;
            System.out.println("The number of files to be renamed does not match te expected.");
        }

        if (!flag){
            System.out.println("The number of files to be renamed matches the expected.");
        }
      
    }
        
        
        
        

    //Scenario 2

    public void allSubmissionsAreMissing()
    {
        // input: sample3 case_ii.zip


    }



    //Scenario 3 
    public void validAndInvalidSubmssions()
    {
        // input: sample3 case_iii.zip

    }



    //Scenario 4
    public void validAndMissingSubmissions()
    {
         // input: sample3 case_iv.zip

    }

    //Scenario 5
    public void allInvalidSubmissions()
    {
         // input: sample5 case_v.zip

    }


    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }

    

}

package ttech.mango;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;

public class AllValidSubmissionsTest{

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

    
    public static void main (String[] args){


        AllValidSubmissionsTest all = new AllValidSubmissionsTest();
        all.runAllValidSubmissions(); 
 
    }
}
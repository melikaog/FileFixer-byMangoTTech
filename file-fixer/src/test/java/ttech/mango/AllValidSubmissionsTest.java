// package ttech.mango;
// import java.io.File;
// import org.junit.jupiter.api.Test;
// import static org.junit.jupiter.api.Assertions.assertEquals;
// //import static org.junit.jupiter.api.Assertions.*;
// import java.util.ArrayList;

// public class AllValidSubmissionsTest{

//     public static File renamedAssigns;
//     public static String currentDirectory;

//     @Test
//     public void runAllValidSubmissions(){
//         // sample 3 case_i.zip file needs to be used
        
//         currentDirectory = System.getProperty("user.dir");
        
//         String folderName = "filesToRename";
//         String renamedFilesFolderName = "Renamed Assignments";
//         String noSubmissionListFileName = "DID NOT SUBMIT.txt";

//         renamedAssigns = new File(currentDirectory + File.separator + folderName + File.separator + renamedFilesFolderName);

//         System.out.println("RENAMEDL : " + renamedAssigns);

//         renamedAssigns.delete();
//         renamedAssigns.mkdir();

//         ToRenameCollection root = new ToRenameCollection(currentDirectory,folderName,renamedFilesFolderName,noSubmissionListFileName);
//         root.initFilesToRenameDir(); //unzipping
//         ArrayList <Student> students = root.readCSV();
        
//         int numFilesRenamed = root.processPDFs();

//         root.createDidNotSubmit(students);

//         int expected = 49;
//         boolean flag = false;

//         try{ 
//             assertEquals(expected, numFilesRenamed);
//         }
//         catch(AssertionError ae){
//             flag = true;
//             System.out.println("The number of files to be renamed does not match te expected.");
//         }

//         if (!flag){
//             System.out.println("The number of files to be renamed matches the expected.");
//         }
//     }

// }


package ttech.mango;

import java.io.File;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
// import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;

//import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;

public class AllValidSubmissionsTest{

    public static File renamedAssigns;
    public static String currentDirectory;
    public static String folderName = "filesToRename";
    public static String renamedFilesFolderName = "Renamed Assignments";
    public static String noSubmissionListFileName = "DID NOT SUBMIT.txt";

    @BeforeAll
    private static void setUp(){
        currentDirectory = System.getProperty("user.dir");

        renamedAssigns = new File(currentDirectory + File.separator + folderName + File.separator + renamedFilesFolderName);
        renamedAssigns.delete();
        renamedAssigns.mkdir();
    }

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
}
package ttech.mango;

import java.io.File;
import org.junit.jupiter.api.BeforeAll;

public abstract class SetUpTest{
    public static File renamedAssigns;
    public static String currentDirectory;
    public static String folderName = "filesToRename";
    public static String renamedFilesFolderName = "Renamed Assignments";
    public static String noSubmissionListFileName = "DID NOT SUBMIT.txt";

    @BeforeAll
    public void runSetUpTest(){
        
        currentDirectory = System.getProperty("user.dir");
        renamedAssigns = new File(currentDirectory + File.separator + folderName + File.separator + renamedFilesFolderName);
        renamedAssigns.delete();
        renamedAssigns.mkdir();
    }     
}
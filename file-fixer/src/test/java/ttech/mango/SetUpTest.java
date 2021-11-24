package ttech.mango;

import java.io.File;
import org.junit.jupiter.api.BeforeAll;
import java.util.Scanner;
import java.util.ArrayList;

public abstract class SetUpTest{
    public static File renamedAssigns;
    public static String currentDirectory;
    public static String folderName = "filesToRename";
    public static String renamedFilesFolderName = "Renamed Assignments";
    public static String noSubmissionListFileName = "DID NOT SUBMIT.txt";
    public static ArrayList <Student> students;
    public static ToRenameCollection root;
    public static int numFilesRenamed;

    // this method sets up the environment variables necessary for the SubmissionsTest to run
    // this method will allows run before any of the other methods in SubmissionsTest can run
    @BeforeAll
    public static void runSetUpTest(){
        
        currentDirectory = System.getProperty("user.dir");
        renamedAssigns = new File(currentDirectory + File.separator + folderName + File.separator + renamedFilesFolderName);
        renamedAssigns.delete();
        renamedAssigns.mkdir();

        root = new ToRenameCollection(currentDirectory, folderName, renamedFilesFolderName, noSubmissionListFileName);
        root.initFilesToRenameDir(); //unzipping
        students = root.readCSV();
        
        numFilesRenamed = root.processPDFs(); // the actual for the 'assert' tests -> the no. of files renamed is returned
    }     

    public static void printDidNotSubmit(){
        try{
            File didNotSubmit = new File(currentDirectory + File.separator + folderName + File.separator + noSubmissionListFileName);
            Scanner s = new Scanner(didNotSubmit);
            int i = 1;

            System.out.println("\nThe students who have -NOT- submitted: \n");

            while(s.hasNextLine()){
                System.out.println("   " + i + "." + s.nextLine());
                i++;
            }
            s.close();
        }
        catch(Exception e){
            System.out.println("Error: " + e);
        }
    }
}

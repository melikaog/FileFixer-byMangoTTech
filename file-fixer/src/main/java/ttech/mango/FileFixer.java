package ttech.mango;

import java.io.File;
import java.util.ArrayList;

public class FileFixer {

    // = new File (currentDirectory + File.separator + "filesToRename" + File.separator + "Renamed Assignments");
    //public static Directory renamedAssignsDir = new Directory(renamedAssigns.getName(), renamedAssigns.getParent());
    //public static File noSubmissionList = new File (FileFixer.currentDirectory + File.separator + "filesToRename" + File.separator +"DID NOT SUBMIT.txt");
    public static File renamedAssigns;
    public static String currentDirectory;
    
    public FileFixer(){

        currentDirectory = System.getProperty("user.dir");

    }
    public static void main(String[] args) {
     
        currentDirectory = System.getProperty("user.dir");
        
        String folderName = "filesToRename";
        String renamedFilesFolderName = "Renamed Assignments";
        String noSubmissionListFileName = "DID NOT SUBMIT.txt";

        renamedAssigns = new File(currentDirectory + File.separator + folderName + File.separator + renamedFilesFolderName);
        renamedAssigns.delete();
        renamedAssigns.mkdir();

        ToRenameCollection root = new ToRenameCollection(currentDirectory,folderName,renamedFilesFolderName,noSubmissionListFileName);
        root.initFilesToRenameDir(); //unzipping
        ArrayList <Student> students = root.readCSV();
        root.processPDFs();
        root.createDidNotSubmit(students);

    }
}
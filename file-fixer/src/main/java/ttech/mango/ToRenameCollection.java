package ttech.mango;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;


public class ToRenameCollection {

    private String filesToRenameFolder;// = FileFixer.currentDirectory + File.separator + "filesToRename";
    private Directory toRename;// = new Directory("filesToRename", FileFixer.currentDirectory);
    private File renamed;// = new File(FileFixer.currentDirectory + File.separator + "filesToRename" + File.separator + "Renamed Assignments");
    private String currentDirectory;
    private File noSubmissionListFile;// //File noSubmissionList = new File (FileFixer.currentDirectory + File.separator + "filesToRename" + File.separator +"DID NOT SUBMIT.txt");
    
    public ToRenameCollection(String currentDirectory, String folderName, String renamedFilesFolderName, String noSubmissionListFileName) {

        this.currentDirectory = currentDirectory; 
        filesToRenameFolder = currentDirectory + File.separator + folderName;
        toRename = new Directory(folderName, currentDirectory);
        renamed = new File(filesToRenameFolder + File.separator + renamedFilesFolderName);

        noSubmissionListFile = new File (filesToRenameFolder + File.separator +noSubmissionListFileName);
    
    }
        
    public void initFilesToRenameDir(){

        // Add /filesToRename/ to the current directory to go to that subdirectory
        File directoryContents = new File(filesToRenameFolder);

        if (!directoryContents.exists() || !directoryContents.isDirectory()){
            System.out.println("Directory does not exist. Please place within the following directory to proceed: \n" + currentDirectory);
            return;
        }

        File[] files = directoryContents.listFiles();

        for(File f: files){ //for each file in filesToRename

            String name = f.getName();
           
            if(name.contains(".zip")){
                
                ZIPFile zip = new ZIPFile(name, f.getParent());
                zip.unzip();
                f.delete();
            }
        } 
        
        files = directoryContents.listFiles();
        storeFiles(files);
    }

    public ArrayList <Student> readCSV(){ 

        ArrayList <Student> students=null;
        File directoryContents = new File(filesToRenameFolder);

        if (!directoryContents.exists() || !directoryContents.isDirectory()){
            System.out.println("Directory does not exist. Please place within the following directory to proceed: \n" + currentDirectory);
            
        }

        /* File[] files = directoryContents.listFiles();
        storeFiles(files); */

        for (Component c: toRename.getComponentsInDir()){
            if(c instanceof CSVFile){
                CSVFile csv = (CSVFile)c;
                students = csv.read();  
            } 
            else{
                continue;
            }  
        }
        //students.remove(0);

        // System.out.println("NUMBER OF STUDENTS IN CSV: " + students.size());
     
        return students;
    }




    public void storeFiles(File[] files){

        Component cd=null;
        ArrayList <Component> compsInDir = new ArrayList<>();
       
        for(File file: files){ //1st iteration: for each file in filesToRename Folder
            
            if (!file.isDirectory()){
                
                if(file.getName().contains(".csv")){
                    cd = new CSVFile(file.getName(), file.getParent());
                }
                else if(file.getName().contains(".pdf")){
                    cd = new PDFFile(file.getName(), file.getParent());
                    PDFFile pdf = (PDFFile) cd;
                }    
                else { //ZIP files deleted after extraction, no need to create obj
                    continue;
                }
            } 
            else if (!file.equals(renamed)){
                cd = new Directory(file.getName(), file.getParent());
                File[] nested = file.listFiles(); //array of files in directory cd
                storeFiles(nested);
            }
            compsInDir.add(cd);
        }
        toRename.addComponentToDir(compsInDir);
    }

    

    public int processPDFs(){

        int numPDFRenamed = 0;

        for (Component c: toRename.getComponentsInDir()){
            
            if(c instanceof PDFFile){
                PDFFile pdf = (PDFFile) c;
                if(pdf.getStrategy() != null)
                    pdf.process();  

                if(pdf.getIsRenamed()) 
                    numPDFRenamed++;
            }
        }

        return numPDFRenamed;
    }    

    public void createDidNotSubmit(ArrayList<Student> students){

        try{
            File [] renamedList = renamed.listFiles();
            FileOutputStream fos = new FileOutputStream(noSubmissionListFile);
            OutputStreamWriter osw = new OutputStreamWriter(fos);

            for(Student s: students){ 
                
                boolean submitted = false;
                String identifier = s.getIdentifier();
                
                for(File file: renamedList){

                    if (file.getName().contains(identifier)){
                        submitted = true;
                        break;
                    }
                }
                if(!submitted)
                    osw.write(s.getFullName() + " " + identifier + "\n");
            }
            osw.close();
        }
        catch(IOException e){
            System.out.println(e.getMessage());  
        }
    }
}


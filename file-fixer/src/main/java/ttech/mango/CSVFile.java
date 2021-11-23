package ttech.mango;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class CSVFile extends FileLeaf{

    //private static final String currentDirectory = System.getProperty("user.dir");
    public CSVFile(String name, String parentPath){
        super(name, parentPath);
    }

    public ArrayList<Student> read(){
        
        ArrayList <Student> students = new ArrayList<Student>();
        
        try{
            
            File myFile = new File(this.getPath());
            Scanner s = new Scanner(myFile);

            while(s.hasNextLine()){
                String[] stu = s.nextLine().split(",");
                Student stud = new Student(stu[0].replace("Participant ", ""), stu[2], stu[1], stu[3]);
                students.add(stud);
               
            }
            students.remove(0); // This represents the header
            s.close();
            return students;
        }
        
        catch(FileNotFoundException e){
            System.out.println("Error encountered: " + e);
            return null;
        }
    }
}

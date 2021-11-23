package ttech.mango;

import java.io.File;
import java.util.ArrayList;

public class Submission{

    private File file;
    private Student submitter;
    private ArrayList<String> parsedFileName;
    private String conventionTwoFileName;


    public Submission(File file) {

        this.file = file;
        this.submitter = null;

        parseFileName();
        //convertToConventionTwo();

    }

    public Submission(File file, Student submitter){

        this.file = file;
        this.submitter = submitter;

        parseFileName();
        convertToConventionTwo();

    }


    public Student findSubmitter(ArrayList<Student> students){

        for (Student student: students){
        
            if (parsedFileName != null){
            

                if (parsedFileName.get(2).equals(student.getIdentifier()))
                    
                    return student;

            }
            //System.out.println("Filename: " + parsedFileName);

        }

        return null;

    }


    public void setSubmitter(Student submitter){

        this.submitter = submitter;

    }


    public File getFile(){

        return file;

    }


    public Student getSubmitter(){

        return submitter;

    }


    public void parseFileName(){
        
        String pdfName = file.getName();

//
        System.out.println("PDF name: "+pdfName);

        String[] parsed = pdfName.split("_");
        
        // rCode1n2 == randomMyElearningCode1 and 2

        if (parsed.length > 2){

            String rCode1n2 = parsed[0];

            String fullName = parsed[1] + " " + parsed[2];

            int pos = 3;

            // this code assumes that a person does not have name beginning with a number
            while (!Character.isDigit(parsed[pos].toCharArray()[0])) {

                fullName += " " + parsed[pos];
                pos++;

            }

            String identifier = parsed[pos].replace("Participant ", "");

            pos++;

            String originalFileName = parsed[pos];

            pos++;

            int size = parsed.length;

            while (pos < size) {

                originalFileName += " " + parsed[pos];
                pos++;

            }

            // System.out.println(originalFileName);
            makeParsedFileName(rCode1n2, fullName, identifier, originalFileName);

        }

    }


    public void makeParsedFileName(String rCode1n2, String fullName, String identifier, String originalFileName){

        ArrayList<String> parsedFileName = new ArrayList<String>();
        parsedFileName.add(rCode1n2);
        parsedFileName.add(fullName);
        parsedFileName.add(identifier);
        parsedFileName.add(originalFileName);
        
        this.parsedFileName = parsedFileName;  

        //System.out.println(parsedFileName);

    }

    public void convertToConventionTwo(){

        String conventionTwo = "";

        conventionTwo += submitter.getFullName() + "_" + submitter.getIdentifier() + "_" + "assignsubmission_file_" + parsedFileName.get(3);
        
        this.conventionTwoFileName = conventionTwo;

    }


    public String getConventionTwoFileName(){

        return conventionTwoFileName;

    }


    public String toString(){

        String str = "";

        int i=0;
        for(String string: parsedFileName){

            str += string;
            i++;

            if (i < parsedFileName.size()){

                str += " ";

            }

        }

        return str;

    }

}
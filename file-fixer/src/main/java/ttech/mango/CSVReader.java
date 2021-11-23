package ttech.mango;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class CSVReader{ //implements FileReader{

  public ArrayList<String> read(String fileName){
    
    ArrayList<String> contents = new ArrayList<String>();

    try{
        File myFile = new File(fileName);
        Scanner s = new Scanner(myFile);

        while(s.hasNextLine()){
          contents.add(s.nextLine());
        }
        s.close();
        
        return contents;
    }
    
    catch(FileNotFoundException e){
      System.out.println("Error encountered: " + e);
      return null;
    }
  }
}
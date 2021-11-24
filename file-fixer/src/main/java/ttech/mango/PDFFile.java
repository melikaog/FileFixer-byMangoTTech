package ttech.mango;

import java.io.File;

public class PDFFile extends FileLeaf{

    //private static String currentDirectory = System.getProperty("user.dir");
    private RenameStrategy renameStrat;
    private boolean isRenamed;

    public PDFFile(String name, String parentPath){
        super(name, parentPath);
        isRenamed = false;
        renameStrat = setRenameStrat();
        //PDDocument doc = Loader.loadPDF(new File(getPath()));
    }

    public RenameStrategy getStrategy(){
        return this.renameStrat;
    }

    public void setIsRenamed(boolean b){
        this.isRenamed = b;
    }

    public boolean getIsRenamed(){
        return this.isRenamed;
    }

    public RenameStrategy setRenameStrat(){
    
        String[] parsed = getName().split("_");
        
        // if - determines if the parsed string is in convention 1
        if (parsed.length  > 4 && parsed[0].length() == 17 && parsed[0].toCharArray()[10] == '-') {
            return new Convention1Strat();
        }
        // else if - determines if the parsed string is in convention 2
        else if(parsed.length > 4 && parsed[2].equals("assignsubmission") && parsed[3].equals("file")){
            //System.out.print(getName() + " ALREADY exists in Renamed Assignments Folder \n\n" );
            return new Convention2Strat();
        }
        else{
            System.out.println("Assignment '" + getName() +"' is invalid. Unable to rename. Please review.");
            return null;
        }
    }


    public void process() {
        
            renameStrat.createRenamedPDF(getName(), new File(getPath()));
            setIsRenamed(true);
    }
}

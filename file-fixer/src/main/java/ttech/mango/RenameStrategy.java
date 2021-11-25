package ttech.mango;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public abstract class RenameStrategy {

    protected static final File f1 = new File (FileFixer.currentDirectory + File.separator + "filesToRename");
    String renamedAssigns = System.getProperty("user.dir") + File.separator + "filesToRename" + File.separator + "Renamed Assignments";

    public final void createRenamedPDF(String name, File existing){ //template method

        String newName = parseName(name);
        copy(existing, new File(renamedAssigns + File.separator + newName));
        
    }

    protected abstract String parseName(String name);

    protected void copy(File src, File dest) { //hook method
        //https://www.java67.com/2016/09/how-to-copy-file-from-one-location-to-another-in-java.html

        InputStream is = null; 
        OutputStream os = null; 
        try { is = new FileInputStream(src);
            os = new FileOutputStream(dest); 
            // buffer size 1K 
            byte[] buf = new byte[1024]; 
            int bytesRead; 
            while ((bytesRead = is.read(buf)) > 0) {
                os.write(buf, 0, bytesRead); 
            } 
           is.close(); 
           os.close(); 
        } 
        catch (IOException e) {
            e.printStackTrace();
        } 
    }

}

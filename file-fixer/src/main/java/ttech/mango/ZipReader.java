package ttech.mango;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class ZipReader{

    private static String currentDirectory = System.getProperty("user.dir");
    public void extractFiles(String ZipFileName){
        //ref: https://www.journaldev.com/960/java-unzip-file-example

        String fileZip = currentDirectory + File.separator + "filesToRename" + File.separator + ZipFileName;
        File destDir = new File(fileZip);

        try (ZipFile ZIPfile = new ZipFile(fileZip)){
    
            Enumeration <?> zipEntries = ZIPfile.entries();
            while (zipEntries.hasMoreElements()){

                ZipEntry zipEntry = (ZipEntry) zipEntries.nextElement();
                if (zipEntry.isDirectory()){

                    String subDir = destDir + File.separator + zipEntry.getName();
                    File as = new File(subDir);
                    as.mkdirs();

                }

                else{ 
                    // Create new json file
                    File newFile = new File(destDir, zipEntry.getName());

                    String extractedDirectoryPath = destDir.getCanonicalPath();
                    String extractedFilePath = newFile.getCanonicalPath();
                    
                    if (!extractedFilePath.startsWith(extractedDirectoryPath + File.separator)){
                        throw new IOException("Entry is outside of the target dir: " + zipEntry.getName());
                    }

                    BufferedInputStream inputStream = new BufferedInputStream(ZIPfile.getInputStream(zipEntry));

                    String dir = currentDirectory;
                    dir += File.separator + "filesToRename";

                    String newName[] = zipEntry.getName().split(File.separator);
                    createDirectory(dir, newName[0]); //was void

                    String newDestinationPath = dir + File.separator + newName[0];
                    File newDestination = new File(newDestinationPath);
                    File testDir = new File(newDestination, newName[1]);

                    //Root.toRename.addComponentToDir(extractedDir); //new

                    //System.out.println("\n\n NewDestinationPath: " + testDir);
                    
                    try (FileOutputStream outputStream = new FileOutputStream(testDir)){ // newFile was the previous input argument - testDir is the new

                        while (inputStream.available() > 0){
                            outputStream.write(inputStream.read());
                        }
                    } 
                    catch(Exception e){
                        System.out.println(e + " has been encountered while extracting");
                    }
                }
            }

        }
        catch(Exception e){
            System.out.println(e + " has been encountered while extracting");
        }
    }


    public void createDirectory(String fileDirectoryPath, String fileName){
        File newFile = new File(fileDirectoryPath, fileName);
        newFile.mkdir();
        //return new Directory(fileName, fileDirectoryPath); //new
    }

}
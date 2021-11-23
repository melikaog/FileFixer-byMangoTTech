package ttech.mango;

import java.io.File;

public abstract class FileLeaf implements Component{
    
    private String name;
    private String parentPath;
    private String filePath;

    public FileLeaf(String name, String parentPath){
        this.name = name;
        this.filePath = parentPath + File.separator + name; 
    }

    public String getName(){
        return name;
    }

    public String getPath() {
        return filePath;
    }

    public String getParent(){
        return parentPath;
    }

    public String toString(){
        return "FILE NAME: " + name + "\n PATH: " + getPath() + "\n";
    }
}

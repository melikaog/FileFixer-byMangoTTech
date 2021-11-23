package ttech.mango;

import java.io.File;
import java.util.ArrayList;

public class Directory implements Component{
    
    private String dirName;
    private String dirPath;
    private String parentPath;
    private ArrayList <Component> componentsInDir;

    public Directory(String name, String parentPath){
        this.dirName = name;
        this.parentPath = parentPath;
        this.dirPath = parentPath + File.separator + name;
        componentsInDir = new ArrayList<>();
    }

    public String getName(){
        return dirName; 
    }

    public String getPath(){
        return dirPath;
    }

    public String getParent(){
        return parentPath;
    }

    public void addComponentToDir(Component c){
        //for(Component c: comp)
            componentsInDir.add(c); 
    }

    public void addComponentToDir(ArrayList <Component> c){
        for(Component comp: c)
            componentsInDir.add(comp); 
    }

    public ArrayList <Component> getComponentsInDir(){
        return componentsInDir;
       
    }
    
    public String toString(){
        String dirContent = getName().toUpperCase();
        
        for(Component cd: componentsInDir){
            if(cd instanceof Directory){
                dirContent += "\n\t\t" + cd.toString();
            }
            // else it's an instance of FileLeaf
            else{
                dirContent += "\n\t\t\t\t\t\t\t" + cd.toString();
            }
        }
        
        return dirContent;
    }
}

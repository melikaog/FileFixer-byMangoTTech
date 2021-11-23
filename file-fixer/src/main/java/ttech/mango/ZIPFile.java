package ttech.mango;

public class ZIPFile extends FileLeaf{
    
    private ZipReader reader;

    public ZIPFile(String name, String parentPath){
        super(name, parentPath);
        reader = new ZipReader();

    }

    public void unzip(){
        reader.extractFiles(getName());
    }
}

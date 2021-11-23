package ttech.mango;

public class Convention1Strat extends RenameStrategy{

    public String parseName(String pdfName){
        
        String[] parsed = pdfName.split("_");

        // rCode1n2 == randomMyElearningCode1 and 2
        String newPDFName;
        String fullName = parsed[1] + " " + parsed[2];
        int pos = 3;

        //assumes that a person cannot have name beginning with a number
        while (!Character.isDigit(parsed[pos].toCharArray()[0])) {
            fullName += " " + parsed[pos];
            pos++;
        }

        String identifier = parsed[pos];
        pos++;
        String originalFileName = parsed[pos];
        pos++;

        int size = parsed.length;
        while (pos < size) {

            originalFileName += "_" + parsed[pos];
            pos++;

        }
        newPDFName = fullName + "_" + identifier + "_assignsubmission_file_" + originalFileName;
        return newPDFName;
    }

}

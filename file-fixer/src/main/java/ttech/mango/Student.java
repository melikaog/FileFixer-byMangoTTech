package ttech.mango;

public class Student {

    private String identifier;
    private String ID;
    private String fullName;
    private String email;
    //private File file;

    public Student(String identifier, String ID, String fullName, String email) {

        this.identifier = identifier;
        this.ID = ID;
        this.fullName = fullName;
        this.email = email;

    }

    public String getFullName() {
        return fullName;
    }

    public String getID() {
        return ID;
    }

    public String getEmail() {
        return email;
    }

    public String getIdentifier() {
        return identifier;
    }

    public boolean equals(String str) {
        if (this.getIdentifier().equals(str))
            return true;
        return false;
    }

    public String toString() {
        String student = this.identifier + " " + this.fullName + " " + this.ID + " " + this.email;
        return student;
    }
}

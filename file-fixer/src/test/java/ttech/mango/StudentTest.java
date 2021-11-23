package ttech.mango;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;

public class StudentTest{

    public void studentTest(String testIdentifier, String testID, String testFName, String testEmail, String testFileName) {

        Student testStudent = new Student(testIdentifier, testID, testFName, testEmail);

        File testFile = new File(System.getProperty("user.dir") + File.separator + testFileName);

        if (!testFile.exists()){

            testFile.mkdir();

        }

        Submission testSubmission = new Submission(testFile);
        testStudentConstructor(testStudent);
        testGetFullName(testStudent, testFName);
        testGetID(testStudent, testID);
        testGetEmail(testStudent, testEmail);
        testGetIdentifier(testStudent, testIdentifier);
        testEquals(testStudent, testIdentifier);
        testSetSubmission(testStudent, testSubmission);
        testGetSubmission(testStudent, testSubmission);
        testHasSubmission(testStudent);
        // System.out.println("The result is: "+testStudentConstructor(testStudent));

    }

    public void testStudentConstructor(Student testStudent) throws AssertionError {

        boolean flag = false;
        try {
            assertNotNull(testStudent);
        }
        catch (AssertionError ae) {
            System.out.println("Constructor returned null");
            flag = true;

        }

        if (!flag) {

            System.out.println("The student constructor works as expected.");

        }

    }

    public void testGetFullName(Student testStudent, String testFName) {

        boolean flag = false;

        try {

            assertTrue(testStudent.getFullName().equals(testFName));

        }
        catch (AssertionError ae) {

            flag = true;
            System.out.println("The student's name does not match the expected name.");

        }

        if (!flag) {

            System.out.println("The student's name matches the expected name.");

        }

    }

    public void testGetID(Student testStudent, String testID) {

        boolean flag = false;

        try {

            assertTrue(testStudent.getID().equals(testID));

        }

        catch (AssertionError ae) {

            flag = true;
            System.out.println("The student's ID does not match the expected ID.");

        }

        if (!flag) {

            System.out.println("The student's ID matches the expected ID.");

        }

    }

    public void testGetEmail(Student testStudent, String testEmail) {
        boolean flag = false;

        try{
            assertTrue(testStudent.getEmail().equals(testEmail));
            
        }

        catch (AssertionError ae){

            flag = true;
            System.out.println("The student's email does not match the expected email.");

        }if(!flag){
            
            System.out.println("The student's email matches the expected email.");
        
        }

    }

    public void testGetIdentifier(Student testStudent, String testIdentifier) {

        boolean flag = false;

        try {

            assertTrue(testStudent.getIdentifier().equals(testIdentifier));

        }

        catch (AssertionError ae) {

            flag = true;
            System.out.println("The student's identifier does not match the expected identifier.");

        }

        if (!flag) {

            System.out.println("The student's identifier matches the expected identifier.");

        }

    }

    public void testEquals(Student testStudent, String testIdentifier) {
        
        boolean flag = false;

        try{

            assertEquals(testIdentifier,testStudent.getIdentifier());

        }

        catch(AssertionError ae){

            flag = true;
            System.out.println("This student's identifier does not match the expected .");
            
        }

        if (!flag) {

            System.out.println("The student's identifier matches the expected identifier.");

        }           
    }

    public void testSetSubmission(Student testStudent, Submission testSubmission) {
        
        boolean flag = false;

        try{
            
            assertTrue(testStudent.setSubmission(testSubmission));

        }

        catch(AssertionError ae){
            
            flag = true;
            System.out.println("The submission was not set.");

        }

        if (!flag){

            System.out.println("The submission was set.");

        }


    }

    public void testGetSubmission(Student testStudent, Submission testSubmission) {
        
        boolean flag = false;

        try{

            assertEquals(testSubmission, testStudent.getSubmission());

        }

        catch(AssertionError ae){
            
            flag = true;
            System.out.println("The student's submission does not match the expected submission.");

        }

        if (!flag){

            System.out.println("The student's submission matches the expected submission.");         

        }

    }

    public void testHasSubmission(Student testStudent) {
        
        boolean flag = false;

        try{
        
            assertNotNull(testStudent.hasSubmission());

        }

        catch(AssertionError ae){

            flag = true;
            System.out.println("The student's submission is null.");

        }

        if (!flag){

            System.out.println("The student's submission is not null.");

        }

    }



}
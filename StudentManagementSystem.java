package StudentManagementSystem;

//importing java packages
import java.util.*;
import java.io.*;

//  studentManagementSystem class
public class StudentManagementSystem {

//    declare and assigning variables
    private static final int maxStudents = 100;
    private static Student[] students = new Student[maxStudents];
    private static int studentCount = 0;

//    main method of the program
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int choice;

        while (true){
            displayMenu();
            choice = scan.nextInt();
            scan.nextLine();

            switch (choice){
                case 0:
                    System.out.println("Exiting...");
                    return;
                case 1:
                    checkAvailableSeats();
                    break;
                case 2:
                    registerStudent(scan);
                    break;
                case 3:
                    deleteStudent(scan);
                    break;
                case 4:
                    findStudent(scan);
                    break;
                case 5:
                    storeStudentDetails();
                    break;
                case 6:
                    loadStudentDetails();
                    break;
                case 7:
                    viewStudents();
                    break;
                case 8:
                    addExtraDetails(scan);
                    break;
                default:
                    System.out.println("Invalid choice");
                    break;
            }
        }
    }

//    menu for the program method
    private static void displayMenu(){
        System.out.println("\n---- Welcome to the Student Management System ----\n");
        System.out.println("************ MENU ************");
        System.out.println("1. Check Available Seats");
        System.out.println("2. Register Student (with ID)");
        System.out.println("3. Delete Student");
        System.out.println("4. Find Student (with student ID)");
        System.out.println("5. Store Student Details into a file");
        System.out.println("6. Load Student Details from the file to the system");
        System.out.println("7. View the list of the Student based on their names");
        System.out.println("8. Add Extra details");
        System.out.println("0. Exit");
        System.out.println("******************************");
        System.out.print("Choose an option : ");
    }

//    sub menu for the add extra details method
    private static void addExtraDetails(Scanner scan){
        while (true) {
            System.out.println("Add Extra details");
            System.out.println("a. Add module marks");
            System.out.println("b. Get summary");
            System.out.println("c. Get report");
            System.out.println("e. Exit");
            System.out.print("Choose an option : ");
            char choice = scan.next().toLowerCase().charAt(0);
            scan.nextLine();

            switch (choice) {
                case 'a':
                    addModuleMarks(scan);
                    break;
                case 'b':
                    getSummary();
                    break;
                case 'c':
                    getReport();
                    break;
                case 'e':
                    System.out.println("Back to main menu...");
                    return;
                default:
                    System.out.println("Invalid choice");
                    break;
            }
        }
    }

//    check the number of available seats methods
    private static void checkAvailableSeats(){
        System.out.println("Available Seats : " + (maxStudents - studentCount));

    }

//    student register method
    private static void registerStudent(Scanner scan){
        if (studentCount >= maxStudents){
            System.out.println("No available seats found");
            return;
        }

        String StudentID;
        while (true){
            System.out.print("Enter student ID (Eg: w1234567) : ");
            StudentID = scan.nextLine();
            if (StudentID.length() == 8){
                break;
            }else {
                System.out.println("Invalid student ID. Please try again .");
            }
        }

        System.out.print("Enter the student name : ");
        String StudentName = scan.nextLine();
        students[studentCount++] = new Student(StudentID, StudentName);
        System.out.println("Student registered successfully.");
    }

//    deletion of registered student method
    private static void deleteStudent(Scanner scan){
        System.out.print("Enter student ID to delete : ");
        String StudentID = scan.nextLine();
        for (int i = 0; i < studentCount; i++){
            if (students[i] != null && students[i].getStudentID().equals(StudentID)){
                students[i] = students[--studentCount];
                students[studentCount] = null;
                System.out.println("Student deleted successfully.");
                return;
            }
        }
        System.out.println("Student not found ");
    }

//    find student using ID method
    private static void findStudent(Scanner scan){
        System.out.print("Enter student ID to find : ");
        String StudentID = scan.nextLine();

        boolean StudentFound = false;
        for (int i = 0; i < studentCount; i++){
            if (students[i] != null && students[i].getStudentID().equals(StudentID)){
                System.out.println("Student found : " + students[i].getStudentName());
                StudentFound = true;
                break;
            }
        }

        if (!StudentFound){
            System.out.println("Student not found");
        }
    }

//    store student details in to a file method
    private static void storeStudentDetails(){
        try (FileWriter fileWriter = new FileWriter("StudentDetails.txt")){
            for (int i = 0; i < studentCount; i++){
                fileWriter.write(students[i].getStudentID() + ", " + students[i].getStudentName() + ", " +
                        students[i].getModule1().getMarks() + ", " + students[i].getModule2().getMarks() + ", " +
                        students[i].getModule3().getMarks() + ", " + students[i].calculateGrade() + "\n");
            }
            System.out.println("Student details saved successfully .");
        }catch (IOException e){
            System.out.println("Error saving student details.");
        }
    }

//    load student details using file method
    private static void loadStudentDetails(){
        try (Scanner fileScanner = new Scanner(new File("StudentDetails.txt"))){
            studentCount = 0;
            while (fileScanner.hasNextLine()){
                String line = fileScanner.nextLine();
                String[] details = line.split(",");
                String StudentID = details[0].trim();
                String StudentName = details[1].trim();
                int mark1 = Integer.parseInt(details[2].trim());
                int mark2 = Integer.parseInt(details[3].trim());
                int mark3 = Integer.parseInt(details[4].trim());
                String grade = details[5];

                students[studentCount] = new Student(StudentID,StudentName);
                students[studentCount].setModuleMarks(mark1, mark2, mark3);
                students[studentCount].calculateGrade();
                studentCount++;
            }
            System.out.println("Student details loaded successfully.");
        }catch (IOException e){
            System.out.println("Student details loading error!");
        }
    }

//    to view saved details of students method
    private static void viewStudents(){
        for (int i = 0; i < studentCount; i++){
            for (int j = 0; j < studentCount - i - 1; j++){
                if (students[j] == null) {
                    break;
                }
                if (students[j].getStudentName().compareTo(students[j + 1].getStudentName()) > 0){
                    Student tempStudent = students[j];
                    students[j] = students[j+1];
                    students[j+1] = tempStudent;
                }
            }
        }
        for (Student student : students) {
            if (student == null) {
                break;
            }
            System.out.println(student.getStudentID());
            System.out.println(student.getStudentName());
            System.out.println();
        }
    }

//    add students' module marks method
    private static void addModuleMarks(Scanner scan){
            System.out.print("Enter student ID to add marks : ");
            String StudentID = scan.nextLine();

            int mark1 = inputMarks(1, scan);
            int mark2 = inputMarks(2, scan);
            int mark3 = inputMarks(3, scan);

            for (int i = 0; i < studentCount; i++){
                if (students[i] == null){
                    break;
                }
                if (students[i].getStudentID().equalsIgnoreCase(StudentID)) {
                    students[i].getModule1().setMarks(mark1);
                    students[i].getModule2().setMarks(mark2);
                    students[i].getModule3().setMarks(mark3);
                    System.out.println("Marks saved successfully.");
                    return;
                }
            }
            System.out.println("Student not found");
    }

//    get the student each module mark method
    private static int inputMarks(int i, Scanner scan){
        while (true){
            try {
                System.out.print("Enter marks for module " + i + ":");
                int mark = scan.nextInt();
                if (mark >= 0 && mark <= 100){
                    return mark;
                }else{
                    System.out.println("Invalid marks");
                }
            } catch (InputMismatchException e){
                System.out.println("Invalid marks. Please try again: ");
                scan.nextLine();
            }

        }
    }

//    get the summary of all the students method
    private static void getSummary(){
        int studentWithOver40 = 0;
        for (Student student : students) {
            if (student != null && student.getModule1().getMarks() > 40 && student.getModule2().getMarks() > 40 &&
                    student.getModule3().getMarks() > 40){
                studentWithOver40++;
            }
        }
        System.out.println("\nNumber of student without over 40 marks: " + studentWithOver40);
    }

//  get the full detail report using student data method
    private static void getReport(){
        for (int i = 0; i < studentCount; i++){
            for (int j = 0; j < studentCount; j++){
                if ((students[j] != null && students[j+1] != null) && students[j].getAverage() < students[j+1].getAverage()){
                    Student tempHold = students[j];
                    students[j] = students[j+1];
                    students[j+1] = tempHold;
                }
            }
        }
        for (Student student : students) {
            if (student != null) {
                System.out.println("Student ID : " + student.getStudentID());
                System.out.println("Student Name : " + student.getStudentName());
                System.out.println("Module 1 Marks : " + student.getModule1().getMarks());
                System.out.println("Module 2 Marks : " + student.getModule2().getMarks());
                System.out.println("Module 3 Marks : " + student.getModule3().getMarks());
                System.out.println("Average Marks : " + (student.getModule1().getMarks() + student.getModule2().getMarks() + student.getModule3().getMarks()) / 3);
                System.out.println("Grade : " + student.calculateGrade());
                System.out.println();
            }
        }
    }
}

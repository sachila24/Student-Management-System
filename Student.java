package StudentManagementSystem;

public class Student {
    private String StudentID;
    private String StudentName;
    private Module module1;
    private Module module2;
    private Module module3;

    public Student(String studentID, String studentName){
        this.StudentID = studentID;
        this.StudentName = studentName;
        this.module1 = new Module("Module 1");
        this.module2 = new Module("Module 2");
        this.module3 = new Module("Module 3");
    }

    public String getStudentID(){
        return StudentID;
    }

    public String getStudentName(){
        return StudentName;
    }

    public Module getModule1(){
        return module1;
    }

    public Module getModule2(){
        return module2;
    }

    public Module getModule3(){
        return module3;
    }

    public void setModuleMarks(int mark1, int mark2, int mark3) {
        this.module1.setMarks(mark1);
        this.module2.setMarks(mark2);
        this.module3.setMarks(mark3);
    }

    public double getAverage(){
        return (double) (module1.getMarks() + module2.getMarks() + module3.getMarks()) / 3;
    }

    public String calculateGrade(){
        double average = getAverage();

        if (average >= 80){
            return "Distinction";
        }else if (average >= 70){
            return "Merit";
        }else if (average >= 40){
            return "Pass";
        }else {
            return "Fail";
        }
    }

}

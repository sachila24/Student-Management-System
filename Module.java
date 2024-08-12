package StudentManagementSystem;

public class Module {
    private String moduleName;
    private int marks;

    public Module(String moduleName){
        this.moduleName = moduleName;
        this.marks = 0;
    }

    public String getModuleName(){
        return moduleName;
    }

    public int getMarks() {
        return marks;
    }

    public void setMarks(int marks) {
        this.marks = marks;
    }
}

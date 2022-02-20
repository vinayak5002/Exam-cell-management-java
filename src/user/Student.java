package user;

public class Student {

    private final String rollNo;
    private final String name;
    private final String password;
    private final String section;

    public Student(String rollNo, String name, String password, String section){
        this.rollNo = rollNo;
        this.name = name;
        this.password = password;
        this.section = section;
    }

    public String getRollNo(){
        return rollNo;
    }

    public String getPassword(){
        return password;
    }

    public String getSection(){
        return section;
    }

}

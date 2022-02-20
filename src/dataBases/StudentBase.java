package dataBases;

import user.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class StudentBase {

    private List<Student> list = new ArrayList<>();

    public void addStudent(Student newStudent){
        list.add(newStudent);
    }

    public boolean login(String roll, String pass){
        for(Student i: list){
            if((Objects.equals(i.getRollNo(), roll)) && Objects.equals(i.getPassword(), pass)){
                return true;
            }
        }
        return false;
    }

    public Student getStudentDetails(String roll){
        for(Student i : list){
            if(Objects.equals(i.getRollNo(), roll)){
                return i;
            }
        }

        return new Student(" ", "StudentNotPresentException", " ", " ");
    }

}

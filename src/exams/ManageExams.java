package exams;

import java.util.ArrayList;
import java.util.List;

public abstract class ManageExams {

    private List<Exam> list = new ArrayList<>();

    public List<Exam> getExams() {
        return list;
    }

    public void addExam(Exam newExam) {
        list.add(newExam);
    }

    public boolean modifyExam(int examID, Date date, int hall, int slot) {
        boolean isInvalid = false;

        for (Exam i : list) {
            if (i.getExamId() == examID) {
                isInvalid = true;
                i.update(date, hall, slot);
                break;
            }
        }

        return isInvalid;
    }

    public boolean deleteExam(int examID) {
        boolean sucess = false;
        Exam removeThis = null;

        for (Exam i : list) {
            if (i.getExamId() == examID) {
                sucess = true;
                removeThis = i;
            }
        }

        if(sucess){
            list.remove(removeThis);
        }

        return sucess;

    }

}

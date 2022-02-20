package exams;

public class Exam {

    private int examId;
    private String name;
    private Date date;
    private int hall;
    private int slot;

    public Exam(int examId, String name, Date date, int hall, int slot){
        this.examId = examId;
        this.name = name;
        this.date = date;
        this.hall = hall;
        this.slot = slot;
    }

    public void update(Date date, int hall, int slot){
        this.date = date;
        this.hall = hall;
        this.slot = slot;
    }

    public String getDetails(){
        return this.examId + " - " + this.name + "  : " + this.date.getDate() + " IN Hall no. " + hall + " on slot no. " + slot;
    }

    public Date getDate() {
        return date;
    }

    public int getExamId() {
        return examId;
    }

    public int getHall(){
        return hall;
    }

    public int getSlot() {
        return slot;
    }

}

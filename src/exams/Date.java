package exams;

public class Date {

    int dd, mm, yyyy;

    public Date(int dd, int mm, int yyyy){
        this.dd = dd;
        this.mm = mm;
        this.yyyy = yyyy;
    }

    public boolean equals(Date other){
        return (this.dd == other.dd) && (this.mm == other.mm);
    }

    public String getDate(){
        return dd + "/" + mm + "/" + yyyy;
    }

}

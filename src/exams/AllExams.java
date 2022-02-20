package exams;

public class AllExams extends ManageExams {

    public int[][] getSlots(int[][] magicMatrix, Date date){
//        magic matrix
//        slot    1  2  3
//        hall 1 [1, 1, 1] //hall number   00 01 02
//        hall 2 [1, 1, 1]                 10 11 12
//        hall 3 [1, 1, 1]                 20 21 22


        for(Exam i : this.getExams()){

            if(i.getDate().equals(date)){
                int hall = i.getHall(); //1
                int slot = i.getSlot(); //0

                hall--;
                slot--;

                magicMatrix[hall][slot] = 0;
            }
        }

        return magicMatrix;
    }


}

import exams.*;
import sections.*;
import dataBases.*;
import user.*;
import java.util.*;
import exceptions.*;

public class Main {

    public static AdminBase adminBase = new AdminBase();
    public static StudentBase studentBase = new StudentBase();
    public static Sections sections = new Sections();
    public static AllExams allExams = new AllExams();
    public static Scanner scan = new Scanner(System.in);

    static{
        adminBase.addAdmin(new Admin("5002", "vinayak", "admin@vinayak"));
        adminBase.addAdmin(new Admin("5003", "abhirup", "admin@abhirup"));
        adminBase.addAdmin(new Admin("5004", "darshan", "admin@darshan"));
        adminBase.addAdmin(new Admin("5005", "manoj", "admin@manoj"));

        studentBase.addStudent(new Student("20465", "arjun", "csee20465", "CSEE"));
        studentBase.addStudent(new Student("20468", "danush", "csee20488", "CSEE"));
        studentBase.addStudent(new Student("20443", "ramesh", "csee20443", "CSEE"));

        studentBase.addStudent(new Student("20432", "ram", "csed20432", "CSED"));
        studentBase.addStudent(new Student("20423", "surya", "csed20423", "CSED"));
        studentBase.addStudent(new Student("20412", "vaibhav", "csed20412", "CSED"));

        Section ESection = new Section("CSEE");
        ESection.addExam(new Exam(4301,"E-sec OOPs quiz 3", new exams.Date(10, 1, 2022), 2, 1));
        ESection.addExam(new Exam(4302,"E-sec AP quiz 3", new exams.Date(11, 1, 2022), 2, 3));
        ESection.addExam(new Exam(4303,"E-sec DBMS quiz 3", new exams.Date(12, 1, 2022), 1, 1));

        Section DSection = new Section("CSED");
        DSection.addExam(new Exam(5301,"D-sec OOPs quiz 3", new exams.Date(10, 1, 2022), 1, 1));
        DSection.addExam(new Exam(5302,"D-sec AP quiz 3", new exams.Date(11, 1, 2022), 3, 3));
        DSection.addExam(new Exam(5303,"D-sec DBMS quiz 3", new exams.Date(12, 1, 2022), 2, 1));

        sections.addSection(ESection);
        sections.addSection(DSection);

        List<Exam> EsectionExamList = ESection.getExams();
        List<Exam> DsectionExamList = DSection.getExams();

        for(Exam i : EsectionExamList){
            allExams.addExam(i);
        }
        for(Exam i : DsectionExamList){
            allExams.addExam(i);
        }

    }

    static void createExam(){
        int examID;
        String sectionName, examName;
        boolean satisfied = false;

        int[][] magicMatrix = new int[3][3];
        for(int i=0; i<3; i++){
            for(int j=0; j<3; j++){
                magicMatrix[i][j] = 1;
            }
        }

        do{
            System.out.println("Enter the name of the exam: ");
            examName = scan.next();
            System.out.println("Enter section name of exam: ");
            scan.nextLine();
            sectionName = scan.nextLine();
            System.out.println("Enter exam Id");
            examID = scan.nextInt();

            int d, m , y;
            System.out.println("Enter date\nEnter day: ");
            d = scan.nextInt();
            System.out.println("Enter month");
            m = scan.nextInt();
            System.out.println("Enter year");
            y = scan.nextInt();
            exams.Date date = new exams.Date(d, m, y);

            magicMatrix = allExams.getSlots(magicMatrix, date);


            System.out.println("\n");
            for(int i=0; i<3; i++){
                System.out.print("Hall " + (i+1) + " available slots: ");
                for(int j=0; j<3; j++){
                    if(magicMatrix[i][j] == 1){
                        System.out.print(j+1 + ", ");
                    }
                }
                System.out.println("\n");
            }

            System.out.println("Satisfied with available slots: enter y for yes and n for no \nEnter x to cancel creating a exam:\n");
            char choice = scan.next().charAt(0);

            if(choice == 'y'){
                satisfied = true;
                System.out.println("Enter hall and slot from available slots ");

                int hall = scan.nextInt();
                int slot = scan.nextInt();

                Exam newExam = new Exam(examID, examName, date, hall, slot);

                allExams.addExam(newExam);

                Section objSection = sections.getList().get(sections.searchSection(sectionName));
                objSection.addExam(newExam);

                System.out.println("Exam successfully added\n");

            }
            else if(choice == 'n'){
                System.out.println("Enter another date\n");
            }

        }while(!satisfied);
    }

    static void modifyExam(){
        int examID;
        String sectionName;

        System.out.println("Modifying exam");
        boolean invalidModification;

        do{
            System.out.println("Enter section name of exam");
            sectionName = scan.next();
            System.out.println("Scheduled exams: ");

            Section objSection = sections.getList().get(sections.searchSection(sectionName));

            List<Exam> objList = objSection.getExams();

            for(Exam i : objList){
                System.out.println(i.getDetails());
            }

            System.out.println("Enter exam Id to be modified");
            examID = scan.nextInt();

            int d, m , y;
            System.out.println("Enter date\nEnter day: ");
            d = scan.nextInt();
            System.out.println("Enter month");
            m = scan.nextInt();
            System.out.println("Enter year");
            y = scan.nextInt();
            exams.Date newDate = new exams.Date(d, m, y);

            System.out.println("Enter new hall number: ");
            int hall = scan.nextInt();

            System.out.println("Enter new slot number: ");
            int slot = scan.nextInt();

            try{
                boolean modificationSucess = objSection.modifyExam(examID, newDate, hall, slot);
                if( !modificationSucess ){
                    throw new ExamNotFoundException("No exam was found with the detials entered, pls check and try again");
                }
                else{
                    System.out.println("Exam was sucessfully modified");
                    invalidModification = false;
                    allExams.modifyExam(examID, newDate, hall, slot);
                }
            } catch (ExamNotFoundException e) {
                System.out.println(e);
                invalidModification = true;
            }

        }while(invalidModification);

    }

    static void cancelExam(){

        int examID;
        String sectionName;
        boolean invalidCancel;

        do{
            System.out.println("Enter section name of exam");
            sectionName = scan.next();

            System.out.println("Scheduled exams: ");

            Section objSection = sections.getList().get(sections.searchSection(sectionName));

            List<Exam> objList = objSection.getExams();

            for(Exam i : objList){
                System.out.println(i.getDetails());
            }

            System.out.println("Enter exam Id to be deleted");
            examID = scan.nextInt();


            try{
                boolean cancelSucess = objSection.deleteExam(examID);

                if(!cancelSucess){
                    throw new ExamNotFoundException("No exam was found with the detials entered, pls check and try again");
                }
                else{
                    System.out.println("Exam was successfully cancelled\n");
                    invalidCancel = false;
                    allExams.deleteExam(examID);
                }
            } catch (ExamNotFoundException e) {
                System.out.println(e);
                invalidCancel = true;
            }

        }while(invalidCancel);

    }

    public static void main(String[] args){
        char c;
        int back = 1, choice;

        do{
            System.out.println("-----------Welcome----------");
            System.out.println("Enter s id you are a student \nEnter a if you are a admin\nEnter X to exit\n");

            boolean invalidMainInput;

            do{
                c = scan.next().charAt(0);
                try{
                    if(c != 's' && c != 'a' && c!='x'){
                        throw new InvalidInputException("Invalid input, valid inputs are 's', 'a', 'x'");
                    }
                    else{
                        invalidMainInput = false;
                    }
                } catch (InvalidInputException e) {
                    System.out.println(e);
                    invalidMainInput = true;
                }

            }while(invalidMainInput);

            // Student portal
            if(c == 's'){
                String roll;
                String pass;

                System.out.println("Welcome to Student portal");

                boolean studentLoginFailed;

                do{
                    System.out.println("Enter you roll number");
                    roll = scan.next();
                    System.out.println("Enter your password");
                    pass = scan.next();

                    try{
                        if(!studentBase.login(roll, pass)){
                            throw new LoginFailException("Login failed, Please check your roll number, password and try again");
                        }else{
                            studentLoginFailed = false;
                        }
                    } catch (LoginFailException e) {
                        System.out.println(e);
                        studentLoginFailed = true;
                    }

                }while(studentLoginFailed);

                System.out.println("Login successful!!");

                Student obj = studentBase.getStudentDetails(roll);

                Section objSection = sections.getList().get(sections.searchSection(obj.getSection()));

                List<Exam> objList = objSection.getExams();

                System.out.println("Exams scheduled: \n");
                for(Exam i : objList){
                    System.out.println(i.getDetails());
                }

                System.out.println("\nEnter any character to continue");
                char con = scan.next().charAt(0);

            }

            //Admin portal
            else if(c == 'a'){
                String id;
                String pass;

                System.out.println("Welcome to admin portal");

                boolean adminLoginFailed;

                do{
                    System.out.println("Enter your admin ID");
                    id = scan.next();
                    System.out.println("Enter your password");
                    pass = scan.next();

                    try{
                        if(!adminBase.login(id, pass)){
                            throw new LoginFailException("Login failed, Please check your admin id, password and try again");
                        }else{
                            adminLoginFailed = false;
                        }
                    } catch (LoginFailException e) {
                        System.out.println(e);
                        adminLoginFailed = true;
                    }

                }while(adminLoginFailed);

                boolean backToPortal = true;

                do{
                    System.out.println("Admin Menu");
                    System.out.println("1. Create a exam");
                    System.out.println("2. Modify exam details");
                    System.out.println("3. Cancel a exam");
                    System.out.println("4. Back to main menu");
                    System.out.println("0. Exit");
                    System.out.println("Enter your choice");

                    boolean invalidAdminChoice;

                    do{
                        choice = scan.nextInt();
                        try{
                            if(choice < 0 || choice >4){
                                throw new InvalidInputException("Invalid input, valid inputs are 0, 1, 2, 3, 4");
                            }
                            else{
                                invalidAdminChoice = false;
                            }
                        } catch (InvalidInputException e) {
                            System.out.println(e);
                            invalidAdminChoice = true;
                        }

                    }while(invalidAdminChoice);

                    if(choice == 0){back = 0;}

                    switch (choice) {
                        case 1 :
                        {
                            createExam();
                        }
                        break;

                        case 2 :
                        {
                            modifyExam();
                        }
                        break;

                        case 3 :
                        {
                            cancelExam();
                        }
                        break;

                        default :
                        {
                            backToPortal = false;
                        }
                    }
                }while(backToPortal);
            }
            else{
                back = 0;
            }
        }while(back == 1);

    }
}
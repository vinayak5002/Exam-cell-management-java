package user;

public class Admin {

    private final String adminID;
    private final String name;
    private final String password;

    public Admin(String adminID, String name, String password){
        this.adminID = adminID;
        this.name = name;
        this.password = password;
    }

    public String getAdminID(){
        return adminID;
    }
    public String getPassword(){
        return password;
    }

}

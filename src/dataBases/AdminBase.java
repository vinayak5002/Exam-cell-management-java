package dataBases;

import user.Admin;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AdminBase {

    private List<Admin> list = new ArrayList<>();

    public void addAdmin(Admin newAdmin){
        list.add(newAdmin);
    }

    public boolean login(String id, String pass){
        for(Admin i: list){
            if(Objects.equals(i.getAdminID(), id) && Objects.equals(i.getPassword(), pass)){
                return true;
            }
        }

        return false;
    }

}

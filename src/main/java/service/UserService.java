package service;

import dao.UserDAO;
import model.User;

import java.util.ArrayList;

public class UserService {
    public boolean login(User u){
        UserDAO ud = new UserDAO();
        ArrayList<User> users = new ArrayList<User>();

        users = ud.getUsers();
        for (User us: users) {
            if(u.getEmail().equals(us.getEmail()) && u.getPassword().equals(us.getPassword())){
                return true;
            }else{
                return false;
            }
        }

    }

}

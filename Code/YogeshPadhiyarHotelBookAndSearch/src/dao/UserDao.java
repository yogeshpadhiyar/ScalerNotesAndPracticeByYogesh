package dao;

import model.User;

import java.util.HashMap;

public class UserDao {
    private static  UserDao userDao = null;
    HashMap<String, User> users;

    public UserDao() {
        this.users = new HashMap<>();
        users.put("tej", new User("tej"));
        users.put("raj", new User("raj"));
    }

    public static UserDao getInstance(){
        if(userDao==null)   userDao=new UserDao();
        return userDao;
    }


    public User getUser(String username) {
        return users.get(username);
    }
}

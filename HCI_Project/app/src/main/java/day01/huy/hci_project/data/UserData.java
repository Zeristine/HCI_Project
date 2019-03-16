package day01.huy.hci_project.data;

import java.util.ArrayList;
import java.util.List;

import day01.huy.hci_project.dto.User;

public class UserData {
    private static List<User> users = null;

    public UserData() {
        if (users == null) {
            users = new ArrayList<>();
        }
    }

    public boolean checkLogin(String username, String password) {
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

    public User getAccountByUsername(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    public boolean register(String username, String password) {
        if (getAccountByUsername(username) != null) {
            return false;
        } else {
            users.add(new User(username, password));
            return true;
        }
    }

}

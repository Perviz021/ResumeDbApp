package main;

import bean.User;
import dao.impl.UserDaoImpl;
import dao.inter.UserDaoInter;

import java.sql.*;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        UserDaoInter userDao = new UserDaoImpl();
//        List<User> userList = userDao.getAll();
//        for (User user : userList) {
//            System.out.println(user);
//        }
//        System.out.println("----------");
//        userDao.removeUser(3);
//        List<User> userList2 = userDao.getAll();
//        for (User user : userList2) {
//            System.out.println(user);
//        }

        User u = userDao.getById(2);
        u.setName("sohret");
        userDao.updateUser(u);
    }
}

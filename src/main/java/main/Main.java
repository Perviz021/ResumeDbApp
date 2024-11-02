package main;

import dao.impl.UserDaoImpl;
import dao.impl.UserSkillDaoImpl;
import dao.inter.*;

public class Main {
    public static void main(String[] args) throws Exception {
        UserDaoInter userDao = Context.instanceUserDao();
        UserSkillDaoInter userSkillDao = Context.instanceUserSkillDao();
        EmploymentHistoryDaoInter empHis = Context.instanceEmploymentHistoryDao();
        SkillDaoInter skillDaoInter = Context.instanceSkillDao();
        CountryDaoInter countryDaoInter = Context.instanceCountryDao();
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

//        User u = userDao.getById(2);
//        u.setName("sohret");
//        userDao.updateUser(u);

//        User u2 = new User(0, "john", "shelbey", "john_shelbey@gmail.com", "+9943346444");
//        userDao.addUser(u2);

        System.out.println(countryDaoInter.getAllCountries());

    }
}

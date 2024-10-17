package dao.inter;

import bean.User;

import java.util.List;

public interface UserDaoInter {
    List<User> getAll();

    User getById(int id);

    boolean addUser(User u);

    boolean updateUser(User u);

    boolean removeUser(int id);
}

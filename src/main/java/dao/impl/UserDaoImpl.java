package dao.impl;

import bean.User;
import dao.inter.AbstractDao;
import dao.inter.UserDaoInter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl extends AbstractDao implements UserDaoInter {
    @Override
    public List<User> getAll() {
        List<User> result = new ArrayList<>();
        try {
            Connection connection = connect();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("Select * from user");

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String surname = rs.getString("surname");
                String email = rs.getString("email");
                String phone = rs.getString("phone");

                result.add(new User(id, name, surname, email, phone));
            }
            connection.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return result;
    }

    @Override
    public User getById(int userId) {
        User result = null;
        try {
            Connection connection = connect();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("Select * from user where id=" + userId);

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String surname = rs.getString("surname");
                String email = rs.getString("email");
                String phone = rs.getString("phone");

                result = new User(id, name, surname, email, phone);
            }
            connection.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return result;
    }

    @Override
    public boolean addUser(User u) {
        try (Connection connection = connect()) {
            PreparedStatement stmt = connection.prepareStatement("insert into user(name, surname, email, phone) " +
                    "values(?, ?, ?, ?)");
            stmt.setString(1, u.getName());
            stmt.setString(2, u.getSurname());
            stmt.setString(3, u.getEmail());
            stmt.setString(4, u.getPhone());
            return stmt.execute();
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    //try-with-resources ile yazanda close yazmaga ehtiyac olmur
    @Override
    public boolean updateUser(User u) {
        try (Connection connection = connect()) {
            PreparedStatement stmt = connection.prepareStatement("update user set name=?," +
                    " surname=?, email=?, phone=? where id=?");
            stmt.setString(1, u.getName());
            stmt.setString(2, u.getSurname());
            stmt.setString(3, u.getEmail());
            stmt.setString(4, u.getPhone());
            stmt.setInt(5, u.getId());
            return stmt.execute();
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean removeUser(int id) {
        try (Connection connection = connect()) {
            Statement stmt = connection.createStatement();
            return stmt.execute("delete from user where id=" + id);
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }
}

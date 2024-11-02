package dao.impl;

import entity.Nationality;
import entity.User;
import dao.inter.AbstractDao;
import dao.inter.UserDaoInter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

public class UserDaoImpl extends AbstractDao implements UserDaoInter {
    private User getUser(ResultSet rs) throws Exception {
        int id = rs.getInt("id");
        String name = rs.getString("name");
        String surname = rs.getString("surname");
        String email = rs.getString("email");
        String phone = rs.getString("phone");
        String profileDesc = rs.getString("profile_description");
        String address = rs.getString("address");
        int birthPlaceId = rs.getInt("birthplace_id");
        String birthPlaceStr = rs.getString("birthplace");
        int nationalityId = rs.getInt("nationality_id");
        String nationalityStr = rs.getString("nationality");
        Date birthDate = rs.getDate("birthDate");

        Nationality nationality = new Nationality(nationalityId, nationalityStr, null);
        Nationality birthPlace = new Nationality(birthPlaceId, birthPlaceStr, null);

        return new User(id, name, surname, email, phone, profileDesc, address, birthDate, nationality, birthPlace);
    }

    @Override
    public List<User> getAll() {
        List<User> result = new ArrayList<>();
        try {
            Connection connection = connect();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("select " +
                    "u.*, n.name as nationality, c.country_name as birthplace " +
                    "from user u " +
                    "left join nationality n on u.nationality_id = n.id " +
                    "left join nationality c on u.birthplace_id = c.id;");

            while (rs.next()) {
                User u = getUser(rs);
                result.add(u);
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
            ResultSet rs = stmt.executeQuery("select " +
                    "u.*, n.name as nationality, c.country_name as birthplace " +
                    "from user u " +
                    "left join nationality n on u.nationality_id = n.id " +
                    "left join nationality c on u.birthplace_id = c.id where u.id=" + userId);

            while (rs.next()) {
                result = getUser(rs);
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
            PreparedStatement stmt = connection.prepareStatement("insert into user(name, surname, email, phone, profile_description, address, birthdate) " +
                    "values(?, ?, ?, ?, ?, ?, ?)");
            stmt.setString(1, u.getName());
            stmt.setString(2, u.getSurname());
            stmt.setString(3, u.getEmail());
            stmt.setString(4, u.getPhone());
            stmt.setString(5, u.getProfileDesc());
            stmt.setString(6, u.getAddress());
            stmt.setDate(7, u.getBirthDate());
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
                    " surname=?, email=?, phone=?, profile_description=?, address=?, birthdate=?, birthplace_id=?, nationality_id=? where id=?");
            stmt.setString(1, u.getName());
            stmt.setString(2, u.getSurname());
            stmt.setString(3, u.getEmail());
            stmt.setString(4, u.getPhone());
            stmt.setString(5, u.getProfileDesc());
            stmt.setString(6, u.getAddress());
            stmt.setDate(7, u.getBirthDate());
            stmt.setInt(8, u.getBirthPlace().getId());
            stmt.setInt(9, u.getNationality().getId());
            stmt.setInt(10, u.getId());
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

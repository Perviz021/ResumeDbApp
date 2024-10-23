package dao.impl;

import dao.inter.AbstractDao;
import dao.inter.EmploymentHistoryDaoInter;
import entity.EmploymentHistory;
import entity.Skill;
import entity.User;
import entity.UserSkill;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EmploymentHistoryDaoImpl extends AbstractDao implements EmploymentHistoryDaoInter {

    private EmploymentHistory getEmploymentHistory(ResultSet rs) throws Exception {
        Integer id = rs.getInt("id");
        Integer userId = rs.getInt("user_id");
        Date beginDate = rs.getDate("begin_date");
        Date endDate = rs.getDate("end_date");
        String header = rs.getString("header");
        String jobDescription = rs.getString("job_description");

        User user = new User(userId);
        return new EmploymentHistory(id, header, beginDate, endDate, jobDescription, user);
    }

    @Override
    public List<EmploymentHistory> getAllEmploymentHistoryByUserId(int userId) {
        List<EmploymentHistory> result = new ArrayList<>();
        try {
            Connection connection = connect();
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM resume.employment_history WHERE user_id = ?");
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                EmploymentHistory u = getEmploymentHistory(rs);
                result.add(u);
            }
            connection.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return result;
    }
}

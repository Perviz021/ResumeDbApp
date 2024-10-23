package dao.impl;

import dao.inter.AbstractDao;
import dao.inter.UserSkillDaoInter;
import entity.Skill;
import entity.User;
import entity.UserSkill;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UserSkillDaoImpl extends AbstractDao implements UserSkillDaoInter {

    private UserSkill getUserSkill(ResultSet rs) throws Exception {
        int id = rs.getInt("id");
        int userId = rs.getInt("user_id");
        int skillId = rs.getInt("skill_id");
        String skillName = rs.getString("skill_name");
        int power = rs.getInt("power");

        Skill skill = new Skill(skillId, skillName);
        User user = new User(userId);
        return new UserSkill(id, skill, user, power);
    }

    @Override
    public List<UserSkill> getAllSkillsByUserId(int userId) {
        List<UserSkill> result = new ArrayList<>();
        try {
            Connection connection = connect();
            PreparedStatement stmt = connection.prepareStatement("""
                    select u.*, us.id as user_id, us.skill_id, us.power, s.name as skill_name
                    from user_skill us
                    left join user u on us.user_id = u.id
                    left join skill s on us.skill_id = s.id
                    where us.user_id = ?;""");
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                UserSkill u = getUserSkill(rs);
                result.add(u);
            }
            connection.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return result;
    }
}
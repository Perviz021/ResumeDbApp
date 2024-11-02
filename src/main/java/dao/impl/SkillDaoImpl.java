package dao.impl;

import dao.inter.AbstractDao;
import dao.inter.SkillDaoInter;
import entity.Skill;
import entity.User;
import entity.UserSkill;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SkillDaoImpl extends AbstractDao implements SkillDaoInter {
    private Skill getSkill(ResultSet rs) throws Exception {
        int id = rs.getInt("id");
        String name = rs.getString("name");

        return new Skill(id, name);
    }

    @Override
    public List<Skill> getAllSkills() {
        List<Skill> result = new ArrayList<>();
        try {
            Connection connection = connect();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("Select * from skill");

            while (rs.next()) {
                Skill u = getSkill(rs);
                result.add(u);
            }
            connection.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return result;
    }
}

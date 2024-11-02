package dao.inter;

import entity.User;
import entity.UserSkill;

import java.util.List;

public interface UserSkillDaoInter {

    List<UserSkill> getAllSkillsByUserId(int userId);

    boolean addUserSkill(UserSkill u);

    boolean removeUserSkill(int id);
}
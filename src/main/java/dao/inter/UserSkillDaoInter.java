package dao.inter;

import entity.UserSkill;

import java.util.List;

public interface UserSkillDaoInter {

    List<UserSkill> getAllSkillsByUserId(int userId);
}
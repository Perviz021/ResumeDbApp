package main;

import dao.impl.EmploymentHistoryDaoImpl;
import dao.impl.UserDaoImpl;
import dao.impl.UserSkillDaoImpl;
import dao.inter.EmploymentHistoryDaoInter;
import dao.inter.UserDaoInter;
import dao.inter.UserSkillDaoInter;

public class Context {
    public static UserDaoInter instanceUserDao() {
        return new UserDaoImpl();
    }

    public static UserSkillDaoInter instanceUserSkillDao() {
        return new UserSkillDaoImpl();
    }

    public static EmploymentHistoryDaoInter instanceEmploymentHistoryDao() {
        return new EmploymentHistoryDaoImpl();
    }
}

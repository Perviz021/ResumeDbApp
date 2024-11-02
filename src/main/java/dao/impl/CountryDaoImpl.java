package dao.impl;

import dao.inter.AbstractDao;
import dao.inter.CountryDaoInter;
import dao.inter.SkillDaoInter;
import entity.Nationality;
import entity.Skill;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CountryDaoImpl extends AbstractDao implements CountryDaoInter {
    private Nationality getNationality(ResultSet rs) throws Exception {
        int id = rs.getInt("id");
        String name = rs.getString("name");
        String countryName = rs.getString("country_name");

        return new Nationality(id, name, countryName);
    }

    @Override
    public List<Nationality> getAllCountries() {
        List<Nationality> result = new ArrayList<>();
        try {
            Connection connection = connect();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("Select * from nationality");

            while (rs.next()) {
                Nationality u = getNationality(rs);
                result.add(u);
            }
            connection.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return result;
    }
}

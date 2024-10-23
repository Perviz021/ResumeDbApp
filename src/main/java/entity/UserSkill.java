package entity;

public class UserSkill {
    private int id;
    private Skill skill;
    private User user;
    private int power;

    public UserSkill() {
    }

    public UserSkill(int id, Skill skill, User user, int power) {
        this.id = id;
        this.skill = skill;
        this.user = user;
        this.power = power;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Skill getSkill() {
        return skill;
    }

    public void setSkill(Skill skill) {
        this.skill = skill;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    @Override
    public String toString() {
        return "UserSkill{" +
                "id=" + id +
                ", skill=" + skill +
                ", user=" + user +
                ", power=" + power +
                '}';
    }
}

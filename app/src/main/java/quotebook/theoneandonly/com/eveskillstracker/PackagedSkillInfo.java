package quotebook.theoneandonly.com.eveskillstracker;

import java.io.Serializable;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PackagedSkillInfo implements Serializable  {





    private String name; //name of skill
    private String description; //description of skill
    private float skillrank;
    private int active_skill_level;
    private int skillpoints_in_skill;
    private int trained_skill_level;

    PackagedSkillInfo(String name, String description, float skillrank,
                      int active_skill_level, int skillpoints_in_skill, int trained_skill_level ){


        this.name = name; //name of skill
        this.description = description; //description of skill
        this.skillrank = skillrank;
        this.active_skill_level = active_skill_level;
        this.skillpoints_in_skill = skillpoints_in_skill;
        this.trained_skill_level = trained_skill_level;


    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getSkillrank() {
        return skillrank;
    }

    public void setSkillrank(float skillrank) {
        this.skillrank = skillrank;
    }

    public int getActive_skill_level() {
        return active_skill_level;
    }

    public void setActive_skill_level(int active_skill_level) {
        this.active_skill_level = active_skill_level;
    }

    public int getSkillpoints_in_skill() {
        return skillpoints_in_skill;
    }

    public void setSkillpoints_in_skill(int skillpoints_in_skill) {
        this.skillpoints_in_skill = skillpoints_in_skill;
    }

    public int getTrained_skill_level() {
        return trained_skill_level;
    }

    public void setTrained_skill_level(int trained_skill_level) {
        this.trained_skill_level = trained_skill_level;
    }
}






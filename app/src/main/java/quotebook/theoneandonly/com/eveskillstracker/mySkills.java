package quotebook.theoneandonly.com.eveskillstracker;

import java.io.Serializable;
import java.util.List;

/**
 * Created by fvf on 6/4/18.
 */

public class mySkills implements Serializable {

     private List<skills> skills;

    public List<mySkills.skills> getSkills() {
        return skills;
    }

    public class skills implements Serializable {
        private int active_skill_level;
        private int skill_id;
        private int skillpoints_in_skill;
        private int trained_skill_level;

        public int getActive_skill_level() {
            return active_skill_level;
        }

        public int getSkill_id() {
            return skill_id;
        }

        public int getSkillpoints_in_skill() {
            return skillpoints_in_skill;
        }

        public int getTrained_skill_level() {
            return trained_skill_level;
        }
    }
}






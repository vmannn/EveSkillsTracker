package quotebook.theoneandonly.com.eveskillstracker;

import java.io.Serializable;
import java.util.List;


//API mapping for queued skills.

public class QUS implements Serializable {


        private String finish_date;
        private int finished_level;
        private int queue_position;
        private int skill_id;
        private String start_date;

        public String getFinish_date() {
            return finish_date;
        }

        public int getFinished_level() {
            return finished_level;
        }

        public int getQueue_position() {
            return queue_position;
        }

        public int getSkill_id() {
            return skill_id;
        }

        public String getStrart_date(){
            return start_date;
        }
    }

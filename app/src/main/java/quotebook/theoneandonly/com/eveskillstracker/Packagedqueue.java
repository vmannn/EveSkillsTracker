package quotebook.theoneandonly.com.eveskillstracker;

import java.io.Serializable;

public class Packagedqueue implements Serializable {


    private String finish_date;
    private String name; //name of skill
    private String description;
    private int queue_position;
    private String start_date;

    public String getFinish_date() {
        return finish_date;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getQueue_position() {
        return queue_position;
    }

    public String getStart_date() {
        return start_date;
    }

    public Packagedqueue(String finish_date, String name, String description, int queue_position, String start_date) {
        this.finish_date = finish_date;
        this.name = name;
        this.description = description;
        this.queue_position = queue_position;
        this.start_date = start_date;
    }
}

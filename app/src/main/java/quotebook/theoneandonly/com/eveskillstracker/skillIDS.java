package quotebook.theoneandonly.com.eveskillstracker;

public class skillIDS {

    private int type_id;

//Class to capture each skill ID

    skillIDS(int type_id){

        this.type_id = type_id;

    }

    public int getType_id() {
        return type_id;
    }

    public void setType_id(int type_id) {
        this.type_id = type_id;
    }
}

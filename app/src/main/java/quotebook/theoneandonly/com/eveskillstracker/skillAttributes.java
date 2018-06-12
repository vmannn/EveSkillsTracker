package quotebook.theoneandonly.com.eveskillstracker;

import java.util.List;

public class skillAttributes {


    private float capacity;
    private String description;
    private List<dogma_attributes> dogma_attributes;

    public List<skillAttributes.dogma_attributes> getDogma_attributes() {
        return dogma_attributes;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }


    public class dogma_attributes{

       private int attribute_id;
       private float value;

        public float getValue() {
            return value;
        }

        public int getAttribute_id() {
            return attribute_id;
        }
    }

    private int group_id;
    private  int     icon_id;
    private int     market_group_id;
    private float      mass;
    private String      name;
    private float     packaged_volume;
    private int      portion_size;
    private boolean     published;
    private float     radius;
    private int      type_id;
    private float      volume;

}

package quotebook.theoneandonly.com.eveskillstracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

public class ViewQueue extends AppCompatActivity {


    private TextView skillinfo;
    private int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_queue);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        ArrayList<Packagedqueue> bundledskills = (ArrayList<Packagedqueue>) intent.getSerializableExtra("queuepack");


        skillinfo = (TextView) findViewById(R.id.qdisplay);


        if(bundledskills.size() == 0){

            skillinfo.setText("You have no skills currently in the training queue...");
            return;


        }
else{
        for(; bundledskills.size() > i; ++i) {

            if (i == 0) {

                skillinfo.setText("Position in queue: " + String.valueOf(bundledskills.get(i).getQueue_position()) +
                        "\n" + "Skill name: " + bundledskills.get(i).getName() +
                        "\n" + "Description: " + bundledskills.get(i).getDescription() +
                        "\n" + "Training time complete date: " + bundledskills.get(i).getFinish_date() +
                        "\n" + "Training time start date: " + bundledskills.get(i).getStart_date() +
                        "\n\n\n");
            } else {

                skillinfo.append("Position in queue: " + String.valueOf(bundledskills.get(i).getQueue_position()) +
                        "\n" + "Skill name: " + bundledskills.get(i).getName() +
                        "\n" + "Description: " + bundledskills.get(i).getDescription() +
                        "\n" + "Training time complete date: " + bundledskills.get(i).getFinish_date() +
                        "\n" + "Training time start date: " + bundledskills.get(i).getStart_date() +
                        "\n\n\n");


            }


        }


        }







    }
}

package quotebook.theoneandonly.com.eveskillstracker;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.widget.TextView;

import java.util.ArrayList;

public class ViewSkills extends Activity {


    TextView allskills;
    int i = 0; //counter

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_skills);


        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        ArrayList<PackagedSkillInfo> bundledskills = (ArrayList<PackagedSkillInfo>) intent.getSerializableExtra("skillpack");

        allskills = (TextView) findViewById(R.id.skills);

        //Goes through skills and displays the necessary info
        for(; i < bundledskills.size(); ++i){

            if(i == 0) {
                allskills.setText("\n\n\nSkill: " + bundledskills.get(i).getName() +
                        "\n" + "Description: " + bundledskills.get(i).getDescription() +
                        "\n" + "Skillrank: " + String.valueOf(Math.round(bundledskills.get(i).getSkillrank())) +
                        "\n" + "Active Skill level: " + String.valueOf(bundledskills.get(i).getActive_skill_level()) +
                        "\n" + "Skill Points in Skill: " + String.valueOf(bundledskills.get(i).getSkillpoints_in_skill()) +
                        "\n" + "Trained Skill Level: " + String.valueOf(bundledskills.get(i).getTrained_skill_level()) +
                        "\n\n\n");

            }


            else{

                allskills.append("Skill: " + bundledskills.get(i).getName() +
                        "\n" + "Description: " + bundledskills.get(i).getDescription() +
                        "\n" + "Skillrank: " + String.valueOf(Math.round(bundledskills.get(i).getSkillrank())) +
                        "\n" + "Active Skill level: " + String.valueOf(bundledskills.get(i).getActive_skill_level()) +
                        "\n" + "Skill Points in Skill: " + String.valueOf(bundledskills.get(i).getSkillpoints_in_skill()) +
                        "\n" + "Trained Skill Level: " + String.valueOf(bundledskills.get(i).getTrained_skill_level()) +
                        "\n\n\n");


            }




        }










    }
}

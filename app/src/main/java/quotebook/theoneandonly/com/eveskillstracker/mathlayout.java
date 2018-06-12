package quotebook.theoneandonly.com.eveskillstracker;

import android.support.v4.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.Fragment;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class mathlayout extends AppCompatActivity {


    //This is the layout file for the skill projector.
    //Uses fragments for each skill so that each calculation
    //for each skill may act on its own

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mathlayout);
        getSupportActionBar().setTitle("Eve Skills Tracker");


        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        ArrayList <PackagedSkillInfo> bundledskills = (ArrayList<PackagedSkillInfo>) intent.getSerializableExtra("skillpack");



        LinearLayout mylay = (LinearLayout) findViewById(R.id.fraglinear);

        int i = 0;

        for(; i < bundledskills.size(); ++i){ //each skill gets put into its own
            //fragment instance.



            FrameLayout frame = new FrameLayout(this);
            frame.setId(i);
            mylay.addView(frame);

            Fragment mathfrag = new SkillMath();
            //retrieve data for each skill and put it into each fragment
            Bundle bundle = new Bundle();
            bundle.putString("skillname", bundledskills.get(i).getName());
            bundle.putFloat("skillrank", bundledskills.get(i).getSkillrank());
            bundle.putInt("active_skill", bundledskills.get(i).getActive_skill_level());
            bundle.putInt("sk_in_skill", bundledskills.get(i).getSkillpoints_in_skill());
            bundle.putInt("trained_sk_level", bundledskills.get(i).getTrained_skill_level());

            mathfrag.setArguments(bundle); //send the data to the fragment


            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

            transaction.add(i, mathfrag);
            transaction.commit();



        }









    }
}

package quotebook.theoneandonly.com.eveskillstracker;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;



public class SkillMath extends Fragment {

    private TextView skillname;
    private TextView skill_rank;
    private TextView activeskill;
    private TextView skillpointsinskill;
    private TextView trainedskilllevel;
    private EditText mytext;
    private Button submit;
    private int number;
    private TextView response;

    //skill level sp needed
    private int LI =  250;
    private int LII = 1414;
    private int LIII = 8000;
    private int LIV = 45255;
    private int  LV = 256000;
    private int returnvalue;
    private int startpoint;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_skill_math, container, false);


        final String skill_name = getArguments().getString("skillname");
        final float skillrank = getArguments().getFloat("skillrank");
        final int active_skill = getArguments().getInt("active_skill");
        final int skillpoints_in_skill = getArguments().getInt("sk_in_skill");
        final int trained_skill_level = getArguments().getInt("trained_sk_level");

        skillname = (TextView) view.findViewById(R.id.skillname);
        skillname.setText("Skill: " + skill_name);

        skill_rank = (TextView) view.findViewById(R.id.skillrank);
        skill_rank.setText("Skill Rank: " + String.valueOf(Math.round(skillrank)));

        activeskill = (TextView) view.findViewById(R.id.activeskill);
        activeskill.setText("Active Skill Level: " + String.valueOf(Math.round(active_skill)));

        skillpointsinskill = (TextView) view.findViewById(R.id.skinskill);
        skillpointsinskill.setText("Skill Points in Skill: " + String.valueOf(Math.round(skillpoints_in_skill)));

        trainedskilllevel = (TextView) view.findViewById(R.id.trainedskilllevel);
        trainedskilllevel.setText("Trained Skill Level: " + String.valueOf(Math.round(trained_skill_level)));

        mytext = (EditText) view.findViewById(R.id.enterskilllevel);

        submit = (Button) view.findViewById(R.id.enter);

        response = (TextView) view.findViewById(R.id.response);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                number = Integer.valueOf(mytext.getText().toString());

                if(number <= active_skill) {
                    response.setText("Please enter a skill level that's lower than" +
                            " your active skill level");
                    return;
                }
                    if(active_skill == 1) {

                        startpoint = LI * Math.round(skillrank);
                    }

                if(active_skill == 2){

                    startpoint = LII * Math.round(skillrank);


                }

                if(active_skill == 3){

                    startpoint = LIII * Math.round(skillrank);


                }

                if(active_skill == 4){

                    startpoint = LIV * Math.round(skillrank);

                }


                if(number == 2) {
                            int returnvalue = (LII * Math.round(skillrank)) - startpoint;

                            response.setText("You will need " + returnvalue +
                                    " SP to advance to level " + String.valueOf(number));
                        }

                        if(number == 3){

                            int returnvalue = (LIII * Math.round(skillrank)) - startpoint;

                            response.setText("You will need " + returnvalue +
                                    " SP to advance to level " + String.valueOf(number));

                        }

                        if(number == 4){

                            int returnvalue = (LIV * Math.round(skillrank)) - startpoint;

                            response.setText("You will need " + returnvalue +
                                    " SP to advance to level " + String.valueOf(number));


                        }

                        if(number == 5){

                            int returnvalue = (LV * Math.round(skillrank)) - startpoint;

                            response.setText("You will need " + returnvalue +
                                    " SP to advance to level " + String.valueOf(number));




                        }

            }








        });










        // Inflate the layout for this fragment
        return view;
    }


    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */

}

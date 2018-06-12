package quotebook.theoneandonly.com.eveskillstracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class LoggedIn extends AppCompatActivity {
    private ImageView characterpic;
    private TextView charismat;
    private TextView intelligencet;
    private TextView memoryt;
    private TextView perceptiont;
    private TextView willpowert;
    private TextView moneyt;
    private TextView namet;
    private Button viewskills;
    private Button viewqskills;
    private Button skill_math;
    ArrayList<PackagedSkillInfo> my_skillpack;
    ArrayList<Packagedqueue> my_queuepack;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logged_in);



        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        String charisma = extras.getString("charisma");
        String intelligence = extras.getString("intelligence");
        String memory = extras.getString("memory");
        String perception = extras.getString("perception");
        String willpower = extras.getString("willpower");
        float money = extras.getFloat("money");
        String named = extras.getString("name");
        String picture = extras.getString("picture");
        my_queuepack = (ArrayList<Packagedqueue>) intent.getSerializableExtra("qpack");
        List skills = (List<mySkills>) intent.getSerializableExtra("skills");
        my_skillpack = (ArrayList<PackagedSkillInfo>) intent.getSerializableExtra("skillpack");

        characterpic = (ImageView) findViewById(R.id.characterpic);
        loadImageFromUrl(picture);

        charismat = (TextView) findViewById(R.id.charisma);
        charismat.setText("Charisma: " + charisma);

        intelligencet = (TextView) findViewById(R.id.intelligence);
        intelligencet.setText("Intelligence: " + intelligence);

        memoryt = (TextView) findViewById(R.id.memory);
        memoryt.setText("Memory: " + memory);

        perceptiont = (TextView) findViewById(R.id.perception);
        perceptiont.setText("Perception: " + perception);

        willpowert = (TextView) findViewById(R.id.willpower);
        willpowert.setText("Willpower: " + willpower);


        String mecash = Float.toString(money);
        moneyt = (TextView) findViewById(R.id.wallet);
        moneyt.setText("My Wallet Balance: " + mecash);

        namet = (TextView) findViewById(R.id.name);
        namet.setText("Welcome " + named + " !");

        viewskills = (Button) findViewById(R.id.skills);
        viewqskills = (Button) findViewById(R.id.qskills);
        skill_math = (Button) findViewById(R.id.calculator);

        viewskills.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(LoggedIn.this, ViewSkills.class);
                Bundle extras = new Bundle();
                extras.putSerializable("skillpack", (Serializable) my_skillpack);
                intent.putExtras(extras);
                startActivity(intent);



            }
        });


        skill_math.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(LoggedIn.this, mathlayout.class);
                Bundle extras = new Bundle();
                extras.putSerializable("skillpack", my_skillpack);
                intent.putExtras(extras);
                startActivity(intent);




            }
        });


        viewqskills.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent queue = new Intent(LoggedIn.this, ViewQueue.class);
                Bundle thequeue = new Bundle();
                thequeue.putSerializable("queuepack", (Serializable) my_queuepack);
                queue.putExtras(thequeue);
                startActivity(queue);




            }
        });

    }

    private void loadImageFromUrl(String url){
        Picasso.with(this).load(url).placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .into(characterpic, new com.squareup.picasso.Callback(){

                    @Override
                    public void onSuccess (){


                    }

                    public void onError (){
                        Toast.makeText(LoggedIn.this, "Cant display character photo :(", Toast.LENGTH_SHORT).show();



                    }

                });



    }
}

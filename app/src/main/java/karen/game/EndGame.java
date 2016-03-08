package karen.game;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class EndGame extends AppCompatActivity {

    DatabaseHelper helper = new DatabaseHelper(this);
    Typeface tf;
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_game);
        //this textview is used to display the score
        tv=(TextView) findViewById(R.id.textView);
        //font for the textView
        tf=Typeface.createFromAsset(getAssets(),"StreetwiseBuddy.ttf");
        tv.setTypeface(tf);
        String score="";
        //gets the extra message sent from the play activity which is the score
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            score = extras.getString("score");
        }
        //changes the text to the score
        tv.setText(score);
    }

    public void saveScore(View v)
    {
        int scorestr= Integer.parseInt( tv.getText().toString());
        EditText name = (EditText)findViewById(R.id.textView2);
        String namestr = name.getText().toString();
        tv.setTypeface(tf);
        name.setTypeface(tf);
        Player c = new Player();
        c.setName(namestr);
        c.setHscore(scorestr);

        helper.insertPlayer(c);

        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }
}

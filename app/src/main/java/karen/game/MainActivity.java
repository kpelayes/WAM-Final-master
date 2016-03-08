package karen.game;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // declare typefaces for the fonts
        Typeface titleFont=Typeface.createFromAsset(getAssets(),"FFF_Tusj.ttf");
        Typeface font=Typeface.createFromAsset(getAssets(),"StreetwiseBuddy.ttf");
        TextView title=(TextView) findViewById(R.id.Title);
        //sets the title's font
        title.setTypeface(titleFont);

        Button play=(Button) findViewById(R.id.play);
        Button leaderboard=(Button) findViewById(R.id.highScores);
        //sets fonts for all the buttons
        play.setTypeface(font);
        leaderboard.setTypeface(font);


        //play button's click handler
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //creates a new intent and starts the Play activity
                Intent intent = new Intent(getApplicationContext(), Play.class);
                startActivity(intent);
            }
        });

        //highScores' button's click handler
        leaderboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //creates a new intent and starts the Play activity
                Intent intent = new Intent(getApplicationContext(), HighScores.class);
                startActivity(intent);
            }
        });
    }
}

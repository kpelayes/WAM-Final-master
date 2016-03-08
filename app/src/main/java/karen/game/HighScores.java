package karen.game;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class HighScores extends AppCompatActivity {

    DatabaseHelper helper = new DatabaseHelper(this);
    List<Player> dataList;
    TextView names;
    TextView scores;
    TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_scores);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Typeface tf=Typeface.createFromAsset(getAssets(),"StreetwiseBuddy.ttf");
        names = (TextView)findViewById(R.id.textView3);
        scores = (TextView)findViewById(R.id.textView4);
        title = (TextView)findViewById(R.id.textView5);

        names.setTypeface(tf);
        scores.setTypeface(tf);
        title.setTypeface(tf);

        dataList = helper.getAll();
        Collections.sort(dataList, new Comparator<Player>() {
            @Override public int compare(Player p1, Player p2) {
                return p2.getHscore()-(p1.getHscore()); // Ascending
            }

        });
        for(int i = 0; i<dataList.size(); i++)
        {

            names.append(dataList.get(i).getName());
            scores.append(dataList.get(i).getHscore()+"");
            names.append("\n");
            scores.append("\n");
        }
    }


}

package karen.game;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class Play extends AppCompatActivity {
    //s keeps track of the score
    private int s=0;
    //textviews to display the timer and score
    private TextView time;
    private TextView score;
    //creates the timer
    private CountDownTimer countDownTimer;
    //the countdown starts at 45 seconds int milliseconds
    private final long startTime = 45 * 1000;
    //the on tick interval is set to half a second in milliseconds
    private final long interval = 1 * 500;
    //imagebuttons to refer to all 9 moles
    private ImageButton m1;
    private ImageButton m2;
    private ImageButton m3;
    private ImageButton m4;
    private ImageButton m5;
    private ImageButton m6;
    private ImageButton m7;
    private ImageButton m8;
    private ImageButton m9;
    //handlers to schedule when the moles will go down and to wait before going to the end game activity
    private Handler handler;
    private Handler handler2;
    //font for the score and timer text
    private Typeface font;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        time=(TextView) findViewById(R.id.timer);
        score=(TextView) findViewById(R.id.score);
        m1=(ImageButton) findViewById(R.id.imageButton);
        m2=(ImageButton) findViewById(R.id.imageButton2);
        m3=(ImageButton) findViewById(R.id.imageButton3);
        m4=(ImageButton) findViewById(R.id.imageButton4);
        m5=(ImageButton) findViewById(R.id.imageButton5);
        m6=(ImageButton) findViewById(R.id.imageButton6);
        m7=(ImageButton) findViewById(R.id.imageButton7);
        m8=(ImageButton) findViewById(R.id.imageButton8);
        m9=(ImageButton) findViewById(R.id.imageButton9);
        font=Typeface.createFromAsset(getAssets(),"StreetwiseBuddy.ttf");
        time.setTypeface(font);
        score.setTypeface(font);

        //disables all the moles
        clear();

        //creates the countdown and starts it
        countDownTimer = new MyCountDownTimer(startTime, interval);
        countDownTimer.start();


    }
    public class MyCountDownTimer extends CountDownTimer {
        public MyCountDownTimer(long startTime, long interval) {
            super(startTime, interval);
        }
        @Override
        //when the timer finishes...
        public void onFinish() {
            //display Time's up
            time.setText("Time's up!");
            //disable all the moles
            clear();
            //create a handler to wait 2 seconds before starting the EndGame activity and send the score
            handler2 = new Handler();
            handler2.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(getApplicationContext(), EndGame.class);
                    String sc=String.valueOf(s);
                    intent.putExtra("score", sc);
                    startActivity(intent);
                }
            }, 2000);
        }
        //this function will get called every half a second until the countdown is finished
        public void onTick(long millisUntilFinished) {
            //change the time to display how much time is left since it's in milliseconds it has to be divided by 1000 to display seconds
            time.setText("0:" + millisUntilFinished / 1000);
            //generate a random number and depending on the number enable a certain mole
            int r=(int)(Math.random()*10);
            if (r==1){
                if (!m1.isEnabled())
                    enableMole(m1);
            }else if (r==2){
                if (!m2.isEnabled())
                    enableMole(m2);
            }
            else if (r==3){
                if (!m3.isEnabled())
                    enableMole(m3);
            }
            else if (r==4){
                if (!m4.isEnabled())
                    enableMole(m4);
            }
            else if (r==5){
                if (!m5.isEnabled())
                    enableMole(m5);
            }
            else if (r==6){
                if (!m6.isEnabled())
                    enableMole(m6);
            }
            else if (r==7){
                if (!m7.isEnabled())
                    enableMole(m7);
            }
            else if (r==8){
                if (!m8.isEnabled())
                    enableMole(m8);
            }
            else {
                if (!m9.isEnabled())
                    enableMole(m9);
            }
        }
    }
    //the imagebuttons' onClick attribute in the xml is set to call this function to increment the score
    //it only gets called if it's clicked while enabled
    public void incrementScore(View v) {
        //score gets incremented by 1 and then changes the text of the score
        s++;
        score.setText("Score: " + s);
        //gets the id of the mole that was clicked on
        ImageButton ibutton=(ImageButton) findViewById(v.getId());

        //changes the image back to a molehill and then disables it
        ibutton.setImageResource(R.drawable.molehill);
        ibutton.setEnabled(false);
    }
    //disables all the moles
    public void clear(){
        m1.setEnabled(false);
        m2.setEnabled(false);
        m3.setEnabled(false);
        m4.setEnabled(false);
        m5.setEnabled(false);
        m6.setEnabled(false);
        m7.setEnabled(false);
        m8.setEnabled(false);
        m9.setEnabled(false);
    }
    //change the image to display a mole and create a handler to wait 2 seconds before disabling it and changing the image again to display a molehill
    public void enableMole(final ImageButton m){
        m.setEnabled(true);
        m.setImageResource(R.drawable.mole);
        handler = new Handler();
        handler.postDelayed(new Runnable() {
            ImageButton mole=(ImageButton) findViewById(m.getId());
            @Override
            public void run() {
                mole.setEnabled(false);
                mole.setImageResource(R.drawable.molehill);
            }
        }, 2000);
    }

}

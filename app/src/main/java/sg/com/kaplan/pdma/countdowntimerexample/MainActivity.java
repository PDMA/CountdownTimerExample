package sg.com.kaplan.pdma.countdowntimerexample;

import android.os.CountDownTimer;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {
    Button buttonPlay, buttonStop;
    TextView textViewStatus;
    boolean isPlaying = false;
    CountDownTimer timer;
    long timeLeft = 10000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonPlay = (Button) findViewById(R.id.buttonPlay);
        buttonStop = (Button) findViewById(R.id.buttonStop);
        textViewStatus = (TextView) findViewById(R.id.textViewStatus);

        buttonStop.setEnabled(false);

        buttonPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonPlay.setEnabled(false);
                buttonStop.setEnabled(true);

                if (isPlaying == false) {
                    timer = new CountDownTimer(timeLeft, 1000) {
                        @Override
                        public void onTick(long millisUntilFinished) {
                            timeLeft = millisUntilFinished;
                            textViewStatus.setText(millisUntilFinished / 1000 + "s");
                        }

                        @Override
                        public void onFinish() {
                            textViewStatus.setText("Countdown completed");
                            buttonPlay.setEnabled(false);
                            buttonStop.setText("Reset");
                            isPlaying = false;
                            timeLeft = 10000;
                        }
                    };
                    timer.start();

                    buttonPlay.setText("Pause");
                    isPlaying = true;
                    buttonPlay.setEnabled(true);
                } else {
                    buttonPlay.setEnabled(false);
                    timer.cancel();
                    buttonPlay.setText("Resume");
                    isPlaying = false;
                    buttonPlay.setEnabled(true);
                }
            }
        });

        buttonStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isPlaying) {
                    timer.cancel();
                }

                buttonStop.setEnabled(false);
                buttonPlay.setEnabled(false);
                buttonPlay.setText("Play");
                buttonStop.setText("Stop");
                textViewStatus.setText("10s");
                timeLeft = 10000;
                isPlaying = false;
                buttonPlay.setEnabled(true);
                buttonStop.setEnabled(true);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

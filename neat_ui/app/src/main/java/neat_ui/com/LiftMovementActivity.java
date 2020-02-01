package neat_ui.com;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class LiftMovementActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lift_movement);

        final Vibrator vibe = (Vibrator) LiftMovementActivity.this.getSystemService(Context.VIBRATOR_SERVICE);
        final MediaPlayer mp_counter = MediaPlayer.create(this, R.raw.pipes);
        final MediaPlayer mp_table = MediaPlayer.create(this, R.raw.flick);
        final MediaPlayer mp_base = MediaPlayer.create(this, R.raw.trill);
        int lift_height = 0;

        final ImageButton button_up = findViewById(R.id.lift_up);

        if(lift_height == 80) {
            button_up.setEnabled(false);
            button_up.setBackgroundResource(R.drawable.disabled_round_corners);
        } else {
            button_up.setEnabled(true);
            button_up.setOnTouchListener(new View.OnTouchListener() {
                public boolean onTouch(View v, MotionEvent event) {
                    if (event.getAction() == android.view.MotionEvent.ACTION_DOWN) {
                        button_up.setBackgroundResource(R.drawable.pressed_green_button);
                        vibe.vibrate(300000000);
                    } else if (event.getAction() == android.view.MotionEvent.ACTION_UP) {
                        button_up.setBackgroundResource(R.drawable.green_rounded_corner);
                        vibe.cancel();
                    }
                    return false;
                }
            });
        }

        final ImageButton button_down = findViewById(R.id.lift_down);

        if(lift_height == 0) {
            button_down.setEnabled(false);
            button_down.setBackgroundResource(R.drawable.disabled_round_corners);
        } else {
            button_down.setEnabled(true);
            button_down.setOnTouchListener(new View.OnTouchListener() {
                public boolean onTouch(View v, MotionEvent event) {
                    if (event.getAction() == android.view.MotionEvent.ACTION_DOWN) {
                        button_down.setBackgroundResource(R.drawable.pressed_green_button);
                        vibe.vibrate(300000000);
                    } else if (event.getAction() == android.view.MotionEvent.ACTION_UP) {
                        button_down.setBackgroundResource(R.drawable.green_rounded_corner);
                        vibe.cancel();
                    }
                    return false;
                }
            });
        }

        Button button_counter = findViewById(R.id.counter);

        if(lift_height == 80) {
            button_counter.setEnabled(false);
            button_counter.setBackgroundResource(R.drawable.disabled_round_corners);
        } else {
            button_counter.setEnabled(true);
            button_counter.setOnClickListener(new View.OnClickListener() {

                @Override

                public void onClick(View v) {
                    mp_counter.start();
                    vibe.vibrate(100);
                }

            });
        }

        Button button_table = findViewById(R.id.table);

        if(lift_height == 40) {
            button_table.setEnabled(false);
            button_table.setBackgroundResource(R.drawable.disabled_round_corners);
        } else {
            button_table.setEnabled(true);
            button_table.setOnClickListener(new View.OnClickListener() {

                @Override

                public void onClick(View v) {
                    mp_table.start();
                    vibe.vibrate(100);
                }

            });
        }

        Button button_base = findViewById(R.id.base);

        if(lift_height == 0) {
            button_base.setEnabled(false);
            button_base.setBackgroundResource(R.drawable.disabled_round_corners);
        } else {
            button_base.setEnabled(true);
            button_base.setOnClickListener(new View.OnClickListener() {

                @Override

                public void onClick(View v) {
                    mp_table.start();
                    vibe.vibrate(100);
                }

            });
        }
    }
}

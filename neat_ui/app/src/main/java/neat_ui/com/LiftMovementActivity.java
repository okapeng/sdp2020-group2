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

        ImageButton button_up = findViewById(R.id.lift_up);

        button_up.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == android.view.MotionEvent.ACTION_DOWN ) {
                    vibe.vibrate(300000000);
                } else
                if(event.getAction() == android.view.MotionEvent.ACTION_UP){
                    vibe.cancel();
                }
                return false;
            }
        });

        ImageButton button_down = findViewById(R.id.lift_down);

        button_down.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == android.view.MotionEvent.ACTION_DOWN ) {
                    vibe.vibrate(300000000);
                } else
                if(event.getAction() == android.view.MotionEvent.ACTION_UP){
                    vibe.cancel();
                }
                return false;
            }
        });

        Button button_counter = findViewById(R.id.counter);

        button_counter.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {

                mp_counter.start();
                vibe.vibrate(100);

            }

        });

        Button button_table = findViewById(R.id.table);

        button_table.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {

                mp_table.start();
                vibe.vibrate(100);

            }

        });

        Button button_base = findViewById(R.id.base);

        button_base.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {

                mp_base.start();
                vibe.vibrate(100);

            }

        });
    }
}

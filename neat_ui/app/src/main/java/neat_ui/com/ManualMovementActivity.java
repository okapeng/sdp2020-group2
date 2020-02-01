package neat_ui.com;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;

public class ManualMovementActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manual_movement);

        final Vibrator vibe = (Vibrator) ManualMovementActivity.this.getSystemService(Context.VIBRATOR_SERVICE);

        ImageButton button_up = findViewById(R.id.up);

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

        ImageButton button_down = findViewById(R.id.down);

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

        ImageButton button_left = findViewById(R.id.left);

        button_left.setOnTouchListener(new View.OnTouchListener() {
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

        ImageButton button_right = findViewById(R.id.right);

        button_right.setOnTouchListener(new View.OnTouchListener() {
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

        ImageButton button_rotate_r = findViewById(R.id.rotate_right);

        button_rotate_r.setOnTouchListener(new View.OnTouchListener() {
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

        ImageButton button_rotate_l = findViewById(R.id.rotate_left);

        button_rotate_l.setOnTouchListener(new View.OnTouchListener() {
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

    }
}

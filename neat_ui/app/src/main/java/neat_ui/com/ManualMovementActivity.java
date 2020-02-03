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

        final ImageButton button_up = findViewById(R.id.up);

        button_up.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == android.view.MotionEvent.ACTION_DOWN ) {
                    button_up.setBackgroundResource(R.drawable.pressed_green_button);
                    vibe.vibrate(900000000);
                    //MainActivity.mConnectedThread.write("move forward");
                } else if(event.getAction() == android.view.MotionEvent.ACTION_UP){
                    button_up.setBackgroundResource(R.drawable.green_rounded_corner);
                    vibe.cancel();
                    //MainActivity.mConnectedThread.write("stop");
                }
                return false;
            }
        });

        final ImageButton button_down = findViewById(R.id.down);

        button_down.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == android.view.MotionEvent.ACTION_DOWN ) {
                    button_down.setBackgroundResource(R.drawable.pressed_green_button);
                    vibe.vibrate(900000000);
                    //MainActivity.mConnectedThread.write("move backward");
                } else if(event.getAction() == android.view.MotionEvent.ACTION_UP){
                    button_down.setBackgroundResource(R.drawable.green_rounded_corner);
                    vibe.cancel();
                    //MainActivity.mConnectedThread.write("stop");
                }
                return false;
            }
        });

        final ImageButton button_left = findViewById(R.id.left);

        button_left.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == android.view.MotionEvent.ACTION_DOWN ) {
                    button_left.setBackgroundResource(R.drawable.pressed_green_button);
                    vibe.vibrate(900000000);
                    //MainActivity.mConnectedThread.write("move left");
                } else if(event.getAction() == android.view.MotionEvent.ACTION_UP){
                    button_left.setBackgroundResource(R.drawable.green_rounded_corner);
                    vibe.cancel();
                    //MainActivity.mConnectedThread.write("stop");
                }
                return false;
            }
        });

        final ImageButton button_right = findViewById(R.id.right);

        button_right.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == android.view.MotionEvent.ACTION_DOWN ) {
                    button_right.setBackgroundResource(R.drawable.pressed_green_button);
                    vibe.vibrate(900000000);
                    //MainActivity.mConnectedThread.write("move right");
                } else if(event.getAction() == android.view.MotionEvent.ACTION_UP){
                    button_right.setBackgroundResource(R.drawable.green_rounded_corner);
                    vibe.cancel();
                    //MainActivity.mConnectedThread.write("stop");
                }
                return false;
            }
        });

        final ImageButton button_rotate_r = findViewById(R.id.rotate_right);

        button_rotate_r.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == android.view.MotionEvent.ACTION_DOWN ) {
                    button_rotate_r.setBackgroundResource(R.drawable.pressed_yellow_circle);
                    vibe.vibrate(900000000);
                    //MainActivity.mConnectedThread.write("rotate right");
                } else if(event.getAction() == android.view.MotionEvent.ACTION_UP){
                    button_rotate_r.setBackgroundResource(R.drawable.yellow_circle);
                    vibe.cancel();
                    //MainActivity.mConnectedThread.write("stop");
                }
                return false;
            }
        });

        final ImageButton button_rotate_l = findViewById(R.id.rotate_left);

        button_rotate_l.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == android.view.MotionEvent.ACTION_DOWN ) {
                    button_rotate_l.setBackgroundResource(R.drawable.pressed_yellow_circle);
                    vibe.vibrate(900000000);
                    //MainActivity.mConnectedThread.write("rotate left");
                } else if(event.getAction() == android.view.MotionEvent.ACTION_UP){
                    button_rotate_l.setBackgroundResource(R.drawable.yellow_circle);
                    vibe.cancel();
                    //MainActivity.mConnectedThread.write("stop");
                }
                return false;
            }
        });

    }
}

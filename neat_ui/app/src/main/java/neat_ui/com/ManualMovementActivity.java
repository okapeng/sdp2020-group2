package neat_ui.com;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;

public class ManualMovementActivity extends AppCompatActivity {
    final TcpConnectionService tcp = new TcpConnectionService("192.168.105.101", 4445);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manual_movement);

        //tcp.execute();

        final Vibrator vibe = (Vibrator) ManualMovementActivity.this.getSystemService(Context.VIBRATOR_SERVICE);

        final ImageButton button_up = findViewById(R.id.up);

        button_up.setOnTouchListener(new View.OnTouchListener() {

            private Handler mHandler;

            @Override public boolean onTouch(View v, MotionEvent event) {
                switch(event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        button_up.setBackgroundResource(R.drawable.pressed_green_button);
                        vibe.vibrate(900000000);
                        if (mHandler != null) return true;
                        mHandler = new Handler();
                        mHandler.postDelayed(mAction, 100);
                        break;
                    case MotionEvent.ACTION_UP:
                        button_up.setBackgroundResource(R.drawable.green_rounded_corner);
                        vibe.cancel();
                        if (mHandler == null) return true;
                        mHandler.removeCallbacks(mAction);
                        mHandler = null;
                        break;
                }
                return false;
            }

            Runnable mAction = new Runnable() {
                @Override public void run() {
                    //tcp.send("forward");
                    System.out.println("forward");
                    mHandler.postDelayed(this, 100);
                }
            };

        });

        final ImageButton button_down = findViewById(R.id.down);

        button_down.setOnTouchListener(new View.OnTouchListener() {

            private Handler mHandler;

            @Override public boolean onTouch(View v, MotionEvent event) {
                switch(event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        button_down.setBackgroundResource(R.drawable.pressed_green_button);
                        vibe.vibrate(900000000);
                        if (mHandler != null) return true;
                        mHandler = new Handler();
                        mHandler.postDelayed(mAction, 100);
                        break;
                    case MotionEvent.ACTION_UP:
                        button_down.setBackgroundResource(R.drawable.green_rounded_corner);
                        vibe.cancel();
                        if (mHandler == null) return true;
                        mHandler.removeCallbacks(mAction);
                        mHandler = null;
                        break;
                }
                return false;
            }

            Runnable mAction = new Runnable() {
                @Override public void run() {
                    //tcp.send("back");
                    System.out.println("back");
                    mHandler.postDelayed(this, 100);
                }
            };

        });

        final ImageButton button_left = findViewById(R.id.left);

        button_left.setOnTouchListener(new View.OnTouchListener() {

            private Handler mHandler;

            @Override public boolean onTouch(View v, MotionEvent event) {
                switch(event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        button_left.setBackgroundResource(R.drawable.pressed_green_button);
                        vibe.vibrate(900000000);
                        if (mHandler != null) return true;
                        mHandler = new Handler();
                        mHandler.postDelayed(mAction, 100);
                        break;
                    case MotionEvent.ACTION_UP:
                        button_left.setBackgroundResource(R.drawable.green_rounded_corner);
                        vibe.cancel();
                        if (mHandler == null) return true;
                        mHandler.removeCallbacks(mAction);
                        mHandler = null;
                        break;
                }
                return false;
            }

            Runnable mAction = new Runnable() {
                @Override public void run() {
                    //tcp.send("left");
                    System.out.println("left");
                    mHandler.postDelayed(this, 100);
                }
            };

        });

        final ImageButton button_right = findViewById(R.id.right);

        button_right.setOnTouchListener(new View.OnTouchListener() {

            private Handler mHandler;

            @Override public boolean onTouch(View v, MotionEvent event) {
                switch(event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        button_right.setBackgroundResource(R.drawable.pressed_green_button);
                        vibe.vibrate(900000000);
                        if (mHandler != null) return true;
                        mHandler = new Handler();
                        mHandler.postDelayed(mAction, 100);
                        break;
                    case MotionEvent.ACTION_UP:
                        button_right.setBackgroundResource(R.drawable.green_rounded_corner);
                        vibe.cancel();
                        if (mHandler == null) return true;
                        mHandler.removeCallbacks(mAction);
                        mHandler = null;
                        break;
                }
                return false;
            }

            Runnable mAction = new Runnable() {
                @Override public void run() {
                    //tcp.send("right");
                    System.out.println("right");
                    mHandler.postDelayed(this, 100);
                }
            };

        });

        final ImageButton button_rotate_r = findViewById(R.id.rotate_right);

        button_rotate_r.setOnTouchListener(new View.OnTouchListener() {

            private Handler mHandler;

            @Override public boolean onTouch(View v, MotionEvent event) {
                switch(event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        button_rotate_r.setBackgroundResource(R.drawable.pressed_yellow_circle);
                        vibe.vibrate(900000000);
                        if (mHandler != null) return true;
                        mHandler = new Handler();
                        mHandler.postDelayed(mAction, 100);
                        break;
                    case MotionEvent.ACTION_UP:
                        button_rotate_r.setBackgroundResource(R.drawable.yellow_circle);
                        vibe.cancel();
                        if (mHandler == null) return true;
                        mHandler.removeCallbacks(mAction);
                        mHandler = null;
                        break;
                }
                return false;
            }

            Runnable mAction = new Runnable() {
                @Override public void run() {
                    //tcp.send("rotr");
                    System.out.println("rotr");
                    mHandler.postDelayed(this, 100);
                }
            };

        });

        final ImageButton button_rotate_l = findViewById(R.id.rotate_left);

        button_rotate_l.setOnTouchListener(new View.OnTouchListener() {

            private Handler mHandler;

            @Override public boolean onTouch(View v, MotionEvent event) {
                switch(event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        button_rotate_l.setBackgroundResource(R.drawable.pressed_yellow_circle);
                        vibe.vibrate(900000000);
                        if (mHandler != null) return true;
                        mHandler = new Handler();
                        mHandler.postDelayed(mAction, 100);
                        break;
                    case MotionEvent.ACTION_UP:
                        button_rotate_l.setBackgroundResource(R.drawable.yellow_circle);
                        vibe.cancel();
                        if (mHandler == null) return true;
                        mHandler.removeCallbacks(mAction);
                        mHandler = null;
                        break;
                }
                return false;
            }

            Runnable mAction = new Runnable() {
                @Override public void run() {
                    //tcp.send("rotl");
                    System.out.println("rotl");
                    mHandler.postDelayed(this, 100);
                }
            };

        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        tcp.close();
    }
}

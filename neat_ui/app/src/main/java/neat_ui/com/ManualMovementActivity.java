package neat_ui.com;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.nfc.Tag;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class ManualMovementActivity extends AppCompatActivity {
    TcpClient tcpClient;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manual_movement);
        tcpClient = new TcpClient();
        tcpClient.connect();
        final Vibrator vibe = (Vibrator) ManualMovementActivity.this.getSystemService(Context.VIBRATOR_SERVICE);

        final ImageButton button_up = findViewById(R.id.up);

        button_up.setOnTouchListener(new View.OnTouchListener() {

            private Handler mHandler;

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        button_up.setBackgroundResource(R.drawable.pressed_green_button);
                        vibe.vibrate(900000000);
                        if (mHandler != null) return true;
                        mHandler = new Handler();
                        mHandler.postDelayed(mAction, 20);
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
                @Override
                public void run() {
                    tcpClient.send("forward");
                    mHandler.postDelayed(this, 20);
                }
            };

        });

        final ImageButton button_down = findViewById(R.id.down);

        button_down.setOnTouchListener(new View.OnTouchListener() {

            private Handler mHandler;

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        button_down.setBackgroundResource(R.drawable.pressed_green_button);
                        vibe.vibrate(900000000);
                        if (mHandler != null) return true;
                        mHandler = new Handler();
                        mHandler.postDelayed(mAction, 20);
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
                @Override
                public void run() {
                    tcpClient.send("back");
                    mHandler.postDelayed(this, 20);
                }
            };

        });

        final ImageButton button_left = findViewById(R.id.left);

        button_left.setOnTouchListener(new View.OnTouchListener() {

            private Handler mHandler;

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        button_left.setBackgroundResource(R.drawable.pressed_green_button);
                        vibe.vibrate(900000000);
                        if (mHandler != null) return true;
                        mHandler = new Handler();
                        mHandler.postDelayed(mAction, 20);
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
                @Override
                public void run() {
                    tcpClient.send("left");
                    mHandler.postDelayed(this, 20);
                }
            };

        });

        final ImageButton button_right = findViewById(R.id.right);

        button_right.setOnTouchListener(new View.OnTouchListener() {

            private Handler mHandler;

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        button_right.setBackgroundResource(R.drawable.pressed_green_button);
                        vibe.vibrate(900000000);
                        if (mHandler != null) return true;
                        mHandler = new Handler();
                        mHandler.postDelayed(mAction, 20);
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
                @Override
                public void run() {
                    tcpClient.send("right");
                    mHandler.postDelayed(this, 20);
                }
            };

        });

        final ImageButton button_rotate_r = findViewById(R.id.rotate_right);

        button_rotate_r.setOnTouchListener(new View.OnTouchListener() {

            private Handler mHandler;

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        button_rotate_r.setBackgroundResource(R.drawable.pressed_yellow_circle);
                        vibe.vibrate(900000000);
                        if (mHandler != null) return true;
                        mHandler = new Handler();
                        mHandler.postDelayed(mAction, 20);
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
                @Override
                public void run() {
                    tcpClient.send("rotr");
                    mHandler.postDelayed(this, 20);
                }
            };

        });

        final ImageButton button_rotate_l = findViewById(R.id.rotate_left);

        button_rotate_l.setOnTouchListener(new View.OnTouchListener() {

            private Handler mHandler;

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        button_rotate_l.setBackgroundResource(R.drawable.pressed_yellow_circle);
                        vibe.vibrate(900000000);
                        if (mHandler != null) return true;
                        mHandler = new Handler();
                        mHandler.postDelayed(mAction, 20);
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
                @Override
                public void run() {
                    tcpClient.send("rotl");
                    mHandler.postDelayed(this, 20);
                }
            };

        });

        final ImageButton button_dtr = findViewById(R.id.dtr);

        button_dtr.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == android.view.MotionEvent.ACTION_DOWN ) {
                    button_dtr.setBackgroundResource(R.drawable.pressed_green_button);
                    vibe.vibrate(900000000);
                } else
                if(event.getAction() == android.view.MotionEvent.ACTION_UP){
                    button_dtr.setBackgroundResource(R.drawable.green_rounded_corner);
                    vibe.cancel();
                }
                return false;
            }
        });

        final ImageButton button_dtl = findViewById(R.id.dtl);

        button_dtl.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == android.view.MotionEvent.ACTION_DOWN ) {
                    button_dtl.setBackgroundResource(R.drawable.pressed_green_button);
                    vibe.vibrate(900000000);
                } else
                if(event.getAction() == android.view.MotionEvent.ACTION_UP){
                    button_dtl.setBackgroundResource(R.drawable.green_rounded_corner);
                    vibe.cancel();
                }
                return false;
            }
        });

        final ImageButton button_dbr = findViewById(R.id.dbr);

        button_dbr.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == android.view.MotionEvent.ACTION_DOWN ) {
                    button_dbr.setBackgroundResource(R.drawable.pressed_green_button);
                    vibe.vibrate(900000000);
                } else
                if(event.getAction() == android.view.MotionEvent.ACTION_UP){
                    button_dbr.setBackgroundResource(R.drawable.green_rounded_corner);
                    vibe.cancel();
                }
                return false;
            }
        });

        final ImageButton button_dbl = findViewById(R.id.dbl);

        button_dbl.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == android.view.MotionEvent.ACTION_DOWN ) {
                    button_dbl.setBackgroundResource(R.drawable.pressed_green_button);
                    vibe.vibrate(900000000);
                } else
                if(event.getAction() == android.view.MotionEvent.ACTION_UP){
                    button_dbl.setBackgroundResource(R.drawable.green_rounded_corner);
                    vibe.cancel();
                }
                return false;
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        tcpClient.disconnect();
    }
}
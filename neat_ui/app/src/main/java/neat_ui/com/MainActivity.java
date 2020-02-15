package neat_ui.com;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.content.Intent;

import android.os.Handler;
import android.os.Vibrator;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity implements ConnectionPopUp.NoticeDialogListener{
    TcpClient mTcpClient;
    boolean isConnected = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Vibrator vibe = (Vibrator) MainActivity.this.getSystemService(Context.VIBRATOR_SERVICE);
        final MediaPlayer mp_manual = MediaPlayer.create(this, R.raw.pipes);
        final MediaPlayer mp_follow = MediaPlayer.create(this, R.raw.flick);
        final MediaPlayer mp_come = MediaPlayer.create(this, R.raw.trill);
        final MediaPlayer mp_lift = MediaPlayer.create(this, R.raw.coconuts);

        checkWhetherConnected(isConnected);

        final Button button_manual = findViewById(R.id.manual_mode);

        button_manual.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                if (!isConnected) {
                    Toast toast = Toast.makeText(getApplicationContext(), "Not connected to your N.E.A.T", Toast.LENGTH_LONG);
                    toast.show();
                    showConnectionPopup();
                } else {
                    mp_manual.start();
                    vibe.vibrate(100);
                    goToManualMovementActivity();
                }
            }
        });

        final Button button_follow = findViewById(R.id.follow);

        button_follow.setOnClickListener(new View.OnClickListener() {
            boolean isFollowing = false;
            private Handler mHandler;

            @Override

            public void onClick(View v) {
                if (!isConnected) {
                    Toast toast = Toast.makeText(getApplicationContext(), "Not connected to your N.E.A.T", Toast.LENGTH_LONG);
                    toast.show();
                    showConnectionPopup();
                } else {
                    if (!isFollowing) {
                        mp_follow.start();
                        vibe.vibrate(100);
                        mHandler = new Handler();
                        mHandler.postDelayed(mAction, 20);
                        button_follow.setBackgroundResource(R.drawable.red_rounded_corners);
                        button_follow.setText("Stop following");
                        isFollowing = true;
                    } else {
                        mp_follow.start();
                        vibe.vibrate(100);
                        mHandler.removeCallbacks(mAction);
                        mHandler = null;
                        mTcpClient.send("stop following");
                        button_follow.setBackgroundResource(R.drawable.yellow_rounded_corner);
                        button_follow.setText("Follow me");
                        isFollowing = false;
                    }
                }
            }

            Runnable mAction = new Runnable() {
                @Override
                public void run() {
                    mTcpClient.send("follow");
                    mHandler.postDelayed(this, 20);
                }
            };

        });

        final Button button_come = findViewById(R.id.come_to_me);

        button_come.setOnClickListener(new View.OnClickListener() {
            boolean isComing = false;

            @Override

            public void onClick(View v) {
                if (!isConnected) {
                    Toast toast = Toast.makeText(getApplicationContext(), "Not connected to your N.E.A.T", Toast.LENGTH_LONG);
                    toast.show();
                    showConnectionPopup();
                } else {
                    if (!isComing) {
                        mp_come.start();
                        vibe.vibrate(100);
                        button_come.setBackgroundResource(R.drawable.red_rounded_corners);
                        button_come.setText("Stop coming");
                        isComing = true;
                    } else {
                        mp_come.start();
                        vibe.vibrate(100);
                        button_come.setBackgroundResource(R.drawable.green_rounded_corner);
                        button_come.setText("Come to me");
                        isComing = false;
                    }
                }
            }
        });

        final Button button_lift = findViewById(R.id.lift_controls);

        button_lift.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (!isConnected) {
                    Toast toast = Toast.makeText(getApplicationContext(), "Not connected to your N.E.A.T", Toast.LENGTH_LONG);
                    toast.show();
                    showConnectionPopup();
                } else {
                    mp_lift.start();
                    vibe.vibrate(100);
                    goToLiftMovementActivity();
                }
            }
        });
    }

    private void goToManualMovementActivity() {

        Intent intent = new Intent(this, ManualMovementActivity.class);

        startActivity(intent);

    }

    private void goToLiftMovementActivity() {

        Intent intent = new Intent(this, LiftMovementActivity.class);

        startActivity(intent);

    }

    private void checkWhetherConnected(boolean isConnected) {
        if(!isConnected) {
            showConnectionPopup();
        }
    }

    private void showConnectionPopup() {
        DialogFragment dialog = new ConnectionPopUp();
        dialog.show(getSupportFragmentManager(), "connect pop-up");
    }

    // The dialog fragment receives a reference to this Activity through the
    // Fragment.onAttach() callback, which it uses to call the following methods
    // defined by the NoticeDialogFragment.NoticeDialogListener interface
    @Override
    public void onDialogPositiveClick(DialogFragment dialog) {
        // User touched the dialog's positive button
        mTcpClient = TcpClient.getInstance();
        mTcpClient.connect();
        isConnected = true;
    }

    @Override
    public void onDialogNegativeClick(DialogFragment dialog) {
        // User touched the dialog's negative button
        isConnected = false;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mTcpClient.disconnect();
    }
}

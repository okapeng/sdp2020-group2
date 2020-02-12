package neat_ui.com;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.content.Intent;

import android.os.Vibrator;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity implements ConnectionPopUp.NoticeDialogListener{
    TcpClient mTcpClient;
    boolean isConnected = true;

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

        if(!isConnected) {
            button_manual.setEnabled(false);
            button_manual.setBackgroundResource(R.drawable.disabled_round_corners);
        } else {
            button_manual.setEnabled(true);
            button_manual.setOnClickListener(new View.OnClickListener() {
                @Override

                public void onClick(View v) {
                    mp_manual.start();
                    vibe.vibrate(100);
                    goToManualMovementActivity();
                }
            });
        }

        final Button button_follow = findViewById(R.id.follow);

        if(!isConnected) {
            button_follow.setEnabled(false);
            button_follow.setBackgroundResource(R.drawable.disabled_round_corners);
        } else {
            button_follow.setEnabled(true);
            button_follow.setOnClickListener(new View.OnClickListener() {
                boolean isFollowing = false;

                @Override

                public void onClick(View v) {
                    if (!isFollowing) {
                        mp_follow.start();
                        vibe.vibrate(100);
                        button_follow.setBackgroundResource(R.drawable.red_rounded_corners);
                        button_follow.setText("Stop following");
                        isFollowing = true;
                    } else {
                        mp_follow.start();
                        vibe.vibrate(100);
                        button_follow.setBackgroundResource(R.drawable.yellow_rounded_corner);
                        button_follow.setText("Follow me");
                        isFollowing = false;
                    }
                }

            });
        }

        final Button button_come = findViewById(R.id.come_to_me);

        if(!isConnected) {
            button_come.setEnabled(false);
            button_come.setBackgroundResource(R.drawable.disabled_round_corners);
        } else {
            button_come.setEnabled(true);
            button_come.setOnClickListener(new View.OnClickListener() {
                boolean isComing = false;

                @Override

                public void onClick(View v) {
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

            });
        }

        final Button button_lift = findViewById(R.id.lift_controls);

        if(!isConnected) {
            button_lift.setEnabled(false);
            button_lift.setBackgroundResource(R.drawable.disabled_round_corners);
        } else {
            button_lift.setEnabled(true);
            button_lift.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    mp_lift.start();
                    vibe.vibrate(100);
                    goToLiftMovementActivity();
                }
            });
        }
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
        DialogFragment newFragment = new ConnectionPopUp();
        newFragment.show(getSupportFragmentManager(), "connect pop-up");
    }

    // The dialog fragment receives a reference to this Activity through the
    // Fragment.onAttach() callback, which it uses to call the following methods
    // defined by the NoticeDialogFragment.NoticeDialogListener interface
    @Override
    public void onDialogPositiveClick(DialogFragment dialog) {
        // User touched the dialog's positive button
        //mTcpClient = new TcpClient();
        //mTcpClient.connect();
        isConnected = true;
    }

    @Override
    public void onDialogNegativeClick(DialogFragment dialog) {
        // User touched the dialog's negative button
        isConnected = false;
    }
}

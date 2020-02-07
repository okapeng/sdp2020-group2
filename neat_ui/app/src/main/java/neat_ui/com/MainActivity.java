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


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Vibrator vibe = (Vibrator) MainActivity.this.getSystemService(Context.VIBRATOR_SERVICE);
        final MediaPlayer mp_manual = MediaPlayer.create(this, R.raw.pipes);
        final MediaPlayer mp_follow = MediaPlayer.create(this, R.raw.flick);
        final MediaPlayer mp_come = MediaPlayer.create(this, R.raw.trill);
        final MediaPlayer mp_lift = MediaPlayer.create(this, R.raw.coconuts);
        boolean isFollowing = false;
        boolean isComing = false;
        boolean isConnected = false;

        checkWhetherConnected(isConnected);

        final Button button_manual = findViewById(R.id.manual_mode);

        button_manual.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {
                mp_manual.start();
                vibe.vibrate(100);
                goToManualMovementActivity();
            }

        });

        Button button_follow = findViewById(R.id.follow);

        if(isFollowing) {
            button_follow.setEnabled(false);
            button_follow.setBackgroundResource(R.drawable.disabled_round_corners);
        } else {
            button_follow.setEnabled(true);
            button_follow.setOnClickListener(new View.OnClickListener() {

                @Override

                public void onClick(View v) {
                    mp_follow.start();
                    vibe.vibrate(100);
                }

            });
        }

        Button button_come = findViewById(R.id.come_to_me);

        if(isComing) {
            button_come.setEnabled(false);
            button_come.setBackgroundResource(R.drawable.disabled_round_corners);
        } else {
            button_come.setEnabled(true);
            button_come.setOnClickListener(new View.OnClickListener() {

                @Override

                public void onClick(View v) {
                    mp_come.start();
                    vibe.vibrate(100);
                }

            });
        }

        Button button_lift = findViewById(R.id.lift_controls);

        button_lift.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {
                mp_lift.start();
                vibe.vibrate(100);
                goToLiftMovementActivity();
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
        DialogFragment newFragment = new ConnectionPopUp();
        newFragment.show(getSupportFragmentManager(), "connect pop-up");
    }
}

package neat_ui.com;

import androidx.appcompat.app.AppCompatActivity;

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
        final MediaPlayer mp = MediaPlayer.create(this, R.raw.pipes);


        Button button_manual = findViewById(R.id.manual_mode);

        button_manual.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {

                mp.start();
                vibe.vibrate(100);
                goToManualMovementActivity();

            }

        });

        Button button_follow = findViewById(R.id.follow);

        button_follow.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {

                mp.start();
                vibe.vibrate(100);

            }

        });

        Button button_come = findViewById(R.id.come_to_me);

        button_come.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {

                mp.start();
                vibe.vibrate(100);

            }

        });

        Button button_lift = findViewById(R.id.lift_controls);

        button_lift.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {

                mp.start();
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
}

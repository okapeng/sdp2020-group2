package neat_ui.com;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.widget.ImageButton;

public class FollowActivity extends AppCompatActivity {
    TcpClient tcpClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_follow);

        //tcpClient = TcpClient.getInstance();

        final Vibrator vibe = (Vibrator) this.getSystemService(Context.VIBRATOR_SERVICE);
        final MediaPlayer mp_follow = MediaPlayer.create(this, R.raw.flick);

        //tcpClient.send("follow");

        final ImageButton button_stop = findViewById(R.id.stop);

        button_stop.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {
                mp_follow.start();
                vibe.vibrate(100);
                //tcpClient.send("stop-follow");
                goToMainActivity();
            }

        });

    }

    private void goToMainActivity() {

        Intent intent = new Intent(this, MainActivity.class);

        startActivity(intent);

    }

}

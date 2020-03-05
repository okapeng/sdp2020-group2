package neat_ui.com;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.content.Context;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.content.Intent;

import android.os.Handler;
import android.os.Vibrator;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.xiasuhuei321.loadingdialog.view.LoadingDialog;
import net.steamcrafted.loadtoast.LoadToast;



public class MainActivity extends AppCompatActivity implements ConnectionPopUp.NoticeDialogListener {
    TcpClient mTcpClient = TcpClient.getInstance();
    boolean isConnected = false;
    RobotHandler robotHandler = new RobotHandler(this) {
        @Override
        public void handleException(String exception) {
            System.out.println("handle exception " + exception);
            ExceptionPopup exceptionPopup = new ExceptionPopup(MainActivity.this);
            exceptionPopup.showPopup(exception);
        }

        @Override
        public void handleConnection(boolean isConnected) {
            System.out.println("Connection: "+isConnected);
            if (isConnected) {
//                lt.success();
                ld.loadSuccess();
                lt.hide();
            } else {
//                lt.error();
                ld.loadFailed();
            }

        }
    };

    LoadToast lt;
    LoadingDialog ld;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Vibrator vibe = (Vibrator) MainActivity.this.getSystemService(Context.VIBRATOR_SERVICE);
        final MediaPlayer mp_manual = MediaPlayer.create(this, R.raw.pipes);
        final MediaPlayer mp_follow = MediaPlayer.create(this, R.raw.flick);
        final MediaPlayer mp_come = MediaPlayer.create(this, R.raw.trill);
        final MediaPlayer mp_lift = MediaPlayer.create(this, R.raw.coconuts);

        lt = new LoadToast(this)
                .setProgressColor(Color.RED)
                .setTranslationY(100)
                .setBorderColor(Color.LTGRAY);

        if (!mTcpClient.isConnected()) {
            showConnectionPopup();
        }
        final ImageView button_setting = findViewById(R.id.setting);

        button_setting.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                vibe.vibrate(100);
                goToSettingActivity();
            }
        });

        final Button button_manual = findViewById(R.id.manual_mode);

        button_manual.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                if (!mTcpClient.isConnected()) {
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
                if (!mTcpClient.isConnected()) {
                    Toast toast = Toast.makeText(getApplicationContext(), "Not connected to your N.E.A.T", Toast.LENGTH_LONG);
                    toast.show();
                    showConnectionPopup();
                } else {
                    if (!isFollowing) {
                        mp_follow.start();
                        vibe.vibrate(100);
                        mTcpClient.send("follow");
                        button_follow.setBackgroundResource(R.drawable.red_rounded_corners);
                        button_follow.setText("Stop following");
                        isFollowing = true;
                    } else {
                        mp_follow.start();
                        vibe.vibrate(100);
                        mTcpClient.send("stop-follow");
                        button_follow.setBackgroundResource(R.drawable.yellow_rounded_corner);
                        button_follow.setText("Follow me");
                        isFollowing = false;
                    }
                }
            }

        });

        final Button button_come = findViewById(R.id.come_to_me);

        button_come.setOnClickListener(new View.OnClickListener() {
            boolean isComing = false;

            @Override

            public void onClick(View v) {
                if (!mTcpClient.isConnected()) {
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
                if (!mTcpClient.isConnected()) {
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

    private void goToSettingActivity() {
        Intent intent = new Intent(this, SettingPreference.class);
        startActivity(intent);
    }

    private void goToLiftMovementActivity() {

        Intent intent = new Intent(this, LiftMovementActivity.class);

        startActivity(intent);

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
//        lt.setText("Connecting to robot").show();
        ld = new LoadingDialog(this);
        ld.setLoadingText("connecting")
                .setSuccessText("connected to robot")
                .setFailedText("failed to connect")
                .setLoadSpeed(LoadingDialog.Speed.SPEED_TWO)
                .setShowTime(1)
                .show();
        mTcpClient = TcpClient.getInstance();
        mTcpClient.setRobotHandler(robotHandler);
        mTcpClient.connect();
    }

    @Override
    public void onDialogNegativeClick(DialogFragment dialog) {
        lt.setText("Please connect to robot before any operation").show();
//        lt.error();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mTcpClient.disconnect();
    }
}

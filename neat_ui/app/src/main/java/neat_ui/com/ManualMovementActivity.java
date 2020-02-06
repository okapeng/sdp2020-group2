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
import java.util.concurrent.ThreadPoolExecutor;

public class ManualMovementActivity extends AppCompatActivity {
//    final TcpConnectionService tcp = new TcpConnectionService("192.168.105.101", 4445);
    TcpClient mTcpClient;
    private final String TAG= TcpClient.class.getSimpleName();
    private String hostAddr = "192.168.105.101";
    private int port = 4445;
    private ThreadPoolExecutor mExecutorService;
    private String receiveMsg;
    private PrintWriter printWriter;
    private BufferedReader in;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manual_movement);

//        new ConnectTask().execute("execute");
        connect();

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
//                        tcp.send("stop");
                        System.out.println("stop");
                        break;
                }
                return false;
            }

            Runnable mAction = new Runnable() {
                @Override public void run() {
                    send("forward");
//                    new SendMessageTask().execute("forward");forward
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
//                        tcp.send("stop");
                        System.out.println("stop");
                        break;
                }
                return false;
            }

            Runnable mAction = new Runnable() {
                @Override public void run() {
                    send("back");
//                    new SendMessageTask().execute("back");
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
//                        tcp.send("stop");
                        System.out.println("stop");
                        break;
                }
                return false;
            }

            Runnable mAction = new Runnable() {
                @Override public void run() {
                    send("left");
//                    new SendMessageTask().execute("left");
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
//                        tcp.send("stop");
                        System.out.println("stop");
                        break;
                }
                return false;
            }

            Runnable mAction = new Runnable() {
                @Override public void run() {
                    send("right");
//                    new SendMessageTask().execute("right");
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
//                        tcp.send("stop");
                        System.out.println("stop");
                        break;
                }
                return false;
            }

            Runnable mAction = new Runnable() {
                @Override public void run() {
                    send("rotr");
                    System.out.println("rotr");
//                    new SendMessageTask().execute("rotr");
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
//                        tcp.send("stop");
                        System.out.println("stop");
                        break;
                }
                return false;
            }

            Runnable mAction = new Runnable() {
                @Override public void run() {
                    send("rotl");
//                    new SendMessageTask().execute("rotl");
                    System.out.println("rotl");
                    mHandler.postDelayed(this, 100);
                }
            };

        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.out.println("disconnected");
//        tcp.send("disconnected");
//        tcp.close();
//        new DisconnectTask().execute();
        disconnect();
    }

    public void connect() {
        mExecutorService.execute(new connectService());  //在一个新的线程中请求 Socket 连接
    }

    public void send(String msg) {
        mExecutorService.execute(new sendService(msg));
    }

    public void disconnect() {
        mExecutorService.execute(new sendService("0"));
    }

    private class sendService implements Runnable {
        private String msg;

        sendService(String msg) {
            this.msg = msg;
        }

        @Override
        public void run() {
            printWriter.println(this.msg);
        }
    }

    private class connectService implements Runnable {
        @Override
        public void run() {
            try {
                Socket socket = new Socket(hostAddr, port);
                socket.setSoTimeout(60000);
                printWriter = new PrintWriter(new BufferedWriter(new OutputStreamWriter(
                        socket.getOutputStream(), "UTF-8")), true);
                in = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
                receiveMsg();
            } catch (Exception e) {
                Log.e(TAG, ("connectService:" + e.getMessage()));
            }
        }
    }

    private void receiveMsg() {
        try {
            while (true) {
                if ((receiveMsg = in.readLine()) != null) {
                    Log.d(TAG, "receiveMsg:" + receiveMsg);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            System.out.println(receiveMsg);
                        }
                    });
                }
            }
        } catch (IOException e) {
            Log.e(TAG, "receiveMsg: ");
            e.printStackTrace();
        }


        /**
         * Sends a message using a background task to avoid doing long/network operations on the UI thread
         */
//    public class SendMessageTask extends AsyncTask<String, Void, Void> {
//
//        @Override
//        protected Void doInBackground(String... params) {
//            System.out.println("im in send messsage");
//
//            // send the message
//            mTcpClient.sendMessage(params[0]);
//
//            return null;
//        }
//    }
//
//    /**
//     * Disconnects using a background task to avoid doing long/network operations on the UI thread
//     */
//    public class DisconnectTask extends AsyncTask<Void, Void, Void> {
//
//        @Override
//        protected Void doInBackground(Void... voids) {
//
//            System.out.println("im in disconnect");
//
//            // disconnect
//            mTcpClient.stopClient();
//            mTcpClient = null;
//
//            return null;
//        }
//    }
//
//    public class ConnectTask extends AsyncTask<String, String, TcpClient> {
//
//        @Override
//        protected TcpClient doInBackground(String... message) {
//
//            //we create a TCPClient object and
//            mTcpClient = new TcpClient(new TcpClient.OnMessageReceived() {
//                @Override
//                //here the messageReceived method is implemented
//                public void messageReceived(String message) {
//                    //this method calls the onProgressUpdate
//                    publishProgress(message);
//                }
//            });
//            mTcpClient.run();
//
//            return null;
//        }
//    }
    }
}

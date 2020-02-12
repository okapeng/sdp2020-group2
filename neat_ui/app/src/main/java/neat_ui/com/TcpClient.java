package neat_ui.com;

import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TcpClient {
    private final String TAG = TcpClient.class.getSimpleName();
    //private String hostAddr = "192.168.105.152"; //for Pi
    private String hostAddr = "192.168.105.86";  //for EV3 INF31
    private int port = 6660;
    //private ThreadPoolExecutor mExecutorService; //this could need to be updated to initialise this
    private ExecutorService mExecutorService = Executors.newCachedThreadPool();
    private String receiveMsg;
    private PrintWriter printWriter;
    private BufferedReader in;


    public void connect() {
        mExecutorService.execute(new connectService());
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
                }
            }
        } catch (IOException e) {
            Log.e(TAG, "receiveMsg: ");
            e.printStackTrace();
        }
    }
}

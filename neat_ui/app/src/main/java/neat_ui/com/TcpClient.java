package neat_ui.com;

import android.os.Message;
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

import static neat_ui.com.RobotHandler.EXCEPTION;

public class TcpClient {
    private final String TAG = TcpClient.class.getSimpleName();
//    private String HOST = "172.20.10.9";
    private String HOST = "172.20.10.12";
//    private final String HOST = "192.168.105.86";
    private final int PORT = 4445;

    private ExecutorService mExecutorService = Executors.newCachedThreadPool();
    private Socket socket;
    private String receiveMsg;
    private PrintWriter printWriter;
    private BufferedReader in;
    private RobotHandler robotHandler;

    private static TcpClient instance = null;

    public static TcpClient getInstance() {
        if(instance == null) {
            instance = new TcpClient();
        }
        return instance;
    }

    public boolean isConnected(){
        return socket != null && socket.isConnected();
    }

    public void setRobotHandler(RobotHandler robotHandler) {
        this.robotHandler = robotHandler;
    }

    public void connect() {
        mExecutorService.execute(new connectService());
    }

    public void send(String msg) {
        mExecutorService.execute(new sendService(msg));
    }

    public void disconnect() {
        mExecutorService.execute(new sendService("disconnect"));
        mExecutorService.execute(new disconnectService());
    }

    private void receiveMsg() {
        try {
            while (true) {
                if ((receiveMsg = in.readLine()) != null) {
                    Robot.getInstance().callBack(receiveMsg);
                    Log.d(TAG, "receiveMsg:" + receiveMsg);
                }
            }
        } catch (IOException e) {
            Log.e(TAG, "receiveMsg: ");
        }
    }

    public void receiveMsg(RobotHandler handler) {
        try {
            while (true) {
                if ((receiveMsg = in.readLine()) != null) {
                    Robot.getInstance().callBack(handler, receiveMsg);
                    Log.d(TAG, "receiveMsg:" + receiveMsg);
                }
            }
        } catch (IOException e) {
            Message msg = new Message();
            msg.what = EXCEPTION;
            msg.obj = "Connection lost, please reconnect!";
            robotHandler.sendMessage(msg);
            Log.e(TAG, "receiveMsg: ");
            e.printStackTrace();
        }
    }


    private class connectService implements Runnable {
        @Override
        public void run() {
            Message handlerMsg = new Message();
            handlerMsg.what = RobotHandler.CONNECTION;
            try {
                socket = new Socket(HOST, PORT);
                socket.setSoTimeout(60000);
                printWriter = new PrintWriter(new BufferedWriter(new OutputStreamWriter(
                        socket.getOutputStream(), "UTF-8")), true);
                in = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
                handlerMsg.obj = isConnected();
                robotHandler.sendMessage(handlerMsg);
                    receiveMsg(robotHandler);
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(TAG, ("connectService:" + e.getMessage()));
                handlerMsg.obj = isConnected();
                robotHandler.sendMessage(handlerMsg);
            }
        }
    }

    private class sendService implements Runnable {
        private String msg;

        sendService(String msg) {
            this.msg = msg;
        }

        @Override
        public void run() {
            if (printWriter != null) {
                printWriter.println(this.msg);
            } else {
                Message handlerMsg = new Message();
                handlerMsg.what = EXCEPTION;
                handlerMsg.obj = "Lost connection to robot";
                robotHandler.sendMessage(handlerMsg);
            }
        }
    }

    private class disconnectService implements Runnable {
        @Override
        public void run() {
            try {
                if (printWriter != null) {
                    printWriter.flush();
                    printWriter.close();
                }
                if (in != null) {
                    in.close();
                }
                if (socket != null) {
                    socket.close();
                }
                printWriter = null;
                in = null;
                socket = null;

            } catch (Exception e) {
                Log.e(TAG, ("disconnectService:" + e.getMessage()));
            }
        }
    }
}

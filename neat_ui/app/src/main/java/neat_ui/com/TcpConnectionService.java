package neat_ui.com;


import android.os.AsyncTask;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TcpConnectionService extends AsyncTask {
    private String hostAddr = "192.168.105.101";
    private int port = 4445;

    private Socket socket;
    private PrintWriter printWriter;
    private ExecutorService mExecutorService;

    public TcpConnectionService(String hostAddr, int port) {
        this.hostAddr = hostAddr;
        this.port = port;
    }

    public void send(String msg) {
        mExecutorService.execute(new SendService(socket, msg));
        try {
            socket.getOutputStream().flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected Object doInBackground(Object[] objects) {
        try {
            this.socket = new Socket(hostAddr, port);
//            this.printWriter = new Psocket.getOutputStream());

            mExecutorService = Executors.newCachedThreadPool();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private class SendService implements Runnable {
        private Socket s;
        private String msg;

        SendService(Socket s, String msg) {
            this.s = s;
            this.msg = msg;
        }

        @Override
        public void run() {
            try {
                OutputStreamWriter out = new OutputStreamWriter(s.getOutputStream());
                out.write(this.msg);
                out.flush();
                out.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    public void close() {
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

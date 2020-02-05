package neat_ui.com;


import android.os.AsyncTask;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TcpConnectionService extends AsyncTask {
    private String hostAddr = "192.168.105.101";
    private int port = 4445;

    private Socket socket;
    private ExecutorService mExecutorService;

    public TcpConnectionService(String hostAddr, int port) {
        this.hostAddr = hostAddr;
        this.port = port;
    }

    public void send(String msg) {
        try {
            mExecutorService.execute(new SendService(socket.getOutputStream(), msg));
            socket.getOutputStream().flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected Object doInBackground(Object[] objects) {
        try {
            this.socket = new Socket(hostAddr, port);
            this.socket.setKeepAlive(true);
//            this.printWriter = new Psocket.getOutputStream());

            mExecutorService = Executors.newCachedThreadPool();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private class SendService implements Runnable {
        private OutputStream s;
        private String msg;

        SendService(OutputStream s, String msg) {
            this.s = s;
            this.msg = msg;
        }

        @Override
        public void run() {
            try {

                OutputStreamWriter out = new OutputStreamWriter(s);
                out.write(this.msg);
                out.flush();
                out.close();
                s.close();
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

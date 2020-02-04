package neat_ui.com;


import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TcpConnectionService {
    private final String hostAddr = "192.168.105.101";
    private final int port = 4444;

    private Socket socket;
    private PrintWriter printWriter;
    private ExecutorService mExecutorService;

//    public TcpConnectionService(String hostAddr, int port) {
//        this.hostAddr = hostAddr;
//        this.port = port;
//    }
    public TcpConnectionService() {
    }

    public void connect() {
        try {
            this.socket = new Socket(hostAddr, port);

            mExecutorService = Executors.newCachedThreadPool();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void send(String msg) {
        mExecutorService.execute(new SendService(msg));
    }

    private class SendService implements Runnable {
        private String msg;

        SendService(String msg) {
            this.msg = msg;
        }

        @Override
        public void run() {
            printWriter.println(this.msg);
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

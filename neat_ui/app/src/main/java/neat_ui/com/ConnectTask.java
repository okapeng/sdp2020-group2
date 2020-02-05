package neat_ui.com;

import android.os.AsyncTask;
import android.util.Log;

public class ConnectTask extends AsyncTask<String, String, TcpClient> {

    private TcpClient mTcpClient;

    @Override
    protected TcpClient doInBackground(String... message) {

        //we create a TCPClient object
        mTcpClient = new TcpClient(new TcpClient.OnMessageReceived() {
            @Override
            //here the messageReceived method is implemented
            public void messageReceived(String message) {
                //this method calls the onProgressUpdate
                Log.d("info", "receive " + message);
            }
        });
        mTcpClient.run();

        return null;
    }

    @Override
    protected void onProgressUpdate(String... values) {
        super.onProgressUpdate(values);
        //response received from server
        Log.d("test", "response " + values[0]);
        //process server response here....

    }
}
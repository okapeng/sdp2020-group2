package neat_ui.com;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;

import java.lang.ref.WeakReference;

public abstract class RobotHandler extends Handler {
    public static final int BATTERY = 1;
    public static final int EXCEPTION = 2;
    public static final int CONNECTION = 3;
    private WeakReference<Activity > mActivityReference;

    RobotHandler(Activity activity) {
        mActivityReference= new WeakReference<Activity>(activity);
    }

    @Override
    public void handleMessage(Message msg) {
        super.handleMessage(msg);
        System.out.println(msg.obj);
//        super.handleMessage(msg);dleMessage: " + (String) msg.obj);
//        handleException((String) msg.obj);
        switch (msg.what) {
            case BATTERY:
                handleException("Robot battery is low");
                break;
            case EXCEPTION:
                handleException((String) msg.obj);
                break;
            case CONNECTION:
                handleConnection((Boolean) msg.obj);
                break;
        }
//        try {
//            String[] tokens = ((String) msg.obj).split(":");
//            Method setter = Robot.class.getDeclaredMethod(String.format("set%s", tokens[0]), String.class);
//            setter.invoke(this, tokens[1]);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }

    public abstract void handleException(String exception);
    public abstract void handleConnection(boolean isConnected);

}
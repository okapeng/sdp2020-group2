package neat_ui.com;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;

import java.lang.ref.WeakReference;
import java.lang.reflect.Method;

public abstract class RobotHandler extends Handler {
    private final int BATTERY = 1;
    private final int EXCEPTION = 2;
    private WeakReference<Activity > mActivityReference;

    RobotHandler(Activity activity) {
        mActivityReference= new WeakReference<Activity>(activity);
    }

    @Override
    public void handleMessage(Message msg) {
        super.handleMessage(msg);
//        System.out.println("handleMessage: " + (String) msg.obj);
//        handleException((String) msg.obj);
        switch (msg.what) {
            case BATTERY: handleException("Robot battery is low");
            case EXCEPTION: handleException((String) msg.obj);
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

}
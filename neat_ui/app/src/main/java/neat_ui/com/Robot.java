package neat_ui.com;

import java.lang.reflect.Method;

public class Robot {
    private static Robot robot = null;
    private int battery = 100;

    public static Robot getInstance() {
        if(robot == null) {
            robot = new Robot();
        }
        return robot;
    }

    public void setBattery(String battery) throws Exception{
        this.battery = Integer.valueOf(battery);
    }

    public int getBattery() {
        return battery;
    }

    public void callBack(String msg){
        try {
            String[] tokens = msg.split(":");
            Method setter = Robot.class.getDeclaredMethod(String.format("set%s", tokens[0]), String.class);
            setter.invoke(this, tokens[1]);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

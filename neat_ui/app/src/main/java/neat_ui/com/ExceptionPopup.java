package neat_ui.com;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

public class ExceptionPopup {
    View popupView;
    PopupWindow popupWindow;

    public ExceptionPopup(Context context) {
        //Create a View object yourself through inflater
//        View popupView = LayoutInflater.from(context).inflate(R.layout.exception_popup,
//                null, false);
        popupView = LayoutInflater.from(context).inflate(R.layout.exception_popup,
                null, false);

        //Specify the length and width through constants
        int width = LinearLayout.LayoutParams.MATCH_PARENT;
        int height = LinearLayout.LayoutParams.MATCH_PARENT;
        //Make Inactive Items Outside Of PopupWindow
        boolean focusable = true;

//        //Create a window with our parameters
//        final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);
        popupWindow = new PopupWindow(popupView, width, height, focusable);


//        TextView test2 = popupView.findViewById(R.id.titleText);
//        test2.setText();
//
//        Button buttonEdit = popupView.findViewById(R.id.messageButton);
//        buttonEdit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                popupWindow.dismiss();
//
//            }
//        });
//
//
//        //Handler for clicking on the inactive zone of the window
//        popupView.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                //Close the window when clicked
//                popupWindow.dismiss();
//                return true;
//            }
//        });
//        popupWindow.showAtLocation(popupView, Gravity.NO_GRAVITY, width, height);
    }

    public void showPopup(String exceptionMsg) {
        TextView test2 = popupView.findViewById(R.id.titleText);
        test2.setText(exceptionMsg);

        Button buttonEdit = popupView.findViewById(R.id.messageButton);
        buttonEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();

            }
        });


        //Handler for clicking on the inactive zone of the window
        popupView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                //Close the window when clicked
                popupWindow.dismiss();
                return true;
            }
        });
        popupWindow.showAtLocation(popupView, Gravity.NO_GRAVITY,
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
    }
}

package auto.scroll.autoscrollingaplication;

import android.content.Context;
import android.graphics.PixelFormat;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.os.Parcelable;
import android.util.Log;
import android.view.DragEvent;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageButton;

import androidx.test.platform.app.InstrumentationRegistry;
//import com.android.monkeyrunner.MonkeyRunner;

import java.lang.reflect.Executable;

public class ScrollerButton {

    private static final String TAG = "ScrollerButton";
    private Context context;
    private LayoutInflater layoutInflater;
    private WindowManager.LayoutParams layoutParams;
    private View view;
    private WindowManager windowManager;
    private Runnable scrollCommand;
    private int startDelay;
    private int inBetweenDelay;
    private Handler handler;
    boolean[] isActivated = {false};

    public ScrollerButton(Context c, int startDelay, int inBetweenDelay, Runnable scrollCommand)
    {
        this.handler = new Handler();
        this.context = c;
        this.startDelay = startDelay;
        this.inBetweenDelay = inBetweenDelay;
        this.scrollCommand = scrollCommand;
        layoutInflater = LayoutInflater.from(context);
        view = layoutInflater.inflate(R.layout.outside_widget, null);
        ImageButton button = (ImageButton) view.findViewById(R.id.stop_and_start_button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("EEEEEE", "Triggered a click");
                if (!isActivated[0])
                {
//                    button.setBackgroundColor(getColor(R.color.stopColor));
                    button.setImageDrawable(new ColorDrawable(context.getColor(R.color.stopColor)));
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Log.d(TAG, "Window, " + startDelay + " - " + inBetweenDelay);
                            scrollCommand.run();
                            if (isActivated[0])
                            {
                                handler.postDelayed(this, inBetweenDelay);
                            }
                        }
                    }, startDelay);
                }
                else
                {
//                    button.setBackgroundColor(getColor(R.color.playColor));
                    button.setImageDrawable(new ColorDrawable(context.getColor(R.color.playColor)));
                    handler.removeCallbacks(null);

//                    stopScroll();
                }
                isActivated[0] = !isActivated[0];
            }
        });

        windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        layoutParams = new WindowManager.LayoutParams(
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.TYPE_ACCESSIBILITY_OVERLAY,

                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                PixelFormat.TRANSLUCENT);
        layoutParams.gravity = Gravity.TOP|Gravity.LEFT;



    }
    public void openWindow()
    {
        windowManager.addView(view,
                layoutParams
        );
    }

    public void closeWindow()
    {
        try
        {
            isActivated[0] = false;
            handler.removeCallbacks(null);
            windowManager.removeView(view);
            view.invalidate();
            ((ViewGroup) view.getParent()).removeAllViews();
        }
        catch (Exception e)
        {
            Log.d("EEEE", e.toString());
        }
    }

//    public void triggerScroll()
//    {
//        Log.d("Scrolling - ", "startDelay = " + startDelay + ", inBetweenDelay = " + inBetweenDelay);
//        UiDevice device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
////        DragEvent event =
////        view.dispatchDragEvent();
//
//        device.pressHome();
//        device.swipe(100, 200, 100, 600, 5);
//    }
}

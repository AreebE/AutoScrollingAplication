package auto.scroll.autoscrollingaplication;

import android.Manifest;
import android.app.Activity;
import android.app.Instrumentation;
import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.DragEvent;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.test.platform.app.InstrumentationRegistry;

public class MainActivity extends AppCompatActivity {
    private ScrollerButton scrollerButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            Log.d("EEEEE", Settings.canDrawOverlays(this) + "");
//            requestPermissions(new String[]{Settings.ACTION_ACCESSIBILITY_SETTINGS}, 0);

            Intent intent = new Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS);
            startActivity(intent);
//            Settings.Secure.putString(getContentResolver(),
//                    Settings.Secure.ENABLED_ACCESSIBILITY_SERVICES, "auto/scroll/autoscrollingaplication/SwipingService.java");
//            Settings.Secure.putString(getContentResolver(),
//                    Settings.Secure.ACCESSIBILITY_ENABLED, "1");
        }


        setContentView(R.layout.activity_main);
        ((Button) findViewById(R.id.test)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                UiDevice uiDevice = UiDevice.getInstance();
//                uiDevice.drag(200, 100, 200, 400,2);
                Intent intent = new Intent(MainActivity.this, SwipingService.class);
                stopService(intent);
                intent.putExtra(SwipingService.START_DELAY_KEY, 100);
                intent.putExtra(SwipingService.IN_BETWEEN_DELAY_KEY, 50);
                startForegroundService(intent);
                triggerScroll();
            }
        });



//
//        UiDevice =
//        Instrumentation ins = new Instrumentation();
//        ins.sendPointerSync(
//                MotionEvent.obtain(
//                        SystemClock.uptimeMillis(),
//                SystemClock.uptimeMillis(),
//                        MotionEvent.ACTION_DOWN,
//                        200,
//                        100,
//                        0));
//        ins.sendPointerSync(
//                MotionEvent.obtain(
//                        SystemClock.uptimeMillis(),
//                        SystemClock.uptimeMillis(),
//                        MotionEvent.ACTION_UP,
//                        200,
//                        300,
//                        0));
    }

    /**
     * Called when a touch screen event was not handled by any of the views
     * under it.  This is most useful to process touch events that happen
     * outside of your window bounds, where there is no view to receive it.
     *
     * @param event The touch screen event being processed.
     * @return Return true if you have consumed the event, false if you haven't.
     * The default implementation always returns false.
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {

        Log.d("EEEEE", MotionEvent.actionToString(event.getAction()));
        return super.onTouchEvent(event);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Intent intent = new Intent(MainActivity.this, SwipingService.class);

        stopService(intent);
    }

    public void triggerScroll()
    {
//        Log.d("Scrolling - ", "startDelay = " + startDelay + ", inBetweenDelay = " + inBetweenDelay);
//        UiDevice device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
//        device.pressHome();
//        device.swipe(100, 200, 100, 600, 5);
//        AccessibilityNodeInfo nodeInfo =
    }
}
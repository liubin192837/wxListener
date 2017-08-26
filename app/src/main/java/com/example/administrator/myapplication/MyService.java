package com.example.administrator.myapplication;

import android.accessibilityservice.AccessibilityService;
import android.app.Notification;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;
import android.widget.Toast;

import java.util.List;
public class MyService extends AccessibilityService  {
    private Handler handler = null;
    String content = null;
    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        int eventType = event.getEventType();
        switch (eventType) {
            //第一步：监听通知栏消息
            case AccessibilityEvent.TYPE_NOTIFICATION_STATE_CHANGED:
                List<CharSequence> texts = event.getText();
                if (!texts.isEmpty()) {
                    for (CharSequence text : texts) {
                        content = text.toString();
                        Notification notification1 = (Notification) event.getParcelableData();
                        Log.i("demo", "text:"+content+"notification1:"+notification1.extras.getString(Notification.EXTRA_TITLE));
                        if("来啊豆子".contains("豆子")){
                           /* handler=new Handler(Looper.getMainLooper());
                            handler.post(new Runnable(){
                                public void run(){
                                    Toast.makeText(getApplicationContext(), "test", Toast.LENGTH_LONG).show();
                                }
                            });*/
                        }
                        if(notification1.extras.getString(Notification.EXTRA_TITLE).contains("豆子")){
                            handler=new Handler(Looper.getMainLooper());
                            handler.post(new Runnable(){
                                public void run(){
                                    Toast.makeText(getApplicationContext(), content, Toast.LENGTH_LONG).show();
                                }
                            });
                        }
                    }
                }
                break;
        }
    }

    @Override
    public void onInterrupt() {
    }
}

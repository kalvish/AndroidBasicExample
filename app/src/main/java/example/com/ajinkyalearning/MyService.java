package example.com.ajinkyalearning;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

/**
 * Created by kalan on 9/7/15.
 */
public class MyService extends Service {
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }


}


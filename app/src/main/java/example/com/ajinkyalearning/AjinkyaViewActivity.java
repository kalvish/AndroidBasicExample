package example.com.ajinkyalearning;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

/**
 * Created by kalan on 8/7/15.
 */
public class AjinkyaViewActivity extends Activity{

    static float x;
    SensorManager mSensorManager;
    Sensor mSensorAcceleration;
     TextView ajinkyaTextView = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.layout_ajinkya_view_activity);

        ajinkyaTextView = (TextView)findViewById(R.id.ajinkyaTextView);
        ajinkyaTextView.setText("Hello Ghorpade!");
        ajinkyaTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView view1 = (TextView)view;
                view1.setText("Why did u click loh?!");
            }
        });



        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        if (mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER) != null){
            mSensorAcceleration = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        }
        else{
            // Sorry, there are no accelerometers on your device.
            // You can't play this game.
        }


    }

    @Override
    protected void onResume() {
        super.onResume();

        mSensorManager.registerListener(ListenAcceleration.getListener(ajinkyaTextView,this), mSensorAcceleration, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(ListenAcceleration.getListener(ajinkyaTextView,this));
    }




}

class ListenAcceleration implements SensorEventListener{
    Activity mActivity;
    TextView ajinkyaTextView = null;
    static ListenAcceleration listenAcceleration;
    Handler handler;
    public ListenAcceleration(TextView ajinkyaTextView, Activity activity){
        this.ajinkyaTextView = ajinkyaTextView;
        this.mActivity = activity;
        handler = new Handler();
    }
    public static ListenAcceleration getListener(TextView ajinkyaTextView, Activity activity){
        if(listenAcceleration==null) listenAcceleration = new ListenAcceleration(ajinkyaTextView,activity);
        return listenAcceleration;
    }
    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    @Override
    public void onSensorChanged(final SensorEvent sensorEvent) {

        mActivity.getApplicationContext().startService(new Intent(mActivity.getApplicationContext(),MyService.class));

        mActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
              //  if(ajinkyaTextView!=null)
              //      ajinkyaTextView.setText(Float.toString(sensorEvent.values[0]));
            }
        });


        handler.post(new Runnable() {
            @Override
            public void run() {
                if(ajinkyaTextView!=null)
                    ajinkyaTextView.setText(Float.toString(sensorEvent.values[0]));
            }
        });


    }
}

package diy.minitools;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;


public class CompassActivity extends Activity implements SensorEventListener {

    // sensors for creating compass
    private SensorManager sensorManager;
    private Sensor sensorAccelerometer;
    private Sensor sensorMagneticField;

    // data to compute
    private float[] gravity;
    private float[] magnetic;

    // display elements
    private TextView textView;
    private CompassView view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences pref = getSharedPreferences(getString(R.string.settings_file), Context.MODE_MULTI_PROCESS);
        setTheme(pref.getInt(getString(R.string.settings_alt_theme), R.style.AppTheme));
        setContentView(R.layout.activity_compass);

        ActionBar actionBar = getActionBar();
        actionBar.setDisplayOptions(ActionBar.DISPLAY_HOME_AS_UP | ActionBar.DISPLAY_SHOW_HOME | ActionBar.DISPLAY_SHOW_TITLE);
        actionBar.setIcon(R.drawable.ic_action_location_found);

        // Initializes your device sensors capabilities.
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensorAccelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorMagneticField = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);

        // Initializes UI elements to display sensor data.
        textView = (TextView) findViewById(R.id.textView);
        view = (CompassView) findViewById(R.id.view);
    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this, sensorAccelerometer, SensorManager.SENSOR_DELAY_UI);
        sensorManager.registerListener(this, sensorMagneticField, SensorManager.SENSOR_DELAY_UI);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        switch (id) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        switch (event.sensor.getType()) {
            case Sensor.TYPE_ACCELEROMETER:
                gravity = event.values.clone();
                break;
            case Sensor.TYPE_MAGNETIC_FIELD:
                magnetic = event.values.clone();
                break;
            default:
                return;
        }
        if (gravity != null && magnetic != null)
            updateDirection();
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {
        // not used
    }

    private void updateDirection() {
        float[] R = new float[9];
        float[] temp = new float[9];
        float[] values = new float[3];

        // Loads rotation matrix into R.
        SensorManager.getRotationMatrix(temp, null, gravity, magnetic);
        // Remaps to camera's point-of-view.
        SensorManager.remapCoordinateSystem(temp, SensorManager.AXIS_X, SensorManager.AXIS_Z, R);
        // Returns the orientation values.
        SensorManager.getOrientation(R, values);

        // converts to degrees
        for (int i = 0; i < values.length; i++) {
            Double degrees = (values[i] * 180) / Math.PI;
            values[i] = degrees.floatValue();
        }

        // displays the raw values
        double degrees = values[0];
        if (degrees < 0)
            degrees = degrees + 360;
        textView.setText(String.format("%.2f°", degrees));

        // updates direction and image
        view.updateDirection(values[0]);
    }

}

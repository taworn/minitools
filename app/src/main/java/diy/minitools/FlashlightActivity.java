package diy.minitools;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.hardware.Camera;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.SeekBar;

import java.util.Timer;
import java.util.TimerTask;


public class FlashlightActivity extends Activity {

    // camera
    private Camera camera;
    private Camera.Parameters params;

    // flashlight on/off
    private boolean lightOn;
    private int lightCounter;
    private Timer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences pref = getSharedPreferences(getString(R.string.settings_file), Context.MODE_MULTI_PROCESS);
        setTheme(pref.getInt(getString(R.string.settings_alt_theme), R.style.AppTheme));
        setContentView(R.layout.activity_flashlight);

        ActionBar actionBar = getActionBar();
        actionBar.setDisplayOptions(ActionBar.DISPLAY_HOME_AS_UP | ActionBar.DISPLAY_SHOW_HOME | ActionBar.DISPLAY_SHOW_TITLE);
        actionBar.setIcon(R.drawable.ic_action_flash_on);

        // initializes SeekBar
        SeekBar seekBar = (SeekBar) findViewById(R.id.seekBar);
        lightCounter = seekBar.getMax();
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                lightCounter = progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();

        // pauses timer and camera for other apps
        if (timer != null) {
            timer.cancel();
            timer = null;
            params.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
            camera.setParameters(params);
        }
        camera.release();
    }

    @Override
    protected void onResume() {
        super.onResume();

        // initializes light on/off
        CompoundButton button = (CompoundButton) findViewById(R.id.toggle);
        lightOn = button.isChecked();

        // reconnects camera and if light on, timer
        camera = Camera.open();
        params = camera.getParameters();
        if (lightOn && timer == null) {
            params.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
            camera.setParameters(params);
            timer = new Timer();
            timer.scheduleAtFixedRate(new FlashlightTimerTask(), 100, 100);
        }
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

    /**
     * This function is called when user clicked on light switch on/off.
     */
    public void onSwitchClicked(View view) {
        lightOn = ((CompoundButton) view).isChecked();
        if (lightOn && timer == null) {
            timer = new Timer();
            timer.scheduleAtFixedRate(new FlashlightTimerTask(), 100, 100);
        } else if (timer != null) {
            timer.cancel();
            timer = null;
            params.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
            camera.setParameters(params);
        }
    }

    class FlashlightTimerTask extends TimerTask {
        int counter = 0;

        public void run() {
            if (counter <= lightCounter) {
                // opens light
                params.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
                camera.setParameters(params);
            } else {
                // closes light
                params.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
                camera.setParameters(params);
            }

            // next counter
            counter++;
            if (counter >= 10)
                counter = 0;
        }
    }

}

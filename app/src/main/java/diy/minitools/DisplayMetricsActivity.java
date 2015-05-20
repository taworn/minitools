package diy.minitools;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.MenuItem;
import android.widget.TextView;


public class DisplayMetricsActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences pref = getSharedPreferences(getString(R.string.settings_file), Context.MODE_MULTI_PROCESS);
        setTheme(pref.getInt(getString(R.string.settings_alt_theme), R.style.AppTheme));
        setContentView(R.layout.activity_display_metrics);

        ActionBar actionBar = getActionBar();
        actionBar.setDisplayOptions(ActionBar.DISPLAY_HOME_AS_UP | ActionBar.DISPLAY_SHOW_HOME | ActionBar.DISPLAY_SHOW_TITLE);
        actionBar.setIcon(R.drawable.ic_action_phone);
    }

    @Override
    protected void onResume() {
        super.onResume();

        // prepares elements
        TextView textDensity = (TextView) findViewById(R.id.textDensity);
        TextView textDensityDpi = (TextView) findViewById(R.id.textDensityDpi);
        TextView textScaleDensity = (TextView) findViewById(R.id.textScaleDensity);
        TextView textWidth = (TextView) findViewById(R.id.textWidth);
        TextView textHeight = (TextView) findViewById(R.id.textHeight);
        TextView textXdpi = (TextView) findViewById(R.id.textXdpi);
        TextView textYdpi = (TextView) findViewById(R.id.textYdpi);
        TextView textDiagonalScreenSize = (TextView) findViewById(R.id.textDiagonalScreenSize);

        // gets display metrics
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        textDensity.setText(String.format("%.2f", metrics.density));
        textDensityDpi.setText(Integer.toString(metrics.densityDpi));
        textScaleDensity.setText(String.format("%.2f", metrics.scaledDensity));
        textWidth.setText(Integer.toString(metrics.widthPixels));
        textHeight.setText(Integer.toString(metrics.heightPixels));
        textXdpi.setText(String.format("%.2f", metrics.xdpi));
        textYdpi.setText(String.format("%.2f", metrics.ydpi));

        // calculates diagonal screen size
        double w = metrics.widthPixels;
        double h = metrics.heightPixels;
        double d = metrics.densityDpi;
        double x = Math.pow(w / d, 2);
        double y = Math.pow(h / d, 2);
        double inches = Math.sqrt(x + y);
        textDiagonalScreenSize.setText(String.format("%.2f", inches) + " inches");
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

}

package diy.minitools;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.SearchView;

import java.lang.reflect.Method;


public class MainActivity extends Activity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, getString(R.string.search_hint));
        SharedPreferences pref = getSharedPreferences(getString(R.string.settings_file), Context.MODE_MULTI_PROCESS);
        int theme = pref.getInt(getString(R.string.settings_alt_theme), R.style.AppTheme);
        setTheme(theme);
        setContentView(R.layout.activity_main);
        if (theme == R.style.AppTheme) {
            ((Button) findViewById(R.id.main_flashlight)).setCompoundDrawablesWithIntrinsicBounds(R.drawable.light_ic_action_flash_on, 0, 0, 0);
            ((Button) findViewById(R.id.main_compass)).setCompoundDrawablesWithIntrinsicBounds(R.drawable.light_ic_action_location_found, 0, 0, 0);
            ((Button) findViewById(R.id.main_display_metrics)).setCompoundDrawablesWithIntrinsicBounds(R.drawable.light_ic_action_phone, 0, 0, 0);
            ((Button) findViewById(R.id.main_base_converter)).setCompoundDrawablesWithIntrinsicBounds(R.drawable.light_ic_action_import_export, 0, 0, 0);
            ((Button) findViewById(R.id.main_calculator)).setCompoundDrawablesWithIntrinsicBounds(R.drawable.light_ic_action_dial_pad, 0, 0, 0);
        }
    }

    @Override
    public boolean onMenuOpened(int featureId, Menu menu) {
        if (featureId == Window.FEATURE_ACTION_BAR && menu != null) {
            if (menu.getClass().getSimpleName().equals("MenuBuilder")) {
                try {
                    Method method = menu.getClass().getDeclaredMethod("setOptionalIconsVisible", Boolean.TYPE);
                    method.setAccessible(true);
                    method.invoke(menu, true);
                } catch (NoSuchMethodException e) {
                    Log.e(TAG, "onMenuOpened", e);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return super.onMenuOpened(featureId, menu);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);

        SharedPreferences pref = getSharedPreferences(getString(R.string.settings_file), Context.MODE_MULTI_PROCESS);
        int theme = pref.getInt(getString(R.string.settings_alt_theme), R.style.AppTheme);
        if (theme == R.style.AppTheme) {
            menu.findItem(R.id.action_settings).setIcon(R.drawable.light_ic_action_settings);
            menu.findItem(R.id.action_help).setIcon(R.drawable.light_ic_action_help);
            menu.findItem(R.id.action_about).setIcon(R.drawable.light_ic_action_about);
        }

        // Associates searchable configuration with the SearchView.
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextChange(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextSubmit(String query) {
                Intent intent = new Intent(Intent.ACTION_WEB_SEARCH);
                intent.putExtra(SearchManager.QUERY, query);
                startActivity(intent);
                return true;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id) {
            case R.id.action_settings:
                startActivityForResult(new Intent(this, SettingsActivity.class), 1);
                return true;

            case R.id.action_help:
                onHelp();
                return true;

            case R.id.action_about:
                onAbout();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            // also recreating theme here, too
            finish();
            Intent intent = new Intent(this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
    }

    /**
     * Displays help screen.
     */
    private void onHelp() {
        startActivity(new Intent(this, HelpActivity.class));
    }

    /**
     * Displays about box.
     */
    private void onAbout() {
        // Uses the Builder class for convenient dialog construction.
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder
                .setMessage(R.string.about_message)
                .setTitle(R.string.about_title)
                .setIcon(R.drawable.ic_action_about)
                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // Ok
                    }
                });
        SharedPreferences pref = getSharedPreferences(getString(R.string.settings_file), Context.MODE_MULTI_PROCESS);
        int theme = pref.getInt(getString(R.string.settings_alt_theme), R.style.AppTheme);
        if (theme == R.style.AppTheme)
            builder.setIcon(R.drawable.light_ic_action_about);

        // Creates the AlertDialog object and displays it.
        builder.create();
        builder.show();
    }

    public void onFlashlight(View view) {
        startActivity(new Intent(this, FlashlightActivity.class));
    }

    public void onCompass(View view) {
        startActivity(new Intent(this, CompassActivity.class));
    }

    public void onDisplayMetrics(View view) {
        startActivity(new Intent(this, DisplayMetricsActivity.class));
    }

    public void onBaseConverter(View view) {
        startActivity(new Intent(this, BaseConverterActivity.class));
    }

    public void onCalculator(View view) {
        startActivity(new Intent(this, CalculatorActivity.class));
    }

}

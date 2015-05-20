package diy.minitools;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class BaseConverterActivity extends Activity {

    // which base to switch
    private int switchBase;
    private EditText switchEditText;

    private EditText editTextBase2;
    private EditText editTextBase8;
    private EditText editTextBase10;
    private EditText editTextBase16;

    private Button[] buttons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences pref = getSharedPreferences(getString(R.string.settings_file), Context.MODE_MULTI_PROCESS);
        setTheme(pref.getInt(getString(R.string.settings_alt_theme), R.style.AppTheme));
        setContentView(R.layout.activity_base_converter);

        ActionBar actionBar = getActionBar();
        actionBar.setDisplayOptions(ActionBar.DISPLAY_HOME_AS_UP | ActionBar.DISPLAY_SHOW_HOME | ActionBar.DISPLAY_SHOW_TITLE);
        actionBar.setIcon(R.drawable.ic_action_import_export);

        // initializes for edit texts
        editTextBase2 = (EditText) findViewById(R.id.editTextBase2);
        editTextBase8 = (EditText) findViewById(R.id.editTextBase8);
        editTextBase10 = (EditText) findViewById(R.id.editTextBase10);
        editTextBase16 = (EditText) findViewById(R.id.editTextBase16);

        // prepares for base 2
        editTextBase2.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b) {
                    switchBase = 2;
                    switchEditText = editTextBase2;
                    setNumbersEnabled(2);
                }
            }
        });
        editTextBase2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (switchBase == 2)
                    convertFromBase(editable.toString(), 2);
            }
        });

        // prepares for base 8
        editTextBase8.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b) {
                    switchBase = 8;
                    switchEditText = editTextBase8;
                    setNumbersEnabled(8);
                }
            }
        });
        editTextBase8.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (switchBase == 8)
                    convertFromBase(editable.toString(), 8);
            }
        });

        // prepares for base 10
        editTextBase10.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b) {
                    switchBase = 10;
                    switchEditText = editTextBase10;
                    setNumbersEnabled(10);
                }
            }
        });
        editTextBase10.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (switchBase == 10)
                    convertFromBase(editable.toString(), 10);
            }
        });

        // prepares for base 16
        editTextBase16.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b) {
                    switchBase = 16;
                    switchEditText = editTextBase16;
                    setNumbersEnabled(16);
                }
            }
        });
        editTextBase16.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (switchBase == 16)
                    convertFromBase(editable.toString(), 16);
            }
        });

        // initializes for buttons
        Button buttonClear = (Button) findViewById(R.id.buttonClear);
        Button buttonDelete = (Button) findViewById(R.id.buttonDelete);
        buttons = new Button[16];

        // add number buttons
        buttons[0] = (Button) findViewById(R.id.button0);
        buttons[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchEditText.getText().append('0');
            }
        });
        buttons[1] = (Button) findViewById(R.id.button1);
        buttons[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchEditText.getText().append('1');
            }
        });
        buttons[2] = (Button) findViewById(R.id.button2);
        buttons[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchEditText.getText().append('2');
            }
        });
        buttons[3] = (Button) findViewById(R.id.button3);
        buttons[3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchEditText.getText().append('3');
            }
        });
        buttons[4] = (Button) findViewById(R.id.button4);
        buttons[4].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchEditText.getText().append('4');
            }
        });
        buttons[5] = (Button) findViewById(R.id.button5);
        buttons[5].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchEditText.getText().append('5');
            }
        });
        buttons[6] = (Button) findViewById(R.id.button6);
        buttons[6].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchEditText.getText().append('6');
            }
        });
        buttons[7] = (Button) findViewById(R.id.button7);
        buttons[7].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchEditText.getText().append('7');
            }
        });
        buttons[8] = (Button) findViewById(R.id.button8);
        buttons[8].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchEditText.getText().append('8');
            }
        });
        buttons[9] = (Button) findViewById(R.id.button9);
        buttons[9].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchEditText.getText().append('9');
            }
        });
        buttons[10] = (Button) findViewById(R.id.buttonA);
        buttons[10].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchEditText.getText().append('A');
            }
        });
        buttons[11] = (Button) findViewById(R.id.buttonB);
        buttons[11].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchEditText.getText().append('B');
            }
        });
        buttons[12] = (Button) findViewById(R.id.buttonC);
        buttons[12].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchEditText.getText().append('C');
            }
        });
        buttons[13] = (Button) findViewById(R.id.buttonD);
        buttons[13].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchEditText.getText().append('D');
            }
        });
        buttons[14] = (Button) findViewById(R.id.buttonE);
        buttons[14].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchEditText.getText().append('E');
            }
        });
        buttons[15] = (Button) findViewById(R.id.buttonF);
        buttons[15].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchEditText.getText().append('F');
            }
        });

        // deleting buttons
        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Editable editable = switchEditText.getText();
                int length = editable.toString().length();
                if (length > 0)
                    editable.delete(length - 1, length);
            }
        });
        buttonClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int oldSwitchBase = switchBase;
                switchBase = 0;
                editTextBase2.setText("", TextView.BufferType.EDITABLE);
                editTextBase8.setText("", TextView.BufferType.EDITABLE);
                editTextBase10.setText("", TextView.BufferType.EDITABLE);
                editTextBase16.setText("", TextView.BufferType.EDITABLE);
                switchBase = oldSwitchBase;
            }
        });
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

    private void convertFromBase(String data, int base) {
        try {
            int value = Integer.valueOf(data, base);
            if (base != 2)
                editTextBase2.setText(Integer.toString(value, 2), TextView.BufferType.EDITABLE);
            if (base != 8)
                editTextBase8.setText(Integer.toString(value, 8), TextView.BufferType.EDITABLE);
            if (base != 10)
                editTextBase10.setText(Integer.toString(value, 10), TextView.BufferType.EDITABLE);
            if (base != 16)
                editTextBase16.setText(Integer.toString(value, 16).toUpperCase(), TextView.BufferType.EDITABLE);
        } catch (NumberFormatException e) {
            if (base != 2) editTextBase2.setText("", TextView.BufferType.EDITABLE);
            if (base != 8) editTextBase8.setText("", TextView.BufferType.EDITABLE);
            if (base != 10) editTextBase10.setText("", TextView.BufferType.EDITABLE);
            if (base != 16) editTextBase16.setText("", TextView.BufferType.EDITABLE);
        }
    }

    private void setNumbersEnabled(int base) {
        for (int i = 2; i < 16; i++)
            buttons[i].setEnabled(base > i);
    }

}

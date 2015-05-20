package diy.minitools;

import android.app.ActionBar;
import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
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


public class CalculatorActivity extends Activity {

    private static final int STAGE_EMPTY = 0;
    private static final int STAGE_OPERAND = 1;
    private static final int STAGE_OPERAND_OPERATOR = 2;
    private static final int STAGE_OPERAND_OPERATOR_OPERAND = 3;
    private static final int STAGE_NAN = 4;
    // edit texts
    private EditText editTextBase2;
    private EditText editTextBase8;
    private EditText editTextBase10;
    private EditText editTextBase16;
    // operand buttons
    private Button[] buttonNumbers;
    // operator buttons
    private Button buttonSign;
    private Button buttonPlus;
    private Button buttonMinus;
    private Button buttonMultiplication;
    private Button buttonDivision;
    private Button buttonModulo;
    private Button buttonShiftLeft;
    private Button buttonShiftRight;
    // result button
    private Button buttonResult;
    // data to display
    private TextView textViewData;
    // which base to switch
    private int switchBase;
    private EditText switchEditText;
    // operand and operator
    private int operand;
    private int operand0;
    private char operator;
    private boolean sign;
    private int stage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences pref = getSharedPreferences(getString(R.string.settings_file), Context.MODE_MULTI_PROCESS);
        setTheme(pref.getInt(getString(R.string.settings_alt_theme), R.style.AppTheme));
        setContentView(R.layout.activity_calculator);

        ActionBar actionBar = getActionBar();
        actionBar.setDisplayOptions(ActionBar.DISPLAY_HOME_AS_UP | ActionBar.DISPLAY_SHOW_HOME | ActionBar.DISPLAY_SHOW_TITLE);
        actionBar.setIcon(R.drawable.ic_action_dial_pad);

        editTextBase2 = (EditText) findViewById(R.id.editTextBase2);
        editTextBase8 = (EditText) findViewById(R.id.editTextBase8);
        editTextBase10 = (EditText) findViewById(R.id.editTextBase10);
        editTextBase16 = (EditText) findViewById(R.id.editTextBase16);

        buttonNumbers = new Button[16];
        buttonNumbers[0] = (Button) findViewById(R.id.button0);
        buttonNumbers[1] = (Button) findViewById(R.id.button1);
        buttonNumbers[2] = (Button) findViewById(R.id.button2);
        buttonNumbers[3] = (Button) findViewById(R.id.button3);
        buttonNumbers[4] = (Button) findViewById(R.id.button4);
        buttonNumbers[5] = (Button) findViewById(R.id.button5);
        buttonNumbers[6] = (Button) findViewById(R.id.button6);
        buttonNumbers[7] = (Button) findViewById(R.id.button7);
        buttonNumbers[8] = (Button) findViewById(R.id.button8);
        buttonNumbers[9] = (Button) findViewById(R.id.button9);
        buttonNumbers[10] = (Button) findViewById(R.id.buttonA);
        buttonNumbers[11] = (Button) findViewById(R.id.buttonB);
        buttonNumbers[12] = (Button) findViewById(R.id.buttonC);
        buttonNumbers[13] = (Button) findViewById(R.id.buttonD);
        buttonNumbers[14] = (Button) findViewById(R.id.buttonE);
        buttonNumbers[15] = (Button) findViewById(R.id.buttonF);

        buttonSign = (Button) findViewById(R.id.buttonSign);
        buttonPlus = (Button) findViewById(R.id.buttonPlus);
        buttonMinus = (Button) findViewById(R.id.buttonMinus);
        buttonMultiplication = (Button) findViewById(R.id.buttonMultipication);
        buttonDivision = (Button) findViewById(R.id.buttonDivision);
        buttonModulo = (Button) findViewById(R.id.buttonModulo);
        buttonShiftLeft = (Button) findViewById(R.id.buttonShiftLeft);
        buttonShiftRight = (Button) findViewById(R.id.buttonShiftRight);

        Button buttonClear = (Button) findViewById(R.id.buttonClear);
        Button buttonDelete = (Button) findViewById(R.id.buttonDelete);
        Button buttonCopy = (Button) findViewById(R.id.buttonCopy);
        Button buttonPaste = (Button) findViewById(R.id.buttonPaste);

        buttonResult = (Button) findViewById(R.id.buttonResult);
        textViewData = (TextView) findViewById(R.id.textViewData);

        // initializes for base 2
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

        // initializes for base 8
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

        // initializes for base 10
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

        // initializes for base 16
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

        // initializes operand buttons
        buttonNumbers[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                appendNumber('0');
            }
        });
        buttonNumbers[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                appendNumber('1');
            }
        });
        buttonNumbers[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                appendNumber('2');
            }
        });
        buttonNumbers[3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                appendNumber('3');
            }
        });
        buttonNumbers[4].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                appendNumber('4');
            }
        });
        buttonNumbers[5].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                appendNumber('5');
            }
        });
        buttonNumbers[6].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                appendNumber('6');
            }
        });
        buttonNumbers[7].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                appendNumber('7');
            }
        });
        buttonNumbers[8].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                appendNumber('8');
            }
        });
        buttonNumbers[9].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                appendNumber('9');
            }
        });
        buttonNumbers[10].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                appendNumber('A');
            }
        });
        buttonNumbers[11].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                appendNumber('B');
            }
        });
        buttonNumbers[12].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                appendNumber('C');
            }
        });
        buttonNumbers[13].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                appendNumber('D');
            }
        });
        buttonNumbers[14].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                appendNumber('E');
            }
        });
        buttonNumbers[15].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                appendNumber('F');
            }
        });

        // initializes operator buttons
        buttonSign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int oldSwitchBase = switchBase;
                switchBase = 0;
                if (!sign) {
                    editTextBase2.getText().insert(0, "-");
                    editTextBase8.getText().insert(0, "-");
                    editTextBase10.getText().insert(0, "-");
                    editTextBase16.getText().insert(0, "-");
                } else {
                    editTextBase2.getText().delete(0, 1);
                    editTextBase8.getText().delete(0, 1);
                    editTextBase10.getText().delete(0, 1);
                    editTextBase16.getText().delete(0, 1);
                }
                switchBase = oldSwitchBase;
                sign = !sign;
            }
        });
        buttonPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculateOperator('+');
            }
        });
        buttonMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculateOperator('-');
            }
        });
        buttonMultiplication.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculateOperator('*');
            }
        });
        buttonDivision.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculateOperator('/');
            }
        });
        buttonModulo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculateOperator('%');
            }
        });
        buttonShiftLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculateOperator('<');
            }
        });
        buttonShiftRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculateOperator('>');
            }
        });

        // initializes other buttons
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
                operand = 0;
                operand0 = 0;
                operator = '\0';
                sign = false;
                setStage(STAGE_EMPTY);
                textViewData.setText("");
            }
        });
        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (stage != STAGE_NAN) {
                    // deletes last character
                    Editable editable = switchEditText.getText();
                    int length = editable.toString().length();
                    if (length > 0 && editable.charAt(length - 1) != '-')
                        editable.delete(length - 1, length);

                    // retracts last stage
                    length = editable.toString().length();
                    if (length <= 0 || (length == 1 && editable.charAt(0) == '-')) {
                        if (stage <= STAGE_OPERAND)
                            setStage(STAGE_EMPTY);
                        else
                            setStage(STAGE_OPERAND_OPERATOR);
                    }
                }
            }
        });
        buttonCopy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("Calculator", switchEditText.getText().toString());
                clipboard.setPrimaryClip(clip);
            }
        });
        buttonPaste.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (stage != STAGE_NAN) {
                    ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                    if (clipboard.hasPrimaryClip()) {
                        ClipData.Item item = clipboard.getPrimaryClip().getItemAt(0);
                        CharSequence pasteData = item.getText();

                        // prepares string to match
                        String pastableData;
                        if (switchBase == 2)
                            pastableData = "01";
                        else if (switchBase == 8)
                            pastableData = "01234567";
                        else if (switchBase == 10)
                            pastableData = "0123456789";
                        else
                            pastableData = "0123456789ABCDEFabcdef";

                        // matches only string in base
                        String data = "";
                        int i = 0, length = pasteData.length();
                        while (i < length) {
                            char ch = pasteData.charAt(i);
                            if (pastableData.indexOf(ch) != -1)
                                data += ch;
                            i++;
                        }

                        // paste data
                        if (data.length() > 0) {
                            switchEditText.getText().append(data.toUpperCase());
                            if (stage <= STAGE_OPERAND)
                                setStage(STAGE_OPERAND);
                            else
                                setStage(STAGE_OPERAND_OPERATOR_OPERAND);
                        }
                    }
                }
            }
        });
        buttonResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculateResult();
            }
        });

        // initializing calculator
        operand = 0;
        operand0 = 0;
        operator = '\0';
        sign = false;
        stage = -1;
        setStage(STAGE_EMPTY);
    }

    @Override
    protected void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putInt("operand", operand);
        bundle.putInt("operand0", operand0);
        bundle.putChar("operator", operator);
        bundle.putBoolean("sign", sign);
        bundle.putInt("stage", stage);
        bundle.putString("textViewData", textViewData.getText().toString());
    }

    @Override
    protected void onRestoreInstanceState(Bundle bundle) {
        super.onRestoreInstanceState(bundle);
        operand = bundle.getInt("operand");
        operand0 = bundle.getInt("operand0");
        operator = bundle.getChar("operator");
        sign = bundle.getBoolean("sign");
        stage = -1;
        setStage(bundle.getInt("stage"));
        textViewData.setText(bundle.getString("textViewData"));
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

    private void setNumbersEnabled(int base) {
        for (int i = 0; i < 16; i++)
            buttonNumbers[i].setEnabled(base > i && stage != STAGE_NAN);
    }

    private void setOperatorEnabled(boolean enabled) {
        buttonSign.setEnabled(stage != STAGE_NAN);
        buttonPlus.setEnabled(enabled);
        buttonMinus.setEnabled(enabled);
        buttonMultiplication.setEnabled(enabled);
        buttonDivision.setEnabled(enabled);
        buttonModulo.setEnabled(enabled);
        buttonShiftLeft.setEnabled(enabled);
        buttonShiftRight.setEnabled(enabled);
    }

    private void setResultEnabled(boolean enabled) {
        buttonResult.setEnabled(enabled);
    }

    private void convertFromBase(String data, int base) {
        CharSequence s = !sign ? "" : "-";
        try {
            int value = Integer.valueOf(data, base);
            if (value < 0) value = -value;
            if (base != 2)
                editTextBase2.setText(s + Integer.toString(value, 2), TextView.BufferType.EDITABLE);
            if (base != 8)
                editTextBase8.setText(s + Integer.toString(value, 8), TextView.BufferType.EDITABLE);
            if (base != 10)
                editTextBase10.setText(s + Integer.toString(value, 10), TextView.BufferType.EDITABLE);
            if (base != 16)
                editTextBase16.setText(s + Integer.toString(value, 16).toUpperCase(), TextView.BufferType.EDITABLE);
        } catch (NumberFormatException e) {
            if (base != 2) editTextBase2.setText(s, TextView.BufferType.EDITABLE);
            if (base != 8) editTextBase8.setText(s, TextView.BufferType.EDITABLE);
            if (base != 10) editTextBase10.setText(s, TextView.BufferType.EDITABLE);
            if (base != 16) editTextBase16.setText(s, TextView.BufferType.EDITABLE);
        }
    }

    private void appendNumber(char ch) {
        switchEditText.getText().append(ch);
        if (stage <= STAGE_OPERAND)
            setStage(STAGE_OPERAND);
        else
            setStage(STAGE_OPERAND_OPERATOR_OPERAND);
    }

    private void calculate() {
        // checks stage
        if (stage == STAGE_OPERAND || stage == STAGE_OPERAND_OPERATOR_OPERAND) {
            // gets number
            try {
                String s = switchEditText.getText().toString();
                operand = Integer.valueOf(s, switchBase);
            } catch (NumberFormatException e) {
                operand = 0;
            }

            // calculates
            if (stage <= STAGE_OPERAND)
                operand0 = operand;
            else {
                try {
                    switch (operator) {
                        case '+':
                            operand0 += operand;
                            break;
                        case '-':
                            operand0 -= operand;
                            break;
                        case '*':
                            operand0 *= operand;
                            break;
                        case '/':
                            operand0 /= operand;
                            break;
                        case '%':
                            operand0 %= operand;
                            break;
                        case '<':
                            operand0 <<= operand;
                            break;
                        case '>':
                            operand0 >>= operand;
                            break;
                        case '\0':
                            operand0 = operand;
                            break;
                    }
                } catch (ArithmeticException e) {
                    setStage(STAGE_NAN);
                    sign = (operand0 < 0) ^ (switchEditText.getText().charAt(0) == '-');
                    String s = (!sign ? "" : "-") + "NaN";
                    int oldSwitchBase = switchBase;
                    switchBase = 0;
                    editTextBase2.setText(s, TextView.BufferType.EDITABLE);
                    editTextBase8.setText(s, TextView.BufferType.EDITABLE);
                    editTextBase10.setText(s, TextView.BufferType.EDITABLE);
                    editTextBase16.setText(s, TextView.BufferType.EDITABLE);
                    switchBase = oldSwitchBase;
                    textViewData.setText(s);
                }
            }
        }
    }

    private void calculateOperator(char op) {
        // calculate
        calculate();
        if (stage == STAGE_NAN)
            return;

        // resets number
        int oldSwitchBase = switchBase;
        switchBase = 0;
        editTextBase2.setText("", TextView.BufferType.EDITABLE);
        editTextBase8.setText("", TextView.BufferType.EDITABLE);
        editTextBase10.setText("", TextView.BufferType.EDITABLE);
        editTextBase16.setText("", TextView.BufferType.EDITABLE);
        switchBase = oldSwitchBase;
        sign = false;

        // sets stage and operator
        setStage(STAGE_OPERAND_OPERATOR);
        operator = op;

        // displays calculator
        String temp;
        if (operator == '<')
            temp = "<<";
        else if (operator == '>')
            temp = ">>";
        else
            temp = String.valueOf(operator);
        textViewData.setText(Integer.toString(operand0) + ' ' + temp);
    }

    private void calculateResult() {
        // calculate
        calculate();
        if (stage == STAGE_NAN)
            return;

        // next stage
        int oldSwitchBase = switchBase;
        switchBase = 0;
        editTextBase2.setText(Integer.toString(operand0, 2), TextView.BufferType.EDITABLE);
        editTextBase8.setText(Integer.toString(operand0, 8), TextView.BufferType.EDITABLE);
        editTextBase10.setText(Integer.toString(operand0, 10), TextView.BufferType.EDITABLE);
        editTextBase16.setText(Integer.toString(operand0, 16).toUpperCase(), TextView.BufferType.EDITABLE);
        switchBase = oldSwitchBase;
        sign = operand0 < 0;
        setStage(STAGE_OPERAND);

        // displays calculator
        textViewData.setText(Integer.toString(operand0));
    }

    private void setStage(int s) {
        if (stage != s) {
            stage = s;
            switch (stage) {
                case STAGE_EMPTY:
                    setNumbersEnabled(switchBase);
                    setOperatorEnabled(false);
                    setResultEnabled(false);
                    break;
                case STAGE_OPERAND:
                    setOperatorEnabled(true);
                    setResultEnabled(false);
                    break;
                case STAGE_OPERAND_OPERATOR:
                    setOperatorEnabled(true);
                    setResultEnabled(false);
                    break;
                case STAGE_OPERAND_OPERATOR_OPERAND:
                    setOperatorEnabled(true);
                    setResultEnabled(true);
                    break;
                case STAGE_NAN:
                    setNumbersEnabled(0);
                    setOperatorEnabled(false);
                    setResultEnabled(false);
                    break;
            }
        }
    }

}

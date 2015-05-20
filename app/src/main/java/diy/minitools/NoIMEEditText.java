package diy.minitools;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.EditText;


/**
 * Edit text without IME.
 */
public class NoIMEEditText extends EditText {

    public NoIMEEditText(Context context) {
        super(context);
    }

    public NoIMEEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public NoIMEEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onCheckIsTextEditor() {
        return false;
    }

}

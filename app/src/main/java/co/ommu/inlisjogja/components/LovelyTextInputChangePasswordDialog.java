package co.ommu.inlisjogja.components;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.StringRes;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import co.ommu.inlisjogja.R;

import com.scottyab.showhidepasswordedittext.ShowHidePasswordEditText;

/**
 * Created by yarolegovich on 16.04.2016.
 */
public class LovelyTextInputChangePasswordDialog extends AbsLovelyDialog<LovelyTextInputChangePasswordDialog> {

    private static final String KEY_HAS_ERROR = "key_has_error";
    private static final String KEY_TYPED_TEXT = "key_typed_text";
    private static final String KEY_TYPED_MEMBER = "key_typed_text";
    private static final String KEY_TYPED_NAME = "key_typed_text";

    private ShowHidePasswordEditText edEmail, edName, edNumber;
    private TextView errorMessage;
    private TextView confirmButton;


    private TextFilter filter1, filter2, filter3;

    public LovelyTextInputChangePasswordDialog(Context context) {
        super(context);
    }

    public LovelyTextInputChangePasswordDialog(Context context, int theme) {
        super(context, theme);
    }

    {
        confirmButton = findView(R.id.ld_btn_confirm);

        edEmail = findView(R.id.input_old_password);
        edName = findView(R.id.input_new_password);
        edNumber = findView(R.id.input_confirm_password);


        errorMessage = findView(R.id.ld_error_message);
        edEmail.addTextChangedListener(new HideErrorOnTextChanged());
    }

    public LovelyTextInputChangePasswordDialog setConfirmButton(@StringRes int text, OnTextInputConfirmListener listener) {
        return setConfirmButton(string(text), listener);
    }

    public LovelyTextInputChangePasswordDialog setConfirmButton(String text, OnTextInputConfirmListener listener) {
        confirmButton.setText(text);
        confirmButton.setOnClickListener(new TextInputListener(listener));
        return this;
    }

    public LovelyTextInputChangePasswordDialog setConfirmButtonColor(int color) {
        confirmButton.setTextColor(color);
        return this;
    }

    public LovelyTextInputChangePasswordDialog setInputFilter(@StringRes int errorMessage, TextFilter email, TextFilter name, TextFilter member) {
        return setInputFilter(string(errorMessage), email, name, member);
    }

    public LovelyTextInputChangePasswordDialog setInputFilter(String errorMessage, TextFilter email, TextFilter name, TextFilter member) {
        filter1 = email;
        filter2 = name;
        filter3 = member;


        this.errorMessage.setText(errorMessage);
        return this;
    }

    public LovelyTextInputChangePasswordDialog setErrorMessageColor(int color) {
        errorMessage.setTextColor(color);
        return this;
    }

    public LovelyTextInputChangePasswordDialog setInputType(int inputType) {
        edEmail.setInputType(inputType);
        return this;
    }

    public LovelyTextInputChangePasswordDialog addTextWatcher(TextWatcher textWatcher) {
        edEmail.addTextChangedListener(textWatcher);
        return this;
    }

    public LovelyTextInputChangePasswordDialog setInitialInput(@StringRes int email, @StringRes int name, @StringRes int member) {
        return setInitialInput(string(email), string(name), string(member));
    }

    public LovelyTextInputChangePasswordDialog setInitialInput(String email, String name, String member) {
        edEmail.setText(email);
        edName.setText(name);
        edNumber.setText(member);

        return this;
    }

    public LovelyTextInputChangePasswordDialog setHint(@StringRes int hint) {
        return setHint(string(hint));
    }

    public LovelyTextInputChangePasswordDialog setHint(String text) {
        edEmail.setHint(text);
        return this;
    }

    private void setError() {
        errorMessage.setVisibility(View.VISIBLE);
    }

    private void hideError() {
        errorMessage.setVisibility(View.GONE);
    }

    @Override
    void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(KEY_HAS_ERROR, errorMessage.getVisibility() == View.VISIBLE);
        outState.putString(KEY_TYPED_TEXT, edEmail.getText().toString());
        outState.putString(KEY_TYPED_NAME, edName.getText().toString());
        outState.putString(KEY_TYPED_MEMBER, edNumber.getText().toString());
    }

    @Override
    void restoreState(Bundle savedState) {
        super.restoreState(savedState);
        if (savedState.getBoolean(KEY_HAS_ERROR, false)) {
            setError();
        }
        edEmail.setText(savedState.getString(KEY_TYPED_TEXT));
    }

    @Override
    protected int getLayout() {
        return R.layout.dialog_change_password;
    }

    private class TextInputListener implements View.OnClickListener {

        private OnTextInputConfirmListener wrapped;

        private TextInputListener(OnTextInputConfirmListener wrapped) {
            this.wrapped = wrapped;
        }

        @Override
        public void onClick(View v) {
            String email = edEmail.getText().toString();
            String name = edName.getText().toString();
            String member = edNumber.getText().toString();

            if (filter1 != null && filter2 != null && filter3 != null) {
                boolean isWrongInput = !filter1.check(email);
                boolean isWrongInput1 = !filter2.check(name);
                boolean isWrongInput2 = !filter3.check(member);

                Log.i("tes data", isWrongInput + "_" + isWrongInput1 + "_" + isWrongInput);
                if (isWrongInput) {
                    setError();
                    return;
                } else if (isWrongInput1) {
                    setError();
                    return;
                } else if (isWrongInput2) {
                    setError();
                    return;
                }
            }

            if (wrapped != null) {
                wrapped.onTextInputConfirmed(email, name, member);
            }

            dismiss();
        }
    }

    private class HideErrorOnTextChanged implements TextWatcher {

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            hideError();
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    }

    public interface OnTextInputConfirmListener {
        void onTextInputConfirmed(String email, String name, String member);
    }

    public interface TextFilter {
        boolean check(String text);
    }
}

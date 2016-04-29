package co.ommu.inlisjogja.components;

/**
 * Created by KurniawanD on 4/29/2016.
 */

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Switch;
import android.widget.Toast;

import co.ommu.inlisjogja.R;

public class CustomDialog {
    Context context;
    Bundle bunSaved;

    public CustomDialog(Context cntxt, Bundle bundle, int id){
        context = cntxt;
        bunSaved = bundle;
        switch (id) {
            case 0:
                dialogStandard();
                break;
            case 1:
                inputDialog();
                break;

            default:
                dialogStandard();
                break;
        }
    }

    private void  dialogStandard () {
        new LovelyStandardDialog(context)
                .setTopColorRes(R.color.indigo)
                .setButtonsColorRes(R.color.darkDeepOrange)
                .setIcon(R.mipmap.ic_launcher)
                .setTitle(R.string.rate_title)
                // .setInstanceStateHandler(ID_STANDARD_DIALOG, saveStateHandler)
                .setSavedInstanceState(bunSaved)
                .setMessage(R.string.rate_message)
                .setPositiveButton(android.R.string.ok, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(context,
                                R.string.repo_waiting,
                                Toast.LENGTH_SHORT)
                                .show();
                    }
                })
                .setNeutralButton(R.string.later, null)
                .setNegativeButton(android.R.string.no, null)
                .show();
    }

    private void inputDialog() {
        new LovelyTextInputDialog(context, R.style.EditTextTintTheme)
                .setTopColorRes(R.color.darkDeepOrange)
                .setTitle(R.string.text_input_title)
                .setMessage(R.string.text_input_message)
                .setIcon(R.mipmap.ic_launcher)
                //.setInstanceStateHandler(ID_TEXT_INPUT_DIALOG, saveStateHandler)
                .setInputFilter(R.string.text_input_error_message, new LovelyTextInputDialog.TextFilter() {
                    @Override
                    public boolean check(String text) {
                        return text.matches("\\w+");
                    }
                })
                .setConfirmButton(android.R.string.ok, new LovelyTextInputDialog.OnTextInputConfirmListener() {
                    @Override
                    public void onTextInputConfirmed(String text) {
                        Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
                    }
                })
                .setSavedInstanceState(bunSaved)
                .show();
    }
}

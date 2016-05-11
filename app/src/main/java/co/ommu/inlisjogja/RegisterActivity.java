package co.ommu.inlisjogja;
/**
 * Created by KurniawanD on 4/27/2016.
 */

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;


import org.json.JSONException;
import org.json.JSONObject;


import co.ommu.inlisjogja.components.AsynRestClient;
import co.ommu.inlisjogja.components.CustomDialog;
import co.ommu.inlisjogja.components.LovelyTextInputDialog;
import co.ommu.inlisjogja.components.Utility;
import cz.msebera.android.httpclient.Header;

import android.content.SharedPreferences;
import android.content.Context;


import com.scottyab.showhidepasswordedittext.ShowHidePasswordEditText;

import android.content.DialogInterface;

public class RegisterActivity extends AppCompatActivity {
    RelativeLayout btnError;

    ProgressDialog pd;
    ProgressBar pb;

    int isLogin = 0;
    String success = "", error = "", message = "",  //login success and error
        token = "", oauth = "", email = "", displayname = "", lastlogin_date = "", enabled = "", verified = "", //login success
        member_id = "", member_number = "", address = "", photo = "", birthday = "", phone_number = "",  //login success
        status = "", member_type = "", change_password = "",     //login success
        membernumber = "",
        fullname = "",
        userEmail = "";
    Bundle bunSaved;
    RelativeLayout btnLogin;
    EditText edEmail;
    TextView tvRegiter, tvSkip;

    ShowHidePasswordEditText edPassword;

    SharedPreferences pref;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bunSaved = savedInstanceState;
        setContentView(R.layout.activity_register);

        pb = (ProgressBar) findViewById(R.id.progressBar);
        pb.setVisibility(View.GONE);
        btnError = (RelativeLayout) findViewById(R.id.rl_error);
        btnError.setVisibility(View.GONE);

        edEmail = (EditText) findViewById(R.id.input_username);
        edPassword = (ShowHidePasswordEditText) findViewById(R.id.input_password);

        btnLogin = (RelativeLayout) findViewById(R.id.rl_login);
        tvRegiter = (TextView) findViewById(R.id.tv_register);
        tvSkip = (TextView) findViewById(R.id.tv_skip);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edEmail.getText().toString().isEmpty()) {
                    edEmail.requestFocus();
                    Toast.makeText(getApplicationContext(), "Email Belum Di isi !", Toast.LENGTH_SHORT).show();
                } else if (edPassword.getText().toString().isEmpty()) {
                    edPassword.requestFocus();
                    Toast.makeText(getApplicationContext(), "Password Belum Di isi !", Toast.LENGTH_SHORT).show();
                } else
                    getRequestLogin();
                //startActivity(new Intent(RegisterActivity.this, WelcomeDrawerActivity.class));
            }
        });

        tvRegiter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputMemberDialog();
            }
        });

        tvSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isLogin = 2;
                updatePref();
                startActivity(new Intent(RegisterActivity.this, WelcomeDrawerActivity.class));
            }
        });

        loadPref();
    }

    private void buildData() {
        btnError.setVisibility(View.GONE);
        pb.setVisibility(View.GONE);
    }

    private void buildError(String message) {
        pd.dismiss();
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
    }

    // Login
    private void getRequestLogin() {
        String urlReq = Utility.inlisUserLoginPathURL;
        RequestParams params = new RequestParams();
        params.put("email", edEmail.getText().toString());
        params.put("password", edPassword.getText().toString());

        pd = ProgressDialog.show(this, "", "Please wait...", true, true);
        pd.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface arg0) {
                // TODO Auto-generated method stub
                AsynRestClient.cancelAllRequests(getApplicationContext());
            }
        });

        AsynRestClient.post(RegisterActivity.this, urlReq, params, new JsonHttpResponseHandler() {
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                // TODO Auto-generated method stub
                try {
                    success = response.getString("success");
                    //error = response.getString("error");
                    message = response.getString("message");

                    if (success.equals("1")) {
                        token = response.getString("token");
                        oauth = response.getString("oauth");
                        email = response.getString("email");
                        displayname = response.getString("displayname");
                        lastlogin_date = response.getString("lastlogin_date");
                        enabled = response.getString("enabled");
                        verified = response.getString("verified");
                        member_id = response.getString("member_id");
                        member_number = response.getString("member_number");
                        address = response.getString("address");
                        photo = response.getString("photo");
                        birthday = response.getString("birthday");
                        phone_number = response.getString("phone_number");
                        status = response.getString("status");
                        member_type = response.getString("member_type");
                        change_password = response.getString("change_password");
                        isLogin = 1;
                        updatePref();
                        startActivity(new Intent(RegisterActivity.this, WelcomeDrawerActivity.class));
                        finish();

                    } else
                        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();

                    pd.dismiss();

                    //buildData();


                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                    Log.i("infffffooo", "ada parsingan yg salah");
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] header, String res, Throwable e) {
                // TODO Auto-generated method stub
                Log.i("data", "_" + statusCode);
                buildError(R.string.msg_error);
            }

            @Override
            public void onFailure(int statusCode, Header[] header, Throwable e, JSONObject jo) {
                buildError(R.string.msg_error);
            }
        });
    }

    public void loadPref() {
        // TODO Auto-generated method stub

        pref = getSharedPreferences(Utility.prefName, Context.MODE_PRIVATE);

        // 0 = belum login, 1=sudah login, 2= skip

        /*
        isLogin = pref.getInt("isFirst", 0);
        switch (isLogin) {
            case 0:
                Toast.makeText(getApplicationContext(), "Belum Login", Toast.LENGTH_LONG).show();
                break;
            case 1:
                Toast.makeText(getApplicationContext(), "Sudah Login", Toast.LENGTH_LONG).show();
                break;
            case 2:
                Toast.makeText(getApplicationContext(), "Skip Login", Toast.LENGTH_LONG).show();
                break;
            default:
                Toast.makeText(getApplicationContext(), "Belum Login", Toast.LENGTH_LONG).show();
                break;
        }

*/
    }

    private void updatePref()
    {
        editor = pref.edit();
        editor.putString("token", token);
        editor.putString("oauth", oauth);
        editor.putString("email", email);
        editor.putString("displayname", displayname);
        editor.putString("member_type", member_type);
        editor.putString("lastlogin_date", lastlogin_date);
        editor.putString("enabled", enabled);
        editor.putString("verified", verified);
        editor.putString("member_id", member_id);
        editor.putString("member_number", member_number);
        editor.putString("address", address);
        editor.putString("photo", photo);
        editor.putString("birthday", birthday);
        editor.putString("phone_number", phone_number);
        editor.putString("status", status);
        editor.putString("member_type", member_type);
        editor.putString("change_password", change_password);
        editor.putInt("isFirst", isLogin);
        editor.commit();
    }

    private void inputMemberDialog() {
        new LovelyTextInputDialog(this, R.style.EditTextTintTheme)
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
                        Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();
                        membernumber = text;
                        getRequestMember();

                    }
                })
                .setSavedInstanceState(bunSaved)
                .show();

    }

    private void userGenerateDialog() {
        new LovelyTextInputDialog(this, R.style.EditTextTintTheme)
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
                        Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();
                        userEmail = text;
                        getRequestUserGenerate();

                    }
                })
                .setSavedInstanceState(bunSaved)
                .show();
    }


    private void getRequestMember() {

        String urlReq = Utility.inlisUserGetMemberPathURL;
        RequestParams params = new RequestParams();
        params.put("membernumber", membernumber);

        pd = ProgressDialog.show(this, "", "Please wait...", true, true);
        pd.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface arg0) {
                // TODO Auto-generated method stub

                AsynRestClient.cancelAllRequests(getApplicationContext());
            }
        });


        AsynRestClient.post(RegisterActivity.this, urlReq, params, new JsonHttpResponseHandler() {

            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                // TODO Auto-generated method stub
                try {

                    success = response.getString("success");
                    //error = response.getString("error");
                    message = response.getString("message");
                    if (success.equals("1")) {
                        member_id = response.getString("member_id");
                        fullname = response.getString("fullname");
                        birthday = response.getString("birthday");
                        phone_number = response.getString("phone_number");
                        member_type = response.getString("member_type");
                    } else {
                        new CustomDialog(RegisterActivity.this, bunSaved, 0);
                    }
                    pd.dismiss();
                    userGenerateDialog();
                    buildData();


                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                    Log.i("infffffooo", "ada parsingan yg salah");
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] header, String res, Throwable e) {
                // TODO Auto-generated method stub
                Log.i("data", "_" + statusCode);
                buildError(R.string.msg_error);

            }

            @Override
            public void onFailure(int statusCode, Header[] header, Throwable e, JSONObject jo) {
                buildError(R.string.msg_error);

            }
        });


    }

    private void getRequestUserGenerate() {

        String urlReq = Utility.inlisUserGeneratePathURL;
        RequestParams params = new RequestParams();
        params.put("email", userEmail);
        params.put("member", member_id);
        params.put("displayname", fullname);

        pd = ProgressDialog.show(this, "", "Please wait...", true, true);
        pd.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface arg0) {
                // TODO Auto-generated method stub

                AsynRestClient.cancelAllRequests(getApplicationContext());
            }
        });


        AsynRestClient.post(RegisterActivity.this, urlReq, params, new JsonHttpResponseHandler() {

            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                // TODO Auto-generated method stub
                try {

                    success = response.getString("success");
                    error = response.getString("error");
                    message = response.getString("message");
                    if (success.equals("1")) {
                        //member_id = response.getString("member_id");

                    } else {
                        new CustomDialog(RegisterActivity.this, bunSaved, 0);
                    }
                    pd.dismiss();
                    buildData();


                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                    Log.i("infffffooo", "ada parsingan yg salah");
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] header, String res, Throwable e) {
                // TODO Auto-generated method stub
                Log.i("data", "_" + statusCode);
                buildError(R.string.msg_error);

            }

            @Override
            public void onFailure(int statusCode, Header[] header, Throwable e, JSONObject jo) {
                buildError(R.string.msg_error);

            }
        });


    }

}

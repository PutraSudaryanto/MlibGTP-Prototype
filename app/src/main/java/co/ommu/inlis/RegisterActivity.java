package co.ommu.inlis;
/**
 * Created by KurniawanD on 4/27/2016.
 */

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.scottyab.showhidepasswordedittext.ShowHidePasswordEditText;

import org.json.JSONException;
import org.json.JSONObject;

import co.ommu.inlis.components.AsynRestClient;
import co.ommu.inlis.components.LovelyTextInputDialog;
import co.ommu.inlis.components.Utility;
import cz.msebera.android.httpclient.Header;

public class RegisterActivity extends AppCompatActivity
{
    int isGuest = 0;
    String success = "", error = "",  //login success and error
        token = "", oauth = "", email = "", displayname = "", lastlogin_date = "", enabled = "", verified = "", //login success
        member_id = "", member_number = "", address = "", photo = "", birthday = "", phone_number = "",  //login success
        status = "", member_type = "", change_password = "",     //login success
        membernumber = "",  //getMember request
        userEmail = ""; //generateMember request
    ProgressDialog pd;
    Bundle bunSaved;
    RelativeLayout btnLogin;
    EditText etEmail;
    TextView tvRegiter, tvSkip;

    ShowHidePasswordEditText etPassword;

    SharedPreferences preferenceAccount;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bunSaved = savedInstanceState;
        setContentView(R.layout.activity_register);

        etEmail = (EditText) findViewById(R.id.input_username);
        etPassword = (ShowHidePasswordEditText) findViewById(R.id.input_password);

        btnLogin = (RelativeLayout) findViewById(R.id.rl_login);
        tvRegiter = (TextView) findViewById(R.id.tv_register);
        tvSkip = (TextView) findViewById(R.id.tv_skip);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etEmail.getText().toString().isEmpty()) {
                    etEmail.requestFocus();
                    Toast.makeText(getApplicationContext(), "Email Belum Di isi !", Toast.LENGTH_SHORT).show();
                } else if (etPassword.getText().toString().isEmpty()) {
                    etPassword.requestFocus();
                    Toast.makeText(getApplicationContext(), "Password Belum Di isi !", Toast.LENGTH_SHORT).show();
                } else
                    getRequestLogin();
                //startActivity(new Intent(RegisterActivity.this, MainActivity.class));
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
                isGuest = 2;
                updatePreferenceAccount();
                startActivity(new Intent(RegisterActivity.this, MainActivity.class));
            }
        });

        loadPreferenceAccount();
    }

    private void buildError(String message) {
        pd.dismiss();
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
    }

    public void loadPreferenceAccount() {
        preferenceAccount = getSharedPreferences(Utility.preferenceAccount, Context.MODE_PRIVATE);

        isGuest = preferenceAccount.getInt("isGuest", 0); // 0=belum login, 1=sudah login, 2=skip
        if (isGuest == 1)
            startActivity(new Intent(RegisterActivity.this, MainActivity.class));
    }

    private void updatePreferenceAccount()
    {
        editor = preferenceAccount.edit();
        editor.putInt("isGuest", isGuest);
        editor.putString("password_token", token);
        editor.putString("oauth_token", oauth);
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
        editor.commit();
    }

    // user.Login
    private void getRequestLogin() {
        String urlReq = Utility.inlisUserLoginPathURL;
        RequestParams params = new RequestParams();
        params.put("email", etEmail.getText().toString());
        params.put("password", etPassword.getText().toString());

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
                    Log.i("response", ""+response.toString());

                    success = response.getString("success");
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
                        isGuest = 1;
                        updatePreferenceAccount();
						Utility.userToken = token;
						Utility.userOauth = oauth;
                        startActivity(new Intent(RegisterActivity.this, MainActivity.class));
                        finish();

                    } else {
                        error = response.getString("error");
                        Toast.makeText(getApplicationContext(), response.getString("message"), Toast.LENGTH_LONG).show();

                    }
                    pd.dismiss();

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
                buildError(getResources().getString(R.string.msg_error));
            }

            @Override
            public void onFailure(int statusCode, Header[] header, Throwable e, JSONObject jo) {
                buildError(getResources().getString(R.string.msg_error));
            }
        });
    }

    

    // user.GetMember
    private void inputMemberDialog() {
        new LovelyTextInputDialog(this, R.style.EditTextTintTheme)
            .setTopColorRes(R.color.darkDeepOrange)
            .setTitle(R.string.text_input_title+" get")
            .setMessage(R.string.text_input_message)
            .setIcon(R.mipmap.ic_launcher)
			.setHint("Nomor Anggota")
                .setInputType(InputType.TYPE_CLASS_NUMBER)
                //.setInstanceStateHandler(ID_TEXT_INPUT_DIALOG, saveStateHandler)
                .setInputFilter(R.string.text_input_error_message, new LovelyTextInputDialog.TextFilter() {
                    @Override
                    public boolean check(String text) {
                        return text.matches("");
                    }
                })
            .setConfirmButton(android.R.string.ok, new LovelyTextInputDialog.OnTextInputConfirmListener() {
                @Override
                public void onTextInputConfirmed(String text) {
                    Toast.makeText(getApplicationContext(), "Member Number: "+text, Toast.LENGTH_SHORT).show();
                    membernumber = text;
                    getRequestMember();
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

                    if (success.equals("0")) {
                        error = response.getString("error");
                        Toast.makeText(getApplicationContext(), response.getString("message"), Toast.LENGTH_LONG).show();
                    }

                    if(success.equals("1") || (success.equals("0") && !error.equals("MEMBER_NULL"))) {
                        member_id = response.getString("member_id");
                        member_number = response.getString("member_number");
                        displayname = response.getString("fullname");
                        email = response.getString("email");
                        phone_number = response.getString("phone_number");
                        status = response.getString("status");
                        member_type = response.getString("member_type");
                    }

                    if (success.equals("1"))
                        userGenerateDialog();
                    pd.dismiss();

                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                    Log.i("infffffooo", "ada parsingan yg salah");
                    pd.dismiss();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] header, String res, Throwable e) {
                // TODO Auto-generated method stub
                Log.i("data", "_" + statusCode);
                buildError(getResources().getString(R.string.msg_error));
            }

            @Override
            public void onFailure(int statusCode, Header[] header, Throwable e, JSONObject jo) {
                buildError(getResources().getString(R.string.msg_error));
            }
        });
    }

    // user.GenerateMember
    private void userGenerateDialog() {
        new LovelyTextInputDialog(this, R.style.EditTextTintTheme)
            .setTopColorRes(R.color.darkDeepOrange)
            .setTitle(R.string.text_input_title+" email")
            .setMessage(R.string.text_input_message)
            .setIcon(R.mipmap.ic_launcher)
			.setHint("Email")
                .setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS)
                //.setInstanceStateHandler(ID_TEXT_INPUT_DIALOG, saveStateHandler)
                .setInputFilter(R.string.text_input_error_message, new LovelyTextInputDialog.TextFilter() {
                    @Override
                    public boolean check(String text) {
                        return text.matches("");
                    }
                })
			
           
            .setConfirmButton(android.R.string.ok, new LovelyTextInputDialog.OnTextInputConfirmListener() {
                @Override
                public void onTextInputConfirmed(String text) {
                    Toast.makeText(getApplicationContext(), "Email: "+text, Toast.LENGTH_SHORT).show();
                    userEmail = text;
                    getRequestUserGenerate();
                }
            })
            .setSavedInstanceState(bunSaved)
            .show();
    }

    private void getRequestUserGenerate() {
        String urlReq = Utility.inlisUserGeneratePathURL;
        RequestParams params = new RequestParams();
        params.put("email", userEmail);
        params.put("member", member_id);
        params.put("displayname", displayname);

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
                    if (success.equals("0")) {
                        error = response.getString("error");
                        Toast.makeText(getApplicationContext(), response.getString("message"), Toast.LENGTH_LONG).show();
                    }
                    pd.dismiss();

                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                    Log.i("infffffooo", "ada parsingan yg salah");
                    pd.dismiss();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] header, String res, Throwable e) {
                // TODO Auto-generated method stub
                Log.i("data", "_" + statusCode);
                buildError(getResources().getString(R.string.msg_error));
            }

            @Override
            public void onFailure(int statusCode, Header[] header, Throwable e, JSONObject jo) {
                buildError(getResources().getString(R.string.msg_error));
            }
        });
    }

}

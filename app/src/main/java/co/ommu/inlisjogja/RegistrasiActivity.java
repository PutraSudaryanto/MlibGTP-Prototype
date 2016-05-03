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
import cz.msebera.android.httpclient.Header;


import com.scottyab.showhidepasswordedittext.ShowHidePasswordEditText;

import android.content.DialogInterface;

public class RegistrasiActivity extends AppCompatActivity {


    String token = "2aff7d8198a8444e9a7909823f91f98d";
    RelativeLayout btnError;


    ProgressDialog pd;
    ProgressBar pb;
    String membernumber = "", success = "", error = "", message = "", member_id = "",
            fullname = "", birthday = "", phone_number = "", member_type = "",
            userEmail = "",
            oauth, email, displayname, lastlogin_date, verified;
    Bundle bunSaved;
    RelativeLayout btnLogin;
    EditText edEmail;
    TextView tvRegiter, tvSkip;

    ShowHidePasswordEditText edPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bunSaved = savedInstanceState;
        setContentView(R.layout.activity_registrasi);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        pb = (ProgressBar) findViewById(R.id.progressBar);
        pb.setVisibility(View.GONE);
        btnError = (RelativeLayout) findViewById(R.id.rl_error);
        btnError.setVisibility(View.GONE);


        btnLogin = (RelativeLayout) findViewById(R.id.rl_login);
        edEmail = (EditText) findViewById(R.id.input_username);
        edPassword = (ShowHidePasswordEditText) findViewById(R.id.input_password);

        tvRegiter = (TextView) findViewById(R.id.tv_register);
        tvSkip = (TextView) findViewById(R.id.tv_skip);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegistrasiActivity.this, WelcomeDrawerActivity.class));


                /*
                if (edEmail.getText().toString().isEmpty()) {
                    edEmail.requestFocus();
                    Toast.makeText(getApplicationContext(), "Email Belum Di isi !", Toast.LENGTH_LONG).show();
                } else if (edPassword.getText().toString().isEmpty()) {
                    edPassword.requestFocus();
                    Toast.makeText(getApplicationContext(), "Password Belum Di isi !", Toast.LENGTH_LONG).show();
                } else
                    getRequestLogin();
                    */

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
                Toast.makeText(getApplicationContext(), "Skip", Toast.LENGTH_LONG).show();

            }
        });


        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
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

        String urlReq = "/inlis/api/user/getmember";
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


        AsynRestClient.post(RegistrasiActivity.this, urlReq, params, new JsonHttpResponseHandler() {

            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                // TODO Auto-generated method stub
                try {

                    success = response.getString("success");
                    error = response.getString("error");
                    message = response.getString("message");
                    if (success.equals("1")) {
                        member_id = response.getString("member_id");
                        fullname = response.getString("fullname");
                        birthday = response.getString("birthday");
                        phone_number = response.getString("phone_number");
                        member_type = response.getString("member_type");
                    } else {
                        new CustomDialog(RegistrasiActivity.this, bunSaved, 0);
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
                pd.dismiss();
                buildError();

            }

            @Override
            public void onFailure(int statusCode, Header[] header, Throwable e, JSONObject jo) {
                pd.dismiss();
                buildError();

            }
        });


    }

    private void getRequestUserGenerate() {

        String urlReq = "/inlis/api/user/generate";
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


        AsynRestClient.post(RegistrasiActivity.this, urlReq, params, new JsonHttpResponseHandler() {

            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                // TODO Auto-generated method stub
                try {

                    success = response.getString("success");
                    error = response.getString("error");
                    message = response.getString("message");
                    if (success.equals("1")) {
                        //member_id = response.getString("member_id");

                    } else {
                        new CustomDialog(RegistrasiActivity.this, bunSaved, 0);
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
                pd.dismiss();
                buildError();

            }

            @Override
            public void onFailure(int statusCode, Header[] header, Throwable e, JSONObject jo) {
                pd.dismiss();
                buildError();

            }
        });


    }

    private void getRequestLogin() {

        String urlReq = "/inlis/api/user/login";
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


        AsynRestClient.post(RegistrasiActivity.this, urlReq, params, new JsonHttpResponseHandler() {

            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                // TODO Auto-generated method stub
                try {

                    success = response.getString("success");
                    //error = response.getString("error");
                    message = response.getString("message");

                    if (success.equals("1")) {
                        token= response.getString("token");
                                oauth= response.getString("oauth");
                                email= response.getString("email");
                                displayname= response.getString("displayname");
                                lastlogin_date= response.getString("lastlogin_date");
                                verified= response.getString("verified");
                        startActivity(new Intent(RegistrasiActivity.this, WelcomeDrawerActivity.class));

                    } else {
                        new CustomDialog(RegistrasiActivity.this, bunSaved, 0);
                    }
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
                pd.dismiss();
                buildError();

            }

            @Override
            public void onFailure(int statusCode, Header[] header, Throwable e, JSONObject jo) {
                pd.dismiss();
                buildError();

            }
        });


    }

    private void buildData() {
        btnError.setVisibility(View.GONE);
        pb.setVisibility(View.GONE);

    }

    private void buildError() {

        btnError.setVisibility(View.VISIBLE);

        btnError.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });
    }


}

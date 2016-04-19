package com.example.recruitment;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Toshif on 19-03-2016.
 */

public class Signup extends ActionBarActivity {

    EditText company_name_edit, last_na, email_comp, number_com_edit, reg_com_edit, first_na, add_com_edit, pw, cpw;
    ImageView img_sign_employr;
    TextView submit;
    String com_name,first_name,email_com,num_com,reg_com,last_name,add_com;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        getSupportActionBar().hide();
        submit = (TextView) findViewById(R.id.submit);

        company_name_edit = (EditText) findViewById(R.id.company_name_edit);
        last_na = (EditText) findViewById(R.id.last_name_edit);
        email_comp = (EditText) findViewById(R.id.email_sign_edit);
        number_com_edit = (EditText) findViewById(R.id.con_edit);
        reg_com_edit = (EditText) findViewById(R.id.reg_num_edit);
        first_na = (EditText) findViewById(R.id.first_name_edit);
        add_com_edit = (EditText) findViewById(R.id.add_edit);
        pw = (EditText) findViewById(R.id.pw);
        cpw = (EditText) findViewById(R.id.cpw);
        img_sign_employr = (ImageView) findViewById(R.id.img_sign_employr);

        img_sign_employr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {
                if (first_na.getText().length() == 0) {

                    Toast.makeText(Signup.this, "Please enter first name", Toast.LENGTH_LONG).show();
                    return;
                }
                if (last_na.getText().length() == 0) {

                    Toast.makeText(Signup.this, "Please enter last name", Toast.LENGTH_LONG).show();
                    return;
                }
                if (company_name_edit.getText().length() == 0) {

                    Toast.makeText(Signup.this, "Please enter company name", Toast.LENGTH_LONG).show();
                    return;
                }

                if (reg_com_edit.getText().length() == 0) {

                    Toast.makeText(Signup.this, "Please enter Registration number", Toast.LENGTH_LONG).show();
                    return;
                }
                if (add_com_edit.getText().length() == 0) {

                    Toast.makeText(Signup.this, "Please enter Address", Toast.LENGTH_LONG).show();
                    return;

                }
                if (number_com_edit.getText().length() == 0) {

                    Toast.makeText(Signup.this, "Please enter Contact number", Toast.LENGTH_LONG).show();
                    return;

                }
                if (email_comp.getText().length() == 0) {

                    Toast.makeText(Signup.this, "Please enter Email ID", Toast.LENGTH_LONG).show();
                    return;

                }
                if (!isValidEmail(email_comp.getText().toString())){

                    Toast.makeText(Signup.this, "Please enter valid Email ID", Toast.LENGTH_LONG).show();
                    return;

                }
                    if (pw.getText().length() == 0) {

                    Toast.makeText(Signup.this, "Please enter Password", Toast.LENGTH_LONG).show();
                    return;

                }
                if (cpw.getText().length() == 0) {

                    Toast.makeText(Signup.this, "Please Confirm password", Toast.LENGTH_LONG).show();
                    return;

                }
                if (!pw.getText().toString().equals(cpw.getText().toString())) {

                    Toast.makeText(Signup.this, "Your password not matched", Toast.LENGTH_SHORT).show();
                    return;

                }
                else {

                    com_name = company_name_edit.getText().toString();
                    first_name = first_na.getText().toString();
                    email_com = email_comp.getText().toString();
                    num_com = number_com_edit.getText().toString();
                    reg_com = reg_com_edit.getText().toString();
                    last_name = last_na.getText().toString();
                    add_com = add_com_edit.getText().toString();

                    new Sgn_up().execute();
                }

            }
        });
    }
    private boolean isValidEmail(String email) {
        //
        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    class Sgn_up extends AsyncTask<String, String, String> {

        JSONParser jsonParser = new JSONParser();
        ProgressDialog pDialog;
        String msg,success;
        JSONObject json;

        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub
            super.onPreExecute();

            pDialog = new ProgressDialog(Signup.this);
            pDialog.setMessage("Loading...");
            pDialog.setCancelable(false);
            pDialog.show();
            // mProgressHUD = ProgressHUD.show(Forget_pw.this,"Loading...", true,true);
        }

        @Override
        protected String doInBackground(String... names) {
            // TODO Auto-generated method stub
            List<NameValuePair> params = new ArrayList<NameValuePair>();

            params.add(new BasicNameValuePair("email",email_com));
            params.add(new BasicNameValuePair("password", cpw.getText().toString()));
            params.add(new BasicNameValuePair("firstname",first_name));
            params.add(new BasicNameValuePair("lastname",last_name));
            params.add(new BasicNameValuePair("company_name",com_name));
            params.add(new BasicNameValuePair("reg_no",reg_com));
            params.add(new BasicNameValuePair("address",add_com));
            params.add(new BasicNameValuePair("phone",num_com));
            params.add(new BasicNameValuePair("tag","recRsignup"));
            params.add(new BasicNameValuePair("submit","SignUp"));


            json = jsonParser.makeHttpRequest(Login.UrlPrifix +"index.php", "POST", params);
            Log.d("prrrrrrrrrrrrrr", "" + json.toString());
            //      System.out.println("daaataaaa >>>>>>>>>>>>>" + json.toString());
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            // TODO Auto-generated method stub
            pDialog.dismiss();
            super.onPostExecute(result);
            if (json != null) {

                try {
                    msg = json.getString("error_msg");
                    success = json.getString("error");
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                if (!(success == null)) {
                    if (success.equals("false")) {
                        Toast.makeText(Signup.this, "Signup successful!", Toast.LENGTH_SHORT)
                                .show();
                        finish();

                    } else {

                        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Signup.this);
                        final AlertDialog alertDialog = alertDialogBuilder.create();

                        alertDialog.setTitle(msg);
                        alertDialog.setCancelable(false);

                        alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(final DialogInterface dialog, final int which) {


                                dialog.dismiss();
                            }
                        });
                        alertDialog.show();
                    }

                } else {
                    Toast.makeText(Signup.this, "Please try again in some time", Toast.LENGTH_SHORT)
                            .show();
                }

            } else {

                Toast.makeText(Signup.this, "Please try again in some time", Toast.LENGTH_LONG).show();
            }

        }
    }
}
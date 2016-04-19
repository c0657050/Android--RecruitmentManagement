package com.example.recruitment;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
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

/**
 * Created by Toshif on 27-03-2016.
 */

public class Login_employer extends ActionBarActivity {
    TextView sign_up_employr,login_text_employr;
    EditText user_name_edit_empyr_edit,editText_pw_lo_employr_edit;
    ImageView employer_back,fp_rec;
    EditText fp_email;
    Dialog dialog_main;
    CheckBox reme1;
    String set_log_emplyr, set_log_pw_emplyr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_employer);
        getSupportActionBar().hide();


        login_text_employr= (TextView) findViewById(R.id.login_text_employr);
        sign_up_employr = (TextView) findViewById(R.id.textView_sign_up_employr);
        user_name_edit_empyr_edit = (EditText) findViewById(R.id.user_name_edit_emplyr);
        editText_pw_lo_employr_edit = (EditText) findViewById(R.id.editText_pw_lo_employr);
        reme1 = (CheckBox) findViewById(R.id.checkBox2);
        fp_rec = (ImageView) findViewById(R.id.fp_rec);

        fp_rec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dialog_main = new Dialog(Login_employer.this);
                dialog_main.setContentView(R.layout.dialog_fp_recruter);
                dialog_main.setTitle("     Forgot Password ???");

                dialog_main.setCancelable(true);

                fp_email = (EditText) dialog_main.findViewById(R.id.rec_email);
                TextView send_pw_seeker = (TextView) dialog_main.findViewById(R.id.send_pw_rec);
                send_pw_seeker.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        if (fp_email.getText().toString().length()==0){

                            Toast.makeText(Login_employer.this, "Please enter email ID",Toast.LENGTH_SHORT).show();

                        }
                        else {
                            new Forgot_pw_rec().execute();
                        }
                    }
                });

                dialog_main.show();

            }
        });


        login_text_employr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (user_name_edit_empyr_edit.length()==0){

                    Toast.makeText(Login_employer.this, "Please enter username", Toast.LENGTH_SHORT).show();
                    return;
                }

                else  if (editText_pw_lo_employr_edit.length()==0){
                    Toast.makeText(Login_employer.this,"Please enter password",Toast.LENGTH_SHORT).show();
                }
                else {

                    // Intent sign = new Intent(Login_employer.this,Job_post.class);
                   /* Intent sign = new Intent(Login_employer.this,Home_employer.class);
                    startActivity(sign);
                    */
                    if (reme1.isChecked()) {

                        reme1.setChecked(reme1.isChecked());
                        set_log_emplyr = user_name_edit_empyr_edit.getText().toString();
                        set_log_pw_emplyr = editText_pw_lo_employr_edit.getText().toString();
                        // yes = reme.setChecked(true);
                        // reme.setChecked(reme.isChecked());

                        SharedPreferences sp = getSharedPreferences("your_prefss", Activity.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sp.edit();
                        editor.putString("log_emp_empyr", set_log_emplyr);
                        editor.putString("log_pw_emplyr", set_log_pw_emplyr);
                        editor.putBoolean("yesm", reme1.isChecked());
                        editor.commit();

                    } else {
                        // reme.setChecked(!reme.isChecked());
                        SharedPreferences loginPreferences = getSharedPreferences("your_prefs", Context.MODE_PRIVATE);
                        loginPreferences.edit().clear().commit();
                    }

                    if (!Util.isConnected(Login_employer.this))
                        Util.buildDialog(Login_employer.this).show();
                    else {
                        new Login_emplyr().execute();
                    }
                }
            }
        });

        sign_up_employr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sign = new Intent(Login_employer.this,Signup.class);
                startActivity(sign);
            }
        });

    }
    public void onResume() {
        super.onResume();
        loadPreferences();
    }
    private void loadPreferences() {

        SharedPreferences sp = getSharedPreferences("your_prefss", Activity.MODE_PRIVATE);
        String myIntValue = sp.getString("log_emp_empyr", set_log_emplyr);
        String mIntValue = sp.getString("log_pw_emplyr", set_log_pw_emplyr);
        Boolean isChecked = sp.getBoolean("yesm", false);
        user_name_edit_empyr_edit.setText(myIntValue);
        editText_pw_lo_employr_edit.setText(mIntValue);
        reme1.setChecked(isChecked);

    }
    class Login_emplyr extends AsyncTask<String, String, String> {

        JSONParser jsonParser = new JSONParser();
        ProgressDialog pDialog;
        String msg,success;
        JSONObject json;

        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub
            super.onPreExecute();

            pDialog = new ProgressDialog(Login_employer.this);
            pDialog.setMessage("Loading...");
            pDialog.setCancelable(false);
            pDialog.show();
            // mProgressHUD = ProgressHUD.show(Forget_pw.this,"Loading...", true,true);
        }

        @Override
        protected String doInBackground(String... names) {
            // TODO Auto-generated method stub
            List<NameValuePair> params = new ArrayList<NameValuePair>();

            params.add(new BasicNameValuePair("email", user_name_edit_empyr_edit.getText().toString()));
            params.add(new BasicNameValuePair("password", editText_pw_lo_employr_edit.getText().toString()));
            params.add(new BasicNameValuePair("tag","recRlogin"));
            params.add(new BasicNameValuePair("submit","Login"));


            json = jsonParser.makeHttpRequest(Login.UrlPrifix +"index.php", "POST", params);

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


                if (!(success == null)) {
                    if (success.equals("false")) {

                        JSONObject user_details = json.getJSONObject("user");
                        String recruiterid = user_details.getString("recruiterid");
                        String company_name = user_details.getString("company_name");
                        String firstname = user_details.getString("firstname");
                        String lastname = user_details.getString("lastname");
                        String email = user_details.getString("email");

                        Util.setSharedPreference(Login_employer.this, "recruiterid_preference", recruiterid);
                        Util.setSharedPreference(Login_employer.this, "company_name_preference", company_name);
                        Util.setSharedPreference(Login_employer.this, "emaill_preference", email);
                        Util.setSharedPreference(Login_employer.this, "firstname_preference", firstname);
                        Util.setSharedPreference(Login_employer.this, "lastname_preference", lastname);

                        Toast.makeText(Login_employer.this, "Login Successful!", Toast.LENGTH_SHORT)
                                .show();

                        Intent sign = new Intent(Login_employer.this,Home_employer.class);
                        startActivity(sign);
                        finish();
                    }

                    else {

                        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Login_employer.this);
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
                    Toast.makeText(Login_employer.this, "Please try again in some time", Toast.LENGTH_SHORT)
                            .show();
                }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else {

                Toast.makeText(Login_employer.this, "Please try again in some time", Toast.LENGTH_LONG).show();
            }

        }
    }
    class Forgot_pw_rec extends AsyncTask<String, String, String> {

        JSONParser jsonParser = new JSONParser();
        ProgressDialog pDialog;
        String msg, success;
        JSONObject json;

        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub
            super.onPreExecute();

            pDialog = new ProgressDialog(Login_employer.this);
            pDialog.setMessage("Loading...");
            pDialog.setCancelable(false);
            pDialog.show();
            // mProgressHUD = ProgressHUD.show(Forget_pw.this,"Loading...", true,true);
        }

        @Override
        protected String doInBackground(String... names) {
            // TODO Auto-generated method stub
            List<NameValuePair> params = new ArrayList<NameValuePair>();

            params.add(new BasicNameValuePair("email", fp_email.getText().toString()));

            params.add(new BasicNameValuePair("tag", "recRforgetpass"));
            params.add(new BasicNameValuePair("submit", "resetpassword"));


            json = jsonParser.makeHttpRequest(Login.UrlPrifix + "index.php", "POST", params);
            Log.d("prrrrrrrrrrrrrr", "" + json.toString());
            System.out.println("daaataaaa >>>>>>>>>>>>>" + json.toString());
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            // TODO Auto-generated method stub
            pDialog.dismiss();
            super.onPostExecute(result);
            if (json != null) {
                //Toast.makeText(Login.this,json.toString(),Toast.LENGTH_SHORT).show();
                try {
                    msg = json.getString("error_msg");
                    success = json.getString("error");
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                if (!(success == null)) {
                    if (success.equals("false")) {
                        Toast.makeText(Login_employer.this, msg, Toast.LENGTH_SHORT)
                                .show();

                        dialog_main.dismiss();
                    } else {

                        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Login_employer.this);
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
                    Toast.makeText(Login_employer.this, "Please try again in some time", Toast.LENGTH_SHORT)
                            .show();
                }

            } else {

                Toast.makeText(Login_employer.this, "Please try again in some time", Toast.LENGTH_LONG).show();
            }

        }
    }
}
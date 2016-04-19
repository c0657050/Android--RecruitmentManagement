package com.example.recruitment;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
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

public class Login extends ActionBarActivity {
    TextView sign_up_employee, login_text_employee;
    EditText user_name_edit_empyee_edit, editText_pw_lo_edit;
    ImageView fw_seeker;
    EditText fp_empl_email;
    public static String UrlPrifix = "http://toshif.net16.net/android/";
    Dialog dialog_main;
    CheckBox reme;
    String set_log_emp, set_log_pw;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getSupportActionBar().hide();

        login_text_employee = (TextView) findViewById(R.id.login_text);
        sign_up_employee = (TextView) findViewById(R.id.textView_sign_up_employee);
        user_name_edit_empyee_edit = (EditText) findViewById(R.id.user_name_edit_empyee);
        editText_pw_lo_edit = (EditText) findViewById(R.id.editText_pw_lo);
        reme = (CheckBox) findViewById(R.id.checkBox);

        fw_seeker = (ImageView) findViewById(R.id.fw_seeker);
        fw_seeker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                dialog_main = new Dialog(Login.this);
                dialog_main.setContentView(R.layout.dialog_fp);
                dialog_main.setTitle("  Forgot Password ???");

                dialog_main.setCancelable(true);

                fp_empl_email = (EditText) dialog_main.findViewById(R.id.fp_empl_email);
                TextView send_pw_seeker = (TextView) dialog_main.findViewById(R.id.send_pw_seeker);
                send_pw_seeker.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        if (fp_empl_email.getText().toString().length() == 0) {

                            Toast.makeText(Login.this, "Please enter email ID", Toast.LENGTH_SHORT).show();

                        } else {
                            if (!isConnected(Login.this)) buildDialog(Login.this);
                            else {

                                new Forgot_pw_seeker().execute();
                            }
                        }
                    }
                });

                dialog_main.show();


            }
        });

        login_text_employee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (user_name_edit_empyee_edit.length() == 0) {

                    Toast.makeText(Login.this, "Please enter username", Toast.LENGTH_SHORT).show();
                    return;
                } else if (editText_pw_lo_edit.length() == 0) {
                    Toast.makeText(Login.this, "Please enter password", Toast.LENGTH_SHORT).show();
                } else {

                    if (reme.isChecked()) {

                        reme.setChecked(reme.isChecked());
                        set_log_emp = user_name_edit_empyee_edit.getText().toString();
                        set_log_pw = editText_pw_lo_edit.getText().toString();
                        // yes = reme.setChecked(true);
                        // reme.setChecked(reme.isChecked());

                        SharedPreferences sp = getSharedPreferences("your_prefs", Activity.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sp.edit();
                        editor.putString("log_emp", set_log_emp);
                        editor.putString("log_pw", set_log_pw);
                        editor.putBoolean("yes", reme.isChecked());
                        editor.commit();

                    } else {
                        // reme.setChecked(!reme.isChecked());
                        SharedPreferences loginPreferences = getSharedPreferences("your_prefs", Context.MODE_PRIVATE);
                        loginPreferences.edit().clear().commit();
                    }

                    if (!isConnected(Login.this)) buildDialog(Login.this);
                    else {

                        new Login_emp().execute();
                    }

                }

            }
        });

        sign_up_employee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sign = new Intent(Login.this, Sign_up_employee.class);
                startActivity(sign);
            }
        });

    }
    public void onResume() {
        super.onResume();
        loadPreferences();
    }
    private void loadPreferences() {

        SharedPreferences sp = getSharedPreferences("your_prefs", Activity.MODE_PRIVATE);
        String myIntValue = sp.getString("log_emp", set_log_emp);
        String mIntValue = sp.getString("log_pw", set_log_pw);
        Boolean isChecked = sp.getBoolean("yes", false);
        user_name_edit_empyee_edit.setText(myIntValue);
        editText_pw_lo_edit.setText(mIntValue);
        reme.setChecked(isChecked);

    }
    class Login_emp extends AsyncTask<String, String, String> {

        JSONParser jsonParser = new JSONParser();
        ProgressDialog pDialog;
        String msg, success;
        JSONObject json;

        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub
            super.onPreExecute();

            pDialog = new ProgressDialog(Login.this);
            pDialog.setMessage("Loading...");
            pDialog.setCancelable(false);
            pDialog.show();
            // mProgressHUD = ProgressHUD.show(Forget_pw.this,"Loading...", true,true);
        }

        @Override
        protected String doInBackground(String... names) {
            // TODO Auto-generated method stub
            List<NameValuePair> params = new ArrayList<NameValuePair>();

            params.add(new BasicNameValuePair("email", user_name_edit_empyee_edit.getText().toString()));
            params.add(new BasicNameValuePair("password", editText_pw_lo_edit.getText().toString()));
            params.add(new BasicNameValuePair("tag", "employeeSlogin"));
            params.add(new BasicNameValuePair("submit", "Login"));


            json = jsonParser.makeHttpRequest(UrlPrifix + "index.php", "POST", params);
//            Log.d("prrrrrrrrrrrrrr", "" + json.toString());
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


                    if (!(success == null)) {
                        if (success.equals("false")) {

                            //  String seeker_detail = json.getString("user");
                            JSONObject user_details = json.getJSONObject("user");


                            String seekerid = user_details.getString("seekerid");
                            String firstname = user_details.getString("firstname");
                            String phone = user_details.getString("phone");
                            String email = user_details.getString("email");

                            Util.setSharedPreference(Login.this, "seekerid_preference", seekerid);
                            Util.setSharedPreference(Login.this, "email_preference", email);
//                        Util.setSharedPreference(Login.this, "name_preference", name);
//                        Util.setSharedPreference(Login.this, "email_preference", email);


                            Toast.makeText(Login.this, "Login Successful!", Toast.LENGTH_SHORT)
                                    .show();

                            Intent sign = new Intent(Login.this, Home_employee_tabs.class);
                            startActivity(sign);
                            finish();

                        } else {

                            final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Login.this);
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
                        Toast.makeText(Login.this, "Please try again in some time", Toast.LENGTH_SHORT)
                                .show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else {

                Toast.makeText(Login.this, "Please try again in some time", Toast.LENGTH_LONG).show();
            }

        }
    }

    class Forgot_pw_seeker extends AsyncTask<String, String, String> {

        JSONParser jsonParser = new JSONParser();
        ProgressDialog pDialog;
        String msg, success;
        JSONObject json;

        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub
            super.onPreExecute();

            pDialog = new ProgressDialog(Login.this);
            pDialog.setMessage("Loading...");
            pDialog.setCancelable(false);
            pDialog.show();
        }

        @Override
        protected String doInBackground(String... names) {
            // TODO Auto-generated method stub
            List<NameValuePair> params = new ArrayList<NameValuePair>();

            params.add(new BasicNameValuePair("email", fp_empl_email.getText().toString()));

            params.add(new BasicNameValuePair("tag", "employeeSforgetpass"));
            params.add(new BasicNameValuePair("submit", "resetpassword"));


            json = jsonParser.makeHttpRequest(UrlPrifix + "index.php", "POST", params);
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
                try {
                    msg = json.getString("error_msg");
                    success = json.getString("error");
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                if (!(success == null)) {
                    if (success.equals("false")) {
                        Toast.makeText(Login.this, msg, Toast.LENGTH_SHORT)
                                .show();
                        dialog_main.dismiss();

                    } else {

                        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Login.this);
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
                    Toast.makeText(Login.this, "Please try again in some time", Toast.LENGTH_SHORT)
                            .show();
                }

            } else {

                Toast.makeText(Login.this, "Please try again in some time", Toast.LENGTH_LONG).show();
            }

        }
    }

    public AlertDialog.Builder buildDialog(Context c) {

        AlertDialog.Builder builder = new AlertDialog.Builder(c);
        builder.setTitle("No Internet connectivity.");
        builder.setMessage("You have no internet connectivity");
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                dialog.dismiss();
            }
        });

        return builder;
    }

    public boolean isConnected(Context context) {

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netinfo = cm.getActiveNetworkInfo();

        if (netinfo != null && netinfo.isConnectedOrConnecting()) {
            android.net.NetworkInfo wifi = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            android.net.NetworkInfo mobile = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

            if ((mobile != null && mobile.isConnectedOrConnecting())
                    || (wifi != null && wifi.isConnectedOrConnecting()))
                return true;
            else
                return false;
        } else
            return false;
    }

}
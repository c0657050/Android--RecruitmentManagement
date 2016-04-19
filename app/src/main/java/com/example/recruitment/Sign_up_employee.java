package com.example.recruitment;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
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
 * Created by Toshif on 20-03-2016.
 */

public class Sign_up_employee extends ActionBarActivity {

    TextView submit_employee;
    ImageView img_sign_employee;
    EditText employee_fname,employee_lname,employee_email,employee_qua,employee_exp,employee_add,employee_pw,employee_cpw,employee_cont;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_employee);
        getSupportActionBar().hide();

        employee_fname= (EditText) findViewById(R.id.employee_fname_edit);
        employee_lname= (EditText) findViewById(R.id.employee_lname_edit);
        employee_email= (EditText) findViewById(R.id.employee_email_edit);
        employee_pw=(EditText) findViewById(R.id.employee_pw_edit);
        employee_cpw=(EditText) findViewById(R.id.employee_cpw_edit);
        employee_cont = (EditText) findViewById(R.id.employee_cont_edit);

        submit_employee= (TextView) findViewById(R.id.employee_submit);
        img_sign_employee = (ImageView) findViewById(R.id.img_sign_employee);


        img_sign_employee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        submit_employee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (employee_fname.length()==0){
                    Toast.makeText(Sign_up_employee.this,"Please enter First name",Toast.LENGTH_SHORT).show();

                    return;
                }
                if (employee_lname.length()==0){
                    Toast.makeText(Sign_up_employee.this,"Please enter Last name",Toast.LENGTH_SHORT).show();

                    return;
                }
                if (employee_email.length()==0){
                    Toast.makeText(Sign_up_employee.this,"Please enter email id",Toast.LENGTH_SHORT).show();

                    return;
                }
                if (!isValidEmail(employee_email.getText().toString())){

                    Toast.makeText(Sign_up_employee.this, "Please enter valid Email ID", Toast.LENGTH_LONG).show();
                    return;

                }
                if (employee_cont.length()==0){
                    Toast.makeText(Sign_up_employee.this,"Please enter contact number",Toast.LENGTH_SHORT).show();

                    return;
                }

                if (employee_pw.length()==0){
                    Toast.makeText(Sign_up_employee.this,"Please enter password",Toast.LENGTH_SHORT).show();

                    return;
                }
                if (employee_cpw.length()==0){
                    Toast.makeText(Sign_up_employee.this,"Please confirm password",Toast.LENGTH_SHORT).show();

                    return;
                }
                if (!employee_pw.getText().toString().equals(employee_cpw.getText().toString())) {

                    Toast.makeText(Sign_up_employee.this, "Your password not matched", Toast.LENGTH_SHORT).show();
                    return;

                }
                else {
                    if (!isConnected(Sign_up_employee.this))
                        buildDialog(Sign_up_employee.this).show();

                    else {

                        new Sign_up_emp().execute();
                    }
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
    class Sign_up_emp extends AsyncTask<String, String, String> {

        JSONParser jsonParser = new JSONParser();
        ProgressDialog pDialog;
        String msg,success;
        JSONObject json;

        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub
            super.onPreExecute();

            pDialog = new ProgressDialog(Sign_up_employee.this);
            pDialog.setMessage("Loading...");
            pDialog.setCancelable(false);
            pDialog.show();
        }
        @Override
        protected String doInBackground(String... names) {
            // TODO Auto-generated method stub
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("firstname", employee_fname.getText().toString()));
            params.add(new BasicNameValuePair("lastname", employee_lname.getText().toString()));
            params.add(new BasicNameValuePair("phone",employee_cont.getText().toString()));
            params.add(new BasicNameValuePair("email", employee_email.getText().toString()));
            params.add(new BasicNameValuePair("password", employee_pw.getText().toString()));

            params.add(new BasicNameValuePair("submit", "SignUp"));
            params.add(new BasicNameValuePair("tag","employeeSsignup"));

            json = jsonParser.makeHttpRequest(Login.UrlPrifix +"index.php", "POST", params);
            System.out.println("sign upppppppp >>>>>>>>>>>>>" + json.toString());
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
                        Toast.makeText(Sign_up_employee.this, "Sign up successful !", Toast.LENGTH_SHORT)
                                .show();
                        finish();
                    }
                    else {

                        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Sign_up_employee.this);
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
                    Toast.makeText(Sign_up_employee.this, "Please try again in some time", Toast.LENGTH_SHORT)
                            .show();
                }

            } else {

                Toast.makeText(Sign_up_employee.this, "Please try again in some time", Toast.LENGTH_LONG).show();
            }

        }
    }

    public AlertDialog.Builder buildDialog(Context c) {

        AlertDialog.Builder builder = new AlertDialog.Builder(c);
        builder.setTitle("No Internet connectivity.");
        builder.setMessage("You have no internet connectivity");
        // builder.setIcon(R.drawable.cross);
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

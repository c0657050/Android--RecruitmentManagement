package com.example.recruitment;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Created by Toshif on 24-03-2016.
 */

public class Resume_create_employee extends ActionBarActivity {

    EditText res_location ,res_fname,res_lname,res_obj,res_intrest,res_skills,res_position,res_qualifctn,res_strength,res_ext;

    TextView res_gender,res_exp,res_sub,res_dob;

    ImageView img_res_epmy_back;
    AlertDialog dialog;

    String[] str_gender= {"Male","Female"};
    String[] str_exp= {"0","1","2","3","4","5","6","7","8","9","10","11","12",
            "13","14","15","16","17","18","19","20","21","22","23","24","25"};

    String seeker_id_s ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resume_create_employee);
        getSupportActionBar().hide();

        seeker_id_s = Util.getSharedPreferences(Resume_create_employee.this,"seekerid_preference");
        res_fname = (EditText) findViewById(R.id.res_fname);
        res_lname = (EditText) findViewById(R.id.res_lname);
        res_dob = (TextView) findViewById(R.id.res_dob);
        res_obj = (EditText) findViewById(R.id.res_obj);
        res_intrest = (EditText) findViewById(R.id.res_area_int);
        res_skills = (EditText) findViewById(R.id.res_primary);
        res_strength = (EditText) findViewById(R.id.res_strength);
        res_qualifctn = (EditText) findViewById(R.id.res_quali);
        res_position = (EditText) findViewById(R.id.res_position);
        res_ext = (EditText) findViewById(R.id.res_extra);
        res_gender = (TextView) findViewById(R.id.res_gender);
        res_exp = (TextView) findViewById(R.id.res_exp);
        res_location = (EditText) findViewById(R.id.res_prefered_loc);
        res_sub = (TextView) findViewById(R.id.res_submit);

        img_res_epmy_back= (ImageView) findViewById(R.id.img_res_epmy_back);

        img_res_epmy_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();
            }
        });

        res_dob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SelectDateTime();
            }
        });
        res_gender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Resume_create_employee.this);
                builder.setTitle("Select");
                ListView list = new ListView(Resume_create_employee.this);

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(Resume_create_employee.this, R.layout.shortlistitem,
                        R.id.textView1, str_gender);

                list.setAdapter(adapter);
                list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                    @Override
                    public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                        // TODO Auto-generated method stub
                        if (dialog.isShowing()) {

                            dialog.dismiss();
                        }
                        res_gender.setText(str_gender[position]);
                        res_gender.setTextColor(Color.parseColor("#000000"));
                    }
                });
                builder.setView(list);
                dialog = builder.create();
                dialog.show();
            }
        });

        res_exp.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                AlertDialog.Builder builder = new AlertDialog.Builder(Resume_create_employee.this);
                builder.setTitle("Select");
                ListView list = new ListView(Resume_create_employee.this);

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(Resume_create_employee.this, R.layout.shortlistitem,
                        R.id.textView1, str_exp);

                list.setAdapter(adapter);
                list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                    @Override
                    public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                        // TODO Auto-generated method stub
                        if (dialog.isShowing()) {

                            dialog.dismiss();


                        }
                        res_exp.setText(str_exp[position]+ "  years");
                        res_exp.setTextColor(Color.parseColor("#000000"));
                    }
                });
                builder.setView(list);
                dialog = builder.create();
                dialog.show();
            }
        });
        res_sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (res_fname.length()==0){

                    Toast.makeText(Resume_create_employee.this,"Please enter first name",Toast.LENGTH_SHORT).show();
                    return;
                }
                if (res_lname.length()==0){

                    Toast.makeText(Resume_create_employee.this,"Please enter last name",Toast.LENGTH_SHORT).show();
                    return;
                }
                if (res_dob.getText().toString()==""){

                    Toast.makeText(Resume_create_employee.this,"Please enter DOB",Toast.LENGTH_SHORT).show();
                    return;
                }
                if (res_gender.getText().toString()==null){

                    Toast.makeText(Resume_create_employee.this,"Please select gender",Toast.LENGTH_SHORT).show();
                    return;
                }
                if (res_obj.length()==0){

                    Toast.makeText(Resume_create_employee.this,"Please enter your objective",Toast.LENGTH_SHORT).show();
                    return;
                }
                if (res_intrest.length()==0){

                    Toast.makeText(Resume_create_employee.this,"Please enter area of interest",Toast.LENGTH_SHORT).show();
                    return;
                }
                if (res_skills.length()==0){

                    Toast.makeText(Resume_create_employee.this,"Please enter your primary skills",Toast.LENGTH_SHORT).show();
                    return;
                }
                if (res_exp.getText().toString()==""){

                    Toast.makeText(Resume_create_employee.this,"Please select experience",Toast.LENGTH_SHORT).show();
                    return;
                }
                if (res_position.length()==0){

                    Toast.makeText(Resume_create_employee.this,"Please enter position",Toast.LENGTH_SHORT).show();
                    return;
                }
                if (res_location.getText().toString()==null){

                    Toast.makeText(Resume_create_employee.this,"Please select preferred location",Toast.LENGTH_SHORT).show();
                    return;
                }
                if (res_qualifctn.length()==0){

                    Toast.makeText(Resume_create_employee.this,"Please enter qualification",Toast.LENGTH_SHORT).show();
                    return;
                }
                if (res_strength.length()==0){

                    Toast.makeText(Resume_create_employee.this,"Please enter strength",Toast.LENGTH_SHORT).show();
                    return;
                }
                if (res_ext.length()==0){

                    Toast.makeText(Resume_create_employee.this,"Please enter extra curricular",Toast.LENGTH_SHORT).show();

                }
                else {
                    if (!Util.isConnected(Resume_create_employee.this))Util. buildDialog(Resume_create_employee.this);
                    else  {
                        new Resume_emp().execute();
                    }
                }
            }
        });

    }
    public void SelectDateTime() {
        final View dialogView = View.inflate(this, R.layout.dialog, null);
        final AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        dialogView.findViewById(R.id.datetimeset).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePicker datePicker = (DatePicker) dialogView.findViewById(R.id.datepicker);

                Calendar calendar = new GregorianCalendar(datePicker.getYear(), datePicker.getMonth(),
                        datePicker.getDayOfMonth());
                int day = datePicker.getDayOfMonth();
                int month = datePicker.getMonth();
                int year = datePicker.getYear();
                // 03/25/2016

                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                String  formatedDate = sdf.format(new Date(year - 1900, month, day));
                res_dob.setText(formatedDate);
                res_dob.setTextColor(Color.parseColor("#000000"));
                alertDialog.dismiss();

            }
        });
        alertDialog.setView(dialogView);
        alertDialog.show();
    }


    class Resume_emp extends AsyncTask<String, String, String> {

        JSONParser jsonParser = new JSONParser();
        ProgressDialog pDialog;
        String msg,success;
        JSONObject json;

        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub
            super.onPreExecute();

            pDialog = new ProgressDialog(Resume_create_employee.this);
            pDialog.setMessage("Loading...");
            pDialog.setCancelable(false);
            pDialog.show();
        }

        @Override
        protected String doInBackground(String... names) {
            // TODO Auto-generated method stub
            List<NameValuePair> params = new ArrayList<NameValuePair>();

            params.add(new BasicNameValuePair("seekerid",seeker_id_s));
            params.add(new BasicNameValuePair("firstname",res_fname.getText().toString()));
            params.add(new BasicNameValuePair("lastname",res_lname.getText().toString()));
            params.add(new BasicNameValuePair("dob", res_dob.getText().toString()));
            params.add(new BasicNameValuePair("gender",res_gender.getText().toString()));
            params.add(new BasicNameValuePair("objective", res_obj.getText().toString()));
            params.add(new BasicNameValuePair("area_of_interest",res_intrest.getText().toString()));
            params.add(new BasicNameValuePair("primary_skills", res_skills.getText().toString()));
            params.add(new BasicNameValuePair("experience",res_exp.getText().toString()));
            params.add(new BasicNameValuePair("position", res_position.getText().toString()));
            params.add(new BasicNameValuePair("preferred_location",res_location.getText().toString()));
            params.add(new BasicNameValuePair("highest_qualification",res_qualifctn.getText().toString()));
            params.add(new BasicNameValuePair("strength", res_strength.getText().toString()));
            params.add(new BasicNameValuePair("extra_curricular",res_ext.getText().toString()));
            params.add(new BasicNameValuePair("submit", "submit"));
            params.add(new BasicNameValuePair("tag", "employeeSresumeAdd"));

            json = jsonParser.makeHttpRequest(Login.UrlPrifix +"index.php", "POST", params);
            System.out.println("resume >>>>>>>>>>>>>" + json.toString());
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

                        Intent in_profile = new Intent(Resume_create_employee.this,Profile_employee.class);
                        startActivity(in_profile);
                        finish();
                        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Resume_create_employee.this);
                        final AlertDialog alertDialog = alertDialogBuilder.create();

                        alertDialog.setTitle("Congratulations!!");
                        alertDialog.setMessage("Your resume saved");
                        alertDialog.setCancelable(false);
                        alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(final DialogInterface dialog, final int which) {
                                dialog.dismiss();
                                finish();
                            }
                        });
                        alertDialog.show();
                    }
                    else {

                        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Resume_create_employee.this);
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
                    Toast.makeText(Resume_create_employee.this, "Please try again in some time", Toast.LENGTH_SHORT)
                            .show();
                }
            } else {

                Toast.makeText(Resume_create_employee.this, "Please try again in some time", Toast.LENGTH_LONG).show();
            }

        }
    }

}

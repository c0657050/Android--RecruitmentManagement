package com.example.recruitment;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Toshif on 20-03-2016.
 */

public class Profile_employee extends ActionBarActivity {

    TextView profile_user_id,profile_res_fname,profile_lname,profile_dob,profile_obj,profile_intrest,profile_skills,
            profile_position,profile_qualifctn,profile_strength,profile_ext,profile_gender,profile_exp,profile_location;
    ImageView img_back_pro;
    String seeker_id_p;
    String str_resumeid_emp ,str_firstname_emp , str_lastname_emp, str_gender_emp ,
            str_objective_emp, str_area_of_interest_emp , str_primary_skills_emp,
            str_experience_emp , str_position_emp ,str_strength_emp,
            str_extra_curricular_emp , str_dob_emp ,str_location_emp, str_qualification_emp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_employee);
        getSupportActionBar().hide();

        seeker_id_p = Util.getSharedPreferences(Profile_employee.this,"seekerid_preference");

        // profile_user_id = (TextView) findViewById(R.id.res_user_id);
        profile_res_fname = (TextView) findViewById(R.id.profile_fname);
        profile_lname = (TextView) findViewById(R.id.profile_lname);
        profile_dob = (TextView) findViewById(R.id.profile_dob);
        profile_obj = (TextView) findViewById(R.id.profile_obj);
        profile_intrest = (TextView) findViewById(R.id.profile_area_int);
        profile_skills = (TextView) findViewById(R.id.profile_primary);
        profile_position = (TextView) findViewById(R.id.profile_strength);
        profile_qualifctn = (TextView) findViewById(R.id.profile_quali);
        profile_strength = (TextView) findViewById(R.id.profile_position);
        profile_ext = (TextView) findViewById(R.id.profile_extra);

        profile_gender = (TextView) findViewById(R.id.profile_gender);
        profile_exp = (TextView) findViewById(R.id.profile_exp);
        profile_location = (TextView) findViewById(R.id.profile_prefered_loc);


        img_back_pro= (ImageView) findViewById(R.id.img_profile_epmy_back);
        if (!Util.isConnected(Profile_employee.this))Util.buildDialog(Profile_employee.this).show();
        else {
            new Profile_emp().execute();
        }
        img_back_pro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();
            }
        });

    }

    class Profile_emp extends AsyncTask<String, String, String> {

        JSONParser jsonParser = new JSONParser();
        ProgressDialog pDialog;
        String msg,success;
        JSONObject json;

        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub
            super.onPreExecute();

            pDialog = new ProgressDialog(Profile_employee.this);
            pDialog.setMessage("Loading...");
            pDialog.setCancelable(false);
            pDialog.show();
        }

        @Override
        protected String doInBackground(String... names) {
            // TODO Auto-generated method stub
            List<NameValuePair> params = new ArrayList<NameValuePair>();

            params.add(new BasicNameValuePair("tag", "employeeSresumeView"));
            params.add(new BasicNameValuePair("seekerid",seeker_id_p));
            params.add(new BasicNameValuePair("resumeid", ""));
            params.add(new BasicNameValuePair("submit","submit"));


            json = jsonParser.makeHttpRequest(Login.UrlPrifix +"index.php", "POST", params);
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


                    if (!(success == null)) {
                        if (success.equals("false")) {

                            JSONArray resumeid_emp = json.getJSONArray("resumeid");
                            JSONArray firstname_emp = json.getJSONArray("firstname");
                            JSONArray lastname_emp = json.getJSONArray("lastname");
                            JSONArray gender_emp = json.getJSONArray("gender");
                            JSONArray objective_emp = json.getJSONArray("objective");
                            JSONArray area_of_interest_emp = json.getJSONArray("area_of_interest");
                            JSONArray primary_skills_emp = json.getJSONArray("primary_skills");
                            JSONArray experience_emp = json.getJSONArray("experience");
                            JSONArray dob_emp = json.getJSONArray("dob");
                            JSONArray preferred_location_emp = json.getJSONArray("preferred_location");
                            JSONArray qualification_emp = json.getJSONArray("highest_qualification");

                            JSONArray position_emp = json.getJSONArray("position");
                            JSONArray strength_emp = json.getJSONArray("strength");
                            JSONArray extra_curricular_emp = json.getJSONArray("extra_curricular");


   for (int i = 0; i < resumeid_emp.length(); i++) {
       str_resumeid_emp  =  resumeid_emp.getString(i);

       str_firstname_emp=firstname_emp.getString(i);
       str_lastname_emp=lastname_emp.getString(i);
       str_gender_emp=gender_emp.getString(i);
       str_objective_emp=objective_emp.getString(i);
       str_area_of_interest_emp=area_of_interest_emp.getString(i);
       str_primary_skills_emp=primary_skills_emp.getString(i);
       str_experience_emp=experience_emp.getString(i);

       str_dob_emp=dob_emp.getString(i);
       str_position_emp=position_emp.getString(i);
       str_strength_emp=strength_emp.getString(i);
       str_extra_curricular_emp=extra_curricular_emp.getString(i);
       str_location_emp=preferred_location_emp.getString(i);
       str_qualification_emp=qualification_emp.getString(i);

                            }

                            profile_res_fname.setText("First Name : "+ str_firstname_emp);
                            profile_lname.setText("Last Name : " +str_lastname_emp);
                            profile_dob.setText("DOB : "+str_dob_emp);
                            profile_obj.setText("Objective :"+ str_objective_emp);
                            profile_intrest.setText("Area Of Interest : " +str_area_of_interest_emp);
                            profile_skills.setText("Primary Skills : "+str_primary_skills_emp.toString());
                            profile_position.setText("Position : "+str_position_emp.toString());
                            profile_qualifctn.setText("Highest Qualification : "+str_qualification_emp.toString());
                            profile_strength.setText("Strength : "+str_strength_emp.toString());
                            profile_ext.setText("Extra Curricular : "+str_extra_curricular_emp.toString());
                            profile_gender.setText("Gender : "+str_gender_emp.toString());
                            profile_exp.setText("Experience : "+str_experience_emp.toString());
                            profile_location.setText("Location : "+ str_location_emp.toString());



                            profile_res_fname.setTextColor(Color.parseColor("#000000"));
                            profile_lname.setTextColor(Color.parseColor("#000000"));
                            profile_dob.setTextColor(Color.parseColor("#000000"));
                            profile_obj.setTextColor(Color.parseColor("#000000"));
                            profile_intrest.setTextColor(Color.parseColor("#000000"));
                            profile_skills.setTextColor(Color.parseColor("#000000"));
                            profile_position.setTextColor(Color.parseColor("#000000"));
                            profile_qualifctn.setTextColor(Color.parseColor("#000000"));
                            profile_strength.setTextColor(Color.parseColor("#000000"));
                            profile_ext.setTextColor(Color.parseColor("#000000"));
                            profile_gender.setTextColor(Color.parseColor("#000000"));
                            profile_exp.setTextColor(Color.parseColor("#000000"));
                            profile_location.setTextColor(Color.parseColor("#000000"));
                        }
                        else {

                            final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Profile_employee.this);
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
                        Toast.makeText(Profile_employee.this, "Please try again in some time", Toast.LENGTH_SHORT)
                                .show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else {

                Toast.makeText(Profile_employee.this, "Please try again in some time", Toast.LENGTH_LONG).show();
            }

        }
    }
}

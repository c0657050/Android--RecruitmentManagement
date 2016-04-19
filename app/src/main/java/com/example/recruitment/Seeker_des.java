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
 * Created by Toshif on 21-03-2016.
 */

public class Seeker_des extends ActionBarActivity {

    TextView s_user_id, s_res_fname, s_lname, s_dob, s_obj, s_intrest, s_skills,
            s_position, s_qualifctn, s_strength, s_ext, s_gender, s_exp, s_location;
    ImageView img_back_s;
    String seekers, recruiter_id;
    String re_seek, f_seek, l_seek, dob_seek, gender_seek, objective_seek, interest_seek, skills_seek,
            exp_seek, qua_seek, strnght_seek, extra_seek, position_seek;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seeker_des);
        getSupportActionBar().hide();

        recruiter_id = Util.getSharedPreferences(Seeker_des.this, "recruiterid_preference");

        Intent i = getIntent();
        seekers = i.getStringExtra("seeker's_id");

        s_res_fname = (TextView) findViewById(R.id.skr_des_fname);
        s_lname = (TextView) findViewById(R.id.skr_des_lname);
        s_dob = (TextView) findViewById(R.id.skr_des_dob);
        s_obj = (TextView) findViewById(R.id.skr_des_obj);
        s_intrest = (TextView) findViewById(R.id.skr_des_area_int);
        s_skills = (TextView) findViewById(R.id.skr_des_primary);
        s_position = (TextView) findViewById(R.id.skr_des_strength);
        s_qualifctn = (TextView) findViewById(R.id.skr_des_quali);
        s_strength = (TextView) findViewById(R.id.skr_des_position);
        s_ext = (TextView) findViewById(R.id.skr_des_extra);


        s_gender = (TextView) findViewById(R.id.skr_des_gender);
        s_exp = (TextView) findViewById(R.id.skr_des_exp);
        //s_location = (TextView) findViewById(R.id.skr_des_prefered_loc);


        img_back_s = (ImageView) findViewById(R.id.img_skr_des);

        img_back_s.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();
            }
        });

        if (!Util.isConnected(Seeker_des.this)) Util.buildDialog(Seeker_des.this).show();

        else {

            new Description_seeker().execute();
        }
    }

    class Description_seeker extends AsyncTask<String, String, String> {

        JSONParser jsonParser = new JSONParser();
        ProgressDialog pDialog;
        String msg, success;
        JSONObject json;

        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub
            super.onPreExecute();

            pDialog = new ProgressDialog(Seeker_des.this);
            pDialog.setMessage("Loading...");
            pDialog.setCancelable(false);
            pDialog.show();
        }

        @Override
        protected String doInBackground(String... names) {
            // TODO Auto-generated method stub
            List<NameValuePair> params = new ArrayList<NameValuePair>();

            params.add(new BasicNameValuePair("seekerid", seekers));
            params.add(new BasicNameValuePair("recruiterid", recruiter_id));
            params.add(new BasicNameValuePair("tag", "employeeSresumeList"));
            params.add(new BasicNameValuePair("submit", "submit"));
            System.out.println(seekers + "   " + recruiter_id);

            json = jsonParser.makeHttpRequest(Login.UrlPrifix + "index.php", "POST", params);
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

                            JSONArray resumeid_see = json.getJSONArray("resumeid");
                            JSONArray firstname_see = json.getJSONArray("firstname");
                            JSONArray lastname_see = json.getJSONArray("lastname");
                            JSONArray dob_see = json.getJSONArray("dob");
                            JSONArray gender_see = json.getJSONArray("gender");
                            JSONArray objective_see = json.getJSONArray("objective");
                            JSONArray area_of_interest_see = json.getJSONArray("area_of_interest");
                            JSONArray primary_skills_see = json.getJSONArray("primary_skills");
                            JSONArray position_see = json.getJSONArray("position");
                            JSONArray experience_see = json.getJSONArray("experience");

                            JSONArray highest_qualification_see = json.getJSONArray("highest_qualification");
                            JSONArray strength_see = json.getJSONArray("strength");
                            JSONArray extra_curricular_see = json.getJSONArray("extra_curricular");

                            for (int i = 0; i < resumeid_see.length(); i++) {

                                re_seek = resumeid_see.getString(i);
                                f_seek = firstname_see.getString(i);
                                l_seek = lastname_see.getString(i);
                                dob_seek = dob_see.getString(i);
                                gender_seek = gender_see.getString(i);
                                objective_seek = objective_see.getString(i);
                                interest_seek = area_of_interest_see.getString(i);
                                skills_seek = primary_skills_see.getString(i);
                                exp_seek = experience_see.getString(i);
                                qua_seek = highest_qualification_see.getString(i);
                                strnght_seek = strength_see.getString(i);
                                extra_seek = extra_curricular_see.getString(i);
                                position_seek = position_see.getString(i);
                            }

                            s_res_fname.setText("First Name : " + f_seek  );
                            s_lname.setText("Last Name : " +l_seek);
                            s_dob.setText("DOB : " +dob_seek);
                            s_obj.setText("Objective : " +objective_seek);
                            s_intrest.setText("Area Of Interest: " +interest_seek);
                            s_skills.setText("Skills : " +skills_seek);
                            s_position.setText("Position : " +position_seek);
                            s_qualifctn.setText("Qualification : " +qua_seek);
                                    s_strength.setText("Strength : " +strnght_seek);
                                    s_ext.setText("Extra Curricular : " +extra_seek);
                                    s_gender.setText("Gender : " +gender_seek);
                                    s_exp.setText("Experience : " +exp_seek);

                            String clr = "#000000";
                            s_res_fname.setTextColor(Color.parseColor(clr));
                            s_lname.setTextColor(Color.parseColor(clr));
                           s_dob.setTextColor(Color.parseColor(clr));
                            s_obj.setTextColor(Color.parseColor(clr));

                            s_intrest.setTextColor(Color.parseColor(clr));
                            s_skills.setTextColor(Color.parseColor(clr));
                            s_position.setTextColor(Color.parseColor(clr));
                            s_qualifctn.setTextColor(Color.parseColor(clr));

                            s_strength.setTextColor(Color.parseColor(clr));
                            s_ext.setTextColor(Color.parseColor(clr));
                            s_gender.setTextColor(Color.parseColor(clr));
                            s_exp.setTextColor(Color.parseColor(clr));

                        } else {

                            final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Seeker_des.this);
                            final AlertDialog alertDialog = alertDialogBuilder.create();

                            alertDialog.setTitle("Oops!!");
                            alertDialog.setMessage("No data found");
                            alertDialog.setCancelable(false);

                            alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
                                public void onClick(final DialogInterface dialog, final int which) {

                                finish();
                                    dialog.dismiss();
                                }
                            });
                            alertDialog.show();
                        }

                    } else {
                        Toast.makeText(Seeker_des.this, "Please try again in some time", Toast.LENGTH_SHORT)
                                .show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else {

                Toast.makeText(Seeker_des.this, "Please try again in some time", Toast.LENGTH_LONG).show();
            }

        }
    }
}

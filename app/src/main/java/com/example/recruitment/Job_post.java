package com.example.recruitment;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
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

/**
 * Created by Toshif on 27-03-2016.
 */

public class Job_post extends ActionBarActivity {
    ImageView img_job_top1;
    EditText job_title,job_details,job_id,exp,job_type,location,role,salary,name,email_job_owner,compny_name;
    TextView submit_job;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jobt_post);
        getSupportActionBar().hide();


        job_id  = (EditText) findViewById(R.id.job_id);
        compny_name  = (EditText) findViewById(R.id.compny_name);
        job_title  = (EditText) findViewById(R.id.job_title);
        job_details  = (EditText) findViewById(R.id.job_details);

        exp  = (EditText) findViewById(R.id.exp);
        job_type  = (EditText) findViewById(R.id.job_type);
        location  = (EditText) findViewById(R.id.location);
        role  = (EditText) findViewById(R.id.role);
        salary  = (EditText) findViewById(R.id.salary);

        email_job_owner  = (EditText) findViewById(R.id.email_job_owner);

        submit_job= (TextView) findViewById(R.id.submit_job);


        submit_job.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (job_id.length()==0){

                    Toast.makeText(Job_post.this,"Please enter job ID",Toast.LENGTH_SHORT).show();
                    return;

                }
                if (compny_name.length()==0){

                    Toast.makeText(Job_post.this,"Please enter company name",Toast.LENGTH_SHORT).show();

                    return;
                }

                if (job_title.length()==0){

                    Toast.makeText(Job_post.this,"Please enter job title",Toast.LENGTH_SHORT).show();
                    return;

                }
                if (job_details.length()==0){

                    Toast.makeText(Job_post.this,"Please enter job details",Toast.LENGTH_SHORT).show();
                    return;

                }
                if (salary.length()==0){

                    Toast.makeText(Job_post.this,"Please enter industry",Toast.LENGTH_SHORT).show();
                    return;

                }
                if (exp.length()==0){

                    Toast.makeText(Job_post.this,"Please enter experience",Toast.LENGTH_SHORT).show();
                    return;

                }
                if (location.length()==0){

                    Toast.makeText(Job_post.this,"Please enter location",Toast.LENGTH_SHORT).show();
                    return;

                }
                if (role.length()==0){

                    Toast.makeText(Job_post.this,"Please enter role",Toast.LENGTH_SHORT).show();
                    return;

                }
                if (job_type.length()==0){

                    Toast.makeText(Job_post.this,"Please enter max. experience",Toast.LENGTH_SHORT).show();
                    return;

                }


                if (name.length()==0){

                    Toast.makeText(Job_post.this,"Please enter name",Toast.LENGTH_SHORT).show();
                    return;

                }
                if (email_job_owner.length()==0){

                    Toast.makeText(Job_post.this,"Please enter email",Toast.LENGTH_SHORT).show();


                }

                else {

                    new Post_job().execute();
                }

            }
        });

    }

    public void onResume() {

        // Set title
        ((Home_employer)getApplicationContext()).setActionBarTitle("Post New Job");
        super.onResume();
    }
    class Post_job extends AsyncTask<String, String, String> {

        JSONParser jsonParser = new JSONParser();
        ProgressDialog pDialog;
        String msg,success;
        JSONObject json;

        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub
            super.onPreExecute();

            pDialog = new ProgressDialog(Job_post.this);
            pDialog.setMessage("Loading...");
            pDialog.setCancelable(false);
            pDialog.show();
        }

        @Override
        protected String doInBackground(String... names) {
            // TODO Auto-generated method stub
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("tag", "recRJobAdd"));
            params.add(new BasicNameValuePair("submit","submit"));
            params.add(new BasicNameValuePair("recruiterid", ""));
            params.add(new BasicNameValuePair("job_title", job_title.getText().toString()));
            params.add(new BasicNameValuePair("job_detail", job_details.getText().toString()));
            params.add(new BasicNameValuePair("salary", salary.getText().toString()));
            params.add(new BasicNameValuePair("location", location.getText().toString()));
            params.add(new BasicNameValuePair("experience", exp.getText().toString()));
            params.add(new BasicNameValuePair("job_type", job_type.getText().toString()));
            params.add(new BasicNameValuePair("role", role.getText().toString()));



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
                            Toast.makeText(Job_post.this, msg, Toast.LENGTH_SHORT)
                                    .show();
                        }


                        else {

                            final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Job_post.this);
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
                        Toast.makeText(Job_post.this, "Please try again in some time", Toast.LENGTH_SHORT)
                                .show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else {

                Toast.makeText(Job_post.this, "Please try again in some time", Toast.LENGTH_LONG).show();
            }

        }
    }
}

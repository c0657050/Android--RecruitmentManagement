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
import android.widget.Button;
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

public class Find_job_des_employee extends ActionBarActivity {
    Button submit_job_show_find;
    TextView job_title_show_find, job_details_show_find, job_id_show_find, exp_show_find, job_type_show_find,
            location_show_find, role_show_find, salary_show_find, name_show_find,
            email_job_owner_show_find, compny_name_show_find;

    ImageView img_job_edit_show_find;
    String seeker_id_s_des;
    String job_id_fd , titel_fd,details_fd,salary_fd,exp_fd,location_fd,role_fd,type_fd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_job_des_employee);
        getSupportActionBar().hide();

        Intent i = getIntent();
        job_id_fd=   i.getStringExtra("id");
        titel_fd=  i.getStringExtra("job_title");
        details_fd=  i.getStringExtra("details");
        salary_fd=   i.getStringExtra("salary");
        exp_fd=i.getStringExtra("exp");
        location_fd= i.getStringExtra("location");
        role_fd= i.getStringExtra("role");
        type_fd=   i.getStringExtra("type");

        System.out.println("ddddddddd>>>>>>>  " +job_id_fd+ " "+ titel_fd+" "+
                        details_fd+ " "+ salary_fd+" "+exp_fd
                        +location_fd+ " "+ role_fd+" "+type_fd
        );
        seeker_id_s_des = Util.getSharedPreferences(Find_job_des_employee.this, "seekerid_preference");

        img_job_edit_show_find = (ImageView) findViewById(R.id.img_job_find_show);
        submit_job_show_find = (Button) findViewById(R.id.submit_job_show_find);

        job_id_show_find = (TextView) findViewById(R.id.job_id_show_find);
       // compny_name_show_find = (TextView) findViewById(R.id.compny_name_show_find);
        job_title_show_find = (TextView) findViewById(R.id.job_title_show_find);
        job_details_show_find = (TextView) findViewById(R.id.job_details_show_find);

        exp_show_find = (TextView) findViewById(R.id.exp_show_find);
        job_type_show_find = (TextView) findViewById(R.id.job_type_show_find);
        location_show_find = (TextView) findViewById(R.id.location_show_find);
        role_show_find = (TextView) findViewById(R.id.role_show_find);
        salary_show_find = (TextView) findViewById(R.id.salary_show_find);
       // name_show_find = (TextView) findViewById(R.id.name_show_find);
      //  email_job_owner_show_find = (TextView) findViewById(R.id.email_job_owner_show_find);

        job_id_show_find.setText("Job id : "+ job_id_fd);
        job_title_show_find.setText("Job title : "+ titel_fd);
        job_details_show_find.setText("Details : "+ details_fd);
        exp_show_find.setText("Experience : " + exp_fd);
        role_show_find.setText("Role :"+role_fd);
        salary_show_find.setText("Salary : "+ salary_fd);
        //name_show_find.setText("Name : ");
      //  email_job_owner_show_find.setText("Email id : ");
        location_show_find.setText("Location : " +location_fd);
        job_type_show_find.setText("Job type : "+type_fd);
      //  compny_name_show_find.setText("Company : ");


        String colr = "#000000";
        job_id_show_find.setTextColor(Color.parseColor(colr));
        job_title_show_find.setTextColor(Color.parseColor(colr));
        job_details_show_find.setTextColor(Color.parseColor(colr));
        exp_show_find.setTextColor(Color.parseColor(colr));
        salary_show_find.setTextColor(Color.parseColor(colr));
        location_show_find.setTextColor(Color.parseColor(colr));
        job_type_show_find.setTextColor(Color.parseColor(colr));
        role_show_find.setTextColor(Color.parseColor(colr));
      //  email_job_owner_show_find.setTextColor(Color.parseColor(colr));
      //  name_show_find.setTextColor(Color.parseColor(colr));
      //  compny_name_show_find.setTextColor(Color.parseColor(colr));


        img_job_edit_show_find.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        submit_job_show_find.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!Util.isConnected(Find_job_des_employee.this))
                    Util.buildDialog(Find_job_des_employee.this).show();
                else {
                    new Apply_job().execute();
                }
               /* Intent i = new Intent(Find_job_des_employee.this,Resume_create_employee.class);
                startActivity(i);*/
            }
        });
    }

    class Apply_job extends AsyncTask<String, String, String> {

        JSONParser jsonParser = new JSONParser();
        ProgressDialog pDialog;
        String msg, success;
        JSONObject json;

        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub
            super.onPreExecute();

            pDialog = new ProgressDialog(Find_job_des_employee.this);
            pDialog.setMessage("Loading...");
            pDialog.setCancelable(false);
            pDialog.show();
            // mProgressHUD = ProgressHUD.show(Forget_pw.this,"Loading...", true,true);
        }

        @Override
        protected String doInBackground(String... names) {
            // TODO Auto-generated method stub
            List<NameValuePair> params = new ArrayList<NameValuePair>();

            params.add(new BasicNameValuePair("tag", "employeeSjobApply"));
            params.add(new BasicNameValuePair("submit", "submit"));
            params.add(new BasicNameValuePair("jobid", job_id_fd));
            params.add(new BasicNameValuePair("seekerid", seeker_id_s_des));

          /*  <form action="index.php" method="post">
            <input type="hidden" name="tag" value="employeeSjobApply" />
            <input type="hidden" name="jobid" value="1"/>
            <input type="hidden" name="seekerid" value="1"/>
            <input type="submit" name="submit" value="submit"><br>
            </form>*/
            json = jsonParser.makeHttpRequest(Login.UrlPrifix + "index.php", "POST", params);
//            Log.d("prrrrrrrrrrrrrr", "" + json.toString());
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

                    if (!(success == null)) {
                        if (success.equals("false")) {

                            final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Find_job_des_employee.this);
                            final AlertDialog alertDialog = alertDialogBuilder.create();

                            alertDialog.setTitle("Successfully applied for this job");
                            alertDialog.setCancelable(false);
                            //alertDialog.setIcon(R.drawable.iconlogo);
                            alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
                                public void onClick(final DialogInterface dialog, final int which) {


                                    dialog.dismiss();
                                    finish();
                                }
                            });
                            alertDialog.show();


                        } else {

                            final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Find_job_des_employee.this);
                            final AlertDialog alertDialog = alertDialogBuilder.create();

                            alertDialog.setTitle(msg);
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
                        Toast.makeText(Find_job_des_employee.this, "Please try again in some time", Toast.LENGTH_SHORT)
                                .show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else {

                Toast.makeText(Find_job_des_employee.this, "Please try again in some time", Toast.LENGTH_LONG).show();
            }

        }
    }
}

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

public class Edit_posted_job extends ActionBarActivity {
    EditText job_title_edit,job_details_edit,job_id_edit,exp_edit,job_type_edit,location_edit,role_edit,salary_edit,name_edit,
            email_job_owner_edit,compny_name_edit;
    TextView submit_job_edit;
    ImageView img_job_edit_top1;

    String job_id_fd_rese,titel_fd_rese,details_fd_rese,salary_fd_rese,exp_fd_rese,location_fd_rese,role_fd_rese,type_fd_rese,recruiterid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_posted_job);
        getSupportActionBar().hide();



        recruiterid = Util.getSharedPreferences(Edit_posted_job.this, "recruiterid_preference");
        Intent i = getIntent();
        job_id_fd_rese=   i.getStringExtra("id_rese");
        titel_fd_rese=  i.getStringExtra("job_title_rese");
        details_fd_rese=  i.getStringExtra("details_rese");
        salary_fd_rese=   i.getStringExtra("salary_rese");
        exp_fd_rese=i.getStringExtra("exp_rese");
        location_fd_rese= i.getStringExtra("location_rese");
        role_fd_rese= i.getStringExtra("role_rese");
        type_fd_rese=   i.getStringExtra("type_rese");

        String email_prefr,fname_prefr,lname_prefr,company_name_prefr;
        company_name_prefr =    Util.getSharedPreferences(Edit_posted_job.this, "company_name_preference");
        email_prefr  = Util.getSharedPreferences(Edit_posted_job.this, "emaill_preference");
        fname_prefr= Util.getSharedPreferences(Edit_posted_job.this, "firstname_preference");
        lname_prefr=  Util.getSharedPreferences(Edit_posted_job.this, "lastname_preference");

        job_id_edit  = (EditText) findViewById(R.id.job_id_edit);
        compny_name_edit  = (EditText) findViewById(R.id.compny_name_edit);
        job_title_edit  = (EditText) findViewById(R.id.job_title_edit);
        job_details_edit  = (EditText) findViewById(R.id.job_details_edit);

        exp_edit  = (EditText) findViewById(R.id.exp_edit);
        job_type_edit  = (EditText)findViewById(R.id.job_type_edit);
        location_edit  = (EditText) findViewById(R.id.location_edit);
        role_edit  = (EditText)findViewById(R.id.role_edit);
        salary_edit  = (EditText) findViewById(R.id.salary_edit);
        name_edit  = (EditText) findViewById(R.id.name_edit);
        email_job_owner_edit  = (EditText) findViewById(R.id.email_job_owner_edit);

        submit_job_edit= (TextView) findViewById(R.id.submit_job_edit);



        img_job_edit_top1 = (ImageView) findViewById(R.id.img_job_edit_top1);

       /* job_id_edit.setText("Job id : "+job_id_fd_rese);
        job_title_edit.setText("Job title : "+titel_fd_rese);
        job_details_edit.setText("Details : "+details_fd_rese);
        exp_edit.setText("Experience : " +exp_fd_rese);
        role_edit.setText("Role :"+role_fd_rese);
        salary_edit.setText("Salary : "+salary_fd_rese);
        name_edit.setText("Name : ");
        email_job_owner_edit.setText("Email id : ");
        location_edit.setText("Location : " +location_fd_rese);
        job_type_edit.setText("Job type : "+type_fd_rese);
        compny_name_edit.setText("Company : ");*/

        job_id_edit.setText(job_id_fd_rese);
        job_title_edit.setText(titel_fd_rese);
        job_details_edit.setText(details_fd_rese);
        exp_edit.setText(exp_fd_rese );
        role_edit.setText(role_fd_rese);
        salary_edit.setText(salary_fd_rese);
        name_edit.setText(fname_prefr+" "+lname_prefr);
        email_job_owner_edit.setText(email_prefr);
        location_edit.setText(location_fd_rese);
        job_type_edit.setText(type_fd_rese);
        compny_name_edit.setText(company_name_prefr);


        String colr = "#000000";
        job_id_edit.setTextColor(Color.parseColor(colr));
        job_title_edit.setTextColor(Color.parseColor(colr));
        job_details_edit.setTextColor(Color.parseColor(colr));
        exp_edit.setTextColor(Color.parseColor(colr));
        role_edit.setTextColor(Color.parseColor(colr));
        salary_edit.setTextColor(Color.parseColor(colr));
        name_edit.setTextColor(Color.parseColor(colr));
        compny_name_edit.setTextColor(Color.parseColor(colr));
        email_job_owner_edit.setTextColor(Color.parseColor(colr));
        location_edit.setTextColor(Color.parseColor(colr));
        job_type_edit.setTextColor(Color.parseColor(colr));



        img_job_edit_top1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();
            }
        });

        submit_job_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (job_id_edit.length()==0){

                    Toast.makeText(Edit_posted_job.this, "Job ID can not be blank", Toast.LENGTH_SHORT).show();
                    return;

                }
                if (compny_name_edit.length()==0){

                    Toast.makeText(Edit_posted_job.this,"Company name can not be blank",Toast.LENGTH_SHORT).show();

                    return;
                }

                if (job_title_edit.length()==0){

                    Toast.makeText(Edit_posted_job.this,"Job title can not be blank",Toast.LENGTH_SHORT).show();
                    return;

                }
                if (job_details_edit.length()==0){

                    Toast.makeText(Edit_posted_job.this,"Job details can not be blank",Toast.LENGTH_SHORT).show();
                    return;

                }
                if (salary_edit.length()==0){

                    Toast.makeText(Edit_posted_job.this,"Salary can not be blank",Toast.LENGTH_SHORT).show();
                    return;

                }
                if (exp_edit.length()==0){

                    Toast.makeText(Edit_posted_job.this,"Experience can not be blank",Toast.LENGTH_SHORT).show();
                    return;

                }
                if (location_edit.length()==0){

                    Toast.makeText(Edit_posted_job.this,"Location can not be blank",Toast.LENGTH_SHORT).show();
                    return;

                }
                if (role_edit.length()==0){

                    Toast.makeText(Edit_posted_job.this,"Role can not be blank",Toast.LENGTH_SHORT).show();
                    return;

                }
                if (job_type_edit.length()==0){

                    Toast.makeText(Edit_posted_job.this,"Job type can not be blank",Toast.LENGTH_SHORT).show();
                    return;

                }


                if (name_edit.length()==0){

                    Toast.makeText(Edit_posted_job.this,"Name can not be blank",Toast.LENGTH_SHORT).show();
                    return;

                }
                if (email_job_owner_edit.length()==0){

                    Toast.makeText(Edit_posted_job.this,"Email can not be blank",Toast.LENGTH_SHORT).show();


                }

                else {
                    //     Toast.makeText(Edit_posted_job.this,"Your job Updated successfully",Toast.LENGTH_SHORT).show();
                    if (!Util.isConnected(Edit_posted_job.this))Util.buildDialog(Edit_posted_job.this).show();
                    else {
                        new Update_job().execute();
                    }

                  /* Intent jobs= new Intent(getActivity(),Old_jobs.class);
                    startActivity(jobs);*/
                    // getActivity().finish();
                }

            }
        });






    }

    class Update_job extends AsyncTask<String, String, String> {

        JSONParser jsonParser = new JSONParser();
        ProgressDialog pDialog;
        String msg,success;
        JSONObject json;

        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub
            super.onPreExecute();

            pDialog = new ProgressDialog(Edit_posted_job.this);
            pDialog.setMessage("Loading...");
            pDialog.setCancelable(false);
            pDialog.show();
            // mProgressHUD = ProgressHUD.show(Forget_pw.this,"Loading...", true,true);
        }

        @Override
        protected String doInBackground(String... names) {
            // TODO Auto-generated method stub
            List<NameValuePair> params = new ArrayList<NameValuePair>();


            params.add(new BasicNameValuePair("tag", "recRJobUpdate"));
            params.add(new BasicNameValuePair("submit", "submit"));
            params.add(new BasicNameValuePair("recruiterid",recruiterid));
            params.add(new BasicNameValuePair("jobid", job_id_edit.getText().toString()));
            params.add(new BasicNameValuePair("job_detail",job_details_edit.getText().toString()));
            params.add(new BasicNameValuePair("job_title", job_title_edit.getText().toString()));
            params.add(new BasicNameValuePair("salary",salary_edit.getText().toString()));
            params.add(new BasicNameValuePair("experience", exp_edit.getText().toString()));
            params.add(new BasicNameValuePair("role", role_edit.getText().toString()));
            params.add(new BasicNameValuePair("location",location_edit.getText().toString()));
            params.add(new BasicNameValuePair("job_type", job_type_edit.getText().toString()));


            json = jsonParser.makeHttpRequest(Login.UrlPrifix +"index.php", "POST", params);
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
                            /*Toast.makeText(Edit_posted_job.this, msg, Toast.LENGTH_SHORT)
                                    .show();
*/
                            Toast.makeText(Edit_posted_job.this, "Job updated", Toast.LENGTH_SHORT)
                                    .show();

                            finish();
                        }


                        else {

                            final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Edit_posted_job.this);
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
                        Toast.makeText(Edit_posted_job.this, "Please try again in some time", Toast.LENGTH_SHORT)
                                .show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else {

                Toast.makeText(Edit_posted_job.this, "Please try again in some time", Toast.LENGTH_LONG).show();
            }

        }
    }
}
